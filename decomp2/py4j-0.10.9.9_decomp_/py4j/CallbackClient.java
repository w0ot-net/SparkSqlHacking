package py4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import py4j.reflection.ReflectionUtil;

public class CallbackClient implements Py4JPythonClient {
   public static final String DEFAULT_ADDRESS = "127.0.0.1";
   protected final int port;
   protected final InetAddress address;
   protected final SocketFactory socketFactory;
   protected final Deque connections;
   protected final Lock lock;
   private final Logger logger;
   private boolean isShutdown;
   private boolean isShuttingDown;
   public static final long DEFAULT_MIN_CONNECTION_TIME = 30L;
   public static final TimeUnit DEFAULT_MIN_CONNECTION_TIME_UNIT;
   private final ScheduledExecutorService executor;
   protected final long minConnectionTime;
   protected final TimeUnit minConnectionTimeUnit;
   protected final boolean enableMemoryManagement;
   protected final int readTimeout;
   protected final String authToken;

   public CallbackClient(int port) {
      this(port, GatewayServer.defaultAddress(), 30L, DEFAULT_MIN_CONNECTION_TIME_UNIT, SocketFactory.getDefault(), true);
   }

   public CallbackClient(int port, InetAddress address) {
      this(port, address, 30L, DEFAULT_MIN_CONNECTION_TIME_UNIT);
   }

   public CallbackClient(int port, InetAddress address, String authToken) {
      this(port, address, authToken, 30L, DEFAULT_MIN_CONNECTION_TIME_UNIT, SocketFactory.getDefault(), true, 0);
   }

   public CallbackClient(int port, InetAddress address, long minConnectionTime, TimeUnit minConnectionTimeUnit) {
      this(port, address, minConnectionTime, minConnectionTimeUnit, SocketFactory.getDefault());
   }

   public CallbackClient(int port, InetAddress address, long minConnectionTime, TimeUnit minConnectionTimeUnit, SocketFactory socketFactory) {
      this(port, address, minConnectionTime, minConnectionTimeUnit, socketFactory, true);
   }

   public CallbackClient(int port, InetAddress address, long minConnectionTime, TimeUnit minConnectionTimeUnit, SocketFactory socketFactory, boolean enableMemoryManagement) {
      this(port, address, minConnectionTime, minConnectionTimeUnit, socketFactory, enableMemoryManagement, 0);
   }

   public CallbackClient(int port, InetAddress address, long minConnectionTime, TimeUnit minConnectionTimeUnit, SocketFactory socketFactory, boolean enableMemoryManagement, int readTimeout) {
      this(port, address, (String)null, minConnectionTime, minConnectionTimeUnit, socketFactory, enableMemoryManagement, readTimeout);
   }

   public CallbackClient(int port, InetAddress address, String authToken, long minConnectionTime, TimeUnit minConnectionTimeUnit, SocketFactory socketFactory, boolean enableMemoryManagement, int readTimeout) {
      this.connections = new ArrayDeque();
      this.lock = new ReentrantLock(true);
      this.logger = Logger.getLogger(CallbackClient.class.getName());
      this.isShutdown = false;
      this.isShuttingDown = false;
      this.executor = Executors.newScheduledThreadPool(1);
      this.port = port;
      this.address = address;
      this.minConnectionTime = minConnectionTime;
      this.minConnectionTimeUnit = minConnectionTimeUnit;
      this.socketFactory = socketFactory;
      this.enableMemoryManagement = enableMemoryManagement;
      this.readTimeout = readTimeout;
      this.authToken = StringUtil.escape(authToken);
      this.setupCleaner();
   }

   public InetAddress getAddress() {
      return this.address;
   }

   public boolean isMemoryManagementEnabled() {
      return this.enableMemoryManagement;
   }

   protected Py4JClientConnection getConnection() throws IOException {
      Py4JClientConnection connection = null;
      connection = (Py4JClientConnection)this.connections.pollLast();
      if (connection == null) {
         connection = new CallbackConnection(this.port, this.address, this.socketFactory, this.readTimeout, this.authToken);
         connection.start();
      }

      return connection;
   }

   protected Py4JClientConnection getConnectionLock() {
      Py4JClientConnection cc = null;

      try {
         this.logger.log(Level.INFO, "Getting CB Connection");
         this.lock.lock();
         if (!this.isShutdown) {
            cc = this.getConnection();
            this.logger.log(Level.INFO, "Acquired CB Connection");
         } else {
            this.logger.log(Level.INFO, "Shutting down, no connection can be created.");
         }
      } catch (Exception e) {
         this.logger.log(Level.SEVERE, "Critical error while sending a command", e);
         throw new Py4JException("Error while obtaining a new communication channel", e);
      } finally {
         this.lock.unlock();
      }

      return cc;
   }

   public int getPort() {
      return this.port;
   }

   public int getReadTimeout() {
      return this.readTimeout;
   }

   public Py4JPythonClient copyWith(InetAddress pythonAddress, int pythonPort) {
      return new CallbackClient(pythonPort, pythonAddress, this.authToken, this.minConnectionTime, this.minConnectionTimeUnit, this.socketFactory, this.enableMemoryManagement, this.readTimeout);
   }

   protected void giveBackConnection(Py4JClientConnection cc) {
      try {
         this.lock.lock();
         if (cc != null) {
            if (!this.isShutdown) {
               this.connections.addLast(cc);
            } else {
               cc.shutdown();
            }
         }
      } finally {
         this.lock.unlock();
      }

   }

   public void periodicCleanup() {
      try {
         this.lock.lock();
         if (!this.isShutdown) {
            int size = this.connections.size();

            for(int i = 0; i < size; ++i) {
               Py4JClientConnection cc = (Py4JClientConnection)this.connections.pollLast();
               if (cc.wasUsed()) {
                  cc.setUsed(false);
                  this.connections.addFirst(cc);
               } else {
                  cc.shutdown();
               }
            }
         }
      } finally {
         this.lock.unlock();
      }

   }

   public String sendCommand(String command) {
      return this.sendCommand(command, true);
   }

   public String sendCommand(String command, boolean blocking) {
      String returnCommand = null;
      Py4JClientConnection cc = this.getConnectionLock();
      if (cc == null) {
         throw new Py4JException("Cannot obtain a new communication channel");
      } else {
         try {
            returnCommand = cc.sendCommand(command, blocking);
         } catch (Py4JNetworkException pe) {
            this.logger.log(Level.WARNING, "Error while sending a command", pe);
            boolean reset = false;
            if (pe.getCause() instanceof SocketTimeoutException) {
               reset = true;
            }

            cc.shutdown(reset);
            if (!this.shouldRetrySendCommand(cc, pe)) {
               this.logger.log(Level.SEVERE, "Error while sending a command.", pe);
               throw new Py4JException("Error while sending a command.", pe);
            }

            returnCommand = this.sendCommand(command, blocking);
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "Critical error while sending a command", e);
            cc.shutdown();
            throw new Py4JException("Error while sending a command.");
         }

         try {
            this.giveBackConnection(cc);
            return returnCommand;
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "Critical error while giving back connection.", e);
            throw new Py4JException("Error while giving back connection.");
         }
      }
   }

   public Object getPythonServerEntryPoint(Gateway gateway, Class[] interfacesToImplement) {
      Object proxy = gateway.createProxy(ReflectionUtil.getClassLoader(), interfacesToImplement, "t");
      return proxy;
   }

   protected boolean shouldRetrySendCommand(Py4JClientConnection cc, Py4JNetworkException pne) {
      return pne.getWhen() == Py4JNetworkException.ErrorTime.ERROR_ON_SEND;
   }

   protected void setupCleaner() {
      if (this.minConnectionTime > 0L) {
         this.executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
               CallbackClient.this.periodicCleanup();
            }
         }, this.minConnectionTime, this.minConnectionTime, this.minConnectionTimeUnit);
      }

   }

   public void shutdown() {
      this.logger.info("Shutting down Callback Client");

      try {
         this.lock.lock();
         if (!this.isShuttingDown) {
            this.isShutdown = true;
            this.isShuttingDown = true;

            for(Py4JClientConnection cc : new ArrayList(this.connections)) {
               cc.shutdown();
            }

            this.executor.shutdownNow();
            this.connections.clear();
            return;
         }
      } finally {
         this.isShuttingDown = false;
         this.lock.unlock();
      }

   }

   static {
      DEFAULT_MIN_CONNECTION_TIME_UNIT = TimeUnit.SECONDS;
   }
}
