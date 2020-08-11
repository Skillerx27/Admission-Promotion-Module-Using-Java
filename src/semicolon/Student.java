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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static semicolon.DBConnection.connect;

/**
 *
 * @author Monirul Islam
 */
public class Student {

    String userName = new String();
    String fullName = new String();
    String contactNo = new String();
    String email = new String();
    String program = new String();
    int firstTermMarks = 0;
    int midTermMarks = 0;
    int finalTermMarks = 0;
    int assignmentMarks = 0;
    int finalMarks = 0;
    char grade = ' ';

    public Student() {

    }

    public Student(String userName, String fullName, String contactNo, String email, String program, String firstTermMarks, String midTermMarks,String finalTermMarks, String assignmentMarks, String finalMarks, String examGrade) {
        this.userName = userName;
        this.fullName = fullName;
        this.contactNo = contactNo;
        this.email = email;
        this.program = program;
        this.firstTermMarks = Integer.valueOf(firstTermMarks);
        this.midTermMarks = Integer.valueOf(midTermMarks);
        this.finalTermMarks = Integer.valueOf(finalTermMarks);
        this.assignmentMarks = Integer.valueOf(assignmentMarks);
        this.finalMarks = Integer.valueOf(finalMarks);
        this.grade = examGrade.charAt(0);
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
    
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setProgram(String userName, String program) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update student set student.program = '" + program + "' where student.user_name = '" + userName + "'";

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
    
    public String getFirstTermMarks() {
        return String.valueOf(firstTermMarks);
    }

    public void setFirstTermMarks(String firstTermMarks) {
        this.firstTermMarks = Integer.getInteger(firstTermMarks);
    }

    public void setFirstTermMarks(String userName, String firstTermMarks) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update student set student.ftm = '" + firstTermMarks + "' where student.user_name = '" + userName + "'";

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
    
    public String getMidTermMarks() {
        return String.valueOf(midTermMarks);
    }

    public void setMidTermMarks(String midTermMarks) {
        this.midTermMarks = Integer.getInteger(midTermMarks);
    }

    public void setMidTermMarks(String userName, String midTermMarks) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update student set student.mtm = '" + midTermMarks + "' where student.user_name = '" + userName + "'";

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
    
    public String getFinalTermMarks() {
        return String.valueOf(finalTermMarks);
    }

    public void setFinalTermMarks(String finalTermMarks) {
        this.finalTermMarks = Integer.getInteger(finalTermMarks);
    }

    public void setFinalTermMarks(String userName, String finalTermMarks) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update student set student.fm = '" + finalTermMarks + "' where student.user_name = '" + userName + "'";

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
    
    public String getAssignmentMarks() {
        return String.valueOf(assignmentMarks);
    }

    public void setAssignmentMarks(String assignmentMarks) {
        this.assignmentMarks = Integer.getInteger(assignmentMarks);
    }

    public void setAssignmentMarks(String userName, String assignmentMarks) {
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "update student set student.am = '" + assignmentMarks + "' where student.user_name = '" + userName + "'";

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

    public String getFinalMarks() {
        return String.valueOf(finalMarks);
    }

    public void setFinalMarks(int examMarks) {
        this.finalMarks = examMarks;
    }

    public String getGrade() {
        return String.valueOf(grade);
    }

    public void setGrade(char examGrade) {
        this.grade = examGrade;
    }

    protected static void studentDashboard(String username, String name, String email) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5, 10, 18, 10));

        Label userinfo = new Label("  " + name + "\n  " + email + "\n  " + "(student)");
        userinfo.setMinHeight(60);
        userinfo.setMaxHeight(60);
        userinfo.setFocusTraversable(true);

        JFXButton[] menuButtons = {
            new JFXButton("☰ View Report"),
            new JFXButton("☰ Put Any Complain")
        };
        for (JFXButton jfxButton : menuButtons) {
            jfxButton.setAlignment(Pos.CENTER_LEFT);
            jfxButton.setMinWidth(180);
            jfxButton.setMaxWidth(180);
            jfxButton.setMinHeight(35);
            jfxButton.setMaxHeight(35);
        }

        menuButtons[0].setOnAction((ActionEvent event) -> {
            try {
                Connection connect;
                connect = DBConnection.connect();
                String SQL = "select user.full_name, user.contact_no, student.program, student.fm, student.total_marks, student.grade from user inner join student on user.user_name = student.user_name where user.user_name = '" + username + "'";

                ResultSet resultSet = connect.createStatement().executeQuery(SQL);

                if (resultSet.next()) {
                    String sname = resultSet.getString("user.full_name");
                    String sprogram = resultSet.getString("student.program");
                    String sContactNo = resultSet.getString("user.contact_no");
                    int result = resultSet.getInt("student.total_marks");
                    int finalMarks = resultSet.getInt("student.fm");
                    char examGrade = resultSet.getString("student.grade").charAt(0);

                    if (finalMarks != 0) {

                        GridPane mainContent = new GridPane();
                        mainContent.setAlignment(Pos.CENTER);
                        mainContent.setHgap(15);
                        mainContent.setVgap(10);

                        mainContent.add(new Label("Name: "), 0, 0);
                        mainContent.add(new Label("Program: "), 0, 1);
                        mainContent.add(new Label("Contact No: "), 0, 2);
                        mainContent.add(new Label("Grade: "), 0, 3);

                        mainContent.add(new Label(sname), 1, 0);
                        mainContent.add(new Label(sprogram), 1, 1);
                        mainContent.add(new Label(sContactNo), 1, 2);
                        mainContent.add(new Label(examGrade + " (" + result + ")"), 1, 3);

                        root.setCenter(mainContent);
                    } else {
                        GridPane mainContent = new GridPane();
                        mainContent.setAlignment(Pos.CENTER);
                        mainContent.add(new Label("Result Not Published Yet"), 0, 0);

                        root.setCenter(mainContent);
                    }
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

        menuButtons[1].setOnAction((ActionEvent event) -> {
            JFXTextField complainBox = new JFXTextField();
            complainBox.setPromptText("write your complain");
            complainBox.setMinWidth(300);
            complainBox.setMaxWidth(300);
            complainBox.setMinHeight(80);
            complainBox.setMaxHeight(80);
            JFXButton putComplain = new JFXButton("Submit Complain");

            GridPane newStudentRegister = new GridPane();
            newStudentRegister.setAlignment(Pos.CENTER);
            newStudentRegister.setVgap(15);
            newStudentRegister.add(complainBox, 0, 0);
            newStudentRegister.add(putComplain, 0, 1);

            putComplain.setOnAction((ActionEvent innerevent) -> {

                try {
                    Connection connect;
                    connect = DBConnection.connect();
                    String SQL = "insert into complain (user_name, complain) values ('" + username + "', '" + complainBox.getText() + "')";
                    connect.createStatement().executeUpdate(SQL);

                    System.out.println("complain submitted");
                    complainBox.setText(null);

                    menuButtons[0].fire();
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
            root.setCenter(newStudentRegister);
        });

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

        root.setLeft(menu);
        root.setBottom(logout);

        Semicolon.stage.setScene(new Scene(root, 700, 400));
        menuButtons[0].fire();
    }

    public static ObservableList<Student> getStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            Connection connect;
            connect = DBConnection.connect();
            String SQL = "select * from user inner join student on user.user_name = student.user_name";

            ResultSet resultSet = connect.createStatement().executeQuery(SQL);

            while (resultSet.next()) {
                String user_name = resultSet.getString("user.user_name");
                String full_name = resultSet.getString("user.full_name");
                String contact_no = resultSet.getString("user.contact_no");
                String mail = resultSet.getString("user.email");
                String sprogram = resultSet.getString("student.program");
                String ftMarks = resultSet.getString("student.ftm");
                String mtMarks = resultSet.getString("student.mtm");
                String fiMarks = resultSet.getString("student.fm");
                String aMarks = resultSet.getString("student.am");
                String fMarks = resultSet.getString("student.total_marks");
                String sGrade = resultSet.getString("student.grade");

                students.add(new Student(user_name, full_name, contact_no, mail, sprogram, ftMarks, mtMarks, fiMarks, aMarks, fMarks, sGrade));
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
        return students;
    }
}
