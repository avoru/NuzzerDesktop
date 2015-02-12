package nuzzer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application
{
    @Override
    public void start(Stage mainStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        mainStage.setTitle("Nuzzer");
        mainStage.setScene(new Scene(root, 600, 600));
        mainStage.getScene().getStylesheets().add("nuzzer/Main.css");
        mainStage.setOnHiding(new EventHandler<WindowEvent>() // Закрытие окна авторизации ВКонтакте при закрытии главного окна
        {
            @Override
            public void handle(WindowEvent event)
            {
                if(MainController.vkController != null)
                {
                    MainController.vkController.vkStage.close();
                }
            }
        });
        mainStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
