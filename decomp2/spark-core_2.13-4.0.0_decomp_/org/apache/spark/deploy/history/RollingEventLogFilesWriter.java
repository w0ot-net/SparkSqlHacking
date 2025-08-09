package org.apache.spark.deploy.history;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.package$;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=h\u0001\u0002\u0017.\u0001aB\u0001\"\u0010\u0001\u0003\u0002\u0003\u0006IA\u0010\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005\u0019\"A\u0001\u000b\u0001B\u0001B\u0003%\u0011\u000b\u0003\u0005Z\u0001\t\u0005\t\u0015!\u0003[\u0011!q\u0006A!A!\u0002\u0013y\u0006\"B4\u0001\t\u0003A\u0007bB8\u0001\u0005\u0004%I\u0001\u001d\u0005\u0007i\u0002\u0001\u000b\u0011B9\t\u000fU\u0004!\u0019!C\u0005m\"1Q\u0010\u0001Q\u0001\n]DqA \u0001A\u0002\u0013%q\u0010C\u0005\u0002\u0018\u0001\u0001\r\u0011\"\u0003\u0002\u001a!A\u0011Q\u0005\u0001!B\u0013\t\t\u0001\u0003\u0005\u0002(\u0001\u0001\r\u0011\"\u0003q\u0011%\tI\u0003\u0001a\u0001\n\u0013\tY\u0003C\u0004\u00020\u0001\u0001\u000b\u0015B9\t\u0015\u0005E\u0002\u00011AA\u0002\u0013%a\u000fC\u0006\u00024\u0001\u0001\r\u00111A\u0005\n\u0005U\u0002BCA\u001d\u0001\u0001\u0007\t\u0011)Q\u0005o\"9\u00111\b\u0001\u0005B\u0005u\u0002bBA \u0001\u0011\u0005\u0013\u0011\t\u0005\n\u0003#\u0002\u0011\u0013!C\u0001\u0003'B\u0001\"!\u001b\u0001\t\u0003i\u0013Q\b\u0005\b\u0003W\u0002A\u0011IA\u001f\u0011\u001d\ti\u0007\u0001C!\u0003_Bq!!\u001d\u0001\t\u0013\t\u0019hB\u0004\u0002z5B\t!a\u001f\u0007\r1j\u0003\u0012AA?\u0011\u00199G\u0004\"\u0001\u0002\u0006\"Q\u0011q\u0011\u000fC\u0002\u0013\u0005Q&!#\t\u0011\u0005UE\u0004)A\u0005\u0003\u0017C!\"a&\u001d\u0005\u0004%\t!LAE\u0011!\tI\n\bQ\u0001\n\u0005-\u0005BCAN9\t\u0007I\u0011A\u0017\u0002\n\"A\u0011Q\u0014\u000f!\u0002\u0013\tY\tC\u0004\u0002 r!\t!!)\t\u000f\u0005%F\u0004\"\u0001\u0002,\"9\u0011q\u0017\u000f\u0005\u0002\u0005e\u0006bBAd9\u0011\u0005\u0011\u0011\u001a\u0005\b\u0003+dB\u0011AAl\u0011\u001d\t)\u000e\bC\u0001\u0003;Dq!!9\u001d\t\u0003\t\u0019\u000fC\u0004\u0002hr!\t!!;\u00035I{G\u000e\\5oO\u00163XM\u001c;M_\u001e4\u0015\u000e\\3t/JLG/\u001a:\u000b\u00059z\u0013a\u00025jgR|'/\u001f\u0006\u0003aE\na\u0001Z3qY>L(B\u0001\u001a4\u0003\u0015\u0019\b/\u0019:l\u0015\t!T'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002m\u0005\u0019qN]4\u0004\u0001M\u0011\u0001!\u000f\t\u0003umj\u0011!L\u0005\u0003y5\u0012!#\u0012<f]Rdun\u001a$jY\u0016<&/\u001b;fe\u0006)\u0011\r\u001d9JIB\u0011q\b\u0013\b\u0003\u0001\u001a\u0003\"!\u0011#\u000e\u0003\tS!aQ\u001c\u0002\rq\u0012xn\u001c;?\u0015\u0005)\u0015!B:dC2\f\u0017BA$E\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011J\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u001d#\u0015\u0001D1qa\u0006#H/Z7qi&#\u0007cA'O}5\tA)\u0003\u0002P\t\n1q\n\u001d;j_:\f!\u0002\\8h\u0005\u0006\u001cX\rR5s!\t\u0011v+D\u0001T\u0015\t!V+A\u0002oKRT\u0011AV\u0001\u0005U\u00064\u0018-\u0003\u0002Y'\n\u0019QKU%\u0002\u0013M\u0004\u0018M]6D_:4\u0007CA.]\u001b\u0005\t\u0014BA/2\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0006iC\u0012|w\u000e]\"p]\u001a\u0004\"\u0001Y3\u000e\u0003\u0005T!AY2\u0002\t\r|gN\u001a\u0006\u0003IN\na\u0001[1e_>\u0004\u0018B\u00014b\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u00061A(\u001b8jiz\"b!\u001b6lY6t\u0007C\u0001\u001e\u0001\u0011\u0015id\u00011\u0001?\u0011\u0015Ye\u00011\u0001M\u0011\u0015\u0001f\u00011\u0001R\u0011\u0015If\u00011\u0001[\u0011\u0015qf\u00011\u0001`\u0003I)g/\u001a8u\r&dW-T1y\u0019\u0016tw\r\u001e5\u0016\u0003E\u0004\"!\u0014:\n\u0005M$%\u0001\u0002'p]\u001e\f1#\u001a<f]R4\u0015\u000e\\3NCbdUM\\4uQ\u0002\n\u0001\u0003\\8h\t&\u0014hi\u001c:BaB\u0004\u0016\r\u001e5\u0016\u0003]\u0004\"\u0001_>\u000e\u0003eT!A_2\u0002\u0005\u0019\u001c\u0018B\u0001?z\u0005\u0011\u0001\u0016\r\u001e5\u0002#1|w\rR5s\r>\u0014\u0018\t\u001d9QCRD\u0007%\u0001\u000bd_VtG/\u001b8h\u001fV$\b/\u001e;TiJ,\u0017-\\\u000b\u0003\u0003\u0003\u0001B!\u0014(\u0002\u0004A!\u0011QAA\n\u001b\t\t9A\u0003\u0003\u0002\n\u0005-\u0011AB8viB,HO\u0003\u0003\u0002\u000e\u0005=\u0011AA5p\u0015\r\t\tbM\u0001\bG>lWn\u001c8t\u0013\u0011\t)\"a\u0002\u0003)\r{WO\u001c;j]\u001e|U\u000f\u001e9viN#(/Z1n\u0003a\u0019w.\u001e8uS:<w*\u001e;qkR\u001cFO]3b[~#S-\u001d\u000b\u0005\u00037\t\t\u0003E\u0002N\u0003;I1!a\bE\u0005\u0011)f.\u001b;\t\u0013\u0005\rB\"!AA\u0002\u0005\u0005\u0011a\u0001=%c\u0005)2m\\;oi&twmT;uaV$8\u000b\u001e:fC6\u0004\u0013!B5oI\u0016D\u0018!C5oI\u0016Dx\fJ3r)\u0011\tY\"!\f\t\u0011\u0005\rr\"!AA\u0002E\fa!\u001b8eKb\u0004\u0013aF2veJ,g\u000e^#wK:$Hj\\4GS2,\u0007+\u0019;i\u0003m\u0019WO\u001d:f]R,e/\u001a8u\u0019><g)\u001b7f!\u0006$\bn\u0018\u0013fcR!\u00111DA\u001c\u0011!\t\u0019CEA\u0001\u0002\u00049\u0018\u0001G2veJ,g\u000e^#wK:$Hj\\4GS2,\u0007+\u0019;iA\u0005)1\u000f^1siR\u0011\u00111D\u0001\u000boJLG/Z#wK:$HCBA\u000e\u0003\u0007\n9\u0005\u0003\u0004\u0002FU\u0001\rAP\u0001\nKZ,g\u000e\u001e&t_:D\u0011\"!\u0013\u0016!\u0003\u0005\r!a\u0013\u0002\u0017\u0019dWo\u001d5M_\u001e<WM\u001d\t\u0004\u001b\u00065\u0013bAA(\t\n9!i\\8mK\u0006t\u0017\u0001F<sSR,WI^3oi\u0012\"WMZ1vYR$#'\u0006\u0002\u0002V)\"\u00111JA,W\t\tI\u0006\u0005\u0003\u0002\\\u0005\u0015TBAA/\u0015\u0011\ty&!\u0019\u0002\u0013Ut7\r[3dW\u0016$'bAA2\t\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u001d\u0014Q\f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001\u0005:pY2,e/\u001a8u\u0019><g)\u001b7f\u0003\u0011\u0019Ho\u001c9\u0002\u000f1|w\rU1uQV\ta(A\nde\u0016\fG/Z!qaN#\u0018\r^;t\r&dW\r\u0006\u0003\u0002\u001c\u0005U\u0004bBA<5\u0001\u0007\u00111J\u0001\u000bS:\u0004&o\\4sKN\u001c\u0018A\u0007*pY2LgnZ#wK:$Hj\\4GS2,7o\u0016:ji\u0016\u0014\bC\u0001\u001e\u001d'\ra\u0012q\u0010\t\u0004\u001b\u0006\u0005\u0015bAAB\t\n1\u0011I\\=SK\u001a$\"!a\u001f\u00023\u00153VI\u0014+`\u0019>;u\fR%S?:\u000bU*R0Q%\u00163\u0015\nW\u000b\u0003\u0003\u0017\u0003B!!$\u0002\u00146\u0011\u0011q\u0012\u0006\u0004\u0003#+\u0016\u0001\u00027b]\u001eL1!SAH\u0003i)e+\u0012(U?2{ui\u0018#J%~s\u0015)T#`!J+e)\u0013-!\u0003i)e+\u0012(U?2{ui\u0018$J\u0019\u0016{f*Q'F?B\u0013VIR%Y\u0003m)e+\u0012(U?2{ui\u0018$J\u0019\u0016{f*Q'F?B\u0013VIR%YA\u0005Q\u0012\t\u0015)T)\u0006#VkU0G\u00132+uLT!N\u000b~\u0003&+\u0012$J1\u0006Y\u0012\t\u0015)T)\u0006#VkU0G\u00132+uLT!N\u000b~\u0003&+\u0012$J1\u0002\nQcZ3u\u0003B\u0004XI^3oi2{w\rR5s!\u0006$\b\u000eF\u0004x\u0003G\u000b)+a*\t\u000bA#\u0003\u0019A)\t\u000bu\"\u0003\u0019\u0001 \t\u000b-#\u0003\u0019\u0001'\u0002)\u001d,G/\u00119q'R\fG/^:GS2,\u0007+\u0019;i)%9\u0018QVAY\u0003g\u000b)\f\u0003\u0004\u00020\u0016\u0002\ra^\u0001\nCB\u0004Hj\\4ESJDQ!P\u0013A\u0002yBQaS\u0013A\u00021Cq!a\u001e&\u0001\u0004\tY%A\nhKR,e/\u001a8u\u0019><g)\u001b7f!\u0006$\b\u000eF\u0006x\u0003w\u000bi,a0\u0002B\u0006\r\u0007BBAXM\u0001\u0007q\u000fC\u0003>M\u0001\u0007a\bC\u0003LM\u0001\u0007A\n\u0003\u0004\u0002(\u0019\u0002\r!\u001d\u0005\u0007\u0003\u000b4\u0003\u0019\u0001'\u0002\u0013\r|G-Z2OC6,\u0017!D5t\u000bZ,g\u000e\u001e'pO\u0012K'\u000f\u0006\u0003\u0002L\u0005-\u0007bBAgO\u0001\u0007\u0011qZ\u0001\u0007gR\fG/^:\u0011\u0007a\f\t.C\u0002\u0002Tf\u0014!BR5mKN#\u0018\r^;t\u00039I7/\u0012<f]Rdun\u001a$jY\u0016$B!a\u0013\u0002Z\"1\u00111\u001c\u0015A\u0002y\n\u0001BZ5mK:\u000bW.\u001a\u000b\u0005\u0003\u0017\ny\u000eC\u0004\u0002N&\u0002\r!a4\u0002\u001f%\u001c\u0018\t\u001d9Ti\u0006$Xo\u001d$jY\u0016$B!a\u0013\u0002f\"9\u0011Q\u001a\u0016A\u0002\u0005=\u0017\u0001F4fi\u00163XM\u001c;M_\u001e4\u0015\u000e\\3J]\u0012,\u0007\u0010F\u0002r\u0003WDa!!<,\u0001\u0004q\u0014\u0001E3wK:$Hj\\4GS2,g*Y7f\u0001"
)
public class RollingEventLogFilesWriter extends EventLogFileWriter {
   private final String appId;
   private final Option appAttemptId;
   private final long eventFileMaxLength;
   private final Path logDirForAppPath;
   private Option countingOutputStream;
   private long index;
   private Path currentEventLogFilePath;

   public static long getEventLogFileIndex(final String eventLogFileName) {
      return RollingEventLogFilesWriter$.MODULE$.getEventLogFileIndex(eventLogFileName);
   }

   public static boolean isAppStatusFile(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isAppStatusFile(status);
   }

   public static boolean isEventLogFile(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isEventLogFile(status);
   }

   public static boolean isEventLogFile(final String fileName) {
      return RollingEventLogFilesWriter$.MODULE$.isEventLogFile(fileName);
   }

   public static boolean isEventLogDir(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isEventLogDir(status);
   }

   public static Path getEventLogFilePath(final Path appLogDir, final String appId, final Option appAttemptId, final long index, final Option codecName) {
      return RollingEventLogFilesWriter$.MODULE$.getEventLogFilePath(appLogDir, appId, appAttemptId, index, codecName);
   }

   public static Path getAppStatusFilePath(final Path appLogDir, final String appId, final Option appAttemptId, final boolean inProgress) {
      return RollingEventLogFilesWriter$.MODULE$.getAppStatusFilePath(appLogDir, appId, appAttemptId, inProgress);
   }

   public static Path getAppEventLogDirPath(final URI logBaseDir, final String appId, final Option appAttemptId) {
      return RollingEventLogFilesWriter$.MODULE$.getAppEventLogDirPath(logBaseDir, appId, appAttemptId);
   }

   private long eventFileMaxLength() {
      return this.eventFileMaxLength;
   }

   private Path logDirForAppPath() {
      return this.logDirForAppPath;
   }

   private Option countingOutputStream() {
      return this.countingOutputStream;
   }

   private void countingOutputStream_$eq(final Option x$1) {
      this.countingOutputStream = x$1;
   }

   private long index() {
      return this.index;
   }

   private void index_$eq(final long x$1) {
      this.index = x$1;
   }

   private Path currentEventLogFilePath() {
      return this.currentEventLogFilePath;
   }

   private void currentEventLogFilePath_$eq(final Path x$1) {
      this.currentEventLogFilePath = x$1;
   }

   public void start() {
      this.requireLogBaseDirAsDirectory();
      if (this.fileSystem().exists(this.logDirForAppPath()) && this.shouldOverwrite()) {
         BoxesRunTime.boxToBoolean(this.fileSystem().delete(this.logDirForAppPath(), true));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (this.fileSystem().exists(this.logDirForAppPath())) {
         throw new IOException("Target log directory already exists (" + this.logDirForAppPath() + ")");
      } else {
         FileSystem.mkdirs(this.fileSystem(), this.logDirForAppPath(), EventLogFileWriter$.MODULE$.LOG_FOLDER_PERMISSIONS());
         this.createAppStatusFile(true);
         this.rollEventLogFile();
      }
   }

   public void writeEvent(final String eventJson, final boolean flushLogger) {
      this.writer().foreach((w) -> {
         $anonfun$writeEvent$1(this, eventJson, w);
         return BoxedUnit.UNIT;
      });
      this.writeLine(eventJson, flushLogger);
   }

   public boolean writeEvent$default$2() {
      return false;
   }

   public void rollEventLogFile() {
      this.closeWriter();
      this.index_$eq(this.index() + 1L);
      this.currentEventLogFilePath_$eq(RollingEventLogFilesWriter$.MODULE$.getEventLogFilePath(this.logDirForAppPath(), this.appId, this.appAttemptId, this.index(), this.compressionCodecName()));
      this.initLogFile(this.currentEventLogFilePath(), (os) -> {
         this.countingOutputStream_$eq(new Some(new CountingOutputStream(os)));
         return new PrintWriter(new OutputStreamWriter((OutputStream)this.countingOutputStream().get(), StandardCharsets.UTF_8));
      });
   }

   public void stop() {
      this.closeWriter();
      Path appStatusPathIncomplete = RollingEventLogFilesWriter$.MODULE$.getAppStatusFilePath(this.logDirForAppPath(), this.appId, this.appAttemptId, true);
      Path appStatusPathComplete = RollingEventLogFilesWriter$.MODULE$.getAppStatusFilePath(this.logDirForAppPath(), this.appId, this.appAttemptId, false);
      this.renameFile(appStatusPathIncomplete, appStatusPathComplete, true);
   }

   public String logPath() {
      return this.logDirForAppPath().toString();
   }

   private void createAppStatusFile(final boolean inProgress) {
      Path appStatusPath = RollingEventLogFilesWriter$.MODULE$.getAppStatusFilePath(this.logDirForAppPath(), this.appId, this.appAttemptId, inProgress);
      FSDataOutputStream outputStream = FileSystem.create(this.fileSystem(), appStatusPath, EventLogFileWriter$.MODULE$.LOG_FILE_PERMISSIONS());
      outputStream.close();
   }

   // $FF: synthetic method
   public static final void $anonfun$writeEvent$1(final RollingEventLogFilesWriter $this, final String eventJson$1, final PrintWriter w) {
      long currentLen = ((CountingOutputStream)$this.countingOutputStream().get()).getByteCount();
      if (currentLen + (long)eventJson$1.length() > $this.eventFileMaxLength()) {
         $this.rollEventLogFile();
      }
   }

   public RollingEventLogFilesWriter(final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      super(appId, appAttemptId, logBaseDir, sparkConf, hadoopConf);
      this.appId = appId;
      this.appAttemptId = appAttemptId;
      this.eventFileMaxLength = BoxesRunTime.unboxToLong(sparkConf.get(package$.MODULE$.EVENT_LOG_ROLLING_MAX_FILE_SIZE()));
      this.logDirForAppPath = RollingEventLogFilesWriter$.MODULE$.getAppEventLogDirPath(logBaseDir, appId, appAttemptId);
      this.countingOutputStream = .MODULE$;
      this.index = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
