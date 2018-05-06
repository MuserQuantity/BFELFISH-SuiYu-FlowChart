package control;

import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class ChooseBox extends Shape implements Serializable{
	/**
	 * 选择框主要以矩形呈现
	 * 
	 * @author minwo
	 */
	private double cBoxX;
	private double cBoxY;
	private double cBoxWidth;
	private double cBoxHeight;
	private double offset = 10;// 偏移量

	transient private Line cBox[] = new Line[5];

	// Pane pane;
	transient Circle circles[] = new Circle[9];

	public ChooseBox() {
		// this.pane = pane;

		for (int i = 0; i < 5; i++) {
			cBox[i] = new Line();
		}

		for (int i = 0; i < 9; i++) {
			circles[i] = new Circle();
		}
	}

	/*
	 * 表示要将操作框画出来offset为偏移量
	 */
	public void draw(double x, double y, double width, double height) {
		cBoxX = x - offset;
		cBoxY = y - offset;
		cBoxWidth = width + offset * 2;
		cBoxHeight = height + offset * 2;

		for (int i = 0; i < 5; i++) {
			cBox[i].setStroke(Color.BLUE);
			if (i == 4) {
				cBox[i].setStartX(cBoxX + cBoxWidth / 2);
				cBox[i].setStartY(cBoxY);
				cBox[i].setEndX(cBoxX + cBoxWidth / 2);
				cBox[i].setEndY(cBoxY - offset * 5);
				// pane.getChildren().add(cBox[i]);
				break;
			}
			if (i == 0 || i == 2 || i == 3) {
				cBox[i].setStartX(cBoxX);
			} else {
				cBox[i].setStartX(cBoxX + cBoxWidth);
			}

			if (i == 0 || i == 3 || i == 1) {
				cBox[i].setStartY(cBoxY);
			} else {
				cBox[i].setStartY(cBoxY + cBoxHeight);
			}
			if (i == 0 || i == 1 || i == 2) {
				cBox[i].setEndX(cBoxX + cBoxWidth);
			} else {
				cBox[i].setEndX(cBoxX);
			}
			if (i == 1 || i == 2 || i == 3) {
				cBox[i].setEndY(cBoxY + cBoxHeight);
			} else {
				cBox[i].setEndY(cBoxY);
			}
		}

		for (int i = 0; i < 9; i++) {
			circles[i].setRadius(offset /2);
			circles[i].setFill(Color.WHITE);
			circles[i].setStroke(Color.DARKRED);
			if (i == 0 || i == 3 || i == 5) { // 定x值
				circles[i].setCenterX(cBoxX);
			} else if (i == 1 || i == 6 || i == 8) {
				circles[i].setCenterX(cBoxX + cBoxWidth / 2);
			} else {
				circles[i].setCenterX(cBoxX + cBoxWidth);
			}
			if (i < 3) { // 定y值
				circles[i].setCenterY(cBoxY);
			} else if (i < 5) {
				circles[i].setCenterY(cBoxY + cBoxHeight / 2);
			} else {
				circles[i].setCenterY(cBoxY + cBoxHeight);
			}
			if (i == 8) {
				circles[i].setCenterY(cBoxY - offset * 5);
			}
			// pane.getChildren().add(circles[i]);
		}

	}

	public Line[] getcBox() {
		return cBox;
	}

	public Circle[] getCircles() {
		return circles;
	}


	public double getOffset() {
		return offset;
	}

	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		// TODO Auto-generated method stub
		return null;
	}


}
