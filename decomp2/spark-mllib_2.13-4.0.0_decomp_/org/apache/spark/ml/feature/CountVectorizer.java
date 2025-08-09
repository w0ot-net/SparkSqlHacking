package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
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
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.collection.OpenHashMap;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf\u0001\u0002\u000b\u0016\u0001\u0001B\u0001B\r\u0001\u0003\u0006\u0004%\te\r\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005i!)A\n\u0001C\u0001\u001b\")A\n\u0001C\u0001%\")A\u000b\u0001C\u0001+\")!\f\u0001C\u00017\")a\f\u0001C\u0001?\")a\r\u0001C\u0001O\")Q\u000e\u0001C\u0001]\")1\u000f\u0001C\u0001i\")q\u000f\u0001C\u0001q\"9\u0011\u0011\u0001\u0001\u0005B\u0005\r\u0001bBA\u0018\u0001\u0011\u0005\u0013\u0011\u0007\u0005\b\u0003\u000b\u0002A\u0011IA$\u000f\u001d\ti&\u0006E\u0001\u0003?2a\u0001F\u000b\t\u0002\u0005\u0005\u0004B\u0002'\u0011\t\u0003\ty\bC\u0004\u0002\u0002B!\t%a!\t\u0013\u0005=\u0005#!A\u0005\n\u0005E%aD\"pk:$h+Z2u_JL'0\u001a:\u000b\u0005Y9\u0012a\u00024fCR,(/\u001a\u0006\u00031e\t!!\u001c7\u000b\u0005iY\u0012!B:qCJ\\'B\u0001\u000f\u001e\u0003\u0019\t\u0007/Y2iK*\ta$A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001C%b\u0003c\u0001\u0012$K5\tq#\u0003\u0002%/\tIQi\u001d;j[\u0006$xN\u001d\t\u0003M\u001dj\u0011!F\u0005\u0003QU\u0011AcQ8v]R4Vm\u0019;pe&TXM]'pI\u0016d\u0007C\u0001\u0014+\u0013\tYSCA\u000bD_VtGOV3di>\u0014\u0018N_3s!\u0006\u0014\u0018-\\:\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0005=:\u0012\u0001B;uS2L!!\r\u0018\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003Q\u0002\"!\u000e \u000f\u0005Yb\u0004CA\u001c;\u001b\u0005A$BA\u001d \u0003\u0019a$o\\8u})\t1(A\u0003tG\u0006d\u0017-\u0003\u0002>u\u00051\u0001K]3eK\u001aL!a\u0010!\u0003\rM#(/\u001b8h\u0015\ti$\bK\u0002\u0002\u0005\"\u0003\"a\u0011$\u000e\u0003\u0011S!!R\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002H\t\n)1+\u001b8dK\u0006\n\u0011*A\u00032]Ur\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002C\u0011\u00061A(\u001b8jiz\"\"AT(\u0011\u0005\u0019\u0002\u0001\"\u0002\u001a\u0004\u0001\u0004!\u0004fA(C\u0011\"\u001a1A\u0011%\u0015\u00039C3\u0001\u0002\"I\u0003-\u0019X\r^%oaV$8i\u001c7\u0015\u0005Y;V\"\u0001\u0001\t\u000ba+\u0001\u0019\u0001\u001b\u0002\u000bY\fG.^3)\u0007\u0015\u0011\u0005*\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000e\u0006\u0002W9\")\u0001L\u0002a\u0001i!\u001aaA\u0011%\u0002\u0019M,GOV8dC\n\u001c\u0016N_3\u0015\u0005Y\u0003\u0007\"\u0002-\b\u0001\u0004\t\u0007C\u00012d\u001b\u0005Q\u0014B\u00013;\u0005\rIe\u000e\u001e\u0015\u0004\u000f\tC\u0015\u0001C:fi6Kg\u000e\u0012$\u0015\u0005YC\u0007\"\u0002-\t\u0001\u0004I\u0007C\u00012k\u0013\tY'H\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0011\tC\u0015\u0001C:fi6\u000b\u0007\u0010\u0012$\u0015\u0005Y{\u0007\"\u0002-\n\u0001\u0004I\u0007fA\u0005Cc\u0006\n!/A\u00033]Qr\u0003'\u0001\u0005tKRl\u0015N\u001c+G)\t1V\u000fC\u0003Y\u0015\u0001\u0007\u0011\u000eK\u0002\u000b\u0005\"\u000b\u0011b]3u\u0005&t\u0017M]=\u0015\u0005YK\b\"\u0002-\f\u0001\u0004Q\bC\u00012|\u0013\ta(HA\u0004C_>dW-\u00198)\u0007-\u0011e0I\u0001\u0000\u0003\u0015\u0011d\u0006\r\u00181\u0003\r1\u0017\u000e\u001e\u000b\u0004K\u0005\u0015\u0001bBA\u0004\u0019\u0001\u0007\u0011\u0011B\u0001\bI\u0006$\u0018m]3ua\u0011\tY!a\u0007\u0011\r\u00055\u00111CA\f\u001b\t\tyAC\u0002\u0002\u0012e\t1a]9m\u0013\u0011\t)\"a\u0004\u0003\u000f\u0011\u000bG/Y:fiB!\u0011\u0011DA\u000e\u0019\u0001!A\"!\b\u0002\u0006\u0005\u0005\t\u0011!B\u0001\u0003?\u00111a\u0018\u00132#\u0011\t\t#a\n\u0011\u0007\t\f\u0019#C\u0002\u0002&i\u0012qAT8uQ&tw\rE\u0002c\u0003SI1!a\u000b;\u0005\r\te.\u001f\u0015\u0004\u0019\ts\u0018a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005M\u0012q\b\t\u0005\u0003k\tY$\u0004\u0002\u00028)!\u0011\u0011HA\b\u0003\u0015!\u0018\u0010]3t\u0013\u0011\ti$a\u000e\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002B5\u0001\r!a\r\u0002\rM\u001c\u0007.Z7bQ\ri!\tS\u0001\u0005G>\u0004\u0018\u0010F\u0002O\u0003\u0013Bq!a\u0013\u000f\u0001\u0004\ti%A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002P\u0005USBAA)\u0015\r\t\u0019fF\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003/\n\tF\u0001\u0005QCJ\fW.T1qQ\rq!\t\u0013\u0015\u0004\u0001\tC\u0015aD\"pk:$h+Z2u_JL'0\u001a:\u0011\u0005\u0019\u00022c\u0002\t\u0002d\u0005%\u0014q\u000e\t\u0004E\u0006\u0015\u0014bAA4u\t1\u0011I\\=SK\u001a\u0004B!LA6\u001d&\u0019\u0011Q\u000e\u0018\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011\u0011OA>\u001b\t\t\u0019H\u0003\u0003\u0002v\u0005]\u0014AA5p\u0015\t\tI(\u0001\u0003kCZ\f\u0017\u0002BA?\u0003g\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\"!a\u0018\u0002\t1|\u0017\r\u001a\u000b\u0004\u001d\u0006\u0015\u0005BBAD%\u0001\u0007A'\u0001\u0003qCRD\u0007\u0006\u0002\nC\u0003\u0017\u000b#!!$\u0002\u000bErcG\f\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005M\u0005\u0003BAK\u00037k!!a&\u000b\t\u0005e\u0015qO\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001e\u0006]%AB(cU\u0016\u001cG\u000f\u000b\u0003\u0011\u0005\u0006-\u0005\u0006B\bC\u0003\u0017\u0003"
)
public class CountVectorizer extends Estimator implements CountVectorizerParams, DefaultParamsWritable {
   private final String uid;
   private IntParam vocabSize;
   private DoubleParam minDF;
   private DoubleParam maxDF;
   private DoubleParam minTF;
   private BooleanParam binary;
   private Param outputCol;
   private Param inputCol;

   public static CountVectorizer load(final String path) {
      return CountVectorizer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return CountVectorizer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getVocabSize() {
      return CountVectorizerParams.getVocabSize$(this);
   }

   public double getMinDF() {
      return CountVectorizerParams.getMinDF$(this);
   }

   public double getMaxDF() {
      return CountVectorizerParams.getMaxDF$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return CountVectorizerParams.validateAndTransformSchema$(this, schema);
   }

   public double getMinTF() {
      return CountVectorizerParams.getMinTF$(this);
   }

   public boolean getBinary() {
      return CountVectorizerParams.getBinary$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public IntParam vocabSize() {
      return this.vocabSize;
   }

   public DoubleParam minDF() {
      return this.minDF;
   }

   public DoubleParam maxDF() {
      return this.maxDF;
   }

   public DoubleParam minTF() {
      return this.minTF;
   }

   public BooleanParam binary() {
      return this.binary;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$vocabSize_$eq(final IntParam x$1) {
      this.vocabSize = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minDF_$eq(final DoubleParam x$1) {
      this.minDF = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$maxDF_$eq(final DoubleParam x$1) {
      this.maxDF = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minTF_$eq(final DoubleParam x$1) {
      this.minTF = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$binary_$eq(final BooleanParam x$1) {
      this.binary = x$1;
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

   public CountVectorizer setInputCol(final String value) {
      return (CountVectorizer)this.set(this.inputCol(), value);
   }

   public CountVectorizer setOutputCol(final String value) {
      return (CountVectorizer)this.set(this.outputCol(), value);
   }

   public CountVectorizer setVocabSize(final int value) {
      return (CountVectorizer)this.set(this.vocabSize(), BoxesRunTime.boxToInteger(value));
   }

   public CountVectorizer setMinDF(final double value) {
      return (CountVectorizer)this.set(this.minDF(), BoxesRunTime.boxToDouble(value));
   }

   public CountVectorizer setMaxDF(final double value) {
      return (CountVectorizer)this.set(this.maxDF(), BoxesRunTime.boxToDouble(value));
   }

   public CountVectorizer setMinTF(final double value) {
      return (CountVectorizer)this.set(this.minTF(), BoxesRunTime.boxToDouble(value));
   }

   public CountVectorizer setBinary(final boolean value) {
      return (CountVectorizer)this.set(this.binary(), BoxesRunTime.boxToBoolean(value));
   }

   public CountVectorizerModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      if (BoxesRunTime.unboxToDouble(this.$(this.minDF())) >= (double)1.0F && BoxesRunTime.unboxToDouble(this.$(this.maxDF())) >= (double)1.0F || BoxesRunTime.unboxToDouble(this.$(this.minDF())) < (double)1.0F && BoxesRunTime.unboxToDouble(this.$(this.maxDF())) < (double)1.0F) {
         .MODULE$.require(BoxesRunTime.unboxToDouble(this.$(this.maxDF())) >= BoxesRunTime.unboxToDouble(this.$(this.minDF())), () -> "maxDF must be >= minDF.");
      }

      int vocSize = BoxesRunTime.unboxToInt(this.$(this.vocabSize()));
      RDD input = dataset.select((String)this.$(this.inputCol()), scala.collection.immutable.Nil..MODULE$).rdd().map((x$1) -> x$1.getSeq(0), scala.reflect.ClassTag..MODULE$.apply(Seq.class));
      boolean countingRequired = BoxesRunTime.unboxToDouble(this.$(this.minDF())) < (double)1.0F || BoxesRunTime.unboxToDouble(this.$(this.maxDF())) < (double)1.0F;
      Object var20;
      if (countingRequired) {
         label81: {
            label80: {
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var6 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label80;
                  }
               } else if (var10000.equals(var6)) {
                  break label80;
               }

               BoxedUnit var19 = BoxedUnit.UNIT;
               break label81;
            }

            input.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         }

         var20 = new Some(BoxesRunTime.boxToLong(input.count()));
      } else {
         var20 = scala.None..MODULE$;
      }

      RDD wordCounts;
      String[] vocab;
      label66: {
         label65: {
            Option maybeInputSize = (Option)var20;
            double minDf = BoxesRunTime.unboxToDouble(this.$(this.minDF())) >= (double)1.0F ? BoxesRunTime.unboxToDouble(this.$(this.minDF())) : BoxesRunTime.unboxToDouble(this.$(this.minDF())) * (double)BoxesRunTime.unboxToLong(maybeInputSize.get());
            double maxDf = BoxesRunTime.unboxToDouble(this.$(this.maxDF())) >= (double)1.0F ? BoxesRunTime.unboxToDouble(this.$(this.maxDF())) : BoxesRunTime.unboxToDouble(this.$(this.maxDF())) * (double)BoxesRunTime.unboxToLong(maybeInputSize.get());
            .MODULE$.require(maxDf >= minDf, () -> "maxDF must be >= minDF.");
            RDD allWordCounts = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(input.flatMap((tokens) -> {
               OpenHashMap wc = new OpenHashMap.mcJ.sp(scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.Long());
               tokens.foreach((w) -> BoxesRunTime.boxToLong($anonfun$fit$5(wc, w)));
               return (Iterable)wc.map((x0$1) -> {
                  if (x0$1 != null) {
                     String word = (String)x0$1._1();
                     long count = x0$1._2$mcJ$sp();
                     return new Tuple2(word, new Tuple2.mcJI.sp(count, 1));
                  } else {
                     throw new MatchError(x0$1);
                  }
               });
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.String..MODULE$).reduceByKey((wcdf1, wcdf2) -> new Tuple2.mcJI.sp(wcdf1._1$mcJ$sp() + wcdf2._1$mcJ$sp(), wcdf1._2$mcI$sp() + wcdf2._2$mcI$sp()));
            boolean filteringRequired = this.isSet(this.minDF()) || this.isSet(this.maxDF());
            RDD maybeFilteredWordCounts = filteringRequired ? allWordCounts.filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$fit$10(minDf, maxDf, x0$2))) : allWordCounts;
            wordCounts = maybeFilteredWordCounts.map((x0$3) -> {
               if (x0$3 != null) {
                  String word = (String)x0$3._1();
                  Tuple2 var4 = (Tuple2)x0$3._2();
                  if (var4 != null) {
                     long count = var4._1$mcJ$sp();
                     return new Tuple2(word, BoxesRunTime.boxToLong(count));
                  }
               }

               throw new MatchError(x0$3);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
            long fullVocabSize = wordCounts.count();
            vocab = (String[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(wordCounts.top((int)scala.math.package..MODULE$.min(fullVocabSize, (long)vocSize), scala.package..MODULE$.Ordering().by((x$3) -> BoxesRunTime.boxToLong($anonfun$fit$12(x$3)), scala.math.Ordering.Long..MODULE$))), (x$4) -> (String)x$4._1(), scala.reflect.ClassTag..MODULE$.apply(String.class));
            StorageLevel var21 = input.getStorageLevel();
            StorageLevel var18 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var21 == null) {
               if (var18 != null) {
                  break label65;
               }
            } else if (!var21.equals(var18)) {
               break label65;
            }

            BoxedUnit var22 = BoxedUnit.UNIT;
            break label66;
         }

         input.unpersist(input.unpersist$default$1());
      }

      wordCounts.unpersist(wordCounts.unpersist$default$1());
      if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(.MODULE$.refArrayOps((Object[])vocab))) {
         this.logWarning(() -> "The vocabulary size is empty. If this was unexpected, you may wish to lower minDF (or) increase maxDF.");
      }

      return (CountVectorizerModel)this.copyValues((new CountVectorizerModel(this.uid(), vocab)).setParent(this), this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public CountVectorizer copy(final ParamMap extra) {
      return (CountVectorizer)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final long $anonfun$fit$5(final OpenHashMap wc$1, final String w) {
      return wc$1.changeValue$mcJ$sp(w, (JFunction0.mcJ.sp)() -> 1L, (JFunction1.mcJJ.sp)(x$2) -> x$2 + 1L);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fit$10(final double minDf$1, final double maxDf$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Tuple2 var7 = (Tuple2)x0$2._2();
         if (var7 != null) {
            int df = var7._2$mcI$sp();
            return (double)df >= minDf$1 && (double)df <= maxDf$1;
         }
      }

      throw new MatchError(x0$2);
   }

   // $FF: synthetic method
   public static final long $anonfun$fit$12(final Tuple2 x$3) {
      return x$3._2$mcJ$sp();
   }

   public CountVectorizer(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      CountVectorizerParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public CountVectorizer() {
      this(Identifiable$.MODULE$.randomUID("cntVec"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
