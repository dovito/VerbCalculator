import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by s1237357 on 14/10/16.
 */
public class FileReader {

    String fileName;


    public FileReader(String fileName){
        this.fileName = fileName;
    }

    public Scanner getScanner() throws FileNotFoundException {
        Scanner scanner = new Scanner (new File(fileName));
        return scanner;
    }

    public List<Annotation> getAnnotations(Scanner scanner) throws FileNotFoundException {

        List<Annotation> annotations= new ArrayList<Annotation>();
        //scanner.useDelimiter(",");
        //scanner.useDelimiter("\n");
        String lemma, image, sense, dataset;
        String LINE;
        scanner.next();
        while(scanner.hasNext())
        {
            LINE = scanner.next();
            String[] parsedLine = LINE.split(",");
            lemma = parsedLine[0];
            image = parsedLine[1];
            sense = parsedLine[2];
            dataset = parsedLine[3];

            Annotation annotation = new Annotation(lemma, image, Double.parseDouble(sense), dataset);
            annotations.add(annotation);
/*            System.out.println("Lemma: " +lemma +
                    " | Image: " + image +
                    " | Sense: " + sense +
                    " | Dataset: " + dataset + "\n"); */
        }
        scanner.close();
        return annotations;

    }

}
