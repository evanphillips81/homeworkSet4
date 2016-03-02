
package problem1;

import java.util.ArrayList;

/**
 *
 * @author Evan Phillips
 */
public interface Subject {
    
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObserver(ArrayList<DowRecord> records);
}
