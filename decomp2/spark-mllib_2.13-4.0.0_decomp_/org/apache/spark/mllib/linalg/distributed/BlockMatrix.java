package org.apache.spark.mllib.linalg.distributed;

import breeze.linalg.DenseVector;
import breeze.linalg.NumericOps;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.PartitionIdPassthrough;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.SparseMatrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD.;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015b\u0001\u0002\u0015*\u0001YB\u0001b\u0012\u0001\u0003\u0006\u0004%\t\u0001\u0013\u0005\tG\u0002\u0011\t\u0011)A\u0005\u0013\"AQ\r\u0001BC\u0002\u0013\u0005a\r\u0003\u0005i\u0001\t\u0005\t\u0015!\u0003T\u0011!Q\u0007A!b\u0001\n\u00031\u0007\u0002\u00037\u0001\u0005\u0003\u0005\u000b\u0011B*\t\u00119\u0004!\u00111A\u0005\n=D\u0001b\u001d\u0001\u0003\u0002\u0004%I\u0001\u001e\u0005\tu\u0002\u0011\t\u0011)Q\u0005a\"A1\u0010\u0001BA\u0002\u0013%q\u000e\u0003\u0005}\u0001\t\u0005\r\u0011\"\u0003~\u0011!y\bA!A!B\u0013\u0001\bbBA\u0001\u0001\u0011\u0005\u00111A\u0003\u0006\u00033\u0001Aa\u0014\u0005\b\u0003\u0003\u0001A\u0011AA\u000e\u0011\u001d\t)\u0003\u0001C!\u0003OAq!a\u000b\u0001\t\u0003\n9\u0003\u0003\u0005\u00020\u0001\u0011\r\u0011\"\u0001g\u0011\u001d\t\u0019\u0004\u0001Q\u0001\nMC\u0001\"a\u000e\u0001\u0005\u0004%\tA\u001a\u0005\b\u0003w\u0001\u0001\u0015!\u0003T\u0011!\ty\u0004\u0001C\u0001[\u0005\u0005\u0003BCA%\u0001!\u0015\r\u0011\"\u0003\u0002L!9\u0011\u0011\u000b\u0001\u0005\n\u0005M\u0003bBA+\u0001\u0011\u0005\u00111\u000b\u0005\b\u00033\u0002A\u0011AA.\u0011\u001d\t\t\u0007\u0001C\u0001\u0003GBq!a\u001e\u0001\t\u0003\tI\bC\u0004\u0002\u0004\u0002!\t!!\"\t\u000f\u0005=\u0005\u0001\"\u0001\u0002\u0012\"9\u0011Q\u0013\u0001\u0005\u0002\u0005]\u0005\u0002CAN\u0001\u0011\u0005Q&!(\t\u0011\u0005M\u0006\u0001\"\u0001.\u0003kCq!!3\u0001\t\u0003\tY\rC\u0004\u0002R\u0002!\t!a5\u0006\r\u0005u\u0007\u0001BAp\u0011!\tY\u0010\u0001C\u0001S\u0005u\bb\u0002B\u0007\u0001\u0011\u0005!q\u0002\u0005\b\u0005\u001b\u0001A\u0011\u0001B\u000b\u0005-\u0011En\\2l\u001b\u0006$(/\u001b=\u000b\u0005)Z\u0013a\u00033jgR\u0014\u0018NY;uK\u0012T!\u0001L\u0017\u0002\r1Lg.\u00197h\u0015\tqs&A\u0003nY2L'M\u0003\u00021c\u0005)1\u000f]1sW*\u0011!gM\u0001\u0007CB\f7\r[3\u000b\u0003Q\n1a\u001c:h\u0007\u0001\u0019B\u0001A\u001c>\u0003B\u0011\u0001hO\u0007\u0002s)\t!(A\u0003tG\u0006d\u0017-\u0003\u0002=s\t1\u0011I\\=SK\u001a\u0004\"AP \u000e\u0003%J!\u0001Q\u0015\u0003#\u0011K7\u000f\u001e:jEV$X\rZ'biJL\u0007\u0010\u0005\u0002C\u000b6\t1I\u0003\u0002E_\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002G\u0007\n9Aj\\4hS:<\u0017A\u00022m_\u000e\\7/F\u0001J!\rQUjT\u0007\u0002\u0017*\u0011AjL\u0001\u0004e\u0012$\u0017B\u0001(L\u0005\r\u0011F\t\u0012\t\u0005qA\u0013f+\u0003\u0002Rs\t1A+\u001e9mKJ\u0002B\u0001\u000f)T'B\u0011\u0001\bV\u0005\u0003+f\u00121!\u00138u!\t9\u0006,D\u0001,\u0013\tI6F\u0001\u0004NCR\u0014\u0018\u000e\u001f\u0015\u0004\u0003m\u000b\u0007C\u0001/`\u001b\u0005i&B\u000100\u0003)\tgN\\8uCRLwN\\\u0005\u0003Av\u0013QaU5oG\u0016\f\u0013AY\u0001\u0006c9\u001ad\u0006M\u0001\bE2|7m[:!Q\r\u00111,Y\u0001\re><8\u000fU3s\u00052|7m[\u000b\u0002'\"\u001a1aW1\u0002\u001bI|wo\u001d)fe\ncwnY6!Q\r!1,Y\u0001\rG>d7\u000fU3s\u00052|7m\u001b\u0015\u0004\u000bm\u000b\u0017!D2pYN\u0004VM\u001d\"m_\u000e\\\u0007\u0005K\u0002\u00077\u0006\fQA\u001c*poN,\u0012\u0001\u001d\t\u0003qEL!A]\u001d\u0003\t1{gnZ\u0001\n]J{wo]0%KF$\"!\u001e=\u0011\u0005a2\u0018BA<:\u0005\u0011)f.\u001b;\t\u000feD\u0011\u0011!a\u0001a\u0006\u0019\u0001\u0010J\u0019\u0002\r9\u0014vn^:!\u0003\u0015q7i\u001c7t\u0003%q7i\u001c7t?\u0012*\u0017\u000f\u0006\u0002v}\"9\u0011pCA\u0001\u0002\u0004\u0001\u0018A\u00028D_2\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\r\u0003\u000b\t9!a\u0003\u0002\u0010\u0005M\u0011Q\u0003\t\u0003}\u0001AQaR\u0007A\u0002%CC!a\u0002\\C\")Q-\u0004a\u0001'\"\"\u00111B.b\u0011\u0015QW\u00021\u0001TQ\u0011\tyaW1\t\u000b9l\u0001\u0019\u00019\t\u000bml\u0001\u0019\u00019)\u00075Y\u0016MA\u0006NCR\u0014\u0018\u000e\u001f\"m_\u000e\\G\u0003CA\u0003\u0003;\ty\"!\t\t\u000b\u001d{\u0001\u0019A%\t\u000b\u0015|\u0001\u0019A*\t\u000b)|\u0001\u0019A*)\u0007=Y\u0016-A\u0004ok6\u0014vn^:\u0015\u0003AD3\u0001E.b\u0003\u001dqW/\\\"pYND3!E.b\u00031qW/\u001c*po\ncwnY6tQ\r\u00112,Y\u0001\u000e]Vl'k\\<CY>\u001c7n\u001d\u0011)\u0007MY\u0016-\u0001\u0007ok6\u001cu\u000e\u001c\"m_\u000e\\7\u000fK\u0002\u00157\u0006\fQB\\;n\u0007>d'\t\\8dWN\u0004\u0003fA\u000b\\C\u0006\t2M]3bi\u0016\u0004\u0016M\u001d;ji&|g.\u001a:\u0015\u0005\u0005\r\u0003c\u0001 \u0002F%\u0019\u0011qI\u0015\u0003\u001f\u001d\u0013\u0018\u000e\u001a)beRLG/[8oKJ\f\u0011B\u00197pG.LeNZ8\u0016\u0005\u00055\u0003\u0003\u0002&N\u0003\u001f\u0002B\u0001\u000f)S%\u0006YQm\u001d;j[\u0006$X\rR5n)\u0005)\u0018\u0001\u0003<bY&$\u0017\r^3)\u0007eY\u0016-A\u0003dC\u000eDW\r\u0006\u0002\u0002^5\t\u0001\u0001K\u0002\u001b7\u0006\fq\u0001]3sg&\u001cH\u000f\u0006\u0003\u0002^\u0005\u0015\u0004bBA47\u0001\u0007\u0011\u0011N\u0001\rgR|'/Y4f\u0019\u00164X\r\u001c\t\u0005\u0003W\n\t(\u0004\u0002\u0002n)\u0019\u0011qN\u0018\u0002\u000fM$xN]1hK&!\u00111OA7\u00051\u0019Fo\u001c:bO\u0016dUM^3mQ\rY2,Y\u0001\u0013i>\u001cun\u001c:eS:\fG/Z'biJL\u0007\u0010\u0006\u0002\u0002|A\u0019a(! \n\u0007\u0005}\u0014F\u0001\tD_>\u0014H-\u001b8bi\u0016l\u0015\r\u001e:jq\"\u001aAdW1\u0002%Q|\u0017J\u001c3fq\u0016$'k\\<NCR\u0014\u0018\u000e\u001f\u000b\u0003\u0003\u000f\u00032APAE\u0013\r\tY)\u000b\u0002\u0011\u0013:$W\r_3e%><X*\u0019;sSbD3!H.b\u00035!x\u000eT8dC2l\u0015\r\u001e:jqR\ta\u000bK\u0002\u001f7\u0006\f\u0011\u0002\u001e:b]N\u0004xn]3\u0016\u0005\u0005\u0015\u0001fA\u0010\\C\u0006AAo\u001c\"sK\u0016TX\r\u0006\u0002\u0002 B1\u0011\u0011UAU\u0003[k!!a)\u000b\u00071\n)K\u0003\u0002\u0002(\u00061!M]3fu\u0016LA!a+\u0002$\nYA)\u001a8tK6\u000bGO]5y!\rA\u0014qV\u0005\u0004\u0003cK$A\u0002#pk\ndW-\u0001\u0005cY>\u001c7.T1q)\u0019\t)!a.\u0002<\"9\u0011\u0011X\u0011A\u0002\u0005\u0015\u0011!B8uQ\u0016\u0014\bbBA_C\u0001\u0007\u0011qX\u0001\u0007E&tW*\u00199\u0011\u0013a\n\t-!2\u0002F\u0006\u0015\u0017bAAbs\tIa)\u001e8di&|gN\r\t\u0007\u0003C\u000b9-!,\n\u0007e\u000b\u0019+A\u0002bI\u0012$B!!\u0002\u0002N\"9\u0011\u0011\u0018\u0012A\u0002\u0005\u0015\u0001f\u0001\u0012\\C\u0006A1/\u001e2ue\u0006\u001cG\u000f\u0006\u0003\u0002\u0006\u0005U\u0007bBA]G\u0001\u0007\u0011Q\u0001\u0015\u0005Gm\u000bI.\t\u0002\u0002\\\u0006)!G\f\u0019/a\t\t\"\t\\8dW\u0012+7\u000f^5oCRLwN\\:\u0011\u000f\u0005\u0005\u0018q\u001e*\u0002v:!\u00111]Av!\r\t)/O\u0007\u0003\u0003OT1!!;6\u0003\u0019a$o\\8u}%\u0019\u0011Q^\u001d\u0002\rA\u0013X\rZ3g\u0013\u0011\t\t0a=\u0003\u00075\u000b\u0007OC\u0002\u0002nf\u0002R!!9\u0002xNKA!!?\u0002t\n\u00191+\u001a;\u0002!MLW.\u001e7bi\u0016lU\u000f\u001c;ja2LH\u0003CA\u0000\u0005\u0007\u0011)A!\u0003\u0011\ra\u0002&\u0011\u0001B\u0001!\r\ti\u0006\n\u0005\b\u0003s+\u0003\u0019AA\u0003\u0011\u001d\u00119!\na\u0001\u0003\u0007\n1\u0002]1si&$\u0018n\u001c8fe\"1!1B\u0013A\u0002M\u000ba\"\\5e\t&l7\u000b\u001d7ji:+X.\u0001\u0005nk2$\u0018\u000e\u001d7z)\u0011\t)A!\u0005\t\u000f\u0005ef\u00051\u0001\u0002\u0006!\u001aaeW1\u0015\r\u0005\u0015!q\u0003B\r\u0011\u001d\tIl\na\u0001\u0003\u000bAaAa\u0007(\u0001\u0004\u0019\u0016a\u00048v[6KG\rR5n'Bd\u0017\u000e^:)\t\u001dZ&qD\u0011\u0003\u0005C\tQA\r\u00183]AB3\u0001A.b\u0001"
)
public class BlockMatrix implements DistributedMatrix, Logging {
   private RDD blockInfo;
   private final RDD blocks;
   private final int rowsPerBlock;
   private final int colsPerBlock;
   private long nRows;
   private long nCols;
   private final int numRowBlocks;
   private final int numColBlocks;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

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

   public RDD blocks() {
      return this.blocks;
   }

   public int rowsPerBlock() {
      return this.rowsPerBlock;
   }

   public int colsPerBlock() {
      return this.colsPerBlock;
   }

   private long nRows() {
      return this.nRows;
   }

   private void nRows_$eq(final long x$1) {
      this.nRows = x$1;
   }

   private long nCols() {
      return this.nCols;
   }

   private void nCols_$eq(final long x$1) {
      this.nCols = x$1;
   }

   public long numRows() {
      if (this.nRows() <= 0L) {
         this.estimateDim();
      }

      return this.nRows();
   }

   public long numCols() {
      if (this.nCols() <= 0L) {
         this.estimateDim();
      }

      return this.nCols();
   }

   public int numRowBlocks() {
      return this.numRowBlocks;
   }

   public int numColBlocks() {
      return this.numColBlocks;
   }

   public GridPartitioner createPartitioner() {
      return GridPartitioner$.MODULE$.apply(this.numRowBlocks(), this.numColBlocks(), this.blocks().partitions().length);
   }

   private RDD blockInfo$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.blockInfo = .MODULE$.rddToPairRDDFunctions(this.blocks(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Matrix.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).mapValues((block) -> new Tuple2.mcII.sp(block.numRows(), block.numCols())).cache();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.blockInfo;
   }

   private RDD blockInfo() {
      return !this.bitmap$0 ? this.blockInfo$lzycompute() : this.blockInfo;
   }

   private void estimateDim() {
      Tuple2 var3 = (Tuple2)this.blockInfo().map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var4 = (Tuple2)x0$1._1();
            Tuple2 var5 = (Tuple2)x0$1._2();
            if (var4 != null) {
               int blockRowIndex = var4._1$mcI$sp();
               int blockColIndex = var4._2$mcI$sp();
               if (var5 != null) {
                  int m = var5._1$mcI$sp();
                  int n = var5._2$mcI$sp();
                  return new Tuple2.mcJJ.sp((long)blockRowIndex * (long)this.rowsPerBlock() + (long)m, (long)blockColIndex * (long)this.colsPerBlock() + (long)n);
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).reduce((x0, x1) -> new Tuple2.mcJJ.sp(scala.math.package..MODULE$.max(x0._1$mcJ$sp(), x1._1$mcJ$sp()), scala.math.package..MODULE$.max(x0._2$mcJ$sp(), x1._2$mcJ$sp())));
      if (var3 != null) {
         long rows = var3._1$mcJ$sp();
         long cols = var3._2$mcJ$sp();
         Tuple2.mcJJ.sp var2 = new Tuple2.mcJJ.sp(rows, cols);
         long rows = ((Tuple2)var2)._1$mcJ$sp();
         long cols = ((Tuple2)var2)._2$mcJ$sp();
         if (this.nRows() <= 0L) {
            this.nRows_$eq(rows);
         }

         scala.Predef..MODULE$.assert(rows <= this.nRows(), () -> "The number of rows " + rows + " is more than claimed " + this.nRows() + ".");
         if (this.nCols() <= 0L) {
            this.nCols_$eq(cols);
         }

         scala.Predef..MODULE$.assert(cols <= this.nCols(), () -> "The number of columns " + cols + " is more than claimed " + this.nCols() + ".");
      } else {
         throw new MatchError(var3);
      }
   }

   public void validate() {
      this.logDebug((Function0)(() -> "Validating BlockMatrix..."));
      this.estimateDim();
      this.logDebug((Function0)(() -> "BlockMatrix dimensions are okay..."));
      .MODULE$.rddToPairRDDFunctions(this.blockInfo(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).countByKey().foreach((x0$1) -> {
         $anonfun$validate$3(x0$1);
         return BoxedUnit.UNIT;
      });
      this.logDebug((Function0)(() -> "MatrixBlock indices are okay..."));
      int var10000 = this.rowsPerBlock();
      String dimensionMsg = "dimensions different than rowsPerBlock: " + var10000 + ", and colsPerBlock: " + this.colsPerBlock() + ". Blocks on the right and bottom edges can have smaller dimensions. You may use the repartition method to fix this issue.";
      this.blockInfo().foreach((x0$2) -> {
         $anonfun$validate$5(this, dimensionMsg, x0$2);
         return BoxedUnit.UNIT;
      });
      this.logDebug((Function0)(() -> "MatrixBlock dimensions are okay..."));
      this.logDebug((Function0)(() -> "BlockMatrix is valid!"));
   }

   public BlockMatrix cache() {
      this.blocks().cache();
      return this;
   }

   public BlockMatrix persist(final StorageLevel storageLevel) {
      this.blocks().persist(storageLevel);
      return this;
   }

   public CoordinateMatrix toCoordinateMatrix() {
      RDD entryRDD = this.blocks().flatMap((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var4 = (Tuple2)x0$1._1();
            Matrix mat = (Matrix)x0$1._2();
            if (var4 != null) {
               int blockRowIndex = var4._1$mcI$sp();
               int blockColIndex = var4._2$mcI$sp();
               long rowStart = (long)blockRowIndex * (long)this.rowsPerBlock();
               long colStart = (long)blockColIndex * (long)this.colsPerBlock();
               ArrayBuffer entryValues = new ArrayBuffer();
               mat.foreachActive((i, j, v) -> {
                  $anonfun$toCoordinateMatrix$2(entryValues, rowStart, colStart, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
                  return BoxedUnit.UNIT;
               });
               return entryValues;
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(MatrixEntry.class));
      return new CoordinateMatrix(entryRDD, this.numRows(), this.numCols());
   }

   public IndexedRowMatrix toIndexedRowMatrix() {
      int cols = (int)this.numCols();
      scala.Predef..MODULE$.require(cols < Integer.MAX_VALUE, () -> "The number of columns should be less than Int.MaxValue (" + cols + ").");
      RDD rows = .MODULE$.rddToPairRDDFunctions(this.blocks().flatMap((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var4 = (Tuple2)x0$1._1();
            Matrix mat = (Matrix)x0$1._2();
            if (var4 != null) {
               int blockRowIdx = var4._1$mcI$sp();
               int blockColIdx = var4._2$mcI$sp();
               return mat.rowIter().zipWithIndex().filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$toIndexedRowMatrix$3(x$2))).map((x0$2) -> {
                  if (x0$2 != null) {
                     Vector vector = (Vector)x0$2._1();
                     int rowIdx = x0$2._2$mcI$sp();
                     return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(blockRowIdx * this.rowsPerBlock() + rowIdx)), new Tuple2(BoxesRunTime.boxToInteger(blockColIdx), vector));
                  } else {
                     throw new MatchError(x0$2);
                  }
               });
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).groupByKey().map((x0$3) -> {
         if (x0$3 != null) {
            int rowIdx = x0$3._1$mcI$sp();
            Iterable vectors = (Iterable)x0$3._2();
            int numberNonZero = BoxesRunTime.unboxToInt(((IterableOnceOps)vectors.map((x$3) -> BoxesRunTime.boxToInteger($anonfun$toIndexedRowMatrix$6(x$3)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
            double numberNonZeroPerRow = (double)numberNonZero / (double)cols;
            Vector var10000;
            if (numberNonZeroPerRow <= 0.1) {
               ArrayBuffer arrBufferIndices = new ArrayBuffer(numberNonZero);
               ArrayBuffer arrBufferValues = new ArrayBuffer(numberNonZero);
               vectors.foreach((x0$4) -> {
                  $anonfun$toIndexedRowMatrix$7(this, arrBufferIndices, arrBufferValues, x0$4);
                  return BoxedUnit.UNIT;
               });
               var10000 = Vectors$.MODULE$.sparse(cols, (int[])arrBufferIndices.toArray(scala.reflect.ClassTag..MODULE$.Int()), (double[])arrBufferValues.toArray(scala.reflect.ClassTag..MODULE$.Double()));
            } else {
               DenseVector wholeVectorBuf = breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(cols, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
               vectors.foreach((x0$5) -> {
                  if (x0$5 != null) {
                     int blockColIdx = x0$5._1$mcI$sp();
                     Vector vec = (Vector)x0$5._2();
                     if (true && vec != null) {
                        int offset = this.colsPerBlock() * blockColIdx;
                        return (DenseVector)((NumericOps)wholeVectorBuf.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(offset), Math.min(cols, offset + this.colsPerBlock())), breeze.linalg.operators.HasOps..MODULE$.canSlice_DV_Range_eq_DV())).$colon$eq(vec.asBreeze(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_V_Double_OpSet());
                     }
                  }

                  throw new MatchError(x0$5);
               });
               var10000 = Vectors$.MODULE$.fromBreeze(wholeVectorBuf);
            }

            Vector wholeVector = var10000;
            return new IndexedRow((long)rowIdx, wholeVector);
         } else {
            throw new MatchError(x0$3);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(IndexedRow.class));
      return new IndexedRowMatrix(rows);
   }

   public Matrix toLocalMatrix() {
      scala.Predef..MODULE$.require(this.numRows() < 2147483647L, () -> "The number of rows of this matrix should be less than Int.MaxValue. Currently numRows: " + this.numRows());
      scala.Predef..MODULE$.require(this.numCols() < 2147483647L, () -> "The number of columns of this matrix should be less than Int.MaxValue. Currently numCols: " + this.numCols());
      scala.Predef..MODULE$.require(this.numRows() * this.numCols() < 2147483647L, () -> "The length of the values array must be less than Int.MaxValue. Currently numRows * numCols: " + this.numRows() * this.numCols());
      int m = (int)this.numRows();
      int n = (int)this.numCols();
      int mem = m * n / 125000;
      if (mem > 500) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Storing this matrix will require ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, BoxesRunTime.boxToInteger(mem))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"MiB of memory!"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      Tuple2[] localBlocks = (Tuple2[])this.blocks().collect();
      double[] values = new double[m * n];
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])localBlocks), (x0$1) -> {
         $anonfun$toLocalMatrix$5(this, m, values, x0$1);
         return BoxedUnit.UNIT;
      });
      return new DenseMatrix(m, n, values);
   }

   public BlockMatrix transpose() {
      RDD transposedBlocks = this.blocks().map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            Matrix mat = (Matrix)x0$1._2();
            if (var3 != null) {
               int blockRowIndex = var3._1$mcI$sp();
               int blockColIndex = var3._2$mcI$sp();
               return new Tuple2(new Tuple2.mcII.sp(blockColIndex, blockRowIndex), mat.transpose());
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return new BlockMatrix(transposedBlocks, this.colsPerBlock(), this.rowsPerBlock(), this.nCols(), this.nRows());
   }

   public breeze.linalg.DenseMatrix toBreeze() {
      Matrix localMat = this.toLocalMatrix();
      return new breeze.linalg.DenseMatrix.mcD.sp(localMat.numRows(), localMat.numCols(), localMat.toArray());
   }

   public BlockMatrix blockMap(final BlockMatrix other, final Function2 binMap) {
      scala.Predef..MODULE$.require(this.numRows() == other.numRows(), () -> {
         long var10000 = this.numRows();
         return "Both matrices must have the same number of rows. A.numRows: " + var10000 + ", B.numRows: " + other.numRows();
      });
      scala.Predef..MODULE$.require(this.numCols() == other.numCols(), () -> {
         long var10000 = this.numCols();
         return "Both matrices must have the same number of columns. A.numCols: " + var10000 + ", B.numCols: " + other.numCols();
      });
      if (this.rowsPerBlock() == other.rowsPerBlock() && this.colsPerBlock() == other.colsPerBlock()) {
         RDD newBlocks = .MODULE$.rddToPairRDDFunctions(this.blocks(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Matrix.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).cogroup(other.blocks(), this.createPartitioner()).map((x0$1) -> {
            if (x0$1 != null) {
               Tuple2 var4 = (Tuple2)x0$1._1();
               Tuple2 var5 = (Tuple2)x0$1._2();
               if (var4 != null) {
                  int blockRowIndex = var4._1$mcI$sp();
                  int blockColIndex = var4._2$mcI$sp();
                  if (var5 != null) {
                     Iterable a = (Iterable)var5._1();
                     Iterable b = (Iterable)var5._2();
                     if (a.size() <= 1 && b.size() <= 1) {
                        if (a.isEmpty()) {
                           breeze.linalg.Matrix zeroBlock = breeze.linalg.Matrix..MODULE$.zeros$mDc$sp(((Matrix)b.head()).numRows(), ((Matrix)b.head()).numCols(), scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
                           breeze.linalg.Matrix result = (breeze.linalg.Matrix)binMap.apply(zeroBlock, ((Matrix)b.head()).asBreeze());
                           return new Tuple2(new Tuple2.mcII.sp(blockRowIndex, blockColIndex), Matrices$.MODULE$.fromBreeze(result));
                        }

                        if (b.isEmpty()) {
                           return new Tuple2(new Tuple2.mcII.sp(blockRowIndex, blockColIndex), a.head());
                        }

                        breeze.linalg.Matrix result = (breeze.linalg.Matrix)binMap.apply(((Matrix)a.head()).asBreeze(), ((Matrix)b.head()).asBreeze());
                        return new Tuple2(new Tuple2.mcII.sp(blockRowIndex, blockColIndex), Matrices$.MODULE$.fromBreeze(result));
                     }

                     throw new SparkException("There are multiple MatrixBlocks with indices: (" + blockRowIndex + ", " + blockColIndex + "). Please remove them.");
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         return new BlockMatrix(newBlocks, this.rowsPerBlock(), this.colsPerBlock(), this.numRows(), this.numCols());
      } else {
         throw new SparkException("Cannot perform on matrices with different block dimensions");
      }
   }

   public BlockMatrix add(final BlockMatrix other) {
      return this.blockMap(other, (x, y) -> (breeze.linalg.Matrix)x.$plus(y, breeze.linalg.operators.HasOps..MODULE$.op_M_DM_Double_OpAdd()));
   }

   public BlockMatrix subtract(final BlockMatrix other) {
      return this.blockMap(other, (x, y) -> (breeze.linalg.Matrix)x.$minus(y, breeze.linalg.operators.HasOps..MODULE$.op_M_DM_Double_OpSub()));
   }

   public Tuple2 simulateMultiply(final BlockMatrix other, final GridPartitioner partitioner, final int midDimSplitNum) {
      Tuple2[] leftMatrix = (Tuple2[]).MODULE$.rddToPairRDDFunctions(this.blockInfo(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).keys().collect();
      Tuple2[] rightMatrix = (Tuple2[]).MODULE$.rddToPairRDDFunctions(other.blockInfo(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).keys().collect();
      scala.collection.immutable.Map rightCounterpartsHelper = (scala.collection.immutable.Map)scala.collection.ArrayOps..MODULE$.groupBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])rightMatrix), (x$4) -> BoxesRunTime.boxToInteger($anonfun$simulateMultiply$1(x$4))).transform((x$5, v) -> $anonfun$simulateMultiply$2(BoxesRunTime.unboxToInt(x$5), v));
      scala.collection.immutable.Map leftDestinations = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])leftMatrix), (x0$1) -> {
         if (x0$1 != null) {
            int rowIndex = x0$1._1$mcI$sp();
            int colIndex = x0$1._2$mcI$sp();
            int[] rightCounterparts = (int[])rightCounterpartsHelper.getOrElse(BoxesRunTime.boxToInteger(colIndex), () -> scala.Array..MODULE$.emptyIntArray());
            int[] partitions = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(rightCounterparts), (JFunction1.mcII.sp)(b) -> partitioner.getPartition(new Tuple2.mcII.sp(rowIndex, b)), scala.reflect.ClassTag..MODULE$.Int());
            int midDimSplitIndex = colIndex % midDimSplitNum;
            return new Tuple2(new Tuple2.mcII.sp(rowIndex, colIndex), scala.Predef..MODULE$.wrapIntArray(partitions).toSet().map((JFunction1.mcII.sp)(pid) -> pid * midDimSplitNum + midDimSplitIndex));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      scala.collection.immutable.Map leftCounterpartsHelper = (scala.collection.immutable.Map)scala.collection.ArrayOps..MODULE$.groupBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])leftMatrix), (x$7) -> BoxesRunTime.boxToInteger($anonfun$simulateMultiply$8(x$7))).transform((x$8, v) -> $anonfun$simulateMultiply$9(BoxesRunTime.unboxToInt(x$8), v));
      scala.collection.immutable.Map rightDestinations = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])rightMatrix), (x0$2) -> {
         if (x0$2 != null) {
            int rowIndex = x0$2._1$mcI$sp();
            int colIndex = x0$2._2$mcI$sp();
            int[] leftCounterparts = (int[])leftCounterpartsHelper.getOrElse(BoxesRunTime.boxToInteger(rowIndex), () -> scala.Array..MODULE$.emptyIntArray());
            int[] partitions = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(leftCounterparts), (JFunction1.mcII.sp)(b) -> partitioner.getPartition(new Tuple2.mcII.sp(b, colIndex)), scala.reflect.ClassTag..MODULE$.Int());
            int midDimSplitIndex = rowIndex % midDimSplitNum;
            return new Tuple2(new Tuple2.mcII.sp(rowIndex, colIndex), scala.Predef..MODULE$.wrapIntArray(partitions).toSet().map((JFunction1.mcII.sp)(pid) -> pid * midDimSplitNum + midDimSplitIndex));
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      return new Tuple2(leftDestinations, rightDestinations);
   }

   public BlockMatrix multiply(final BlockMatrix other) {
      return this.multiply(other, 1);
   }

   public BlockMatrix multiply(final BlockMatrix other, final int numMidDimSplits) {
      scala.Predef..MODULE$.require(this.numCols() == other.numRows(), () -> {
         long var10000 = this.numCols();
         return "The number of columns of A and the number of rows of B must be equal. A.numCols: " + var10000 + ", B.numRows: " + other.numRows() + ". If you think they should be equal, try setting the dimensions of A and B explicitly while initializing them.";
      });
      scala.Predef..MODULE$.require(numMidDimSplits > 0, () -> "numMidDimSplits should be a positive integer.");
      if (this.colsPerBlock() == other.rowsPerBlock()) {
         GridPartitioner resultPartitioner = GridPartitioner$.MODULE$.apply(this.numRowBlocks(), other.numColBlocks(), scala.math.package..MODULE$.max(this.blocks().partitions().length, other.blocks().partitions().length));
         Tuple2 var6 = this.simulateMultiply(other, resultPartitioner, numMidDimSplits);
         if (var6 != null) {
            scala.collection.immutable.Map leftDestinations = (scala.collection.immutable.Map)var6._1();
            scala.collection.immutable.Map rightDestinations = (scala.collection.immutable.Map)var6._2();
            Tuple2 var5 = new Tuple2(leftDestinations, rightDestinations);
            scala.collection.immutable.Map leftDestinations = (scala.collection.immutable.Map)var5._1();
            scala.collection.immutable.Map rightDestinations = (scala.collection.immutable.Map)var5._2();
            RDD flatA = this.blocks().flatMap((x0$1) -> {
               if (x0$1 != null) {
                  Tuple2 var4 = (Tuple2)x0$1._1();
                  Matrix block = (Matrix)x0$1._2();
                  if (var4 != null) {
                     int blockRowIndex = var4._1$mcI$sp();
                     int blockColIndex = var4._2$mcI$sp();
                     Set destinations = (Set)leftDestinations.getOrElse(new Tuple2.mcII.sp(blockRowIndex, blockColIndex), () -> scala.Predef..MODULE$.Set().empty());
                     return (Set)destinations.map((j) -> $anonfun$multiply$5(blockRowIndex, blockColIndex, block, BoxesRunTime.unboxToInt(j)));
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            RDD flatB = other.blocks().flatMap((x0$2) -> {
               if (x0$2 != null) {
                  Tuple2 var4 = (Tuple2)x0$2._1();
                  Matrix block = (Matrix)x0$2._2();
                  if (var4 != null) {
                     int blockRowIndex = var4._1$mcI$sp();
                     int blockColIndex = var4._2$mcI$sp();
                     Set destinations = (Set)rightDestinations.getOrElse(new Tuple2.mcII.sp(blockRowIndex, blockColIndex), () -> scala.Predef..MODULE$.Set().empty());
                     return (Set)destinations.map((j) -> $anonfun$multiply$8(blockRowIndex, blockColIndex, block, BoxesRunTime.unboxToInt(j)));
                  }
               }

               throw new MatchError(x0$2);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            PartitionIdPassthrough intermediatePartitioner = new PartitionIdPassthrough(resultPartitioner.numPartitions() * numMidDimSplits);
            RDD newBlocks = .MODULE$.rddToPairRDDFunctions(.MODULE$.rddToPairRDDFunctions(.MODULE$.rddToPairRDDFunctions(flatA, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class), scala.math.Ordering.Int..MODULE$).cogroup(flatB, intermediatePartitioner).flatMap((x0$3) -> {
               if (x0$3 != null) {
                  Tuple2 var3 = (Tuple2)x0$3._2();
                  if (var3 != null) {
                     Iterable a = (Iterable)var3._1();
                     Iterable b = (Iterable)var3._2();
                     return (Iterable)a.flatMap((x0$4) -> {
                        if (x0$4 != null) {
                           int leftRowIndex = BoxesRunTime.unboxToInt(x0$4._1());
                           int leftColIndex = BoxesRunTime.unboxToInt(x0$4._2());
                           Matrix leftBlock = (Matrix)x0$4._3();
                           return (Iterable)((IterableOps)b.filter((x$11) -> BoxesRunTime.boxToBoolean($anonfun$multiply$11(leftColIndex, x$11)))).map((x0$5) -> {
                              if (x0$5 != null) {
                                 int rightColIndex = BoxesRunTime.unboxToInt(x0$5._2());
                                 Matrix rightBlock = (Matrix)x0$5._3();
                                 DenseMatrix var10000;
                                 if (rightBlock instanceof DenseMatrix) {
                                    DenseMatrix var10 = (DenseMatrix)rightBlock;
                                    var10000 = leftBlock.multiply(var10);
                                 } else {
                                    if (!(rightBlock instanceof SparseMatrix)) {
                                       throw new SparkException("Unrecognized matrix type " + rightBlock.getClass() + ".");
                                    }

                                    SparseMatrix var11 = (SparseMatrix)rightBlock;
                                    var10000 = leftBlock.multiply(var11.toDense());
                                 }

                                 DenseMatrix C = var10000;
                                 return new Tuple2(new Tuple2.mcII.sp(leftRowIndex, rightColIndex), C.asBreeze());
                              } else {
                                 throw new MatchError(x0$5);
                              }
                           });
                        } else {
                           throw new MatchError(x0$4);
                        }
                     });
                  }
               }

               throw new MatchError(x0$3);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(breeze.linalg.Matrix.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).reduceByKey(resultPartitioner, (a, b) -> (breeze.linalg.Matrix)a.$plus(b, breeze.linalg.operators.HasOps..MODULE$.op_M_DM_Double_OpAdd())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(breeze.linalg.Matrix.class), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$)).mapValues((breeze) -> Matrices$.MODULE$.fromBreeze(breeze));
            return new BlockMatrix(newBlocks, this.rowsPerBlock(), other.colsPerBlock(), this.numRows(), other.numCols());
         } else {
            throw new MatchError(var6);
         }
      } else {
         int var10002 = this.colsPerBlock();
         throw new SparkException("colsPerBlock of A doesn't match rowsPerBlock of B. A.colsPerBlock: " + var10002 + ", B.rowsPerBlock: " + other.rowsPerBlock());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$validate$3(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 key = (Tuple2)x0$1._1();
         long cnt = x0$1._2$mcJ$sp();
         if (cnt > 1L) {
            throw new SparkException("Found multiple MatrixBlocks with the indices " + key + ". Please remove blocks with duplicate indices.");
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$validate$5(final BlockMatrix $this, final String dimensionMsg$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Tuple2 var5 = (Tuple2)x0$2._1();
         Tuple2 var6 = (Tuple2)x0$2._2();
         if (var5 != null) {
            int blockRowIndex = var5._1$mcI$sp();
            int blockColIndex = var5._2$mcI$sp();
            if (var6 != null) {
               int m = var6._1$mcI$sp();
               int n = var6._2$mcI$sp();
               if ((blockRowIndex >= $this.numRowBlocks() - 1 || m == $this.rowsPerBlock()) && (blockRowIndex != $this.numRowBlocks() - 1 || m > 0 && m <= $this.rowsPerBlock())) {
                  if ((blockColIndex >= $this.numColBlocks() - 1 || n == $this.colsPerBlock()) && (blockColIndex != $this.numColBlocks() - 1 || n > 0 && n <= $this.colsPerBlock())) {
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                     return;
                  }

                  throw new SparkException("The MatrixBlock at (" + blockRowIndex + ", " + blockColIndex + ") has " + dimensionMsg$1);
               }

               throw new SparkException("The MatrixBlock at (" + blockRowIndex + ", " + blockColIndex + ") has " + dimensionMsg$1);
            }
         }
      }

      throw new MatchError(x0$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$toCoordinateMatrix$2(final ArrayBuffer entryValues$1, final long rowStart$1, final long colStart$1, final int i, final int j, final double v) {
      if (v != (double)0.0F) {
         entryValues$1.$plus$eq(new MatrixEntry(rowStart$1 + (long)i, colStart$1 + (long)j, v));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toIndexedRowMatrix$3(final Tuple2 x$2) {
      return ((Vector)x$2._1()).size() > 0;
   }

   // $FF: synthetic method
   public static final int $anonfun$toIndexedRowMatrix$6(final Tuple2 x$3) {
      return ((Vector)x$3._2()).numActives();
   }

   // $FF: synthetic method
   public static final void $anonfun$toIndexedRowMatrix$7(final BlockMatrix $this, final ArrayBuffer arrBufferIndices$1, final ArrayBuffer arrBufferValues$1, final Tuple2 x0$4) {
      if (x0$4 != null) {
         int blockColIdx = x0$4._1$mcI$sp();
         Vector vec = (Vector)x0$4._2();
         if (true && vec != null) {
            int offset = $this.colsPerBlock() * blockColIdx;
            vec.foreachNonZero((JFunction2.mcVID.sp)(colIdx, value) -> {
               arrBufferIndices$1.$plus$eq(BoxesRunTime.boxToInteger(offset + colIdx));
               arrBufferValues$1.$plus$eq(BoxesRunTime.boxToDouble(value));
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$4);
   }

   // $FF: synthetic method
   public static final void $anonfun$toLocalMatrix$6(final int colOffset$1, final int m$1, final int rowOffset$1, final double[] values$1, final int i, final int j, final double v) {
      int indexOffset = (j + colOffset$1) * m$1 + rowOffset$1 + i;
      values$1[indexOffset] = v;
   }

   // $FF: synthetic method
   public static final void $anonfun$toLocalMatrix$5(final BlockMatrix $this, final int m$1, final double[] values$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var6 = (Tuple2)x0$1._1();
         Matrix submat = (Matrix)x0$1._2();
         if (var6 != null) {
            int blockRowIndex = var6._1$mcI$sp();
            int blockColIndex = var6._2$mcI$sp();
            int rowOffset = blockRowIndex * $this.rowsPerBlock();
            int colOffset = blockColIndex * $this.colsPerBlock();
            submat.foreachActive((i, j, v) -> {
               $anonfun$toLocalMatrix$6(colOffset, m$1, rowOffset, values$1, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j), BoxesRunTime.unboxToDouble(v));
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$simulateMultiply$1(final Tuple2 x$4) {
      return x$4._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final int $anonfun$simulateMultiply$3(final Tuple2 x$6) {
      return x$6._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final int[] $anonfun$simulateMultiply$2(final int x$5, final Tuple2[] v) {
      return (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])v), (x$6) -> BoxesRunTime.boxToInteger($anonfun$simulateMultiply$3(x$6)), scala.reflect.ClassTag..MODULE$.Int());
   }

   // $FF: synthetic method
   public static final int $anonfun$simulateMultiply$8(final Tuple2 x$7) {
      return x$7._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final int $anonfun$simulateMultiply$10(final Tuple2 x$9) {
      return x$9._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final int[] $anonfun$simulateMultiply$9(final int x$8, final Tuple2[] v) {
      return (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])v), (x$9) -> BoxesRunTime.boxToInteger($anonfun$simulateMultiply$10(x$9)), scala.reflect.ClassTag..MODULE$.Int());
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$multiply$5(final int blockRowIndex$1, final int blockColIndex$1, final Matrix block$1, final int j) {
      return new Tuple2(BoxesRunTime.boxToInteger(j), new Tuple3(BoxesRunTime.boxToInteger(blockRowIndex$1), BoxesRunTime.boxToInteger(blockColIndex$1), block$1));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$multiply$8(final int blockRowIndex$2, final int blockColIndex$2, final Matrix block$2, final int j) {
      return new Tuple2(BoxesRunTime.boxToInteger(j), new Tuple3(BoxesRunTime.boxToInteger(blockRowIndex$2), BoxesRunTime.boxToInteger(blockColIndex$2), block$2));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$multiply$11(final int leftColIndex$1, final Tuple3 x$11) {
      return BoxesRunTime.unboxToInt(x$11._1()) == leftColIndex$1;
   }

   public BlockMatrix(final RDD blocks, final int rowsPerBlock, final int colsPerBlock, final long nRows, final long nCols) {
      this.blocks = blocks;
      this.rowsPerBlock = rowsPerBlock;
      this.colsPerBlock = colsPerBlock;
      this.nRows = nRows;
      this.nCols = nCols;
      super();
      Logging.$init$(this);
      this.numRowBlocks = (int)scala.math.package..MODULE$.ceil((double)this.numRows() * (double)1.0F / (double)rowsPerBlock);
      this.numColBlocks = (int)scala.math.package..MODULE$.ceil((double)this.numCols() * (double)1.0F / (double)colsPerBlock);
   }

   public BlockMatrix(final RDD blocks, final int rowsPerBlock, final int colsPerBlock) {
      this(blocks, rowsPerBlock, colsPerBlock, 0L, 0L);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
