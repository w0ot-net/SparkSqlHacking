package org.apache.spark.ml.clustering;

import java.io.IOException;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.clustering.PowerIterationClusteringModel;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.Some;
import scala.collection.immutable.;
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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]d\u0001\u0002\n\u0014\u0001yA\u0001b\f\u0001\u0003\u0006\u0004%\t\u0005\r\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005c!1q\t\u0001C\u0001'!CQa\u0012\u0001\u0005\u00021CQA\u0014\u0001\u0005\u0002=CQa\u0016\u0001\u0005\u0002aCQa\u0017\u0001\u0005\u0002qCQa\u0018\u0001\u0005\u0002\u0001DQa\u0019\u0001\u0005\u0002\u0011DQa\u001a\u0001\u0005\u0002!DQa\u001b\u0001\u0005\u00021Dq!a\t\u0001\t\u0003\n)cB\u0004\u0002<MA\t!!\u0010\u0007\rI\u0019\u0002\u0012AA \u0011\u00199e\u0002\"\u0001\u0002X!9\u0011\u0011\f\b\u0005B\u0005m\u0003\"CA2\u001d\u0005\u0005I\u0011BA3\u0005a\u0001vn^3s\u0013R,'/\u0019;j_:\u001cE.^:uKJLgn\u001a\u0006\u0003)U\t!b\u00197vgR,'/\u001b8h\u0015\t1r#\u0001\u0002nY*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001yR%\u000b\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019:S\"A\n\n\u0005!\u001a\"A\b)po\u0016\u0014\u0018\n^3sCRLwN\\\"mkN$XM]5oOB\u000b'/Y7t!\tQS&D\u0001,\u0015\taS#\u0001\u0003vi&d\u0017B\u0001\u0018,\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005\t\u0004C\u0001\u001a:\u001d\t\u0019t\u0007\u0005\u00025C5\tQG\u0003\u00027;\u00051AH]8pizJ!\u0001O\u0011\u0002\rA\u0013X\rZ3g\u0013\tQ4H\u0001\u0004TiJLgn\u001a\u0006\u0003q\u0005B3!A\u001fD!\tq\u0014)D\u0001@\u0015\t\u0001u#\u0001\u0006b]:|G/\u0019;j_:L!AQ \u0003\u000bMKgnY3\"\u0003\u0011\u000bQA\r\u00185]A\nA!^5eA!\u001a!!P\"\u0002\rqJg.\u001b;?)\tI%\n\u0005\u0002'\u0001!)qf\u0001a\u0001c!\u001a!*P\"\u0015\u0003%C3\u0001B\u001fD\u0003\u0011\u0019X\r^&\u0015\u0005A\u000bV\"\u0001\u0001\t\u000bI+\u0001\u0019A*\u0002\u000bY\fG.^3\u0011\u0005\u0001\"\u0016BA+\"\u0005\rIe\u000e\u001e\u0015\u0004\u000bu\u001a\u0015aC:fi&s\u0017\u000e^'pI\u0016$\"\u0001U-\t\u000bI3\u0001\u0019A\u0019)\u0007\u0019i4)\u0001\u0006tKRl\u0015\r_%uKJ$\"\u0001U/\t\u000bI;\u0001\u0019A*)\u0007\u001di4)A\u0005tKR\u001c&oY\"pYR\u0011\u0001+\u0019\u0005\u0006%\"\u0001\r!\r\u0015\u0004\u0011u\u001a\u0015!C:fi\u0012\u001bHoQ8m)\t\u0001V\rC\u0003S\u0013\u0001\u0007\u0011\u0007K\u0002\n{\r\u000bAb]3u/\u0016Lw\r\u001b;D_2$\"\u0001U5\t\u000bIS\u0001\u0019A\u0019)\u0007)i4)\u0001\bbgNLwM\\\"mkN$XM]:\u0015\u00055t\bC\u00018|\u001d\ty\u0007P\u0004\u0002qm:\u0011\u0011/\u001e\b\u0003eRt!\u0001N:\n\u0003qI!AG\u000e\n\u0005aI\u0012BA<\u0018\u0003\r\u0019\u0018\u000f\\\u0005\u0003sj\fq\u0001]1dW\u0006<WM\u0003\u0002x/%\u0011A0 \u0002\n\t\u0006$\u0018M\u0012:b[\u0016T!!\u001f>\t\r}\\\u0001\u0019AA\u0001\u0003\u001d!\u0017\r^1tKR\u0004D!a\u0001\u0002\u0010A1\u0011QAA\u0004\u0003\u0017i\u0011A_\u0005\u0004\u0003\u0013Q(a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003\u001b\ty\u0001\u0004\u0001\u0005\u0017\u0005Ea0!A\u0001\u0002\u000b\u0005\u00111\u0003\u0002\u0004?\u0012\n\u0014\u0003BA\u000b\u00037\u00012\u0001IA\f\u0013\r\tI\"\t\u0002\b\u001d>$\b.\u001b8h!\r\u0001\u0013QD\u0005\u0004\u0003?\t#aA!os\"\u001a1\"P\"\u0002\t\r|\u0007/\u001f\u000b\u0004\u0013\u0006\u001d\u0002bBA\u0015\u0019\u0001\u0007\u00111F\u0001\u0006Kb$(/\u0019\t\u0005\u0003[\t\u0019$\u0004\u0002\u00020)\u0019\u0011\u0011G\u000b\u0002\u000bA\f'/Y7\n\t\u0005U\u0012q\u0006\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001aA\"P\")\u0007\u0001i4)\u0001\rQ_^,'/\u0013;fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:j]\u001e\u0004\"A\n\b\u0014\r9y\u0012\u0011IA$!\u0011Q\u00131I%\n\u0007\u0005\u00153FA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012\f'\r\\3\u0011\t\u0005%\u00131K\u0007\u0003\u0003\u0017RA!!\u0014\u0002P\u0005\u0011\u0011n\u001c\u0006\u0003\u0003#\nAA[1wC&!\u0011QKA&\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\ti$\u0001\u0003m_\u0006$GcA%\u0002^!1\u0011q\f\tA\u0002E\nA\u0001]1uQ\"\u001a\u0001#P\"\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u001d\u0004\u0003BA5\u0003_j!!a\u001b\u000b\t\u00055\u0014qJ\u0001\u0005Y\u0006tw-\u0003\u0003\u0002r\u0005-$AB(cU\u0016\u001cG\u000fK\u0002\u000f{\rC3!D\u001fD\u0001"
)
public class PowerIterationClustering implements PowerIterationClusteringParams, DefaultParamsWritable {
   private final String uid;
   private IntParam k;
   private Param initMode;
   private Param srcCol;
   private Param dstCol;
   private Param weightCol;
   private IntParam maxIter;
   private Param[] params;
   private ParamMap paramMap;
   private ParamMap defaultParamMap;
   private volatile boolean bitmap$0;

   public static PowerIterationClustering load(final String path) {
      return PowerIterationClustering$.MODULE$.load(path);
   }

   public static MLReader read() {
      return PowerIterationClustering$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return PowerIterationClusteringParams.getK$(this);
   }

   public String getInitMode() {
      return PowerIterationClusteringParams.getInitMode$(this);
   }

   public String getSrcCol() {
      return PowerIterationClusteringParams.getSrcCol$(this);
   }

   public String getDstCol() {
      return PowerIterationClusteringParams.getDstCol$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
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

   public final IntParam k() {
      return this.k;
   }

   public final Param initMode() {
      return this.initMode;
   }

   public Param srcCol() {
      return this.srcCol;
   }

   public Param dstCol() {
      return this.dstCol;
   }

   public final void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$initMode_$eq(final Param x$1) {
      this.initMode = x$1;
   }

   public void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$srcCol_$eq(final Param x$1) {
      this.srcCol = x$1;
   }

   public void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$dstCol_$eq(final Param x$1) {
      this.dstCol = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
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

   public PowerIterationClustering setK(final int value) {
      return (PowerIterationClustering)this.set((Param)this.k(), BoxesRunTime.boxToInteger(value));
   }

   public PowerIterationClustering setInitMode(final String value) {
      return (PowerIterationClustering)this.set((Param)this.initMode(), value);
   }

   public PowerIterationClustering setMaxIter(final int value) {
      return (PowerIterationClustering)this.set((Param)this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public PowerIterationClustering setSrcCol(final String value) {
      return (PowerIterationClustering)this.set((Param)this.srcCol(), value);
   }

   public PowerIterationClustering setDstCol(final String value) {
      return (PowerIterationClustering)this.set((Param)this.dstCol(), value);
   }

   public PowerIterationClustering setWeightCol(final String value) {
      return (PowerIterationClustering)this.set((Param)this.weightCol(), value);
   }

   public Dataset assignClusters(final Dataset dataset) {
      SparkSession spark;
      label14: {
         spark = dataset.sparkSession();
         SchemaUtils$.MODULE$.checkColumnTypes(dataset.schema(), (String)this.$(this.srcCol()), new .colon.colon(org.apache.spark.sql.types.IntegerType..MODULE$, new .colon.colon(org.apache.spark.sql.types.LongType..MODULE$, scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
         SchemaUtils$.MODULE$.checkColumnTypes(dataset.schema(), (String)this.$(this.dstCol()), new .colon.colon(org.apache.spark.sql.types.IntegerType..MODULE$, new .colon.colon(org.apache.spark.sql.types.LongType..MODULE$, scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
         Option var4 = this.get(this.weightCol());
         if (var4 instanceof Some var5) {
            String w = (String)var5.value();
            if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(w))) {
               SchemaUtils$.MODULE$.checkNumericType(dataset.schema(), w, SchemaUtils$.MODULE$.checkNumericType$default$3());
               BoxedUnit var14 = BoxedUnit.UNIT;
               break label14;
            }
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      Dataset var15 = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.srcCol())).cast(org.apache.spark.sql.types.LongType..MODULE$), org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.dstCol())).cast(org.apache.spark.sql.types.LongType..MODULE$), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))})));
      SQLImplicits var10001 = spark.implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PowerIterationClustering.class.getClassLoader());

      final class $typecreator5$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new .colon.colon($m$untyped.staticClass("scala.Long").asType().toTypeConstructor(), new .colon.colon($m$untyped.staticClass("scala.Long").asType().toTypeConstructor(), new .colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))));
         }

         public $typecreator5$1() {
         }
      }

      RDD rdd = var15.as(var10001.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).rdd();
      org.apache.spark.mllib.clustering.PowerIterationClustering algorithm = (new org.apache.spark.mllib.clustering.PowerIterationClustering()).setK(BoxesRunTime.unboxToInt(this.$(this.k()))).setInitializationMode((String)this.$(this.initMode())).setMaxIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter())));
      PowerIterationClusteringModel model = algorithm.run(rdd);
      SQLImplicits var16 = spark.implicits();
      RDD var17 = model.assignments();
      SQLImplicits var10002 = spark.implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PowerIterationClustering.class.getClassLoader());

      final class $typecreator15$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("org.apache.spark.mllib.clustering").asModule().moduleClass()), $m$untyped.staticModule("org.apache.spark.mllib.clustering.PowerIterationClustering")), $m$untyped.staticClass("org.apache.spark.mllib.clustering.PowerIterationClustering.Assignment"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator15$1() {
         }
      }

      return var16.rddToDatasetHolder(var17, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator15$1()))).toDF();
   }

   public PowerIterationClustering copy(final ParamMap extra) {
      return (PowerIterationClustering)this.defaultCopy(extra);
   }

   public PowerIterationClustering(final String uid) {
      this.uid = uid;
      Identifiable.$init$(this);
      Params.$init$(this);
      HasMaxIter.$init$(this);
      HasWeightCol.$init$(this);
      PowerIterationClusteringParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public PowerIterationClustering() {
      this(Identifiable$.MODULE$.randomUID("PowerIterationClustering"));
   }
}
