import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivangi Chand on 3/22/2017.
 */
public class Word implements Serializable {


    private List<Integer> postings = new ArrayList<Integer>();
    final static long serialVersionUID = -3696191086353573895L ;
    private String word;

    public Word(String word, int urlId) {
        this.word = word;
        postings.add(urlId);
    }

    public void addURLID(int urlId){
        postings.add(urlId);
    }

    public String getWord(){
        return word;
    }

    public List<Integer> getList(){
        return postings;
    }

    public boolean equals(Object obj){
        if(((Word)obj).word.equals(this.word)) {
           return true;
        }
        return false;

    }
}
