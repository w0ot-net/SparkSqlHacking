package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.PredictionModel;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamMap$;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasRawPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.StringOps.;
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
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t%d\u0001B\u0012%\u0005=B\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0005\u0011\u0005\t/\u0002\u0011\t\u0011)A\u0005\u0003\"I\u0011\f\u0001BC\u0002\u0013\u0005aE\u0017\u0005\tG\u0002\u0011\t\u0011)A\u00057\"AA\r\u0001BC\u0002\u0013\u0005Q\r\u0003\u0005p\u0001\t\u0005\t\u0015!\u0003g\u0011!\tY\u0001\u0001C\u0001M\u00055\u0001\"CA\u0019\u0001\t\u0007I\u0011AA\u001a\u0011!\t\t\u0005\u0001Q\u0001\n\u0005U\u0002\"CA#\u0001\t\u0007I\u0011AA\u001a\u0011!\tI\u0005\u0001Q\u0001\n\u0005U\u0002bBA'\u0001\u0011\u0005\u0011q\n\u0005\b\u0003;\u0002A\u0011AA0\u0011\u001d\t)\u0007\u0001C\u0001\u0003OBq!!\u001c\u0001\t\u0003\ny\u0007C\u0004\u0002~\u0001!\t%a \t\u000f\u0005e\u0006\u0001\"\u0011\u0002<\"9\u00111\u001b\u0001\u0005B\u0005U\u0007bBAp\u0001\u0011\u0005\u0013\u0011]\u0004\b\u0003W$\u0003\u0012AAw\r\u0019\u0019C\u0005#\u0001\u0002p\"9\u00111B\u000b\u0005\u0002\t5\u0001b\u0002B\b+\u0011\u0005#\u0011\u0003\u0005\b\u00057)B\u0011\tB\u000f\r\u001d\u0011)#\u0006\u0001\u0016\u0005OA\u0011B!\u000b\u001a\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u000f\u0005-\u0011\u0004\"\u0001\u0003,!9!1G\r\u0005R\tUbA\u0002B +\u0011\u0011\t\u0005C\u0004\u0002\fu!\tAa\u0011\t\u0013\t\u001dSD1A\u0005\n\t%\u0003\u0002\u0003B+;\u0001\u0006IAa\u0013\t\u000f\tmQ\u0004\"\u0011\u0003X!I!1L\u000b\u0002\u0002\u0013%!Q\f\u0002\u000f\u001f:,gk\u001d*fgRlu\u000eZ3m\u0015\t)c%\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u000b\u0005\u001dB\u0013AA7m\u0015\tI#&A\u0003ta\u0006\u00148N\u0003\u0002,Y\u00051\u0011\r]1dQ\u0016T\u0011!L\u0001\u0004_J<7\u0001A\n\u0005\u0001A2\u0014\bE\u00022eQj\u0011AJ\u0005\u0003g\u0019\u0012Q!T8eK2\u0004\"!\u000e\u0001\u000e\u0003\u0011\u0002\"!N\u001c\n\u0005a\"#aD(oKZ\u001b(+Z:u!\u0006\u0014\u0018-\\:\u0011\u0005ijT\"A\u001e\u000b\u0005q2\u0013\u0001B;uS2L!AP\u001e\u0003\u00155cuK]5uC\ndW-A\u0002vS\u0012,\u0012!\u0011\t\u0003\u0005.s!aQ%\u0011\u0005\u0011;U\"A#\u000b\u0005\u0019s\u0013A\u0002\u001fs_>$hHC\u0001I\u0003\u0015\u00198-\u00197b\u0013\tQu)\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&HQ\r\tq*\u0016\t\u0003!Nk\u0011!\u0015\u0006\u0003%\"\n!\"\u00198o_R\fG/[8o\u0013\t!\u0016KA\u0003TS:\u001cW-I\u0001W\u0003\u0015\td\u0006\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\tyU+A\u0007mC\n,G.T3uC\u0012\fG/Y\u000b\u00027B\u0011A,Y\u0007\u0002;*\u0011alX\u0001\u0006if\u0004Xm\u001d\u0006\u0003A\"\n1a]9m\u0013\t\u0011WL\u0001\u0005NKR\fG-\u0019;b\u00039a\u0017MY3m\u001b\u0016$\u0018\rZ1uC\u0002\na!\\8eK2\u001cX#\u000141\u0005\u001dl\u0007c\u00015jW6\tq)\u0003\u0002k\u000f\n)\u0011I\u001d:bsB\u0011A.\u001c\u0007\u0001\t%qg!!A\u0001\u0002\u000b\u0005\u0011OA\u0002`IE\nq!\\8eK2\u001c\b\u0005K\u0002\u0007\u001fV\u000b\"A];\u0011\u0005!\u001c\u0018B\u0001;H\u0005\u001dqu\u000e\u001e5j]\u001e\u0004DA\u001e>\u0002\u0006A)Qg^=\u0002\u0004%\u0011\u0001\u0010\n\u0002\u0014\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\t\u0003Yj$\u0011b\u001f?\u0002\u0002\u0003\u0005)\u0011A?\u0003\u0007}##\u0007B\u0005o\r\u0005\u0005\u0019\u0011!B\u0001cF\u0011!O \t\u0003Q~L1!!\u0001H\u0005\r\te.\u001f\t\u0004Y\u0006\u0015AACA\u0004y\u0006\u0005\t\u0011!B\u0001{\n\u0019q\fJ\u001a)\u0007\u0015yU+\u0001\u0004=S:LGO\u0010\u000b\bi\u0005=\u00111CA\u000b\u0011\u0015yt\u00011\u0001BQ\u0011\tyaT+\t\u000be;\u0001\u0019A.\t\r\u0011<\u0001\u0019AA\fa\u0011\tI\"!\b\u0011\t!L\u00171\u0004\t\u0004Y\u0006uAa\u00038\u0002\u0016\u0005\u0005\t\u0011!B\u0001\u0003?\t2A]A\u0011a\u0019\t\u0019#a\n\u0002.A1Qg^A\u0013\u0003W\u00012\u0001\\A\u0014\t)Y\u0018\u0011FA\u0001\u0002\u0003\u0015\t! \u0003\f]\u0006U\u0011\u0011aA\u0001\u0006\u0003\ty\u0002E\u0002m\u0003[!1\"a\u0002\u0002*\u0005\u0005\t\u0011!B\u0001{\"\"\u0011QC(V\u0003)qW/\\\"mCN\u001cXm]\u000b\u0003\u0003k\u00012\u0001[A\u001c\u0013\r\tId\u0012\u0002\u0004\u0013:$\b\u0006\u0002\u0005P\u0003{\t#!a\u0010\u0002\u000bIrCG\f\u0019\u0002\u00179,Xn\u00117bgN,7\u000f\t\u0015\u0005\u0013=\u000bi$A\u0006ok64U-\u0019;ve\u0016\u001c\b\u0006\u0002\u0006P\u0003{\tAB\\;n\r\u0016\fG/\u001e:fg\u0002BCaC(\u0002>\u0005q1/\u001a;GK\u0006$XO]3t\u0007>dG\u0003BA)\u0003'j\u0011\u0001\u0001\u0005\u0007\u0003+b\u0001\u0019A!\u0002\u000bY\fG.^3)\t1y\u0015\u0011L\u0011\u0003\u00037\nQA\r\u00182]A\n\u0001c]3u!J,G-[2uS>t7i\u001c7\u0015\t\u0005E\u0013\u0011\r\u0005\u0007\u0003+j\u0001\u0019A!)\t5y\u0015\u0011L\u0001\u0014g\u0016$(+Y<Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\u000b\u0005\u0003#\nI\u0007\u0003\u0004\u0002V9\u0001\r!\u0011\u0015\u0005\u001d=\u000bi$A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\t\t(a\u001e\u0011\u0007q\u000b\u0019(C\u0002\u0002vu\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\tIh\u0004a\u0001\u0003c\naa]2iK6\f\u0007fA\bP+\u0006IAO]1og\u001a|'/\u001c\u000b\u0005\u0003\u0003\u000by\n\u0005\u0003\u0002\u0004\u0006ee\u0002BAC\u0003+sA!a\"\u0002\u0014:!\u0011\u0011RAI\u001d\u0011\tY)a$\u000f\u0007\u0011\u000bi)C\u0001.\u0013\tYC&\u0003\u0002*U%\u0011\u0001\rK\u0005\u0004\u0003/{\u0016a\u00029bG.\fw-Z\u0005\u0005\u00037\u000biJA\u0005ECR\fgI]1nK*\u0019\u0011qS0\t\u000f\u0005\u0005\u0006\u00031\u0001\u0002$\u00069A-\u0019;bg\u0016$\b\u0007BAS\u0003_\u0003b!a*\u0002*\u00065V\"A0\n\u0007\u0005-vLA\u0004ECR\f7/\u001a;\u0011\u00071\fy\u000bB\u0006\u00022\u0006}\u0015\u0011!A\u0001\u0006\u0003i(aA0%i!\"\u0001cTA[C\t\t9,A\u00033]Ar\u0003'\u0001\u0003d_BLHc\u0001\u001b\u0002>\"9\u0011qX\tA\u0002\u0005\u0005\u0017!B3yiJ\f\u0007\u0003BAb\u0003\u0013l!!!2\u000b\u0007\u0005\u001dg%A\u0003qCJ\fW.\u0003\u0003\u0002L\u0006\u0015'\u0001\u0003)be\u0006lW*\u00199)\tEy\u0015qZ\u0011\u0003\u0003#\fQ!\r\u00185]E\nQa\u001e:ji\u0016,\"!a6\u0011\u0007i\nI.C\u0002\u0002\\n\u0012\u0001\"\u0014'Xe&$XM\u001d\u0015\u0005%=\u000b),\u0001\u0005u_N#(/\u001b8h)\u0005\t\u0005\u0006B\nP\u0003K\f#!a:\u0002\u000bMr\u0003G\f\u0019)\u0007\u0001yU+\u0001\bP]\u001646OU3ti6{G-\u001a7\u0011\u0005U*2cB\u000b\u0002r\u0006]\u0018Q \t\u0004Q\u0006M\u0018bAA{\u000f\n1\u0011I\\=SK\u001a\u0004BAOA}i%\u0019\u00111`\u001e\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002\u0000\n%QB\u0001B\u0001\u0015\u0011\u0011\u0019A!\u0002\u0002\u0005%|'B\u0001B\u0004\u0003\u0011Q\u0017M^1\n\t\t-!\u0011\u0001\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003[\fAA]3bIV\u0011!1\u0003\t\u0005u\tUA'C\u0002\u0003\u0018m\u0012\u0001\"\u0014'SK\u0006$WM\u001d\u0015\u0005/=\u000b),\u0001\u0003m_\u0006$Gc\u0001\u001b\u0003 !1!\u0011\u0005\rA\u0002\u0005\u000bA\u0001]1uQ\"\"\u0001dTA[\u0005Qye.\u001a,t%\u0016\u001cH/T8eK2<&/\u001b;feN\u0019\u0011$a6\u0002\u0011%t7\u000f^1oG\u0016$BA!\f\u00032A\u0019!qF\r\u000e\u0003UAaA!\u000b\u001c\u0001\u0004!\u0014\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\t]\"Q\b\t\u0004Q\ne\u0012b\u0001B\u001e\u000f\n!QK\\5u\u0011\u0019\u0011\t\u0003\ba\u0001\u0003\n!rJ\\3WgJ+7\u000f^'pI\u0016d'+Z1eKJ\u001c2!\bB\n)\t\u0011)\u0005E\u0002\u00030u\t\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\t-\u0003\u0003\u0002B'\u0005'j!Aa\u0014\u000b\t\tE#QA\u0001\u0005Y\u0006tw-C\u0002M\u0005\u001f\n!b\u00197bgNt\u0015-\\3!)\r!$\u0011\f\u0005\u0007\u0005C\t\u0003\u0019A!\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t}\u0003\u0003\u0002B'\u0005CJAAa\u0019\u0003P\t1qJ\u00196fGRDC!F(\u00026\"\"AcTA[\u0001"
)
public final class OneVsRestModel extends Model implements OneVsRestParams, MLWritable {
   private final String uid;
   private final Metadata labelMetadata;
   private final ClassificationModel[] models;
   private final int numClasses;
   private final int numFeatures;
   private Param classifier;
   private Param weightCol;
   private Param rawPredictionCol;
   private Param predictionCol;
   private Param featuresCol;
   private Param labelCol;

   public static OneVsRestModel load(final String path) {
      return OneVsRestModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return OneVsRestModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
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

   public Metadata labelMetadata() {
      return this.labelMetadata;
   }

   public ClassificationModel[] models() {
      return this.models;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public OneVsRestModel setFeaturesCol(final String value) {
      return (OneVsRestModel)this.set(this.featuresCol(), value);
   }

   public OneVsRestModel setPredictionCol(final String value) {
      return (OneVsRestModel)this.set(this.predictionCol(), value);
   }

   public OneVsRestModel setRawPredictionCol(final String value) {
      return (OneVsRestModel)this.set(this.rawPredictionCol(), value);
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema, false, this.getClassifier().featuresDataType());
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumValues(outputSchema, (String)this.$(this.predictionCol()), this.numClasses());
      }

      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.rawPredictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.rawPredictionCol()), this.numClasses());
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      if (this.getPredictionCol().isEmpty() && this.getRawPredictionCol().isEmpty()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": OneVsRestModel.transform() does nothing "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return dataset.toDF();
      } else {
         boolean isProbModel;
         String tmpRawPredName;
         String accColName;
         Dataset newDataset;
         Column[] columns;
         UserDefinedFunction updateUDF;
         boolean var29;
         label42: {
            label41: {
               isProbModel = scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(this.models())) instanceof ProbabilisticClassificationModel;
               tmpRawPredName = "mbc$raw" + UUID.randomUUID().toString();
               accColName = "mbc$acc" + UUID.randomUUID().toString();
               newDataset = dataset.withColumn(accColName, org.apache.spark.sql.functions..MODULE$.lit(scala.Array..MODULE$.emptyDoubleArray()));
               columns = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])newDataset.schema().fieldNames()), (colName) -> org.apache.spark.sql.functions..MODULE$.col(colName), scala.reflect.ClassTag..MODULE$.apply(Column.class));
               functions var10000 = org.apache.spark.sql.functions..MODULE$;
               Function2 var10001 = (preds, pred) -> (double[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.doubleArrayOps(preds), BoxesRunTime.boxToDouble(pred.apply(1)), scala.reflect.ClassTag..MODULE$.Double());
               JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
               JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneVsRestModel.class.getClassLoader());

               final class $typecreator1$1 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
                  }

                  public $typecreator1$1() {
                  }
               }

               TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
               JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
               JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneVsRestModel.class.getClassLoader());

               final class $typecreator2$1 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
                  }

                  public $typecreator2$1() {
                  }
               }

               TypeTags.TypeTag var10003 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1());
               JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
               JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneVsRestModel.class.getClassLoader());

               final class $typecreator3$1 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
                  }

                  public $typecreator3$1() {
                  }
               }

               updateUDF = var10000.udf(var10001, var10002, var10003, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1()));
               if (!dataset.isStreaming()) {
                  StorageLevel var28 = dataset.storageLevel();
                  StorageLevel var16 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
                  if (var28 == null) {
                     if (var16 == null) {
                        break label41;
                     }
                  } else if (var28.equals(var16)) {
                     break label41;
                  }
               }

               var29 = false;
               break label42;
            }

            var29 = true;
         }

         boolean handlePersistence = var29;
         if (handlePersistence) {
            newDataset.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         } else {
            BoxedUnit var30 = BoxedUnit.UNIT;
         }

         Dataset aggregatedDataset = (Dataset)scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps(this.models()), newDataset, (x0$1, x1$1) -> {
            Tuple2 var9 = new Tuple2(x0$1, x1$1);
            if (var9 != null) {
               Dataset df = (Dataset)var9._1();
               ClassificationModel model = (ClassificationModel)var9._2();
               ClassificationModel tmpModel = (ClassificationModel)model.copy(ParamMap$.MODULE$.empty());
               tmpModel.setFeaturesCol((String)this.$(this.featuresCol()));
               tmpModel.setRawPredictionCol(tmpRawPredName);
               tmpModel.setPredictionCol("");
               if (isProbModel) {
                  ((ProbabilisticClassificationModel)tmpModel).setProbabilityCol("");
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               return tmpModel.transform(df).withColumn(accColName, updateUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(accColName), org.apache.spark.sql.functions..MODULE$.col(tmpRawPredName)})))).select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(columns).toImmutableArraySeq());
            } else {
               throw new MatchError(var9);
            }
         });
         if (handlePersistence) {
            newDataset.unpersist();
         } else {
            BoxedUnit var31 = BoxedUnit.UNIT;
         }

         Seq predictionColNames = (Seq)scala.package..MODULE$.Seq().empty();
         Seq predictionColumns = (Seq)scala.package..MODULE$.Seq().empty();
         if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(this.getRawPredictionCol()))) {
            functions var32 = org.apache.spark.sql.functions..MODULE$;
            Function1 var34 = (preds) -> org.apache.spark.ml.linalg.Vectors..MODULE$.dense(preds);
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneVsRestModel.class.getClassLoader());

            final class $typecreator4$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator4$1() {
               }
            }

            TypeTags.TypeTag var36 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1());
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneVsRestModel.class.getClassLoader());

            final class $typecreator5$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
               }

               public $typecreator5$1() {
               }
            }

            UserDefinedFunction rawPredictionUDF = var32.udf(var34, var36, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()));
            predictionColNames = (Seq)predictionColNames.$colon$plus(this.getRawPredictionCol());
            predictionColumns = (Seq)predictionColumns.$colon$plus(rawPredictionUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(accColName)}))).as((String)this.$(this.rawPredictionCol()), outputSchema.apply((String)this.$(this.rawPredictionCol())).metadata()));
         }

         if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(this.getPredictionCol()))) {
            functions var33 = org.apache.spark.sql.functions..MODULE$;
            Function1 var35 = (preds) -> BoxesRunTime.boxToDouble($anonfun$transform$6(preds));
            TypeTags.TypeTag var37 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneVsRestModel.class.getClassLoader());

            final class $typecreator6$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
               }

               public $typecreator6$1() {
               }
            }

            UserDefinedFunction labelUDF = var33.udf(var35, var37, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator6$1()));
            predictionColNames = (Seq)predictionColNames.$colon$plus(this.getPredictionCol());
            predictionColumns = (Seq)predictionColumns.$colon$plus(labelUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(accColName)}))).as(this.getPredictionCol(), this.labelMetadata()));
         }

         return aggregatedDataset.withColumns(predictionColNames, predictionColumns).drop(accColName);
      }
   }

   public OneVsRestModel copy(final ParamMap extra) {
      OneVsRestModel copied = new OneVsRestModel(this.uid(), this.labelMetadata(), (ClassificationModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.models()), (x$2) -> (ClassificationModel)x$2.copy(extra), scala.reflect.ClassTag..MODULE$.apply(ClassificationModel.class)));
      return (OneVsRestModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new OneVsRestModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "OneVsRestModel: uid=" + var10000 + ", classifier=" + this.$(this.classifier()) + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$6(final double[] preds) {
      return (double)BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.doubleArrayOps(preds)).maxBy((JFunction1.mcDI.sp)(i) -> preds[i], scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
   }

   public OneVsRestModel(final String uid, final Metadata labelMetadata, final ClassificationModel[] models) {
      this.uid = uid;
      this.labelMetadata = labelMetadata;
      this.models = models;
      HasLabelCol.$init$(this);
      HasFeaturesCol.$init$(this);
      HasPredictionCol.$init$(this);
      PredictorParams.$init$(this);
      HasRawPredictionCol.$init$(this);
      ClassifierParams.$init$(this);
      HasWeightCol.$init$(this);
      OneVsRestParams.$init$(this);
      MLWritable.$init$(this);
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(models)), () -> "OneVsRestModel requires at least one model for one class");
      this.numClasses = models.length;
      this.numFeatures = ((PredictionModel)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(models))).numFeatures();
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class OneVsRestModelWriter extends MLWriter {
      private final OneVsRestModel instance;

      public void saveImpl(final String path) {
         JObject extraJson = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("labelMetadata"), this.instance.labelMetadata().json()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numClasses"), BoxesRunTime.boxToInteger(this.instance.models().length)), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$saveImpl$5(BoxesRunTime.unboxToInt(x)));
         OneVsRestParams$.MODULE$.saveImpl(path, this.instance, this.sparkSession(), new Some(extraJson));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.instance.models()), (x$3) -> (MLWritable)x$3, scala.reflect.ClassTag..MODULE$.apply(MLWritable.class))))), (x0$1) -> {
            $anonfun$saveImpl$7(path, x0$1);
            return BoxedUnit.UNIT;
         });
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$5(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final void $anonfun$saveImpl$7(final String path$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            MLWritable model = (MLWritable)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            String modelPath = (new Path(path$1, "model_" + idx)).toString();
            model.save(modelPath);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }

      public OneVsRestModelWriter(final OneVsRestModel instance) {
         this.instance = instance;
         OneVsRestParams$.MODULE$.validateParams(instance);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class OneVsRestModelReader extends MLReader {
      private final String className = OneVsRestModel.class.getName();

      private String className() {
         return this.className;
      }

      public OneVsRestModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         Tuple2 var5 = OneVsRestParams$.MODULE$.loadImpl(path, this.sparkSession(), this.className());
         if (var5 != null) {
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var5._1();
            Classifier classifier = (Classifier)var5._2();
            Tuple2 var4 = new Tuple2(metadata, classifier);
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var4._1();
            Classifier classifier = (Classifier)var4._2();
            Metadata labelMetadata = org.apache.spark.sql.types.Metadata..MODULE$.fromJson((String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "labelMetadata")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
            int numClasses = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numClasses")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
            ClassificationModel[] models = (ClassificationModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.package..MODULE$.Range().apply(0, numClasses).toArray(scala.reflect.ClassTag..MODULE$.Int())), (idx) -> $anonfun$load$1(this, path, BoxesRunTime.unboxToInt(idx)), scala.reflect.ClassTag..MODULE$.apply(ClassificationModel.class));
            OneVsRestModel ovrModel = new OneVsRestModel(metadata.uid(), labelMetadata, models);
            metadata.getAndSetParams(ovrModel, metadata.getAndSetParams$default$2());
            ovrModel.set("classifier", classifier);
            return ovrModel;
         } else {
            throw new MatchError(var5);
         }
      }

      // $FF: synthetic method
      public static final ClassificationModel $anonfun$load$1(final OneVsRestModelReader $this, final String path$2, final int idx) {
         String modelPath = (new Path(path$2, "model_" + idx)).toString();
         return (ClassificationModel)DefaultParamsReader$.MODULE$.loadParamsInstance(modelPath, $this.sparkSession());
      }

      public OneVsRestModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
