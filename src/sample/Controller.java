package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

import java.io.IOException;

public class Controller
{
    //dsf
    @FXML WebView WV;
    String url1="https://oauth.vk.com/authorize?client_id=4775337&scope=wall&redirect_uri=https://oauth.vk.com/blank.html&display=popup&v=5.28&response_type=token";
    String access_token;
    String user_id;
    //String[] parser;
    String[] parser1;

    public void autorize() throws IOException
    {
       /* Stage primaryStage1 =new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage1.setScene(new Scene(root1,12,12));
        primaryStage1.show();*/

        WV.setVisible(true);
        WV.getEngine().load("https://oauth.vk.com/authorize?client_id=4775337&scope=wall&redirect_uri=https://oauth.vk.com/blank.html&display=popup&v=5.28&response_type=token");
        WV.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                url1=newValue;
                parseURL();
                if(url1.startsWith("https://oauth.vk.com/blank.html"))
                    WV.setVisible(false);
            }
        });

    }

    public void parseURL()
    {
        try
        {
            if(url1==null)
                return;
            if(url1.startsWith("https://oauth.vk.com/blank.html"))
            {
                String parser[] = url1.split("\\&");
                parser1 = parser[0].split("\\#");
                parser[0] = parser1[1];
                parser1 = parser[0].split("\\=");
                access_token = parser1[1];


                parser1 = parser[2].split("\\=");
                user_id = parser1[1];
                //System.out.println();
                //System.out.println(access_token);
                //System.out.println(user_id);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}

