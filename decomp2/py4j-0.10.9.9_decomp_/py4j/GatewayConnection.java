package py4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import py4j.commands.ArrayCommand;
import py4j.commands.AuthCommand;
import py4j.commands.CallCommand;
import py4j.commands.CancelCommand;
import py4j.commands.Command;
import py4j.commands.ConstructorCommand;
import py4j.commands.DirCommand;
import py4j.commands.ExceptionCommand;
import py4j.commands.FieldCommand;
import py4j.commands.HelpPageCommand;
import py4j.commands.JVMViewCommand;
import py4j.commands.ListCommand;
import py4j.commands.MemoryCommand;
import py4j.commands.ReflectionCommand;
import py4j.commands.ShutdownGatewayServerCommand;
import py4j.commands.StreamCommand;

public class GatewayConnection implements Runnable, Py4JServerConnection {
   private static final List baseCommands = new ArrayList();
   protected final Socket socket;
   protected final String authToken;
   protected final AuthCommand authCommand;
   protected final BufferedWriter writer;
   protected final BufferedReader reader;
   protected final Map commands;
   protected final Logger logger;
   protected final List listeners;

   public static List getBaseCommands() {
      return baseCommands;
   }

   public GatewayConnection(Gateway gateway, Socket socket) throws IOException {
      this(gateway, socket, (List)null, new ArrayList());
   }

   public GatewayConnection(Gateway gateway, Socket socket, List customCommands, List listeners) throws IOException {
      this(gateway, socket, (String)null, customCommands, listeners);
   }

   public GatewayConnection(Gateway gateway, Socket socket, String authToken, List customCommands, List listeners) throws IOException {
      this.logger = Logger.getLogger(GatewayConnection.class.getName());
      this.socket = socket;
      this.authToken = authToken;
      if (authToken != null) {
         this.authCommand = new AuthCommand(authToken);
      } else {
         this.authCommand = null;
      }

      this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
      this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
      this.commands = new HashMap();
      this.initCommands(gateway, baseCommands);
      if (customCommands != null) {
         this.initCommands(gateway, customCommands);
      }

      if (this.authCommand != null) {
         this.initCommand(gateway, this.authCommand);
      }

      this.listeners = listeners;
   }

   public void startConnection() {
      Thread t = new Thread(this);
      t.start();
   }

   protected void fireConnectionStopped() {
      this.logger.info("Connection Stopped");

      for(GatewayServerListener listener : this.listeners) {
         try {
            listener.connectionStopped(this);
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "A listener crashed.", e);
         }
      }

   }

   public Socket getSocket() {
      return this.socket;
   }

   protected void initCommands(Gateway gateway, List commandsClazz) {
      for(Class clazz : commandsClazz) {
         try {
            Command cmd = (Command)clazz.newInstance();
            this.initCommand(gateway, cmd);
         } catch (Exception e) {
            String name = "null";
            if (clazz != null) {
               name = clazz.getName();
            }

            this.logger.log(Level.SEVERE, "Could not initialize command " + name, e);
         }
      }

   }

   private void initCommand(Gateway gateway, Command cmd) {
      cmd.init(gateway, this);
      this.commands.put(cmd.getCommandName(), cmd);
   }

   protected void quietSendFatalError(BufferedWriter writer, Throwable exception) {
      try {
         String returnCommand = Protocol.getOutputFatalErrorCommand(exception);
         this.logger.fine("Trying to return error: " + returnCommand);
         writer.write(returnCommand);
         writer.flush();
      } catch (Exception e) {
         this.logger.log(Level.FINEST, "Error in quiet send.", e);
      }

   }

   public void run() {
      boolean executing = false;
      boolean reset = false;
      Throwable error = null;

      try {
         this.logger.info("Gateway Connection ready to receive messages");
         String commandLine = null;

         do {
            commandLine = this.reader.readLine();
            executing = true;
            this.logger.fine("Received command: " + commandLine);
            Command command = (Command)this.commands.get(commandLine);
            if (command == null) {
               reset = true;
               throw new Py4JException("Unknown command received: " + commandLine);
            }

            if (this.authCommand != null && !this.authCommand.isAuthenticated()) {
               this.authCommand.execute(commandLine, this.reader, this.writer);
            } else {
               command.execute(commandLine, this.reader, this.writer);
            }

            executing = false;
         } while(commandLine != null && !commandLine.equals("q"));
      } catch (SocketTimeoutException ste) {
         this.logger.log(Level.WARNING, "Timeout occurred while waiting for a command.", ste);
         error = ste;
         reset = true;
      } catch (Py4JAuthenticationException pae) {
         this.logger.log(Level.SEVERE, "Authentication error.", pae);
         reset = true;
      } catch (Exception e) {
         this.logger.log(Level.WARNING, "Error occurred while waiting for a command.", e);
         error = e;
      } finally {
         if (error != null && executing && this.writer != null) {
            this.quietSendFatalError(this.writer, error);
         }

         this.shutdown(reset);
      }

   }

   public void shutdown() {
      this.shutdown(false);
   }

   public void shutdown(boolean reset) {
      if (reset) {
         NetworkUtil.quietlySetLinger(this.socket);
      }

      NetworkUtil.quietlyClose(this.socket);
      NetworkUtil.quietlyClose((Closeable)this.reader);
      NetworkUtil.quietlyClose((Closeable)this.writer);
      this.fireConnectionStopped();
   }

   static {
      baseCommands.add(ArrayCommand.class);
      baseCommands.add(CallCommand.class);
      baseCommands.add(ConstructorCommand.class);
      baseCommands.add(FieldCommand.class);
      baseCommands.add(HelpPageCommand.class);
      baseCommands.add(ListCommand.class);
      baseCommands.add(MemoryCommand.class);
      baseCommands.add(ReflectionCommand.class);
      baseCommands.add(ShutdownGatewayServerCommand.class);
      baseCommands.add(CancelCommand.class);
      baseCommands.add(JVMViewCommand.class);
      baseCommands.add(ExceptionCommand.class);
      baseCommands.add(DirCommand.class);
      baseCommands.add(StreamCommand.class);
   }
}
