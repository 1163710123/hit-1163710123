package com.experiment;
import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.Reader;
        import java.net.URL;
        import java.nio.charset.Charset;

        import org.codehaus.jettison.json.JSONArray;
        import org.codehaus.jettison.json.JSONException;
        import org.codehaus.jettison.json.JSONObject;
        import java.io.PrintWriter;
        import java.net.URLConnection;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class Example {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject postRequestFromUrl(String url, String body) throws IOException, JSONException {
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        out.print(body);
        out.flush();

        InputStream instream = conn.getInputStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            instream.close();
        }
    }

    public static JSONObject getRequestFromUrl(String url) throws IOException, JSONException {
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        InputStream instream = conn.getInputStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            instream.close();
        }
    }

    public  static String fenci(String string){
        String url = "http://api01.idataapi.cn:8000/nlp/segment/bitspaceman?apikey=SieU1idNOWcILw1RWwzqamyphwpY0KJ0BUFt51p3RhkmjWYd1kXEk49HF2mzjE8t";
        String body = "text="+string;
        Pattern patPunc = Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        Matcher matcher = patPunc.matcher(string);
        if(matcher.find()){
            return string;
        }
        try{
            JSONObject json = postRequestFromUrl(url,body);
            JSONArray j = json.getJSONArray("wordList");
            String string1 = "";
            for(int i =0 ; i<j.length();i++){
                if(j.getJSONObject(i).get("word").toString().equals("，")){
                    continue;
                }
                if (i != j.length()-1){
                    string1 = string1 +j.getJSONObject(i).get("word").toString() + ",";
                }else{
                    string1 = string1 +j.getJSONObject(i).get("word").toString();
                }
            }
            return string1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args){

        System.out.println(fenci("哈尔滨市南岗区西苑小区"));
    }
}