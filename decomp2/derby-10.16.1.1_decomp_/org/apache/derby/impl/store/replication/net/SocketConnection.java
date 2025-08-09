package org.apache.derby.impl.store.replication.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection {
   private final Socket socket;
   private final ObjectOutputStream objOutputStream;
   private final ObjectInputStream objInputStream;

   public SocketConnection(Socket var1) throws IOException {
      this.socket = var1;
      this.objOutputStream = new ObjectOutputStream(var1.getOutputStream());
      this.objInputStream = new ObjectInputStream(var1.getInputStream());
   }

   public Object readMessage() throws ClassNotFoundException, IOException {
      return this.objInputStream.readObject();
   }

   public void writeMessage(Object var1) throws IOException {
      this.objOutputStream.reset();
      this.objOutputStream.writeObject(var1);
      this.objOutputStream.flush();
   }

   public void tearDown() throws IOException {
      try {
         this.objInputStream.close();
         this.objOutputStream.close();
      } finally {
         this.socket.close();
      }

   }
}
