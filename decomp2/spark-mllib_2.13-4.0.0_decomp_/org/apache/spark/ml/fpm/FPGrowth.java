package org.apache.spark.ml.fpm;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%f\u0001B\n\u0015\u0001}A\u0001\"\r\u0001\u0003\u0006\u0004%\tE\r\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005g!)1\n\u0001C\u0001\u0019\")1\n\u0001C\u0001#\")1\u000b\u0001C\u0001)\")Q\f\u0001C\u0001=\")A\r\u0001C\u0001K\")\u0001\u000e\u0001C\u0001S\")A\u000e\u0001C\u0001[\")\u0001\u000f\u0001C!c\"9\u0011q\u0002\u0001\u0005\n\u0005E\u0001bBA\u001d\u0001\u0011\u0005\u00131\b\u0005\b\u0003\u001f\u0002A\u0011IA)\u000f\u001d\t9\u0007\u0006E\u0001\u0003S2aa\u0005\u000b\t\u0002\u0005-\u0004BB&\u0010\t\u0003\tI\tC\u0004\u0002\f>!\t%!$\t\u0013\u0005Uu\"!A\u0005\n\u0005]%\u0001\u0003$Q\u000fJ|w\u000f\u001e5\u000b\u0005U1\u0012a\u00014q[*\u0011q\u0003G\u0001\u0003[2T!!\u0007\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005ma\u0012AB1qC\u000eDWMC\u0001\u001e\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0001\u0005K\u0016\u0011\u0007\u0005\u0012C%D\u0001\u0017\u0013\t\u0019cCA\u0005FgRLW.\u0019;peB\u0011QEJ\u0007\u0002)%\u0011q\u0005\u0006\u0002\u000e\rB;%o\\<uQ6{G-\u001a7\u0011\u0005\u0015J\u0013B\u0001\u0016\u0015\u000591\u0005k\u0012:poRD\u0007+\u0019:b[N\u0004\"\u0001L\u0018\u000e\u00035R!A\f\f\u0002\tU$\u0018\u000e\\\u0005\u0003a5\u0012Q\u0003R3gCVdG\u000fU1sC6\u001cxK]5uC\ndW-A\u0002vS\u0012,\u0012a\r\t\u0003iur!!N\u001e\u0011\u0005YJT\"A\u001c\u000b\u0005ar\u0012A\u0002\u001fs_>$hHC\u0001;\u0003\u0015\u00198-\u00197b\u0013\ta\u0014(\u0001\u0004Qe\u0016$WMZ\u0005\u0003}}\u0012aa\u0015;sS:<'B\u0001\u001f:Q\r\t\u0011i\u0012\t\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\tb\t!\"\u00198o_R\fG/[8o\u0013\t15IA\u0003TS:\u001cW-I\u0001I\u0003\u0015\u0011dF\r\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\t\tu)\u0001\u0004=S:LGO\u0010\u000b\u0003\u001b:\u0003\"!\n\u0001\t\u000bE\u001a\u0001\u0019A\u001a)\u00079\u000bu\tK\u0002\u0004\u0003\u001e#\u0012!\u0014\u0015\u0004\t\u0005;\u0015!D:fi6KgnU;qa>\u0014H\u000f\u0006\u0002V-6\t\u0001\u0001C\u0003X\u000b\u0001\u0007\u0001,A\u0003wC2,X\r\u0005\u0002Z56\t\u0011(\u0003\u0002\\s\t1Ai\\;cY\u0016D3!B!H\u0003A\u0019X\r\u001e(v[B\u000b'\u000f^5uS>t7\u000f\u0006\u0002V?\")qK\u0002a\u0001AB\u0011\u0011,Y\u0005\u0003Ef\u00121!\u00138uQ\r1\u0011iR\u0001\u0011g\u0016$X*\u001b8D_:4\u0017\u000eZ3oG\u0016$\"!\u00164\t\u000b];\u0001\u0019\u0001-)\u0007\u001d\tu)A\u0006tKRLE/Z7t\u0007>dGCA+k\u0011\u00159\u0006\u00021\u00014Q\rA\u0011iR\u0001\u0011g\u0016$\bK]3eS\u000e$\u0018n\u001c8D_2$\"!\u00168\t\u000b]K\u0001\u0019A\u001a)\u0007%\tu)A\u0002gSR$\"\u0001\n:\t\u000bMT\u0001\u0019\u0001;\u0002\u000f\u0011\fG/Y:fiB\u0012Q/ \t\u0004mf\\X\"A<\u000b\u0005aD\u0012aA:rY&\u0011!p\u001e\u0002\b\t\u0006$\u0018m]3u!\taX\u0010\u0004\u0001\u0005\u0013y\u0014\u0018\u0011!A\u0001\u0006\u0003y(aA0%cE!\u0011\u0011AA\u0004!\rI\u00161A\u0005\u0004\u0003\u000bI$a\u0002(pi\"Lgn\u001a\t\u00043\u0006%\u0011bAA\u0006s\t\u0019\u0011I\\=)\u0007)\tu)\u0001\u0006hK:,'/[2GSR,B!a\u0005\u0002*Q!\u0011QCA\u0017)\r!\u0013q\u0003\u0005\n\u00033Y\u0011\u0011!a\u0002\u00037\t!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\ti\"a\t\u0002(5\u0011\u0011q\u0004\u0006\u0004\u0003CI\u0014a\u0002:fM2,7\r^\u0005\u0005\u0003K\tyB\u0001\u0005DY\u0006\u001c8\u000fV1h!\ra\u0018\u0011\u0006\u0003\u0007\u0003WY!\u0019A@\u0003\u0003QCaa]\u0006A\u0002\u0005=\u0002\u0007BA\u0019\u0003k\u0001BA^=\u00024A\u0019A0!\u000e\u0005\u0017\u0005]\u0012QFA\u0001\u0002\u0003\u0015\ta \u0002\u0004?\u0012\u0012\u0014a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005u\u0012\u0011\n\t\u0005\u0003\u007f\t)%\u0004\u0002\u0002B)\u0019\u00111I<\u0002\u000bQL\b/Z:\n\t\u0005\u001d\u0013\u0011\t\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA&\u0019\u0001\u0007\u0011QH\u0001\u0007g\u000eDW-\\1)\u00071\tu)\u0001\u0003d_BLHcA'\u0002T!9\u0011QK\u0007A\u0002\u0005]\u0013!B3yiJ\f\u0007\u0003BA-\u0003?j!!a\u0017\u000b\u0007\u0005uc#A\u0003qCJ\fW.\u0003\u0003\u0002b\u0005m#\u0001\u0003)be\u0006lW*\u00199)\u00075\tu\tK\u0002\u0001\u0003\u001e\u000b\u0001B\u0012)He><H\u000f\u001b\t\u0003K=\u0019raDA7\u0003g\nI\bE\u0002Z\u0003_J1!!\u001d:\u0005\u0019\te.\u001f*fMB!A&!\u001eN\u0013\r\t9(\f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\tY(!\"\u000e\u0005\u0005u$\u0002BA@\u0003\u0003\u000b!![8\u000b\u0005\u0005\r\u0015\u0001\u00026bm\u0006LA!a\"\u0002~\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011N\u0001\u0005Y>\fG\rF\u0002N\u0003\u001fCa!!%\u0012\u0001\u0004\u0019\u0014\u0001\u00029bi\"D3!E!H\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\n\u0005\u0003\u0002\u001c\u0006\u0005VBAAO\u0015\u0011\ty*!!\u0002\t1\fgnZ\u0005\u0005\u0003G\u000biJ\u0001\u0004PE*,7\r\u001e\u0015\u0004\u001f\u0005;\u0005f\u0001\bB\u000f\u0002"
)
public class FPGrowth extends Estimator implements FPGrowthParams, DefaultParamsWritable {
   private final String uid;
   private Param itemsCol;
   private DoubleParam minSupport;
   private IntParam numPartitions;
   private DoubleParam minConfidence;
   private Param predictionCol;

   public static FPGrowth load(final String path) {
      return FPGrowth$.MODULE$.load(path);
   }

   public static MLReader read() {
      return FPGrowth$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getItemsCol() {
      return FPGrowthParams.getItemsCol$(this);
   }

   public double getMinSupport() {
      return FPGrowthParams.getMinSupport$(this);
   }

   public int getNumPartitions() {
      return FPGrowthParams.getNumPartitions$(this);
   }

   public double getMinConfidence() {
      return FPGrowthParams.getMinConfidence$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return FPGrowthParams.validateAndTransformSchema$(this, schema);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public Param itemsCol() {
      return this.itemsCol;
   }

   public DoubleParam minSupport() {
      return this.minSupport;
   }

   public IntParam numPartitions() {
      return this.numPartitions;
   }

   public DoubleParam minConfidence() {
      return this.minConfidence;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$itemsCol_$eq(final Param x$1) {
      this.itemsCol = x$1;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minSupport_$eq(final DoubleParam x$1) {
      this.minSupport = x$1;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$numPartitions_$eq(final IntParam x$1) {
      this.numPartitions = x$1;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minConfidence_$eq(final DoubleParam x$1) {
      this.minConfidence = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public FPGrowth setMinSupport(final double value) {
      return (FPGrowth)this.set(this.minSupport(), BoxesRunTime.boxToDouble(value));
   }

   public FPGrowth setNumPartitions(final int value) {
      return (FPGrowth)this.set(this.numPartitions(), BoxesRunTime.boxToInteger(value));
   }

   public FPGrowth setMinConfidence(final double value) {
      return (FPGrowth)this.set(this.minConfidence(), BoxesRunTime.boxToDouble(value));
   }

   public FPGrowth setItemsCol(final String value) {
      return (FPGrowth)this.set(this.itemsCol(), value);
   }

   public FPGrowth setPredictionCol(final String value) {
      return (FPGrowth)this.set(this.predictionCol(), value);
   }

   public FPGrowthModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      return this.genericFit(dataset, .MODULE$.Nothing());
   }

   private FPGrowthModel genericFit(final Dataset dataset, final ClassTag evidence$1) {
      return (FPGrowthModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         boolean var14;
         label32: {
            label31: {
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var4 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10000 == null) {
                  if (var4 == null) {
                     break label31;
                  }
               } else if (var10000.equals(var4)) {
                  break label31;
               }

               var14 = false;
               break label32;
            }

            var14 = true;
         }

         boolean handlePersistence = var14;
         instr.logPipelineStage(this);
         instr.logDataset(dataset);
         instr.logParams(this, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.params()).toImmutableArraySeq());
         Dataset data = dataset.select((String)this.$(this.itemsCol()), scala.collection.immutable.Nil..MODULE$);
         RDD items = data.where(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.itemsCol())).isNotNull()).rdd().map((r) -> r.getSeq(0).toArray(.MODULE$.Any()), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class)));
         org.apache.spark.mllib.fpm.FPGrowth mllibFP = (new org.apache.spark.mllib.fpm.FPGrowth()).setMinSupport(BoxesRunTime.unboxToDouble(this.$(this.minSupport())));
         if (this.isSet(this.numPartitions())) {
            mllibFP.setNumPartitions(BoxesRunTime.unboxToInt(this.$(this.numPartitions())));
         } else {
            BoxedUnit var15 = BoxedUnit.UNIT;
         }

         if (handlePersistence) {
            items.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         } else {
            BoxedUnit var16 = BoxedUnit.UNIT;
         }

         long inputRowCount = items.count();
         instr.logNumExamples(inputRowCount);
         org.apache.spark.mllib.fpm.FPGrowthModel parentModel = mllibFP.run(items, .MODULE$.Any());
         RDD rows = parentModel.freqItemsets().map((f) -> org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{f.items(), BoxesRunTime.boxToLong(f.freq())})), .MODULE$.apply(Row.class));
         StructType schema = new StructType((StructField[])((Object[])(new StructField[]{new StructField("items", dataset.schema().apply((String)this.$(this.itemsCol())).dataType(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("freq", org.apache.spark.sql.types.LongType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
         Dataset frequentItems = dataset.sparkSession().createDataFrame(rows, schema);
         if (handlePersistence) {
            items.unpersist(items.unpersist$default$1());
         } else {
            BoxedUnit var17 = BoxedUnit.UNIT;
         }

         return (FPGrowthModel)((Model)this.copyValues(new FPGrowthModel(this.uid(), frequentItems, parentModel.itemSupport(), inputRowCount), this.copyValues$default$2())).setParent(this);
      });
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public FPGrowth copy(final ParamMap extra) {
      return (FPGrowth)this.defaultCopy(extra);
   }

   public FPGrowth(final String uid) {
      this.uid = uid;
      HasPredictionCol.$init$(this);
      FPGrowthParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public FPGrowth() {
      this(Identifiable$.MODULE$.randomUID("fpgrowth"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
