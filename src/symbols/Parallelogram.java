package symbols;



import java.io.Serializable;
import java.util.ArrayList;

import com.sun.javafx.tk.Toolkit;

import control.ChooseBox;
import control.MyUtil;
import javafx.scene.Cursor;

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

 * Parrallelogram类继承Polygon多边形类

 * 

 * @author suisui

 *

 * 

 * 

 */



public class Parallelogram extends Polygon implements Symbol,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6770139281731072371L;

	private boolean isElected = false; // 选中状态，默认没被选中

	transient private Text text=new Text(""); // 文本框

	private boolean TextFieldIsEleted = false;

	private double WidthOfText = 0;// 该值记录文本 宽度，用于文本居中

	private double x = 0;

	private double y = 0;

	private double width = 0;

	private double height = 0;

	private Double[] points;

	ChooseBox cBox = new ChooseBox();

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

			drawElectBox();

			x = e.getX() - width / 2;

			y = e.getY() + height / 2;

			updatePoints();

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



		points = new Double[] {



				x, y,



				x + height / Math.sqrt(3), y - height,



				x + width, y - height,



				x + width - height / Math.sqrt(3), y };



	}



	// 画出判断框

	private void drawElectBox() {

		isElected = true;

		Circle circles[] = cBox.getCircles();

		circles[0].setOnMouseDragged(e -> {

			if (circles[7].getCenterX() - e.getX() > 50 + this.getHeight() / (Math.sqrt(3))) {

				this.setWidth(this.getWidth() + circles[0].getCenterX() - e.getX());

				this.setX(e.getX() - circles[0].getCenterX() + this.getX());



			}

			if (circles[7].getCenterY() - e.getY() <= this.getWidth() && circles[7].getCenterY() - e.getY() - 50 > 0) {// 因为平行四边形不对称

				this.setHeight(circles[0].getCenterY() - e.getY() + this.getHeight());

			}



			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);



		});



		circles[1].setOnMouseDragged(e -> {

			if (circles[7].getCenterY() - e.getY() - 50 >= 0 && circles[6].getCenterY() - e.getY() <= this.getWidth()) {

				this.setHeight(this.getHeight() + circles[1].getCenterY() - e.getY());

			}

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);

		});



		circles[2].setOnMouseDragged(e -> {

			if (circles[5].getCenterY() - e.getY() <= this.getWidth() && circles[5].getCenterY() - e.getY() - 50 > 0) {

				this.setHeight(circles[2].getCenterY() - e.getY() + this.getHeight());

			}

			if (e.getX() - circles[0].getCenterX() > 50 + this.getHeight() / Math.sqrt(3)) {

				this.setWidth(e.getX() - circles[2].getCenterX() + this.getWidth());

			}

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);

		});



		circles[3].setOnMouseDragged(e -> {

			if (circles[4].getCenterX() - e.getX() > 50 + this.height) {

				this.setX(e.getX() - circles[3].getCenterX() + this.getX());

				this.setWidth(circles[3].getCenterX() - e.getX() + this.getWidth());

			}

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);

		});



		circles[4].setOnMouseDragged(e -> {

			if (e.getX() - circles[3].getCenterX() > 50 + this.getHeight()) {

				this.setWidth(e.getX() - circles[4].getCenterX() + this.getWidth());

			}

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);

		});



		circles[5].setOnMouseDragged(e -> {

			if (circles[2].getCenterX() - e.getX() > 50 + this.getHeight()) {

				this.setX(e.getX() - circles[5].getCenterX() + this.getX());

				this.setWidth(circles[5].getCenterX() - e.getX() + this.getWidth());

			}

			if (e.getY() - circles[2].getCenterY() <= this.getWidth() && e.getY() - circles[2].getCenterY() - 50 > 0) {

				this.setY(e.getY() - circles[5].getCenterY() + this.getY());

				this.setHeight(e.getY() - circles[5].getCenterY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);

		});



		circles[6].setOnMouseDragged(e -> {

			if (e.getY() - circles[1].getCenterY() - 50 > 0 && e.getY() - circles[1].getCenterY() <= this.getWidth()) {

				this.setY(e.getY() - circles[6].getCenterY() + this.getY());

				this.setHeight(e.getY() - circles[6].getCenterY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);

		});



		circles[7].setOnMouseDragged(e -> {

			if (e.getX() - circles[0].getCenterX() > 50 + this.getHeight()) {

				this.setWidth(e.getX() - circles[7].getCenterX() + this.getWidth());

			}

			if (e.getY() - circles[0].getCenterY() <= this.getWidth() && e.getY() - circles[0].getCenterY() - 50 > 0) {

				this.setY(e.getY() - circles[7].getCenterY() + this.getY());

				this.setHeight(e.getY() - circles[7].getCenterY() + this.getHeight());

			}

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

			updatePoints();

			this.getPoints().addAll(points);

		});

		cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());



	}



	/**

	 * 

	 * Parallelogram构造函数

	 * 

	 * @param x

	 *            平行四边形左下角的x坐标

	 * 

	 * @param y

	 *            平行四边形左下角的y坐标

	 * 

	 * @param width

	 *            平行四边形的长

	 * 

	 * @param height


	 *            平行四边形的宽

	 * 

	 */



	public Parallelogram(double x, double y, double width, double height) {



		this.x = x;



		this.y = y;



		this.width = width;



		this.height = height;



		this.initialize();



		super.getPoints().addAll(points);



	}



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
		text.setLayoutY(this.getY()-this.getHeight()/2+5);
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
//		Parallelogram p = new Parallelogram(x, y, width, height);
//
//		p.updatePoints();
//
//		p.setCursor(Cursor.HAND);
//
//		p.setStroke(Color.BLACK);
//
//		p.setFill(Color.WHITE);
//
//		p.setStrokeWidth(1.3);
//
//		p.showSymbolBorder();
//
//		p.setOnMouseClicked(e -> {
//
//			p.isElected = true;
//
//			p.drawElectBox();
//
//		});
//
//		p.setOnMouseDragged(e -> {
//
//			p.drawElectBox();
//
//			p.setX(e.getX() - p.getWidth() / 2);
//
//			p.setY(e.getY() + p.getHeight() / 2);
//
//			p.updatePoints();
//
//			p.getPoints().addAll(points);
//
//
//
//		});
//
//		return p;
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
