package org.apache.spark.sql.hive.execution;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.ql.exec.TaskRunner;
import org.apache.spark.SparkException.;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.ExternalCatalogWithListener;
import org.apache.spark.sql.hive.HiveExternalCatalog;
import org.apache.spark.sql.hive.client.package;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dc\u0001B\u000b\u0017\u0001\rB\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u0005\tk\u0001\u0011)\u0019!C\u0001m!Aq\b\u0001B\u0001B\u0003%q\u0007\u0003\u0005A\u0001\t\u0005\t\u0015!\u0003B\u0011\u00159\u0005\u0001\"\u0001I\u0011\u001dq\u0005\u00011A\u0005\n=Cqa\u0015\u0001A\u0002\u0013%A\u000b\u0003\u0004[\u0001\u0001\u0006K\u0001\u0015\u0005\t7\u0002A)\u0019!C\u00019\"AQ\f\u0001EC\u0002\u0013%a\fC\u0003j\u0001\u0011%!\u000eC\u0003\\\u0001\u0011%A\u000eC\u0003|\u0001\u0011%A\u0010\u0003\u0005\u0002\u000e\u0001!\t\u0001GA\b\u0011\u001d\t9\u0002\u0001C\u0005\u00033Aq!!\r\u0001\t\u0013\t\u0019\u0004C\u0004\u00026\u0001!\t!a\u000e\t\u000f\u0005e\u0002\u0001\"\u0001\u00028!9\u00111\b\u0001\u0005\u0002\u0005u\u0002bBA\"\u0001\u0011\u0005\u0013Q\t\u0002\r\u0011&4X\rV3naB\u000bG\u000f\u001b\u0006\u0003/a\t\u0011\"\u001a=fGV$\u0018n\u001c8\u000b\u0005eQ\u0012\u0001\u00025jm\u0016T!a\u0007\u000f\u0002\u0007M\fHN\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u00192\u0001\u0001\u0013+!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u00111FL\u0007\u0002Y)\u0011Q\u0006H\u0001\tS:$XM\u001d8bY&\u0011q\u0006\f\u0002\b\u0019><w-\u001b8h\u0003\u001d\u0019Xm]:j_:\u0004\"AM\u001a\u000e\u0003iI!\u0001\u000e\u000e\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0002\u0015!\fGm\\8q\u0007>tg-F\u00018!\tAT(D\u0001:\u0015\tQ4(\u0001\u0003d_:4'B\u0001\u001f\u001f\u0003\u0019A\u0017\rZ8pa&\u0011a(\u000f\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\u0017!\fGm\\8q\u0007>tg\rI\u0001\u0005a\u0006$\b\u000e\u0005\u0002C\u000b6\t1I\u0003\u0002Ew\u0005\u0011am]\u0005\u0003\r\u000e\u0013A\u0001U1uQ\u00061A(\u001b8jiz\"B!S&M\u001bB\u0011!\nA\u0007\u0002-!)\u0001'\u0002a\u0001c!)Q'\u0002a\u0001o!)\u0001)\u0002a\u0001\u0003\u0006)2\u000f^1hS:<G)\u001b:G_J\u001c%/Z1uS:<W#\u0001)\u0011\u0007\u0015\n\u0016)\u0003\u0002SM\t1q\n\u001d;j_:\f\u0011d\u001d;bO&tw\rR5s\r>\u00148I]3bi&twm\u0018\u0013fcR\u0011Q\u000b\u0017\t\u0003KYK!a\u0016\u0014\u0003\tUs\u0017\u000e\u001e\u0005\b3\u001e\t\t\u00111\u0001Q\u0003\rAH%M\u0001\u0017gR\fw-\u001b8h\t&\u0014hi\u001c:De\u0016\fG/\u001b8hA\u0005\u0001R\r\u001f;fe:\fG\u000eV3naB\u000bG\u000f[\u000b\u0002\u0003\u0006\tB-\u0019;f)&lWMR8s[\u0006$H/\u001a:\u0016\u0003}\u0003\"\u0001Y4\u000e\u0003\u0005T!AY2\u0002\r\u0019|'/\\1u\u0015\t!W-\u0001\u0003uS6,'\"\u00014\u0002\t)\fg/Y\u0005\u0003Q\u0006\u0014\u0011\u0003R1uKRKW.\u001a$pe6\fG\u000f^3s\u0003I9W\r^#yi\u0016\u0014h.\u00197U[B\u0004\u0016\r\u001e5\u0015\u0005\u0005[\u0007\"\u0002!\f\u0001\u0004\tEcA!n]\")\u0001\t\u0004a\u0001\u0003\")q\u000e\u0004a\u0001a\u0006Q1\u000f^1hS:<G)\u001b:\u0011\u0005EDhB\u0001:w!\t\u0019h%D\u0001u\u0015\t)(%\u0001\u0004=e>|GOP\u0005\u0003o\u001a\na\u0001\u0015:fI\u00164\u0017BA={\u0005\u0019\u0019FO]5oO*\u0011qOJ\u0001\u0016O\u0016$X\t\u001f;fe:\fGnU2sCR\u001c\u0007\u000eR5s)\u0011\tU0a\u0003\t\u000byl\u0001\u0019A@\u0002\r\u0015DH/\u0016*J!\u0011\t\t!a\u0002\u000e\u0005\u0005\r!bAA\u0003K\u0006\u0019a.\u001a;\n\t\u0005%\u00111\u0001\u0002\u0004+JK\u0005\"B8\u000e\u0001\u0004\u0001\u0018!D4fiN#\u0018mZ5oO\u0012K'\u000fF\u0003B\u0003#\t)\u0002\u0003\u0004\u0002\u00149\u0001\r!Q\u0001\nS:\u0004X\u000f\u001e)bi\"DQa\u001c\bA\u0002A\f\u0001\"[:Tk\n$\u0015N\u001d\u000b\t\u00037\t\t#!\n\u0002*A\u0019Q%!\b\n\u0007\u0005}aEA\u0004C_>dW-\u00198\t\r\u0005\rr\u00021\u0001B\u0003\t\u0001\u0018\u0007\u0003\u0004\u0002(=\u0001\r!Q\u0001\u0003aJBa\u0001R\bA\u0002\u0005-\u0002c\u0001\"\u0002.%\u0019\u0011qF\"\u0003\u0015\u0019KG.Z*zgR,W.A\u0006fq\u0016\u001cW\u000f^5p]&#W#\u00019\u0002\u001b\u0011,G.\u001a;f)6\u0004\b+\u0019;i)\u0005)\u0016!D2sK\u0006$X\rV7q!\u0006$\b.A\u000beK2,G/Z%g\u001d>$8\u000b^1hS:<G)\u001b:\u0015\u000bU\u000by$!\u0011\t\u000b\u0001\u001b\u0002\u0019A!\t\r\u0011\u001b\u0002\u0019AA\u0016\u0003!!xn\u0015;sS:<G#\u00019"
)
public class HiveTempPath implements Logging {
   private Path externalTempPath;
   private DateTimeFormatter dateTimeFormatter;
   private final SparkSession session;
   private final Configuration hadoopConf;
   private final Path path;
   private Option stagingDirForCreating;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

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

   public Configuration hadoopConf() {
      return this.hadoopConf;
   }

   private Option stagingDirForCreating() {
      return this.stagingDirForCreating;
   }

   private void stagingDirForCreating_$eq(final Option x$1) {
      this.stagingDirForCreating = x$1;
   }

   private Path externalTempPath$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.externalTempPath = this.getExternalTmpPath(this.path);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.externalTempPath;
   }

   public Path externalTempPath() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.externalTempPath$lzycompute() : this.externalTempPath;
   }

   private DateTimeFormatter dateTimeFormatter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss_SSS", Locale.US).withZone(ZoneId.systemDefault());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.dateTimeFormatter;
   }

   private DateTimeFormatter dateTimeFormatter() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.dateTimeFormatter$lzycompute() : this.dateTimeFormatter;
   }

   private Path getExternalTmpPath(final Path path) {
      ExternalCatalogWithListener externalCatalog = this.session.sharedState().externalCatalog();
      package.HiveVersion hiveVersion = ((HiveExternalCatalog)externalCatalog.unwrapped()).client().version();
      String stagingDir = this.hadoopConf().get("hive.exec.stagingdir", ".hive-staging");
      if (package.hive$.MODULE$.allSupportedHiveVersions().contains(hiveVersion)) {
         return this.externalTempPath(path, stagingDir);
      } else {
         throw .MODULE$.internalError("Unsupported hive version: " + hiveVersion.fullVersion());
      }
   }

   private Path externalTempPath(final Path path, final String stagingDir) {
      label14: {
         URI extURI = path.toUri();
         String var10000 = extURI.getScheme();
         String var4 = "viewfs";
         if (var10000 == null) {
            if (var4 == null) {
               break label14;
            }
         } else if (var10000.equals(var4)) {
            break label14;
         }

         Path qualifiedStagingDir = this.getExternalScratchDir(extURI, stagingDir);
         this.stagingDirForCreating_$eq(new Some(qualifiedStagingDir));
         return new Path(qualifiedStagingDir, "-ext-10000");
      }

      Path qualifiedStagingDir = this.getStagingDir(path, stagingDir);
      this.stagingDirForCreating_$eq(new Some(qualifiedStagingDir));
      return new Path(qualifiedStagingDir, "-ext-10000");
   }

   private Path getExternalScratchDir(final URI extURI, final String stagingDir) {
      return this.getStagingDir(new Path(extURI.getScheme(), extURI.getAuthority(), extURI.getPath()), stagingDir);
   }

   public Path getStagingDir(final Path inputPath, final String stagingDir) {
      String inputPathName = inputPath.toString();
      FileSystem fs = inputPath.getFileSystem(this.hadoopConf());
      ObjectRef stagingPathName = ObjectRef.create(inputPathName.indexOf(stagingDir) == -1 ? (new Path(inputPathName, stagingDir)).toString() : inputPathName.substring(0, inputPathName.indexOf(stagingDir) + stagingDir.length()));
      if (this.isSubDir(new Path((String)stagingPathName.elem), inputPath, fs) && !scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString((String)stagingPathName.elem), inputPathName)), "/").startsWith(".")) {
         this.logDebug((Function0)(() -> "The staging dir '" + (String)stagingPathName.elem + "' should be a child directory starts with '.' to avoid being deleted if we set hive.exec.stagingdir under the table directory."));
         stagingPathName.elem = (new Path(inputPathName, ".hive-staging")).toString();
      }

      String var10003 = (String)stagingPathName.elem;
      Path dir = fs.makeQualified(new Path(var10003 + "_" + this.executionId() + "-" + TaskRunner.getTaskRunnerID()));
      this.logDebug((Function0)(() -> "Created staging dir = " + dir + " for path = " + inputPath));
      return dir;
   }

   private boolean isSubDir(final Path p1, final Path p2, final FileSystem fs) {
      Path var10000 = fs.makeQualified(p1);
      String path1 = var10000.toString() + "/";
      var10000 = fs.makeQualified(p2);
      String path2 = var10000.toString() + "/";
      return path1.startsWith(path2);
   }

   private String executionId() {
      Random rand = new Random();
      String var10000 = this.dateTimeFormatter().format((new Date()).toInstant());
      return "hive_" + var10000 + "_" + Math.abs(rand.nextLong());
   }

   public void deleteTmpPath() {
      try {
         this.stagingDirForCreating().foreach((stagingDirx) -> {
            FileSystem fs = stagingDirx.getFileSystem(this.hadoopConf());
            return fs.delete(stagingDirx, true) ? BoxesRunTime.boxToBoolean(fs.cancelDeleteOnExit(stagingDirx)) : BoxedUnit.UNIT;
         });
      } catch (Throwable var6) {
         if (var6 == null || !scala.util.control.NonFatal..MODULE$.apply(var6)) {
            throw var6;
         }

         String stagingDir = this.hadoopConf().get("hive.exec.stagingdir", ".hive-staging");
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to delete staging directory: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, stagingDir)})))), var6);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public void createTmpPath() {
      try {
         this.stagingDirForCreating().foreach((stagingDir) -> BoxesRunTime.boxToBoolean($anonfun$createTmpPath$1(this, stagingDir)));
      } catch (IOException var2) {
         throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.cannotCreateStagingDirError("'" + this.stagingDirForCreating().toString() + "': " + var2.getMessage(), var2);
      }
   }

   public void deleteIfNotStagingDir(final Path path, final FileSystem fs) {
      label14: {
         Option var10000 = scala.Option..MODULE$.apply(path);
         Option var3 = this.stagingDirForCreating();
         if (var10000 == null) {
            if (var3 != null) {
               break label14;
            }
         } else if (!var10000.equals(var3)) {
            break label14;
         }

         return;
      }

      fs.delete(path, true);
   }

   public String toString() {
      return "HiveTempPath(" + this.path + ")";
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createTmpPath$1(final HiveTempPath $this, final Path stagingDir) {
      FileSystem fs = stagingDir.getFileSystem($this.hadoopConf());
      if (!FileUtils.mkdir(fs, stagingDir, true, $this.hadoopConf())) {
         throw .MODULE$.internalError("Cannot create staging directory  '" + stagingDir.toString() + "'");
      } else {
         return fs.deleteOnExit(stagingDir);
      }
   }

   public HiveTempPath(final SparkSession session, final Configuration hadoopConf, final Path path) {
      this.session = session;
      this.hadoopConf = hadoopConf;
      this.path = path;
      Logging.$init$(this);
      this.stagingDirForCreating = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
