package ac.soton.codin.codegen.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eventb.codegen.il1.IL1Element;
import org.eventb.codegen.tasking.TaskingTranslationManager;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.impl.MachineImpl;

import ac.soton.eventb.statemachines.AbstractNode;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;

public class StateMachineTranslationManager {

	MachineImpl parentMachine;
	TaskingTranslationManager taskingTranslationManager;
	IL1Element actualTarget;
	// In our translation Events map to sequences, and branches in the process
	// task body.
	// So we record info relating to the events rather than transitions, since
	// transitions may be associated with multiple events.

	// Given a state node, get the events associated with the state
	Map<State, List<Event>> stateEventMap = new HashMap<State, List<Event>>();

	// Complement to the stateEventMap above: Keys are nodes, but not including
	// states.
	// Given a node, we can get the associated events. (Needed for Joins, in
	// particular)
	Map<AbstractNode, List<Event>> nodeEventMap = new HashMap<AbstractNode, List<Event>>();

	// Given a current state node: navigate to next state, via events/joins etc:
	// Descriptively: a map of type CurrentState <-> (Event <-> NextState)
	Map<State, Map<Event, AbstractNode>> current_NextStateMap = new HashMap<State, Map<Event, AbstractNode>>();

	// Given an initial node: navigate to next state, via events/joins etc:
	// Descriptively: a map of type InitialState <-> (Event <-> NextState)
	// Initial states have to be treated differently.
	Map<State, Map<Event, AbstractNode>> initial_NextStateMap = new HashMap<State, Map<Event, AbstractNode>>();

	// Given a current state node: navigate to next state, via events/joins etc:
	// Descriptively: a map of type CurrentState <-> (Event <-> NextState)
	// Represents a flattened state machine
	public Map<State, Map<Event, AbstractNode>> flattenedNextStateMap;
	
	
	// A map to store which state-machines use an event in synchronous state-machines.
	// If an event of a synchronous state-machine appears in a process state-machine then
	// the synch state machine is invoked.
	public Map<Event, List<Statemachine>> synchEventUser = new HashMap<Event, List<Statemachine>>();

	// call reset for each new component
	public void resetMaps() {
		stateEventMap.clear();
		nodeEventMap.clear();
		current_NextStateMap.clear();
		initial_NextStateMap.clear()
		;
	}
}
