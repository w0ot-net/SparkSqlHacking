package jakarta.ws.rs.client;

public interface InvocationCallback {
   void completed(Object var1);

   void failed(Throwable var1);
}
