package net.yunt.pamradio;

import com.codename1.io.Storage;

//import java.io.BufferedReader;
import java.io.InputStream;
import java.util.*;

import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Display;



public class CSVHelper {

    public static Map readFile() {
        ArrayList<Card> list = new ArrayList<Card>();
        try {
            InputStream is = Display.getInstance().getResourceAsStream(MyApplication.class, "/data.csv");
            String s = Util.readToString(is, "UTF-8");
            String[] lines = Util.split(s, "\n");
            for(int i=0;i<lines.length;i++) {
                String[] columns = Util.split(lines[i], ",");
                Card c = new Card();
                c.setId(Integer.parseInt(columns[0]));
                c.setSection(Integer.parseInt(columns[1]));
                c.setQuestion(columns[2]);
                c.setAnswer(columns[3]);
                if(columns.length > 4) {
                    c.setProductLink(columns[4]);
                }
                list.add(c);

            }
            Log.setLevel(Log.DEBUG);
            Log.p(s);
            return splitListIntoSection(list);

        } catch(Exception err) {
            Log.e(err);
        }

        return null;
    }

    private static Map splitListIntoSection(List list) {
        Map<Integer,List<Card>> content = new HashMap();
        Iterator<Card> it = list.iterator();
        while(it.hasNext()) {
            Card card = it.next();
            if(!content.containsKey(card.getSection())) {
                content.put(card.getSection(),new ArrayList<Card>());
            }
            content.get(card.getSection()).add(card);

        }
        return content;
    }

}
