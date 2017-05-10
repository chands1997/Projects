import java.io.Serializable;

/**
 * Created by Shivangi Chand on 4/5/2017.
 */
public class Result implements Serializable, Comparable<Result>{

    int score = 1 ;
    final static  long serialVersionUID = -938761094876384658L;
    String url;
    int urlID;

    public Result(String url,int urlId){
        this.url = url;
        this.urlID = urlId;
    }

    public void updateScore(int score){
        this.score = this.score + score;
    }

    public void incrementScore(){
        this.updateScore(1);
    }

    public int getScore(){
        return score;
    }

    public String getURL(){
        return url;
    }

    public int getURLID(){
        return urlID;
    }

    public boolean equals(Object obj){
        if(((Result)obj).url.equals(this.url) && ((Result)obj).urlID == this.urlID && ((Result)obj).score == this.score){
            return true;
        }
        return false;
    }

    public int compareTo(Result candidate){
        if(this.score == candidate.score){
            return 0;
        }
        else if(this.score> candidate.score){
            return -1;
        }
        return 1;
    }

}

