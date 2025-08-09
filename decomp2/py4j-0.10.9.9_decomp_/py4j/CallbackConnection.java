package py4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;

public class CallbackConnection implements Py4JClientConnection {
   private boolean used;
   public static final int DEFAULT_NONBLOCKING_SO_TIMEOUT = 1000;
   private final int port;
   private final InetAddress address;
   private final SocketFactory socketFactory;
   private Socket socket;
   private BufferedReader reader;
   private BufferedWriter writer;
   private final Logger logger;
   private final int blockingReadTimeout;
   private final int nonBlockingReadTimeout;
   private final String authToken;

   public CallbackConnection(int port, InetAddress address) {
      this(port, address, SocketFactory.getDefault());
   }

   public CallbackConnection(int port, InetAddress address, SocketFactory socketFactory) {
      this(port, address, socketFactory, 0);
   }

   public CallbackConnection(int port, InetAddress address, SocketFactory socketFactory, int readTimeout) {
      this(port, address, socketFactory, readTimeout, (String)null);
   }

   public CallbackConnection(int port, InetAddress address, SocketFactory socketFactory, int readTimeout, String authToken) {
      this.logger = Logger.getLogger(CallbackConnection.class.getName());
      this.port = port;
      this.address = address;
      this.socketFactory = socketFactory;
      this.blockingReadTimeout = readTimeout;
      if (readTimeout > 0) {
         this.nonBlockingReadTimeout = readTimeout;
      } else {
         this.nonBlockingReadTimeout = 1000;
      }

      this.authToken = authToken;
   }

   public String sendCommand(String command) {
      return this.sendCommand(command, true);
   }

   public String sendCommand(String command, boolean blocking) {
      this.logger.log(Level.INFO, "Sending CB command: " + command);
      String returnCommand = null;

      try {
         this.used = true;
         this.writer.write(command);
         this.writer.flush();
      } catch (Exception e) {
         throw new Py4JNetworkException("Error while sending a command: null response: " + command, e, Py4JNetworkException.ErrorTime.ERROR_ON_SEND);
      }

      try {
         if (blocking) {
            returnCommand = this.readBlockingResponse(this.reader);
         } else {
            returnCommand = this.readNonBlockingResponse(this.socket, this.reader);
         }
      } catch (Exception e) {
         throw new Py4JNetworkException("Error while sending a command: " + command, e, Py4JNetworkException.ErrorTime.ERROR_ON_RECEIVE);
      }

      if (returnCommand == null) {
         throw new Py4JNetworkException("Error while sending a command: null response: " + command, Py4JNetworkException.ErrorTime.ERROR_ON_RECEIVE);
      } else {
         if (Protocol.isReturnMessage(returnCommand)) {
            returnCommand = returnCommand.substring(1);
         }

         this.logger.log(Level.INFO, "Returning CB command: " + returnCommand);
         return returnCommand;
      }
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

   public void setUsed(boolean used) {
      this.used = used;
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
   }

   public void start() throws IOException {
      this.logger.info("Starting Communication Channel on " + this.address + " at " + this.port);
      this.socket = this.socketFactory.createSocket(this.address, this.port);
      this.socket.setSoTimeout(this.blockingReadTimeout);
      this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), Charset.forName("UTF-8")));
      this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), Charset.forName("UTF-8")));
      if (this.authToken != null) {
         try {
            NetworkUtil.authToServer(this.reader, this.writer, this.authToken);
         } catch (IOException ioe) {
            this.shutdown(true);
            throw ioe;
         }
      }

   }

   public boolean wasUsed() {
      return this.used;
   }
}
