package jdbctemplate;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

/**
 *
 * @author Albin
 */
public interface PSDBInterface {

    /**
     * This is the method to be used to initialize database resources ie.
     * connection.
     *
     * @param ds The DataSource object that defines the connection.
     */
    public void setDataSource(DataSource ds);

    /**
     * Creates a record in the "Employee" table based on the information
     * stored within the argument.
     * 
     * @param emp The instance of Employee which to store in the table.
     */
    public void createEmployee(Employee emp) 
            throws DataAccessException, CannotGetJdbcConnectionException;

    /**
     * @param RFID: The RFID corresponding to a Employee in the "Employees"
     * table.
     * @return The employee corresponding to the specified RFID code. 
     */
    public Employee getEmployee(String RFID) 
            throws IncorrectResultSizeDataAccessException, 
            DataAccessException, CannotGetJdbcConnectionException;

    /**
     * @return A list of all the Employees in the database.
     */
    public List<Employee> listEmployees() 
            throws DataAccessException, CannotGetJdbcConnectionException;

    /**
     * Deletes the employee coupled to the specified RFID code.
     * @param rfid: The RFID corresponding to the employee to be deleted.
     */
    public void deleteEmployee(RFID rfid)
            throws DataAccessException, CannotGetJdbcConnectionException;
    
    /**
     * Updates the employee coupled with the specified RFID.
     * note that it does not update the RFID.
     * 
     * @param rfid 
     * @param forename
     * @param surname
     * @param employeeId 
     * @return  true if a record with the specified RFID was encountered in the 
     * database else, false.
     */
    public boolean updateEmployee(RFID rfid, String forename, 
            String surname, String employeeId)
            throws DataAccessException, CannotGetJdbcConnectionException;
            
    /**
     * Adds a timestamp to the "TimeStamp" table in the database. 
     * 
     * @param stamp A object containing the current time and 
     * associated RFID number.
     * @throws jdbctemplate.SignedInException
     * @throws jdbctemplate.SignedOutException
     */
    public void addRFIDTimeStamp(RFIDTimestamp stamp) 
            throws SignedInException, SignedOutException, CannotGetJdbcConnectionException,
            IncorrectResultSizeDataAccessException, DataAccessException;
    
    
    
    /**
     * @param rfid 
     * @return A list containing all the timestamps corresponding 
     * to the specified RFID.
     */
    public List<RFIDTimestamp> getTimestamps(RFID rfid)
            throws DataAccessException, CannotGetJdbcConnectionException;
    
    /**
     * @return A list containing all the timestamps
     */
    public List<RFIDTimestamp> getAllTimestamps()
            throws DataAccessException, CannotGetJdbcConnectionException;
    
    /**
     * Returns a list containing all the signed in rfid's.
     * @return 
     */
    public List<RFID> getAllSignedIn()
            throws DataAccessException, CannotGetJdbcConnectionException;
    
}
