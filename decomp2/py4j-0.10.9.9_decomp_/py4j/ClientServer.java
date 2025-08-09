package py4j;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

public class ClientServer {
   protected final int javaPort;
   protected final InetAddress javaAddress;
   protected final int pythonPort;
   protected final InetAddress pythonAddress;
   protected final int connectTimeout;
   protected final int readTimeout;
   protected final ServerSocketFactory sSocketFactory;
   protected final SocketFactory socketFactory;
   protected final Gateway gateway;
   protected final Py4JJavaServer javaServer;
   protected final Py4JPythonClientPerThread pythonClient;
   protected final boolean autoStartJavaServer;
   protected final boolean enableMemoryManagement;
   protected final String authToken;
   protected final Logger logger;

   public ClientServer(Object entryPoint) {
      this(25333, GatewayServer.defaultAddress(), 25334, GatewayServer.defaultAddress(), 0, 0, ServerSocketFactory.getDefault(), SocketFactory.getDefault(), entryPoint);
   }

   public ClientServer(int javaPort, InetAddress javaAddress, int pythonPort, InetAddress pythonAddress, int connectTimeout, int readTimeout, ServerSocketFactory sSocketFactory, SocketFactory socketFactory, Object entryPoint) {
      this(javaPort, javaAddress, pythonPort, pythonAddress, connectTimeout, readTimeout, sSocketFactory, socketFactory, entryPoint, true, true);
   }

   public ClientServer(int javaPort, InetAddress javaAddress, int pythonPort, InetAddress pythonAddress, int connectTimeout, int readTimeout, ServerSocketFactory sSocketFactory, SocketFactory socketFactory, Object entryPoint, boolean autoStartJavaServer, boolean enableMemoryManagement) {
      this(javaPort, javaAddress, pythonPort, pythonAddress, connectTimeout, readTimeout, sSocketFactory, socketFactory, entryPoint, autoStartJavaServer, enableMemoryManagement, (String)null);
   }

   private ClientServer(int javaPort, InetAddress javaAddress, int pythonPort, InetAddress pythonAddress, int connectTimeout, int readTimeout, ServerSocketFactory sSocketFactory, SocketFactory socketFactory, Object entryPoint, boolean autoStartJavaServer, boolean enableMemoryManagement, String authToken) {
      this.logger = Logger.getLogger(ClientServer.class.getName());
      this.javaPort = javaPort;
      this.javaAddress = javaAddress;
      this.pythonPort = pythonPort;
      this.pythonAddress = pythonAddress;
      this.connectTimeout = connectTimeout;
      this.readTimeout = readTimeout;
      this.sSocketFactory = sSocketFactory;
      this.socketFactory = socketFactory;
      this.enableMemoryManagement = enableMemoryManagement;
      this.authToken = authToken;
      this.pythonClient = this.createPythonClient();
      this.javaServer = this.createJavaServer(entryPoint, this.pythonClient);
      this.gateway = this.javaServer.getGateway();
      this.pythonClient.setGateway(this.gateway);
      this.pythonClient.setJavaServer(this.javaServer);
      this.autoStartJavaServer = autoStartJavaServer;
      if (autoStartJavaServer) {
         this.javaServer.start();
      } else {
         this.gateway.startup();
      }

   }

   protected Py4JPythonClientPerThread createPythonClient() {
      return new PythonClient((Gateway)null, (List)null, this.pythonPort, this.pythonAddress, 30L, TimeUnit.SECONDS, this.socketFactory, (Py4JJavaServer)null, this.enableMemoryManagement, this.readTimeout, this.authToken);
   }

   protected Py4JJavaServer createJavaServer(Object entryPoint, Py4JPythonClientPerThread pythonClient) {
      return new JavaServer(entryPoint, this.javaPort, this.connectTimeout, this.readTimeout, (List)null, pythonClient, this.authToken);
   }

   public Py4JJavaServer getJavaServer() {
      return this.javaServer;
   }

   public Py4JPythonClient getPythonClient() {
      return this.pythonClient;
   }

   public void startServer() {
      this.startServer(true);
   }

   public void startServer(boolean fork) {
      if (!this.autoStartJavaServer) {
         this.javaServer.start(fork);
      }

   }

   public void shutdown() {
      this.javaServer.shutdown(true);
   }

   public Object getPythonServerEntryPoint(Class[] interfacesToImplement) {
      return this.pythonClient.getPythonServerEntryPoint(this.gateway, interfacesToImplement);
   }

   public static class ClientServerBuilder {
      private int javaPort;
      private InetAddress javaAddress;
      private int pythonPort;
      private InetAddress pythonAddress;
      private int connectTimeout;
      private int readTimeout;
      private ServerSocketFactory serverSocketFactory;
      private SocketFactory socketFactory;
      private Object entryPoint;
      private boolean autoStartJavaServer;
      private boolean enableMemoryManagement;
      private String authToken;

      public ClientServerBuilder() {
         this((Object)null);
      }

      public ClientServerBuilder(Object entryPoint) {
         this.javaPort = 25333;
         this.javaAddress = GatewayServer.defaultAddress();
         this.pythonPort = 25334;
         this.pythonAddress = GatewayServer.defaultAddress();
         this.connectTimeout = 0;
         this.readTimeout = 0;
         this.serverSocketFactory = ServerSocketFactory.getDefault();
         this.socketFactory = SocketFactory.getDefault();
         this.entryPoint = entryPoint;
         this.autoStartJavaServer = true;
         this.enableMemoryManagement = true;
      }

      public ClientServer build() {
         return new ClientServer(this.javaPort, this.javaAddress, this.pythonPort, this.pythonAddress, this.connectTimeout, this.readTimeout, this.serverSocketFactory, this.socketFactory, this.entryPoint, this.autoStartJavaServer, this.enableMemoryManagement, this.authToken);
      }

      public ClientServerBuilder javaPort(int javaPort) {
         this.javaPort = javaPort;
         return this;
      }

      public ClientServerBuilder javaAddress(InetAddress javaAddress) {
         this.javaAddress = javaAddress;
         return this;
      }

      public ClientServerBuilder pythonPort(int pythonPort) {
         this.pythonPort = pythonPort;
         return this;
      }

      public ClientServerBuilder pythonAddress(InetAddress pythonAddress) {
         this.pythonAddress = pythonAddress;
         return this;
      }

      public ClientServerBuilder connectTimeout(int connectTimeout) {
         this.connectTimeout = connectTimeout;
         return this;
      }

      public ClientServerBuilder readTimeout(int readTimeout) {
         this.readTimeout = readTimeout;
         return this;
      }

      public ClientServerBuilder serverSocketFactory(ServerSocketFactory serverSocketFactory) {
         this.serverSocketFactory = serverSocketFactory;
         return this;
      }

      public ClientServerBuilder socketFactory(SocketFactory socketFactory) {
         this.socketFactory = socketFactory;
         return this;
      }

      public ClientServerBuilder entryPoint(Object entryPoint) {
         this.entryPoint = entryPoint;
         return this;
      }

      public ClientServerBuilder autoStartJavaServer(boolean autoStartJavaServer) {
         this.autoStartJavaServer = autoStartJavaServer;
         return this;
      }

      public ClientServerBuilder enableMemoryManagement(boolean enableMemoryManagement) {
         this.enableMemoryManagement = enableMemoryManagement;
         return this;
      }

      public ClientServerBuilder authToken(String authToken) {
         this.authToken = StringUtil.escape(authToken);
         return this;
      }
   }
}
