import com.karryli.mapreduce.MapReduce;
import junit.framework.TestCase;
import com.karryli.mapreduce.map.Mapper;
import com.karryli.mapreduce.reduce.Reducer;

import java.io.*;
import java.util.*;

/**
 * Created by yuxiao on 8/10/18.
 */
public class MapReduceTest extends TestCase {
    static ArrayList<String> docs = new ArrayList<String>();
    private static String prefixPath = "/Users/yuxiao/intellijProject/my-mapreduce-java/src/main/java/com/karryli/mapreduce/";
    private  static String inputFilePath = prefixPath+"input";

    private static String outputFilePath =prefixPath+"output/part-1.txt";


    private static void readPassages(){
        try{

            File input = new File(inputFilePath);
            File[] listsFiles = input.listFiles();
            for(int i=0;i<listsFiles.length;i++){
                File currentFile = listsFiles[i];
                FileInputStream fileInputStream = new FileInputStream(currentFile);

                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                StringBuffer buffer = new StringBuffer();
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = br.readLine())!=null){
                    buffer.append(line+"\n");
                }
                docs.add(buffer.toString());
            }


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void outPut(Object result){
        HashMap resultMap = (HashMap) result;
//        for(Object entry:resultMap.entrySet()){
//            entry.
//        }
        try{
            File outputFile = new File(outputFilePath);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            PrintStream printStream = new PrintStream(outputStream);
            for(Object str: resultMap.keySet()){
                printStream.print(str+"  ");
                printStream.print(resultMap.get(str));
                printStream.println();

            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public void testWordCount() {
        readPassages();


        long start = System.currentTimeMillis();
        MapReduce mr = new MapReduce.Builder().input(docs).mapper(new Mapper() {
            public Object map(Object data) {
                String doc = (String) data;
                String[] tokens = doc.trim().split("\\s+");
                HashMap results = new HashMap();
                for (int i = 0; i < tokens.length; i++) {
                    accumulate(tokens[i], results);
                }
                return results;

            }
        }).reducer(new Reducer() {
            // ReduceStage 23行执行reduce操作
            public Object reduce(Collection c) {
                HashMap h = new HashMap();
                for (Iterator i = c.iterator(); i.hasNext(); ) {
                    Collection entries = ((HashMap) i.next()).entrySet();
                    for (Iterator j = entries.iterator(); j.hasNext(); ) {
                        Map.Entry e = (Map.Entry) j.next();
                        Object key = e.getKey();
                        Integer val = (Integer) e.getValue();
                        if (h.containsKey(key)) {
                            Integer oldval = (Integer) h.get(key);
                            h.put(key,val + oldval);
                        } else {
                            h.put(key, val);
                        }
                    }
                }
                return h;

            }
        }).workersNo(100).build();

        // MasterWorkers.java 33 行
        mr.begin();
        // ReduceStage.java 23行
        Object result = mr.master.reduceResults();
        System.out.printf("Program took %d sec to execute.\n", (System.currentTimeMillis() - start) / 1000);

//        System.out.println(result.toString());

        outPut(result);
    }

    private void accumulate(String s, HashMap acc) {
        String key = s.toLowerCase();
        if (acc.containsKey(key)) {
            Integer I = (Integer) acc.get(key);
            int newval = I.intValue() + 1;
            acc.put(key, new Integer(newval));
        } else {
            acc.put(key, new Integer(1));
        }
    }
}
