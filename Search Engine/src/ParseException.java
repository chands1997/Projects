import java.io.Serializable;

/**
 * Created by Shivangi Chand on 3/23/2017.
 */
public class ParseException extends Exception implements Serializable{

    public ParseException(String msg){
        super(msg);
    }
}
