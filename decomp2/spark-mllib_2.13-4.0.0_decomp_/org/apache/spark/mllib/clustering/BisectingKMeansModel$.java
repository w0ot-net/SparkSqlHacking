package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BisectingKMeansModel$ implements Loader, Serializable {
   public static final BisectingKMeansModel$ MODULE$ = new BisectingKMeansModel$();

   public BisectingKMeansModel load(final SparkContext sc, final String path) {
      Tuple3 var6 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         label99: {
            String loadedClassName = (String)var6._1();
            String formatVersion = (String)var6._2();
            JValue __ = (JValue)var6._3();
            Tuple3 var5 = new Tuple3(loadedClassName, formatVersion, __);
            String loadedClassName = (String)var5._1();
            String formatVersion = (String)var5._2();
            JValue var12 = (JValue)var5._3();
            Tuple2 var13 = new Tuple2(loadedClassName, formatVersion);
            if (var13 != null) {
               label96: {
                  String var14 = (String)var13._1();
                  String var15 = (String)var13._2();
                  String var10000 = BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisClassName();
                  if (var10000 == null) {
                     if (var14 != null) {
                        break label96;
                     }
                  } else if (!var10000.equals(var14)) {
                     break label96;
                  }

                  var10000 = BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisFormatVersion();
                  if (var10000 == null) {
                     if (var15 == null) {
                        break label99;
                     }
                  } else if (var10000.equals(var15)) {
                     break label99;
                  }
               }
            }

            label100: {
               if (var13 != null) {
                  label97: {
                     String var19 = (String)var13._1();
                     String var20 = (String)var13._2();
                     String var30 = BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisClassName();
                     if (var30 == null) {
                        if (var19 != null) {
                           break label97;
                        }
                     } else if (!var30.equals(var19)) {
                        break label97;
                     }

                     var30 = BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisFormatVersion();
                     if (var30 == null) {
                        if (var20 == null) {
                           break label100;
                        }
                     } else if (var30.equals(var20)) {
                        break label100;
                     }
                  }
               }

               label101: {
                  if (var13 != null) {
                     String var24 = (String)var13._1();
                     String var25 = (String)var13._2();
                     String var32 = BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisClassName();
                     if (var32 == null) {
                        if (var24 != null) {
                           throw new Exception("BisectingKMeansModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + formatVersion + ").  Supported:\n  (" + BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisClassName() + "\n  (" + BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisClassName() + ")\n  (" + BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisClassName() + ")");
                        }
                     } else if (!var32.equals(var24)) {
                        throw new Exception("BisectingKMeansModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + formatVersion + ").  Supported:\n  (" + BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisClassName() + "\n  (" + BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisClassName() + ")\n  (" + BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisClassName() + ")");
                     }

                     var32 = BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisFormatVersion();
                     if (var32 == null) {
                        if (var25 == null) {
                           break label101;
                        }
                     } else if (var32.equals(var25)) {
                        break label101;
                     }
                  }

                  throw new Exception("BisectingKMeansModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + formatVersion + ").  Supported:\n  (" + BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV1_0$.MODULE$.thisClassName() + "\n  (" + BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV2_0$.MODULE$.thisClassName() + ")\n  (" + BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisClassName() + ", " + BisectingKMeansModel.SaveLoadV3_0$.MODULE$.thisClassName() + ")");
               }

               BisectingKMeansModel model = BisectingKMeansModel.SaveLoadV3_0$.MODULE$.load(sc, path);
               return model;
            }

            BisectingKMeansModel model = BisectingKMeansModel.SaveLoadV2_0$.MODULE$.load(sc, path);
            return model;
         }

         BisectingKMeansModel model = BisectingKMeansModel.SaveLoadV1_0$.MODULE$.load(sc, path);
         return model;
      }
   }

   public ClusteringTreeNode[] org$apache$spark$mllib$clustering$BisectingKMeansModel$$getNodes(final ClusteringTreeNode node) {
      return .MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(node.children())) ? (ClusteringTreeNode[])((Object[])(new ClusteringTreeNode[]{node})) : (ClusteringTreeNode[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(node.children()), (nodex) -> MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$getNodes(nodex), (xs) -> scala.Predef..MODULE$.wrapRefArray(xs), scala.reflect.ClassTag..MODULE$.apply(ClusteringTreeNode.class))), new ClusteringTreeNode[]{node}, scala.reflect.ClassTag..MODULE$.apply(ClusteringTreeNode.class));
   }

   public ClusteringTreeNode org$apache$spark$mllib$clustering$BisectingKMeansModel$$buildTree(final int rootId, final Map nodes) {
      BisectingKMeansModel.Data root = (BisectingKMeansModel.Data)nodes.apply(BoxesRunTime.boxToInteger(rootId));
      if (root.children().isEmpty()) {
         return new ClusteringTreeNode(root.index(), root.size(), new VectorWithNorm(root.center(), root.norm(), VectorWithNorm$.MODULE$.$lessinit$greater$default$3()), root.cost(), root.height(), new ClusteringTreeNode[0]);
      } else {
         Seq children = (Seq)root.children().map((c) -> $anonfun$buildTree$1(nodes, BoxesRunTime.unboxToInt(c)));
         return new ClusteringTreeNode(root.index(), root.size(), new VectorWithNorm(root.center(), root.norm(), VectorWithNorm$.MODULE$.$lessinit$greater$default$3()), root.cost(), root.height(), (ClusteringTreeNode[])children.toArray(scala.reflect.ClassTag..MODULE$.apply(ClusteringTreeNode.class)));
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BisectingKMeansModel$.class);
   }

   // $FF: synthetic method
   public static final ClusteringTreeNode $anonfun$buildTree$1(final Map nodes$1, final int c) {
      return MODULE$.org$apache$spark$mllib$clustering$BisectingKMeansModel$$buildTree(c, nodes$1);
   }

   private BisectingKMeansModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
