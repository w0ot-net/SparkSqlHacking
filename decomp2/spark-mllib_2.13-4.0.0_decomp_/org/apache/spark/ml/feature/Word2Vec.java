package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005g\u0001B\f\u0019\u0005\rB\u0001\"\u000e\u0001\u0003\u0006\u0004%\tE\u000e\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005o!)q\n\u0001C\u0001!\")q\n\u0001C\u0001+\")q\u000b\u0001C\u00011\")Q\f\u0001C\u0001=\")\u0011\r\u0001C\u0001E\")\u0011\u000e\u0001C\u0001U\")q\u000e\u0001C\u0001a\")a\u000f\u0001C\u0001o\")!\u0010\u0001C\u0001w\")a\u0010\u0001C\u0001\u007f\"9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u0003?\u0001A\u0011IA\u0011\u0011\u001d\ti\u0005\u0001C!\u0003\u001fBq!a\u0019\u0001\t\u0003\n)gB\u0004\u0002\u0000aA\t!!!\u0007\r]A\u0002\u0012AAB\u0011\u0019y5\u0003\"\u0001\u0002\"\"9\u00111U\n\u0005B\u0005\u0015\u0006\"CAW'\u0005\u0005I\u0011BAX\u0005!9vN\u001d33-\u0016\u001c'BA\r\u001b\u0003\u001d1W-\u0019;ve\u0016T!a\u0007\u000f\u0002\u00055d'BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0004\u0001M!\u0001\u0001\n\u00170!\r)c\u0005K\u0007\u00025%\u0011qE\u0007\u0002\n\u000bN$\u0018.\\1u_J\u0004\"!\u000b\u0016\u000e\u0003aI!a\u000b\r\u0003\u001b]{'\u000f\u001a\u001aWK\u000elu\u000eZ3m!\tIS&\u0003\u0002/1\taqk\u001c:eeY+7MQ1tKB\u0011\u0001gM\u0007\u0002c)\u0011!GG\u0001\u0005kRLG.\u0003\u00025c\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\tq\u0007\u0005\u00029\u0003:\u0011\u0011h\u0010\t\u0003uuj\u0011a\u000f\u0006\u0003y\t\na\u0001\u0010:p_Rt$\"\u0001 \u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001k\u0014A\u0002)sK\u0012,g-\u0003\u0002C\u0007\n11\u000b\u001e:j]\u001eT!\u0001Q\u001f)\u0007\u0005)5\n\u0005\u0002G\u00136\tqI\u0003\u0002I9\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005);%!B*j]\u000e,\u0017%\u0001'\u0002\u000bErCG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005\u0015[\u0015A\u0002\u001fj]&$h\b\u0006\u0002R%B\u0011\u0011\u0006\u0001\u0005\u0006k\r\u0001\ra\u000e\u0015\u0004%\u0016[\u0005fA\u0002F\u0017R\t\u0011\u000bK\u0002\u0005\u000b.\u000b1b]3u\u0013:\u0004X\u000f^\"pYR\u0011\u0011LW\u0007\u0002\u0001!)1,\u0002a\u0001o\u0005)a/\u00197vK\"\u001aQ!R&\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\u0005e{\u0006\"B.\u0007\u0001\u00049\u0004f\u0001\u0004F\u0017\u0006i1/\u001a;WK\u000e$xN]*ju\u0016$\"!W2\t\u000bm;\u0001\u0019\u00013\u0011\u0005\u00154W\"A\u001f\n\u0005\u001dl$aA%oi\"\u001aq!R&\u0002\u001bM,GoV5oI><8+\u001b>f)\tI6\u000eC\u0003\\\u0011\u0001\u0007A\rK\u0002\t\u000b6\f\u0013A\\\u0001\u0006c92d\u0006M\u0001\fg\u0016$8\u000b^3q'&TX\r\u0006\u0002Zc\")1,\u0003a\u0001eB\u0011Qm]\u0005\u0003iv\u0012a\u0001R8vE2,\u0007fA\u0005F\u0017\u0006\u00012/\u001a;Ok6\u0004\u0016M\u001d;ji&|gn\u001d\u000b\u00033bDQa\u0017\u0006A\u0002\u0011D3AC#L\u0003)\u0019X\r^'bq&#XM\u001d\u000b\u00033rDQaW\u0006A\u0002\u0011D3aC#L\u0003\u001d\u0019X\r^*fK\u0012$2!WA\u0001\u0011\u0019YF\u00021\u0001\u0002\u0004A\u0019Q-!\u0002\n\u0007\u0005\u001dQH\u0001\u0003M_:<\u0007f\u0001\u0007F\u0017\u0006Y1/\u001a;NS:\u001cu.\u001e8u)\rI\u0016q\u0002\u0005\u000676\u0001\r\u0001\u001a\u0015\u0004\u001b\u0015[\u0015\u0001F:fi6\u000b\u0007pU3oi\u0016t7-\u001a'f]\u001e$\b\u000eF\u0002Z\u0003/AQa\u0017\bA\u0002\u0011DCAD#\u0002\u001c\u0005\u0012\u0011QD\u0001\u0006e9\u0002d\u0006M\u0001\u0004M&$Hc\u0001\u0015\u0002$!9\u0011QE\bA\u0002\u0005\u001d\u0012a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003S\tI\u0004\u0005\u0004\u0002,\u0005E\u0012QG\u0007\u0003\u0003[Q1!a\f\u001d\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u0003g\tiCA\u0004ECR\f7/\u001a;\u0011\t\u0005]\u0012\u0011\b\u0007\u0001\t1\tY$a\t\u0002\u0002\u0003\u0005)\u0011AA\u001f\u0005\ryF%M\t\u0005\u0003\u007f\t)\u0005E\u0002f\u0003\u0003J1!a\u0011>\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!ZA$\u0013\r\tI%\u0010\u0002\u0004\u0003:L\b\u0006B\bF\u00037\tq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003#\ni\u0006\u0005\u0003\u0002T\u0005eSBAA+\u0015\u0011\t9&!\f\u0002\u000bQL\b/Z:\n\t\u0005m\u0013Q\u000b\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA0!\u0001\u0007\u0011\u0011K\u0001\u0007g\u000eDW-\\1)\u0007A)5*\u0001\u0003d_BLHcA)\u0002h!9\u0011\u0011N\tA\u0002\u0005-\u0014!B3yiJ\f\u0007\u0003BA7\u0003gj!!a\u001c\u000b\u0007\u0005E$$A\u0003qCJ\fW.\u0003\u0003\u0002v\u0005=$\u0001\u0003)be\u0006lW*\u00199)\tE)\u0015\u0011P\u0011\u0003\u0003w\nQ!\r\u00185]EB3\u0001A#L\u0003!9vN\u001d33-\u0016\u001c\u0007CA\u0015\u0014'\u001d\u0019\u0012QQAF\u0003#\u00032!ZAD\u0013\r\tI)\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\tA\ni)U\u0005\u0004\u0003\u001f\u000b$!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7OU3bI\u0006\u0014G.\u001a\t\u0005\u0003'\u000bi*\u0004\u0002\u0002\u0016*!\u0011qSAM\u0003\tIwN\u0003\u0002\u0002\u001c\u0006!!.\u0019<b\u0013\u0011\ty*!&\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\u0005\u0015\u0001\u00027pC\u0012$2!UAT\u0011\u0019\tI+\u0006a\u0001o\u0005!\u0001/\u0019;iQ\r)R)\\\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003c\u0003B!a-\u0002:6\u0011\u0011Q\u0017\u0006\u0005\u0003o\u000bI*\u0001\u0003mC:<\u0017\u0002BA^\u0003k\u0013aa\u00142kK\u000e$\bfA\nF[\"\u001a!#R7"
)
public final class Word2Vec extends Estimator implements Word2VecBase, DefaultParamsWritable {
   private final String uid;
   private IntParam vectorSize;
   private IntParam windowSize;
   private IntParam numPartitions;
   private IntParam minCount;
   private IntParam maxSentenceLength;
   private LongParam seed;
   private DoubleParam stepSize;
   private IntParam maxIter;
   private Param outputCol;
   private Param inputCol;

   public static Word2Vec load(final String path) {
      return Word2Vec$.MODULE$.load(path);
   }

   public static MLReader read() {
      return Word2Vec$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getVectorSize() {
      return Word2VecBase.getVectorSize$(this);
   }

   public int getWindowSize() {
      return Word2VecBase.getWindowSize$(this);
   }

   public int getNumPartitions() {
      return Word2VecBase.getNumPartitions$(this);
   }

   public int getMinCount() {
      return Word2VecBase.getMinCount$(this);
   }

   public int getMaxSentenceLength() {
      return Word2VecBase.getMaxSentenceLength$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return Word2VecBase.validateAndTransformSchema$(this, schema);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam vectorSize() {
      return this.vectorSize;
   }

   public final IntParam windowSize() {
      return this.windowSize;
   }

   public final IntParam numPartitions() {
      return this.numPartitions;
   }

   public final IntParam minCount() {
      return this.minCount;
   }

   public final IntParam maxSentenceLength() {
      return this.maxSentenceLength;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$vectorSize_$eq(final IntParam x$1) {
      this.vectorSize = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$windowSize_$eq(final IntParam x$1) {
      this.windowSize = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$numPartitions_$eq(final IntParam x$1) {
      this.numPartitions = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$minCount_$eq(final IntParam x$1) {
      this.minCount = x$1;
   }

   public final void org$apache$spark$ml$feature$Word2VecBase$_setter_$maxSentenceLength_$eq(final IntParam x$1) {
      this.maxSentenceLength = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public DoubleParam stepSize() {
      return this.stepSize;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
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

   public Word2Vec setInputCol(final String value) {
      return (Word2Vec)this.set(this.inputCol(), value);
   }

   public Word2Vec setOutputCol(final String value) {
      return (Word2Vec)this.set(this.outputCol(), value);
   }

   public Word2Vec setVectorSize(final int value) {
      return (Word2Vec)this.set(this.vectorSize(), BoxesRunTime.boxToInteger(value));
   }

   public Word2Vec setWindowSize(final int value) {
      return (Word2Vec)this.set(this.windowSize(), BoxesRunTime.boxToInteger(value));
   }

   public Word2Vec setStepSize(final double value) {
      return (Word2Vec)this.set(this.stepSize(), BoxesRunTime.boxToDouble(value));
   }

   public Word2Vec setNumPartitions(final int value) {
      return (Word2Vec)this.set(this.numPartitions(), BoxesRunTime.boxToInteger(value));
   }

   public Word2Vec setMaxIter(final int value) {
      return (Word2Vec)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public Word2Vec setSeed(final long value) {
      return (Word2Vec)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public Word2Vec setMinCount(final int value) {
      return (Word2Vec)this.set(this.minCount(), BoxesRunTime.boxToInteger(value));
   }

   public Word2Vec setMaxSentenceLength(final int value) {
      return (Word2Vec)this.set(this.maxSentenceLength(), BoxesRunTime.boxToInteger(value));
   }

   public Word2VecModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      RDD input = dataset.select((String)this.$(this.inputCol()), .MODULE$).rdd().map((x$1) -> x$1.getSeq(0), scala.reflect.ClassTag..MODULE$.apply(Seq.class));
      org.apache.spark.mllib.feature.Word2VecModel wordVectors = (new org.apache.spark.mllib.feature.Word2Vec()).setLearningRate(BoxesRunTime.unboxToDouble(this.$(this.stepSize()))).setMinCount(BoxesRunTime.unboxToInt(this.$(this.minCount()))).setNumIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter()))).setNumPartitions(BoxesRunTime.unboxToInt(this.$(this.numPartitions()))).setSeed(BoxesRunTime.unboxToLong(this.$(this.seed()))).setVectorSize(BoxesRunTime.unboxToInt(this.$(this.vectorSize()))).setWindowSize(BoxesRunTime.unboxToInt(this.$(this.windowSize()))).setMaxSentenceLength(BoxesRunTime.unboxToInt(this.$(this.maxSentenceLength()))).fit(input);
      return (Word2VecModel)this.copyValues((new Word2VecModel(this.uid(), wordVectors)).setParent(this), this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public Word2Vec copy(final ParamMap extra) {
      return (Word2Vec)this.defaultCopy(extra);
   }

   public Word2Vec(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasSeed.$init$(this);
      Word2VecBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public Word2Vec() {
      this(Identifiable$.MODULE$.randomUID("w2v"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
