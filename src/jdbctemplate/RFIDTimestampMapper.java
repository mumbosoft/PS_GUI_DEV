
package jdbctemplate;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Albin
 */
public class RFIDTimestampMapper implements RowMapper<RFIDTimestamp> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public RFIDTimestamp mapRow(ResultSet rs, int rowNum) throws SQLException {
      RFID rfid = new RFID(rs.getString("RFID"));
      Date time = rs.getTimestamp("time");
      String inOut = rs.getString("inout");
      
      return new RFIDTimestamp(rfid, time, inOut);
   }
}
