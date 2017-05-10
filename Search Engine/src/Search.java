import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by paulrenner on 4/5/17.
 */
public class Search extends Object{
    public static List<Page> pageList = new ArrayList<>();
    private String pageListFile;
    public static List<Result> resultSet;
    public static List<Word> wordList = new ArrayList<>();
    private String wordListFile;

    public Search(String wordListFile, String pageListFile) {
        this.wordListFile = wordListFile;
        this.pageListFile = pageListFile;
        setup(wordListFile,pageListFile);
    }
    public void setup(String wordListFile, String pageListFile) {
        FileUtils fileUtils = new FileUtils();
        try {
            pageList=fileUtils.getPageList(pageListFile);
            wordList=fileUtils.getWordList(wordListFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Result> executeQuery(String query) {

        nullCheck();
        query = query.toLowerCase();
        resultSet = Collections.synchronizedList(new ArrayList<Result>());
        String terms[];
        terms = query.split(" ");
        int start =0;
        int end = wordList.size() / 5;
        int ini = end;
        int temp = wordList.size() - (end * 5);
        Thread[] t = new Thread[5];
        for (int i = 0; i <5; i++) {

            t[i] = new Thread(new SearchThread(start, end, terms));
            start = end+1;
            if(i == t.length-1){
                end = end + temp;
            }
            else{
                end =ini *(i+2);
            }
        }
        for (Thread thread : t) {
            thread.start();
        }
        for (Thread thread : t) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sort();
        return resultSet;
    }
    public void nullCheck() {
    if(pageList == null || wordList == null){
        setup(wordListFile,pageListFile);
    }
    }
    public void sort() {
        Collections.sort(resultSet);
    }
}

