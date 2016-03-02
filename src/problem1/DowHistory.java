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
public class DowHistory implements Subject {

    private ArrayList<DowRecord> records;
    private ArrayList<String> dates;
    private ArrayList<Observer> observers;
    private int size;

    public DowHistory() {
        observers = new ArrayList();
        records = new ArrayList();
        dates = new ArrayList();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<DowRecord> getList() {
        return records;
    }

    public ArrayList<String> getDates() {
        for (int i = 0; i < records.size(); i++) {
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
            if (records.get(i).getDate().isAfter(fromDate) && records.get(i).getDate().isBefore(toDate)) {
                subList.add(records.get(i));
            }
        }
        notifyObserver(subList);
        return subList;
    }

    public void addRecord(DowRecord record) {
        records.add(record);
        size++;
        notifyObserver(records);
    }

    public void removeRecord(DowRecord record) {
        records.remove(record);
        size--;
        notifyObserver(records);
    }

    public void displayRecords() {
        for (DowRecord r : records) {
            System.out.println(r.toString());
        }
    }

    @Override
    public void notifyObserver(ArrayList<DowRecord> records) {
        for (Observer o : observers) {
            o.update(records);
        }
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);

    }

    @Override
    public void unregister(Observer existingObserver) {
        int index = observers.indexOf(existingObserver);
		observers.remove(index);
		System.out.println("Observer " + (index + 1) + " deleted.");
    }
}
