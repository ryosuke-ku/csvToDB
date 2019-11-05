public class Mimetype {


























































    public String getType() {
        return this.type;
    }





    public String getSubtype() {
        return this.subtype;
    }





    public Map<String, String> getParameters() {
        return parameters;
    }












    public static MimeType valueOf(String stringValue) throws IllegalArgumentException {
        if (stringValue == null) throw new IllegalArgumentException("String value is null");

        String params = null;
        int semicolonIndex = stringValue.indexOf(";");
        if (semicolonIndex > -1) {
            params = stringValue.substring(semicolonIndex + 1).trim();
            stringValue = stringValue.substring(0, semicolonIndex);
        }
        String major = null;
        String subtype = null;
        String[] paths = stringValue.split("/");

        if (paths.length < 2 && stringValue.equals(WILDCARD)) {

            major = WILDCARD;
            subtype = WILDCARD;

        } else if (paths.length == 2) {

            major = paths[0].trim();
            subtype = paths[1].trim();

        } else if (paths.length != 2) {

            throw new IllegalArgumentException("Error parsing string: " + stringValue);
        }

        if (params != null && params.length() > 0) {
            HashMap<String, String> map = new HashMap();

            int start = 0;

            while (start < params.length()) {
                start = readParamsIntoMap(map, params, start);
            }
            return new MimeType(major, subtype, map);
        } else {
            return new MimeType(major, subtype);
        }
    }






















































































    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(toStringNoParameters());
        if (getParameters() != null || getParameters().size() > 0) {
            for (String name : getParameters().keySet()) {
                sb.append(";").append(name).append("=\"").append(getParameters().get(name)).append("\"");
            }
        }
        return sb.toString();
    }





}
