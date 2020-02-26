package aogutala;

import dnd.die.D10;
import dnd.die.D20;
import dnd.die.D6;
import dnd.models.Exit;
import dnd.models.Trap;

import java.io.Serializable;
import java.util.ArrayList;

public class Door implements Serializable {
	// instance variable for a door
	private boolean lock;
	private boolean open;
	private boolean archway;
	private Trap trap;
	private Exit exit;
	private String description;
	private ArrayList<Space> spaceList;
	private boolean connectedVar;



	public Door() {
		//needs to set defaults
		init();

	}
	public Door(Exit theExit) {
		//sets up the door based on the Exit from the tables
		this();
		exit = theExit;
	}

	public void init() {
		this.lock = false;
		this.open = false;
		this.archway = false;
		this.trap = null;
		this.exit = new Exit();
		this.description = "";
		this.spaceList = new ArrayList<Space>();
		this.connectedVar = false;


		D6 d6 = new D6();
		D10 d10 = new D10();
		D20 d20 = new D20();

		if(d20.roll() == d20.roll()) {	//1/20 chance it will be a trapped door
			this.setTrapped(true);
			description += "The door is trapped. ";
		}
		else if(d10.roll() == d10.roll()) {	//	1/10 chance that the door is an archway
			archway = true;					// in which case it is not trapped and not locked but is open)
			if(archway) {
				this.setTrapped(false);
				lock = false;
				if(!open) {				//a door can be open (only if unlocked), or closed
					open = true;
				}
			}
			description += "It's an archway. It's neither trapped nor locked, and it's always open. ";

		}
		else if(d6.roll() == d6.roll()) {		//1/6 chance it will be locked
			lock = true;
			description += "The door is locked. ";
		}
		else{
			description += "The door is neither trapped nor locked. ";
		}
	}

	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we 
	don't want to dictate how you set up your packages we need them to be public 
	for the purposes of running an automated test suite (junit) on your code.  */

	public void setTrapped(boolean flag, int ... roll) {
		// true == trapped.  Trap must be rolled if no integer is given
		if(flag ) {
			trap = new Trap();
		}


	}
	public void setOpen(boolean flag) {

		this.open = flag;
		if(this.isArchway())
			this.open = true;
	}

	public boolean setLock(boolean locked) {
		this.lock = locked;
		return lock;
	}
	public void setArchway(boolean flag) {
		//true == is archway
		this.archway = flag;
		if(archway) {
			this.lock = false;
			this.open = true;
			this.trap =null;	
		}
	}




	public boolean isTrapped() {
		if(this.trap == null) {
			return false;
		}
		return true;

	}
	public boolean isOpen() {
		return this.open;
	}
	public boolean isLocked() {
		return this.lock;
	}
	public boolean isArchway() {
		return this.archway;
	}

	public String getTrapDescription() {
		if(trap != null) {
			return this.getDescription();
		}
		return null;
	}

	public ArrayList<Space> getSpaces() {
		//returns the two spaces that are connected by the door

		return spaceList;
	}
	public void setSpaces(Space spaceOne, Space spaceTwo) {
		//identifies the two spaces with the door
		// this method should also call the setDoor method from Space		

		spaceList.add(0, spaceOne);
		spaceList.add(1, spaceTwo);


	}

	public Exit getExit() {
		return this.exit;
	}
	public void setConnected(boolean connectedVar) {
		this.connectedVar = true;
	}

	public boolean isConnected() {
		return this.connectedVar;
	}


	public String getDescription() {
		return "Door description: " + this.description;
	}
	public void setDecription(String newDescription){
		this.description = description + "\n" + newDescription;
	}
/***********
You can write your own methods too, you aren't limited to the required ones
*************/
}