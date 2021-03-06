package View;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Main.Main;
import Utils.Fonts;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomOut;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePaused implements Initializable {


	@FXML
	private AnchorPane pauseAnchor;

	@FXML
	private Label resumeLabel;
	

    @FXML
    private Label restartLabel;

    @FXML
    private Label instructions;

	@FXML
	private Label endLabel;

	@FXML
	private Label exitLabel;

	@FXML
	private Label gamePausedLabel;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	resumeLabel.setFont(Fonts.minecraft30);
    	restartLabel.setFont(Fonts.minecraft30);
    	instructions.setFont(Fonts.minecraft30);
		 endLabel.setFont(Fonts.minecraft30);
		 exitLabel.setFont(Fonts.minecraft30);
		 gamePausedLabel.setFont(Fonts.minecraft50);
		 new Pulse(resumeLabel).setCycleCount(Timeline.INDEFINITE).setSpeed(1).play();
		 new Pulse(endLabel).setCycleCount(Timeline.INDEFINITE).setSpeed(1).play();
		 new Pulse(exitLabel).setCycleCount(Timeline.INDEFINITE).setSpeed(1).play();
		 new Pulse(gamePausedLabel).setCycleCount(Timeline.INDEFINITE).setSpeed(1).play();
		 new Pulse(instructions).setCycleCount(Timeline.INDEFINITE).setSpeed(1).play();
		 new Pulse(restartLabel).setCycleCount(Timeline.INDEFINITE).setSpeed(1).play();
	}
    
    @FXML
    void restart(MouseEvent event) {
    	
    	
    	new ZoomOut(restartLabel).setCycleCount(1).setSpeed(0.2).play();
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					StackPane popupInGameView= (StackPane)pauseAnchor.getParent().getParent();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("GameView.fxml"));
					StackPane pane  = loader.load();
					pane.setPrefSize(popupInGameView.getWidth(), popupInGameView.getHeight());
					popupInGameView.getChildren().removeAll(popupInGameView.getChildren());
					popupInGameView.getChildren().add(pane);
					
					GameView view = (GameView)loader.getController();	
					view.setStage((Stage)pane.getScene().getWindow());
					view.resetGame();
					view.resume();	
				
				}

				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}) , new KeyFrame(Duration.seconds(1.5)));
		timeline.play();   	
    }

    @FXML
    void instructionBtn(MouseEvent event) {
    	new ZoomOut(instructions).setCycleCount(1).setSpeed(0.7).play();
    	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
			
			try {
				Instructions.cameFromMainMenu=false;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Instructions.fxml"));
				AnchorPane pane  = loader.load();
				pane.setPrefSize(pauseAnchor.getWidth(), pauseAnchor.getHeight());
				pauseAnchor.getChildren().removeAll(pauseAnchor.getChildren());
				pauseAnchor.getChildren().add(pane);
			}
			
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		}) , new KeyFrame(Duration.seconds(1)));
	timeline.play();
    }

    

    @FXML
    void resumeGame(MouseEvent event ) {
    	
    	new ZoomOut(resumeLabel).setCycleCount(1).setSpeed(0.7).play();
    	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e) {
					e.printStackTrace();
				}
				
				robot.keyPress(KeyEvent.VK_SPACE);
				pauseAnchor.setVisible(false);
			}
		}) , new KeyFrame(Duration.seconds(1)));
	timeline.play();

    }

    @FXML
    void endGame(MouseEvent event ) {
    	
    	new ZoomOut(endLabel).setCycleCount(1).setSpeed(0.7).play();
    	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
			Stage stage=(Stage) pauseAnchor.getScene().getWindow();
						
			try {
				FXMLLoader gameViewLoader = new FXMLLoader(getClass().getResource("/View/GameView.fxml"));
				
				gameViewLoader.load();
				GameView view = (GameView)gameViewLoader.getController();
				System.out.println(view);
				view.resetGame();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
				AnchorPane pane  = loader.load();
				Scene scene = new Scene(pane);
				stage.setScene(scene);
				//stage.show();	
			}
			
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		}) , new KeyFrame(Duration.seconds(1)));
	timeline.play();

    }
    
    @FXML
    void exit(MouseEvent event ) {
    	
    	new ZoomOut(exitLabel).setCycleCount(1).setSpeed(0.7).play();
    	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Main.f.delete();
				Platform.exit();
				System.exit(0);;
			}
		}) , new KeyFrame(Duration.seconds(1)));
	timeline.play();

    }
    
	

}
