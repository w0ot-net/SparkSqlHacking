package org.apache.spark.rdd;

import org.apache.spark.Partition;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2Q\u0001C\u0005\u0001\u0017EA\u0001\"\u000b\u0001\u0003\u0006\u0004%\tA\u000b\u0005\tW\u0001\u0011\t\u0011)A\u00051!AA\u0006\u0001BC\u0002\u0013\u0005Q\u0006\u0003\u00052\u0001\t\u0005\t\u0015!\u0003/\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u001dA\u0004A1A\u0005BeBa!\u0010\u0001!\u0002\u0013Q$a\u0007.jaB,GmV5uQ&sG-\u001a=S\t\u0012\u0003\u0016M\u001d;ji&|gN\u0003\u0002\u000b\u0017\u0005\u0019!\u000f\u001a3\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001cB\u0001\u0001\n\u00199A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000e\u000e\u0003-I!aG\u0006\u0003\u0013A\u000b'\u000f^5uS>t\u0007CA\u000f'\u001d\tqBE\u0004\u0002 G5\t\u0001E\u0003\u0002\"E\u00051AH]8piz\u001a\u0001!C\u0001\u0016\u0013\t)C#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dB#\u0001D*fe&\fG.\u001b>bE2,'BA\u0013\u0015\u0003\u0011\u0001(/\u001a<\u0016\u0003a\tQ\u0001\u001d:fm\u0002\n!b\u001d;beRLe\u000eZ3y+\u0005q\u0003CA\n0\u0013\t\u0001DC\u0001\u0003M_:<\u0017aC:uCJ$\u0018J\u001c3fq\u0002\na\u0001P5oSRtDc\u0001\u001b7oA\u0011Q\u0007A\u0007\u0002\u0013!)\u0011&\u0002a\u00011!)A&\u0002a\u0001]\u0005)\u0011N\u001c3fqV\t!\b\u0005\u0002\u0014w%\u0011A\b\u0006\u0002\u0004\u0013:$\u0018AB5oI\u0016D\b\u0005"
)
public class ZippedWithIndexRDDPartition implements Partition {
   private final Partition prev;
   private final long startIndex;
   private final int index;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int hashCode() {
      return Partition.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public Partition prev() {
      return this.prev;
   }

   public long startIndex() {
      return this.startIndex;
   }

   public int index() {
      return this.index;
   }

   public ZippedWithIndexRDDPartition(final Partition prev, final long startIndex) {
      this.prev = prev;
      this.startIndex = startIndex;
      Partition.$init$(this);
      this.index = prev.index();
   }
}
