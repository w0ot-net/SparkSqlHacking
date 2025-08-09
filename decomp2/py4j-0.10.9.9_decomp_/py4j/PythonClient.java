package py4j;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import py4j.commands.Command;

public class PythonClient extends CallbackClient implements Py4JPythonClientPerThread, GatewayServerListener {
   protected Gateway gateway;
   protected List customCommands;
   protected final Logger logger;
   protected Py4JJavaServer javaServer;
   protected ThreadLocal threadConnection;
   protected final int readTimeout;

   public PythonClient(Gateway gateway, List customCommands, int pythonPort, InetAddress pythonAddress, long minConnectionTime, TimeUnit minConnectionTimeUnit, SocketFactory socketFactory, Py4JJavaServer javaServer) {
      this(gateway, customCommands, pythonPort, pythonAddress, minConnectionTime, minConnectionTimeUnit, socketFactory, javaServer, true, 0);
   }

   public PythonClient(Gateway gateway, List customCommands, int pythonPort, InetAddress pythonAddress, long minConnectionTime, TimeUnit minConnectionTimeUnit, SocketFactory socketFactory, Py4JJavaServer javaServer, boolean enableMemoryManagement, int readTimeout) {
      this(gateway, customCommands, pythonPort, pythonAddress, minConnectionTime, minConnectionTimeUnit, socketFactory, javaServer, enableMemoryManagement, readTimeout, (String)null);
   }

   public PythonClient(Gateway gateway, List customCommands, int pythonPort, InetAddress pythonAddress, long minConnectionTime, TimeUnit minConnectionTimeUnit, SocketFactory socketFactory, Py4JJavaServer javaServer, boolean enableMemoryManagement, int readTimeout, String authToken) {
      super(pythonPort, pythonAddress, authToken, minConnectionTime, minConnectionTimeUnit, socketFactory, enableMemoryManagement, readTimeout);
      this.logger = Logger.getLogger(PythonClient.class.getName());
      this.gateway = gateway;
      this.javaServer = javaServer;
      this.customCommands = customCommands;
      this.threadConnection = new ThreadLocal();
      this.readTimeout = readTimeout;
      this.setSelfListener();
   }

   private void setSelfListener() {
      if (this.javaServer != null) {
         this.javaServer.addListener(this);
      }

   }

   public ClientServerConnection getPerThreadConnection() {
      ClientServerConnection connection = null;
      WeakReference<ClientServerConnection> weakConnection = (WeakReference)this.threadConnection.get();
      if (weakConnection != null) {
         connection = (ClientServerConnection)weakConnection.get();
      }

      return connection;
   }

   public void setPerThreadConnection(ClientServerConnection clientServerConnection) {
      this.threadConnection.set(new WeakReference(clientServerConnection));
   }

   public Gateway getGateway() {
      return this.gateway;
   }

   public void setGateway(Gateway gateway) {
      this.gateway = gateway;
   }

   public Py4JJavaServer getJavaServer() {
      return this.javaServer;
   }

   public void setJavaServer(Py4JJavaServer javaServer) {
      this.javaServer = javaServer;
      this.setSelfListener();
   }

   public int getReadTimeout() {
      return this.readTimeout;
   }

   protected void setupCleaner() {
   }

   protected Socket startClientSocket() throws IOException {
      this.logger.info("Starting Python Client connection on " + this.address + " at " + this.port);
      Socket socket = this.socketFactory.createSocket(this.address, this.port);
      socket.setSoTimeout(this.readTimeout);
      return socket;
   }

   protected Py4JClientConnection getConnection() throws IOException {
      ClientServerConnection connection = null;
      connection = this.getPerThreadConnection();
      if (connection != null) {
         try {
            this.lock.lock();
            this.connections.remove(connection);
         } finally {
            this.lock.unlock();
         }
      }

      if (connection == null || connection.getSocket() == null) {
         Socket socket = this.startClientSocket();
         connection = new ClientServerConnection(this.gateway, socket, this.customCommands, this, this.javaServer, this.readTimeout, this.authToken);
         connection.setInitiatedFromClient(true);
         connection.start();
         this.setPerThreadConnection(connection);
      }

      return connection;
   }

   protected boolean shouldRetrySendCommand(Py4JClientConnection cc, Py4JNetworkException pne) {
      boolean shouldRetry = super.shouldRetrySendCommand(cc, pne);
      if (shouldRetry && cc instanceof ClientServerConnection) {
         ClientServerConnection csc = (ClientServerConnection)cc;
         shouldRetry = csc.isInitiatedFromClient();
      }

      return shouldRetry;
   }

   protected void giveBackConnection(Py4JClientConnection cc) {
      try {
         this.lock.lock();
         this.connections.addLast(cc);
      } finally {
         this.lock.unlock();
      }

   }

   public Py4JPythonClient copyWith(InetAddress pythonAddress, int pythonPort) {
      return new PythonClient(this.gateway, this.customCommands, pythonPort, pythonAddress, this.minConnectionTime, this.minConnectionTimeUnit, this.socketFactory, this.javaServer);
   }

   public void connectionError(Exception e) {
   }

   public void connectionStarted(Py4JServerConnection gatewayConnection) {
   }

   public void connectionStopped(Py4JServerConnection gatewayConnection) {
      try {
         this.lock.lock();
         this.connections.remove(gatewayConnection);
      } catch (Exception var6) {
      } finally {
         this.lock.unlock();
      }

   }

   public void serverError(Exception e) {
   }

   public void serverPostShutdown() {
   }

   public void serverPreShutdown() {
   }

   public void serverStarted() {
   }

   public void serverStopped() {
   }
}
