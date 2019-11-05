public class Jsonconvert {
































	public Boolean convertToBoolean(Object obj) {
		return new Boolean(obj.toString());
	}


	public Byte convertToByte(Object obj) {
		return new Byte(obj.toString());
	}


	public Character convertToCharacter(Object obj) {
		String str = obj.toString();
		if(str.length() > 1) {
			str = str.replaceFirst("\"", "");
		}
		if(!str.equals(null) && !str.equals("")){
			return new Character(str.charAt(0));
		}
		return new Character('\0');
	}


	public Double convertToDouble(Object obj) {
		return new Double(obj.toString());
	}


	public Float convertToFloat(Object obj) {
		return new Float(obj.toString());
	}


	public Integer convertToInteger(Object obj) {
		return new Integer(obj.toString());
	}


	public Long convertToLong(Object obj) {
		return new Long(obj.toString());
	}


	public Short convertToShort(Object obj) {
		return new Short(obj.toString());
	}


	public String convertToString(Object obj) {
		if(obj == null) {
			return "";
		}
		return obj.toString();
	}


	public boolean convertToboolean(Object obj) {
		return Boolean.parseBoolean(obj.toString());
	}


	public byte convertTobyte(Object obj) {
		return Byte.parseByte(obj.toString());
	}


	public char convertTochar(Object obj) {
		String str = obj.toString();
		if(str.length() > 1) {
			str = str.replaceFirst("\"", "");
		}
		if(!str.equals(null) && !str.equals("")){
			return str.charAt(0);
		}
		return '\0';
	}


	public double convertTodouble(Object obj) {
		return Double.parseDouble(obj.toString());
	}


	public float convertTofloat(Object obj) {
		return Float.parseFloat(obj.toString());
	}


	public int convertToint(Object obj) {
		return Integer.parseInt(obj.toString());
	}


	public long convertTolong(Object obj) {
		return Long.parseLong(obj.toString());
	}


	public short convertToshort(Object obj) {
		return Short.parseShort(obj.toString());
	}


	public Object convertToT(Object obj, Class<?> clazz) throws Exception {
		if(obj == null || obj.toString().equalsIgnoreCase("")) {
			return null;
		}
		return JsonHelper.toJava(obj.toString(), clazz);
	}
	

	public Object convertToT(Object obj, Class<?> containClass, Class<?> itemClass) throws Exception {
		if(obj == null || obj.toString().equalsIgnoreCase("")) {
			return null;
		}
		return JsonHelper.toJava(obj.toString(), containClass, itemClass);
	}
}
