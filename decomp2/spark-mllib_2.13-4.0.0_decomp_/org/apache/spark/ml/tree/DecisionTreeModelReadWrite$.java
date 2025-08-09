package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.param.Param$;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class DecisionTreeModelReadWrite$ {
   public static final DecisionTreeModelReadWrite$ MODULE$ = new DecisionTreeModelReadWrite$();

   public Node loadTreeNodes(final String path, final DefaultParamsReader.Metadata metadata, final SparkSession sparkSession) {
      DefaultFormats format = .MODULE$;
      JValue impurityJson = metadata.getParamValue("impurity");
      String impurityType = (String)Param$.MODULE$.jsonDecode(org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(impurityJson, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())));
      String dataPath = (new Path(path, "data")).toString();
      Dataset df = sparkSession.read().parquet(dataPath);
      Tuple2 var11 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
      if (var11 != null) {
         int major = var11._1$mcI$sp();
         if (major < 3) {
            df = df.withColumn("rawCount", org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToLong(-1L)));
         }

         SQLImplicits var10002 = sparkSession.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator5$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.tree.DecisionTreeModelReadWrite.NodeData").asType().toTypeConstructor();
            }

            public $typecreator5$1() {
            }
         }

         return this.buildTreeFromNodes((DecisionTreeModelReadWrite.NodeData[])df.as(var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).collect(), impurityType);
      } else {
         throw new MatchError(var11);
      }
   }

   public Node buildTreeFromNodes(final DecisionTreeModelReadWrite.NodeData[] data, final String impurityType) {
      DecisionTreeModelReadWrite.NodeData[] nodes = (DecisionTreeModelReadWrite.NodeData[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(data), (x$17) -> BoxesRunTime.boxToInteger($anonfun$buildTreeFromNodes$1(x$17)), scala.math.Ordering.Int..MODULE$);
      scala.Predef..MODULE$.assert(((DecisionTreeModelReadWrite.NodeData)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(nodes))).id() == 0, () -> {
         DecisionTreeModelReadWrite.NodeData var10000 = (DecisionTreeModelReadWrite.NodeData)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(nodes));
         return "Decision Tree load failed.  Expected smallest node ID to be 0, but found " + var10000.id();
      });
      scala.Predef..MODULE$.assert(((DecisionTreeModelReadWrite.NodeData)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(nodes))).id() == nodes.length - 1, () -> "Decision Tree load failed.  Expected largest node ID to be " + (nodes.length - 1) + ", but found " + ((DecisionTreeModelReadWrite.NodeData)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(nodes))).id());
      Node[] finalNodes = new Node[nodes.length];
      scala.collection.ArrayOps..MODULE$.reverseIterator$extension(scala.Predef..MODULE$.refArrayOps(nodes)).foreach((x0$1) -> {
         $anonfun$buildTreeFromNodes$4(impurityType, finalNodes, x0$1);
         return BoxedUnit.UNIT;
      });
      return (Node)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(finalNodes));
   }

   // $FF: synthetic method
   public static final int $anonfun$buildTreeFromNodes$1(final DecisionTreeModelReadWrite.NodeData x$17) {
      return x$17.id();
   }

   // $FF: synthetic method
   public static final void $anonfun$buildTreeFromNodes$4(final String impurityType$1, final Node[] finalNodes$1, final DecisionTreeModelReadWrite.NodeData x0$1) {
      if (x0$1 != null) {
         ImpurityCalculator impurityStats = ImpurityCalculator$.MODULE$.getCalculator(impurityType$1, x0$1.impurityStats(), x0$1.rawCount());
         Object var10000;
         if (x0$1.leftChild() != -1) {
            Node leftChild = finalNodes$1[x0$1.leftChild()];
            Node rightChild = finalNodes$1[x0$1.rightChild()];
            var10000 = new InternalNode(x0$1.prediction(), x0$1.impurity(), x0$1.gain(), leftChild, rightChild, x0$1.split().getSplit(), impurityStats);
         } else {
            var10000 = new LeafNode(x0$1.prediction(), x0$1.impurity(), impurityStats);
         }

         Node node = (Node)var10000;
         finalNodes$1[x0$1.id()] = node;
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private DecisionTreeModelReadWrite$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
