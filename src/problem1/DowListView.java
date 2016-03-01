
package problem1;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Evan Phillips
 */
public class DowListView implements Observer {
    
    private ListView<String> list;
    
    public DowListView() {
        list = new ListView<>();
    }
    
    public void setListView(ArrayList<DowRecord> records) {
        for (int i = 0; i < records.size(); i++) {
            String date = records.get(i).getDate().toString();
            String value = ("$"+records.get(i).getValue());
            list.getItems().add(date + ": " + value);
        }
        
    }
    
    public ListView getListView() {
        return list;
    }

    @Override
    public void update(ArrayList<DowRecord> records) {
       setListView(records);
    }
}
