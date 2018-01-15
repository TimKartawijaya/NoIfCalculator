package test;


import org.junit.Test;

import calc.CalculatorFace;

public class TestCalcBasic extends TestCalcAbs {

    @Test
    public void singleDigit() {
        testSequence("5", new String[] {"5"});
    }
    
    @Test
    public void tripleDigit() {
        testSequence("213", new String[] {"213"});
    }
    
    @Test
    public void decimal() {
        testSequence("12.7", new String[] {"12.7"});
    }
    
    @Test
    public void negativeMultiple() {
        testSequence("1" + CalculatorFace.PLUS_MINUS + CalculatorFace.PLUS_MINUS + CalculatorFace.PLUS_MINUS + "5", new String[] {"-15","-15.0"});
    }
    
    @Test
    public void negativePre() {
        testSequence(CalculatorFace.PLUS_MINUS + "15", new String[] {"-15","-15.0"});
    }
    
    @Test
    public void negativeMid() {
        testSequence("1" + CalculatorFace.PLUS_MINUS + "5", new String[] {"-15","-15.0"});
    }

    @Test
    public void negativePost() {
        testSequence("15" + CalculatorFace.PLUS_MINUS, new String[] {"-15"});
    }

    @Test
    public void addEq() {
        testSequence("1+2=", new String[] {"3", "3.0"});
    }
    
    @Test
    public void addOp() {
        testSequence("1+2+", new String[] {"3", "3.0"});
    }
    
    @Test
    public void addThreeNotFinished() {
        testSequence("1+2+4", new String[] {"4", "4.0"});
    }
    
    @Test
    public void addThreeEq() {
        testSequence("1+2+4=", new String[] {"7", "7.0"});
    }

    @Test
    public void clear() {
        testSequence("1+5C4+9=", new String[] {"13", "13.0"});
    }
    

    //Minus Tests 
    @Test
    public void minusEq() {
        testSequence("5-3=", new String[] {"2", "2.0"});
    }
    
    @Test
    public void minusOp() {
        testSequence("5-3-", new String[] {"2", "2.0"});
    }
    
    @Test
    public void minusThreeNotFinished() {
        testSequence("5-3-2", new String[] {"2", "2.0"});
    }
    
    @Test
    public void minusThreeEq() {
        testSequence("5-3-2=", new String[] {"0", "0.0"});
    }
    
    @Test
    public void minusNegativeEq() {
        testSequence("5-6=", new String[] {"-1", "-1.0"});
    }
    
    @Test
    public void minusNegativeOp() {
        testSequence("5-6-", new String[] {"-1", "-1.0"});
    }
    
    @Test
    public void minusNegativeThreeEq() {
        testSequence("5-3-6=", new String[] {"-4", "-4.0"});
    }

    //Multiply Tests
    @Test
    public void multiplyEq() {
        testSequence("3*3=", new String[] {"9", "9.0"});
    }
    
    @Test
    public void multiplyOp() {
        testSequence("3*3*", new String[] {"9", "9.0"});
    }
    
    @Test
    public void multiplyThreeNotFinished() {
        testSequence("3*2*1", new String[] {"1", "1.0"});
    }
    
    @Test
    public void multiplyThreeEq() {
        testSequence("3*2*5=", new String[] {"30", "30.0"});
    }
    
    //Division Tests
    @Test
    public void divisionEq() {
        testSequence("4/2=", new String[] {"2", "2.0"});
    }
    
    @Test
    public void divisionOp() {
        testSequence("10/5/", new String[] {"2", "2.0"});
    }
    
    @Test
    public void divisionThreeNotFinished() {
        testSequence("20/5/2", new String[] {"2", "2.0"});
    }
    
    @Test
    public void divisionThreeEq() {
        testSequence("20/5/2=", new String[] {"2", "2.0"});
    }
    
    //Mix Tests
    @Test
    public void mixedEq() {
        testSequence("2+2*6/4-2=", new String[] {"4", "4.0"});
    }
    
    @Test
    public void decimalMultipleEquals() {
        testSequence("2+2=-3=*6.1*", new String[] {"6.1", "6.1"});
    }
    
    @Test
    public void decimalPlus() {
        testSequence("2.1+2.1=", new String[] {"4.2", "4.2"});
    }
    
    @Test
    public void decimalMinus() {
        testSequence("3.1-2.1=", new String[] {"1", "1.0"});
    }
    
    @Test
    public void decimalMultiply() {
        testSequence("3.1*2.0=", new String[] {"6.2", "6.2"});
    }
    
    @Test
    public void decimalDivide() {
        testSequence("5.5/5.0=1.1", new String[] {"1.1", "1.1"});
    }
    
    @Test
    public void decimalMultiplePeriod() {
        testSequence("5......5....5....5", new String[] {"5.555", "5.555"});
    }
    
    @Test
    public void decimalBefore() {
        testSequence(".123", new String[] {"0.123", "0.123"});
    }
    
    @Test
    public void decimalPreCalc() {
        testSequence("12+.3=", new String[] {"12.3", "12.3"});
    }
    
    @Test
    public void decimalPostCalc() {
        testSequence("12+3=.2", new String[] {"0.2", "0.2"});
    }
    
    @Test
    public void decimalPostCalcAgain() {
        testSequence("12+3=+.3=", new String[] {"15.3", "15.3"});
    }
    
    
    @Test
    public void decimalWithNon() {
        testSequence("3.5+3=6.5", new String[] {"6.5", "6.5"});
    }
    
    
    @Test
    public void multipleEquals() {
        testSequence("2+2=-3=*6=", new String[] {"6", "6.0"});
    }
    
    @Test
    public void divideDecimalByZero() {
        testSequence("5.3/0=", new String[] {"Error", "Error"});
    }
    
    @Test
    public void largeNumber() {
        testSequence("1234567890123456", new String[] {"123456789012345"});
    }
}
