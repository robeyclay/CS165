import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.MalformedInputException;
import java.util.*;


/**
 *
 */
public class ExpressionTree extends ZTree {

    public Queue<String> parse(String expression) {
        Queue<String> infix = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "(?<=[-+*()%/])|(?=[-+*()%/])", true);
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if(!token.trim().isEmpty()) {
                infix.add(token.trim());
            }
        }
        return infix;
    }

    public List<String> convert(Queue<String> infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> operators = new ArrayDeque<>(); // used as a stack
        // YOUR CODE HERE
        while(infix.size() != 0) {
            String curr = infix.poll();
            if(isInteger(curr)) {
                postfix.add(curr);
                //System.out.println("Adding Integer" + output.toString());
            } else if (isOperator(curr)) {
                while(!operators.isEmpty() && precedence(curr) >= precedence(operators.peek()) && !operators.peek().equals("(")) {
                    //System.out.println("Operator Stack" + operator.toString());
                    String nextOperator = operators.pop();
                    postfix.add(nextOperator);
                }
                operators.push(curr);
                //System.out.println("Adding Operator" + output.toString());
            } else if (curr.equals("(")) {
                operators.push(curr);
            } else if (curr.equals(")")) {
                while(!operators.peek().equals("(")) {
                    //System.out.println("Operator Stack" + operator.toString());
                    String nextOperator = operators.pop();
                    postfix.add(nextOperator);
                }
                //System.out.println("Checking Parenthesis: " + output.toString());
                if(operators.peek().equals("(")) {
                    operators.pop();
                }
            }
        }
        if(infix.size() == 0) {
            while(operators.size() != 0) {
                String nextOperator = operators.pop();
                postfix.add(nextOperator);
            }
        }
        //System.out.println(postfix);
        return postfix;
    }

    @Override
    public void build(List<String> postfix) {
        Collections.reverse(postfix);
        for (String token : postfix)
            buildRecursive(root, token);
    }

    /**
     * Builds an expression tree from the postfix representation returned from the convert method.
     * To build the correct tree, pull tokens from {@code List<String> postfix}, and places
     * them at the next available node in the tree.
     * Here is the exact algorithm:
     * <ol>
     *     <li> If the tree root is null, create a new node containing the token,
     *          assign it to the root, and return {@code true}.
     *     <li> If the right child of the current node is null, create a new node
     *          with the token, place it in the right child, and return {@code true}.
     *     <li> If the right child of the current node is an operator, make a recursive
     *          call passing the right child and token, and return true if successful,
     *          otherwise continue.
     *     <li> If the left child of the current node is null, create a new node with
     *          the token, place it in the left child, and return {@code true}.
     *     <li> If the left child of the current node is an operator, make a recursive
     *          call passing the left child and token, and return {@code true} if successful,
     *          otherwise continue.
     *     <li> If none of the above code returns {@code true}, then the code has failed to add
     *          the token to the tree, so return {@code false}.
     * </ol>
     *
     * Our implementation of the recursive method is ~19 lines of code
     * @param current the current Node being checked
     * @param token the token to add
     * @return {@code true}, if successful
     */
    public boolean buildRecursive(Node current, String token) {
        // YOUR CODE HERE
        if(root == null) {
            Node newNode = new Node(token);
            root = newNode;
            //System.out.println("Root: " + root.token);
            return true;
        } if (current.right == null) {
            Node newNode = new Node(token);
            current.right = newNode;
            //System.out.println("Current.right: " + current.right.token);
            return true;
        } if (isOperator(current.right.token)) {
            //System.out.println("recursive call right");
            if(buildRecursive(current.right, token)) {
                return true;
            }
        } if (current.left == null) {
            Node newNode = new Node(token);
            current.left = newNode;
            //System.out.println("Current.left: " + current.left.token);
            return true;
        } if (isOperator(current.left.token)) {
            //System.out.println("recursive call left");
            if(buildRecursive(current.left, token)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String prefix() {
        return prefixRecursive(root);
    }

    /**
     * Concatenates the tokens in the expression tree returned from the
     * {@link #build(List)} method in <b>prefix</b> order.
     * <p>
     * Accumulate the operator first, then the string from the left
     * and right subtrees. Add an extra space after each token.
     * <p>
     * Our implementation of this method is ~2-6 lines of code.
     * @param current the root node
     * @return the tokens in prefix order
     */
    public String prefixRecursive(Node current) {
        // YOUR CODE HERE
        if(current.right == null && current.left == null) {
            return current.token + " ";
        } else {
            return current.token + " " + prefixRecursive(current.left)  + prefixRecursive(current.right);
        }
    }

    @Override
    public String infix() {
        return infixRecursive(root);
    }

    /**
     * Concatenates the tokens in the expression tree returned from the
     * {@link #build(List)} method in <b>infix</b> order.
     * <ol>
     *     <li> Accumulate the string from the left subtree
     *     <li> Add the operator
     *     <li> Accumulate the string from the right subtree
     * </ol>
     * This method should add parentheses to maintain the correct evaluation order,
     * add a left parentheses before traversing the left subtree, and a right
     * parentheses after traversing the right subtree.
     * Do not add any space to the expression string.
     * <p>
     * Our implementation of this method is ~2-6 lines of code.
     * @param current
     * @return the tokens in infix order
     */
    public String infixRecursive(Node current) {
        if(current.right == null && current.left == null) {
            return current.token;
        } else {
            return "(" + infixRecursive(current.left) + current.token + infixRecursive(current.right) + ")";
        }
    }

    @Override
    public String postfix() {
        return postfixRecursive(root);
    }

    /**
     * Concatenates the tokens in the expression tree returned from the
     * {@link #build(List)} method in <b>postfix</b> order.
     * First accumulate the string from the left and right subtrees, then add the
     * operator. Add an extra space after each token.
     * <p>
     * Our implementation of this method is ~2-6 lines of code.
     * @param current reference to the current node (starts with root)
     * @return a String representing the tree in postfix order
     */
    public String postfixRecursive(Node current) {
        // YOUR CODE HERE
        if(current.right == null && current.left == null) {
            return current.token + " ";
        } else {
            return postfixRecursive(current.left) +  postfixRecursive(current.right) + current.token + " ";
        }
    }

    public int evaluate() {
        return evaluateRecursive(root, 0);
    }

    /**
     * Traverses the expression tree and produces the correct answer, which should be an integer.
     * To evaluate, call the recursive version of the method to get the result from the left
     * subtree, do the same for the right subtree, then combine these two results using the
     * operator. A case statement based on the operator is needed to perform the mathematical operation.
     * <p>
     * Our implementation uses one helper method (~7 lines of code) and is, itself, ~2 lines of code.
     * @param current
     * @return
     */
    public int evaluateRecursive(Node current, int eval) {
        // YOUR CODE HERE
        if(root == null) {
            eval = 0;
        } else {
            String temp = current.token;

            if(isOperator(temp)) {
                int operand1 = evaluateRecursive(current.left, eval);
                int operand2 = evaluateRecursive(current.right, eval);

                switch (temp) {
                    case "+":
                        eval = operand1 + operand2;
                        break;
                    case "-":
                        eval = operand1 - operand2;
                        break;
                    case "*":
                        eval = operand1 * operand2;
                        break;
                    case "/":
                        eval = operand1 / operand2;
                        break;
                    case "%":
                        eval = operand1 % operand2;
                        break;
                }
            } else {
                eval = Integer.parseInt(temp);
            }
        }
        return eval;
    }

    // Test code for
    public static void main(String[] args) {

        // Instantiate student code
        ExpressionTree eTree = new ExpressionTree();

        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();

        System.out.println("Original Expression: " + expression);

        // Verify parse
        Queue<String> infix = eTree.parse(expression);
        System.out.println("Infix Tokens: " + infix.toString());

        // Verify convert
        List<String> postfix = eTree.convert(infix);
        System.out.println("Postfix Tokens: " + postfix.toString());

        // Verify build
        eTree.build(postfix);
        System.out.println("Build: complete");

        // Verify prefix
        System.out.println("Prefix: " + eTree.prefix());

        // Verify infix
        System.out.println("Infix: " + eTree.infix());

        // Verify postfix
        System.out.println("Postfix: " + eTree.postfix());

        // Verify evaluate
        System.out.println("Evaluate: " + eTree.evaluate());

        // Verify display
        System.out.println("Display: complete");
    }



}
