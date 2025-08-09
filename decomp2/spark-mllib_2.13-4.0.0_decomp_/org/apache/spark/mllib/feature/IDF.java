package org.apache.spark.mllib.feature;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001B\r\u001b\u0001\u0015B\u0001\u0002\f\u0001\u0003\u0006\u0004%\t!\f\u0005\tu\u0001\u0011\t\u0011)A\u0005]!)A\b\u0001C\u0001{!)A\b\u0001C\u0001\u0007\")q\t\u0001C\u0001\u0011\")q\t\u0001C\u00017\u001e)qM\u0007E\u0005Q\u001a)\u0011D\u0007E\u0005S\")A\b\u0003C\u0001U\u001a!1\u000e\u0003\u0001m\u0011!a#B!b\u0001\n\u0003i\u0003\u0002\u0003\u001e\u000b\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u000bqRA\u0011A=\t\u000fuT\u0001\u0019!C\u0005}\"I\u0011Q\u0001\u0006A\u0002\u0013%\u0011q\u0001\u0005\b\u0003'Q\u0001\u0015)\u0003\u0000\u0011-\t)B\u0003a\u0001\u0002\u0004%I!a\u0006\t\u0017\u0005\u001d\"\u00021AA\u0002\u0013%\u0011\u0011\u0006\u0005\f\u0003[Q\u0001\u0019!A!B\u0013\tI\u0002\u0003\u0004=\u0015\u0011\u0005\u0011q\u0006\u0005\b\u0003cQA\u0011AA\u001a\u0011\u001d\tYD\u0003C\u0001\u0003{Aq!a\u0011\u000b\t\u0013\t)\u0005C\u0004\u0002N)!\t!a\u0014\u0003\u0007%#eI\u0003\u0002\u001c9\u00059a-Z1ukJ,'BA\u000f\u001f\u0003\u0015iG\u000e\\5c\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0001A\n\u0003\u0001\u0019\u0002\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u0012a!\u00118z%\u00164\u0017AC7j]\u0012{7M\u0012:fcV\ta\u0006\u0005\u0002(_%\u0011\u0001\u0007\u000b\u0002\u0004\u0013:$\bfA\u00013qA\u00111GN\u0007\u0002i)\u0011QGH\u0001\u000bC:tw\u000e^1uS>t\u0017BA\u001c5\u0005\u0015\u0019\u0016N\\2fC\u0005I\u0014!B\u0019/e9\u0002\u0014aC7j]\u0012{7M\u0012:fc\u0002B3A\u0001\u001a9\u0003\u0019a\u0014N\\5u}Q\u0011a\b\u0011\t\u0003\u007f\u0001i\u0011A\u0007\u0005\u0006Y\r\u0001\rA\f\u0015\u0004\u0001JB\u0004fA\u00023qQ\ta\bK\u0002\u0005e\u0015\u000b\u0013AR\u0001\u0006c9\nd\u0006M\u0001\u0004M&$HCA%M!\ty$*\u0003\u0002L5\tA\u0011\n\u0012$N_\u0012,G\u000eC\u0003N\u000b\u0001\u0007a*A\u0004eCR\f7/\u001a;\u0011\u0007=\u0013F+D\u0001Q\u0015\t\tf$A\u0002sI\u0012L!a\u0015)\u0003\u0007I#E\t\u0005\u0002V16\taK\u0003\u0002X9\u00051A.\u001b8bY\u001eL!!\u0017,\u0003\rY+7\r^8sQ\r)!'\u0012\u000b\u0003\u0013rCQ!\u0014\u0004A\u0002u\u00032AX2U\u001b\u0005y&B\u00011b\u0003\u0011Q\u0017M^1\u000b\u0005\tt\u0012aA1qS&\u0011Am\u0018\u0002\b\u0015\u00064\u0018M\u0015#EQ\r1!'\u0012\u0015\u0004\u0001I*\u0015aA%E\rB\u0011q\bC\n\u0003\u0011\u0019\"\u0012\u0001\u001b\u0002\u001c\t>\u001cW/\\3oi\u001a\u0013X-];f]\u000eL\u0018iZ4sK\u001e\fGo\u001c:\u0014\u0007)1S\u000e\u0005\u0002om:\u0011q\u000e\u001e\b\u0003aNl\u0011!\u001d\u0006\u0003e\u0012\na\u0001\u0010:p_Rt\u0014\"A\u0015\n\u0005UD\u0013a\u00029bG.\fw-Z\u0005\u0003ob\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!!\u001e\u0015\u0015\u0005id\bCA>\u000b\u001b\u0005A\u0001\"\u0002\u0017\u000e\u0001\u0004q\u0013!A7\u0016\u0003}\u00042aJA\u0001\u0013\r\t\u0019\u0001\u000b\u0002\u0005\u0019>tw-A\u0003n?\u0012*\u0017\u000f\u0006\u0003\u0002\n\u0005=\u0001cA\u0014\u0002\f%\u0019\u0011Q\u0002\u0015\u0003\tUs\u0017\u000e\u001e\u0005\t\u0003#y\u0011\u0011!a\u0001\u007f\u0006\u0019\u0001\u0010J\u0019\u0002\u00055\u0004\u0013A\u00013g+\t\tI\u0002E\u0003\u0002\u001c\u0005\rr0\u0004\u0002\u0002\u001e)\u0019q+a\b\u000b\u0005\u0005\u0005\u0012A\u00022sK\u0016TX-\u0003\u0003\u0002&\u0005u!a\u0003#f]N,g+Z2u_J\fa\u0001\u001a4`I\u0015\fH\u0003BA\u0005\u0003WA\u0011\"!\u0005\u0013\u0003\u0003\u0005\r!!\u0007\u0002\u0007\u00114\u0007\u0005F\u0001{\u0003\r\tG\r\u001a\u000b\u0005\u0003k\t9$D\u0001\u000b\u0011\u0019\tI$\u0006a\u0001)\u0006\u0019Am\\2\u0002\u000b5,'oZ3\u0015\t\u0005U\u0012q\b\u0005\u0007\u0003\u00032\u0002\u0019\u0001>\u0002\u000b=$\b.\u001a:\u0002\u000f%\u001cX)\u001c9usV\u0011\u0011q\t\t\u0004O\u0005%\u0013bAA&Q\t9!i\\8mK\u0006t\u0017aA5eMR\u0011\u0011\u0011\u000b\t\bO\u0005MC+a\u0016\u0000\u0013\r\t)\u0006\u000b\u0002\u0007)V\u0004H.Z\u001a\u0011\t\u001d\nIf`\u0005\u0004\u00037B#!B!se\u0006L\b"
)
public class IDF {
   private final int minDocFreq;

   public int minDocFreq() {
      return this.minDocFreq;
   }

   public IDFModel fit(final RDD dataset) {
      DocumentFrequencyAggregator x$1 = new DocumentFrequencyAggregator(this.minDocFreq());
      Function2 x$2 = (df, v) -> df.add(v);
      Function2 x$3 = (df1, df2) -> df1.merge(df2);
      int x$4 = dataset.treeAggregate$default$4(x$1);
      Tuple3 var4 = ((DocumentFrequencyAggregator)dataset.treeAggregate(x$1, x$2, x$3, x$4, .MODULE$.apply(DocumentFrequencyAggregator.class))).idf();
      if (var4 != null) {
         Vector idf = (Vector)var4._1();
         long[] docFreq = (long[])var4._2();
         long numDocs = BoxesRunTime.unboxToLong(var4._3());
         if (idf != null && docFreq != null && true) {
            Tuple3 var3 = new Tuple3(idf, docFreq, BoxesRunTime.boxToLong(numDocs));
            Vector idf = (Vector)var3._1();
            long[] docFreq = (long[])var3._2();
            long numDocs = BoxesRunTime.unboxToLong(var3._3());
            return new IDFModel(idf, docFreq, numDocs);
         }
      }

      throw new MatchError(var4);
   }

   public IDFModel fit(final JavaRDD dataset) {
      return this.fit(dataset.rdd());
   }

   public IDF(final int minDocFreq) {
      this.minDocFreq = minDocFreq;
   }

   public IDF() {
      this(0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class DocumentFrequencyAggregator implements Serializable {
      private final int minDocFreq;
      private long m;
      private DenseVector df;

      public int minDocFreq() {
         return this.minDocFreq;
      }

      private long m() {
         return this.m;
      }

      private void m_$eq(final long x$1) {
         this.m = x$1;
      }

      private DenseVector df() {
         return this.df;
      }

      private void df_$eq(final DenseVector x$1) {
         this.df = x$1;
      }

      public DocumentFrequencyAggregator add(final Vector doc) {
         if (this.isEmpty()) {
            this.df_$eq(breeze.linalg.DenseVector..MODULE$.zeros$mJc$sp(doc.size(), .MODULE$.Long(), breeze.storage.Zero..MODULE$.LongZero()));
         }

         label59: {
            if (doc instanceof SparseVector var4) {
               Option var5 = SparseVector$.MODULE$.unapply(var4);
               if (!var5.isEmpty()) {
                  int[] indices = (int[])((Tuple3)var5.get())._2();
                  double[] values = (double[])((Tuple3)var5.get())._3();
                  int nnz = indices.length;

                  for(int k = 0; k < nnz; ++k) {
                     if (values[k] > (double)0) {
                        DenseVector var10 = this.df();
                        int var11 = indices[k];
                        var10.update$mcJ$sp(var11, var10.apply$mcJ$sp(var11) + 1L);
                     }
                  }

                  BoxedUnit var19 = BoxedUnit.UNIT;
                  break label59;
               }
            }

            if (!(doc instanceof org.apache.spark.mllib.linalg.DenseVector)) {
               throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + doc.getClass() + ".");
            }

            org.apache.spark.mllib.linalg.DenseVector var12 = (org.apache.spark.mllib.linalg.DenseVector)doc;
            Option var13 = DenseVector$.MODULE$.unapply(var12);
            if (var13.isEmpty()) {
               throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + doc.getClass() + ".");
            }

            double[] values = (double[])var13.get();
            int n = values.length;

            for(int j = 0; j < n; ++j) {
               if (values[j] > (double)0.0F) {
                  DenseVector var17 = this.df();
                  var17.update$mcJ$sp(j, var17.apply$mcJ$sp(j) + 1L);
               }
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.m_$eq(this.m() + 1L);
         return this;
      }

      public DocumentFrequencyAggregator merge(final DocumentFrequencyAggregator other) {
         if (!other.isEmpty()) {
            this.m_$eq(this.m() + other.m());
            if (this.df() == null) {
               this.df_$eq(other.df().copy$mcJ$sp());
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               this.df().$plus$eq(other.df(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Long_OpAdd());
            }
         } else {
            BoxedUnit var2 = BoxedUnit.UNIT;
         }

         return this;
      }

      private boolean isEmpty() {
         return this.m() == 0L;
      }

      public Tuple3 idf() {
         if (this.isEmpty()) {
            throw new IllegalStateException("Haven't seen any document yet.");
         } else {
            int n = this.df().length();
            double[] inv = new double[n];
            long[] dfv = new long[n];

            for(int j = 0; j < n; ++j) {
               if (this.df().apply$mcJ$sp(j) >= (long)this.minDocFreq()) {
                  inv[j] = scala.math.package..MODULE$.log(((double)this.m() + (double)1.0F) / ((double)this.df().apply$mcJ$sp(j) + (double)1.0F));
                  dfv[j] = this.df().apply$mcJ$sp(j);
               }
            }

            return new Tuple3(Vectors$.MODULE$.dense(inv), dfv, BoxesRunTime.boxToLong(this.m()));
         }
      }

      public DocumentFrequencyAggregator(final int minDocFreq) {
         this.minDocFreq = minDocFreq;
         this.m = 0L;
      }

      public DocumentFrequencyAggregator() {
         this(0);
      }
   }
}
