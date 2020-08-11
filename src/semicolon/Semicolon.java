package semicolon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Monirul Islam
 */
public class Semicolon extends Application {

    protected static Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        Semicolon.stage = primaryStage;
        Semicolon.stage.setTitle("Marks Submission and Report Module");
        Semicolon.stage.setScene(Semicolon.login());
        Semicolon.stage.setMinHeight(432);
        Semicolon.stage.setMaxHeight(432);
        Semicolon.stage.setMinWidth(702);
        Semicolon.stage.setMaxWidth(702);
        Semicolon.stage.show();
    }

    public static Scene login() {
        JFXTextField userName = new JFXTextField();
        userName.setPromptText("username");
        userName.setMinWidth(160.0);
        userName.setMaxWidth(160.0);

        JFXPasswordField password = new JFXPasswordField();
        password.setPromptText("password");
        password.setMinWidth(160.0);
        password.setMaxWidth(160.0);

        JFXButton login = new JFXButton("Login");
        login.setMinWidth(160.0);
        login.setMaxWidth(160.0);
        login.setOnAction((ActionEvent event) -> {
            Connection connect = null;
            
            if (userName.getText() != null && password.getText() != null) {
                try {
                    connect = DBConnection.connect();
                    String SQL = "select * from user where user_name = '" + userName.getText() + "' and password = '" + password.getText() + "'";
                    
                    ResultSet resultSet = connect.createStatement().executeQuery(SQL);
                    
                    if (resultSet.next()) {
                        String username = resultSet.getString("user.user_name");
                        String pass = resultSet.getString("user.password");
                        String name = resultSet.getString("user.full_name");
                        String email = resultSet.getString("user.email");
                        String usertype = resultSet.getString("user.user_type");
                        if (username.equals(userName.getText()) && pass.equals(password.getText())) {
                            userName.setText(null);
                            password.setText(null);
                            
                            switch (usertype) {
                                case "admin":
                                    Admin.adminDashboard(name, email);
                                    System.out.println("logged in");
                                    break;
                                case "faculty":
                                    Faculty.facultyDashboard(name, email);
                                    System.out.println("logged in");
                                    break;
                                case "student":
                                    Student.studentDashboard(username, name, email);
                                    System.out.println("logged in");
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            userName.setText(null);
                            password.setText(null);
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("error");
                            alert.setContentText("username or password or both are incorrect");
                            alert.showAndWait();
                        }
                    } else {
                        userName.setText(null);
                        password.setText(null);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setContentText("username or password or both are incorrect");
                        alert.showAndWait();
                    }
                    connect.close();
                } catch (SQLException ex) {
                    System.out.println("Error on Building Data");
                    System.out.println(ex);
                } finally {
                    try {
                        connect.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Semicolon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                userName.setText(null);
                password.setText(null);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("username or password cannot be empty");
                alert.showAndWait();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.add(userName, 0, 1);
        gridPane.add(password, 0, 2);
        gridPane.add(login, 0, 3);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Scene scene = new Scene(gridPane, 700, 400);
        return scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
