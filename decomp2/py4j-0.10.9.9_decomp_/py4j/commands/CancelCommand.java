package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import py4j.Gateway;
import py4j.Py4JException;
import py4j.Py4JJavaServer;
import py4j.Py4JServerConnection;

public class CancelCommand extends AbstractCommand {
   private Py4JJavaServer gatewayServer;

   public CancelCommand() {
      this.commandName = "z";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String address = reader.readLine();
      int remotePort = Integer.parseInt(reader.readLine());
      int localPort = Integer.parseInt(reader.readLine());
      if (this.gatewayServer != null) {
         this.gatewayServer.shutdownSocket(address, remotePort, localPort);
      }

   }

   public void init(Gateway gateway, Py4JServerConnection connection) {
      super.init(gateway, connection);
      Object serverObj = gateway.getObject("GATEWAY_SERVER");
      if (serverObj != null && serverObj instanceof Py4JJavaServer) {
         this.gatewayServer = (Py4JJavaServer)serverObj;
      }

   }
}
