package py4j;

import java.net.InetAddress;

public interface Py4JPythonClient {
   String sendCommand(String var1);

   String sendCommand(String var1, boolean var2);

   void shutdown();

   Py4JPythonClient copyWith(InetAddress var1, int var2);

   boolean isMemoryManagementEnabled();

   int getPort();

   int getReadTimeout();

   InetAddress getAddress();

   Object getPythonServerEntryPoint(Gateway var1, Class[] var2);
}
