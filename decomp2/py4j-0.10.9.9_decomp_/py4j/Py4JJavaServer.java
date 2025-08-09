package py4j;

import java.net.InetAddress;
import java.util.List;

public interface Py4JJavaServer {
   List getListeners();

   InetAddress getAddress();

   Gateway getGateway();

   int getListeningPort();

   int getPort();

   InetAddress getPythonAddress();

   int getPythonPort();

   void removeListener(GatewayServerListener var1);

   void shutdown();

   void shutdown(boolean var1);

   void shutdownSocket(String var1, int var2, int var3);

   void addListener(GatewayServerListener var1);

   void start();

   void start(boolean var1);
}
