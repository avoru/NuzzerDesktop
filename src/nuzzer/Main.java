package nuzzer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public static MainController mainController;

    @Override
    public void start(Stage mainStage) throws Exception
    {
        mainController = new MainController();
        mainController.initiate(mainStage); // Инициализация окна авторизации ВКонтакте
        mainController.getStage().show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
