package org.apache.spark.mllib.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.random.XORShiftRandom;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.HashMap;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t=f\u0001B\u001d;\u0001\u0015CQA\u0018\u0001\u0005\u0002}CqA\u0019\u0001A\u0002\u0013%1\rC\u0004h\u0001\u0001\u0007I\u0011\u00025\t\r9\u0004\u0001\u0015)\u0003e\u0011\u001dy\u0007\u00011A\u0005\nADq\u0001\u001e\u0001A\u0002\u0013%Q\u000f\u0003\u0004x\u0001\u0001\u0006K!\u001d\u0005\bq\u0002\u0001\r\u0011\"\u0003d\u0011\u001dI\b\u00011A\u0005\niDa\u0001 \u0001!B\u0013!\u0007bB?\u0001\u0001\u0004%Ia\u0019\u0005\b}\u0002\u0001\r\u0011\"\u0003\u0000\u0011\u001d\t\u0019\u0001\u0001Q!\n\u0011D\u0011\"!\u0002\u0001\u0001\u0004%I!a\u0002\t\u0013\u0005=\u0001\u00011A\u0005\n\u0005E\u0001\u0002CA\u000b\u0001\u0001\u0006K!!\u0003\t\u0011\u0005]\u0001\u00011A\u0005\n\rD\u0011\"!\u0007\u0001\u0001\u0004%I!a\u0007\t\u000f\u0005}\u0001\u0001)Q\u0005I\"A\u0011\u0011\u0005\u0001A\u0002\u0013%1\rC\u0005\u0002$\u0001\u0001\r\u0011\"\u0003\u0002&!9\u0011\u0011\u0006\u0001!B\u0013!\u0007bBA\u0016\u0001\u0011\u0005\u0011Q\u0006\u0005\b\u0003\u000b\u0002A\u0011AA$\u0011\u001d\t\t\u0006\u0001C\u0001\u0003'Bq!!\u0017\u0001\t\u0003\tY\u0006C\u0004\u0002b\u0001!\t!a\u0019\t\u000f\u0005%\u0004\u0001\"\u0001\u0002l!9\u0011\u0011\u000f\u0001\u0005\u0002\u0005M\u0004bBA@\u0001\u0011\u0005\u0011\u0011\u0011\u0005\t\u0003\u0017\u0003!\u0019!C\u0005G\"9\u0011Q\u0012\u0001!\u0002\u0013!\u0007\u0002CAH\u0001\t\u0007I\u0011B2\t\u000f\u0005E\u0005\u0001)A\u0005I\"A\u00111\u0013\u0001C\u0002\u0013%1\rC\u0004\u0002\u0016\u0002\u0001\u000b\u0011\u00023\t\u0011\u0005]\u0004\u00011A\u0005\n\rD\u0011\"a&\u0001\u0001\u0004%I!!'\t\u000f\u0005u\u0005\u0001)Q\u0005I\"I\u0011q\u0014\u0001A\u0002\u0013%\u0011q\u0001\u0005\n\u0003C\u0003\u0001\u0019!C\u0005\u0003GC\u0001\"a*\u0001A\u0003&\u0011\u0011\u0002\u0005\t\u0003S\u0003\u0001\u0019!C\u0005G\"I\u00111\u0016\u0001A\u0002\u0013%\u0011Q\u0016\u0005\b\u0003c\u0003\u0001\u0015)\u0003e\u0011%\t\u0019\f\u0001a\u0001\n\u0013\t)\fC\u0005\u0002D\u0002\u0001\r\u0011\"\u0003\u0002F\"A\u0011\u0011\u001a\u0001!B\u0013\t9\fC\u0005\u0002T\u0002\u0011\r\u0011\"\u0003\u0002V\"A\u0011q\u001f\u0001!\u0002\u0013\t9\u000eC\u0004\u0002|\u0002!I!!@\t\u000f\t\u001d\u0002\u0001\"\u0003\u0003*!9!1\u0007\u0001\u0005\n\tU\u0002b\u0002B\u001c\u0001\u0011\u0005!\u0011\b\u0005\b\u0005\u001b\u0002A\u0011\u0002B(\u0011\u001d\u00119\u0004\u0001C\u0001\u0005\u0007\u0013\u0001bV8sIJ2Vm\u0019\u0006\u0003wq\nqAZ3biV\u0014XM\u0003\u0002>}\u0005)Q\u000e\u001c7jE*\u0011q\bQ\u0001\u0006gB\f'o\u001b\u0006\u0003\u0003\n\u000ba!\u00199bG\",'\"A\"\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u00011E\n\u0017\t\u0003\u000f*k\u0011\u0001\u0013\u0006\u0002\u0013\u0006)1oY1mC&\u00111\n\u0013\u0002\u0007\u0003:L(+\u001a4\u0011\u00055+fB\u0001(T\u001d\ty%+D\u0001Q\u0015\t\tF)\u0001\u0004=e>|GOP\u0005\u0002\u0013&\u0011A\u000bS\u0001\ba\u0006\u001c7.Y4f\u0013\t1vK\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002U\u0011B\u0011\u0011\fX\u0007\u00025*\u00111LP\u0001\tS:$XM\u001d8bY&\u0011QL\u0017\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}Q\t\u0001\r\u0005\u0002b\u00015\t!(\u0001\u0006wK\u000e$xN]*ju\u0016,\u0012\u0001\u001a\t\u0003\u000f\u0016L!A\u001a%\u0003\u0007%sG/\u0001\bwK\u000e$xN]*ju\u0016|F%Z9\u0015\u0005%d\u0007CA$k\u0013\tY\u0007J\u0001\u0003V]&$\bbB7\u0004\u0003\u0003\u0005\r\u0001Z\u0001\u0004q\u0012\n\u0014a\u0003<fGR|'oU5{K\u0002\nA\u0002\\3be:Lgn\u001a*bi\u0016,\u0012!\u001d\t\u0003\u000fJL!a\u001d%\u0003\r\u0011{WO\u00197f\u0003AaW-\u0019:oS:<'+\u0019;f?\u0012*\u0017\u000f\u0006\u0002jm\"9QNBA\u0001\u0002\u0004\t\u0018!\u00047fCJt\u0017N\\4SCR,\u0007%A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\u0012]Vl\u0007+\u0019:uSRLwN\\:`I\u0015\fHCA5|\u0011\u001di\u0017\"!AA\u0002\u0011\faB\\;n!\u0006\u0014H/\u001b;j_:\u001c\b%A\u0007ok6LE/\u001a:bi&|gn]\u0001\u0012]Vl\u0017\n^3sCRLwN\\:`I\u0015\fHcA5\u0002\u0002!9Q\u000eDA\u0001\u0002\u0004!\u0017A\u00048v[&#XM]1uS>t7\u000fI\u0001\u0005g\u0016,G-\u0006\u0002\u0002\nA\u0019q)a\u0003\n\u0007\u00055\u0001J\u0001\u0003M_:<\u0017\u0001C:fK\u0012|F%Z9\u0015\u0007%\f\u0019\u0002\u0003\u0005n\u001f\u0005\u0005\t\u0019AA\u0005\u0003\u0015\u0019X-\u001a3!\u0003!i\u0017N\\\"pk:$\u0018\u0001D7j]\u000e{WO\u001c;`I\u0015\fHcA5\u0002\u001e!9QNEA\u0001\u0002\u0004!\u0017!C7j]\u000e{WO\u001c;!\u0003Ei\u0017\r_*f]R,gnY3MK:<G\u000f[\u0001\u0016[\u0006D8+\u001a8uK:\u001cW\rT3oORDw\fJ3r)\rI\u0017q\u0005\u0005\b[V\t\t\u00111\u0001e\u0003Ii\u0017\r_*f]R,gnY3MK:<G\u000f\u001b\u0011\u0002)M,G/T1y'\u0016tG/\u001a8dK2+gn\u001a;i)\u0011\ty#!\r\u000e\u0003\u0001Aa!!\t\u0018\u0001\u0004!\u0007&B\f\u00026\u0005\u0005\u0003\u0003BA\u001c\u0003{i!!!\u000f\u000b\u0007\u0005mb(\u0001\u0006b]:|G/\u0019;j_:LA!a\u0010\u0002:\t)1+\u001b8dK\u0006\u0012\u00111I\u0001\u0006e9\u0002d\u0006M\u0001\u000eg\u0016$h+Z2u_J\u001c\u0016N_3\u0015\t\u0005=\u0012\u0011\n\u0005\u0006Eb\u0001\r\u0001\u001a\u0015\u00061\u0005U\u0012QJ\u0011\u0003\u0003\u001f\nQ!\r\u00182]A\nqb]3u\u0019\u0016\f'O\\5oOJ\u000bG/\u001a\u000b\u0005\u0003_\t)\u0006C\u0003p3\u0001\u0007\u0011\u000fK\u0003\u001a\u0003k\ti%\u0001\ttKRtU/\u001c)beRLG/[8ogR!\u0011qFA/\u0011\u0015A(\u00041\u0001eQ\u0015Q\u0012QGA'\u0003A\u0019X\r\u001e(v[&#XM]1uS>t7\u000f\u0006\u0003\u00020\u0005\u0015\u0004\"B?\u001c\u0001\u0004!\u0007&B\u000e\u00026\u00055\u0013aB:fiN+W\r\u001a\u000b\u0005\u0003_\ti\u0007C\u0004\u0002\u0006q\u0001\r!!\u0003)\u000bq\t)$!\u0014\u0002\u001bM,GoV5oI><8+\u001b>f)\u0011\ty#!\u001e\t\r\u0005]T\u00041\u0001e\u0003\u00199\u0018N\u001c3po\"*Q$!\u000e\u0002|\u0005\u0012\u0011QP\u0001\u0006c92d\u0006M\u0001\fg\u0016$X*\u001b8D_VtG\u000f\u0006\u0003\u00020\u0005\r\u0005BBA\f=\u0001\u0007A\rK\u0003\u001f\u0003k\t9)\t\u0002\u0002\n\u0006)\u0011GL\u001a/a\u0005qQ\t\u0017)`)\u0006\u0013E*R0T\u0013j+\u0015aD#Y!~#\u0016I\u0011'F?NK%,\u0012\u0011\u0002\u000f5\u000b\u0005lX#Y!\u0006AQ*\u0011-`\u000bb\u0003\u0006%A\bN\u0003b{6i\u0014#F?2+ej\u0012+I\u0003Ai\u0015\tW0D\u001f\u0012+u\fT#O\u000fRC\u0005%\u0001\u0006xS:$wn^0%KF$2![AN\u0011\u001dig%!AA\u0002\u0011\fqa^5oI><\b%A\bue\u0006LgnV8sIN\u001cu.\u001e8u\u0003M!(/Y5o/>\u0014Hm]\"pk:$x\fJ3r)\rI\u0017Q\u0015\u0005\t[&\n\t\u00111\u0001\u0002\n\u0005\u0001BO]1j]^{'\u000fZ:D_VtG\u000fI\u0001\nm>\u001c\u0017MY*ju\u0016\fQB^8dC\n\u001c\u0016N_3`I\u0015\fHcA5\u00020\"9Q\u000eLA\u0001\u0002\u0004!\u0017A\u0003<pG\u0006\u00147+\u001b>fA\u0005)ao\\2bEV\u0011\u0011q\u0017\t\u0006\u000f\u0006e\u0016QX\u0005\u0004\u0003wC%!B!se\u0006L\bcA1\u0002@&\u0019\u0011\u0011\u0019\u001e\u0003\u0013Y{7-\u00192X_J$\u0017!\u0003<pG\u0006\u0014w\fJ3r)\rI\u0017q\u0019\u0005\t[>\n\t\u00111\u0001\u00028\u00061ao\\2bE\u0002B3\u0001MAg!\r9\u0015qZ\u0005\u0004\u0003#D%!\u0003;sC:\u001c\u0018.\u001a8u\u0003%1xnY1c\u0011\u0006\u001c\b.\u0006\u0002\u0002XB9\u0011\u0011\\Ar\u0003O$WBAAn\u0015\u0011\ti.a8\u0002\u000f5,H/\u00192mK*\u0019\u0011\u0011\u001d%\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002f\u0006m'a\u0002%bg\"l\u0015\r\u001d\t\u0005\u0003S\f\tP\u0004\u0003\u0002l\u00065\bCA(I\u0013\r\ty\u000fS\u0001\u0007!J,G-\u001a4\n\t\u0005M\u0018Q\u001f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005=\b*\u0001\u0006w_\u000e\f'\rS1tQ\u0002B3AMAg\u0003)aW-\u0019:o->\u001c\u0017MY\u000b\u0005\u0003\u007f\u0014)\u0002F\u0002j\u0005\u0003AqAa\u00014\u0001\u0004\u0011)!A\u0004eCR\f7/\u001a;\u0011\r\t\u001d!Q\u0002B\t\u001b\t\u0011IAC\u0002\u0003\fy\n1A\u001d3e\u0013\u0011\u0011yA!\u0003\u0003\u0007I#E\t\u0005\u0003\u0003\u0014\tUA\u0002\u0001\u0003\b\u0005/\u0019$\u0019\u0001B\r\u0005\u0005\u0019\u0016\u0003\u0002B\u000e\u0005C\u00012a\u0012B\u000f\u0013\r\u0011y\u0002\u0013\u0002\b\u001d>$\b.\u001b8h!\u0015i%1EAt\u0013\r\u0011)c\u0016\u0002\t\u0013R,'/\u00192mK\u0006q1M]3bi\u0016,\u0005\u0010\u001d+bE2,GC\u0001B\u0016!\u00159\u0015\u0011\u0018B\u0017!\r9%qF\u0005\u0004\u0005cA%!\u0002$m_\u0006$\u0018\u0001E2sK\u0006$XMQ5oCJLHK]3f)\u0005I\u0017a\u00014jiV!!1\bB%)\u0011\u0011iDa\u0011\u0011\u0007\u0005\u0014y$C\u0002\u0003Bi\u0012QbV8sIJ2VmY'pI\u0016d\u0007b\u0002B\u0002m\u0001\u0007!Q\t\t\u0007\u0005\u000f\u0011iAa\u0012\u0011\t\tM!\u0011\n\u0003\b\u0005/1$\u0019\u0001B\rQ\u00151\u0014QGA'\u0003\u0015!wNR5u+\u0011\u0011\tF!\u0017\u0015\u0019\tu\"1\u000bB.\u0005O\u00129H! \t\u000f\t\rq\u00071\u0001\u0003VA1!q\u0001B\u0007\u0005/\u0002BAa\u0005\u0003Z\u00119!qC\u001cC\u0002\te\u0001b\u0002B/o\u0001\u0007!qL\u0001\u0003g\u000e\u0004BA!\u0019\u0003d5\ta(C\u0002\u0003fy\u0012Ab\u00159be.\u001cuN\u001c;fqRDqA!\u001b8\u0001\u0004\u0011Y'\u0001\u0005fqB$\u0016M\u00197f!\u0019\u0011iGa\u001d\u0003,5\u0011!q\u000e\u0006\u0004\u0005cr\u0014!\u00032s_\u0006$7-Y:u\u0013\u0011\u0011)Ha\u001c\u0003\u0013\t\u0013x.\u00193dCN$\bb\u0002B=o\u0001\u0007!1P\u0001\bE\u000e4vnY1c!\u0019\u0011iGa\u001d\u00028\"9!qP\u001cA\u0002\t\u0005\u0015a\u00032d->\u001c\u0017M\u0019%bg\"\u0004bA!\u001c\u0003t\u0005]W\u0003\u0002BC\u00057#BA!\u0010\u0003\b\"9!1\u0001\u001dA\u0002\t%\u0005C\u0002BF\u0005+\u0013I*\u0004\u0002\u0003\u000e*!!q\u0012BI\u0003\u0011Q\u0017M^1\u000b\u0007\tMe(A\u0002ba&LAAa&\u0003\u000e\n9!*\u0019<b%\u0012#\u0005\u0003\u0002B\n\u00057#qAa\u00069\u0005\u0004\u0011i*\u0005\u0003\u0003\u001c\t}\u0005C\u0002BQ\u0005S\u000b9/\u0004\u0002\u0003$*!!Q\u0015BT\u0003\u0011a\u0017M\\4\u000b\u0005\t=\u0015\u0002\u0002B\u0013\u0005GCS\u0001OA\u001b\u0003\u001bBS\u0001AA\u001b\u0003\u001b\u0002"
)
public class Word2Vec implements Serializable, Logging {
   private int vectorSize;
   private double learningRate;
   private int numPartitions;
   private int numIterations;
   private long seed;
   private int minCount;
   private int maxSentenceLength;
   private final int EXP_TABLE_SIZE;
   private final int MAX_EXP;
   private final int MAX_CODE_LENGTH;
   private int window;
   private long trainWordsCount;
   private int vocabSize;
   private transient VocabWord[] vocab;
   private final transient HashMap vocabHash;
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

   private int vectorSize() {
      return this.vectorSize;
   }

   private void vectorSize_$eq(final int x$1) {
      this.vectorSize = x$1;
   }

   private double learningRate() {
      return this.learningRate;
   }

   private void learningRate_$eq(final double x$1) {
      this.learningRate = x$1;
   }

   private int numPartitions() {
      return this.numPartitions;
   }

   private void numPartitions_$eq(final int x$1) {
      this.numPartitions = x$1;
   }

   private int numIterations() {
      return this.numIterations;
   }

   private void numIterations_$eq(final int x$1) {
      this.numIterations = x$1;
   }

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   private int minCount() {
      return this.minCount;
   }

   private void minCount_$eq(final int x$1) {
      this.minCount = x$1;
   }

   private int maxSentenceLength() {
      return this.maxSentenceLength;
   }

   private void maxSentenceLength_$eq(final int x$1) {
      this.maxSentenceLength = x$1;
   }

   public Word2Vec setMaxSentenceLength(final int maxSentenceLength) {
      .MODULE$.require(maxSentenceLength > 0, () -> "Maximum length of sentences must be positive but got " + maxSentenceLength);
      this.maxSentenceLength_$eq(maxSentenceLength);
      return this;
   }

   public Word2Vec setVectorSize(final int vectorSize) {
      .MODULE$.require(vectorSize > 0, () -> "vector size must be positive but got " + vectorSize);
      this.vectorSize_$eq(vectorSize);
      return this;
   }

   public Word2Vec setLearningRate(final double learningRate) {
      .MODULE$.require(learningRate > (double)0, () -> "Initial learning rate must be positive but got " + learningRate);
      this.learningRate_$eq(learningRate);
      return this;
   }

   public Word2Vec setNumPartitions(final int numPartitions) {
      .MODULE$.require(numPartitions > 0, () -> "Number of partitions must be positive but got " + numPartitions);
      this.numPartitions_$eq(numPartitions);
      return this;
   }

   public Word2Vec setNumIterations(final int numIterations) {
      .MODULE$.require(numIterations >= 0, () -> "Number of iterations must be nonnegative but got " + numIterations);
      this.numIterations_$eq(numIterations);
      return this;
   }

   public Word2Vec setSeed(final long seed) {
      this.seed_$eq(seed);
      return this;
   }

   public Word2Vec setWindowSize(final int window) {
      .MODULE$.require(window > 0, () -> "Window of words must be positive but got " + window);
      this.window_$eq(window);
      return this;
   }

   public Word2Vec setMinCount(final int minCount) {
      .MODULE$.require(minCount >= 0, () -> "Minimum number of times must be nonnegative but got " + minCount);
      this.minCount_$eq(minCount);
      return this;
   }

   private int EXP_TABLE_SIZE() {
      return this.EXP_TABLE_SIZE;
   }

   private int MAX_EXP() {
      return this.MAX_EXP;
   }

   private int MAX_CODE_LENGTH() {
      return this.MAX_CODE_LENGTH;
   }

   private int window() {
      return this.window;
   }

   private void window_$eq(final int x$1) {
      this.window = x$1;
   }

   private long trainWordsCount() {
      return this.trainWordsCount;
   }

   private void trainWordsCount_$eq(final long x$1) {
      this.trainWordsCount = x$1;
   }

   private int vocabSize() {
      return this.vocabSize;
   }

   private void vocabSize_$eq(final int x$1) {
      this.vocabSize = x$1;
   }

   private VocabWord[] vocab() {
      return this.vocab;
   }

   private void vocab_$eq(final VocabWord[] x$1) {
      this.vocab = x$1;
   }

   private HashMap vocabHash() {
      return this.vocabHash;
   }

   private void learnVocab(final RDD dataset) {
      RDD words = dataset.flatMap((x) -> x, scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.vocab_$eq((VocabWord[])scala.collection.ArrayOps..MODULE$.sortBy$extension(.MODULE$.refArrayOps(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(words.map((w) -> new Tuple2(w, BoxesRunTime.boxToInteger(1)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.Int(), scala.math.Ordering.String..MODULE$).reduceByKey((JFunction2.mcIII.sp)(x$1, x$2) -> x$1 + x$2).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$learnVocab$4(this, x$3))).map((x) -> new VocabWord((String)x._1(), (long)x._2$mcI$sp(), new int[this.MAX_CODE_LENGTH()], new int[this.MAX_CODE_LENGTH()], 0), scala.reflect.ClassTag..MODULE$.apply(VocabWord.class)).collect()), (x$4) -> BoxesRunTime.boxToLong($anonfun$learnVocab$6(x$4)), scala.package..MODULE$.Ordering().apply(scala.math.Ordering.Long..MODULE$).reverse()));
      this.vocabSize_$eq(this.vocab().length);
      .MODULE$.require(this.vocabSize() > 0, () -> "The vocabulary size should be > 0. You may need to check the setting of minCount, which could be large enough to remove all your words in sentences.");

      for(int a = 0; a < this.vocabSize(); ++a) {
         this.vocabHash().$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.vocab()[a].word()), BoxesRunTime.boxToInteger(a)));
         this.trainWordsCount_$eq(this.trainWordsCount() + this.vocab()[a].cn());
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"vocabSize = ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VOCAB_SIZE..MODULE$, BoxesRunTime.boxToInteger(this.vocabSize()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" trainWordsCount = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TRAIN_WORD..MODULE$, BoxesRunTime.boxToLong(this.trainWordsCount()))}))))));
   }

   private float[] createExpTable() {
      float[] expTable = new float[this.EXP_TABLE_SIZE()];

      for(int i = 0; i < this.EXP_TABLE_SIZE(); ++i) {
         double tmp = scala.math.package..MODULE$.exp(((double)2.0F * (double)i / (double)this.EXP_TABLE_SIZE() - (double)1.0F) * (double)this.MAX_EXP());
         expTable[i] = (float)(tmp / (tmp + (double)1.0F));
      }

      return expTable;
   }

   private void createBinaryTree() {
      long[] count = new long[this.vocabSize() * 2 + 1];
      int[] binary = new int[this.vocabSize() * 2 + 1];
      int[] parentNode = new int[this.vocabSize() * 2 + 1];
      int[] code = new int[this.MAX_CODE_LENGTH()];
      int[] point = new int[this.MAX_CODE_LENGTH()];

      int a;
      for(a = 0; a < this.vocabSize(); ++a) {
         count[a] = this.vocab()[a].cn();
      }

      while(a < 2 * this.vocabSize()) {
         count[a] = Long.MAX_VALUE;
         ++a;
      }

      int pos1 = this.vocabSize() - 1;
      int pos2 = this.vocabSize();
      int min1i = 0;
      int min2i = 0;

      for(int var13 = 0; var13 < this.vocabSize() - 1; ++var13) {
         if (pos1 >= 0) {
            if (count[pos1] < count[pos2]) {
               min1i = pos1--;
            } else {
               min1i = pos2++;
            }
         } else {
            min1i = pos2++;
         }

         if (pos1 >= 0) {
            if (count[pos1] < count[pos2]) {
               min2i = pos1--;
            } else {
               min2i = pos2++;
            }
         } else {
            min2i = pos2++;
         }

         .MODULE$.assert(count[min1i] < Long.MAX_VALUE);
         .MODULE$.assert(count[min2i] < Long.MAX_VALUE);
         count[this.vocabSize() + var13] = count[min1i] + count[min2i];
         parentNode[min1i] = this.vocabSize() + var13;
         parentNode[min2i] = this.vocabSize() + var13;
         binary[min2i] = 1;
      }

      int i = 0;

      for(int var14 = 0; var14 < this.vocabSize(); ++var14) {
         int b = var14;

         for(i = 0; b != this.vocabSize() * 2 - 2; b = parentNode[b]) {
            code[i] = binary[b];
            point[i] = b;
            ++i;
         }

         this.vocab()[var14].codeLen_$eq(i);
         this.vocab()[var14].point()[0] = this.vocabSize() - 2;

         for(int var18 = 0; var18 < i; ++var18) {
            this.vocab()[var14].code()[i - var18 - 1] = code[var18];
            this.vocab()[var14].point()[i - var18] = point[var18] - this.vocabSize();
         }
      }

   }

   public Word2VecModel fit(final RDD dataset) {
      this.learnVocab(dataset);
      this.createBinaryTree();
      SparkContext sc = dataset.context();
      Broadcast expTable = sc.broadcast(this.createExpTable(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE)));
      Broadcast bcVocab = sc.broadcast(this.vocab(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(VocabWord.class)));
      Broadcast bcVocabHash = sc.broadcast(this.vocabHash(), scala.reflect.ClassTag..MODULE$.apply(HashMap.class));

      Word2VecModel var10000;
      try {
         var10000 = this.doFit(dataset, sc, expTable, bcVocab, bcVocabHash);
      } finally {
         expTable.destroy();
         bcVocab.destroy();
         bcVocabHash.destroy();
      }

      return var10000;
   }

   private Word2VecModel doFit(final RDD dataset, final SparkContext sc, final Broadcast expTable, final Broadcast bcVocab, final Broadcast bcVocabHash) {
      RDD sentences = dataset.mapPartitions((sentenceIter) -> sentenceIter.flatMap((sentence) -> {
            HashMap var4 = (HashMap)bcVocabHash.value();
            Iterable wordIndexes = (Iterable)sentence.flatMap((key) -> var4.get(key));
            return wordIndexes.grouped(this.maxSentenceLength()).map((x$5) -> (int[])x$5.toArray(scala.reflect.ClassTag..MODULE$.Int()));
         }), dataset.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)));
      int x$1 = this.numPartitions();
      Ordering x$2 = sentences.repartition$default$2(x$1);
      RDD newSentences = sentences.repartition(x$1, x$2).cache();
      XORShiftRandom initRandom = new XORShiftRandom(this.seed());
      if ((long)this.vocabSize() * (long)this.vectorSize() >= 2147483647L) {
         int var10002 = this.vocabSize();
         throw new RuntimeException("Please increase minCount or decrease vectorSize in Word2Vec to avoid an OOM. You are highly recommended to make your vocabSize*vectorSize, which is " + var10002 + "*" + this.vectorSize() + " for now, less than `Int.MaxValue`.");
      } else {
         float[] syn0Global = (float[])scala.Array..MODULE$.fill(this.vocabSize() * this.vectorSize(), (JFunction0.mcF.sp)() -> (initRandom.nextFloat() - 0.5F) / (float)this.vectorSize(), scala.reflect.ClassTag..MODULE$.Float());
         float[] syn1Global = new float[this.vocabSize() * this.vectorSize()];
         long totalWordsCounts = (long)this.numIterations() * this.trainWordsCount() + 1L;
         DoubleRef alpha = DoubleRef.create(this.learningRate());
         scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(1), this.numIterations()).foreach$mVc$sp((JFunction1.mcVI.sp)(k) -> {
            Broadcast bcSyn0Global = sc.broadcast(syn0Global, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE)));
            Broadcast bcSyn1Global = sc.broadcast(syn1Global, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Float.TYPE)));
            long numWordsProcessedInPreviousIterations = (long)(k - 1) * this.trainWordsCount();
            RDD partial = newSentences.mapPartitionsWithIndex((x0$1, x1$1) -> $anonfun$doFit$7(this, k, bcSyn0Global, bcSyn1Global, alpha, numWordsProcessedInPreviousIterations, totalWordsCounts, bcVocab, expTable, BoxesRunTime.unboxToInt(x0$1), x1$1), newSentences.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            Tuple2[] synAgg = (Tuple2[])org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(partial.mapPartitions((iter) -> iter.map((x0$3) -> {
                  if (x0$3 != null) {
                     int id = x0$3._1$mcI$sp();
                     float[] vec = (float[])x0$3._2();
                     return new Tuple2(BoxesRunTime.boxToInteger(id), new Tuple2(vec, BoxesRunTime.boxToInteger(1)));
                  } else {
                     throw new MatchError(x0$3);
                  }
               }), partial.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).reduceByKey((vc1, vc2) -> {
               org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().saxpy(this.vectorSize(), 1.0F, (float[])vc2._1(), 1, (float[])vc1._1(), 1);
               return new Tuple2(vc1._1(), BoxesRunTime.boxToInteger(vc1._2$mcI$sp() + vc2._2$mcI$sp()));
            }).map((x0$4) -> {
               if (x0$4 != null) {
                  int id = x0$4._1$mcI$sp();
                  Tuple2 var5 = (Tuple2)x0$4._2();
                  if (var5 != null) {
                     float[] vec = (float[])var5._1();
                     int count = var5._2$mcI$sp();
                     org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().sscal(this.vectorSize(), 1.0F / (float)count, vec, 1);
                     return new Tuple2(BoxesRunTime.boxToInteger(id), vec);
                  }
               }

               throw new MatchError(x0$4);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).collect();

            for(int i = 0; i < synAgg.length; ++i) {
               int index = synAgg[i]._1$mcI$sp();
               if (index < this.vocabSize()) {
                  scala.Array..MODULE$.copy(synAgg[i]._2(), 0, syn0Global, index * this.vectorSize(), this.vectorSize());
               } else {
                  scala.Array..MODULE$.copy(synAgg[i]._2(), 0, syn1Global, (index - this.vocabSize()) * this.vectorSize(), this.vectorSize());
               }
            }

            bcSyn0Global.destroy();
            bcSyn1Global.destroy();
         });
         newSentences.unpersist(newSentences.unpersist$default$1());
         String[] wordArray = (String[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.vocab()), (x$6) -> x$6.word(), scala.reflect.ClassTag..MODULE$.apply(String.class));
         return new Word2VecModel(org.apache.spark.util.collection.Utils..MODULE$.toMapWithIndex(.MODULE$.wrapRefArray((Object[])wordArray)), syn0Global);
      }
   }

   public Word2VecModel fit(final JavaRDD dataset) {
      return this.fit(dataset.rdd().map((x$7) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(x$7).asScala(), scala.reflect.ClassTag..MODULE$.apply(Iterable.class)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$learnVocab$4(final Word2Vec $this, final Tuple2 x$3) {
      return x$3._2$mcI$sp() >= $this.minCount();
   }

   // $FF: synthetic method
   public static final long $anonfun$learnVocab$6(final VocabWord x$4) {
      return x$4.cn();
   }

   // $FF: synthetic method
   public static final Option $anonfun$doFit$10(final Word2Vec $this, final int[] syn0Modify$1, final float[] syn0Local$1, final int index) {
      return (Option)(syn0Modify$1[index] > 0 ? new Some(new Tuple2(BoxesRunTime.boxToInteger(index), scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.floatArrayOps(syn0Local$1), index * $this.vectorSize(), (index + 1) * $this.vectorSize()))) : scala.None..MODULE$);
   }

   // $FF: synthetic method
   public static final Option $anonfun$doFit$12(final Word2Vec $this, final int[] syn1Modify$1, final float[] syn1Local$1, final int index) {
      return (Option)(syn1Modify$1[index] > 0 ? new Some(new Tuple2(BoxesRunTime.boxToInteger(index + $this.vocabSize()), scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.floatArrayOps(syn1Local$1), index * $this.vectorSize(), (index + 1) * $this.vectorSize()))) : scala.None..MODULE$);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$doFit$7(final Word2Vec $this, final int k$1, final Broadcast bcSyn0Global$1, final Broadcast bcSyn1Global$1, final DoubleRef alpha$1, final long numWordsProcessedInPreviousIterations$1, final long totalWordsCounts$1, final Broadcast bcVocab$1, final Broadcast expTable$1, final int x0$1, final Iterator x1$1) {
      Tuple2 var14 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var14 != null) {
         int idx = var14._1$mcI$sp();
         Iterator iter = (Iterator)var14._2();
         XORShiftRandom random = new XORShiftRandom($this.seed() ^ (long)(idx + 1 << 16) ^ (long)(-k$1 - 1 << 8));
         int[] syn0Modify = new int[$this.vocabSize()];
         int[] syn1Modify = new int[$this.vocabSize()];
         Tuple4 model = (Tuple4)iter.foldLeft(new Tuple4(bcSyn0Global$1.value(), bcSyn1Global$1.value(), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L)), (x0$2, x1$2) -> {
            Tuple2 var14 = new Tuple2(x0$2, x1$2);
            if (var14 != null) {
               Tuple4 var15 = (Tuple4)var14._1();
               int[] sentence = (int[])var14._2();
               if (var15 != null) {
                  float[] syn0 = (float[])var15._1();
                  float[] syn1 = (float[])var15._2();
                  long lastWordCount = BoxesRunTime.unboxToLong(var15._3());
                  long wordCount = BoxesRunTime.unboxToLong(var15._4());
                  long lwc = lastWordCount;
                  if (wordCount - lastWordCount > 10000L) {
                     lwc = wordCount;
                     alpha$1.elem = $this.learningRate() * ((double)1 - ((double)$this.numPartitions() * (double)wordCount + (double)numWordsProcessedInPreviousIterations$1) / (double)totalWordsCounts$1);
                     if (alpha$1.elem < $this.learningRate() * 1.0E-4) {
                        alpha$1.elem = $this.learningRate() * 1.0E-4;
                     }

                     $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"wordCount ="})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToLong(wordCount + numWordsProcessedInPreviousIterations$1))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" alpha = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ALPHA..MODULE$, BoxesRunTime.boxToDouble(alpha$1.elem))}))))));
                  }

                  long wc = wordCount + (long)sentence.length;

                  for(int pos = 0; pos < sentence.length; ++pos) {
                     int word = sentence[pos];
                     int b = random.nextInt($this.window());

                     for(int a = b; a < $this.window() * 2 + 1 - b; ++a) {
                        if (a != $this.window()) {
                           int c = pos - $this.window() + a;
                           if (c >= 0 && c < sentence.length) {
                              int lastWord = sentence[c];
                              int l1 = lastWord * $this.vectorSize();
                              float[] neu1e = new float[$this.vectorSize()];

                              for(int d = 0; d < ((VocabWord[])bcVocab$1.value())[word].codeLen(); ++d) {
                                 int inner = ((VocabWord[])bcVocab$1.value())[word].point()[d];
                                 int l2 = inner * $this.vectorSize();
                                 float f = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().sdot($this.vectorSize(), syn0, l1, 1, syn1, l2, 1);
                                 if (f > (float)(-$this.MAX_EXP()) && f < (float)$this.MAX_EXP()) {
                                    int ind = (int)((double)(f + (float)$this.MAX_EXP()) * ((double)($this.EXP_TABLE_SIZE() / $this.MAX_EXP()) / (double)2.0F));
                                    f = ((float[])expTable$1.value())[ind];
                                    float g = (float)((double)((float)(1 - ((VocabWord[])bcVocab$1.value())[word].code()[d]) - f) * alpha$1.elem);
                                    org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().saxpy($this.vectorSize(), g, syn1, l2, 1, neu1e, 0, 1);
                                    org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().saxpy($this.vectorSize(), g, syn0, l1, 1, syn1, l2, 1);
                                    int var10002 = syn1Modify[inner]++;
                                 }
                              }

                              org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().saxpy($this.vectorSize(), 1.0F, neu1e, 0, 1, syn0, l1, 1);
                              int var42 = syn0Modify[lastWord]++;
                           }
                        }
                     }
                  }

                  return new Tuple4(syn0, syn1, BoxesRunTime.boxToLong(lwc), BoxesRunTime.boxToLong(wc));
               }
            }

            throw new MatchError(var14);
         });
         float[] syn0Local = (float[])model._1();
         float[] syn1Local = (float[])model._2();
         return scala.package..MODULE$.Iterator().tabulate($this.vocabSize(), (index) -> $anonfun$doFit$10($this, syn0Modify, syn0Local, BoxesRunTime.unboxToInt(index))).flatten(.MODULE$.$conforms()).$plus$plus(() -> scala.package..MODULE$.Iterator().tabulate($this.vocabSize(), (index) -> $anonfun$doFit$12($this, syn1Modify, syn1Local, BoxesRunTime.unboxToInt(index))).flatten(.MODULE$.$conforms()));
      } else {
         throw new MatchError(var14);
      }
   }

   public Word2Vec() {
      Logging.$init$(this);
      this.vectorSize = 100;
      this.learningRate = 0.025;
      this.numPartitions = 1;
      this.numIterations = 1;
      this.seed = org.apache.spark.util.Utils..MODULE$.random().nextLong();
      this.minCount = 5;
      this.maxSentenceLength = 1000;
      this.EXP_TABLE_SIZE = 1000;
      this.MAX_EXP = 6;
      this.MAX_CODE_LENGTH = 40;
      this.window = 5;
      this.trainWordsCount = 0L;
      this.vocabSize = 0;
      this.vocab = null;
      this.vocabHash = scala.collection.mutable.HashMap..MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
