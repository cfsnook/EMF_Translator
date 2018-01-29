/*******************************************************************************
 *  Copyright (c) 2015-2017 University of Southampton.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *  Contributors:
 *  University of Southampton - Initial implementation
 *******************************************************************************/
package ac.soton.emf.translator.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import ac.soton.emf.translator.Activator;
import ac.soton.emf.translator.TranslatorFactory;
import ac.soton.emf.translator.impl.Messages;

/**
 * Handler for commands to run the translator.
 * 
 * @author cfs 
 *
 */
public class TranslateHandler extends AbstractHandler {
	
	private TransactionalEditingDomain editingDomain = null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public final IStatus execute(final ExecutionEvent event) throws ExecutionException {
		final MultiStatus status = new MultiStatus(Activator.PLUGIN_ID, IStatus.OK, Messages.TRANSLATOR_MSG_09, null) ;
		final EObject sourceElement;
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection && !selection.isEmpty()){
			Object obj = ((IStructuredSelection)selection).getFirstElement();
			sourceElement = getEObject(obj);
		} else sourceElement = null;
		if (sourceElement==null) { 
			status.merge(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.TRANSLATOR_MSG_07));
		}else{
	
			IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
			final Shell shell = activeWorkbenchWindow.getShell();
			final String commandId = event.getCommand().getId();
	
			try {
				final TranslatorFactory factory = TranslatorFactory.getFactory();
				ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
				if (factory != null && factory.canTranslate(commandId, sourceElement.eClass())){
					dialog.run(true, true, new IRunnableWithProgress(){			
							public void run(IProgressMonitor monitor) throws InvocationTargetException { 
								try {
									editingDomain = getEditingDomain(sourceElement);
									
									SubMonitor submonitor = SubMonitor.convert(monitor, "validating", 10);
									IStatus validationStatus = validate(event, submonitor.newChild(1));
									
									status.merge(validationStatus);
									if (enablePreProcessing(validationStatus)){
										submonitor.setTaskName("preprocessing");
										status.merge(
											preProcessing(sourceElement, commandId, submonitor.newChild(1))
											);
										save(submonitor.newChild(2));
									}
									if (enableTranslation(validationStatus)){
										submonitor.setTaskName("translating");
										status.merge(
											factory.translate(getEditingDomain(), sourceElement, commandId, submonitor.newChild(2))
											);
										save(submonitor.newChild(2));
									}
									if (enablePostProcessing(validationStatus)){
										submonitor.setTaskName("postProcessing");
										status.merge(
											postProcessing(sourceElement, commandId, submonitor.newChild(1))
											);
										save(submonitor.newChild(2));
									}
								} catch (Exception e) {
									throw new InvocationTargetException(e);
								}
							}
						});
					}
			} catch (InterruptedException e) {
				status.merge(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.TRANSLATOR_MSG_08, e));        	
				Activator.logError(Messages.TRANSLATOR_MSG_08, e);
			}catch (Exception e) {
				e.printStackTrace();
				status.merge(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.TRANSLATOR_MSG_07, e));
				Activator.logError(Messages.TRANSLATOR_MSG_07, e);
			}
		}
		return status;
	}

	/**
	 * Return whether or not the pre-processing should run based on the validation errors
	 * 
	 * Default is to only run if the severity status is less than error
	 * 
	 * @param validationStatus
	 * @return
	 */
	protected boolean enablePreProcessing(IStatus validationStatus) {
		return validationStatus.getSeverity()<IStatus.ERROR;
	}

	/**
	 * Return whether or not the translation should run based on the validation errors
	 * 
	 * Default is to only run if the severity status is less than error
	 * 
	 * @param validationStatus
	 * @return
	 */
	protected boolean enableTranslation(IStatus validationStatus) {
		return validationStatus.getSeverity()<IStatus.ERROR;
	}

	/**
	 * Return whether or not the post-processing should run based on the validation errors
	 * 
	 * Default is to only run if the severity status is less than error
	 * 
	 * @param validationStatus
	 * @return
	 */
	protected boolean enablePostProcessing(IStatus validationStatus) {
		return validationStatus.getSeverity()<IStatus.ERROR;
	}
	
	/**
	 * 
	 * This can be overridden to provide the TransactionalEditingDomain 
	 * to be used for generating the new EMF elements.
	 * 
	 * The default implementation uses the transactional editing domain of the source element if there is one,
	 * otherwise creates a new one. This works for most scenarios.
	 * 
	 * @param sourceElement
	 * @return 
	 */
	protected TransactionalEditingDomain getEditingDomain(EObject sourceElement) {
		TransactionalEditingDomain editingDomain = null;
		if (sourceElement.eResource()!=null && sourceElement.eResource().getResourceSet()!=null){
			editingDomain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(sourceElement.eResource().getResourceSet());
		}
		if (editingDomain==null){
			editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		}
		
		return editingDomain;
	}
	
	/**
	 * returns the editing domain
	 * @return
	 */
	protected final TransactionalEditingDomain getEditingDomain(){
		return editingDomain;
	}
	
	/**
	 * This can be overridden to perform some validation.
	 * 
	 * translation will only proceed if an OK_STATUS is returned.
	 * 
	 * @param ExecutionEvent
	 * @param monitor
	 * @return status = OK to continue translation, INFO to report validation errors and cancel translation
	 * @throws ExecutionException 
	 */
	protected IStatus validate(ExecutionEvent event, IProgressMonitor monitor) throws ExecutionException {
		monitor.done();
		return Status.OK_STATUS;
	}

	/**
	 * From the selected object, get an EObject that can be translated.
	 * The default code handles 
	 * 	selection is an EObject (return object cast to EObject)
	 * 	selection is an IAdaptable (return adapted EObject)
	 *  selection is an EMF Resource (return contained EObject)
	 * This can be overridden to handle other cases
	 * @param obj
	 * @return corresponding EObject to be translated
	 */
	protected EObject getEObject(Object obj) {
		final EObject sourceElement;
		if (obj instanceof EObject){
			sourceElement = (EObject)obj;
		}else if (obj instanceof IAdaptable) {
			Object adaptedObj = ((IAdaptable) obj).getAdapter(EObject.class);
			if (adaptedObj instanceof EObject){
				sourceElement = (EObject) adaptedObj; 
			} else sourceElement = null;
		}else if (obj instanceof Resource){
			sourceElement = ((Resource)obj).getContents().get(0);
		}else sourceElement = null;
		return sourceElement;
	}

	/**
	 * This can be overridden to add some pre-processing before the translation
	 * default implementation does nothing
	 * @param sourceElement
	 * @param commandId
	 * @param monitor
	 */
	protected IStatus  preProcessing(EObject sourceElement, String commandId, IProgressMonitor monitor) throws Exception {
        monitor.done();
        return Status.OK_STATUS;
	}

	/**
	 * This can be overridden to add some post-processing after the translation
	 * default implementation does nothing
	 * @param sourceElement
	 * @param commandId
	 * @param monitor
	 * @throws CoreException 
	 */
	protected IStatus  postProcessing(EObject sourceElement, String commandId, IProgressMonitor monitor) throws Exception {
        monitor.done();
        return Status.OK_STATUS;
	}
	
	/**
	 * Save modified resources
	 * This can be overridden to use particular persistence mechanisms
	 * 
	 * The default implementation saves all modified
	 * resources in the resource set of the editing domain
	 * 
	 * @param editingDomain
	 * @param monitor
	 * @throws Exception 
	 */
	protected IStatus save(IProgressMonitor monitor) throws Exception {
		ResourcesPlugin.getWorkspace().run(new ICoreRunnable() {
			public void run(final IProgressMonitor monitor) throws CoreException{
				try{
					//try to save all the modified resources
					for (Resource resource : getEditingDomain().getResourceSet().getResources()){
						if (resource.isModified()){
							resource.save(Collections.emptyMap());
							monitor.worked(1);
						}
					}
				}catch (Exception e) {
					//throw this as a CoreException
					new Exception(e);
				}
				monitor.done();
			}
		},monitor);
		monitor.done();
        return Status.OK_STATUS;
	}
	
	
}
