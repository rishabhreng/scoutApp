package com.scoutingapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

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
    @FXML private ComboBox<String> ml; //match level
    @FXML private CheckBox cp; //cargo preload
//page 2
    @FXML private LimitedTextField ca, ucsa, lcsa, cmda; //cargo acquired, ucargo, lcargo, cargodropped
    @FXML private CheckBox ta; //taxied
//page 3
    @FXML private LimitedTextField ucst, lcst, cmdt; //ucargo, lcargo, cargodropped
//page4
    @FXML private CheckBox cla;
    @FXML private ComboBox<String> cl;
//page5
    @FXML private CheckBox dt;
    @FXML private ComboBox<String> de;
    @FXML private ComboBox<String> df;
    @FXML private TextField co;
    @FXML private LimitedTextField f;
//page 6
    @FXML private ImageView imageBox;

    //used for changing pages
    private static int sceneIndex = 0;
    //stores user input data
    private static final HashMap<String, String> info = new HashMap<>();

    //compiles data in info HashMap into a String of text and sends to console/QR
   @FXML public void sendInfo() throws Exception {
        StringBuilder output = new StringBuilder();
        for (Object keyName : info.keySet()) {
            output.append(keyName).append("=");
            if (info.get(keyName) == null) {}
            else if (info.get(keyName).equals("true"))  output.append("1");
            else if (info.get(keyName).equals("false")) output.append("0");
            else output.append(info.get(keyName));
            output.append(";");

        }
        output = new StringBuilder(output.substring(0, output.length() - 1));

//        two plausible ways to send QR Code
//        QRFuncs.generateQRCode(output, "src\\main\\codes\\qrcode" + info.get("mn") + "-" + info.get("tn") +".png");
        QRFuncs.generateQRCode(output.toString(), "qrcode.png");
        File file = new File("qrcode.png");
        Image img = new Image(file.getAbsolutePath());
        imageBox.setImage(img);
        System.out.println(Arrays.toString(info.entrySet().toArray()) + "info sent");
        }

    //used in changing pages, doesn't need to be edited
    private void setPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene" + (sceneIndex) + ".fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("satApp Page" + (sceneIndex));
        stage.setScene(scene);
        reloadData();
        stage.show();
    }

    private void reloadData() {

    }
    //sends data to info HashMap, needs to be edited with introduction of new data elements
    @FXML public void collectData() {
        switch (sceneIndex) {
            case 1:
                info.put("sln", sln.getText());
                info.put("tn", tn.getText());
                info.put("mn", mn.getText());
                info.put("ml", ml.getValue());
                info.put("cp", String.valueOf(cp.isSelected()));
                info.put("rp", rp.getValue());
                break;
            case 2:
                info.put("ca",ca.getText());
                info.put("ucsa", ucsa.getText());
                info.put("lcsa", lcsa.getText());
                info.put("cmda", cmda.getText());
                info.put("ta", String.valueOf(ta.isSelected()));
                break;
            case 3:
                info.put("ucst", ucst.getText());
                info.put("lcst", lcst.getText());
                info.put("cmdt", cmdt.getText());
                break;
            case 4:
                info.put("cla", String.valueOf(cla.isSelected()));
                info.put("cl", cl.getValue());
                break;
            case 5:
                info.put("dt", String.valueOf(dt.isSelected()));
                info.put("df", df.getValue());
                info.put("de", de.getValue());
                info.put("co", co.getText());
                info.put("f", f.getText());
                break;
            default:
                System.out.println("default case");
        }
        System.out.println(Arrays.toString(info.entrySet().toArray()));
    }

    //don't edit
    @FXML public void goToNextPage(ActionEvent event) throws IOException {
        collectData();
        if (sceneIndex >= 6) sceneIndex = 0;
        else sceneIndex++;
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
    public void increment(LimitedTextField txtfield) {
        txtfield.setText(String.valueOf(Integer.parseInt(txtfield.getText())+1));}
    public void decrement(LimitedTextField txtfield) {
        if(!txtfield.getText().equals("0")) txtfield.setText(String.valueOf(Integer.parseInt(txtfield.getText())-1));}

    //add more of these when you add more "incrementer/decrementer button" elements for text elements
    @FXML public void incrementCA() {increment(ca);}
    @FXML public void decrementCA() {decrement(ca);}

    @FXML public void incrementUCSA() {increment(ucsa);}
    @FXML public void decrementUCSA() {decrement(ucsa);}

    @FXML public void incrementLCSA() {increment(lcsa);}
    @FXML public void decrementLCSA() {decrement(lcsa);}

    @FXML public void incrementCMDA() {increment(cmda);}
    @FXML public void decrementCMDA() {decrement(cmda);}

    @FXML public void incrementUCST() {increment(ucst);}
    @FXML public void decrementUCST() {decrement(ucst);}

    @FXML public void incrementLCST() {increment(lcst);}
    @FXML public void decrementLCST() {decrement(lcst);}

    @FXML public void incrementCMDT() {increment(cmdt);}
    @FXML public void decrementCMDT() {decrement(cmdt);}

    @FXML public void incrementF() {increment(f);}
    @FXML public void decrementF() {decrement(f);}
}