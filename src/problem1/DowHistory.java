
package problem1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Evan Phillips
 */
public class DowHistory {
    
    private ArrayList<DowRecord> records;
    private ObservableList<String> dates;
    private int size;
    
    public DowHistory() {
        records = new ArrayList();
        dates = FXCollections.observableArrayList();
        size = 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public ArrayList<DowRecord> getList() {
        return records;
    }
    
    public ObservableList<String> getDates() {
        for (int i = 0; i < records.size(); i++){
            dates.add(records.get(i).getDate().toString());
        }
        
        return dates;
    }
    
    public ArrayList<DowRecord> getSubList(String from, String to) {
        ArrayList<DowRecord> subList = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(from, formatter);
        LocalDate toDate = LocalDate.parse(to, formatter);
        
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getDate().isEqual(fromDate) || records.get(i).getDate().isAfter(fromDate)) {
               subList.add(records.get(i));
               if (records.get(i).getDate().isEqual(toDate)) {
                   break;
               }
            }           
        }
        
        return subList;
    }
    
    public void addRecord(DowRecord record) {
        records.add(record);
        size++;
    }
    
    public void removeRecord(DowRecord record) {
        records.remove(record);
        size--;
    }
    
    public void displayRecords() {
        for (DowRecord r : records) {
            System.out.println(r.toString());
        }
    }
}
