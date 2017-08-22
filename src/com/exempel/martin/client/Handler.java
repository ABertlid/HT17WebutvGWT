package com.exempel.martin.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class Handler implements KeyDownHandler, ClickHandler {
	
	private ExempelProjekt exempelProjekt;
	
	public Handler(ExempelProjekt exempelProjekt) {
		this.exempelProjekt = exempelProjekt;
	}
	
	// Listen for mouse events on the buttons.
	@Override
	public void onClick(ClickEvent event) {
		exempelProjekt.clearTextFields();
		
	}
	// Listen for keyboard events(Enter)in the input from textbox.
	@Override
	public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			exempelProjekt.calculationType();
		}
		
	}

}
