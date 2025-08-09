package org.sparkproject.jetty.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ReadPendingException;
import java.nio.channels.WritePendingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.AttributesMap;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.TypeUtil;

public class ProxyConnectionFactory extends DetectorConnectionFactory {
   public static final String TLS_VERSION = "TLS_VERSION";
   private static final Logger LOG = LoggerFactory.getLogger(ProxyConnectionFactory.class);

   public ProxyConnectionFactory() {
      this((String)null);
   }

   public ProxyConnectionFactory(String nextProtocol) {
      super(new ProxyV1ConnectionFactory(nextProtocol), new ProxyV2ConnectionFactory(nextProtocol));
   }

   private static ConnectionFactory findNextConnectionFactory(String nextProtocol, Connector connector, String currentProtocol, EndPoint endp) {
      currentProtocol = "[" + currentProtocol + "]";
      if (LOG.isDebugEnabled()) {
         LOG.debug("finding connection factory following {} for protocol {}", currentProtocol, nextProtocol);
      }

      String nextProtocolToFind = nextProtocol;
      if (nextProtocol == null) {
         nextProtocolToFind = AbstractConnectionFactory.findNextProtocol(connector, currentProtocol);
      }

      if (nextProtocolToFind == null) {
         throw new IllegalStateException("Cannot find protocol following '" + currentProtocol + "' in connector's protocol list " + String.valueOf(connector.getProtocols()) + " for " + String.valueOf(endp));
      } else {
         ConnectionFactory connectionFactory = connector.getConnectionFactory(nextProtocolToFind);
         if (connectionFactory == null) {
            throw new IllegalStateException("Cannot find protocol '" + nextProtocol + "' in connector's protocol list " + String.valueOf(connector.getProtocols()) + " for " + String.valueOf(endp));
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("found next connection factory {} for protocol {}", connectionFactory, nextProtocol);
            }

            return connectionFactory;
         }
      }
   }

   public int getMaxProxyHeader() {
      ProxyV2ConnectionFactory v2 = (ProxyV2ConnectionFactory)this.getBean(ProxyV2ConnectionFactory.class);
      return v2.getMaxProxyHeader();
   }

   public void setMaxProxyHeader(int maxProxyHeader) {
      ProxyV2ConnectionFactory v2 = (ProxyV2ConnectionFactory)this.getBean(ProxyV2ConnectionFactory.class);
      v2.setMaxProxyHeader(maxProxyHeader);
   }

   private static class ProxyV1ConnectionFactory extends AbstractConnectionFactory implements ConnectionFactory.Detecting {
      private static final byte[] SIGNATURE;
      private final String _nextProtocol;

      private ProxyV1ConnectionFactory(String nextProtocol) {
         super("proxy");
         this._nextProtocol = nextProtocol;
      }

      public ConnectionFactory.Detecting.Detection detect(ByteBuffer buffer) {
         if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
            ProxyConnectionFactory.LOG.debug("Proxy v1 attempting detection with {} bytes", buffer.remaining());
         }

         if (buffer.remaining() < SIGNATURE.length) {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 detection requires more bytes");
            }

            return ConnectionFactory.Detecting.Detection.NEED_MORE_BYTES;
         } else {
            for(int i = 0; i < SIGNATURE.length; ++i) {
               byte signatureByte = SIGNATURE[i];
               byte byteInBuffer = buffer.get(i);
               if (byteInBuffer != signatureByte) {
                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug("Proxy v1 detection unsuccessful");
                  }

                  return ConnectionFactory.Detecting.Detection.NOT_RECOGNIZED;
               }
            }

            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 detection succeeded");
            }

            return ConnectionFactory.Detecting.Detection.RECOGNIZED;
         }
      }

      public Connection newConnection(Connector connector, EndPoint endp) {
         ConnectionFactory nextConnectionFactory = ProxyConnectionFactory.findNextConnectionFactory(this._nextProtocol, connector, this.getProtocol(), endp);
         return this.configure(new ProxyProtocolV1Connection(endp, connector, nextConnectionFactory), connector, endp);
      }

      static {
         SIGNATURE = "PROXY".getBytes(StandardCharsets.US_ASCII);
      }

      private static class ProxyProtocolV1Connection extends AbstractConnection implements Connection.UpgradeFrom, Connection.UpgradeTo {
         private static final int CR_INDEX = 6;
         private static final int LF_INDEX = 7;
         private final Connector _connector;
         private final ConnectionFactory _next;
         private final ByteBuffer _buffer;
         private final StringBuilder _builder = new StringBuilder();
         private final String[] _fields = new String[6];
         private int _index;
         private int _length;

         private ProxyProtocolV1Connection(EndPoint endp, Connector connector, ConnectionFactory next) {
            super(endp, connector.getExecutor());
            this._connector = connector;
            this._next = next;
            this._buffer = this._connector.getByteBufferPool().acquire(this.getInputBufferSize(), true);
         }

         public void onFillable() {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 onFillable current index = {}", this._index);
            }

            try {
               while(true) {
                  if (this._index < 7) {
                     int fill = this.getEndPoint().fill(this._buffer);
                     if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                        ProxyConnectionFactory.LOG.debug("Proxy v1 filled buffer with {} bytes", fill);
                     }

                     if (fill < 0) {
                        this._connector.getByteBufferPool().release(this._buffer);
                        this.getEndPoint().shutdownOutput();
                        return;
                     }

                     if (fill == 0) {
                        this.fillInterested();
                        return;
                     }

                     if (!this.parse()) {
                        continue;
                     }
                  }

                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug("Proxy v1 onFillable parsing done, now upgrading");
                  }

                  this.upgrade();
                  break;
               }
            } catch (Throwable x) {
               ProxyConnectionFactory.LOG.warn("Proxy v1 error for {}", this.getEndPoint(), x);
               this.releaseAndClose();
            }

         }

         public void onOpen() {
            super.onOpen();

            try {
               while(this._index < 7) {
                  if (!this.parse()) {
                     if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                        ProxyConnectionFactory.LOG.debug("Proxy v1 onOpen parsing ran out of bytes, marking as fillInterested");
                     }

                     this.fillInterested();
                     return;
                  }
               }

               if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                  ProxyConnectionFactory.LOG.debug("Proxy v1 onOpen parsing done, now upgrading");
               }

               this.upgrade();
            } catch (Throwable x) {
               ProxyConnectionFactory.LOG.warn("Proxy v1 error for {}", this.getEndPoint(), x);
               this.releaseAndClose();
            }

         }

         public ByteBuffer onUpgradeFrom() {
            if (this._buffer.hasRemaining()) {
               ByteBuffer unconsumed = ByteBuffer.allocateDirect(this._buffer.remaining());
               unconsumed.put(this._buffer);
               unconsumed.flip();
               this._connector.getByteBufferPool().release(this._buffer);
               return unconsumed;
            } else {
               return null;
            }
         }

         public void onUpgradeTo(ByteBuffer buffer) {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 copying unconsumed buffer {}", BufferUtil.toDetailString(buffer));
            }

            BufferUtil.append(this._buffer, buffer);
         }

         private boolean parse() throws IOException {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 parsing {}", BufferUtil.toDetailString(this._buffer));
            }

            this._length += this._buffer.remaining();

            while(this._buffer.hasRemaining()) {
               byte b = this._buffer.get();
               if (this._index >= 6) {
                  if (b == 10) {
                     this._index = 7;
                     if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                        ProxyConnectionFactory.LOG.debug("Proxy v1 parsing is done");
                     }

                     return true;
                  }

                  throw new IOException("Proxy v1 bad CRLF " + (b & 255));
               }

               if (b != 32 && b != 13) {
                  if (b < 32) {
                     throw new IOException("Proxy v1 bad character " + (b & 255));
                  }

                  this._builder.append((char)b);
               } else {
                  this._fields[this._index++] = this._builder.toString();
                  this._builder.setLength(0);
                  if (b == 13) {
                     this._index = 6;
                  }
               }
            }

            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 parsing requires more bytes");
            }

            return false;
         }

         private void releaseAndClose() {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 releasing buffer and closing");
            }

            this._connector.getByteBufferPool().release(this._buffer);
            this.close();
         }

         private void upgrade() {
            int proxyLineLength = this._length - this._buffer.remaining();
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v1 pre-upgrade packet length (including CRLF) is {}", proxyLineLength);
            }

            if (proxyLineLength >= 110) {
               ProxyConnectionFactory.LOG.warn("Proxy v1 PROXY line too long {} for {}", proxyLineLength, this.getEndPoint());
               this.releaseAndClose();
            } else if (!"PROXY".equals(this._fields[0])) {
               ProxyConnectionFactory.LOG.warn("Proxy v1 not PROXY protocol for {}", this.getEndPoint());
               this.releaseAndClose();
            } else {
               String srcIP = this._fields[2];
               String srcPort = this._fields[4];
               String dstIP = this._fields[3];
               String dstPort = this._fields[5];
               boolean unknown = "UNKNOWN".equalsIgnoreCase(this._fields[1]);
               EndPoint proxyEndPoint;
               if (unknown) {
                  EndPoint endPoint = this.getEndPoint();
                  proxyEndPoint = new ProxyEndPoint(endPoint, endPoint.getLocalSocketAddress(), endPoint.getRemoteSocketAddress());
               } else {
                  SocketAddress remote = new InetSocketAddress(srcIP, Integer.parseInt(srcPort));
                  SocketAddress local = new InetSocketAddress(dstIP, Integer.parseInt(dstPort));
                  proxyEndPoint = new ProxyEndPoint(this.getEndPoint(), local, remote);
               }

               if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                  ProxyConnectionFactory.LOG.debug("Proxy v1 next protocol '{}' for {} -> {}", new Object[]{this._next, this.getEndPoint(), proxyEndPoint});
               }

               DetectorConnectionFactory.upgradeToConnectionFactory(this._next, this._connector, proxyEndPoint);
            }
         }
      }
   }

   private static class ProxyV2ConnectionFactory extends AbstractConnectionFactory implements ConnectionFactory.Detecting {
      private static final byte[] SIGNATURE = new byte[]{13, 10, 13, 10, 0, 13, 10, 81, 85, 73, 84, 10};
      private final String _nextProtocol;
      private int _maxProxyHeader = 1024;

      private ProxyV2ConnectionFactory(String nextProtocol) {
         super("proxy");
         this._nextProtocol = nextProtocol;
      }

      public ConnectionFactory.Detecting.Detection detect(ByteBuffer buffer) {
         if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
            ProxyConnectionFactory.LOG.debug("Proxy v2 attempting detection with {} bytes", buffer.remaining());
         }

         if (buffer.remaining() < SIGNATURE.length) {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v2 detection requires more bytes");
            }

            return ConnectionFactory.Detecting.Detection.NEED_MORE_BYTES;
         } else {
            for(int i = 0; i < SIGNATURE.length; ++i) {
               byte signatureByte = SIGNATURE[i];
               byte byteInBuffer = buffer.get(i);
               if (byteInBuffer != signatureByte) {
                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug("Proxy v2 detection unsuccessful");
                  }

                  return ConnectionFactory.Detecting.Detection.NOT_RECOGNIZED;
               }
            }

            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v2 detection succeeded");
            }

            return ConnectionFactory.Detecting.Detection.RECOGNIZED;
         }
      }

      public int getMaxProxyHeader() {
         return this._maxProxyHeader;
      }

      public void setMaxProxyHeader(int maxProxyHeader) {
         this._maxProxyHeader = maxProxyHeader;
      }

      public Connection newConnection(Connector connector, EndPoint endp) {
         ConnectionFactory nextConnectionFactory = ProxyConnectionFactory.findNextConnectionFactory(this._nextProtocol, connector, this.getProtocol(), endp);
         return this.configure(new ProxyProtocolV2Connection(endp, connector, nextConnectionFactory), connector, endp);
      }

      private static enum Family {
         UNSPEC,
         INET,
         INET6,
         UNIX;

         // $FF: synthetic method
         private static Family[] $values() {
            return new Family[]{UNSPEC, INET, INET6, UNIX};
         }
      }

      private static enum Transport {
         UNSPEC,
         STREAM,
         DGRAM;

         // $FF: synthetic method
         private static Transport[] $values() {
            return new Transport[]{UNSPEC, STREAM, DGRAM};
         }
      }

      private class ProxyProtocolV2Connection extends AbstractConnection implements Connection.UpgradeFrom, Connection.UpgradeTo {
         private static final int HEADER_LENGTH = 16;
         private final Connector _connector;
         private final ConnectionFactory _next;
         private final ByteBuffer _buffer;
         private boolean _local;
         private Family _family;
         private int _length;
         private boolean _headerParsed;

         protected ProxyProtocolV2Connection(EndPoint endp, Connector connector, ConnectionFactory next) {
            super(endp, connector.getExecutor());
            this._connector = connector;
            this._next = next;
            this._buffer = this._connector.getByteBufferPool().acquire(this.getInputBufferSize(), true);
         }

         public void onUpgradeTo(ByteBuffer buffer) {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v2 copying unconsumed buffer {}", BufferUtil.toDetailString(buffer));
            }

            BufferUtil.append(this._buffer, buffer);
         }

         public void onOpen() {
            super.onOpen();

            try {
               this.parseHeader();
               if (this._headerParsed && this._buffer.remaining() >= this._length) {
                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug("Proxy v2 onOpen parsing fixed length packet part done, now upgrading");
                  }

                  this.parseBodyAndUpgrade();
               } else {
                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug("Proxy v2 onOpen parsing fixed length packet ran out of bytes, marking as fillInterested");
                  }

                  this.fillInterested();
               }
            } catch (Exception x) {
               ProxyConnectionFactory.LOG.warn("Proxy v2 error for {}", this.getEndPoint(), x);
               this.releaseAndClose();
            }

         }

         public void onFillable() {
            try {
               if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                  ProxyConnectionFactory.LOG.debug("Proxy v2 onFillable header parsed? {}", this._headerParsed);
               }

               while(!this._headerParsed) {
                  int fill = this.getEndPoint().fill(this._buffer);
                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug("Proxy v2 filled buffer with {} bytes", fill);
                  }

                  if (fill < 0) {
                     this._connector.getByteBufferPool().release(this._buffer);
                     this.getEndPoint().shutdownOutput();
                     return;
                  }

                  if (fill == 0) {
                     this.fillInterested();
                     return;
                  }

                  this.parseHeader();
               }

               if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                  ProxyConnectionFactory.LOG.debug("Proxy v2 onFillable header parsed, length = {}, buffer = {}", this._length, BufferUtil.toDetailString(this._buffer));
               }

               while(this._buffer.remaining() < this._length) {
                  int fill = this.getEndPoint().fill(this._buffer);
                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug("Proxy v2 filled buffer with {} bytes", fill);
                  }

                  if (fill < 0) {
                     this._connector.getByteBufferPool().release(this._buffer);
                     this.getEndPoint().shutdownOutput();
                     return;
                  }

                  if (fill == 0) {
                     this.fillInterested();
                     return;
                  }
               }

               this.parseBodyAndUpgrade();
            } catch (Throwable x) {
               ProxyConnectionFactory.LOG.warn("Proxy v2 error for {}", this.getEndPoint(), x);
               this.releaseAndClose();
            }

         }

         public ByteBuffer onUpgradeFrom() {
            if (this._buffer.hasRemaining()) {
               ByteBuffer unconsumed = ByteBuffer.allocateDirect(this._buffer.remaining());
               unconsumed.put(this._buffer);
               unconsumed.flip();
               this._connector.getByteBufferPool().release(this._buffer);
               return unconsumed;
            } else {
               return null;
            }
         }

         private void parseBodyAndUpgrade() throws IOException {
            int nonProxyRemaining = this._buffer.remaining() - this._length;
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v2 parsing body, length = {}, buffer = {}", this._length, BufferUtil.toHexSummary(this._buffer));
            }

            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v2 body {} from {} for {}", new Object[]{this._next, BufferUtil.toHexSummary(this._buffer), this});
            }

            EndPoint endPoint = this.getEndPoint();
            ProxyEndPoint proxyEndPoint;
            if (this._local) {
               this._buffer.position(this._buffer.position() + this._length);
               proxyEndPoint = new ProxyEndPoint(endPoint, endPoint.getLocalSocketAddress(), endPoint.getRemoteSocketAddress());
            } else {
               SocketAddress local;
               SocketAddress remote;
               switch (this._family.ordinal()) {
                  case 1:
                     byte[] addr = new byte[4];
                     this._buffer.get(addr);
                     InetAddress srcAddr = Inet4Address.getByAddress(addr);
                     this._buffer.get(addr);
                     InetAddress dstAddr = Inet4Address.getByAddress(addr);
                     int srcPort = this._buffer.getChar();
                     int dstPort = this._buffer.getChar();
                     local = new InetSocketAddress(dstAddr, dstPort);
                     remote = new InetSocketAddress(srcAddr, srcPort);
                     break;
                  case 2:
                     byte[] addr = new byte[16];
                     this._buffer.get(addr);
                     InetAddress srcAddr = Inet6Address.getByAddress(addr);
                     this._buffer.get(addr);
                     InetAddress dstAddr = Inet6Address.getByAddress(addr);
                     int srcPort = this._buffer.getChar();
                     int dstPort = this._buffer.getChar();
                     local = new InetSocketAddress(dstAddr, dstPort);
                     remote = new InetSocketAddress(srcAddr, srcPort);
                     break;
                  case 3:
                     byte[] addr = new byte[108];
                     this._buffer.get(addr);
                     String src = ProxyConnectionFactory.UnixDomain.toPath(addr);
                     this._buffer.get(addr);
                     String dst = ProxyConnectionFactory.UnixDomain.toPath(addr);
                     local = ProxyConnectionFactory.UnixDomain.newSocketAddress(dst);
                     remote = ProxyConnectionFactory.UnixDomain.newSocketAddress(src);
                     break;
                  default:
                     throw new IllegalStateException("Unsupported family " + String.valueOf(this._family));
               }

               proxyEndPoint = new ProxyEndPoint(endPoint, local, remote);

               while(this._buffer.remaining() > nonProxyRemaining) {
                  int type = 255 & this._buffer.get();
                  int length = this._buffer.getChar();
                  byte[] value = new byte[length];
                  this._buffer.get(value);
                  if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                     ProxyConnectionFactory.LOG.debug(String.format("Proxy v2 T=%x L=%d V=%s for %s", type, length, TypeUtil.toHexString(value), this));
                  }

                  if (type != 4) {
                     proxyEndPoint.putTLV(type, value);
                  }

                  if (type == 32) {
                     int client = value[0] & 255;
                     if (client == 1) {
                        int i = 5;

                        while(i < length) {
                           int subType = value[i++] & 255;
                           int subLength = (value[i++] & 255) * 256 + (value[i++] & 255);
                           byte[] subValue = new byte[subLength];
                           System.arraycopy(value, i, subValue, 0, subLength);
                           i += subLength;
                           if (subType == 33) {
                              String tlsVersion = new String(subValue, StandardCharsets.US_ASCII);
                              proxyEndPoint.setAttribute("TLS_VERSION", tlsVersion);
                           }
                        }
                     }
                  }
               }

               if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                  ProxyConnectionFactory.LOG.debug("Proxy v2 {} {}", endPoint, proxyEndPoint);
               }
            }

            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v2 parsing dynamic packet part is now done, upgrading to {}", ProxyV2ConnectionFactory.this._nextProtocol);
            }

            DetectorConnectionFactory.upgradeToConnectionFactory(this._next, this._connector, proxyEndPoint);
         }

         private void parseHeader() throws IOException {
            if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
               ProxyConnectionFactory.LOG.debug("Proxy v2 parsing fixed length packet part, buffer = {}", BufferUtil.toDetailString(this._buffer));
            }

            if (this._buffer.remaining() >= 16) {
               if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                  ProxyConnectionFactory.LOG.debug("Proxy v2 header {} for {}", BufferUtil.toHexSummary(this._buffer), this);
               }

               for(byte signatureByte : ProxyConnectionFactory.ProxyV2ConnectionFactory.SIGNATURE) {
                  if (this._buffer.get() != signatureByte) {
                     throw new IOException("Proxy v2 bad PROXY signature");
                  }
               }

               int versionAndCommand = 255 & this._buffer.get();
               if ((versionAndCommand & 240) != 32) {
                  throw new IOException("Proxy v2 bad PROXY version");
               } else {
                  this._local = (versionAndCommand & 15) == 0;
                  int transportAndFamily = 255 & this._buffer.get();
                  switch (transportAndFamily >> 4) {
                     case 0:
                        this._family = ProxyConnectionFactory.ProxyV2ConnectionFactory.Family.UNSPEC;
                        break;
                     case 1:
                        this._family = ProxyConnectionFactory.ProxyV2ConnectionFactory.Family.INET;
                        break;
                     case 2:
                        this._family = ProxyConnectionFactory.ProxyV2ConnectionFactory.Family.INET6;
                        break;
                     case 3:
                        this._family = ProxyConnectionFactory.ProxyV2ConnectionFactory.Family.UNIX;
                        break;
                     default:
                        throw new IOException("Proxy v2 bad PROXY family");
                  }

                  Transport transport;
                  switch (transportAndFamily & 15) {
                     case 0:
                        transport = ProxyConnectionFactory.ProxyV2ConnectionFactory.Transport.UNSPEC;
                        break;
                     case 1:
                        transport = ProxyConnectionFactory.ProxyV2ConnectionFactory.Transport.STREAM;
                        break;
                     case 2:
                        transport = ProxyConnectionFactory.ProxyV2ConnectionFactory.Transport.DGRAM;
                        break;
                     default:
                        throw new IOException("Proxy v2 bad PROXY family");
                  }

                  this._length = this._buffer.getChar();
                  if (this._local || this._family != ProxyConnectionFactory.ProxyV2ConnectionFactory.Family.UNSPEC && transport == ProxyConnectionFactory.ProxyV2ConnectionFactory.Transport.STREAM) {
                     if (this._length > ProxyV2ConnectionFactory.this.getMaxProxyHeader()) {
                        throw new IOException(String.format("Proxy v2 Unsupported PROXY mode 0x%x,0x%x,0x%x", versionAndCommand, transportAndFamily, this._length));
                     } else {
                        if (ProxyConnectionFactory.LOG.isDebugEnabled()) {
                           ProxyConnectionFactory.LOG.debug("Proxy v2 fixed length packet part is now parsed");
                        }

                        this._headerParsed = true;
                     }
                  } else {
                     throw new IOException(String.format("Proxy v2 unsupported PROXY mode 0x%x,0x%x", versionAndCommand, transportAndFamily));
                  }
               }
            }
         }

         private void releaseAndClose() {
            this._connector.getByteBufferPool().release(this._buffer);
            this.close();
         }
      }
   }

   public static class ProxyEndPoint extends AttributesMap implements EndPoint, EndPoint.Wrapper {
      private static final int PP2_TYPE_NOOP = 4;
      private static final int PP2_TYPE_SSL = 32;
      private static final int PP2_TYPE_SSL_PP2_CLIENT_SSL = 1;
      private static final int PP2_SUBTYPE_SSL_VERSION = 33;
      private final EndPoint _endPoint;
      private final SocketAddress _local;
      private final SocketAddress _remote;
      private Map _tlvs;

      /** @deprecated */
      @Deprecated
      public ProxyEndPoint(EndPoint endPoint, InetSocketAddress remote, InetSocketAddress local) {
         this(endPoint, (SocketAddress)local, (SocketAddress)remote);
      }

      public ProxyEndPoint(EndPoint endPoint, SocketAddress local, SocketAddress remote) {
         this._endPoint = endPoint;
         this._local = local;
         this._remote = remote;
      }

      public EndPoint unwrap() {
         return this._endPoint;
      }

      private void putTLV(int type, byte[] value) {
         if (this._tlvs == null) {
            this._tlvs = new HashMap();
         }

         this._tlvs.put(type, value);
      }

      public byte[] getTLV(int type) {
         return this._tlvs != null ? (byte[])this._tlvs.get(type) : null;
      }

      public void close(Throwable cause) {
         this._endPoint.close(cause);
      }

      public int fill(ByteBuffer buffer) throws IOException {
         return this._endPoint.fill(buffer);
      }

      public void fillInterested(Callback callback) throws ReadPendingException {
         this._endPoint.fillInterested(callback);
      }

      public boolean flush(ByteBuffer... buffer) throws IOException {
         return this._endPoint.flush(buffer);
      }

      public Connection getConnection() {
         return this._endPoint.getConnection();
      }

      public void setConnection(Connection connection) {
         this._endPoint.setConnection(connection);
      }

      public long getCreatedTimeStamp() {
         return this._endPoint.getCreatedTimeStamp();
      }

      public long getIdleTimeout() {
         return this._endPoint.getIdleTimeout();
      }

      public void setIdleTimeout(long idleTimeout) {
         this._endPoint.setIdleTimeout(idleTimeout);
      }

      public InetSocketAddress getLocalAddress() {
         SocketAddress local = this.getLocalSocketAddress();
         return local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
      }

      public SocketAddress getLocalSocketAddress() {
         return this._local;
      }

      public InetSocketAddress getRemoteAddress() {
         SocketAddress remote = this.getRemoteSocketAddress();
         return remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
      }

      public SocketAddress getRemoteSocketAddress() {
         return this._remote;
      }

      public Object getTransport() {
         return this._endPoint.getTransport();
      }

      public boolean isFillInterested() {
         return this._endPoint.isFillInterested();
      }

      public boolean isInputShutdown() {
         return this._endPoint.isInputShutdown();
      }

      public boolean isOpen() {
         return this._endPoint.isOpen();
      }

      public boolean isOutputShutdown() {
         return this._endPoint.isOutputShutdown();
      }

      public void onClose(Throwable cause) {
         this._endPoint.onClose(cause);
      }

      public void onOpen() {
         this._endPoint.onOpen();
      }

      public void shutdownOutput() {
         this._endPoint.shutdownOutput();
      }

      public String toString() {
         return String.format("%s@%x[remote=%s,local=%s,endpoint=%s]", this.getClass().getSimpleName(), this.hashCode(), this._remote, this._local, this._endPoint);
      }

      public boolean tryFillInterested(Callback callback) {
         return this._endPoint.tryFillInterested(callback);
      }

      public void upgrade(Connection newConnection) {
         this._endPoint.upgrade(newConnection);
      }

      public void write(Callback callback, ByteBuffer... buffers) throws WritePendingException {
         this._endPoint.write(callback, buffers);
      }
   }

   private static class UnixDomain {
      private static final Class unixDomainSocketAddress = probe();

      private static Class probe() {
         try {
            return ClassLoader.getPlatformClassLoader().loadClass("java.net.UnixDomainSocketAddress");
         } catch (Throwable var1) {
            return null;
         }
      }

      private static SocketAddress newSocketAddress(String path) {
         try {
            return unixDomainSocketAddress != null ? (SocketAddress)unixDomainSocketAddress.getMethod("of", String.class).invoke((Object)null, path) : null;
         } catch (Throwable var2) {
            return null;
         }
      }

      private static String toPath(byte[] bytes) {
         int i = 0;

         while(i < bytes.length && bytes[i++] != 0) {
         }

         return (new String(bytes, 0, i, StandardCharsets.US_ASCII)).trim();
      }
   }
}
