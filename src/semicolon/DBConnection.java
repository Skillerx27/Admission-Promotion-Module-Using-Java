/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semicolon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author monirul
 */
public class DBConnection {
    static Connection connect;
    
    static Connection connect()
    {
        try
        {  
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/MarksSubmissionandReportModule","root","");  
        }
        catch(SQLException e)
        { 
            System.out.println(e);
        }
        return connect;
    }
}
