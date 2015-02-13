package nuzzer.vk.auth;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class VkController
{
    private Stage vkStage;
    private Scene vkScene;

    public void initiate(Stage stage)
    {
        vkStage = stage;
        vkScene = new Scene(new VkBrowser(), 500, 450);
        vkStage.setScene(vkScene);
        vkStage.setTitle("Авторизация ВКонтакте");
    }

    public Stage getStage()
    {
        return vkStage;
    }
}
