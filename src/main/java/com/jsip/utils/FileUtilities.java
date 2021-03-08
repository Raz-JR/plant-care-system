package com.jsip.utils;

import com.jsip.model.Plant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ali on 3/6/2021.
 */
public class FileUtilities {

    public static String getFileContent(InputStream fis, String encoding) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }
    public static int takeNumberOutOfString(String input){
        int extractedNumber = 0;
        Pattern pattern = Pattern.compile("\\d+"); // the regex
        Matcher matcher = pattern.matcher(input);

        if (matcher.find())
            extractedNumber = Integer.parseInt(matcher.group());

        return extractedNumber;

    }

}
