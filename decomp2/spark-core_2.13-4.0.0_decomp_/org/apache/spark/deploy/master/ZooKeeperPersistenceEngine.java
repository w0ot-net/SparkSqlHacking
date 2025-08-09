package org.apache.spark.deploy.master;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.DeleteBuilder;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.api.PathAndBytesable;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkCuratorUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Deploy$;
import org.apache.spark.serializer.Serializer;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c!B\b\u0011\u0001AQ\u0002\u0002C\u0013\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u0011-\u0002!Q1A\u0005\u00021B\u0001B\r\u0001\u0003\u0002\u0003\u0006I!\f\u0005\u0006g\u0001!\t\u0001\u000e\u0005\bq\u0001\u0011\r\u0011\"\u0003:\u0011\u0019\u0011\u0005\u0001)A\u0005u!91\t\u0001b\u0001\n\u0013!\u0005BB'\u0001A\u0003%Q\tC\u0003O\u0001\u0011\u0005s\nC\u0003h\u0001\u0011\u0005\u0003\u000eC\u0003k\u0001\u0011\u00053\u000eC\u0004\u0002\u001a\u0001!\t%a\u0007\t\u000f\u0005u\u0001\u0001\"\u0003\u0002 !9\u0011q\u0006\u0001\u0005\n\u0005E\"A\u0007.p_.+W\r]3s!\u0016\u00148/[:uK:\u001cW-\u00128hS:,'BA\t\u0013\u0003\u0019i\u0017m\u001d;fe*\u00111\u0003F\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c2\u0001A\u000e !\taR$D\u0001\u0011\u0013\tq\u0002CA\tQKJ\u001c\u0018n\u001d;f]\u000e,WI\\4j]\u0016\u0004\"\u0001I\u0012\u000e\u0003\u0005R!A\t\u000b\u0002\u0011%tG/\u001a:oC2L!\u0001J\u0011\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g\u0007\u0001\u0001\"\u0001K\u0015\u000e\u0003QI!A\u000b\u000b\u0003\u0013M\u0003\u0018M]6D_:4\u0017AC:fe&\fG.\u001b>feV\tQ\u0006\u0005\u0002/a5\tqF\u0003\u0002,)%\u0011\u0011g\f\u0002\u000b'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018aC:fe&\fG.\u001b>fe\u0002\na\u0001P5oSRtDcA\u001b7oA\u0011A\u0004\u0001\u0005\u0006K\u0011\u0001\ra\n\u0005\u0006W\u0011\u0001\r!L\u0001\u000bo>\u00148.\u001b8h\t&\u0014X#\u0001\u001e\u0011\u0005m\u0002U\"\u0001\u001f\u000b\u0005ur\u0014\u0001\u00027b]\u001eT\u0011aP\u0001\u0005U\u00064\u0018-\u0003\u0002By\t11\u000b\u001e:j]\u001e\f1b^8sW&tw\rR5sA\u0005\u0011!p[\u000b\u0002\u000bB\u0011aiS\u0007\u0002\u000f*\u0011\u0001*S\u0001\nMJ\fW.Z<pe.T!A\u0013\f\u0002\u000f\r,(/\u0019;pe&\u0011Aj\u0012\u0002\u0011\u0007V\u0014\u0018\r^8s\rJ\fW.Z<pe.\f1A_6!\u0003\u001d\u0001XM]:jgR$2\u0001\u0015,c!\t\tF+D\u0001S\u0015\u0005\u0019\u0016!B:dC2\f\u0017BA+S\u0005\u0011)f.\u001b;\t\u000b]K\u0001\u0019\u0001-\u0002\t9\fW.\u001a\t\u00033\u0002t!A\u00170\u0011\u0005m\u0013V\"\u0001/\u000b\u0005u3\u0013A\u0002\u001fs_>$h(\u0003\u0002`%\u00061\u0001K]3eK\u001aL!!Q1\u000b\u0005}\u0013\u0006\"B2\n\u0001\u0004!\u0017aA8cUB\u00111(Z\u0005\u0003Mr\u0012aa\u00142kK\u000e$\u0018!C;oa\u0016\u00148/[:u)\t\u0001\u0016\u000eC\u0003X\u0015\u0001\u0007\u0001,\u0001\u0003sK\u0006$WC\u00017z)\ri\u0017Q\u0003\u000b\u0004]\u0006\u0015\u0001cA8uo:\u0011\u0001O\u001d\b\u00037FL\u0011aU\u0005\u0003gJ\u000bq\u0001]1dW\u0006<W-\u0003\u0002vm\n\u00191+Z9\u000b\u0005M\u0014\u0006C\u0001=z\u0019\u0001!QA_\u0006C\u0002m\u0014\u0011\u0001V\t\u0003y~\u0004\"!U?\n\u0005y\u0014&a\u0002(pi\"Lgn\u001a\t\u0004#\u0006\u0005\u0011bAA\u0002%\n\u0019\u0011I\\=\t\u0013\u0005\u001d1\"!AA\u0004\u0005%\u0011AC3wS\u0012,gnY3%cA)\u00111BA\to6\u0011\u0011Q\u0002\u0006\u0004\u0003\u001f\u0011\u0016a\u0002:fM2,7\r^\u0005\u0005\u0003'\tiA\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u0019\t9b\u0003a\u00011\u00061\u0001O]3gSb\fQa\u00197pg\u0016$\u0012\u0001U\u0001\u0012g\u0016\u0014\u0018.\u00197ju\u0016Le\u000e^8GS2,G#\u0002)\u0002\"\u0005\u0015\u0002BBA\u0012\u001b\u0001\u0007\u0001,\u0001\u0003qCRD\u0007bBA\u0014\u001b\u0001\u0007\u0011\u0011F\u0001\u0006m\u0006dW/\u001a\t\u0004#\u0006-\u0012bAA\u0017%\n1\u0011I\\=SK\u001a\f1\u0003Z3tKJL\u0017\r\\5{K\u001a\u0013x.\u001c$jY\u0016,B!a\r\u0002@Q!\u0011QGA$)\u0011\t9$!\u0011\u0011\u000bE\u000bI$!\u0010\n\u0007\u0005m\"K\u0001\u0004PaRLwN\u001c\t\u0004q\u0006}B!\u0002>\u000f\u0005\u0004Y\bbBA\"\u001d\u0001\u000f\u0011QI\u0001\u0002[B1\u00111BA\t\u0003{Aa!!\u0013\u000f\u0001\u0004A\u0016\u0001\u00034jY\u0016t\u0017-\\3"
)
public class ZooKeeperPersistenceEngine extends PersistenceEngine implements Logging {
   private final Serializer serializer;
   private final String workingDir;
   private final CuratorFramework zk;
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

   public Serializer serializer() {
      return this.serializer;
   }

   private String workingDir() {
      return this.workingDir;
   }

   private CuratorFramework zk() {
      return this.zk;
   }

   public void persist(final String name, final Object obj) {
      this.serializeIntoFile(this.workingDir() + "/" + name, obj);
   }

   public void unpersist(final String name) {
      DeleteBuilder var10000 = this.zk().delete();
      String var10001 = this.workingDir();
      var10000.forPath(var10001 + "/" + name);
   }

   public Seq read(final String prefix, final ClassTag evidence$1) {
      return ((IterableOnceOps)((IterableOps).MODULE$.ListHasAsScala((List)this.zk().getChildren().forPath(this.workingDir())).asScala().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$read$1(prefix, x$1)))).flatMap((filename) -> this.deserializeFromFile(filename, evidence$1))).toSeq();
   }

   public void close() {
      this.zk().close();
   }

   private void serializeIntoFile(final String path, final Object value) {
      ByteBuffer serialized = this.serializer().newInstance().serialize(value, scala.reflect.ClassTag..MODULE$.AnyRef());
      byte[] bytes = new byte[serialized.remaining()];
      serialized.get(bytes);
      ((PathAndBytesable)this.zk().create().withMode(CreateMode.PERSISTENT)).forPath(path, bytes);
   }

   private Option deserializeFromFile(final String filename, final ClassTag m) {
      GetDataBuilder var10000 = this.zk().getData();
      String var10001 = this.workingDir();
      byte[] fileData = (byte[])var10000.forPath(var10001 + "/" + filename);

      try {
         var7 = new Some(this.serializer().newInstance().deserialize(ByteBuffer.wrap(fileData), m));
      } catch (Exception var5) {
         this.logWarning((Function0)(() -> "Exception while reading persisted file, deleting"), var5);
         DeleteBuilder var6 = this.zk().delete();
         var10001 = this.workingDir();
         var6.forPath(var10001 + "/" + filename);
         var7 = scala.None..MODULE$;
      }

      return (Option)var7;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$read$1(final String prefix$1, final String x$1) {
      return x$1.startsWith(prefix$1);
   }

   public ZooKeeperPersistenceEngine(final SparkConf conf, final Serializer serializer) {
      this.serializer = serializer;
      Logging.$init$(this);
      Option var10001 = (Option)conf.get((ConfigEntry)Deploy$.MODULE$.ZOOKEEPER_DIRECTORY());
      this.workingDir = (String)var10001.getOrElse(() -> "/spark") + "/master_status";
      this.zk = SparkCuratorUtil$.MODULE$.newClient(conf, SparkCuratorUtil$.MODULE$.newClient$default$2());
      SparkCuratorUtil$.MODULE$.mkdir(this.zk(), this.workingDir());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
