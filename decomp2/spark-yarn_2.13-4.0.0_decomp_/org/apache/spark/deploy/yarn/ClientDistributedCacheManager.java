package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LocalResourceVisibility;
import org.apache.hadoop.yarn.api.records.URL;
import org.apache.hadoop.yarn.util.Records;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015e!B\u0007\u000f\u0001IA\u0002\"B\u0013\u0001\t\u00039\u0003b\u0002\u0016\u0001\u0005\u0004%Ia\u000b\u0005\u0007o\u0001\u0001\u000b\u0011\u0002\u0017\t\u000ba\u0002A\u0011A\u001d\t\u0013\u0005=\u0001!%A\u0005\u0002\u0005E\u0001bBA\u0014\u0001\u0011\u0005\u0011\u0011\u0006\u0005\t\u0003k\u0001A\u0011\u0001\b\u00028!9\u0011q\t\u0001\u0005\n\u0005%\u0003\u0002CA)\u0001\u0011\u0005a\"a\u0015\t\u000f\u0005]\u0003\u0001\"\u0003\u0002Z!9\u0011\u0011\r\u0001\u0005\n\u0005\r\u0004\u0002CA>\u0001\u0011\u0005a\"! \u0003;\rc\u0017.\u001a8u\t&\u001cHO]5ckR,GmQ1dQ\u0016l\u0015M\\1hKJT!a\u0004\t\u0002\te\f'O\u001c\u0006\u0003#I\ta\u0001Z3qY>L(BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0014\u0007\u0001Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A\rj\u0011!\t\u0006\u0003EI\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003I\u0005\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005A\u0003CA\u0015\u0001\u001b\u0005q\u0011\u0001\u00053jgR\u001c\u0015m\u00195f\u000b:$(/[3t+\u0005a\u0003cA\u00173i5\taF\u0003\u00020a\u00059Q.\u001e;bE2,'BA\u0019\u001c\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003g9\u0012!\u0002T5ti\n+hMZ3s!\tIS'\u0003\u00027\u001d\tQ1)Y2iK\u0016sGO]=\u0002#\u0011L7\u000f^\"bG\",WI\u001c;sS\u0016\u001c\b%A\u0006bI\u0012\u0014Vm]8ve\u000e,GC\u0003\u001e>\r6\u00136\u000e\u001d:\u0002\u0006A\u0011!dO\u0005\u0003ym\u0011A!\u00168ji\")a\b\u0002a\u0001\u007f\u0005\u0011am\u001d\t\u0003\u0001\u0012k\u0011!\u0011\u0006\u0003}\tS!a\u0011\u000b\u0002\r!\fGm\\8q\u0013\t)\u0015I\u0001\u0006GS2,7+_:uK6DQa\u0012\u0003A\u0002!\u000bAaY8oMB\u0011\u0011jS\u0007\u0002\u0015*\u0011qIQ\u0005\u0003\u0019*\u0013QbQ8oM&<WO]1uS>t\u0007\"\u0002(\u0005\u0001\u0004y\u0015\u0001\u00033fgR\u0004\u0016\r\u001e5\u0011\u0005\u0001\u0003\u0016BA)B\u0005\u0011\u0001\u0016\r\u001e5\t\u000bM#\u0001\u0019\u0001+\u0002\u001d1|7-\u00197SKN|WO]2fgB!Q&V,c\u0013\t1fFA\u0004ICNDW*\u00199\u0011\u0005a{fBA-^!\tQ6$D\u0001\\\u0015\taf%\u0001\u0004=e>|GOP\u0005\u0003=n\ta\u0001\u0015:fI\u00164\u0017B\u00011b\u0005\u0019\u0019FO]5oO*\u0011al\u0007\t\u0003G&l\u0011\u0001\u001a\u0006\u0003K\u001a\fqA]3d_J$7O\u0003\u0002hQ\u0006\u0019\u0011\r]5\u000b\u0005=\u0011\u0015B\u00016e\u00055aunY1m%\u0016\u001cx.\u001e:dK\")A\u000e\u0002a\u0001[\u0006a!/Z:pkJ\u001cW\rV=qKB\u00111M\\\u0005\u0003_\u0012\u0014\u0011\u0003T8dC2\u0014Vm]8ve\u000e,G+\u001f9f\u0011\u0015\tH\u00011\u0001X\u0003\u0011a\u0017N\\6\t\u000bM$\u0001\u0019\u0001;\u0002\u0013M$\u0018\r^\"bG\",\u0007\u0003B\u0017vo~L!A\u001e\u0018\u0003\u00075\u000b\u0007\u000f\u0005\u0002y{6\t\u0011P\u0003\u0002{w\u0006\u0019a.\u001a;\u000b\u0003q\fAA[1wC&\u0011a0\u001f\u0002\u0004+JK\u0005c\u0001!\u0002\u0002%\u0019\u00111A!\u0003\u0015\u0019KG.Z*uCR,8\u000fC\u0005\u0002\b\u0011\u0001\n\u00111\u0001\u0002\n\u0005i\u0011\r\u001d9NCN$XM](oYf\u00042AGA\u0006\u0013\r\tia\u0007\u0002\b\u0005>|G.Z1o\u0003U\tG\r\u001a*fg>,(oY3%I\u00164\u0017-\u001e7uIa*\"!a\u0005+\t\u0005%\u0011QC\u0016\u0003\u0003/\u0001B!!\u0007\u0002$5\u0011\u00111\u0004\u0006\u0005\u0003;\ty\"A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011E\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002&\u0005m!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u0019R\u000f\u001d3bi\u0016\u001cuN\u001c4jOV\u0014\u0018\r^5p]R\u0019!(a\u000b\t\r\u001d3\u0001\u0019AA\u0017!\u0011\ty#!\r\u000e\u0003II1!a\r\u0013\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0007hKR4\u0016n]5cS2LG/\u001f\u000b\t\u0003s\ty$!\u0011\u0002FA\u00191-a\u000f\n\u0007\u0005uBMA\fM_\u000e\fGNU3t_V\u00148-\u001a,jg&\u0014\u0017\u000e\\5us\")qi\u0002a\u0001\u0011\"1\u00111I\u0004A\u0002]\f1!\u001e:j\u0011\u0015\u0019x\u00011\u0001u\u0003!I7\u000fU;cY&\u001cG\u0003CA\u0005\u0003\u0017\ni%a\u0014\t\u000b\u001dC\u0001\u0019\u0001%\t\r\u0005\r\u0003\u00021\u0001x\u0011\u0015\u0019\b\u00021\u0001u\u000319W\r\u001e)be\u0016tG/\u0016*J)\r9\u0018Q\u000b\u0005\u0007\u0003\u0007J\u0001\u0019A<\u0002?\u0005t7-Z:u_J\u001c\b*\u0019<f\u000bb,7-\u001e;f!\u0016\u0014X.[:tS>t7\u000f\u0006\u0005\u0002\n\u0005m\u0013QLA0\u0011\u0015q$\u00021\u0001@\u0011\u0019\t\u0019E\u0003a\u0001o\")1O\u0003a\u0001i\u000612\r[3dWB+'/\\5tg&|gn\u00144Pi\",'\u000f\u0006\u0006\u0002\n\u0005\u0015\u0014qMA5\u0003sBQAP\u0006A\u0002}Ba!a\u0011\f\u0001\u00049\bbBA6\u0017\u0001\u0007\u0011QN\u0001\u0007C\u000e$\u0018n\u001c8\u0011\t\u0005=\u0014QO\u0007\u0003\u0003cR1!a\u001dB\u0003)\u0001XM]7jgNLwN\\\u0005\u0005\u0003o\n\tH\u0001\u0005Gg\u0006\u001bG/[8o\u0011\u0015\u00198\u00021\u0001u\u000359W\r\u001e$jY\u0016\u001cF/\u0019;vgR9q0a \u0002\u0002\u0006\r\u0005\"\u0002 \r\u0001\u0004y\u0004BBA\"\u0019\u0001\u0007q\u000fC\u0003t\u0019\u0001\u0007A\u000f"
)
public class ClientDistributedCacheManager implements Logging {
   private final ListBuffer distCacheEntries;
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

   private ListBuffer distCacheEntries() {
      return this.distCacheEntries;
   }

   public void addResource(final FileSystem fs, final Configuration conf, final Path destPath, final HashMap localResources, final LocalResourceType resourceType, final String link, final scala.collection.mutable.Map statCache, final boolean appMasterOnly) {
      FileStatus destStatus = this.getFileStatus(fs, destPath.toUri(), statCache);
      LocalResource amJarRsrc = (LocalResource)Records.newRecord(LocalResource.class);
      amJarRsrc.setType(resourceType);
      LocalResourceVisibility visibility = this.getVisibility(conf, destPath.toUri(), statCache);
      amJarRsrc.setVisibility(visibility);
      amJarRsrc.setResource(URL.fromPath(destPath));
      amJarRsrc.setTimestamp(destStatus.getModificationTime());
      amJarRsrc.setSize(destStatus.getLen());
      .MODULE$.require(link != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(link)), () -> "You must specify a valid link name.");
      localResources.update(link, amJarRsrc);
      if (!appMasterOnly) {
         URI uri = destPath.toUri();
         URI pathURI = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), (String)null, link);
         this.distCacheEntries().$plus$eq(new CacheEntry(pathURI, destStatus.getLen(), destStatus.getModificationTime(), visibility, resourceType));
      }
   }

   public boolean addResource$default$8() {
      return false;
   }

   public void updateConfiguration(final SparkConf conf) {
      conf.set(package$.MODULE$.CACHED_FILES(), ((IterableOnceOps)this.distCacheEntries().map((x$1) -> x$1.uri().toString())).toSeq());
      conf.set(package$.MODULE$.CACHED_FILES_SIZES(), ((IterableOnceOps)this.distCacheEntries().map((x$2) -> BoxesRunTime.boxToLong($anonfun$updateConfiguration$2(x$2)))).toSeq());
      conf.set(package$.MODULE$.CACHED_FILES_TIMESTAMPS(), ((IterableOnceOps)this.distCacheEntries().map((x$3) -> BoxesRunTime.boxToLong($anonfun$updateConfiguration$3(x$3)))).toSeq());
      conf.set(package$.MODULE$.CACHED_FILES_VISIBILITIES(), ((IterableOnceOps)this.distCacheEntries().map((x$4) -> x$4.visibility().name())).toSeq());
      conf.set(package$.MODULE$.CACHED_FILES_TYPES(), ((IterableOnceOps)this.distCacheEntries().map((x$5) -> x$5.resType().name())).toSeq());
   }

   public LocalResourceVisibility getVisibility(final Configuration conf, final URI uri, final scala.collection.mutable.Map statCache) {
      return this.isPublic(conf, uri, statCache) ? LocalResourceVisibility.PUBLIC : LocalResourceVisibility.PRIVATE;
   }

   private boolean isPublic(final Configuration conf, final URI uri, final scala.collection.mutable.Map statCache) {
      FileSystem fs = FileSystem.get(uri, conf);
      return !this.checkPermissionOfOther(fs, uri, FsAction.READ, statCache) ? false : this.ancestorsHaveExecutePermissions(fs, this.getParentURI(uri), statCache);
   }

   public URI getParentURI(final URI uri) {
      Path path = new Path(uri.toString());
      Path parent = path.getParent();
      return parent == null ? null : parent.toUri();
   }

   private boolean ancestorsHaveExecutePermissions(final FileSystem fs, final URI uri, final scala.collection.mutable.Map statCache) {
      for(URI current = uri; current != null; current = this.getParentURI(current)) {
         if (!this.checkPermissionOfOther(fs, current, FsAction.EXECUTE, statCache)) {
            return false;
         }
      }

      return true;
   }

   private boolean checkPermissionOfOther(final FileSystem fs, final URI uri, final FsAction action, final scala.collection.mutable.Map statCache) {
      FileStatus status = this.getFileStatus(fs, uri, statCache);
      FsPermission perms = status.getPermission();
      FsAction otherAction = perms.getOtherAction();
      return otherAction.implies(action);
   }

   public FileStatus getFileStatus(final FileSystem fs, final URI uri, final scala.collection.mutable.Map statCache) {
      Option var6 = statCache.get(uri);
      FileStatus var10000;
      if (var6 instanceof Some var7) {
         FileStatus existstat = (FileStatus)var7.value();
         var10000 = existstat;
      } else {
         if (!scala.None..MODULE$.equals(var6)) {
            throw new MatchError(var6);
         }

         FileStatus newStat = fs.getFileStatus(new Path(uri));
         statCache.put(uri, newStat);
         var10000 = newStat;
      }

      FileStatus stat = var10000;
      return stat;
   }

   // $FF: synthetic method
   public static final long $anonfun$updateConfiguration$2(final CacheEntry x$2) {
      return x$2.size();
   }

   // $FF: synthetic method
   public static final long $anonfun$updateConfiguration$3(final CacheEntry x$3) {
      return x$3.modTime();
   }

   public ClientDistributedCacheManager() {
      Logging.$init$(this);
      this.distCacheEntries = new ListBuffer();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
