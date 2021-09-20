package pages;

import datatools.DatabaseUtils;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tools.Beans;
import tools.Weather;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static pages.TableController.items;

public class MainController implements Initializable {

    private final Stage stage = Start.giveController;

    GridPane[] pans=new GridPane[3];

    @FXML
    private Pane topBar;
    @FXML
    private GridPane gp1;
    @FXML
    private GridPane gp2;
    @FXML
    private GridPane gp3;
    @FXML
    private Label position;
    @FXML
    private Label userName;
    @FXML
    private Button count;
    private TableView<OneRow> datatable=(TableView<OneRow>) FXMLLoader.load(getClass().getResource("/pages/table_page.fxml"));

    @FXML
    private Pagination selector;
    public static Pagination toModleSelector;

    private List<Weather.DataBean.ForecastBean> beans;
    private double initX;
    private double initY;
    private List data;
    private Stage loginStage = new Stage();
    private Stage addStage=null;
    private final InitHandler handler=new InitHandler();
    static Label toLogin;

    static Stage toLogin1=null;
    public MainController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        toModleSelector=selector;
        toLogin=userName;
        items = datatable.getItems();
//        add.setDefaultButton(true);

            final int max = handler.getMax();
        selector.setPageCount(max/25+1);
        selector.setMaxPageIndicatorCount(10);

        selector.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer page) {
                handler.initTable(25,page);
                return datatable;
            }
        });

        topBar.setOnMousePressed(e -> {
            initX = e.getScreenX() - stage.getX();
            initY = e.getScreenY() - stage.getY();
        });

        topBar.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - initX);
            stage.setY(e.getScreenY() - initY);
        });

        try {
            data = Beans.initPos();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data == null){
            return;
        }
        position.setText((String) data.get(0));
        pans[0]=gp1;
        pans[1]=gp2;
        pans[2]=gp3;
    }

    public void close(MouseEvent mouseEvent) {
        stage.close();
        System.exit(0);
    }

    public void min(MouseEvent mouseEvent) {
            stage.setIconified(true);
    }

    private TranslateTransition[] t=new TranslateTransition[3];

    public void show(ActionEvent actionEvent) throws URISyntaxException, ClassNotFoundException, SQLException, IOException {

        if(data==null || pans[0].getTranslateX()<240){
            return;
        }

        beans = Beans.getForecastBeans((int)data.get(1));
        for(int i = 0;i<3;i++){
            Weather.DataBean.ForecastBean bean = beans.get(i);
            ImageView iv1 = (ImageView) pans[i].lookup(".iv1");
            ImageView iv2 = (ImageView) pans[i].lookup(".iv2");
            iv1.setImage(new Image(getClass().getResource("/resources/weather/W"+bean.getConditionIdDay()+".png").toExternalForm()));
            iv2.setImage(new Image(getClass().getResource("/resources/weather/W"+bean.getConditionIdNight()+".png").toExternalForm()));
            Text text1 = (Text) pans[i].lookup(".t1");
            Text text2 = (Text) pans[i].lookup(".t2");
            text1.setText("白天"+bean.getConditionDay()+"温度"+bean.getTempDay()+"℃");
            text2.setText(bean.getTempNight()+"℃夜晚"+bean.getConditionNight());
            t[i]=new TranslateTransition(Duration.seconds(4),pans[i]);
            t[i].setToX(0);
        }
        play();
    }

    void play(){
        for (TranslateTransition each:t){
            each.play();
        }
    }


    public void login(MouseEvent mouseEvent) {
        if(loginStage!=null && loginStage.isShowing()){
            if(loginStage.isIconified()) loginStage.setIconified(false);
            loginStage.requestFocus();
            return;
        }

        Pane addPane = null;
        try {
            addPane = (Pane) FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginStage.setScene(new Scene(addPane));
        MainController.toLogin1=loginStage;
        loginStage.show();
    }

    public void addData(ActionEvent actionEvent) {
//        if(userName.getText().equals("未登录"))return;
        final TableCell<OneRow, String> toMain = TableController.toMain;
        if(addStage==null){
            Pane addPane = null;
            try {
                addPane = (Pane) FXMLLoader.load(getClass().getResource("add.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            addStage = new Stage();
            addStage.setScene(new Scene(addPane));
            addStage.setX(Start.giveController.getX()+1010);
        }
        if(addStage.isShowing()){
            if(addStage.isIconified())addStage.setIconified(false);
            addStage.requestFocus();
            return;
        }
        addStage.show();
    }

    public void refresh(ActionEvent actionEvent) {
        try {
            data = Beans.initPos();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(data == null){
            return;
        }
        position.setText((String) data.get(0));
        pans[0]=gp1;
        pans[1]=gp2;
        pans[2]=gp3;
    }

    public void countAll(ActionEvent actionEvent) {
        final String sql = "select sum(number) from details";
        count.setText(
                "支"+DatabaseUtils.count(sql+" where number<0")+
                "收"+DatabaseUtils.count(sql+" where number>0"));
    }
}