import java.io.*;
import java.util.*;


/**
 * Created by Shivangi Chand on 4/3/2017.
 */
public class FileUtils {

    public List<Page> getPageList(String filePath) {

        if(filePath == null){
            return null;
        }


        File f  = new File(filePath);
        List<Page> pList= null;
        Page page = null;

        if(f.exists()){
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                pList = ((ArrayList<Page>)ois.readObject());
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return pList;

        }
        else {
            return null;
        }
    }

    public List<Word> getWordList(String filePath){

        if(filePath == null){
            return null;
        }

        File f  = new File(filePath);
        List<Word> wList= null;


        if(f.exists()){
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                wList = ((ArrayList<Word>) ois.readObject());
                ois.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return wList;
        }
        else {
            return null;
        }
    }

    public boolean savePageTable(List<Page> pageTable, String filePath){

        if(filePath == null){
            return false;
        }
        if(pageTable== null){
            return false;
        }


        File f = new File(filePath);
        int check = 0;

        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            if (pageTable.isEmpty()) {
                oos.writeObject(pageTable);
                check = 1;
            } else {
                oos.writeObject(pageTable);
                check = 1;
            }
            oos.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(check == 1 ){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean saveWordTable(List<Word> wordTable, String filePath){
        if(filePath == null){
            return false;
        }
        if(wordTable == null){
            return false;
        }

        File f = new File(filePath);
        int check = 0;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            if (wordTable.isEmpty()) {
                check = 1;
                oos.writeObject(wordTable);

            } else {
                oos.writeObject(wordTable);
                check = 1;
            }

            oos.close();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        if(check == 1){
            return true;
        }
        else{
            return false;
        }

    }
}
