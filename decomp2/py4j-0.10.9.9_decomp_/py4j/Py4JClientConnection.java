package py4j;

import java.io.IOException;

public interface Py4JClientConnection {
   String sendCommand(String var1);

   String sendCommand(String var1, boolean var2);

   void shutdown();

   void shutdown(boolean var1);

   void start() throws IOException;

   void setUsed(boolean var1);

   boolean wasUsed();
}
