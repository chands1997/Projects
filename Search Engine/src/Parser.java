import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.TimeoutException;

/**
 * Created by Shivangi Chand on 3/24/2017.
 */
public class Parser extends Object{

    public Parser (){}

    public String getBody(Document doc) throws ParseException{
        if(doc == null){
            throw new ParseException("getBody() failed. Document parameter is null.");
        }
        Element elementBody = doc.body();
        String stringBody = elementBody.text();
        return stringBody;
    }

    public Document getDocument(String url) throws ParseException, TimeoutException {

        Document d = null;
        if (url == null) {
            throw new ParseException("getDocument() failed. String url is null.");
        }
        if (url.equals("")) {
            throw new ParseException("getDocument() failed. String url is empty.");
        }

        Connection.Response res = null;
        try {
            //System.out.println(url);
            res = Jsoup.connect(url).ignoreContentType(true).timeout(10*1000).execute();
        } catch (Exception e) {
            //e.printStackTrace();
            throw new ParseException("getDocument() failed. Connection failed.");
        }
        if (res != null) {
            int statusCode = res.statusCode();
            if (statusCode == 200) {
                //System.out.println(res.contentType());
                if (res.contentType().startsWith("text/html")) {
                    //System.out.println("1");
                    try {
                        d = res.parse();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        throw new ParseException("getDocument() failed. Connection failed.");
                    }
                } else {
                    throw new ParseException("getDocument() failed. Unknown Type.");
                }
            } else {
                //System.out.println("3");
                throw new ParseException("getDocument() failed. Response is not OK.");
            }
        } else {
            throw new ParseException("getDocument() failed. Connection failed.");
        }

        if(d == null){
            throw new ParseException("getDocument() failed: Document is null.");
        }
        //System.out.println("Returning document");
        return d;
    }

    public Elements getLinks(Document doc) throws ParseException{

        if(doc == null){
            throw new ParseException("getLinks() failed. Document parameter is null.");
        }

        Elements links = doc.select("a[href]");
        return links;
    }

}
