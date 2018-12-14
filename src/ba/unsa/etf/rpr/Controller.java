package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private TextField input;

    @FXML
    private ListView<String> output;

    @FXML
    private Button traziBtn;

    @FXML
    private Button prekiniBtn;

    private static boolean prekini = false;
    private Thread thread;
    private ObservableList<String> pronadjeno = FXCollections.observableArrayList();

    public void pretraga(File dir) {
        try {
            if (prekini)
                return;
            for (File f : dir.listFiles()) {
                if (f.isDirectory())
                    pretraga(f);
                else if (f.isFile() && f.getName().contains(input.getText())) {
                    pronadjeno.add(f.toString());
                    output.setItems(pronadjeno);
                }
            }
        }
        catch(Exception e) {
            //do nothing
        }
    }

    public void traziBtn() {
        prekini = false;
        File f = new File(System.getProperty("user.home"));
        try {
            thread = new Thread( () -> {
                prekiniBtn.setDisable(false);
                traziBtn.setDisable(true);
                pretraga(f);
                prekiniBtn.setDisable(true);
                traziBtn.setDisable(false);
            });
        }
        catch (Exception e) {
            //do nothing
        }

        thread.start();
    }

    public void prekiniBtn() {
        prekini = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prekiniBtn.setDisable(true);
        traziBtn.setDisable(false);
    }
}
