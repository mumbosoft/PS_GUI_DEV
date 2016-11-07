package jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Albin
 */
public class StringMapper implements RowMapper<String> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
      String string = rs.getString("RFID");
      return string;
   }
}
