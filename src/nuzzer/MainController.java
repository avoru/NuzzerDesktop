package nuzzer;

import api.vk.Api;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuzzer.vk.auth.VkBrowser;
import nuzzer.vk.auth.VkController;
import utils.Log;

import java.io.IOException;

public class MainController
{
    public static Api api = null;
    Stage vkStage;

    public void VKAuth() throws IOException
    {
        if(api == null)
        {
            Log.e("API", "NULL");
            vkStage = new Stage();
            VkController vkController = new VkController();
            vkController.initiate(vkStage, new Scene(new VkBrowser(), 750, 500));
            vkStage.show();
        }
        else
        {
            Log.e("API", "ACCESS");
            Log.e("access_token", api.getAccessToken());
            Log.e("user_id", api.getApiId());
        }
    }
}