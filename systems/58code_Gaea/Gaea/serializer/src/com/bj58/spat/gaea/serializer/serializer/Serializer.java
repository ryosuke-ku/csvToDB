public class Serializer {









































































    public byte[] Serialize(Object obj) throws Exception {
        GaeaOutStream stream = null;
        try {
            stream = new GaeaOutStream();
            stream.Encoder = _Encoder;
            if (obj == null) {
                SerializerFactory.GetSerializer(null).WriteObject(obj, stream);
            } else {
                Class type = obj.getClass();
                if (obj instanceof IGaeaSerializer) {
                    ((IGaeaSerializer) obj).Serialize(stream);
                } else {
                    SerializerFactory.GetSerializer(type).WriteObject(obj, stream);
                }
            }
            byte[] result = stream.toByteArray();
            return result;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }





    public Object Derialize(byte[] buffer, Class type) throws Exception {
        GaeaInStream stream = null;
        try {
            stream = new GaeaInStream(buffer);
            stream.Encoder = _Encoder;
            if (ClassHelper.InterfaceOf(type, IGaeaSerializer.class)) {
                IGaeaSerializer obj = (IGaeaSerializer) type.newInstance();
                obj.Derialize(stream);
                return obj;
            }
            return SerializerFactory.GetSerializer(type).ReadObject(stream, type);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
