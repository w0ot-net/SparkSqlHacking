package org.apache.spark.graphx;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.impl.GraphImpl;
import org.apache.spark.graphx.impl.GraphImpl$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction2;

public final class Graph$ implements Serializable {
   public static final Graph$ MODULE$ = new Graph$();

   public Graph fromEdgeTuples(final RDD rawEdges, final Object defaultValue, final Option uniqueEdges, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$15) {
      RDD edges = rawEdges.map((px) -> new Edge$mcI$sp(px._1$mcJ$sp(), px._2$mcJ$sp(), 1), .MODULE$.apply(Edge.class));
      GraphImpl graph = GraphImpl$.MODULE$.apply(edges, defaultValue, edgeStorageLevel, vertexStorageLevel, evidence$15, .MODULE$.Int());
      if (uniqueEdges instanceof Some var11) {
         PartitionStrategy p = (PartitionStrategy)var11.value();
         return graph.partitionBy(p).groupEdges((JFunction2.mcIII.sp)(a, b) -> a + b);
      } else if (scala.None..MODULE$.equals(uniqueEdges)) {
         return graph;
      } else {
         throw new MatchError(uniqueEdges);
      }
   }

   public Option fromEdgeTuples$default$3() {
      return scala.None..MODULE$;
   }

   public StorageLevel fromEdgeTuples$default$4() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   public StorageLevel fromEdgeTuples$default$5() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   public Graph fromEdges(final RDD edges, final Object defaultValue, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$16, final ClassTag evidence$17) {
      return GraphImpl$.MODULE$.apply(edges, defaultValue, edgeStorageLevel, vertexStorageLevel, evidence$16, evidence$17);
   }

   public StorageLevel fromEdges$default$3() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   public StorageLevel fromEdges$default$4() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   public Graph apply(final RDD vertices, final RDD edges, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$18, final ClassTag evidence$19) {
      return GraphImpl$.MODULE$.apply(vertices, edges, defaultVertexAttr, edgeStorageLevel, vertexStorageLevel, evidence$18, evidence$19);
   }

   public Object apply$default$3() {
      return null;
   }

   public StorageLevel apply$default$4() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   public StorageLevel apply$default$5() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   public GraphOps graphToGraphOps(final Graph g, final ClassTag evidence$20, final ClassTag evidence$21) {
      return g.ops();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Graph$.class);
   }

   private Graph$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
