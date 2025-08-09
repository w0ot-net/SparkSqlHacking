package py4j;

public interface Py4JPythonClientPerThread extends Py4JPythonClient {
   ClientServerConnection getPerThreadConnection();

   void setPerThreadConnection(ClientServerConnection var1);

   Gateway getGateway();

   void setGateway(Gateway var1);

   Py4JJavaServer getJavaServer();

   void setJavaServer(Py4JJavaServer var1);
}
