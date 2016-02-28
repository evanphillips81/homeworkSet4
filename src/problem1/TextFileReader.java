
package problem1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Evan Phillips
 */
public class TextFileReader {
    
    String file;
    DowHistory records;
    
    public TextFileReader(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public DowHistory getRecords() {

        try {
            String l;
            LocalDate date;
            double value;

            records = new DowHistory();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while ((l = br.readLine()) != null) {
                String data[] = l.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(data[0], formatter);
                if (!data[1].contentEquals(".")) {
                    value = Double.parseDouble(data[1]);
                    records.addRecord(new DowRecord(date, value));
                }              
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
