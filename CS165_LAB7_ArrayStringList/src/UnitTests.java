public class UnitTests {
    public static void main(String[] args) {
        ArrayStringList testArr1 = new ArrayStringList(2);
        System.out.println(testArr1.size());
        testArr1.resizeData(testArr1.size() * 2);
        System.out.println(testArr1.size());

    }
}
