package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author Thomas VanDrunen and Tim Kartawijaya
 * CS 245, Wheaton College
 * June 27, 2014
*/
public class SetUp {

	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {

		face.writeToScreen("0");
		Internal internal = new Internal(face);
		
		
		//for number zero
		face.addNumberActionListener(0, new ActionListener() {
			/**
			 * Call insertNumber method in Internal.java when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.numberZero("0");
			}
		});
		
		//the rest of the number buttons
		for (int num = 1; num < 10; num++) {
			final int numButton = num;
			face.addNumberActionListener(num, new ActionListener() {
				/**
				 * Call numberNonZero method in Internal when button is pressed.
				 * @param ae unused
				 */
				public void actionPerformed(ActionEvent ae) {
						internal.numberNonZero(Integer.toString(numButton));
				}
			});
		
		}
		
		//for clear button
		face.addActionListener('C', new ActionListener() {
			/**
			 * Call clear method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.clear();
			}
		});
		
		//for operations
		face.addActionListener('+', new ActionListener() {
			/**
			 * Call plus method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.plus();
			}
		});
		
		face.addActionListener('/', new ActionListener() {
			/**
			 * Call divide method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.divide();
			}
		});
		
		face.addActionListener('-', new ActionListener() {
			/**
			 * Call minus method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.minus();
			}
		});
		
		face.addActionListener('*', new ActionListener() {
			/**
			 * Call multiply method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.multiply();
			}
		});
		
		face.addActionListener('=', new ActionListener() {
			/**
			 * Call equal method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.equal();
			}
		});
		
		//for decimal button
		face.addActionListener('.', new ActionListener() {
			/**
			 * Call decimal method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.decimal();
			}
		});
		
		//for plusminus button
		face.addPlusMinusActionListener(new ActionListener() {
			/**
			 * Call plusminus method in Internal when button is pressed.
			 * @param ae unused
			 */
			public void actionPerformed(ActionEvent ae) {
					internal.plusMinus();
			}
		});
	}
	
	/**
	 * This main method is for your testing of your calculator.
	 * It will *not* be used during grading. Any changes you make
	 * to this method will be ignored at grading.
	 */
	public static void main(String[] args) {
		setUpCalculator(new PlainCalculatorFace());
	}

}
