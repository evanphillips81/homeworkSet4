
package problem1;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
    private HBox bottomPane;
    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu editMenu;
    private Button okBtn; 
    private Button getPricesBtn;
    private Label startDateLbl;
    private Label endDateLbl;
    private ChoiceBox startDateChoice;
    private ChoiceBox endDateChoice;
    private DowLineChart graph;
    
    public DJIAView (Stage stage) {
        this.stage = stage;
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
                new ChoiceBox(FXCollections.observableArrayList("",""));
        endDateChoice = 
                new ChoiceBox(FXCollections.observableArrayList("",""));
        getPricesBtn = new Button("Get Prices");
        HBox dateRange = new HBox(SP_M);
        dateRange.setPadding(new Insets(SP_S, SP_S, SP_S, SP_S));
        dateRange.setAlignment(Pos.CENTER);
        dateRange.getChildren().addAll(startDateLbl, startDateChoice, 
                                       endDateLbl, endDateChoice, getPricesBtn);
                
        menuBar.getMenus().addAll(fileMenu, editMenu);
        topPane.getChildren().addAll(menuBar,dateRange);
        
        return topPane;
    }
    
    private VBox getCenterPane() {
        
        TextFileReader reader = new TextFileReader(FILE);
        DowHistory history = reader.getRecords();
        
        centerPane = new VBox();
        graph = new DowLineChart();
        graph.setChartValues(history.getSubList("2016-02-16","2016-02-26"));
        centerPane.getChildren().add(graph.getLineChart());
        
        return centerPane;
    }
    
    private HBox getBottomPane() {
        bottomPane = new HBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(SP_S, SP_S, SP_S, SP_S));
        okBtn = new Button("OK");
        bottomPane.getChildren().addAll(okBtn);
        
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
        stage.setScene(new Scene(getLayout(),W,H));
        stage.show();
    }
    
    private void actions() {
        okBtn.setOnAction(e -> {
            close();
        });
    }
    
    private void close() {
        stage.close();
    }
    
    
}
