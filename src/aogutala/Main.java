package aogutala;


class Main{

    public static void main(String[] args) {

        Floor myFloor = new Floor();
        myFloor.createChambers(5);
        myFloor.addDoorToChamber();
        //ArrayList<Chamber> c = myFloor.getPossibleTargets(1);
        myFloor.connectDoorsToChambers();
        myFloor.connectDoorsToChamberDoors();

    }   

}
