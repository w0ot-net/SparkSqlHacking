package org.apache.spark.ml;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005u4QAC\u0006\u0002\u0002QAQA\r\u0001\u0005\u0002MBQ!\u000f\u0001\u0005\u0002iBQ\u0001\u0013\u0001\u0005\u0002%CQa\u0013\u0001\u0005\u00021CQA\u0014\u0001\u0005B=CQ\u0001\u0018\u0001\u0007BuCQA\u001a\u0001\u0007\u0012\u001dDaA\u001c\u0001\u0005\u0002-y\u0007\"\u0002<\u0001\t\u0003:(!\u0003)sK\u0012L7\r^8s\u0015\taQ\"\u0001\u0002nY*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xm\u0001\u0001\u0016\tUIc\u0007H\n\u0004\u0001Yy\u0003cA\f\u001955\t1\"\u0003\u0002\u001a\u0017\tIQi\u001d;j[\u0006$xN\u001d\t\u00037qa\u0001\u0001B\u0003\u001e\u0001\t\u0007aDA\u0001N#\tyR\u0005\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013EA\u0004O_RD\u0017N\\4\u0011\t]1\u0003FG\u0005\u0003O-\u0011q\u0002\u0015:fI&\u001cG/[8o\u001b>$W\r\u001c\t\u00037%\"QA\u000b\u0001C\u0002-\u0012ABR3biV\u0014Xm\u001d+za\u0016\f\"a\b\u0017\u0011\u0005\u0001j\u0013B\u0001\u0018\"\u0005\r\te.\u001f\t\u0003/AJ!!M\u0006\u0003\u001fA\u0013X\rZ5di>\u0014\b+\u0019:b[N\fa\u0001P5oSRtD#\u0001\u001b\u0011\u000b]\u0001\u0001&\u000e\u000e\u0011\u0005m1D!B\u001c\u0001\u0005\u0004A$a\u0002'fCJtWM]\t\u0003?Q\n1b]3u\u0019\u0006\u0014W\r\\\"pYR\u0011Qg\u000f\u0005\u0006y\t\u0001\r!P\u0001\u0006m\u0006dW/\u001a\t\u0003}\u0015s!aP\"\u0011\u0005\u0001\u000bS\"A!\u000b\u0005\t\u001b\u0012A\u0002\u001fs_>$h(\u0003\u0002EC\u00051\u0001K]3eK\u001aL!AR$\u0003\rM#(/\u001b8h\u0015\t!\u0015%\u0001\btKR4U-\u0019;ve\u0016\u001c8i\u001c7\u0015\u0005UR\u0005\"\u0002\u001f\u0004\u0001\u0004i\u0014\u0001E:fiB\u0013X\rZ5di&|gnQ8m)\t)T\nC\u0003=\t\u0001\u0007Q(A\u0002gSR$\"A\u0007)\t\u000bE+\u0001\u0019\u0001*\u0002\u000f\u0011\fG/Y:fiB\u00121K\u0017\t\u0004)^KV\"A+\u000b\u0005Yk\u0011aA:rY&\u0011\u0001,\u0016\u0002\b\t\u0006$\u0018m]3u!\tY\"\fB\u0005\\!\u0006\u0005\t\u0011!B\u0001W\t\u0019q\fJ\u0019\u0002\t\r|\u0007/\u001f\u000b\u0003kyCQa\u0018\u0004A\u0002\u0001\fQ!\u001a=ue\u0006\u0004\"!\u00193\u000e\u0003\tT!aY\u0006\u0002\u000bA\f'/Y7\n\u0005\u0015\u0014'\u0001\u0003)be\u0006lW*\u00199\u0002\u000bQ\u0014\u0018-\u001b8\u0015\u0005iA\u0007\"B)\b\u0001\u0004I\u0007G\u00016m!\r!vk\u001b\t\u000371$\u0011\"\u001c5\u0002\u0002\u0003\u0005)\u0011A\u0016\u0003\u0007}##'\u0001\tgK\u0006$XO]3t\t\u0006$\u0018\rV=qKV\t\u0001\u000f\u0005\u0002ri6\t!O\u0003\u0002t+\u0006)A/\u001f9fg&\u0011QO\u001d\u0002\t\t\u0006$\u0018\rV=qK\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0002ywB\u0011\u0011/_\u0005\u0003uJ\u0014!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015a\u0018\u00021\u0001y\u0003\u0019\u00198\r[3nC\u0002"
)
public abstract class Predictor extends Estimator implements PredictorParams {
   private Param predictionCol;
   private Param featuresCol;
   private Param labelCol;

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public Predictor setLabelCol(final String value) {
      return (Predictor)this.set(this.labelCol(), value);
   }

   public Predictor setFeaturesCol(final String value) {
      return (Predictor)this.set(this.featuresCol(), value);
   }

   public Predictor setPredictionCol(final String value) {
      return (Predictor)this.set(this.predictionCol(), value);
   }

   public PredictionModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Metadata labelMeta = dataset.schema().apply((String)this.$(this.labelCol())).metadata();
      Dataset labelCasted = dataset.withColumn((String)this.$(this.labelCol()), .MODULE$.col((String)this.$(this.labelCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$), labelMeta);
      Dataset var10000;
      if (this instanceof HasWeightCol) {
         if (this.isDefined(((HasWeightCol)this).weightCol()) && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(((HasWeightCol)this).weightCol())))) {
            Metadata weightMeta = dataset.schema().apply((String)this.$(((HasWeightCol)this).weightCol())).metadata();
            var10000 = labelCasted.withColumn((String)this.$(((HasWeightCol)this).weightCol()), .MODULE$.col((String)this.$(((HasWeightCol)this).weightCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$), weightMeta);
         } else {
            var10000 = labelCasted;
         }
      } else {
         var10000 = labelCasted;
      }

      Dataset casted = var10000;
      return (PredictionModel)this.copyValues(this.train(casted).setParent(this), this.copyValues$default$2());
   }

   public abstract Predictor copy(final ParamMap extra);

   public abstract PredictionModel train(final Dataset dataset);

   public DataType featuresDataType() {
      return new VectorUDT();
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema, true, this.featuresDataType());
   }

   public Predictor() {
      HasLabelCol.$init$(this);
      HasFeaturesCol.$init$(this);
      HasPredictionCol.$init$(this);
      PredictorParams.$init$(this);
      Statics.releaseFence();
   }
}
