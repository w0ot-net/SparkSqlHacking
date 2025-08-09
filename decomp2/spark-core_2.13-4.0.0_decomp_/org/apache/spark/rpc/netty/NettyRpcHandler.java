package org.apache.spark.rpc.netty;

import java.lang.invoke.SerializedLambda;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.server.StreamManager;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcAddress$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!\u0002\b\u0010\u0001=I\u0002\u0002\u0003\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u00119\u0002!\u0011!Q\u0001\n=B\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\u0006m\u0001!\ta\u000e\u0005\by\u0001\u0011\r\u0011\"\u0003>\u0011\u0019a\u0005\u0001)A\u0005}!)Q\n\u0001C!\u001d\")Q\n\u0001C!S\")A\u000e\u0001C\u0005[\")1\u000f\u0001C!i\")Q\u000f\u0001C!m\"9\u0011Q\u0002\u0001\u0005B\u0005=\u0001bBA\n\u0001\u0011\u0005\u0013Q\u0003\u0002\u0010\u001d\u0016$H/\u001f*qG\"\u000bg\u000e\u001a7fe*\u0011\u0001#E\u0001\u0006]\u0016$H/\u001f\u0006\u0003%M\t1A\u001d9d\u0015\t!R#A\u0003ta\u0006\u00148N\u0003\u0002\u0017/\u00051\u0011\r]1dQ\u0016T\u0011\u0001G\u0001\u0004_J<7c\u0001\u0001\u001bEA\u00111\u0004I\u0007\u00029)\u0011QDH\u0001\u0007g\u0016\u0014h/\u001a:\u000b\u0005}\u0019\u0012a\u00028fi^|'o[\u0005\u0003Cq\u0011!B\u00159d\u0011\u0006tG\r\\3s!\t\u0019c%D\u0001%\u0015\t)3#\u0001\u0005j]R,'O\\1m\u0013\t9CEA\u0004M_\u001e<\u0017N\\4\u0002\u0015\u0011L7\u000f]1uG\",'o\u0001\u0001\u0011\u0005-bS\"A\b\n\u00055z!A\u0003#jgB\fGo\u00195fe\u0006Aa.\u001a;us\u0016sg\u000f\u0005\u0002,a%\u0011\u0011g\u0004\u0002\f\u001d\u0016$H/\u001f*qG\u0016sg/A\u0007tiJ,\u0017-\\'b]\u0006<WM\u001d\t\u00037QJ!!\u000e\u000f\u0003\u001bM#(/Z1n\u001b\u0006t\u0017mZ3s\u0003\u0019a\u0014N\\5u}Q!\u0001(\u000f\u001e<!\tY\u0003\u0001C\u0003)\t\u0001\u0007!\u0006C\u0003/\t\u0001\u0007q\u0006C\u00033\t\u0001\u00071'A\bsK6|G/Z!eIJ,7o]3t+\u0005q\u0004\u0003B G\u0011\"k\u0011\u0001\u0011\u0006\u0003\u0003\n\u000b!bY8oGV\u0014(/\u001a8u\u0015\t\u0019E)\u0001\u0003vi&d'\"A#\u0002\t)\fg/Y\u0005\u0003\u000f\u0002\u0013\u0011cQ8oGV\u0014(/\u001a8u\u0011\u0006\u001c\b.T1q!\tI%*D\u0001\u0012\u0013\tY\u0015C\u0001\u0006Sa\u000e\fE\r\u001a:fgN\f\u0001C]3n_R,\u0017\t\u001a3sKN\u001cXm\u001d\u0011\u0002\u000fI,7-Z5wKR!q*\u0016/e!\t\u00016+D\u0001R\u0015\u0005\u0011\u0016!B:dC2\f\u0017B\u0001+R\u0005\u0011)f.\u001b;\t\u000bY;\u0001\u0019A,\u0002\r\rd\u0017.\u001a8u!\tA&,D\u0001Z\u0015\t1f$\u0003\u0002\\3\nyAK]1ogB|'\u000f^\"mS\u0016tG\u000fC\u0003^\u000f\u0001\u0007a,A\u0004nKN\u001c\u0018mZ3\u0011\u0005}\u0013W\"\u00011\u000b\u0005\u0005$\u0015a\u00018j_&\u00111\r\u0019\u0002\u000b\u0005f$XMQ;gM\u0016\u0014\b\"B3\b\u0001\u00041\u0017\u0001C2bY2\u0014\u0017mY6\u0011\u0005a;\u0017B\u00015Z\u0005M\u0011\u0006o\u0019*fgB|gn]3DC2d'-Y2l)\ry%n\u001b\u0005\u0006-\"\u0001\ra\u0016\u0005\u0006;\"\u0001\rAX\u0001\u0010S:$XM\u001d8bYJ+7-Z5wKR\u0019a.\u001d:\u0011\u0005-z\u0017B\u00019\u0010\u00059\u0011V-];fgRlUm]:bO\u0016DQAV\u0005A\u0002]CQ!X\u0005A\u0002y\u000b\u0001cZ3u'R\u0014X-Y7NC:\fw-\u001a:\u0015\u0003M\nq\"\u001a=dKB$\u0018n\u001c8DCV<\u0007\u000e\u001e\u000b\u0005\u001f^\fY\u0001C\u0003y\u0017\u0001\u0007\u00110A\u0003dCV\u001cX\rE\u0002{\u0003\u000bq1a_A\u0001\u001d\tax0D\u0001~\u0015\tq\u0018&\u0001\u0004=e>|GOP\u0005\u0002%&\u0019\u00111A)\u0002\u000fA\f7m[1hK&!\u0011qAA\u0005\u0005%!\u0006N]8xC\ndWMC\u0002\u0002\u0004ECQAV\u0006A\u0002]\u000bQb\u00195b]:,G.Q2uSZ,GcA(\u0002\u0012!)a\u000b\u0004a\u0001/\u0006y1\r[1o]\u0016d\u0017J\\1di&4X\rF\u0002P\u0003/AQAV\u0007A\u0002]\u0003"
)
public class NettyRpcHandler extends RpcHandler implements Logging {
   private final Dispatcher dispatcher;
   private final NettyRpcEnv nettyEnv;
   private final StreamManager streamManager;
   private final ConcurrentHashMap remoteAddresses;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private ConcurrentHashMap remoteAddresses() {
      return this.remoteAddresses;
   }

   public void receive(final TransportClient client, final ByteBuffer message, final RpcResponseCallback callback) {
      RequestMessage messageToDispatch = this.internalReceive(client, message);
      this.dispatcher.postRemoteMessage(messageToDispatch, callback);
   }

   public void receive(final TransportClient client, final ByteBuffer message) {
      RequestMessage messageToDispatch = this.internalReceive(client, message);
      this.dispatcher.postOneWayMessage(messageToDispatch);
   }

   private RequestMessage internalReceive(final TransportClient client, final ByteBuffer message) {
      InetSocketAddress addr = (InetSocketAddress)client.getChannel().remoteAddress();
      .MODULE$.assert(addr != null);
      RpcAddress clientAddr = RpcAddress$.MODULE$.apply(addr.getHostString(), addr.getPort());
      RequestMessage requestMessage = RequestMessage$.MODULE$.apply(this.nettyEnv, client, message);
      if (requestMessage.senderAddress() == null) {
         return new RequestMessage(clientAddr, requestMessage.receiver(), requestMessage.content());
      } else {
         RpcAddress remoteEnvAddress = requestMessage.senderAddress();
         if (this.remoteAddresses().putIfAbsent(clientAddr, remoteEnvAddress) == null) {
            this.dispatcher.postToAll(new RemoteProcessConnected(remoteEnvAddress));
         }

         return requestMessage;
      }
   }

   public StreamManager getStreamManager() {
      return this.streamManager;
   }

   public void exceptionCaught(final Throwable cause, final TransportClient client) {
      InetSocketAddress addr = (InetSocketAddress)client.getChannel().remoteAddress();
      if (addr != null) {
         RpcAddress clientAddr = RpcAddress$.MODULE$.apply(addr.getHostString(), addr.getPort());
         this.dispatcher.postToAll(new RemoteProcessConnectionError(cause, clientAddr));
         RpcAddress remoteEnvAddress = (RpcAddress)this.remoteAddresses().get(clientAddr);
         if (remoteEnvAddress != null) {
            this.dispatcher.postToAll(new RemoteProcessConnectionError(cause, remoteEnvAddress));
         }
      } else {
         this.logError((Function0)(() -> "Exception before connecting to the client"), cause);
      }
   }

   public void channelActive(final TransportClient client) {
      InetSocketAddress addr = (InetSocketAddress)client.getChannel().remoteAddress();
      .MODULE$.assert(addr != null);
      RpcAddress clientAddr = RpcAddress$.MODULE$.apply(addr.getHostString(), addr.getPort());
      this.dispatcher.postToAll(new RemoteProcessConnected(clientAddr));
   }

   public void channelInactive(final TransportClient client) {
      InetSocketAddress addr = (InetSocketAddress)client.getChannel().remoteAddress();
      if (addr != null) {
         RpcAddress clientAddr = RpcAddress$.MODULE$.apply(addr.getHostString(), addr.getPort());
         this.nettyEnv.removeOutbox(clientAddr);
         this.dispatcher.postToAll(new RemoteProcessDisconnected(clientAddr));
         RpcAddress remoteEnvAddress = (RpcAddress)this.remoteAddresses().remove(clientAddr);
         if (remoteEnvAddress != null) {
            this.dispatcher.postToAll(new RemoteProcessDisconnected(remoteEnvAddress));
         }
      }
   }

   public NettyRpcHandler(final Dispatcher dispatcher, final NettyRpcEnv nettyEnv, final StreamManager streamManager) {
      this.dispatcher = dispatcher;
      this.nettyEnv = nettyEnv;
      this.streamManager = streamManager;
      Logging.$init$(this);
      this.remoteAddresses = new ConcurrentHashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
