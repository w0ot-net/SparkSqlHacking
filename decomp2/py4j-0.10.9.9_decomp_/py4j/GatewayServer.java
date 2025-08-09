package py4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import py4j.commands.Command;

public class GatewayServer extends DefaultGatewayServerListener implements Py4JJavaServer, Runnable {
   public static final String DEFAULT_ADDRESS = "127.0.0.1";
   public static final String DEFAULT_IPv6_ADDRESS = "::1";
   public static final int DEFAULT_PORT = 25333;
   public static final int DEFAULT_PYTHON_PORT = 25334;
   public static final int DEFAULT_CONNECT_TIMEOUT = 0;
   public static final int DEFAULT_READ_TIMEOUT = 0;
   public static final String GATEWAY_SERVER_ID = "GATEWAY_SERVER";
   public static final Logger PY4J_LOGGER = Logger.getLogger("py4j");
   private final InetAddress address;
   private final int port;
   private int pythonPort;
   private InetAddress pythonAddress;
   private final Gateway gateway;
   private final int connectTimeout;
   private final int readTimeout;
   private final Logger logger;
   private final List connections;
   private final List customCommands;
   private final CopyOnWriteArrayList listeners;
   private final ServerSocketFactory sSocketFactory;
   protected final String authToken;
   private ServerSocket sSocket;
   private boolean isShutdown;
   private boolean isShuttingDown;
   private final Lock lock;

   public static void turnAllLoggingOn() {
      PY4J_LOGGER.setLevel(Level.ALL);
   }

   public static void turnLoggingOff() {
      PY4J_LOGGER.setLevel(Level.OFF);
   }

   public static void turnLoggingOn() {
      PY4J_LOGGER.setLevel(Level.INFO);
   }

   public static InetAddress defaultAddress() {
      try {
         return InetAddress.getByName("127.0.0.1");
      } catch (UnknownHostException e) {
         throw new Py4JNetworkException(e);
      }
   }

   public static InetAddress defaultIPv6Address() {
      try {
         return InetAddress.getByName("::1");
      } catch (UnknownHostException e) {
         throw new Py4JNetworkException(e);
      }
   }

   public GatewayServer() {
      this((Object)null, 25333, 0, 0);
   }

   public GatewayServer(Object entryPoint) {
      this(entryPoint, 25333, 0, 0);
   }

   public GatewayServer(Object entryPoint, int port) {
      this(entryPoint, port, 0, 0);
   }

   public GatewayServer(Object entryPoint, int port, int pythonPort, InetAddress address, InetAddress pythonAddress, int connectTimeout, int readTimeout, List customCommands) {
      this((Object)entryPoint, port, address, connectTimeout, readTimeout, customCommands, (Py4JPythonClient)(new CallbackClient(pythonPort, pythonAddress)), (ServerSocketFactory)ServerSocketFactory.getDefault());
   }

   public void resetCallbackClient(InetAddress pythonAddress, int pythonPort) {
      this.gateway.resetCallbackClient(pythonAddress, pythonPort);
      this.pythonPort = pythonPort;
      this.pythonAddress = pythonAddress;
   }

   public GatewayServer(Object entryPoint, int port, int connectTimeout, int readTimeout) {
      this(entryPoint, port, 25334, connectTimeout, readTimeout, (List)null);
   }

   public GatewayServer(Object entryPoint, int port, int pythonPort, int connectTimeout, int readTimeout, List customCommands) {
      this((Object)entryPoint, port, defaultAddress(), connectTimeout, readTimeout, customCommands, (Py4JPythonClient)(new CallbackClient(pythonPort, defaultAddress())), (ServerSocketFactory)ServerSocketFactory.getDefault());
   }

   public GatewayServer(Object entryPoint, int port, int connectTimeout, int readTimeout, List customCommands, Py4JPythonClient cbClient) {
      this(entryPoint, port, defaultAddress(), connectTimeout, readTimeout, customCommands, cbClient, ServerSocketFactory.getDefault());
   }

   public GatewayServer(Object entryPoint, int port, InetAddress address, int connectTimeout, int readTimeout, List customCommands, Py4JPythonClient cbClient) {
      this(entryPoint, port, address, connectTimeout, readTimeout, customCommands, cbClient, ServerSocketFactory.getDefault());
   }

   public GatewayServer(Object entryPoint, int port, InetAddress address, int connectTimeout, int readTimeout, List customCommands, Py4JPythonClient cbClient, ServerSocketFactory sSocketFactory) {
      this((Object)entryPoint, port, address, connectTimeout, readTimeout, customCommands, (Py4JPythonClient)cbClient, (ServerSocketFactory)sSocketFactory, (String)null);
   }

   GatewayServer(Object entryPoint, int port, InetAddress address, int connectTimeout, int readTimeout, List customCommands, Py4JPythonClient cbClient, ServerSocketFactory sSocketFactory, String authToken) {
      this.logger = Logger.getLogger(GatewayServer.class.getName());
      this.connections = new ArrayList();
      this.isShutdown = false;
      this.isShuttingDown = false;
      this.lock = new ReentrantLock(true);
      this.port = port;
      this.address = address;
      this.connectTimeout = connectTimeout;
      this.readTimeout = readTimeout;
      this.gateway = new Gateway(entryPoint, cbClient);
      this.pythonPort = cbClient.getPort();
      this.pythonAddress = cbClient.getAddress();
      this.gateway.putObject("GATEWAY_SERVER", this);
      if (customCommands != null) {
         this.customCommands = customCommands;
      } else {
         this.customCommands = new ArrayList();
      }

      this.listeners = new CopyOnWriteArrayList();
      this.sSocketFactory = sSocketFactory;
      this.authToken = authToken;
   }

   public GatewayServer(Gateway gateway, int port, InetAddress address, int connectTimeout, int readTimeout, List customCommands, ServerSocketFactory sSocketFactory) {
      this((Gateway)gateway, port, address, connectTimeout, readTimeout, customCommands, (ServerSocketFactory)sSocketFactory, (String)null);
   }

   private GatewayServer(Gateway gateway, int port, InetAddress address, int connectTimeout, int readTimeout, List customCommands, ServerSocketFactory sSocketFactory, String authToken) {
      this.logger = Logger.getLogger(GatewayServer.class.getName());
      this.connections = new ArrayList();
      this.isShutdown = false;
      this.isShuttingDown = false;
      this.lock = new ReentrantLock(true);
      this.port = port;
      this.address = address;
      this.connectTimeout = connectTimeout;
      this.readTimeout = readTimeout;
      this.gateway = gateway;
      this.pythonPort = gateway.getCallbackClient().getPort();
      this.pythonAddress = gateway.getCallbackClient().getAddress();
      this.gateway.putObject("GATEWAY_SERVER", this);
      if (customCommands != null) {
         this.customCommands = customCommands;
      } else {
         this.customCommands = new ArrayList();
      }

      this.listeners = new CopyOnWriteArrayList();
      this.sSocketFactory = sSocketFactory;
      this.authToken = authToken;
   }

   public void addListener(GatewayServerListener listener) {
      this.listeners.addIfAbsent(listener);
   }

   public void connectionStopped(Py4JServerConnection gatewayConnection) {
      try {
         this.lock.lock();
         if (!this.isShutdown) {
            this.connections.remove(gatewayConnection);
         }
      } finally {
         this.lock.unlock();
      }

   }

   protected Py4JServerConnection createConnection(Gateway gateway, Socket socket) throws IOException {
      GatewayConnection connection = new GatewayConnection(gateway, socket, this.authToken, this.customCommands, this.listeners);
      connection.startConnection();
      return connection;
   }

   protected void fireConnectionError(Exception e) {
      this.logger.log(Level.SEVERE, "Connection Server Error", e);

      for(GatewayServerListener listener : this.listeners) {
         try {
            listener.connectionError(e);
         } catch (Exception ex) {
            this.logger.log(Level.SEVERE, "A listener crashed.", ex);
         }
      }

   }

   protected void fireConnectionStarted(Py4JServerConnection gatewayConnection) {
      this.logger.info("Connection Started");

      for(GatewayServerListener listener : this.listeners) {
         try {
            listener.connectionStarted(gatewayConnection);
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "A listener crashed.", e);
         }
      }

   }

   protected void fireServerError(Exception e) {
      boolean sendEvent = false;
      if (e.getMessage().toLowerCase().contains("socket closed")) {
         this.logger.log(Level.FINE, "Gateway Server Error", e);
      } else {
         sendEvent = true;
         this.logger.log(Level.SEVERE, "Gateway Server Error", e);
      }

      if (sendEvent) {
         for(GatewayServerListener listener : this.listeners) {
            try {
               listener.serverError(e);
            } catch (Exception ex) {
               this.logger.log(Level.SEVERE, "A listener crashed.", ex);
            }
         }
      }

   }

   protected void fireServerPostShutdown() {
      this.logger.fine("Gateway Server Post Shutdown");

      for(GatewayServerListener listener : this.listeners) {
         try {
            listener.serverPostShutdown();
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "A listener crashed.", e);
         }
      }

   }

   protected void fireServerPreShutdown() {
      this.logger.fine("Gateway Server Pre Shutdown");

      for(GatewayServerListener listener : this.listeners) {
         try {
            listener.serverPreShutdown();
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "A listener crashed.", e);
         }
      }

   }

   protected void fireServerStarted() {
      this.logger.info("Gateway Server Started");

      for(GatewayServerListener listener : this.listeners) {
         try {
            listener.serverStarted();
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "A listener crashed.", e);
         }
      }

   }

   protected void fireServerStopped() {
      this.logger.info("Gateway Server Stopped");

      for(GatewayServerListener listener : this.listeners) {
         try {
            listener.serverStopped();
         } catch (Exception e) {
            this.logger.log(Level.SEVERE, "A listener crashed.", e);
         }
      }

   }

   public InetAddress getAddress() {
      return this.address;
   }

   public Py4JPythonClient getCallbackClient() {
      return this.gateway.getCallbackClient();
   }

   public int getConnectTimeout() {
      return this.connectTimeout;
   }

   public Gateway getGateway() {
      return this.gateway;
   }

   public int getListeningPort() {
      int port = -1;

      try {
         if (this.sSocket.isBound()) {
            port = this.sSocket.getLocalPort();
         }
      } catch (Exception var3) {
      }

      return port;
   }

   public int getPort() {
      return this.port;
   }

   public InetAddress getPythonAddress() {
      return this.pythonAddress;
   }

   public int getPythonPort() {
      return this.pythonPort;
   }

   public int getReadTimeout() {
      return this.readTimeout;
   }

   protected void processSocket(Socket socket) {
      try {
         this.lock.lock();
         if (!this.isShutdown) {
            socket.setSoTimeout(this.readTimeout);
            Py4JServerConnection gatewayConnection = this.createConnection(this.gateway, socket);
            this.connections.add(gatewayConnection);
            this.fireConnectionStarted(gatewayConnection);
         }
      } catch (Exception e) {
         this.fireConnectionError(e);
      } finally {
         this.lock.unlock();
      }

   }

   public void removeListener(GatewayServerListener listener) {
      this.listeners.remove(listener);
   }

   public void run() {
      try {
         this.gateway.startup();
         this.fireServerStarted();
         this.addListener(this);

         while(!this.isShutdown) {
            Socket socket = this.sSocket.accept();
            this.processSocket(socket);
         }
      } catch (Exception e) {
         this.fireServerError(e);
      }

      this.fireServerStopped();
      this.removeListener(this);
   }

   public void shutdown() {
      this.shutdown(true);
   }

   public void shutdownSocket(String address, int remotePort, int localPort) {
      try {
         this.lock.lock();

         for(Py4JServerConnection connection : new ArrayList(this.connections)) {
            if (connection.getSocket() != null && (connection.getSocket().getPort() == remotePort || connection.getSocket().getLocalPort() == localPort)) {
               connection.shutdown();
               this.connections.remove(connection);
            }
         }
      } finally {
         this.lock.unlock();
      }

   }

   public void shutdown(boolean shutdownCallbackClient) {
      this.fireServerPreShutdown();

      label58: {
         try {
            this.lock.lock();
            if (!this.isShuttingDown) {
               this.isShutdown = true;
               this.isShuttingDown = true;
               NetworkUtil.quietlyClose(this.sSocket);

               for(Py4JServerConnection connection : new ArrayList(this.connections)) {
                  connection.shutdown();
               }

               this.connections.clear();
               this.gateway.shutdown(shutdownCallbackClient);
               break label58;
            }
         } finally {
            this.isShuttingDown = false;
            this.lock.unlock();
         }

         return;
      }

      this.fireServerPostShutdown();
   }

   public void start() {
      this.start(true);
   }

   public void start(boolean fork) {
      this.startSocket();
      if (fork) {
         Thread t = new Thread(this);
         t.start();
      } else {
         this.run();
      }

   }

   protected void startSocket() throws Py4JNetworkException {
      try {
         this.sSocket = this.sSocketFactory.createServerSocket();
         this.sSocket.setSoTimeout(this.connectTimeout);
         this.sSocket.setReuseAddress(true);
         this.sSocket.bind(new InetSocketAddress(this.address, this.port), -1);
      } catch (IOException e) {
         throw new Py4JNetworkException("Failed to bind to " + this.address + ":" + this.port, e);
      }
   }

   public Object getPythonServerEntryPoint(Class[] interfacesToImplement) {
      return this.getCallbackClient().getPythonServerEntryPoint(this.gateway, interfacesToImplement);
   }

   public static void main(String[] args) {
      int port = 25333;
      boolean dieOnBrokenPipe = false;
      boolean enableAuth = false;
      String usage = "usage: [--die-on-broken-pipe] [--enable-auth] [port]";

      for(int i = 0; i < args.length; ++i) {
         String opt = args[i];
         if (opt.equals("--die-on-broken-pipe")) {
            dieOnBrokenPipe = true;
         } else if (opt.equals("--enable-auth")) {
            enableAuth = true;
         } else {
            try {
               port = Integer.parseInt(opt);
            } catch (NumberFormatException var10) {
               System.err.println(usage);
               System.exit(1);
            }
         }
      }

      String authToken = null;
      if (enableAuth) {
         SecureRandom rnd = new SecureRandom();
         byte[] token = new byte[32];
         rnd.nextBytes(token);
         authToken = Base64.encodeToString(token, false);
      }

      GatewayServer gatewayServer = (new GatewayServerBuilder()).javaPort(port).authToken(authToken).build();
      gatewayServer.start();
      int listening_port = gatewayServer.getListeningPort();
      System.out.println("" + listening_port);
      if (authToken != null) {
         System.out.println(authToken);
      }

      if (dieOnBrokenPipe) {
         try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
            stdin.readLine();
            System.exit(0);
         } catch (IOException var9) {
            System.exit(1);
         }
      }

   }

   public List getCustomCommands() {
      return Collections.unmodifiableList(this.customCommands);
   }

   public List getListeners() {
      return Collections.unmodifiableList(this.listeners);
   }

   static {
      turnLoggingOff();
   }

   public static class GatewayServerBuilder {
      private int javaPort;
      private InetAddress javaAddress;
      private int connectTimeout;
      private int readTimeout;
      private Gateway gateway;
      private ServerSocketFactory serverSocketFactory;
      private Object entryPoint;
      private Py4JPythonClient callbackClient;
      private List customCommands;
      private String authToken;

      public GatewayServerBuilder() {
         this((Object)null);
      }

      public GatewayServerBuilder(Object entryPoint) {
         this.javaPort = 25333;
         this.javaAddress = GatewayServer.defaultAddress();
         this.connectTimeout = 0;
         this.readTimeout = 0;
         this.serverSocketFactory = ServerSocketFactory.getDefault();
         this.entryPoint = entryPoint;
      }

      public GatewayServer build() {
         if (this.gateway == null) {
            if (this.callbackClient == null) {
               this.callbackClient = new CallbackClient(25334);
            }

            return new GatewayServer(this.entryPoint, this.javaPort, this.javaAddress, this.connectTimeout, this.readTimeout, this.customCommands, this.callbackClient, this.serverSocketFactory, this.authToken);
         } else {
            return new GatewayServer(this.gateway, this.javaPort, this.javaAddress, this.connectTimeout, this.readTimeout, this.customCommands, this.serverSocketFactory, this.authToken);
         }
      }

      public GatewayServerBuilder gateway(Gateway gateway) {
         this.gateway = gateway;
         return this;
      }

      public GatewayServerBuilder javaPort(int javaPort) {
         this.javaPort = javaPort;
         return this;
      }

      public GatewayServerBuilder javaAddress(InetAddress javaAddress) {
         this.javaAddress = javaAddress;
         return this;
      }

      public GatewayServerBuilder callbackClient(int pythonPort, InetAddress pythonAddress) {
         this.callbackClient = new CallbackClient(pythonPort, pythonAddress);
         return this;
      }

      public GatewayServerBuilder callbackClient(int pythonPort, InetAddress pythonAddress, String authToken) {
         this.callbackClient = new CallbackClient(pythonPort, pythonAddress, authToken);
         return this;
      }

      public GatewayServerBuilder callbackClient(CallbackClient callbackClient) {
         this.callbackClient = callbackClient;
         return this;
      }

      public GatewayServerBuilder connectTimeout(int connectTimeout) {
         this.connectTimeout = connectTimeout;
         return this;
      }

      public GatewayServerBuilder readTimeout(int readTimeout) {
         this.readTimeout = readTimeout;
         return this;
      }

      public GatewayServerBuilder serverSocketFactory(ServerSocketFactory serverSocketFactory) {
         this.serverSocketFactory = serverSocketFactory;
         return this;
      }

      public GatewayServerBuilder entryPoint(Object entryPoint) {
         this.entryPoint = entryPoint;
         return this;
      }

      public GatewayServerBuilder customCommands(List customCommands) {
         this.customCommands = customCommands;
         return this;
      }

      public GatewayServerBuilder authToken(String authToken) {
         this.authToken = StringUtil.escape(authToken);
         return this;
      }
   }
}
