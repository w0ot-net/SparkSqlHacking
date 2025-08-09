package org.apache.derby.impl.store.replication.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javax.net.SocketFactory;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;

public class ReplicationMessageTransmit {
   private final int DEFAULT_MESSAGE_RESPONSE_TIMEOUT = 30000;
   private final Object receiveSemaphore = new Object();
   private ReplicationMessage receivedMsg = null;
   private volatile boolean stopMessageReceiver = false;
   private final SlaveAddress slaveAddress;
   private SocketConnection socketConn;
   private String dbname;

   public ReplicationMessageTransmit(SlaveAddress var1) {
      this.slaveAddress = var1;
   }

   public void initConnection(int var1, long var2) throws IOException, StandardException, ClassNotFoundException {
      Object var4 = null;
      SocketFactory var6 = SocketFactory.getDefault();
      InetSocketAddress var7 = new InetSocketAddress(this.slaveAddress.getHostAddress(), this.slaveAddress.getPortNumber());
      Socket var8 = var6.createSocket();
      var8.connect(var7, var1);
      var8.setKeepAlive(true);
      this.socketConn = new SocketConnection(var8);
      this.startMessageReceiverThread(this.dbname);
      this.brokerConnection(var2);
   }

   public void tearDown() throws IOException {
      this.stopMessageReceiver = true;
      if (this.socketConn != null) {
         this.socketConn.tearDown();
         this.socketConn = null;
      }

   }

   public void sendMessage(ReplicationMessage var1) throws IOException {
      this.checkSocketConnection();
      this.socketConn.writeMessage(var1);
   }

   public synchronized ReplicationMessage sendMessageWaitForReply(ReplicationMessage var1) throws IOException, StandardException {
      this.receivedMsg = null;
      this.checkSocketConnection();
      this.socketConn.writeMessage(var1);
      long var2 = System.currentTimeMillis();

      for(long var4 = 0L; this.receivedMsg == null && var4 < 30000L; var4 = System.currentTimeMillis() - var2) {
         synchronized(this.receiveSemaphore) {
            try {
               this.receiveSemaphore.wait(30000L - var4);
               break;
            } catch (InterruptedException var9) {
               InterruptStatus.setInterrupted();
            }
         }
      }

      if (this.receivedMsg == null) {
         throw StandardException.newException("XRE04.C.2", new Object[]{this.dbname});
      } else {
         return this.receivedMsg;
      }
   }

   private void brokerConnection(long var1) throws IOException, StandardException, ClassNotFoundException {
      ReplicationMessage var3 = new ReplicationMessage(0, 1L);
      this.verifyMessageType(this.sendMessageWaitForReply(var3), 11);
      var3 = new ReplicationMessage(1, var1);
      this.verifyMessageType(this.sendMessageWaitForReply(var3), 11);
   }

   private boolean verifyMessageType(ReplicationMessage var1, int var2) throws StandardException {
      if (var1.getType() == var2) {
         return true;
      } else if (var1.getType() == 12) {
         String[] var3 = (String[])var1.getMessage();
         throw StandardException.newException(var3[var3.length - 1], var3);
      } else {
         throw StandardException.newException("XRE03", new Object[0]);
      }
   }

   private void checkSocketConnection() throws IOException {
      if (this.socketConn == null) {
         throw new IOException("R012");
      }
   }

   private void startMessageReceiverThread(String var1) {
      MasterReceiverThread var2 = new MasterReceiverThread(var1);
      var2.setDaemon(true);
      var2.start();
   }

   private class MasterReceiverThread extends Thread {
      private final ReplicationMessage pongMsg = new ReplicationMessage(14, (Object)null);

      MasterReceiverThread(String var2) {
         super("derby.master.receiver-" + var2);
      }

      public void run() {
         while(!ReplicationMessageTransmit.this.stopMessageReceiver) {
            try {
               ReplicationMessage var1 = this.readMessage();
               switch (var1.getType()) {
                  case 11:
                  case 12:
                     synchronized(ReplicationMessageTransmit.this.receiveSemaphore) {
                        ReplicationMessageTransmit.this.receivedMsg = var1;
                        ReplicationMessageTransmit.this.receiveSemaphore.notify();
                        break;
                     }
                  case 13:
                     ReplicationMessageTransmit.this.sendMessage(this.pongMsg);
               }
            } catch (SocketTimeoutException var5) {
            } catch (ClassNotFoundException var6) {
            } catch (IOException var7) {
               ReplicationMessageTransmit.this.stopMessageReceiver = true;
            }
         }

      }

      private ReplicationMessage readMessage() throws ClassNotFoundException, IOException {
         ReplicationMessageTransmit.this.checkSocketConnection();
         return (ReplicationMessage)ReplicationMessageTransmit.this.socketConn.readMessage();
      }
   }
}
