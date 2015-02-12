package nuzzer.vk.auth;

import api.vk.Api;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import nuzzer.MainController;
import utils.Log;

import java.io.IOException;

public class VkController
{
    public static Api api;
    @FXML public WebView Authorize_vk;
    String access_token;
    String user_id;

    String[] parser1;

    public Stage stage;

    public void setStage(final Stage stage)
    {
        this.stage = stage;
        this.stage.setResizable(true);
        this.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            //
            }
        });

    }

    public void start() throws IOException
    {
        Authorize_vk.setLayoutX(MainController.db.getValue());
        Authorize_vk.getEngine().load("https://oauth.vk.com/authorize?client_id=4775337&scope=65536&redirect_uri=https://oauth.vk.com/blank.html&display=popup&v=5.28&response_type=token");
        Authorize_vk.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Log.e("URL", newValue);

                try {
                    if (newValue.startsWith("https://oauth.vk.com/blank.html")) {
                        String parser[] = newValue.split("\\&");
                        parser1 = parser[0].split("\\#");
                        parser1[0] = parser[1];
                        parser1 = parser[0].split("\\=");
                        access_token = parser1[1];


                        parser1 = parser[2].split("\\=");
                        user_id = parser1[1];
                        api = new Api(access_token, user_id);
                        Log.e("access_token", access_token);
                        Log.e("user_id", user_id);

                        if (access_token.length() != 0)
                            stage.close();
                    }
                    // else
                           /* if(newValue.startsWith("https://vk.com"))
                            {
                            start();
                            }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
