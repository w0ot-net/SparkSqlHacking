package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.stat.ANOVATest$;
import org.apache.spark.ml.stat.ChiSquareTest$;
import org.apache.spark.ml.stat.FValueTest$;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg\u0001\u0002\f\u0018\u0005\tB\u0001\u0002\u000e\u0001\u0003\u0006\u0004%\t%\u000e\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005m!)a\n\u0001C\u0001\u001f\")a\n\u0001C\u0001)\")a\u000b\u0001C\u0001/\")A\f\u0001C\u0001;\")A\r\u0001C\u0001K\")\u0001\u000e\u0001C\u0001S\")A\u000e\u0001C\u0001[\")\u0001\u000f\u0001C\u0001c\")A\u000f\u0001C\u0001k\")\u0001\u0010\u0001C!s\"9\u0011q\u0004\u0001\u0005\n\u0005\u0005\u0002\u0002CA+\u0001\u0011\u0005q#a\u0016\t\u000f\u0005%\u0004\u0001\"\u0011\u0002l!9\u0011q\u0010\u0001\u0005B\u0005\u0005uaBAL/!\u0005\u0011\u0011\u0014\u0004\u0007-]A\t!a'\t\r9\u0013B\u0011AA]\u0011\u001d\tYL\u0005C!\u0003{C\u0011\"!2\u0013\u0003\u0003%I!a2\u00033Us\u0017N^1sS\u0006$XMR3biV\u0014XmU3mK\u000e$xN\u001d\u0006\u00031e\tqAZ3biV\u0014XM\u0003\u0002\u001b7\u0005\u0011Q\u000e\u001c\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sO\u000e\u00011\u0003\u0002\u0001$W9\u00022\u0001J\u0013(\u001b\u0005I\u0012B\u0001\u0014\u001a\u0005%)5\u000f^5nCR|'\u000f\u0005\u0002)S5\tq#\u0003\u0002+/\tqRK\\5wCJL\u0017\r^3GK\u0006$XO]3TK2,7\r^8s\u001b>$W\r\u001c\t\u0003Q1J!!L\f\u0003?Us\u0017N^1sS\u0006$XMR3biV\u0014XmU3mK\u000e$xN\u001d)be\u0006l7\u000f\u0005\u00020e5\t\u0001G\u0003\u000223\u0005!Q\u000f^5m\u0013\t\u0019\u0004GA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULG-F\u00017!\t9\u0004I\u0004\u00029}A\u0011\u0011\bP\u0007\u0002u)\u00111(I\u0001\u0007yI|w\u000e\u001e \u000b\u0003u\nQa]2bY\u0006L!a\u0010\u001f\u0002\rA\u0013X\rZ3g\u0013\t\t%I\u0001\u0004TiJLgn\u001a\u0006\u0003\u007fqB3!\u0001#K!\t)\u0005*D\u0001G\u0015\t95$\u0001\u0006b]:|G/\u0019;j_:L!!\u0013$\u0003\u000bMKgnY3\"\u0003-\u000bQa\r\u00182]E\nA!^5eA!\u001a!\u0001\u0012&\u0002\rqJg.\u001b;?)\t\u0001\u0016\u000b\u0005\u0002)\u0001!)Ag\u0001a\u0001m!\u001a\u0011\u000b\u0012&)\u0007\r!%\nF\u0001QQ\r!AIS\u0001\u0011g\u0016$8+\u001a7fGRLwN\\'pI\u0016$\"\u0001W-\u000e\u0003\u0001AQAW\u0003A\u0002Y\nQA^1mk\u0016D3!\u0002#K\u0003U\u0019X\r^*fY\u0016\u001cG/[8o)\"\u0014Xm\u001d5pY\u0012$\"\u0001\u00170\t\u000bi3\u0001\u0019A0\u0011\u0005\u0001\fW\"\u0001\u001f\n\u0005\td$A\u0002#pk\ndW\rK\u0002\u0007\t*\u000bab]3u\r\u0016\fG/\u001e:f)f\u0004X\r\u0006\u0002YM\")!l\u0002a\u0001m!\u001aq\u0001\u0012&\u0002\u0019M,G\u000fT1cK2$\u0016\u0010]3\u0015\u0005aS\u0007\"\u0002.\t\u0001\u00041\u0004f\u0001\u0005E\u0015\u0006q1/\u001a;GK\u0006$XO]3t\u0007>dGC\u0001-o\u0011\u0015Q\u0016\u00021\u00017Q\rIAIS\u0001\rg\u0016$x*\u001e;qkR\u001cu\u000e\u001c\u000b\u00031JDQA\u0017\u0006A\u0002YB3A\u0003#K\u0003-\u0019X\r\u001e'bE\u0016d7i\u001c7\u0015\u0005a3\b\"\u0002.\f\u0001\u00041\u0004fA\u0006E\u0015\u0006\u0019a-\u001b;\u0015\u0005\u001dR\b\"B>\r\u0001\u0004a\u0018a\u00023bi\u0006\u001cX\r\u001e\u0019\u0004{\u0006-\u0001#\u0002@\u0002\u0004\u0005\u001dQ\"A@\u000b\u0007\u0005\u00051$A\u0002tc2L1!!\u0002\u0000\u0005\u001d!\u0015\r^1tKR\u0004B!!\u0003\u0002\f1\u0001AaCA\u0007u\u0006\u0005\t\u0011!B\u0001\u0003\u001f\u00111a\u0018\u00132#\u0011\t\t\"a\u0006\u0011\u0007\u0001\f\u0019\"C\u0002\u0002\u0016q\u0012qAT8uQ&tw\rE\u0002a\u00033I1!a\u0007=\u0005\r\te.\u001f\u0015\u0004\u0019\u0011S\u0015!D4fiR{\u0007/\u00138eS\u000e,7\u000f\u0006\u0004\u0002$\u0005=\u0012\u0011\u000b\t\u0006A\u0006\u0015\u0012\u0011F\u0005\u0004\u0003Oa$!B!se\u0006L\bc\u00011\u0002,%\u0019\u0011Q\u0006\u001f\u0003\u0007%sG\u000fC\u0004\u000225\u0001\r!a\r\u0002\u0005\u00114\u0007\u0003BA\u001b\u0003\u0017rA!a\u000e\u0002H9!\u0011\u0011HA#\u001d\u0011\tY$a\u0011\u000f\t\u0005u\u0012\u0011\t\b\u0004s\u0005}\u0012\"\u0001\u0011\n\u0005yy\u0012B\u0001\u000f\u001e\u0013\r\t\taG\u0005\u0004\u0003\u0013z\u0018a\u00029bG.\fw-Z\u0005\u0005\u0003\u001b\nyEA\u0005ECR\fgI]1nK*\u0019\u0011\u0011J@\t\u000f\u0005MS\u00021\u0001\u0002*\u0005\t1.\u0001\rtK2,7\r^%oI&\u001cWm\u001d$s_6\u0004f+\u00197vKN$\"\"a\t\u0002Z\u0005u\u0013\u0011MA3\u0011\u001d\tYF\u0004a\u0001\u0003S\t1B\\;n\r\u0016\fG/\u001e:fg\"9\u0011q\f\bA\u0002\u0005M\u0012\u0001\u0003:fgVdG\u000f\u0012$\t\r\u0005\rd\u00021\u00017\u00035\u0019X\r\\3di&|g.T8eK\"1\u0011q\r\bA\u0002}\u000b!c]3mK\u000e$\u0018n\u001c8UQJ,7\u000f[8mI\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002n\u0005e\u0004\u0003BA8\u0003kj!!!\u001d\u000b\u0007\u0005Mt0A\u0003usB,7/\u0003\u0003\u0002x\u0005E$AC*ueV\u001cG\u000fV=qK\"9\u00111P\bA\u0002\u00055\u0014AB:dQ\u0016l\u0017\rK\u0002\u0010\t*\u000bAaY8qsR\u0019\u0001+a!\t\u000f\u0005\u0015\u0005\u00031\u0001\u0002\b\u0006)Q\r\u001f;sCB!\u0011\u0011RAH\u001b\t\tYIC\u0002\u0002\u000ef\tQ\u0001]1sC6LA!!%\u0002\f\nA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u0011\t*C3\u0001\u0001#K\u0003e)f.\u001b<be&\fG/\u001a$fCR,(/Z*fY\u0016\u001cGo\u001c:\u0011\u0005!\u00122c\u0002\n\u0002\u001e\u0006\r\u0016\u0011\u0016\t\u0004A\u0006}\u0015bAAQy\t1\u0011I\\=SK\u001a\u0004BaLAS!&\u0019\u0011q\u0015\u0019\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u00111VA[\u001b\t\tiK\u0003\u0003\u00020\u0006E\u0016AA5p\u0015\t\t\u0019,\u0001\u0003kCZ\f\u0017\u0002BA\\\u0003[\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!!'\u0002\t1|\u0017\r\u001a\u000b\u0004!\u0006}\u0006BBAa)\u0001\u0007a'\u0001\u0003qCRD\u0007f\u0001\u000bE\u0015\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u001a\t\u0005\u0003\u0017\f\t.\u0004\u0002\u0002N*!\u0011qZAY\u0003\u0011a\u0017M\\4\n\t\u0005M\u0017Q\u001a\u0002\u0007\u001f\nTWm\u0019;)\u0007I!%\nK\u0002\u0012\t*\u0003"
)
public final class UnivariateFeatureSelector extends Estimator implements UnivariateFeatureSelectorParams, DefaultParamsWritable {
   private final String uid;
   private Param featureType;
   private Param labelType;
   private Param selectionMode;
   private DoubleParam selectionThreshold;
   private Param outputCol;
   private Param labelCol;
   private Param featuresCol;

   public static UnivariateFeatureSelector load(final String path) {
      return UnivariateFeatureSelector$.MODULE$.load(path);
   }

   public static MLReader read() {
      return UnivariateFeatureSelector$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getFeatureType() {
      return UnivariateFeatureSelectorParams.getFeatureType$(this);
   }

   public String getLabelType() {
      return UnivariateFeatureSelectorParams.getLabelType$(this);
   }

   public String getSelectionMode() {
      return UnivariateFeatureSelectorParams.getSelectionMode$(this);
   }

   public double getSelectionThreshold() {
      return UnivariateFeatureSelectorParams.getSelectionThreshold$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final Param featureType() {
      return this.featureType;
   }

   public final Param labelType() {
      return this.labelType;
   }

   public final Param selectionMode() {
      return this.selectionMode;
   }

   public final DoubleParam selectionThreshold() {
      return this.selectionThreshold;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$featureType_$eq(final Param x$1) {
      this.featureType = x$1;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$labelType_$eq(final Param x$1) {
      this.labelType = x$1;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionMode_$eq(final Param x$1) {
      this.selectionMode = x$1;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionThreshold_$eq(final DoubleParam x$1) {
      this.selectionThreshold = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public UnivariateFeatureSelector setSelectionMode(final String value) {
      return (UnivariateFeatureSelector)this.set(this.selectionMode(), value);
   }

   public UnivariateFeatureSelector setSelectionThreshold(final double value) {
      return (UnivariateFeatureSelector)this.set(this.selectionThreshold(), BoxesRunTime.boxToDouble(value));
   }

   public UnivariateFeatureSelector setFeatureType(final String value) {
      return (UnivariateFeatureSelector)this.set(this.featureType(), value);
   }

   public UnivariateFeatureSelector setLabelType(final String value) {
      return (UnivariateFeatureSelector)this.set(this.labelType(), value);
   }

   public UnivariateFeatureSelector setFeaturesCol(final String value) {
      return (UnivariateFeatureSelector)this.set(this.featuresCol(), value);
   }

   public UnivariateFeatureSelector setOutputCol(final String value) {
      return (UnivariateFeatureSelector)this.set(this.outputCol(), value);
   }

   public UnivariateFeatureSelector setLabelCol(final String value) {
      return (UnivariateFeatureSelector)this.set(this.labelCol(), value);
   }

   public UnivariateFeatureSelectorModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      int numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.featuresCol()));
      double threshold = Double.NaN;
      if (this.isSet(this.selectionThreshold())) {
         threshold = BoxesRunTime.unboxToDouble(this.$(this.selectionThreshold()));
      } else {
         label89: {
            String var6 = (String)this.$(this.selectionMode());
            switch (var6 == null ? 0 : var6.hashCode()) {
               case -921824963:
                  if (!"percentile".equals(var6)) {
                     throw new MatchError(var6);
                  }

                  threshold = 0.1;
                  break label89;
               case -315846324:
                  if (!"numTopFeatures".equals(var6)) {
                     throw new MatchError(var6);
                  }

                  threshold = (double)50.0F;
                  break label89;
               case 101236:
                  if (!"fdr".equals(var6)) {
                     throw new MatchError(var6);
                  }
                  break;
               case 101608:
                  if (!"fpr".equals(var6)) {
                     throw new MatchError(var6);
                  }
                  break;
               case 101812:
                  if (!"fwe".equals(var6)) {
                     throw new MatchError(var6);
                  }
                  break;
               default:
                  throw new MatchError(var6);
            }

            threshold = 0.05;
         }
      }

      label66: {
         Dataset var10000;
         label88: {
            Tuple2 var8 = new Tuple2(this.$(this.featureType()), this.$(this.labelType()));
            if (var8 != null) {
               String var9 = (String)var8._1();
               String var10 = (String)var8._2();
               if ("categorical".equals(var9) && "categorical".equals(var10)) {
                  var10000 = ChiSquareTest$.MODULE$.test(dataset.toDF(), this.getFeaturesCol(), this.getLabelCol(), true);
                  break label88;
               }
            }

            if (var8 != null) {
               String var11 = (String)var8._1();
               String var12 = (String)var8._2();
               if ("continuous".equals(var11) && "categorical".equals(var12)) {
                  var10000 = ANOVATest$.MODULE$.test(dataset.toDF(), this.getFeaturesCol(), this.getLabelCol(), true);
                  break label88;
               }
            }

            if (var8 == null) {
               break label66;
            }

            String var13 = (String)var8._1();
            String var14 = (String)var8._2();
            if (!"continuous".equals(var13) || !"continuous".equals(var14)) {
               break label66;
            }

            var10000 = FValueTest$.MODULE$.test(dataset.toDF(), this.getFeaturesCol(), this.getLabelCol(), true);
         }

         Dataset resultDF = var10000;
         int[] indices = this.selectIndicesFromPValues(numFeatures, resultDF, (String)this.$(this.selectionMode()), threshold);
         return (UnivariateFeatureSelectorModel)this.copyValues((new UnivariateFeatureSelectorModel(this.uid(), indices)).setParent(this), this.copyValues$default$2());
      }

      Object var10002 = this.$(this.featureType());
      throw new IllegalArgumentException("Unsupported combination: featureType=" + var10002 + ", labelType=" + this.$(this.labelType()));
   }

   private int[] getTopIndices(final Dataset df, final int k) {
      SparkSession spark = .MODULE$.builder().getOrCreate();
      return (int[])df.sort("pValue", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"featureIndex"}))).select("featureIndex", scala.collection.immutable.Nil..MODULE$).limit(k).as(spark.implicits().newIntEncoder()).collect();
   }

   public int[] selectIndicesFromPValues(final int numFeatures, final Dataset resultDF, final String selectionMode, final double selectionThreshold) {
      SparkSession spark = .MODULE$.builder().getOrCreate();
      int[] var10000;
      switch (selectionMode == null ? 0 : selectionMode.hashCode()) {
         case -921824963:
            if (!"percentile".equals(selectionMode)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + selectionMode);
            }

            var10000 = this.getTopIndices(resultDF, (int)((double)numFeatures * selectionThreshold));
            break;
         case -315846324:
            if (!"numTopFeatures".equals(selectionMode)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + selectionMode);
            }

            var10000 = this.getTopIndices(resultDF, (int)selectionThreshold);
            break;
         case 101236:
            if (!"fdr".equals(selectionMode)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + selectionMode);
            }

            double f = selectionThreshold / (double)numFeatures;
            int maxIndex = BoxesRunTime.unboxToInt(resultDF.sort("pValue", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"featureIndex"}))).select("pValue", scala.collection.immutable.Nil..MODULE$).as(spark.implicits().newDoubleEncoder()).rdd().zipWithIndex().flatMap((x0$1) -> {
               if (x0$1 != null) {
                  double pValue = x0$1._1$mcD$sp();
                  long index = x0$1._2$mcJ$sp();
                  return pValue <= f * (double)(index + 1L) ? scala.package..MODULE$.Iterator().single(BoxesRunTime.boxToInteger((int)index)) : scala.package..MODULE$.Iterator().empty();
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.Int()).fold(BoxesRunTime.boxToInteger(-1), (JFunction2.mcIII.sp)(x, y) -> scala.math.package..MODULE$.max(x, y)));
            var10000 = maxIndex >= 0 ? this.getTopIndices(resultDF, maxIndex + 1) : scala.Array..MODULE$.emptyIntArray();
            break;
         case 101608:
            if (!"fpr".equals(selectionMode)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + selectionMode);
            }

            var10000 = (int[])resultDF.select("featureIndex", scala.collection.immutable.Nil..MODULE$).where(org.apache.spark.sql.functions..MODULE$.col("pValue").$less(BoxesRunTime.boxToDouble(selectionThreshold))).as(spark.implicits().newIntEncoder()).collect();
            break;
         case 101812:
            if ("fwe".equals(selectionMode)) {
               var10000 = (int[])resultDF.select("featureIndex", scala.collection.immutable.Nil..MODULE$).where(org.apache.spark.sql.functions..MODULE$.col("pValue").$less(BoxesRunTime.boxToDouble(selectionThreshold / (double)numFeatures))).as(spark.implicits().newIntEncoder()).collect();
               break;
            }

            throw new IllegalArgumentException("Unknown Selector Type: " + selectionMode);
         default:
            throw new IllegalArgumentException("Unknown Selector Type: " + selectionMode);
      }

      int[] indices = var10000;
      return indices;
   }

   public StructType transformSchema(final StructType schema) {
      if (this.isSet(this.selectionThreshold())) {
         label86: {
            double threshold = BoxesRunTime.unboxToDouble(this.$(this.selectionThreshold()));
            String var4 = (String)this.$(this.selectionMode());
            switch (var4 == null ? 0 : var4.hashCode()) {
               case -921824963:
                  if (!"percentile".equals(var4)) {
                     throw new MatchError(var4);
                  }
                  break;
               case -315846324:
                  if (!"numTopFeatures".equals(var4)) {
                     throw new MatchError(var4);
                  }

                  scala.Predef..MODULE$.require(threshold >= (double)1 && (double)((int)threshold) == threshold, () -> "selectionThreshold needs to be a positive Integer for selection mode numTopFeatures, but got " + threshold);
                  break label86;
               case 101236:
                  if (!"fdr".equals(var4)) {
                     throw new MatchError(var4);
                  }
                  break;
               case 101608:
                  if (!"fpr".equals(var4)) {
                     throw new MatchError(var4);
                  }
                  break;
               case 101812:
                  if (!"fwe".equals(var4)) {
                     throw new MatchError(var4);
                  }
                  break;
               default:
                  throw new MatchError(var4);
            }

            scala.Predef..MODULE$.require((double)0 <= threshold && threshold <= (double)1, () -> {
               Object var10000 = this.$(this.selectionMode());
               return "selectionThreshold needs to be in the range [0, 1] for selection mode " + var10000 + ", but got " + threshold;
            });
         }
      }

      scala.Predef..MODULE$.require(this.isSet(this.featureType()) && this.isSet(this.labelType()), () -> "featureType and labelType need to be set");
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   public UnivariateFeatureSelector copy(final ParamMap extra) {
      return (UnivariateFeatureSelector)this.defaultCopy(extra);
   }

   public UnivariateFeatureSelector(final String uid) {
      this.uid = uid;
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasOutputCol.$init$(this);
      UnivariateFeatureSelectorParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public UnivariateFeatureSelector() {
      this(Identifiable$.MODULE$.randomUID("UnivariateFeatureSelector"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
