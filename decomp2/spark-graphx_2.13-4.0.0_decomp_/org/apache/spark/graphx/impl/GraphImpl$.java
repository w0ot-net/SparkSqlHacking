package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.EdgeRDD;
import org.apache.spark.graphx.EdgeRDD$;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.graphx.VertexRDD$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.reflect.ClassTag;
import scala.reflect.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class GraphImpl$ implements Serializable {
   public static final GraphImpl$ MODULE$ = new GraphImpl$();

   public GraphImpl apply(final RDD edges, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$13, final ClassTag evidence$14) {
      return this.fromEdgeRDD(EdgeRDD$.MODULE$.fromEdges(edges, evidence$14, evidence$13), defaultVertexAttr, edgeStorageLevel, vertexStorageLevel, evidence$13, evidence$14);
   }

   public GraphImpl fromEdgePartitions(final RDD edgePartitions, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$15, final ClassTag evidence$16) {
      return this.fromEdgeRDD(EdgeRDD$.MODULE$.fromEdgePartitions(edgePartitions, evidence$16, evidence$15), defaultVertexAttr, edgeStorageLevel, vertexStorageLevel, evidence$15, evidence$16);
   }

   public GraphImpl apply(final RDD vertices, final RDD edges, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$17, final ClassTag evidence$18) {
      EdgeRDDImpl edgeRDD = EdgeRDD$.MODULE$.fromEdges(edges, .MODULE$.classTag(evidence$18), .MODULE$.classTag(evidence$17)).withTargetStorageLevel(edgeStorageLevel);
      VertexRDD vertexRDD = VertexRDD$.MODULE$.apply(vertices, edgeRDD, defaultVertexAttr, evidence$17).withTargetStorageLevel(vertexStorageLevel);
      return this.apply(vertexRDD, edgeRDD, evidence$17, evidence$18);
   }

   public GraphImpl apply(final VertexRDD vertices, final EdgeRDD edges, final ClassTag evidence$19, final ClassTag evidence$20) {
      vertices.cache();
      EdgeRDDImpl newEdges = ((EdgeRDDImpl)edges).mapEdgePartitions((pid, part) -> $anonfun$apply$1(evidence$19, BoxesRunTime.unboxToInt(pid), part), evidence$20, evidence$19).cache();
      return this.fromExistingRDDs(vertices, newEdges, evidence$19, evidence$20);
   }

   public GraphImpl fromExistingRDDs(final VertexRDD vertices, final EdgeRDD edges, final ClassTag evidence$21, final ClassTag evidence$22) {
      return new GraphImpl(vertices, new ReplicatedVertexView((EdgeRDDImpl)edges, ReplicatedVertexView$.MODULE$.$lessinit$greater$default$2(), ReplicatedVertexView$.MODULE$.$lessinit$greater$default$3(), evidence$21, evidence$22), evidence$21, evidence$22);
   }

   private GraphImpl fromEdgeRDD(final EdgeRDDImpl edges, final Object defaultVertexAttr, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel, final ClassTag evidence$23, final ClassTag evidence$24) {
      EdgeRDDImpl edgesCached = edges.withTargetStorageLevel(edgeStorageLevel).cache();
      VertexRDD vertices = VertexRDD$.MODULE$.fromEdges(edgesCached, edgesCached.partitions().length, defaultVertexAttr, evidence$23).withTargetStorageLevel(vertexStorageLevel);
      return this.fromExistingRDDs(vertices, edgesCached, evidence$23, evidence$24);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GraphImpl$.class);
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$apply$1(final ClassTag evidence$19$1, final int pid, final EdgePartition part) {
      return part.withoutVertexAttributes(evidence$19$1);
   }

   private GraphImpl$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
