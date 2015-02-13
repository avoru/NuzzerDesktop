package nuzzer;

import api.vk.Api;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nuzzer.vk.auth.VkController;
import utils.Log;

public class MainController
{
    public static Api vkApi = null;
    public static VkController vkController;

    private Stage mainStage;

    private Scene mainScene;

    private Pane mainPane;
    private Pane topPane;
    private ScrollPane newsPane;
    private Pane vkAuth;
    private Label textVkAuth;

    public void initiate(Stage stage)
    {
        mainStage = stage;

        mainPane = new Pane();
        mainPane.setId("mainPane");

        mainScene = new Scene(mainPane, 1000, 600);
        mainScene.getStylesheets().add("nuzzer/Main.css");
        mainScene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                topPane.setMaxWidth(mainScene.getWidth());
                topPane.setMinWidth(mainScene.getWidth());
                topPane.setPrefWidth(mainScene.getWidth());
            }
        });
        mainStage.setScene(mainScene);
        mainStage.setMinWidth(600);
        mainStage.setOnHiding(new EventHandler<WindowEvent>() // Закрытие окна авторизации ВКонтакте при закрытии главного окна
        {
            @Override
            public void handle(WindowEvent event) {
                if (MainController.vkController != null) {
                    MainController.vkController.getStage().close();
                }
            }
        });
        mainStage.setTitle("Nuzzer");

        setBaseElements();
    }

    void setBaseElements()
    {
        topPane = new Pane();
        topPane.setPrefWidth(mainScene.getWidth());
        topPane.setPrefHeight(50);
        topPane.setId("topPane");
        topPane.setLayoutX(0);
        topPane.setLayoutY(0);
        mainPane.getChildren().add(topPane);

        newsPane = new ScrollPane();
        newsPane.setPrefWidth(600);
        newsPane.setPrefHeight(mainScene.getHeight() - 50);
        newsPane.setLayoutX((mainScene.getWidth() / 2) - 300);
        newsPane.setLayoutY(50);
        newsPane.setId("newsPane");
        mainScene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                newsPane.setLayoutX((mainScene.getWidth()/2)-300);
            }
        });
        mainScene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                newsPane.setMaxHeight(mainScene.getHeight() - 50);
                newsPane.setMinHeight(mainScene.getHeight() - 50);
                newsPane.setPrefHeight(mainScene.getHeight() - 50);
            }
        });
        mainPane.getChildren().add(newsPane);

        textVkAuth = new Label("Войти");
        textVkAuth.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        textVkAuth.getStyleClass().add("button");
        textVkAuth.setMaxWidth(70);
        textVkAuth.setFocusTraversable(false);
        textVkAuth.setMinWidth(70);
        textVkAuth.setPrefWidth(70);
        textVkAuth.setMaxHeight(49);
        textVkAuth.setMinHeight(49);
        textVkAuth.setPrefHeight(49);
        textVkAuth.setLayoutX(topPane.getWidth() - 90);
        textVkAuth.setLayoutY(0);
        textVkAuth.setAlignment(Pos.CENTER);

        topPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textVkAuth.setLayoutX(topPane.getWidth() - 90);
            }
        });
        textVkAuth.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onClick_vkAuth();
            }
        });
        topPane.getChildren().add(textVkAuth);
    }

    public void onClick_vkAuth()
    {
        if (vkApi == null) //Проверка на существование access_token
        {
            if(vkController == null)  //Проверка существования окна авторизации ВКонтакте
            {
                Log.e("API", "NULL");
                vkController = new VkController();
                vkController.initiate(new Stage()); // Инициализация окна авторизации ВКонтакте
                vkController.getStage().show();
            }
            else
            {
                if (vkController.getStage().isShowing())  //Если окно создано - перенаправление на него
                {
                    vkController.getStage().requestFocus();
                }
                else
                {
                    vkController.getStage().show();
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

    public Stage getStage()
    {
        return mainStage;
    }
}