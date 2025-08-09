package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.collection.SeqOps;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d\u0001\u0002\t\u0012\u0001qA\u0001B\f\u0001\u0003\u0006\u0004%\te\f\u0005\t\r\u0002\u0011\t\u0011)A\u0005a!)\u0001\n\u0001C\u0001\u0013\")\u0001\n\u0001C\u0001\u001d\")\u0001\u000b\u0001C\u0001#\")a\u000b\u0001C\u0001/\")!\f\u0001C\u00017\")!\r\u0001C!G\")1\u0010\u0001C!y\"9\u0011Q\u0002\u0001\u0005B\u0005=qaBA\u0013#!\u0005\u0011q\u0005\u0004\u0007!EA\t!!\u000b\t\r!cA\u0011AA$\u0011\u001d\tI\u0005\u0004C!\u0003\u0017B\u0011\"a\u0016\r\u0003\u0003%I!!\u0017\u0003\u0007A\u001b\u0015I\u0003\u0002\u0013'\u00059a-Z1ukJ,'B\u0001\u000b\u0016\u0003\tiGN\u0003\u0002\u0017/\u0005)1\u000f]1sW*\u0011\u0001$G\u0001\u0007CB\f7\r[3\u000b\u0003i\t1a\u001c:h\u0007\u0001\u0019B\u0001A\u000f&QA\u0019adH\u0011\u000e\u0003MI!\u0001I\n\u0003\u0013\u0015\u001bH/[7bi>\u0014\bC\u0001\u0012$\u001b\u0005\t\u0012B\u0001\u0013\u0012\u0005!\u00016)Q'pI\u0016d\u0007C\u0001\u0012'\u0013\t9\u0013CA\u0005Q\u0007\u0006\u0003\u0016M]1ngB\u0011\u0011\u0006L\u0007\u0002U)\u00111fE\u0001\u0005kRLG.\u0003\u0002.U\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\t\u0001\u0007\u0005\u00022u9\u0011!\u0007\u000f\t\u0003gYj\u0011\u0001\u000e\u0006\u0003km\ta\u0001\u0010:p_Rt$\"A\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005e2\u0014A\u0002)sK\u0012,g-\u0003\u0002<y\t11\u000b\u001e:j]\u001eT!!\u000f\u001c)\u0007\u0005qD\t\u0005\u0002@\u00056\t\u0001I\u0003\u0002B+\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\r\u0003%!B*j]\u000e,\u0017%A#\u0002\u000bErSG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005y\"\u0015A\u0002\u001fj]&$h\b\u0006\u0002K\u0017B\u0011!\u0005\u0001\u0005\u0006]\r\u0001\r\u0001\r\u0015\u0004\u0017z\"\u0005fA\u0002?\tR\t!\nK\u0002\u0005}\u0011\u000b1b]3u\u0013:\u0004X\u000f^\"pYR\u0011!kU\u0007\u0002\u0001!)A+\u0002a\u0001a\u0005)a/\u00197vK\"\u001aQA\u0010#\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\u0005IC\u0006\"\u0002+\u0007\u0001\u0004\u0001\u0004f\u0001\u0004?\t\u0006!1/\u001a;L)\t\u0011F\fC\u0003U\u000f\u0001\u0007Q\f\u0005\u0002_?6\ta'\u0003\u0002am\t\u0019\u0011J\u001c;)\u0007\u001dqD)A\u0002gSR$\"!\t3\t\u000b\u0015D\u0001\u0019\u00014\u0002\u000f\u0011\fG/Y:fiB\u0012qm\u001c\t\u0004Q.lW\"A5\u000b\u0005),\u0012aA:rY&\u0011A.\u001b\u0002\b\t\u0006$\u0018m]3u!\tqw\u000e\u0004\u0001\u0005\u0013A$\u0017\u0011!A\u0001\u0006\u0003\t(aA0%cE\u0011!/\u001e\t\u0003=NL!\u0001\u001e\u001c\u0003\u000f9{G\u000f[5oOB\u0011aL^\u0005\u0003oZ\u00121!\u00118zQ\rAa(_\u0011\u0002u\u0006)!G\f\u0019/a\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\rF\u0002~\u0003\u000f\u00012A`A\u0002\u001b\u0005y(bAA\u0001S\u0006)A/\u001f9fg&\u0019\u0011QA@\u0003\u0015M#(/^2u)f\u0004X\r\u0003\u0004\u0002\n%\u0001\r!`\u0001\u0007g\u000eDW-\\1)\u0007%qD)\u0001\u0003d_BLHc\u0001&\u0002\u0012!9\u00111\u0003\u0006A\u0002\u0005U\u0011!B3yiJ\f\u0007\u0003BA\f\u0003;i!!!\u0007\u000b\u0007\u0005m1#A\u0003qCJ\fW.\u0003\u0003\u0002 \u0005e!\u0001\u0003)be\u0006lW*\u00199)\u0007)qD\tK\u0002\u0001}\u0011\u000b1\u0001U\"B!\t\u0011CbE\u0004\r\u0003W\t\t$a\u000e\u0011\u0007y\u000bi#C\u0002\u00020Y\u0012a!\u00118z%\u00164\u0007\u0003B\u0015\u00024)K1!!\u000e+\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!!\u000f\u0002D5\u0011\u00111\b\u0006\u0005\u0003{\ty$\u0001\u0002j_*\u0011\u0011\u0011I\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002F\u0005m\"\u0001D*fe&\fG.\u001b>bE2,GCAA\u0014\u0003\u0011aw.\u00193\u0015\u0007)\u000bi\u0005\u0003\u0004\u0002P9\u0001\r\u0001M\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u000f}\u0005M\u0013EAA+\u0003\u0015\tdF\u000e\u00181\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\u0006\u0005\u0003\u0002^\u0005\rTBAA0\u0015\u0011\t\t'a\u0010\u0002\t1\fgnZ\u0005\u0005\u0003K\nyF\u0001\u0004PE*,7\r\u001e\u0015\u0005\u0019y\n\u0019\u0006\u000b\u0003\f}\u0005M\u0003"
)
public class PCA extends Estimator implements PCAParams, DefaultParamsWritable {
   private final String uid;
   private IntParam k;
   private Param outputCol;
   private Param inputCol;

   public static PCA load(final String path) {
      return PCA$.MODULE$.load(path);
   }

   public static MLReader read() {
      return PCA$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return PCAParams.getK$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return PCAParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam k() {
      return this.k;
   }

   public final void org$apache$spark$ml$feature$PCAParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public PCA setInputCol(final String value) {
      return (PCA)this.set(this.inputCol(), value);
   }

   public PCA setOutputCol(final String value) {
      return (PCA)this.set(this.outputCol(), value);
   }

   public PCA setK(final int value) {
      return (PCA)this.set(this.k(), BoxesRunTime.boxToInteger(value));
   }

   public PCAModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      RDD input = dataset.select((String)this.$(this.inputCol()), .MODULE$).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(1) == 0) {
               Object v = ((SeqOps)var3.get()).apply(0);
               if (v instanceof Vector) {
                  Vector var5 = (Vector)v;
                  return Vectors$.MODULE$.fromML(var5);
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.linalg.Vector.class));
      org.apache.spark.mllib.feature.PCA pca = new org.apache.spark.mllib.feature.PCA(BoxesRunTime.unboxToInt(this.$(this.k())));
      org.apache.spark.mllib.feature.PCAModel pcaModel = pca.fit(input);
      return (PCAModel)this.copyValues((new PCAModel(this.uid(), pcaModel.pc().asML(), pcaModel.explainedVariance().asML())).setParent(this), this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public PCA copy(final ParamMap extra) {
      return (PCA)this.defaultCopy(extra);
   }

   public PCA(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      PCAParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public PCA() {
      this(Identifiable$.MODULE$.randomUID("pca"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
