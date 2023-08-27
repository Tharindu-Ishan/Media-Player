package lk.ijse.dep11;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;

public class MainFormController {

    public AnchorPane root;
    public ImageView imgPause;
    @FXML
    private ImageView imgNext;

    @FXML
    private ImageView imgOpen;

    @FXML
    private ImageView imgPlay;

    @FXML
    private ImageView imgPrev;

    @FXML
    private ImageView imgRepeat;

    @FXML
    private ImageView imgStop;

    @FXML
    private ImageView imgVolume;

    @FXML
    private Slider sldTiming;

    @FXML
    private Slider sldVolume;

    @FXML
    private TextField txtOpen;
    MediaPlayer mediaPlayer;
    
    public void initialize(){

    }

    public void imgOpenOnMouseClicked(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3 Files","*mp3"));
        File audioFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if(audioFile!=null){
            txtOpen.setText(audioFile.getAbsolutePath());
            Media media = new Media(audioFile.toURI().toString());

            mediaPlayer = new MediaPlayer(media);
        }
        else {
            txtOpen.clear();
        }

    }


}
