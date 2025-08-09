package org.apache.spark.mllib.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.optimization.Optimizer;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e!B\u000f\u001f\u0003\u0003I\u0003\"B\"\u0001\t\u0003!\u0005b\u0002*\u0001\u0005\u0004%\tb\u0015\u0005\u0007M\u0002\u0001\u000b\u0011\u0002+\t\u000b\u001d\u0004a\u0011\u00015\t\u000fa\u0004\u0001\u0019!C\ts\"9!\u0010\u0001a\u0001\n#Y\bbBA\u0002\u0001\u0001\u0006Ka\u0019\u0005\t\u0003\u000b\u0001\u0001\u0019!C\ts\"I\u0011q\u0001\u0001A\u0002\u0013E\u0011\u0011\u0002\u0005\b\u0003\u001b\u0001\u0001\u0015)\u0003d\u0011%\ty\u0001\u0001a\u0001\n#\t\t\u0002C\u0005\u0002\u001a\u0001\u0001\r\u0011\"\u0005\u0002\u001c!A\u0011q\u0004\u0001!B\u0013\t\u0019\u0002C\u0005\u0002\"\u0001\u0001\r\u0011\"\u0001!s\"Q\u00111\u0005\u0001A\u0002\u0013\u0005\u0001%!\n\t\u000f\u0005%\u0002\u0001)Q\u0005G\"9\u00111\u0006\u0001\u0005\u0002\u0005E\u0001\"CA\u001a\u0001\u0001\u0007I\u0011CA\t\u0011%\t)\u0004\u0001a\u0001\n#\t9\u0004\u0003\u0005\u0002<\u0001\u0001\u000b\u0015BA\n\u0011!\ti\u0004\u0001C\u0001A\u0005}\u0002bBA#\u0001\u0019E\u0011q\t\u0005\u0007\u0003G\u0002A\u0011A=\t\u000f\u0005\u001d\u0004\u0001\"\u0001\u0002j!9\u0011q\u000e\u0001\u0005\u0002\u0005E\u0004bBA<\u0001\u0011E\u0011\u0011\u0010\u0005\b\u0003\u007f\u0002A\u0011AAA\u0011\u001d\ty\b\u0001C\u0001\u0003\u000f\u0013!dR3oKJ\fG.\u001b>fI2Kg.Z1s\u00032<wN]5uQ6T!a\b\u0011\u0002\u0015I,wM]3tg&|gN\u0003\u0002\"E\u0005)Q\u000e\u001c7jE*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005)J5\u0003\u0002\u0001,c]\u0002\"\u0001L\u0018\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012a!\u00118z%\u00164\u0007C\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b#\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u001c4\u0005\u001daunZ4j]\u001e\u0004\"\u0001\u000f!\u000f\u0005erdB\u0001\u001e>\u001b\u0005Y$B\u0001\u001f)\u0003\u0019a$o\\8u}%\ta&\u0003\u0002@[\u00059\u0001/Y2lC\u001e,\u0017BA!C\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tyT&\u0001\u0004=S:LGO\u0010\u000b\u0002\u000bB\u0019a\tA$\u000e\u0003y\u0001\"\u0001S%\r\u0001\u0011)!\n\u0001b\u0001\u0017\n\tQ*\u0005\u0002M\u001fB\u0011A&T\u0005\u0003\u001d6\u0012qAT8uQ&tw\r\u0005\u0002G!&\u0011\u0011K\b\u0002\u0017\u000f\u0016tWM]1mSj,G\rT5oK\u0006\u0014Xj\u001c3fY\u0006Qa/\u00197jI\u0006$xN]:\u0016\u0003Q\u00032\u0001O+X\u0013\t1&IA\u0002TKF\u0004B\u0001\f-[G&\u0011\u0011,\f\u0002\n\rVt7\r^5p]F\u00022a\u00170a\u001b\u0005a&BA/#\u0003\r\u0011H\rZ\u0005\u0003?r\u00131A\u0015#E!\t1\u0015-\u0003\u0002c=\taA*\u00192fY\u0016$\u0007k\\5oiB\u0011A\u0006Z\u0005\u0003K6\u0012qAQ8pY\u0016\fg.A\u0006wC2LG-\u0019;peN\u0004\u0013!C8qi&l\u0017N_3s+\u0005I\u0007C\u00016n\u001b\u0005Y'B\u00017!\u00031y\u0007\u000f^5nSj\fG/[8o\u0013\tq7NA\u0005PaRLW.\u001b>fe\"\u001aA\u0001\u001d<\u0011\u0005E$X\"\u0001:\u000b\u0005M\u0014\u0013AC1o]>$\u0018\r^5p]&\u0011QO\u001d\u0002\u0006'&t7-Z\u0011\u0002o\u0006)\u0001G\f\u001d/a\u0005a\u0011\r\u001a3J]R,'oY3qiV\t1-\u0001\tbI\u0012Le\u000e^3sG\u0016\u0004Ho\u0018\u0013fcR\u0011Ap \t\u0003YuL!A`\u0017\u0003\tUs\u0017\u000e\u001e\u0005\t\u0003\u00031\u0011\u0011!a\u0001G\u0006\u0019\u0001\u0010J\u0019\u0002\u001b\u0005$G-\u00138uKJ\u001cW\r\u001d;!\u000311\u0018\r\\5eCR,G)\u0019;b\u0003A1\u0018\r\\5eCR,G)\u0019;b?\u0012*\u0017\u000fF\u0002}\u0003\u0017A\u0001\"!\u0001\n\u0003\u0003\u0005\raY\u0001\u000em\u0006d\u0017\u000eZ1uK\u0012\u000bG/\u0019\u0011\u0002)9,Xn\u00144MS:,\u0017M\u001d)sK\u0012L7\r^8s+\t\t\u0019\u0002E\u0002-\u0003+I1!a\u0006.\u0005\rIe\u000e^\u0001\u0019]VlwJ\u001a'j]\u0016\f'\u000f\u0015:fI&\u001cGo\u001c:`I\u0015\fHc\u0001?\u0002\u001e!I\u0011\u0011\u0001\u0007\u0002\u0002\u0003\u0007\u00111C\u0001\u0016]VlwJ\u001a'j]\u0016\f'\u000f\u0015:fI&\u001cGo\u001c:!\u0003E)8/\u001a$fCR,(/Z*dC2LgnZ\u0001\u0016kN,g)Z1ukJ,7kY1mS:<w\fJ3r)\ra\u0018q\u0005\u0005\t\u0003\u0003y\u0011\u0011!a\u0001G\u0006\u0011Ro]3GK\u0006$XO]3TG\u0006d\u0017N\\4!\u000399W\r\u001e(v[\u001a+\u0017\r^;sKNDC!\u00059\u00020\u0005\u0012\u0011\u0011G\u0001\u0006c9\"d\u0006M\u0001\f]Vlg)Z1ukJ,7/A\bok64U-\u0019;ve\u0016\u001cx\fJ3r)\ra\u0018\u0011\b\u0005\n\u0003\u0003\u0019\u0012\u0011!a\u0001\u0003'\tAB\\;n\r\u0016\fG/\u001e:fg\u0002\n\u0011c]3u\r\u0016\fG/\u001e:f'\u000e\fG.\u001b8h)\u0011\t\t%a\u0011\u000e\u0003\u0001Aa!!\t\u0016\u0001\u0004\u0019\u0017aC2sK\u0006$X-T8eK2$RaRA%\u00033Bq!a\u0013\u0017\u0001\u0004\ti%A\u0004xK&<\u0007\u000e^:\u0011\t\u0005=\u0013QK\u0007\u0003\u0003#R1!a\u0015!\u0003\u0019a\u0017N\\1mO&!\u0011qKA)\u0005\u00191Vm\u0019;pe\"9\u00111\f\fA\u0002\u0005u\u0013!C5oi\u0016\u00148-\u001a9u!\ra\u0013qL\u0005\u0004\u0003Cj#A\u0002#pk\ndW-\u0001\bjg\u0006#G-\u00138uKJ\u001cW\r\u001d;)\t]\u0001\u0018qF\u0001\rg\u0016$\u0018J\u001c;fe\u000e,\u0007\u000f\u001e\u000b\u0005\u0003\u0003\nY\u0007C\u0003y1\u0001\u00071\rK\u0002\u0019aZ\fqb]3u-\u0006d\u0017\u000eZ1uK\u0012\u000bG/\u0019\u000b\u0005\u0003\u0003\n\u0019\b\u0003\u0004\u0002\u0006e\u0001\ra\u0019\u0015\u00043A4\u0018AF4f]\u0016\u0014\u0018\r^3J]&$\u0018.\u00197XK&<\u0007\u000e^:\u0015\t\u00055\u00131\u0010\u0005\u0007\u0003{R\u0002\u0019\u0001.\u0002\u000b%t\u0007/\u001e;\u0002\u0007I,h\u000eF\u0002H\u0003\u0007Ca!! \u001c\u0001\u0004Q\u0006fA\u000eqmR)q)!#\u0002\f\"1\u0011Q\u0010\u000fA\u0002iCq!!$\u001d\u0001\u0004\ti%\u0001\bj]&$\u0018.\u00197XK&<\u0007\u000e^:)\tq\u0001\u0018\u0011S\u0011\u0003\u0003'\u000bQ!\r\u00181]AB3\u0001\u00019w\u0001"
)
public abstract class GeneralizedLinearAlgorithm implements Logging, Serializable {
   private final Seq validators;
   private boolean addIntercept;
   private boolean validateData;
   private int numOfLinearPredictor;
   private boolean useFeatureScaling;
   private int numFeatures;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Seq validators() {
      return this.validators;
   }

   public abstract Optimizer optimizer();

   public boolean addIntercept() {
      return this.addIntercept;
   }

   public void addIntercept_$eq(final boolean x$1) {
      this.addIntercept = x$1;
   }

   public boolean validateData() {
      return this.validateData;
   }

   public void validateData_$eq(final boolean x$1) {
      this.validateData = x$1;
   }

   public int numOfLinearPredictor() {
      return this.numOfLinearPredictor;
   }

   public void numOfLinearPredictor_$eq(final int x$1) {
      this.numOfLinearPredictor = x$1;
   }

   public boolean useFeatureScaling() {
      return this.useFeatureScaling;
   }

   public void useFeatureScaling_$eq(final boolean x$1) {
      this.useFeatureScaling = x$1;
   }

   public int getNumFeatures() {
      return this.numFeatures();
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public void numFeatures_$eq(final int x$1) {
      this.numFeatures = x$1;
   }

   public GeneralizedLinearAlgorithm setFeatureScaling(final boolean useFeatureScaling) {
      this.useFeatureScaling_$eq(useFeatureScaling);
      return this;
   }

   public abstract GeneralizedLinearModel createModel(final Vector weights, final double intercept);

   public boolean isAddIntercept() {
      return this.addIntercept();
   }

   public GeneralizedLinearAlgorithm setIntercept(final boolean addIntercept) {
      this.addIntercept_$eq(addIntercept);
      return this;
   }

   public GeneralizedLinearAlgorithm setValidateData(final boolean validateData) {
      this.validateData_$eq(validateData);
      return this;
   }

   public Vector generateInitialWeights(final RDD input) {
      if (this.numFeatures() < 0) {
         this.numFeatures_$eq(BoxesRunTime.unboxToInt(input.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$generateInitialWeights$1(x$1)), .MODULE$.Int()).first()));
      }

      if (this.numOfLinearPredictor() == 1) {
         return Vectors$.MODULE$.zeros(this.numFeatures());
      } else {
         return this.addIntercept() ? Vectors$.MODULE$.zeros((this.numFeatures() + 1) * this.numOfLinearPredictor()) : Vectors$.MODULE$.zeros(this.numFeatures() * this.numOfLinearPredictor());
      }
   }

   public GeneralizedLinearModel run(final RDD input) {
      return this.run(input, this.generateInitialWeights(input));
   }

   public GeneralizedLinearModel run(final RDD input, final Vector initialWeights) {
      if (this.numFeatures() < 0) {
         this.numFeatures_$eq(BoxesRunTime.unboxToInt(input.map((x$2x) -> BoxesRunTime.boxToInteger($anonfun$run$1(x$2x)), .MODULE$.Int()).first()));
      }

      label112: {
         StorageLevel var10000 = input.getStorageLevel();
         StorageLevel var3 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
         if (var10000 == null) {
            if (var3 != null) {
               break label112;
            }
         } else if (!var10000.equals(var3)) {
            break label112;
         }

         this.logWarning((Function0)(() -> "The input data is not directly cached, which may hurt performance if its parent RDDs are also uncached."));
      }

      if (this.validateData() && !this.validators().forall((func) -> BoxesRunTime.boxToBoolean($anonfun$run$3(input, func)))) {
         throw new SparkException("Input validation failed.");
      } else {
         StandardScalerModel var21;
         if (this.useFeatureScaling()) {
            boolean x$1 = true;
            boolean x$2 = false;
            var21 = (new StandardScaler(false, true)).fit(input.map((x$3) -> x$3.features(), .MODULE$.apply(Vector.class)));
         } else {
            var21 = null;
         }

         StandardScalerModel scaler = var21;
         RDD data = this.addIntercept() ? (this.useFeatureScaling() ? input.map((lp) -> new Tuple2(BoxesRunTime.boxToDouble(lp.label()), MLUtils$.MODULE$.appendBias(scaler.transform(lp.features()))), .MODULE$.apply(Tuple2.class)).cache() : input.map((lp) -> new Tuple2(BoxesRunTime.boxToDouble(lp.label()), MLUtils$.MODULE$.appendBias(lp.features())), .MODULE$.apply(Tuple2.class)).cache()) : (this.useFeatureScaling() ? input.map((lp) -> new Tuple2(BoxesRunTime.boxToDouble(lp.label()), scaler.transform(lp.features())), .MODULE$.apply(Tuple2.class)).cache() : input.map((lp) -> new Tuple2(BoxesRunTime.boxToDouble(lp.label()), lp.features()), .MODULE$.apply(Tuple2.class)));
         Vector initialWeightsWithIntercept = this.addIntercept() && this.numOfLinearPredictor() == 1 ? MLUtils$.MODULE$.appendBias(initialWeights) : initialWeights;
         Vector weightsWithIntercept = this.optimizer().optimize(data, initialWeightsWithIntercept);
         double intercept = this.addIntercept() && this.numOfLinearPredictor() == 1 ? weightsWithIntercept.apply(weightsWithIntercept.size() - 1) : (double)0.0F;
         Vector weights = this.addIntercept() && this.numOfLinearPredictor() == 1 ? Vectors$.MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(weightsWithIntercept.toArray()), 0, weightsWithIntercept.size() - 1)) : weightsWithIntercept;
         if (this.useFeatureScaling()) {
            if (this.numOfLinearPredictor() == 1) {
               weights = scaler.transform(weights);
            } else {
               int i = 0;
               int n = weights.size() / this.numOfLinearPredictor();

               double[] weightsArray;
               for(weightsArray = weights.toArray(); i < this.numOfLinearPredictor(); ++i) {
                  int start = i * n;
                  int end = (i + 1) * n - (this.addIntercept() ? 1 : 0);
                  double[] partialWeightsArray = scaler.transform(Vectors$.MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(weightsArray), start, end))).toArray();
                  System.arraycopy(partialWeightsArray, 0, weightsArray, start, partialWeightsArray.length);
               }

               weights = Vectors$.MODULE$.dense(weightsArray);
            }
         }

         label79: {
            StorageLevel var22 = input.getStorageLevel();
            StorageLevel var19 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var22 == null) {
               if (var19 != null) {
                  break label79;
               }
            } else if (!var22.equals(var19)) {
               break label79;
            }

            this.logWarning((Function0)(() -> "The input data was not directly cached, which may hurt performance if its parent RDDs are also uncached."));
         }

         label138: {
            StorageLevel var23 = data.getStorageLevel();
            StorageLevel var20 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var23 == null) {
               if (var20 != null) {
                  break label138;
               }
            } else if (!var23.equals(var20)) {
               break label138;
            }

            BoxedUnit var24 = BoxedUnit.UNIT;
            return this.createModel(weights, intercept);
         }

         data.unpersist(data.unpersist$default$1());
         return this.createModel(weights, intercept);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$generateInitialWeights$1(final LabeledPoint x$1) {
      return x$1.features().size();
   }

   // $FF: synthetic method
   public static final int $anonfun$run$1(final LabeledPoint x$2) {
      return x$2.features().size();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$3(final RDD input$1, final Function1 func) {
      return BoxesRunTime.unboxToBoolean(func.apply(input$1));
   }

   public GeneralizedLinearAlgorithm() {
      Logging.$init$(this);
      this.validators = scala.collection.immutable.Nil..MODULE$;
      this.addIntercept = false;
      this.validateData = true;
      this.numOfLinearPredictor = 1;
      this.useFeatureScaling = false;
      this.numFeatures = -1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
