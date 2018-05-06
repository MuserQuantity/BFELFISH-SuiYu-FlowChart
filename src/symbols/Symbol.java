package symbols;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import control.ChooseBox;
import javafx.geometry.Point2D;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public interface Symbol {
	// 图形类是否被选中
	boolean isElected();

	void setElected(boolean isElected);

	boolean isTextFieldIsEleted();

	void setTextFieldIsEleted(boolean textFieldIsEleted);

	public TextField getT();

	public Text getText();

	public void setText(Text text);

	boolean add(Symbol symbol);

	boolean remove(Symbol symbol);

	Symbol getSymbol(int index);

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	double getWidth();

	void setWidth(double width);

	double getHeight();

	void setHeight(double height);

	// 获得被选中图形（除直线外）的操作框
	ChooseBox getcBox();

	// 获得直线的操作框
	Circle[] getCircles();

	Symbol clone();

	ArrayList<LLine> getLines();
	void setLines(ArrayList<LLine> lines);
	
	public ArrayList<LLine> getInLine();

	public void setInLine(LLine line);

	public ArrayList<LLine> getOutLine();

	public void setOutLine(LLine line);

	public boolean canAddInLine();

	public boolean canAddOutLine();

	boolean contains(Point2D p);

	void setStroke(Paint Color);
	void setStrokeWidth(double width);
}
