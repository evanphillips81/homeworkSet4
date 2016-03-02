
package problem1;

import java.text.ParseException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class StockObserverDemo extends Application {
    
    public static void main (String[] args) throws ParseException {
              
        launch();    
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        DJIAView view = new DJIAView(stage);
        
    }
      
}
