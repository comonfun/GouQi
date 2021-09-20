package pages;

import datatools.DatabaseUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginController {

    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private GridPane loginPane;
    @FXML
    private Label tip;


    public void login(ActionEvent actionEvent) {
        String sUser=user.getText();
        String sPassWord = password.getText();
        final String verify = DatabaseUtils.verify(sUser, sPassWord);
        if(verify ==null)tip.setText("未注册或密码错误");
        else {
            MainController.toLogin.setText(verify);
            MainController.toLogin1.close();
        }
    }

    PasswordField rootPassword = new PasswordField();

    public void regist(ActionEvent actionEvent) {
        final ObservableList<Node> children = loginPane.getChildren();
        if (!children.contains(rootPassword)){
            rootPassword.setPromptText("需要输入管理员密码");
            GridPane.setConstraints(rootPassword,0,4,2,1);
            children.add(rootPassword);
            tip.setText("");
            return;
        }
        final String user = this.user.getText().strip();
        final String password = this.password.getText().strip();
        final String rootPassword = this.rootPassword.getText().strip();
        if(user.equals("") || password.equals("")){
            tip.setText("账户密码不能为空");
        }
        else if(rootPassword.equals("")){
            tip.setText("管理员密码不能为空");
        }
        else if(user.equals("root")){
            tip.setText("不能注册用户为管理员");
        }
        else {
            if(DatabaseUtils.addUser(user,password, rootPassword))tip.setText("注册成功，直接点击登录");
            else tip.setText("管理员密码错误");
        }
    }
}
