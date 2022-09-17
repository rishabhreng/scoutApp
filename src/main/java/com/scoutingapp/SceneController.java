package com.scoutingapp;

//TODO add QR functionality to sendInfo()

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneController {

    //scene0:begin
    //scene1:pregame
    //scene2:auton
    //scene3:teleop
    //scene4:endgame
    //scene5:othernotes
    //scene6:QR CODE

//page 1
    @FXML private LimitedTextField tn; //team number
    @FXML private LimitedTextField mn; //match number
    @FXML private LimitedTextField sln; //scouter last name
    @FXML private ComboBox<String> rp; //robot position
    @FXML private ComboBox<String> ml;
    @FXML private CheckBox cp;
//page 2
    @FXML private Text ca;

    //used for changing pages
    private static int sceneIndex = 0;

    //stores user input data
    private static HashMap<String, String> info = new HashMap<>();

    //compiles data in info HashMap into a String of text and sends to console
    public void sendInfo() {
//        System.out.println(Arrays.toString(info.entrySet().toArray()));
        String output = "";
        for (Object keyName : info.keySet()) {
            output += keyName;
            output = output + "=" + info.get(keyName) + ";";
        }
        output = output.substring(0, output.length()-1);
        System.out.println(output);
    }

    //used in changing pages, doesn't need to be edited
    private void setPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene" + (sceneIndex) + ".fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("satApp Page" + (sceneIndex));
        stage.setScene(scene);
        stage.show();
    }

    //sends data to info HashMap, needs to be edited with introduction of new data elements
    public void collectData() {
        switch (sceneIndex) {
            case 1:
                info.put("sln", sln.getText());
                info.put("tn", tn.getText());
                info.put("mn", mn.getText());
                info.put("ml", ml.getValue());
                info.put("cp", String.valueOf(cp.isSelected()));
                System.out.println(Arrays.toString(info.entrySet().toArray()));
                break;
            case 2:
                info.put("ca",ca.getText());
                System.out.println(Arrays.toString(info.entrySet().toArray()));
                break;
            default:
                System.out.println(Arrays.toString(info.entrySet().toArray()));
        }
    }

    //don't edit
    @FXML public void goToNextPage(ActionEvent event) throws IOException {
        collectData();
        if (sceneIndex < 6) sceneIndex++;
        setPage(event);
    }
    @FXML public void goToPrevPage(ActionEvent event) throws IOException {
        collectData();
        if (sceneIndex > 0) sceneIndex--;
        setPage(event);
    }

    //edit when you want new restrictions for certain data
    @FXML public void limit(KeyEvent keyEvent) {
        LimitedTextField src = (LimitedTextField) keyEvent.getSource();
        if (src.equals(tn)) {
            src.setIntegerField();
            src.setMaxLength(4);
        }
        else if (src.equals(mn)) {
            src.setIntegerField();
            src.setMaxLength(3);
        }
        else if (src.equals(sln)) {
            src.setLetterField();
            src.setMaxLength(30);
        }
    }

    //don't edit
    public void increment(Text text) {text.setText(String.valueOf(Integer.parseInt(text.getText())+1));}
    public void decrement(Text text) {if(!text.getText().equals("0")) text.setText(String.valueOf(Integer.parseInt(text.getText())-1));}

    //add more of these when you add more "incrementer/decrementer button" elements for text elements
    @FXML public void incrementCA(ActionEvent actionEvent) {increment(ca);}
    @FXML public void decrementCA(ActionEvent actionEvent) {decrement(ca);}
}