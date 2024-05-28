package StatsLibrary;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;

// Homework 1
public class StatsLibrary {
	
	public void runProgram() {
		// find mean/median (3.0) of odd numbered array
		int [] num = {1, 2, 3, 4, 5};
		
		// find median (3.5) of even numbered array
		int [] num2 = {1, 2, 3, 4, 5, 6};
		
		// find mode (2.0) when 2 shows up twice
		int [] num3 = {1, 2, 2, 3, 5, 6};
		
		// find mode (3.0) when 3 shows up thrice and 5 shows up twice
		int [] num4 = {1, 3, 3, 3, 5, 5, 6};
		
		// find the standard deviation (example problems from class)
		int [] example1 = {46, 69, 32, 60, 52, 41};
		int [] example2 = {19, 21, 35, 18, 37};
		
		System.out.println("Mean of num: " + mean(num));
		System.out.println("Median of num: " + median(num));
		System.out.println("Median of num2: " + median(num2));
		System.out.println("Mode of num3: " + mode(num3));
		System.out.println("Mode of num4: " + mode(num4));
		System.out.println("Variance of example1: " + variance(example1));
		System.out.println("Standard Deviation of example1: " + standardDeviation(example1));
		System.out.println("Variance of example2: " + variance(example2));
		System.out.println("Standard Deviation of example2: " + standardDeviation(example2) + "\n");
		// practice with problems in textbook
		System.out.println("Example 2.8 (n = 30, r = 3): " + permutations(30, 3));
		System.out.println("Example 2.11 (n = 5, r = 2): " + combinations(5, 2));
		System.out.println("Example 2.9 (n, r = 4): " + permutations(4, 4));
		System.out.println("Exercise 2.37a: " + factorial(6));
		System.out.println("Exercise 2.37b: " + (1/combinations(2, 1)) + "\n");
		System.out.println("Binomial Distribution Example 1: " + binomialDistribution(10, 0.80, 0.20, 7));
		System.out.println("Binomial Distribution Example 2: " + binomialDistribution(5, 0.75, 0.25, 3));
		System.out.println("Geometric Distribution Example 1: " + geometricDistribution((1-0.45), 0.45, 3)); // 0.1361
		// System.out.println("Hypergeometric Distribution Example 1: " + hypergeometricDistribution(BigInteger.valueOf(20), BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(4))); // 0.135
		System.out.println("Hypergeometric Distribution Exercise 3.102: " + hypergeometricDistribution(BigInteger.valueOf(10), BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(3)));
		// Poisson Distribution: My PC crashes once (lambda = 1) every 4 months probability that it won't crash at all (y = 0)
		System.out.println("Poisson Distribution Example 1: " + poissonDistribution(1, 0));
		System.out.println("Tchebysheff's Theorem (k = 2): " + tchebysheffsTheorem(2));
		System.out.println("Conditional Probability Example 2.14: " + conditionalProbability((double)1/6, (double)1/2));
		System.out.println("Bayes' Theorem Exercise 2.124: " + bayesTheorem(0.7, 0.6, 0.3, 0.4));
		System.out.println("Big Factorial: " + bigFactorial(BigInteger.valueOf(60)));
	}
	
	/*
	 * find the mean (add up all the numbers in the set with a for loop and divide by the size)
	 * return the mean
	 */
	
	public double mean(int[] myNumbers) {
		double sum = 0;
		int size = myNumbers.length;
		for (int i = 0; i < size; i++) {
			sum += myNumbers[i];
		}
		return sum/size;
	}
	
	/*
	 * find the median (middle number in the set)
	 * if it's odd just half the size and return the middle position
	 * if it's even choose the two middle numbers and divide by 2
	 */
	
	public double median(int[] myNumbers) {
		int size = myNumbers.length;
		double x = 0;
		double y = 0;
		double middle = 0;
		
		if (size%2 == 0) {
			x = myNumbers[(size/2)];
			y = myNumbers[(size/2)-1];
			middle = (x+y)/2;
		} else {
			middle = myNumbers[size/2];
		}
		
		return middle;
	}
	
	/*
	 * find the mode which is most common number in the set
	 * compare first number with all then compare 2nd number with all and so on
	 * maybe add like current count versus, previous count, and a mode number
	 * compare whichever one has the highest count and then save it in mode
	 * at the end of the loop return the mode number
	 */
	
	public double mode(int [] myNumbers) {
		int size = myNumbers.length;
		int currCount = 0;
		int prevCount = 0;
		int modeNum = 0;
		
		for (int i = 0; i < size; i++) {
			prevCount = currCount;
			currCount = 0;
			for (int j = 1; j < size-1; j++) {
				if (myNumbers[i] == myNumbers[j]) {
					currCount++;
				}
			}
			
			if (currCount > prevCount) {
				modeNum = myNumbers[i];
			}
		}
		
		return modeNum;
	}
	
	/* 
	 * find the variance: how "far" each value in the set differs from the mean
	 * square the standard deviation
	 */
	public double variance(int [] myNumbers) {
		double result = 0;
		double sd = standardDeviation(myNumbers);
		result = Math.pow(sd, 2);
		return result;
	}
	
	/*
	 * find standard deviation: how much a group of numbers deviates from the mean
	 * find the mean
	 * subtract the mean from each value in the list
	 * square each value in the list
	 * divide by 1 less than the number of values
	 * square root the previous step
	 */
	
	public double standardDeviation(int [] myNumbers) {
		double meanResult = mean(myNumbers);
		int size = myNumbers.length;
		double sum = 0;
		double result = 0;
		
		for(int i = 0; i < size; i++) {
			myNumbers[i] = (int) (myNumbers[i] - meanResult);
			myNumbers[i] = (int) (Math.pow(myNumbers[i], 2));
			sum += myNumbers[i];
		}
		
		result = sum/(size-1);
		
		return Math.sqrt(result);
	}
	
	/* 
	 * permutations (order matters) - arranging objects in order
	 * combinations (order doesn't matter) - selecting objects in a way where order doesn't matter
	 * need to find the factorial (recursion)
	 */
	
	public int factorial(int num) {
		if (num == 1) {
			return num;
		} if (num == 0) {
			return 1;
		} else {
			return num * factorial (num-1);
		}
	}
	
	public BigInteger bigFactorial(BigInteger num) {
		if (num.equals(BigInteger.ONE)) {
			return num;
		} if (num.equals(BigInteger.ZERO)) {
			return BigInteger.ONE;
		} else {
			return num.multiply(bigFactorial(num.subtract(BigInteger.ONE)));
		}
	}
	
	public double permutations(int n, int r) {
		double numerator = 0;
		double denominator = 0;
		int nminusr = n-r;
		numerator = factorial(n);
		denominator = factorial(nminusr);
		return numerator/denominator;
	}
	
	public double combinations(int n, int r) {
		double numerator = 0;
		double denominator = 0;
		numerator = factorial(n);
		denominator = factorial(r)*factorial(n-r);
		return numerator/denominator;
	}
	
	public BigDecimal bigPermutations(BigInteger n, BigInteger r) {
		BigDecimal numerator = new BigDecimal(bigFactorial(n));
		BigDecimal denominator = new BigDecimal(bigFactorial(n.subtract(r)));
		BigDecimal result = numerator.divide(denominator);
		return result;
	}
	
	public BigDecimal bigCombinations(BigInteger n, BigInteger r) {
		BigDecimal numerator = new BigDecimal(bigFactorial(n));
		BigDecimal denominator = new BigDecimal(bigFactorial(r).multiply(bigFactorial(n.subtract(r))));
		BigDecimal result = numerator.divide(denominator);
		return result;
	}
	
	/* 
	 * binomial distribution is finding the probability of an event that can succeed or fail
	 * p = probability of success, q = probability of failure, n = number of trials, y = number of successes
	 */
	// p(y) = P(Y = y) = combinations [nCy] * p^y * q^(n-y)
	public double binomialDistribution(int n, double p, double q, int y) {
		double combinations = combinations(n, y);
		return combinations*Math.pow(p, y)*Math.pow(q, (n-y));
	}
	
	/*
	 * geometric distribution is finding the probability of how many fails are carried out until success
	 * p = success, q = 1 - p (failure), y = # of total trials (including success), Y = y <-> y - 1 means failures minus success
	 * p(y) = P(Y = y) = q^(y-1)*p
	*/
	
	public double geometricDistribution(double q, double p, int y) {
		double result = Math.pow(q, (y-1))*p;
		return result;
	}
	
	/*
	 * hypergeometric distribution is finding the probability of the number of successes without replacement from a sample size
	 * refer to formula sheet for variable meanings
	 * p(y) = P(Y = y) = P(A) = (r)nCr(y) * (N-r)nCr(n-y)/(N)nCr(n)
	 */
	
	public BigDecimal hypergeometricDistribution(BigInteger N, BigInteger n, BigInteger r, BigInteger y) {
		BigDecimal combinations1 = bigCombinations(r, y);
		BigDecimal combinations2 = bigCombinations(N.subtract(r), n.subtract(y));
		BigDecimal combinations3 = bigCombinations(N, n);
		BigDecimal result = (combinations1.multiply(combinations2)).divide(combinations3, 2, RoundingMode.HALF_UP);
		return result;
	}
	
	/*
	 * poisson distribution is finding the probability that successes that occur independently in a continuous time at a continuous rate
	 * lambda is rate at which successes occur
	 */
	
	public double poissonDistribution(int lambda, int y) {
		double factorial = factorial(y);
		double pow1 = Math.pow(lambda, y);
		// euler value is Math.exp(1)
		double pow2 = Math.pow(Math.exp(1), -lambda);
		double result = (pow1/factorial)*pow2;
		return result;
	}
	
	
	/*
	public double hypergeometricDistribution(int N, int n, int r, int y) {
		double combinations1 = combinations(r, y);
		double combinations2 = combinations((N-r), (n-y));
		double combinations3 = combinations(N, n);
		
		double result = ((combinations1)*(combinations2))/(combinations3);
		return result;
	}
	*/
	
	// tchebysheff's theorem finds how much values are in the interval around the average:
	public double tchebysheffsTheorem(int k) {
		double result = -1;
		if(k > 1) {
			result = (1)-(1/Math.pow(k, 2));
		}
		return result;
	}
	
	// probability of A given B has occurred
	public double conditionalProbability(double probAIntersectionB, double probB) {
		double result = probAIntersectionB/probB;
		return result;
	}
	
	// bayes' theorem 3rd
	public double bayesTheorem(double probAGivenB, double probB, double probAGivenBComplement, double probBComplement) {
		double result = (probAGivenB*probB)/((probAGivenB*probB)+(probAGivenBComplement*probBComplement));
		return result;
	}
}
