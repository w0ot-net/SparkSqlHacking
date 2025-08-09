package py4j;

public interface GatewayServerListener {
   void connectionError(Exception var1);

   void connectionStarted(Py4JServerConnection var1);

   void connectionStopped(Py4JServerConnection var1);

   void serverError(Exception var1);

   void serverPostShutdown();

   void serverPreShutdown();

   void serverStarted();

   void serverStopped();
}
