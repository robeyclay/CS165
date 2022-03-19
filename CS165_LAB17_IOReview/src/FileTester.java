import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileTester {
    File f;
    public FileTester(){
        f = new File("resources/data_in.txt");
    }

    /** take a look in 'resources/data_in.txt' to see what the text looks like first.
     * This method should read the lines in the file and add each line of a paragraph to a list and then
     * when that paragraph is complete, add that list to the contents list
     * @return A list of lists which contain the lines of each paragraph
     *
     **/
    public List<List<String>> readFile(){
        List<List<String>> contents = new ArrayList<>();
        List<String> paragraph = new ArrayList<>();
        try {
            Scanner in = new Scanner(f);
            //YOUR CODE HERE
            while(in.hasNextLine()) {
                String currLine = in.nextLine();
                if(currLine.equals("")) {
                    contents.add(paragraph);
                    paragraph = new ArrayList<>();
                } else {
                    paragraph.add(currLine);
                }
            }
            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return contents;
    }

    /**
     * This method should write the paragraphs to the file exactly the way they were read in
     * each List in the list called paragraphs should be written to the file and there should be a line in
     * between each paragraph.
     */
    public void writeFile(List<List<String>> paragraphs) {
        try {
            File file = new File("resources/data_out.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            //YOUR CODE HERE
            for(List<String> p : paragraphs) {
                for(String s : p){
                    writer.write(s);
                    writer.newLine();
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void checkFile(){
        try {
            Scanner in = new Scanner(new File("resources/data_in.txt"));
            Scanner out = new Scanner(new File("resources/data_out.txt"));
            int count = 1;
            int errCount = 0;
            while(in.hasNextLine()){
                String inLine = in.nextLine();
                if(out.hasNextLine()){
                    String outLine = out.nextLine();
                    if(!inLine.equals(outLine)){
                        System.err.printf("Error: Lines did not match at line %d\nLine from the input file: %s\nLine from your output file: %s",
                                count, inLine, outLine);
                        errCount++;
                    }
                }
                count++;
            }
            if(errCount == 0) System.out.println("YOUR CODE IS CORRECT, YAY!!!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    public static void main(String[] args){
        FileTester f = new FileTester();
        List<List<String>> paragraphs = f.readFile();
        f.writeFile(paragraphs);
        f.checkFile();
    }
}
