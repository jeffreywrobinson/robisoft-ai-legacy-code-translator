package com.robisoft.legacy.translator;
	
import java.util.ArrayList;
import java.util.List;

import com.gluonhq.richtextarea.RichTextArea;
import com.gluonhq.richtextarea.model.DecorationModel;
import com.gluonhq.richtextarea.model.Document;
import com.gluonhq.richtextarea.model.ParagraphDecoration;
import com.robisoft.legacy.translator.helpers.CommonHelpers;
import com.robisoft.legacy.translator.helpers.Constants;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DashboardMain extends Application {
	 final static String AppName = "AI Legacy Code Translator";
	 final static String startText = "Awaiting Translation";
	 private String currentFileName="";
	 private String prompt="";
	 private String payload="";	 
     int catType=0,chartIdx=0;
     private Scene scene;
	 BorderPane pane = new BorderPane(); 
	 ListView<String> listView;
	 ParagraphDecoration paragraphDecoration;
     DecorationModel decorationModel;
     Document document;
	 RichTextArea editor = new RichTextArea();
	 //RichTextArea responseText = new RichTextArea();
	 String selectedStr = "";
	 TextFlow textFlow = new TextFlow();
	 Text textSrc,text1,text2,text3,text4,text5,text6;
	 Label statusLabel = new Label("\u00a9" + "Robisoft LLC " + AppName);	 
     final String path = "/resources/images/";         
     Thread backgroundThread=null;
     ProcessAction p=null;        
	 
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {	
	   primaryStage.setTitle(AppName);        
       Group controllGrp = new Group();	      
       
	   //*************************************
       //Left Pane - Button List             *
	   //*************************************
       
       //Button #1
       Image icon1 = new Image(path+"stats.png");
       ImageView imageView1 = new ImageView(icon1);
       Button button1 = new Button("Stats", imageView1);
       button1.setMaxSize(275,250);
       button1.setAlignment(Pos.CENTER_LEFT);
       button1.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
           	   startTask(primaryStage,"stats");
           }
       });

       //Button #2
       Image icon2 = new Image(path+"design.png");       
       ImageView imageView2 = new ImageView(icon2);
       Button button2 = new Button("Notes", imageView2);
       button2.setMaxSize(275, 250);
       button2.setAlignment(Pos.CENTER_LEFT);
       button2.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
        	   startTask(primaryStage,"design");
         }
       });

       //Button #3
	   Image icon3 = new Image(path+"pseudocode.png");
       ImageView imageView3 = new ImageView(icon3);
       Button button3 = new Button("Pseudo code", imageView3);
       button3.setMaxSize(275, 250);
       button3.setAlignment(Pos.CENTER_LEFT);
       button3.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
        	   startTask(primaryStage,"pseudo");
           }
       });

       //Button #4
       Image icon4 = new Image(path+"flowchart.png");
       ImageView imageView4 = new ImageView(icon4);
       Button button4 = new Button("Flowchart", imageView4);
       button4.setMaxSize(275,250);
       button4.setAlignment(Pos.CENTER_LEFT);
       button4.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              startTask(primaryStage,"chart");
           }
       });

       //Button #5
       Image icon5 = new Image(path+"symtbl.png");       
       ImageView imageView5 = new ImageView(icon5);
       Button button5 = new Button("Symbol Table", imageView5);
       button5.setMaxSize(275, 250);
       button5.setAlignment(Pos.CENTER_LEFT);
       button5.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
        	   startTask(primaryStage,"symtbl");
         }
       });

       //Button #6
	   Image icon6 = new Image(path+"target.png");
       ImageView imageView6 = new ImageView(icon6);
       Button button6 = new Button("Target code", imageView6);
       button6.setMaxSize(275, 250);
       button6.setAlignment(Pos.CENTER_LEFT);
       button6.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
        	   startTask(primaryStage,"target");
           }
       });

       
       //Add all buttons to the Left panel
       controllGrp.getChildren().addAll(button1,button2,button3,button4,button5,button6);
	   VBox leftPane = new VBox(button1,button2,button3,button4,button5,button6);
	   ScrollPane scrollLeft = new ScrollPane();
       scrollLeft.setContent(leftPane);
       scrollLeft.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollLeft.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       
	   //************************************************
       //Center Pane - Main display area with scrolling *
	   //************************************************
       textSrc = new Text("Load Source File");
       ScrollPane scrollSource = new ScrollPane();
       scrollSource.setContent(textSrc);
       scrollSource.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollSource.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       
       //TitledPane for the source code statistics
       TitledPane pane1 = new TitledPane();
       pane1.setText("Statistics");
       text1 = new Text("No Stats");
       ScrollPane scrollPane1 = new ScrollPane();
       scrollPane1.setContent(text1);
       scrollPane1.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollPane1.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       pane1.setContent(scrollPane1);
       
       //Creating the second TitledPane
       TitledPane pane2 = new TitledPane();
       pane2.setText("GenAIDesign Notes");
       text2 = new Text("Empty");
       ScrollPane scrollPane2 = new ScrollPane();
       scrollPane2.setContent(text2);
       scrollPane2.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollPane2.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       pane2.setContent(scrollPane2);
       
       //Creating the third TitledPane
       TitledPane pane3 = new TitledPane();
       pane3.setText("GenAI Pseudo-code");
       text3 = new Text("No Pseudo-code");
       ScrollPane scrollPane3 = new ScrollPane();
       scrollPane3.setContent(text3);
       scrollPane3.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollPane3.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       pane3.setContent(scrollPane3);

       //Creating the fourth TitledPane
       TitledPane pane4 = new TitledPane();
       pane4.setText("Chart");
       text4 = new Text("No Chart");
       ScrollPane scrollPane4 = new ScrollPane();
       scrollPane4.setContent(text4);
       scrollPane4.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollPane4.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       pane4.setContent(scrollPane4);
  
       //Creating the fifth TitledPane
       TitledPane pane5 = new TitledPane();
       pane5.setText("Symbol Table");
       text5 = new Text("No symbol table");
       ScrollPane scrollPane5 = new ScrollPane();
       scrollPane5.setContent(text5);
       scrollPane5.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollPane5.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       pane5.setContent(scrollPane5);
       
       //Creating the sixth TitledPane
       TitledPane pane6 = new TitledPane();
       pane6.setText("Target Code");
       text6 = new Text("No target code");
       ScrollPane scrollPane6 = new ScrollPane();
       scrollPane6.setContent(text6);
       scrollPane6.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);               
       scrollPane6.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       pane6.setContent(scrollPane6);

       //Creating an Accordion for all TitledPane
       Accordion accordionNew = new Accordion();
       accordionNew.getPanes().addAll(pane1, pane2, pane3, pane4, pane5, pane6);
       VBox centerPane = new VBox(scrollSource,accordionNew);

       //Setup Editor settings
       /*TextDecoration textDecoration = TextDecoration.builder().presets()
               .fontFamily("Arial")
               .fontSize(12)
               .foreground("black")
               .build();
       
       paragraphDecoration = ParagraphDecoration.builder().presets().build();
       decorationModel = new DecorationModel(0, startText.length(), textDecoration, paragraphDecoration);
       document = new Document(startText, List.of(decorationModel), startText.length());
       responseText.getActionFactory().open(document).execute(new ActionEvent()); */
       
       //Create RichText Editor       
       ScrollPane scrollPane = new ScrollPane();
       scrollPane.setContent(centerPane);
       scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);               
       scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
       	   
	   //*************************************
       //Set menu for top area
	   //*************************************
       MenuBar menuBar = buildMenu(primaryStage);
       HBox topPane = new HBox(menuBar);        
       
       /***************************************************
	   //Establish master border layout pane
	    ***************************************************/
       pane.setTop(topPane);
       pane.setLeft(scrollLeft);
       pane.setCenter(centerPane);
       pane.setBottom(statusLabel);
       
       //Set the scene        
       setScene(new Scene(pane, 1000, 600));
       scene.getStylesheets().add("com/robisoft/legacy/translator/application.css");        
       primaryStage.setScene(getScene());
       primaryStage.setTitle(AppName);
       Image appIcon = new Image(path+"system1.png");       
       primaryStage.getIcons().add(appIcon);
       primaryStage.show();       
	}

	public static void main(String[] args) {
		launch(args);
	}
	
   
	/**
	 * Setup and start background thread
	 */
	public synchronized void startTask(Stage primaryStage, String action) {
	    Alert dialog = new Alert(Alert.AlertType.NONE);
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    dialog.initOwner(primaryStage);
	    dialog.setHeaderText(
	            "Please Wait -- Contacting "
	            + Constants.getModel().toUpperCase());
	    dialog.setGraphic(new ProgressIndicator());
	    dialog.show();

	    Task<String> task = new Task<>() {
	        @Override
	        protected String call() throws Exception {
	            setPrompt(action);
	            p = new ProcessAction();

	            return p.executeAPI(getPrompt(), getPayload());
	        }
	    };

	    task.setOnSucceeded(event -> {
	        String response = task.getValue();

	        dialog.close();
	        getScene().setCursor(Cursor.DEFAULT);
	        setResponse(action, response);

	  //      CommonHelpers.showAlertLater(
	  //              AlertType.INFORMATION,
	  //              "Process Completed");
	    });

	    task.setOnFailed(event -> {
	        dialog.close();
	        getScene().setCursor(Cursor.DEFAULT);

	        Throwable exception = task.getException();
	        if (exception != null) {
	            exception.printStackTrace();
	        }

	    //    CommonHelpers.showAlertLater(
	    //            AlertType.ERROR,
	    //            "Process Failed");
	    });

	    task.setOnCancelled(event -> {
	        dialog.close();
	        getScene().setCursor(Cursor.DEFAULT);
	    });

	    getScene().setCursor(Cursor.WAIT);

	    backgroundThread = new Thread(task);
	    backgroundThread.setDaemon(true);
	    backgroundThread.start();
	}
	
   /**
   	 * builds the application menu   
   	 * @param stage
   	 * @return
   	 */
    private MenuBar buildMenu(Stage stage) {
    	Menu menu = new Menu("File");
        
    	MenuItem fileItem1 = new MenuItem("Open File");    	
    	fileItem1.setOnAction(e -> {
        	setCurrentFileName(CommonHelpers.chooseFileName("Open Source File","ALL","*.*"));
        	if ((getCurrentFileName()!=null) && (!getCurrentFileName().trim().isEmpty())) {
        		List<String> list1 = new ArrayList<String>();
        		CommonHelpers.readFile2List(getCurrentFileName(),list1);
        		if (list1.size()>0) {
        			setPayload(list1);
        			textSrc.setText(getPayload());
        		}
        	}	
        	
        });
    	
    	MenuItem fileItem3 = new MenuItem("Exit " + AppName);
        fileItem3.setOnAction(e -> {
        	System.exit(0);
        });
        menu.getItems().addAll(fileItem1,fileItem3);
        
        //Add Items to the menuBar
        MenuBar menuBar = new MenuBar();        
        menuBar.getMenus().add(menu);
        
        //Add Help to the menu
        Menu menu2 = new Menu("Help");
        MenuItem helpItem1 = new MenuItem("About " + AppName);
        helpItem1.setOnAction(e -> {
    		doHelpAbout(stage);
    	});
        menu2.getItems().add(helpItem1);
        menuBar.getMenus().add(menu2);
        
       //Return menu bar
        return(menuBar);
    }
	
	private void doHelpAbout(Stage stage) {
	  String msg = "(c)2026 Robisoft LLC. All Rights Reserved.";	
	  CommonHelpers.showAlert(AlertType.INFORMATION, stage.getOwner(), "About "+AppName, msg);	
	}

	private void setResponse(String action, String response) {
		if (action.equals("stats"))
			this.text1.setText(response); 
		else if (action.equals("design"))
			this.text2.setText(response); 
		else if (action.equals("pseudo"))
			this.text3.setText(response); 
		else if (action.equals("chart"))
			this.text4.setText(response); 
		else if (action.equals("symtbl"))
			this.text5.setText(response); 
		else if (action.equals("target"))
			this.text6.setText(response); 
		
		return;
	}
	
	
    /***********************************************************/
    /*Public Getter & Setters                                  */
    /************************************************************/

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public String getCurrentFileName() {
		return currentFileName;
	}

	public void setCurrentFileName(String currentFilename) {
		this.currentFileName = currentFilename;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String tcAction) {
		if (tcAction.equals("stats"))
			this.prompt = Constants.prompt1;
		else if (tcAction.equals("design"))
			this.prompt = Constants.prompt2;
		else if (tcAction.equals("pseudo"))
			this.prompt = Constants.prompt3;
		else if (tcAction.equals("chart"))
			this.prompt = Constants.prompt4;
		else if (tcAction.equals("symtbl"))
			this.prompt = Constants.prompt5;
		else if (tcAction.equals("target"))
			this.prompt = Constants.prompt6;

		return;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(List<String> list1) {
        StringBuilder payload = new StringBuilder("");
		for (String item : list1) {
			payload.append(item + "\n");
		}
		this.payload = payload.toString();
	}
	
}
