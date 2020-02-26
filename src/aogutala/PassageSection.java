package aogutala;

import java.io.Serializable;

import dnd.die.D20;
import dnd.models.Monster;

/* Represents a 10 ft section of passageway */

public class PassageSection implements Serializable {

	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we 
	don't want to dictate how you set up your packages we need them to be public 
	for the purposes of running an automated test suite (junit) on your code.  */

	private String description;
	private Door door;
	private Monster monster;
	private int length;



public PassageSection() {
	//sets up the 10 foot section with default settings
	this.door = new Door();
	this.monster = new Monster();
	this.length = 10;
	this.description = "";
	description += "The length of the passage section is 10 feet. \n";
}

public PassageSection(String description)
 {
	//sets up a specific passage based on the values sent in from
	//modified table 1
	this();
	this.description = description;

}

public Door getDoor() {
	//returns the door that is in the passage section, if there is one
	if(this.door != null) {
		description += "Door of passage section: " + door.getDescription();
		return this.door;
	}
return null;
}

public void addMonsterPS(Monster monster) {
	monster.setType(new D20().roll());
	this.monster = monster;
	this.description += "+ Monster added to the Passage Section: "+ this.monster.getDescription() + "\n";
}
public void removeMonsterPS(Monster monster){
	this.description += "+ Monster removed from the Passage Section: "+ this.monster.getDescription() + "\n";
	this.monster = null;
}

public Monster getMonster() {

	//returns the monster that is in the passage section, if there is one
	if((this.monster != null) && (this.description.contains("Monster"))) {

		return this.monster;
	}
return null;
}
public String getDescription() {
	return this.description;
}



}