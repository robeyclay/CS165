import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class P7_Unit_Testing {

	ExpressionTree eTree;
	
	@BeforeEach
	void initializeTree() {
		eTree = new ExpressionTree();
	}
	
	//Test to make sure parse() reads in an expression correctly
	@Test
	void testParse() {
		Queue<String> test = new LinkedList<String>();
		test.add("3");
		test.add("-");
		test.add("4");
		test.add("+");
		test.add("5");
		
		assertEquals(test,eTree.parse("3-4+5"));
	}
	
	//Test convert() to see if it correctly transforms the output of parse()
	@Test
	void testConvert() {
		List<String> testList = new ArrayList<String>();
		testList.add("3");
		testList.add("4");
		testList.add("-");
		testList.add("2");
		testList.add("-");
		
		assertEquals(testList,eTree.convert(eTree.parse("3-4-2")));
	}
	
	//!! buildRecursive() many conditions! you will need to implement more sophisticated
	// tests in order to reach full code coverage of this method !!
	@Test
	void testBuildRecursiveRootNull() {
		assertTrue(eTree.buildRecursive(eTree.root,""));
	}
	
	@Test
	void testBuildRecursiveRightNull() {
		List<String> testRightNull = new LinkedList<>();
		testRightNull.add("root");
		eTree.build(testRightNull);
		assertEquals(true, eTree.buildRecursive(eTree.root, ""));
	}
	
	@Test
	void testBuildRecursiveRightOp() {
		List<String> testRightOp = new LinkedList<>();
		testRightOp.add("+");
		testRightOp.add("root");
		eTree.build(testRightOp);
		assertEquals(true, eTree.buildRecursive(eTree.root, "after +"));
	}
	
	@Test
	void testBuildRecursiveLeftNull() {
		List<String> testLeftNull = new LinkedList<>();
		testLeftNull.add("root");
		testLeftNull.add("Right");
		eTree.build(testLeftNull);
		assertTrue(eTree.buildRecursive(eTree.root, ""));
	}
	
	@Test
	void testBuildRecursiveLeftOp() {
		List<String> testLeftOp = new LinkedList<>();
		testLeftOp.add("+");
		testLeftOp.add("right");
		testLeftOp.add("root");
		eTree.build(testLeftOp);
		assertEquals(true, eTree.buildRecursive(eTree.root, "after +"));
	}
	
	@Test
	void testBuildRecursiveAddFail() {
		List<String> testFail = new LinkedList<>();
		testFail.add("left");
		testFail.add("right");
		testFail.add("root");
		eTree.build(testFail);
		assertEquals(false, eTree.buildRecursive(eTree.root, "Fail"));
	}
	
	
	//Test if the prefixRecursive() method correctly returns the prefix version of the given expression
	@Test
	void testPrefixRecursive() {
		Queue<String> testQueue = eTree.parse("5*5+3");
		eTree.build(eTree.convert(testQueue));
		
		assertEquals("+ * 5 5 3 ",eTree.prefix());
	}
	
	//Test if the infixRecursive() method correctly returns the infix version of the given expression
	@Test
	void testInfixRecursive() {
		Queue<String> testQueue = eTree.parse("5*5+3");
		eTree.build(eTree.convert(testQueue));
		
		assertEquals("((5*5)+3)",eTree.infix());
	}
	
	//Test if the postfixRecursive() method correctly returns the postfix version of the given expression
	@Test
	void testPostfixRecursive() {
		Queue<String> testQueue = eTree.parse("5*5+3");
		eTree.build(eTree.convert(testQueue));
		
		assertEquals("5 5 * 3 + ",eTree.postfix());
	}
	
	//Test if the evaluateRecursive() method correctly returns the solution of the given expression
	@Test
	void testEvaluateRecursive() {
		Queue<String> testQueue = eTree.parse("5*5+3");
		eTree.build(eTree.convert(testQueue));
		
		assertEquals(28,eTree.evaluate());
	}

}
