package jdbctemplate;


import java.sql.SQLException;

/**
 *
 * @author Albin
 */
public class JDBCTemplateExample {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        //Connect to database
        PSJDBCTemplate empTemp = new PSJDBCTemplate();
        empTemp.updateEmployee(new RFID("00002"), "PUSSYLICKER", "MADAFACKHA", "69696969");

    }
}
