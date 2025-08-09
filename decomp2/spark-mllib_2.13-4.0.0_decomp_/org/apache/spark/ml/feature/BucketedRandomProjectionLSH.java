package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import scala.Array.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c\u0001\u0002\n\u0014\u0001yA\u0001\"\r\u0001\u0003\u0006\u0004%\tE\r\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005g!)\u0011\t\u0001C\u0001\u0005\")Q\t\u0001C!\r\")1\u000b\u0001C!)\")q\u000b\u0001C!1\")\u0011\t\u0001C\u0001?\")\u0011\r\u0001C\u0001E\")\u0001\u000e\u0001C\u0001S\"1q\u000e\u0001Q\u0005RADQ\u0001\u001e\u0001\u0005BUDq!a\u0001\u0001\t\u0003\n)aB\u0004\u0002\u0018MA\t!!\u0007\u0007\rI\u0019\u0002\u0012AA\u000e\u0011\u0019\te\u0002\"\u0001\u0002@!9\u0011\u0011\t\b\u0005B\u0005\r\u0003\"CA&\u001d\u0005\u0005I\u0011BA'\u0005m\u0011UoY6fi\u0016$'+\u00198e_6\u0004&o\u001c6fGRLwN\u001c'T\u0011*\u0011A#F\u0001\bM\u0016\fG/\u001e:f\u0015\t1r#\u0001\u0002nY*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001yb%\u000b\t\u0004A\u0005\u001aS\"A\n\n\u0005\t\u001a\"a\u0001'T\u0011B\u0011\u0001\u0005J\u0005\u0003KM\u0011\u0001EQ;dW\u0016$X\r\u001a*b]\u0012|W\u000e\u0015:pU\u0016\u001cG/[8o\u0019NCUj\u001c3fYB\u0011\u0001eJ\u0005\u0003QM\u0011\u0011EQ;dW\u0016$X\r\u001a*b]\u0012|W\u000e\u0015:pU\u0016\u001cG/[8o\u0019NC\u0005+\u0019:b[N\u0004\"AK\u0018\u000e\u0003-R!\u0001L\u0017\u0002\rMD\u0017M]3e\u0015\tqS#A\u0003qCJ\fW.\u0003\u00021W\t9\u0001*Y:TK\u0016$\u0017aA;jIV\t1\u0007\u0005\u00025{9\u0011Qg\u000f\t\u0003mej\u0011a\u000e\u0006\u0003qu\ta\u0001\u0010:p_Rt$\"\u0001\u001e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qJ\u0014A\u0002)sK\u0012,g-\u0003\u0002?\u007f\t11\u000b\u001e:j]\u001eT!\u0001P\u001d\u0002\tULG\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\r#\u0005C\u0001\u0011\u0001\u0011\u0015\t4\u00011\u00014\u0003-\u0019X\r^%oaV$8i\u001c7\u0015\u0005\u001dCU\"\u0001\u0001\t\u000b%#\u0001\u0019A\u001a\u0002\u000bY\fG.^3)\u0007\u0011Y\u0015\u000b\u0005\u0002M\u001f6\tQJ\u0003\u0002O/\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ak%!B*j]\u000e,\u0017%\u0001*\u0002\u000bIr\u0013G\f\u0019\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\u0005\u001d+\u0006\"B%\u0006\u0001\u0004\u0019\u0004fA\u0003L#\u0006\u00012/\u001a;Ok6D\u0015m\u001d5UC\ndWm\u001d\u000b\u0003\u000ffCQ!\u0013\u0004A\u0002i\u0003\"a\u0017/\u000e\u0003eJ!!X\u001d\u0003\u0007%sG\u000fK\u0002\u0007\u0017F#\u0012a\u0011\u0015\u0004\u000f-\u000b\u0016aD:fi\n+8m[3u\u0019\u0016tw\r\u001e5\u0015\u0005\u001d\u001b\u0007\"B%\t\u0001\u0004!\u0007CA.f\u0013\t1\u0017H\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0011-\u000b\u0016aB:fiN+W\r\u001a\u000b\u0003\u000f*DQ!S\u0005A\u0002-\u0004\"a\u00177\n\u00055L$\u0001\u0002'p]\u001eD3!C&R\u0003E\u0019'/Z1uKJ\u000bw\u000fT*I\u001b>$W\r\u001c\u000b\u0003GEDQA\u001d\u0006A\u0002i\u000b\u0001\"\u001b8qkR$\u0015.\u001c\u0015\u0004\u0015-\u000b\u0016a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\u0005Yt\bCA<}\u001b\u0005A(BA={\u0003\u0015!\u0018\u0010]3t\u0015\tYx#A\u0002tc2L!! =\u0003\u0015M#(/^2u)f\u0004X\rC\u0003\u0000\u0017\u0001\u0007a/\u0001\u0004tG\",W.\u0019\u0015\u0004\u0017-\u000b\u0016\u0001B2paf$2aRA\u0004\u0011\u001d\tI\u0001\u0004a\u0001\u0003\u0017\tQ!\u001a=ue\u0006\u0004B!!\u0004\u0002\u00105\tQ&C\u0002\u0002\u00125\u0012\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004\u0019-\u000b\u0006f\u0001\u0001L#\u0006Y\")^2lKR,GMU1oI>l\u0007K]8kK\u000e$\u0018n\u001c8M'\"\u0003\"\u0001\t\b\u0014\u000f9\ti\"a\t\u00020A\u00191,a\b\n\u0007\u0005\u0005\u0012H\u0001\u0004B]f\u0014VM\u001a\t\u0006\u0003K\tYcQ\u0007\u0003\u0003OQ1!!\u000b\u0016\u0003\u0011)H/\u001b7\n\t\u00055\u0012q\u0005\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\t\t$a\u000f\u000e\u0005\u0005M\"\u0002BA\u001b\u0003o\t!![8\u000b\u0005\u0005e\u0012\u0001\u00026bm\u0006LA!!\u0010\u00024\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011D\u0001\u0005Y>\fG\rF\u0002D\u0003\u000bBa!a\u0012\u0011\u0001\u0004\u0019\u0014\u0001\u00029bi\"D3\u0001E&R\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\u0005\u0005\u0003\u0002R\u0005]SBAA*\u0015\u0011\t)&a\u000e\u0002\t1\fgnZ\u0005\u0005\u00033\n\u0019F\u0001\u0004PE*,7\r\u001e\u0015\u0004\u001d-\u000b\u0006fA\u0007L#\u0002"
)
public class BucketedRandomProjectionLSH extends LSH implements BucketedRandomProjectionLSHParams, HasSeed {
   private final String uid;
   private LongParam seed;
   private DoubleParam bucketLength;

   public static BucketedRandomProjectionLSH load(final String path) {
      return BucketedRandomProjectionLSH$.MODULE$.load(path);
   }

   public static MLReader read() {
      return BucketedRandomProjectionLSH$.MODULE$.read();
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final double getBucketLength() {
      return BucketedRandomProjectionLSHParams.getBucketLength$(this);
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public DoubleParam bucketLength() {
      return this.bucketLength;
   }

   public void org$apache$spark$ml$feature$BucketedRandomProjectionLSHParams$_setter_$bucketLength_$eq(final DoubleParam x$1) {
      this.bucketLength = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public BucketedRandomProjectionLSH setInputCol(final String value) {
      return (BucketedRandomProjectionLSH)super.setInputCol(value);
   }

   public BucketedRandomProjectionLSH setOutputCol(final String value) {
      return (BucketedRandomProjectionLSH)super.setOutputCol(value);
   }

   public BucketedRandomProjectionLSH setNumHashTables(final int value) {
      return (BucketedRandomProjectionLSH)super.setNumHashTables(value);
   }

   public BucketedRandomProjectionLSH setBucketLength(final double value) {
      return (BucketedRandomProjectionLSH)this.set(this.bucketLength(), BoxesRunTime.boxToDouble(value));
   }

   public BucketedRandomProjectionLSH setSeed(final long value) {
      return (BucketedRandomProjectionLSH)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public BucketedRandomProjectionLSHModel createRawLSHModel(final int inputDim) {
      Random rng = new Random(BoxesRunTime.unboxToLong(this.$(this.seed())));
      int localNumHashTables = BoxesRunTime.unboxToInt(this.$(this.numHashTables()));
      double[] values = (double[]).MODULE$.fill(localNumHashTables * inputDim, (JFunction0.mcD.sp)() -> rng.nextGaussian(), scala.reflect.ClassTag..MODULE$.Double());

      for(int i = 0; i < localNumHashTables; ++i) {
         int offset = i * inputDim;
         double norm = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().dnrm2(inputDim, values, offset, 1);
         if (norm != (double)0) {
            org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().dscal(inputDim, (double)1.0F / norm, values, offset, 1);
         }
      }

      DenseMatrix randMatrix = new DenseMatrix(localNumHashTables, inputDim, values, true);
      return new BucketedRandomProjectionLSHModel(this.uid(), randMatrix);
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      return this.validateAndTransformSchema(schema);
   }

   public BucketedRandomProjectionLSH copy(final ParamMap extra) {
      return (BucketedRandomProjectionLSH)this.defaultCopy(extra);
   }

   public BucketedRandomProjectionLSH(final String uid) {
      this.uid = uid;
      BucketedRandomProjectionLSHParams.$init$(this);
      HasSeed.$init$(this);
      Statics.releaseFence();
   }

   public BucketedRandomProjectionLSH() {
      this(Identifiable$.MODULE$.randomUID("brp-lsh"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
