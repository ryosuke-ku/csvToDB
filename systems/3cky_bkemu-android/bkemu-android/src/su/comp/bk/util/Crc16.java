public class Crc16 {


































    public static short calculate(short crcValue, byte data) {
      short x = (short) (((crcValue >>> 8) ^ data) & 0xff);
      x ^= (x >>> 4);
      return (short) ((crcValue << 8) ^ (x << 12) ^ (x << 5) ^ x);
    }








    public static short calculate(byte[] data, int offset, int length) {
        short crcValue = INIT_VALUE;
        int counter = length;
        int index = offset;
        while (counter-- > 0) {
            crcValue = calculate(crcValue, data[index++]);
        }
        return crcValue;
    }







    public static short calculate(byte[] data) {
        return calculate(data, 0, data.length);
    }
}
