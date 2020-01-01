package com.asrez.utils;
/**
 *
 * @Name : RationalNumber
 * @File : com/asrez/RationalNumber.java
 * @Version : 1.0
 * @Programmer : Max
 * @Date : 2019-12-25, 2019-12-27
 * @Released under : https://github.com/BaseMax/RationalNumber/blob/master/LICENSE
 * @Repository : https://github.com/BaseMax/RationalNumber
 * @Reference : https://github.com/BaseMax/RationalNumber
 *
 **/
// import org.junit.Test;
// import static org.junit.numeratorssert.*;

public class RationalNumber{
	private int numerator;
	private int denominator=1;

	public void set(int numerator, int denominator, boolean simplify) {
		setNumerator(numerator);
		setDenominator(denominator);
		if(simplify) {
			simplify();
		}
	}
	
	public void set(int numerator, int denominator) {
		set(numerator, denominator, true);
	}

	public void setNumerator(int numerator) {
		this.numerator=numerator;
	}

	public int getNumerator() {
		return this.numerator;
	}
	
	public void setDenominator(int denominator) {
		if(denominator==0) {
			System.out.println("Error: Cannot use zero in bottom line of rational!");
			return;
		}
		if(denominator < 0) {
			this.numerator=-this.numerator;
			this.denominator=-denominator;
		}
		else {
			this.denominator=denominator;
		}
	}

	public int getDenominator() {
		return this.denominator;
	}
	
	public RationalNumber() {
	}
	
	public RationalNumber(int numerator, int denominator, boolean simplify) {
		if(denominator==0) {
			System.out.println("Error: Cannot use zero in bottom line of rational!");
			return;
		}
		if(denominator < 0) {
			this.numerator=-numerator;
			this.denominator=-denominator;
		}
		else {
			this.numerator=numerator;
			this.denominator=denominator;
		}
		if(simplify) {
			simplify();
		}
	}

	public RationalNumber(int numerator, int denominator) {
		this(numerator, denominator, true);
	}

	// http://www.montereyinstitute.org/courses/Algebra1/COURSE_TEXT_RESOURCE/U11_L1_T1_text_final.html
	public void simplify() {
		if(this.numerator == this.denominator) {
			this.numerator=1;
			this.denominator=1;
		}
		else {
			int gcd=findGCD(this.numerator, this.denominator);
			this.numerator=this.numerator / gcd;
			this.denominator=this.denominator / gcd;
		}
	}
	
	public void print() {
		System.out.println(toString());
		// if(this.numerator == this.denominator) {
		//     // System.out.println(this.numerator);
		//     System.out.println(1);
		// }
		// else {
		//     System.out.println(this.numerator + " / " + this.denominator);
		// }
	}
	
	public String toString() {
		if(this.numerator == this.denominator) {
			// System.out.println(this.numerator);
			return("1");
		}
		else {
			return(this.numerator + " / " + this.denominator);
		}
		// return this.numerator + " / " + this.denominator;
	}
	
	public double toDouble() {
		return this.numerator / this.denominator;
	}
	
	public void parseFrom(String input) {
// 		System.out.println(input);
		String[] split = input.split("/");
// 		System.out.println(split.length);
// 		System.out.println(split[0]);
// 		System.out.println(split[1]);
		if(split.length != 2) {
			System.out.println("Error: parseFrom input is not a valid and standard string!");
		}
// 		this.numerator=Integer.parseInt(split[0]);
// 		this.denominator=Integer.parseInt(split[1]);
//      simplify();
		set(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
	}


	public static int findGCD(int numerator, int denominator) {
		if(denominator == 0){
			return numerator;
		}
		return findGCD(denominator, numerator%denominator);
	}
	
	public static int findLCM(int numerator, int denominator) {
		return (numerator*denominator) / findGCD(numerator, denominator);
	}
	
	public void addWith(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		addWith(with);
	}
	
	// http://www.mesacc.edu/~scotz47781/mat120/notes/rational/add_subtract/add_subtract.html
	public void addWith(RationalNumber with) {
		if(this.denominator != with.denominator) {
			int lcm=findLCM(this.denominator, with.denominator);
			int lcmFactor=lcm;
			this.numerator=(this.numerator * (lcmFactor / this.denominator)) + (with.numerator * (lcmFactor / with.denominator));
			// if(this.denominator > with.denominator) {
			//     // lcmFactor/=with.denominator;
			//     this.numerator=(this.numerator * (lcmFactor / this.denominator)) + (with.numerator * (lcmFactor / with.denominator));
			// }
			// else {
			//     this.numerator=(this.numerator * (lcmFactor / this.denominator)) + (with.numerator * (lcmFactor / with.denominator));
			//     // lcmFactor/=this.denominator;
			//     // this.numerator=with.numerator + (this.numerator * lcmFactor);
			// }
			this.denominator=lcm;
		}
		else {
			this.numerator=this.numerator + with.numerator;
		}
		simplify();
	}
	
	
	public void minusWith(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		minusWith(with);
	}
	
	// https://www.symbolab.com/solver/rational-expression-calculator/%5Cfrac%7B1%7D%7B2%7D-%5Cfrac%7B1%7D%7B4%7D
	public void minusWith(RationalNumber with) {
		with.numerator=-with.numerator;
		addWith(with);
	}
	
	public void mulWith(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		mulWith(with);
	}

	// https://mcckc.edu/tutoring/docs/bt/algebra/Multiplying_Rational_Expressions.pdf
	public void mulWith(RationalNumber with) {
		this.numerator=this.numerator * with.numerator;
		this.denominator=this.denominator * with.denominator;
		simplify();
	}
	
	public void divWith(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		divWith(with);
	}
	
	// https://mcckc.edu/tutoring/docs/bt/algebra/Multiplying_Rational_Expressions.pdf
	public void divWith(RationalNumber with) {
		this.numerator=this.numerator * with.denominator;
		this.denominator=this.denominator * with.numerator;
		simplify();
	}
	
	public boolean moreThen(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		return moreThen(with);
	}
	
	public boolean moreThen(RationalNumber with) {
		if(this.denominator == with.denominator) {
			if(this.numerator > with.numerator) {
				return true;
			}
			return false;
		}
		else if(this.denominator > with.denominator) {
			if(this.numerator > with.numerator) {
				return false;
			}
			return true;
		}
		else {
			if(this.numerator > with.numerator) {
				return true;
			}
			return false;
		}
	}

	public boolean lessThen(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		return lessThen(with);
	}
	
	public boolean lessThen(RationalNumber with) {
		if(this.denominator == with.denominator) {
			if(this.numerator < with.numerator) {
				return true;
			}
			return false;
		}
		else if(this.denominator > with.denominator) {
			if(this.numerator < with.numerator) {
				return false;
			}
			return true;
		}
		else {
			if(this.numerator < with.numerator) {
				return true;
			}
			return false;
		}
	}

	public boolean moreEqThen(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		return moreEqThen(with);
	}
	
	public boolean moreEqThen(RationalNumber with) {
		if(this.denominator == with.denominator) {
			if(this.numerator >= with.numerator) {
				return true;
			}
			return false;
		}
		else if(this.denominator > with.denominator) {
			if(this.numerator >= with.numerator) {
				return false;
			}
			return true;
		}
		else {
			if(this.numerator >= with.numerator) {
				return true;
			}
			return false;
		}
	}

	public boolean lessEqThen(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		return lessEqThen(with);
	}
	
	public boolean lessEqThen(RationalNumber with) {
		if(this.denominator == with.denominator) {
			if(this.numerator <= with.numerator) {
				return true;
			}
			return false;
		}
		else if(this.denominator > with.denominator) {
			if(this.numerator <= with.numerator) {
				return false;
			}
			return true;
		}
		else {
			if(this.numerator <= with.numerator) {
				return true;
			}
			return false;
		}
	}

	public boolean eqThen(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		return eqThen(with);
	}
	
	public boolean eqThen(RationalNumber with) {
		// this.simplify();
		// with.simplify();
		if(this.numerator == with.numerator && this.denominator == with.denominator) {
			return true;
		}
		return false;
	}

	public boolean notEqThen(int numerator, int denominator) {
		RationalNumber with=new RationalNumber(numerator, denominator);
		return notEqThen(with);
	}
	
	public boolean notEqThen(RationalNumber with) {
		return !eqThen(with);
	}
	
	public static void asrt(boolean result) {
		asrt(0, result);
	}
	
	public static void asrt(int index, boolean result) {
		// assert (r1.getNumerator()!=1 || r1.getDenominator()!=2) : "Error!";
		if(!result) {
			System.out.println("Error "+index+"!");
		}
	}

	public static void test(){
		// r1.set(2, 12);
		// r1.print();
		// r2.set(1, 4);
		// r2.print();
		// r1.addWith(r2);
		// r1.print();
		
		// System.out.println("----------");

		// r1.minusWith(1, 2);
		// r1.print();

		RationalNumber r;

		r=new RationalNumber(1, 2);
		asrt(1, r.getNumerator() == 1 && r.getDenominator() == 2);

		r=new RationalNumber(2, 4);
		asrt(2, r.getNumerator() == 1 && r.getDenominator() == 2);

		r=new RationalNumber(2, 4, false);
		asrt(3, r.getNumerator() == 2 && r.getDenominator() == 4);

		r.set(2, 4);
		asrt(4, r.getNumerator() == 1 && r.getDenominator() == 2);

		r.set(2, 4, false);
		asrt(5, r.getNumerator() == 2 && r.getDenominator() == 4);

		r.setNumerator(2);
		r.setDenominator(4);
		asrt(6, r.getNumerator() == 2 && r.getDenominator() == 4);
		
		r.simplify();
		asrt(7, r.getNumerator() == 1 && r.getDenominator() == 2);

		// r.setNumerator(2);
		// r.setDenominator(4);
		r.addWith(1, 2);
		asrt(8, r.getNumerator() == 1 && r.getDenominator() == 1);

		// r.setNumerator(2);
		// r.setDenominator(4);
		// r.addWith(1, 2);
		r.addWith(1, 2);
		asrt(9, r.getNumerator() == 3 && r.getDenominator() == 2);

		r.addWith(1, 2);
		// asrt(10, r.getNumerator() == 4 && r.getDenominator() == 2);
		asrt(10, r.getNumerator() == 2 && r.getDenominator() == 1);

		r.addWith(1, 2);
		asrt(11, r.getNumerator() == 5 && r.getDenominator() == 2);

		r.addWith(1, 5);
		asrt(12, r.getNumerator() == 27 && r.getDenominator() == 10);

		r.addWith(18, 5);
		asrt(13, r.getNumerator() == 63 && r.getDenominator() == 10);

		r.addWith(-1, 10);
		// asrt(14, r.getNumerator() == 62 && r.getDenominator() == 10);
		asrt(14, r.getNumerator() == 31 && r.getDenominator() == 5);

		r.addWith(-5, 8);
		asrt(15, r.getNumerator() == 223 && r.getDenominator() == 40);

		r.set(62, 10);
		r.minusWith(5, 8);
		asrt(16, r.getNumerator() == 223 && r.getDenominator() == 40);

		r.addWith(r);
		asrt(17, r.getNumerator() == 223 && r.getDenominator() == 20);

		r.set(2, 5);
		r.mulWith(2, 5);
		asrt(18, r.getNumerator() == 4 && r.getDenominator() == 25);

		r.set(223, 20);
		r.mulWith(2, 5);
		asrt(19, r.getNumerator() == 223 && r.getDenominator() == 50);

		r.set(2, 5);
		r.divWith(2, 5);
		asrt(20, r.getNumerator() == 1 && r.getDenominator() == 1);

		r.set(3, 5);
		r.divWith(4, 5);
		asrt(21, r.getNumerator() == 3 && r.getDenominator() == 4);

		r.set(2, 3);
		r.divWith(5, 6);
		asrt(22, r.getNumerator() == 4 && r.getDenominator() == 5);

		r.set(2, 3);
		asrt(23, !r.moreThen(5, 6));

		r.set(5, 6);
		asrt(24, !r.moreThen(2, 3));

		r.set(2, 3);
		asrt(25, r.lessThen(5, 6));

		r.set(2, 3);
		asrt(26, r.lessEqThen(5, 6));

		r.set(2, 3);
		asrt(27, r.lessEqThen(4, 6));

		r.set(2, 3);
		asrt(28, r.eqThen(4, 6));

		r.set(2, 3);
		asrt(29, !r.eqThen(3, 6));

		r.set(2, 3);
		asrt(30, r.notEqThen(3, 6));

		r.parseFrom("2/3");
		asrt(31, r.getNumerator() == 2 && r.getDenominator() == 3);

		r.parseFrom("5/2");
		asrt(32, r.toDouble() == 5/2);

		r.parseFrom("2/5");
		asrt(32, r.toDouble() == 2/5);

		r.parseFrom("5/5");
		asrt(32, r.toDouble() == 5/5);

		r.parseFrom("6/6");
		asrt(32, r.toDouble() == 1);
	}

	public static void main(String []args){
		System.out.println("Hello World");
		test();
	}
}
