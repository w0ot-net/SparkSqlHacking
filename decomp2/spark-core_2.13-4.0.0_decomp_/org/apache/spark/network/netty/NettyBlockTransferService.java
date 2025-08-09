package org.apache.spark.network.netty;

import com.codahale.metrics.MetricSet;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.apache.spark.ExecutorDeadException;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.BlockDataManager;
import org.apache.spark.network.BlockTransferService;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportClientFactory;
import org.apache.spark.network.crypto.AuthClientBootstrap;
import org.apache.spark.network.crypto.AuthServerBootstrap;
import org.apache.spark.network.server.TransportServer;
import org.apache.spark.network.shuffle.BlockFetchingListener;
import org.apache.spark.network.shuffle.BlockTransferListener;
import org.apache.spark.network.shuffle.DownloadFileManager;
import org.apache.spark.network.shuffle.OneForOneBlockFetcher;
import org.apache.spark.network.shuffle.RetryingBlockTransferor;
import org.apache.spark.network.shuffle.protocol.UploadBlock;
import org.apache.spark.network.shuffle.protocol.UploadBlockStream;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.TransportConf;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManagerMessages;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rd!\u0002\u0011\"\u0001\u0015Z\u0003\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\t\u0011Y\u0002!\u0011!Q\u0001\n]B\u0001B\u000f\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005\u0005\"Aq\n\u0001BC\u0002\u0013\u0005\u0003\u000b\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003C\u0011!\u0011\u0006A!A!\u0002\u0013\u0019\u0006\u0002C,\u0001\u0005\u0003\u0005\u000b\u0011B*\t\u0011a\u0003!\u0011!Q\u0001\neCQa\u0018\u0001\u0005\u0002\u0001DqA\u0010\u0001C\u0002\u0013%1\u000e\u0003\u0004p\u0001\u0001\u0006I\u0001\u001c\u0005\ba\u0002\u0011\r\u0011\"\u0003r\u0011\u0019)\b\u0001)A\u0005e\"Ia\u000f\u0001a\u0001\u0002\u0003\u0006Ka\u001e\u0005\nu\u0002\u0001\r\u0011!Q!\nmDq!!\u0001\u0001\t\u0003\n\u0019\u0001C\u0004\u0002\u0016\u0001!I!a\u0006\t\u000f\u0005U\u0002\u0001\"\u0011\u00028!9\u0011Q\n\u0001\u0005B\u0005=\u0003bBA,\u0001\u0011\u0005\u0013\u0011\u0011\u0005\b\u0003\u0007\u0003A\u0011IAC\u0011\u001d\ti\u000f\u0001C!\u0003_DA\"!=\u0001!\u0003\u0005\t\u0011!C\u0001\u0003gDAB!\u0002\u0001!\u0003\u0005\t\u0011!C\u0001\u0005\u000fAAB!\u0007\u0001!\u0003\u0005\t\u0011!C\u0001\u00057AABa\u000b\u0001!\u0003\u0005\t\u0011!C\u0001\u0005[9!B!\u0010\"\u0003\u0003E\t!\nB \r%\u0001\u0013%!A\t\u0002\u0015\u0012\t\u0005\u0003\u0004`;\u0011\u0005!\u0011\n\u0005\n\u0005\u0017j\u0012\u0013!C\u0001\u0005\u001b\u0012\u0011DT3uif\u0014En\\2l)J\fgn\u001d4feN+'O^5dK*\u0011!eI\u0001\u0006]\u0016$H/\u001f\u0006\u0003I\u0015\nqA\\3uo>\u00148N\u0003\u0002'O\u0005)1\u000f]1sW*\u0011\u0001&K\u0001\u0007CB\f7\r[3\u000b\u0003)\n1a\u001c:h'\t\u0001A\u0006\u0005\u0002.]5\t1%\u0003\u00020G\t!\"\t\\8dWR\u0013\u0018M\\:gKJ\u001cVM\u001d<jG\u0016\fAaY8oM\u000e\u0001\u0001CA\u001a5\u001b\u0005)\u0013BA\u001b&\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\btK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s!\t\u0019\u0004(\u0003\u0002:K\ty1+Z2ve&$\u00180T1oC\u001e,'/A\ttKJL\u0017\r\\5{KJl\u0015M\\1hKJ\u0004\"\u0001P \u000e\u0003uR!AP\u0013\u0002\u0015M,'/[1mSj,'/\u0003\u0002A{\t\t2+\u001a:jC2L'0\u001a:NC:\fw-\u001a:\u0002\u0017\tLg\u000eZ!eIJ,7o\u001d\t\u0003\u00072s!\u0001\u0012&\u0011\u0005\u0015CU\"\u0001$\u000b\u0005\u001d\u000b\u0014A\u0002\u001fs_>$hHC\u0001J\u0003\u0015\u00198-\u00197b\u0013\tY\u0005*\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001b:\u0013aa\u0015;sS:<'BA&I\u0003!Awn\u001d;OC6,W#\u0001\"\u0002\u0013!|7\u000f\u001e(b[\u0016\u0004\u0013!B0q_J$\bC\u0001+V\u001b\u0005A\u0015B\u0001,I\u0005\rIe\u000e^\u0001\t]Vl7i\u001c:fg\u0006\tBM]5wKJ,e\u000e\u001a)pS:$(+\u001a4\u0011\u0005ikV\"A.\u000b\u0005q+\u0013a\u0001:qG&\u0011al\u0017\u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u0003\u0019a\u0014N\\5u}QI\u0011m\u00193fM\u001eD\u0017N\u001b\t\u0003E\u0002i\u0011!\t\u0005\u0006a)\u0001\rA\r\u0005\u0006m)\u0001\ra\u000e\u0005\u0006u)\u0001\ra\u000f\u0005\u0006\u0003*\u0001\rA\u0011\u0005\u0006\u001f*\u0001\rA\u0011\u0005\u0006%*\u0001\ra\u0015\u0005\u0006/*\u0001\ra\u0015\u0005\b1*\u0001\n\u00111\u0001Z+\u0005a\u0007C\u0001\u001fn\u0013\tqWH\u0001\u0006TKJL\u0017\r\\5{KJ\f1b]3sS\u0006d\u0017N_3sA\u0005Y\u0011-\u001e;i\u000b:\f'\r\\3e+\u0005\u0011\bC\u0001+t\u0013\t!\bJA\u0004C_>dW-\u00198\u0002\u0019\u0005,H\u000f[#oC\ndW\r\u001a\u0011\u0002!Q\u0014\u0018M\\:q_J$8i\u001c8uKb$\bCA\u0017y\u0013\tI8E\u0001\tUe\u0006t7\u000f]8si\u000e{g\u000e^3yi\u000611/\u001a:wKJ\u0004\"\u0001 @\u000e\u0003uT!A_\u0012\n\u0005}l(a\u0004+sC:\u001c\bo\u001c:u'\u0016\u0014h/\u001a:\u0002\t%t\u0017\u000e\u001e\u000b\u0005\u0003\u000b\tY\u0001E\u0002U\u0003\u000fI1!!\u0003I\u0005\u0011)f.\u001b;\t\u000f\u00055\u0011\u00031\u0001\u0002\u0010\u0005\u0001\"\r\\8dW\u0012\u000bG/Y'b]\u0006<WM\u001d\t\u0004[\u0005E\u0011bAA\nG\t\u0001\"\t\\8dW\u0012\u000bG/Y'b]\u0006<WM]\u0001\rGJ,\u0017\r^3TKJ4XM\u001d\u000b\u0004w\u0006e\u0001bBA\u000e%\u0001\u0007\u0011QD\u0001\u000bE>|Go\u001d;sCB\u001c\bCBA\u0010\u0003S\tyC\u0004\u0003\u0002\"\u0005\u0015bbA#\u0002$%\t\u0011*C\u0002\u0002(!\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002,\u00055\"\u0001\u0002'jgRT1!a\nI!\ra\u0018\u0011G\u0005\u0004\u0003gi(\u0001\u0007+sC:\u001c\bo\u001c:u'\u0016\u0014h/\u001a:C_>$8\u000f\u001e:ba\u0006q1\u000f[;gM2,W*\u001a;sS\u000e\u001cHCAA\u001d!\u0011\tY$!\u0013\u000e\u0005\u0005u\"\u0002BA \u0003\u0003\nq!\\3ue&\u001c7O\u0003\u0003\u0002D\u0005\u0015\u0013\u0001C2pI\u0006D\u0017\r\\3\u000b\u0005\u0005\u001d\u0013aA2p[&!\u00111JA\u001f\u0005%iU\r\u001e:jGN+G/A\u0006gKR\u001c\u0007N\u00117pG.\u001cHCDA\u0003\u0003#\n)&!\u0017\u0002^\u0005\u001d\u0014q\u000f\u0005\u0007\u0003'\"\u0002\u0019\u0001\"\u0002\t!|7\u000f\u001e\u0005\u0007\u0003/\"\u0002\u0019A*\u0002\tA|'\u000f\u001e\u0005\u0007\u00037\"\u0002\u0019\u0001\"\u0002\r\u0015DXmY%e\u0011\u001d\ty\u0006\u0006a\u0001\u0003C\n\u0001B\u00197pG.LEm\u001d\t\u0005)\u0006\r$)C\u0002\u0002f!\u0013Q!\u0011:sCfDq!!\u001b\u0015\u0001\u0004\tY'\u0001\u0005mSN$XM\\3s!\u0011\ti'a\u001d\u000e\u0005\u0005=$bAA9G\u000591\u000f[;gM2,\u0017\u0002BA;\u0003_\u0012QC\u00117pG.4U\r^2iS:<G*[:uK:,'\u000fC\u0004\u0002zQ\u0001\r!a\u001f\u0002\u001fQ,W\u000e\u001d$jY\u0016l\u0015M\\1hKJ\u0004B!!\u001c\u0002~%!\u0011qPA8\u0005M!un\u001e8m_\u0006$g)\u001b7f\u001b\u0006t\u0017mZ3s+\u0005\u0019\u0016aC;qY>\fGM\u00117pG.$\u0002#a\"\u0002\u0014\u0006]\u0015\u0011TAN\u0003W\u000bY,!2\u0011\r\u0005%\u0015qRA\u0003\u001b\t\tYIC\u0002\u0002\u000e\"\u000b!bY8oGV\u0014(/\u001a8u\u0013\u0011\t\t*a#\u0003\r\u0019+H/\u001e:f\u0011\u0019\t)J\u0006a\u0001\u0005\u0006A\u0001n\\:u]\u0006lW\r\u0003\u0004\u0002XY\u0001\ra\u0015\u0005\u0007\u000372\u0002\u0019\u0001\"\t\u000f\u0005ue\u00031\u0001\u0002 \u00069!\r\\8dW&#\u0007\u0003BAQ\u0003Ok!!a)\u000b\u0007\u0005\u0015V%A\u0004ti>\u0014\u0018mZ3\n\t\u0005%\u00161\u0015\u0002\b\u00052|7m[%e\u0011\u001d\tiK\u0006a\u0001\u0003_\u000b\u0011B\u00197pG.$\u0015\r^1\u0011\t\u0005E\u0016qW\u0007\u0003\u0003gS1!!.$\u0003\u0019\u0011WO\u001a4fe&!\u0011\u0011XAZ\u00055i\u0015M\\1hK\u0012\u0014UO\u001a4fe\"9\u0011Q\u0018\fA\u0002\u0005}\u0016!\u00027fm\u0016d\u0007\u0003BAQ\u0003\u0003LA!a1\u0002$\na1\u000b^8sC\u001e,G*\u001a<fY\"9\u0011q\u0019\fA\u0002\u0005%\u0017\u0001C2mCN\u001cH+Y41\t\u0005-\u00171\u001c\t\u0007\u0003\u001b\f\u0019.a6\u000e\u0005\u0005='bAAi\u0011\u00069!/\u001a4mK\u000e$\u0018\u0002BAk\u0003\u001f\u0014\u0001b\u00117bgN$\u0016m\u001a\t\u0005\u00033\fY\u000e\u0004\u0001\u0005\u0019\u0005u\u0017QYA\u0001\u0002\u0003\u0015\t!a8\u0003\u0007}#\u0013'\u0005\u0003\u0002b\u0006\u001d\bc\u0001+\u0002d&\u0019\u0011Q\u001d%\u0003\u000f9{G\u000f[5oOB\u0019A+!;\n\u0007\u0005-\bJA\u0002B]f\fQa\u00197pg\u0016$\"!!\u0002\u0002/A\u0014x\u000e^3di\u0016$Ge\u00197jK:$h)Y2u_JLH\u0003BA{\u0005\u0003\u0001B!a>\u0002~6\u0011\u0011\u0011 \u0006\u0004\u0003w\u001c\u0013AB2mS\u0016tG/\u0003\u0003\u0002\u0000\u0006e(A\u0006+sC:\u001c\bo\u001c:u\u00072LWM\u001c;GC\u000e$xN]=\t\u0011\t\r\u0001$!AA\u0002\u0005\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c;fGR,G\rJ1qa&#G\u0003\u0002B\u0005\u0005/\u0001BAa\u0003\u0003\u00165\u0011!Q\u0002\u0006\u0005\u0005\u001f\u0011\t\"\u0001\u0003mC:<'B\u0001B\n\u0003\u0011Q\u0017M^1\n\u00075\u0013i\u0001\u0003\u0005\u0003\u0004e\t\t\u00111\u0001b\u0003]\u0001(o\u001c;fGR,G\r\n;sC:\u001c\bo\u001c:u\u0007>tg\r\u0006\u0003\u0003\u001e\t%\u0002\u0003\u0002B\u0010\u0005Ki!A!\t\u000b\u0007\t\r2%\u0001\u0003vi&d\u0017\u0002\u0002B\u0014\u0005C\u0011Q\u0002\u0016:b]N\u0004xN\u001d;D_:4\u0007\u0002\u0003B\u00025\u0005\u0005\t\u0019A1\u0002!A\u0014x\u000e^3di\u0016$G\u0005\\8hO\u0016\u0014H\u0003\u0002B\u0018\u0005w\u0001BA!\r\u000385\u0011!1\u0007\u0006\u0004\u0005k)\u0013\u0001C5oi\u0016\u0014h.\u00197\n\t\te\"1\u0007\u0002\f'B\f'o\u001b'pO\u001e,'\u000f\u0003\u0005\u0003\u0004m\t\t\u00111\u0001b\u0003eqU\r\u001e;z\u00052|7m\u001b+sC:\u001ch-\u001a:TKJ4\u0018nY3\u0011\u0005\tl2cA\u000f\u0003DA\u0019AK!\u0012\n\u0007\t\u001d\u0003J\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0005\u007f\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012BTC\u0001B(U\rI&\u0011K\u0016\u0003\u0005'\u0002BA!\u0016\u0003`5\u0011!q\u000b\u0006\u0005\u00053\u0012Y&A\u0005v]\u000eDWmY6fI*\u0019!Q\f%\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003b\t]#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002"
)
public class NettyBlockTransferService extends BlockTransferService {
   private final SparkConf conf;
   private final SecurityManager securityManager;
   private final String bindAddress;
   private final String hostName;
   private final int _port;
   private final int numCores;
   public final RpcEndpointRef org$apache$spark$network$netty$NettyBlockTransferService$$driverEndPointRef;
   private final Serializer serializer;
   private final boolean authEnabled;
   private TransportContext transportContext;
   public TransportServer org$apache$spark$network$netty$NettyBlockTransferService$$server;

   public static RpcEndpointRef $lessinit$greater$default$8() {
      return NettyBlockTransferService$.MODULE$.$lessinit$greater$default$8();
   }

   // $FF: synthetic method
   public TransportClientFactory protected$clientFactory(final NettyBlockTransferService x$1) {
      return x$1.clientFactory;
   }

   // $FF: synthetic method
   public String protected$appId(final NettyBlockTransferService x$1) {
      return x$1.appId;
   }

   // $FF: synthetic method
   public TransportConf protected$transportConf(final NettyBlockTransferService x$1) {
      return x$1.transportConf;
   }

   // $FF: synthetic method
   public SparkLogger protected$logger(final NettyBlockTransferService x$1) {
      return x$1.logger;
   }

   public String hostName() {
      return this.hostName;
   }

   private Serializer serializer() {
      return this.serializer;
   }

   private boolean authEnabled() {
      return this.authEnabled;
   }

   public void init(final BlockDataManager blockDataManager) {
      NettyBlockRpcServer rpcHandler = new NettyBlockRpcServer(this.conf.getAppId(), this.serializer(), blockDataManager);
      Option serverBootstrap = .MODULE$;
      Option clientBootstrap = .MODULE$;
      SparkConf x$1 = this.conf;
      String x$2 = "shuffle";
      int x$3 = this.numCores;
      Some x$4 = new Some(this.securityManager.getRpcSSLOptions());
      Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
      this.transportConf = SparkTransportConf$.MODULE$.fromSparkConf(x$1, "shuffle", x$3, x$5, x$4);
      if (this.authEnabled()) {
         serverBootstrap = new Some(new AuthServerBootstrap(this.transportConf, this.securityManager));
         clientBootstrap = new Some(new AuthClientBootstrap(this.transportConf, this.conf.getAppId(), this.securityManager));
      }

      this.transportContext = new TransportContext(this.transportConf, rpcHandler);
      this.clientFactory = this.transportContext.createClientFactory(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.Option..MODULE$.option2Iterable(clientBootstrap).toSeq()).asJava());
      this.org$apache$spark$network$netty$NettyBlockTransferService$$server = this.createServer(serverBootstrap.toList());
      this.appId = this.conf.getAppId();
      if (this.hostName().equals(this.bindAddress)) {
         this.logger.info("Server created on {}:{}", new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.hostName()), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$network$netty$NettyBlockTransferService$$server.getPort()))});
      } else {
         this.logger.info("Server created on {} {}:{}", new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.hostName()), new MDC(org.apache.spark.internal.LogKeys.BIND_ADDRESS..MODULE$, this.bindAddress), new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$network$netty$NettyBlockTransferService$$server.getPort()))});
      }
   }

   private TransportServer createServer(final List bootstraps) {
      return (TransportServer)Utils$.MODULE$.startServiceOnPort(this._port, (port) -> $anonfun$createServer$1(this, bootstraps, BoxesRunTime.unboxToInt(port)), this.conf, this.getClass().getName())._1();
   }

   public MetricSet shuffleMetrics() {
      scala.Predef..MODULE$.require(this.org$apache$spark$network$netty$NettyBlockTransferService$$server != null && this.clientFactory != null, () -> "NettyBlockTransferServer is not initialized");
      return new MetricSet() {
         private final HashMap allMetrics;
         // $FF: synthetic field
         private final NettyBlockTransferService $outer;

         private HashMap allMetrics() {
            return this.allMetrics;
         }

         public Map getMetrics() {
            this.allMetrics().putAll(this.$outer.protected$clientFactory(this.$outer).getAllMetrics().getMetrics());
            this.allMetrics().putAll(this.$outer.org$apache$spark$network$netty$NettyBlockTransferService$$server.getAllMetrics().getMetrics());
            return this.allMetrics();
         }

         public {
            if (NettyBlockTransferService.this == null) {
               throw null;
            } else {
               this.$outer = NettyBlockTransferService.this;
               this.allMetrics = new HashMap();
            }
         }
      };
   }

   public void fetchBlocks(final String host, final int port, final String execId, final String[] blockIds, final BlockFetchingListener listener, final DownloadFileManager tempFileManager) {
      if (this.logger.isTraceEnabled()) {
         this.logger.trace("Fetch blocks from " + host + ":" + port + " (executor id " + execId + ")");
      }

      try {
         int maxRetries = this.transportConf.maxIORetries();
         RetryingBlockTransferor.BlockTransferStarter blockFetchStarter = new RetryingBlockTransferor.BlockTransferStarter(host, port, maxRetries, execId, tempFileManager) {
            // $FF: synthetic field
            private final NettyBlockTransferService $outer;
            private final String host$1;
            private final int port$1;
            private final int maxRetries$1;
            private final String execId$1;
            private final DownloadFileManager tempFileManager$1;

            public void createAndStart(final String[] blockIds, final BlockTransferListener listener) {
               scala.Predef..MODULE$.assert(listener instanceof BlockFetchingListener, () -> "Expecting a BlockFetchingListener, but got " + listener.getClass());

               try {
                  TransportClient client = this.$outer.protected$clientFactory(this.$outer).createClient(this.host$1, this.port$1, this.maxRetries$1 > 0);
                  (new OneForOneBlockFetcher(client, this.$outer.protected$appId(this.$outer), this.execId$1, blockIds, (BlockFetchingListener)listener, this.$outer.protected$transportConf(this.$outer), this.tempFileManager$1)).start();
               } catch (IOException var9) {
                  Try var6 = scala.util.Try..MODULE$.apply((JFunction0.mcZ.sp)() -> BoxesRunTime.unboxToBoolean(this.$outer.org$apache$spark$network$netty$NettyBlockTransferService$$driverEndPointRef.askSync(new BlockManagerMessages.IsExecutorAlive(this.execId$1), scala.reflect.ClassTag..MODULE$.Boolean())));
                  if (var6 instanceof Success var7) {
                     boolean v = BoxesRunTime.unboxToBoolean(var7.value());
                     if (!v) {
                        throw new ExecutorDeadException("The relative remote executor(Id: " + this.execId$1 + "), which maintains the block data to fetch is dead.");
                     }
                  }

                  throw var9;
               }
            }

            public {
               if (NettyBlockTransferService.this == null) {
                  throw null;
               } else {
                  this.$outer = NettyBlockTransferService.this;
                  this.host$1 = host$1;
                  this.port$1 = port$1;
                  this.maxRetries$1 = maxRetries$1;
                  this.execId$1 = execId$1;
                  this.tempFileManager$1 = tempFileManager$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
         if (maxRetries > 0) {
            (new RetryingBlockTransferor(this.transportConf, blockFetchStarter, blockIds, listener)).start();
         } else {
            blockFetchStarter.createAndStart(blockIds, listener);
         }
      } catch (Exception var10) {
         this.logger.error("Exception while beginning fetchBlocks", var10);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])blockIds), (x$3) -> {
            $anonfun$fetchBlocks$1(listener, var10, x$3);
            return BoxedUnit.UNIT;
         });
      }

   }

   public int port() {
      return this.org$apache$spark$network$netty$NettyBlockTransferService$$server.getPort();
   }

   public Future uploadBlock(final String hostname, final int port, final String execId, final BlockId blockId, final ManagedBuffer blockData, final StorageLevel level, final ClassTag classTag) {
      Promise result = scala.concurrent.Promise..MODULE$.apply();
      TransportClient client = this.clientFactory.createClient(hostname, port);
      byte[] metadata = JavaUtils.bufferToArray(this.serializer().newInstance().serialize(new Tuple2(level, classTag), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
      boolean asStream = blockData.size() > BoxesRunTime.unboxToLong(this.conf.get(package$.MODULE$.MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM())) || blockId.isShuffle();
      RpcResponseCallback callback = new RpcResponseCallback(blockId, asStream, result) {
         // $FF: synthetic field
         private final NettyBlockTransferService $outer;
         private final BlockId blockId$1;
         private final boolean asStream$1;
         private final Promise result$1;

         public void onSuccess(final ByteBuffer response) {
            if (this.$outer.protected$logger(this.$outer).isTraceEnabled()) {
               this.$outer.protected$logger(this.$outer).trace("Successfully uploaded block " + this.blockId$1 + (this.asStream$1 ? " as stream" : ""));
            }

            this.result$1.success(BoxedUnit.UNIT);
         }

         public void onFailure(final Throwable e) {
            if (this.asStream$1) {
               this.$outer.protected$logger(this.$outer).error("Error while uploading {} as stream", e, new MDC[]{org.apache.spark.internal.MDC..MODULE$.of(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, this.blockId$1)});
            } else {
               this.$outer.protected$logger(this.$outer).error("Error while uploading {}", e, new MDC[]{org.apache.spark.internal.MDC..MODULE$.of(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, this.blockId$1)});
            }

            this.result$1.failure(e);
         }

         public {
            if (NettyBlockTransferService.this == null) {
               throw null;
            } else {
               this.$outer = NettyBlockTransferService.this;
               this.blockId$1 = blockId$1;
               this.asStream$1 = asStream$1;
               this.result$1 = result$1;
            }
         }
      };
      if (asStream) {
         ByteBuffer streamHeader = (new UploadBlockStream(blockId.name(), metadata)).toByteBuffer();
         client.uploadStream(new NioManagedBuffer(streamHeader), blockData, callback);
      } else {
         byte[] array = JavaUtils.bufferToArray(blockData.nioByteBuffer());
         client.sendRpc((new UploadBlock(this.appId, execId, blockId.name(), metadata, array)).toByteBuffer(), callback);
      }

      return result.future();
   }

   public void close() {
      if (this.org$apache$spark$network$netty$NettyBlockTransferService$$server != null) {
         this.org$apache$spark$network$netty$NettyBlockTransferService$$server.close();
      }

      if (this.clientFactory != null) {
         this.clientFactory.close();
      }

      if (this.transportContext != null) {
         this.transportContext.close();
      }
   }

   private final Tuple2 startService$1(final int port, final List bootstraps$1) {
      TransportServer server = this.transportContext.createServer(this.bindAddress, port, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(bootstraps$1).asJava());
      return new Tuple2(server, BoxesRunTime.boxToInteger(server.getPort()));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$createServer$1(final NettyBlockTransferService $this, final List bootstraps$1, final int port) {
      return $this.startService$1(port, bootstraps$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchBlocks$1(final BlockFetchingListener listener$2, final Exception e$1, final String x$3) {
      listener$2.onBlockFetchFailure(x$3, e$1);
   }

   public NettyBlockTransferService(final SparkConf conf, final SecurityManager securityManager, final SerializerManager serializerManager, final String bindAddress, final String hostName, final int _port, final int numCores, final RpcEndpointRef driverEndPointRef) {
      this.conf = conf;
      this.securityManager = securityManager;
      this.bindAddress = bindAddress;
      this.hostName = hostName;
      this._port = _port;
      this.numCores = numCores;
      this.org$apache$spark$network$netty$NettyBlockTransferService$$driverEndPointRef = driverEndPointRef;
      this.serializer = serializerManager.getSerializer(scala.reflect.package..MODULE$.classTag(scala.reflect.ClassTag..MODULE$.Any()), false);
      this.authEnabled = securityManager.isAuthenticationEnabled();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
