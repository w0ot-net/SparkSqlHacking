package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import py4j.Gateway;
import py4j.Py4JException;
import py4j.Py4JJavaServer;
import py4j.Py4JServerConnection;

public class ShutdownGatewayServerCommand extends AbstractCommand {
   private Py4JJavaServer gatewayServer;
   public static final String SHUTDOWN_GATEWAY_SERVER_COMMAND_NAME = "s";

   public ShutdownGatewayServerCommand() {
      this.commandName = "s";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      this.gatewayServer.shutdown();
   }

   public void init(Gateway gateway, Py4JServerConnection connection) {
      super.init(gateway, connection);
      this.gatewayServer = (Py4JJavaServer)gateway.getObject("GATEWAY_SERVER");
   }
}
