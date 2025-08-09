package org.apache.spark.ml.fpm;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.Identifiable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.ArrayImplicits.;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua\u0001B\u0006\r\u0005]A\u0001B\t\u0001\u0003\u0006\u0004%\te\t\u0005\tq\u0001\u0011\t\u0011)A\u0005I!)!\b\u0001C\u0001w!)!\b\u0001C\u0001\u007f!)\u0011\t\u0001C\u0001\u0005\")!\n\u0001C\u0001\u0017\")\u0011\u000b\u0001C\u0001%\")\u0001\f\u0001C\u00013\")A\f\u0001C\u0001;\"9\u0011Q\u0001\u0001\u0005B\u0005\u001d!A\u0003)sK\u001aL\u0007p\u00159b]*\u0011QBD\u0001\u0004MBl'BA\b\u0011\u0003\tiGN\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h\u0007\u0001\u00192\u0001\u0001\r\u001f!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u0019\te.\u001f*fMB\u0011q\u0004I\u0007\u0002\u0019%\u0011\u0011\u0005\u0004\u0002\u0011!J,g-\u001b=Ta\u0006t\u0007+\u0019:b[N\f1!^5e+\u0005!\u0003CA\u0013-\u001d\t1#\u0006\u0005\u0002(55\t\u0001F\u0003\u0002*-\u00051AH]8pizJ!a\u000b\u000e\u0002\rA\u0013X\rZ3g\u0013\ticF\u0001\u0004TiJLgn\u001a\u0006\u0003WiA3!\u0001\u00197!\t\tD'D\u00013\u0015\t\u0019\u0004#\u0001\u0006b]:|G/\u0019;j_:L!!\u000e\u001a\u0003\u000bMKgnY3\"\u0003]\nQA\r\u00185]A\nA!^5eA!\u001a!\u0001\r\u001c\u0002\rqJg.\u001b;?)\taT\b\u0005\u0002 \u0001!)!e\u0001a\u0001I!\u001aQ\b\r\u001c\u0015\u0003qB3\u0001\u0002\u00197\u00035\u0019X\r^'j]N+\b\u000f]8siR\u00111\tR\u0007\u0002\u0001!)Q)\u0002a\u0001\r\u0006)a/\u00197vKB\u0011\u0011dR\u0005\u0003\u0011j\u0011a\u0001R8vE2,\u0007fA\u00031m\u0005\u00192/\u001a;NCb\u0004\u0016\r\u001e;fe:dUM\\4uQR\u00111\t\u0014\u0005\u0006\u000b\u001a\u0001\r!\u0014\t\u000339K!a\u0014\u000e\u0003\u0007%sG\u000fK\u0002\u0007aY\nQc]3u\u001b\u0006DHj\\2bYB\u0013xN\u001b#C'&TX\r\u0006\u0002D'\")Qi\u0002a\u0001)B\u0011\u0011$V\u0005\u0003-j\u0011A\u0001T8oO\"\u001aq\u0001\r\u001c\u0002\u001dM,GoU3rk\u0016t7-Z\"pYR\u00111I\u0017\u0005\u0006\u000b\"\u0001\r\u0001\n\u0015\u0004\u0011A2\u0014A\b4j]\u00124%/Z9vK:$8+Z9vK:$\u0018.\u00197QCR$XM\u001d8t)\tqv\u000e\u0005\u0002`Y:\u0011\u0001-\u001b\b\u0003C\u001et!A\u00194\u000f\u0005\r,gBA\u0014e\u0013\u0005)\u0012BA\n\u0015\u0013\t\t\"#\u0003\u0002i!\u0005\u00191/\u001d7\n\u0005)\\\u0017a\u00029bG.\fw-\u001a\u0006\u0003QBI!!\u001c8\u0003\u0013\u0011\u000bG/\u0019$sC6,'B\u00016l\u0011\u0015\u0001\u0018\u00021\u0001r\u0003\u001d!\u0017\r^1tKR\u0004$A\u001d=\u0011\u0007M$h/D\u0001l\u0013\t)8NA\u0004ECR\f7/\u001a;\u0011\u0005]DH\u0002\u0001\u0003\ns>\f\t\u0011!A\u0003\u0002i\u00141a\u0018\u00132#\tYh\u0010\u0005\u0002\u001ay&\u0011QP\u0007\u0002\b\u001d>$\b.\u001b8h!\tIr0C\u0002\u0002\u0002i\u00111!\u00118zQ\rI\u0001GN\u0001\u0005G>\u0004\u0018\u0010F\u0002=\u0003\u0013Aq!a\u0003\u000b\u0001\u0004\ti!A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002\u0010\u0005UQBAA\t\u0015\r\t\u0019BD\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003/\t\tB\u0001\u0005QCJ\fW.T1qQ\rQ\u0001G\u000e\u0015\u0004\u0001A2\u0004"
)
public final class PrefixSpan implements PrefixSpanParams {
   private final String uid;
   private DoubleParam minSupport;
   private IntParam maxPatternLength;
   private LongParam maxLocalProjDBSize;
   private Param sequenceCol;
   private Param[] params;
   private ParamMap paramMap;
   private ParamMap defaultParamMap;
   private volatile boolean bitmap$0;

   public double getMinSupport() {
      return PrefixSpanParams.getMinSupport$(this);
   }

   public int getMaxPatternLength() {
      return PrefixSpanParams.getMaxPatternLength$(this);
   }

   public long getMaxLocalProjDBSize() {
      return PrefixSpanParams.getMaxLocalProjDBSize$(this);
   }

   public String getSequenceCol() {
      return PrefixSpanParams.getSequenceCol$(this);
   }

   public String explainParam(final Param param) {
      return Params.explainParam$(this, param);
   }

   public String explainParams() {
      return Params.explainParams$(this);
   }

   public final boolean isSet(final Param param) {
      return Params.isSet$(this, param);
   }

   public final boolean isDefined(final Param param) {
      return Params.isDefined$(this, param);
   }

   public boolean hasParam(final String paramName) {
      return Params.hasParam$(this, paramName);
   }

   public Param getParam(final String paramName) {
      return Params.getParam$(this, paramName);
   }

   public final Params set(final Param param, final Object value) {
      return Params.set$(this, (Param)param, value);
   }

   public final Params set(final String param, final Object value) {
      return Params.set$(this, (String)param, value);
   }

   public final Params set(final ParamPair paramPair) {
      return Params.set$(this, paramPair);
   }

   public final Option get(final Param param) {
      return Params.get$(this, param);
   }

   public final Params clear(final Param param) {
      return Params.clear$(this, param);
   }

   public final Object getOrDefault(final Param param) {
      return Params.getOrDefault$(this, param);
   }

   public final Object $(final Param param) {
      return Params.$$(this, param);
   }

   public final Params setDefault(final Param param, final Object value) {
      return Params.setDefault$(this, param, value);
   }

   public final Params setDefault(final Seq paramPairs) {
      return Params.setDefault$(this, paramPairs);
   }

   public final Option getDefault(final Param param) {
      return Params.getDefault$(this, param);
   }

   public final boolean hasDefault(final Param param) {
      return Params.hasDefault$(this, param);
   }

   public final Params defaultCopy(final ParamMap extra) {
      return Params.defaultCopy$(this, extra);
   }

   public final ParamMap extractParamMap(final ParamMap extra) {
      return Params.extractParamMap$(this, extra);
   }

   public final ParamMap extractParamMap() {
      return Params.extractParamMap$(this);
   }

   public Params copyValues(final Params to, final ParamMap extra) {
      return Params.copyValues$(this, to, extra);
   }

   public ParamMap copyValues$default$2() {
      return Params.copyValues$default$2$(this);
   }

   public void onParamChange(final Param param) {
      Params.onParamChange$(this, param);
   }

   public String toString() {
      return Identifiable.toString$(this);
   }

   public DoubleParam minSupport() {
      return this.minSupport;
   }

   public IntParam maxPatternLength() {
      return this.maxPatternLength;
   }

   public LongParam maxLocalProjDBSize() {
      return this.maxLocalProjDBSize;
   }

   public Param sequenceCol() {
      return this.sequenceCol;
   }

   public void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$minSupport_$eq(final DoubleParam x$1) {
      this.minSupport = x$1;
   }

   public void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$maxPatternLength_$eq(final IntParam x$1) {
      this.maxPatternLength = x$1;
   }

   public void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$maxLocalProjDBSize_$eq(final LongParam x$1) {
      this.maxLocalProjDBSize = x$1;
   }

   public void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$sequenceCol_$eq(final Param x$1) {
      this.sequenceCol = x$1;
   }

   private Param[] params$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.params = Params.params$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.params;
   }

   public Param[] params() {
      return !this.bitmap$0 ? this.params$lzycompute() : this.params;
   }

   public ParamMap paramMap() {
      return this.paramMap;
   }

   public ParamMap defaultParamMap() {
      return this.defaultParamMap;
   }

   public void org$apache$spark$ml$param$Params$_setter_$paramMap_$eq(final ParamMap x$1) {
      this.paramMap = x$1;
   }

   public void org$apache$spark$ml$param$Params$_setter_$defaultParamMap_$eq(final ParamMap x$1) {
      this.defaultParamMap = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public PrefixSpan setMinSupport(final double value) {
      return (PrefixSpan)this.set((Param)this.minSupport(), BoxesRunTime.boxToDouble(value));
   }

   public PrefixSpan setMaxPatternLength(final int value) {
      return (PrefixSpan)this.set((Param)this.maxPatternLength(), BoxesRunTime.boxToInteger(value));
   }

   public PrefixSpan setMaxLocalProjDBSize(final long value) {
      return (PrefixSpan)this.set((Param)this.maxLocalProjDBSize(), BoxesRunTime.boxToLong(value));
   }

   public PrefixSpan setSequenceCol(final String value) {
      return (PrefixSpan)this.set((Param)this.sequenceCol(), value);
   }

   public Dataset findFrequentSequentialPatterns(final Dataset dataset) {
      return (Dataset)Instrumentation$.MODULE$.instrumented((instr) -> {
         instr.logDataset(dataset);
         instr.logParams(this, .MODULE$.SparkArrayOps(this.params()).toImmutableArraySeq());
         String sequenceColParam = (String)this.$(this.sequenceCol());
         DataType inputType = dataset.schema().apply(sequenceColParam).dataType();
         scala.Predef..MODULE$.require(inputType instanceof ArrayType && ((ArrayType)inputType).elementType() instanceof ArrayType, () -> "The input column must be ArrayType and the array element type must also be ArrayType, but got " + inputType + ".");
         Dataset data = dataset.select(sequenceColParam, scala.collection.immutable.Nil..MODULE$);
         RDD sequences = data.where(org.apache.spark.sql.functions..MODULE$.col(sequenceColParam).isNotNull()).rdd().map((r) -> ((IterableOnceOps)r.getSeq(0).map((x$1) -> x$1.toArray(scala.reflect.ClassTag..MODULE$.Any()))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class))), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class))));
         org.apache.spark.mllib.fpm.PrefixSpan mllibPrefixSpan = (new org.apache.spark.mllib.fpm.PrefixSpan()).setMinSupport(BoxesRunTime.unboxToDouble(this.$(this.minSupport()))).setMaxPatternLength(BoxesRunTime.unboxToInt(this.$(this.maxPatternLength()))).setMaxLocalProjDBSize(BoxesRunTime.unboxToLong(this.$(this.maxLocalProjDBSize())));
         RDD rows = mllibPrefixSpan.run(sequences, scala.reflect.ClassTag..MODULE$.Any()).freqSequences().map((f) -> org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{f.sequence(), BoxesRunTime.boxToLong(f.freq())})), scala.reflect.ClassTag..MODULE$.apply(Row.class));
         StructType schema = new StructType((StructField[])((Object[])(new StructField[]{new StructField("sequence", dataset.schema().apply(sequenceColParam).dataType(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("freq", org.apache.spark.sql.types.LongType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
         Dataset freqSequences = dataset.sparkSession().createDataFrame(rows, schema);
         return freqSequences;
      });
   }

   public PrefixSpan copy(final ParamMap extra) {
      return (PrefixSpan)this.defaultCopy(extra);
   }

   public PrefixSpan(final String uid) {
      this.uid = uid;
      Identifiable.$init$(this);
      Params.$init$(this);
      PrefixSpanParams.$init$(this);
      Statics.releaseFence();
   }

   public PrefixSpan() {
      this(Identifiable$.MODULE$.randomUID("prefixSpan"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
