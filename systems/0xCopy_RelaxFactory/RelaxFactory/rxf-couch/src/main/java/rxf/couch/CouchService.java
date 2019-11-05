public class Couchservice {





























































    public CouchTx addAttachment(String content, String fileName, String contentType) {
      JsonSendActionBuilder actionBuilder =
          new JsonSend().opaque(db + "/" + id + "/" + fileName + "?rev=" + rev).validjson(content)
              .to();
      actionBuilder.hdr().headerString(HttpHeaders.Content$2dType, contentType);
      CouchTx tx = actionBuilder.fire().tx();
      rev = tx.rev();
      return tx;
    }

    public Writer addAttachment(final String fileName, final String contentType) {
      return new StringWriter() {

        public void close() throws IOException {
          JsonSendActionBuilder actionBuilder =
              new JsonSend().opaque(db + "/" + id + "/" + fileName + "?rev=" + rev).validjson(
                  getBuffer().toString()).to();
          actionBuilder.hdr().headerString(HttpHeaders.Content$2dType, contentType);
          CouchTx tx = actionBuilder.fire().tx();
          if (!tx.ok()) {
            throw new IOException(tx.error());
          }
          rev = tx.rev();
        }
      };
    }

    public CouchTx updateAttachment(String content, String fileName, String contentType) {
      JsonSendActionBuilder actionBuilder =
          new JsonSend().opaque(db + "/" + id + "/" + fileName + "?rev=" + rev).validjson(content)
              .to();
      actionBuilder.hdr().headerString(HttpHeaders.Content$2dType, contentType);
      CouchTx tx = actionBuilder.fire().tx();
      rev = tx.rev();
      return tx;
    }

    public Writer updateAttachment(final String fileName, final String contentType) {
      return new StringWriter() {

        public void close() throws IOException {
          JsonSendActionBuilder actionBuilder =
              new JsonSend().opaque(db + "/" + id + "/" + fileName + "?rev=" + rev).validjson(
                  getBuffer().toString()).to();
          actionBuilder.hdr().headerString(HttpHeaders.Content$2dType, contentType);
          CouchTx tx = actionBuilder.fire().tx();
          if (!tx.ok()) {
            throw new IOException(tx.error());
          }
          rev = tx.rev();
        }
      };
    }

    public String getAttachment(String fileName) {
      return new DocFetch().db(db).docId(id + "/" + fileName).to().fire().json();
    }




















































































































































































}
