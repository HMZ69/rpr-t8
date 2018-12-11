package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FilenameFilter;


public class Controller {

    private ObservableList<String> pronadjeno = FXCollections.observableArrayList();

    @FXML
    private TextField input;

    @FXML
    private ListView<String> output;

    public void pretraga(File dir) {
        for (File f : dir.listFiles()) {
            if (f.isDirectory())
                pretraga(f);
            else if (f.isFile() && f.getName().contains(input.getText())) {
                pronadjeno.add(f.toString());
                output.setItems(pronadjeno);
            }
        }
    }

    public void traziBtn() {
        File f = new File(System.getProperty("user.home"));
        pretraga(f);
    }
}
