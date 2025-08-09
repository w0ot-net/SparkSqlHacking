package org.apache.spark.storage;

import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Me!B\u00193\u0003CY\u0004\"\u0002\"\u0001\t\u0003\u0019\u0005\"\u0002$\u0001\r\u00039\u0005\"B*\u0001\t\u0003!\u0006\"B.\u0001\t\u0003a\u0006\"\u00021\u0001\t\u0003a\u0006\"B1\u0001\t\u0003a\u0006\"\u00022\u0001\t\u0003a\u0006\"B2\u0001\t\u0003\"waBA\u0014e!\u0005\u0011\u0011\u0006\u0004\u0007cIB\t!a\u000b\t\r\tSA\u0011AA\u0017\u0011%\tyC\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002D)\u0001\u000b\u0011BA\u001a\u0011%\t)E\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002H)\u0001\u000b\u0011BA\u001a\u0011%\tIE\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002L)\u0001\u000b\u0011BA\u001a\u0011%\tiE\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002P)\u0001\u000b\u0011BA\u001a\u0011%\t\tF\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002T)\u0001\u000b\u0011BA\u001a\u0011%\t)F\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002X)\u0001\u000b\u0011BA\u001a\u0011%\tIF\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002\\)\u0001\u000b\u0011BA\u001a\u0011%\tiF\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002`)\u0001\u000b\u0011BA\u001a\u0011%\t\tG\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002d)\u0001\u000b\u0011BA\u001a\u0011%\t)G\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002h)\u0001\u000b\u0011BA\u001a\u0011%\tIG\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002l)\u0001\u000b\u0011BA\u001a\u0011%\tiG\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002p)\u0001\u000b\u0011BA\u001a\u0011%\t\tH\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002t)\u0001\u000b\u0011BA\u001a\u0011%\t)H\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002x)\u0001\u000b\u0011BA\u001a\u0011%\tIH\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002|)\u0001\u000b\u0011BA\u001a\u0011%\tiH\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002\u0000)\u0001\u000b\u0011BA\u001a\u0011%\t\tI\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002\u0004*\u0001\u000b\u0011BA\u001a\u0011%\t)I\u0003b\u0001\n\u0003\t\t\u0004\u0003\u0005\u0002\b*\u0001\u000b\u0011BA\u001a\u0011\u001d\tII\u0003C\u0001\u0003\u0017\u0013qA\u00117pG.LEM\u0003\u00024i\u000591\u000f^8sC\u001e,'BA\u001b7\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0004(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002s\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0010\t\u0003{\u0001k\u0011A\u0010\u0006\u0002\u007f\u0005)1oY1mC&\u0011\u0011I\u0010\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005!\u0005CA#\u0001\u001b\u0005\u0011\u0014\u0001\u00028b[\u0016,\u0012\u0001\u0013\t\u0003\u0013Bs!A\u0013(\u0011\u0005-sT\"\u0001'\u000b\u00055S\u0014A\u0002\u001fs_>$h(\u0003\u0002P}\u00051\u0001K]3eK\u001aL!!\u0015*\u0003\rM#(/\u001b8h\u0015\tye(A\u0004bgJ#E)\u00133\u0016\u0003U\u00032!\u0010,Y\u0013\t9fH\u0001\u0004PaRLwN\u001c\t\u0003\u000bfK!A\u0017\u001a\u0003\u0015I#EI\u00117pG.LE-A\u0003jgJ#E)F\u0001^!\tid,\u0003\u0002`}\t9!i\\8mK\u0006t\u0017!C5t'\",hM\u001a7f\u00039I7o\u00155vM\u001adWm\u00115v].\f1\"[:Ce>\fGmY1ti\u0006AAo\\*ue&tw\rF\u0001ISm\u0001a\r\u001b6ZY:\u0004(\u000f\u001e<yurt\u0018\u0011AA\u0003\u0003\u0013\ti!!\u0005\u0002\u0016%\u0011qM\r\u0002\u0011\u0005J|\u0017\rZ2bgR\u0014En\\2l\u0013\u0012L!!\u001b\u001a\u0003\u000f\r\u000b7\r[3JI&\u00111N\r\u0002\u0014!f$\bn\u001c8TiJ,\u0017-\u001c\"m_\u000e\\\u0017\nZ\u0005\u0003[J\u00121c\u00155vM\u001adWM\u00117pG.\u0014\u0015\r^2i\u0013\u0012L!a\u001c\u001a\u0003'MCWO\u001a4mK\ncwnY6DQVt7.\u00133\n\u0005E\u0014$AD*ik\u001a4G.\u001a\"m_\u000e\\\u0017\nZ\u0005\u0003gJ\u0012ac\u00155vM\u001adWm\u00115fG.\u001cX/\u001c\"m_\u000e\\\u0017\nZ\u0005\u0003kJ\u0012!c\u00155vM\u001adW\rR1uC\ncwnY6JI&\u0011qO\r\u0002\u0014'\",hM\u001a7f\u0013:$W\r\u001f\"m_\u000e\\\u0017\nZ\u0005\u0003sJ\u0012Ac\u00155vM\u001adW-T3sO\u0016$'\t\\8dW&#\u0017BA>3\u0005a\u0019\u0006.\u001e4gY\u0016lUM]4fI\u0012\u000bG/\u0019\"m_\u000e\\\u0017\nZ\u0005\u0003{J\u0012\u0011d\u00155vM\u001adW-T3sO\u0016$\u0017J\u001c3fq\ncwnY6JI&\u0011qP\r\u0002\u0019'\",hM\u001a7f\u001b\u0016\u0014x-\u001a3NKR\f'\t\\8dW&#\u0017bAA\u0002e\t\u00112\u000b[;gM2,\u0007+^:i\u00052|7m[%e\u0013\r\t9A\r\u0002\u000e'R\u0014X-Y7CY>\u001c7.\u00133\n\u0007\u0005-!GA\tUCN\\'+Z:vYR\u0014En\\2l\u0013\u0012L1!a\u00043\u0005A!V-\u001c9M_\u000e\fGN\u00117pG.LE-C\u0002\u0002\u0014I\u0012!\u0003V3naNCWO\u001a4mK\ncwnY6JI&\u0019\u0011q\u0003\u001a\u0003\u0017Q+7\u000f\u001e\"m_\u000e\\\u0017\n\u001a\u0015\u0004\u0001\u0005m\u0001\u0003BA\u000f\u0003Gi!!a\b\u000b\u0007\u0005\u0005B'\u0001\u0006b]:|G/\u0019;j_:LA!!\n\u0002 \taA)\u001a<fY>\u0004XM]!qS\u00069!\t\\8dW&#\u0007CA#\u000b'\tQA\b\u0006\u0002\u0002*\u0005\u0019!\u000b\u0012#\u0016\u0005\u0005M\u0002\u0003BA\u001b\u0003\u007fi!!a\u000e\u000b\t\u0005e\u00121H\u0001\t[\u0006$8\r[5oO*\u0019\u0011Q\b \u0002\tU$\u0018\u000e\\\u0005\u0005\u0003\u0003\n9DA\u0003SK\u001e,\u00070\u0001\u0003S\t\u0012\u0003\u0013aB*I+\u001a3E*R\u0001\t'\"+fI\u0012'FA\u0005i1\u000bS+G\r2+uLQ!U\u0007\"\u000bab\u0015%V\r\u001acUi\u0018\"B)\u000eC\u0005%\u0001\u0007T\u0011V3e\tT#`\t\u0006#\u0016)A\u0007T\u0011V3e\tT#`\t\u0006#\u0016\tI\u0001\u000e'\"+fI\u0012'F?&sE)\u0012-\u0002\u001dMCUK\u0012$M\u000b~Ke\nR#YA\u0005a1\u000bS+G\r2+u\fU+T\u0011\u0006i1\u000bS+G\r2+u\fU+T\u0011\u0002\nab\u0015%V\r\u001acUiX'F%\u001e+E)A\bT\u0011V3e\tT#`\u001b\u0016\u0013v)\u0012#!\u0003M\u0019\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{F)\u0011+B\u0003Q\u0019\u0006*\u0016$G\u0019\u0016{V*\u0012*H\u000b\u0012{F)\u0011+BA\u0005!2\u000bS+G\r2+u,T#S\u000f\u0016#u,\u0013(E\u000bb\u000bQc\u0015%V\r\u001acUiX'F%\u001e+EiX%O\t\u0016C\u0006%A\nT\u0011V3e\tT#`\u001b\u0016\u0013v)\u0012#`\u001b\u0016#\u0016)\u0001\u000bT\u0011V3e\tT#`\u001b\u0016\u0013v)\u0012#`\u001b\u0016#\u0016\tI\u0001\u000e'\"+fI\u0012'F?\u000eCUKT&\u0002\u001dMCUK\u0012$M\u000b~\u001b\u0005*\u0016(LA\u0005I!IU(B\t\u000e\u000b5\u000bV\u0001\u000b\u0005J{\u0015\tR\"B'R\u0003\u0013A\u0003+B'.\u0013ViU+M)\u0006YA+Q*L%\u0016\u001bV\u000b\u0014+!\u0003\u0019\u0019FKU#B\u001b\u000691\u000b\u0016*F\u00036\u0003\u0013!\u0004)Z)\"{ejX*U%\u0016\u000bU*\u0001\bQ3RCuJT0T)J+\u0015)\u0014\u0011\u0002\u0015Q+U\nU0M\u001f\u000e\u000bE*A\u0006U\u000b6\u0003v\fT(D\u00032\u0003\u0013\u0001\u0004+F\u001bB{6\u000bS+G\r2+\u0015!\u0004+F\u001bB{6\u000bS+G\r2+\u0005%\u0001\u0003U\u000bN#\u0016!\u0002+F'R\u0003\u0013!B1qa2LHc\u0001#\u0002\u000e\")a\t\ra\u0001\u0011\"\u001a!\"a\u0007)\u0007%\tY\u0002"
)
public abstract class BlockId {
   public static BlockId apply(final String name) {
      return BlockId$.MODULE$.apply(name);
   }

   public static Regex TEST() {
      return BlockId$.MODULE$.TEST();
   }

   public static Regex TEMP_SHUFFLE() {
      return BlockId$.MODULE$.TEMP_SHUFFLE();
   }

   public static Regex TEMP_LOCAL() {
      return BlockId$.MODULE$.TEMP_LOCAL();
   }

   public static Regex PYTHON_STREAM() {
      return BlockId$.MODULE$.PYTHON_STREAM();
   }

   public static Regex STREAM() {
      return BlockId$.MODULE$.STREAM();
   }

   public static Regex TASKRESULT() {
      return BlockId$.MODULE$.TASKRESULT();
   }

   public static Regex BROADCAST() {
      return BlockId$.MODULE$.BROADCAST();
   }

   public static Regex SHUFFLE_CHUNK() {
      return BlockId$.MODULE$.SHUFFLE_CHUNK();
   }

   public static Regex SHUFFLE_MERGED_META() {
      return BlockId$.MODULE$.SHUFFLE_MERGED_META();
   }

   public static Regex SHUFFLE_MERGED_INDEX() {
      return BlockId$.MODULE$.SHUFFLE_MERGED_INDEX();
   }

   public static Regex SHUFFLE_MERGED_DATA() {
      return BlockId$.MODULE$.SHUFFLE_MERGED_DATA();
   }

   public static Regex SHUFFLE_MERGED() {
      return BlockId$.MODULE$.SHUFFLE_MERGED();
   }

   public static Regex SHUFFLE_PUSH() {
      return BlockId$.MODULE$.SHUFFLE_PUSH();
   }

   public static Regex SHUFFLE_INDEX() {
      return BlockId$.MODULE$.SHUFFLE_INDEX();
   }

   public static Regex SHUFFLE_DATA() {
      return BlockId$.MODULE$.SHUFFLE_DATA();
   }

   public static Regex SHUFFLE_BATCH() {
      return BlockId$.MODULE$.SHUFFLE_BATCH();
   }

   public static Regex SHUFFLE() {
      return BlockId$.MODULE$.SHUFFLE();
   }

   public static Regex RDD() {
      return BlockId$.MODULE$.RDD();
   }

   public abstract String name();

   public Option asRDDId() {
      return (Option)(this.isRDD() ? new Some((RDDBlockId)this) : .MODULE$);
   }

   public boolean isRDD() {
      return this instanceof RDDBlockId;
   }

   public boolean isShuffle() {
      return this instanceof ShuffleBlockId || this instanceof ShuffleBlockBatchId || this instanceof ShuffleDataBlockId || this instanceof ShuffleIndexBlockId;
   }

   public boolean isShuffleChunk() {
      return this instanceof ShuffleBlockChunkId;
   }

   public boolean isBroadcast() {
      return this instanceof BroadcastBlockId;
   }

   public String toString() {
      return this.name();
   }
}
