package org.apache.spark.status;

import org.apache.spark.util.kvstore.KVIndex;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3QAB\u0004\u0001\u0013=A\u0001B\u0006\u0001\u0003\u0006\u0004%\t\u0001\u0007\u0005\to\u0001\u0011\t\u0011)A\u00053!A\u0001\b\u0001BC\u0002\u0013\u0005\u0011\b\u0003\u0005A\u0001\t\u0005\t\u0015!\u0003;\u0011\u0015\t\u0005\u0001\"\u0001C\u0005!\u0001vn\u001c7ECR\f'B\u0001\u0005\n\u0003\u0019\u0019H/\u0019;vg*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0005\u0002\u0001!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\fAA\\1nK\u000e\u0001Q#A\r\u0011\u0005i\tcBA\u000e !\ta\"#D\u0001\u001e\u0015\tqr#\u0001\u0004=e>|GOP\u0005\u0003AI\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0012$\u0005\u0019\u0019FO]5oO*\u0011\u0001E\u0005\u0015\u0003\u0003\u0015R#A\n\u0018\u0011\u0005\u001dbS\"\u0001\u0015\u000b\u0005%R\u0013aB6wgR|'/\u001a\u0006\u0003W%\tA!\u001e;jY&\u0011Q\u0006\u000b\u0002\b\u0017ZKe\u000eZ3yW\u0005y\u0003C\u0001\u00196\u001b\u0005\t$B\u0001\u001a4\u0003\u0011iW\r^1\u000b\u0005Q\u0012\u0012AC1o]>$\u0018\r^5p]&\u0011a'\r\u0002\u0007O\u0016$H/\u001a:\u0002\u000b9\fW.\u001a\u0011\u0002\u0011M$\u0018mZ3JIN,\u0012A\u000f\t\u00045mj\u0014B\u0001\u001f$\u0005\r\u0019V\r\u001e\t\u0003#yJ!a\u0010\n\u0003\u0007%sG/A\u0005ti\u0006<W-\u00133tA\u00051A(\u001b8jiz\"2aQ#G!\t!\u0005!D\u0001\b\u0011\u00151R\u00011\u0001\u001a\u0011\u0015AT\u00011\u0001;\u0001"
)
public class PoolData {
   private final String name;
   private final Set stageIds;

   @KVIndex
   public String name() {
      return this.name;
   }

   public Set stageIds() {
      return this.stageIds;
   }

   public PoolData(final String name, final Set stageIds) {
      this.name = name;
      this.stageIds = stageIds;
   }
}
