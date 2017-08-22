package com.exempel.martin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

public class Handler implements KeyDownHandler, ClickHandler {

	private ExempelProjekt exempelProjekt;
	private CalcButton calcButton;

	public Handler(ExempelProjekt exempelProjekt) {
		this.exempelProjekt = exempelProjekt;
	}

	public Handler(CalcButton calcButton) {
		this.calcButton = calcButton;
	}

	// Listen for mouse events on the buttons.
	@Override
	public void onClick(ClickEvent event) {
		Button button = (Button) event.getSource();
		Object object = event.getSource();
		if (object instanceof Button) {
			button = (Button) object;
			if (button.getText().equals("Calculate")) {
				exempelProjekt.calculationType();
			} else if (button.getText().equals("Clear")) {
				exempelProjekt.clearTextFields();
			} else {
				GWT.log(button.getText());
			}

		}

	}

	// Listen for keyboard events(Enter)in the input from textbox.
	@Override
	public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			if (((TextBox) event.getSource()).getName().equals("firstNumberBox")) {
				exempelProjekt.getOperatorBox().setFocus(true);
			} else {
				exempelProjekt.calculationType();
			}
		}

	}

}
