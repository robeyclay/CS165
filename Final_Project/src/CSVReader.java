import java.io.File;
import java.util.*; //supposed to import all

public class CSVReader
{
    private Scanner scanner;
    private String delim = ",";

    public CSVReader(String file, boolean skipHeader)
    {
        try {
            scanner = new Scanner(new File(file));
        }
        catch (Exception e) {
            scanner = null;
            System.out.println("***** CSVReader File Error *****\n" + e);
        }


        if (skipHeader)
            scanner.nextLine();
    }

    public CSVReader(String file)
    {
        new CSVReader(file, false);
    }

    public List<String> getNext()
    {
        return hasNext() ? Arrays.asList(scanner.nextLine().split(delim)) : null;
    }

    public boolean hasNext()
    {
        return scanner.hasNext();
    }
}