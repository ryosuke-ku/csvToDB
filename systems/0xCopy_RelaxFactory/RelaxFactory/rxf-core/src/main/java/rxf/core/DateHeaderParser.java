public class Dateheaderparser {





























































  public static Date parseDate(CharSequence dateValue) {

    char c = dateValue.charAt(0);
    switch (c) {
      case '\'':
      case '"':

        dateValue = dateValue.subSequence(1, dateValue.length() - 1);
      default:
        break;
    }
    String source = dateValue.toString();
    for (DateHeaderParser dateHeaderParser : values()) {
      try {
        return dateHeaderParser.format.parse(source);
      } catch (ParseException e) {
        if (false) {
          System.err.println(".--" + dateHeaderParser.name() + " failed parse: " + source);

        }
      }
    }
    return null;
  }








}
