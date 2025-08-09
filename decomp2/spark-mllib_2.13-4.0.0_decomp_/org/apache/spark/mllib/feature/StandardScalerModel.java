package org.apache.spark.mllib.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.feature.StandardScalerModel$;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001B\n\u0015\u0001}A\u0001B\u000b\u0001\u0003\u0006\u0004%\ta\u000b\u0005\tw\u0001\u0011\t\u0011)A\u0005Y!AQ\b\u0001BC\u0002\u0013\u00051\u0006\u0003\u0005B\u0001\t\u0005\t\u0015!\u0003-\u0011!\u0019\u0005A!a\u0001\n\u0003!\u0005\u0002C%\u0001\u0005\u0003\u0007I\u0011\u0001&\t\u0011E\u0003!\u0011!Q!\n\u0015C\u0001b\u0015\u0001\u0003\u0002\u0004%\t\u0001\u0012\u0005\t+\u0002\u0011\t\u0019!C\u0001-\"A\u0011\f\u0001B\u0001B\u0003&Q\tC\u0003\\\u0001\u0011\u0005A\fC\u0003\\\u0001\u0011\u0005q\rC\u0003\\\u0001\u0011\u00051\u000eC\u0003o\u0001\u0011\u0005q\u000eC\u0003t\u0001\u0011\u0005A\u000f\u0003\u0005x\u0001!\u0015\r\u0011\"\u0003y\u0011!y\b\u0001#b\u0001\n\u0013A\bbBA\u0001\u0001\u0011\u0005\u00131\u0001\u0002\u0014'R\fg\u000eZ1sIN\u001b\u0017\r\\3s\u001b>$W\r\u001c\u0006\u0003+Y\tqAZ3biV\u0014XM\u0003\u0002\u00181\u0005)Q\u000e\u001c7jE*\u0011\u0011DG\u0001\u0006gB\f'o\u001b\u0006\u00037q\ta!\u00199bG\",'\"A\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0001c\u0005\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#E\u0001\u0004B]f\u0014VM\u001a\t\u0003O!j\u0011\u0001F\u0005\u0003SQ\u0011\u0011CV3di>\u0014HK]1og\u001a|'/\\3s\u0003\r\u0019H\u000fZ\u000b\u0002YA\u0011Q\u0006M\u0007\u0002])\u0011qFF\u0001\u0007Y&t\u0017\r\\4\n\u0005Er#A\u0002,fGR|'\u000fK\u0002\u0002ge\u0002\"\u0001N\u001c\u000e\u0003UR!A\u000e\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u00029k\t)1+\u001b8dK\u0006\n!(A\u00032]Mr\u0003'\u0001\u0003ti\u0012\u0004\u0003f\u0001\u00024s\u0005!Q.Z1oQ\r\u00191gP\u0011\u0002\u0001\u0006)\u0011GL\u0019/a\u0005)Q.Z1oA!\u001aAaM \u0002\u000f]LG\u000f[*uIV\tQ\t\u0005\u0002\"\r&\u0011qI\t\u0002\b\u0005>|G.Z1oQ\r)1'O\u0001\fo&$\bn\u0015;e?\u0012*\u0017\u000f\u0006\u0002L\u001dB\u0011\u0011\u0005T\u0005\u0003\u001b\n\u0012A!\u00168ji\"9qJBA\u0001\u0002\u0004)\u0015a\u0001=%c!\u001aaaM\u001d\u0002\u0011]LG\u000f[*uI\u0002B3aB\u001a:\u0003!9\u0018\u000e\u001e5NK\u0006t\u0007f\u0001\u00054s\u0005aq/\u001b;i\u001b\u0016\fgn\u0018\u0013fcR\u00111j\u0016\u0005\b\u001f&\t\t\u00111\u0001FQ\rI1'O\u0001\no&$\b.T3b]\u0002B3AC\u001a:\u0003\u0019a\u0014N\\5u}Q)QL\u00181cIB\u0011q\u0005\u0001\u0005\u0006U-\u0001\r\u0001\f\u0015\u0004=NJ\u0004\"B\u001f\f\u0001\u0004a\u0003f\u000114\u007f!)1i\u0003a\u0001\u000b\"\u001a!mM\u001d\t\u000bM[\u0001\u0019A#)\u0007\u0011\u001c\u0014\bK\u0002\fge\"2!\u00185j\u0011\u0015QC\u00021\u0001-\u0011\u0015iD\u00021\u0001-Q\ra1'\u000f\u000b\u0003;2DQAK\u0007A\u00021B3!D\u001a:\u0003-\u0019X\r^,ji\"lU-\u00198\u0015\u0005A\fX\"\u0001\u0001\t\u000bMs\u0001\u0019A#)\u00079\u0019\u0014(\u0001\u0006tKR<\u0016\u000e\u001e5Ti\u0012$\"\u0001];\t\u000b\r{\u0001\u0019A#)\u0007=\u0019\u0014(A\u0003tQ&4G/F\u0001z!\r\t#\u0010`\u0005\u0003w\n\u0012Q!\u0011:sCf\u0004\"!I?\n\u0005y\u0014#A\u0002#pk\ndW-A\u0003tG\u0006dW-A\u0005ue\u0006t7OZ8s[R\u0019A&!\u0002\t\r\u0005\u001d!\u00031\u0001-\u0003\u00191Xm\u0019;pe\"\u001a!cM )\u0007\u0001\u0019t\b"
)
public class StandardScalerModel implements VectorTransformer {
   private double[] shift;
   private double[] scale;
   private final Vector std;
   private final Vector mean;
   private boolean withStd;
   private boolean withMean;
   private volatile byte bitmap$0;

   public RDD transform(final RDD data) {
      return VectorTransformer.transform$(this, (RDD)data);
   }

   public JavaRDD transform(final JavaRDD data) {
      return VectorTransformer.transform$(this, (JavaRDD)data);
   }

   public Vector std() {
      return this.std;
   }

   public Vector mean() {
      return this.mean;
   }

   public boolean withStd() {
      return this.withStd;
   }

   public void withStd_$eq(final boolean x$1) {
      this.withStd = x$1;
   }

   public boolean withMean() {
      return this.withMean;
   }

   public void withMean_$eq(final boolean x$1) {
      this.withMean = x$1;
   }

   public StandardScalerModel setWithMean(final boolean withMean) {
      .MODULE$.require(!withMean || this.mean() != null, () -> "cannot set withMean to true while mean is null");
      this.withMean_$eq(withMean);
      return this;
   }

   public StandardScalerModel setWithStd(final boolean withStd) {
      .MODULE$.require(!withStd || this.std() != null, () -> "cannot set withStd to true while std is null");
      this.withStd_$eq(withStd);
      return this;
   }

   private double[] shift$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.shift = this.mean().toArray();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.shift;
   }

   private double[] shift() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.shift$lzycompute() : this.shift;
   }

   private double[] scale$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.scale = (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.doubleArrayOps(this.std().toArray()), (JFunction1.mcDD.sp)(v) -> v == (double)0 ? (double)0.0F : (double)1.0F / v, scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.scale;
   }

   private double[] scale() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.scale$lzycompute() : this.scale;
   }

   public Vector transform(final Vector vector) {
      .MODULE$.require(this.mean().size() == vector.size());
      Tuple2.mcZZ.sp var6 = new Tuple2.mcZZ.sp(this.withMean(), this.withStd());
      if (var6 != null) {
         boolean var7 = ((Tuple2)var6)._1$mcZ$sp();
         boolean var8 = ((Tuple2)var6)._2$mcZ$sp();
         if (var7 && var8) {
            double[] localShift = this.shift();
            double[] localScale = this.scale();
            double[] var38;
            if (vector instanceof DenseVector) {
               DenseVector var13 = (DenseVector)vector;
               var38 = (double[])var13.values().clone();
            } else {
               if (vector == null) {
                  throw new MatchError(vector);
               }

               var38 = vector.toArray();
            }

            double[] values = var38;
            double[] newValues = StandardScalerModel$.MODULE$.transformWithBoth(localShift, localScale, values);
            return Vectors$.MODULE$.dense(newValues);
         }
      }

      if (var6 != null) {
         boolean var16 = ((Tuple2)var6)._1$mcZ$sp();
         boolean var17 = ((Tuple2)var6)._2$mcZ$sp();
         if (var16 && !var17) {
            double[] localShift = this.shift();
            double[] var10000;
            if (vector instanceof DenseVector) {
               DenseVector var21 = (DenseVector)vector;
               var10000 = (double[])var21.values().clone();
            } else {
               if (vector == null) {
                  throw new MatchError(vector);
               }

               var10000 = vector.toArray();
            }

            double[] values = var10000;
            double[] newValues = StandardScalerModel$.MODULE$.transformWithShift(localShift, values);
            return Vectors$.MODULE$.dense(newValues);
         }
      }

      if (var6 != null) {
         boolean var24 = ((Tuple2)var6)._1$mcZ$sp();
         boolean var25 = ((Tuple2)var6)._2$mcZ$sp();
         if (!var24 && var25) {
            double[] localScale = this.scale();
            if (vector instanceof DenseVector) {
               DenseVector var28 = (DenseVector)vector;
               Option var29 = DenseVector$.MODULE$.unapply(var28);
               if (!var29.isEmpty()) {
                  double[] values = (double[])var29.get();
                  double[] newValues = StandardScalerModel$.MODULE$.transformDenseWithScale(localScale, (double[])(([D)values).clone());
                  return Vectors$.MODULE$.dense(newValues);
               }
            }

            if (vector instanceof SparseVector) {
               SparseVector var32 = (SparseVector)vector;
               Option var33 = SparseVector$.MODULE$.unapply(var32);
               if (!var33.isEmpty()) {
                  int size = BoxesRunTime.unboxToInt(((Tuple3)var33.get())._1());
                  int[] indices = (int[])((Tuple3)var33.get())._2();
                  double[] values = (double[])((Tuple3)var33.get())._3();
                  double[] newValues = StandardScalerModel$.MODULE$.transformSparseWithScale(localScale, indices, (double[])(([D)values).clone());
                  return Vectors$.MODULE$.sparse(size, indices, newValues);
               }
            }

            throw new IllegalArgumentException("Unknown vector type " + vector.getClass() + ".");
         }
      }

      return vector;
   }

   public StandardScalerModel(final Vector std, final Vector mean, final boolean withStd, final boolean withMean) {
      this.std = std;
      this.mean = mean;
      this.withStd = withStd;
      this.withMean = withMean;
      super();
      VectorTransformer.$init$(this);
   }

   public StandardScalerModel(final Vector std, final Vector mean) {
      this(std, mean, std != null, mean != null);
      .MODULE$.require(this.withStd() || this.withMean(), () -> "at least one of std or mean vectors must be provided");
      if (this.withStd() && this.withMean()) {
         .MODULE$.require(mean.size() == std.size(), () -> "mean and std vectors must have equal size if both are provided");
      }

   }

   public StandardScalerModel(final Vector std) {
      this(std, (Vector)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
