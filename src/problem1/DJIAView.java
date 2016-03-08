package problem1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author Evan Phillips
 */
public class DJIAView {

    static final String pattern = "yyyy-MM-dd";
    static final String TITLE = "Dow Jones Industrial Average";
    static final String FILE = "lib/DJIA.csv";
    static final int W = 1000;
    static final int H = 750;
    static final int SP_S = 5;
    static final int SP_M = 15;
    static final int SP_L = 25;

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
    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;
    private StringConverter converter;

    public DJIAView(Stage stage) {
        this.stage = stage;
        reader = new TextFileReader(FILE);
        records = reader.getRecords();
        formatDates();
        datePickerStart = new DatePicker();
        datePickerEnd = new DatePicker();
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
        getPricesBtn = new Button("Get Prices");
        datePickerStart.setConverter(converter);
        datePickerEnd.setConverter(converter);
        HBox dateRange = new HBox(SP_M);
        dateRange.setPadding(new Insets(SP_S, SP_S, SP_S, SP_S));
        dateRange.setAlignment(Pos.CENTER);
        dateRange.getChildren().addAll(startDateLbl, datePickerStart,
                endDateLbl, datePickerEnd, getPricesBtn);

        menuBar.getMenus().addAll(fileMenu, editMenu);
        topPane.getChildren().addAll(menuBar, dateRange);

        return topPane;
    }

    private VBox getCenterPane() {
        centerPane = new VBox();
        graph = new DowLineChart();
        list = new DowListView();
        records.register(graph);
        records.register(list);
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
        stage.getIcons().add(new Image("stocks.png"));
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
        addBtn.setOnAction(e -> {
            addNewRecord(dateTf.getText(),Double.parseDouble(valueTf.getText()));
        });
    }

    private void getPrices() {
        String startDate = datePickerStart.getValue().toString();
        String endDate = datePickerEnd.getValue().toString();
        records.getSubList(startDate, endDate);
        System.out.println(records.getSubList(startDate, endDate));
    }
    
    private void addNewRecord(String date, double value) {
        records.addRecord(new DowRecord(LocalDate.parse(date),value));
    }

    private void formatDates() {
        converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter
                    = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
    }

    private void close() {
        stage.close();
    }
}
