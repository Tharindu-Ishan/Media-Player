package lk.ijse.dep11;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
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


    private int index=0;
    List<File> files;
    File audioFile;
    MediaPlayer mediaPlayer;




    public void initialize(){

        if(audioFile!=null) {

        }
        imgRepeat.setOnMouseEntered(mouseEvent1 -> {
            imgRepeat.setScaleX(1.1);
            imgRepeat.setScaleY(1.1);
        });
        imgRepeat.setOnMouseExited(mouseEvent -> {
            imgRepeat.setScaleX(1);
            imgRepeat.setScaleY(1);
        });
        imgRepeat.setOnMousePressed(event -> imgRepeat.setEffect(new InnerShadow(30, Color.BLACK)));

        imgPrev.setOnMouseEntered(mouseEvent1 -> {
            imgPrev.setScaleX(1.1);
            imgPrev.setScaleY(1.1);
        });
        imgPrev.setOnMouseExited(mouseEvent -> {
            imgPrev.setScaleX(1);
            imgPrev.setScaleY(1);
        });
        imgPlay.setOnMouseEntered(mouseEvent1 -> {
            imgPlay.setScaleX(1.1);
            imgPlay.setScaleY(1.1);
        });
        imgPlay.setOnMouseExited(mouseEvent -> {
            imgPlay.setScaleX(1);
            imgPlay.setScaleY(1);
        });
        imgPause.setOnMouseEntered(mouseEvent1 -> {
            imgPause.setScaleX(1.1);
            imgPause.setScaleY(1.1);
        });
        imgPause.setOnMouseExited(mouseEvent -> {
            imgPause.setScaleX(1);
            imgPause.setScaleY(1);
        });
        imgNext.setOnMouseEntered(mouseEvent1 -> {
            imgNext.setScaleX(1.1);
            imgNext.setScaleY(1.1);
        });
        imgNext.setOnMouseExited(mouseEvent -> {
            imgNext.setScaleX(1);
            imgNext.setScaleY(1);
        });
        imgStop.setOnMouseEntered(mouseEvent1 -> {
            imgStop.setScaleX(1.1);
            imgStop.setScaleY(1.1);
        });
        imgStop.setOnMouseExited(mouseEvent -> {
            imgStop.setScaleX(1);
            imgStop.setScaleY(1);
        });
        imgVolume.setOnMouseEntered(mouseEvent1 -> {
            imgVolume.setScaleX(1.1);
            imgVolume.setScaleY(1.1);
        });
        imgVolume.setOnMouseExited(mouseEvent -> {
            imgVolume.setScaleX(1);
            imgVolume.setScaleY(1);
        });
        imgMute.setOnMouseEntered(mouseEvent1 -> {
            imgMute.setScaleX(1.1);
            imgMute.setScaleY(1.1);
        });
        imgMute.setOnMouseExited(mouseEvent -> {
            imgMute.setScaleX(1);
            imgMute.setScaleY(1);
        });
        imgOpen.setOnMouseEntered(mouseEvent1 -> {
            imgOpen.setScaleX(1.1);
            imgOpen.setScaleY(1.1);
        });
        imgOpen.setOnMouseExited(mouseEvent -> {
            imgOpen.setScaleX(1);
            imgOpen.setScaleY(1);
        });


    }

    public void imgOpenOnMouseClicked(MouseEvent mouseEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3 Files","*mp3"));
//        File audioFile = fileChooser.showOpenDialog(root.getScene().getWindow());


        files = fileChooser.showOpenMultipleDialog(root.getScene().getWindow());
        if(files!=null) {
            audioFile = files.get(index);
            txtOpen.setText(audioFile.getAbsolutePath());
        }
        else {
            txtOpen.clear();
        }

    }

    public void imgPlayOnMouseClicked(MouseEvent mouseEvent) {
        if(files!=null) {
            audioFile = files.get(index);
            txtOpen.setText(audioFile.getAbsolutePath());
            Media media = new Media(audioFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
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
    public void sldVolumeOnMouseDragged(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.setVolume(sldVolume.getValue());
            System.out.println(sldVolume.getValue());
        }
    }

    public void sldVolumeOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null){
            mediaPlayer.setVolume(sldVolume.getValue());
        }
    }
    public void imgNextOnMouseClicked(MouseEvent mouseEvent) {

        if(files.size()-1>index) index++;
        else index=0;
        imgStopOnMouseClicked(mouseEvent);
        imgPlayOnMouseClicked(mouseEvent);
    }

    public void imgPrevOnMouseClicked(MouseEvent mouseEvent) {
        if(index>0) index--;
        else index=files.size()-1;
        imgStopOnMouseClicked(mouseEvent);
        imgPlayOnMouseClicked(mouseEvent);
    }
}
