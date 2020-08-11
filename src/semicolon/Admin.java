package semicolon;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static semicolon.DBConnection.connect;

/**
 *
 * @author Monirul Islam
 */
public class Admin {

    String complainID = new String();
    String complainUserName = new String();
    String complain = new String();

    public Admin() {

    }

    public Admin(String complainID, String complainUserName, String complain) {
        this.complainID = complainID;
        this.complainUserName = complainUserName;
        this.complain = complain;
    }
    
    public String getComplainID() {
        return complainID;
    }
    
    public void setComplainID(String complainID) {
        this.complainID = complainID;
    }
    
    public String getComplainUserName() {
        return complainUserName;
    }
    
    public void setComplainUserName(String complainUserName) {
        this.complainUserName = complainUserName;
    }
    
    public String getComplain() {
        return complain;
    }
    
    public void setComplain(String complain) {
        this.complain = complain;
    }

    protected static void adminDashboard(String name, String email) {
        StackPane root = new StackPane();
        BorderPane mainContent = new BorderPane();
        mainContent.setPadding(new Insets(5, 10, 18, 10));

        Label userinfo = new Label("  " + name + "\n  " + email + "\n  " + "(admin)");
        userinfo.setMinHeight(60);
        userinfo.setMaxHeight(60);
        userinfo.setFocusTraversable(true);

        JFXButton[] menuButtons = {
            new JFXButton("☰ Add Faculty"),
            new JFXButton("☰ Remove Faculty"),
            new JFXButton("☰ View Faculty List"),
            new JFXButton("☰ Add Student"),
            new JFXButton("☰ Remove Student"),
            new JFXButton("☰ View Student List"),
            new JFXButton("☰ View Complain List")};
        for (JFXButton jfxButton : menuButtons) {
            jfxButton.setAlignment(Pos.CENTER_LEFT);
            jfxButton.setMinWidth(180);
            jfxButton.setMaxWidth(180);
            jfxButton.setMinHeight(35);
            jfxButton.setMaxHeight(35);
        }

        JFXButton logout = new JFXButton("Logout");
        logout.setAlignment(Pos.CENTER_RIGHT);
        logout.setStyle("-fx-background-color: #40404060;");

        logout.setOnAction((ActionEvent event) -> {
            System.out.println("logged out");
            Semicolon.stage.setScene(Semicolon.login());
            Semicolon.stage.show();
        });

        VBox menu = new VBox();
        menu.getStyleClass().add("content_scene_left");
        menu.getChildren().add(userinfo);
        menu.getChildren().addAll(menuButtons);

        mainContent.setLeft(menu);
        mainContent.setBottom(logout);

        menuButtons[0].setOnAction((ActionEvent event) -> {
            JFXTextField facultyUserName = new JFXTextField();
            facultyUserName.setPromptText("username");
            facultyUserName.setMinWidth(160);
            facultyUserName.setMaxWidth(160);
            JFXTextField facultyPassword = new JFXTextField();
            facultyPassword.setPromptText("password");
            facultyPassword.setMinWidth(160);
            facultyPassword.setMaxWidth(160);
            JFXButton addFaculty = new JFXButton("Add Faculty");
            addFaculty.setMinWidth(160);
            addFaculty.setMaxWidth(160);

            GridPane newFacultyRegister = new GridPane();
            newFacultyRegister.setAlignment(Pos.CENTER);
            newFacultyRegister.setVgap(15);
            newFacultyRegister.add(facultyUserName, 0, 0);
            newFacultyRegister.add(facultyPassword, 0, 1);
            newFacultyRegister.add(addFaculty, 0, 2);

            addFaculty.setOnAction((ActionEvent innerevent) -> {

                if (facultyUserName.getText() != null && facultyPassword.getText() != null) {
                    try {
                        Connection connect;
                        connect = DBConnection.connect();
                        String SQL = "insert into user (user_name, user_type, password) values ('" + facultyUserName.getText() + "', 'faculty', '" + facultyPassword.getText() + "')";
                        connect.createStatement().executeUpdate(SQL);

                        System.out.println("New Faculty Added");
                        facultyUserName.setText(null);
                        facultyPassword.setText(null);

                        menuButtons[2].fire();
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setContentText("username or password cannot be empty");
                    alert.showAndWait();
                }
            });
            mainContent.setCenter(newFacultyRegister);
        });

        menuButtons[1].setOnAction((ActionEvent event) -> {
            JFXTextField facultyUserName = new JFXTextField();
            facultyUserName.setPromptText("username");
            facultyUserName.setMinWidth(160);
            facultyUserName.setMaxWidth(160);
            JFXButton remove = new JFXButton("Remove Faculty");
            remove.setMinWidth(160);
            remove.setMaxWidth(160);

            GridPane removeFaculty = new GridPane();
            removeFaculty.setAlignment(Pos.CENTER);
            removeFaculty.setVgap(15);
            removeFaculty.add(facultyUserName, 0, 0);
            removeFaculty.add(remove, 0, 1);

            remove.setOnAction((ActionEvent innerevent) -> {

                if (facultyUserName.getText() != null) {
                    try {
                        Connection connect;
                        connect = DBConnection.connect();
                        String SQL = "delete from user where user.user_name = '" + facultyUserName.getText() + "'";
                        connect.createStatement().executeUpdate(SQL);

                        System.out.println("Faculty Removed");
                        facultyUserName.setText(null);

                        menuButtons[2].fire();
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setContentText("username or password cannot be empty");
                    alert.showAndWait();
                }
            });
            mainContent.setCenter(removeFaculty);
        });

        menuButtons[2].setOnAction((ActionEvent event) -> {
            TableColumn<Faculty, String> userName = new TableColumn<>("User Name");
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

            TableColumn<Faculty, String> fullName = new TableColumn<>("Name");
            fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            fullName.setCellFactory(TextFieldTableCell.forTableColumn());
            fullName.setOnEditCommit((TableColumn.CellEditEvent<Faculty, String> t) -> {
                ((Faculty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFullName(((Faculty) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Faculty, String> contactNo = new TableColumn<>("Contact No");
            contactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
            contactNo.setCellFactory(TextFieldTableCell.forTableColumn());
            contactNo.setOnEditCommit((TableColumn.CellEditEvent<Faculty, String> t) -> {
                ((Faculty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setContactNo(((Faculty) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Faculty, String> mail = new TableColumn<>("Email");
            mail.setCellValueFactory(new PropertyValueFactory<>("email"));
            mail.setCellFactory(TextFieldTableCell.forTableColumn());
            mail.setOnEditCommit((TableColumn.CellEditEvent<Faculty, String> t) -> {
                ((Faculty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmail(((Faculty) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableView<Faculty> facultyTable = new TableView<>();
            facultyTable.setEditable(true);
            facultyTable.setItems(Faculty.getFaculty());
            facultyTable.getColumns().addAll(userName, fullName, contactNo, mail);
            mainContent.setCenter(facultyTable);
        });

        menuButtons[3].setOnAction((ActionEvent event) -> {
            JFXTextField studentUserName = new JFXTextField();
            studentUserName.setPromptText("username");
            studentUserName.setMinWidth(160);
            studentUserName.setMaxWidth(160);
            JFXTextField studentPassword = new JFXTextField();
            studentPassword.setPromptText("password");
            studentPassword.setMinWidth(160);
            studentPassword.setMaxWidth(160);
            JFXButton addStudent = new JFXButton("Add Student");
            addStudent.setMinWidth(160);
            addStudent.setMaxWidth(160);

            GridPane newStudentRegister = new GridPane();
            newStudentRegister.setAlignment(Pos.CENTER);
            newStudentRegister.setVgap(15);
            newStudentRegister.add(studentUserName, 0, 0);
            newStudentRegister.add(studentPassword, 0, 1);
            newStudentRegister.add(addStudent, 0, 2);

            addStudent.setOnAction((ActionEvent innerevent) -> {

                if (studentUserName.getText() != null && studentPassword.getText() != null) {
                    try {
                        Connection connect;
                        connect = DBConnection.connect();
                        String SQL = "insert into user (user_name, user_type, password) values ('" + studentUserName.getText() + "', 'student', '" + studentPassword.getText() + "')";
                        connect.createStatement().executeUpdate(SQL);

                        SQL = "insert into student (user_name) values ('" + studentUserName.getText() + "')";
                        connect.createStatement().executeUpdate(SQL);

                        System.out.println("New Student Added");
                        studentUserName.setText(null);
                        studentPassword.setText(null);

                        menuButtons[5].fire();
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setContentText("username or password cannot be empty");
                    alert.showAndWait();
                }
            });
            mainContent.setCenter(newStudentRegister);
        });

        menuButtons[4].setOnAction((ActionEvent event) -> {
            JFXTextField studentUserName = new JFXTextField();
            studentUserName.setPromptText("username");
            studentUserName.setMinWidth(160);
            studentUserName.setMaxWidth(160);
            JFXButton addStudent = new JFXButton("Remove Student");
            addStudent.setMinWidth(160);
            addStudent.setMaxWidth(160);

            GridPane newStudentRegister = new GridPane();
            newStudentRegister.setAlignment(Pos.CENTER);
            newStudentRegister.setVgap(15);
            newStudentRegister.add(studentUserName, 0, 0);
            newStudentRegister.add(addStudent, 0, 1);

            addStudent.setOnAction((ActionEvent innerevent) -> {

                if (studentUserName.getText() != null) {
                    try {
                        Connection connect;
                        connect = DBConnection.connect();
                        String SQL = "delete from user where user.user_name = '" + studentUserName.getText() + "'";
                        connect.createStatement().executeUpdate(SQL);

                        SQL = "delete from student where student.user_name = '" + studentUserName.getText() + "'";
                        connect.createStatement().executeUpdate(SQL);

                        System.out.println("Student Removed");
                        studentUserName.setText(null);

                        menuButtons[5].fire();
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setContentText("username or password cannot be empty");
                    alert.showAndWait();
                }
            });
            mainContent.setCenter(newStudentRegister);
        });

        menuButtons[5].setOnAction((ActionEvent event) -> {
            TableColumn<Student, String> userName = new TableColumn<>("Student User Name");
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

            TableColumn<Student, String> fullName = new TableColumn<>("Student Name");
            fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            fullName.setCellFactory(TextFieldTableCell.forTableColumn());
            fullName.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFullName(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Student, String> contactNo = new TableColumn<>("Contact No");
            contactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
            contactNo.setCellFactory(TextFieldTableCell.forTableColumn());
            contactNo.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setContactNo(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Student, String> mail = new TableColumn<>("Email");
            mail.setCellValueFactory(new PropertyValueFactory<>("email"));
            mail.setCellFactory(TextFieldTableCell.forTableColumn());
            mail.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmail(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Student, String> sprogram = new TableColumn<>("Program");
            sprogram.setCellValueFactory(new PropertyValueFactory<>("program"));
            sprogram.setCellFactory(TextFieldTableCell.forTableColumn());
            sprogram.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setProgram(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableView<Student> studentTable = new TableView<>();
            studentTable.setEditable(true);
            studentTable.setItems(Student.getStudents());
            studentTable.getColumns().addAll(userName, fullName, contactNo, mail, sprogram);
            mainContent.setCenter(studentTable);
        });

        menuButtons[6].setOnAction((ActionEvent event) -> {
            TableColumn<Admin, String> compid = new TableColumn<>("Complain ID");
            compid.setCellValueFactory(new PropertyValueFactory<>("complainID"));

            TableColumn<Admin, String> compun = new TableColumn<>("Student Name");
            compun.setCellValueFactory(new PropertyValueFactory<>("complainUserName"));

            TableColumn<Admin, String> comp = new TableColumn<>("Contact No");
            comp.setCellValueFactory(new PropertyValueFactory<>("complain"));

            TableView<Admin> complainTable = new TableView<>();
            complainTable.setItems(Admin.getComplains());
            complainTable.getColumns().addAll(compid, compun, comp);
            mainContent.setCenter(complainTable);
        });

        Semicolon.stage.setScene(new Scene(mainContent, 700, 400));
        menuButtons[6].fire();
    }

    public static ObservableList<Admin> getComplains() {
        ObservableList<Admin> complains = FXCollections.observableArrayList();
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "select * from complain";

            ResultSet resultSet = connect.createStatement().executeQuery(SQL);

            while (resultSet.next()) {
                String compid = resultSet.getString("complain.id");
                String compun = resultSet.getString("complain.user_name");
                String comp = resultSet.getString("complain.complain");

                complains.add(new Admin(compid, compun, comp));
            }
        } catch (SQLException ex) {
            System.out.println("Error on Building Data");
            System.out.println(ex);
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return complains;
    }
}
