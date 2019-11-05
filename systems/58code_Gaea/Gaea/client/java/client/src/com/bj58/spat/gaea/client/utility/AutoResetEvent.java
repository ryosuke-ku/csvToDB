public class Autoresetevent {






































    public void set() {
        cdl.countDown();
    }

    public boolean waitOne(long time) throws InterruptedException {
        return cdl.await(time, TimeUnit.MILLISECONDS);
    }
}
