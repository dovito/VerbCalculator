import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by s1237357 on 14/10/16.
 */
public class Main {

    public static List<String> distinctVerbs = new ArrayList<>();
    public static Map<String, Integer> verbsAndTheirNumber = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        String fileName = "gold_all_final_lemma_image_sense_annotations.csv";
        FileReader fileReader = new FileReader(fileName);

        Scanner scanner = fileReader.getScanner();
        List<Annotation> annotations = fileReader.getAnnotations(scanner);
        getDistinctVerbs(annotations);
        getSplitSet();

    }

    private static void getDistinctVerbs(List<Annotation> annotations){

        for(Annotation annotation : annotations){

            if(!distinctVerbs.contains(annotation.lemma)){
                verbsAndTheirNumber.put(annotation.lemma, 1);
                distinctVerbs.add(annotation.lemma);
            } else {
                verbsAndTheirNumber.put(annotation.lemma, verbsAndTheirNumber.get(annotation.lemma) + 1);
            }
        }
    }

    private static void getSplitSet(){

        System.out.println("Number of distinct verbs: "+distinctVerbs.size());
        int sum = 0;
        Set<String> verbs = verbsAndTheirNumber.keySet();
        for(String verb : verbs){
            sum += verbsAndTheirNumber.get(verb);
        }
        double mean = sum / distinctVerbs.size();
        System.out.println("On average there are "+mean + " verb occurrences");
        System.out.println("Verb occurrences in lower set:");
        for(String verb : verbs){
            if(verbsAndTheirNumber.get(verb) < mean)
                System.out.println(verb + " | " + verbsAndTheirNumber.get(verb));
        }
        System.out.println("Verb occurrences in higher set:");
        for(String verb : verbs){
            if(verbsAndTheirNumber.get(verb) >= mean)
                System.out.println(verb + " | " + verbsAndTheirNumber.get(verb));
        }
    }


}
