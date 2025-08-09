package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.BooleanType.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005a\u0001\u0002\u0011\"\u00011B\u0001\u0002\u0010\u0001\u0003\u0006\u0004%\t%\u0010\u0005\t)\u0002\u0011\t\u0011)A\u0005}!Ia\u000b\u0001BC\u0002\u0013\u00051e\u0016\u0005\t7\u0002\u0011\t\u0011)A\u00051\"IA\f\u0001BC\u0002\u0013\u00051%\u0018\u0005\tC\u0002\u0011\t\u0011)A\u0005=\"1!\r\u0001C\u0001C\rDaA\u0019\u0001\u0005\u0002\rB\u0007\"B5\u0001\t\u0003R\u0007bBA\u0013\u0001\u0011\u0005\u0013q\u0005\u0005\b\u0003w\u0001A\u0011IA\u001f\u0011\u001d\t\t\u0006\u0001C!\u0003'Bq!a\u0016\u0001\t\u0003\u0019S\bC\u0004\u0002`\u0001!I!!\u0019\t\u000f\u0005=\u0004\u0001\"\u0003\u0002r!9\u00111\u0010\u0001\u0005B\u0005utaBAEC!\u0005\u00111\u0012\u0004\u0007A\u0005B\t!!$\t\r\t\u0014B\u0011AAV\u0011\u001d\tiK\u0005C!\u0003_Cq!!/\u0013\t\u0003\nYLB\u0004\u0002DJ\u0001!#!2\t\u0013\u0005\u001dgC!A!\u0002\u0013\t\u0004B\u00022\u0017\t\u0003\tI\rC\u0004\u0002RZ!\t&a5\u0007\r\u0005]'\u0003BAm\u0011\u0019\u0011'\u0004\"\u0001\u0002\\\"I\u0011q\u001c\u000eC\u0002\u0013%\u0011\u0011\u001d\u0005\t\u0003[T\u0002\u0015!\u0003\u0002d\"9\u0011\u0011\u0018\u000e\u0005B\u0005=\b\"CAz%\u0005\u0005I\u0011BA{\u00055\u0011fi\u001c:nk2\fWj\u001c3fY*\u0011!eI\u0001\bM\u0016\fG/\u001e:f\u0015\t!S%\u0001\u0002nY*\u0011aeJ\u0001\u0006gB\f'o\u001b\u0006\u0003Q%\na!\u00199bG\",'\"\u0001\u0016\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001i3G\u000e\t\u0004]=\nT\"A\u0012\n\u0005A\u001a#!B'pI\u0016d\u0007C\u0001\u001a\u0001\u001b\u0005\t\u0003C\u0001\u001a5\u0013\t)\u0014E\u0001\u0007S\r>\u0014X.\u001e7b\u0005\u0006\u001cX\r\u0005\u00028u5\t\u0001H\u0003\u0002:G\u0005!Q\u000f^5m\u0013\tY\u0004H\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005q\u0004CA I\u001d\t\u0001e\t\u0005\u0002B\t6\t!I\u0003\u0002DW\u00051AH]8pizR\u0011!R\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000f\u0012\u000ba\u0001\u0015:fI\u00164\u0017BA%K\u0005\u0019\u0019FO]5oO*\u0011q\t\u0012\u0015\u0004\u00031\u0013\u0006CA'Q\u001b\u0005q%BA(&\u0003)\tgN\\8uCRLwN\\\u0005\u0003#:\u0013QaU5oG\u0016\f\u0013aU\u0001\u0006c9*d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003\u0019J\u000bqB]3t_24X\r\u001a$pe6,H.Y\u000b\u00021B\u0011!'W\u0005\u00035\u0006\u0012\u0001CU3t_24X\r\u001a*G_JlW\u000f\\1\u0002!I,7o\u001c7wK\u00124uN]7vY\u0006\u0004\u0013!\u00049ja\u0016d\u0017N\\3N_\u0012,G.F\u0001_!\tqs,\u0003\u0002aG\ti\u0001+\u001b9fY&tW-T8eK2\fa\u0002]5qK2Lg.Z'pI\u0016d\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005c\u00114w\rC\u0003=\u000f\u0001\u0007a\bK\u0002e\u0019JCQAV\u0004A\u0002aCQ\u0001X\u0004A\u0002y#\u0012!M\u0001\niJ\fgn\u001d4pe6$\"a\u001b?\u0011\u00051LhBA7w\u001d\tqGO\u0004\u0002pg:\u0011\u0001O\u001d\b\u0003\u0003FL\u0011AK\u0005\u0003Q%J!AJ\u0014\n\u0005U,\u0013aA:rY&\u0011q\u000f_\u0001\ba\u0006\u001c7.Y4f\u0015\t)X%\u0003\u0002{w\nIA)\u0019;b\rJ\fW.\u001a\u0006\u0003obDQ!`\u0005A\u0002y\fq\u0001Z1uCN,G\u000fM\u0002\u0000\u0003\u0017\u0001b!!\u0001\u0002\u0004\u0005\u001dQ\"\u0001=\n\u0007\u0005\u0015\u0001PA\u0004ECR\f7/\u001a;\u0011\t\u0005%\u00111\u0002\u0007\u0001\t-\ti\u0001`A\u0001\u0002\u0003\u0015\t!a\u0004\u0003\u0007}##'\u0005\u0003\u0002\u0012\u0005e\u0001\u0003BA\n\u0003+i\u0011\u0001R\u0005\u0004\u0003/!%a\u0002(pi\"Lgn\u001a\t\u0005\u0003'\tY\"C\u0002\u0002\u001e\u0011\u00131!\u00118zQ\u0011IA*!\t\"\u0005\u0005\r\u0012!\u0002\u001a/a9\u0002\u0014a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005%\u0012Q\u0007\t\u0005\u0003W\t\t$\u0004\u0002\u0002.)\u0019\u0011q\u0006=\u0002\u000bQL\b/Z:\n\t\u0005M\u0012Q\u0006\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\u001c\u0015\u0001\u0007\u0011\u0011F\u0001\u0007g\u000eDW-\\1)\u0007)a%+\u0001\u0003d_BLHcA\u0019\u0002@!9\u0011\u0011I\u0006A\u0002\u0005\r\u0013!B3yiJ\f\u0007\u0003BA#\u0003\u0017j!!a\u0012\u000b\u0007\u0005%3%A\u0003qCJ\fW.\u0003\u0003\u0002N\u0005\u001d#\u0001\u0003)be\u0006lW*\u00199)\u0007-a%+\u0001\u0005u_N#(/\u001b8h)\u0005q\u0004\u0006\u0002\u0007M\u0003C\tQC]3t_24X\r\u001a$pe6,H.Y*ue&tw\r\u000b\u0003\u000e\u0019\u0006m\u0013EAA/\u0003\u0015!d\u0006\r\u00181\u00039!(/\u00198tM>\u0014X\u000eT1cK2$2a[A2\u0011\u0019ih\u00021\u0001\u0002fA\"\u0011qMA6!\u0019\t\t!a\u0001\u0002jA!\u0011\u0011BA6\t1\ti'a\u0019\u0002\u0002\u0003\u0005)\u0011AA\b\u0005\ryFeM\u0001\u0012G\",7m[\"b]R\u0013\u0018M\\:g_JlG\u0003BA:\u0003s\u0002B!a\u0005\u0002v%\u0019\u0011q\u000f#\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003oy\u0001\u0019AA\u0015\u0003\u00159(/\u001b;f+\t\ty\bE\u00028\u0003\u0003K1!a!9\u0005!iEj\u0016:ji\u0016\u0014\b\u0006\u0002\tM\u0003CA3\u0001\u0001'S\u00035\u0011fi\u001c:nk2\fWj\u001c3fYB\u0011!GE\n\b%\u0005=\u0015QSAN!\u0011\t\u0019\"!%\n\u0007\u0005MEI\u0001\u0004B]f\u0014VM\u001a\t\u0005o\u0005]\u0015'C\u0002\u0002\u001ab\u0012!\"\u0014'SK\u0006$\u0017M\u00197f!\u0011\ti*a*\u000e\u0005\u0005}%\u0002BAQ\u0003G\u000b!![8\u000b\u0005\u0005\u0015\u0016\u0001\u00026bm\u0006LA!!+\u0002 \na1+\u001a:jC2L'0\u00192mKR\u0011\u00111R\u0001\u0005e\u0016\fG-\u0006\u0002\u00022B!q'a-2\u0013\r\t)\f\u000f\u0002\t\u001b2\u0013V-\u00193fe\"\"A\u0003TA\u0011\u0003\u0011aw.\u00193\u0015\u0007E\ni\f\u0003\u0004\u0002@V\u0001\rAP\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u0016\u0019\u0006\u0005\"a\u0005*G_JlW\u000f\\1N_\u0012,Gn\u0016:ji\u0016\u00148c\u0001\f\u0002\u0000\u0005A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0002L\u0006=\u0007cAAg-5\t!\u0003\u0003\u0004\u0002Hb\u0001\r!M\u0001\tg\u00064X-S7qYR!\u00111OAk\u0011\u0019\ty,\u0007a\u0001}\t\u0019\"KR8s[Vd\u0017-T8eK2\u0014V-\u00193feN\u0019!$!-\u0015\u0005\u0005u\u0007cAAg5\u0005I1\r\\1tg:\u000bW.Z\u000b\u0003\u0003G\u0004B!!:\u0002l6\u0011\u0011q\u001d\u0006\u0005\u0003S\f\u0019+\u0001\u0003mC:<\u0017bA%\u0002h\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0015\u0007E\n\t\u0010\u0003\u0004\u0002@z\u0001\rAP\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003o\u0004B!!:\u0002z&!\u00111`At\u0005\u0019y%M[3di\"\"!\u0003TA\u0011Q\u0011\tB*!\t"
)
public class RFormulaModel extends Model implements RFormulaBase, MLWritable {
   private final String uid;
   private final ResolvedRFormula resolvedFormula;
   private final PipelineModel pipelineModel;
   private Param formula;
   private BooleanParam forceIndexLabel;
   private Param handleInvalid;
   private Param stringIndexerOrderType;
   private Param labelCol;
   private Param featuresCol;

   public static RFormulaModel load(final String path) {
      return RFormulaModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RFormulaModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getFormula() {
      return RFormulaBase.getFormula$(this);
   }

   public boolean getForceIndexLabel() {
      return RFormulaBase.getForceIndexLabel$(this);
   }

   public String getStringIndexerOrderType() {
      return RFormulaBase.getStringIndexerOrderType$(this);
   }

   public boolean hasLabelCol(final StructType schema) {
      return RFormulaBase.hasLabelCol$(this, schema);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public Param formula() {
      return this.formula;
   }

   public BooleanParam forceIndexLabel() {
      return this.forceIndexLabel;
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public final Param stringIndexerOrderType() {
      return this.stringIndexerOrderType;
   }

   public void org$apache$spark$ml$feature$RFormulaBase$_setter_$formula_$eq(final Param x$1) {
      this.formula = x$1;
   }

   public void org$apache$spark$ml$feature$RFormulaBase$_setter_$forceIndexLabel_$eq(final BooleanParam x$1) {
      this.forceIndexLabel = x$1;
   }

   public void org$apache$spark$ml$feature$RFormulaBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public final void org$apache$spark$ml$feature$RFormulaBase$_setter_$stringIndexerOrderType_$eq(final Param x$1) {
      this.stringIndexerOrderType = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
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

   public ResolvedRFormula resolvedFormula() {
      return this.resolvedFormula;
   }

   public PipelineModel pipelineModel() {
      return this.pipelineModel;
   }

   public Dataset transform(final Dataset dataset) {
      this.checkCanTransform(dataset.schema());
      return this.transformLabel(this.pipelineModel().transform(dataset));
   }

   public StructType transformSchema(final StructType schema) {
      this.checkCanTransform(schema);
      StructType withFeatures = this.pipelineModel().transformSchema(schema);
      if (!this.resolvedFormula().label().isEmpty() && !this.hasLabelCol(withFeatures)) {
         if (SchemaUtils$.MODULE$.checkSchemaFieldExist(schema, this.resolvedFormula().label())) {
            DataType var6 = SchemaUtils$.MODULE$.getSchemaFieldType(schema, this.resolvedFormula().label());
            boolean nullable = !(var6 instanceof NumericType ? true : .MODULE$.equals(var6));
            return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])withFeatures.fields()), new StructField((String)this.$(this.labelCol()), org.apache.spark.sql.types.DoubleType..MODULE$, nullable, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
         } else {
            return withFeatures;
         }
      } else {
         return withFeatures;
      }
   }

   public RFormulaModel copy(final ParamMap extra) {
      RFormulaModel copied = (RFormulaModel)(new RFormulaModel(this.uid(), this.resolvedFormula(), this.pipelineModel())).setParent(this.parent());
      return (RFormulaModel)this.copyValues(copied, extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "RFormulaModel: uid=" + var10000 + ", resolvedFormula=" + this.resolvedFormula();
   }

   public String resolvedFormulaString() {
      return this.resolvedFormula().toString();
   }

   private Dataset transformLabel(final Dataset dataset) {
      String labelName = this.resolvedFormula().label();
      if (!labelName.isEmpty() && !this.hasLabelCol(dataset.schema())) {
         if (SchemaUtils$.MODULE$.checkSchemaFieldExist(dataset.schema(), labelName)) {
            DataType var5 = SchemaUtils$.MODULE$.getSchemaFieldType(dataset.schema(), labelName);
            if (var5 instanceof NumericType ? true : .MODULE$.equals(var5)) {
               return dataset.withColumn((String)this.$(this.labelCol()), dataset.apply(labelName).cast(org.apache.spark.sql.types.DoubleType..MODULE$));
            } else {
               throw new IllegalArgumentException("Unsupported type for label: " + var5);
            }
         } else {
            return dataset.toDF();
         }
      } else {
         return dataset.toDF();
      }
   }

   private void checkCanTransform(final StructType schema) {
      scala.Predef..MODULE$.require(!SchemaUtils$.MODULE$.checkSchemaFieldExist(schema, (String)this.$(this.featuresCol())), () -> "Features column already exists.");
      scala.Predef..MODULE$.require(!SchemaUtils$.MODULE$.checkSchemaFieldExist(schema, (String)this.$(this.labelCol())) || SchemaUtils$.MODULE$.getSchemaFieldType(schema, (String)this.$(this.labelCol())) instanceof NumericType, () -> "Label column already exists and is not of type " + org.apache.spark.sql.types.NumericType..MODULE$.simpleString() + ".");
   }

   public MLWriter write() {
      return new RFormulaModelWriter(this);
   }

   public RFormulaModel(final String uid, final ResolvedRFormula resolvedFormula, final PipelineModel pipelineModel) {
      this.uid = uid;
      this.resolvedFormula = resolvedFormula;
      this.pipelineModel = pipelineModel;
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasHandleInvalid.$init$(this);
      RFormulaBase.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public RFormulaModel() {
      this("", (ResolvedRFormula)null, (PipelineModel)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class RFormulaModelWriter extends MLWriter {
      private final RFormulaModel instance;

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(this.instance.resolvedFormula(), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RFormulaModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.feature.ResolvedRFormula").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).repartition(1).write().parquet(dataPath);
         String pmPath = (new Path(path, "pipelineModel")).toString();
         this.instance.pipelineModel().save(pmPath);
      }

      public RFormulaModelWriter(final RFormulaModel instance) {
         this.instance = instance;
      }
   }

   private static class RFormulaModelReader extends MLReader {
      private final String className = RFormulaModel.class.getName();

      private String className() {
         return this.className;
      }

      public RFormulaModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("label", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"terms", "hasIntercept"}))).head();
         String label = data.getString(0);
         Seq terms = (Seq)data.getSeq(1).map((x$3) -> x$3.toSeq());
         boolean hasIntercept = data.getBoolean(2);
         ResolvedRFormula resolvedRFormula = new ResolvedRFormula(label, terms, hasIntercept);
         String pmPath = (new Path(path, "pipelineModel")).toString();
         PipelineModel pipelineModel = PipelineModel$.MODULE$.load(pmPath);
         RFormulaModel model = new RFormulaModel(metadata.uid(), resolvedRFormula, pipelineModel);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public RFormulaModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
