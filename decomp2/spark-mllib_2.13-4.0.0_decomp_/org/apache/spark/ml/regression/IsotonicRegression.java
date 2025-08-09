package org.apache.spark.ml.regression;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StorageLevel.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=e\u0001B\n\u0015\u0001}A\u0001\"\r\u0001\u0003\u0006\u0004%\tE\r\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005g!)1\n\u0001C\u0001\u0019\")1\n\u0001C\u0001#\")1\u000b\u0001C\u0001)\")\u0011\f\u0001C\u00015\")Q\f\u0001C\u0001=\")\u0011\r\u0001C\u0001E\")\u0011\u000e\u0001C\u0001U\")Q\u000e\u0001C\u0001]\")A\u000f\u0001C!k\"1q\u0010\u0001C!\u0003\u0003Aq!!\r\u0001\t\u0003\n\u0019dB\u0004\u0002JQA\t!a\u0013\u0007\rM!\u0002\u0012AA'\u0011\u0019Yu\u0002\"\u0001\u0002l!9\u0011QN\b\u0005B\u0005=\u0004\"CA>\u001f\u0005\u0005I\u0011BA?\u0005II5o\u001c;p]&\u001c'+Z4sKN\u001c\u0018n\u001c8\u000b\u0005U1\u0012A\u0003:fOJ,7o]5p]*\u0011q\u0003G\u0001\u0003[2T!!\u0007\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005ma\u0012AB1qC\u000eDWMC\u0001\u001e\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0001\u0005K\u0016\u0011\u0007\u0005\u0012C%D\u0001\u0017\u0013\t\u0019cCA\u0005FgRLW.\u0019;peB\u0011QEJ\u0007\u0002)%\u0011q\u0005\u0006\u0002\u0018\u0013N|Go\u001c8jGJ+wM]3tg&|g.T8eK2\u0004\"!J\u0015\n\u0005)\"\"AF%t_R|g.[2SK\u001e\u0014Xm]:j_:\u0014\u0015m]3\u0011\u00051zS\"A\u0017\u000b\u000592\u0012\u0001B;uS2L!\u0001M\u0017\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003M\u0002\"\u0001N\u001f\u000f\u0005UZ\u0004C\u0001\u001c:\u001b\u00059$B\u0001\u001d\u001f\u0003\u0019a$o\\8u})\t!(A\u0003tG\u0006d\u0017-\u0003\u0002=s\u00051\u0001K]3eK\u001aL!AP \u0003\rM#(/\u001b8h\u0015\ta\u0014\bK\u0002\u0002\u0003\u001e\u0003\"AQ#\u000e\u0003\rS!\u0001\u0012\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002G\u0007\n)1+\u001b8dK\u0006\n\u0001*A\u00032]Ur\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002B\u000f\u00061A(\u001b8jiz\"\"!\u0014(\u0011\u0005\u0015\u0002\u0001\"B\u0019\u0004\u0001\u0004\u0019\u0004f\u0001(B\u000f\"\u001a1!Q$\u0015\u00035C3\u0001B!H\u0003-\u0019X\r\u001e'bE\u0016d7i\u001c7\u0015\u0005U3V\"\u0001\u0001\t\u000b]+\u0001\u0019A\u001a\u0002\u000bY\fG.^3)\u0007\u0015\tu)\u0001\btKR4U-\u0019;ve\u0016\u001c8i\u001c7\u0015\u0005U[\u0006\"B,\u0007\u0001\u0004\u0019\u0004f\u0001\u0004B\u000f\u0006\u00012/\u001a;Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\u000b\u0003+~CQaV\u0004A\u0002MB3aB!H\u0003-\u0019X\r^%t_R|g.[2\u0015\u0005U\u001b\u0007\"B,\t\u0001\u0004!\u0007CA3g\u001b\u0005I\u0014BA4:\u0005\u001d\u0011un\u001c7fC:D3\u0001C!H\u00031\u0019X\r^,fS\u001eDGoQ8m)\t)6\u000eC\u0003X\u0013\u0001\u00071\u0007K\u0002\n\u0003\u001e\u000bqb]3u\r\u0016\fG/\u001e:f\u0013:$W\r\u001f\u000b\u0003+>DQa\u0016\u0006A\u0002A\u0004\"!Z9\n\u0005IL$aA%oi\"\u001a!\"Q$\u0002\t\r|\u0007/\u001f\u000b\u0003\u001bZDQa^\u0006A\u0002a\fQ!\u001a=ue\u0006\u0004\"!\u001f?\u000e\u0003iT!a\u001f\f\u0002\u000bA\f'/Y7\n\u0005uT(\u0001\u0003)be\u0006lW*\u00199)\u0007-\tu)A\u0002gSR$2\u0001JA\u0002\u0011\u001d\t)\u0001\u0004a\u0001\u0003\u000f\tq\u0001Z1uCN,G\u000f\r\u0003\u0002\n\u0005e\u0001CBA\u0006\u0003#\t)\"\u0004\u0002\u0002\u000e)\u0019\u0011q\u0002\r\u0002\u0007M\fH.\u0003\u0003\u0002\u0014\u00055!a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003/\tI\u0002\u0004\u0001\u0005\u0019\u0005m\u00111AA\u0001\u0002\u0003\u0015\t!!\b\u0003\u0007}##'\u0005\u0003\u0002 \u0005\u0015\u0002cA3\u0002\"%\u0019\u00111E\u001d\u0003\u000f9{G\u000f[5oOB\u0019Q-a\n\n\u0007\u0005%\u0012HA\u0002B]fDC\u0001D!\u0002.\u0005\u0012\u0011qF\u0001\u0006e9\u0002d\u0006M\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u0011QGA!!\u0011\t9$!\u0010\u000e\u0005\u0005e\"\u0002BA\u001e\u0003\u001b\tQ\u0001^=qKNLA!a\u0010\u0002:\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005\rS\u00021\u0001\u00026\u000511o\u00195f[\u0006D3!D!HQ\r\u0001\u0011iR\u0001\u0013\u0013N|Go\u001c8jGJ+wM]3tg&|g\u000e\u0005\u0002&\u001fM9q\"a\u0014\u0002V\u0005m\u0003cA3\u0002R%\u0019\u00111K\u001d\u0003\r\u0005s\u0017PU3g!\u0011a\u0013qK'\n\u0007\u0005eSFA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012\f'\r\\3\u0011\t\u0005u\u0013qM\u0007\u0003\u0003?RA!!\u0019\u0002d\u0005\u0011\u0011n\u001c\u0006\u0003\u0003K\nAA[1wC&!\u0011\u0011NA0\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\tY%\u0001\u0003m_\u0006$GcA'\u0002r!1\u00111O\tA\u0002M\nA\u0001]1uQ\"\"\u0011#QA<C\t\tI(A\u00032]Yr\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0000A!\u0011\u0011QAD\u001b\t\t\u0019I\u0003\u0003\u0002\u0006\u0006\r\u0014\u0001\u00027b]\u001eLA!!#\u0002\u0004\n1qJ\u00196fGRDCaD!\u0002x!\"a\"QA<\u0001"
)
public class IsotonicRegression extends Estimator implements IsotonicRegressionBase, DefaultParamsWritable {
   private final String uid;
   private BooleanParam isotonic;
   private IntParam featureIndex;
   private Param weightCol;
   private Param predictionCol;
   private Param labelCol;
   private Param featuresCol;

   public static IsotonicRegression load(final String path) {
      return IsotonicRegression$.MODULE$.load(path);
   }

   public static MLReader read() {
      return IsotonicRegression$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final boolean getIsotonic() {
      return IsotonicRegressionBase.getIsotonic$(this);
   }

   public final int getFeatureIndex() {
      return IsotonicRegressionBase.getFeatureIndex$(this);
   }

   public boolean hasWeightCol() {
      return IsotonicRegressionBase.hasWeightCol$(this);
   }

   public RDD extractWeightedLabeledPoints(final Dataset dataset) {
      return IsotonicRegressionBase.extractWeightedLabeledPoints$(this, dataset);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting) {
      return IsotonicRegressionBase.validateAndTransformSchema$(this, schema, fitting);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final BooleanParam isotonic() {
      return this.isotonic;
   }

   public final IntParam featureIndex() {
      return this.featureIndex;
   }

   public final void org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$isotonic_$eq(final BooleanParam x$1) {
      this.isotonic = x$1;
   }

   public final void org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$featureIndex_$eq(final IntParam x$1) {
      this.featureIndex = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
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

   public IsotonicRegression setLabelCol(final String value) {
      return (IsotonicRegression)this.set(this.labelCol(), value);
   }

   public IsotonicRegression setFeaturesCol(final String value) {
      return (IsotonicRegression)this.set(this.featuresCol(), value);
   }

   public IsotonicRegression setPredictionCol(final String value) {
      return (IsotonicRegression)this.set(this.predictionCol(), value);
   }

   public IsotonicRegression setIsotonic(final boolean value) {
      return (IsotonicRegression)this.set(this.isotonic(), BoxesRunTime.boxToBoolean(value));
   }

   public IsotonicRegression setWeightCol(final String value) {
      return (IsotonicRegression)this.set(this.weightCol(), value);
   }

   public IsotonicRegression setFeatureIndex(final int value) {
      return (IsotonicRegression)this.set(this.featureIndex(), BoxesRunTime.boxToInteger(value));
   }

   public IsotonicRegression copy(final ParamMap extra) {
      return (IsotonicRegression)this.defaultCopy(extra);
   }

   public IsotonicRegressionModel fit(final Dataset dataset) {
      return (IsotonicRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         RDD instances;
         boolean var8;
         label27: {
            label26: {
               this.transformSchema(dataset.schema(), true);
               instances = this.extractWeightedLabeledPoints(dataset);
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var5 = .MODULE$.NONE();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label26;
                  }
               } else if (var10000.equals(var5)) {
                  break label26;
               }

               var8 = false;
               break label27;
            }

            var8 = true;
         }

         boolean handlePersistence = var8;
         if (handlePersistence) {
            instances.persist(.MODULE$.MEMORY_AND_DISK());
         } else {
            BoxedUnit var9 = BoxedUnit.UNIT;
         }

         instr.logPipelineStage(this);
         instr.logDataset(dataset);
         instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.weightCol(), this.predictionCol(), this.featureIndex(), this.isotonic()}));
         instr.logNumFeatures(1L);
         org.apache.spark.mllib.regression.IsotonicRegression isotonicRegression = (new org.apache.spark.mllib.regression.IsotonicRegression()).setIsotonic(BoxesRunTime.unboxToBoolean(this.$(this.isotonic())));
         org.apache.spark.mllib.regression.IsotonicRegressionModel oldModel = isotonicRegression.run(instances);
         if (handlePersistence) {
            instances.unpersist(instances.unpersist$default$1());
         } else {
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

         return (IsotonicRegressionModel)this.copyValues((new IsotonicRegressionModel(this.uid(), oldModel)).setParent(this), this.copyValues$default$2());
      });
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema, true);
   }

   public IsotonicRegression(final String uid) {
      this.uid = uid;
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasPredictionCol.$init$(this);
      HasWeightCol.$init$(this);
      IsotonicRegressionBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public IsotonicRegression() {
      this(Identifiable$.MODULE$.randomUID("isoReg"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
