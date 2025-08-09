package org.apache.spark.launcher;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

class LauncherServer implements Closeable {
   private static final Logger LOG = Logger.getLogger(LauncherServer.class.getName());
   private static final String THREAD_NAME_FMT = "LauncherServer-%d";
   private static final long DEFAULT_CONNECT_TIMEOUT = 10000L;
   private static final SecureRandom RND = new SecureRandom();
   private static volatile LauncherServer serverInstance;
   private final AtomicLong refCount = new AtomicLong(0L);
   private final AtomicLong threadIds;
   private final ConcurrentMap secretToPendingApps;
   private final List clients;
   private final ServerSocket server;
   private final Thread serverThread;
   private final ThreadFactory factory;
   private final Timer timeoutTimer;
   private volatile boolean running;

   static synchronized LauncherServer getOrCreateServer() throws IOException {
      LauncherServer server;
      do {
         server = serverInstance != null ? serverInstance : new LauncherServer();
      } while(!server.running);

      server.ref();
      serverInstance = server;
      return server;
   }

   static synchronized LauncherServer getServer() {
      return serverInstance;
   }

   private LauncherServer() throws IOException {
      ServerSocket server = new ServerSocket();

      try {
         server.setReuseAddress(true);
         server.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0));
         this.clients = new ArrayList();
         this.threadIds = new AtomicLong();
         this.factory = new NamedThreadFactory("LauncherServer-%d");
         this.secretToPendingApps = new ConcurrentHashMap();
         this.timeoutTimer = new Timer("LauncherServer-TimeoutTimer", true);
         this.server = server;
         this.running = true;
         this.serverThread = this.factory.newThread(this::acceptConnections);
         this.serverThread.start();
      } catch (IOException ioe) {
         this.close();
         throw ioe;
      } catch (Exception e) {
         this.close();
         throw new IOException(e);
      }
   }

   synchronized String registerHandle(AbstractAppHandle handle) {
      String secret = this.createSecret();
      this.secretToPendingApps.put(secret, handle);
      return secret;
   }

   public void close() throws IOException {
      synchronized(this) {
         if (!this.running) {
            return;
         }

         this.running = false;
      }

      synchronized(LauncherServer.class) {
         serverInstance = null;
      }

      this.timeoutTimer.cancel();
      this.server.close();
      synchronized(this.clients) {
         List<ServerConnection> copy = new ArrayList(this.clients);
         this.clients.clear();

         for(ServerConnection client : copy) {
            client.close();
         }
      }

      if (this.serverThread != null) {
         try {
            this.serverThread.join();
         } catch (InterruptedException var6) {
         }
      }

   }

   void ref() {
      this.refCount.incrementAndGet();
   }

   void unref() {
      synchronized(LauncherServer.class) {
         if (this.refCount.decrementAndGet() == 0L) {
            try {
               this.close();
            } catch (IOException var4) {
            }
         }

      }
   }

   int getPort() {
      return this.server.getLocalPort();
   }

   void unregister(AbstractAppHandle handle) {
      for(Map.Entry e : this.secretToPendingApps.entrySet()) {
         if (((AbstractAppHandle)e.getValue()).equals(handle)) {
            String secret = (String)e.getKey();
            this.secretToPendingApps.remove(secret);
            break;
         }
      }

      this.unref();
   }

   private void acceptConnections() {
      Thread clientThread;
      try {
         for(; this.running; clientThread.start()) {
            final Socket client = this.server.accept();
            TimerTask timerTask = new TimerTask() {
               public void run() {
                  LauncherServer.LOG.warning("Timed out waiting for hello message from client.");

                  try {
                     client.close();
                  } catch (IOException var2) {
                  }

               }
            };
            ServerConnection clientConnection = new ServerConnection(client, timerTask);
            clientThread = this.factory.newThread(clientConnection);
            clientConnection.setConnectionThread(clientThread);
            synchronized(this.clients) {
               this.clients.add(clientConnection);
            }

            long timeoutMs = this.getConnectionTimeout();
            if (timeoutMs > 0L) {
               this.timeoutTimer.schedule(timerTask, timeoutMs);
            } else {
               timerTask.run();
            }
         }
      } catch (IOException ioe) {
         if (this.running) {
            LOG.log(Level.SEVERE, "Error in accept loop.", ioe);
         }
      }

   }

   private long getConnectionTimeout() {
      String value = (String)SparkLauncher.launcherConfig.get("spark.launcher.childConnectionTimeout");
      if (value != null) {
         return Long.parseLong(value);
      } else {
         value = (String)SparkLauncher.launcherConfig.get("spark.launcher.childConectionTimeout");
         if (value != null) {
            LOG.log(Level.WARNING, "Property 'spark.launcher.childConectionTimeout' is deprecated, please switch to 'spark.launcher.childConnectionTimeout'.");
            return Long.parseLong(value);
         } else {
            return 10000L;
         }
      }
   }

   private String createSecret() {
      String secretStr;
      do {
         byte[] secret = new byte[128];
         RND.nextBytes(secret);
         StringBuilder sb = new StringBuilder();

         for(byte b : secret) {
            int ival = b >= 0 ? b : 127 - b;
            if (ival < 16) {
               sb.append("0");
            }

            sb.append(Integer.toHexString(ival));
         }

         secretStr = sb.toString();
      } while(this.secretToPendingApps.containsKey(secretStr));

      return secretStr;
   }

   class ServerConnection extends LauncherConnection {
      private TimerTask timeout;
      private volatile Thread connectionThread;
      private volatile AbstractAppHandle handle;

      ServerConnection(Socket socket, TimerTask timeout) throws IOException {
         super(socket);
         this.timeout = timeout;
      }

      void setConnectionThread(Thread t) {
         this.connectionThread = t;
      }

      protected void handle(LauncherProtocol.Message msg) throws IOException {
         try {
            if (msg instanceof LauncherProtocol.Hello hello) {
               this.timeout.cancel();
               this.timeout = null;
               AbstractAppHandle handle = (AbstractAppHandle)LauncherServer.this.secretToPendingApps.remove(hello.secret);
               if (handle == null) {
                  throw new IllegalArgumentException("Received Hello for unknown client.");
               }

               handle.setConnection(this);
               handle.setState(SparkAppHandle.State.CONNECTED);
               this.handle = handle;
            } else {
               String msgClassName = msg != null ? msg.getClass().getName() : "no message";
               if (this.handle == null) {
                  throw new IllegalArgumentException("Expected hello, got: " + msgClassName);
               }

               if (msg instanceof LauncherProtocol.SetAppId set) {
                  this.handle.setAppId(set.appId);
               } else {
                  if (!(msg instanceof LauncherProtocol.SetState)) {
                     throw new IllegalArgumentException("Invalid message: " + msgClassName);
                  }

                  LauncherProtocol.SetState setState = (LauncherProtocol.SetState)msg;
                  this.handle.setState(setState.state);
               }
            }
         } catch (Exception e) {
            LauncherServer.LOG.log(Level.INFO, "Error handling message from client.", e);
            if (this.timeout != null) {
               this.timeout.cancel();
            }

            this.close();
            if (this.handle != null) {
               this.handle.dispose();
            }
         } finally {
            LauncherServer.this.timeoutTimer.purge();
         }

      }

      public void close() throws IOException {
         if (this.isOpen()) {
            synchronized(LauncherServer.this.clients) {
               LauncherServer.this.clients.remove(this);
            }

            super.close();
         }
      }

      public void waitForClose() throws IOException {
         Thread connThread = this.connectionThread;
         if (Thread.currentThread() != connThread) {
            try {
               connThread.join(LauncherServer.this.getConnectionTimeout());
            } catch (InterruptedException var3) {
            }

            if (connThread.isAlive()) {
               LauncherServer.LOG.log(Level.WARNING, "Timed out waiting for child connection to close.");
               this.close();
            }
         }

      }
   }
}
