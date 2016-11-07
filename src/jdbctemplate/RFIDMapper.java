package jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Albin
 */
public class RFIDMapper implements RowMapper<RFID> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public RFID mapRow(ResultSet rs, int rowNum) throws SQLException {
      RFID rfid = new RFID(rs.getString("RFID"));
      return rfid;
   }
}
