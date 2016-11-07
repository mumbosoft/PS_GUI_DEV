package jdbctemplate;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * When the constructor is called an instance of this class is created
 * containing the RFID, date and time of the moment of instantiation.
 * User code have the option to update old timestamps using the instance method:
 * regenerate().
 *
 * @author Albin
 */
public class RFIDTimestamp {
    private final RFID rfid;
    private Date time;
    private String inOut;
    
    /**
     * Constructor.
     * Creates a timestamp of the time when this object was instantiated.
     * 
     * @param rfid 
     * @param inOut 
     */
    public RFIDTimestamp(RFID rfid, String inOut) {
        this.rfid = rfid;
        this.inOut = inOut;
        time = new Date();
    }
    
    /**
     * Constructor..
     * 
     * @param rfid
     * @param time 
     * @param inOut 
     */
    public RFIDTimestamp(RFID rfid, Date time, String inOut) {
        this.rfid = rfid;
        this.time = time;
        this.inOut = inOut;
    }
    
    /**
     * @return The timestamp that was created 
     * when this object was instantiated.
     */
    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(this.time);
    }
    
    /**
     * @return The RFID tag contained within this object.
     */
    public RFID getRFID() {
        return this.rfid;
    }
    
    /**
     *
     * @return
     */
    public String getInOut() {
        return this.inOut;
    }
    
    /**
     * Reloads the timestamp.
     */
    public void reload() {
        this.time = new Date();
    }
    
    /**
     * @return a string representing this object.
     */
    @Override
    public String toString() {
        return this.rfid + " " + this.getTime() + " " + this.inOut;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        RFIDTimestamp t1 = new RFIDTimestamp(new RFID("123476247624"), "IN");
        RFIDTimestamp t2 = new RFIDTimestamp(new RFID("123476247624"), "OUT");
        
        System.out.println(t1);
        System.out.println(t2);
    }
    
}
