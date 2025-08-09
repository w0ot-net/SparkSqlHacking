package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.RDDOperationClusterWrapper;
import org.apache.spark.status.RDDOperationGraphWrapper;
import org.apache.spark.ui.scope.RDDOperationEdge;
import org.apache.spark.ui.scope.RDDOperationNode;
import scala.collection.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4QAC\u0006\u0001\u0017UAQ\u0001\n\u0001\u0005\u0002\u0019BQ\u0001\u000b\u0001\u0005B%BQA\r\u0001\u0005\u0002MBQA\u000e\u0001\u0005\n]BQA\u0011\u0001\u0005\n\rCQ!\u0012\u0001\u0005\n\u0019CQa\u0015\u0001\u0005\nQCQA\u0016\u0001\u0005\n]CQa\u0018\u0001\u0005\n\u0001\u0014!E\u0015#E\u001fB,'/\u0019;j_:<%/\u00199i/J\f\u0007\u000f]3s'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0007\u000e\u0003!\u0001(o\u001c;pEV4'B\u0001\b\u0010\u0003\u0019\u0019H/\u0019;vg*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xmE\u0002\u0001-q\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007cA\u000f\u001fA5\t1\"\u0003\u0002 \u0017\ti\u0001K]8u_\n,hmU3s\t\u0016\u0004\"!\t\u0012\u000e\u00035I!aI\u0007\u00031I#Ei\u00149fe\u0006$\u0018n\u001c8He\u0006\u0004\bn\u0016:baB,'/\u0001\u0004=S:LGOP\u0002\u0001)\u00059\u0003CA\u000f\u0001\u0003%\u0019XM]5bY&TX\r\u0006\u0002+aA\u0019qcK\u0017\n\u00051B\"!B!se\u0006L\bCA\f/\u0013\ty\u0003D\u0001\u0003CsR,\u0007\"B\u0019\u0003\u0001\u0004\u0001\u0013AA8q\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u0005\u0001\"\u0004\"B\u001b\u0004\u0001\u0004Q\u0013!\u00022zi\u0016\u001c\u0018aI:fe&\fG.\u001b>f%\u0012#u\n]3sCRLwN\\\"mkN$XM],sCB\u0004XM\u001d\u000b\u0003q}\u0002\"!\u000f\u001f\u000f\u0005uQ\u0014BA\u001e\f\u0003)\u0019Fo\u001c:f)f\u0004Xm]\u0005\u0003{y\u0012!D\u0015#E\u001fB,'/\u0019;j_:\u001cE.^:uKJ<&/\u00199qKJT!aO\u0006\t\u000bE\"\u0001\u0019\u0001!\u0011\u0005\u0005\n\u0015BA\u001f\u000e\u0003\u0015\"Wm]3sS\u0006d\u0017N_3S\t\u0012{\u0005/\u001a:bi&|gn\u00117vgR,'o\u0016:baB,'\u000f\u0006\u0002A\t\")\u0011'\u0002a\u0001q\u0005I2/\u001a:jC2L'0\u001a*E\t>\u0003XM]1uS>tgj\u001c3f)\t9%\n\u0005\u0002:\u0011&\u0011\u0011J\u0010\u0002\u0011%\u0012#u\n]3sCRLwN\u001c(pI\u0016DQa\u0013\u0004A\u00021\u000bAA\\8eKB\u0011QJU\u0007\u0002\u001d*\u0011q\nU\u0001\u0006g\u000e|\u0007/\u001a\u0006\u0003#>\t!!^5\n\u0005%s\u0015a\u00073fg\u0016\u0014\u0018.\u00197ju\u0016\u0014F\tR(qKJ\fG/[8o\u001d>$W\r\u0006\u0002M+\")1j\u0002a\u0001\u000f\u0006I2/\u001a:jC2L'0\u001a*E\t>\u0003XM]1uS>tW\tZ4f)\tA6\f\u0005\u0002:3&\u0011!L\u0010\u0002\u0011%\u0012#u\n]3sCRLwN\\#eO\u0016DQ\u0001\u0018\u0005A\u0002u\u000bA!\u001a3hKB\u0011QJX\u0005\u00035:\u000b1\u0004Z3tKJL\u0017\r\\5{KJ#Ei\u00149fe\u0006$\u0018n\u001c8FI\u001e,GCA/b\u0011\u0015a\u0016\u00021\u0001Y\u0001"
)
public class RDDOperationGraphWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final RDDOperationGraphWrapper op) {
      StoreTypes.RDDOperationGraphWrapper.Builder builder = StoreTypes.RDDOperationGraphWrapper.newBuilder();
      builder.setStageId((long)op.stageId());
      op.edges().foreach((e) -> builder.addEdges(this.serializeRDDOperationEdge(e)));
      op.outgoingEdges().foreach((e) -> builder.addOutgoingEdges(this.serializeRDDOperationEdge(e)));
      op.incomingEdges().foreach((e) -> builder.addIncomingEdges(this.serializeRDDOperationEdge(e)));
      builder.setRootCluster(this.serializeRDDOperationClusterWrapper(op.rootCluster()));
      return builder.build().toByteArray();
   }

   public RDDOperationGraphWrapper deserialize(final byte[] bytes) {
      StoreTypes.RDDOperationGraphWrapper wrapper = StoreTypes.RDDOperationGraphWrapper.parseFrom(bytes);
      return new RDDOperationGraphWrapper((int)wrapper.getStageId(), (Seq).MODULE$.ListHasAsScala(wrapper.getEdgesList()).asScala().map((edge) -> this.deserializeRDDOperationEdge(edge)), (Seq).MODULE$.ListHasAsScala(wrapper.getOutgoingEdgesList()).asScala().map((edge) -> this.deserializeRDDOperationEdge(edge)), (Seq).MODULE$.ListHasAsScala(wrapper.getIncomingEdgesList()).asScala().map((edge) -> this.deserializeRDDOperationEdge(edge)), this.deserializeRDDOperationClusterWrapper(wrapper.getRootCluster()));
   }

   private StoreTypes.RDDOperationClusterWrapper serializeRDDOperationClusterWrapper(final RDDOperationClusterWrapper op) {
      StoreTypes.RDDOperationClusterWrapper.Builder builder = StoreTypes.RDDOperationClusterWrapper.newBuilder();
      Utils$.MODULE$.setStringField(op.id(), (value) -> builder.setId(value));
      Utils$.MODULE$.setStringField(op.name(), (value) -> builder.setName(value));
      op.childNodes().foreach((node) -> builder.addChildNodes(this.serializeRDDOperationNode(node)));
      op.childClusters().foreach((cluster) -> builder.addChildClusters(this.serializeRDDOperationClusterWrapper(cluster)));
      return builder.build();
   }

   private RDDOperationClusterWrapper deserializeRDDOperationClusterWrapper(final StoreTypes.RDDOperationClusterWrapper op) {
      return new RDDOperationClusterWrapper(Utils$.MODULE$.getStringField(op.hasId(), () -> op.getId()), Utils$.MODULE$.getStringField(op.hasName(), () -> op.getName()), (Seq).MODULE$.ListHasAsScala(op.getChildNodesList()).asScala().map((node) -> this.deserializeRDDOperationNode(node)), (Seq).MODULE$.ListHasAsScala(op.getChildClustersList()).asScala().map((opx) -> this.deserializeRDDOperationClusterWrapper(opx)));
   }

   private StoreTypes.RDDOperationNode serializeRDDOperationNode(final RDDOperationNode node) {
      StoreTypes.DeterministicLevel outputDeterministicLevel = DeterministicLevelSerializer$.MODULE$.serialize(node.outputDeterministicLevel());
      StoreTypes.RDDOperationNode.Builder builder = StoreTypes.RDDOperationNode.newBuilder();
      builder.setId(node.id());
      Utils$.MODULE$.setStringField(node.name(), (value) -> builder.setName(value));
      Utils$.MODULE$.setStringField(node.callsite(), (value) -> builder.setCallsite(value));
      builder.setCached(node.cached());
      builder.setBarrier(node.barrier());
      builder.setOutputDeterministicLevel(outputDeterministicLevel);
      return builder.build();
   }

   private RDDOperationNode deserializeRDDOperationNode(final StoreTypes.RDDOperationNode node) {
      return new RDDOperationNode(node.getId(), Utils$.MODULE$.getStringField(node.hasName(), () -> node.getName()), node.getCached(), node.getBarrier(), Utils$.MODULE$.getStringField(node.hasCallsite(), () -> node.getCallsite()), DeterministicLevelSerializer$.MODULE$.deserialize(node.getOutputDeterministicLevel()));
   }

   private StoreTypes.RDDOperationEdge serializeRDDOperationEdge(final RDDOperationEdge edge) {
      StoreTypes.RDDOperationEdge.Builder builder = StoreTypes.RDDOperationEdge.newBuilder();
      builder.setFromId(edge.fromId());
      builder.setToId(edge.toId());
      return builder.build();
   }

   private RDDOperationEdge deserializeRDDOperationEdge(final StoreTypes.RDDOperationEdge edge) {
      return new RDDOperationEdge(edge.getFromId(), edge.getToId());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
