package org.apache.spark.util.logging;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.client.HdfsDataOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-h!B\u001b7\u0001i\u0002\u0005\u0002C'\u0001\u0005\u0003\u0005\u000b\u0011B(\t\u000bM\u0003A\u0011\u0001+\t\u000fa\u0003!\u0019!C\u00053\"1Q\f\u0001Q\u0001\niCqA\u0018\u0001C\u0002\u0013%\u0011\f\u0003\u0004`\u0001\u0001\u0006IA\u0017\u0005\bA\u0002\u0011\r\u0011\"\u0003b\u0011\u0019Q\u0007\u0001)A\u0005E\"91\u000e\u0001b\u0001\n\u0013a\u0007BB<\u0001A\u0003%Q\u000eC\u0004y\u0001\t\u0007I\u0011B=\t\u000f\u0005%\u0001\u0001)A\u0005u\"I\u00111\u0002\u0001A\u0002\u0013%\u0011Q\u0002\u0005\n\u0003s\u0003\u0001\u0019!C\u0005\u0003wC\u0001\"a0\u0001A\u0003&\u0011q\u0002\u0005\b\u0003\u0003\u0004A\u0011BAY\u0011\u001d\t\u0019\r\u0001C\u0001\u0003\u000bDq!!3\u0001\t\u0003\t\tLB\u0004\u0002\u001a\u0001\u0001!(a\u0007\t\u0013\u0005%2C!A!\u0002\u0013Q\bBCA\u0016'\t\u0005\t\u0015!\u0003\u0002.!11k\u0005C\u0001\u0003oA\u0011\"!\u0010\u0014\u0001\u0004%I!a\u0010\t\u0013\u0005\u001d3\u00031A\u0005\n\u0005%\u0003\u0002CA+'\u0001\u0006K!!\u0011\t\u0013\u0005]3\u00031A\u0005\n\u0005e\u0003\"CA4'\u0001\u0007I\u0011BA5\u0011!\tig\u0005Q!\n\u0005m\u0003\"CA8'\u0001\u0007I\u0011BA9\u0011%\tYh\u0005a\u0001\n\u0013\ti\b\u0003\u0005\u0002\u0002N\u0001\u000b\u0015BA:\u0011%\t\u0019i\u0005b\u0001\n\u0013\t)\t\u0003\u0005\u0002\u0014N\u0001\u000b\u0011BAD\u0011-\t)j\u0005a\u0001\u0002\u0004%I!a&\t\u0017\u0005\u001d6\u00031AA\u0002\u0013%\u0011\u0011\u0016\u0005\f\u0003[\u001b\u0002\u0019!A!B\u0013\tI\nC\u0004\u00020N!I!!-\t\u000f\u0005M6\u0003\"\u0001\u00022\"9\u0011QW\n\u0005\n\u0005E\u0006bBA\\'\u0011\u0005\u0011\u0011W\u0004\t\u0003\u00174\u0004\u0012\u0001\u001e\u0002N\u001a9QG\u000eE\u0001u\u0005=\u0007BB*+\t\u0003\t\t\u000e\u0003\u0005\u0002T*\u0012\r\u0011\"\u0001b\u0011\u001d\t)N\u000bQ\u0001\n\tD\u0001\"a6+\u0005\u0004%\t!\u0019\u0005\b\u00033T\u0003\u0015!\u0003c\u0011!\tYN\u000bb\u0001\n\u0003\t\u0007bBAoU\u0001\u0006IA\u0019\u0005\t\u0003?T#\u0019!C\u0001C\"9\u0011\u0011\u001d\u0016!\u0002\u0013\u0011\u0007bBArU\u0011\u0005\u0011Q\u001d\u0002\r\tJLg/\u001a:M_\u001e<WM\u001d\u0006\u0003oa\nq\u0001\\8hO&twM\u0003\u0002:u\u0005!Q\u000f^5m\u0015\tYD(A\u0003ta\u0006\u00148N\u0003\u0002>}\u00051\u0011\r]1dQ\u0016T\u0011aP\u0001\u0004_J<7c\u0001\u0001B\u000fB\u0011!)R\u0007\u0002\u0007*\tA)A\u0003tG\u0006d\u0017-\u0003\u0002G\u0007\n1\u0011I\\=SK\u001a\u0004\"\u0001S&\u000e\u0003%S!A\u0013\u001e\u0002\u0011%tG/\u001a:oC2L!\u0001T%\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g\u0007\u0001\u0001\"\u0001U)\u000e\u0003iJ!A\u0015\u001e\u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u0002\u001fj]&$h\b\u0006\u0002V/B\u0011a\u000bA\u0007\u0002m!)QJ\u0001a\u0001\u001f\u0006\tR\u000b\u0015'P\u0003\u0012{6\tS+O\u0017~\u001b\u0016JW#\u0016\u0003i\u0003\"AQ.\n\u0005q\u001b%aA%oi\u0006\u0011R\u000b\u0015'P\u0003\u0012{6\tS+O\u0017~\u001b\u0016JW#!\u0003])\u0006\u000bT(B\t~Ke\nV#S-\u0006cu,\u0013(`'\u0016\u001b5+\u0001\rV!2{\u0015\tR0J\u001dR+%KV!M?&sulU#D'\u0002\na\u0002R#G\u0003VcEk\u0018'B3>+F+F\u0001c!\t\u0019\u0007.D\u0001e\u0015\t)g-\u0001\u0003mC:<'\"A4\u0002\t)\fg/Y\u0005\u0003S\u0012\u0014aa\u0015;sS:<\u0017a\u0004#F\r\u0006+F\nV0M\u0003f{U\u000b\u0016\u0011\u0002)1{ui\u0018$J\u0019\u0016{\u0006+\u0012*N\u0013N\u001b\u0016j\u0014(T+\u0005i\u0007C\u00018v\u001b\u0005y'B\u00019r\u0003)\u0001XM]7jgNLwN\u001c\u0006\u0003eN\f!AZ:\u000b\u0005Qd\u0014A\u00025bI>|\u0007/\u0003\u0002w_\naai\u001d)fe6L7o]5p]\u0006)BjT$`\r&cUi\u0018)F%6K5kU%P\u001dN\u0003\u0013\u0001\u00047pG\u0006dGj\\4GS2,W#\u0001>\u0011\u0007m\f)AD\u0002}\u0003\u0003\u0001\"!`\"\u000e\u0003yT!a (\u0002\rq\u0012xn\u001c;?\u0013\r\t\u0019aQ\u0001\u0007!J,G-\u001a4\n\u0007%\f9AC\u0002\u0002\u0004\r\u000bQ\u0002\\8dC2dun\u001a$jY\u0016\u0004\u0013AB<sSR,'/\u0006\u0002\u0002\u0010A)!)!\u0005\u0002\u0016%\u0019\u00111C\"\u0003\r=\u0003H/[8o!\r\t9bE\u0007\u0002\u0001\tqAIZ:Bgft7m\u0016:ji\u0016\u00148CB\n\u0002\u001e\u0005\rr\tE\u0002d\u0003?I1!!\te\u0005\u0019y%M[3diB\u00191-!\n\n\u0007\u0005\u001dBM\u0001\u0005Sk:t\u0017M\u00197f\u0003\u0015\t\u0007\u000f]%e\u0003)A\u0017\rZ8pa\u000e{gN\u001a\t\u0005\u0003_\t\u0019$\u0004\u0002\u00022)\u0011Qj]\u0005\u0005\u0003k\t\tDA\u0007D_:4\u0017nZ;sCRLwN\u001c\u000b\u0007\u0003+\tI$a\u000f\t\r\u0005%b\u00031\u0001{\u0011\u001d\tYC\u0006a\u0001\u0003[\tAb\u001d;sK\u0006l7\t\\8tK\u0012,\"!!\u0011\u0011\u0007\t\u000b\u0019%C\u0002\u0002F\r\u0013qAQ8pY\u0016\fg.\u0001\ttiJ,\u0017-\\\"m_N,Gm\u0018\u0013fcR!\u00111JA)!\r\u0011\u0015QJ\u0005\u0004\u0003\u001f\u001a%\u0001B+oSRD\u0011\"a\u0015\u0019\u0003\u0003\u0005\r!!\u0011\u0002\u0007a$\u0013'A\u0007tiJ,\u0017-\\\"m_N,G\rI\u0001\tS:\u001cFO]3b[V\u0011\u00111\f\t\u0005\u0003;\n\u0019'\u0004\u0002\u0002`)\u0019\u0011\u0011\r4\u0002\u0005%|\u0017\u0002BA3\u0003?\u00121\"\u00138qkR\u001cFO]3b[\u0006a\u0011N\\*ue\u0016\fWn\u0018\u0013fcR!\u00111JA6\u0011%\t\u0019fGA\u0001\u0002\u0004\tY&A\u0005j]N#(/Z1nA\u0005aq.\u001e;qkR\u001cFO]3b[V\u0011\u00111\u000f\t\u0005\u0003k\n9(D\u0001r\u0013\r\tI(\u001d\u0002\u0013\rN#\u0015\r^1PkR\u0004X\u000f^*ue\u0016\fW.\u0001\tpkR\u0004X\u000f^*ue\u0016\fWn\u0018\u0013fcR!\u00111JA@\u0011%\t\u0019FHA\u0001\u0002\u0004\t\u0019(A\u0007pkR\u0004X\u000f^*ue\u0016\fW\u000eI\u0001\ni6\u0004()\u001e4gKJ,\"!a\"\u0011\u000b\t\u000bI)!$\n\u0007\u0005-5IA\u0003BeJ\f\u0017\u0010E\u0002C\u0003\u001fK1!!%D\u0005\u0011\u0011\u0015\u0010^3\u0002\u0015Ql\u0007OQ;gM\u0016\u0014\b%\u0001\u0006uQJ,\u0017\r\u001a9p_2,\"!!'\u0011\t\u0005m\u00151U\u0007\u0003\u0003;SA!a(\u0002\"\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005e2\u0017\u0002BAS\u0003;\u0013\u0001dU2iK\u0012,H.\u001a3Fq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u00039!\bN]3bIB|w\u000e\\0%KF$B!a\u0013\u0002,\"I\u00111K\u0012\u0002\u0002\u0003\u0007\u0011\u0011T\u0001\fi\"\u0014X-\u00193q_>d\u0007%\u0001\u0003j]&$HCAA&\u0003\r\u0011XO\\\u0001\u0006G2|7/Z\u0001\fG2|7/Z,sSR,'/\u0001\u0006xe&$XM]0%KF$B!a\u0013\u0002>\"I\u00111\u000b\b\u0002\u0002\u0003\u0007\u0011qB\u0001\boJLG/\u001a:!\u00039\tG\r\u001a'pO\u0006\u0003\b/\u001a8eKJ\f\u0011b\u001d;beR\u001c\u0016P\\2\u0015\t\u0005-\u0013q\u0019\u0005\b\u0003W\t\u0002\u0019AA\u0017\u0003\u0011\u0019Ho\u001c9\u0002\u0019\u0011\u0013\u0018N^3s\u0019><w-\u001a:\u0011\u0005YS3c\u0001\u0016B\u000fR\u0011\u0011QZ\u0001\u000f\tJKe+\u0012*`\u0019>;u\fR%S\u0003=!%+\u0013,F%~cujR0E\u0013J\u0003\u0013a\u0004#S\u0013Z+%k\u0018'P\u000f~3\u0015\nT#\u0002!\u0011\u0013\u0016JV#S?2{ui\u0018$J\u0019\u0016\u0003\u0013A\u0006#S\u0013Z+%k\u0018'P\u000f~3\u0015\nT#`'V3e)\u0013-\u0002/\u0011\u0013\u0016JV#S?2{ui\u0018$J\u0019\u0016{6+\u0016$G\u0013b\u0003\u0013!D!Q!\u0016sE)\u0012*`\u001d\u0006kU)\u0001\bB!B+e\nR#S?:\u000bU*\u0012\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005\u001d\u0018\u0011\u001e\t\u0005\u0005\u0006EQ\u000bC\u0003Ni\u0001\u0007q\n"
)
public class DriverLogger implements Logging {
   public final SparkConf org$apache$spark$util$logging$DriverLogger$$conf;
   private final int org$apache$spark$util$logging$DriverLogger$$UPLOAD_CHUNK_SIZE;
   private final int org$apache$spark$util$logging$DriverLogger$$UPLOAD_INTERVAL_IN_SECS;
   private final String DEFAULT_LAYOUT;
   private final FsPermission org$apache$spark$util$logging$DriverLogger$$LOG_FILE_PERMISSIONS;
   private final String org$apache$spark$util$logging$DriverLogger$$localLogFile;
   private Option writer;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option apply(final SparkConf conf) {
      return DriverLogger$.MODULE$.apply(conf);
   }

   public static String APPENDER_NAME() {
      return DriverLogger$.MODULE$.APPENDER_NAME();
   }

   public static String DRIVER_LOG_FILE_SUFFIX() {
      return DriverLogger$.MODULE$.DRIVER_LOG_FILE_SUFFIX();
   }

   public static String DRIVER_LOG_FILE() {
      return DriverLogger$.MODULE$.DRIVER_LOG_FILE();
   }

   public static String DRIVER_LOG_DIR() {
      return DriverLogger$.MODULE$.DRIVER_LOG_DIR();
   }

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

   public int org$apache$spark$util$logging$DriverLogger$$UPLOAD_CHUNK_SIZE() {
      return this.org$apache$spark$util$logging$DriverLogger$$UPLOAD_CHUNK_SIZE;
   }

   public int org$apache$spark$util$logging$DriverLogger$$UPLOAD_INTERVAL_IN_SECS() {
      return this.org$apache$spark$util$logging$DriverLogger$$UPLOAD_INTERVAL_IN_SECS;
   }

   private String DEFAULT_LAYOUT() {
      return this.DEFAULT_LAYOUT;
   }

   public FsPermission org$apache$spark$util$logging$DriverLogger$$LOG_FILE_PERMISSIONS() {
      return this.org$apache$spark$util$logging$DriverLogger$$LOG_FILE_PERMISSIONS;
   }

   public String org$apache$spark$util$logging$DriverLogger$$localLogFile() {
      return this.org$apache$spark$util$logging$DriverLogger$$localLogFile;
   }

   private Option writer() {
      return this.writer;
   }

   private void writer_$eq(final Option x$1) {
      this.writer = x$1;
   }

   private void addLogAppender() {
      org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger)LogManager.getRootLogger();
      PatternLayout layout = this.org$apache$spark$util$logging$DriverLogger$$conf.contains((ConfigEntry)package$.MODULE$.DRIVER_LOG_LAYOUT()) ? PatternLayout.newBuilder().withPattern((String)((Option)this.org$apache$spark$util$logging$DriverLogger$$conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_LAYOUT())).get()).build() : PatternLayout.newBuilder().withPattern(this.DEFAULT_LAYOUT()).build();
      Configuration config = logger.getContext().getConfiguration();
      org.apache.logging.log4j.core.appender.FileAppender fa = this.log4jFileAppender$1(config, layout);
      logger.addAppender(fa);
      fa.start();
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added a local log appender at: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, this.org$apache$spark$util$logging$DriverLogger$$localLogFile())})))));
   }

   public void startSync(final org.apache.hadoop.conf.Configuration hadoopConf) {
      try {
         String appId = Utils$.MODULE$.sanitizeDirName(this.org$apache$spark$util$logging$DriverLogger$$conf.getAppId());
         this.writer_$eq(new Some(new DfsAsyncWriter(appId, hadoopConf)));
      } catch (Exception var4) {
         this.logError((Function0)(() -> "Could not persist driver logs to dfs"), var4);
      }

   }

   public void stop() {
      try {
         org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger)LogManager.getRootLogger();
         Appender fa = (Appender)logger.getAppenders().get(DriverLogger$.MODULE$.APPENDER_NAME());
         logger.removeAppender(fa);
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> fa.stop());
         this.writer().foreach((x$2) -> {
            $anonfun$stop$2(x$2);
            return BoxedUnit.UNIT;
         });
      } catch (Exception var7) {
         this.logError((Function0)(() -> "Error in persisting driver logs"), var7);
      } finally {
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> JavaUtils.deleteRecursively(FileUtils.getFile(new String[]{this.org$apache$spark$util$logging$DriverLogger$$localLogFile()}).getParentFile()));
      }

   }

   private final org.apache.logging.log4j.core.appender.FileAppender log4jFileAppender$1(final Configuration config$1, final PatternLayout layout$1) {
      org.apache.logging.log4j.core.appender.FileAppender.Builder builder = org.apache.logging.log4j.core.appender.FileAppender.newBuilder();
      builder.withAppend(false);
      builder.setBufferedIo(false);
      builder.setConfiguration(config$1);
      builder.withFileName(this.org$apache$spark$util$logging$DriverLogger$$localLogFile());
      builder.setIgnoreExceptions(false);
      builder.setLayout(layout$1);
      builder.setName(DriverLogger$.MODULE$.APPENDER_NAME());
      return builder.build();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$2(final DfsAsyncWriter x$2) {
      x$2.closeWriter();
   }

   public DriverLogger(final SparkConf conf) {
      this.org$apache$spark$util$logging$DriverLogger$$conf = conf;
      Logging.$init$(this);
      this.org$apache$spark$util$logging$DriverLogger$$UPLOAD_CHUNK_SIZE = 1048576;
      this.org$apache$spark$util$logging$DriverLogger$$UPLOAD_INTERVAL_IN_SECS = 5;
      this.DEFAULT_LAYOUT = "%d{yy/MM/dd HH:mm:ss.SSS} %t %p %c{1}: %m%n%ex";
      this.org$apache$spark$util$logging$DriverLogger$$LOG_FILE_PERMISSIONS = new FsPermission((short)Integer.parseInt("770", 8));
      this.org$apache$spark$util$logging$DriverLogger$$localLogFile = (String)((Option)conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_LOCAL_DIR())).map((x$1) -> FileUtils.getFile(new String[]{x$1, DriverLogger$.MODULE$.DRIVER_LOG_FILE()}).getAbsolutePath()).getOrElse(() -> FileUtils.getFile(new String[]{Utils$.MODULE$.getLocalDir(this.org$apache$spark$util$logging$DriverLogger$$conf), DriverLogger$.MODULE$.DRIVER_LOG_DIR(), DriverLogger$.MODULE$.DRIVER_LOG_FILE()}).getAbsolutePath());
      this.writer = scala.None..MODULE$;
      this.addLogAppender();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class DfsAsyncWriter implements Runnable, Logging {
      private final String appId;
      private final org.apache.hadoop.conf.Configuration hadoopConf;
      private boolean streamClosed;
      private InputStream inStream;
      private FSDataOutputStream outputStream;
      private final byte[] tmpBuffer;
      private ScheduledExecutorService threadpool;
      private transient Logger org$apache$spark$internal$Logging$$log_;
      // $FF: synthetic field
      public final DriverLogger $outer;

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

      private boolean streamClosed() {
         return this.streamClosed;
      }

      private void streamClosed_$eq(final boolean x$1) {
         this.streamClosed = x$1;
      }

      private InputStream inStream() {
         return this.inStream;
      }

      private void inStream_$eq(final InputStream x$1) {
         this.inStream = x$1;
      }

      private FSDataOutputStream outputStream() {
         return this.outputStream;
      }

      private void outputStream_$eq(final FSDataOutputStream x$1) {
         this.outputStream = x$1;
      }

      private byte[] tmpBuffer() {
         return this.tmpBuffer;
      }

      private ScheduledExecutorService threadpool() {
         return this.threadpool;
      }

      private void threadpool_$eq(final ScheduledExecutorService x$1) {
         this.threadpool = x$1;
      }

      private void init() {
         String rootDir = (String)((Option)this.org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer().org$apache$spark$util$logging$DriverLogger$$conf.get((ConfigEntry)package$.MODULE$.DRIVER_LOG_DFS_DIR())).get();
         FileSystem fileSystem = (new Path(rootDir)).getFileSystem(this.hadoopConf);
         if (!fileSystem.exists(new Path(rootDir))) {
            throw new RuntimeException(rootDir + " does not exist. Please create this dir in order to persist driver logs");
         } else {
            String var10004 = this.appId;
            Path dfsLogFile = fileSystem.makeQualified(new Path(rootDir, var10004 + DriverLogger$.MODULE$.DRIVER_LOG_FILE_SUFFIX()));

            try {
               this.inStream_$eq(new BufferedInputStream(new FileInputStream(this.org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer().org$apache$spark$util$logging$DriverLogger$$localLogFile())));
               this.outputStream_$eq(SparkHadoopUtil$.MODULE$.createFile(fileSystem, dfsLogFile, BoxesRunTime.unboxToBoolean(this.org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer().org$apache$spark$util$logging$DriverLogger$$conf.get(package$.MODULE$.DRIVER_LOG_ALLOW_EC()))));
               fileSystem.setPermission(dfsLogFile, this.org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer().org$apache$spark$util$logging$DriverLogger$$LOG_FILE_PERMISSIONS());
            } catch (Exception var5) {
               JavaUtils.closeQuietly(this.inStream());
               JavaUtils.closeQuietly(this.outputStream());
               throw var5;
            }

            this.threadpool_$eq(ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("dfsSyncThread"));
            this.threadpool().scheduleWithFixedDelay(this, (long)this.org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer().org$apache$spark$util$logging$DriverLogger$$UPLOAD_INTERVAL_IN_SECS(), (long)this.org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer().org$apache$spark$util$logging$DriverLogger$$UPLOAD_INTERVAL_IN_SECS(), TimeUnit.SECONDS);
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Started driver log file sync to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dfsLogFile)})))));
         }
      }

      public void run() {
         if (!this.streamClosed()) {
            try {
               int remaining = this.inStream().available();

               boolean hadData;
               int read;
               for(hadData = remaining > 0; remaining > 0; remaining -= read) {
                  read = this.inStream().read(this.tmpBuffer(), 0, scala.math.package..MODULE$.min(remaining, this.org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer().org$apache$spark$util$logging$DriverLogger$$UPLOAD_CHUNK_SIZE()));
                  this.outputStream().write(this.tmpBuffer(), 0, read);
               }

               if (hadData) {
                  FSDataOutputStream var5 = this.outputStream();
                  if (var5 instanceof HdfsDataOutputStream) {
                     HdfsDataOutputStream var6 = (HdfsDataOutputStream)var5;
                     var6.hsync(EnumSet.allOf(HdfsDataOutputStream.SyncFlag.class));
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     var5.hflush();
                     BoxedUnit var9 = BoxedUnit.UNIT;
                  }
               }
            } catch (Exception var8) {
               this.logError((Function0)(() -> "Failed writing driver logs to dfs"), var8);
            }

         }
      }

      private void close() {
         if (!this.streamClosed()) {
            try {
               this.run();
            } finally {
               try {
                  this.streamClosed_$eq(true);
                  this.inStream().close();
                  this.outputStream().close();
               } catch (Exception var6) {
                  this.logError((Function0)(() -> "Error in closing driver log input/output stream"), var6);
               }

            }

         }
      }

      public void closeWriter() {
         try {
            this.threadpool().execute(() -> this.close());
            this.threadpool().shutdown();
            this.threadpool().awaitTermination(1L, TimeUnit.MINUTES);
         } catch (Exception var2) {
            this.logError((Function0)(() -> "Error in shutting down threadpool"), var2);
         }

      }

      // $FF: synthetic method
      public DriverLogger org$apache$spark$util$logging$DriverLogger$DfsAsyncWriter$$$outer() {
         return this.$outer;
      }

      public DfsAsyncWriter(final String appId, final org.apache.hadoop.conf.Configuration hadoopConf) {
         this.appId = appId;
         this.hadoopConf = hadoopConf;
         if (DriverLogger.this == null) {
            throw null;
         } else {
            this.$outer = DriverLogger.this;
            super();
            Logging.$init$(this);
            this.streamClosed = false;
            this.inStream = null;
            this.outputStream = null;
            this.tmpBuffer = new byte[DriverLogger.this.org$apache$spark$util$logging$DriverLogger$$UPLOAD_CHUNK_SIZE()];
            this.init();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
