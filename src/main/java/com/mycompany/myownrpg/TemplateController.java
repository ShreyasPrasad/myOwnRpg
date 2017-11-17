package com.mycompany.myownrpg;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TemplateController implements Initializable {

    @FXML
    Label t1, t2, t3, t4, story, moves;
    @FXML
    ImageView waveleft, waveright, fist, doubletap;
    @FXML
    Pane pane;
    @FXML
    AnchorPane bg;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //display all scenes
      //  for (int i = 0; i < configLoader.numTemplates; i++) {           
            story.setText(configLoader.templates.get(0).getTextProfile().getContent());
            story.setStyle("-fx-font-size: " + configLoader.templates.get(0).getTextProfile().getSize() + "pt");
            bg.setStyle("-fx-background-color: " + configLoader.templates.get(0).getBgColor());

            fadePane(0);
       // }

    }

    private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.LEFT) {
                waveleft.setImage(new Image(getClass().getResource("/images/waveleft1").toExternalForm()));
                userMove = 0;
                timer(1,0,-1);
            } else if (event.getCode() == KeyCode.RIGHT) {
                waveright.setImage(new Image(getClass().getResource("/images/waveright1").toExternalForm()));
                userMove = 1;
                 timer(1,0,-2);
            } else if (event.getCode() == KeyCode.UP) {
                fist.setImage(new Image(getClass().getResource("/images/fist1").toExternalForm()));
                userMove = 2;
                 timer(1,0,-3);
            } else if (event.getCode() == KeyCode.DOWN) {
                doubletap.setImage(new Image(getClass().getResource("/images/doubletap1").toExternalForm()));
                userMove = 3;
                 timer(1,0,-4);
            }
            event.consume();
        }
    };

    private void fadePane(int screen) {
        timer(configLoader.templates.get(screen).getDuration(), 0, screen);
    }

    private void timer(double interval, final int duration, final int screen) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(interval),
                        new EventHandler() {

                            @Override
                            public void handle(Event event) {
                                if (duration != 0 && canMove) {
                                    switch (screen) {
                                        case 0:
                                            int newTime = Integer.parseInt(t1.getText()) + (-1);
                                            t1.setText("" + newTime);
                                            break;
                                        case 1:
                                            int newTime1 = Integer.parseInt(t2.getText()) + (-1);
                                            t2.setText("" + newTime1);
                                            break;
                                        case 2:
                                            int newTime2 = Integer.parseInt(t3.getText()) + (-1);
                                            t3.setText("" + newTime2);
                                            break;
                                        default:
                                            int newTime3 = Integer.parseInt(t4.getText()) + (-1);
                                            t4.setText("" + newTime3);
                                            break;
                                    }
                                    if (t1.getText().equals("0") || t2.getText().equals("0") || t3.getText().equals("0") || t4.getText().equals("0")) {
                                        timeline.stop();
                                        initLoss();
                                    } else if (userMove == screen) {
                                        timeline.stop();
                                        t1.setText(null);
                                        t2.setText(null);
                                        t3.setText(null);
                                        t4.setText(null);
                                        userMove = -1;
                                        canMove = false;
                                        currentM++;
                                        startGame();
                                    }

                                } else {
                                    if (screen>=0){
                                    AnimationProfile animPane = new AnimationProfile(pane);
                                    animPane.setFade(1000);
                                    pane.setVisible(true);
                                    animPane.beginCombinedAnim(1, true);

                                    AnimationProfile animPane1 = new AnimationProfile(moves);
                                    animPane1.setFade(400);
                                    moves.setVisible(true);
                                    animPane1.beginCombinedAnim(1, true);
                                    animPane1.qt.onFinishedProperty().set(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            startGame();
                                        }
                                    });
                                    }
                                    else{
                                        int curScreen=-1*screen;
                                        switch (curScreen){
                                            case 1:
                   
                                                waveleft.setImage(new Image(getClass().getResource("/images/waveleft").toExternalForm()));
                                                break;
                                            case 2:
                                                waveright.setImage(new Image(getClass().getResource("/images/waveright").toExternalForm()));
                                                   break;                
                                            case 3:
                                                fist.setImage(new Image(getClass().getResource("/images/fist").toExternalForm()));
                                                   break;
                                            default:
                                                doubletap.setImage(new Image(getClass().getResource("/images/doubletap").toExternalForm()));
                                                   break;
                                        }
                                    }
                                }
                            }
                        }));
        timeline.playFromStart();
    }
    private boolean canMove = false;
    private int userMove = -1;
    public int currentM=0;
    private int curScreen=0;
    private void startGame() {
        ArrayList<motionClass> motions = configLoader.templates.get(curScreen).motions;
            int type = motions.get(currentM).getType();
            double duration = motions.get(currentM).getDuration();
            switch (type) {
                case 0:
                    moves.setText("You have " + duration + " seconds to wave left.");
                    t1.setText(duration + "");
                    break;
                case 1:
                    moves.setText("You have " + duration + " seconds to wave right.");
                    t2.setText(duration + "");
                    break;
                case 2:
                    moves.setText("You have " + duration + " seconds to make a fist.");
                    t3.setText(duration + "");
                    break;
                default:
                    moves.setText("You have " + duration + " seconds to doubletap.");
                    t4.setText(duration + "");
                    break;
            }
            canMove = true;
            timer(1.0, (int) duration, type);
    }

    private void initLoss() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Loss.fxml"));
            Scene newScene = new Scene(root);

            Stage currentStage=myownrpgmain.getStage();
            currentStage.hide();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AnimationProfile {

    protected ArrayList<FadeTransition> ft;
    protected ArrayList<TranslateTransition> tt;
    protected ArrayList<ScaleTransition> st;
    protected ParallelTransition pt;
    protected SequentialTransition qt;

    protected Node node;

    public AnimationProfile(Node _node) {
        node = _node;
        ft = new ArrayList<FadeTransition>();
        tt = new ArrayList<TranslateTransition>();
        st = new ArrayList<ScaleTransition>();
    }

    protected void setFade(int durationInMs) {
        FadeTransition ft1 = new FadeTransition(Duration.millis(durationInMs), node);
        ft1.setFromValue(0.0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(false);
        ft.add(ft1);
    }

    protected void setTranslate(int duration, double xmove, double ymove) {
        TranslateTransition tt1 = new TranslateTransition(Duration.millis(duration), node);
        tt1.setByX(xmove);
        tt1.setByY(ymove);
        tt1.setCycleCount(1);
        tt1.setAutoReverse(false);
        tt.add(tt1);
    }

    protected void setScale(int duration, double xmove, double ymove) {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(duration), node);
        st1.setByX(xmove);
        st1.setByY(ymove);
        st1.setCycleCount(1);
        st1.setAutoReverse(false);
        st.add(st1);
    }

    protected void beginCombinedAnim(int cycle, boolean isSeq) {
        pt = new ParallelTransition();
        qt = new SequentialTransition();
        if (ft != null) {
            if (isSeq) {
                for (FadeTransition ft1 : ft) {
                    qt.getChildren().add(ft1);
                }
            } else {
                for (FadeTransition ft1 : ft) {
                    pt.getChildren().add(ft1);
                }
            }
        }
        if (tt != null) {
            if (isSeq) {
                for (TranslateTransition tt1 : tt) {
                    qt.getChildren().add(tt1);
                }
            } else {
                for (TranslateTransition tt1 : tt) {
                    pt.getChildren().add(tt1);
                }
            }
        }
        if (st != null) {
            if (isSeq) {
                for (ScaleTransition st1 : st) {
                    qt.getChildren().add(st1);
                }
            } else {
                for (ScaleTransition st1 : st) {
                    pt.getChildren().add(st1);
                }
            }
        }
        if (isSeq) {
            qt.setCycleCount(cycle);
            qt.setAutoReverse(false);
            qt.play();
        } else {
            pt.setCycleCount(cycle);
            pt.setAutoReverse(false);
            pt.play();
        }
    }
}
