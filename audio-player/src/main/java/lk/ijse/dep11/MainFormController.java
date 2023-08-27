package lk.ijse.dep11;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    public Label lblTime;
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


//        imgRepeat.setOnMousePressed(event -> imgRepeat.setEffect(new InnerShadow(30, Color.BLACK)


        ImageView [] array={imgRepeat,imgPrev,imgPlay,imgPause,imgNext,imgStop,imgVolume,imgMute,imgOpen};
        for (ImageView imageView : array) {
            imageView.setOnMouseEntered(mouseEvent -> {
                imageView.setScaleX(1.1);
                imageView.setScaleY(1.1);
            });
            imageView.setOnMouseExited(mouseEvent -> {
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            });
            imageView.setOnMousePressed(mouseEvent -> {
                imageView.setEffect(new InnerShadow(30, Color.BLACK));
            });
            imageView.setOnMouseReleased(mouseEvent -> {
                imageView.setEffect(new InnerShadow(0, Color.BLACK));

            });
        }

    }

    public void imgOpenOnMouseClicked(MouseEvent mouseEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3 Files","*mp3"));

        files = fileChooser.showOpenMultipleDialog(root.getScene().getWindow());
        if(files!=null) {
            audioFile = files.get(index);
            txtOpen.setText(audioFile.getAbsolutePath());
            setup(mouseEvent);
        }
        else {
            txtOpen.clear();
        }

    }

    public void imgPlayOnMouseClicked(MouseEvent mouseEvent) {
        if(mediaPlayer!=null) {

            mediaPlayer.play();
            imgPause.toFront();
            mediaPlayer.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                lblTime.setText(String.format("Time :%02.0f: %02.0f",duration.toMinutes(),duration.toSeconds()));
                sldTiming.setValue(duration.toSeconds()*100/mediaPlayer.getStopTime().toSeconds());
            });
        }
    }
    public void setup(MouseEvent mouseEvent){
        if(files!=null) {
            audioFile = files.get(index);
            txtOpen.setText(audioFile.getAbsolutePath());
            Media media = new Media(audioFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            imgPlayOnMouseClicked(mouseEvent);

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
//            System.out.println(sldVolume.getValue());
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
        setup(mouseEvent);
    }

    public void imgPrevOnMouseClicked(MouseEvent mouseEvent) {
        if(index>0) index--;
        else index=files.size()-1;
        imgStopOnMouseClicked(mouseEvent);
        setup(mouseEvent);
    }
}
