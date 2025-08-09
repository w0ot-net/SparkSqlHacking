package org.apache.derby.impl.store.replication.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ServerSocketFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.store.raw.log.LogCounter;
import org.apache.derby.shared.common.error.StandardException;

public class ReplicationMessageReceive {
   private final SlaveAddress slaveAddress;
   private ServerSocket serverSocket;
   private SocketConnection socketConn;
   private static final int DEFAULT_PING_TIMEOUT = 5000;
   private Thread pingThread = null;
   private boolean killPingThread = false;
   private boolean connectionConfirmed = false;
   private final Object sendPingSemaphore = new Object();
   private boolean doSendPing = false;
   private final Object receivePongSemaphore = new Object();

   public ReplicationMessageReceive(SlaveAddress var1, String var2) {
      this.slaveAddress = var1;
      Monitor.logTextMessage("R011", var2, var1.getHostAddress().getHostName(), String.valueOf(var1.getPortNumber()));
   }

   public void initConnection(int var1, long var2, String var4) throws IOException, StandardException, ClassNotFoundException {
      if (this.serverSocket == null) {
         this.serverSocket = this.createServerSocket();
      }

      this.serverSocket.setSoTimeout(var1);
      Object var5 = null;
      Socket var6 = this.serverSocket.accept();
      this.socketConn = new SocketConnection(var6);
      this.parseAndAckVersion(this.readMessage(), var4);
      this.parseAndAckInstant(this.readMessage(), var2, var4);
      this.killPingThread = false;
      this.pingThread = new SlavePingThread(var4);
      this.pingThread.setDaemon(true);
      this.pingThread.start();
   }

   private ServerSocket createServerSocket() throws IOException {
      Object var1 = null;
      ServerSocketFactory var2 = ServerSocketFactory.getDefault();
      ServerSocket var3 = var2.createServerSocket(this.slaveAddress.getPortNumber(), 0, this.slaveAddress.getHostAddress());
      return var3;
   }

   public void tearDown() throws IOException {
      synchronized(this.sendPingSemaphore) {
         this.killPingThread = true;
         this.sendPingSemaphore.notify();
      }

      try {
         if (this.socketConn != null) {
            this.socketConn.tearDown();
         }
      } finally {
         if (this.serverSocket != null) {
            this.serverSocket.close();
         }

      }

   }

   private void parseAndAckVersion(ReplicationMessage var1, String var2) throws IOException, StandardException {
      Object var3 = null;
      if (var1.getType() != 0) {
         String var4 = String.valueOf(0);
         String var5 = String.valueOf(var1.getType());
         this.handleUnexpectedMessage(var2, var4, var5);
      }

      long var8 = (Long)var1.getMessage();
      if (var8 == 1L) {
         ReplicationMessage var7 = new ReplicationMessage(11, "UID OK");
         this.sendMessage(var7);
      } else {
         ReplicationMessage var6 = new ReplicationMessage(12, new String[]{"XRE02"});
         this.sendMessage(var6);
         throw StandardException.newException("XRE02", new Object[0]);
      }
   }

   private void parseAndAckInstant(ReplicationMessage var1, long var2, String var4) throws IOException, StandardException {
      Object var5 = null;
      if (var1.getType() != 1) {
         String var6 = String.valueOf(1);
         String var7 = String.valueOf(var1.getType());
         this.handleUnexpectedMessage(var4, var6, var7);
      }

      long var11 = (Long)var1.getMessage();
      if (var11 == var2) {
         ReplicationMessage var10 = new ReplicationMessage(11, "Instant OK");
         this.sendMessage(var10);
      } else {
         String[] var8 = new String[]{var4, String.valueOf(LogCounter.getLogFileNumber(var11)), String.valueOf(LogCounter.getLogFilePosition(var11)), String.valueOf(LogCounter.getLogFileNumber(var2)), String.valueOf(LogCounter.getLogFilePosition(var2)), "XRE05.C"};
         ReplicationMessage var9 = new ReplicationMessage(12, var8);
         this.sendMessage(var9);
         throw StandardException.newException("XRE05.C", var8);
      }
   }

   private void handleUnexpectedMessage(String var1, String var2, String var3) throws StandardException, IOException {
      String[] var4 = new String[]{var1, var2, var3, "XRE12"};
      ReplicationMessage var5 = new ReplicationMessage(12, var4);
      this.sendMessage(var5);
      throw StandardException.newException("XRE12", var4);
   }

   public void sendMessage(ReplicationMessage var1) throws IOException {
      this.checkSocketConnection();
      this.socketConn.writeMessage(var1);
   }

   public ReplicationMessage readMessage() throws ClassNotFoundException, IOException {
      this.checkSocketConnection();
      ReplicationMessage var1 = (ReplicationMessage)this.socketConn.readMessage();
      if (var1.getType() == 14) {
         synchronized(this.receivePongSemaphore) {
            this.connectionConfirmed = true;
            this.receivePongSemaphore.notify();
         }

         return this.readMessage();
      } else {
         return var1;
      }
   }

   private void checkSocketConnection() throws IOException {
      if (this.socketConn == null) {
         throw new IOException("R012");
      }
   }

   public synchronized boolean isConnectedToMaster() {
      synchronized(this.receivePongSemaphore) {
         this.connectionConfirmed = false;
         long var6 = 5000L;
         long var4;
         synchronized(this.sendPingSemaphore) {
            this.doSendPing = true;
            this.sendPingSemaphore.notify();
            long var2 = System.currentTimeMillis();
            var4 = var2 + 5000L;
         }

         do {
            try {
               this.receivePongSemaphore.wait(var6);
            } catch (InterruptedException var11) {
               InterruptStatus.setInterrupted();
            }

            var6 = var4 - System.currentTimeMillis();
         } while(!this.connectionConfirmed && var6 > 0L);
      }

      return this.connectionConfirmed;
   }

   private class SlavePingThread extends Thread {
      private final ReplicationMessage pingMsg = new ReplicationMessage(13, (Object)null);

      SlavePingThread(String var2) {
         super("derby.slave.ping-" + var2);
      }

      public void run() {
         try {
            while(!ReplicationMessageReceive.this.killPingThread) {
               synchronized(ReplicationMessageReceive.this.sendPingSemaphore) {
                  while(!ReplicationMessageReceive.this.doSendPing) {
                     try {
                        ReplicationMessageReceive.this.sendPingSemaphore.wait();
                     } catch (InterruptedException var4) {
                        InterruptStatus.setInterrupted();
                     }
                  }

                  ReplicationMessageReceive.this.doSendPing = false;
               }

               if (ReplicationMessageReceive.this.killPingThread) {
                  break;
               }

               ReplicationMessageReceive.this.sendMessage(this.pingMsg);
            }
         } catch (IOException var6) {
         }

      }
   }
}
