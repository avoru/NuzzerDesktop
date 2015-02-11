package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Stage primaryStage1sdfsdfsdf = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Parent root1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 500));
        //primaryStage1.setScene(new Scene(root1,12,12));
        primaryStage.show();
        //primaryStage1.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
