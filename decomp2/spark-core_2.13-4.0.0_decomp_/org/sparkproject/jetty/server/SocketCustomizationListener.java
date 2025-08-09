package org.sparkproject.jetty.server;

import java.net.Socket;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.SocketChannelEndPoint;
import org.sparkproject.jetty.io.ssl.SslConnection;

public class SocketCustomizationListener implements Connection.Listener {
   private final boolean _ssl;

   public SocketCustomizationListener() {
      this(true);
   }

   public SocketCustomizationListener(boolean ssl) {
      this._ssl = ssl;
   }

   public void onOpened(Connection connection) {
      EndPoint endPoint = connection.getEndPoint();
      boolean ssl = false;
      if (this._ssl && endPoint instanceof SslConnection.DecryptedEndPoint) {
         endPoint = ((SslConnection.DecryptedEndPoint)endPoint).getSslConnection().getEndPoint();
         ssl = true;
      }

      if (endPoint instanceof SocketChannelEndPoint) {
         Socket socket = ((SocketChannelEndPoint)endPoint).getChannel().socket();
         this.customize(socket, connection.getClass(), ssl);
      }

   }

   protected void customize(Socket socket, Class connection, boolean ssl) {
   }

   public void onClosed(Connection connection) {
   }
}
