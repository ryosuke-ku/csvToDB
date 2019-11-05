public class Messagedispatcher {





























































































	void handle(KadMessage msg) {
		assert (shouldHandleMessage(msg));
		
		if (isDone.get())
			return;
		
		if (timeoutTimerTask != null)
			timeoutTimerTask.cancel();
		
		outstandingRequests.remove(this);
		if (isConsumbale) {
			expecters.remove(this);
			if (!isDone.compareAndSet(false, true))
				return;
		}
		
		if (callback != null)
			callback.completed(msg, attachment);
	}
	
















	public MessageDispatcher<A> setConsumable(boolean consume) {
		isConsumbale = consume;
		return this;
	}
	
	public MessageDispatcher<A> register() {
		expecters.add(this);
		setupTimeout();
		return this;
	}
	

	public Future<KadMessage> futureRegister() {
		
		FutureCallback<KadMessage, A> f = new FutureCallback<KadMessage, A>() {
			@Override
			public synchronized boolean cancel(boolean mayInterruptIfRunning) {
				MessageDispatcher.this.cancel(new CancellationException());
				return super.cancel(mayInterruptIfRunning);
			};
		};
		
		setCallback(null, f);
		expecters.add(this);
		setupTimeout();
		
		return f;
	}
	







			public void run() {
				MessageDispatcher.this.cancel(new TimeoutException());
			}
		};



	public void send(Node to, KadRequest req) {
		setConsumable(true);
		try {
			/*
			if (!outstandingRequests.offer(this, timeout, TimeUnit.MILLISECONDS))
				throw new RejectedExecutionException();
			*/
			outstandingRequests.put(this);
			expecters.add(this);
			communicator.send(to, req);
			
			setupTimeout();
			
		} catch (Exception e) {
			cancel(e);
		}
	}
	
	public Future<KadMessage> futureSend(Node to, KadRequest req) {
		
		FutureCallback<KadMessage, A> f = new FutureCallback<KadMessage, A>();
		setCallback(null, f);
		
		send(to, req);
		
		return f;
	}
}
