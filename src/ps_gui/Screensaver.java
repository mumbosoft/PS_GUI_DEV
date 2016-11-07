package ps_gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A method to control the Raspberry Pi 7" touch screen back light. Make shure
 * you change the permissions to the file :
 * /sys/class/backlight/rpi_backlight/bl_power
 *
 * you can do that by writing: $ sudo chmod a+rwx
 * /sys/class/backlight/rpi_backlight/bl_power which will grant everyone the
 * permission to read/write/execute the file.
 *
 * if that file doesn't exist you should probably run: $ sudo apt-get update $
 * sudo apt-get dist-upgrade $ sudo reboot
 *
 * If you write a 1 to the file, the screen will turn off, if you write 0, the
 * screen will turn on.
 *
 * To manually toggle the back light you have to write in the gnome terminal: $
 * echo 1 > /sys/class/backlight/rpi_backlight/bl_power or $ echo 0 >
 * /sys/class/backlight/rpi_backlight/bl_power
 *
 * @author albin
 */
public class Screensaver {

    private PrintWriter writer;
    private File bl_power;
    private boolean state;

    /**
     * Constructor for this object. Requires the file
     * /sys/class/backlight/rpi_backlight/bl_power to exist in the raspbian
     * filesystem. else this constructor will throw FileNotFoundException.
     *
     * @throws FileNotFoundException
     */
    public Screensaver() {
        try {
            bl_power = new File("/sys/class/backlight/rpi_backlight/bl_power");
            writer = new PrintWriter(bl_power);
        } catch (FileNotFoundException ex) {
            System.err.println("Screensaver error: " + ex.getLocalizedMessage());
            resolveFile();
        }
    }

    /**
     * Turn off the backlight,
     */
    public void off() {
        writer.print("0");
        writer.flush();
        state = false;
    }

    /**
     * Turn on the backlight.
     */
    public void on() {
        writer.print("1");
        writer.flush();
        state = true;
    }

    /**
     * Return the current state of the backlight.
     *
     * @return the boolean state of the backlight.
     */
    public boolean getState() {
        return state;
    }

    /**
     * Closes the file stream to /sys/class/backlight/rpi_backlight/bl_power.
     */
    public void close() {
        writer.close();
    }
    
    /**
     * Runs chmod on the file to try to edit permissions.
     */
    private void resolveFile() {
        //TODO: run chmod and edit permissions. $sudo chmod a+rwx /sys/class/backlight/rpi_backlight/bl_power
    }
}
