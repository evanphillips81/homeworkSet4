
package problem1;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Evan Phillips
 */
public class DowLineChart {
    
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<String,Number> lineChart;
    private XYChart.Series series;
    
    public DowLineChart() {
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Dollar Value");
        lineChart = new LineChart(xAxis,yAxis);    
        lineChart.setTitle("Dow Jones Industrial Average");
        series = new XYChart.Series();
        series.setName("DJIA");
        lineChart.getData().add(series); 
    }
    
    public LineChart<String,Number> getLineChart() {
        return lineChart;
    }
    
    public void setChartValues(ArrayList<DowRecord> list) {
        for (int i = 0; i < list.size(); i++) {
            series.getData().add(new XYChart.Data(list.get(i).getDate().toString(),
                    list.get(i).getValue()));
        }
    }
    
}
