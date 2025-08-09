package py4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkUtil {
   private static final Logger logger = Logger.getLogger(NetworkUtil.class.getName());

   public static String safeReadLine(BufferedReader reader, boolean addSpace) throws IOException {
      String line = reader.readLine();
      if (line == null || line.length() == 0 && addSpace) {
         if (addSpace) {
            line = " ";
         } else {
            line = "";
         }
      }

      return line;
   }

   public static String safeReadLine(BufferedReader reader) throws IOException {
      return safeReadLine(reader, true);
   }

   public static void quietlyClose(Closeable closeable) {
      try {
         if (closeable != null) {
            closeable.close();
         }
      } catch (Exception e) {
         logger.log(Level.FINE, "Closeable cannot be closed.", e);
      }

   }

   public static void quietlyClose(ServerSocket closeable) {
      try {
         if (closeable != null) {
            closeable.close();
         }
      } catch (Exception e) {
         logger.log(Level.FINE, "Socket cannot be closed.", e);
      }

   }

   public static void quietlyClose(Socket closeable) {
      try {
         if (closeable != null) {
            closeable.close();
         }
      } catch (Exception e) {
         logger.log(Level.FINE, "Socket cannot be closed.", e);
      }

   }

   public static void quietlySetLinger(Socket socket) {
      try {
         socket.setSoLinger(true, 0);
      } catch (Exception e) {
         logger.log(Level.FINE, "Cannot set linger on socket.", e);
      }

   }

   static void authToServer(BufferedReader reader, BufferedWriter writer, String authToken) throws IOException {
      writer.write(Protocol.getAuthCommand(authToken));
      writer.flush();
      String returnCommand = reader.readLine();
      if (returnCommand == null || !returnCommand.equals(Protocol.getOutputVoidCommand().trim())) {
         logger.log(Level.SEVERE, "Could not authenticate connection. Received this response: " + returnCommand);
         throw new IOException("Authentication with callback server unsuccessful.");
      }
   }
}
