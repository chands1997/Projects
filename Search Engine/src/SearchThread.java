import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by paulrenner on 4/5/17.
 */
public class SearchThread implements Runnable{
    int finish;
    int start;
    String[] terms;

    public SearchThread(int start, int finish, String[] terms) {
        this.finish = finish;
        this.start = start;
        this.terms = terms;
    }
    public void run() {
        Search.resultSet = Collections.synchronizedList(new ArrayList<Result>());
        Word word;
        boolean check = false;
        for (String term : terms) {
            word = findTerm(term);

            if (word != null) {
                for (Integer id : word.getList()) {
                    check = false;

                    for (int i = 0; i < Search.resultSet.size(); i++) {
                        if (Search.resultSet.get(i).getURLID() == id) {
                            Search.resultSet.get(i).incrementScore();
                            check = true;
                        }
                    }
                    if (check == false) {
                        String url = "";
                        for (int i = 0; i < Search.pageList.size(); i++) {
                            if (Search.pageList.get(i).getURLID() == id) {
                                url = Search.pageList.get(i).getURL();
                                if (url != "") {
                                    Result result = new Result(url, id);
                                    Search.resultSet.add(result);
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public Word findTerm(String term) {
       Word match = null;
        for (int i = start; i < finish+1; i++) {
            if (Search.wordList.get(i).getWord().equalsIgnoreCase(term)) {
                match = Search.wordList.get(i);
            }
        }
        return match;
    }
}

