
package jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Albin
 */
public class EmployeeMapper implements RowMapper<Employee> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
      String RFID = rs.getString("RFID");
      String forename = rs.getString("forename");
      String surname = rs.getString("surname");
      String employeeId = rs.getString("employeeId");
      return new Employee(RFID, forename, surname, employeeId);
   }
}
