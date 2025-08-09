package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.param.Param$;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.rdd.OrderedRDDFunctions;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.ArrayOps.;
import scala.collection.immutable.ArraySeq;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

public final class EnsembleModelReadWrite$ {
   public static final EnsembleModelReadWrite$ MODULE$ = new EnsembleModelReadWrite$();

   public void saveImpl(final Params instance, final String path, final SparkSession sparkSession, final JObject extraMetadata) {
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, sparkSession, new Some(extraMetadata));
      Tuple3[] treesMetadataWeights = (Tuple3[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(((TreeEnsembleModel)instance).trees()))), (x0$1) -> {
         if (x0$1 != null) {
            DecisionTreeModel tree = (DecisionTreeModel)x0$1._1();
            int treeID = x0$1._2$mcI$sp();
            return new Tuple3(BoxesRunTime.boxToInteger(treeID), DefaultParamsWriter$.MODULE$.getMetadataToSave((Params)tree, sparkSession), BoxesRunTime.boxToDouble(((TreeEnsembleModel)instance).treeWeights()[treeID]));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      String treesMetadataPath = (new Path(path, "treesMetadata")).toString();
      ArraySeq var10001 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(treesMetadataWeights).toImmutableArraySeq();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))));
         }

         public $typecreator1$1() {
         }
      }

      sparkSession.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"treeID", "metadata", "weights"}))).repartition(1).write().parquet(treesMetadataPath);
      String dataPath = (new Path(path, "data")).toString();
      int numDataParts = DecisionTreeModelReadWrite.NodeData$.MODULE$.inferNumPartitions(BoxesRunTime.unboxToLong(scala.Predef..MODULE$.wrapLongArray((long[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(((TreeEnsembleModel)instance).trees()), (x$18) -> BoxesRunTime.boxToLong($anonfun$saveImpl$2(x$18)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$)));
      SparkContext qual$1 = sparkSession.sparkContext();
      ArraySeq x$1 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(((TreeEnsembleModel)instance).trees()))).toImmutableArraySeq();
      int x$2 = qual$1.parallelize$default$2();
      RDD nodeDataRDD = qual$1.parallelize(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).flatMap((x0$2) -> {
         if (x0$2 != null) {
            DecisionTreeModel tree = (DecisionTreeModel)x0$2._1();
            int treeID = x0$2._2$mcI$sp();
            return EnsembleModelReadWrite.EnsembleNodeData$.MODULE$.build(tree, treeID);
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(EnsembleModelReadWrite.EnsembleNodeData.class));
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.tree.EnsembleModelReadWrite.EnsembleNodeData").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      sparkSession.createDataFrame(nodeDataRDD, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).repartition(numDataParts).write().parquet(dataPath);
   }

   public Tuple3 loadImpl(final String path, final SparkSession sparkSession, final String className, final String treeClassName) {
      DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
      DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, sparkSession, className);
      JValue impurityJson = metadata.getParamValue("impurity");
      String impurityType = (String)Param$.MODULE$.jsonDecode(org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(impurityJson, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())));
      String treesMetadataPath = (new Path(path, "treesMetadata")).toString();
      Dataset var10000 = sparkSession.read().parquet(treesMetadataPath).select("treeID", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"metadata", "weights"})));
      SQLImplicits var10001 = sparkSession.implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator5$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))));
         }

         public $typecreator5$2() {
         }
      }

      RDD treesMetadataRDD = var10000.as(var10001.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$2()))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            int treeID = BoxesRunTime.unboxToInt(x0$1._1());
            String json = (String)x0$1._2();
            double weights = BoxesRunTime.unboxToDouble(x0$1._3());
            if (true && json != null && true) {
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(treeID)), new Tuple2(DefaultParamsReader$.MODULE$.parseMetadata(json, treeClassName), BoxesRunTime.boxToDouble(weights)));
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      RDD var38 = org.apache.spark.rdd.RDD..MODULE$;
      OrderedRDDFunctions qual$1 = org.apache.spark.rdd.RDD..MODULE$.rddToOrderedRDDFunctions(treesMetadataRDD, scala.math.Ordering.Int..MODULE$, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      boolean x$1 = qual$1.sortByKey$default$1();
      int x$2 = qual$1.sortByKey$default$2();
      Tuple2[] treesMetadataWeights = (Tuple2[])var38.rddToPairRDDFunctions(qual$1.sortByKey(x$1, x$2), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).values().collect();
      DefaultParamsReader.Metadata[] treesMetadata = (DefaultParamsReader.Metadata[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])treesMetadataWeights), (x$19) -> (DefaultParamsReader.Metadata)x$19._1(), scala.reflect.ClassTag..MODULE$.apply(DefaultParamsReader.Metadata.class));
      double[] treesWeights = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])treesMetadataWeights), (x$20) -> BoxesRunTime.boxToDouble($anonfun$loadImpl$3(x$20)), scala.reflect.ClassTag..MODULE$.Double());
      String dataPath = (new Path(path, "data")).toString();
      Dataset df = sparkSession.read().parquet(dataPath);
      Tuple2 var24 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
      if (var24 != null) {
         int major = var24._1$mcI$sp();
         if (major < 3) {
            DataType var27 = df.schema().apply("nodeData").dataType();
            if (!(var27 instanceof StructType)) {
               throw new MatchError(var27);
            }

            StructType var28 = (StructType)var27;
            StructField[] fields = var28.fields();
            Column[] cols = (Column[]).MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fields), (f) -> org.apache.spark.sql.functions..MODULE$.col("nodeData." + f.name()), scala.reflect.ClassTag..MODULE$.apply(Column.class))), org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToLong(-1L)).as("rawCount"), scala.reflect.ClassTag..MODULE$.apply(Column.class));
            Column newNodeDataCol = org.apache.spark.sql.functions..MODULE$.struct(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(cols).toImmutableArraySeq());
            df = df.withColumn("nodeData", newNodeDataCol);
         }

         var38 = org.apache.spark.rdd.RDD..MODULE$;
         SQLImplicits var10002 = sparkSession.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator10$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.tree.EnsembleModelReadWrite.EnsembleNodeData").asType().toTypeConstructor();
            }

            public $typecreator10$1() {
            }
         }

         RDD rootNodesRDD = var38.rddToPairRDDFunctions(df.as(var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).rdd().map((d) -> new Tuple2(BoxesRunTime.boxToInteger(d.treeID()), d.nodeData()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeModelReadWrite.NodeData.class), scala.math.Ordering.Int..MODULE$).groupByKey().map((x0$2) -> {
            if (x0$2 != null) {
               int treeID = x0$2._1$mcI$sp();
               Iterable nodeData = (Iterable)x0$2._2();
               if (true && nodeData != null) {
                  return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(treeID)), DecisionTreeModelReadWrite$.MODULE$.buildTreeFromNodes((DecisionTreeModelReadWrite.NodeData[])nodeData.toArray(scala.reflect.ClassTag..MODULE$.apply(DecisionTreeModelReadWrite.NodeData.class)), impurityType));
               }
            }

            throw new MatchError(x0$2);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         var38 = org.apache.spark.rdd.RDD..MODULE$;
         OrderedRDDFunctions qual$2 = org.apache.spark.rdd.RDD..MODULE$.rddToOrderedRDDFunctions(rootNodesRDD, scala.math.Ordering.Int..MODULE$, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Node.class));
         boolean x$3 = qual$2.sortByKey$default$1();
         int x$4 = qual$2.sortByKey$default$2();
         Node[] rootNodes = (Node[])var38.rddToPairRDDFunctions(qual$2.sortByKey(x$3, x$4), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Node.class), scala.math.Ordering.Int..MODULE$).values().collect();
         return new Tuple3(metadata, .MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(treesMetadata), scala.Predef..MODULE$.wrapRefArray(rootNodes)), treesWeights);
      } else {
         throw new MatchError(var24);
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$saveImpl$2(final DecisionTreeModel x$18) {
      return (long)x$18.numNodes();
   }

   // $FF: synthetic method
   public static final double $anonfun$loadImpl$3(final Tuple2 x$20) {
      return x$20._2$mcD$sp();
   }

   private EnsembleModelReadWrite$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
