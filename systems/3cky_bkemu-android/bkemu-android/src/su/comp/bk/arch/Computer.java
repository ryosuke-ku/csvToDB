public class Computer {










































































































































































































































































































































































































































    public Cpu getCpu() {
        return cpu;
    }





















































    public void addMemory(Memory memory) {
        int memoryStartBlock = memory.getStartAddress() >> 13;
        int memoryBlocksCount = memory.getSize() >> 12; // ((size << 1) >> 13)
        if ((memory.getSize() & 07777) != 0) {
            memoryBlocksCount++;
        }
        for (int memoryBlockIdx = 0; memoryBlockIdx < memoryBlocksCount; memoryBlockIdx++) {
            memoryTable[memoryStartBlock + memoryBlockIdx] = memory;
        }
        // Correct devices start address, if needed
        int memoryEndAddress = memory.getStartAddress() + (memory.getSize() << 1);
        if (getDevicesStartAddress() < memoryEndAddress) {
            setDevicesStartAddress(Math.min(memoryEndAddress, IO_REGISTERS_MAX_ADDRESS));
        }
    }





    public void addDevice(Device device) {
        deviceList.add(device);
        int[] deviceAddresses = device.getAddresses();
        for (int deviceAddress : deviceAddresses) {
            int deviceTableIndex = (deviceAddress - IO_REGISTERS_MIN_ADDRESS) >> 1;
            @SuppressWarnings("unchecked")
            List<Device> addressDevices = (List<Device>) deviceTable[deviceTableIndex];
            if (addressDevices == null) {
                addressDevices = new ArrayList<Device>(1);
                deviceTable[deviceTableIndex] = addressDevices;
            }
            addressDevices.add(device);
        }
    }

















































    public synchronized void reset() {
        getCpu().reset();
    }








    public int readMemory(boolean isByteMode, int address) {
        int readValue = BUS_ERROR;
        // First check for I/O registers
        if (address >= getDevicesStartAddress()) {
            List<Device> subdevices = getDevices(address);
            if (subdevices != null) {
                long cpuClock = getCpu().getTime();
                readValue = 0;
                for (Device subdevice: subdevices) {
                    // Read subdevice state value in word mode
                    int subdeviceState = subdevice.read(cpuClock, address & 0177776);
                    // For byte mode read and odd address - extract high byte
                    if (isByteMode && (address & 1) != 0) {
                        subdeviceState >>= 8;
                    }
                    // Concatenate this subdevice state value with values of other subdevices
                    readValue |= (subdeviceState & (isByteMode ? 0377 : 0177777));
                }
            }
        } else {
            // Check for memory at given address
            Memory memory = getMemory(address);
            if (memory != null) {
                readValue = memory.read(isByteMode, address);
            }
        }
        return readValue;
    }









    public boolean writeMemory(boolean isByteMode, int address, int value) {
        boolean isWritten = false;
        // First check for I/O registers
        if (address >= getDevicesStartAddress()) {
            List<Device> devices = getDevices(address);
            if (devices != null) {
                long cpuClock = getCpu().getTime();
                for (Device device: devices) {
                    if (device.write(cpuClock, isByteMode, address, value)) {
                        isWritten = true;
                    }
                }

            }
        } else {
            // Check for memory at given address
            Memory memory = getMemory(address);
            if (memory != null) {
                isWritten = memory.write(isByteMode, address, value);
            }
        }
        return isWritten;
    }














































































































































































}
