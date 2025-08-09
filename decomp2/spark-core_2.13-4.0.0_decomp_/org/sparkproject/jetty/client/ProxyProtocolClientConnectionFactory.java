package org.sparkproject.jetty.client;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.thread.Invocable;

public abstract class ProxyProtocolClientConnectionFactory implements ClientConnectionFactory {
   private final ClientConnectionFactory factory;

   private ProxyProtocolClientConnectionFactory(ClientConnectionFactory factory) {
      this.factory = factory;
   }

   public ClientConnectionFactory getClientConnectionFactory() {
      return this.factory;
   }

   public Connection newConnection(EndPoint endPoint, Map context) {
      ProxyProtocolConnection connection = this.newProxyProtocolConnection(endPoint, context);
      return this.customize(connection, context);
   }

   protected abstract ProxyProtocolConnection newProxyProtocolConnection(EndPoint var1, Map var2);

   public static class V1 extends ProxyProtocolClientConnectionFactory {
      public V1(ClientConnectionFactory factory) {
         super(factory);
      }

      protected ProxyProtocolConnection newProxyProtocolConnection(EndPoint endPoint, Map context) {
         HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
         Executor executor = destination.getHttpClient().getExecutor();
         Tag tag = (Tag)destination.getOrigin().getTag();
         if (tag == null) {
            SocketAddress local = endPoint.getLocalSocketAddress();
            InetSocketAddress inetLocal = local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
            InetAddress localAddress = inetLocal == null ? null : inetLocal.getAddress();
            SocketAddress remote = endPoint.getRemoteSocketAddress();
            InetSocketAddress inetRemote = remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
            InetAddress remoteAddress = inetRemote == null ? null : inetRemote.getAddress();
            String family = local != null && inetLocal != null ? (localAddress instanceof Inet4Address ? "TCP4" : "TCP6") : "UNKNOWN";
            tag = new Tag(family, localAddress == null ? null : localAddress.getHostAddress(), inetLocal == null ? 0 : inetLocal.getPort(), remoteAddress == null ? null : remoteAddress.getHostAddress(), inetRemote == null ? 0 : inetRemote.getPort());
         }

         return new ProxyProtocolConnectionV1(endPoint, executor, this.getClientConnectionFactory(), context, tag);
      }

      public static class Tag implements ClientConnectionFactory.Decorator {
         public static final Tag UNKNOWN = new Tag("UNKNOWN", (String)null, 0, (String)null, 0);
         private final String family;
         private final String srcIP;
         private final int srcPort;
         private final String dstIP;
         private final int dstPort;

         public Tag() {
            this((String)null, 0);
         }

         public Tag(String srcIP, int srcPort) {
            this((String)null, srcIP, srcPort, (String)null, 0);
         }

         public Tag(String family, String srcIP, int srcPort, String dstIP, int dstPort) {
            this.family = family;
            this.srcIP = srcIP;
            this.srcPort = srcPort;
            this.dstIP = dstIP;
            this.dstPort = dstPort;
         }

         public String getFamily() {
            return this.family;
         }

         public String getSourceAddress() {
            return this.srcIP;
         }

         public int getSourcePort() {
            return this.srcPort;
         }

         public String getDestinationAddress() {
            return this.dstIP;
         }

         public int getDestinationPort() {
            return this.dstPort;
         }

         public ClientConnectionFactory apply(ClientConnectionFactory factory) {
            return new V1(factory);
         }

         public boolean equals(Object obj) {
            if (this == obj) {
               return true;
            } else if (obj != null && this.getClass() == obj.getClass()) {
               Tag that = (Tag)obj;
               return Objects.equals(this.family, that.family) && Objects.equals(this.srcIP, that.srcIP) && this.srcPort == that.srcPort && Objects.equals(this.dstIP, that.dstIP) && this.dstPort == that.dstPort;
            } else {
               return false;
            }
         }

         public int hashCode() {
            return Objects.hash(new Object[]{this.family, this.srcIP, this.srcPort, this.dstIP, this.dstPort});
         }
      }
   }

   public static class V2 extends ProxyProtocolClientConnectionFactory {
      public V2(ClientConnectionFactory factory) {
         super(factory);
      }

      protected ProxyProtocolConnection newProxyProtocolConnection(EndPoint endPoint, Map context) {
         HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
         Executor executor = destination.getHttpClient().getExecutor();
         Tag tag = (Tag)destination.getOrigin().getTag();
         if (tag == null) {
            SocketAddress local = endPoint.getLocalSocketAddress();
            InetSocketAddress inetLocal = local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
            InetAddress localAddress = inetLocal == null ? null : inetLocal.getAddress();
            SocketAddress remote = endPoint.getRemoteSocketAddress();
            InetSocketAddress inetRemote = remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
            InetAddress remoteAddress = inetRemote == null ? null : inetRemote.getAddress();
            Tag.Family family = local != null && inetLocal != null ? (localAddress instanceof Inet4Address ? ProxyProtocolClientConnectionFactory.V2.Tag.Family.INET4 : ProxyProtocolClientConnectionFactory.V2.Tag.Family.INET6) : ProxyProtocolClientConnectionFactory.V2.Tag.Family.UNSPEC;
            tag = new Tag(ProxyProtocolClientConnectionFactory.V2.Tag.Command.PROXY, family, ProxyProtocolClientConnectionFactory.V2.Tag.Protocol.STREAM, localAddress == null ? null : localAddress.getHostAddress(), inetLocal == null ? 0 : inetLocal.getPort(), remoteAddress == null ? null : remoteAddress.getHostAddress(), inetRemote == null ? 0 : inetRemote.getPort(), (List)null);
         }

         return new ProxyProtocolConnectionV2(endPoint, executor, this.getClientConnectionFactory(), context, tag);
      }

      public static class Tag implements ClientConnectionFactory.Decorator {
         public static final Tag LOCAL;
         private final Command command;
         private final Family family;
         private final Protocol protocol;
         private final String srcIP;
         private final int srcPort;
         private final String dstIP;
         private final int dstPort;
         private final List tlvs;

         public Tag() {
            this((String)null, 0);
         }

         public Tag(String srcIP, int srcPort) {
            this(ProxyProtocolClientConnectionFactory.V2.Tag.Command.PROXY, (Family)null, ProxyProtocolClientConnectionFactory.V2.Tag.Protocol.STREAM, srcIP, srcPort, (String)null, 0, (List)null);
         }

         public Tag(String srcIP, int srcPort, List tlvs) {
            this(ProxyProtocolClientConnectionFactory.V2.Tag.Command.PROXY, (Family)null, ProxyProtocolClientConnectionFactory.V2.Tag.Protocol.STREAM, srcIP, srcPort, (String)null, 0, tlvs);
         }

         public Tag(Command command, Family family, Protocol protocol, String srcIP, int srcPort, String dstIP, int dstPort, List tlvs) {
            this.command = command;
            this.family = family;
            this.protocol = protocol;
            this.srcIP = srcIP;
            this.srcPort = srcPort;
            this.dstIP = dstIP;
            this.dstPort = dstPort;
            this.tlvs = tlvs;
         }

         public Command getCommand() {
            return this.command;
         }

         public Family getFamily() {
            return this.family;
         }

         public Protocol getProtocol() {
            return this.protocol;
         }

         public String getSourceAddress() {
            return this.srcIP;
         }

         public int getSourcePort() {
            return this.srcPort;
         }

         public String getDestinationAddress() {
            return this.dstIP;
         }

         public int getDestinationPort() {
            return this.dstPort;
         }

         public List getTLVs() {
            return this.tlvs;
         }

         public ClientConnectionFactory apply(ClientConnectionFactory factory) {
            return new V2(factory);
         }

         public boolean equals(Object obj) {
            if (this == obj) {
               return true;
            } else if (obj != null && this.getClass() == obj.getClass()) {
               Tag that = (Tag)obj;
               return this.command == that.command && this.family == that.family && this.protocol == that.protocol && Objects.equals(this.srcIP, that.srcIP) && this.srcPort == that.srcPort && Objects.equals(this.dstIP, that.dstIP) && this.dstPort == that.dstPort && Objects.equals(this.tlvs, that.tlvs);
            } else {
               return false;
            }
         }

         public int hashCode() {
            return Objects.hash(new Object[]{this.command, this.family, this.protocol, this.srcIP, this.srcPort, this.dstIP, this.dstPort, this.tlvs});
         }

         static {
            LOCAL = new Tag(ProxyProtocolClientConnectionFactory.V2.Tag.Command.LOCAL, ProxyProtocolClientConnectionFactory.V2.Tag.Family.UNSPEC, ProxyProtocolClientConnectionFactory.V2.Tag.Protocol.UNSPEC, (String)null, 0, (String)null, 0, (List)null);
         }

         public static enum Command {
            LOCAL,
            PROXY;

            // $FF: synthetic method
            private static Command[] $values() {
               return new Command[]{LOCAL, PROXY};
            }
         }

         public static enum Family {
            UNSPEC,
            INET4,
            INET6,
            UNIX;

            // $FF: synthetic method
            private static Family[] $values() {
               return new Family[]{UNSPEC, INET4, INET6, UNIX};
            }
         }

         public static enum Protocol {
            UNSPEC,
            STREAM,
            DGRAM;

            // $FF: synthetic method
            private static Protocol[] $values() {
               return new Protocol[]{UNSPEC, STREAM, DGRAM};
            }
         }

         public static class TLV {
            private final int type;
            private final byte[] value;

            public TLV(int type, byte[] value) {
               if (type >= 0 && type <= 255) {
                  if (value != null && value.length > 65535) {
                     throw new IllegalArgumentException("Invalid value length: " + value.length);
                  } else {
                     this.type = type;
                     this.value = (byte[])Objects.requireNonNull(value);
                  }
               } else {
                  throw new IllegalArgumentException("Invalid type: " + type);
               }
            }

            public int getType() {
               return this.type;
            }

            public byte[] getValue() {
               return this.value;
            }

            public boolean equals(Object obj) {
               if (this == obj) {
                  return true;
               } else if (obj != null && this.getClass() == obj.getClass()) {
                  TLV that = (TLV)obj;
                  return this.type == that.type && Arrays.equals(this.value, that.value);
               } else {
                  return false;
               }
            }

            public int hashCode() {
               int result = Objects.hash(new Object[]{this.type});
               result = 31 * result + Arrays.hashCode(this.value);
               return result;
            }
         }
      }
   }

   protected abstract static class ProxyProtocolConnection extends AbstractConnection implements Callback {
      protected static final Logger LOG = LoggerFactory.getLogger(ProxyProtocolConnection.class);
      private final ClientConnectionFactory factory;
      private final Map context;

      private ProxyProtocolConnection(EndPoint endPoint, Executor executor, ClientConnectionFactory factory, Map context) {
         super(endPoint, executor);
         this.factory = factory;
         this.context = context;
      }

      public void onOpen() {
         super.onOpen();
         this.writePROXYBytes(this.getEndPoint(), this);
      }

      protected abstract void writePROXYBytes(EndPoint var1, Callback var2);

      public void succeeded() {
         try {
            EndPoint endPoint = this.getEndPoint();
            Connection connection = this.factory.newConnection(endPoint, this.context);
            if (LOG.isDebugEnabled()) {
               LOG.debug("Written PROXY line, upgrading to {}", connection);
            }

            endPoint.upgrade(connection);
         } catch (Throwable x) {
            this.failed(x);
         }

      }

      public void failed(Throwable x) {
         this.close();
         Promise<?> promise = (Promise)this.context.get("org.sparkproject.jetty.client.connection.promise");
         promise.failed(x);
      }

      public Invocable.InvocationType getInvocationType() {
         return Invocable.InvocationType.NON_BLOCKING;
      }

      public void onFillable() {
      }
   }

   private static class ProxyProtocolConnectionV1 extends ProxyProtocolConnection {
      private final V1.Tag tag;

      public ProxyProtocolConnectionV1(EndPoint endPoint, Executor executor, ClientConnectionFactory factory, Map context, V1.Tag tag) {
         super(endPoint, executor, factory, context);
         this.tag = tag;
      }

      protected void writePROXYBytes(EndPoint endPoint, Callback callback) {
         try {
            SocketAddress local = endPoint.getLocalSocketAddress();
            InetSocketAddress inetLocal = local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
            InetAddress localAddress = inetLocal == null ? null : inetLocal.getAddress();
            SocketAddress remote = endPoint.getRemoteSocketAddress();
            InetSocketAddress inetRemote = remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
            InetAddress remoteAddress = inetRemote == null ? null : inetRemote.getAddress();
            String family = this.tag.getFamily();
            String srcIP = this.tag.getSourceAddress();
            int srcPort = this.tag.getSourcePort();
            String dstIP = this.tag.getDestinationAddress();
            int dstPort = this.tag.getDestinationPort();
            if (family == null) {
               family = local != null && inetLocal != null ? (localAddress instanceof Inet4Address ? "TCP4" : "TCP6") : "UNKNOWN";
            }

            family = family.toUpperCase(Locale.ENGLISH);
            boolean unknown = family.equals("UNKNOWN");
            StringBuilder builder = new StringBuilder(64);
            builder.append("PROXY ").append(family);
            if (!unknown) {
               if (srcIP == null && localAddress != null) {
                  srcIP = localAddress.getHostAddress();
               }

               builder.append(" ").append(srcIP);
               if (dstIP == null && remoteAddress != null) {
                  dstIP = remoteAddress.getHostAddress();
               }

               builder.append(" ").append(dstIP);
               if (srcPort <= 0 && inetLocal != null) {
                  srcPort = inetLocal.getPort();
               }

               builder.append(" ").append(srcPort);
               if (dstPort <= 0 && inetRemote != null) {
                  dstPort = inetRemote.getPort();
               }

               builder.append(" ").append(dstPort);
            }

            builder.append("\r\n");
            String line = builder.toString();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Writing PROXY bytes: {}", line.trim());
            }

            ByteBuffer buffer = ByteBuffer.wrap(line.getBytes(StandardCharsets.US_ASCII));
            endPoint.write(callback, buffer);
         } catch (Throwable x) {
            callback.failed(x);
         }

      }
   }

   private static class ProxyProtocolConnectionV2 extends ProxyProtocolConnection {
      private static final byte[] MAGIC = new byte[]{13, 10, 13, 10, 0, 13, 10, 81, 85, 73, 84, 10};
      private final V2.Tag tag;

      public ProxyProtocolConnectionV2(EndPoint endPoint, Executor executor, ClientConnectionFactory factory, Map context, V2.Tag tag) {
         super(endPoint, executor, factory, context);
         this.tag = tag;
      }

      protected void writePROXYBytes(EndPoint endPoint, Callback callback) {
         try {
            int capacity = MAGIC.length;
            ++capacity;
            ++capacity;
            capacity += 2;
            capacity += 216;
            List<V2.Tag.TLV> tlvs = this.tag.getTLVs();
            int vectorsLength = tlvs == null ? 0 : tlvs.stream().mapToInt((tlvx) -> 3 + tlvx.getValue().length).sum();
            capacity += vectorsLength;
            ByteBuffer buffer = ByteBuffer.allocateDirect(capacity);
            buffer.put(MAGIC);
            V2.Tag.Command command = this.tag.getCommand();
            int versionAndCommand = 32 | command.ordinal() & 15;
            buffer.put((byte)versionAndCommand);
            V2.Tag.Family family = this.tag.getFamily();
            String srcAddr = this.tag.getSourceAddress();
            SocketAddress local = endPoint.getLocalSocketAddress();
            InetSocketAddress inetLocal = local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
            InetAddress localAddress = inetLocal == null ? null : inetLocal.getAddress();
            if (srcAddr == null && localAddress != null) {
               srcAddr = localAddress.getHostAddress();
            }

            int srcPort = this.tag.getSourcePort();
            if (srcPort <= 0 && inetLocal != null) {
               srcPort = inetLocal.getPort();
            }

            if (family == null) {
               family = local != null && inetLocal != null ? (localAddress instanceof Inet4Address ? ProxyProtocolClientConnectionFactory.V2.Tag.Family.INET4 : ProxyProtocolClientConnectionFactory.V2.Tag.Family.INET6) : ProxyProtocolClientConnectionFactory.V2.Tag.Family.UNSPEC;
            }

            V2.Tag.Protocol protocol = this.tag.getProtocol();
            if (protocol == null) {
               protocol = local == null ? ProxyProtocolClientConnectionFactory.V2.Tag.Protocol.UNSPEC : ProxyProtocolClientConnectionFactory.V2.Tag.Protocol.STREAM;
            }

            int familyAndProtocol = family.ordinal() << 4 | protocol.ordinal();
            buffer.put((byte)familyAndProtocol);
            int length = 0;
            switch (family.ordinal()) {
               case 0:
                  break;
               case 1:
                  length = 12;
                  break;
               case 2:
                  length = 36;
                  break;
               case 3:
                  length = 216;
                  break;
               default:
                  throw new IllegalStateException();
            }

            length += vectorsLength;
            buffer.putShort((short)length);
            String dstAddr = this.tag.getDestinationAddress();
            SocketAddress remote = endPoint.getRemoteSocketAddress();
            InetSocketAddress inetRemote = remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
            InetAddress remoteAddress = inetRemote == null ? null : inetRemote.getAddress();
            if (dstAddr == null && remoteAddress != null) {
               dstAddr = remoteAddress.getHostAddress();
            }

            int dstPort = this.tag.getDestinationPort();
            if (dstPort <= 0 && inetRemote != null) {
               dstPort = inetRemote.getPort();
            }

            switch (family.ordinal()) {
               case 0:
                  break;
               case 1:
               case 2:
                  buffer.put(InetAddress.getByName(srcAddr).getAddress());
                  buffer.put(InetAddress.getByName(dstAddr).getAddress());
                  buffer.putShort((short)srcPort);
                  buffer.putShort((short)dstPort);
                  break;
               case 3:
                  int position = buffer.position();
                  if (srcAddr != null) {
                     buffer.put(srcAddr.getBytes(StandardCharsets.US_ASCII));
                  }

                  position += 108;
                  buffer.position(position);
                  if (dstAddr != null) {
                     buffer.put(dstAddr.getBytes(StandardCharsets.US_ASCII));
                  }

                  position += 108;
                  buffer.position(position);
                  break;
               default:
                  throw new IllegalStateException();
            }

            if (tlvs != null) {
               for(V2.Tag.TLV tlv : tlvs) {
                  buffer.put((byte)tlv.getType());
                  byte[] data = tlv.getValue();
                  buffer.putShort((short)data.length);
                  buffer.put(data);
               }
            }

            buffer.flip();
            endPoint.write(callback, buffer);
         } catch (Throwable x) {
            callback.failed(x);
         }

      }
   }
}
