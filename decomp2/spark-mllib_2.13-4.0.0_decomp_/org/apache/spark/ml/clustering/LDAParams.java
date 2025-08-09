package org.apache.spark.ml.clustering;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.clustering.EMLDAOptimizer;
import org.apache.spark.mllib.clustering.LDAOptimizer;
import org.apache.spark.mllib.clustering.OnlineLDAOptimizer;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra\u0001\u0003\u0011\"!\u0003\r\t!I\u0016\t\u000b\u001d\u0003A\u0011A%\t\u000f5\u0003!\u0019!C\u0003\u001d\")1\f\u0001C\u00019\"9\u0011\r\u0001b\u0001\n\u000b\u0011\u0007\"B4\u0001\t\u0003A\u0007\"\u00029\u0001\t#\t\bb\u0002=\u0001\u0005\u0004%)!\u001f\u0005\u0006}\u0002!\ta \u0005\u0007\u0003\u0007\u0001A\u0011C@\t\u0013\u0005\u0015\u0001A1A\u0005\u0006\u0005\u001d\u0001\"CA\u0012\u0001\t\u0007IQAA\u0013\u0011\u001d\ty\u0003\u0001C\u0001\u0003cA\u0011\"!\u000e\u0001\u0005\u0004%)!!\n\t\u000f\u0005e\u0002\u0001\"\u0001\u00022!A\u0011Q\b\u0001C\u0002\u0013\u0015\u0011\u0010\u0003\u0004\u0002B\u0001!\ta \u0005\t\u0003\u000b\u0002!\u0019!C\u0003s\"1\u0011\u0011\n\u0001\u0005\u0002}D\u0001\"!\u0014\u0001\u0005\u0004%)!\u001f\u0005\u0007\u0003#\u0002A\u0011A@\t\u0013\u0005U\u0003A1A\u0005\u0006\u0005]\u0003bBA1\u0001\u0011\u0005\u00111\r\u0005\n\u0003[\u0002!\u0019!C\u0003\u0003/Bq!!\u001e\u0001\t\u0003\t\u0019\u0007C\u0004\u0002z\u0001!\t\"a\u001f\t\u0011\u0005E\u0005\u0001\"\u0001\"\u0003';q!a)\"\u0011\u0013\t)K\u0002\u0004!C!%\u0011\u0011\u0016\u0005\b\u0003wcB\u0011AA_\u0011\u001d\ty\f\bC\u0001\u0003\u0003D\u0011\"a=\u001d\u0003\u0003%I!!>\u0003\u00131#\u0015\tU1sC6\u001c(B\u0001\u0012$\u0003)\u0019G.^:uKJLgn\u001a\u0006\u0003I\u0015\n!!\u001c7\u000b\u0005\u0019:\u0013!B:qCJ\\'B\u0001\u0015*\u0003\u0019\t\u0007/Y2iK*\t!&A\u0002pe\u001e\u001cr\u0001\u0001\u00173qy\nE\t\u0005\u0002.a5\taFC\u00010\u0003\u0015\u00198-\u00197b\u0013\t\tdF\u0001\u0004B]f\u0014VM\u001a\t\u0003gYj\u0011\u0001\u000e\u0006\u0003k\r\nQ\u0001]1sC6L!a\u000e\u001b\u0003\rA\u000b'/Y7t!\tID(D\u0001;\u0015\tYD'\u0001\u0004tQ\u0006\u0014X\rZ\u0005\u0003{i\u0012a\u0002S1t\r\u0016\fG/\u001e:fg\u000e{G\u000e\u0005\u0002:\u007f%\u0011\u0001I\u000f\u0002\u000b\u0011\u0006\u001cX*\u0019=Ji\u0016\u0014\bCA\u001dC\u0013\t\u0019%HA\u0004ICN\u001cV-\u001a3\u0011\u0005e*\u0015B\u0001$;\u0005UA\u0015m]\"iK\u000e\\\u0007o\\5oi&sG/\u001a:wC2\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002\u0015B\u0011QfS\u0005\u0003\u0019:\u0012A!\u00168ji\u0006\t1.F\u0001P!\t\u0019\u0004+\u0003\u0002Ri\tA\u0011J\u001c;QCJ\fW\u000eK\u0002\u0003'f\u0003\"\u0001V,\u000e\u0003US!AV\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Y+\n)1+\u001b8dK\u0006\n!,A\u00032]Yr\u0003'\u0001\u0003hKR\\U#A/\u0011\u00055r\u0016BA0/\u0005\rIe\u000e\u001e\u0015\u0004\u0007MK\u0016\u0001\u00053pG\u000e{gnY3oiJ\fG/[8o+\u0005\u0019\u0007CA\u001ae\u0013\t)GG\u0001\tE_V\u0014G.Z!se\u0006L\b+\u0019:b[\"\u001aAaU-\u0002'\u001d,G\u000fR8d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8\u0016\u0003%\u00042!\f6m\u0013\tYgFA\u0003BeJ\f\u0017\u0010\u0005\u0002.[&\u0011aN\f\u0002\u0007\t>,(\r\\3)\u0007\u0015\u0019\u0016,\u0001\fhKR|E\u000e\u001a#pG\u000e{gnY3oiJ\fG/[8o+\u0005\u0011\bCA:w\u001b\u0005!(BA;$\u0003\u0019a\u0017N\\1mO&\u0011q\u000f\u001e\u0002\u0007-\u0016\u001cGo\u001c:\u0002%Q|\u0007/[2D_:\u001cWM\u001c;sCRLwN\\\u000b\u0002uB\u00111g_\u0005\u0003yR\u00121\u0002R8vE2,\u0007+\u0019:b[\"\u001aqaU-\u0002+\u001d,G\u000fV8qS\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]V\tA\u000eK\u0002\t'f\u000b\u0001dZ3u\u001f2$Gk\u001c9jG\u000e{gnY3oiJ\fG/[8o\u0003M\u0019X\u000f\u001d9peR,Gm\u00149uS6L'0\u001a:t+\t\tI\u0001\u0005\u0003.U\u0006-\u0001\u0003BA\u0007\u00037qA!a\u0004\u0002\u0018A\u0019\u0011\u0011\u0003\u0018\u000e\u0005\u0005M!bAA\u000b\u0011\u00061AH]8pizJ1!!\u0007/\u0003\u0019\u0001&/\u001a3fM&!\u0011QDA\u0010\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011\u0004\u0018)\u0007)\u0019\u0016,A\u0005paRLW.\u001b>feV\u0011\u0011q\u0005\t\u0006g\u0005%\u00121B\u0005\u0004\u0003W!$!\u0002)be\u0006l\u0007fA\u0006T3\u0006aq-\u001a;PaRLW.\u001b>feV\u0011\u00111\u0002\u0015\u0004\u0019MK\u0016\u0001\u0006;pa&\u001cG)[:ue&\u0014W\u000f^5p]\u000e{G\u000eK\u0002\u000e'f\u000bqcZ3u)>\u0004\u0018n\u0019#jgR\u0014\u0018NY;uS>t7i\u001c7)\u00079\u0019\u0016,\u0001\bmK\u0006\u0014h.\u001b8h\u001f\u001a47/\u001a;)\u0007=\u0019\u0016,A\thKRdU-\u0019:oS:<wJ\u001a4tKRD3\u0001E*Z\u00035aW-\u0019:oS:<G)Z2bs\"\u001a\u0011cU-\u0002!\u001d,G\u000fT3be:Lgn\u001a#fG\u0006L\bf\u0001\nT3\u0006y1/\u001e2tC6\u0004H.\u001b8h%\u0006$X\rK\u0002\u0014'f\u000b!cZ3u'V\u00147/Y7qY&twMU1uK\"\u001aAcU-\u00021=\u0004H/[7ju\u0016$unY\"p]\u000e,g\u000e\u001e:bi&|g.\u0006\u0002\u0002ZA\u00191'a\u0017\n\u0007\u0005uCG\u0001\u0007C_>dW-\u00198QCJ\fW\u000eK\u0002\u0016'f\u000b1dZ3u\u001fB$\u0018.\\5{K\u0012{7mQ8oG\u0016tGO]1uS>tWCAA3!\ri\u0013qM\u0005\u0004\u0003Sr#a\u0002\"p_2,\u0017M\u001c\u0015\u0004-MK\u0016AE6fKBd\u0015m\u001d;DQ\u0016\u001c7\u000e]8j]RDCaF*\u0002r\u0005\u0012\u00111O\u0001\u0006e9\u0002d\u0006M\u0001\u0016O\u0016$8*Z3q\u0019\u0006\u001cHo\u00115fG.\u0004x.\u001b8uQ\u0011A2+!\u001d\u00025Y\fG.\u001b3bi\u0016\fe\u000e\u001a+sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005u\u0014Q\u0012\t\u0005\u0003\u007f\nI)\u0004\u0002\u0002\u0002*!\u00111QAC\u0003\u0015!\u0018\u0010]3t\u0015\r\t9)J\u0001\u0004gFd\u0017\u0002BAF\u0003\u0003\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\ty)\u0007a\u0001\u0003{\naa]2iK6\f\u0017aD4fi>cGm\u00149uS6L'0\u001a:\u0016\u0005\u0005U\u0005\u0003BAL\u0003?k!!!'\u000b\u0007\t\nYJC\u0002\u0002\u001e\u0016\nQ!\u001c7mS\nLA!!)\u0002\u001a\naA\nR!PaRLW.\u001b>fe\u0006IA\nR!QCJ\fWn\u001d\t\u0004\u0003OcR\"A\u0011\u0014\tqa\u00131\u0016\t\u0005\u0003[\u000b9,\u0004\u0002\u00020*!\u0011\u0011WAZ\u0003\tIwN\u0003\u0002\u00026\u0006!!.\u0019<b\u0013\u0011\tI,a,\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\t\t)+A\bhKR\fe\u000eZ*fiB\u000b'/Y7t)\u0015Q\u00151YAe\u0011\u001d\t)M\ba\u0001\u0003\u000f\fQ!\\8eK2\u00042!a*\u0001\u0011\u001d\tYM\ba\u0001\u0003\u001b\f\u0001\"\\3uC\u0012\fG/\u0019\t\u0005\u0003\u001f\fiO\u0004\u0003\u0002R\u0006\u001dh\u0002BAj\u0003GtA!!6\u0002b:!\u0011q[Ap\u001d\u0011\tI.!8\u000f\t\u0005E\u00111\\\u0005\u0002U%\u0011\u0001&K\u0005\u0003M\u001dJ!\u0001J\u0013\n\u0007\u0005\u00158%\u0001\u0003vi&d\u0017\u0002BAu\u0003W\f1\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eKJT1!!:$\u0013\u0011\ty/!=\u0003\u00115+G/\u00193bi\u0006TA!!;\u0002l\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u001f\t\u0005\u0003s\fy0\u0004\u0002\u0002|*!\u0011Q`AZ\u0003\u0011a\u0017M\\4\n\t\t\u0005\u00111 \u0002\u0007\u001f\nTWm\u0019;"
)
public interface LDAParams extends HasFeaturesCol, HasMaxIter, HasSeed, HasCheckpointInterval {
   static void getAndSetParams(final LDAParams model, final DefaultParamsReader.Metadata metadata) {
      LDAParams$.MODULE$.getAndSetParams(model, metadata);
   }

   void org$apache$spark$ml$clustering$LDAParams$_setter_$k_$eq(final IntParam x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$docConcentration_$eq(final DoubleArrayParam x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$topicConcentration_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$supportedOptimizers_$eq(final String[] x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$optimizer_$eq(final Param x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$topicDistributionCol_$eq(final Param x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$learningOffset_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$learningDecay_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$subsamplingRate_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$optimizeDocConcentration_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$clustering$LDAParams$_setter_$keepLastCheckpoint_$eq(final BooleanParam x$1);

   IntParam k();

   // $FF: synthetic method
   static int getK$(final LDAParams $this) {
      return $this.getK();
   }

   default int getK() {
      return BoxesRunTime.unboxToInt(this.$(this.k()));
   }

   DoubleArrayParam docConcentration();

   // $FF: synthetic method
   static double[] getDocConcentration$(final LDAParams $this) {
      return $this.getDocConcentration();
   }

   default double[] getDocConcentration() {
      return (double[])this.$(this.docConcentration());
   }

   // $FF: synthetic method
   static Vector getOldDocConcentration$(final LDAParams $this) {
      return $this.getOldDocConcentration();
   }

   default Vector getOldDocConcentration() {
      return this.isSet(this.docConcentration()) ? .MODULE$.dense(this.getDocConcentration()) : .MODULE$.dense((double)-1.0F, scala.collection.immutable.Nil..MODULE$);
   }

   DoubleParam topicConcentration();

   // $FF: synthetic method
   static double getTopicConcentration$(final LDAParams $this) {
      return $this.getTopicConcentration();
   }

   default double getTopicConcentration() {
      return BoxesRunTime.unboxToDouble(this.$(this.topicConcentration()));
   }

   // $FF: synthetic method
   static double getOldTopicConcentration$(final LDAParams $this) {
      return $this.getOldTopicConcentration();
   }

   default double getOldTopicConcentration() {
      return this.isSet(this.topicConcentration()) ? this.getTopicConcentration() : (double)-1.0F;
   }

   String[] supportedOptimizers();

   Param optimizer();

   // $FF: synthetic method
   static String getOptimizer$(final LDAParams $this) {
      return $this.getOptimizer();
   }

   default String getOptimizer() {
      return (String)this.$(this.optimizer());
   }

   Param topicDistributionCol();

   // $FF: synthetic method
   static String getTopicDistributionCol$(final LDAParams $this) {
      return $this.getTopicDistributionCol();
   }

   default String getTopicDistributionCol() {
      return (String)this.$(this.topicDistributionCol());
   }

   DoubleParam learningOffset();

   // $FF: synthetic method
   static double getLearningOffset$(final LDAParams $this) {
      return $this.getLearningOffset();
   }

   default double getLearningOffset() {
      return BoxesRunTime.unboxToDouble(this.$(this.learningOffset()));
   }

   DoubleParam learningDecay();

   // $FF: synthetic method
   static double getLearningDecay$(final LDAParams $this) {
      return $this.getLearningDecay();
   }

   default double getLearningDecay() {
      return BoxesRunTime.unboxToDouble(this.$(this.learningDecay()));
   }

   DoubleParam subsamplingRate();

   // $FF: synthetic method
   static double getSubsamplingRate$(final LDAParams $this) {
      return $this.getSubsamplingRate();
   }

   default double getSubsamplingRate() {
      return BoxesRunTime.unboxToDouble(this.$(this.subsamplingRate()));
   }

   BooleanParam optimizeDocConcentration();

   // $FF: synthetic method
   static boolean getOptimizeDocConcentration$(final LDAParams $this) {
      return $this.getOptimizeDocConcentration();
   }

   default boolean getOptimizeDocConcentration() {
      return BoxesRunTime.unboxToBoolean(this.$(this.optimizeDocConcentration()));
   }

   BooleanParam keepLastCheckpoint();

   // $FF: synthetic method
   static boolean getKeepLastCheckpoint$(final LDAParams $this) {
      return $this.getKeepLastCheckpoint();
   }

   default boolean getKeepLastCheckpoint() {
      return BoxesRunTime.unboxToBoolean(this.$(this.keepLastCheckpoint()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final LDAParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      if (this.isSet(this.docConcentration())) {
         if (this.getDocConcentration().length != 1) {
            scala.Predef..MODULE$.require(this.getDocConcentration().length == this.getK(), () -> {
               int var10000 = this.getDocConcentration().length;
               return "LDA docConcentration was of length " + var10000 + ", but k = " + this.getK() + ".  docConcentration must be an array of length either 1 (scalar) or k (num topics).";
            });
         }

         String var2 = this.getOptimizer().toLowerCase(Locale.ROOT);
         switch (var2 == null ? 0 : var2.hashCode()) {
            case -1012222381:
               if (!"online".equals(var2)) {
                  throw new MatchError(var2);
               }

               scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(this.getDocConcentration()), (JFunction1.mcZD.sp)(x$2) -> x$2 >= (double)0), () -> {
                  ArraySeq.ofDouble var10000 = scala.Predef..MODULE$.wrapDoubleArray(this.getDocConcentration());
                  return "For Online LDA optimizer, docConcentration values must be >= 0.  Found values: " + var10000.mkString(",");
               });
               break;
            case 3240:
               if ("em".equals(var2)) {
                  scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(this.getDocConcentration()), (JFunction1.mcZD.sp)(x$3) -> x$3 >= (double)0), () -> {
                     ArraySeq.ofDouble var10000 = scala.Predef..MODULE$.wrapDoubleArray(this.getDocConcentration());
                     return "For EM optimizer, docConcentration values must be >= 1.  Found values: " + var10000.mkString(",");
                  });
                  break;
               }

               throw new MatchError(var2);
            default:
               throw new MatchError(var2);
         }
      }

      if (this.isSet(this.topicConcentration())) {
         String var3 = this.getOptimizer().toLowerCase(Locale.ROOT);
         switch (var3 == null ? 0 : var3.hashCode()) {
            case -1012222381:
               if (!"online".equals(var3)) {
                  throw new MatchError(var3);
               }

               scala.Predef..MODULE$.require(this.getTopicConcentration() >= (double)0, () -> "For Online LDA optimizer, topicConcentration must be >= 0.  Found value: " + this.getTopicConcentration());
               break;
            case 3240:
               if ("em".equals(var3)) {
                  scala.Predef..MODULE$.require(this.getTopicConcentration() >= (double)0, () -> "For EM optimizer, topicConcentration must be >= 1.  Found value: " + this.getTopicConcentration());
                  break;
               }

               throw new MatchError(var3);
            default:
               throw new MatchError(var3);
         }
      }

      SchemaUtils$.MODULE$.validateVectorCompatibleColumn(schema, this.getFeaturesCol());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.topicDistributionCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   // $FF: synthetic method
   static LDAOptimizer getOldOptimizer$(final LDAParams $this) {
      return $this.getOldOptimizer();
   }

   default LDAOptimizer getOldOptimizer() {
      String var2 = this.getOptimizer().toLowerCase(Locale.ROOT);
      switch (var2 == null ? 0 : var2.hashCode()) {
         case -1012222381:
            if ("online".equals(var2)) {
               return (new OnlineLDAOptimizer()).setTau0(BoxesRunTime.unboxToDouble(this.$(this.learningOffset()))).setKappa(BoxesRunTime.unboxToDouble(this.$(this.learningDecay()))).setMiniBatchFraction(BoxesRunTime.unboxToDouble(this.$(this.subsamplingRate()))).setOptimizeDocConcentration(BoxesRunTime.unboxToBoolean(this.$(this.optimizeDocConcentration())));
            }
            break;
         case 3240:
            if ("em".equals(var2)) {
               return (new EMLDAOptimizer()).setKeepLastCheckpoint(BoxesRunTime.unboxToBoolean(this.$(this.keepLastCheckpoint())));
            }
      }

      throw new MatchError(var2);
   }

   // $FF: synthetic method
   static boolean $anonfun$docConcentration$1(final double[] alpha) {
      return scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(alpha), (JFunction1.mcZD.sp)(x$1) -> x$1 >= (double)0.0F);
   }

   // $FF: synthetic method
   static boolean $anonfun$optimizer$1(final LDAParams $this, final String value) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])$this.supportedOptimizers()), value.toLowerCase(Locale.ROOT));
   }

   static void $init$(final LDAParams $this) {
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$k_$eq(new IntParam($this, "k", "The number of topics (clusters) to infer. Must be > 1.", ParamValidators$.MODULE$.gt((double)1.0F)));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$docConcentration_$eq(new DoubleArrayParam($this, "docConcentration", "Concentration parameter (commonly named \"alpha\") for the prior placed on documents' distributions over topics (\"theta\").", (alpha) -> BoxesRunTime.boxToBoolean($anonfun$docConcentration$1(alpha))));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$topicConcentration_$eq(new DoubleParam($this, "topicConcentration", "Concentration parameter (commonly named \"beta\" or \"eta\") for the prior placed on topic' distributions over terms.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$supportedOptimizers_$eq((String[])((Object[])(new String[]{"online", "em"})));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$optimizer_$eq(new Param($this, "optimizer", "Optimizer or inference algorithm used to estimate the LDA model. Supported: " + scala.Predef..MODULE$.wrapRefArray((Object[])$this.supportedOptimizers()).mkString(", "), (value) -> BoxesRunTime.boxToBoolean($anonfun$optimizer$1($this, value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$topicDistributionCol_$eq(new Param($this, "topicDistributionCol", "Output column with estimates of the topic mixture distribution for each document (often called \"theta\" in the literature).  Returns a vector of zeros for an empty document.", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$learningOffset_$eq(new DoubleParam($this, "learningOffset", "(For online optimizer) A (positive) learning parameter that downweights early iterations. Larger values make early iterations count less.", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$learningDecay_$eq(new DoubleParam($this, "learningDecay", "(For online optimizer) Learning rate, set as an exponential decay rate. This should be between (0.5, 1.0] to guarantee asymptotic convergence.", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$subsamplingRate_$eq(new DoubleParam($this, "subsamplingRate", "(For online optimizer) Fraction of the corpus to be sampled and used in each iteration of mini-batch gradient descent, in range (0, 1].", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F, false, true)));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$optimizeDocConcentration_$eq(new BooleanParam($this, "optimizeDocConcentration", "(For online optimizer only, currently) Indicates whether the docConcentration (Dirichlet parameter for document-topic distribution) will be optimized during training."));
      $this.org$apache$spark$ml$clustering$LDAParams$_setter_$keepLastCheckpoint_$eq(new BooleanParam($this, "keepLastCheckpoint", "(For EM optimizer) If using checkpointing, this indicates whether to keep the last checkpoint. If false, then the checkpoint will be deleted. Deleting the checkpoint can cause failures if a data partition is lost, so set this bit with care."));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(20)), $this.k().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.optimizer().$minus$greater("online"), $this.checkpointInterval().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.learningOffset().$minus$greater(BoxesRunTime.boxToDouble((double)1024.0F)), $this.learningDecay().$minus$greater(BoxesRunTime.boxToDouble(0.51)), $this.subsamplingRate().$minus$greater(BoxesRunTime.boxToDouble(0.05)), $this.optimizeDocConcentration().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.keepLastCheckpoint().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.topicDistributionCol().$minus$greater("topicDistribution")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
