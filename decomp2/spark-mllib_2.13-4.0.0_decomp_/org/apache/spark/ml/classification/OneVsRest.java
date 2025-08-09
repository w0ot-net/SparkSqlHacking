package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.PredictionModel;
import org.apache.spark.ml.Predictor;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.Attribute$;
import org.apache.spark.ml.attribute.BinaryAttribute$;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.UnresolvedAttribute$;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasParallelism;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasRawPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.MetadataUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import scala.Function0;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rb\u0001B\u0010!\u0005-B\u0001\"\u0012\u0001\u0003\u0006\u0004%\tE\u0012\u0005\t;\u0002\u0011\t\u0011)A\u0005\u000f\")q\f\u0001C\u0001A\")q\f\u0001C\u0001K\")q\r\u0001C\u0001Q\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!a\f\u0001\t\u0003\t\t\u0004C\u0004\u0002B\u0001!\t!a\u0011\t\u000f\u0005%\u0003\u0001\"\u0011\u0002L!9\u00111\r\u0001\u0005B\u0005\u0015\u0004bBAA\u0001\u0011\u0005\u00131\u0011\u0005\b\u0003/\u0003A\u0011IAM\u000f\u001d\t)\u000b\tE\u0001\u0003O3aa\b\u0011\t\u0002\u0005%\u0006BB0\u0012\t\u0003\t9\rC\u0004\u0002JF!\t%a3\t\u000f\u0005U\u0017\u0003\"\u0011\u0002X\u001a9\u0011q\\\t\u0001#\u0005\u0005\b\"CAr+\t\u0005\t\u0015!\u0003b\u0011\u0019yV\u0003\"\u0001\u0002f\"9\u0011Q^\u000b\u0005R\u0005=hABA}#\u0011\tY\u0010\u0003\u0004`3\u0011\u0005\u0011Q \u0005\n\u0005\u0003I\"\u0019!C\u0005\u0005\u0007A\u0001Ba\u0004\u001aA\u0003%!Q\u0001\u0005\b\u0003+LB\u0011\tB\t\u0011%\u0011)\"EA\u0001\n\u0013\u00119BA\u0005P]\u001646OU3ti*\u0011\u0011EI\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\t\u0019C%\u0001\u0002nY*\u0011QEJ\u0001\u0006gB\f'o\u001b\u0006\u0003O!\na!\u00199bG\",'\"A\u0015\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001aCgN \u0011\u00075r\u0003'D\u0001#\u0013\ty#EA\u0005FgRLW.\u0019;peB\u0011\u0011GM\u0007\u0002A%\u00111\u0007\t\u0002\u000f\u001f:,gk\u001d*fgRlu\u000eZ3m!\t\tT'\u0003\u00027A\tyqJ\\3WgJ+7\u000f\u001e)be\u0006l7\u000f\u0005\u00029{5\t\u0011H\u0003\u0002;w\u000511\u000f[1sK\u0012T!\u0001\u0010\u0012\u0002\u000bA\f'/Y7\n\u0005yJ$A\u0004%bgB\u000b'/\u00197mK2L7/\u001c\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005\n\nA!\u001e;jY&\u0011A)\u0011\u0002\u000b\u001b2;&/\u001b;bE2,\u0017aA;jIV\tq\t\u0005\u0002I#:\u0011\u0011j\u0014\t\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019*\na\u0001\u0010:p_Rt$\"\u0001(\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ak\u0015A\u0002)sK\u0012,g-\u0003\u0002S'\n11\u000b\u001e:j]\u001eT!\u0001U')\u0007\u0005)6\f\u0005\u0002W36\tqK\u0003\u0002YI\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005i;&!B*j]\u000e,\u0017%\u0001/\u0002\u000bErCG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005U[\u0016A\u0002\u001fj]&$h\b\u0006\u0002bEB\u0011\u0011\u0007\u0001\u0005\u0006\u000b\u000e\u0001\ra\u0012\u0015\u0004EV[\u0006fA\u0002V7R\t\u0011\rK\u0002\u0005+n\u000bQb]3u\u00072\f7o]5gS\u0016\u0014HCA5k\u001b\u0005\u0001\u0001\"B6\u0006\u0001\u0004a\u0017!\u0002<bYV,\u0007'B7s{\u0006\u0005\u0001#B\u0019oar|\u0018BA8!\u0005)\u0019E.Y:tS\u001aLWM\u001d\t\u0003cJd\u0001\u0001B\u0005tU\u0006\u0005\t\u0011!B\u0001i\n!q\fJ\u00196#\t)\u0018\u0010\u0005\u0002wo6\tQ*\u0003\u0002y\u001b\n9aj\u001c;iS:<\u0007C\u0001<{\u0013\tYXJA\u0002B]f\u0004\"!]?\u0005\u0013yT\u0017\u0011!A\u0001\u0006\u0003!(\u0001B0%cY\u00022!]A\u0001\t)\t\u0019A[A\u0001\u0002\u0003\u0015\t\u0001\u001e\u0002\u0005?\u0012\nt\u0007K\u0002\u0006+n\u000b1b]3u\u0019\u0006\u0014W\r\\\"pYR\u0019\u0011.a\u0003\t\u000b-4\u0001\u0019A$)\t\u0019)\u0016qB\u0011\u0003\u0003#\tQ!\r\u00186]A\nab]3u\r\u0016\fG/\u001e:fg\u000e{G\u000eF\u0002j\u0003/AQa[\u0004A\u0002\u001dCCaB+\u0002\u0010\u0005\u00012/\u001a;Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\u000b\u0004S\u0006}\u0001\"B6\t\u0001\u00049\u0005\u0006\u0002\u0005V\u0003\u001f\t1c]3u%\u0006<\bK]3eS\u000e$\u0018n\u001c8D_2$2![A\u0014\u0011\u0015Y\u0017\u00021\u0001HQ\u0011IQ+a\u000b\"\u0005\u00055\u0012!\u0002\u001a/i9\u0002\u0014AD:fiB\u000b'/\u00197mK2L7/\u001c\u000b\u0004S\u0006M\u0002BB6\u000b\u0001\u0004\t)\u0004E\u0002w\u0003oI1!!\u000fN\u0005\rIe\u000e\u001e\u0015\u0005\u0015U\u000bi$\t\u0002\u0002@\u0005)!GL\u001a/a\u0005a1/\u001a;XK&<\u0007\u000e^\"pYR\u0019\u0011.!\u0012\t\u000b-\\\u0001\u0019A$)\t-)\u0016QH\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u0011QJA/!\u0011\ty%!\u0017\u000e\u0005\u0005E#\u0002BA*\u0003+\nQ\u0001^=qKNT1!a\u0016%\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u00037\n\tF\u0001\u0006TiJ,8\r\u001e+za\u0016Dq!a\u0018\r\u0001\u0004\ti%\u0001\u0004tG\",W.\u0019\u0015\u0004\u0019U[\u0016a\u00014jiR\u0019\u0001'a\u001a\t\u000f\u0005%T\u00021\u0001\u0002l\u00059A-\u0019;bg\u0016$\b\u0007BA7\u0003o\u0002b!a\u001c\u0002r\u0005UTBAA+\u0013\u0011\t\u0019(!\u0016\u0003\u000f\u0011\u000bG/Y:fiB\u0019\u0011/a\u001e\u0005\u0017\u0005e\u0014qMA\u0001\u0002\u0003\u0015\t\u0001\u001e\u0002\u0005?\u0012\n\u0004\b\u000b\u0003\u000e+\u0006u\u0014EAA@\u0003\u0015\u0011d\u0006\r\u00181\u0003\u0011\u0019w\u000e]=\u0015\u0007\u0005\f)\tC\u0004\u0002\b:\u0001\r!!#\u0002\u000b\u0015DHO]1\u0011\t\u0005-\u0015QR\u0007\u0002w%\u0019\u0011qR\u001e\u0003\u0011A\u000b'/Y7NCBDCAD+\u0002\u0014\u0006\u0012\u0011QS\u0001\u0006c9\"d&M\u0001\u0006oJLG/Z\u000b\u0003\u00037\u00032\u0001QAO\u0013\r\ty*\u0011\u0002\t\u001b2;&/\u001b;fe\"\"q\"VA?Q\r\u0001QkW\u0001\n\u001f:,gk\u001d*fgR\u0004\"!M\t\u0014\u000fE\tY+!-\u00028B\u0019a/!,\n\u0007\u0005=VJ\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0001\u0006M\u0016-C\u0002\u00026\u0006\u0013!\"\u0014'SK\u0006$\u0017M\u00197f!\u0011\tI,a1\u000e\u0005\u0005m&\u0002BA_\u0003\u007f\u000b!![8\u000b\u0005\u0005\u0005\u0017\u0001\u00026bm\u0006LA!!2\u0002<\na1+\u001a:jC2L'0\u00192mKR\u0011\u0011qU\u0001\u0005e\u0016\fG-\u0006\u0002\u0002NB!\u0001)a4b\u0013\r\t\t.\u0011\u0002\t\u001b2\u0013V-\u00193fe\"\"1#VA?\u0003\u0011aw.\u00193\u0015\u0007\u0005\fI\u000e\u0003\u0004\u0002\\R\u0001\raR\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u0015+\u0006u$aD(oKZ\u001b(+Z:u/JLG/\u001a:\u0014\u0007U\tY*\u0001\u0005j]N$\u0018M\\2f)\u0011\t9/a;\u0011\u0007\u0005%X#D\u0001\u0012\u0011\u0019\t\u0019o\u0006a\u0001C\u0006A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0002r\u0006]\bc\u0001<\u0002t&\u0019\u0011Q_'\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u00037D\u0002\u0019A$\u0003\u001f=sWMV:SKN$(+Z1eKJ\u001c2!GAg)\t\ty\u0010E\u0002\u0002jf\t\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\t\u0015\u0001\u0003\u0002B\u0004\u0005\u001bi!A!\u0003\u000b\t\t-\u0011qX\u0001\u0005Y\u0006tw-C\u0002S\u0005\u0013\t!b\u00197bgNt\u0015-\\3!)\r\t'1\u0003\u0005\u0007\u00037l\u0002\u0019A$\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\te\u0001\u0003\u0002B\u0004\u00057IAA!\b\u0003\n\t1qJ\u00196fGRDC!E+\u0002~!\"\u0001#VA?\u0001"
)
public final class OneVsRest extends Estimator implements OneVsRestParams, HasParallelism, MLWritable {
   private final String uid;
   private IntParam parallelism;
   private Param classifier;
   private Param weightCol;
   private Param rawPredictionCol;
   private Param predictionCol;
   private Param featuresCol;
   private Param labelCol;

   public static OneVsRest load(final String path) {
      return OneVsRest$.MODULE$.load(path);
   }

   public static MLReader read() {
      return OneVsRest$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getParallelism() {
      return HasParallelism.getParallelism$(this);
   }

   public ExecutionContext getExecutionContext() {
      return HasParallelism.getExecutionContext$(this);
   }

   public Classifier getClassifier() {
      return OneVsRestParams.getClassifier$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$classification$ClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getRawPredictionCol() {
      return HasRawPredictionCol.getRawPredictionCol$(this);
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

   public IntParam parallelism() {
      return this.parallelism;
   }

   public void org$apache$spark$ml$param$shared$HasParallelism$_setter_$parallelism_$eq(final IntParam x$1) {
      this.parallelism = x$1;
   }

   public Param classifier() {
      return this.classifier;
   }

   public void org$apache$spark$ml$classification$OneVsRestParams$_setter_$classifier_$eq(final Param x$1) {
      this.classifier = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param rawPredictionCol() {
      return this.rawPredictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasRawPredictionCol$_setter_$rawPredictionCol_$eq(final Param x$1) {
      this.rawPredictionCol = x$1;
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

   public String uid() {
      return this.uid;
   }

   public OneVsRest setClassifier(final Classifier value) {
      return (OneVsRest)this.set(this.classifier(), value);
   }

   public OneVsRest setLabelCol(final String value) {
      return (OneVsRest)this.set(this.labelCol(), value);
   }

   public OneVsRest setFeaturesCol(final String value) {
      return (OneVsRest)this.set(this.featuresCol(), value);
   }

   public OneVsRest setPredictionCol(final String value) {
      return (OneVsRest)this.set(this.predictionCol(), value);
   }

   public OneVsRest setRawPredictionCol(final String value) {
      return (OneVsRest)this.set(this.rawPredictionCol(), value);
   }

   public OneVsRest setParallelism(final int value) {
      return (OneVsRest)this.set(this.parallelism(), BoxesRunTime.boxToInteger(value));
   }

   public OneVsRest setWeightCol(final String value) {
      return (OneVsRest)this.set(this.weightCol(), value);
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema, true, this.getClassifier().featuresDataType());
   }

   public OneVsRestModel fit(final Dataset dataset) {
      return (OneVsRestModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         StructField labelSchema;
         int numClasses;
         boolean var21;
         label70: {
            this.transformSchema(dataset.schema());
            instr.logPipelineStage(this);
            instr.logDataset(dataset);
            instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.weightCol(), this.featuresCol(), this.predictionCol(), this.rawPredictionCol(), this.parallelism()}));
            instr.logNamedValue("classifier", this.$(this.classifier()).getClass().getCanonicalName());
            labelSchema = dataset.schema().apply((String)this.$(this.labelCol()));
            Function0 computeNumClasses = () -> {
               Row var6 = (Row)dataset.agg(org.apache.spark.sql.functions..MODULE$.max(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.labelCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$)), scala.collection.immutable.Nil..MODULE$).head();
               if (var6 != null) {
                  Some var7 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var6);
                  if (!var7.isEmpty() && var7.get() != null && ((SeqOps)var7.get()).lengthCompare(1) == 0) {
                     Object maxLabelIndex = ((SeqOps)var7.get()).apply(0);
                     if (maxLabelIndex instanceof Double) {
                        double var9 = BoxesRunTime.unboxToDouble(maxLabelIndex);
                        return (int)var9 + 1;
                     }
                  }
               }

               throw new MatchError(var6);
            };
            numClasses = BoxesRunTime.unboxToInt(MetadataUtils$.MODULE$.getNumClasses(labelSchema).fold(computeNumClasses, (JFunction1.mcII.sp)(x) -> BoxesRunTime.unboxToInt(scala.Predef..MODULE$.identity(BoxesRunTime.boxToInteger(x)))));
            instr.logNumClasses((long)numClasses);
            if (this.isDefined(this.weightCol()) && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.weightCol())))) {
               Classifier var10 = this.getClassifier();
               if (var10 instanceof HasWeightCol) {
                  var21 = true;
               } else {
                  instr.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"weightCol is ignored, as it is not supported by "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " now."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASSIFIER..MODULE$, var10)}))))));
                  var21 = false;
               }

               if (var21) {
                  var21 = true;
                  break label70;
               }
            }

            var21 = false;
         }

         boolean weightColIsUsed;
         Dataset multiclassLabeled;
         label62: {
            label61: {
               weightColIsUsed = var21;
               multiclassLabeled = weightColIsUsed ? dataset.select((String)this.$(this.labelCol()), .MODULE$.wrapRefArray((Object[])(new String[]{(String)this.$(this.featuresCol()), (String)this.$(this.weightCol())}))) : dataset.select((String)this.$(this.labelCol()), .MODULE$.wrapRefArray((Object[])(new String[]{(String)this.$(this.featuresCol())})));
               StorageLevel var22 = dataset.storageLevel();
               StorageLevel var13 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var22 == null) {
                  if (var13 == null) {
                     break label61;
                  }
               } else if (var22.equals(var13)) {
                  break label61;
               }

               var21 = false;
               break label62;
            }

            var21 = true;
         }

         boolean handlePersistence = var21;
         if (handlePersistence) {
            multiclassLabeled.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         } else {
            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         ExecutionContext executionContext = this.getExecutionContext();
         IndexedSeq modelFutures = scala.package..MODULE$.Range().apply(0, numClasses).map((index) -> $anonfun$fit$5(this, multiclassLabeled, weightColIsUsed, executionContext, BoxesRunTime.unboxToInt(index)));
         ClassificationModel[] models = (ClassificationModel[])((IterableOnceOps)modelFutures.map((x$5) -> (ClassificationModel)org.apache.spark.util.ThreadUtils..MODULE$.awaitResult(x$5, scala.concurrent.duration.Duration..MODULE$.Inf()))).toArray(scala.reflect.ClassTag..MODULE$.apply(ClassificationModel.class));
         instr.logNumFeatures((long)((PredictionModel)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(models))).numFeatures());
         if (handlePersistence) {
            multiclassLabeled.unpersist();
         } else {
            BoxedUnit var25 = BoxedUnit.UNIT;
         }

         Attribute var18 = Attribute$.MODULE$.fromStructField(labelSchema);
         Object var26;
         if (var18 instanceof NumericAttribute ? true : UnresolvedAttribute$.MODULE$.equals(var18)) {
            var26 = NominalAttribute$.MODULE$.defaultAttr().withName("label").withNumValues(numClasses);
         } else {
            if (var18 == null) {
               throw new MatchError(var18);
            }

            var26 = var18;
         }

         Attribute labelAttribute = (Attribute)var26;
         OneVsRestModel model = (OneVsRestModel)(new OneVsRestModel(this.uid(), labelAttribute.toMetadata(), models)).setParent(this);
         return (OneVsRestModel)this.copyValues(model, this.copyValues$default$2());
      });
   }

   public OneVsRest copy(final ParamMap extra) {
      OneVsRest copied = (OneVsRest)this.defaultCopy(extra);
      if (this.isDefined(this.classifier())) {
         copied.setClassifier((Classifier)((Predictor)this.$(this.classifier())).copy(extra));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return copied;
   }

   public MLWriter write() {
      return new OneVsRestWriter(this);
   }

   // $FF: synthetic method
   public static final Future $anonfun$fit$5(final OneVsRest $this, final Dataset multiclassLabeled$1, final boolean weightColIsUsed$1, final ExecutionContext executionContext$1, final int index) {
      Metadata newLabelMeta = BinaryAttribute$.MODULE$.defaultAttr().withName("label").toMetadata();
      String labelColName = "mc2b$" + index;
      Dataset trainingDataset = multiclassLabeled$1.withColumn(labelColName, org.apache.spark.sql.functions..MODULE$.when(org.apache.spark.sql.functions..MODULE$.col((String)$this.$($this.labelCol())).$eq$eq$eq(BoxesRunTime.boxToDouble((double)index)), BoxesRunTime.boxToDouble((double)1.0F)).otherwise(BoxesRunTime.boxToDouble((double)0.0F)), newLabelMeta);
      Classifier classifier = $this.getClassifier();
      ParamMap paramMap = new ParamMap();
      paramMap.put((Seq).MODULE$.wrapRefArray(new ParamPair[]{classifier.labelCol().$minus$greater(labelColName)}));
      paramMap.put((Seq).MODULE$.wrapRefArray(new ParamPair[]{classifier.featuresCol().$minus$greater($this.getFeaturesCol())}));
      paramMap.put((Seq).MODULE$.wrapRefArray(new ParamPair[]{classifier.predictionCol().$minus$greater($this.getPredictionCol())}));
      return scala.concurrent.Future..MODULE$.apply(() -> {
         if (weightColIsUsed$1) {
            paramMap.put((Seq).MODULE$.wrapRefArray(new ParamPair[]{((HasWeightCol)classifier).weightCol().$minus$greater($this.getWeightCol())}));
            return (ClassificationModel)classifier.fit(trainingDataset, paramMap);
         } else {
            return (ClassificationModel)classifier.fit(trainingDataset, paramMap);
         }
      }, executionContext$1);
   }

   public OneVsRest(final String uid) {
      this.uid = uid;
      HasLabelCol.$init$(this);
      HasFeaturesCol.$init$(this);
      HasPredictionCol.$init$(this);
      PredictorParams.$init$(this);
      HasRawPredictionCol.$init$(this);
      ClassifierParams.$init$(this);
      HasWeightCol.$init$(this);
      OneVsRestParams.$init$(this);
      HasParallelism.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public OneVsRest() {
      this(Identifiable$.MODULE$.randomUID("oneVsRest"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class OneVsRestWriter extends MLWriter {
      private final OneVsRest instance;

      public void saveImpl(final String path) {
         OneVsRestParams$.MODULE$.saveImpl(path, this.instance, this.sparkSession(), OneVsRestParams$.MODULE$.saveImpl$default$4());
      }

      public OneVsRestWriter(final OneVsRest instance) {
         this.instance = instance;
         OneVsRestParams$.MODULE$.validateParams(instance);
      }
   }

   private static class OneVsRestReader extends MLReader {
      private final String className = OneVsRest.class.getName();

      private String className() {
         return this.className;
      }

      public OneVsRest load(final String path) {
         Tuple2 var4 = OneVsRestParams$.MODULE$.loadImpl(path, this.sparkSession(), this.className());
         if (var4 != null) {
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var4._1();
            Classifier classifier = (Classifier)var4._2();
            Tuple2 var3 = new Tuple2(metadata, classifier);
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var3._1();
            Classifier classifier = (Classifier)var3._2();
            OneVsRest ovr = new OneVsRest(metadata.uid());
            metadata.getAndSetParams(ovr, metadata.getAndSetParams$default$2());
            return ovr.setClassifier(classifier);
         } else {
            throw new MatchError(var4);
         }
      }

      public OneVsRestReader() {
      }
   }
}
