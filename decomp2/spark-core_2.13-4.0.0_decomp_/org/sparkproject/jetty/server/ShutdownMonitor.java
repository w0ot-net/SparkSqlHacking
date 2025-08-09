package org.sparkproject.jetty.server;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.component.Destroyable;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.ShutdownThread;

public class ShutdownMonitor {
   private final AutoLock.WithCondition _lock = new AutoLock.WithCondition();
   private final Set _lifeCycles = new LinkedHashSet();
   private boolean debug = System.getProperty("DEBUG") != null;
   private final String host = System.getProperty("STOP.HOST", "127.0.0.1");
   private int port = Integer.getInteger("STOP.PORT", -1);
   private String key = System.getProperty("STOP.KEY", (String)null);
   private boolean exitVm = true;
   private boolean alive;

   public static ShutdownMonitor getInstance() {
      return ShutdownMonitor.Holder.instance;
   }

   protected static void reset() {
      ShutdownMonitor.Holder.instance = new ShutdownMonitor();
   }

   public static void register(LifeCycle... lifeCycles) {
      getInstance().addLifeCycles(lifeCycles);
   }

   public static void deregister(LifeCycle lifeCycle) {
      getInstance().removeLifeCycle(lifeCycle);
   }

   public static boolean isRegistered(LifeCycle lifeCycle) {
      return getInstance().containsLifeCycle(lifeCycle);
   }

   private ShutdownMonitor() {
      this.exitVm = Boolean.valueOf(System.getProperty("STOP.EXIT", "true"));
   }

   private void addLifeCycles(LifeCycle... lifeCycles) {
      try (AutoLock l = this._lock.lock()) {
         this._lifeCycles.addAll(Arrays.asList(lifeCycles));
      }

   }

   private void removeLifeCycle(LifeCycle lifeCycle) {
      try (AutoLock l = this._lock.lock()) {
         this._lifeCycles.remove(lifeCycle);
      }

   }

   private boolean containsLifeCycle(LifeCycle lifeCycle) {
      try (AutoLock l = this._lock.lock()) {
         return this._lifeCycles.contains(lifeCycle);
      }
   }

   private void debug(String format, Object... args) {
      if (this.debug) {
         System.err.printf("[ShutdownMonitor] " + format + "%n", args);
      }

   }

   private void debug(Throwable t) {
      if (this.debug) {
         t.printStackTrace(System.err);
      }

   }

   public String getKey() {
      try (AutoLock l = this._lock.lock()) {
         return this.key;
      }
   }

   public int getPort() {
      try (AutoLock l = this._lock.lock()) {
         return this.port;
      }
   }

   public boolean isExitVm() {
      try (AutoLock l = this._lock.lock()) {
         return this.exitVm;
      }
   }

   public void setDebug(boolean flag) {
      this.debug = flag;
   }

   public void setExitVm(boolean exitVm) {
      try (AutoLock l = this._lock.lock()) {
         if (this.alive) {
            throw new IllegalStateException("ShutdownMonitor already started");
         }

         this.exitVm = exitVm;
      }

   }

   public void setKey(String key) {
      try (AutoLock l = this._lock.lock()) {
         if (this.alive) {
            throw new IllegalStateException("ShutdownMonitor already started");
         }

         this.key = key;
      }

   }

   public void setPort(int port) {
      try (AutoLock l = this._lock.lock()) {
         if (this.alive) {
            throw new IllegalStateException("ShutdownMonitor already started");
         }

         this.port = port;
      }

   }

   public void start() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         if (this.alive) {
            this.debug("Already started");
            return;
         }

         ServerSocket serverSocket = this.listen();
         if (serverSocket != null) {
            this.alive = true;
            Thread thread = new Thread(new ShutdownMonitorRunnable(serverSocket));
            thread.setDaemon(true);
            thread.setName("ShutdownMonitor");
            thread.start();
         }
      }

   }

   private void stop() {
      try (AutoLock.WithCondition l = this._lock.lock()) {
         this.alive = false;
         l.signalAll();
      }

   }

   public void await() throws InterruptedException {
      try (AutoLock.WithCondition l = this._lock.lock()) {
         while(this.alive) {
            l.await();
         }
      }

   }

   protected boolean isAlive() {
      try (AutoLock l = this._lock.lock()) {
         return this.alive;
      }
   }

   private ServerSocket listen() {
      int port = this.getPort();
      if (port < 0) {
         this.debug("Not enabled (port < 0): %d", port);
         return null;
      } else {
         String key = this.getKey();

         ServerSocket e;
         try {
            ServerSocket serverSocket = new ServerSocket();

            try {
               serverSocket.setReuseAddress(true);
               serverSocket.bind(new InetSocketAddress(InetAddress.getByName(this.host), port));
            } catch (Throwable e) {
               IO.close((Closeable)serverSocket);
               throw e;
            }

            if (port == 0) {
               port = serverSocket.getLocalPort();
               System.out.printf("STOP.PORT=%d%n", port);
               this.setPort(port);
            }

            if (key == null) {
               key = Long.toString((long)((double)Long.MAX_VALUE * Math.random() + (double)this.hashCode() + (double)System.currentTimeMillis()), 36);
               System.out.printf("STOP.KEY=%s%n", key);
               this.setKey(key);
            }

            e = serverSocket;
            return e;
         } catch (Throwable x) {
            this.debug(x);
            System.err.println("Error binding ShutdownMonitor to port " + port + ": " + x.toString());
            e = null;
         } finally {
            this.debug("STOP.PORT=%d", port);
            this.debug("STOP.KEY=%s", key);
            this.debug("STOP.EXIT=%b", this.exitVm);
         }

         return e;
      }
   }

   public String toString() {
      return String.format("%s[port=%d,alive=%b]", this.getClass().getName(), this.getPort(), this.isAlive());
   }

   private static class Holder {
      static ShutdownMonitor instance = new ShutdownMonitor();
   }

   private class ShutdownMonitorRunnable implements Runnable {
      private final ServerSocket serverSocket;

      private ShutdownMonitorRunnable(ServerSocket serverSocket) {
         this.serverSocket = serverSocket;
      }

      public void run() {
         ShutdownMonitor.this.debug("Started");

         try {
            String key = ShutdownMonitor.this.getKey();

            while(true) {
               try {
                  Socket socket = this.serverSocket.accept();

                  label199: {
                     label198: {
                        label217: {
                           try {
                              LineNumberReader reader = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
                              String receivedKey = reader.readLine();
                              if (key.equals(receivedKey)) {
                                 String cmd = reader.readLine();
                                 ShutdownMonitor.this.debug("command=%s", cmd);
                                 OutputStream out = socket.getOutputStream();
                                 boolean exitVm = ShutdownMonitor.this.isExitVm();
                                 if ("stop".equalsIgnoreCase(cmd)) {
                                    ShutdownMonitor.this.debug("Performing stop command");
                                    this.stopLifeCycles(ShutdownThread::isRegistered, exitVm);
                                    ShutdownMonitor.this.debug("Informing client that we are stopped");
                                    this.informClient(out, "Stopped\r\n");
                                    if (exitVm) {
                                       ShutdownMonitor.this.debug("Killing JVM");
                                       System.exit(0);
                                       break label199;
                                    }
                                    break label198;
                                 }

                                 if (!"forcestop".equalsIgnoreCase(cmd)) {
                                    if ("stopexit".equalsIgnoreCase(cmd)) {
                                       ShutdownMonitor.this.debug("Performing stop and exit commands");
                                       this.stopLifeCycles(ShutdownThread::isRegistered, true);
                                       ShutdownMonitor.this.debug("Informing client that we are stopped");
                                       this.informClient(out, "Stopped\r\n");
                                       ShutdownMonitor.this.debug("Killing JVM");
                                       System.exit(0);
                                    } else if ("exit".equalsIgnoreCase(cmd)) {
                                       ShutdownMonitor.this.debug("Killing JVM");
                                       System.exit(0);
                                    } else if ("status".equalsIgnoreCase(cmd)) {
                                       this.informClient(out, "OK\r\n");
                                    } else if ("pid".equalsIgnoreCase(cmd)) {
                                       this.informClient(out, Long.toString(ProcessHandle.current().pid()));
                                    }
                                    break label199;
                                 }

                                 ShutdownMonitor.this.debug("Performing forced stop command");
                                 this.stopLifeCycles((l) -> true, exitVm);
                                 ShutdownMonitor.this.debug("Informing client that we are stopped");
                                 this.informClient(out, "Stopped\r\n");
                                 if (!exitVm) {
                                    break label217;
                                 }

                                 ShutdownMonitor.this.debug("Killing JVM");
                                 System.exit(0);
                                 break label199;
                              }

                              ShutdownMonitor.this.debug("Ignoring command with incorrect key: %s", receivedKey);
                           } catch (Throwable var15) {
                              if (socket != null) {
                                 try {
                                    socket.close();
                                 } catch (Throwable var14) {
                                    var15.addSuppressed(var14);
                                 }
                              }

                              throw var15;
                           }

                           if (socket != null) {
                              socket.close();
                           }
                           continue;
                        }

                        if (socket != null) {
                           socket.close();
                        }
                        break;
                     }

                     if (socket != null) {
                        socket.close();
                     }
                     break;
                  }

                  if (socket != null) {
                     socket.close();
                  }
               } catch (Throwable x) {
                  ShutdownMonitor.this.debug(x);
               }
            }
         } catch (Throwable x) {
            ShutdownMonitor.this.debug(x);
         } finally {
            IO.close((Closeable)this.serverSocket);
            ShutdownMonitor.this.stop();
            ShutdownMonitor.this.debug("Stopped");
         }

      }

      private void informClient(OutputStream out, String message) throws IOException {
         out.write(message.getBytes(StandardCharsets.UTF_8));
         out.flush();
      }

      private void stopLifeCycles(Predicate predicate, boolean destroy) {
         List<LifeCycle> lifeCycles;
         try (AutoLock l = ShutdownMonitor.this._lock.lock()) {
            lifeCycles = new ArrayList(ShutdownMonitor.this._lifeCycles);
         }

         for(LifeCycle l : lifeCycles) {
            try {
               if (l.isStarted() && predicate.test(l)) {
                  l.stop();
               }

               if (l instanceof Destroyable && destroy) {
                  ((Destroyable)l).destroy();
               }
            } catch (Throwable x) {
               ShutdownMonitor.this.debug(x);
            }
         }

      }
   }
}
