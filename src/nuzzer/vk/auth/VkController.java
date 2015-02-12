package nuzzer.vk.auth;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class VkController
{
    public Stage vkStage;
    private Scene vkScene;

    public void initiate(Stage stage, Scene scene)
    {
        vkStage = stage;
        vkScene = scene;
        vkStage.setScene(vkScene);
        vkStage.setTitle("Авторизация ВКонтакте");
    }
}
