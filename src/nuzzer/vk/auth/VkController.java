package nuzzer.vk.auth;

import api.vk.Api;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VkController
{
    public static Api api;

    private Stage stage;
    private Scene scene;

    public void initiate(Stage stage, Scene scene)
    {
        this.stage = stage;
        this.scene = scene;
        this.stage.setScene(this.scene);
        this.stage.setTitle("Web View");
    }
}
