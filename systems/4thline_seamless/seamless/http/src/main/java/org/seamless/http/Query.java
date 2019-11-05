public class Query {



























































































    public String get(String name) {
        List<String> values = parameters.get(name);
        if (values == null)
            return "";

        if (values.size() == 0)
            return "";

        return values.get(0);
    }

    public String[] getValues(String name) {
        List<String> values = parameters.get(name);
        if (values == null)
            return null;

        return values.toArray(new String[values.size()]);
    }

    public List<String> getValuesAsList(String name) {
        return parameters.containsKey(name)
            ? Collections.unmodifiableList(parameters.get(name))
            : null;
    }





    public Map<String, String[]> getMap() {
        Map<String, String[]> map = new TreeMap<String, String[]>();
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            List<String> list = entry.getValue();
            String[] values;
            if (list == null)
                values = null;
            else
                values = list.toArray(new String[list.size()]);
            map.put(entry.getKey(), values);
        }
        return map;
    }

    public Map<String, List<String>> getMapWithLists() {
        return Collections.unmodifiableMap(parameters);
    }

    public boolean isEmpty() {
        return parameters.size() == 0;
    }

    public Query cloneAndAdd(String name, String... values) {
        Map<String, List<String>> params = new HashMap(getMapWithLists());
        List<String> existingValues = params.get(name);
        if (existingValues == null) {
            existingValues = new ArrayList<String>();
            params.put(name, existingValues);
        }
        existingValues.addAll(Arrays.asList(values));
        return Query.newInstance(params);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            for (String v : entry.getValue()) {
                if (v == null || v.length() == 0) continue;
                if (sb.length() > 0) sb.append("&");
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(v);
            }
        }
        return sb.toString();
    }
}
