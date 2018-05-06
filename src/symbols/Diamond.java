package symbols;



import java.io.Serializable;
import java.util.ArrayList;

import com.sun.javafx.tk.Toolkit;

import control.ChooseBox;
import control.MyUtil;
import javafx.scene.Cursor;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextField;

import javafx.scene.effect.Light.Point;

import javafx.scene.layout.Pane;



import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;

import javafx.scene.shape.Polygon;



import javafx.scene.shape.Rectangle;

import javafx.scene.shape.Shape;

import javafx.scene.text.Text;



/**

 * 

 * Diamond类继承Polygon类

 * 

 * 

 * 

 * @author suisui

 *

 * 

 * 

 */



public class Diamond extends Polygon implements Symbol,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8308985132401445009L;

	private boolean isElected = false; // 选中状态，默认没被选中

	transient private Text text = new Text(""); // 文本框

	private boolean TextFieldIsEleted = false;

	private double WidthOfText = 0;// 该值记录文本 宽度，用于文本居中

	private double x = 0;

	private double y = 0;

	private double width = 0;

	private double height = 0;

	private Double[] points;

	private ChooseBox cBox = new ChooseBox();

	private ArrayList<LLine> lines = new ArrayList<>();



	/**

	 * 

	 * 私有函数，初始化图形的属性

	 * 

	 */



	private void initialize() {

		updatePoints();

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

			x = e.getX() - width / 2;

			y = e.getY();

			updatePoints();

			drawElectBox();



			this.getPoints().addAll(points);

			this.updateText();

		});



	}



	// 更新Text的位置

	private void updateText() {

		text.setVisible(false);
		setTextCencered();
		text.setVisible(true);

	}



	/**

	 *

	 * 鼠标进入图形内时，显示图形边界

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



	/**

	 * 

	 * 私有函数，更新多边形的点集

	 * 

	 */



	private void updatePoints() {



		this.getPoints().clear();



		points = new Double[] { x, y, x + width / 2, y - height / 2, x + width, y, x + width / 2, y + height / 2 };



	}



	/**

	 * 

	 * Diamond类构造函数

	 * 

	 * 

	 * 

	 * @param x

	 * 

	 *            菱形最左边一点的x坐标

	 * 

	 * @param y

	 * 

	 *            菱形最左边一点的y坐标

	 * 

	 * @param width

	 * 

	 *            菱形的长

	 * 

	 * @param height

	 * 

	 *            菱形的宽

	 * 

	 */



	public Diamond(double x, double y, double width, double height) {



		this.x = x;



		this.y = y;



		this.width = width;



		this.height = height;



		this.initialize();



		super.getPoints().addAll(points);



	}



	// 画出操作框

	private void drawElectBox() {

		isElected = true;



		if (isElected) {

			Circle circles[] = cBox.getCircles();

			circles[0].setOnMouseDragged(e -> {

				this.setWidth(circles[0].getCenterX() - e.getX() + this.getWidth());

				this.setHeight(circles[0].getCenterY() - e.getY() + this.getHeight());

				if (e.getX() < circles[2].getCenterX()) {// 因为此时的2的x值和7的y值是不变的

					this.setX(e.getX() + cBox.getOffset());

				} else {

					this.setX(circles[2].getCenterX());

					this.setWidth(0);

				}

				if (e.getY() < circles[7].getCenterY()) {

					this.setY(e.getY() + cBox.getOffset() + this.getHeight() / 2);

				} else {

					this.setY(circles[7].getCenterY() + cBox.getOffset());

					this.setHeight(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});



			circles[1].setOnMouseDragged(e -> {

				this.setHeight(circles[1].getCenterY() - e.getY() + this.getHeight());

				if (e.getY() < circles[7].getCenterY()) {

					this.setY(e.getY() + cBox.getOffset() + this.getHeight() / 2);

				} else {

					this.setY(circles[7].getCenterY() + cBox.getOffset() + this.getHeight() / 2);

					this.setHeight(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});



			circles[2].setOnMouseDragged(e -> {

				this.setWidth(e.getX() - circles[2].getCenterX() + this.getWidth());

				this.setHeight(circles[2].getCenterY() - e.getY() + this.getHeight());

				if (e.getX() < circles[0].getCenterX()) {

					this.setWidth(0);

				}

				if (e.getY() < circles[7].getCenterY()) {

					this.setY(e.getY() + cBox.getOffset() + this.getHeight() / 2);

				} else {

					this.setHeight(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});

			circles[3].setOnMouseDragged(e -> {

				this.setWidth(circles[3].getCenterX() - e.getX() + this.getWidth());

				if (e.getX() < circles[2].getCenterX()) {

					this.setX(e.getX() + cBox.getOffset());

				} else {

					this.setWidth(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});



			circles[4].setOnMouseDragged(e -> {

				this.setWidth(e.getX() - circles[4].getCenterX() + this.getWidth());

				if (e.getX() < circles[0].getCenterX()) {

					this.setWidth(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});



			circles[5].setOnMouseDragged(e -> {

				this.setWidth(circles[5].getCenterX() - e.getX() + this.getWidth());

				this.setHeight(e.getY() - circles[5].getCenterY() + this.getHeight());

				this.setY((e.getY() - circles[5].getCenterY()) / 2 + this.getY());// 因为左边点的y变动是height变动的一半

				if (e.getX() < circles[2].getCenterX()) {

					this.setX(e.getX() + cBox.getOffset());

				} else {

					this.setWidth(0);

				}

				if (e.getY() < circles[2].getCenterY()) {

					this.setHeight(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});



			circles[6].setOnMouseDragged(e -> {

				this.setHeight(e.getY() - circles[6].getCenterY() + this.getHeight());

				this.setY((e.getY() - circles[6].getCenterY()) / 2 + this.getY()); // 变动的height/2就是左边点要变动的距离

				if (e.getY() < circles[0].getCenterY()) {

					this.setHeight(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});



			circles[7].setOnMouseDragged(e -> {

				this.setWidth(e.getX() - circles[7].getCenterX() + this.getWidth());

				this.setHeight(e.getY() - circles[7].getCenterY() + this.getHeight());

				this.setY((e.getY() - circles[7].getCenterY()) / 2 + this.getY()); // 同样因为左边的点的变动是height的一半

				if (e.getX() < circles[0].getCenterX()) {

					this.setWidth(0);

				}

				if (e.getY() < circles[0].getCenterY()) {

					this.setHeight(0);

				}

				updatePoints();

				this.getPoints().addAll(points);

				cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

			});

			cBox.draw(this.getX(), this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());



		}



	}

	// getters&setters



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
		setTextCencered();

	}
	private void setTextCencered() {
		double width = Toolkit.getToolkit().getFontLoader().computeStringWidth(text.getText(), text.getFont());
		text.setLayoutX(this.getX()+this.getWidth()/2-width/2);
		text.setLayoutY(this.getY()+5);
	}


	public double getX() {



		return x;



	}



	public void setX(double x) {



		this.x = x;



	}



	public double getY() {



		return y;



	}



	public void setY(double y) {



		this.y = y;



	}



	public double getWidth() {



		return width;



	}



	public void setWidth(double width) {



		this.width = width;



	}



	public double getHeight() {



		return height;



	}



	public void setHeight(double height) {



		this.height = height;



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
//		Diamond d = new Diamond(x, y, width, height);
//
//		d.updatePoints();
//
//		d.setCursor(Cursor.HAND);
//
//		d.setStroke(Color.BLACK);
//
//		d.setFill(Color.WHITE);
//
//		d.setStrokeWidth(1.3);
//
//		d.showSymbolBorder();
//
//		d.setOnMouseClicked(e -> {
//
//			d.drawElectBox();
//
//		});
//
//		d.setOnMouseDragged(e -> {
//
//			d.setX(e.getX() - d.getWidth() / 2);
//
//			d.setY(e.getY());
//
//			d.updatePoints();
//
//			d.drawElectBox();
//
//			d.getPoints().addAll(points);
//
//
//
//		});
//
//
//
//		return d;
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



	public boolean isTextFieldIsEleted() {

		return TextFieldIsEleted;

	}



	public void setTextFieldIsEleted(boolean textFieldIsEleted) {

		TextFieldIsEleted = textFieldIsEleted;

	}



	@Override

	public TextField getT() {

		// TODO Auto-generated method stub

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
