package calc;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Internal.java
 * 
 * Class to define an object that will act as the internals of the 
 * calculator, performing functions such as doing operations on numbers, 
 * inserting numbers, and clearing the textfield in the calculator.
 * 
 * @author Tim Kartawijaya
 * 
 * CS 245, Fall 2017
 * Project 7: No-If Calculator 
 * December 5, 2017
 *
 * GRADING NOTE: The negativePre test works with by-hand testing but not by the TestCalcBasic JUnit. 
 * 
 */
public class Internal {
	/**
	 * The window that it controls
	 */
	private CalculatorFace face;
	
	/**
	 * The object to format text in case number gets too large/small
	 */
	private DecimalFormat pattern = new DecimalFormat("#0.##");
	
	/**
	 * String that represents the number shown on the screen
	 */
	private String screenNum = "";
	
	/**
	 * String that represents the number stored in the internals of the calculator,
	 * which will be operated with the next coming number.
	 */
	private String operand = "0";
	
	/**
	 * State of the number field / screen of the calculator
	 */
	private NumFieldState fieldState;

	/**
	 * State that keeps track whether the number field have or don't have
	 * a decimal point.
	 */
	private NumFieldState decimalState;
	
	/**
	 * State that keeps track whether and which operation button was pressed
	 */
	private OperatorState operatorState;
	
	/**
	 * State whether or not its a negative/positive number
	 */
	private PlusMinusState plusMinusState;
	
	/**
	 * Constructor
	 * @param face the window that it controls
	 */
	public Internal(CalculatorFace face) {
		this.face = face; 
		this.fieldState = new EmptyFieldState();
		this.operatorState = new NoOperatorState(); 
		this.plusMinusState = new PositiveState();
		this.decimalState = new NoDecimalState();
	}
	
	/**
	 * Act on nonzero buttons
	 * @param num
	 */
	public void numberNonZero(String num) {
		fieldState.numPress(num); 
	}
	
	/**
	 * Act on number zero button
	 * @param num
	 */
	public void numberZero(String num) {
		fieldState.numZeroPress();
	}
	
	/**
	 * Act on plusminus button
	 */
	public void plusMinus() {
		plusMinusState.plusMinusPress();
	}
	
	/**
	 * Act on addition button
	 */
	public void plus() {
		operatorState.plusPress();
	}
	
	/**
	 * Act on divide button
	 */
	public void divide() {
		operatorState.dividePress();
	}
	
	/**
	 * Act on subtract button
	 */
	public void minus() {
		operatorState.minusPress();
	}
	
	/**
	 * Act on multiply button
	 */
	public void multiply() {
		operatorState.multiplyPress();
	}
	
	/**
	 * Act on equal button
	 */
	public void equal() {
		operatorState.equalPress();
	}
	
	/**
	 * Act on decimal button
	 */
	public void decimal() {
		fieldState.decimalPress(); //for the case if the equals button was pressed
		decimalState.decimalPress();
	}
	
	/**
	 * Acts on clear button. resetAndStores all states and screen.
	 */
	public void clear() {
		face.writeToScreen("0");
		fieldState = new EmptyFieldState();
		operatorState = new NoOperatorState();
	}
	
	/**
	 * State class that keeps track of what operator has been pressed.
	 * @author Tim Kartawijaya
	 *
	 */
	private abstract class OperatorState{
		/**
		 * Method if the plus button was pressed in this state
		 */
		public abstract void plusPress();
		/**
		 * Method if the minus button was pressed in this state
		 */
		public abstract void minusPress();
		/**
		 * Method if the multiply button was pressed in this state
		 */
		public abstract void multiplyPress();
		/**
		 * Method if the equal button was pressed in this state
		 */
		public abstract void equalPress();
		/**
		 * Method if the divide button was pressed in this state
		 */
		public abstract void dividePress();
		/**
		 * resetAndStores the first number stored in the calculator and several states 
		 * to properly store the operand for the next operation.
		 * @param computed The number that was computed after an operation
		 */
		protected void resetAndStore(String computed) {
			operand = computed; //store number for next operation
			face.writeToScreen(pattern.format(Double.parseDouble(computed))); //prints computed in decimal format with given pattern
			fieldState = new EmptyFieldState();
			decimalState = new NoDecimalState();
			plusMinusState = new PositiveState();
			screenNum = "0";
		}
	}
	
	/**
	 * State class that tracks if the number field is already filled or not, and whether
	 * or not a decimal point exists on the field.
	 * @author Tim Kartawijaya
	 */
	private abstract class NumFieldState {
		
		/**
		 * Method if a nonzero button was pressed
		 * @param num The number that was pressed
		 */
		public abstract void numPress(String num);
		/**
		 * Method if a zero button was pressed
		 */
		public abstract void numZeroPress();
		/**
		 * Method if a decimal button was pressed
		 */
		public abstract void decimalPress();
	}
	
	/**
	 * State class that tracks if the plus minus button was pressed or not
	 * @author Tim Kartawijaya
	 */
	private abstract class PlusMinusState{
		/**
		 * Method if plus minus button was pressed
		 */
		public abstract void plusMinusPress();
	}
	
	/**
	 * State class for when a field is empty
	 * @author Tim Kartawijaya
	 */
	private class EmptyFieldState extends NumFieldState{
		/**
		 * Prints the number then store the number to first num. Move to next state
		 */
		public void numPress(String num) {
			face.writeToScreen(num);
			screenNum = num;
			fieldState = new FilledFieldState();
		}
		/**
		 * Do nothing and print 0 to screen
		 */
		public void numZeroPress() { face.writeToScreen("0"); }
		/**
		 * To handle case for calculation after the equals button is pressed
		 */
		public void decimalPress() { screenNum = "0";};
	}
	
	/**
	 * State class for when a field is filled
	 * @author Tim Kartawijaya
	 */
	private class FilledFieldState extends NumFieldState {
		/**
		 * When number pressed, concatenate the number to the number already
		 * on the screen.
		 */
		public void numPress(String num) { 
			if (screenNum.length() == 15) return;//do nothing if number field is filled
			screenNum = screenNum + num;
			face.writeToScreen(screenNum);
		}
		public void numZeroPress() {	numPress("0");}
		public void decimalPress() {}
	}
	
	/**
	 * State class for when no operator was pressed before
	 * @author Tim Kartawijaya
	 */
	private class NoOperatorState extends OperatorState {
		/**
		 * Store first number and move to the next state, getting ready for the operand input
		 */
		public void plusPress() { resetAndStore(screenNum); operatorState = new PlusOperatorState(); }
		public void minusPress() { resetAndStore(screenNum); operatorState = new MinusOperatorState(); }
		public void multiplyPress() { resetAndStore(screenNum); operatorState = new MultiplyOperatorState(); }
		public void dividePress() { resetAndStore(screenNum); operatorState = new DivideOperatorState(); }
		public void equalPress() {}
	}
	
	/**
	 * State class for addition operator
	 * @author Tim Kartawijaya
	 *
	 */
	private class PlusOperatorState extends OperatorState {
		/**
		 * Helper method to compute addition
		 */
		private String compute() {
			BigDecimal firstNumBig = new BigDecimal(operand);
			BigDecimal secondNumBig = new BigDecimal(screenNum);
			String computed = (firstNumBig.add(secondNumBig).toPlainString());
			resetAndStore(computed);
			return computed;
		}
		/**
		 * Computes value and move on to next state.
		 */
		public void plusPress() { compute();} 
		public void minusPress() { compute(); operatorState = new MinusOperatorState(); }
		public void multiplyPress() { compute(); operatorState = new MultiplyOperatorState();}
		public void dividePress() { compute(); operatorState = new DivideOperatorState(); }
		public void equalPress() { screenNum = compute(); operatorState = new NoOperatorState(); }
	}
	
	/**
	 * State class for subtraction operator
	 * @author Tim Kartawijaya
	 *
	 */
	private class MinusOperatorState extends OperatorState {
		/**
		 * Helper method to compute division
		 */
		private String compute() {
			BigDecimal firstNumBig = new BigDecimal(operand);
			BigDecimal secondNumBig = new BigDecimal(screenNum);
			String computed = (firstNumBig.subtract(secondNumBig).toPlainString());
			resetAndStore(computed);
			return computed;
		}
		
		/**
		 * Computes value and move on to next state.
		 */
		public void plusPress() { compute(); operatorState = new PlusOperatorState(); } 
		public void minusPress() { compute(); }
		public void multiplyPress() { compute(); operatorState = new MultiplyOperatorState();}
		public void dividePress() { compute(); operatorState = new DivideOperatorState(); }
		public void equalPress() { screenNum = compute(); operatorState = new NoOperatorState(); }
	}
	
	/**
	 * State class for multiplication operator
	 * @author Tim Kartawijaya
	 *
	 */
	private class MultiplyOperatorState extends OperatorState {
		/**
		 * Helper method to compute division
		 * @return The number that was just computed
		 */
		private String compute() {
			BigDecimal firstNumBig = new BigDecimal(operand);
			BigDecimal secondNumBig = new BigDecimal(screenNum);
			String computed = (firstNumBig.multiply(secondNumBig).toPlainString());
			resetAndStore(computed);
			return computed;
		}
	
		/**
		 * Computes value and move on to next state.
		 */
		public void plusPress() { compute(); operatorState = new PlusOperatorState(); } 
		public void minusPress() { compute(); operatorState = new MinusOperatorState(); }
		public void multiplyPress() { compute();}
		public void dividePress() { compute(); operatorState = new DivideOperatorState(); }
		public void equalPress() { screenNum = compute(); operatorState = new NoOperatorState(); }
	}
	
	/**
	 * State class for division operator
	 * @author Tim Kartawijaya
	 *
	 */
	private class DivideOperatorState extends OperatorState {
		
		/**
		 * Helper method to compute division
		 */
		private String compute() {
			//case for division by zero
			if (Double.parseDouble(screenNum) == 0) {
				face.writeToScreen("Error");
				fieldState = new EmptyFieldState();
				operatorState = new NoOperatorState();
				return "0";
			}
			BigDecimal firstNumBig = new BigDecimal(operand);
			BigDecimal secondNumBig = new BigDecimal(screenNum);
			String computed = (firstNumBig.divide(secondNumBig).toPlainString());
			resetAndStore(computed);
			return computed;
		}
		/**
		 * Computes value, prints the value to screen, stores value as the next first operand and resetAndStores the field state
		 */
		public void plusPress() { compute(); operatorState = new PlusOperatorState(); }
		public void minusPress() { compute(); operatorState = new MinusOperatorState(); }
		public void multiplyPress() { compute(); operatorState = new MultiplyOperatorState(); }
		public void dividePress() { compute();}
		public void equalPress() { screenNum = compute(); operatorState = new NoOperatorState();}
	}
	
	/**
	 * State class for if the number on calculator is negative
	 * @author Tim Kartawijaya
	 */
	private class NegativeState extends PlusMinusState {
		/**
		 * Remove negative sign and change state
		 */
		public void plusMinusPress() {
			screenNum = screenNum.replace("-", "");
			face.writeToScreen(screenNum);
			plusMinusState = new PositiveState();
		}
	}
	
	/**
	 * State class for if the number on calculator is positive
	 * @author Tim Kartawijaya
	 */
	private class PositiveState extends PlusMinusState {
		/**
		 * Add negative sign 
		 */
		public void plusMinusPress() {
			screenNum = "-" + screenNum;
			face.writeToScreen(screenNum);
			plusMinusState = new NegativeState();
			fieldState = new FilledFieldState();//for negativePre case
		}
	}
	
	/**
	 * State class if number contains no decimal points
	 * @author Tim Kartawijaya
	 */
	private class NoDecimalState extends NumFieldState {
		public void numPress(String num) {}
		public void numZeroPress() {}
		/**
		 * Adds decimal point to screen number
		 */
		public void decimalPress() { 
			screenNum += "."; 
			face.writeToScreen(screenNum);
			fieldState = new FilledFieldState(); //in case of 0.
			decimalState = new ExistDecimalState(); }
	}
	
	/**
	 * State class if number contains decimal points
	 * @author Tim Kartawijaya
	 */
	private class ExistDecimalState extends NumFieldState {
		public void numPress(String num) {}
		public void numZeroPress() {}
		public void decimalPress() {}
	}
	
	
}
