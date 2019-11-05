public class Timespanhelper {




























    public static int getIntFromTimeSpan(String timeSpan) {
        int returnint = 0;
        String[] times = timeSpan.split(":");
        if(times.length == 3) {
            returnint += Integer.parseInt(times[0]) * 60 * 60 * 1000;
            returnint += Integer.parseInt(times[1]) * 60 * 1000;
            returnint += Integer.parseInt(times[2]) * 1000;
        }
        return returnint;
    }

    public static String getTimeSpanFromInt(int timeSpan) throws Exception {
        throw new Exception("NotImplementedException");
    }
    



































}
