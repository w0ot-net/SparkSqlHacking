package org.apache.spark.mllib.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Aggregator;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext.;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.BoundedPriorityQueue;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua\u0001B\u0006\r\u0001]A\u0001b\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\t\u0005\u0002\u0011\u0019\u0011)A\u0006\u0007\"A\u0011\n\u0001B\u0002B\u0003-!\nC\u0003L\u0001\u0011\u0005A\nC\u0003T\u0001\u0011\u0005AkB\u0003f\u0019!\u0005aMB\u0003\f\u0019!\u0005q\rC\u0003L\u000f\u0011\u0005q\u000eC\u0003q\u000f\u0011\r\u0011\u000fC\u0005\u0002\u0006\u001d\t\t\u0011\"\u0003\u0002\b\t\u0011R\n\u0014)bSJ\u0014F\t\u0012$v]\u000e$\u0018n\u001c8t\u0015\tia\"A\u0002sI\u0012T!a\u0004\t\u0002\u000b5dG.\u001b2\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c\u0001!F\u0002\u0019m\u0001\u001b2\u0001A\r !\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0011\u0001\u0005\u000b\b\u0003C\u0019r!AI\u0013\u000e\u0003\rR!\u0001\n\f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012BA\u0014\u001c\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000b\u0016\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u001dZ\u0012\u0001B:fY\u001a\u00042!L\u00182\u001b\u0005q#BA\u0007\u0011\u0013\t\u0001dFA\u0002S\t\u0012\u0003BA\u0007\u001a5\u007f%\u00111g\u0007\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005U2D\u0002\u0001\u0003\u0006o\u0001\u0011\r\u0001\u000f\u0002\u0002\u0017F\u0011\u0011\b\u0010\t\u00035iJ!aO\u000e\u0003\u000f9{G\u000f[5oOB\u0011!$P\u0005\u0003}m\u00111!\u00118z!\t)\u0004\tB\u0003B\u0001\t\u0007\u0001HA\u0001W\u0003))g/\u001b3f]\u000e,G%\r\t\u0004\t\u001e#T\"A#\u000b\u0005\u0019[\u0012a\u0002:fM2,7\r^\u0005\u0003\u0011\u0016\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001#H\u007f\u00051A(\u001b8jiz\"\"!\u0014*\u0015\u00079\u0003\u0016\u000b\u0005\u0003P\u0001QzT\"\u0001\u0007\t\u000b\t#\u00019A\"\t\u000b%#\u00019\u0001&\t\u000b-\"\u0001\u0019\u0001\u0017\u0002\u0011Q|\u0007OQ=LKf$\"!\u00161\u0015\u0005Y[\u0006cA\u00170/B!!D\r\u001bY!\rQ\u0012lP\u0005\u00035n\u0011Q!\u0011:sCfDQ\u0001X\u0003A\u0004u\u000b1a\u001c:e!\r\u0001clP\u0005\u0003?*\u0012\u0001b\u0014:eKJLgn\u001a\u0005\u0006C\u0016\u0001\rAY\u0001\u0004]Vl\u0007C\u0001\u000ed\u0013\t!7DA\u0002J]R\f!#\u0014'QC&\u0014(\u000b\u0012#Gk:\u001cG/[8ogB\u0011qjB\n\u0004\u000feA\u0007CA5o\u001b\u0005Q'BA6m\u0003\tIwNC\u0001n\u0003\u0011Q\u0017M^1\n\u0005%RG#\u00014\u0002\u0017\u0019\u0014x.\u001c)bSJ\u0014F\tR\u000b\u0004eZDHCA:\u0000)\r!\u0018\u0010 \t\u0005\u001f\u0002)x\u000f\u0005\u00026m\u0012)q'\u0003b\u0001qA\u0011Q\u0007\u001f\u0003\u0006\u0003&\u0011\r\u0001\u000f\u0005\bu&\t\t\u0011q\u0001|\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004\t\u001e+\bbB?\n\u0003\u0003\u0005\u001dA`\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004c\u0001#Ho\"1Q\"\u0003a\u0001\u0003\u0003\u0001B!L\u0018\u0002\u0004A!!DM;x\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0001\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\ty\u0001\\\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0014\u00055!AB(cU\u0016\u001cG\u000f"
)
public class MLPairRDDFunctions implements Serializable {
   private final RDD self;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;

   public static MLPairRDDFunctions fromPairRDD(final RDD rdd, final ClassTag evidence$3, final ClassTag evidence$4) {
      return MLPairRDDFunctions$.MODULE$.fromPairRDD(rdd, evidence$3, evidence$4);
   }

   public RDD topByKey(final int num, final Ordering ord) {
      Function1 createCombiner = (v) -> (BoundedPriorityQueue)(new BoundedPriorityQueue(num, ord)).$plus$eq(v);
      Function2 mergeValue = (c, v) -> (BoundedPriorityQueue)c.$plus$eq(v);
      Function2 mergeCombiners = (c1, c2) -> (BoundedPriorityQueue)c1.$plus$plus$eq(c2);
      SparkContext qual$1 = this.self.context();
      boolean x$2 = qual$1.clean$default$2();
      Function1 var10002 = (Function1)qual$1.clean(createCombiner, x$2);
      SparkContext qual$2 = this.self.context();
      boolean x$4 = qual$2.clean$default$2();
      Function2 var10003 = (Function2)qual$2.clean(mergeValue, x$4);
      SparkContext qual$3 = this.self.context();
      boolean x$6 = qual$3.clean$default$2();
      Aggregator aggregator = new Aggregator(var10002, var10003, (Function2)qual$3.clean(mergeCombiners, x$6));
      RDD x$7 = this.self.mapPartitions((iter) -> {
         TaskContext context = .MODULE$.get();
         return new InterruptibleIterator(context, aggregator.combineValuesByKey(iter, context).map((x0$1) -> {
            if (x0$1 != null) {
               Object k = x0$1._1();
               BoundedPriorityQueue v = (BoundedPriorityQueue)x0$1._2();
               return new Tuple2(k, scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.genericArrayOps(v.toArray(this.evidence$2)), ord.reverse()));
            } else {
               throw new MatchError(x0$1);
            }
         }));
      }, true, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      ClassTag x$8 = this.evidence$1;
      ClassTag x$9 = scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(this.evidence$2.runtimeClass()));
      Null x$10 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(x$7);
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(x$7, x$8, x$9, (Ordering)null).reduceByKey((array1, array2) -> {
         int size = scala.math.package..MODULE$.min(num, scala.runtime.ScalaRunTime..MODULE$.array_length(array1) + scala.runtime.ScalaRunTime..MODULE$.array_length(array2));
         Object array = scala.Array..MODULE$.ofDim(size, this.evidence$2);
         org.apache.spark.util.collection.Utils..MODULE$.mergeOrdered(new scala.collection.immutable..colon.colon(scala.Predef..MODULE$.genericWrapArray(array1), new scala.collection.immutable..colon.colon(scala.Predef..MODULE$.genericWrapArray(array2), scala.collection.immutable.Nil..MODULE$)), ord.reverse()).copyToArray(array, 0, size);
         return array;
      });
   }

   public MLPairRDDFunctions(final RDD self, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.self = self;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
