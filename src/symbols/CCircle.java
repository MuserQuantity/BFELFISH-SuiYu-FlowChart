package symbols;

import java.io.Serializable;
import java.util.ArrayList;

import control.ChooseBox;
import control.MyUtil;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 * 
 * CCircle类继承Circle类
 * 
 * 
 * 
 * @author suisui
 *
 * 
 * 
 */

public class CCircle extends Circle implements Symbol,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6856048953730420051L;
	/**
	 * 
	 */
	private boolean isElected = false; // 选中状态，默认没被选中
	transient private Text text=new Text(""); // 文本框
	private ChooseBox cBox = new ChooseBox();
	private ArrayList<LLine> lines=new ArrayList<LLine>();
	/**
	 * 
	 * 私有函数，初始化图形的属性
	 * 
	 */

	private void initialize() {
		this.setCursor(Cursor.HAND);
		this.setStroke(Color.BLACK);
		this.setFill(Color.WHITE);
		this.setStrokeWidth(1.3);
		this.showSymbolBorder();
		this.setOnMouseClicked(e -> {
			drawElectBox();

		});
		this.setOnMouseDragged(e -> {
			drawElectBox();
			this.setCenterX(e.getX());
			this.setCenterY(e.getY());
		});
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
	 * CCircle构造函数
	 * 
	 * 
	 * 
	 * @param centerX
	 * 
	 *            圆心x坐标
	 * 
	 * @param centerY
	 * 
	 *            圆心y坐标
	 * 
	 * @param radius
	 * 
	 *            圆的半径
	 * 
	 */

	public CCircle(double centerX, double centerY, double radius) {

		super(centerX, centerY, radius);

		initialize();

	}

	/**
	 * 画出操作框
	 */
	private void drawElectBox() {
		isElected = true;

		Circle circles[] = cBox.getCircles();
		circles[0].setOnMouseDragged(e -> {
			this.setCenterX((e.getX() - circles[0].getCenterX()) / 2 + this.getCenterX());
			this.setCenterY(((e.getY() - circles[0].getCenterY())) / 2 + this.getCenterY());
			if (e.getX() < circles[0].getCenterX() || e.getY() < circles[0].getCenterY()) {
				this.setRadius(this.getRadius()
						+ (distance(e.getX(), e.getY(), circles[0].getCenterX(), circles[0].getCenterY())) / 2);
			} else if (e.getX() >= circles[2].getCenterX() || e.getY() >= circles[7].getCenterY()) {
				this.setRadius(0);
			} else {
				this.setRadius(this.getRadius()
						- (distance(e.getX(), e.getY(), circles[0].getCenterX(), circles[0].getCenterY())) / 2);
			}
			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		});

		circles[1].setOnMouseDragged(e -> {
			this.setCenterY(this.getCenterY() + (e.getY() - circles[0].getCenterY()) / 2);
			if (e.getY() < circles[7].getCenterY()) {
				this.setRadius((circles[0].getCenterY() - e.getY()) / 2 + this.getRadius());
			} else {
				this.setRadius(0);
			}
			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);

		});

		circles[2].setOnMouseDragged(e -> {
			this.setCenterX((e.getX() - circles[2].getCenterX()) / 2 + this.getCenterX());
			this.setCenterY((e.getY() - circles[2].getCenterY()) / 2 + this.getCenterY());
			if (e.getX() > circles[2].getCenterX() || e.getY() < circles[2].getCenterY()) {
				this.setRadius(this.getRadius()
						+ (distance(e.getX(), e.getY(), circles[2].getCenterX(), circles[2].getCenterY())) / 2);
			} else if (e.getX() < circles[0].getCenterX() || e.getY() > circles[7].getCenterY()) {
				this.setRadius(0);
			} else {
				this.setRadius(this.getRadius()
						- (distance(e.getX(), e.getY(), circles[2].getCenterX(), circles[2].getCenterY())) / 2);
			}
			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		});

		circles[3].setOnMouseDragged(e -> {
			this.setCenterX((e.getX() - circles[3].getCenterX()) / 2 + this.getCenterX());
			if (e.getX() < circles[4].getCenterX()) {
				this.setRadius((circles[3].getCenterX() - e.getX()) / 2 + this.getRadius());

			} else {
				this.setRadius(0);
			}

			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		});

		circles[4].setOnMouseDragged(e -> {
			this.setCenterX((e.getX() - circles[4].getCenterX()) / 2 + this.getCenterX());
			if (e.getX() > circles[3].getCenterX()) {
				this.setRadius((e.getX() - circles[4].getCenterX()) / 2 + this.getRadius());

			} else {
				this.setRadius(0);
			}
			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		});

		circles[5].setOnMouseDragged(e -> {
			this.setCenterX((e.getX() - circles[5].getCenterX()) / 2 + this.getCenterX());
			this.setCenterY(((e.getY() - circles[5].getCenterY())) / 2 + this.getCenterY());
			if (e.getX() < circles[5].getCenterX() || e.getY() > circles[5].getCenterY()) {
				this.setRadius(this.getRadius()
						+ (distance(e.getX(), e.getY(), circles[5].getCenterX(), circles[5].getCenterY())) / 2);
			} else if (e.getX() > circles[2].getCenterX() || e.getY() < circles[2].getCenterY()) {
				this.setRadius(0);
			} else {
				this.setRadius(this.getRadius()
						- (distance(e.getX(), e.getY(), circles[5].getCenterX(), circles[5].getCenterY())) / 2);
			}
			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		});

		circles[6].setOnMouseDragged(e -> {
			this.setCenterY((e.getY() - circles[6].getCenterY()) / 2 + this.getCenterY());
			if (e.getY() > circles[1].getCenterY()) {
				this.setRadius((e.getY() - circles[6].getCenterY()) / 2 + this.getRadius());
			} else {
				this.setRadius(0);
			}
			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		});

		circles[7].setOnMouseDragged(e -> {
			this.setCenterX((e.getX() - circles[7].getCenterX()) / 2 + this.getCenterX());
			this.setCenterY(((e.getY() - circles[7].getCenterY())) / 2 + this.getCenterY());
			if (e.getX() > circles[7].getCenterX() || e.getY() > circles[7].getCenterY()) {
				this.setRadius(this.getRadius()
						+ (distance(e.getX(), e.getY(), circles[7].getCenterX(), circles[7].getCenterY())) / 2);
			} else if (e.getX() < circles[0].getCenterX() || e.getY() < circles[0].getCenterY()) {
				this.setRadius(0);
			} else {
				this.setRadius(this.getRadius()
						- (distance(e.getX(), e.getY(), circles[7].getCenterX(), circles[7].getCenterY())) / 2);
			}
			cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
					this.getRadius() * 2);
		});
		cBox.draw(this.getCenterX() - this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() * 2,
				this.getRadius() * 2);
	}

	private double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
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

	}

	public ChooseBox getcBox() {

		return cBox;

	}

	public void setcBox(ChooseBox cBox) {

		this.cBox = cBox;

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
//	public Symbol clone() {
//		CCircle circle=new CCircle(this.getCenterX(), this.getCenterY(), this.getRadius());
//		circle.setCursor(Cursor.HAND);
//		circle.setStroke(Color.BLACK);
//		circle.setFill(Color.WHITE);
//		circle.setStrokeWidth(1.3);
//		circle.showSymbolBorder();
//		circle.setOnMouseClicked(e -> {
//			circle.drawElectBox();
//
//		});
//		circle.setOnMouseDragged(e -> {
//			circle.drawElectBox();
//			circle.setCenterX(e.getX());
//			circle.setCenterY(e.getY());
//		});
//		return circle;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTextFieldIsEleted(boolean textFieldIsEleted) {
		
	}

	@Override
	public TextField getT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getX() {
		return this.getLayoutX();
	}

	@Override
	public void setX(double x) {
		this.setLayoutX(x);
	}

	@Override
	public double getY() {
		return this.getLayoutY();
	}

	@Override
	public void setY(double y) {
		this.setLayoutY(y);
	}

	@Override
	public double getWidth() {
		return this.getRadius();
	}

	@Override
	public void setWidth(double width) {
		this.setRadius(width);
	}

	@Override
	public double getHeight() {
		return this.getRadius();
	}

	@Override
	public void setHeight(double height) {
		this.setRadius(height);
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
