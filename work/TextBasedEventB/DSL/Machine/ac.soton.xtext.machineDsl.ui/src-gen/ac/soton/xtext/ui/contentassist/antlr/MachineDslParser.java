/*
* generated by Xtext
*/
package ac.soton.xtext.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import ac.soton.xtext.services.MachineDslGrammarAccess;

public class MachineDslParser extends AbstractContentAssistParser {
	
	@Inject
	private MachineDslGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected ac.soton.xtext.ui.contentassist.antlr.internal.InternalMachineDslParser createParser() {
		ac.soton.xtext.ui.contentassist.antlr.internal.InternalMachineDslParser result = new ac.soton.xtext.ui.contentassist.antlr.internal.InternalMachineDslParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getEStringAccess().getAlternatives(), "rule__EString__Alternatives");
					put(grammarAccess.getQStringAccess().getAlternatives(), "rule__QString__Alternatives");
					put(grammarAccess.getAbstractNodeAccess().getAlternatives(), "rule__AbstractNode__Alternatives");
					put(grammarAccess.getConvergenceAccess().getAlternatives(), "rule__Convergence__Alternatives");
					put(grammarAccess.getTranslationKindAccess().getAlternatives(), "rule__TranslationKind__Alternatives");
					put(grammarAccess.getMachineAccess().getGroup(), "rule__Machine__Group__0");
					put(grammarAccess.getMachineAccess().getGroup_3(), "rule__Machine__Group_3__0");
					put(grammarAccess.getMachineAccess().getGroup_4(), "rule__Machine__Group_4__0");
					put(grammarAccess.getMachineAccess().getGroup_5(), "rule__Machine__Group_5__0");
					put(grammarAccess.getMachineAccess().getGroup_6(), "rule__Machine__Group_6__0");
					put(grammarAccess.getMachineAccess().getGroup_7(), "rule__Machine__Group_7__0");
					put(grammarAccess.getMachineAccess().getGroup_8(), "rule__Machine__Group_8__0");
					put(grammarAccess.getMachineAccess().getGroup_9(), "rule__Machine__Group_9__0");
					put(grammarAccess.getMachineAccess().getGroup_10(), "rule__Machine__Group_10__0");
					put(grammarAccess.getVariableAccess().getGroup(), "rule__Variable__Group__0");
					put(grammarAccess.getVariableAccess().getGroup_2(), "rule__Variable__Group_2__0");
					put(grammarAccess.getInvariantAccess().getGroup(), "rule__Invariant__Group__0");
					put(grammarAccess.getInvariantAccess().getGroup_4(), "rule__Invariant__Group_4__0");
					put(grammarAccess.getVariantAccess().getGroup(), "rule__Variant__Group__0");
					put(grammarAccess.getVariantAccess().getGroup_4(), "rule__Variant__Group_4__0");
					put(grammarAccess.getEventAccess().getGroup(), "rule__Event__Group__0");
					put(grammarAccess.getEventAccess().getGroup_5(), "rule__Event__Group_5__0");
					put(grammarAccess.getEventAccess().getGroup_6(), "rule__Event__Group_6__0");
					put(grammarAccess.getEventAccess().getGroup_7(), "rule__Event__Group_7__0");
					put(grammarAccess.getEventAccess().getGroup_8(), "rule__Event__Group_8__0");
					put(grammarAccess.getEventAccess().getGroup_9(), "rule__Event__Group_9__0");
					put(grammarAccess.getEventAccess().getGroup_10(), "rule__Event__Group_10__0");
					put(grammarAccess.getQStringAccess().getGroup_1(), "rule__QString__Group_1__0");
					put(grammarAccess.getQStringAccess().getGroup_1_1(), "rule__QString__Group_1_1__0");
					put(grammarAccess.getParameterAccess().getGroup(), "rule__Parameter__Group__0");
					put(grammarAccess.getParameterAccess().getGroup_2(), "rule__Parameter__Group_2__0");
					put(grammarAccess.getGuardAccess().getGroup(), "rule__Guard__Group__0");
					put(grammarAccess.getGuardAccess().getGroup_4(), "rule__Guard__Group_4__0");
					put(grammarAccess.getWitnessAccess().getGroup(), "rule__Witness__Group__0");
					put(grammarAccess.getWitnessAccess().getGroup_4(), "rule__Witness__Group_4__0");
					put(grammarAccess.getActionAccess().getGroup(), "rule__Action__Group__0");
					put(grammarAccess.getActionAccess().getGroup_3(), "rule__Action__Group_3__0");
					put(grammarAccess.getStatemachineAccess().getGroup(), "rule__Statemachine__Group__0");
					put(grammarAccess.getStatemachineAccess().getGroup_2(), "rule__Statemachine__Group_2__0");
					put(grammarAccess.getStatemachineAccess().getGroup_3(), "rule__Statemachine__Group_3__0");
					put(grammarAccess.getStatemachineAccess().getGroup_4(), "rule__Statemachine__Group_4__0");
					put(grammarAccess.getStatemachineAccess().getGroup_5(), "rule__Statemachine__Group_5__0");
					put(grammarAccess.getStatemachineAccess().getGroup_6(), "rule__Statemachine__Group_6__0");
					put(grammarAccess.getStatemachineAccess().getGroup_7(), "rule__Statemachine__Group_7__0");
					put(grammarAccess.getStatemachineAccess().getGroup_8(), "rule__Statemachine__Group_8__0");
					put(grammarAccess.getStatemachineAccess().getGroup_8_2(), "rule__Statemachine__Group_8_2__0");
					put(grammarAccess.getStatemachineAccess().getGroup_9(), "rule__Statemachine__Group_9__0");
					put(grammarAccess.getTransitionAccess().getGroup(), "rule__Transition__Group__0");
					put(grammarAccess.getTransitionAccess().getGroup_3(), "rule__Transition__Group_3__0");
					put(grammarAccess.getTransitionAccess().getGroup_4(), "rule__Transition__Group_4__0");
					put(grammarAccess.getTransitionAccess().getGroup_5(), "rule__Transition__Group_5__0");
					put(grammarAccess.getTransitionAccess().getGroup_6(), "rule__Transition__Group_6__0");
					put(grammarAccess.getTransitionAccess().getGroup_7(), "rule__Transition__Group_7__0");
					put(grammarAccess.getTransitionAccess().getGroup_8(), "rule__Transition__Group_8__0");
					put(grammarAccess.getTransitionAccess().getGroup_9(), "rule__Transition__Group_9__0");
					put(grammarAccess.getTransitionAccess().getGroup_10(), "rule__Transition__Group_10__0");
					put(grammarAccess.getStateAccess().getGroup(), "rule__State__Group__0");
					put(grammarAccess.getStateAccess().getGroup_3(), "rule__State__Group_3__0");
					put(grammarAccess.getStateAccess().getGroup_4(), "rule__State__Group_4__0");
					put(grammarAccess.getStateAccess().getGroup_5(), "rule__State__Group_5__0");
					put(grammarAccess.getInitialAccess().getGroup(), "rule__Initial__Group__0");
					put(grammarAccess.getFinalAccess().getGroup(), "rule__Final__Group__0");
					put(grammarAccess.getAnyAccess().getGroup(), "rule__Any__Group__0");
					put(grammarAccess.getJunctionAccess().getGroup(), "rule__Junction__Group__0");
					put(grammarAccess.getForkAccess().getGroup(), "rule__Fork__Group__0");
					put(grammarAccess.getTypedParameterAccess().getGroup(), "rule__TypedParameter__Group__0");
					put(grammarAccess.getTypedParameterAccess().getGroup_3(), "rule__TypedParameter__Group_3__0");
					put(grammarAccess.getMachineAccess().getNameAssignment_2(), "rule__Machine__NameAssignment_2");
					put(grammarAccess.getMachineAccess().getCommentAssignment_3_1(), "rule__Machine__CommentAssignment_3_1");
					put(grammarAccess.getMachineAccess().getRefinesAssignment_4_1(), "rule__Machine__RefinesAssignment_4_1");
					put(grammarAccess.getMachineAccess().getRefinesAssignment_4_2(), "rule__Machine__RefinesAssignment_4_2");
					put(grammarAccess.getMachineAccess().getSeesAssignment_5_1(), "rule__Machine__SeesAssignment_5_1");
					put(grammarAccess.getMachineAccess().getSeesAssignment_5_2(), "rule__Machine__SeesAssignment_5_2");
					put(grammarAccess.getMachineAccess().getVariablesAssignment_6_1(), "rule__Machine__VariablesAssignment_6_1");
					put(grammarAccess.getMachineAccess().getVariablesAssignment_6_2(), "rule__Machine__VariablesAssignment_6_2");
					put(grammarAccess.getMachineAccess().getInvariantsAssignment_7_1(), "rule__Machine__InvariantsAssignment_7_1");
					put(grammarAccess.getMachineAccess().getInvariantsAssignment_7_2(), "rule__Machine__InvariantsAssignment_7_2");
					put(grammarAccess.getMachineAccess().getVariantAssignment_8_1(), "rule__Machine__VariantAssignment_8_1");
					put(grammarAccess.getMachineAccess().getEventsAssignment_9_1(), "rule__Machine__EventsAssignment_9_1");
					put(grammarAccess.getMachineAccess().getEventsAssignment_9_2(), "rule__Machine__EventsAssignment_9_2");
					put(grammarAccess.getMachineAccess().getExtensionsAssignment_10_0(), "rule__Machine__ExtensionsAssignment_10_0");
					put(grammarAccess.getMachineAccess().getExtensionsAssignment_10_1(), "rule__Machine__ExtensionsAssignment_10_1");
					put(grammarAccess.getVariableAccess().getNameAssignment_1(), "rule__Variable__NameAssignment_1");
					put(grammarAccess.getVariableAccess().getCommentAssignment_2_1(), "rule__Variable__CommentAssignment_2_1");
					put(grammarAccess.getInvariantAccess().getNameAssignment_1(), "rule__Invariant__NameAssignment_1");
					put(grammarAccess.getInvariantAccess().getPredicateAssignment_2(), "rule__Invariant__PredicateAssignment_2");
					put(grammarAccess.getInvariantAccess().getTheoremAssignment_3(), "rule__Invariant__TheoremAssignment_3");
					put(grammarAccess.getInvariantAccess().getCommentAssignment_4_1(), "rule__Invariant__CommentAssignment_4_1");
					put(grammarAccess.getVariantAccess().getExpressionAssignment_3(), "rule__Variant__ExpressionAssignment_3");
					put(grammarAccess.getVariantAccess().getCommentAssignment_4_1(), "rule__Variant__CommentAssignment_4_1");
					put(grammarAccess.getEventAccess().getNameAssignment_2(), "rule__Event__NameAssignment_2");
					put(grammarAccess.getEventAccess().getExtendedAssignment_3(), "rule__Event__ExtendedAssignment_3");
					put(grammarAccess.getEventAccess().getConvergenceAssignment_4(), "rule__Event__ConvergenceAssignment_4");
					put(grammarAccess.getEventAccess().getCommentAssignment_5_1(), "rule__Event__CommentAssignment_5_1");
					put(grammarAccess.getEventAccess().getRefinesAssignment_6_1(), "rule__Event__RefinesAssignment_6_1");
					put(grammarAccess.getEventAccess().getRefinesAssignment_6_2(), "rule__Event__RefinesAssignment_6_2");
					put(grammarAccess.getEventAccess().getParametersAssignment_7_1(), "rule__Event__ParametersAssignment_7_1");
					put(grammarAccess.getEventAccess().getParametersAssignment_7_2(), "rule__Event__ParametersAssignment_7_2");
					put(grammarAccess.getEventAccess().getGuardsAssignment_8_1(), "rule__Event__GuardsAssignment_8_1");
					put(grammarAccess.getEventAccess().getGuardsAssignment_8_2(), "rule__Event__GuardsAssignment_8_2");
					put(grammarAccess.getEventAccess().getWitnessesAssignment_9_1(), "rule__Event__WitnessesAssignment_9_1");
					put(grammarAccess.getEventAccess().getWitnessesAssignment_9_2(), "rule__Event__WitnessesAssignment_9_2");
					put(grammarAccess.getEventAccess().getActionsAssignment_10_1(), "rule__Event__ActionsAssignment_10_1");
					put(grammarAccess.getEventAccess().getActionsAssignment_10_2(), "rule__Event__ActionsAssignment_10_2");
					put(grammarAccess.getParameterAccess().getNameAssignment_1(), "rule__Parameter__NameAssignment_1");
					put(grammarAccess.getParameterAccess().getCommentAssignment_2_1(), "rule__Parameter__CommentAssignment_2_1");
					put(grammarAccess.getGuardAccess().getNameAssignment_1(), "rule__Guard__NameAssignment_1");
					put(grammarAccess.getGuardAccess().getPredicateAssignment_2(), "rule__Guard__PredicateAssignment_2");
					put(grammarAccess.getGuardAccess().getTheoremAssignment_3(), "rule__Guard__TheoremAssignment_3");
					put(grammarAccess.getGuardAccess().getCommentAssignment_4_1(), "rule__Guard__CommentAssignment_4_1");
					put(grammarAccess.getWitnessAccess().getLocalGeneratedAssignment_0(), "rule__Witness__LocalGeneratedAssignment_0");
					put(grammarAccess.getWitnessAccess().getNameAssignment_2(), "rule__Witness__NameAssignment_2");
					put(grammarAccess.getWitnessAccess().getPredicateAssignment_3(), "rule__Witness__PredicateAssignment_3");
					put(grammarAccess.getWitnessAccess().getCommentAssignment_4_1(), "rule__Witness__CommentAssignment_4_1");
					put(grammarAccess.getActionAccess().getNameAssignment_1(), "rule__Action__NameAssignment_1");
					put(grammarAccess.getActionAccess().getActionAssignment_2(), "rule__Action__ActionAssignment_2");
					put(grammarAccess.getActionAccess().getCommentAssignment_3_1(), "rule__Action__CommentAssignment_3_1");
					put(grammarAccess.getStatemachineAccess().getNameAssignment_1(), "rule__Statemachine__NameAssignment_1");
					put(grammarAccess.getStatemachineAccess().getInstancesAssignment_2_1(), "rule__Statemachine__InstancesAssignment_2_1");
					put(grammarAccess.getStatemachineAccess().getSelfNameAssignment_3_1(), "rule__Statemachine__SelfNameAssignment_3_1");
					put(grammarAccess.getStatemachineAccess().getTranslationAssignment_4_1(), "rule__Statemachine__TranslationAssignment_4_1");
					put(grammarAccess.getStatemachineAccess().getElaboratesAssignment_5_1(), "rule__Statemachine__ElaboratesAssignment_5_1");
					put(grammarAccess.getStatemachineAccess().getRefinesAssignment_6_1(), "rule__Statemachine__RefinesAssignment_6_1");
					put(grammarAccess.getStatemachineAccess().getCommentAssignment_7_1(), "rule__Statemachine__CommentAssignment_7_1");
					put(grammarAccess.getStatemachineAccess().getNodesAssignment_8_1(), "rule__Statemachine__NodesAssignment_8_1");
					put(grammarAccess.getStatemachineAccess().getNodesAssignment_8_2_1(), "rule__Statemachine__NodesAssignment_8_2_1");
					put(grammarAccess.getStatemachineAccess().getTransitionsAssignment_9_1(), "rule__Statemachine__TransitionsAssignment_9_1");
					put(grammarAccess.getStatemachineAccess().getTransitionsAssignment_9_2(), "rule__Statemachine__TransitionsAssignment_9_2");
					put(grammarAccess.getTransitionAccess().getExtendedAssignment_1(), "rule__Transition__ExtendedAssignment_1");
					put(grammarAccess.getTransitionAccess().getCommentAssignment_3_1(), "rule__Transition__CommentAssignment_3_1");
					put(grammarAccess.getTransitionAccess().getElaboratesAssignment_4_1(), "rule__Transition__ElaboratesAssignment_4_1");
					put(grammarAccess.getTransitionAccess().getElaboratesAssignment_4_2(), "rule__Transition__ElaboratesAssignment_4_2");
					put(grammarAccess.getTransitionAccess().getSourceAssignment_5_1(), "rule__Transition__SourceAssignment_5_1");
					put(grammarAccess.getTransitionAccess().getTargetAssignment_6_1(), "rule__Transition__TargetAssignment_6_1");
					put(grammarAccess.getTransitionAccess().getParametersAssignment_7_1(), "rule__Transition__ParametersAssignment_7_1");
					put(grammarAccess.getTransitionAccess().getParametersAssignment_7_2(), "rule__Transition__ParametersAssignment_7_2");
					put(grammarAccess.getTransitionAccess().getGuardsAssignment_8_1(), "rule__Transition__GuardsAssignment_8_1");
					put(grammarAccess.getTransitionAccess().getGuardsAssignment_8_2(), "rule__Transition__GuardsAssignment_8_2");
					put(grammarAccess.getTransitionAccess().getWitnessesAssignment_9_1(), "rule__Transition__WitnessesAssignment_9_1");
					put(grammarAccess.getTransitionAccess().getWitnessesAssignment_9_2(), "rule__Transition__WitnessesAssignment_9_2");
					put(grammarAccess.getTransitionAccess().getActionsAssignment_10_1(), "rule__Transition__ActionsAssignment_10_1");
					put(grammarAccess.getTransitionAccess().getActionsAssignment_10_2(), "rule__Transition__ActionsAssignment_10_2");
					put(grammarAccess.getStateAccess().getNameAssignment_2(), "rule__State__NameAssignment_2");
					put(grammarAccess.getStateAccess().getRefinesAssignment_3_1(), "rule__State__RefinesAssignment_3_1");
					put(grammarAccess.getStateAccess().getInvariantsAssignment_4_1(), "rule__State__InvariantsAssignment_4_1");
					put(grammarAccess.getStateAccess().getInvariantsAssignment_4_2(), "rule__State__InvariantsAssignment_4_2");
					put(grammarAccess.getStateAccess().getStatemachinesAssignment_5_1(), "rule__State__StatemachinesAssignment_5_1");
					put(grammarAccess.getStateAccess().getStatemachinesAssignment_5_2(), "rule__State__StatemachinesAssignment_5_2");
					put(grammarAccess.getInitialAccess().getNameAssignment_2(), "rule__Initial__NameAssignment_2");
					put(grammarAccess.getFinalAccess().getNameAssignment_2(), "rule__Final__NameAssignment_2");
					put(grammarAccess.getAnyAccess().getNameAssignment_2(), "rule__Any__NameAssignment_2");
					put(grammarAccess.getJunctionAccess().getNameAssignment_2(), "rule__Junction__NameAssignment_2");
					put(grammarAccess.getForkAccess().getNameAssignment_2(), "rule__Fork__NameAssignment_2");
					put(grammarAccess.getTypedParameterAccess().getNameAssignment_0(), "rule__TypedParameter__NameAssignment_0");
					put(grammarAccess.getTypedParameterAccess().getTypeAssignment_2(), "rule__TypedParameter__TypeAssignment_2");
					put(grammarAccess.getTypedParameterAccess().getCommentAssignment_3_1(), "rule__TypedParameter__CommentAssignment_3_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			ac.soton.xtext.ui.contentassist.antlr.internal.InternalMachineDslParser typedParser = (ac.soton.xtext.ui.contentassist.antlr.internal.InternalMachineDslParser) parser;
			typedParser.entryRuleMachine();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public MachineDslGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(MachineDslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
