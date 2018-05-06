package symbols;

import java.io.Serializable;
import java.util.ArrayList;

import control.ChooseBox;
import control.MyUtil;
import javafx.scene.Cursor;

import javafx.scene.control.TextField;

import javafx.scene.effect.Light.Point;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;

import javafx.scene.Cursor;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;

import javafx.scene.shape.Circle;

import javafx.scene.shape.Rectangle;

import javafx.scene.shape.Shape;

import javafx.scene.text.Text;

public class RoundRectangle extends Rectangle implements Symbol ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8154344449258771157L;

	private boolean isElected = false;// 选中状态，默认没被选中

	transient private Text text = new Text(""); // 文本框

	private boolean TextFieldIsEleted = false;

	private ChooseBox cBox = new ChooseBox();

	private ArrayList<LLine> lines = new ArrayList<>();

	/**
	 * 
	 * 
	 * 
	 * 私有函数，初始化图形的属性
	 * 
	 *
	 * 
	 * 
	 * 
	 */

	private void initialize() {

		this.setArcHeight(25);

		this.setArcWidth(25);

		this.setCursor(Cursor.HAND);

		this.setStroke(Color.BLACK);

		this.setFill(Color.WHITE);

		this.setStrokeWidth(1.3);

		this.showSymbolBorder();

		this.updateText();

		this.setOnMouseClicked(e -> {

			drawElectBox();

			this.updateText();

		});

		this.setOnMouseDragged(e -> {

			drawElectBox();

			this.setX(e.getX() - this.getWidth() / 2);

			this.setY(e.getY() - this.getHeight() / 2);

			this.updateText();

		});

	}

	// 更新Text的位置

	private void updateText() {

		text.setVisible(false);
		setTextCentered();
		text.setVisible(true);
		

	}

	/**
	 *
	 * 
	 * 
	 * 鼠标进入图形内时，显示图形边界
	 * 
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

	// 画出判断框

	private void drawElectBox() {

		isElected = true;

		Circle circles[] = cBox.getCircles();

		circles[0].setOnMouseDragged(e -> {

			if (e.getX() < circles[7].getCenterX()) {

				this.setWidth(circles[0].getCenterX() - e.getX() + this.getWidth());

				this.setX(e.getX() - circles[0].getCenterX() + this.getX());

			}

			if (e.getY() < circles[7].getCenterY()) {

				this.setY(e.getY() - circles[0].getCenterY() + this.getY());

				this.setHeight(circles[0].getCenterY() - e.getY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		circles[1].setOnMouseDragged(e -> {

			if (e.getY() < circles[6].getCenterY()) {

				this.setY(e.getY() - circles[1].getCenterY() + this.getY());

				this.setHeight(circles[1].getCenterY() - e.getY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		circles[2].setOnMouseDragged(e -> {

			if (e.getX() > circles[5].getCenterX()) {

				this.setWidth(e.getX() - circles[2].getCenterX() + this.getWidth());

			}

			if (e.getY() < circles[5].getCenterY()) {

				this.setY(e.getY() - circles[2].getCenterY() + this.getY());

				this.setHeight(circles[2].getCenterY() - e.getY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		circles[3].setOnMouseDragged(e -> {

			if (e.getX() < circles[2].getCenterX()) {

				this.setWidth(circles[3].getCenterX() - e.getX() + this.getWidth());

				this.setX(e.getX() - circles[3].getCenterX() + this.getX());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		circles[4].setOnMouseDragged(e -> {

			if (e.getX() > circles[3].getCenterX()) {

				this.setWidth(e.getX() - circles[4].getCenterX() + this.getWidth());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		circles[5].setOnMouseDragged(e -> {

			if (e.getX() < circles[2].getCenterX()) {

				this.setX(e.getX() - circles[5].getCenterX() + this.getX());

				this.setWidth(circles[5].getCenterX() - e.getX() + this.getWidth());

			}

			if (e.getY() > circles[2].getCenterY()) {

				this.setHeight(e.getY() - circles[5].getCenterY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		circles[6].setOnMouseDragged(e -> {

			if (e.getY() > circles[1].getCenterY()) {

				this.setHeight(e.getY() - circles[6].getCenterY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		circles[7].setOnMouseDragged(e -> {

			if (e.getX() > circles[0].getCenterX()) {

				this.setWidth(e.getX() - circles[7].getCenterX() + this.getWidth());

			}

			if (e.getY() > circles[0].getCenterY()) {

				this.setHeight(e.getY() - circles[7].getCenterY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		});

		cBox.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());

	}

	/**
	 * 
	 * 
	 * 
	 * RoundRectangle构造方法
	 * 
	 * 
	 * 
	 */

	public RoundRectangle() {

		super();

		initialize();

	}

	/**
	 * 
	 * 
	 * 
	 * RoundRectangle构造方法
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param width
	 * 
	 * 
	 * 
	 *            圆角矩形的宽
	 * 
	 * 
	 * 
	 * @param height
	 * 
	 * 
	 * 
	 *            圆角矩形的长
	 * 
	 * 
	 * 
	 */

	public RoundRectangle(double width, double height) {

		super(width, height);

		initialize();

	}

	/**
	 * 
	 * 
	 * 
	 * RoundRectangle构造方法
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param x
	 * 
	 * 
	 * 
	 *            圆角矩形左上角的x坐标
	 * 
	 * 
	 * 
	 * @param y
	 * 
	 * 
	 * 
	 *            圆角矩形左上角的y坐标
	 * 
	 * 
	 * 
	 * @param width
	 * 
	 * 
	 * 
	 *            圆角矩形的宽
	 * 
	 * 
	 * 
	 * @param height
	 * 
	 * 
	 * 
	 *            圆角矩形的长
	 * 
	 * 
	 * 
	 */

	public RoundRectangle(double x, double y, double width, double height) {

		super(x, y, width, height);

		initialize();

	}

	/**
	 * 
	 * 
	 * 
	 * RoundRectangle构造方法
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param width
	 * 
	 * 
	 * 
	 *            矩形的宽
	 * 
	 * 
	 * 
	 * @param height
	 * 
	 * 
	 * 
	 *            矩形的长
	 * 
	 * 
	 * 
	 * @param text
	 * 
	 * 
	 * 
	 *            文本框
	 * 
	 * 
	 * 
	 */

	public RoundRectangle(double width, double height, Text text) {

		this(width, height);

		this.text = text;

		initialize();

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

		double textWidth = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(text.getText(),
				text.getFont());

		text.setLayoutX(this.getX() + this.getWidth() / 2 - textWidth / 2);
		text.setLayoutY(this.getY() + this.getHeight() / 2 + 5);
	}

	public ChooseBox getcBox() {

		return cBox;

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

//	@Override
//
//	public Symbol clone() {
//
//		RoundRectangle roundRect = new RoundRectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//
//		roundRect.setArcHeight(15);
//
//		roundRect.setArcWidth(15);
//
//		roundRect.setCursor(Cursor.HAND);
//
//		roundRect.setStroke(Color.BLACK);
//
//		roundRect.setFill(Color.WHITE);
//
//		roundRect.setStrokeWidth(1.3);
//
//		roundRect.showSymbolBorder();
//
//		roundRect.setOnMouseClicked(e -> {
//
//			roundRect.isElected = true;
//
//			roundRect.drawElectBox();
//
//		});
//
//		roundRect.setOnMouseDragged(e -> {
//
//			roundRect.drawElectBox();
//
//			roundRect.setX(e.getX() - roundRect.getWidth() / 2);
//
//			roundRect.setY(e.getY() - roundRect.getHeight() / 2);
//
//		});
//
//		return roundRect;
//
//	}
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

		// TODO Auto-generated method stub

		return null;

	}

	@Override

	public boolean isTextFieldIsEleted() {

		return this.TextFieldIsEleted;

	}

	@Override

	public void setTextFieldIsEleted(boolean textFieldIsEleted) {

		this.TextFieldIsEleted = textFieldIsEleted;

	}

	@Override

	public TextField getT() {

		return null;

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
