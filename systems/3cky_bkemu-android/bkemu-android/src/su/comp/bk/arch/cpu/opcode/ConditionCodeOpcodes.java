public class Conditioncodeopcodes {













































































    public void execute() {
        int instruction = getInstruction();
        int psw = getCpu().getPswState();
        int conditionMask = instruction & 017;
        psw = (instruction & 020) != 0 ? (psw | conditionMask) : (psw & ~conditionMask);
        getCpu().setPswState((short) psw);
    }

}
