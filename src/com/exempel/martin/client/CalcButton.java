package com.exempel.martin.client;

import java.util.ArrayList;
import com.google.gwt.user.client.ui.Button;

public class CalcButton {

	public static ArrayList<Button> createButton() {
		ArrayList<Button> numbers = new ArrayList<Button>();
		String[] numbersOperatorList = new String[] { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0",
				"=", "%", "/" };
		for (int i = 0; i < numbersOperatorList.length; i++) {
			numbers.add(new Button(numbersOperatorList[i]));
		}
		return numbers;
	}
}
