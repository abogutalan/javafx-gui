package gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import aogutala.*;
import dnd.models.Monster;
import dnd.models.Treasure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.control.*; 







public class GuiDemo extends Application {
    /* Even if it is a GUI it is useful to have instance variables
    so that you can break the processing up into smaller methods that have
    one responsibility.
     */
    private Controller theController;
    private BorderPane root;  //the root element of this GUI
    private Popup descriptionPane;
    private Stage primaryStage;  //The stage that is passed in on initialization

    private TextArea textArea;
    private ArrayList<Chamber> chambers;
    private ArrayList<Passage> passages;
    private ArrayList<Door> doors;
    private ComboBox<String> choiceBox;
    BorderPane myBorderPane;
    private Chamber currentChamber;
    private Passage currentPassage;
    private int chamber_or_passage;      // 1 = chamber  &&  2 = passage
    private int add_or_remove;      // 1 = add  &&  2 == remove
    


    /*a call to start replaces a call to the constructor for a JavaFX GUI*/
    @Override
    public void start(Stage assignedStage) {
        
        /*Initializing instance variables */
        theController = new Controller(this);
        primaryStage = assignedStage;
        textArea = new TextArea();
        chambers = theController.getListOfChambers();
        passages = theController.getListOfPassages();
        doors = new ArrayList<>();
        this.choiceBox = new ComboBox<>();
        myBorderPane = new BorderPane();
        
        /*Border Panes have  top, left, right, center and bottom sections */
        root = setUpRoot();
        descriptionPane = createPopUp(200, 300, "Example Description of something");
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setTitle("Hello GUI Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    private BorderPane setUpRoot() {
        BorderPane temp = new BorderPane(); 
        


        //temp.setTop(new Label("The name or identifier of the thing below"));
        Node top = setTopButtonPanel(); //serializedBorderPane();
        Node bottom = setBottomPanel();        
        Node left = setLeftButtonPanel();  //separate method for the left section
        Node right = setRightButtonPanel();
        Node centre = setCentreButtonPanel();
        temp.setTop(top);
        temp.setBottom(bottom);
        temp.setLeft(left);
        temp.setRight(right);
        temp.setCenter(centre);
        
        
        
        
        //TilePane room = createTilePanel();
        //GridPane room = new ChamberView(4,4);
        return temp;

        
    }

    private Node setBottomPanel(){
        VBox temp = new VBox();
        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Button editButton = createButton("Edit", "-fx-background-color: #C9FDFF;");
        editButton.setOnAction((ActionEvent event) -> {
            Label secondLabel = new Label("I'm a Label on new Window\n");
            
            
            
            BorderPane secondaryLayout = editButtonActivities();
            secondaryLayout.getChildren().add(secondLabel);
            Scene secondScene = new Scene(secondaryLayout, 430, 400);
            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);

            // Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() +200);
            newWindow.setY(primaryStage.getY() + 100);

            

            newWindow.show();


        });
        temp.getChildren().add(editButton);

        return temp;
    }

    private BorderPane editButtonActivities() {
        BorderPane temp = new BorderPane(); 

        Node centre = setCentre();
        temp.setCenter(centre);

        return temp;

    }
    private Node setCentre(){
        VBox temp = new VBox();
        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        TextArea tArea = new TextArea();
        tArea.setText("Please click a button then choose which Monster/Treasure");
        temp.getChildren().add(tArea);
        ComboBox<String> comboBox = new ComboBox<>();
        HashMap<String,Integer> myHashMap = new HashMap<>();

        Button addMonsterBttn = createButton("Add Monster", "");
        addMonsterBttn.setOnAction((ActionEvent event2) -> {
            comboBox.getItems().clear();
            comboBox.getItems().add("Which Monster?");        
            comboBox.setValue("Which Monster?");
            myHashMap.clear();
            for(int i= 1; i<=100; i++){
                Monster m = new Monster();
                Monster tempMonster = new Monster();
                if(i==1){
                    comboBox.getItems().add("Ant, giant");
                    myHashMap.put("Ant, giant", i);
                } 
                else{
                    tempMonster.setType(i-1);
                    m.setType(i);
                    if((m.getDescription() != tempMonster.getDescription()) && (m.getDescription() != null))
                    {
                        comboBox.getItems().add(m.getDescription());
                        myHashMap.put(m.getDescription(), i);
                    }
                }                
            }
            this.add_or_remove = 1;            
        });
        temp.getChildren().add(addMonsterBttn);

        Button removeMonsterBttn = createButton("Remove Monster", "");
        removeMonsterBttn.setOnAction((ActionEvent event2) -> {
            comboBox.getItems().clear();
            comboBox.getItems().add("Which Monster?");        
            comboBox.setValue("Which Monster?");
            for(int i= 1; i<=100; i++){
                Monster m = new Monster();
                Monster tempMonster = new Monster();
                if(i==1) comboBox.getItems().add("Ant, giant");
                else{
                    tempMonster.setType(i-1);
                    m.setType(i);
                    if((m.getDescription() != tempMonster.getDescription()) && (m.getDescription() != null))
                    {
                        comboBox.getItems().add(m.getDescription());
                    }
                }                
            }
            this.add_or_remove = 2;
        });
        temp.getChildren().add(removeMonsterBttn);

        Button addTreasureBttn = createButton("Add Treasure", "");
        addTreasureBttn.setOnAction((ActionEvent event2) -> {
            comboBox.getItems().clear();
            comboBox.getItems().add("Which Treasure?");        
            comboBox.setValue("Which Treasure?");
            myHashMap.clear();
            for(int i= 1; i<=100; i++){
                Treasure t = new Treasure();
                Treasure tempTreasure = new Treasure();
                if(i==1){
                    comboBox.getItems().add("1000 copper pieces/level");
                    myHashMap.put("1000 copper pieces/level", i);
                } 
                else{
                    tempTreasure.chooseTreasure(i-1);
                    t.chooseTreasure(i);
                    if((t.getDescription() != tempTreasure.getDescription()) && (t.getDescription() != null))
                    {
                        comboBox.getItems().add(t.getDescription());
                        myHashMap.put(t.getDescription(), i);
                    }
                }                
            }
            this.add_or_remove = 1;
        });
        temp.getChildren().add(addTreasureBttn);
        
        Button removeTreasureBttn = createButton("Remove Treasure", "");
        removeTreasureBttn.setOnAction((ActionEvent event2) -> {
            comboBox.getItems().clear();
            comboBox.getItems().add("Which Treasure?");        
            comboBox.setValue("Which Treasure?");
            for(int i= 1; i<=100; i++){
                Treasure t = new Treasure();
                Treasure tempTreasure = new Treasure();
                if(i==1){
                    comboBox.getItems().add("1000 copper pieces/level");
                    myHashMap.put("1000 copper pieces/level", i);
                } 
                else{
                    tempTreasure.chooseTreasure(i-1);
                    t.chooseTreasure(i);
                    if((t.getDescription() != tempTreasure.getDescription()) && (t.getDescription() != null))
                    {
                        comboBox.getItems().add(t.getDescription());
                        myHashMap.put(t.getDescription(), i);
                    }
                }                
            }
            this.add_or_remove = 2;
        });
        temp.getChildren().add(removeTreasureBttn);

        temp.getChildren().add(comboBox);
        Button updateBttn = createButton("Click for Update (Click current Chamber/Passage to see updated description)", "-fx-background-color:#A0FF30;");
        updateBttn.setOnAction((ActionEvent event2) -> {
            
            if( (comboBox.getValue() != null) && (myHashMap.get("Ant, giant") == 1) ){
                Monster m = new Monster();
                
                if(add_or_remove == 1){
                    m.setType(myHashMap.get(comboBox.getValue()));
                    if(chamber_or_passage == 1) currentChamber.addMonster(m);
                    else if(chamber_or_passage == 2){
                        currentPassage.addPassageSection(new PassageSection());
                        currentPassage.addMonster(m, 0);
                    }
                }
                else if(add_or_remove == 2){
                    m.setType(myHashMap.get(comboBox.getValue()));
                    if(chamber_or_passage == 1) currentChamber.removeMonster(m);
                }
                
            }
            else if( (comboBox.getValue() != null) && (myHashMap.get("1000 copper pieces/level") == 1 ) && (add_or_remove == 1) ){
                Treasure t = new Treasure();
                t.chooseTreasure(myHashMap.get(comboBox.getValue()));
                currentChamber.addTreasure(t);
                if(chamber_or_passage == 1) currentChamber.addTreasure(t);
                else if(chamber_or_passage == 2){
                    System.out.println("Treasure can not be added to Passage!");
                }
            }

            
            this.textArea.clear();
        });
        temp.getChildren().add(updateBttn);

        return temp;
    }


    private Node setCentreButtonPanel() {
        VBox room = new VBox();

        textArea.setText("Please select a cahmber or a passage to see the description");
        textArea.setStyle("-fx-text-fill: green;" +
                            "-fx-font-size: 16px;" +
                            "-fx-padding: 10;"    +
                            "-fx-border-width: 20px;" +
                            "-fx-border-insets: 50px;" +
                            "-fx-background-insets: 5px;");
        double height = 800; //making a variable called height with a value 800
        double width = 300;  //making a variable called height with a value 300

        textArea.setPrefHeight(height);  //sets height of the TextArea to 800 pixels 
        textArea.setPrefWidth(width);    //sets width of the TextArea to 300 pixels

        //room.add(textArea, 25, 25);
        room.getChildren().add(textArea);
        return room;
    }

    private Node setTopButtonPanel(){
        // create a menu 
        Menu m = new Menu("Menu"); 
  
        // create menuitems 
        MenuItem m1 = new MenuItem("save"); 
        MenuItem m2 = new MenuItem("load"); 
  
        // add menu items to menu 
        m.getItems().add(m1); 
        m.getItems().add(m2); 

        // label to display events 
        Label l = new Label("\t\t\t\t"
                            + "no menu item selected"); 

        // create events for menu items 
        // action event 
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                String task = ((MenuItem)e.getSource()).getText(); 
                l.setText("\t\t\t\t" + (task + " selected")); 

                if(task.equals("save")){
                    theController.updateData("save");
                    
                }
                else if(task.equals("load")){
                    theController.updateData("load");

                    chambers = theController.getListOfChambers();
                    passages = theController.getListOfPassages();
                    
                }
            } 
        }; 
        
        // add event 
        m1.setOnAction(event); 
        m2.setOnAction(event); 

        // create a menubar 
        MenuBar mb = new MenuBar(); 
  
        // add menu to menubar 
        mb.getMenus().add(m); 
  
        // create a VBox 
        VBox vb = new VBox(mb, l); 
        
        return vb;
    }

    private Node setRightButtonPanel() {
        /*this method should be broken down into even smaller methods, maybe one per button*/
        VBox temp = new VBox();

        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        
        

  
        // Create action event 
        EventHandler<ActionEvent> myEvent = 
                  new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 

                
                String test = theController.getDoorDescription(choiceBox.getValue());
                changeDescriptionText(test);

                 if((choiceBox.getValue() != null) && (!choiceBox.getValue().equals("List of Doors")))
                 descriptionPane.show(primaryStage);
            } 
        }; 
  
        // Set on action 
        choiceBox.setOnAction(myEvent); 

        choiceBox.getItems().add("List of Doors");        
        choiceBox.setValue("List of Doors");
        temp.getChildren().add(choiceBox);
        
        Button hideButton = createButton("Hide Description", "-fx-background-color: #FFFFFF; ");
        hideButton.setOnAction((ActionEvent event) -> {
            descriptionPane.hide();
        });
        temp.getChildren().add(hideButton);

       

    
        return temp;

    }

    private Node setLeftButtonPanel() {
        /*this method should be broken down into even smaller methods, maybe one per button*/
        VBox temp = new VBox();
        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        temp = createChamberBttns(temp);
        temp = createPassageBttns(temp);
        
        return temp;

    }

    /* an example of a popup area that can be set to nearly any
    type of node
     */
    private Popup createPopUp(int x, int y, String text) {
        Popup popup = new Popup();
        popup.setX(x);
        popup.setY(y);
        TextArea textA = new TextArea(text);
        popup.getContent().addAll(textA);
        textA.setStyle(" -fx-background-color: white;");
        textA.setMinWidth(80);
        textA.setMinHeight(50);
        return popup;
    }

    /*generic button creation method ensure that all buttons will have a
    similar style and means that the style only need to be in one place
     */
    private Button createButton(String text, String format) {
        Button btn = new Button();
        btn.setText(text);
        btn.setStyle(format);
        return btn;
    }

    private void changeDescriptionText(String text) {
        ObservableList<Node> list = descriptionPane.getContent();
        for (Node t : list) {
            if (t instanceof TextArea) {
                TextArea temp = (TextArea) t;
                temp.setText(text);
            }

        }

    }
    /* ********************************************** */
    private VBox createChamberBttns(VBox temp){

        Button firstButton = createButton("Chamber 1", "-fx-background-color: #FFFFFF;");
        firstButton.setOnAction((ActionEvent event) -> {

            addItemsToChoiceBox("Chamber", 0);
            textArea.setText(chambers.get(0).getDescription());            
            currentChamber = chambers.get(0);
            chamber_or_passage = 1;
        });
        temp.getChildren().add(firstButton);

        Button secondButton = createButton("Chamber 2", "-fx-background-color: #FFFFFF;");
        secondButton.setOnAction((ActionEvent event) -> {
            addItemsToChoiceBox("Chamber", 1);
            textArea.setText(chambers.get(1).getDescription());
            currentChamber = chambers.get(1);
            chamber_or_passage = 1;
        });
        temp.getChildren().add(secondButton);

        Button thirdButton = createButton("Chamber 3", "-fx-background-color: #FFFFFF;");
        thirdButton.setOnAction((ActionEvent event) -> {
            addItemsToChoiceBox("Chamber", 2);
            textArea.setText(chambers.get(2).getDescription());
            currentChamber = chambers.get(2);
            chamber_or_passage = 1;
        });
        temp.getChildren().add(thirdButton);

        Button fourthButton = createButton("Chamber 4", "-fx-background-color: #FFFFFF;");
        fourthButton.setOnAction((ActionEvent event) -> {
            addItemsToChoiceBox("Chamber", 3);
            textArea.setText(chambers.get(3).getDescription());
            currentChamber = chambers.get(3);
            chamber_or_passage = 1;
        });
        temp.getChildren().add(fourthButton);

        Button fifthButton = createButton("Chamber 5", "-fx-background-color: #FFFFFF;");
        fifthButton.setOnAction((ActionEvent event) -> {     
            addItemsToChoiceBox("Chamber", 4);
            textArea.setText(chambers.get(4).getDescription());
            currentChamber = chambers.get(4);
            chamber_or_passage = 1;
        });
        temp.getChildren().add(fifthButton);

        return temp;
    }
    private VBox createPassageBttns(VBox temp){

        Button firstButton = createButton("Passage 1", "-fx-background-color: #FFFFFF;");
        firstButton.setOnAction((ActionEvent event) -> {
            addItemsToChoiceBox("Passage", 0);
            textArea.setText(passages.get(0).getDescription());
            currentPassage = passages.get(0);
            chamber_or_passage = 2;
        });
        temp.getChildren().add(firstButton);

        Button secondButton = createButton("Passage 2", "-fx-background-color: #FFFFFF;");
        secondButton.setOnAction((ActionEvent event) -> {
            addItemsToChoiceBox("Passage", 1);
            textArea.setText(passages.get(1).getDescription());
            currentPassage = passages.get(1);
            chamber_or_passage = 2;

        });
        temp.getChildren().add(secondButton);

        Button thirdButton = createButton("Passage 3", "-fx-background-color: #FFFFFF;");
        thirdButton.setOnAction((ActionEvent event) -> {
            addItemsToChoiceBox("Passage", 2);
            textArea.setText(passages.get(2).getDescription());
            currentPassage = passages.get(2);
            chamber_or_passage = 2;


        });
        temp.getChildren().add(thirdButton);

        Button fourthButton = createButton("Passage 4", "-fx-background-color: #FFFFFF;");
        fourthButton.setOnAction((ActionEvent event) -> {
            addItemsToChoiceBox("Passage", 3);
            textArea.setText(passages.get(3).getDescription());
            currentPassage = passages.get(3);
            chamber_or_passage = 2;

        });
        temp.getChildren().add(fourthButton);
        return temp;
    }

    private void addItemsToChoiceBox(String type, int index){
        choiceBox.getItems().clear();
        choiceBox.getItems().add("List of Doors");        
        choiceBox.setValue("List of Doors");
        if(type.equals("Chamber"))
        {
            for(int i = 0; i < chambers.get(index).getDoors().size(); i++){
                choiceBox.getItems().add("Door "+(i+1));                
            }
            //doors.removeAll(doors);
            doors = chambers.get(index).getDoors();
        }
        else if(type.equals("Passage"))
        {
            
            for(int i = 0; i < passages.get(index).getDoors().size(); i++){
                choiceBox.getItems().add("Door "+(i+1));
            }
            //doors.removeAll(doors);
            doors = passages.get(index).getDoors();
        }

    }

    public ComboBox getChoiceBox(){
        return this.choiceBox;
    }
    public ArrayList<Door> getDoorListOfGUI(){
        return this.doors;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

    
    // passage and passage section descriptions must be fixed