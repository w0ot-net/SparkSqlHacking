package org.apache.spark.ml.ann;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.optimization.Gradient;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.LBFGS;
import org.apache.spark.mllib.optimization.Optimizer;
import org.apache.spark.mllib.optimization.Updater;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mf!\u0002\u0015*\u0001-\u001a\u0004\u0002C$\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u00111\u0003!Q1A\u0005\u00025C\u0001\"\u0015\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\t%\u0002\u0011)\u0019!C\u0001\u001b\"A1\u000b\u0001B\u0001B\u0003%a\nC\u0003U\u0001\u0011\u0005Q\u000bC\u0004[\u0001\u0001\u0007I\u0011B.\t\u000f}\u0003\u0001\u0019!C\u0005A\"1a\r\u0001Q!\nqCqa\u001a\u0001A\u0002\u0013%\u0001\u000eC\u0004p\u0001\u0001\u0007I\u0011\u00029\t\rI\u0004\u0001\u0015)\u0003j\u0011\u001d\u0019\b\u00011A\u0005\n5Cq\u0001\u001e\u0001A\u0002\u0013%Q\u000f\u0003\u0004x\u0001\u0001\u0006KA\u0014\u0005\bq\u0002\u0001\r\u0011\"\u0003z\u0011\u001di\b\u00011A\u0005\nyDq!!\u0001\u0001A\u0003&!\u0010C\u0005\u0002\u0004\u0001\u0001\r\u0011\"\u0003\u0002\u0006!I\u0011q\u0003\u0001A\u0002\u0013%\u0011\u0011\u0004\u0005\t\u0003;\u0001\u0001\u0015)\u0003\u0002\b!I\u0011q\u0004\u0001A\u0002\u0013%\u0011\u0011\u0005\u0005\n\u0003S\u0001\u0001\u0019!C\u0005\u0003WA\u0001\"a\f\u0001A\u0003&\u00111\u0005\u0005\n\u0003c\u0001\u0001\u0019!C\u0005\u0003gA\u0011\"a\u000f\u0001\u0001\u0004%I!!\u0010\t\u0011\u0005\u0005\u0003\u0001)Q\u0005\u0003kAa!a\u0011\u0001\t\u0003Y\u0006bBA#\u0001\u0011\u0005\u0011q\t\u0005\u0007\u0003\u001f\u0002A\u0011\u00015\t\u000f\u0005E\u0003\u0001\"\u0001\u0002T!9\u0011q\u000b\u0001\u0005\u0002\u0005e\u0003bBA/\u0001\u0011\u0005\u0011q\f\u0005\b\u0003O\u0002A\u0011AA5\u0011\u001d\t\t\b\u0001C\u0001\u0003gBq!a\u001e\u0001\t\u0003\tI\b\u0003\u0005\u0002~\u0001\u0001K\u0011BA@\u0011!\t)\t\u0001Q\u0005\n\u0005\u001d\u0005bBAG\u0001\u0011\u0005\u0011q\u0012\u0002\u0013\r\u0016,GMR8so\u0006\u0014H\r\u0016:bS:,'O\u0003\u0002+W\u0005\u0019\u0011M\u001c8\u000b\u00051j\u0013AA7m\u0015\tqs&A\u0003ta\u0006\u00148N\u0003\u00021c\u00051\u0011\r]1dQ\u0016T\u0011AM\u0001\u0004_J<7c\u0001\u00015uA\u0011Q\u0007O\u0007\u0002m)\tq'A\u0003tG\u0006d\u0017-\u0003\u0002:m\t1\u0011I\\=SK\u001a\u0004\"a\u000f#\u000f\u0005q\u0012eBA\u001fB\u001b\u0005q$BA A\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u001c\n\u0005\r3\u0014a\u00029bG.\fw-Z\u0005\u0003\u000b\u001a\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!a\u0011\u001c\u0002\u0011Q|\u0007o\u001c7pOf\u0004\"!\u0013&\u000e\u0003%J!aS\u0015\u0003\u0011Q{\u0007o\u001c7pOf\f\u0011\"\u001b8qkR\u001c\u0016N_3\u0016\u00039\u0003\"!N(\n\u0005A3$aA%oi\u0006Q\u0011N\u001c9viNK'0\u001a\u0011\u0002\u0015=,H\u000f];u'&TX-A\u0006pkR\u0004X\u000f^*ju\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003W/bK\u0006CA%\u0001\u0011\u00159e\u00011\u0001I\u0011\u0015ae\u00011\u0001O\u0011\u0015\u0011f\u00011\u0001O\u0003\u0015y6/Z3e+\u0005a\u0006CA\u001b^\u0013\tqfG\u0001\u0003M_:<\u0017!C0tK\u0016$w\fJ3r)\t\tG\r\u0005\u00026E&\u00111M\u000e\u0002\u0005+:LG\u000fC\u0004f\u0011\u0005\u0005\t\u0019\u0001/\u0002\u0007a$\u0013'\u0001\u0004`g\u0016,G\rI\u0001\t?^,\u0017n\u001a5ugV\t\u0011\u000e\u0005\u0002k[6\t1N\u0003\u0002mW\u00051A.\u001b8bY\u001eL!A\\6\u0003\rY+7\r^8s\u00031yv/Z5hQR\u001cx\fJ3r)\t\t\u0017\u000fC\u0004f\u0017\u0005\u0005\t\u0019A5\u0002\u0013};X-[4iiN\u0004\u0013AC0ti\u0006\u001c7nU5{K\u0006qql\u001d;bG.\u001c\u0016N_3`I\u0015\fHCA1w\u0011\u001d)g\"!AA\u00029\u000b1bX:uC\u000e\\7+\u001b>fA\u0005YA-\u0019;b'R\f7m[3s+\u0005Q\bCA%|\u0013\ta\u0018FA\u0006ECR\f7\u000b^1dW\u0016\u0014\u0018a\u00043bi\u0006\u001cF/Y2lKJ|F%Z9\u0015\u0005\u0005|\bbB3\u0012\u0003\u0003\u0005\rA_\u0001\rI\u0006$\u0018m\u0015;bG.,'\u000fI\u0001\n?\u001e\u0014\u0018\rZ5f]R,\"!a\u0002\u0011\t\u0005%\u00111C\u0007\u0003\u0003\u0017QA!!\u0004\u0002\u0010\u0005aq\u000e\u001d;j[&T\u0018\r^5p]*\u0019\u0011\u0011C\u0017\u0002\u000b5dG.\u001b2\n\t\u0005U\u00111\u0002\u0002\t\u000fJ\fG-[3oi\u0006iql\u001a:bI&,g\u000e^0%KF$2!YA\u000e\u0011!)G#!AA\u0002\u0005\u001d\u0011AC0he\u0006$\u0017.\u001a8uA\u0005Aq,\u001e9eCR,'/\u0006\u0002\u0002$A!\u0011\u0011BA\u0013\u0013\u0011\t9#a\u0003\u0003\u000fU\u0003H-\u0019;fe\u0006aq,\u001e9eCR,'o\u0018\u0013fcR\u0019\u0011-!\f\t\u0011\u0015<\u0012\u0011!a\u0001\u0003G\t\u0011bX;qI\u0006$XM\u001d\u0011\u0002\u0013=\u0004H/[7ju\u0016\u0014XCAA\u001b!\u0011\tI!a\u000e\n\t\u0005e\u00121\u0002\u0002\n\u001fB$\u0018.\\5{KJ\fQb\u001c9uS6L'0\u001a:`I\u0015\fHcA1\u0002@!AQMGA\u0001\u0002\u0004\t)$\u0001\u0006paRLW.\u001b>fe\u0002\nqaZ3u'\u0016,G-A\u0004tKR\u001cV-\u001a3\u0015\t\u0005%\u00131J\u0007\u0002\u0001!1\u0011QJ\u000fA\u0002q\u000bQA^1mk\u0016\f!bZ3u/\u0016Lw\r\u001b;t\u0003)\u0019X\r^,fS\u001eDGo\u001d\u000b\u0005\u0003\u0013\n)\u0006\u0003\u0004\u0002N}\u0001\r![\u0001\rg\u0016$8\u000b^1dWNK'0\u001a\u000b\u0005\u0003\u0013\nY\u0006\u0003\u0004\u0002N\u0001\u0002\rAT\u0001\r'\u001e#u\n\u001d;j[&TXM]\u000b\u0003\u0003C\u0002B!!\u0003\u0002d%!\u0011QMA\u0006\u0005=9%/\u00193jK:$H)Z:dK:$\u0018A\u0004'C\r\u001e\u001bv\n\u001d;j[&TXM]\u000b\u0003\u0003W\u0002B!!\u0003\u0002n%!\u0011qNA\u0006\u0005\u0015a%IR$T\u0003)\u0019X\r^+qI\u0006$XM\u001d\u000b\u0005\u0003\u0013\n)\bC\u0004\u0002N\r\u0002\r!a\t\u0002\u0017M,Go\u0012:bI&,g\u000e\u001e\u000b\u0005\u0003\u0013\nY\bC\u0004\u0002N\u0011\u0002\r!a\u0002\u0002\u001dU\u0004H-\u0019;f\u000fJ\fG-[3oiR\u0019\u0011-!!\t\u000f\u0005\rU\u00051\u0001\u0002\b\u0005AqM]1eS\u0016tG/A\u0007va\u0012\fG/Z+qI\u0006$XM\u001d\u000b\u0004C\u0006%\u0005bBAFM\u0001\u0007\u00111E\u0001\bkB$\u0017\r^3s\u0003\u0015!(/Y5o)\u0011\t\t*!+\u0011\u000fU\n\u0019*a&\u0002\u001e&\u0019\u0011Q\u0013\u001c\u0003\rQ+\b\u000f\\33!\rI\u0015\u0011T\u0005\u0004\u00037K#!\u0004+pa>dwnZ=N_\u0012,G\u000eE\u00036\u0003?\u000b\u0019+C\u0002\u0002\"Z\u0012Q!\u0011:sCf\u00042!NAS\u0013\r\t9K\u000e\u0002\u0007\t>,(\r\\3\t\u000f\u0005-v\u00051\u0001\u0002.\u0006!A-\u0019;b!\u0019\ty+!.\u0002:6\u0011\u0011\u0011\u0017\u0006\u0004\u0003gk\u0013a\u0001:eI&!\u0011qWAY\u0005\r\u0011F\t\u0012\t\u0006k\u0005M\u0015.\u001b"
)
public class FeedForwardTrainer implements Serializable {
   private final Topology topology;
   private final int inputSize;
   private final int outputSize;
   private long _seed;
   private Vector _weights;
   private int _stackSize;
   private DataStacker dataStacker;
   private Gradient _gradient;
   private Updater _updater;
   private Optimizer optimizer;

   public int inputSize() {
      return this.inputSize;
   }

   public int outputSize() {
      return this.outputSize;
   }

   private long _seed() {
      return this._seed;
   }

   private void _seed_$eq(final long x$1) {
      this._seed = x$1;
   }

   private Vector _weights() {
      return this._weights;
   }

   private void _weights_$eq(final Vector x$1) {
      this._weights = x$1;
   }

   private int _stackSize() {
      return this._stackSize;
   }

   private void _stackSize_$eq(final int x$1) {
      this._stackSize = x$1;
   }

   private DataStacker dataStacker() {
      return this.dataStacker;
   }

   private void dataStacker_$eq(final DataStacker x$1) {
      this.dataStacker = x$1;
   }

   private Gradient _gradient() {
      return this._gradient;
   }

   private void _gradient_$eq(final Gradient x$1) {
      this._gradient = x$1;
   }

   private Updater _updater() {
      return this._updater;
   }

   private void _updater_$eq(final Updater x$1) {
      this._updater = x$1;
   }

   private Optimizer optimizer() {
      return this.optimizer;
   }

   private void optimizer_$eq(final Optimizer x$1) {
      this.optimizer = x$1;
   }

   public long getSeed() {
      return this._seed();
   }

   public FeedForwardTrainer setSeed(final long value) {
      this._seed_$eq(value);
      return this;
   }

   public Vector getWeights() {
      return this._weights();
   }

   public FeedForwardTrainer setWeights(final Vector value) {
      this._weights_$eq(value);
      return this;
   }

   public FeedForwardTrainer setStackSize(final int value) {
      this._stackSize_$eq(value);
      this.dataStacker_$eq(new DataStacker(value, this.inputSize(), this.outputSize()));
      return this;
   }

   public GradientDescent SGDOptimizer() {
      GradientDescent sgd = new GradientDescent(this._gradient(), this._updater());
      this.optimizer_$eq(sgd);
      return sgd;
   }

   public LBFGS LBFGSOptimizer() {
      LBFGS lbfgs = new LBFGS(this._gradient(), this._updater());
      this.optimizer_$eq(lbfgs);
      return lbfgs;
   }

   public FeedForwardTrainer setUpdater(final Updater value) {
      this._updater_$eq(value);
      this.updateUpdater(value);
      return this;
   }

   public FeedForwardTrainer setGradient(final Gradient value) {
      this._gradient_$eq(value);
      this.updateGradient(value);
      return this;
   }

   private void updateGradient(final Gradient gradient) {
      Optimizer var3 = this.optimizer();
      if (var3 instanceof LBFGS var4) {
         var4.setGradient(gradient);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (var3 instanceof GradientDescent var5) {
         var5.setGradient(gradient);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new UnsupportedOperationException("Only LBFGS and GradientDescent are supported but got " + var3.getClass() + ".");
      }
   }

   private void updateUpdater(final Updater updater) {
      Optimizer var3 = this.optimizer();
      if (var3 instanceof LBFGS var4) {
         var4.setUpdater(updater);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (var3 instanceof GradientDescent var5) {
         var5.setUpdater(updater);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new UnsupportedOperationException("Only LBFGS and GradientDescent are supported but got " + var3.getClass() + ".");
      }
   }

   public Tuple2 train(final RDD data) {
      Vector w;
      RDD trainData;
      boolean var17;
      label48: {
         label47: {
            w = this.getWeights() == null ? this.topology.model(this._seed()).weights() : this.getWeights();
            trainData = this.dataStacker().stack(data).map((v) -> new Tuple2(BoxesRunTime.boxToDouble(v._1$mcD$sp()), Vectors$.MODULE$.fromML((Vector)v._2())), .MODULE$.apply(Tuple2.class));
            StorageLevel var10000 = trainData.getStorageLevel();
            StorageLevel var7 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var10000 == null) {
               if (var7 == null) {
                  break label47;
               }
            } else if (var10000.equals(var7)) {
               break label47;
            }

            var17 = false;
            break label48;
         }

         var17 = true;
      }

      boolean handlePersistence = var17;
      if (handlePersistence) {
         trainData.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      } else {
         BoxedUnit var18 = BoxedUnit.UNIT;
      }

      Optimizer var10 = this.optimizer();
      Tuple2 var19;
      if (var10 instanceof LBFGS var11) {
         var19 = var11.optimizeWithLossReturned(trainData, VectorImplicits$.MODULE$.mlVectorToMLlibVector(w));
      } else {
         if (!(var10 instanceof GradientDescent)) {
            throw new UnsupportedOperationException("Only LBFGS and GradientDescent are supported but got " + var10.getClass() + ".");
         }

         GradientDescent var12 = (GradientDescent)var10;
         var19 = var12.optimizeWithLossReturned(trainData, VectorImplicits$.MODULE$.mlVectorToMLlibVector(w));
      }

      Tuple2 var9 = var19;
      if (var9 != null) {
         org.apache.spark.mllib.linalg.Vector newWeights = (org.apache.spark.mllib.linalg.Vector)var9._1();
         double[] lossHistory = (double[])var9._2();
         Tuple2 var8 = new Tuple2(newWeights, lossHistory);
         org.apache.spark.mllib.linalg.Vector newWeights = (org.apache.spark.mllib.linalg.Vector)var8._1();
         double[] lossHistory = (double[])var8._2();
         if (handlePersistence) {
            trainData.unpersist(trainData.unpersist$default$1());
         } else {
            BoxedUnit var20 = BoxedUnit.UNIT;
         }

         return new Tuple2(this.topology.model(VectorImplicits$.MODULE$.mllibVectorToMLVector(newWeights)), lossHistory);
      } else {
         throw new MatchError(var9);
      }
   }

   public FeedForwardTrainer(final Topology topology, final int inputSize, final int outputSize) {
      this.topology = topology;
      this.inputSize = inputSize;
      this.outputSize = outputSize;
      this._seed = (long)this.getClass().getName().hashCode();
      this._weights = null;
      this._stackSize = 128;
      this.dataStacker = new DataStacker(this._stackSize(), inputSize, outputSize);
      this._gradient = new ANNGradient(topology, this.dataStacker());
      this._updater = new ANNUpdater();
      this.optimizer = this.LBFGSOptimizer().setConvergenceTol(1.0E-4).setNumIterations(100);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
