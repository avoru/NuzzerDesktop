package nuzzer.vk.auth;

import api.vk.Api;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.Log;

public class VkMain extends Application
{
    @FXML
    WebView vkWebView;

    private WebEngine vkWebEngine;

    public Stage vkAuth;
    public Parent vkAuthParent;
    public static Api api;

    String access_token;
    String user_id;
    //String[] parser;
    String[] parser1;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        vkAuth = new Stage();
        vkAuthParent = FXMLLoader.load(getClass().getResource("vk/auth/vk_auth.fxml"));
        vkAuth.setScene(new Scene(vkAuthParent, 600, 500));
        vkWebView = new WebView();
        vkWebEngine = vkWebView.getEngine();

        vkAuth.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Log.e("sdfsd", "sdf");
//                vkWebEngine.load(Auth.getUrl(Api.APP_ID, Auth.getSettings()));
                vkWebEngine.load("https://oauth.vk.com/authorize?client_id=4775337&scope=wall&redirect_uri=https://oauth.vk.com/blank.html&display=popup&v=5.28&response_type=token");
                vkWebEngine.locationProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        try {
                            if (newValue.equals("")) {
                                Log.e("error", "error");
                                return;
                            }
                            if (newValue.startsWith("https://oauth.vk.com/blank.html")) {
                                Log.e("error", "error");
                                String parser[] = newValue.split("\\&");
                                parser = parser[0].split("\\#");
                                parser[0] = parser[1];
                                parser = parser[0].split("\\=");
                                access_token = parser1[1];


                                parser1 = parser[2].split("\\=");
                                user_id = parser1[1];
                                api = new Api(access_token, user_id);
                                Log.e(access_token, access_token);
                                Log.e(user_id, user_id);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        try
//                        {
//                            if(newValue.equals(""))
//                            {
//                                return;
//                            }
////                            Log.e("sdfsd", "sdf");
//                            if(newValue.startsWith("https://oauth.vk.com/blank.html"))
//                            {
//                                String[] data = Auth.parseRedirectUrl(newValue);
//                                MainController.api = new Api(data[0], data[1]);
//                                vkAuth.close();
//                            }
//                            else
//                            {
//                                vkWebEngine.load(Auth.getUrl(Api.APP_ID, Auth.getSettings()));
//                            }
//                        }
//                        catch (Exception e)
//                        {
//                            e.printStackTrace();
//                        }
                    }
                });
            }
        });
    }
}
