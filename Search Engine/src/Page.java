import java.io.Serializable;

/**
 * Created by Shivangi Chand on 3/22/2017.
 */
public class Page implements Serializable, Comparable<Page>{

    String url;
    final static long serialVersionUID = -1827677255104766839L;
    private int urlID;

    public Page(String url, int urlId){
        super();
        this.url = url;
        this.urlID = urlId;
    }

    public String getURL(){
        return url;
    }

    public int getURLID(){
        return urlID;
    }

    public boolean equals(Object obj){
        if(((Page) obj).url.equals(this.url)||((Page)obj).urlID == this.urlID) {
            return true;
        }
        return false;
    }

    public int compareTo(Page candidate){
        if(this.urlID < candidate.urlID){
            return -1;
        }
        else if(this.urlID == candidate.urlID){
            return 0;
        }
        else if(this.urlID > candidate.urlID){
            return 1;
        }
        return -1;
    }

}
