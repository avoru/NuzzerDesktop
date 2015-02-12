package nuzzer;

import api.vk.Api;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuzzer.vk.auth.VkController;

import java.io.IOException;

public class MainController {
    public static Api api;
   // @FXML WebView test;
    String access_token;
    String user_id;
    //Controller_VK hyi;
    String[] parser1;
    public static DoubleBinding db;
    public static Stage primaryStage;

    public void VKAuth() throws IOException
    {
        primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vk/auth/authorize.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 610, 400));
        VkController vkController = loader.getController();
        vkController.setStage(primaryStage);
        primaryStage.show();
        db = primaryStage.getScene().widthProperty().subtract(610);
        vkController.start();
    }
}