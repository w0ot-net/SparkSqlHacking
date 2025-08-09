package org.apache.spark.input;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EqA\u0002\b\u0010\u0011\u0003\trC\u0002\u0004\u001a\u001f!\u0005\u0011C\u0007\u0005\u0006C\u0005!\ta\t\u0005\bI\u0005\u0011\r\u0011\"\u0001&\u0011\u0019q\u0013\u0001)A\u0005M!)q&\u0001C\u0001a\u0019)\u0011d\u0004\u0001\u0012}!)\u0011E\u0002C\u0001+\"9qK\u0002a\u0001\n\u0013A\u0006bB-\u0007\u0001\u0004%IA\u0017\u0005\u0007A\u001a\u0001\u000b\u0015B\u0019\t\u000b\u00054A\u0011\t2\t\u000b=4A\u0011\t9\t\u000bi4A\u0011I>\u00029\u0019K\u00070\u001a3MK:<G\u000f\u001b\"j]\u0006\u0014\u00180\u00138qkR4uN]7bi*\u0011\u0001#E\u0001\u0006S:\u0004X\u000f\u001e\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sOB\u0011\u0001$A\u0007\u0002\u001f\tab)\u001b=fI2+gn\u001a;i\u0005&t\u0017M]=J]B,HOR8s[\u0006$8CA\u0001\u001c!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0018\u0003Y\u0011ViQ(S\t~cUIT$U\u0011~\u0003&k\u0014)F%RKV#\u0001\u0014\u0011\u0005\u001dbS\"\u0001\u0015\u000b\u0005%R\u0013\u0001\u00027b]\u001eT\u0011aK\u0001\u0005U\u00064\u0018-\u0003\u0002.Q\t11\u000b\u001e:j]\u001e\fqCU#D\u001fJ#u\fT#O\u000fRCu\f\u0015*P!\u0016\u0013F+\u0017\u0011\u0002\u001f\u001d,GOU3d_J$G*\u001a8hi\"$\"!\r\u001b\u0011\u0005q\u0011\u0014BA\u001a\u001e\u0005\rIe\u000e\u001e\u0005\u0006k\u0015\u0001\rAN\u0001\bG>tG/\u001a=u!\t9D(D\u00019\u0015\tI$(A\u0005nCB\u0014X\rZ;dK*\u00111hE\u0001\u0007Q\u0006$wn\u001c9\n\u0005uB$A\u0003&pE\u000e{g\u000e^3yiN\u0019aaP(\u0011\t\u0001#e\tT\u0007\u0002\u0003*\u0011\u0001C\u0011\u0006\u0003\u0007b\n1\u0001\\5c\u0013\t)\u0015IA\bGS2,\u0017J\u001c9vi\u001a{'/\\1u!\t9%*D\u0001I\u0015\tI%(\u0001\u0002j_&\u00111\n\u0013\u0002\r\u0019>twm\u0016:ji\u0006\u0014G.\u001a\t\u0003\u000f6K!A\u0014%\u0003\u001b\tKH/Z:Xe&$\u0018M\u00197f!\t\u00016+D\u0001R\u0015\t\u0011\u0016#\u0001\u0005j]R,'O\\1m\u0013\t!\u0016KA\u0004M_\u001e<\u0017N\\4\u0015\u0003Y\u0003\"\u0001\u0007\u0004\u0002\u0019I,7m\u001c:e\u0019\u0016tw\r\u001e5\u0016\u0003E\n\u0001C]3d_J$G*\u001a8hi\"|F%Z9\u0015\u0005ms\u0006C\u0001\u000f]\u0013\tiVD\u0001\u0003V]&$\bbB0\n\u0003\u0003\u0005\r!M\u0001\u0004q\u0012\n\u0014!\u0004:fG>\u0014H\rT3oORD\u0007%A\u0006jgN\u0003H.\u001b;bE2,GcA2gOB\u0011A\u0004Z\u0005\u0003Kv\u0011qAQ8pY\u0016\fg\u000eC\u00036\u0017\u0001\u0007a\u0007C\u0003i\u0017\u0001\u0007\u0011.\u0001\u0005gS2,g.Y7f!\tQW.D\u0001l\u0015\ta'(\u0001\u0002gg&\u0011an\u001b\u0002\u0005!\u0006$\b.\u0001\td_6\u0004X\u000f^3Ta2LGoU5{KR!\u0011\u000f\u001e<y!\ta\"/\u0003\u0002t;\t!Aj\u001c8h\u0011\u0015)H\u00021\u0001r\u0003%\u0011Gn\\2l'&TX\rC\u0003x\u0019\u0001\u0007\u0011/A\u0004nS:\u001c\u0016N_3\t\u000bed\u0001\u0019A9\u0002\u000f5\f\u0007pU5{K\u0006\u00112M]3bi\u0016\u0014VmY8sIJ+\u0017\rZ3s)\u0011ax0!\u0003\u0011\t]jh\tT\u0005\u0003}b\u0012ABU3d_J$'+Z1eKJDq!!\u0001\u000e\u0001\u0004\t\u0019!A\u0003ta2LG\u000fE\u00028\u0003\u000bI1!a\u00029\u0005)Ie\u000e];u'Bd\u0017\u000e\u001e\u0005\u0007k5\u0001\r!a\u0003\u0011\u0007]\ni!C\u0002\u0002\u0010a\u0012!\u0003V1tW\u0006#H/Z7qi\u000e{g\u000e^3yi\u0002"
)
public class FixedLengthBinaryInputFormat extends FileInputFormat implements Logging {
   private int recordLength;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static int getRecordLength(final JobContext context) {
      return FixedLengthBinaryInputFormat$.MODULE$.getRecordLength(context);
   }

   public static String RECORD_LENGTH_PROPERTY() {
      return FixedLengthBinaryInputFormat$.MODULE$.RECORD_LENGTH_PROPERTY();
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

   private int recordLength() {
      return this.recordLength;
   }

   private void recordLength_$eq(final int x$1) {
      this.recordLength = x$1;
   }

   public boolean isSplitable(final JobContext context, final Path filename) {
      if (this.recordLength() == -1) {
         this.recordLength_$eq(FixedLengthBinaryInputFormat$.MODULE$.getRecordLength(context));
      }

      if (this.recordLength() <= 0) {
         this.logDebug((Function0)(() -> "record length is less than 0, file cannot be split"));
         return false;
      } else {
         return true;
      }
   }

   public long computeSplitSize(final long blockSize, final long minSize, final long maxSize) {
      long defaultSize = super.computeSplitSize(blockSize, minSize, maxSize);
      return defaultSize < (long)this.recordLength() ? (long)this.recordLength() : defaultSize / (long)this.recordLength() * (long)this.recordLength();
   }

   public RecordReader createRecordReader(final InputSplit split, final TaskAttemptContext context) {
      return new FixedLengthBinaryRecordReader();
   }

   public FixedLengthBinaryInputFormat() {
      Logging.$init$(this);
      this.recordLength = -1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
