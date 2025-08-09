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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import py4j.commands.AuthCommand;
import py4j.commands.Command;

public class ClientServerConnection implements Py4JServerConnection, Py4JClientConnection, Runnable {
   private boolean used;
   private boolean initiatedFromClient;
   protected Socket socket;
   protected BufferedWriter writer;
   protected BufferedReader reader;
   protected final Map commands;
   protected final Logger logger;
   protected final Py4JJavaServer javaServer;
   protected final Py4JPythonClientPerThread pythonClient;
   protected final int blockingReadTimeout;
   protected final int nonBlockingReadTimeout;
   protected final String authToken;
   protected final AuthCommand authCommand;
   protected Thread jvmThread;

   public ClientServerConnection(Gateway gateway, Socket socket, List customCommands, Py4JPythonClientPerThread pythonClient, Py4JJavaServer javaServer, int readTimeout) throws IOException {
      this(gateway, socket, customCommands, pythonClient, javaServer, readTimeout, (String)null);
   }

   public ClientServerConnection(Gateway gateway, Socket socket, List customCommands, Py4JPythonClientPerThread pythonClient, Py4JJavaServer javaServer, int readTimeout, String authToken) throws IOException {
      this.used = false;
      this.initiatedFromClient = false;
      this.logger = Logger.getLogger(ClientServerConnection.class.getName());
      this.socket = socket;
      this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
      this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
      this.commands = new HashMap();
      this.initCommands(gateway, GatewayConnection.getBaseCommands());
      if (customCommands != null) {
         this.initCommands(gateway, customCommands);
      }

      this.javaServer = javaServer;
      this.pythonClient = pythonClient;
      this.blockingReadTimeout = readTimeout;
      if (readTimeout > 0) {
         this.nonBlockingReadTimeout = readTimeout;
      } else {
         this.nonBlockingReadTimeout = 1000;
      }

      this.authToken = authToken;
      if (authToken != null) {
         this.authCommand = new AuthCommand(authToken);
         this.initCommand(gateway, this.authCommand);
      } else {
         this.authCommand = null;
      }

   }

   public void startServerConnection() throws IOException {
      this.jvmThread = new Thread(this);
      this.jvmThread.start();
   }

   public void run() {
      this.pythonClient.setPerThreadConnection(this);
      this.waitForCommands();
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

   protected void fireConnectionStopped() {
      this.logger.info("Connection Stopped");

      for(GatewayServerListener listener : this.javaServer.getListeners()) {
         try {
            listener.connectionStopped(this);
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "A listener crashed.", e);
         }
      }

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

   public Socket getSocket() {
      return this.socket;
   }

   public void waitForCommands() {
      boolean reset = false;
      boolean executing = false;
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
         reset = true;
         error = ste;
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

   public String sendCommand(String command) {
      return this.sendCommand(command, true);
   }

   public String sendCommand(String command, boolean blocking) {
      this.logger.log(Level.INFO, "Sending Python command: " + command);
      String returnCommand = null;

      try {
         this.writer.write(command);
         this.writer.flush();
      } catch (Exception e) {
         throw new Py4JNetworkException("Error while sending a command: " + command, e, Py4JNetworkException.ErrorTime.ERROR_ON_SEND);
      }

      try {
         while(true) {
            if (blocking) {
               returnCommand = this.readBlockingResponse(this.reader);
            } else {
               returnCommand = this.readNonBlockingResponse(this.socket, this.reader);
            }

            if (returnCommand == null || returnCommand.trim().equals("")) {
               throw new Py4JException("Received empty command");
            }

            if (Protocol.isReturnMessage(returnCommand)) {
               returnCommand = returnCommand.substring(1);
               this.logger.log(Level.INFO, "Returning CB command: " + returnCommand);
               return returnCommand;
            }

            Command commandObj = (Command)this.commands.get(returnCommand);
            if (commandObj != null) {
               commandObj.execute(returnCommand, this.reader, this.writer);
            } else {
               this.logger.log(Level.WARNING, "Unknown command " + returnCommand);
            }
         }
      } catch (Exception e) {
         throw new Py4JNetworkException("Error while sending a command: " + command, e, Py4JNetworkException.ErrorTime.ERROR_ON_RECEIVE);
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
      this.socket = null;
      this.writer = null;
      this.reader = null;
      if (!this.initiatedFromClient) {
         this.fireConnectionStopped();
      }

      if (this.jvmThread != null && this.jvmThread.isAlive()) {
         this.jvmThread.interrupt();
      }

   }

   public void start() throws IOException {
      if (this.authToken != null) {
         try {
            NetworkUtil.authToServer(this.reader, this.writer, this.authToken);
         } catch (IOException ioe) {
            this.shutdown(true);
            throw ioe;
         }
      }

   }

   public void setUsed(boolean used) {
      this.used = used;
   }

   public boolean wasUsed() {
      return this.used;
   }

   public boolean isInitiatedFromClient() {
      return this.initiatedFromClient;
   }

   public void setInitiatedFromClient(boolean initiatedFromClient) {
      this.initiatedFromClient = initiatedFromClient;
   }

   protected String readBlockingResponse(BufferedReader reader) throws IOException {
      return reader.readLine();
   }

   protected String readNonBlockingResponse(Socket socket, BufferedReader reader) throws IOException {
      String returnCommand = null;
      socket.setSoTimeout(this.nonBlockingReadTimeout);

      try {
         returnCommand = reader.readLine();
      } finally {
         socket.setSoTimeout(this.blockingReadTimeout);
      }

      socket.setSoTimeout(this.blockingReadTimeout);
      return returnCommand;
   }
}
