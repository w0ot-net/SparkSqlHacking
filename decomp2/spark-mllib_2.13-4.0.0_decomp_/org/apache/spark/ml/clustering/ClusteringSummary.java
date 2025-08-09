package org.apache.spark.ml.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.Summary;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import scala.MatchError;
import scala.Some;
import scala.collection.SeqOps;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u4AAD\b\u00015!A1\u0007\u0001BC\u0002\u0013\u0005A\u0007\u0003\u0005F\u0001\t\u0005\t\u0015!\u00036\u0011!Q\u0005A!b\u0001\n\u0003Y\u0005\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011\u0002'\t\u0011U\u0003!Q1A\u0005\u0002-C\u0001B\u0016\u0001\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\t/\u0002\u0011)\u0019!C\u00011\"AA\f\u0001B\u0001B\u0003%\u0011\f\u0003\u0005^\u0001\t\u0015\r\u0011\"\u0001Y\u0011!9\u0007A!A!\u0002\u0013I\u0006BB5\u0001\t\u0003y!\u000e\u0003\u0005t\u0001!\u0015\r\u0011\"\u00015\u0011!)\b\u0001#b\u0001\n\u00031(!E\"mkN$XM]5oON+X.\\1ss*\u0011\u0001#E\u0001\u000bG2,8\u000f^3sS:<'B\u0001\n\u0014\u0003\tiGN\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h\u0007\u0001\u0019B\u0001A\u000e\"OA\u0011AdH\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\t1\u0011I\\=SK\u001a\u0004\"AI\u0013\u000e\u0003\rR!\u0001J\t\u0002\tU$\u0018\u000e\\\u0005\u0003M\r\u0012qaU;n[\u0006\u0014\u0018\u0010\u0005\u0002)a9\u0011\u0011F\f\b\u0003U5j\u0011a\u000b\u0006\u0003Ye\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0010\n\u0005=j\u0012a\u00029bG.\fw-Z\u0005\u0003cI\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aL\u000f\u0002\u0017A\u0014X\rZ5di&|gn]\u000b\u0002kA\u0011aG\u0011\b\u0003o\u0001s!\u0001\u000f \u000f\u0005ejdB\u0001\u001e=\u001d\tQ3(C\u0001\u0019\u0013\t1r#\u0003\u0002\u0015+%\u0011qhE\u0001\u0004gFd\u0017BA\u0018B\u0015\ty4#\u0003\u0002D\t\nIA)\u0019;b\rJ\fW.\u001a\u0006\u0003_\u0005\u000bA\u0002\u001d:fI&\u001cG/[8og\u0002B#AA$\u0011\u0005qA\u0015BA%\u001e\u0005%!(/\u00198tS\u0016tG/A\u0007qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\\\u000b\u0002\u0019B\u0011Q*\u0015\b\u0003\u001d>\u0003\"AK\u000f\n\u0005Ak\u0012A\u0002)sK\u0012,g-\u0003\u0002S'\n11\u000b\u001e:j]\u001eT!\u0001U\u000f\u0002\u001dA\u0014X\rZ5di&|gnQ8mA\u0005Ya-Z1ukJ,7oQ8m\u000311W-\u0019;ve\u0016\u001c8i\u001c7!\u0003\u0005YW#A-\u0011\u0005qQ\u0016BA.\u001e\u0005\rIe\u000e^\u0001\u0003W\u0002\nqA\\;n\u0013R,'\u000fK\u0002\n?\u0016\u0004\"\u0001Y2\u000e\u0003\u0005T!AY\n\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002eC\n)1+\u001b8dK\u0006\na-A\u00033]Qr\u0003'\u0001\u0005ok6LE/\u001a:!Q\rQq,Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\r-lgn\u001c9r!\ta\u0007!D\u0001\u0010\u0011\u0015\u00194\u00021\u00016\u0011\u0015Q5\u00021\u0001M\u0011\u0015)6\u00021\u0001M\u0011\u001596\u00021\u0001Z\u0011\u0015i6\u00021\u0001ZQ\r\tx,Z\u0001\bG2,8\u000f^3sQ\taq)\u0001\u0007dYV\u001cH/\u001a:TSj,7/F\u0001x!\ra\u0002P_\u0005\u0003sv\u0011Q!\u0011:sCf\u0004\"\u0001H>\n\u0005ql\"\u0001\u0002'p]\u001e\u0004"
)
public class ClusteringSummary implements Summary, Serializable {
   private transient Dataset cluster;
   private long[] clusterSizes;
   private final transient Dataset predictions;
   private final String predictionCol;
   private final String featuresCol;
   private final int k;
   private final int numIter;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public Dataset predictions() {
      return this.predictions;
   }

   public String predictionCol() {
      return this.predictionCol;
   }

   public String featuresCol() {
      return this.featuresCol;
   }

   public int k() {
      return this.k;
   }

   public int numIter() {
      return this.numIter;
   }

   private Dataset cluster$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.cluster = this.predictions().select(this.predictionCol(), .MODULE$);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.cluster;
   }

   public Dataset cluster() {
      return !this.bitmap$trans$0 ? this.cluster$lzycompute() : this.cluster;
   }

   private long[] clusterSizes$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            long[] sizes = (long[])scala.Array..MODULE$.ofDim(this.k(), scala.reflect.ClassTag..MODULE$.Long());
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.cluster().groupBy(this.predictionCol(), .MODULE$).count().select(this.predictionCol(), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"count"}))).collect()), (x0$1) -> {
               $anonfun$clusterSizes$1(sizes, x0$1);
               return BoxedUnit.UNIT;
            });
            this.clusterSizes = sizes;
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.clusterSizes;
   }

   public long[] clusterSizes() {
      return !this.bitmap$0 ? this.clusterSizes$lzycompute() : this.clusterSizes;
   }

   // $FF: synthetic method
   public static final void $anonfun$clusterSizes$1(final long[] sizes$1, final Row x0$1) {
      if (x0$1 != null) {
         Some var4 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
         if (!var4.isEmpty() && var4.get() != null && ((SeqOps)var4.get()).lengthCompare(2) == 0) {
            Object cluster = ((SeqOps)var4.get()).apply(0);
            Object count = ((SeqOps)var4.get()).apply(1);
            if (cluster instanceof Integer) {
               int var7 = BoxesRunTime.unboxToInt(cluster);
               if (count instanceof Long) {
                  long var8 = BoxesRunTime.unboxToLong(count);
                  sizes$1[var7] = var8;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  return;
               }
            }
         }
      }

      throw new MatchError(x0$1);
   }

   public ClusteringSummary(final Dataset predictions, final String predictionCol, final String featuresCol, final int k, final int numIter) {
      this.predictions = predictions;
      this.predictionCol = predictionCol;
      this.featuresCol = featuresCol;
      this.k = k;
      this.numIter = numIter;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
