import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryastage) throws Exception {
        //primaryastage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/home_page_form.fxml"))));
        primaryastage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashBord_form.fxml"))));
        primaryastage.setTitle(" welcome Form");
        primaryastage.centerOnScreen();
        primaryastage.show();
    }
}

