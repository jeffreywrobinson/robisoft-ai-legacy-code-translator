package com.robisoft.legacy.translator.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.FileChooser;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CommonHelpers {
    public static final long MAX_SOURCE_FILE_BYTES = 1_000_000;
	
	/**
	 * Show and alert type dialog
	 * @param alertType
	 * @param owner
	 * @param title
	 * @param message
	 */
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    /**
     * Show an Alert Message using run later
     * @param msg
     */
    public static void showAlertLater(Alert.AlertType alertType,String msg) {
  	  Platform.runLater(new Runnable() {
  		  @Override
  		  public void run() {
  			showAlert(alertType, null, "Attention!",msg);  
  		  }
  	  });  
    }
    
    public static String showChoiceDialog(String[] list,String defaultStr,String title, String optStr) {
       String index = "";
       
       ChoiceDialog<String> dialog = new ChoiceDialog<String>(defaultStr,list);  
       dialog.setTitle(title);
       dialog.setHeaderText("Please make a selection");
       dialog.setContentText(optStr);
       
       Optional<String> result = dialog.showAndWait();
       
       if (result.isPresent()){
    	  index = result.get();
       }
       return(index);
    }
    
    public static void getInputLater(String title,String defaultStr) {
    	  Platform.runLater(new Runnable(  ) {
    		  @Override
    		  public void run() {
    			getUserInput(title,defaultStr);  
    		  }
    	  });  
    	  
      }
		  
    public static String getUserInput(String title,String defaultStr) {
    	String tempStr = defaultStr;
    	Stage stage = new Stage();
   
    	Text txtHdr = new Text("Enter Input Below");
    	Label txtLabel = new Label(defaultStr);
    	TextField txtInput = new TextField();
    	txtInput.setPrefWidth(150);
    	HBox hbox1 = new HBox(txtLabel,txtInput);
    	hbox1.setAlignment(Pos.CENTER_LEFT);
    	hbox1.setSpacing(5);
    	hbox1.setPrefWidth(150);;
    	HBox.setHgrow(txtInput,Priority.ALWAYS);
    	
    	Button button1 = new Button("Cancel");
    	button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Node  source = (Node)  event.getSource(); 
        		Stage stage  = (Stage) source.getScene().getWindow();
        		stage.close();
            }
        });
    	Button button2 = new Button("Save");
    	button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Node  source = (Node)  event.getSource(); 
        		Stage stage  = (Stage) source.getScene().getWindow();
        		stage.close();
            }
        });
    	
    	HBox hbox2 = new HBox(button1,button2);
    	hbox2.setAlignment(Pos.BOTTOM_RIGHT);
    	hbox2.setPrefWidth(150);;
    	hbox2.setSpacing(5);;
    	
    	VBox vbox = new VBox(txtHdr,hbox1,hbox2);
    	vbox.setAlignment(Pos.CENTER);
    	vbox.setPrefWidth(150);
    	vbox.setSpacing(10);
    	
    	AnchorPane anchor = new AnchorPane(vbox);

    	Scene scene = new Scene(anchor, 350, 150);
    	stage.setTitle(title);
    	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(scene);
    	stage.showAndWait();

    	//Update return string
    	tempStr = txtInput.getText().trim();

    	return(tempStr);
    }

    public static boolean doesFileExist(String fileName) {
      if (fileName == null || fileName.isBlank()) {
          return false;
      }
      Path path = Path.of(fileName);
      return Files.isRegularFile(path) && Files.isReadable(path);
    }
    
    public static String readFileData(String fileName) throws IOException {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name must not be blank.");
        }
        Path path = Path.of(fileName);
        if (!Files.isRegularFile(path) || !Files.isReadable(path)) {
            throw new IOException("The selected path is not a readable file.");
        }
        long size = Files.size(path);
        if (size > MAX_SOURCE_FILE_BYTES) {
            throw new IOException("Source file exceeds the 1 MB safety limit.");
        }
        return Files.readString(path, StandardCharsets.UTF_8);
    }
    
    public static void readFile2List(String fileName,List<String> lineList) throws IOException {
        if (lineList == null) {
            throw new IllegalArgumentException("Destination list must not be null.");
        }
        lineList.addAll(readFileData(fileName).lines().toList());
	}
    
    /**
     * Pause thread number of seconds given
     */
    public static void pause(int seconds) {
    	int milli = seconds * 1000;
    	
    	try {    		
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}  
    }
    
    /**
     * Logging feature
     */
    public static void logger(String msg) {
      System.out.println("LOG>"+msg);	
    }

    
    public static boolean isCorrectPage(String url,String pageName) {
    	boolean retFlag = true;

        if (url.contains(pageName))
        	retFlag = true;
        else
           retFlag = false;
        
    	return(retFlag);
    }
    
    /**
     * Choose a filename
     * @param title
     * @param ftype - file type
     * @param fext - file extension
     * @return
     */
    public static String chooseFileName(String title,String ftype,String fext) {
      String retFileName=null;
      
      FileChooser fileChooser = new FileChooser();		
      fileChooser.setTitle(title);
      fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(ftype,fext));
      File filename = fileChooser.showOpenDialog(null);
      if (filename!=null) {
        if (!filename.getPath().trim().isEmpty())  
     	  retFileName = filename.getPath().trim();
	  }	 

      return(retFileName);	    	
    }
    
}  
