public class Randomaccessmemory {





















































































































    public int getStartAddress() {
        return startAddress;
    }


    public int getSize() {
        return size;
    }


    public short[] getData() {
        return data;
    }






































    public int read(boolean isByteMode, int address) {
        return isByteMode ? readByte(address) : readWord(address);
    }


    public boolean write(boolean isByteMode, int address, int value) {
        if (isByteMode) {
            writeByte(address, value);
        } else {
            writeWord(address, value);
        }
        return true;
    }
































}
