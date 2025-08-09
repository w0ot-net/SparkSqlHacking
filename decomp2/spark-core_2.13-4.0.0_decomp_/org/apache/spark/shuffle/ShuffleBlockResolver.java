package org.apache.spark.shuffle;

import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.shuffle.MergedBlockMeta;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.ShuffleMergedBlockId;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0005\u0005\u00063\u0001!\taG\u0003\u0005?\u0001\u0001\u0001\u0005C\u0003$\u0001\u0019\u0005A\u0005C\u0004I\u0001E\u0005I\u0011A%\t\u000bQ\u0003A\u0011A+\t\u000b\u0019\u0004a\u0011A4\t\u000b9\u0004a\u0011A8\t\u000b]\u0004a\u0011A\u000e\u0003)MCWO\u001a4mK\ncwnY6SKN|GN^3s\u0015\tYA\"A\u0004tQV4g\r\\3\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\"\u0001A\n\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u000f\u0011\u0005Qi\u0012B\u0001\u0010\u0016\u0005\u0011)f.\u001b;\u0003\u0013MCWO\u001a4mK&#\u0007C\u0001\u000b\"\u0013\t\u0011SCA\u0002J]R\fAbZ3u\u00052|7m\u001b#bi\u0006$2!J\u00176!\t13&D\u0001(\u0015\tA\u0013&\u0001\u0004ck\u001a4WM\u001d\u0006\u0003U1\tqA\\3uo>\u00148.\u0003\u0002-O\tiQ*\u00198bO\u0016$')\u001e4gKJDQAL\u0002A\u0002=\nqA\u00197pG.LE\r\u0005\u00021g5\t\u0011G\u0003\u00023\u0019\u000591\u000f^8sC\u001e,\u0017B\u0001\u001b2\u0005\u001d\u0011En\\2l\u0013\u0012DqAN\u0002\u0011\u0002\u0003\u0007q'\u0001\u0003eSJ\u001c\bc\u0001\u000b9u%\u0011\u0011(\u0006\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007QYT(\u0003\u0002=+\t)\u0011I\u001d:bsB\u0011a(\u0012\b\u0003\u007f\r\u0003\"\u0001Q\u000b\u000e\u0003\u0005S!A\u0011\u000e\u0002\rq\u0012xn\u001c;?\u0013\t!U#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\r\u001e\u0013aa\u0015;sS:<'B\u0001#\u0016\u0003Y9W\r\u001e\"m_\u000e\\G)\u0019;bI\u0011,g-Y;mi\u0012\u0012T#\u0001&+\u0005]Z5&\u0001'\u0011\u00055\u0013V\"\u0001(\u000b\u0005=\u0003\u0016!C;oG\",7m[3e\u0015\t\tV#\u0001\u0006b]:|G/\u0019;j_:L!a\u0015(\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\nhKR\u0014En\\2lg\u001a{'o\u00155vM\u001adW\rF\u0002W?\u0006\u00042a\u0016/0\u001d\tA&L\u0004\u0002A3&\ta#\u0003\u0002\\+\u00059\u0001/Y2lC\u001e,\u0017BA/_\u0005\r\u0019V-\u001d\u0006\u00037VAQ\u0001Y\u0003A\u0002\u0001\n\u0011b\u001d5vM\u001adW-\u00133\t\u000b\t,\u0001\u0019A2\u0002\u000b5\f\u0007/\u00133\u0011\u0005Q!\u0017BA3\u0016\u0005\u0011auN\\4\u0002%\u001d,G/T3sO\u0016$'\t\\8dW\u0012\u000bG/\u0019\u000b\u0004Q&l\u0007cA,]K!)aF\u0002a\u0001UB\u0011\u0001g[\u0005\u0003YF\u0012Ac\u00155vM\u001adW-T3sO\u0016$'\t\\8dW&#\u0007\"\u0002\u001c\u0007\u0001\u00049\u0014AE4fi6+'oZ3e\u00052|7m['fi\u0006$2\u0001];w!\t\t8/D\u0001s\u0015\tY\u0011&\u0003\u0002ue\nyQ*\u001a:hK\u0012\u0014En\\2l\u001b\u0016$\u0018\rC\u0003/\u000f\u0001\u0007!\u000eC\u00037\u000f\u0001\u0007q'\u0001\u0003ti>\u0004\b"
)
public interface ShuffleBlockResolver {
   ManagedBuffer getBlockData(final BlockId blockId, final Option dirs);

   // $FF: synthetic method
   static Option getBlockData$default$2$(final ShuffleBlockResolver $this) {
      return $this.getBlockData$default$2();
   }

   default Option getBlockData$default$2() {
      return .MODULE$;
   }

   // $FF: synthetic method
   static Seq getBlocksForShuffle$(final ShuffleBlockResolver $this, final int shuffleId, final long mapId) {
      return $this.getBlocksForShuffle(shuffleId, mapId);
   }

   default Seq getBlocksForShuffle(final int shuffleId, final long mapId) {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   Seq getMergedBlockData(final ShuffleMergedBlockId blockId, final Option dirs);

   MergedBlockMeta getMergedBlockMeta(final ShuffleMergedBlockId blockId, final Option dirs);

   void stop();

   static void $init$(final ShuffleBlockResolver $this) {
   }
}
