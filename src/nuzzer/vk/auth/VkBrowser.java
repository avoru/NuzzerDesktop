package nuzzer.vk.auth;

import api.vk.Api;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import nuzzer.MainController;
import utils.Log;

public class VkBrowser extends Region
{
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    String access_token;
    String user_id;

    public VkBrowser()
    {
        // Загрузка страници авторизации
        load("https://oauth.vk.com/authorize?client_id=4775337&scope=65536&redirect_uri=https://oauth.vk.com/blank.html&display=popup&v=5.28&response_type=token");

        //Парсинг access_token
        webEngine.locationProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, final String oldValue, final String newValue)
            {
                Log.i("URL", newValue);
                try
                {
                    if (newValue.startsWith("https://oauth.vk.com/blank.html"))
                    {
                        String parser[] = newValue.split("\\&");
                        String[] parser1 = parser[0].split("\\#");
                        parser1[0] = parser[1];
                        parser1 = parser[0].split("\\=");
                        Log.e("access_token", parser1[1]);
                        access_token = parser1[1];

                        parser1 = parser[2].split("\\=");
                        Log.e("user_id", parser1[1]);
                        user_id = parser1[1];
                        MainController.vkApi = new Api(access_token, user_id);
                        if(access_token.length() != 0)  //Если access_token получен - закрытие окна авторизации ВКонтакте
                        {
                            MainController.vkController.vkStage.close();
                        }
                    }
                    else // При перенаправлении на другие страници - возврат на исходную страницу авторизации
                    if (newValue.startsWith("https://vk.com/") || newValue.startsWith("http://vk.com/join?reg=1"))
                    {
                        Platform.runLater(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                webEngine.load(oldValue);
                            }
                        });
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        // Добавление на vkScene WebView browser
        getChildren().add(browser);
    }

    public void load(String url)
    {
        webEngine.load(url);
    }

    // Изменение размеров WebView browser как у vkScene
    @Override
    protected void layoutChildren()
    {
        layoutInArea(browser, 0, 0, getWidth(), getHeight(), 0, HPos.CENTER, VPos.CENTER);
    }
}