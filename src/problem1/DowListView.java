
package problem1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Evan Phillips
 */
public class DowListView {
    
    private ListView<String> list;
    
    public DowListView(DowHistory records) {
        
        list = new ListView<>();
        for (int i = 0; i < records.getSize(); i++) {
            String date = records.getList().get(i).getDate().toString();
            String value = ("$"+records.getList().get(i).getValue());
            list.getItems().add(date + ": " + value);
        }
    }
    
    public ListView getListView() {
        return list;
    }
}
