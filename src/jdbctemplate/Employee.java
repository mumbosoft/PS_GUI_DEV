
package jdbctemplate;

import java.util.Comparator;
import java.util.Objects;

/**
 * An object representing an item in the passagesystemdb
 * 
 * @author Albin
 */
public class Employee {
    
    private final String RFID, forename, surname, employeeId;

    /**
     * Constructor. 
     * @param RFID The RFID number corresponding to this employee.
     * @param forename 
     * @param surname
     * @param employeeId 
     */
    public Employee(String RFID, String forename, String surname, String employeeId) {
        this.RFID = RFID;
        this.forename = forename;
        this.surname = surname;
        this.employeeId = employeeId;
    }

    /**
     * Returns the RFID code of this employee.
     * @return RFID
     */
    public String getRFID() {
        return RFID;
    }

    /**
     * Returns the forename of this employee.
     * @return forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Returns the surname of this employee. 
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the employee id of this employee.
     * @return employeeId.
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @return A String representation of this object. 
     */
    @Override
    public String toString() {
        return String.format("%10s %15s %15s %6s",
                this.RFID, this.forename, this.surname, this.employeeId);
    }
    
    /**
     * Compare the equality of this object to another.
     * 
     * @param other: The object to compare to.
     * @return true if this object equals other else false.
     */
    @Override
    public boolean equals(Object other) {       
        if(!(other instanceof Employee)) {
            return false;
        }
        
        Employee tmp = (Employee) other;
        
        if(!tmp.RFID.equalsIgnoreCase(this.RFID)) {
            return false;
        } else if(!tmp.forename.equalsIgnoreCase(this.forename)) {
            return false;
        } else if(!tmp.surname.equalsIgnoreCase(this.surname)) {
            return false;
        } else if(!tmp.employeeId.equalsIgnoreCase(this.employeeId)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return A hashCode corresponding to this object. 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.RFID);
        hash = 59 * hash + Objects.hashCode(this.forename);
        hash = 59 * hash + Objects.hashCode(this.surname);
        hash = 59 * hash + Objects.hashCode(this.employeeId);
        return hash;
    }
    
    /**
     * @return RFIDComparator.
     */
    public Comparator<Employee> getRFIDComp() {
        return new RFIDComparator();
    }
    
    /**
     * @return ForenameComparator.
     */
    public Comparator<Employee> getFornameComp() {
        return new ForenameComparator();
    }
    
    /**
     * @return SurnameComparator.
     */
    public Comparator<Employee> getSurnameComp() {
        return new SurnameComparator();
    }
    
    /**
     * @return EmployeeIdComparator.
     */
    public Comparator<Employee> getEmployeeIdComp() {
        return new EmployeeIdComparator();
    }
    
    /**
     * RFIDComparator class.  
     */
    private class RFIDComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.RFID.compareTo(e2.RFID);
        }
    }
    
    /**
     * ForenameComparator class.
     */
    private class ForenameComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.forename.compareTo(e2.forename);
        }
    }
    
    /**
     * SurnameComparator class.
     */
    private class SurnameComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.surname.compareTo(e2.surname);
        }
    }
    
    /**
     * EmployeeIdComparator class
     */
    private class EmployeeIdComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.employeeId.compareTo(e2.employeeId);
        }
    }
    
}
