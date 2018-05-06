package symbols;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.javafx.tk.Toolkit;

import control.ChooseBox;
import control.MyUtil;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * 
 * RRectangle类继承Rectangle 新增数据域：选中状态、文本框、图形类型 新增函数：draw()
 * 
 * 
 * 
 * 
 * @author suisui
 *
 */

public class RRectangle extends Rectangle implements Symbol ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7640731149146732271L;

	private boolean isElected = false; // 选中状态，默认没被选中

	transient private Text text = new Text(""); // 文本框

	private boolean TextFieldIsEleted = false;

	// private double WidthOfText = 0;// 该值记录文本 宽度，用于文本居中

	private ChooseBox cBox = new ChooseBox();

	private ArrayList<LLine> lines = new ArrayList<>();

	private boolean inEnable=true;
	private boolean outEnable=true;
	/**
	 * 
	 * 
	 * 私有函数，初始化图形的属性
	 */
	
	private void initialize() {

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
	 * 鼠标进入图形内时，显示图形边界
	 * 
	 */

	private void showSymbolBorder() {
//		double x=this.getX();
//		double y=this.getY();
//		double w=this.getWidth();
//		double h=this.getHeight();

		this.setOnMouseEntered(e -> {
//			new Circle(x+w/2,y,5).setFill(Color.DARKRED);
//			new Circle(x+w,y-h/2,5).setFill(Color.DARKRED);
//			new Circle(x+w/2,y-h,5).setFill(Color.DARKRED);
//			new Circle(x,y-h/2,5).setFill(Color.DARKRED);
			this.setStroke(Color.DARKRED);
			this.setStrokeWidth(2.0);

		});

		this.setOnMouseExited(e -> {

			this.setStroke(Color.BLACK);

			this.setStrokeWidth(1.3);

		});

	}

	// 画出操作框

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

			//

			// if (e.getY() > circles[1].getCenterY()) {

			//

			// this.setHeight(e.getY() - circles[6].getCenterY() + this.getHeight());

			//

			// }

			LLine l = new LLine(circles[6].getCenterX(), circles[6].getCenterY(), e.getX(), e.getY());

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
	 * RRectangle构造方法
	 */

	public RRectangle() {

		super();

		initialize();

	}

	/**
	 * 
	 * 
	 * RRectangle构造方法
	 * 
	 * @param width
	 * 
	 *            矩形的宽
	 * 
	 * 
	 * @param height
	 *            矩形的长
	 * 
	 * 
	 */

	public RRectangle(double width, double height) {

		super(width, height);

		initialize();

	}

	/**
	 * 
	 * 
	 * RRectangle构造方法
	 * 
	 * 
	 * @param width
	 * 
	 *            矩形的宽
	 * 
	 * @param height
	 *            矩形的长
	 * 
	 * @param text
	 * 
	 *            文本框
	 * 
	 * 
	 */

	public RRectangle(double width, double height, Text text) {

		this(width, height);

		this.text = text;

		initialize();

	}

	/**
	 * 
	 * RRectangle构造方法
	 * 
	 * 
	 * @param x
	 * 
	 * 
	 *            矩形左上角的x坐标
	 * 
	 * @param y
	 * 
	 * 
	 *            矩形左上角的y坐标
	 * 
	 * @param width
	 * 
	 *            矩形的宽
	 * 
	 * 
	 * @param height
	 * 
	 *            矩形的长
	 * 
	 */

	public RRectangle(double x, double y, double width, double height) {

		super(x, y, width, height);

		initialize();

	}

	// getters & setters

	@Override

	public boolean isElected() {

		return isElected;

	}

	@Override

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

	public ArrayList<LLine> getLines() {

		return lines;

	}

	public void setLines(ArrayList<LLine> lines) {

		this.lines = lines;

	}

	@Override
	public TextField getT() {
		// TODO Auto-generated method stub
		return null;
	}

}
