package org.apache.spark.deploy.history;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\teb!B\u001a5\u0003\u0003y\u0004\u0002\u0003'\u0001\u0005\u0003\u0005\u000b\u0011B'\t\u0011a\u0003!\u0011!Q\u0001\neC\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006I!\u0018\u0005\tK\u0002\u0011\t\u0011)A\u0005M\"A!\u000e\u0001B\u0001B\u0003%1\u000eC\u0003t\u0001\u0011\u0005A\u000fC\u0004}\u0001\t\u0007I\u0011C?\t\u000f\u0005\r\u0001\u0001)A\u0005}\"A\u0011Q\u0001\u0001C\u0002\u0013EQ\u0010C\u0004\u0002\b\u0001\u0001\u000b\u0011\u0002@\t\u0013\u0005%\u0001A1A\u0005\u0012\u0005-\u0001\u0002CA\n\u0001\u0001\u0006I!!\u0004\t\u0013\u0005U\u0001A1A\u0005\u0012\u0005]\u0001\u0002CA\u0013\u0001\u0001\u0006I!!\u0007\t\u0013\u0005\u001d\u0002A1A\u0005\u0012\u0005%\u0002\u0002CA\u001d\u0001\u0001\u0006I!a\u000b\t\u0015\u0005m\u0002A1A\u0005\u0002Q\ni\u0004C\u0004\u0002@\u0001\u0001\u000b\u0011B-\t\u0013\u0005\u0005\u0003\u00011A\u0005\u0012\u0005\r\u0003\"CA'\u0001\u0001\u0007I\u0011CA(\u0011!\tY\u0006\u0001Q!\n\u0005\u0015\u0003\"CA/\u0001\u0001\u0007I\u0011CA0\u0011%\ti\u0007\u0001a\u0001\n#\ty\u0007\u0003\u0005\u0002t\u0001\u0001\u000b\u0015BA1\u0011\u001d\t)\b\u0001C\t\u0003oBq!!\u001f\u0001\t#\tY\bC\u0004\u0002\u001a\u0002!\t\"a'\t\u0013\u0005\u0015\u0006!%A\u0005\u0012\u0005\u001d\u0006bBA_\u0001\u0011E\u0011q\u000f\u0005\b\u0003\u007f\u0003A\u0011CAa\u0011\u001d\ty\r\u0001D\u0001\u0003oBq!!5\u0001\r\u0003\t\u0019\u000eC\u0005\u0002\\\u0002\t\n\u0011\"\u0001\u0002(\"9\u0011Q\u001c\u0001\u0007\u0002\u0005]\u0004bBAp\u0001\u0019\u0005\u0011\u0011]\u0004\b\u0003G$\u0004\u0012AAs\r\u0019\u0019D\u0007#\u0001\u0002h\"11/\nC\u0001\u0003SD\u0011\"a;&\u0005\u0004%\t!!<\t\u0011\u0005eX\u0005)A\u0005\u0003_D\u0011\"a?&\u0005\u0004%\t!!<\t\u0011\u0005uX\u0005)A\u0005\u0003_D\u0011\"a@&\u0005\u0004%\tA!\u0001\t\u0011\t=Q\u0005)A\u0005\u0005\u0007A\u0011B!\u0005&\u0005\u0004%\tA!\u0001\t\u0011\tMQ\u0005)A\u0005\u0005\u0007AqA!\u0006&\t\u0003\u00119\u0002C\u0004\u0003$\u0015\"\tA!\n\t\u000f\t-R\u0005\"\u0001\u0003.!9!1G\u0013\u0005\u0002\tU\"AE#wK:$Hj\\4GS2,wK]5uKJT!!\u000e\u001c\u0002\u000f!L7\u000f^8ss*\u0011q\u0007O\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005eR\u0014!B:qCJ\\'BA\u001e=\u0003\u0019\t\u0007/Y2iK*\tQ(A\u0002pe\u001e\u001c\u0001aE\u0002\u0001\u0001\u001a\u0003\"!\u0011#\u000e\u0003\tS\u0011aQ\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000b\n\u0013a!\u00118z%\u00164\u0007CA$K\u001b\u0005A%BA%9\u0003!Ig\u000e^3s]\u0006d\u0017BA&I\u0005\u001daunZ4j]\u001e\fQ!\u00199q\u0013\u0012\u0004\"AT+\u000f\u0005=\u001b\u0006C\u0001)C\u001b\u0005\t&B\u0001*?\u0003\u0019a$o\\8u}%\u0011AKQ\u0001\u0007!J,G-\u001a4\n\u0005Y;&AB*ue&twM\u0003\u0002U\u0005\u0006a\u0011\r\u001d9BiR,W\u000e\u001d;JIB\u0019\u0011IW'\n\u0005m\u0013%AB(qi&|g.\u0001\u0006m_\u001e\u0014\u0015m]3ESJ\u0004\"AX2\u000e\u0003}S!\u0001Y1\u0002\u00079,GOC\u0001c\u0003\u0011Q\u0017M^1\n\u0005\u0011|&aA+S\u0013\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0003O\"l\u0011\u0001O\u0005\u0003Sb\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u0015!\fGm\\8q\u0007>tg\r\u0005\u0002mc6\tQN\u0003\u0002o_\u0006!1m\u001c8g\u0015\t\u0001((\u0001\u0004iC\u0012|w\u000e]\u0005\u0003e6\u0014QbQ8oM&<WO]1uS>t\u0017A\u0002\u001fj]&$h\b\u0006\u0004vobL(p\u001f\t\u0003m\u0002i\u0011\u0001\u000e\u0005\u0006\u0019\u001a\u0001\r!\u0014\u0005\u00061\u001a\u0001\r!\u0017\u0005\u00069\u001a\u0001\r!\u0018\u0005\u0006K\u001a\u0001\rA\u001a\u0005\u0006U\u001a\u0001\ra[\u0001\u000fg\"|W\u000f\u001c3D_6\u0004(/Z:t+\u0005q\bCA!\u0000\u0013\r\t\tA\u0011\u0002\b\u0005>|G.Z1o\u0003=\u0019\bn\\;mI\u000e{W\u000e\u001d:fgN\u0004\u0013aD:i_VdGm\u0014<fe^\u0014\u0018\u000e^3\u0002!MDw.\u001e7e\u001fZ,'o\u001e:ji\u0016\u0004\u0013\u0001E8viB,HOQ;gM\u0016\u00148+\u001b>f+\t\ti\u0001E\u0002B\u0003\u001fI1!!\u0005C\u0005\rIe\u000e^\u0001\u0012_V$\b/\u001e;Ck\u001a4WM]*ju\u0016\u0004\u0013A\u00034jY\u0016\u001c\u0016p\u001d;f[V\u0011\u0011\u0011\u0004\t\u0005\u00037\t\t#\u0004\u0002\u0002\u001e)\u0019\u0011qD8\u0002\u0005\u0019\u001c\u0018\u0002BA\u0012\u0003;\u0011!BR5mKNK8\u000f^3n\u0003-1\u0017\u000e\\3TsN$X-\u001c\u0011\u0002!\r|W\u000e\u001d:fgNLwN\\\"pI\u0016\u001cWCAA\u0016!\u0011\t%,!\f\u0011\t\u0005=\u0012QG\u0007\u0003\u0003cQ1!a\r9\u0003\tIw.\u0003\u0003\u00028\u0005E\"\u0001E\"p[B\u0014Xm]:j_:\u001cu\u000eZ3d\u0003E\u0019w.\u001c9sKN\u001c\u0018n\u001c8D_\u0012,7\rI\u0001\u0015G>l\u0007O]3tg&|gnQ8eK\u000et\u0015-\\3\u0016\u0003e\u000bQcY8naJ,7o]5p]\u000e{G-Z2OC6,\u0007%\u0001\tiC\u0012|w\u000e\u001d#bi\u0006\u001cFO]3b[V\u0011\u0011Q\t\t\u0005\u0003j\u000b9\u0005\u0005\u0003\u0002\u001c\u0005%\u0013\u0002BA&\u0003;\u0011!CR*ECR\fw*\u001e;qkR\u001cFO]3b[\u0006!\u0002.\u00193p_B$\u0015\r^1TiJ,\u0017-\\0%KF$B!!\u0015\u0002XA\u0019\u0011)a\u0015\n\u0007\u0005U#I\u0001\u0003V]&$\b\"CA-)\u0005\u0005\t\u0019AA#\u0003\rAH%M\u0001\u0012Q\u0006$wn\u001c9ECR\f7\u000b\u001e:fC6\u0004\u0013AB<sSR,'/\u0006\u0002\u0002bA!\u0011IWA2!\u0011\t)'!\u001b\u000e\u0005\u0005\u001d$bAA\u001aC&!\u00111NA4\u0005-\u0001&/\u001b8u/JLG/\u001a:\u0002\u0015]\u0014\u0018\u000e^3s?\u0012*\u0017\u000f\u0006\u0003\u0002R\u0005E\u0004\"CA-/\u0005\u0005\t\u0019AA1\u0003\u001d9(/\u001b;fe\u0002\nAD]3rk&\u0014X\rT8h\u0005\u0006\u001cX\rR5s\u0003N$\u0015N]3di>\u0014\u0018\u0010\u0006\u0002\u0002R\u0005Y\u0011N\\5u\u0019><g)\u001b7f)\u0011\ti(a$\u0015\t\u0005E\u0013q\u0010\u0005\b\u0003\u0003S\u0002\u0019AAB\u000351gnU3ukB<&/\u001b;feB9\u0011)!\"\u0002\n\u0006\r\u0014bAAD\u0005\nIa)\u001e8di&|g.\r\t\u0005\u0003K\nY)\u0003\u0003\u0002\u000e\u0006\u001d$\u0001D(viB,Ho\u0015;sK\u0006l\u0007bBAI5\u0001\u0007\u00111S\u0001\u0005a\u0006$\b\u000e\u0005\u0003\u0002\u001c\u0005U\u0015\u0002BAL\u0003;\u0011A\u0001U1uQ\u0006IqO]5uK2Kg.\u001a\u000b\u0007\u0003#\ni*!)\t\r\u0005}5\u00041\u0001N\u0003\u0011a\u0017N\\3\t\u0011\u0005\r6\u0004%AA\u0002y\f1B\u001a7vg\"dunZ4fe\u0006\u0019rO]5uK2Kg.\u001a\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\u0016\u0016\u0004}\u0006-6FAAW!\u0011\ty+!/\u000e\u0005\u0005E&\u0002BAZ\u0003k\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005]&)\u0001\u0006b]:|G/\u0019;j_:LA!a/\u00022\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0017\rdwn]3Xe&$XM]\u0001\u000be\u0016t\u0017-\\3GS2,G\u0003CA)\u0003\u0007\f9-a3\t\u000f\u0005\u0015g\u00041\u0001\u0002\u0014\u0006\u00191O]2\t\u000f\u0005%g\u00041\u0001\u0002\u0014\u0006!A-Z:u\u0011\u0019\tiM\ba\u0001}\u0006IqN^3soJLG/Z\u0001\u0006gR\f'\u000f^\u0001\u000boJLG/Z#wK:$HCBA)\u0003+\fI\u000e\u0003\u0004\u0002X\u0002\u0002\r!T\u0001\nKZ,g\u000e\u001e&t_:D\u0001\"a)!!\u0003\u0005\rA`\u0001\u0015oJLG/Z#wK:$H\u0005Z3gCVdG\u000f\n\u001a\u0002\tM$x\u000e]\u0001\bY><\u0007+\u0019;i+\u0005i\u0015AE#wK:$Hj\\4GS2,wK]5uKJ\u0004\"A^\u0013\u0014\u0005\u0015\u0002ECAAs\u0003-Iej\u0018)S\u001f\u001e\u0013ViU*\u0016\u0005\u0005=\b\u0003BAy\u0003ol!!a=\u000b\u0007\u0005U\u0018-\u0001\u0003mC:<\u0017b\u0001,\u0002t\u0006a\u0011JT0Q%>;%+R*TA\u0005I1iT'Q\u0003\u000e#V\tR\u0001\u000b\u0007>k\u0005+Q\"U\u000b\u0012\u0003\u0013\u0001\u0006'P\u000f~3\u0015\nT#`!\u0016\u0013V*S*T\u0013>s5+\u0006\u0002\u0003\u0004A!!Q\u0001B\u0006\u001b\t\u00119A\u0003\u0003\u0003\n\u0005u\u0011A\u00039fe6L7o]5p]&!!Q\u0002B\u0004\u0005115\u000fU3s[&\u001c8/[8o\u0003UaujR0G\u00132+u\fU#S\u001b&\u001b6+S(O'\u0002\na\u0003T(H?\u001a{E\nR#S?B+%+T%T'&{ejU\u0001\u0018\u0019>;uLR(M\t\u0016\u0013v\fU#S\u001b&\u001b6+S(O'\u0002\nQ!\u00199qYf$2\"\u001eB\r\u00057\u0011iBa\b\u0003\"!)Aj\fa\u0001\u001b\")\u0001l\fa\u00013\")Al\fa\u0001;\")Qm\fa\u0001M\")!n\fa\u0001W\u0006!b.Y7f\r>\u0014\u0018\t\u001d9B]\u0012\fE\u000f^3naR$R!\u0014B\u0014\u0005SAQ\u0001\u0014\u0019A\u00025CQ\u0001\u0017\u0019A\u0002e\u000b\u0011bY8eK\u000et\u0015-\\3\u0015\u0007e\u0013y\u0003C\u0004\u00032E\u0002\r!a%\u0002\u00071|w-A\u0006jg\u000e{W\u000e]1di\u0016$Gc\u0001@\u00038!9!\u0011\u0007\u001aA\u0002\u0005M\u0005"
)
public abstract class EventLogFileWriter implements Logging {
   private final URI logBaseDir;
   private final SparkConf sparkConf;
   private final Configuration hadoopConf;
   private final boolean shouldCompress;
   private final boolean shouldOverwrite;
   private final int outputBufferSize;
   private final FileSystem fileSystem;
   private final Option compressionCodec;
   private final Option compressionCodecName;
   private Option hadoopDataStream;
   private Option writer;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean isCompacted(final Path log) {
      return EventLogFileWriter$.MODULE$.isCompacted(log);
   }

   public static Option codecName(final Path log) {
      return EventLogFileWriter$.MODULE$.codecName(log);
   }

   public static String nameForAppAndAttempt(final String appId, final Option appAttemptId) {
      return EventLogFileWriter$.MODULE$.nameForAppAndAttempt(appId, appAttemptId);
   }

   public static EventLogFileWriter apply(final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      return EventLogFileWriter$.MODULE$.apply(appId, appAttemptId, logBaseDir, sparkConf, hadoopConf);
   }

   public static FsPermission LOG_FOLDER_PERMISSIONS() {
      return EventLogFileWriter$.MODULE$.LOG_FOLDER_PERMISSIONS();
   }

   public static FsPermission LOG_FILE_PERMISSIONS() {
      return EventLogFileWriter$.MODULE$.LOG_FILE_PERMISSIONS();
   }

   public static String COMPACTED() {
      return EventLogFileWriter$.MODULE$.COMPACTED();
   }

   public static String IN_PROGRESS() {
      return EventLogFileWriter$.MODULE$.IN_PROGRESS();
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

   public boolean shouldCompress() {
      return this.shouldCompress;
   }

   public boolean shouldOverwrite() {
      return this.shouldOverwrite;
   }

   public int outputBufferSize() {
      return this.outputBufferSize;
   }

   public FileSystem fileSystem() {
      return this.fileSystem;
   }

   public Option compressionCodec() {
      return this.compressionCodec;
   }

   public Option compressionCodecName() {
      return this.compressionCodecName;
   }

   public Option hadoopDataStream() {
      return this.hadoopDataStream;
   }

   public void hadoopDataStream_$eq(final Option x$1) {
      this.hadoopDataStream = x$1;
   }

   public Option writer() {
      return this.writer;
   }

   public void writer_$eq(final Option x$1) {
      this.writer = x$1;
   }

   public void requireLogBaseDirAsDirectory() {
      if (!this.fileSystem().getFileStatus(new Path(this.logBaseDir)).isDirectory()) {
         throw new IllegalArgumentException("Log directory " + this.logBaseDir + " is not a directory.");
      }
   }

   public void initLogFile(final Path path, final Function1 fnSetupWriter) {
      if (this.shouldOverwrite() && this.fileSystem().delete(path, true)) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Event log ", " already exists. Overwriting..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))));
      }

      boolean var10000;
      label59: {
         String defaultFs = FileSystem.getDefaultUri(this.hadoopConf).getScheme();
         if (defaultFs != null) {
            label58: {
               String var5 = "file";
               if (defaultFs == null) {
                  if (var5 == null) {
                     break label58;
                  }
               } else if (defaultFs.equals(var5)) {
                  break label58;
               }

               var10000 = false;
               break label59;
            }
         }

         var10000 = true;
      }

      label61: {
         boolean isDefaultLocal = var10000;
         URI uri = path.toUri();
         if (!isDefaultLocal || uri.getScheme() != null) {
            label60: {
               String var13 = uri.getScheme();
               String var8 = "file";
               if (var13 == null) {
                  if (var8 == null) {
                     break label60;
                  }
               } else if (var13.equals(var8)) {
                  break label60;
               }

               this.hadoopDataStream_$eq(new Some(SparkHadoopUtil$.MODULE$.createFile(this.fileSystem(), path, BoxesRunTime.unboxToBoolean(this.sparkConf.get(package$.MODULE$.EVENT_LOG_ALLOW_EC())))));
               var14 = (OutputStream)this.hadoopDataStream().get();
               break label61;
            }
         }

         var14 = new FileOutputStream(uri.getPath());
      }

      OutputStream dstream = (OutputStream)var14;

      try {
         OutputStream cstream = (OutputStream)this.compressionCodec().map((x$1) -> x$1.compressedContinuousOutputStream(dstream)).getOrElse(() -> dstream);
         BufferedOutputStream bstream = new BufferedOutputStream(cstream, this.outputBufferSize());
         this.fileSystem().setPermission(path, EventLogFileWriter$.MODULE$.LOG_FILE_PERMISSIONS());
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Logging events to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))));
         this.writer_$eq(new Some(fnSetupWriter.apply(bstream)));
      } catch (Exception var12) {
         dstream.close();
         throw var12;
      }
   }

   public void writeLine(final String line, final boolean flushLogger) {
      this.writer().foreach((x$2) -> {
         $anonfun$writeLine$1(line, x$2);
         return BoxedUnit.UNIT;
      });
      if (flushLogger) {
         this.writer().foreach((x$3) -> {
            $anonfun$writeLine$2(x$3);
            return BoxedUnit.UNIT;
         });
         this.hadoopDataStream().foreach((x$4) -> {
            $anonfun$writeLine$3(x$4);
            return BoxedUnit.UNIT;
         });
      }
   }

   public boolean writeLine$default$2() {
      return false;
   }

   public void closeWriter() {
      this.writer().foreach((x$5) -> {
         $anonfun$closeWriter$1(x$5);
         return BoxedUnit.UNIT;
      });
   }

   public void renameFile(final Path src, final Path dest, final boolean overwrite) {
      if (this.fileSystem().exists(dest)) {
         if (!overwrite) {
            throw new IOException("Target log file already exists (" + dest + ")");
         }

         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Event log ", " already exists. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EVENT_LOG_DESTINATION..MODULE$, dest)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Overwriting..."})))).log(scala.collection.immutable.Nil..MODULE$))));
         if (!this.fileSystem().delete(dest, true)) {
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EVENT_LOG_DESTINATION..MODULE$, dest)})))));
         }
      }

      this.fileSystem().rename(src, dest);

      try {
         this.fileSystem().setTimes(dest, System.currentTimeMillis(), -1L);
      } catch (Exception var5) {
         this.logDebug((Function0)(() -> "failed to set time of " + dest), var5);
      }

   }

   public abstract void start();

   public abstract void writeEvent(final String eventJson, final boolean flushLogger);

   public boolean writeEvent$default$2() {
      return false;
   }

   public abstract void stop();

   public abstract String logPath();

   // $FF: synthetic method
   public static final void $anonfun$writeLine$1(final String line$1, final PrintWriter x$2) {
      x$2.println(line$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$writeLine$2(final PrintWriter x$3) {
      x$3.flush();
   }

   // $FF: synthetic method
   public static final void $anonfun$writeLine$3(final FSDataOutputStream x$4) {
      x$4.hflush();
   }

   // $FF: synthetic method
   public static final void $anonfun$closeWriter$1(final PrintWriter x$5) {
      x$5.close();
   }

   public EventLogFileWriter(final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      this.logBaseDir = logBaseDir;
      this.sparkConf = sparkConf;
      this.hadoopConf = hadoopConf;
      Logging.$init$(this);
      this.shouldCompress = BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.EVENT_LOG_COMPRESS())) && !((String)sparkConf.get(package$.MODULE$.EVENT_LOG_COMPRESSION_CODEC())).equalsIgnoreCase("none");
      this.shouldOverwrite = BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.EVENT_LOG_OVERWRITE()));
      this.outputBufferSize = (int)BoxesRunTime.unboxToLong(sparkConf.get(package$.MODULE$.EVENT_LOG_OUTPUT_BUFFER_SIZE())) * 1024;
      this.fileSystem = Utils$.MODULE$.getHadoopFileSystem(logBaseDir, hadoopConf);
      this.compressionCodec = (Option)(this.shouldCompress() ? new Some(CompressionCodec$.MODULE$.createCodec(sparkConf, (String)sparkConf.get(package$.MODULE$.EVENT_LOG_COMPRESSION_CODEC()))) : scala.None..MODULE$);
      this.compressionCodecName = this.compressionCodec().map((c) -> CompressionCodec$.MODULE$.getShortName(c.getClass().getName()));
      this.hadoopDataStream = scala.None..MODULE$;
      this.writer = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
