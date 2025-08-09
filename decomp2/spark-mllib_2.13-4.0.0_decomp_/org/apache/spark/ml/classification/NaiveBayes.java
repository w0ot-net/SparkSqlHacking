package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import scala.Array;
import scala.Function1;
import scala.MatchError;
import scala.Predef;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.ArrayOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eh\u0001\u0002\u0010 \u0001)B\u0001B\u0011\u0001\u0003\u0006\u0004%\te\u0011\u0005\t5\u0002\u0011\t\u0011)A\u0005\t\")A\f\u0001C\u0001;\")A\f\u0001C\u0001C\")1\r\u0001C\u0001I\")Q\u000e\u0001C\u0001]\")\u0011\u000f\u0001C\u0001e\")q\u000f\u0001C)q\"A\u00111\u0004\u0001\u0005\u0002\r\ni\u0002C\u0004\u00026\u0001!I!a\u000e\t\u000f\u0005=\u0003\u0001\"\u0003\u0002R!9\u0011\u0011\r\u0001\u0005B\u0005\rtaBA=?!\u0005\u00111\u0010\u0004\u0007=}A\t!! \t\rqsA\u0011AAN\u0011%\tiJ\u0004b\u0001\n\u0003y2\tC\u0004\u0002 :\u0001\u000b\u0011\u0002#\t\u0013\u0005\u0005fB1A\u0005\u0002}\u0019\u0005bBAR\u001d\u0001\u0006I\u0001\u0012\u0005\n\u0003Ks!\u0019!C\u0001?\rCq!a*\u000fA\u0003%A\tC\u0005\u0002*:\u0011\r\u0011\"\u0001 \u0007\"9\u00111\u0016\b!\u0002\u0013!\u0005BCAW\u001d\t\u0007I\u0011A\u0010\u00020\"A\u0011\u0011\u0019\b!\u0002\u0013\t\t\f\u0003\u0005\u0002D:!\t!IAc\u0011!\t\tN\u0004C\u0001C\u0005M\u0007bBAl\u001d\u0011\u0005\u0013\u0011\u001c\u0005\n\u0003Kt\u0011\u0011!C\u0005\u0003O\u0014!BT1jm\u0016\u0014\u0015-_3t\u0015\t\u0001\u0013%\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u000b\u0005\t\u001a\u0013AA7m\u0015\t!S%A\u0003ta\u0006\u00148N\u0003\u0002'O\u00051\u0011\r]1dQ\u0016T\u0011\u0001K\u0001\u0004_J<7\u0001A\n\u0005\u0001-JD\bE\u0003-[=*d'D\u0001 \u0013\tqsDA\fQe>\u0014\u0017MY5mSN$\u0018nY\"mCN\u001c\u0018NZ5feB\u0011\u0001gM\u0007\u0002c)\u0011!'I\u0001\u0007Y&t\u0017\r\\4\n\u0005Q\n$A\u0002,fGR|'\u000f\u0005\u0002-\u0001A\u0011AfN\u0005\u0003q}\u0011qBT1jm\u0016\u0014\u0015-_3t\u001b>$W\r\u001c\t\u0003YiJ!aO\u0010\u0003!9\u000b\u0017N^3CCf,7\u000fU1sC6\u001c\bCA\u001fA\u001b\u0005q$BA \"\u0003\u0011)H/\u001b7\n\u0005\u0005s$!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7o\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#\u0001#\u0011\u0005\u0015seB\u0001$M!\t9%*D\u0001I\u0015\tI\u0015&\u0001\u0004=e>|GO\u0010\u0006\u0002\u0017\u0006)1oY1mC&\u0011QJS\u0001\u0007!J,G-\u001a4\n\u0005=\u0003&AB*ue&twM\u0003\u0002N\u0015\"\u001a\u0011A\u0015-\u0011\u0005M3V\"\u0001+\u000b\u0005U\u001b\u0013AC1o]>$\u0018\r^5p]&\u0011q\u000b\u0016\u0002\u0006'&t7-Z\u0011\u00023\u0006)\u0011GL\u001b/a\u0005!Q/\u001b3!Q\r\u0011!\u000bW\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005Ur\u0006\"\u0002\"\u0004\u0001\u0004!\u0005f\u00010S1\"\u001a1A\u0015-\u0015\u0003UB3\u0001\u0002*Y\u00031\u0019X\r^*n_>$\b.\u001b8h)\t)g-D\u0001\u0001\u0011\u00159W\u00011\u0001i\u0003\u00151\u0018\r\\;f!\tI'.D\u0001K\u0013\tY'J\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u000bIC\u0016\u0001D:fi6{G-\u001a7UsB,GCA3p\u0011\u00159g\u00011\u0001EQ\r1!\u000bW\u0001\rg\u0016$x+Z5hQR\u001cu\u000e\u001c\u000b\u0003KNDQaZ\u0004A\u0002\u0011C3a\u0002*vC\u00051\u0018!\u0002\u001a/c9\u0002\u0014!\u0002;sC&tGC\u0001\u001cz\u0011\u0015Q\b\u00021\u0001|\u0003\u001d!\u0017\r^1tKR\u00044\u0001`A\u0005!\u0015i\u0018\u0011AA\u0003\u001b\u0005q(BA@$\u0003\r\u0019\u0018\u000f\\\u0005\u0004\u0003\u0007q(a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003\u000f\tI\u0001\u0004\u0001\u0005\u0017\u0005-\u00110!A\u0001\u0002\u000b\u0005\u0011Q\u0002\u0002\u0004?\u0012\n\u0014\u0003BA\b\u0003+\u00012![A\t\u0013\r\t\u0019B\u0013\u0002\b\u001d>$\b.\u001b8h!\rI\u0017qC\u0005\u0004\u00033Q%aA!os\u0006\u0019BO]1j]^KG\u000f\u001b'bE\u0016d7\t[3dWR)a'a\b\u0002,!1!0\u0003a\u0001\u0003C\u0001D!a\t\u0002(A)Q0!\u0001\u0002&A!\u0011qAA\u0014\t1\tI#a\b\u0002\u0002\u0003\u0005)\u0011AA\u0007\u0005\ryFE\r\u0005\b\u0003[I\u0001\u0019AA\u0018\u0003AqwN\u001c(fO\u0006$\u0018N^3MC\n,G\u000eE\u0002j\u0003cI1!a\rK\u0005\u001d\u0011un\u001c7fC:\f\u0011\u0003\u001e:bS:$\u0015n]2sKR,\u0017*\u001c9m)\u00151\u0014\u0011HA#\u0011\u0019Q(\u00021\u0001\u0002<A\"\u0011QHA!!\u0015i\u0018\u0011AA !\u0011\t9!!\u0011\u0005\u0019\u0005\r\u0013\u0011HA\u0001\u0002\u0003\u0015\t!!\u0004\u0003\u0007}#3\u0007C\u0004\u0002H)\u0001\r!!\u0013\u0002\u000b%t7\u000f\u001e:\u0011\u0007u\nY%C\u0002\u0002Ny\u0012q\"\u00138tiJ,X.\u001a8uCRLwN\\\u0001\u0012iJ\f\u0017N\\$bkN\u001c\u0018.\u00198J[BdG#\u0002\u001c\u0002T\u0005}\u0003B\u0002>\f\u0001\u0004\t)\u0006\r\u0003\u0002X\u0005m\u0003#B?\u0002\u0002\u0005e\u0003\u0003BA\u0004\u00037\"A\"!\u0018\u0002T\u0005\u0005\t\u0011!B\u0001\u0003\u001b\u00111a\u0018\u00135\u0011\u001d\t9e\u0003a\u0001\u0003\u0013\nAaY8qsR\u0019Q'!\u001a\t\u000f\u0005\u001dD\u00021\u0001\u0002j\u0005)Q\r\u001f;sCB!\u00111NA9\u001b\t\tiGC\u0002\u0002p\u0005\nQ\u0001]1sC6LA!a\u001d\u0002n\tA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\r%bC3\u0001\u0001*Y\u0003)q\u0015-\u001b<f\u0005\u0006LXm\u001d\t\u0003Y9\u0019rADA@\u0003\u000b\u000bY\tE\u0002j\u0003\u0003K1!a!K\u0005\u0019\te.\u001f*fMB!Q(a\"6\u0013\r\tII\u0010\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\ti)a&\u000e\u0005\u0005=%\u0002BAI\u0003'\u000b!![8\u000b\u0005\u0005U\u0015\u0001\u00026bm\u0006LA!!'\u0002\u0010\na1+\u001a:jC2L'0\u00192mKR\u0011\u00111P\u0001\f\u001bVdG/\u001b8p[&\fG.\u0001\u0007Nk2$\u0018N\\8nS\u0006d\u0007%A\u0005CKJtw.\u001e7mS\u0006Q!)\u001a:o_VdG.\u001b\u0011\u0002\u0011\u001d\u000bWo]:jC:\f\u0011bR1vgNL\u0017M\u001c\u0011\u0002\u0015\r{W\u000e\u001d7f[\u0016tG/A\u0006D_6\u0004H.Z7f]R\u0004\u0013aE:vaB|'\u000f^3e\u001b>$W\r\u001c+za\u0016\u001cXCAAY!\u0015\t\u0019,!0E\u001b\t\t)L\u0003\u0003\u00028\u0006e\u0016!C5n[V$\u0018M\u00197f\u0015\r\tYLS\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA`\u0003k\u00131aU3u\u0003Q\u0019X\u000f\u001d9peR,G-T8eK2$\u0016\u0010]3tA\u0005A\"/Z9vSJ,gj\u001c8oK\u001e\fG/\u001b<f-\u0006dW/Z:\u0015\t\u0005\u001d\u0017Q\u001a\t\u0004S\u0006%\u0017bAAf\u0015\n!QK\\5u\u0011\u0019\tyM\u0007a\u0001_\u0005\ta/A\u000fsKF,\u0018N]3[KJ|wJ\\3CKJtw.\u001e7mSZ\u000bG.^3t)\u0011\t9-!6\t\r\u0005=7\u00041\u00010\u0003\u0011aw.\u00193\u0015\u0007U\nY\u000e\u0003\u0004\u0002^r\u0001\r\u0001R\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u001d%\u0006\u0005\u0018EAAr\u0003\u0015\tdF\u000e\u00181\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u000f\u0005\u0003\u0002l\u0006EXBAAw\u0015\u0011\ty/a%\u0002\t1\fgnZ\u0005\u0005\u0003g\fiO\u0001\u0004PE*,7\r\u001e\u0015\u0005\u001dI\u000b\t\u000f\u000b\u0003\u000e%\u0006\u0005\b"
)
public class NaiveBayes extends ProbabilisticClassifier implements NaiveBayesParams, DefaultParamsWritable {
   private final String uid;
   private DoubleParam smoothing;
   private Param modelType;
   private Param weightCol;

   public static NaiveBayes load(final String path) {
      return NaiveBayes$.MODULE$.load(path);
   }

   public static MLReader read() {
      return NaiveBayes$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final double getSmoothing() {
      return NaiveBayesParams.getSmoothing$(this);
   }

   public final String getModelType() {
      return NaiveBayesParams.getModelType$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final DoubleParam smoothing() {
      return this.smoothing;
   }

   public final Param modelType() {
      return this.modelType;
   }

   public final void org$apache$spark$ml$classification$NaiveBayesParams$_setter_$smoothing_$eq(final DoubleParam x$1) {
      this.smoothing = x$1;
   }

   public final void org$apache$spark$ml$classification$NaiveBayesParams$_setter_$modelType_$eq(final Param x$1) {
      this.modelType = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public NaiveBayes setSmoothing(final double value) {
      return (NaiveBayes)this.set(this.smoothing(), BoxesRunTime.boxToDouble(value));
   }

   public NaiveBayes setModelType(final String value) {
      return (NaiveBayes)this.set(this.modelType(), value);
   }

   public NaiveBayes setWeightCol(final String value) {
      return (NaiveBayes)this.set(this.weightCol(), value);
   }

   public NaiveBayesModel train(final Dataset dataset) {
      return this.trainWithLabelCheck(dataset, true);
   }

   public NaiveBayesModel trainWithLabelCheck(final Dataset dataset, final boolean nonNegativeLabel) {
      return (NaiveBayesModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         Column validatedLabelCol;
         Column validatedWeightCol;
         Column vecCol;
         String var12;
         boolean var23;
         label113: {
            label117: {
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.weightCol(), this.predictionCol(), this.rawPredictionCol(), this.probabilityCol(), this.modelType(), this.smoothing(), this.thresholds()}));
               validatedLabelCol = nonNegativeLabel ? DatasetUtils$.MODULE$.checkClassificationLabels((String)this.$(this.labelCol()), this.get(this.thresholds()).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$trainWithLabelCheck$2(x$1)))) : DatasetUtils$.MODULE$.checkRegressionLabels((String)this.$(this.labelCol()));
               validatedWeightCol = DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()));
               vecCol = org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()));
               var12 = (String)this.$(this.modelType());
               String var10000 = NaiveBayes$.MODULE$.Multinomial();
               if (var10000 == null) {
                  if (var12 == null) {
                     break label117;
                  }
               } else if (var10000.equals(var12)) {
                  break label117;
               }

               label105: {
                  var10000 = NaiveBayes$.MODULE$.Complement();
                  if (var10000 == null) {
                     if (var12 == null) {
                        break label105;
                     }
                  } else if (var10000.equals(var12)) {
                     break label105;
                  }

                  var23 = false;
                  break label113;
               }

               var23 = true;
               break label113;
            }

            var23 = true;
         }

         Column var24;
         if (var23) {
            var24 = org.apache.spark.sql.functions..MODULE$.when(vecCol.isNull(), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.lit("Vectors MUST NOT be Null"))).when(org.apache.spark.sql.functions..MODULE$.exists(org.apache.spark.sql.functions..MODULE$.unwrap_udt(vecCol).getField("values"), (v) -> v.isNaN().$bar$bar(v.$less(BoxesRunTime.boxToInteger(0))).$bar$bar(v.$eq$eq$eq(BoxesRunTime.boxToDouble(Double.POSITIVE_INFINITY)))), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.concat(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.lit("Vector values MUST NOT be Negative, NaN or Infinity, but got "), vecCol.cast(org.apache.spark.sql.types.StringType..MODULE$)}))))).otherwise(vecCol);
         } else {
            label97: {
               label96: {
                  String var25 = NaiveBayes$.MODULE$.Bernoulli();
                  if (var25 == null) {
                     if (var12 == null) {
                        break label96;
                     }
                  } else if (var25.equals(var12)) {
                     break label96;
                  }

                  var24 = DatasetUtils$.MODULE$.checkNonNanVectors(vecCol);
                  break label97;
               }

               var24 = org.apache.spark.sql.functions..MODULE$.when(vecCol.isNull(), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.lit("Vectors MUST NOT be Null"))).when(org.apache.spark.sql.functions..MODULE$.exists(org.apache.spark.sql.functions..MODULE$.unwrap_udt(vecCol).getField("values"), (v) -> v.$eq$bang$eq(BoxesRunTime.boxToInteger(0)).$amp$amp(v.$eq$bang$eq(BoxesRunTime.boxToInteger(1)))), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.concat(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.lit("Vector values MUST be in {0, 1}, but got "), vecCol.cast(org.apache.spark.sql.types.StringType..MODULE$)}))))).otherwise(vecCol);
            }
         }

         Dataset validated;
         String var17;
         label89: {
            label118: {
               Column validatedfeaturesCol = var24;
               validated = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{validatedLabelCol.as("_validated_label_"), validatedWeightCol.as("_validated_weight_"), validatedfeaturesCol.as("_validated_features_")})));
               var17 = (String)this.$(this.modelType());
               String var26 = NaiveBayes$.MODULE$.Bernoulli();
               if (var26 == null) {
                  if (var17 == null) {
                     break label118;
                  }
               } else if (var26.equals(var17)) {
                  break label118;
               }

               label119: {
                  var26 = NaiveBayes$.MODULE$.Multinomial();
                  if (var26 == null) {
                     if (var17 == null) {
                        break label119;
                     }
                  } else if (var26.equals(var17)) {
                     break label119;
                  }

                  label74: {
                     var26 = NaiveBayes$.MODULE$.Complement();
                     if (var26 == null) {
                        if (var17 == null) {
                           break label74;
                        }
                     } else if (var26.equals(var17)) {
                        break label74;
                     }

                     var29 = false;
                     break label89;
                  }

                  var29 = true;
                  break label89;
               }

               var29 = true;
               break label89;
            }

            var29 = true;
         }

         if (var29) {
            return this.trainDiscreteImpl(validated, instr);
         } else {
            String var30 = NaiveBayes$.MODULE$.Gaussian();
            if (var30 == null) {
               if (var17 == null) {
                  return this.trainGaussianImpl(validated, instr);
               }
            } else if (var30.equals(var17)) {
               return this.trainGaussianImpl(validated, instr);
            }

            throw new IllegalArgumentException("Invalid modelType: " + this.$(this.modelType()) + ".");
         }
      });
   }

   private NaiveBayesModel trainDiscreteImpl(final Dataset dataset, final Instrumentation instr) {
      SparkSession spark = dataset.sparkSession();
      String[] var10 = dataset.schema().fieldNames();
      if (var10 != null) {
         Object var11 = scala.Array..MODULE$.unapplySeq(var10);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var11) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 3) == 0) {
            Tuple4[] aggregated;
            int numFeatures;
            int numLabels;
            double numDocuments;
            double[] labelArray;
            double[] piArray;
            double[] thetaArray;
            String var31;
            boolean var50;
            label104: {
               label112: {
                  String label = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 0);
                  String weight = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 1);
                  String featuers = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 2);
                  Tuple3 var9 = new Tuple3(label, weight, featuers);
                  String label = (String)var9._1();
                  String weight = (String)var9._2();
                  String featuers = (String)var9._3();
                  ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
                  Predef var10001 = scala.Predef..MODULE$;
                  Dataset var10002 = dataset.groupBy(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(label)}))).agg(org.apache.spark.sql.functions..MODULE$.sum(org.apache.spark.sql.functions..MODULE$.col(weight)).as("weightSum"), .MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{"sum", "count"}))).summary(org.apache.spark.sql.functions..MODULE$.col(featuers), org.apache.spark.sql.functions..MODULE$.col(weight)).as("summary")}))).select(label, .MODULE$.wrapRefArray((Object[])(new String[]{"weightSum", "summary.sum", "summary.count"})));
                  SQLImplicits var10003 = spark.implicits();
                  JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
                  JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NaiveBayes.class.getClassLoader());

                  final class $typecreator5$1 extends TypeCreator {
                     public Types.TypeApi apply(final Mirror $m$untyped) {
                        Universe $u = $m$untyped.universe();
                        return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Long").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)))));
                     }

                     public $typecreator5$1() {
                     }
                  }

                  aggregated = (Tuple4[])var10000.sortBy$extension(var10001.refArrayOps(var10002.as(var10003.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).collect()), (x$3) -> BoxesRunTime.boxToDouble($anonfun$trainDiscreteImpl$1(x$3)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
                  numFeatures = ((Vector)((Tuple4)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated)))._3()).size();
                  instr.logNumFeatures((long)numFeatures);
                  long numSamples = BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated), (x$4) -> BoxesRunTime.boxToLong($anonfun$trainDiscreteImpl$2(x$4)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
                  instr.logNumExamples(numSamples);
                  numLabels = aggregated.length;
                  instr.logNumClasses((long)numLabels);
                  numDocuments = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated), (x$5) -> BoxesRunTime.boxToDouble($anonfun$trainDiscreteImpl$3(x$5)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
                  instr.logSumOfWeights(numDocuments);
                  labelArray = new double[numLabels];
                  piArray = new double[numLabels];
                  thetaArray = new double[numLabels * numFeatures];
                  var31 = (String)this.$(this.modelType());
                  String var48 = NaiveBayes$.MODULE$.Multinomial();
                  if (var48 == null) {
                     if (var31 == null) {
                        break label112;
                     }
                  } else if (var48.equals(var31)) {
                     break label112;
                  }

                  label96: {
                     var48 = NaiveBayes$.MODULE$.Bernoulli();
                     if (var48 == null) {
                        if (var31 == null) {
                           break label96;
                        }
                     } else if (var48.equals(var31)) {
                        break label96;
                     }

                     var50 = false;
                     break label104;
                  }

                  var50 = true;
                  break label104;
               }

               var50 = true;
            }

            Iterator var51;
            if (var50) {
               var51 = scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated));
            } else {
               String var52 = NaiveBayes$.MODULE$.Complement();
               if (var52 == null) {
                  if (var31 != null) {
                     throw new MatchError(var31);
                  }
               } else if (!var52.equals(var31)) {
                  throw new MatchError(var31);
               }

               Vector featureSum = org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(numFeatures);
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated), (x0$1) -> {
                  $anonfun$trainDiscreteImpl$4(featureSum, x0$1);
                  return BoxedUnit.UNIT;
               });
               var51 = scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated)).map((x0$2) -> {
                  if (x0$2 != null) {
                     double label = BoxesRunTime.unboxToDouble(x0$2._1());
                     double n = BoxesRunTime.unboxToDouble(x0$2._2());
                     Vector sumTermFreqs = (Vector)x0$2._3();
                     long count = BoxesRunTime.unboxToLong(x0$2._4());
                     Vector comp = featureSum.copy();
                     org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)-1.0F, sumTermFreqs, comp);
                     return new Tuple4(BoxesRunTime.boxToDouble(label), BoxesRunTime.boxToDouble(n), comp, BoxesRunTime.boxToLong(count));
                  } else {
                     throw new MatchError(x0$2);
                  }
               });
            }

            Vector pi;
            String var42;
            label80: {
               label114: {
                  Iterator aggIter = var51;
                  double lambda = BoxesRunTime.unboxToDouble(this.$(this.smoothing()));
                  double piLogDenom = scala.math.package..MODULE$.log(numDocuments + (double)numLabels * lambda);
                  IntRef i = IntRef.create(0);
                  aggIter.foreach((x0$3) -> {
                     $anonfun$trainDiscreteImpl$6(this, labelArray, i, piArray, lambda, piLogDenom, numFeatures, thetaArray, x0$3);
                     return BoxedUnit.UNIT;
                  });
                  pi = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(piArray);
                  var42 = (String)this.$(this.modelType());
                  String var53 = NaiveBayes$.MODULE$.Multinomial();
                  if (var53 == null) {
                     if (var42 == null) {
                        break label114;
                     }
                  } else if (var53.equals(var42)) {
                     break label114;
                  }

                  label72: {
                     var53 = NaiveBayes$.MODULE$.Bernoulli();
                     if (var53 == null) {
                        if (var42 == null) {
                           break label72;
                        }
                     } else if (var53.equals(var42)) {
                        break label72;
                     }

                     var55 = false;
                     break label80;
                  }

                  var55 = true;
                  break label80;
               }

               var55 = true;
            }

            if (var55) {
               DenseMatrix theta = new DenseMatrix(numLabels, numFeatures, thetaArray, true);
               return (new NaiveBayesModel(this.uid(), pi.compressed(), theta.compressed(), org.apache.spark.ml.linalg.Matrices..MODULE$.zeros(0, 0))).setOldLabels(labelArray);
            }

            label64: {
               String var56 = NaiveBayes$.MODULE$.Complement();
               if (var56 == null) {
                  if (var42 == null) {
                     break label64;
                  }
               } else if (var56.equals(var42)) {
                  break label64;
               }

               throw new MatchError(var42);
            }

            DenseMatrix theta = new DenseMatrix(numLabels, numFeatures, (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(thetaArray), (JFunction1.mcDD.sp)(v) -> -v, scala.reflect.ClassTag..MODULE$.Double()), true);
            return new NaiveBayesModel(this.uid(), pi.compressed(), theta.compressed(), org.apache.spark.ml.linalg.Matrices..MODULE$.zeros(0, 0));
         }
      }

      throw new MatchError(var10);
   }

   private NaiveBayesModel trainGaussianImpl(final Dataset dataset, final Instrumentation instr) {
      SparkSession spark = dataset.sparkSession();
      String[] var6 = dataset.schema().fieldNames();
      if (var6 != null) {
         Object var7 = scala.Array..MODULE$.unapplySeq(var6);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var7) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 3) == 0) {
            String label = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 0);
            String weight = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 1);
            String featuers = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 2);
            Tuple3 var5 = new Tuple3(label, weight, featuers);
            String label = (String)var5._1();
            String weight = (String)var5._2();
            String featuers = (String)var5._3();
            ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
            Predef var10001 = scala.Predef..MODULE$;
            Dataset var10002 = dataset.groupBy(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(label)}))).agg(org.apache.spark.sql.functions..MODULE$.sum(org.apache.spark.sql.functions..MODULE$.col(weight)).as("weightSum"), .MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{"mean", "normL2"}))).summary(org.apache.spark.sql.functions..MODULE$.col(featuers), org.apache.spark.sql.functions..MODULE$.col(weight)).as("summary")}))).select(label, .MODULE$.wrapRefArray((Object[])(new String[]{"weightSum", "summary.mean", "summary.normL2"})));
            SQLImplicits var10003 = spark.implicits();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NaiveBayes.class.getClassLoader());

            final class $typecreator5$2 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)))));
               }

               public $typecreator5$2() {
               }
            }

            var10002 = var10002.as(var10003.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$2())));
            Function1 var35 = (x0$1) -> {
               if (x0$1 != null) {
                  double label = BoxesRunTime.unboxToDouble(x0$1._1());
                  double weightSum = BoxesRunTime.unboxToDouble(x0$1._2());
                  Vector mean = (Vector)x0$1._3();
                  Vector normL2 = (Vector)x0$1._4();
                  return new Tuple4(BoxesRunTime.boxToDouble(label), BoxesRunTime.boxToDouble(weightSum), mean, org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(normL2.toArray()), (JFunction1.mcDD.sp)(v) -> v * v, scala.reflect.ClassTag..MODULE$.Double())));
               } else {
                  throw new MatchError(x0$1);
               }
            };
            SQLImplicits var10004 = spark.implicits();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NaiveBayes.class.getClassLoader());

            final class $typecreator10$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple4"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)))));
               }

               public $typecreator10$1() {
               }
            }

            Tuple4[] aggregated = (Tuple4[])var10000.sortBy$extension(var10001.refArrayOps(var10002.map(var35, var10004.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).collect()), (x$7) -> BoxesRunTime.boxToDouble($anonfun$trainGaussianImpl$3(x$7)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
            int numFeatures = ((Vector)((Tuple4)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated)))._3()).size();
            instr.logNumFeatures((long)numFeatures);
            int numLabels = aggregated.length;
            instr.logNumClasses((long)numLabels);
            double numInstances = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated), (x$8) -> BoxesRunTime.boxToDouble($anonfun$trainGaussianImpl$4(x$8)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            instr.logSumOfWeights(numInstances);
            double epsilon = BoxesRunTime.unboxToDouble(scala.package..MODULE$.Iterator().range(0, numFeatures).map((JFunction1.mcDI.sp)(j) -> {
               DoubleRef globalSum = DoubleRef.create((double)0.0F);
               DoubleRef globalSqrSum = DoubleRef.create((double)0.0F);
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated), (x0$2) -> {
                  $anonfun$trainGaussianImpl$6(globalSum, j, globalSqrSum, x0$2);
                  return BoxedUnit.UNIT;
               });
               return globalSqrSum.elem / numInstances - globalSum.elem * globalSum.elem / numInstances / numInstances;
            }).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)) * 1.0E-9;
            double[] piArray = new double[numLabels];
            double[] thetaArray = new double[numLabels * numFeatures];
            double[] sigmaArray = new double[numLabels * numFeatures];
            IntRef i = IntRef.create(0);
            double logNumInstances = scala.math.package..MODULE$.log(numInstances);
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])aggregated), (x0$3) -> {
               $anonfun$trainGaussianImpl$7(piArray, i, logNumInstances, numFeatures, thetaArray, sigmaArray, epsilon, x0$3);
               return BoxedUnit.UNIT;
            });
            Vector pi = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(piArray);
            DenseMatrix theta = new DenseMatrix(numLabels, numFeatures, thetaArray, true);
            DenseMatrix sigma = new DenseMatrix(numLabels, numFeatures, sigmaArray, true);
            return new NaiveBayesModel(this.uid(), pi.compressed(), theta.compressed(), sigma.compressed());
         }
      }

      throw new MatchError(var6);
   }

   public NaiveBayes copy(final ParamMap extra) {
      return (NaiveBayes)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final int $anonfun$trainWithLabelCheck$2(final double[] x$1) {
      return x$1.length;
   }

   // $FF: synthetic method
   public static final double $anonfun$trainDiscreteImpl$1(final Tuple4 x$3) {
      return BoxesRunTime.unboxToDouble(x$3._1());
   }

   // $FF: synthetic method
   public static final long $anonfun$trainDiscreteImpl$2(final Tuple4 x$4) {
      return BoxesRunTime.unboxToLong(x$4._4());
   }

   // $FF: synthetic method
   public static final double $anonfun$trainDiscreteImpl$3(final Tuple4 x$5) {
      return BoxesRunTime.unboxToDouble(x$5._2());
   }

   // $FF: synthetic method
   public static final void $anonfun$trainDiscreteImpl$4(final Vector featureSum$1, final Tuple4 x0$1) {
      if (x0$1 != null) {
         Vector sumTermFreqs = (Vector)x0$1._3();
         org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, sumTermFreqs, featureSum$1);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$trainDiscreteImpl$6(final NaiveBayes $this, final double[] labelArray$1, final IntRef i$1, final double[] piArray$1, final double lambda$1, final double piLogDenom$1, final int numFeatures$1, final double[] thetaArray$1, final Tuple4 x0$3) {
      if (x0$3 == null) {
         throw new MatchError(x0$3);
      } else {
         double n;
         Vector sumTermFreqs;
         String var23;
         boolean var30;
         label63: {
            label67: {
               double label = BoxesRunTime.unboxToDouble(x0$3._1());
               n = BoxesRunTime.unboxToDouble(x0$3._2());
               sumTermFreqs = (Vector)x0$3._3();
               labelArray$1[i$1.elem] = label;
               piArray$1[i$1.elem] = scala.math.package..MODULE$.log(n + lambda$1) - piLogDenom$1;
               var23 = (String)$this.$($this.modelType());
               String var10000 = NaiveBayes$.MODULE$.Multinomial();
               if (var10000 == null) {
                  if (var23 == null) {
                     break label67;
                  }
               } else if (var10000.equals(var23)) {
                  break label67;
               }

               label55: {
                  var10000 = NaiveBayes$.MODULE$.Complement();
                  if (var10000 == null) {
                     if (var23 == null) {
                        break label55;
                     }
                  } else if (var10000.equals(var23)) {
                     break label55;
                  }

                  var30 = false;
                  break label63;
               }

               var30 = true;
               break label63;
            }

            var30 = true;
         }

         double var31;
         if (var30) {
            var31 = scala.math.package..MODULE$.log(BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(sumTermFreqs.toArray()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) + (double)numFeatures$1 * lambda$1);
         } else {
            String var32 = NaiveBayes$.MODULE$.Bernoulli();
            if (var32 == null) {
               if (var23 != null) {
                  throw new MatchError(var23);
               }
            } else if (!var32.equals(var23)) {
               throw new MatchError(var23);
            }

            var31 = scala.math.package..MODULE$.log(n + (double)2.0F * lambda$1);
         }

         double thetaLogDenom = var31;
         int j = 0;

         for(int offset = i$1.elem * numFeatures$1; j < numFeatures$1; ++j) {
            thetaArray$1[offset + j] = scala.math.package..MODULE$.log(sumTermFreqs.apply(j) + lambda$1) - thetaLogDenom;
         }

         ++i$1.elem;
         BoxedUnit var33 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$trainGaussianImpl$3(final Tuple4 x$7) {
      return BoxesRunTime.unboxToDouble(x$7._1());
   }

   // $FF: synthetic method
   public static final double $anonfun$trainGaussianImpl$4(final Tuple4 x$8) {
      return BoxesRunTime.unboxToDouble(x$8._2());
   }

   // $FF: synthetic method
   public static final void $anonfun$trainGaussianImpl$6(final DoubleRef globalSum$1, final int j$1, final DoubleRef globalSqrSum$1, final Tuple4 x0$2) {
      if (x0$2 != null) {
         double weightSum = BoxesRunTime.unboxToDouble(x0$2._2());
         Vector mean = (Vector)x0$2._3();
         Vector squareSum = (Vector)x0$2._4();
         globalSum$1.elem += mean.apply(j$1) * weightSum;
         globalSqrSum$1.elem += squareSum.apply(j$1);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$trainGaussianImpl$7(final double[] piArray$2, final IntRef i$2, final double logNumInstances$1, final int numFeatures$2, final double[] thetaArray$2, final double[] sigmaArray$1, final double epsilon$1, final Tuple4 x0$3) {
      if (x0$3 == null) {
         throw new MatchError(x0$3);
      } else {
         double weightSum = BoxesRunTime.unboxToDouble(x0$3._2());
         Vector mean = (Vector)x0$3._3();
         Vector squareSum = (Vector)x0$3._4();
         piArray$2[i$2.elem] = scala.math.package..MODULE$.log(weightSum) - logNumInstances$1;
         int j = 0;

         for(int offset = i$2.elem * numFeatures$2; j < numFeatures$2; ++j) {
            double m = mean.apply(j);
            thetaArray$2[offset + j] = m;
            sigmaArray$1[offset + j] = epsilon$1 + squareSum.apply(j) / weightSum - m * m;
         }

         ++i$2.elem;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public NaiveBayes(final String uid) {
      this.uid = uid;
      HasWeightCol.$init$(this);
      NaiveBayesParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public NaiveBayes() {
      this(Identifiable$.MODULE$.randomUID("nb"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
