package ac.soton.codin.codegen.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eventb.codegen.il1.IL1Element;
import org.eventb.codegen.il1.Program;
import org.eventb.codegen.tasking.TaskingTranslationManager;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.impl.MachineImpl;
import org.rodinp.core.IRodinProject;

import ac.soton.eventb.statemachines.AbstractNode;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;

public class StateMachineTranslationData {

	MachineImpl parentMachine;
	public IRodinProject parentProject = null;

	TaskingTranslationManager taskingTranslationManager;
	IL1Element actualTarget;
	// In our translation Events map to sequences, and branches in the process
	// task body.
	// So we record info relating to the events rather than transitions, since
	// transitions may be associated with multiple events.

	// Given a current state node: navigate to next state, via events/joins etc:
	// Descriptively: a map of type CurrentState <-> (Event <-> NextState)
	// Represention of flattened state machine
	public Map<State, Map<Event, AbstractNode>> processSM_flattenedNextStateMap;

	// Given a synchronous State-machine and current state node: navigate to the
	// next state, via events/joins etc: Descriptively: a map of 
	// type State-machine <-> (CurrentState <-> (Event <-> NextState))
	// Representation of flattened state machine
	public Map<Statemachine,Map<State, Map<Event, AbstractNode>>> synchSM_flattened_nextStateMap = 
			new HashMap<Statemachine, Map<State,Map<Event,AbstractNode>>>();

	// A map to store which state-machines use an event in synchronous
	// state-machines.
	// If an event of a synchronous state-machine appears in a process
	// state-machine then
	// the synch state machine is invoked.
	public Map<Event, List<Statemachine>> synchSMEventUser = new HashMap<Event, List<Statemachine>>();

	// The IL1 program that we will create.
	public Program program;

	// Store the synchronous state-machines for each component
	// in a map of ComponentName <-> ListOfStatemachines
	public Map<String, List<Statemachine>> synchronousSM_Map = new HashMap<String, List<Statemachine>>();

	// There is just one process state-machine per component,
	// for each component, record the process state-machines
	// in a map of ComponentName <-> ProcessStateMachine
	public Map<String, Statemachine> processSM_Map = new HashMap<String, Statemachine>();

	// BEGIN: The following maps are refreshed (cleared) for each new component.

	// In a component: Given a state node, get the events associated with the
	// state
	public Map<State, List<Event>> component_stateEventMap = new HashMap<State, List<Event>>();

	// In a component: Complement to the stateEventMap above: Keys are nodes,
	// but not including states.
	// Given a node, we can get the associated events. (Needed for Joins, in
	// particular)
	public Map<AbstractNode, List<Event>> component_nodeEventMap = new HashMap<AbstractNode, List<Event>>();


	// call reset for each new component
	public void resetMaps() {
		component_stateEventMap.clear();
		component_nodeEventMap.clear();
	}

	// END: of maps refreshed (cleared) for each new component.

}