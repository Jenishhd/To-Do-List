package todolist;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

public class Controller {



    private List<TodoItem> todoItems;

    @FXML
    private Label deadlineLabel;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private void initialize(){
        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card for John",
                LocalDate.of(2020, Month.FEBRUARY,6));
        TodoItem item2 = new TodoItem("Doctor's Appointment","See Dr.Smith at 123 Main Street",
                LocalDate.of(2020,Month.JANUARY,6));
        TodoItem item3 = new TodoItem("Pick up dry cleaning", "The clothes will be ready soon",
                LocalDate.of(2020,Month.JANUARY,11));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null){
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

    }

    @FXML
    public void handleClickListView(){
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
        System.out.println("Hello");

//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//
//
//        itemDetailsTextArea.setText(sb.toString());

//        System.out.println("The selected item is " + item);

    }
}
