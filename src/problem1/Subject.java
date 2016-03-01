
package problem1;

import java.util.ArrayList;

/**
 *
 * @author Evan Phillips
 */
public interface Subject {
    
    public void regiester(Observer o);
    public void unregiester(Observer o);
    public void notifyObserver(ArrayList<DowRecord> records);
}
