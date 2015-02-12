package nuzzer;

import api.vk.Api;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nuzzer.vk.auth.VkBrowser;
import nuzzer.vk.auth.VkController;

import java.io.IOException;

public class MainController
{
    public static Api api;
    public static Stage vkStage;

    public void VKAuth() throws IOException
    {
        vkStage = new Stage();
        VkController vkController = new VkController();
        vkController.initiate(vkStage, new Scene(new VkBrowser(), 750, 500));
        vkStage.show();
    }
}