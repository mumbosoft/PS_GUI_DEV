package jdbctemplate;

import java.util.Objects;

/**
 * Simple wrapper class for RFID tag numbers.
 * 
 * @author Albin
 */
public class RFID {
    private final String RFID;
    
    /**
     * Constructor.
     * @param RFID 
     */
    public RFID(String RFID) {
        this.RFID = RFID;
    }

    /**
     * @return A String representing the RFID number.
     */
    @Override
    public String toString() {
        return this.RFID;
    }

    /**
     * Method to test for equality against another object.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RFID other = (RFID) obj;
        if (!Objects.equals(this.RFID, other.RFID)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
