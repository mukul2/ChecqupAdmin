package com.chequp.admin.data;

import com.chequp.admin.model.CommonUserModel;
import com.chequp.admin.model.LoginResponse;
import com.chequp.admin.model.PublicLatestQueryModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class data {
    public  static CommonUserModel commonUserModel;
    public  static LoginResponse loginResponse;
    public  static String IMAGE_BASE="http://telemedicine.drshahidulislam.com/";
    public  static String CURRENCY_USD_SIGN=" $";
    public  static String TOKEN;
    public  static int MY_ID;
    public  static PublicLatestQueryModel QUERY_MODEL;
    public static String getColorCode(int pos) {
        List<String> colors = new ArrayList<>();
       /* colors.add("#DC7633");
        colors.add("#2E4053");
        colors.add("#2ECC71");
        colors.add("#27AE60");
        colors.add("#48C9B0");
        colors.add("#2980B9");
        colors.add("#8E44AD");*/
        // colors.add("#E74C3C");
        colors.add("#3498DB");
        colors.add("#45B39D");
        colors.add("#1F618D");
        return colors.get(pos % colors.size());
    }
    public static String changeDateformate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //  SimpleDateFormat targetFormat = new SimpleDateFormat("hh:mm aa MMM dd ");
        SimpleDateFormat targetFormat = new SimpleDateFormat("hh:mm aa dd MMM");
        return targetFormat.format(sourceDate);
    }
    public static String convertToWeekDay(String day) {
        Map<String, String> days = new HashMap<>();
        days.put("6", "Sat");
        days.put("0", "Sun");
        days.put("1", "Mon");
        days.put("2", "Tue");
        days.put("3", "Wed");
        days.put("4", "Thu");
        days.put("5", "Fri");

        return days.get(day);
    }
    public static String changeDateformate2(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //  SimpleDateFormat targetFormat = new SimpleDateFormat("hh:mm aa MMM dd ");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd MMM hh:mm aa");
        return targetFormat.format(sourceDate);
    }

}
