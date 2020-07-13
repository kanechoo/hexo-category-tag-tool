package site.konchoo.tool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage appStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        appStage.setTitle("Simple Hexo Append Category And Tags Tool");
        appStage.setScene(new Scene(root, 500, 640));
        appStage.show();
    }
}
