import java.util.ArrayList;

public class UnitTests {
    public static void main(String[] args)
    {
        ArrayList<String> testNameList = new ArrayList<String>();
        ArrayList<String> testPermList = new ArrayList<String>();
        testNameList.add("Julia");
        testNameList.add("Lucas");
        testNameList.add("Mia");
        PhotoLineups.allPermutations(testPermList, testNameList);

    }
}
