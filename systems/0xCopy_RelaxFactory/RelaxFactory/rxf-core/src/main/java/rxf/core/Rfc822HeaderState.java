public class Rfc822headerstate {



















































































































  public static Rfc822HeaderState hdr() {
    return new Rfc822HeaderState();
  }

  public String headerString(HttpHeaders httpHeader) {
    return headerString(httpHeader.getHeader()); // To change body of created methods use File | Settings | File
    // Templates.
  }
















    public String method() {
      return methodProtocol(); // To change body of overridden methods use File | Settings | File Templates.
    }

    public HttpRequest method(HttpMethod method) {
      return method(method.name()); // To change body of overridden methods use File | Settings | File Templates.
    }

    public HttpRequest method(String s) {
      return (HttpRequest) methodProtocol(s);
    }

    public String path() {
      return pathResCode(); // To change body of overridden methods use File | Settings | File Templates.
    }

    public HttpRequest path(String path) {
      return (HttpRequest) pathResCode(path);
    }

    public String protocol() {
      return protocolStatus(); // To change body of overridden methods use File | Settings | File Templates.
    }

    public HttpRequest protocol(String protocol) {
      return (HttpRequest) protocolStatus(protocol); // To change body of overridden methods use File | Settings | File
      // Templates.
    }

    public String toString() {
      return asRequestHeaderString();
    }

    public <T> T as(Class<T> clazz) {
      if (ByteBuffer.class.equals(clazz)) {
        if (null == protocol()) {
          protocol(HTTP_1_1);
        }
        return (T) asByteBuffer();
      }
      return (T) super.as(clazz);
    }








    public ByteBuffer asByteBuffer() {
      String protocol = asRequestHeaderString();
      return ByteBuffer.wrap(protocol.getBytes(UTF_8));
    }

































































    public String toString() {
      return asResponseHeaderString();
    }

    public String protocol() {
      return methodProtocol();
    }









    public HttpResponse protocol(String protocol) {
      return (HttpResponse) methodProtocol(protocol);
    }























    public <T> T as(Class<T> clazz) {
      if (ByteBuffer.class.equals(clazz)) {
        if (null == protocol()) {
          protocol(HTTP_1_1);
        }
        return (T) asByteBuffer();
      }
      return super.as(clazz); // To change body of overridden methods use File | Settings | File Templates.

    }









    public ByteBuffer asByteBuffer() {
      String protocol = asResponseHeaderString();
      return ByteBuffer.wrap(protocol.getBytes(UTF_8));
    }
  }















  public String toString() {
    return "Rfc822HeaderState:{" + "headerInterest:" + deepToString(headerInterest())
        + ", cookies:" + cookies + ", sourceRoute:" + sourceRoute() + ", headerBuf:" + headerBuf()
        + ", headerStrings:" + headerStrings() + ", methodProtocol:" + methodProtocol()
        + ", pathRescode:" + pathResCode() + ", protocolStatus:" + protocolStatus()
        + ", sourceKey:" + sourceKey() + '}';
  }

  public <T> T as(final Class<T> clazz) {
    if (clazz.equals(HttpResponse.class)) {
      return (T) $res();

    } else if (clazz.equals(HttpRequest.class)) {
      return (T) $req();
    } else if (clazz.equals(String.class)) {
      return (T) toString();
    } else if (clazz.equals(ByteBuffer.class)) {
      throw new UnsupportedOperationException(
          "must promote to as((HttpRequest|HttpResponse)).class first");
    } else
      throw new UnsupportedOperationException("don't know how to infer " + clazz.getCanonicalName());

  }












  public ByteBuffer asByteBuffer() {
    throw new UnsupportedOperationException(
        "must promote to as((HttpRequest|HttpResponse)).class first");

  }









































































  public Rfc822HeaderState headerString(HttpHeaders hdrEnum, String s) {
    return headerString(hdrEnum.getHeader().trim(), s); // To change body of created methods use File | Settings | File
    // Templates.
  }






















































































































  public Rfc822HeaderState read(ByteBuffer cursor) {
    if (!cursor.hasRemaining()) {
      cursor.flip();
    }
    int anchor = cursor.position();
    ByteBuffer slice = cursor.duplicate().slice();
    while (slice.hasRemaining() && SPC != slice.get()) {
    }
    methodProtocol.set(UTF_8.decode((ByteBuffer) slice.flip()).toString().trim());

    while (cursor.hasRemaining() && SPC != cursor.get()) {
      // method/proto
    }
    slice = cursor.slice();
    while (slice.hasRemaining() && SPC != slice.get()) {
    }
    pathRescode.set(UTF_8.decode((ByteBuffer) slice.flip()).toString().trim());

    while (cursor.hasRemaining() && SPC != cursor.get()) {
    }
    slice = cursor.slice();
    while (slice.hasRemaining() && LF != slice.get()) {
    }
    protocolStatus.set(UTF_8.decode((ByteBuffer) slice.flip()).toString().trim());

    boolean wantsCookies = null != cookies;
    boolean wantsHeaders = wantsCookies || 0 < headerInterest.get().length;
    moveCaretToDoubleEol(cursor);
    headerBuf = (ByteBuffer) (cursor).duplicate().flip();
    headerStrings().clear();
    if (wantsHeaders) {
      Map<String, int[]> headerMap = HttpHeaders.getHeaders((ByteBuffer) headerBuf.rewind());
      headerStrings.set(new LinkedHashMap<String, String>());
      for (String o : headerInterest.get()) {
        int[] o1 = headerMap.get(o);
        if (null != o1) {
          headerStrings.get().put(
              o,
              UTF_8.decode((ByteBuffer) headerBuf.duplicate().clear().position(o1[0]).limit(o1[1]))
                  .toString().trim());
        }
      }

    }
    return this;
  }

































































  public Rfc822HeaderState headerInterest(HttpHeaders... replaceInterest) {
    String[] strings = staticHeaderStrings(replaceInterest);
    return headerInterest(strings);
  }









  public Rfc822HeaderState headerInterest(String... replaceInterest) {
    headerInterest.set(replaceInterest);
    return this;
  }

  public Rfc822HeaderState addHeaderInterest(HttpHeaders... appendInterest) {
    String[] strings = staticHeaderStrings(appendInterest);
    return addHeaderInterest(strings);
  }















  public Rfc822HeaderState addHeaderInterest(String... newInterest) {

    // adds a few more instructions than the blind append but does what was desired
    Set<String> theCow = new CopyOnWriteArraySet<>(Arrays.<String> asList(headerInterest.get()));
    theCow.addAll(asList(newInterest));
    String[] strings = theCow.toArray(new String[theCow.size()]);
    Arrays.sort(strings);
    headerInterest.set(strings);
    return this;
  }






  public String[] headerInterest() {
    headerInterest.compareAndSet(null, new String[] {});
    return headerInterest.get();
  }












































  public Rfc822HeaderState headerStrings(Map<String, String> headerStrings) {
    this.headerStrings.set(headerStrings);
    return this;
  }













  public Map<String, String> headerStrings() {
    headerStrings.compareAndSet(null, new LinkedHashMap<String, String>());
    return headerStrings.get();
  }





  public String methodProtocol() {
    return methodProtocol.get();
  }





  public Rfc822HeaderState methodProtocol(String methodProtocol) {
    this.methodProtocol.set(methodProtocol);
    return this;
  }










  public String pathResCode() {
    return pathRescode.get();
  }





  public Rfc822HeaderState pathResCode(String pathRescode) {
    this.pathRescode.set(pathRescode);
    return this;
  }







  public String protocolStatus() {
    return protocolStatus.get();
  }




  public Rfc822HeaderState protocolStatus(String protocolStatus) {
    this.protocolStatus.set(protocolStatus);
    return this;
  }














































  public String headerString(String headerKey) {
    return headerStrings().get(headerKey); // To change body of created methods use File | Settings | File Templates.
  }




















  public Rfc822HeaderState headerString(String key, String val) {
    headerStrings().put(key, val);
    return this;
  }


























}
