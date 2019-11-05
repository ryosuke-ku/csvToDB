public class Timer {







































































































































    public int read(long cpuTime, int address) {
        switch (address) {
            case PRESET_REGISTER_ADDRESS:
                return getPresetRegister();
            case CONTROL_REGISTER_ADDRESS:
                return getControlRegister(cpuTime);
            default:
                return getCounterRegister(cpuTime);
        }
    }


    public boolean write(long cpuTime, boolean isByteMode, int address, int value) {
        switch (address & 0177776) {
            case PRESET_REGISTER_ADDRESS:
                setPresetRegister(cpuTime, value);
                break;
            case CONTROL_REGISTER_ADDRESS:
                setControlRegister(cpuTime, value);
                break;
            default:
                // Counter register is read only
                break;
        }
        return true;
    }




























































































}
