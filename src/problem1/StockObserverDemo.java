
package problem1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class StockObserverDemo extends Application {
    
    public static void main (String[] args) throws ParseException {
              
        launch();
        
        //String oldDate = new SimpleDateFormat("yyyy-dd-MM").format();
        
        //Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(oldDate);
        
        //System.out.println(newDate.toString());
               
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        DJIAView view = new DJIAView(stage);
        
        
    }
    
    
}
