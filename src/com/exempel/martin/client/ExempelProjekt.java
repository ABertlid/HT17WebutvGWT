package com.exempel.martin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ExempelProjekt implements EntryPoint {

	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private VerticalPanel buttonPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel1Row = new HorizontalPanel();
	private HorizontalPanel buttonPanel2Row = new HorizontalPanel();
	private HorizontalPanel buttonPanel3Row = new HorizontalPanel();
	private HorizontalPanel buttonPanel4Row = new HorizontalPanel();
	private HorizontalPanel labelPanel = new HorizontalPanel();
	private FlexTable resultFlexTable = new FlexTable();
	private TextBox firstNumberBox = new TextBox();
	private TextBox secondNumberBox = new TextBox();
	private TextBox operatorBox = new TextBox();
	private Button calcButton = new Button("Calculate");
	private Button removeButton = new Button("Clear");
	private CalcButton calculatorButton = new CalcButton();
	private Label calcLabel = new Label();
	private Handler handler = new Handler(this);

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		setUpFlexTable();

		// Associate the Main panel with the HTML host page.

		RootPanel.get("calc").add(mainPanel);

		// Listen for events
		calcButton.addClickHandler(handler);
		removeButton.addClickHandler(handler);
		firstNumberBox.addKeyDownHandler(handler);
		operatorBox.addKeyDownHandler(handler);
		secondNumberBox.addKeyDownHandler(handler);

		// Move cursor focus to the input box.
		firstNumberBox.setFocus(true);
	}

	private void setUpFlexTable() {

		// create the setup for calcbuttons. Add buttons to four different horizontal
		// panels
		for (int i = 0; i < 4; i++) {
			buttonPanel1Row.add(calculatorButton.createButton(handler).get(i));
		}
		for (int i = 4; i < 8; i++) {
			buttonPanel2Row.add(calculatorButton.createButton(handler).get(i));
		}
		for (int i = 8; i < 12; i++) {
			buttonPanel3Row.add(calculatorButton.createButton(handler).get(i));
		}
		for (int i = 12; i < 16; i++) {
			buttonPanel4Row.add(calculatorButton.createButton(handler).get(i));
		}
		// add elements to the horizontal panel
		addPanel.add(firstNumberBox);
		addPanel.add(operatorBox);
		addPanel.add(secondNumberBox);
		addPanel.add(calcButton);
		addPanel.add(removeButton);

		// add elements to the vertical panel
		buttonPanel.add(calcLabel);
		buttonPanel.add(buttonPanel1Row);
		buttonPanel.add(buttonPanel2Row);
		buttonPanel.add(buttonPanel3Row);
		buttonPanel.add(buttonPanel4Row);

		// mainPanel.add(buttonPanel);
		mainPanel.add(addPanel);
		mainPanel.add(labelPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(resultFlexTable);

		// Create table for visibility and result
		resultFlexTable.setText(0, 0, "First number");
		resultFlexTable.setText(0, 1, "Operator");
		resultFlexTable.setText(0, 2, "Second Number");
		resultFlexTable.setText(0, 3, "Result");

		// Add styles to elements in the result list table
		resultFlexTable.getRowFormatter().addStyleName(0, "resultListHeader");
		resultFlexTable.addStyleName("resultList");
		resultFlexTable.getCellFormatter().addStyleName(0, 0, "resultListColumn");
		resultFlexTable.getCellFormatter().addStyleName(0, 1, "resultListColumn");
		resultFlexTable.getCellFormatter().addStyleName(0, 2, "resultListColumn");
		resultFlexTable.getCellFormatter().addStyleName(0, 3, "resultListColumn");
		addPanel.addStyleName("addPanel");
		mainPanel.addStyleName("mainPanel");
		buttonPanel.addStyleName("buttonPanel");
		firstNumberBox.setName("firstNumberBox");
	}

	public void calcTypeFromBoard(String number) {

		calcLabel.setText(calcLabel.getText() + number);
		int row = resultFlexTable.getRowCount();
		resultFlexTable.setText(row, 0, number);
	}

	public void calculationType() {
		secondNumberBox.setFocus(true);
		final String operator = operatorBox.getText().trim();
		if (!operator.matches("^[\\.+\\.*\\./\\.-]$")) {
			Window.alert("Not valid");
			return;
		}
		double sum;
		double firstNumber = Double.parseDouble(firstNumberBox.getText());
		double secondNumber = Double.parseDouble(secondNumberBox.getText());

		switch (operator) {
		case "+":
			sum = Calculation.calculateAddition(firstNumber, secondNumber);
			updateTable(sum);
			break;
		case "-":
			sum = Calculation.calculateSubtraction(firstNumber, secondNumber);
			updateTable(sum);
			break;
		case "/":
			sum = Calculation.calculateDivision(firstNumber, secondNumber);
			updateTable(sum);
			break;
		case "*":
			sum = Calculation.calculateMultiplication(firstNumber, secondNumber);
			updateTable(sum);
			break;
		default:
			break;

		}

	}

	public void updateTable(double sum) {
		final String number1 = firstNumberBox.getText();
		final String operator = operatorBox.getText();
		final String number2 = secondNumberBox.getText();
		int row = resultFlexTable.getRowCount();

		resultFlexTable.setText(row, 0, number1);
		resultFlexTable.setText(row, 1, operator);
		resultFlexTable.setText(row, 2, number2);

		double divideSum = Double.parseDouble(number1) / Double.parseDouble(number2);
		String sumDoubleDivide = NumberFormat.getFormat("#,##0.00").format(divideSum);

		if (operator == "/") {
			if (number2.equals("0")) {
				String sumError = "Error";
				resultFlexTable.setText(row, 3, sumError);
			} else {
				resultFlexTable.setText(row, 3, String.valueOf(sumDoubleDivide));
			}
		} else {
			resultFlexTable.setText(row, 3, String.valueOf(sum));
		}
	}

	public void clearTextFields() {
		calcLabel.setText("");
		firstNumberBox.setText("");
		operatorBox.setText("");
		secondNumberBox.setText("");
		firstNumberBox.setFocus(true);
	}

	public TextBox getOperatorBox() {
		return operatorBox;
	}
}
