package org.apache.spark.shuffle;

import org.apache.spark.annotation.Experimental;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u000514qAB\u0004\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0019\u0005Q\u0004C\u0003/\u0001\u0011\u0005q\u0006C\u00036\u0001\u0019\u0005a\u0007C\u0003P\u0001\u0019\u0005\u0001K\u0001\nNS\u001e\u0014\u0018\r^1cY\u0016\u0014Vm]8mm\u0016\u0014(B\u0001\u0005\n\u0003\u001d\u0019\b.\u001e4gY\u0016T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003e\u0001\"A\u0005\u000e\n\u0005m\u0019\"\u0001B+oSR\f\u0011cZ3u'R|'/\u001a3TQV4g\r\\3t)\u0005q\u0002cA\u0010(U9\u0011\u0001%\n\b\u0003C\u0011j\u0011A\t\u0006\u0003G=\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b\n\u0005\u0019\u001a\u0012a\u00029bG.\fw-Z\u0005\u0003Q%\u00121aU3r\u0015\t13\u0003\u0005\u0002,Y5\tq!\u0003\u0002.\u000f\t\u00012\u000b[;gM2,'\t\\8dW&sgm\\\u0001\u0011C\u0012$7\u000b[;gM2,Gk\\*lSB$\"!\u0007\u0019\t\u000bE\u001a\u0001\u0019\u0001\u001a\u0002\u0013MDWO\u001a4mK&#\u0007C\u0001\n4\u0013\t!4CA\u0002J]R\fq\u0003];u'\",hM\u001a7f\u00052|7m[!t'R\u0014X-Y7\u0015\u0007]zt\t\u0005\u00029{5\t\u0011H\u0003\u0002;w\u000511\r\\5f]RT!\u0001P\u0005\u0002\u000f9,Go^8sW&\u0011a(\u000f\u0002\u0015'R\u0014X-Y7DC2d'-Y2l/&$\b.\u0013#\t\u000b\u0001#\u0001\u0019A!\u0002\u000f\tdwnY6JIB\u0011!)R\u0007\u0002\u0007*\u0011A)C\u0001\bgR|'/Y4f\u0013\t15IA\u0004CY>\u001c7.\u00133\t\u000b!#\u0001\u0019A%\u0002#M,'/[1mSj,'/T1oC\u001e,'\u000f\u0005\u0002K\u001b6\t1J\u0003\u0002M\u0013\u0005Q1/\u001a:jC2L'0\u001a:\n\u00059[%!E*fe&\fG.\u001b>fe6\u000bg.Y4fe\u0006\u0011r-\u001a;NS\u001e\u0014\u0018\r^5p]\ncwnY6t)\t\tV\fE\u0002 %RK!aU\u0015\u0003\t1K7\u000f\u001e\t\u0005%U\u000bu+\u0003\u0002W'\t1A+\u001e9mKJ\u0002\"\u0001W.\u000e\u0003eS!AW\u001e\u0002\r\t,hMZ3s\u0013\ta\u0016LA\u0007NC:\fw-\u001a3Ck\u001a4WM\u001d\u0005\u0006=\u0016\u0001\rAK\u0001\u0011g\",hM\u001a7f\u00052|7m[%oM>D#\u0001\u00011\u0011\u0005\u0005$W\"\u00012\u000b\u0005\rL\u0011AC1o]>$\u0018\r^5p]&\u0011QM\u0019\u0002\r\u000bb\u0004XM]5nK:$\u0018\r\u001c\u0015\u0004\u0001\u001dT\u0007CA1i\u0013\tI'MA\u0003TS:\u001cW-I\u0001l\u0003\u0015\u0019d&\r\u00181\u0001"
)
public interface MigratableResolver {
   Seq getStoredShuffles();

   // $FF: synthetic method
   static void addShuffleToSkip$(final MigratableResolver $this, final int shuffleId) {
      $this.addShuffleToSkip(shuffleId);
   }

   default void addShuffleToSkip(final int shuffleId) {
   }

   StreamCallbackWithID putShuffleBlockAsStream(final BlockId blockId, final SerializerManager serializerManager);

   List getMigrationBlocks(final ShuffleBlockInfo shuffleBlockInfo);

   static void $init$(final MigratableResolver $this) {
   }
}
