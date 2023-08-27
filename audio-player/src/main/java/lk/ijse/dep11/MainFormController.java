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
import java.util.List;

public class MainFormController {

    public AnchorPane root;
    public ImageView imgPause;
    public ImageView imgMute;
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
//        File audioFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        List<File> files = fileChooser.showOpenMultipleDialog(root.getScene().getWindow());
        File audioFile = files.get(0);

        if(audioFile!=null){
            txtOpen.setText(audioFile.getAbsolutePath());
            Media media = new Media(audioFile.toURI().toString());

            mediaPlayer = new MediaPlayer(media);
        }
        else {
            txtOpen.clear();
        }

    }
    public void imgPlayOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.play();
            imgPause.toFront();
        }
    }
    public void imgPauseOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            imgPlay.toFront();
        }
    }

    public void imgStopOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            imgPlay.toFront();
        }
    }

    public void imgRepeatOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }

    public void imgMuteOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.setMute(false);
            imgVolume.toFront();
        }

    }

    public void imgVolumeOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.setMute(true);
            imgMute.toFront();
        }

    }
}
