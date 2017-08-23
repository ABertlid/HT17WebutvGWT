package com.exempel.martin.client;

import java.util.ArrayList;
import com.google.gwt.user.client.ui.Button;

public class CalcButton {
	
	public ArrayList<Button> createButton(Handler handler) {
		
		ArrayList<Button> numbers = new ArrayList<Button>();
		String[] numbersOperatorList = new String[] { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0",
				".", "=", "/" };
		for (int i = 0; i < numbersOperatorList.length; i++) {
			Button button = new Button(numbersOperatorList[i]);
			button.addClickHandler(handler);
			button.setPixelSize(50, 50);
			numbers.add(button);
		}
		return numbers;
	}
}
