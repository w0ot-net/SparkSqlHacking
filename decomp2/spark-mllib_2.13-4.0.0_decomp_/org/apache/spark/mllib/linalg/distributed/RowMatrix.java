package org.apache.spark.mllib.linalg.distributed;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.MatrixSingularException;
import breeze.linalg.NumericOps;
import breeze.linalg.SparseVector;
import breeze.linalg.qr;
import breeze.linalg.svd;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.EigenValueDecomposition$;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.QRDecomposition;
import org.apache.spark.mllib.linalg.SingularValueDecomposition;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.stat.MultivariateOnlineSummarizer;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.random.XORShiftRandom;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LazyRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001de\u0001B\u0015+\u0001]B\u0001\u0002\u0013\u0001\u0003\u0006\u0004%\t!\u0013\u0005\t;\u0002\u0011\t\u0011)A\u0005\u0015\"Aq\f\u0001BA\u0002\u0013%\u0001\r\u0003\u0005e\u0001\t\u0005\r\u0011\"\u0003f\u0011!Y\u0007A!A!B\u0013\t\u0007\u0002\u00037\u0001\u0005\u0003\u0007I\u0011B7\t\u0011E\u0004!\u00111A\u0005\nID\u0001\u0002\u001e\u0001\u0003\u0002\u0003\u0006KA\u001c\u0005\u0006k\u0002!\tA\u001e\u0005\u0006k\u0002!\t! \u0005\b\u0003\u0003\u0001A\u0011IA\u0002\u0011\u001d\t9\u0001\u0001C!\u0003\u0007A\u0001\"a\u0003\u0001\t\u0003q\u0013Q\u0002\u0005\b\u0003O\u0001A\u0011AA\u0015\u0011\u001d\t\u0019\u0004\u0001C\u0005\u0003kAq!a\u0011\u0001\t\u0013\t)\u0005C\u0004\u0002N\u0001!I!a\u0014\t\u000f\u0005U\u0003\u0001\"\u0001\u0002X!I\u00111\u000f\u0001\u0012\u0002\u0013\u0005\u0011Q\u000f\u0005\n\u0003\u0013\u0003\u0011\u0013!C\u0001\u0003\u0017C\u0001\"!\u0016\u0001\t\u0003q\u0013q\u0012\u0005\b\u0003s\u0003A\u0011BA^\u0011\u001d\ti\f\u0001C\u0001\u0003SAq!!1\u0001\t\u0003\t\u0019\rC\u0004\u0002T\u0002!\t!!6\t\u000f\u0005m\u0007\u0001\"\u0001\u0002^\"9\u0011Q\u001e\u0001\u0005\u0002\u0005=\bbBA|\u0001\u0011\u0005\u0011\u0011 \u0005\b\u0003o\u0004A\u0011\u0001B\u0004\u0011\u001d\u0011y\u0001\u0001C\u0001\u0005#A\u0011Ba\t\u0001#\u0003%\t!!\u001e\t\u0011\t\u0015\u0002\u0001\"\u0001/\u0005OA\u0001Ba\u000e\u0001\t\u0003r#\u0011\b\u0005\b\u0005\u0003\u0002A\u0011\u0002B\"\u0011!\u00119\u0005\u0001C\u0001a\t%sa\u0002B)U!\u0005!1\u000b\u0004\u0007S)B\tA!\u0016\t\rU,C\u0011\u0001B4\u0011\u001d\u0011I'\nC\u0005\u0005WB\u0011Ba\u001d&\u0003\u0003%IA!\u001e\u0003\u0013I{w/T1ue&D(BA\u0016-\u0003-!\u0017n\u001d;sS\n,H/\u001a3\u000b\u00055r\u0013A\u00027j]\u0006dwM\u0003\u00020a\u0005)Q\u000e\u001c7jE*\u0011\u0011GM\u0001\u0006gB\f'o\u001b\u0006\u0003gQ\na!\u00199bG\",'\"A\u001b\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001AdH\u0011\t\u0003sqj\u0011A\u000f\u0006\u0002w\u0005)1oY1mC&\u0011QH\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005}\u0002U\"\u0001\u0016\n\u0005\u0005S#!\u0005#jgR\u0014\u0018NY;uK\u0012l\u0015\r\u001e:jqB\u00111IR\u0007\u0002\t*\u0011Q\tM\u0001\tS:$XM\u001d8bY&\u0011q\t\u0012\u0002\b\u0019><w-\u001b8h\u0003\u0011\u0011xn^:\u0016\u0003)\u00032a\u0013(Q\u001b\u0005a%BA'1\u0003\r\u0011H\rZ\u0005\u0003\u001f2\u00131A\u0015#E!\t\t&+D\u0001-\u0013\t\u0019FF\u0001\u0004WK\u000e$xN\u001d\u0015\u0004\u0003U[\u0006C\u0001,Z\u001b\u00059&B\u0001-1\u0003)\tgN\\8uCRLwN\\\u0005\u00035^\u0013QaU5oG\u0016\f\u0013\u0001X\u0001\u0006c9\u0002d\u0006M\u0001\u0006e><8\u000f\t\u0015\u0004\u0005U[\u0016!\u00028S_^\u001cX#A1\u0011\u0005e\u0012\u0017BA2;\u0005\u0011auN\\4\u0002\u00139\u0014vn^:`I\u0015\fHC\u00014j!\tIt-\u0003\u0002iu\t!QK\\5u\u0011\u001dQG!!AA\u0002\u0005\f1\u0001\u001f\u00132\u0003\u0019q'k\\<tA\u0005)anQ8mgV\ta\u000e\u0005\u0002:_&\u0011\u0001O\u000f\u0002\u0004\u0013:$\u0018!\u00038D_2\u001cx\fJ3r)\t17\u000fC\u0004k\u000f\u0005\u0005\t\u0019\u00018\u0002\r9\u001cu\u000e\\:!\u0003\u0019a\u0014N\\5u}Q!q\u000f\u001f>|!\ty\u0004\u0001C\u0003I\u0013\u0001\u0007!\nK\u0002y+nCQaX\u0005A\u0002\u0005DQ\u0001\\\u0005A\u00029D3!C+\\)\t9h\u0010C\u0003I\u0015\u0001\u0007!\nK\u0002\u000b+n\u000bqA\\;n\u0007>d7\u000fF\u0001bQ\rYQkW\u0001\b]Vl'k\\<tQ\raQkW\u0001\u0018[VdG/\u001b9ms\u001e\u0013\u0018-\\5b]6\u000bGO]5y\u0005f$B!a\u0004\u0002$A1\u0011\u0011CA\r\u0003;i!!a\u0005\u000b\u00075\n)B\u0003\u0002\u0002\u0018\u00051!M]3fu\u0016LA!a\u0007\u0002\u0014\tYA)\u001a8tKZ+7\r^8s!\rI\u0014qD\u0005\u0004\u0003CQ$A\u0002#pk\ndW\rC\u0004\u0002&5\u0001\r!a\u0004\u0002\u0003Y\fAcY8naV$Xm\u0012:b[&\fg.T1ue&DHCAA\u0016!\r\t\u0016QF\u0005\u0004\u0003_a#AB'biJL\u0007\u0010K\u0002\u000f+n\u000bAdY8naV$X\rR3og\u00164Vm\u0019;pe\u000e{g/\u0019:jC:\u001cW\r\u0006\u0005\u0002,\u0005]\u00121HA \u0011\u0019\tId\u0004a\u0001!\u0006!Q.Z1o\u0011\u0019\tid\u0004a\u0001]\u0006\ta\u000e\u0003\u0004\u0002B=\u0001\r!Y\u0001\u0002[\u0006i2m\\7qkR,7\u000b]1sg\u00164Vm\u0019;pe\u000e{g/\u0019:jC:\u001cW\r\u0006\u0005\u0002,\u0005\u001d\u0013\u0011JA&\u0011\u0019\tI\u0004\u0005a\u0001!\"1\u0011Q\b\tA\u00029Da!!\u0011\u0011\u0001\u0004\t\u0017aD2iK\u000e\\g*^7D_2,XN\\:\u0015\u0007\u0019\f\t\u0006\u0003\u0004\u0002TE\u0001\rA\\\u0001\u0005G>d7/\u0001\u0006d_6\u0004X\u000f^3T-\u0012#\u0002\"!\u0017\u0002`\u0005\r\u0014Q\u000e\t\u0007#\u0006ms/a\u000b\n\u0007\u0005uCF\u0001\u000eTS:<W\u000f\\1s-\u0006dW/\u001a#fG>l\u0007o\\:ji&|g\u000e\u0003\u0004\u0002bI\u0001\rA\\\u0001\u0002W\"I\u0011Q\r\n\u0011\u0002\u0003\u0007\u0011qM\u0001\tG>l\u0007/\u001e;f+B\u0019\u0011(!\u001b\n\u0007\u0005-$HA\u0004C_>dW-\u00198\t\u0013\u0005=$\u0003%AA\u0002\u0005u\u0011!\u0002:D_:$\u0007f\u0001\nV7\u0006!2m\\7qkR,7K\u0016#%I\u00164\u0017-\u001e7uII*\"!a\u001e+\t\u0005\u001d\u0014\u0011P\u0016\u0003\u0003w\u0002B!! \u0002\u00066\u0011\u0011q\u0010\u0006\u0005\u0003\u0003\u000b\u0019)A\u0005v]\u000eDWmY6fI*\u0011\u0001LO\u0005\u0005\u0003\u000f\u000byHA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAcY8naV$Xm\u0015,EI\u0011,g-Y;mi\u0012\u001aTCAAGU\u0011\ti\"!\u001f\u0015\u001d\u0005e\u0013\u0011SAJ\u0003+\u000b9*a'\u0002 \"1\u0011\u0011M\u000bA\u00029Dq!!\u001a\u0016\u0001\u0004\t9\u0007C\u0004\u0002pU\u0001\r!!\b\t\r\u0005eU\u00031\u0001o\u0003\u001di\u0017\r_%uKJDq!!(\u0016\u0001\u0004\ti\"A\u0002u_2Dq!!)\u0016\u0001\u0004\t\u0019+\u0001\u0003n_\u0012,\u0007\u0003BAS\u0003gsA!a*\u00020B\u0019\u0011\u0011\u0016\u001e\u000e\u0005\u0005-&bAAWm\u00051AH]8pizJ1!!-;\u0003\u0019\u0001&/\u001a3fM&!\u0011QWA\\\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011\u0017\u001e\u0002\u001d%\u001c8\u000b]1sg\u0016l\u0015\r\u001e:jqV\u0011\u0011qM\u0001\u0012G>l\u0007/\u001e;f\u0007>4\u0018M]5b]\u000e,\u0007fA\fV7\u0006q3m\\7qkR,\u0007K]5oG&\u0004\u0018\r\\\"p[B|g.\u001a8ug\u0006sG-\u0012=qY\u0006Lg.\u001a3WCJL\u0017M\\2f)\u0011\t)-a3\u0011\re\n9-a\u000bQ\u0013\r\tIM\u000f\u0002\u0007)V\u0004H.\u001a\u001a\t\r\u0005\u0005\u0004\u00041\u0001oQ\u0011AR+a4\"\u0005\u0005E\u0017!B\u0019/m9\u0002\u0014AG2p[B,H/\u001a)sS:\u001c\u0017\u000e]1m\u0007>l\u0007o\u001c8f]R\u001cH\u0003BA\u0016\u0003/Da!!\u0019\u001a\u0001\u0004q\u0007fA\rV7\u0006q2m\\7qkR,7i\u001c7v[:\u001cV/\\7bef\u001cF/\u0019;jgRL7m\u001d\u000b\u0003\u0003?\u0004B!!9\u0002h6\u0011\u00111\u001d\u0006\u0004\u0003Kt\u0013\u0001B:uCRLA!!;\u0002d\nqR*\u001e7uSZ\f'/[1uKN#\u0018\r^5ti&\u001c\u0017\r\\*v[6\f'/\u001f\u0015\u00045U[\u0016\u0001C7vYRL\u0007\u000f\\=\u0015\u0007]\f\t\u0010C\u0004\u0002tn\u0001\r!a\u000b\u0002\u0003\tC3aG+\\\u0003I\u0019w\u000e\\;n]NKW.\u001b7be&$\u0018.Z:\u0015\u0005\u0005m\bcA \u0002~&\u0019\u0011q \u0016\u0003!\r{wN\u001d3j]\u0006$X-T1ue&D\b\u0006\u0002\u000fV\u0005\u0007\t#A!\u0002\u0002\u000bEr#G\f\u0019\u0015\t\u0005m(\u0011\u0002\u0005\b\u0005\u0017i\u0002\u0019AA\u000f\u0003%!\bN]3tQ>dG\r\u000b\u0003\u001e+\n\r\u0011\u0001\u0004;bY2\u001c6.\u001b8osF\u0013F\u0003\u0002B\n\u00053\u0001b!\u0015B\u000bo\u0006-\u0012b\u0001B\fY\ty\u0011K\u0015#fG>l\u0007o\\:ji&|g\u000eC\u0005\u0003\u001cy\u0001\n\u00111\u0001\u0002h\u0005A1m\\7qkR,\u0017\u000b\u000b\u0003\u001f+\n}\u0011E\u0001B\u0011\u0003\u0015\td&\u000e\u00181\u0003Y!\u0018\r\u001c7TW&tg._)SI\u0011,g-Y;mi\u0012\n\u0014\u0001G2pYVlgnU5nS2\f'/\u001b;jKN$\u0015*T*V\u001bR1\u00111 B\u0015\u0005gAqAa\u000b!\u0001\u0004\u0011i#A\u0004d_2l\u0015mZ:\u0011\u000be\u0012y#!\b\n\u0007\tE\"HA\u0003BeJ\f\u0017\u0010C\u0004\u00036\u0001\u0002\r!!\b\u0002\u000b\u001d\fW.\\1\u0002\u0011Q|'I]3fu\u0016$\"Aa\u000f\u0011\r\u0005E!QHA\u000f\u0013\u0011\u0011y$a\u0005\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e_\u0001\u000ekB$\u0017\r^3Ok6\u0014vn^:\u0015\u0007\u0019\u0014)\u0005\u0003\u0004\u0002B\t\u0002\r!Y\u0001\u001bO\u0016$HK]3f\u0003\u001e<'/Z4bi\u0016LE-Z1m\t\u0016\u0004H\u000f\u001b\u000b\u0004]\n-\u0003B\u0002B'G\u0001\u0007\u0011-A\u000ebO\u001e\u0014XmZ1uK\u0012|%M[3diNK'0Z%o\u0005f$Xm\u001d\u0015\u0004\u0001U[\u0016!\u0003*po6\u000bGO]5y!\tyTe\u0005\u0003&q\t]\u0003\u0003\u0002B-\u0005Gj!Aa\u0017\u000b\t\tu#qL\u0001\u0003S>T!A!\u0019\u0002\t)\fg/Y\u0005\u0005\u0005K\u0012YF\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0003T\u0005QAO]5v)>4U\u000f\u001c7\u0015\r\u0005-\"Q\u000eB8\u0011\u0019\tid\na\u0001]\"9!\u0011O\u0014A\u0002\t5\u0012!A+\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t]\u0004\u0003\u0002B=\u0005\u007fj!Aa\u001f\u000b\t\tu$qL\u0001\u0005Y\u0006tw-\u0003\u0003\u0003\u0002\nm$AB(cU\u0016\u001cG\u000fK\u0002&+nC3\u0001J+\\\u0001"
)
public class RowMatrix implements DistributedMatrix, Logging {
   private final RDD rows;
   private long nRows;
   private int nCols;
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

   public RDD rows() {
      return this.rows;
   }

   private long nRows() {
      return this.nRows;
   }

   private void nRows_$eq(final long x$1) {
      this.nRows = x$1;
   }

   private int nCols() {
      return this.nCols;
   }

   private void nCols_$eq(final int x$1) {
      this.nCols = x$1;
   }

   public long numCols() {
      if (this.nCols() <= 0) {
         try {
            this.nCols_$eq(((Vector)this.rows().first()).size());
         } catch (UnsupportedOperationException var2) {
            throw .MODULE$.error("Cannot determine the number of cols because it is not specified in the constructor and the rows RDD is empty.");
         }
      }

      return (long)this.nCols();
   }

   public long numRows() {
      if (this.nRows() <= 0L) {
         this.nRows_$eq(this.rows().count());
         if (this.nRows() == 0L) {
            throw .MODULE$.error("Cannot determine the number of rows because it is not specified in the constructor and the rows RDD is empty.");
         }
      }

      return this.nRows();
   }

   public DenseVector multiplyGramianMatrixBy(final DenseVector v) {
      int n = (int)this.numCols();
      Broadcast vbr = this.rows().context().broadcast(v, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      DenseVector x$1 = null;
      Function2 x$2 = (U, r) -> {
         breeze.linalg.Vector rBrz = r.asBreeze();
         double a = BoxesRunTime.unboxToDouble(rBrz.dot(vbr.value(), breeze.linalg.operators.HasOps..MODULE$.castOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), breeze.linalg.operators.HasOps..MODULE$.impl_OpMulInner_V_V_eq_S_Double())));
         DenseVector theU = U == null ? breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(n, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()) : U;
         if (rBrz instanceof DenseVector) {
            breeze.linalg.package..MODULE$.axpy(BoxesRunTime.boxToDouble(a), (DenseVector)rBrz, theU, breeze.linalg.operators.HasOps..MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!(rBrz instanceof SparseVector)) {
               throw new UnsupportedOperationException("Do not support vector operation from type " + rBrz.getClass().getName() + ".");
            }

            breeze.linalg.package..MODULE$.axpy(BoxesRunTime.boxToDouble(a), (SparseVector)rBrz, theU, breeze.linalg.operators.HasOps..MODULE$.implScaleAdd_DV_S_SV_InPlace_Double());
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

         return theU;
      };
      Function2 x$3 = (U1, U2) -> {
         if (U1 == null) {
            return U2;
         } else if (U2 == null) {
            return U1;
         } else {
            U1.$plus$eq(U2, breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            return U1;
         }
      };
      int x$4 = this.rows().treeAggregate$default$4(x$1);
      return (DenseVector)this.rows().treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
   }

   public Matrix computeGramianMatrix() {
      int n = (int)this.numCols();
      this.checkNumColumns(n);
      int nt = n % 2 == 0 ? n / 2 * (n + 1) : n * ((n + 1) / 2);
      long gramianSizeInBytes = (long)nt * 8L;
      DenseVector GU = (DenseVector)this.rows().treeAggregate((Object)null, (maybeU, v) -> {
         DenseVector U = (DenseVector)(maybeU == null ? new DenseVector.mcD.sp(nt, scala.reflect.ClassTag..MODULE$.Double()) : maybeU);
         BLAS$.MODULE$.spr((double)1.0F, v, U.data$mcD$sp());
         return U;
      }, (U1, U2) -> {
         if (U1 == null) {
            return U2;
         } else {
            return U2 == null ? U1 : (DenseVector)U1.$plus$eq(U2, breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         }
      }, this.getTreeAggregateIdealDepth(gramianSizeInBytes), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      return RowMatrix$.MODULE$.org$apache$spark$mllib$linalg$distributed$RowMatrix$$triuToFull(n, GU.data$mcD$sp());
   }

   private Matrix computeDenseVectorCovariance(final Vector mean, final int n, final long m) {
      Broadcast bc = this.rows().context().broadcast(mean, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      int nt = n % 2 == 0 ? n / 2 * (n + 1) : n * ((n + 1) / 2);
      DenseVector x$1 = null;
      Function2 x$2 = (maybeU, v) -> {
         DenseVector U = (DenseVector)(maybeU == null ? new DenseVector.mcD.sp(nt, scala.reflect.ClassTag..MODULE$.Double()) : maybeU);
         int n = v.size();
         double[] na = (double[])scala.Array..MODULE$.ofDim(n, scala.reflect.ClassTag..MODULE$.Double());
         Vector means = (Vector)bc.value();
         double[] ta = v.toArray();
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n).foreach$mVc$sp((JFunction1.mcVI.sp)(index) -> na[index] = ta[index] - means.apply(index));
         BLAS$.MODULE$.spr((double)1.0F, new org.apache.spark.mllib.linalg.DenseVector(na), (double[])U.data$mcD$sp());
         return U;
      };
      Function2 x$3 = (U1, U2) -> {
         if (U1 == null) {
            return U2;
         } else {
            return U2 == null ? U1 : (DenseVector)U1.$plus$eq(U2, breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         }
      };
      int x$4 = this.rows().treeAggregate$default$4(x$1);
      DenseVector MU = (DenseVector)this.rows().treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      bc.destroy();
      breeze.linalg.Matrix M = RowMatrix$.MODULE$.org$apache$spark$mllib$linalg$distributed$RowMatrix$$triuToFull(n, MU.data$mcD$sp()).asBreeze();
      int i = 0;
      int j = 0;

      for(double m1 = (double)m - (double)1.0F; i < n; ++i) {
         for(int var19 = i; var19 < n; ++var19) {
            double Mij = M.apply$mcD$sp(i, var19) / m1;
            M.update$mcD$sp(i, var19, Mij);
            M.update$mcD$sp(var19, i, Mij);
         }
      }

      return Matrices$.MODULE$.fromBreeze(M);
   }

   private Matrix computeSparseVectorCovariance(final Vector mean, final int n, final long m) {
      breeze.linalg.Matrix G = this.computeGramianMatrix().asBreeze();
      int i = 0;
      int j = 0;
      double m1 = (double)m - (double)1.0F;

      for(double alpha = (double)0.0F; i < n; ++i) {
         alpha = (double)m / m1 * mean.apply(i);

         for(int var14 = i; var14 < n; ++var14) {
            double Gij = G.apply$mcD$sp(i, var14) / m1 - alpha * mean.apply(var14);
            G.update$mcD$sp(i, var14, Gij);
            G.update$mcD$sp(var14, i, Gij);
         }
      }

      return Matrices$.MODULE$.fromBreeze(G);
   }

   private void checkNumColumns(final int cols) {
      if (cols > 65535) {
         throw new IllegalArgumentException("Argument with more than 65535 cols: " + cols);
      } else if (cols > 10000) {
         long memMB = (long)cols * (long)cols / 125000L;
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " columns will require at least "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_COLUMNS..MODULE$, BoxesRunTime.boxToInteger(cols))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " megabytes of memory!"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, BoxesRunTime.boxToLong(memMB))}))))));
      }
   }

   public SingularValueDecomposition computeSVD(final int k, final boolean computeU, final double rCond) {
      int maxIter = scala.math.package..MODULE$.max(300, k * 3);
      double tol = 1.0E-10;
      return this.computeSVD(k, computeU, rCond, maxIter, tol, "auto");
   }

   public SingularValueDecomposition computeSVD(final int k, final boolean computeU, final double rCond, final int maxIter, final double tol, final String mode) {
      LazyRef SVDMode$module = new LazyRef();
      int n = (int)this.numCols();
      scala.Predef..MODULE$.require(k > 0 && k <= n, () -> "Requested k singular values but got k=" + k + " and numCols=" + n + ".");
      Enumeration.Value var10000;
      switch (mode == null ? 0 : mode.hashCode()) {
         case -1206360833:
            if (!"local-svd".equals(mode)) {
               throw new IllegalArgumentException("Do not support mode " + mode + ".");
            }

            var10000 = this.SVDMode$2(SVDMode$module).LocalLAPACK();
            break;
         case 3005871:
            if (!"auto".equals(mode)) {
               throw new IllegalArgumentException("Do not support mode " + mode + ".");
            }

            if (k > 5000) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"computing svd with k=", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_LEADING_SINGULAR_VALUES..MODULE$, BoxesRunTime.boxToInteger(k))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"n=", ", please check necessity"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_COLUMNS..MODULE$, BoxesRunTime.boxToInteger(n))}))))));
            }

            var10000 = n < 100 || k > n / 2 && n <= 15000 ? (k < n / 3 ? this.SVDMode$2(SVDMode$module).LocalARPACK() : this.SVDMode$2(SVDMode$module).LocalLAPACK()) : this.SVDMode$2(SVDMode$module).DistARPACK();
            break;
         case 304032855:
            if (!"dist-eigs".equals(mode)) {
               throw new IllegalArgumentException("Do not support mode " + mode + ".");
            }

            var10000 = this.SVDMode$2(SVDMode$module).DistARPACK();
            break;
         case 1257090482:
            if ("local-eigs".equals(mode)) {
               var10000 = this.SVDMode$2(SVDMode$module).LocalARPACK();
               break;
            }

            throw new IllegalArgumentException("Do not support mode " + mode + ".");
         default:
            throw new IllegalArgumentException("Do not support mode " + mode + ".");
      }

      Enumeration.Value computeMode;
      label225: {
         label226: {
            computeMode = var10000;
            var10000 = this.SVDMode$2(SVDMode$module).LocalARPACK();
            if (var10000 == null) {
               if (computeMode == null) {
                  break label226;
               }
            } else if (var10000.equals(computeMode)) {
               break label226;
            }

            label227: {
               var10000 = this.SVDMode$2(SVDMode$module).LocalLAPACK();
               if (var10000 == null) {
                  if (computeMode == null) {
                     break label227;
                  }
               } else if (var10000.equals(computeMode)) {
                  break label227;
               }

               var10000 = this.SVDMode$2(SVDMode$module).DistARPACK();
               if (var10000 == null) {
                  if (computeMode != null) {
                     throw new MatchError(computeMode);
                  }
               } else if (!var10000.equals(computeMode)) {
                  throw new MatchError(computeMode);
               }

               label158: {
                  StorageLevel var63 = this.rows().getStorageLevel();
                  StorageLevel var33 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
                  if (var63 == null) {
                     if (var33 != null) {
                        break label158;
                     }
                  } else if (!var63.equals(var33)) {
                     break label158;
                  }

                  this.logWarning((Function0)(() -> "The input data is not directly cached, which may hurt performance if its parent RDDs are also uncached."));
               }

               scala.Predef..MODULE$.require(k < n, () -> "k must be smaller than n in dist-eigs mode but got k=" + k + " and n=" + n + ".");
               var64 = EigenValueDecomposition$.MODULE$.symmetricEigs((v) -> this.multiplyGramianMatrixBy(v), n, k, tol, maxIter);
               break label225;
            }

            scala.Predef..MODULE$.require(n < 17515, () -> n + " exceeds the breeze svd capability");
            DenseMatrix G = (DenseMatrix)this.computeGramianMatrix().asBreeze();
            svd.SVD var25 = (svd.SVD)breeze.linalg.svd..MODULE$.apply(G, breeze.linalg.svd.Svd_DM_Impl..MODULE$);
            if (var25 == null) {
               throw new MatchError(var25);
            }

            DenseMatrix uFull = (DenseMatrix)var25.leftVectors();
            DenseVector sigmaSquaresFull = (DenseVector)var25.singularValues();
            if (uFull == null || sigmaSquaresFull == null) {
               throw new MatchError(var25);
            }

            Tuple2 var24 = new Tuple2(uFull, sigmaSquaresFull);
            DenseMatrix uFull = (DenseMatrix)var24._1();
            DenseVector sigmaSquaresFull = (DenseVector)var24._2();
            var64 = new Tuple2(sigmaSquaresFull, uFull);
            break label225;
         }

         scala.Predef..MODULE$.require(k < n, () -> "k must be smaller than n in local-eigs mode but got k=" + k + " and n=" + n + ".");
         DenseMatrix G = (DenseMatrix)this.computeGramianMatrix().asBreeze();
         var64 = EigenValueDecomposition$.MODULE$.symmetricEigs((v) -> (DenseVector)G.$times(v, breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), n, k, tol, maxIter);
      }

      Tuple2 var18 = var64;
      if (var18 != null) {
         DenseVector sigmaSquares = (DenseVector)var18._1();
         DenseMatrix u = (DenseMatrix)var18._2();
         if (sigmaSquares != null && u != null) {
            Tuple2 var17 = new Tuple2(sigmaSquares, u);
            DenseVector sigmaSquares = (DenseVector)var17._1();
            DenseMatrix u = (DenseMatrix)var17._2();
            DenseVector sigmas = (DenseVector)breeze.numerics.package.sqrt..MODULE$.apply(sigmaSquares, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapActiveValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.sqrt.sqrtDoubleImpl..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())));
            double sigma0 = sigmas.apply$mcD$sp(0);
            double threshold = rCond * sigma0;
            int i = 0;
            if (sigmas.length() < k) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requested ", " singular "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_LEADING_SINGULAR_VALUES..MODULE$, BoxesRunTime.boxToInteger(k))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"values but only found ", " converged."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIGMAS_LENGTH..MODULE$, BoxesRunTime.boxToInteger(sigmas.length()))}))))));
            }

            while(i < scala.math.package..MODULE$.min(k, sigmas.length()) && sigmas.apply$mcD$sp(i) >= threshold) {
               ++i;
            }

            int sk = i;
            if (i < k) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requested ", " singular "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_LEADING_SINGULAR_VALUES..MODULE$, BoxesRunTime.boxToInteger(k))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"values but only found ", " nonzeros."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(i))}))))));
            }

            label230: {
               Enumeration.Value var47 = this.SVDMode$2(SVDMode$module).DistARPACK();
               if (computeMode == null) {
                  if (var47 != null) {
                     break label230;
                  }
               } else if (!computeMode.equals(var47)) {
                  break label230;
               }

               StorageLevel var65 = this.rows().getStorageLevel();
               StorageLevel var48 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var65 == null) {
                  if (var48 != null) {
                     break label230;
                  }
               } else if (!var65.equals(var48)) {
                  break label230;
               }

               this.logWarning((Function0)(() -> "The input data was not directly cached, which may hurt performance if its parent RDDs are also uncached."));
            }

            Vector s = Vectors$.MODULE$.dense(Arrays.copyOfRange(sigmas.data$mcD$sp(), 0, i));
            Matrix V = Matrices$.MODULE$.dense(n, i, Arrays.copyOfRange(u.data$mcD$sp(), 0, n * i));
            if (!computeU) {
               return new SingularValueDecomposition((Object)null, s, V);
            }

            DenseMatrix N = new DenseMatrix.mcD.sp(n, i, Arrays.copyOfRange(u.data$mcD$sp(), 0, n * i));
            int i = 0;

            for(int j = 0; j < sk; ++j) {
               i = 0;

               for(double sigma = sigmas.apply$mcD$sp(j); i < n; ++i) {
                  N.update$mcD$sp(i, j, N.apply$mcD$sp(i, j) / sigma);
               }
            }

            RowMatrix U = this.multiply(Matrices$.MODULE$.fromBreeze(N));
            return new SingularValueDecomposition(U, s, V);
         }
      }

      throw new MatchError(var18);
   }

   public boolean computeSVD$default$2() {
      return false;
   }

   public double computeSVD$default$3() {
      return 1.0E-9;
   }

   private boolean isSparseMatrix() {
      return this.rows().filter((row) -> BoxesRunTime.boxToBoolean($anonfun$isSparseMatrix$1(row))).isEmpty();
   }

   public Matrix computeCovariance() {
      int n = (int)this.numCols();
      this.checkNumColumns(n);
      SummarizerBuffer summary = Statistics$.MODULE$.colStats(this.rows().map((x$3) -> new Tuple2(x$3, BoxesRunTime.boxToDouble((double)1.0F)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), new scala.collection.immutable..colon.colon("count", new scala.collection.immutable..colon.colon("mean", scala.collection.immutable.Nil..MODULE$)));
      long m = summary.count();
      scala.Predef..MODULE$.require(m > 1L, () -> "RowMatrix.computeCovariance called on matrix with only " + m + " rows.  Cannot compute the covariance of a RowMatrix with <= 1 row.");
      Vector mean = Vectors$.MODULE$.fromML(summary.mean());
      return !this.isSparseMatrix() ? this.computeDenseVectorCovariance(mean, n, m) : this.computeSparseVectorCovariance(mean, n, m);
   }

   public Tuple2 computePrincipalComponentsAndExplainedVariance(final int k) {
      int n = (int)this.numCols();
      scala.Predef..MODULE$.require(k > 0 && k <= n, () -> "k = " + k + " out of range (0, n = " + n + "]");
      if (n > 65535) {
         SingularValueDecomposition svd = this.computeSVD(k, this.computeSVD$default$2(), this.computeSVD$default$3());
         double[] s = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(svd.s().toArray()), (JFunction1.mcDD.sp)(eigValue) -> eigValue * eigValue / (double)(n - 1), scala.reflect.ClassTag..MODULE$.Double());
         double eigenSum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(s).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
         double[] explainedVariance = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(s), (JFunction1.mcDD.sp)(x$4) -> x$4 / eigenSum, scala.reflect.ClassTag..MODULE$.Double());
         return new Tuple2(svd.V(), Vectors$.MODULE$.dense(explainedVariance));
      } else {
         DenseMatrix Cov = (DenseMatrix)this.computeCovariance().asBreeze();
         svd.SVD var11 = (svd.SVD)breeze.linalg.svd..MODULE$.apply(Cov, breeze.linalg.svd.Svd_DM_Impl..MODULE$);
         if (var11 != null) {
            DenseMatrix u = (DenseMatrix)var11.leftVectors();
            DenseVector s = (DenseVector)var11.singularValues();
            if (u != null && s != null) {
               Tuple2 var10 = new Tuple2(u, s);
               DenseMatrix u = (DenseMatrix)var10._1();
               DenseVector s = (DenseVector)var10._2();
               double eigenSum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(s.data$mcD$sp()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
               double[] explainedVariance = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(s.data$mcD$sp()), (JFunction1.mcDD.sp)(x$6) -> x$6 / eigenSum, scala.reflect.ClassTag..MODULE$.Double());
               if (k == n) {
                  return new Tuple2(Matrices$.MODULE$.dense(n, k, u.data$mcD$sp()), Vectors$.MODULE$.dense(explainedVariance));
               }

               return new Tuple2(Matrices$.MODULE$.dense(n, k, Arrays.copyOfRange(u.data$mcD$sp(), 0, n * k)), Vectors$.MODULE$.dense(Arrays.copyOfRange(explainedVariance, 0, k)));
            }
         }

         throw new MatchError(var11);
      }
   }

   public Matrix computePrincipalComponents(final int k) {
      return (Matrix)this.computePrincipalComponentsAndExplainedVariance(k)._1();
   }

   public MultivariateStatisticalSummary computeColumnSummaryStatistics() {
      MultivariateOnlineSummarizer x$1 = new MultivariateOnlineSummarizer();
      Function2 x$2 = (aggregator, data) -> aggregator.add(data);
      Function2 x$3 = (aggregator1, aggregator2) -> aggregator1.merge(aggregator2);
      int x$4 = this.rows().treeAggregate$default$4(x$1);
      MultivariateOnlineSummarizer summary = (MultivariateOnlineSummarizer)this.rows().treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(MultivariateOnlineSummarizer.class));
      this.updateNumRows(summary.count());
      return summary;
   }

   public RowMatrix multiply(final Matrix B) {
      int n = (int)this.numCols();
      int k = B.numCols();
      scala.Predef..MODULE$.require(n == B.numRows(), () -> "Dimension mismatch: " + n + " vs " + B.numRows());
      scala.Predef..MODULE$.require(B instanceof org.apache.spark.mllib.linalg.DenseMatrix, () -> "Only support dense matrix at this time but found " + B.getClass().getName() + ".");
      Broadcast Bb = this.rows().context().broadcast(((DenseMatrix)B.asBreeze()).toDenseVector$mcD$sp().toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      RDD AB = this.rows().mapPartitions((iter) -> {
         double[] Bi = (double[])Bb.value();
         return iter.map((row) -> {
            DenseVector v = breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(k, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());

            for(int i = 0; i < k; ++i) {
               v.update$mcD$sp(i, BoxesRunTime.unboxToDouble(row.asBreeze().dot(new DenseVector.mcD.sp(Bi, i * n, 1, n), breeze.linalg.operators.HasOps..MODULE$.castOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), breeze.linalg.operators.HasOps..MODULE$.impl_OpMulInner_V_V_eq_S_Double()))));
            }

            return Vectors$.MODULE$.fromBreeze(v);
         });
      }, this.rows().mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      return new RowMatrix(AB, this.nRows(), B.numCols());
   }

   public CoordinateMatrix columnSimilarities() {
      return this.columnSimilarities((double)0.0F);
   }

   public CoordinateMatrix columnSimilarities(final double threshold) {
      scala.Predef..MODULE$.require(threshold >= (double)0, () -> "Threshold cannot be negative: " + threshold);
      if (threshold > (double)1) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Threshold is greater than 1: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THRESHOLD..MODULE$, BoxesRunTime.boxToDouble(threshold))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Computation will be more efficient with promoted sparsity, "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"however there is no correctness guarantee."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      double gamma = threshold < 1.0E-6 ? Double.POSITIVE_INFINITY : (double)10 * scala.math.package..MODULE$.log((double)this.numCols()) / threshold;
      SummarizerBuffer summary = Statistics$.MODULE$.colStats(this.rows().map((x$7) -> new Tuple2(x$7, BoxesRunTime.boxToDouble((double)1.0F)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), new scala.collection.immutable..colon.colon("normL2", scala.collection.immutable.Nil..MODULE$));
      return this.columnSimilaritiesDIMSUM(summary.normL2().toArray(), gamma);
   }

   public QRDecomposition tallSkinnyQR(final boolean computeQ) {
      int col = (int)this.numCols();
      RDD blockQRs = this.rows().retag(Vector.class).glom().filter((x$8) -> BoxesRunTime.boxToBoolean($anonfun$tallSkinnyQR$1(x$8))).map((partRows) -> {
         DenseMatrix bdm = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(partRows.length, col, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
         IntRef i = IntRef.create(0);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(partRows), (row) -> {
            $anonfun$tallSkinnyQR$3(bdm, i, row);
            return BoxedUnit.UNIT;
         });
         return (DenseMatrix)((qr.QR)breeze.linalg.qr.reduced..MODULE$.apply(bdm, breeze.linalg.qr.reduced.impl_reduced_DM_Double..MODULE$)).r();
      }, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
      DenseMatrix combinedR = (DenseMatrix)blockQRs.treeReduce((r1, r2) -> {
         DenseMatrix stackedR = breeze.linalg.DenseMatrix..MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new DenseMatrix[]{r1, r2})), breeze.linalg.operators.HasOps..MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
         return (DenseMatrix)((qr.QR)breeze.linalg.qr.reduced..MODULE$.apply(stackedR, breeze.linalg.qr.reduced.impl_reduced_DM_Double..MODULE$)).r();
      }, blockQRs.treeReduce$default$2());
      Matrix finalR = Matrices$.MODULE$.fromBreeze(combinedR.toDenseMatrix$mcD$sp(scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()));
      RowMatrix var10000;
      if (computeQ) {
         try {
            DenseMatrix invR = (DenseMatrix)breeze.linalg.inv..MODULE$.apply(combinedR, breeze.linalg.inv..MODULE$.canInvUsingLU_Double(breeze.linalg.LU.primitive.LU_DM_Impl_Double..MODULE$));
            var10000 = this.multiply(Matrices$.MODULE$.fromBreeze(invR));
         } catch (MatrixSingularException var9) {
            this.logWarning((Function0)(() -> "R is not invertible and return Q as null"));
            var10000 = null;
         }
      } else {
         var10000 = null;
      }

      RowMatrix finalQ = var10000;
      return new QRDecomposition(finalQ, finalR);
   }

   public boolean tallSkinnyQR$default$1() {
      return false;
   }

   public CoordinateMatrix columnSimilaritiesDIMSUM(final double[] colMags, final double gamma) {
      scala.Predef..MODULE$.require(gamma > (double)1.0F, () -> "Oversampling should be greater than 1: " + gamma);
      scala.Predef..MODULE$.require((long)colMags.length == this.numCols(), () -> "Number of magnitudes didn't match column dimension");
      double sg = scala.math.package..MODULE$.sqrt(gamma);
      double[] colMagsCorrected = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(colMags), (JFunction1.mcDD.sp)(x) -> x == (double)0 ? (double)1.0F : x, scala.reflect.ClassTag..MODULE$.Double());
      SparkContext sc = this.rows().context();
      Broadcast pBV = sc.broadcast(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(colMagsCorrected), (JFunction1.mcDD.sp)(c) -> sg / c, scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      Broadcast qBV = sc.broadcast(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(colMagsCorrected), (JFunction1.mcDD.sp)(c) -> scala.math.package..MODULE$.min(sg, c), scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      RDD sims = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.rows().mapPartitionsWithIndex((index, iter) -> $anonfun$columnSimilaritiesDIMSUM$6(pBV, qBV, BoxesRunTime.unboxToInt(index), iter), this.rows().mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).reduceByKey((JFunction2.mcDDD.sp)(x$9, x$10) -> x$9 + x$10).map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            double sim = x0$1._2$mcD$sp();
            if (var3 != null) {
               int i = var3._1$mcI$sp();
               int j = var3._2$mcI$sp();
               return new MatrixEntry((long)i, (long)j, sim);
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(MatrixEntry.class));
      return new CoordinateMatrix(sims, this.numCols(), this.numCols());
   }

   public DenseMatrix toBreeze() {
      int m = (int)this.numRows();
      int n = (int)this.numCols();
      DenseMatrix mat = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(m, n, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      IntRef i = IntRef.create(0);
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.rows().collect()), (vector) -> {
         $anonfun$toBreeze$1(mat, i, vector);
         return BoxedUnit.UNIT;
      });
      return mat;
   }

   private void updateNumRows(final long m) {
      if (this.nRows() <= 0L) {
         this.nRows_$eq(m);
      } else {
         scala.Predef..MODULE$.require(this.nRows() == m, () -> "The number of rows " + m + " is different from what specified or previously computed: " + this.nRows() + ".");
      }
   }

   public int getTreeAggregateIdealDepth(final long aggregatedObjectSizeInBytes) {
      scala.Predef..MODULE$.require(aggregatedObjectSizeInBytes > 0L, () -> "Cannot compute aggregate depth heuristic based on a zero-size object to aggregate");
      long maxDriverResultSizeInBytes = BoxesRunTime.unboxToLong(this.rows().conf().get(org.apache.spark.internal.config.package..MODULE$.MAX_RESULT_SIZE()));
      if (maxDriverResultSizeInBytes <= 0L) {
         return 1;
      } else {
         scala.Predef..MODULE$.require(maxDriverResultSizeInBytes > aggregatedObjectSizeInBytes, () -> "Cannot aggregate object of size " + aggregatedObjectSizeInBytes + " Bytes, as it's bigger than maxResultSize (" + maxDriverResultSizeInBytes + " Bytes)");
         double numerator = scala.math.package..MODULE$.log((double)this.rows().getNumPartitions());
         double denominator = scala.math.package..MODULE$.log((double)maxDriverResultSizeInBytes) - scala.math.package..MODULE$.log((double)aggregatedObjectSizeInBytes);
         double desiredTreeDepth = scala.math.package..MODULE$.ceil(numerator / denominator);
         if (desiredTreeDepth > (double)4) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Desired tree depth for treeAggregation is big "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "). "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESIRED_TREE_DEPTH..MODULE$, BoxesRunTime.boxToDouble(desiredTreeDepth))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Consider increasing driver max result size or reducing number of partitions"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         return (int)scala.math.package..MODULE$.min(scala.math.package..MODULE$.max((double)1.0F, desiredTreeDepth), (double)10.0F);
      }
   }

   // $FF: synthetic method
   private static final SVDMode$1$ SVDMode$lzycompute$1(final LazyRef SVDMode$module$1) {
      synchronized(SVDMode$module$1){}

      SVDMode$1$ var2;
      try {
         class SVDMode$1$ extends Enumeration {
            private final Enumeration.Value LocalARPACK = this.Value();
            private final Enumeration.Value LocalLAPACK = this.Value();
            private final Enumeration.Value DistARPACK = this.Value();

            public Enumeration.Value LocalARPACK() {
               return this.LocalARPACK;
            }

            public Enumeration.Value LocalLAPACK() {
               return this.LocalLAPACK;
            }

            public Enumeration.Value DistARPACK() {
               return this.DistARPACK;
            }

            public SVDMode$1$() {
            }
         }

         var2 = SVDMode$module$1.initialized() ? (SVDMode$1$)SVDMode$module$1.value() : (SVDMode$1$)SVDMode$module$1.initialize(new SVDMode$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final SVDMode$1$ SVDMode$2(final LazyRef SVDMode$module$1) {
      return SVDMode$module$1.initialized() ? (SVDMode$1$)SVDMode$module$1.value() : SVDMode$lzycompute$1(SVDMode$module$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isSparseMatrix$1(final Vector row) {
      return row.sparsity() < (double)0.5F;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$tallSkinnyQR$1(final Vector[] x$8) {
      return x$8.length != 0;
   }

   // $FF: synthetic method
   public static final void $anonfun$tallSkinnyQR$3(final DenseMatrix bdm$1, final IntRef i$1, final Vector row) {
      ((NumericOps)bdm$1.apply(BoxesRunTime.boxToInteger(i$1.elem), scala.package..MODULE$.$colon$colon(), breeze.linalg.operators.HasOps..MODULE$.canSliceRow())).$colon$eq(row.asBreeze().t(breeze.linalg.operators.HasOps..MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl())), breeze.linalg.operators.HasOps..MODULE$.liftInPlaceOps(breeze.gymnastics.NotGiven..MODULE$.neq(), breeze.linalg.operators.HasOps..MODULE$.canUntranspose(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_V_Double_OpSet()));
      ++i$1.elem;
   }

   // $FF: synthetic method
   public static final ListBuffer $anonfun$columnSimilaritiesDIMSUM$8(final int[] indices$1, final double[] scaled$1, final XORShiftRandom rand$1, final double[] p$1, final int nnz$1, final int k) {
      ListBuffer buf = new ListBuffer();
      int i = indices$1[k];
      double iVal = scaled$1[k];
      if (iVal != (double)0 && rand$1.nextDouble() < p$1[i]) {
         for(int l = k + 1; l < nnz$1; ++l) {
            int j = indices$1[l];
            double jVal = scaled$1[l];
            if (jVal != (double)0 && rand$1.nextDouble() < p$1[j]) {
               buf.$plus$eq(new Tuple2(new Tuple2.mcII.sp(i, j), BoxesRunTime.boxToDouble(iVal * jVal)));
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }
      }

      return buf;
   }

   // $FF: synthetic method
   public static final ListBuffer $anonfun$columnSimilaritiesDIMSUM$9(final double[] scaled$1, final XORShiftRandom rand$1, final double[] p$1, final int n$5, final int i) {
      ListBuffer buf = new ListBuffer();
      double iVal = scaled$1[i];
      if (iVal != (double)0 && rand$1.nextDouble() < p$1[i]) {
         for(int j = i + 1; j < n$5; ++j) {
            double jVal = scaled$1[j];
            if (jVal != (double)0 && rand$1.nextDouble() < p$1[j]) {
               buf.$plus$eq(new Tuple2(new Tuple2.mcII.sp(i, j), BoxesRunTime.boxToDouble(iVal * jVal)));
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }
      }

      return buf;
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$columnSimilaritiesDIMSUM$6(final Broadcast pBV$1, final Broadcast qBV$1, final int index, final Iterator iter) {
      double[] p = (double[])pBV$1.value();
      double[] q = (double[])qBV$1.value();
      XORShiftRandom rand = new XORShiftRandom((long)index);
      double[] scaled = new double[p.length];
      return iter.flatMap((row) -> {
         if (row instanceof org.apache.spark.mllib.linalg.SparseVector var7) {
            Option var8 = SparseVector$.MODULE$.unapply(var7);
            if (!var8.isEmpty()) {
               int[] indices = (int[])((Tuple3)var8.get())._2();
               double[] values = (double[])((Tuple3)var8.get())._3();
               int nnz = indices.length;

               for(int k = 0; k < nnz; ++k) {
                  scaled[k] = values[k] / q[indices[k]];
               }

               return scala.package..MODULE$.Iterator().tabulate(nnz, (kx) -> $anonfun$columnSimilaritiesDIMSUM$8(indices, scaled, rand, p, nnz, BoxesRunTime.unboxToInt(kx))).flatten(scala.Predef..MODULE$.$conforms());
            }
         }

         if (row instanceof org.apache.spark.mllib.linalg.DenseVector var13) {
            Option var14 = DenseVector$.MODULE$.unapply(var13);
            if (!var14.isEmpty()) {
               double[] values = (double[])var14.get();
               int n = values.length;

               for(int i = 0; i < n; ++i) {
                  scaled[i] = values[i] / q[i];
               }

               return scala.package..MODULE$.Iterator().tabulate(n, (ix) -> $anonfun$columnSimilaritiesDIMSUM$9(scaled, rand, p, n, BoxesRunTime.unboxToInt(ix))).flatten(scala.Predef..MODULE$.$conforms());
            }
         }

         throw new IllegalArgumentException("Unknown vector type " + row.getClass() + ".");
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$toBreeze$1(final DenseMatrix mat$1, final IntRef i$2, final Vector vector) {
      vector.foreachNonZero((JFunction2.mcVID.sp)(x0$1, x1$1) -> {
         Tuple2.mcID.sp var6 = new Tuple2.mcID.sp(x0$1, x1$1);
         if (var6 != null) {
            int j = ((Tuple2)var6)._1$mcI$sp();
            double v = ((Tuple2)var6)._2$mcD$sp();
            mat$1.update$mcD$sp(i$2.elem, j, v);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var6);
         }
      });
      ++i$2.elem;
   }

   public RowMatrix(final RDD rows, final long nRows, final int nCols) {
      this.rows = rows;
      this.nRows = nRows;
      this.nCols = nCols;
      super();
      Logging.$init$(this);
   }

   public RowMatrix(final RDD rows) {
      this(rows, 0L, 0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
