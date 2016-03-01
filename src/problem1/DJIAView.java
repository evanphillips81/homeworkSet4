
package problem1;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Evan Phillips
 */
public class DJIAView {
    
    static final String TITLE ="Dow Jones Industrial Average";
    static final String FILE ="lib/DJIA.csv";
    static final int W =1000;
    static final int H =750;
    static final int SP_S =5;
    static final int SP_M =15;
    static final int SP_L =25;
    
    private Stage stage;
    private BorderPane layout;
    private VBox topPane;
    private VBox centerPane;
    private VBox bottomPane;
    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu editMenu;
    private Button okBtn; 
    private Button getPricesBtn;
    private Button addBtn;
    private Label startDateLbl;
    private Label endDateLbl;
    private Label dateLbl;
    private Label valueLbl;
    private TextField dateTf;
    private TextField valueTf;
    private ChoiceBox<String> startDateChoice;
    private ChoiceBox<String> endDateChoice;
    private ObservableList<String> dates;
    private DowLineChart graph;
    private DowListView list;
    private DowHistory records;
    private TextFileReader reader;
    
    public DJIAView (Stage stage) {
        this.stage = stage;
        reader = new TextFileReader(FILE);
        records = reader.getRecords();
        dates = FXCollections.observableArrayList(records.getDates());
        dateLbl = new Label("Date: ");
        valueLbl = new Label("Value: ");
        dateTf = new TextField();
        valueTf = new TextField();
        showScene();
        actions();
    }
    
    private VBox getTopPane() {
        topPane = new VBox();
        menuBar = new MenuBar();
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");
        
        startDateLbl = new Label("Start date: ");
        endDateLbl = new Label("End date: ");
        startDateChoice = 
                new ChoiceBox(FXCollections.observableArrayList(dates));
        endDateChoice = 
                new ChoiceBox(FXCollections.observableArrayList(dates));
        getPricesBtn = new Button("Get Prices");
                
        HBox dateRange = new HBox(SP_M);
        dateRange.setPadding(new Insets(SP_S, SP_S, SP_S, SP_S));
        dateRange.setAlignment(Pos.CENTER);
        dateRange.getChildren().addAll(startDateLbl, startDateChoice, 
                                       endDateLbl, endDateChoice, getPricesBtn);
                
        menuBar.getMenus().addAll(fileMenu, editMenu);
        topPane.getChildren().addAll(menuBar, dateRange);
        
        return topPane;
    }
    
    private VBox getCenterPane() {
        centerPane = new VBox();
        graph = new DowLineChart();
        list = new DowListView();
        centerPane.setPadding(new Insets(SP_S, SP_S, SP_S, SP_S));
        centerPane.getChildren().addAll(graph.getLineChart(), list.getListView());
        
        return centerPane;
    }
    
    private VBox getBottomPane() {
        HBox manualEntry = new HBox(SP_S);
        addBtn = new Button("Add Record");
        manualEntry.setAlignment(Pos.CENTER);
        manualEntry.setPadding(new Insets(SP_S, SP_S, SP_S, SP_S));
        manualEntry.getChildren().addAll(dateLbl, dateTf, valueLbl, valueTf, addBtn);
        
        bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(SP_S, SP_S, SP_S, SP_S));
        okBtn = new Button("OK");
        bottomPane.getChildren().addAll(manualEntry, okBtn);
        
        return bottomPane;
    }
    
    private BorderPane getLayout() {
        layout = new BorderPane();
        layout.setTop(getTopPane());
        layout.setCenter(getCenterPane());
        layout.setBottom(getBottomPane());
        
        return layout;  
    }
    
    private void showScene() {
        stage.setTitle(TITLE);
        Scene scene = new Scene(getLayout(), W, H);
        //scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();
    }
    
    private void actions() {
        okBtn.setOnAction(e -> {
            close();
        });
        getPricesBtn.setOnAction(e -> {
            getPrices();
        });
    }
    
    private void getPrices() {
        graph.update(records.getSubList(startDateChoice.getValue(),endDateChoice.getValue()));
        list.update(records.getSubList(startDateChoice.getValue(),endDateChoice.getValue()));
    }
    
    private void close() {
        stage.close();
    }
}
