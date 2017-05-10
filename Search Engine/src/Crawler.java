import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivangi Chand on 3/30/17.
 */
public class Crawler {
    static int currentID = 0;
    static String domain;
    static int limit;
    static List<Page> parsed = new ArrayList<>();
    static Parser parser = new Parser();
    MyQueue toParse = new MyQueue();
    static int totalURLs = 0;
    static List<String> visited = new ArrayList<>();
    static List<Word> words = new ArrayList<>();

    public Crawler(String seed, String domain, int limit) {

        this.domain = domain;
        this.limit = limit;
        totalURLs = 0;
        toParse.add(seed);
    }

    public void crawl() {

        //while(!toParse.isEmpty() && totalURLs<limit){
        while(!toParse.isEmpty()&& currentID<limit){
            Page temp = new Page((String) toParse.remove().getData(),currentID);
            //System.out.println("0>>>>"+temp.getURL());

            if (visited.contains(temp.getURL()) || (!isValidURL(temp.getURL()))|| (!isInDomain(temp.getURL()))) {
                //System.out.println("1>>>"+temp.getURL());
                continue;
            } else {
                try {
                    //System.out.println("2>>>>"+temp.getURL());
                    Document doc = parser.getDocument(temp.getURL());
                    parse(doc, currentID);
                    addPageToList(temp);
                    currentID += 1;
                    visited.add(temp.getURL());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //System.out.println(totalURLs);
        }

        //System.out.print(parsed);

    }

    public boolean parse(Document doc, int id){
        try {
            parseLinks(doc);
            parseText(doc, id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public void parseLinks(Document doc) throws Exception{
        Elements e = parser.getLinks(doc);
        for(Element el :e){
            //System.out.println(el);
//            if(totalURLs >= limit)
//                break;
            String at = el.attr("abs:href");
            if(isValidURL(at) && isInDomain(at)){
                //System.out.println("Adding to queue"+el);
                addToQueue(at);
            }
        }
    }
    public void parseText(Document doc, int id) throws Exception{

        String body = parser.getBody(doc);
        String[] temp = body.split(" ");
        for(int i=0; i<temp.length; i++){
            addWordToList(temp[i].toLowerCase(),id);
        }
    }
    public void addWordToList(String word, int id) {
        int len = words.size();
        int check = 0;
        for(int i = 0; i<len; i++){
            if(words.get(i).getWord().equals(word)){
                words.get(i).addURLID(id);
                check = 1;
                break;
            }
        }
        if(check == 0){
            Word w = new Word(word, id);
            words.add(w);
        }
    }
    public void addToQueue(String url) {
        int ch = 0;

        if(visited.contains(url)){
            ch = 1;
        }
        if(ch != 1) {
            toParse.add(url);
            totalURLs += 1;
        }
    }
    public void addPageToList(Page p) {
        parsed.add(p);
    }
    public boolean isInDomain(String url) {
        if(url == null){
            return false;
        }
        if(url.toLowerCase().contains(domain.toLowerCase())){
            return true;
        }
        return false;
    }
    public boolean isValidURL(String url) {
        if(url == null){
            return false;
        }
        if(url.startsWith("http://")|| url.startsWith("https://")){
            return true;
        }
        return false;
    }

}


