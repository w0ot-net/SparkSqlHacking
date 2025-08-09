package org.apache.zookeeper.server.quorum;

import io.netty.buffer.Unpooled;
import io.netty.handler.ssl.SslHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import javax.net.ssl.SSLSocket;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.common.X509Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnifiedServerSocket extends ServerSocket {
   private static final Logger LOG = LoggerFactory.getLogger(UnifiedServerSocket.class);
   private X509Util x509Util;
   private final boolean allowInsecureConnection;

   public UnifiedServerSocket(X509Util x509Util, boolean allowInsecureConnection) throws IOException {
      this.x509Util = x509Util;
      this.allowInsecureConnection = allowInsecureConnection;
   }

   public UnifiedServerSocket(X509Util x509Util, boolean allowInsecureConnection, int port) throws IOException {
      super(port);
      this.x509Util = x509Util;
      this.allowInsecureConnection = allowInsecureConnection;
   }

   public UnifiedServerSocket(X509Util x509Util, boolean allowInsecureConnection, int port, int backlog) throws IOException {
      super(port, backlog);
      this.x509Util = x509Util;
      this.allowInsecureConnection = allowInsecureConnection;
   }

   public UnifiedServerSocket(X509Util x509Util, boolean allowInsecureConnection, int port, int backlog, InetAddress bindAddr) throws IOException {
      super(port, backlog, bindAddr);
      this.x509Util = x509Util;
      this.allowInsecureConnection = allowInsecureConnection;
   }

   public Socket accept() throws IOException {
      if (this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if (!this.isBound()) {
         throw new SocketException("Socket is not bound yet");
      } else {
         PrependableSocket prependableSocket = new PrependableSocket((SocketImpl)null);
         this.implAccept(prependableSocket);
         return new UnifiedSocket(this.x509Util, this.allowInsecureConnection, prependableSocket);
      }
   }

   public static class UnifiedSocket extends Socket {
      private final X509Util x509Util;
      private final boolean allowInsecureConnection;
      private PrependableSocket prependableSocket;
      private SSLSocket sslSocket;
      private Mode mode;

      private UnifiedSocket(X509Util x509Util, boolean allowInsecureConnection, PrependableSocket prependableSocket) {
         this.x509Util = x509Util;
         this.allowInsecureConnection = allowInsecureConnection;
         this.prependableSocket = prependableSocket;
         this.sslSocket = null;
         this.mode = UnifiedServerSocket.UnifiedSocket.Mode.UNKNOWN;
      }

      public boolean isSecureSocket() {
         return this.mode == UnifiedServerSocket.UnifiedSocket.Mode.TLS;
      }

      public boolean isPlaintextSocket() {
         return this.mode == UnifiedServerSocket.UnifiedSocket.Mode.PLAINTEXT;
      }

      public boolean isModeKnown() {
         return this.mode != UnifiedServerSocket.UnifiedSocket.Mode.UNKNOWN;
      }

      private void detectMode() throws IOException {
         byte[] litmus = new byte[5];
         int oldTimeout = -1;
         int bytesRead = 0;
         int newTimeout = this.x509Util.getSslHandshakeTimeoutMillis();

         try {
            oldTimeout = this.prependableSocket.getSoTimeout();
            this.prependableSocket.setSoTimeout(newTimeout);
            bytesRead = this.prependableSocket.getInputStream().read(litmus, 0, litmus.length);
         } catch (SocketTimeoutException var16) {
            UnifiedServerSocket.LOG.warn("Socket mode detection timed out after {} ms, assuming PLAINTEXT", newTimeout);
         } finally {
            try {
               if (oldTimeout != -1) {
                  this.prependableSocket.setSoTimeout(oldTimeout);
               }
            } catch (Exception e) {
               UnifiedServerSocket.LOG.warn("Failed to restore old socket timeout value of {} ms", oldTimeout, e);
            }

         }

         if (bytesRead < 0) {
            bytesRead = 0;
         }

         if (bytesRead == litmus.length && SslHandler.isEncrypted(Unpooled.wrappedBuffer(litmus), false)) {
            try {
               this.sslSocket = this.x509Util.createSSLSocket(this.prependableSocket, litmus);
            } catch (X509Exception e) {
               throw new IOException("failed to create SSL context", e);
            }

            this.prependableSocket = null;
            this.mode = UnifiedServerSocket.UnifiedSocket.Mode.TLS;
            UnifiedServerSocket.LOG.info("Accepted TLS connection from {} - {} - {}", new Object[]{this.sslSocket.getRemoteSocketAddress(), this.sslSocket.getSession().getProtocol(), this.sslSocket.getSession().getCipherSuite()});
         } else {
            if (!this.allowInsecureConnection) {
               this.prependableSocket.close();
               this.mode = UnifiedServerSocket.UnifiedSocket.Mode.PLAINTEXT;
               throw new IOException("Blocked insecure connection attempt");
            }

            this.prependableSocket.prependToInputStream(litmus, 0, bytesRead);
            this.mode = UnifiedServerSocket.UnifiedSocket.Mode.PLAINTEXT;
            UnifiedServerSocket.LOG.info("Accepted plaintext connection from {}", this.prependableSocket.getRemoteSocketAddress());
         }

      }

      private Socket getSocketAllowUnknownMode() {
         return (Socket)(this.isSecureSocket() ? this.sslSocket : this.prependableSocket);
      }

      private Socket getSocket() throws IOException {
         if (!this.isModeKnown()) {
            this.detectMode();
         }

         return (Socket)(this.mode == UnifiedServerSocket.UnifiedSocket.Mode.TLS ? this.sslSocket : this.prependableSocket);
      }

      public SSLSocket getSslSocket() throws IOException {
         if (!this.isModeKnown()) {
            this.detectMode();
         }

         if (!this.isSecureSocket()) {
            throw new SocketException("Socket mode is not TLS");
         } else {
            return this.sslSocket;
         }
      }

      public void connect(SocketAddress endpoint) throws IOException {
         this.getSocketAllowUnknownMode().connect(endpoint);
      }

      public void connect(SocketAddress endpoint, int timeout) throws IOException {
         this.getSocketAllowUnknownMode().connect(endpoint, timeout);
      }

      public void bind(SocketAddress bindpoint) throws IOException {
         this.getSocketAllowUnknownMode().bind(bindpoint);
      }

      public InetAddress getInetAddress() {
         return this.getSocketAllowUnknownMode().getInetAddress();
      }

      public InetAddress getLocalAddress() {
         return this.getSocketAllowUnknownMode().getLocalAddress();
      }

      public int getPort() {
         return this.getSocketAllowUnknownMode().getPort();
      }

      public int getLocalPort() {
         return this.getSocketAllowUnknownMode().getLocalPort();
      }

      public SocketAddress getRemoteSocketAddress() {
         return this.getSocketAllowUnknownMode().getRemoteSocketAddress();
      }

      public SocketAddress getLocalSocketAddress() {
         return this.getSocketAllowUnknownMode().getLocalSocketAddress();
      }

      public SocketChannel getChannel() {
         return this.getSocketAllowUnknownMode().getChannel();
      }

      public InputStream getInputStream() throws IOException {
         return new UnifiedInputStream(this);
      }

      public OutputStream getOutputStream() throws IOException {
         return new UnifiedOutputStream(this);
      }

      public void setTcpNoDelay(boolean on) throws SocketException {
         this.getSocketAllowUnknownMode().setTcpNoDelay(on);
      }

      public boolean getTcpNoDelay() throws SocketException {
         return this.getSocketAllowUnknownMode().getTcpNoDelay();
      }

      public void setSoLinger(boolean on, int linger) throws SocketException {
         this.getSocketAllowUnknownMode().setSoLinger(on, linger);
      }

      public int getSoLinger() throws SocketException {
         return this.getSocketAllowUnknownMode().getSoLinger();
      }

      public void sendUrgentData(int data) throws IOException {
         this.getSocket().sendUrgentData(data);
      }

      public void setOOBInline(boolean on) throws SocketException {
         this.getSocketAllowUnknownMode().setOOBInline(on);
      }

      public boolean getOOBInline() throws SocketException {
         return this.getSocketAllowUnknownMode().getOOBInline();
      }

      public synchronized void setSoTimeout(int timeout) throws SocketException {
         this.getSocketAllowUnknownMode().setSoTimeout(timeout);
      }

      public synchronized int getSoTimeout() throws SocketException {
         return this.getSocketAllowUnknownMode().getSoTimeout();
      }

      public synchronized void setSendBufferSize(int size) throws SocketException {
         this.getSocketAllowUnknownMode().setSendBufferSize(size);
      }

      public synchronized int getSendBufferSize() throws SocketException {
         return this.getSocketAllowUnknownMode().getSendBufferSize();
      }

      public synchronized void setReceiveBufferSize(int size) throws SocketException {
         this.getSocketAllowUnknownMode().setReceiveBufferSize(size);
      }

      public synchronized int getReceiveBufferSize() throws SocketException {
         return this.getSocketAllowUnknownMode().getReceiveBufferSize();
      }

      public void setKeepAlive(boolean on) throws SocketException {
         this.getSocketAllowUnknownMode().setKeepAlive(on);
      }

      public boolean getKeepAlive() throws SocketException {
         return this.getSocketAllowUnknownMode().getKeepAlive();
      }

      public void setTrafficClass(int tc) throws SocketException {
         this.getSocketAllowUnknownMode().setTrafficClass(tc);
      }

      public int getTrafficClass() throws SocketException {
         return this.getSocketAllowUnknownMode().getTrafficClass();
      }

      public void setReuseAddress(boolean on) throws SocketException {
         this.getSocketAllowUnknownMode().setReuseAddress(on);
      }

      public boolean getReuseAddress() throws SocketException {
         return this.getSocketAllowUnknownMode().getReuseAddress();
      }

      public synchronized void close() throws IOException {
         this.getSocketAllowUnknownMode().close();
      }

      public void shutdownInput() throws IOException {
         this.getSocketAllowUnknownMode().shutdownInput();
      }

      public void shutdownOutput() throws IOException {
         this.getSocketAllowUnknownMode().shutdownOutput();
      }

      public String toString() {
         return "UnifiedSocket[mode=" + this.mode.toString() + "socket=" + this.getSocketAllowUnknownMode().toString() + "]";
      }

      public boolean isConnected() {
         return this.getSocketAllowUnknownMode().isConnected();
      }

      public boolean isBound() {
         return this.getSocketAllowUnknownMode().isBound();
      }

      public boolean isClosed() {
         return this.getSocketAllowUnknownMode().isClosed();
      }

      public boolean isInputShutdown() {
         return this.getSocketAllowUnknownMode().isInputShutdown();
      }

      public boolean isOutputShutdown() {
         return this.getSocketAllowUnknownMode().isOutputShutdown();
      }

      public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
         this.getSocketAllowUnknownMode().setPerformancePreferences(connectionTime, latency, bandwidth);
      }

      private static enum Mode {
         UNKNOWN,
         PLAINTEXT,
         TLS;
      }
   }

   private static class UnifiedInputStream extends InputStream {
      private final UnifiedSocket unifiedSocket;
      private InputStream realInputStream;

      private UnifiedInputStream(UnifiedSocket unifiedSocket) {
         this.unifiedSocket = unifiedSocket;
         this.realInputStream = null;
      }

      public int read() throws IOException {
         return this.getRealInputStream().read();
      }

      public int read(byte[] b) throws IOException {
         return this.getRealInputStream().read(b);
      }

      public int read(byte[] b, int off, int len) throws IOException {
         return this.getRealInputStream().read(b, off, len);
      }

      private InputStream getRealInputStream() throws IOException {
         if (this.realInputStream == null) {
            this.realInputStream = this.unifiedSocket.getSocket().getInputStream();
         }

         return this.realInputStream;
      }

      public long skip(long n) throws IOException {
         return this.getRealInputStream().skip(n);
      }

      public int available() throws IOException {
         return this.getRealInputStream().available();
      }

      public void close() throws IOException {
         this.getRealInputStream().close();
      }

      public synchronized void mark(int readlimit) {
         try {
            this.getRealInputStream().mark(readlimit);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public synchronized void reset() throws IOException {
         this.getRealInputStream().reset();
      }

      public boolean markSupported() {
         try {
            return this.getRealInputStream().markSupported();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
   }

   private static class UnifiedOutputStream extends OutputStream {
      private final UnifiedSocket unifiedSocket;
      private OutputStream realOutputStream;

      private UnifiedOutputStream(UnifiedSocket unifiedSocket) {
         this.unifiedSocket = unifiedSocket;
         this.realOutputStream = null;
      }

      public void write(int b) throws IOException {
         this.getRealOutputStream().write(b);
      }

      public void write(byte[] b) throws IOException {
         this.getRealOutputStream().write(b);
      }

      public void write(byte[] b, int off, int len) throws IOException {
         this.getRealOutputStream().write(b, off, len);
      }

      public void flush() throws IOException {
         this.getRealOutputStream().flush();
      }

      public void close() throws IOException {
         this.getRealOutputStream().close();
      }

      private OutputStream getRealOutputStream() throws IOException {
         if (this.realOutputStream == null) {
            this.realOutputStream = this.unifiedSocket.getSocket().getOutputStream();
         }

         return this.realOutputStream;
      }
   }
}
