public class Portmapping {














































































































    public boolean isEnabled() {
        return enabled;
    }





    public UnsignedIntegerFourBytes getLeaseDurationSeconds() {
        return leaseDurationSeconds;
    }





    public boolean hasRemoteHost() {
        return remoteHost != null && remoteHost.length() > 0;
    }









    public UnsignedIntegerTwoBytes getExternalPort() {
        return externalPort;
    }





    public UnsignedIntegerTwoBytes getInternalPort() {
        return internalPort;
    }





    public String getInternalClient() {
        return internalClient;
    }





    public Protocol getProtocol() {
        return protocol;
    }





    public boolean hasDescription() {
        return description != null;
    }

    public String getDescription() {
        return description == null ? "-" : description;
    }









}
