public class Httpconnector {

































































































































	public void shutdown() {
		try {
			srvScok.get().close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	

















	public HttpResponse send(Node to, HttpRequest req) throws IOException {
		HttpHost host = new HttpHost(to.getInetAddress().getHostAddress(), to.getPort(scheme));
		
		HttpContext context = httpContextProvider.get();
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);
		
		DefaultHttpClientConnection conn = connProvider.get();
		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		Socket socket = new Socket(host.getHostName(), host.getPort());
		HttpResponse response = null;
		try {
	        conn.bind(socket, clientParams);
	        req.setParams(clientParams);
	        clientExecutor.preProcess(req, clientProcessor, context);
			response = clientExecutor.execute(req, conn, context);
	        response.setParams(clientParams);
			clientExecutor.postProcess(response, clientProcessor, context);
			
			if (response.getEntity() != null) {
				ByteArrayEntity entity = new ByteArrayEntity(EntityUtils.toByteArray(response.getEntity()));
				response.setEntity(entity);
			}
			
		} catch (HttpException e) {
			throw new IOException(e);
		} finally {
			conn.close();
		}
		
		return response;
	}
	

}
