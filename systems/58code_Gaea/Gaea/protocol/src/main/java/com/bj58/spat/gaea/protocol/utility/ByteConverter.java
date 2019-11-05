public class Byteconverter {

































    public static int bytesToIntLittleEndian(byte buf[]) {
        return buf[0] & 0xff
                | ((buf[1] << 8) & 0xff00)
                | ((buf[2] << 16) & 0xff0000)
                | ((buf[3] << 24) & 0xff000000);
    }






    public static byte[] intToBytesLittleEndian(int n) {
        byte[] buf = new byte[4];
        buf[0] = (byte) (0xff & n);
        buf[1] = (byte) ((0xff00 & n) >> 8);
        buf[2] = (byte) ((0xff0000 & n) >> 16);
        buf[3] = (byte) ((0xff000000 & n) >> 24);
        return buf;
    }




























    public static int bytesToIntLittleEndian(byte buf[], int offset) {
        return buf[offset] & 0xff
                | ((buf[offset + 1] << 8) & 0xff00)
                | ((buf[offset + 2] << 16) & 0xff0000)
                | ((buf[offset + 3] << 24) & 0xff000000);
    }







}
