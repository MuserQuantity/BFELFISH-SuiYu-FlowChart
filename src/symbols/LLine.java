package symbols;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.javafx.tk.Toolkit;

import control.ChooseBox;
import control.MyUtil;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.Dragboard;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;

import javafx.scene.shape.MoveTo;

import javafx.scene.shape.Path;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 * 
 * LLine类
 * 
 * 
 * 
 * @author suisui
 *
 * 
 * 
 */

public class LLine extends Path implements Symbol, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5579908370661051831L;
	private boolean isElected = false; // 选中状态，默认没被选中
	transient private Text text = new Text(""); // 文本框
	private double startX = 0;
	private double startY = 0;
	private double endX = 0;
	private double endY = 0;
	private double length;
	private boolean withArrow = false;// 直线是否带箭头，默认为false；
	transient private Circle cBox[] = new Circle[2];
	
	private Symbol startSymbol;		//线所指的开始图形节点
	private Symbol endSymbol;	//线所指的结束图形节点
	
	private ArrayList<LLine> lines=new ArrayList<LLine>();
	private final double OFFSET = 10;
	{
		for (int i = 0; i < cBox.length; i++) {
			cBox[i] = new Circle();
		}
	}

	/**
	 * 
	 * 私有函数，初始化图形的属性
	 * 
	 */

	private void initialize() {
		updatePath();
		this.setStrokeWidth(1.3);
		this.setCursor(Cursor.HAND);
		this.setStroke(Color.BLACK);
		this.setFill(Color.BLACK);
		this.showSymbolBorder();
		this.updateText();
		this.setOnMouseClicked(e -> {
			drawElectBox();
			this.updateText();
		});

		this.setOnMouseDragged(e -> {
			drawElectBox();
			double x = (startX + endX) / 2;
			double y = (startY + endY) / 2;
			startX += e.getX() - x;
			startY += e.getY() - y;
			endX += e.getX() - x;
			endY += e.getY() - y;
			updatePath();
		});
	}

	private void updateText() {
		text.setVisible(false);
		this.setTextCentered();
		text.setVisible(true);
	}

	/**
	 * 鼠标进入图形内边框变红
	 */
	private void showSymbolBorder() {
		this.setOnMouseEntered(e -> {
			this.setStroke(Color.DARKRED);
			this.setStrokeWidth(2.0);
		});
		this.setOnMouseExited(e -> {
			this.setStroke(Color.BLACK);
			this.setStrokeWidth(1.3);
		});
	}

	private void drawElectBox() {
		this.isElected = true;
		cBox[0].setOnMouseDragged(e -> {
			this.startX += e.getX() - cBox[0].getCenterX();
			this.startY += e.getY() - cBox[0].getCenterY();
			updatePath();
			draw();
		});
		cBox[1].setOnMouseDragged(e -> {
			this.endX += e.getX() - cBox[1].getCenterX();
			this.endY += e.getY() - cBox[1].getCenterY();
			updatePath();
			draw();
		});
		draw();
	}

	private void draw() {
		for (int i = 0; i < cBox.length; i++) {
			cBox[i].setRadius(OFFSET / 2);
			cBox[i].setFill(Color.WHITE);
			cBox[i].setStroke(Color.BLACK);
		}
		if (endX > startX) {
			cBox[0].setCenterX(startX - OFFSET);
			cBox[1].setCenterX(endX + OFFSET);
		} else {
			cBox[0].setCenterX(startX + OFFSET);
			cBox[1].setCenterX(endX - OFFSET);
		}
		if (endY > startY) {
			cBox[0].setCenterY(startY - OFFSET);
			cBox[1].setCenterY(endY + OFFSET);
		} else {
			cBox[0].setCenterY(startY + OFFSET);
			cBox[1].setCenterY(endY - OFFSET);
		}
	}

	// 画没有箭头的直线
	private void drawLine() {
		this.getElements().clear();
		MoveTo start = new MoveTo(startX, startY);
		LineTo line1 = new LineTo(endX, endY);
		this.getElements().addAll(start, line1);
	}

	// 画带箭头的直线
	private void drawLineArrow() {
		this.getElements().clear();
		double H = 10; // 箭头高度
		double L = 4; // 底边的一半
		int x3 = 0;
		int y3 = 0;
		int x4 = 0;
		int y4 = 0;
		double awrad = Math.atan(L / H); // 箭头角度
		double awraLen = Math.sqrt(L * L + H * H); // 箭头的长度
		double[] arrXY_1 = rotateVec(endX - startX, endY - startY, awrad, true, awraLen);
		double[] arrXY_2 = rotateVec(endX - startX, endY - startY, -awrad, true, awraLen);
		double x_3 = endX - arrXY_1[0]; // (x3,y3)是第一端点
		double y_3 = endY - arrXY_1[1];
		double x_4 = endX - arrXY_2[0]; // (x4,y4)是第二端点
		double y_4 = endY - arrXY_2[1];
		Double X3 = new Double(x_3);
		x3 = X3.intValue();
		Double Y3 = new Double(y_3);
		y3 = Y3.intValue();
		Double X4 = new Double(x_4);
		x4 = X4.intValue();
		Double Y4 = new Double(y_4);
		y4 = Y4.intValue();
		MoveTo start = new MoveTo(startX, startY);
		LineTo line1 = new LineTo(endX, endY);
		LineTo line2 = new LineTo(x3, y3);
		LineTo line3 = new LineTo(x4, y4);
		LineTo line4 = new LineTo(endX, endY);
		this.getElements().addAll(start, line1, line2, line3, line4);
	}

	private void updatePath() {
		if (withArrow) {
			drawLineArrow();
		}else {
			drawLine();
		}
	}

	// 计算

	private double[] rotateVec(double px, double py, double angle, boolean isChLen, double newLen) {

		// 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度

		double mathstr[] = new double[2];

		// 计算完成后的(vx,vy)

		double vx = px * Math.cos(angle) - py * Math.sin(angle);

		double vy = px * Math.sin(angle) + py * Math.cos(angle);

		if (isChLen) {

			double d = Math.sqrt(vx * vx + vy * vy);

			vx = vx / d * newLen;

			vy = vy / d * newLen;

			mathstr[0] = vx;

			mathstr[1] = vy;

		}

		return mathstr;

	}

	/**
	 * 
	 * LLine构造函数
	 * 
	 * 
	 * 
	 * @param startX
	 * 
	 *            直线开始的x坐标
	 * 
	 * @param startY
	 * 
	 *            直线开始的y坐标
	 * 
	 * @param endX
	 * 
	 *            直线结束的x坐标
	 * 
	 * @param endY
	 * 
	 *            直线结束的y坐标
	 * 
	 */

	public LLine(double startX, double startY, double endX, double endY) {

		this.startX = startX;

		this.startY = startY;

		this.endX = endX;

		this.endY = endY;

		this.length = Math.sqrt((startY - startX) * (startY - startX) + (endX - endY) * (endX - endY));

		this.initialize();

	}

	// getters & setters

	public boolean isElected() {

		return isElected;

	}

	public void setElected(boolean isElected) {

		this.isElected = isElected;

	}

	public Text getText() {

		return text;

	}

	public void setText(Text text) {
		this.text = text;
		setTextCentered();
	}

	private void setTextCentered() {
		double textWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth(text.getText(), text.getFont());
		text.setLayoutX(this.getX() - textWidth / 2);
		text.setLayoutY(this.getY() + 5);
	}

	public double getStartX() {

		return startX;

	}

	public void setStartX(double startX) {

		this.startX = startX;

	}

	public double getStartY() {

		return startY;

	}

	public void setStartY(double startY) {

		this.startY = startY;

	}

	public double getEndX() {

		return endX;

	}

	public void setEndX(double endX) {

		this.endX = endX;

	}

	public double getEndY() {

		return endY;

	}

	public void setEndY(double endY) {

		this.endY = endY;

	}

	public double getLength() {

		return length;

	}

	public void setLength(double length) {

		this.length = length;

	}

	@Override
	public boolean add(Symbol symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Symbol symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Symbol getSymbol(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChooseBox getcBox() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public Symbol clone() {
	// LLine l=new LLine(startX, startY, endX, endY);
	// l.updatePath();
	// l.setStrokeWidth(1.3);
	// l.setCursor(Cursor.HAND);
	// l.setStroke(Color.BLACK);
	// l.setFill(Color.BLACK);
	// l.showSymbolBorder();
	// l.setOnMouseClicked(e -> {
	// l.drawElectBox();
	// });
	// l.setOnMouseDragged(e -> {
	// l.drawElectBox();
	// double x = (l.getStartX()+l.getEndX()) / 2;
	// double y = (l.getStartY() + l.getEndY()) / 2;
	// l.setStartX(l.getStartX()+e.getX()-x);
	// l.setStartY(l.getStartY()+e.getY()-y);
	// l.setEndX(l.getEndX()+e.getX()-x);
	// l.setEndY(l.getEndY()+e.getY()-y);
	//
	// l.updatePath();
	//
	// });
	// return l;
	// }
	public Symbol clone() {
		try {
			return MyUtil.clone(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<LLine> getInLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInLine(LLine line) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<LLine> getOutLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOutLine(LLine line) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canAddInLine() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAddOutLine() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Circle[] getCircles() {
		return this.cBox;
	}

	@Override
	public boolean isTextFieldIsEleted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTextFieldIsEleted(boolean textFieldIsEleted) {
		// TODO Auto-generated method stub

	}

	@Override
	public TextField getT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getX() {
		return this.startX;
	}

	@Override
	public void setX(double x) {
		this.setStartX(x);
	}

	@Override
	public double getY() {
		return this.getStartY();
	}

	@Override
	public void setY(double y) {
		this.setStartX(y);
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHeight(double height) {
		// TODO Auto-generated method stub

	}
	@Override
	public ArrayList<LLine> getLines() {
		return this.lines;
	}

	@Override
	public void setLines(ArrayList<LLine> lines) {
		this.lines=lines;
	}

}
