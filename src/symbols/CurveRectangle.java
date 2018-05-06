package symbols;



import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collection;



import java.util.Collections;

import com.sun.javafx.tk.Toolkit;

import control.ChooseBox;
import control.MyUtil;
import javafx.scene.Cursor;

import javafx.scene.control.TextField;

import javafx.scene.effect.Light.Point;

import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;

import javafx.scene.shape.CubicCurve;



import javafx.scene.shape.Path;



import javafx.scene.shape.PathElement;

import javafx.scene.shape.Shape;

import javafx.scene.text.Text;



import javafx.scene.shape.CubicCurveTo;



import javafx.scene.shape.LineTo;



import javafx.scene.shape.MoveTo;;



/**

 * 

 * CurveRectangle继承Path类，曲线多边形

 * 

 * 

 * 

 * @author suisui

 *

 * 

 * 

 */



public class CurveRectangle extends Path implements Symbol,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1984314419478829736L;

	private boolean isElected = false; // 选中状态，默认没被选中

	transient private Text text=new Text(""); // 文本框

	private boolean TextFieldIsEleted = false;

	private double WidthOfText = 0;// 该值记录文本 宽度，用于文本居中

	private double x = 0;

	private double y = 0;

	private double height = 0;

	private double width = 0;

	private ChooseBox cBox = new ChooseBox();

	private ArrayList<LLine> lines = new ArrayList<>();


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



		this.setFill(Color.WHITE);

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

			updatePath();

			this.updateText();

		});

	}

	

	//更新Text的位置

		private void updateText() {

			text.setVisible(false);
			setTextCentered();
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

	 * 私有函数，更新曲线矩形的Path集

	 * 

	 */



	private void updatePath() {



		this.getElements().clear();



		double controlX1 = x + width / 2;



		double controlY1 = y - height / 2;



		double controlX2 = x + width / 2;



		double controlY2 = y + height / 2;



		MoveTo start1 = new MoveTo(x, y);



		LineTo line1 = new LineTo(x, y - height);



		LineTo line2 = new LineTo(x + width, y - height);



		LineTo line3 = new LineTo(x + width, y);



		CubicCurveTo curve = new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, x, y);



		this.getElements().addAll(start1, line1, line2, line3, curve);



	}



	public CurveRectangle(double x, double y, double width, double height) {



		this.x = x;



		this.y = y;



		this.width = width;



		this.height = height;



		initialize();



	}



	// 画出操作框

	private void drawElectBox() {

		this.isElected = true;

		Circle circles[] = this.cBox.getCircles();

		circles[0].setOnMouseDragged(e -> {

			if (e.getX() < circles[7].getCenterX()) {

				this.setX(e.getX() - circles[0].getCenterX() + this.getX());

				this.setWidth(circles[0].getCenterX() - e.getX() + this.getWidth());



			}

			if (e.getY() < circles[7].getCenterY()) {



				this.setHeight(circles[0].getCenterY() - e.getY() + this.getHeight());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());



		});



		circles[1].setOnMouseDragged(e -> {

			if (e.getY() < circles[6].getCenterY()) {



				this.setHeight(circles[1].getCenterY() - e.getY() + this.getHeight());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

		});



		circles[2].setOnMouseDragged(e -> {

			if (e.getX() > circles[5].getCenterX()) {

				this.setWidth(e.getX() - circles[2].getCenterX() + this.getWidth());

			}

			if (e.getY() < circles[5].getCenterY()) {

				this.setHeight(circles[2].getCenterY() - e.getY() + this.getHeight());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

		});



		circles[3].setOnMouseDragged(e -> {

			if (e.getX() < circles[4].getCenterX()) {

				this.setX(e.getX() - circles[3].getCenterX() + this.getX());

				this.setWidth(circles[3].getCenterX() - e.getX() + this.getWidth());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

		});



		circles[4].setOnMouseDragged(e -> {

			if (e.getX() > circles[3].getCenterX()) {

				this.setWidth(e.getX() - circles[4].getCenterX() + this.getWidth());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

		});



		circles[5].setOnMouseDragged(e -> {

			if (e.getX() < circles[2].getCenterX()) {

				this.setX(e.getX() - circles[5].getCenterX() + this.getX());

				this.setWidth(circles[5].getCenterX() - e.getX() + this.getWidth());

			}

			if (e.getY() > circles[2].getCenterY()) {

				this.setY(e.getY() - circles[5].getCenterY() + this.getY());

				this.setHeight(e.getY() - circles[5].getCenterY() + this.getHeight());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

		});



		circles[6].setOnMouseDragged(e -> {

			if (e.getY() > circles[1].getCenterY()) {

				this.setY(e.getY() - circles[6].getCenterY() + this.getY());

				this.setHeight(e.getY() - circles[6].getCenterY() + this.getHeight());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

		});



		circles[7].setOnMouseDragged(e -> {

			if (e.getX() > circles[0].getCenterX()) {

				this.setWidth(e.getX() - circles[7].getCenterX() + this.getWidth());

			}

			if (e.getY() > circles[0].getCenterY()) {

				this.setY(e.getY() - circles[7].getCenterY() + this.getY());

				this.setHeight(e.getY() - circles[7].getCenterY() + this.getHeight());

			}

			updatePath();

			cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

		});

		cBox.draw(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());

	}



	public ChooseBox getcBox() {

		return cBox;

	}



	public boolean isElected() {

		return isElected;

	}



	public void setElected(boolean isElected) {

		this.isElected = isElected;

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



	public double getHeight() {

		return height;

	}



	public void setHeight(double height) {

		this.height = height;

	}



	public double getWidth() {

		return width;

	}



	public void setWidth(double width) {

		this.width = width;

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
//		CurveRectangle cr = new CurveRectangle(x, y, width, height);
//
//		cr.updatePath();
//
//
//
//		cr.setStrokeWidth(1.3);
//
//
//
//		cr.setCursor(Cursor.HAND);
//
//
//
//		cr.setStroke(Color.BLACK);
//
//
//
//		cr.setFill(Color.WHITE);
//
//		cr.showSymbolBorder();
//
//		cr.setOnMouseClicked(e -> {
//
//			cr.drawElectBox();
//
//		});
//
//		cr.setOnMouseDragged(e -> {
//
//			cr.drawElectBox();
//
//			cr.setX(e.getX() - cr.getWidth() / 2);
//
//			cr.setY(e.getY() + cr.getHeight() / 2);
//
//			cr.updatePath();
//
//		});
//
//		return cr;
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


	public Text getText() {

		return text;

	}



	public void setText(Text text) {

		this.text = text;
		setTextCentered();

	}
	
	private void setTextCentered() {
		double width  = Toolkit.getToolkit().getFontLoader().computeStringWidth(text.getText(), text.getFont());
		text.setLayoutX(this.getX()+this.getWidth()/2-width/2);
		text.setLayoutY(this.getY()-this.getHeight()/2);
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
