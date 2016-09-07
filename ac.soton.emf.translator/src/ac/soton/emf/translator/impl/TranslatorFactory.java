/*******************************************************************************
 *  Copyright (c) 2015 University of Southampton.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *  Contributors:
 *  University of Southampton - Initial implementation
 *******************************************************************************/
package ac.soton.emf.translator.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import ac.soton.emf.translator.configuration.IAdapter;
import ac.soton.emf.translator.configuration.IRule;


public class TranslatorFactory {

	// The shared instance
	private static TranslatorFactory factory = null;
	
	
	//cached store of translator configurations that have been loaded from extension points
	private Map<String,TranslatorConfig> translatorConfigRegistry = new HashMap<String, TranslatorConfig  >();
	
	
	/*
	 * The constructor for the shared instance of factory,
	 * populates the registry of translator configurations from extensions point
	 */
	private TranslatorFactory() throws CoreException {

		// populate translator configuration data from registered extensions
		for (final IExtension translatorExtension : Platform.getExtensionRegistry().getExtensionPoint(Identifiers.EXTPT_TRANSLATORS_EXTPTID).getExtensions()) {
			for (final IConfigurationElement translatorExtensionElement : translatorExtension.getConfigurationElements()) {
//				try {
					EPackage rootSourcePackage = EPackage.Registry.INSTANCE.getEPackage(translatorExtensionElement.getAttribute(Identifiers.EXTPT_TRANSLATORS_TRANSLATOR_SOURCEPACKAGE));
					EClassifier rootSourceClass = rootSourcePackage.getEClassifier(translatorExtensionElement.getAttribute(Identifiers.EXTPT_TRANSLATORS_TRANSLATOR_ROOTSOURCECLASS));
					String translatorID = translatorExtensionElement.getAttribute(Identifiers.EXTPT_TRANSLATORS_TRANSLATOR_TRANSLATORID);
					String commandID = translatorExtensionElement.getAttribute(Identifiers.EXTPT_TRANSLATORS_TRANSLATOR_COMMANDID);
					final IAdapter adapter = (IAdapter) translatorExtensionElement.createExecutableExtension(Identifiers.EXTPT_TRANSLATORS_TRANSLATOR_ADAPTERCLASS);
					if (rootSourcePackage!= null) {
						TranslatorConfig translatorConfig = new TranslatorConfig(translatorID, rootSourcePackage, rootSourceClass, adapter);
						
						for (final IExtension rulesetExtension : Platform.getExtensionRegistry().getExtensionPoint(Identifiers.EXTPT_RULESETS_EXTPTID).getExtensions()) {				
							for (final IConfigurationElement rulesetExtensionElement : rulesetExtension.getConfigurationElements()) {
								if (translatorID.equals(rulesetExtensionElement.getAttribute(Identifiers.EXTPT_RULESETS_RULESET_TRANSLATORID))){
									for (final IConfigurationElement ruleExtensionElement : rulesetExtensionElement.getChildren(Identifiers.EXTPT_RULESETS_RULESET_RULE)) {
										
										//see whether a source EPackage has been explicitly defined for this rule
										EPackage sourcePackage = EPackage.Registry.INSTANCE.getEPackage(ruleExtensionElement.getAttribute(Identifiers.EXTPT_RULESETS_RULESET_RULE_SOURCEPACKAGE));
										if (sourcePackage == null) {
											//no explicit EPackage so use the rootSourcePackage of the translator
											sourcePackage = rootSourcePackage;
										}

										//find the source EClass in the source EPackage
										EClassifier sourceClass = sourcePackage.getEClassifier(ruleExtensionElement.getAttribute(Identifiers.EXTPT_RULESETS_RULESET_RULE_SOURCECLASS));
										//if not in the rootPackage, try its subPackages
										if(sourceClass == null){
											for (EPackage subPackage  : sourcePackage.getESubpackages()){
												sourceClass = subPackage.getEClassifier(ruleExtensionElement.getAttribute(Identifiers.EXTPT_RULESETS_RULESET_RULE_SOURCECLASS));
												if (sourceClass != null) break;
											}
										}
									
										// if we found the class, add the rule
										if (sourceClass != null) {
											final IRule rule = (IRule) ruleExtensionElement.createExecutableExtension(Identifiers.EXTPT_RULESETS_RULESET_RULE_RULECLASS);									
											translatorConfig.addRule(sourceClass, rule);
										}
									}
								}
							}
						}
						//save config data for this translator
						if (translatorConfig != null) translatorConfigRegistry.put(commandID,translatorConfig);
					}
//				} catch (final CoreException e) {
//					throw e;
//				}
			}
		}
	}
	


	public static TranslatorFactory getFactory() throws CoreException{
		if (factory == null){
			factory = new TranslatorFactory();
		}
		return factory;
	}



	public boolean canTranslate(String commandId, EClassifier rootSourceClass){
		return translatorConfigRegistry.containsKey(commandId) &&
				translatorConfigRegistry.get(commandId).rootSourceClass.equals(rootSourceClass);
	}
	
	/**
	 * Construct a translator.
	 * This should be called from a command handler action.
	 * 	
	 * @param rootSourceClass	- the EClass of the root element that this is a translator for
	 */
		
	public Translator createTranslator(String commandId, EClass rootSourceClass){ 	
		if (canTranslate(commandId, rootSourceClass)){
			return new Translator(translatorConfigRegistry.get(commandId));
		}else{
			return null;
		}
	}
	
	
}
