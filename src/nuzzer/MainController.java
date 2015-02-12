package nuzzer;

import api.vk.Api;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuzzer.vk.auth.VkBrowser;
import nuzzer.vk.auth.VkController;
import utils.Log;

public class MainController
{
    public static Api vkApi = null;
    public static VkController vkController;

    public void onClick_vkAuth()
    {
        if(vkApi == null) //Проверка на существование access_token
        {
            if(vkController == null)  //Проверка существования окна авторизации ВКонтакте
            {
                Log.e("API", "NULL");
                vkController = new VkController();
                vkController.initiate(new Stage(), new Scene(new VkBrowser(), 500, 450)); // Инициализация окна авторизации ВКонтакте
                vkController.vkStage.show();
            }
            else
            {
                if (vkController.vkStage.isShowing())  //Если окно создано - перенаправление на него
                {
                    vkController.vkStage.requestFocus();
                }
                else
                {
                    vkController.vkStage.show();
                }
            }
        }
        else  // vkApi и accsess_token уже созданы
        {
            Log.e("API", "ACCESS");
            Log.e("access_token", vkApi.getAccessToken());
            Log.e("user_id", vkApi.getApiId());
        }
    }
}