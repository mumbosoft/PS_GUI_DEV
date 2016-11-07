
package ps_gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author albhja15
 */
public class Startup extends Application {
    
    private static String[] arg;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PS_GUIFxml.fxml"));
        
        Scene scene = new Scene(root);
        scene.setCursor(Cursor.NONE);
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        arg = args;
        launch(args);
    }
    
}
