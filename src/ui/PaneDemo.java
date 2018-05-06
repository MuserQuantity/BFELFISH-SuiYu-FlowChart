package ui;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Stack;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import javafx.stage.Stage;
import symbols.Diamond;
import symbols.RRectangle;

import symbols.RoundRectangle;

public class PaneDemo extends Application {

	private static Stage primaryStage;

	private BorderPane mainLayout;

	@Override

	public void start(Stage primaryStage) throws IOException {

		this.primaryStage = primaryStage;

		this.primaryStage.setTitle("流程图绘制程序");

		showMainView();

	}

	private void showMainView() throws IOException {

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(PaneDemo.class.getResource("/view/initView1.fxml"));

		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		Pane pane = (Pane) mainLayout.getCenter();
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private static Stack<Scene> StageStack = new Stack<Scene>();

	public static void setScene(Scene scene) {

		primaryStage.setScene(scene);

		StageStack.add(scene);

	}

	public static void backToLastScene() {

		if (!StageStack.isEmpty()) {

			Scene scene = StageStack.peek();

			primaryStage.setScene(scene);

		} else {

			// doing nothing

		}

	}

	public static void main(String[] args) {

		launch(args);

	}

}
