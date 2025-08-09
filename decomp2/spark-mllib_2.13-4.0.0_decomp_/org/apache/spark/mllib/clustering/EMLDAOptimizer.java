package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseVector;
import breeze.linalg.NumericOps;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.TripletFields;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.graphx.util.PeriodicGraphCheckpointer;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005me\u0001B\u0012%\u0005=BQA\u000f\u0001\u0005\u0002mBq!\u0010\u0001A\u0002\u0013%a\bC\u0004C\u0001\u0001\u0007I\u0011B\"\t\r%\u0003\u0001\u0015)\u0003@\u0011\u0015Q\u0005\u0001\"\u0001?\u0011\u0015!\u0006\u0001\"\u0001V\u0011!I\u0006\u00011A\u0005\u0002\u0011R\u0006\u0002C6\u0001\u0001\u0004%\t\u0001\n7\t\r9\u0004\u0001\u0015)\u0003\\\u0011!y\u0007\u00011A\u0005\u0002\u0011\u0002\b\u0002\u0003;\u0001\u0001\u0004%\t\u0001J;\t\r]\u0004\u0001\u0015)\u0003r\u0011!A\b\u00011A\u0005\u0002\u0011\u0002\b\u0002C=\u0001\u0001\u0004%\t\u0001\n>\t\rq\u0004\u0001\u0015)\u0003r\u0011!i\b\u00011A\u0005\u0002\u0011r\bBCA\u0003\u0001\u0001\u0007I\u0011\u0001\u0013\u0002\b!9\u00111\u0002\u0001!B\u0013y\b\"CA\u0007\u0001\u0001\u0007I\u0011\u0001\u0013\u007f\u0011)\ty\u0001\u0001a\u0001\n\u0003!\u0013\u0011\u0003\u0005\b\u0003+\u0001\u0001\u0015)\u0003\u0000\u0011%\t9\u0002\u0001a\u0001\n\u0003!\u0003\u000f\u0003\u0006\u0002\u001a\u0001\u0001\r\u0011\"\u0001%\u00037Aq!a\b\u0001A\u0003&\u0011\u000fC\u0005\u0002\"\u0001\u0001\r\u0011\"\u0003\u0002$!I\u0011\u0011\u0007\u0001A\u0002\u0013%\u00111\u0007\u0005\t\u0003o\u0001\u0001\u0015)\u0003\u0002&!A\u0011\u0011\b\u0001\u0005B\u0011\nY\u0004C\u0004\u0002p\u0001!\t\u0005J\u001e\t\u0015\u0005E\u0004\u00011A\u0005\u0002\u0011\n\u0019\b\u0003\u0006\u0002v\u0001\u0001\r\u0011\"\u0001%\u0003oBq!a\u001f\u0001A\u0003&\u0011\rC\u0004\u0002~\u0001!I!a \t\u0011\u0005\u0005\u0005\u0001\"\u0011%\u0003\u0007\u0013a\"R'M\t\u0006{\u0005\u000f^5nSj,'O\u0003\u0002&M\u0005Q1\r\\;ti\u0016\u0014\u0018N\\4\u000b\u0005\u001dB\u0013!B7mY&\u0014'BA\u0015+\u0003\u0015\u0019\b/\u0019:l\u0015\tYC&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002[\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\r\u001c\u0011\u0005E\"T\"\u0001\u001a\u000b\u0003M\nQa]2bY\u0006L!!\u000e\u001a\u0003\r\u0005s\u0017PU3g!\t9\u0004(D\u0001%\u0013\tIDE\u0001\u0007M\t\u0006{\u0005\u000f^5nSj,'/\u0001\u0004=S:LGO\u0010\u000b\u0002yA\u0011q\u0007A\u0001\u0013W\u0016,\u0007\u000fT1ti\u000eCWmY6q_&tG/F\u0001@!\t\t\u0004)\u0003\u0002Be\t9!i\\8mK\u0006t\u0017AF6fKBd\u0015m\u001d;DQ\u0016\u001c7\u000e]8j]R|F%Z9\u0015\u0005\u0011;\u0005CA\u0019F\u0013\t1%G\u0001\u0003V]&$\bb\u0002%\u0004\u0003\u0003\u0005\raP\u0001\u0004q\u0012\n\u0014aE6fKBd\u0015m\u001d;DQ\u0016\u001c7\u000e]8j]R\u0004\u0013!F4fi.+W\r\u001d'bgR\u001c\u0005.Z2la>Lg\u000e\u001e\u0015\u0004\u000b1\u0013\u0006CA'Q\u001b\u0005q%BA()\u0003)\tgN\\8uCRLwN\\\u0005\u0003#:\u0013QaU5oG\u0016\f\u0013aU\u0001\u0006e9\u0002d\u0006M\u0001\u0016g\u0016$8*Z3q\u0019\u0006\u001cHo\u00115fG.\u0004x.\u001b8u)\t1v+D\u0001\u0001\u0011\u0015id\u00011\u0001@Q\r1AJU\u0001\u0006OJ\f\u0007\u000f[\u000b\u00027B!AlX1i\u001b\u0005i&B\u00010)\u0003\u00199'/\u00199iq&\u0011\u0001-\u0018\u0002\u0006\u000fJ\f\u0007\u000f\u001b\t\u0003E\u0016t!aN2\n\u0005\u0011$\u0013a\u0001'E\u0003&\u0011am\u001a\u0002\f)>\u0004\u0018nY\"pk:$8O\u0003\u0002eIA\u0011!-[\u0005\u0003U\u001e\u0014!\u0002V8lK:\u001cu.\u001e8u\u0003%9'/\u00199i?\u0012*\u0017\u000f\u0006\u0002E[\"9\u0001\nCA\u0001\u0002\u0004Y\u0016AB4sCBD\u0007%A\u0001l+\u0005\t\bCA\u0019s\u0013\t\u0019(GA\u0002J]R\fQa[0%KF$\"\u0001\u0012<\t\u000f![\u0011\u0011!a\u0001c\u0006\u00111\u000eI\u0001\nm>\u001c\u0017MY*ju\u0016\fQB^8dC\n\u001c\u0016N_3`I\u0015\fHC\u0001#|\u0011\u001dAe\"!AA\u0002E\f!B^8dC\n\u001c\u0016N_3!\u0003A!wnY\"p]\u000e,g\u000e\u001e:bi&|g.F\u0001\u0000!\r\t\u0014\u0011A\u0005\u0004\u0003\u0007\u0011$A\u0002#pk\ndW-\u0001\u000be_\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]~#S-\u001d\u000b\u0004\t\u0006%\u0001b\u0002%\u0012\u0003\u0003\u0005\ra`\u0001\u0012I>\u001c7i\u001c8dK:$(/\u0019;j_:\u0004\u0013A\u0005;pa&\u001c7i\u001c8dK:$(/\u0019;j_:\fa\u0003^8qS\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]~#S-\u001d\u000b\u0004\t\u0006M\u0001b\u0002%\u0015\u0003\u0003\u0005\ra`\u0001\u0014i>\u0004\u0018nY\"p]\u000e,g\u000e\u001e:bi&|g\u000eI\u0001\u0013G\",7m\u001b9pS:$\u0018J\u001c;feZ\fG.\u0001\fdQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006dw\fJ3r)\r!\u0015Q\u0004\u0005\b\u0011^\t\t\u00111\u0001r\u0003M\u0019\u0007.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197!\u0003E9'/\u00199i\u0007\",7m\u001b9pS:$XM]\u000b\u0003\u0003K\u0001b!a\n\u0002.\u0005DWBAA\u0015\u0015\r\tY#X\u0001\u0005kRLG.\u0003\u0003\u00020\u0005%\"!\u0007)fe&|G-[2He\u0006\u0004\bn\u00115fG.\u0004x.\u001b8uKJ\fQc\u001a:ba\"\u001c\u0005.Z2la>Lg\u000e^3s?\u0012*\u0017\u000fF\u0002E\u0003kA\u0001\u0002\u0013\u000e\u0002\u0002\u0003\u0007\u0011QE\u0001\u0013OJ\f\u0007\u000f[\"iK\u000e\\\u0007o\\5oi\u0016\u0014\b%\u0001\u0006j]&$\u0018.\u00197ju\u0016$R\u0001PA\u001f\u0003KBq!a\u0010\u001d\u0001\u0004\t\t%\u0001\u0003e_\u000e\u001c\bCBA\"\u0003\u0013\ni%\u0004\u0002\u0002F)\u0019\u0011q\t\u0015\u0002\u0007I$G-\u0003\u0003\u0002L\u0005\u0015#a\u0001*E\tB9\u0011'a\u0014\u0002T\u0005e\u0013bAA)e\t1A+\u001e9mKJ\u00022!MA+\u0013\r\t9F\r\u0002\u0005\u0019>tw\r\u0005\u0003\u0002\\\u0005\u0005TBAA/\u0015\r\tyFJ\u0001\u0007Y&t\u0017\r\\4\n\t\u0005\r\u0014Q\f\u0002\u0007-\u0016\u001cGo\u001c:\t\u000f\u0005\u001dD\u00041\u0001\u0002j\u0005\u0019A\u000eZ1\u0011\u0007]\nY'C\u0002\u0002n\u0011\u00121\u0001\u0014#B\u0003\u0011qW\r\u001f;\u0002#\u001ddwNY1m)>\u0004\u0018n\u0019+pi\u0006d7/F\u0001b\u0003U9Gn\u001c2bYR{\u0007/[2U_R\fGn]0%KF$2\u0001RA=\u0011\u001dAu$!AA\u0002\u0005\f!c\u001a7pE\u0006dGk\u001c9jGR{G/\u00197tA\u0005A2m\\7qkR,w\t\\8cC2$v\u000e]5d)>$\u0018\r\\:\u0015\u0003\u0005\f1bZ3u\u0019\u0012\u000bUj\u001c3fYR!\u0011QQAF!\r9\u0014qQ\u0005\u0004\u0003\u0013##\u0001\u0003'E\u00036{G-\u001a7\t\u000f\u00055%\u00051\u0001\u0002\u0010\u0006q\u0011\u000e^3sCRLwN\u001c+j[\u0016\u001c\b\u0003B\u0019\u0002\u0012~L1!a%3\u0005\u0015\t%O]1zQ\u0011\u0001A*a&\"\u0005\u0005e\u0015!B\u0019/i9\u0002\u0004"
)
public final class EMLDAOptimizer implements LDAOptimizer {
   private boolean keepLastCheckpoint = true;
   private Graph graph = null;
   private int k = 0;
   private int vocabSize = 0;
   private double docConcentration = (double)0.0F;
   private double topicConcentration = (double)0.0F;
   private int checkpointInterval = 10;
   private PeriodicGraphCheckpointer graphCheckpointer = null;
   private DenseVector globalTopicTotals = null;

   private boolean keepLastCheckpoint() {
      return this.keepLastCheckpoint;
   }

   private void keepLastCheckpoint_$eq(final boolean x$1) {
      this.keepLastCheckpoint = x$1;
   }

   public boolean getKeepLastCheckpoint() {
      return this.keepLastCheckpoint();
   }

   public EMLDAOptimizer setKeepLastCheckpoint(final boolean keepLastCheckpoint) {
      this.keepLastCheckpoint_$eq(keepLastCheckpoint);
      return this;
   }

   public Graph graph() {
      return this.graph;
   }

   public void graph_$eq(final Graph x$1) {
      this.graph = x$1;
   }

   public int k() {
      return this.k;
   }

   public void k_$eq(final int x$1) {
      this.k = x$1;
   }

   public int vocabSize() {
      return this.vocabSize;
   }

   public void vocabSize_$eq(final int x$1) {
      this.vocabSize = x$1;
   }

   public double docConcentration() {
      return this.docConcentration;
   }

   public void docConcentration_$eq(final double x$1) {
      this.docConcentration = x$1;
   }

   public double topicConcentration() {
      return this.topicConcentration;
   }

   public void topicConcentration_$eq(final double x$1) {
      this.topicConcentration = x$1;
   }

   public int checkpointInterval() {
      return this.checkpointInterval;
   }

   public void checkpointInterval_$eq(final int x$1) {
      this.checkpointInterval = x$1;
   }

   private PeriodicGraphCheckpointer graphCheckpointer() {
      return this.graphCheckpointer;
   }

   private void graphCheckpointer_$eq(final PeriodicGraphCheckpointer x$1) {
      this.graphCheckpointer = x$1;
   }

   public EMLDAOptimizer initialize(final RDD docs, final LDA lda) {
      double docConcentration = lda.getDocConcentration();
      double topicConcentration = lda.getTopicConcentration();
      int k = lda.getK();
      .MODULE$.require(docConcentration > (double)1.0F || docConcentration == (double)-1.0F, () -> "LDA docConcentration must be > 1.0 (or -1 for auto) for EM Optimizer, but was set to " + docConcentration);
      .MODULE$.require(topicConcentration > (double)1.0F || topicConcentration == (double)-1.0F, () -> "LDA topicConcentration must be > 1.0 (or -1 for auto) for EM Optimizer, but was set to " + topicConcentration);
      this.docConcentration_$eq(docConcentration == (double)-1 ? (double)50.0F / (double)k + (double)1.0F : docConcentration);
      this.topicConcentration_$eq(topicConcentration == (double)-1 ? 1.1 : topicConcentration);
      long randomSeed = lda.getSeed();
      RDD edges = docs.flatMap((x0$1) -> {
         if (x0$1 != null) {
            long docID = x0$1._1$mcJ$sp();
            Vector termCounts = (Vector)x0$1._2();
            if (true && termCounts != null) {
               return termCounts.nonZeroIterator().map((x0$2) -> {
                  if (x0$2 != null) {
                     int term = x0$2._1$mcI$sp();
                     double cnt = x0$2._2$mcD$sp();
                     return new Edge.mcD.sp(docID, LDA$.MODULE$.term2index(term), cnt);
                  } else {
                     throw new MatchError(x0$2);
                  }
               });
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Edge.class));
      RDD verticesTMP = edges.mapPartitionsWithIndex((x0$3, x1$1) -> $anonfun$initialize$5(randomSeed, k, BoxesRunTime.unboxToInt(x0$3), x1$1), edges.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      RDD docTermVertices = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(verticesTMP, scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.math.Ordering.Long..MODULE$).reduceByKey((x$1, x$2) -> (DenseVector)x$1.$plus(x$2, breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_DV_DV_eq_DV_Double()));
      this.graph_$eq(org.apache.spark.graphx.Graph..MODULE$.apply(docTermVertices, edges, org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Double.TYPE)).partitionBy(org.apache.spark.graphx.PartitionStrategy.EdgePartition1D..MODULE$));
      this.k_$eq(k);
      this.vocabSize_$eq(((Vector)((Tuple2)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(docs.take(1))))._2()).size());
      this.checkpointInterval_$eq(lda.getCheckpointInterval());
      this.graphCheckpointer_$eq(new PeriodicGraphCheckpointer(this.checkpointInterval(), this.graph().vertices().sparkContext()));
      this.graphCheckpointer().update(this.graph());
      this.globalTopicTotals_$eq(this.computeGlobalTopicTotals());
      return this;
   }

   public EMLDAOptimizer next() {
      .MODULE$.require(this.graph() != null, () -> "graph is null, EMLDAOptimizer not initialized.");
      double eta = this.topicConcentration();
      int W = this.vocabSize();
      double alpha = this.docConcentration();
      DenseVector N_k = this.globalTopicTotals();
      Function1 sendMsg = (edgeContext) -> {
         $anonfun$next$2(N_k, W, eta, alpha, edgeContext);
         return BoxedUnit.UNIT;
      };
      Function2 mergeMsg = (m0, m1) -> {
         DenseVector sum = m0._1$mcZ$sp() ? (DenseVector)((NumericOps)m0._2()).$plus$eq(m1._2(), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double()) : (m1._1$mcZ$sp() ? (DenseVector)((NumericOps)m1._2()).$plus$eq(m0._2(), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double()) : (DenseVector)((NumericOps)m0._2()).$plus(m1._2(), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_DV_DV_eq_DV_Double()));
         return new Tuple2(BoxesRunTime.boxToBoolean(true), sum);
      };
      Graph qual$1 = this.graph();
      TripletFields x$3 = qual$1.aggregateMessages$default$3();
      VertexRDD docTopicDistributions = qual$1.aggregateMessages(sendMsg, mergeMsg, x$3, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).mapValues((x$3x) -> (DenseVector)x$3x._2(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      Graph prevGraph = this.graph();
      Graph newGraph = org.apache.spark.graphx.Graph..MODULE$.apply(docTopicDistributions, this.graph().edges(), org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Double.TYPE));
      this.graph_$eq(newGraph);
      this.graphCheckpointer().update(newGraph);
      this.globalTopicTotals_$eq(this.computeGlobalTopicTotals());
      prevGraph.unpersistVertices(prevGraph.unpersistVertices$default$1());
      prevGraph.edges().unpersist(prevGraph.edges().unpersist$default$1());
      return this;
   }

   public DenseVector globalTopicTotals() {
      return this.globalTopicTotals;
   }

   public void globalTopicTotals_$eq(final DenseVector x$1) {
      this.globalTopicTotals = x$1;
   }

   private DenseVector computeGlobalTopicTotals() {
      int numTopics = this.k();
      return (DenseVector)org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.graph().vertices().filter((v) -> BoxesRunTime.boxToBoolean($anonfun$computeGlobalTopicTotals$1(v))), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.math.Ordering.Long..MODULE$).values().fold(breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(numTopics, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()), (x$4, x$5) -> (DenseVector)x$4.$plus$eq(x$5, breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double()));
   }

   public LDAModel getLDAModel(final double[] iterationTimes) {
      .MODULE$.require(this.graph() != null, () -> "graph is null, EMLDAOptimizer not initialized.");
      String[] var10000;
      if (this.keepLastCheckpoint()) {
         this.graphCheckpointer().deleteAllCheckpointsButLast();
         var10000 = this.graphCheckpointer().getAllCheckpointFiles();
      } else {
         this.graphCheckpointer().deleteAllCheckpoints();
         var10000 = (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
      }

      String[] checkpointFiles = var10000;
      return new DistributedLDAModel(this.graph(), this.globalTopicTotals(), this.k(), this.vocabSize(), Vectors$.MODULE$.dense((double[])scala.Array..MODULE$.fill(this.k(), (JFunction0.mcD.sp)() -> this.docConcentration(), scala.reflect.ClassTag..MODULE$.Double())), this.topicConcentration(), iterationTimes, DistributedLDAModel$.MODULE$.defaultGammaShape(), checkpointFiles);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$initialize$5(final long randomSeed$1, final int k$1, final int x0$3, final Iterator x1$1) {
      Tuple2 var6 = new Tuple2(BoxesRunTime.boxToInteger(x0$3), x1$1);
      if (var6 != null) {
         int partIndex = var6._1$mcI$sp();
         Iterator partEdges = (Iterator)var6._2();
         Random random = new Random((long)partIndex + randomSeed$1);
         return partEdges.flatMap((edge) -> {
            DenseVector gamma = (DenseVector)breeze.linalg.normalize..MODULE$.apply(breeze.linalg.DenseVector..MODULE$.fill(k$1, (JFunction0.mcD.sp)() -> random.nextDouble(), scala.reflect.ClassTag..MODULE$.Double()), BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())));
            DenseVector sum = (DenseVector)gamma.$times(BoxesRunTime.boxToDouble(edge.attr$mcD$sp()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulMatrix());
            return new scala.collection.immutable..colon.colon(new Tuple2(BoxesRunTime.boxToLong(edge.srcId()), sum), new scala.collection.immutable..colon.colon(new Tuple2(BoxesRunTime.boxToLong(edge.dstId()), sum), scala.collection.immutable.Nil..MODULE$));
         });
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$next$2(final DenseVector N_k$1, final int W$1, final double eta$1, final double alpha$1, final EdgeContext edgeContext) {
      double N_wj = BoxesRunTime.unboxToDouble(edgeContext.attr());
      DenseVector scaledTopicDistribution = (DenseVector)LDA$.MODULE$.computePTopic((DenseVector)edgeContext.srcAttr(), (DenseVector)edgeContext.dstAttr(), N_k$1, W$1, eta$1, alpha$1).$times$eq(BoxesRunTime.boxToDouble(N_wj), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
      edgeContext.sendToDst(new Tuple2(BoxesRunTime.boxToBoolean(false), scaledTopicDistribution));
      edgeContext.sendToSrc(new Tuple2(BoxesRunTime.boxToBoolean(false), scaledTopicDistribution));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$computeGlobalTopicTotals$1(final Tuple2 v) {
      return LDA$.MODULE$.isTermVertex(v);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
