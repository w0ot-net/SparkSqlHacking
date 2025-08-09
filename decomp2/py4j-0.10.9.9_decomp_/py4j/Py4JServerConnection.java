package py4j;

import java.net.Socket;

public interface Py4JServerConnection {
   Socket getSocket();

   void shutdown();

   void shutdown(boolean var1);
}
