package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.param.shared.HasRelativeError;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t]a\u0001\u0002\u0011\"\u00011B\u0001\u0002\u0010\u0001\u0003\u0006\u0004%\t%\u0010\u0005\t)\u0002\u0011\t\u0011)A\u0005}!Aa\u000b\u0001BC\u0002\u0013\u0005q\u000b\u0003\u0005k\u0001\t\u0005\t\u0015!\u0003Y\u0011\u0019a\u0007\u0001\"\u0001$[\"1A\u000e\u0001C\u0001GIDQa\u001d\u0001\u0005\u0002QDQa\u001f\u0001\u0005\u0002qDaa \u0001\u0005\u0002\u0005\u0005\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\u000b\u0003'\u0001\u0001R1A\u0005\n\u0005U\u0001bBA\u001b\u0001\u0011\u0005\u0013q\u0007\u0005\b\u0003;\u0002A\u0011IA0\u0011\u001d\t\t\b\u0001C!\u0003gBq!!\"\u0001\t\u0003\n9\tC\u0004\u0002\u0012\u0002!\t%a%\b\u000f\u0005e\u0015\u0005#\u0001\u0002\u001c\u001a1\u0001%\tE\u0001\u0003;Ca\u0001\u001c\n\u0005\u0002\u0005mfaBA_%\u0001\u0011\u0012q\u0018\u0005\n\u0003\u0003$\"\u0011!Q\u0001\nEBa\u0001\u001c\u000b\u0005\u0002\u0005\r\u0007bBAf)\u0011E\u0013Q\u001a\u0004\u0007\u00033\u0014B!a7\t\r1DB\u0011AAr\u0011%\t9\u000f\u0007b\u0001\n\u0013\tI\u000f\u0003\u0005\u0002vb\u0001\u000b\u0011BAv\u0011\u001d\t9\u0010\u0007C!\u0003sDq!!@\u0013\t\u0003\ny\u0010C\u0004\u0002xJ!\tEa\u0001\t\u0013\t%!#!A\u0005\n\t-!\u0001D%naV$XM]'pI\u0016d'B\u0001\u0012$\u0003\u001d1W-\u0019;ve\u0016T!\u0001J\u0013\u0002\u00055d'B\u0001\u0014(\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0013&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002U\u0005\u0019qN]4\u0004\u0001M!\u0001!L\u001a7!\rqs&M\u0007\u0002G%\u0011\u0001g\t\u0002\u0006\u001b>$W\r\u001c\t\u0003e\u0001i\u0011!\t\t\u0003eQJ!!N\u0011\u0003\u001b%k\u0007/\u001e;feB\u000b'/Y7t!\t9$(D\u00019\u0015\tI4%\u0001\u0003vi&d\u0017BA\u001e9\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#\u0001 \u0011\u0005}BeB\u0001!G!\t\tE)D\u0001C\u0015\t\u00195&\u0001\u0004=e>|GO\u0010\u0006\u0002\u000b\u0006)1oY1mC&\u0011q\tR\u0001\u0007!J,G-\u001a4\n\u0005%S%AB*ue&twM\u0003\u0002H\t\"\u001a\u0011\u0001\u0014*\u0011\u00055\u0003V\"\u0001(\u000b\u0005=+\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011K\u0014\u0002\u0006'&t7-Z\u0011\u0002'\u0006)!G\f\u001a/a\u0005!Q/\u001b3!Q\r\u0011AJU\u0001\fgV\u0014(o\\4bi\u0016$e)F\u0001Y!\tIfM\u0004\u0002[G:\u00111,\u0019\b\u00039\u0002t!!X0\u000f\u0005\u0005s\u0016\"\u0001\u0016\n\u0005!J\u0013B\u0001\u0014(\u0013\t\u0011W%A\u0002tc2L!\u0001Z3\u0002\u000fA\f7m[1hK*\u0011!-J\u0005\u0003O\"\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0005\u0011,\u0007fA\u0002M%\u0006a1/\u001e:s_\u001e\fG/\u001a#GA!\u001aA\u0001\u0014*\u0002\rqJg.\u001b;?)\r\td\u000e\u001d\u0005\u0006y\u0015\u0001\rA\u0010\u0015\u0004]2\u0013\u0006\"\u0002,\u0006\u0001\u0004A\u0006f\u00019M%R\t\u0011'A\u0006tKRLe\u000e];u\u0007>dGCA;w\u001b\u0005\u0001\u0001\"B<\b\u0001\u0004q\u0014!\u0002<bYV,\u0007fA\u0004Ms\u0006\n!0A\u00034]Ar\u0003'\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000e\u0006\u0002v{\")q\u000f\u0003a\u0001}!\u001a\u0001\u0002T=\u0002\u0019M,G/\u00138qkR\u001cu\u000e\\:\u0015\u0007U\f\u0019\u0001\u0003\u0004x\u0013\u0001\u0007\u0011Q\u0001\t\u0006\u0003\u000f\tIAP\u0007\u0002\t&\u0019\u00111\u0002#\u0003\u000b\u0005\u0013(/Y=\u0002\u001bM,GoT;uaV$8i\u001c7t)\r)\u0018\u0011\u0003\u0005\u0007o*\u0001\r!!\u0002\u0002\u0015M,(O]8hCR,7/\u0006\u0002\u0002\u0018A9\u0011\u0011DA\u0012}\u0005\u001dRBAA\u000e\u0015\u0011\ti\"a\b\u0002\u0013%lW.\u001e;bE2,'bAA\u0011\t\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0015\u00121\u0004\u0002\u0004\u001b\u0006\u0004\b\u0003BA\u0004\u0003SI1!a\u000bE\u0005\u0019!u.\u001e2mK\"\u001a1\"a\f\u0011\t\u0005\u001d\u0011\u0011G\u0005\u0004\u0003g!%!\u0003;sC:\u001c\u0018.\u001a8u\u0003%!(/\u00198tM>\u0014X\u000eF\u0002Y\u0003sAq!a\u000f\r\u0001\u0004\ti$A\u0004eCR\f7/\u001a;1\t\u0005}\u00121\n\t\u0007\u0003\u0003\n\u0019%a\u0012\u000e\u0003\u0015L1!!\u0012f\u0005\u001d!\u0015\r^1tKR\u0004B!!\u0013\u0002L1\u0001A\u0001DA'\u0003s\t\t\u0011!A\u0003\u0002\u0005=#aA0%eE!\u0011\u0011KA,!\u0011\t9!a\u0015\n\u0007\u0005UCIA\u0004O_RD\u0017N\\4\u0011\t\u0005\u001d\u0011\u0011L\u0005\u0004\u00037\"%aA!os\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002b\u00055\u0004\u0003BA2\u0003Sj!!!\u001a\u000b\u0007\u0005\u001dT-A\u0003usB,7/\u0003\u0003\u0002l\u0005\u0015$AC*ueV\u001cG\u000fV=qK\"9\u0011qN\u0007A\u0002\u0005\u0005\u0014AB:dQ\u0016l\u0017-\u0001\u0003d_BLHcA\u0019\u0002v!9\u0011q\u000f\bA\u0002\u0005e\u0014!B3yiJ\f\u0007\u0003BA>\u0003\u0003k!!! \u000b\u0007\u0005}4%A\u0003qCJ\fW.\u0003\u0003\u0002\u0004\u0006u$\u0001\u0003)be\u0006lW*\u00199\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0005\u0005%\u0005cA\u001c\u0002\f&\u0019\u0011Q\u0012\u001d\u0003\u00115cuK]5uKJD3a\u0004'S\u0003!!xn\u0015;sS:<G#\u0001 )\u0007Aa\u0015\u0010K\u0002\u0001\u0019J\u000bA\"S7qkR,'/T8eK2\u0004\"A\r\n\u0014\u000fI\ty*!*\u0002,B!\u0011qAAQ\u0013\r\t\u0019\u000b\u0012\u0002\u0007\u0003:L(+\u001a4\u0011\t]\n9+M\u0005\u0004\u0003SC$AC'M%\u0016\fG-\u00192mKB!\u0011QVA\\\u001b\t\tyK\u0003\u0003\u00022\u0006M\u0016AA5p\u0015\t\t),\u0001\u0003kCZ\f\u0017\u0002BA]\u0003_\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a'\u0003%%k\u0007/\u001e;fe6{G-\u001a7Xe&$XM]\n\u0004)\u0005%\u0015\u0001C5ogR\fgnY3\u0015\t\u0005\u0015\u0017\u0011\u001a\t\u0004\u0003\u000f$R\"\u0001\n\t\r\u0005\u0005g\u00031\u00012\u0003!\u0019\u0018M^3J[BdG\u0003BAh\u0003+\u0004B!a\u0002\u0002R&\u0019\u00111\u001b#\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003/<\u0002\u0019\u0001 \u0002\tA\fG\u000f\u001b\u0002\u000e\u00136\u0004X\u000f^3s%\u0016\fG-\u001a:\u0014\u0007a\ti\u000e\u0005\u00038\u0003?\f\u0014bAAqq\tAQ\n\u0014*fC\u0012,'\u000f\u0006\u0002\u0002fB\u0019\u0011q\u0019\r\u0002\u0013\rd\u0017m]:OC6,WCAAv!\u0011\ti/a=\u000e\u0005\u0005=(\u0002BAy\u0003g\u000bA\u0001\\1oO&\u0019\u0011*a<\u0002\u0015\rd\u0017m]:OC6,\u0007%\u0001\u0003m_\u0006$GcA\u0019\u0002|\"1\u0011q\u001b\u000fA\u0002y\nAA]3bIV\u0011\u0011Q\u001c\u0015\u0004;1\u0013FcA\u0019\u0003\u0006!1\u0011q\u001b\u0010A\u0002yB3A\b'S\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011i\u0001\u0005\u0003\u0002n\n=\u0011\u0002\u0002B\t\u0003_\u0014aa\u00142kK\u000e$\bf\u0001\nM%\"\u001a\u0011\u0003\u0014*"
)
public class ImputerModel extends Model implements ImputerParams, MLWritable {
   private transient Map surrogates;
   private final String uid;
   private final Dataset surrogateDF;
   private Param strategy;
   private DoubleParam missingValue;
   private DoubleParam relativeError;
   private StringArrayParam outputCols;
   private Param outputCol;
   private StringArrayParam inputCols;
   private Param inputCol;
   private transient volatile boolean bitmap$trans$0;

   public static ImputerModel load(final String path) {
      return ImputerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ImputerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getStrategy() {
      return ImputerParams.getStrategy$(this);
   }

   public double getMissingValue() {
      return ImputerParams.getMissingValue$(this);
   }

   public Tuple2 getInOutCols() {
      return ImputerParams.getInOutCols$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return ImputerParams.validateAndTransformSchema$(this, schema);
   }

   public final double getRelativeError() {
      return HasRelativeError.getRelativeError$(this);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final Param strategy() {
      return this.strategy;
   }

   public final DoubleParam missingValue() {
      return this.missingValue;
   }

   public final void org$apache$spark$ml$feature$ImputerParams$_setter_$strategy_$eq(final Param x$1) {
      this.strategy = x$1;
   }

   public final void org$apache$spark$ml$feature$ImputerParams$_setter_$missingValue_$eq(final DoubleParam x$1) {
      this.missingValue = x$1;
   }

   public final DoubleParam relativeError() {
      return this.relativeError;
   }

   public final void org$apache$spark$ml$param$shared$HasRelativeError$_setter_$relativeError_$eq(final DoubleParam x$1) {
      this.relativeError = x$1;
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
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

   public Dataset surrogateDF() {
      return this.surrogateDF;
   }

   public ImputerModel setInputCol(final String value) {
      return (ImputerModel)this.set(this.inputCol(), value);
   }

   public ImputerModel setOutputCol(final String value) {
      return (ImputerModel)this.set(this.outputCol(), value);
   }

   public ImputerModel setInputCols(final String[] value) {
      return (ImputerModel)this.set(this.inputCols(), value);
   }

   public ImputerModel setOutputCols(final String[] value) {
      return (ImputerModel)this.set(this.outputCols(), value);
   }

   private Map surrogates$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            Row row = (Row)this.surrogateDF().head();
            this.surrogates = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.refArrayOps((Object[])row.schema().fieldNames()))), (x0$1) -> {
               if (x0$1 != null) {
                  String name = (String)x0$1._1();
                  int index = x0$1._2$mcI$sp();
                  return new Tuple2(name, BoxesRunTime.boxToDouble(row.getDouble(index)));
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.surrogates;
   }

   private Map surrogates() {
      return !this.bitmap$trans$0 ? this.surrogates$lzycompute() : this.surrogates;
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      Tuple2 var4 = this.getInOutCols();
      if (var4 != null) {
         String[] inputColumns = (String[])var4._1();
         String[] outputColumns = (String[])var4._2();
         Tuple2 var3 = new Tuple2(inputColumns, outputColumns);
         String[] inputColumns = (String[])var3._1();
         String[] outputColumns = (String[])var3._2();
         Column[] newCols = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])inputColumns), (inputCol) -> {
            double surrogate = BoxesRunTime.unboxToDouble(this.surrogates().apply(inputCol));
            DataType inputType = SchemaUtils$.MODULE$.getSchemaFieldType(dataset.schema(), inputCol);
            Column ic = org.apache.spark.sql.functions..MODULE$.col(inputCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
            return org.apache.spark.sql.functions..MODULE$.when(ic.isNull(), BoxesRunTime.boxToDouble(surrogate)).when(ic.$eq$eq$eq(this.$(this.missingValue())), BoxesRunTime.boxToDouble(surrogate)).otherwise(ic).cast(inputType);
         }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
         return dataset.withColumns(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(outputColumns).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(newCols).toImmutableArraySeq()).toDF();
      } else {
         throw new MatchError(var4);
      }
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public ImputerModel copy(final ParamMap extra) {
      ImputerModel copied = new ImputerModel(this.uid(), this.surrogateDF());
      return (ImputerModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new ImputerModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "ImputerModel: uid=" + var10000 + ", strategy=" + this.$(this.strategy()) + ", missingValue=" + this.$(this.missingValue()) + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "") + this.get(this.outputCols()).map((c) -> ", numOutputCols=" + c.length).getOrElse(() -> "");
   }

   public ImputerModel(final String uid, final Dataset surrogateDF) {
      this.uid = uid;
      this.surrogateDF = surrogateDF;
      HasInputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasOutputCols.$init$(this);
      HasRelativeError.$init$(this);
      ImputerParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public ImputerModel() {
      this("", (Dataset)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ImputerModelWriter extends MLWriter {
      private final ImputerModel instance;

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         String dataPath = (new Path(path, "data")).toString();
         this.instance.surrogateDF().repartition(1).write().parquet(dataPath);
      }

      public ImputerModelWriter(final ImputerModel instance) {
         this.instance = instance;
      }
   }

   private static class ImputerReader extends MLReader {
      private final String className = ImputerModel.class.getName();

      private String className() {
         return this.className;
      }

      public ImputerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset surrogateDF = this.sparkSession().read().parquet(dataPath);
         ImputerModel model = new ImputerModel(metadata.uid(), surrogateDF);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public ImputerReader() {
      }
   }
}
