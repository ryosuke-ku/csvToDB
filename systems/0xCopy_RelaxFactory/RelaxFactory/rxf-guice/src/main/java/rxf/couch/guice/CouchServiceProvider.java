public class Couchserviceprovider {



















  public T get() {
    try {
      return CouchServiceFactory.get(type, namespace);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
