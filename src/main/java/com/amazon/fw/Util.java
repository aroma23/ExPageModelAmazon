package com.amazon.fw;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * This class going to have all utility classes and its corresponding methods.
 * String related methods must be direct child of this class.
 * All other methods must have meaningful father of its own. Ex Date
 *
 * @author Muthukumar Ramaiyah
 *
 */

public class Util {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA_LOWER = "abcdefghijklmnopqrstuvxyz";
    private static final String NUMERIC = "0123456789";
    private static final String HEXA = "0123456789abcdef";
    Util() { }

    public static Map<String, Boolean> validateJson(List<String> values, String input){
        Map<String, Boolean> results = new HashMap<>();
        for(String value: values)
            if (!input.contains(value))
                results.put(value, false);
        return results;
    }

    /**
     * Quick utility method to get a randomized alpha-numeric string
     * @param length - length of random string
     */
    public static String getRandomAlphaNumericString(int length) {
        return getRandomString(ALPHA + ALPHA_LOWER + NUMERIC, length);
    }

    /**
     * Quick utility method to get a randomized alpha string
     * @param length - length of random string
     */
    public static String getRandomAlphaString(int length) {
        return getRandomString(ALPHA, length);
    }

    /**
     * Quick utility method to get a randomized string from input chars
     * @param length - length of random string
     */
    private static String getRandomString(String chars, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(chars.charAt(new Random().nextInt(chars.length())));
        return sb.toString();
    }

    /**
     * Quick utility method to get a randomized hexa-numeric string
     * @param length - length of random string
     */
    public static String getRandomHexaString(int length) {
        return getRandomString(HEXA, length);
    }

    /**
     * Quick utility method to get a randomized project id string
     */
    public static String getRandomProjectId() {
        return getRandomString(HEXA, 24);
    }

    /**
     * Quick utility method to get a randomized mac address
     */
    public static String getMacAddress() {
        StringBuilder sb = new StringBuilder(17);
        for (int i = 0; i < 6; i++)
            sb.append(getRandomHexaString(2)).append(":");
        return sb.toString().substring(0,17);
    }
    /**
     * Quick utility method to get a randomized numeric string
     * @param length - length of random string
     */
    public static String getRandomNumericString(int length) {
        return getRandomString(NUMERIC, length);
    }
    /**
     * Quick utility method to get a Map<String, String> for given query from url
     * @param query - query from url and must start with '?'
     */
    public static Map<String, String> formQueryMap(String query) {
        Map<String, String> queryMap = new HashMap<>();
        query = query.substring(query.indexOf('?') + 1);
        Arrays.asList(query.split("&")).forEach(pair ->
                queryMap.put(pair.substring(0, pair.indexOf('=')), pair.substring(pair.indexOf('=') + 1)));
        return queryMap;
    }

    /**
     * Quick utility method to get a Multimap<String, String> for given query from url
     * @param query - query from url and must start with '?'
     */
    public static Multimap<String, String> formQueryMultiMap(String query) {
        Multimap<String, String> queryMap = ArrayListMultimap.create();
        query = query.substring(query.indexOf('?') + 1);
        Arrays.asList(query.split("&")).forEach(pair ->
                queryMap.put(pair.substring(0, pair.indexOf('=')), pair.substring(pair.indexOf('=') + 1)));
        return queryMap;
    }
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static List<String> toList(String str) {
        return new ArrayList<>(Collections.singleton(str));
    }

    /**
     * This class going to have all Date related utility methods needed for this framework
     */
    public static class Date {

        Date() {}

        private static String getDate(int variance, int adjust, String format) {
            Calendar now = Calendar.getInstance();
            now.add(variance, adjust);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
            return simpleDateFormat.format(now.getTime());
        }

        private static String getDate(int variance, int adjust, String zone,  String format) {
            Calendar now = Calendar.getInstance();
            now.add(variance, adjust);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(zone));
            return simpleDateFormat.format(now.getTime());
        }

        private static String getDate(int variance, int adjust) {
            return getDate(variance, adjust, DEFAULT_DATE_FORMAT);
        }
        /**
         * Quick utility method to get current datentime plus given minutes
         * @param mins - minutes to add
         */
        public static String  datePlusMins(int mins) {
            return getDate(Calendar.MINUTE, mins);
        }

        public static String  datePlusMins(int mins, String zone, String format) {
            return getDate(Calendar.MINUTE, mins, zone, format);
        }
        /**
         * Quick utility method to get current datentime plus given seconds
         * @param secs - minutes to add
         */
        public static String  datePlusSecs(int secs) {
            return getDate(Calendar.SECOND, secs);
        }
        /**
         * Quick utility method to get current datentime plus given days
         * @param days - days to add
         */
        public static String  datePlusDays(int days) {
            return getDate(Calendar.DAY_OF_MONTH, days);
        }

        /**
         * Quick utility method to get current datentime plus given days
         * @param days - days to add
         */
        public static String  datePlusDays(int days, String format) {
            return getDate(Calendar.DAY_OF_MONTH, days, format);
        }
        /**
         * Quick utility method to get current datentime plus given months
         * @param months - months to add
         */
        public static String  datePlusMonths(int months) {
            return getDate(Calendar.MONTH, months);
        }
        /**
         * Quick utility method to get current datentime plus given years
         * @param years - years to add
         */
        public static String  datePlusYears(int years) {
            return getDate(Calendar.YEAR, years);
        }
        /**
         * Quick utility method to get current datentime plus given minutes without zone
         * @param mins - minutes to add
         */
        public static String datePlusMinsNoZone(int mins) {
            return getDate(Calendar.MINUTE, mins, "yyyy-MM-dd'T'HH:mm:ss.SSS");
        }

        public static boolean isTimePassed(String utc) throws ParseException {
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            df2.setTimeZone(TimeZone.getTimeZone("GMT"));
            java.util.Date launchTime = df2.parse(utc);
            return java.util.Date.from(Instant.now()).compareTo(launchTime) >= 1;
        }

        public static String currentEpoch2String() {
            return currentEpoch2String(false);
        }

        public static String currentEpoch2String(boolean withMilliSeconds) {
            return  withMilliSeconds ? Long.toString(Instant.now().toEpochMilli()) :
                    Long.toString(Instant.now().getEpochSecond());
        }

        //Human Readable
        public static String epoch2HRString(String epocTimeString, String timeFormat, String timeZone) {
            java.util.Date date = new java.util.Date(Long.parseLong(epocTimeString) * 1000L);
            DateFormat format = new SimpleDateFormat(timeFormat);
            format.setTimeZone(TimeZone.getTimeZone(timeZone));
            return format.format(date);
        }

        public static String epoch2HRString(String epocTimeString) {
            return epoch2HRString(epocTimeString, "yyyy-MM-dd HH:mm:ss.SSS", "Etc/UTC");
        }

        //Human Readable
        public static String currentEpoch2HRString() {
            return epoch2HRString(currentEpoch2String(), "yyyy-MM-dd HH:mm:ss.SSS", "Etc/UTC");
        }
    }
}
