package nuzzer.vk.auth;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class VkController
{
    private static Stage vkStage;
    private Scene vkScene;

    public void initiate(Stage stage, Scene scene)
    {
        vkStage = stage;
        vkScene = scene;
        vkStage.setScene(vkScene);
        vkStage.setTitle("Web View");
    }

    static void closeStage()
    {
        vkStage.close();
    }
}
