public class Sel1registersystembits {




























































    public int read(long cpuTime, int address) {
        // Return current state and clear write flag
        int result = state;
        setWriteFlagState(false);
        return result;
    }


    public boolean write(long cpuTime, boolean isByteMode, int address, int value) {
        // Only set write flag
        setWriteFlagState(true);
        return true;
    }












}
