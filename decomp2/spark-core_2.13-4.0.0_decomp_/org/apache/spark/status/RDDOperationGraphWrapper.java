package org.apache.spark.status;

import org.apache.spark.ui.scope.RDDOperationGraph;
import org.apache.spark.util.kvstore.KVIndex;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Q!\u0004\b\u0001!YA\u0001\"\b\u0001\u0003\u0006\u0004%\ta\b\u0005\tm\u0001\u0011\t\u0011)A\u0005A!Aq\u0007\u0001BC\u0002\u0013\u0005\u0001\b\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003:\u0011!A\u0005A!b\u0001\n\u0003A\u0004\u0002C%\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011)\u0003!Q1A\u0005\u0002aB\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t\u0019\u0002\u0011)\u0019!C\u0001\u001b\"A!\u000b\u0001B\u0001B\u0003%a\nC\u0003T\u0001\u0011\u0005A\u000bC\u0003\\\u0001\u0011\u0005AL\u0001\rS\t\u0012{\u0005/\u001a:bi&|gn\u0012:ba\"<&/\u00199qKJT!a\u0004\t\u0002\rM$\u0018\r^;t\u0015\t\t\"#A\u0003ta\u0006\u00148N\u0003\u0002\u0014)\u00051\u0011\r]1dQ\u0016T\u0011!F\u0001\u0004_J<7C\u0001\u0001\u0018!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fM\u000691\u000f^1hK&#7\u0001A\u000b\u0002AA\u0011\u0001$I\u0005\u0003Ee\u00111!\u00138uQ\t\tAE\u000b\u0002&[A\u0011aeK\u0007\u0002O)\u0011\u0001&K\u0001\bWZ\u001cHo\u001c:f\u0015\tQ\u0003#\u0001\u0003vi&d\u0017B\u0001\u0017(\u0005\u001dYe+\u00138eKb\\\u0013A\f\t\u0003_Qj\u0011\u0001\r\u0006\u0003cI\nA!\\3uC*\u00111'G\u0001\u000bC:tw\u000e^1uS>t\u0017BA\u001b1\u0005\u00199W\r\u001e;fe\u0006A1\u000f^1hK&#\u0007%A\u0003fI\u001e,7/F\u0001:!\rQThP\u0007\u0002w)\u0011A(G\u0001\u000bG>dG.Z2uS>t\u0017B\u0001 <\u0005\r\u0019V-\u001d\t\u0003\u0001\u0016k\u0011!\u0011\u0006\u0003\u0005\u000e\u000bQa]2pa\u0016T!\u0001\u0012\t\u0002\u0005UL\u0017B\u0001$B\u0005A\u0011F\tR(qKJ\fG/[8o\u000b\u0012<W-\u0001\u0004fI\u001e,7\u000fI\u0001\u000e_V$xm\\5oO\u0016#w-Z:\u0002\u001d=,HoZ8j]\u001e,EmZ3tA\u0005i\u0011N\\2p[&tw-\u00123hKN\fa\"\u001b8d_6LgnZ#eO\u0016\u001c\b%A\u0006s_>$8\t\\;ti\u0016\u0014X#\u0001(\u0011\u0005=\u0003V\"\u0001\b\n\u0005Es!A\u0007*E\t>\u0003XM]1uS>t7\t\\;ti\u0016\u0014xK]1qa\u0016\u0014\u0018\u0001\u0004:p_R\u001cE.^:uKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0004V-^C\u0016L\u0017\t\u0003\u001f\u0002AQ!H\u0006A\u0002\u0001BQaN\u0006A\u0002eBQ\u0001S\u0006A\u0002eBQAS\u0006A\u0002eBQ\u0001T\u0006A\u00029\u000b1\u0003^8S\t\u0012{\u0005/\u001a:bi&|gn\u0012:ba\"$\u0012!\u0018\t\u0003\u0001zK!aX!\u0003#I#Ei\u00149fe\u0006$\u0018n\u001c8He\u0006\u0004\b\u000e"
)
public class RDDOperationGraphWrapper {
   private final int stageId;
   private final Seq edges;
   private final Seq outgoingEdges;
   private final Seq incomingEdges;
   private final RDDOperationClusterWrapper rootCluster;

   @KVIndex
   public int stageId() {
      return this.stageId;
   }

   public Seq edges() {
      return this.edges;
   }

   public Seq outgoingEdges() {
      return this.outgoingEdges;
   }

   public Seq incomingEdges() {
      return this.incomingEdges;
   }

   public RDDOperationClusterWrapper rootCluster() {
      return this.rootCluster;
   }

   public RDDOperationGraph toRDDOperationGraph() {
      return new RDDOperationGraph(this.edges(), this.outgoingEdges(), this.incomingEdges(), this.rootCluster().toRDDOperationCluster());
   }

   public RDDOperationGraphWrapper(final int stageId, final Seq edges, final Seq outgoingEdges, final Seq incomingEdges, final RDDOperationClusterWrapper rootCluster) {
      this.stageId = stageId;
      this.edges = edges;
      this.outgoingEdges = outgoingEdges;
      this.incomingEdges = incomingEdges;
      this.rootCluster = rootCluster;
   }
}
