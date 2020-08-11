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
public class Faculty {
    
    String userName = new String();
    String fullName = new String();
    String contactNo = new String();
    String email = new String();
    
    public Faculty() {

    }

    public Faculty(String userName, String fullName, String contactNo, String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.contactNo = contactNo;
        this.email = email;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFullName(String userName, String fullName) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update user set user.full_name = '" + fullName + "' where user.user_name = '" + userName + "'";

            connect.createStatement().executeUpdate(SQL);
            System.out.println("database updated");
        } catch (SQLException ex) {
            System.out.println("Error on Building Data");
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(Semicolon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setContactNo(String userName, String contactNo) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update user set user.contact_no = '" + contactNo + "' where user.user_name = '" + userName + "'";

            connect.createStatement().executeUpdate(SQL);
            System.out.println("database updated");
        } catch (SQLException ex) {
            System.out.println("Error on Building Data");
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(Semicolon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail(String userName, String email) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update user set user.email = '" + email + "' where user.user_name = '" + userName + "'";

            connect.createStatement().executeUpdate(SQL);
            System.out.println("database updated");
        } catch (SQLException ex) {
            System.out.println("Error on Building Data");
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(Semicolon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected static void facultyDashboard(String name, String email) {
        StackPane root = new StackPane();
        BorderPane mainContent = new BorderPane();
        mainContent.setPadding(new Insets(5, 10, 18, 10));

        Label userinfo = new Label("  " + name + "\n  " + email + "\n  " + "(faculty)");
        userinfo.setMinHeight(60);
        userinfo.setMaxHeight(60);
        userinfo.setFocusTraversable(true);

        JFXButton[] menuButtons = {
            new JFXButton("☰ Edit/ Submit Marks"),
            new JFXButton("☰ Create Report")};
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
            TableColumn<Student, String> userName = new TableColumn<>("StudentID");
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

            TableColumn<Student, String> fullName = new TableColumn<>("Student Name");
            fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));

            TableColumn<Student, String> contactNo = new TableColumn<>("Contact No");
            contactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

            TableColumn<Student, String> firstTermMarks = new TableColumn<>("First Term Marks");
            firstTermMarks.setCellValueFactory(new PropertyValueFactory<>("firstTermMarks"));
            firstTermMarks.setCellFactory(TextFieldTableCell.forTableColumn());
            firstTermMarks.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFirstTermMarks(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Student, String> midTermMarks = new TableColumn<>("Mid Term Marks");
            midTermMarks.setCellValueFactory(new PropertyValueFactory<>("midTermMarks"));
            midTermMarks.setCellFactory(TextFieldTableCell.forTableColumn());
            midTermMarks.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setMidTermMarks(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Student, String> finalTermMarks = new TableColumn<>("Final Term Marks");
            finalTermMarks.setCellValueFactory(new PropertyValueFactory<>("finalTermMarks"));
            finalTermMarks.setCellFactory(TextFieldTableCell.forTableColumn());
            finalTermMarks.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFinalTermMarks(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Student, String> assignmentMarks = new TableColumn<>("Assignment Marks");
            assignmentMarks.setCellValueFactory(new PropertyValueFactory<>("assignmentMarks"));
            assignmentMarks.setCellFactory(TextFieldTableCell.forTableColumn());
            assignmentMarks.setOnEditCommit((TableColumn.CellEditEvent<Student, String> t) -> {
                ((Student) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAssignmentMarks(((Student) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).getUserName(), t.getNewValue());
            });

            TableColumn<Student, String> finalMarks = new TableColumn<>("Total Marks");
            finalMarks.setCellValueFactory(new PropertyValueFactory<>("finalMarks"));

            TableColumn<Student, String> grade = new TableColumn<>("ExamGrade");
            grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

            TableView<Student> studentTable = new TableView<>();
            studentTable.setEditable(true);
            studentTable.setItems(Student.getStudents());
            studentTable.getColumns().addAll(userName, fullName, contactNo, firstTermMarks, midTermMarks, finalTermMarks, assignmentMarks, finalMarks, grade);
            mainContent.setCenter(studentTable);
        });

        menuButtons[1].setOnAction((ActionEvent event) -> {
            JFXTextField studentUserName = new JFXTextField();
            studentUserName.setPromptText("Student User Name");
            studentUserName.setMinWidth(180);
            studentUserName.setMaxWidth(180);
            studentUserName.setMinHeight(35);
            studentUserName.setMaxHeight(35);
            JFXButton report = new JFXButton("Create Report");
            report.setMinWidth(180);
            report.setMaxWidth(180);
            report.setMinHeight(35);
            report.setMaxHeight(35);

            GridPane createReport = new GridPane();
            createReport.setAlignment(Pos.CENTER);
            createReport.setVgap(15);
            createReport.add(studentUserName, 0, 0);
            createReport.add(report, 0, 1);
            mainContent.setCenter(createReport);

            report.setOnAction((ActionEvent innerevent) -> {

                try {
                    Connection connect;
                    connect = DBConnection.connect();
                    String SQL = "select * from user inner join student on user.user_name = student.user_name";

                    ResultSet resultSet = connect.createStatement().executeQuery(SQL);

                    while (resultSet.next()) {
                        String user_name = resultSet.getString("user.user_name");
                        String full_name = resultSet.getString("user.full_name");
                        String contact_no = resultSet.getString("user.contact_no");
                        String ftMarks = resultSet.getString("student.ftm");
                        String mtMarks = resultSet.getString("student.mtm");
                        String fiMarks = resultSet.getString("student.fm");
                        String aMarks = resultSet.getString("student.am");
                        String fMarks = resultSet.getString("student.total_marks");
                        String sGrade = resultSet.getString("student.grade");

                        GridPane studentReport = new GridPane();
                        studentReport.setAlignment(Pos.CENTER);
                        studentReport.setVgap(15);
                        studentReport.setHgap(15);
                        studentReport.add(new Label("Student User Name:"), 0, 0);
                        studentReport.add(new Label(user_name), 1, 0);
                        studentReport.add(new Label("Student Name:"), 0, 1);
                        studentReport.add(new Label(full_name), 1, 1);
                        studentReport.add(new Label("Contact No:"), 0, 2);
                        studentReport.add(new Label(contact_no), 1, 2);
                        studentReport.add(new Label("First Term Marks:"), 0, 3);
                        studentReport.add(new Label(ftMarks), 1, 3);
                        studentReport.add(new Label("Mid Term Marks:"), 0, 4);
                        studentReport.add(new Label(mtMarks), 1, 4);
                        studentReport.add(new Label("Final Term Marks:"), 0, 5);
                        studentReport.add(new Label(fiMarks), 1, 5);
                        studentReport.add(new Label("Assignment Marks:"), 0, 6);
                        studentReport.add(new Label(aMarks), 1, 6);
                        studentReport.add(new Label("Total Marks:"), 0, 7);
                        studentReport.add(new Label(fMarks), 1, 7);
                        studentReport.add(new Label("Exam Grade:"), 0, 8);
                        studentReport.add(new Label(sGrade), 1, 8);
                        
                        mainContent.setCenter(studentReport);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error on Building Data");
                    System.out.println(ex);
                } finally {
                    try {
                        DBConnection.connect.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Semicolon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        });

        Semicolon.stage.setScene(new Scene(mainContent, 700, 400));
        menuButtons[0].fire();
    }
    
    public static ObservableList<Faculty> getFaculty() {
        ObservableList<Faculty> faculty = FXCollections.observableArrayList();
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "select * from user where user.user_type = 'faculty'";

            ResultSet resultSet = connect.createStatement().executeQuery(SQL);

            while (resultSet.next()) {
                String user_name = resultSet.getString("user.user_name");
                String full_name = resultSet.getString("user.full_name");
                String contact_no = resultSet.getString("user.contact_no");
                String mail = resultSet.getString("user.email");

                faculty.add(new Faculty(user_name, full_name, contact_no, mail));
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
        return faculty;
    }
}
