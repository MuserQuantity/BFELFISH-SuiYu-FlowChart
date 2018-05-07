package view;

import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import javax.imageio.ImageIO;

import control.MyUtil;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import memento.Caretaker;
import memento.Memento;
import symbols.CCircle;
import symbols.CurveRectangle;
import symbols.Diamond;
import symbols.LLine;
import symbols.Parallelogram;
import symbols.RRectangle;
import symbols.RoundRectangle;
import symbols.Symbol;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BorderPaneController {
	@FXML
	private Pane pane;
	@FXML
	private Rectangle rectangle; // 矩形
	@FXML
	private Rectangle roundRect; // 圆角矩形
	@FXML
	private Path diamond; // 菱形
	@FXML
	private Path parallelogram; // 平行四边形
	@FXML
	private Line line; // 实线
	@FXML
	private Circle circle; // 圆
	@FXML
	private Path curveRect; // 曲线矩形

	private boolean isElectedSymbols = false;// 当前是否有被选中的元素
	private Symbol elctedSymbol = null;
	private TextField textBox = new TextField("");
	private LinkedList<Symbol> symbolList = new LinkedList<Symbol>();
	private LinkedList<Shape> boxList = new LinkedList<Shape>();
	private LinkedList<Text> textList = new LinkedList<Text>();
	private LinkedList<LLine> lineList = new LinkedList<LLine>();
	private Caretaker caretaker = new Caretaker();

	@FXML
	private void drawRect() {
		textBox.setVisible(false);
		double width = 100;
		double height = 40;
		double x = (pane.getPrefWidth() - width) / 2;
		double y = (pane.getPrefHeight() - height) / 2;
		RRectangle rect = new RRectangle(x, y, width, height);
		// symbolList.add(rect.clone());
		symbolList.add(rect);
		// caretaker.add(new Memento(symbolList));
		// pane.getChildren().add(rect);
		repaint();
	}

	@FXML
	private void drawRoundRect() {
		textBox.setVisible(false);
		double width = 100;
		double height = 40;
		double x = (pane.getPrefWidth() - width) / 2;
		double y = (pane.getPrefHeight() - height) / 2;
		RoundRectangle roundRect = new RoundRectangle(x, y, width, height);
		// symbolList.add(roundRect.clone());
		symbolList.add(roundRect);
		// caretaker.add(new Memento(symbolList));
		// pane.getChildren().add(roundRect);
		repaint();
	}

	@FXML
	private void drawDiamond() {
		textBox.setVisible(false);
		double width = 100.0;
		double height = 50.0;
		double x = (pane.getPrefWidth() - width) / 2;
		double y = (pane.getPrefHeight() - height) / 2;
		Diamond d = new Diamond(x, y, width, height);
		// symbolList.add(d.clone());
		symbolList.add(d);
		// caretaker.add(new Memento(symbolList));
		// pane.getChildren().add(d);
		repaint();
	}

	@FXML
	private void drawParallelogram() {
		textBox.setVisible(false);
		double width = 115.0;
		double height = 40.0;
		double x = (pane.getPrefWidth() - width) / 2;
		double y = (pane.getPrefHeight() - height) / 2;
		Parallelogram p = new Parallelogram(x, y, width, height);
		// symbolList.add(p.clone());
		symbolList.add(p);
		// caretaker.add(new Memento(symbolList));
		// pane.getChildren().add(p);
		repaint();
	}

	@FXML
	private void drawLine() {
		textBox.setVisible(false);
		double startX = (pane.getPrefWidth() - 100) / 2;
		double startY = (pane.getPrefHeight() - 100) / 2;
		double length = 150;
		double endX = startX + length;
		double endY = startY;
		LLine l = new LLine(startX, startY, endX, endY);
		// symbolList.add(l.clone());
		symbolList.add(l);
		// caretaker.add(new Memento(symbolList));
		// pane.getChildren().add(l);
		repaint();
	}

	private void drawLine1(Symbol symbol) {
		lineList.addAll(symbol.getLines());
		repaint();
	}

	@FXML
	private void drawCircle() {
		textBox.setVisible(false);
		double radius = 10;
		double centerX = (pane.getPrefWidth() - radius) / 2;
		double centerY = (pane.getPrefHeight() - radius) / 2;
		CCircle c = new CCircle(centerX, centerY, radius);
		// symbolList.add(c.clone());
		symbolList.add(c);
		// caretaker.add(new Memento(symbolList));
		// pane.getChildren().add(c);
		repaint();

	}

	@FXML
	private void drawCurveRect() {
		textBox.setVisible(false);
		double width = 120.0;
		double height = 50.0;
		double startX = (pane.getPrefWidth() - width) / 2;
		double startY = (pane.getPrefHeight() - height) / 2;
		CurveRectangle cr = new CurveRectangle(startX, startY, width, height);
		// symbolList.add(cr.clone());
		symbolList.add(cr);
		// caretaker.add(new Memento(symbolList));
		// pane.getChildren().add(cr);
		repaint();
	}

	@FXML
	private void paneClicked(MouseEvent e) {
		textBox.setVisible(false);
		double x = e.getX();
		double y = e.getY();
		int index = findClickedElement(new Point2D(x, y));
		isElectedSymbols = false;
		if (index == -1) {// 没有被选中的图形
			isElectedSymbols = false;
			// 清除所有操作框
			boxList.clear();
			pane.getChildren().clear();
			textBox.setVisible(false);
			for (Symbol symbol : symbolList) {
				symbol.setElected(false);
				symbol.setTextFieldIsEleted(false);
			}
			repaint();
		} else if (index == -2) {// 选中了操作框，当前处于操作阶段
			textBox.setVisible(false);
			if (isElectedSymbols) {
				boxList.clear();
				drawBox(symbolList.get(index));
			}
			isElectedSymbols = true;
		} else {// 选中了图形
			elctedSymbol = symbolList.get(index);
			if (!isElectedSymbols) {
				boxList.clear();
				drawBox(elctedSymbol);
				if (e.getClickCount() == 2) {
					textBox.setVisible(true);
					elctedSymbol.setTextFieldIsEleted(true);
					drawTextField(elctedSymbol);
					elctedSymbol.setTextFieldIsEleted(false);
				}
			}
			isElectedSymbols = true;
		}
		// caretaker.add(new Memento(symbolList));
		repaint();
	}


	private void drawBox(Symbol symbol) {
		if (symbol.isElected() && symbol instanceof LLine) {
			Circle circles[] = symbol.getCircles();
			for (Circle c : circles) {
				boxList.add(c);
			}
		} else if (symbol.isElected()) {
			Line line[] = symbol.getcBox().getcBox();
			for (Line l : line) {
				boxList.add(l);
			}
			Circle circles[] = symbol.getcBox().getCircles();
			for (Circle c : circles) {
				boxList.add(c);
			}
		}
	}

	// 画出TextField
	private void drawTextField(Symbol symbol) {
		if (symbol.isTextFieldIsEleted()) {
			// 初始化
			textBox.setPrefHeight(symbol.getHeight());
			if (symbol instanceof RRectangle || symbol instanceof RoundRectangle) {
				textBox.setLayoutX(symbol.getX() + symbol.getWidth() / 40);
				textBox.setLayoutY(symbol.getY());
				textBox.setPrefWidth(symbol.getWidth() - 2 * symbol.getWidth() / 40);

			} else if (symbol instanceof Parallelogram || symbol instanceof CurveRectangle) {
				textBox.setLayoutX(symbol.getX() + symbol.getWidth() / 10);
				textBox.setLayoutY(symbol.getY() - symbol.getHeight());
				textBox.setPrefWidth(symbol.getWidth() - 2 * symbol.getWidth() / 10);

			} else if (symbol instanceof Diamond) {
				textBox.setLayoutX(symbol.getX() + symbol.getWidth() / 10);
				textBox.setLayoutY(symbol.getY() - symbol.getHeight() / 2);
				textBox.setPrefWidth(symbol.getWidth() - 2 * symbol.getWidth() / 10);
			} else if (symbol instanceof LLine) {
				textBox.setLayoutX(symbol.getX());
				textBox.setLayoutY(symbol.getY() - 20);
				textBox.setPrefWidth(10);
			} else {
				textBox.setVisible(false);
			}

			textBox.setText("");
			if (symbol.getText().getText() != "") {// 使得新出现的TextField依旧是原来的文本
				textBox.setText(symbol.getText().getText());
			} else {
				textBox.setPromptText("请输入文字");
			}

			// 为TextBox添加键盘监听
			textBox.setOnKeyReleased(e -> {
				Text buf = new Text(textBox.getText());
				symbol.setText(buf);
			});
			repaint();
			textBox.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					textBox.setVisible(false);
					Text buf = new Text(textBox.getText());
					symbol.setText(buf);
				}
			});
			repaint();
		}
	}

	// 寻找鼠标所在位置是否点中某个图形节点
	private int findClickedElement(Point2D p) {
		for (int i = 0; i < boxList.size(); i++) {
			if (boxList.get(i).contains(p)) {
				return -2;
			}
		}
		for (int i = 0; i < symbolList.size(); i++) {
			if (symbolList.get(i).contains(p)) {
				return i;
			}
		}
		return -1;

	}

	private LinkedList<Symbol> symbolListClone(LinkedList<Symbol> symbols) {
		LinkedList<Symbol> buf = new LinkedList<Symbol>();
		int index = 0;
		for (Symbol symbol : symbols) {
			// System.out.println("symbolListClone方法");
			// System.out.println(symbol);
			// System.out.println(symbol.clone());
			buf.add(index, symbol.clone());
			index++;
		}
		return buf;
	}

	/**
	 * 撤销
	 * 
	 * @throws Exception
	 */
	@FXML
	private void undo() {
		// for (Symbol symbol : symbolList) {
		// System.out.println(symbol);
		// }
		// System.out.println(symbolList);
		LinkedList<Symbol> buf = symbolListClone(caretaker.getUndo());
		symbolList = buf;

		// System.out.println("撤销后：");
		// for (Symbol symbol : symbolList) {
		// System.out.println(symbol);
		// }
		isElectedSymbols = false;
		boxList.clear();
		elctedSymbol = null;

		repaint();
	}

	/**
	 * 前进
	 */
	@FXML
	private void redo() {
		LinkedList<Symbol> buf = caretaker.getRedo();
		symbolList.clear();
		int index = 0;
		for (Symbol symbol : buf) {
			symbolList.set(index, symbol.clone());
			index++;
		}
		// for (Symbol symbol : symbolList) {
		// System.out.println(symbol);
		// }
		// System.out.println(symbolList);
		isElectedSymbols = false;
		boxList.clear();
		elctedSymbol = null;
		repaint();
	}

	/**
	 * 删除
	 * 
	 * @param KeyEvent
	 * 
	 */
	@FXML
	private void delete(KeyEvent k) {
		boolean flag = false;
		boolean allElected = false;
		if (k.isControlDown() && k.getCode() == KeyCode.A) {
			System.out.println("quan xuan");
			for (Symbol symbol : symbolList) {
				symbol.setElected(true);
				
			}
			repaint();
			allElected = true;
		}

		System.out.println(k.getCode());
		if (k.getCode() == KeyCode.SHIFT) {
			boxList.clear();
			symbolList.remove(elctedSymbol);
			repaint();
		}

	}

	private void repaint() {
		caretaker.add(new Memento(symbolListClone(symbolList)));
		pane.getChildren().clear();
		pane.getChildren().addAll(boxList);
		pane.getChildren().addAll(lineList);
		for (Symbol symbol : symbolList) {
			// System.out.println("repaint方法");
			// System.out.println(symbol);
			pane.getChildren().add((Shape) symbol);
			if (symbol.getText() != null)
				pane.getChildren().add(symbol.getText());
		}
		if (textBox != null)
			pane.getChildren().add(textBox);
	}
	@FXML
    public void screenshot(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fileChooser.showSaveDialog(null);
        WritableImage snapshot=pane.snapshot(null,null);
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null), "png", file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
