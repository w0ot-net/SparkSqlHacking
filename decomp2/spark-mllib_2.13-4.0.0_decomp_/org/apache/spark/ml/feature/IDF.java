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
   bytes = "\u0006\u0005\u0005=d\u0001\u0002\t\u0012\u0005qA\u0001B\f\u0001\u0003\u0006\u0004%\te\f\u0005\t\r\u0002\u0011\t\u0011)A\u0005a!)\u0001\n\u0001C\u0001\u0013\")\u0001\n\u0001C\u0001\u001d\")\u0001\u000b\u0001C\u0001#\")a\u000b\u0001C\u0001/\")!\f\u0001C\u00017\")!\r\u0001C!G\")1\u0010\u0001C!y\"9\u0011Q\u0002\u0001\u0005B\u0005=qaBA\u0015#!\u0005\u00111\u0006\u0004\u0007!EA\t!!\f\t\r!cA\u0011AA&\u0011\u001d\ti\u0005\u0004C!\u0003\u001fB\u0011\"a\u0017\r\u0003\u0003%I!!\u0018\u0003\u0007%#eI\u0003\u0002\u0013'\u00059a-Z1ukJ,'B\u0001\u000b\u0016\u0003\tiGN\u0003\u0002\u0017/\u0005)1\u000f]1sW*\u0011\u0001$G\u0001\u0007CB\f7\r[3\u000b\u0003i\t1a\u001c:h\u0007\u0001\u0019B\u0001A\u000f&QA\u0019adH\u0011\u000e\u0003MI!\u0001I\n\u0003\u0013\u0015\u001bH/[7bi>\u0014\bC\u0001\u0012$\u001b\u0005\t\u0012B\u0001\u0013\u0012\u0005!IEIR'pI\u0016d\u0007C\u0001\u0012'\u0013\t9\u0013CA\u0004J\t\u001a\u0013\u0015m]3\u0011\u0005%bS\"\u0001\u0016\u000b\u0005-\u001a\u0012\u0001B;uS2L!!\f\u0016\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003A\u0002\"!\r\u001e\u000f\u0005IB\u0004CA\u001a7\u001b\u0005!$BA\u001b\u001c\u0003\u0019a$o\\8u})\tq'A\u0003tG\u0006d\u0017-\u0003\u0002:m\u00051\u0001K]3eK\u001aL!a\u000f\u001f\u0003\rM#(/\u001b8h\u0015\tId\u0007K\u0002\u0002}\u0011\u0003\"a\u0010\"\u000e\u0003\u0001S!!Q\u000b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002D\u0001\n)1+\u001b8dK\u0006\nQ)A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002?\t\u00061A(\u001b8jiz\"\"AS&\u0011\u0005\t\u0002\u0001\"\u0002\u0018\u0004\u0001\u0004\u0001\u0004fA&?\t\"\u001a1A\u0010#\u0015\u0003)C3\u0001\u0002 E\u0003-\u0019X\r^%oaV$8i\u001c7\u0015\u0005I\u001bV\"\u0001\u0001\t\u000bQ+\u0001\u0019\u0001\u0019\u0002\u000bY\fG.^3)\u0007\u0015qD)\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000e\u0006\u0002S1\")AK\u0002a\u0001a!\u001aaA\u0010#\u0002\u001bM,G/T5o\t>\u001cgI]3r)\t\u0011F\fC\u0003U\u000f\u0001\u0007Q\f\u0005\u0002_?6\ta'\u0003\u0002am\t\u0019\u0011J\u001c;)\u0007\u001dqD)A\u0002gSR$\"!\t3\t\u000b\u0015D\u0001\u0019\u00014\u0002\u000f\u0011\fG/Y:fiB\u0012qm\u001c\t\u0004Q.lW\"A5\u000b\u0005),\u0012aA:rY&\u0011A.\u001b\u0002\b\t\u0006$\u0018m]3u!\tqw\u000e\u0004\u0001\u0005\u0013A$\u0017\u0011!A\u0001\u0006\u0003\t(aA0%cE\u0011!/\u001e\t\u0003=NL!\u0001\u001e\u001c\u0003\u000f9{G\u000f[5oOB\u0011aL^\u0005\u0003oZ\u00121!\u00118zQ\rAa(_\u0011\u0002u\u0006)!G\f\u0019/a\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\rF\u0002~\u0003\u000f\u00012A`A\u0002\u001b\u0005y(bAA\u0001S\u0006)A/\u001f9fg&\u0019\u0011QA@\u0003\u0015M#(/^2u)f\u0004X\r\u0003\u0004\u0002\n%\u0001\r!`\u0001\u0007g\u000eDW-\\1)\u0007%qD)\u0001\u0003d_BLHc\u0001&\u0002\u0012!9\u00111\u0003\u0006A\u0002\u0005U\u0011!B3yiJ\f\u0007\u0003BA\f\u0003;i!!!\u0007\u000b\u0007\u0005m1#A\u0003qCJ\fW.\u0003\u0003\u0002 \u0005e!\u0001\u0003)be\u0006lW*\u00199)\t)q\u00141E\u0011\u0003\u0003K\tQ!\r\u00185]EB3\u0001\u0001 E\u0003\rIEI\u0012\t\u0003E1\u0019r\u0001DA\u0018\u0003k\tY\u0004E\u0002_\u0003cI1!a\r7\u0005\u0019\te.\u001f*fMB!\u0011&a\u000eK\u0013\r\tID\u000b\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\ti$a\u0012\u000e\u0005\u0005}\"\u0002BA!\u0003\u0007\n!![8\u000b\u0005\u0005\u0015\u0013\u0001\u00026bm\u0006LA!!\u0013\u0002@\ta1+\u001a:jC2L'0\u00192mKR\u0011\u00111F\u0001\u0005Y>\fG\rF\u0002K\u0003#Ba!a\u0015\u000f\u0001\u0004\u0001\u0014\u0001\u00029bi\"DCA\u0004 \u0002X\u0005\u0012\u0011\u0011L\u0001\u0006c92d\u0006M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003?\u0002B!!\u0019\u0002h5\u0011\u00111\r\u0006\u0005\u0003K\n\u0019%\u0001\u0003mC:<\u0017\u0002BA5\u0003G\u0012aa\u00142kK\u000e$\b\u0006\u0002\u0007?\u0003/BCa\u0003 \u0002X\u0001"
)
public final class IDF extends Estimator implements IDFBase, DefaultParamsWritable {
   private final String uid;
   private IntParam minDocFreq;
   private Param outputCol;
   private Param inputCol;

   public static IDF load(final String path) {
      return IDF$.MODULE$.load(path);
   }

   public static MLReader read() {
      return IDF$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getMinDocFreq() {
      return IDFBase.getMinDocFreq$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return IDFBase.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam minDocFreq() {
      return this.minDocFreq;
   }

   public final void org$apache$spark$ml$feature$IDFBase$_setter_$minDocFreq_$eq(final IntParam x$1) {
      this.minDocFreq = x$1;
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

   public IDF setInputCol(final String value) {
      return (IDF)this.set(this.inputCol(), value);
   }

   public IDF setOutputCol(final String value) {
      return (IDF)this.set(this.outputCol(), value);
   }

   public IDF setMinDocFreq(final int value) {
      return (IDF)this.set(this.minDocFreq(), BoxesRunTime.boxToInteger(value));
   }

   public IDFModel fit(final Dataset dataset) {
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
      org.apache.spark.mllib.feature.IDFModel idf = (new org.apache.spark.mllib.feature.IDF(BoxesRunTime.unboxToInt(this.$(this.minDocFreq())))).fit(input);
      return (IDFModel)this.copyValues((new IDFModel(this.uid(), idf)).setParent(this), this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public IDF copy(final ParamMap extra) {
      return (IDF)this.defaultCopy(extra);
   }

   public IDF(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      IDFBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public IDF() {
      this(Identifiable$.MODULE$.randomUID("idf"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
