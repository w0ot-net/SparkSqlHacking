package py4j;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import javax.net.ServerSocketFactory;
import py4j.commands.Command;

public class JavaServer extends GatewayServer {
   public JavaServer(Object entryPoint, int port, int connectTimeout, int readTimeout, List customCommands, Py4JPythonClientPerThread pythonClient) {
      this(entryPoint, port, connectTimeout, readTimeout, customCommands, pythonClient, (String)null);
   }

   public JavaServer(Object entryPoint, int port, int connectTimeout, int readTimeout, List customCommands, Py4JPythonClientPerThread pythonClient, String authToken) {
      super((Object)entryPoint, port, defaultAddress(), connectTimeout, readTimeout, customCommands, (Py4JPythonClient)pythonClient, (ServerSocketFactory)ServerSocketFactory.getDefault(), (String)authToken);
   }

   protected Py4JServerConnection createConnection(Gateway gateway, Socket socket) throws IOException {
      ClientServerConnection connection = new ClientServerConnection(gateway, socket, this.getCustomCommands(), (Py4JPythonClientPerThread)this.getCallbackClient(), this, this.getReadTimeout(), this.authToken);
      connection.startServerConnection();
      return connection;
   }
}
