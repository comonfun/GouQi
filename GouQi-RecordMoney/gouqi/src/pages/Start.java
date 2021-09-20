package pages;
//Win10系统下可开启动态模糊，需要导入javafxblur包，将ddl文件加入java目录下
//import com.kieferlam.javafxblur.Blur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Start extends Application {

    static Stage giveController=null;
    @Override
    public void start(Stage stage) throws Exception {
        giveController=stage;
        Pane myPane = FXMLLoader.load(getClass().getResource("/pages/main-page.fxml"));
        Scene scene = new Scene(myPane);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("枸杞记账");
        stage.getIcons().add(new Image(getClass().getResource("/resources/app-icon.png").toExternalForm()));
        stage.show();
//        Blur.applyBlur(stage,Blur.ACRYLIC);

        stage.setOnCloseRequest(new EventHandler<>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
//        Blur.loadBlurLibrary();
        launch(args);
    }
}
