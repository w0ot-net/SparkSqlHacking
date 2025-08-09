package org.apache.spark.rdd;

import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005q3Aa\u0003\u0007\u0001+!AA\u0004\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003\u001f\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u001d\u0011\u0004A1A\u0005\u0002MBa\u0001\u0011\u0001!\u0002\u0013!\u0004\"B!\u0001\t\u0003\u0011uaB'\r\u0003\u0003E\tA\u0014\u0004\b\u00171\t\t\u0011#\u0001P\u0011\u0015i\u0003\u0002\"\u0001Q\u0011\u001d\t\u0006\"%A\u0005\u0002I\u0013a\u0002U1si&$\u0018n\u001c8He>,\bO\u0003\u0002\u000e\u001d\u0005\u0019!\u000f\u001a3\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001-A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\fq\u0001\u001d:fM2{7-F\u0001\u001f!\r9r$I\u0005\u0003Aa\u0011aa\u00149uS>t\u0007C\u0001\u0012*\u001d\t\u0019s\u0005\u0005\u0002%15\tQE\u0003\u0002')\u00051AH]8pizJ!\u0001\u000b\r\u0002\rA\u0013X\rZ3g\u0013\tQ3F\u0001\u0004TiJLgn\u001a\u0006\u0003Qa\t\u0001\u0002\u001d:fM2{7\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005=\n\u0004C\u0001\u0019\u0001\u001b\u0005a\u0001b\u0002\u000f\u0004!\u0003\u0005\rAH\u0001\u000ba\u0006\u0014H/\u001b;j_:\u001cX#\u0001\u001b\u0011\u0007URD(D\u00017\u0015\t9\u0004(A\u0004nkR\f'\r\\3\u000b\u0005eB\u0012AC2pY2,7\r^5p]&\u00111H\u000e\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0005\u0002>}5\ta\"\u0003\u0002@\u001d\tI\u0001+\u0019:uSRLwN\\\u0001\fa\u0006\u0014H/\u001b;j_:\u001c\b%A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u000b\u0002\u0007B\u0011q\u0003R\u0005\u0003\u000bb\u00111!\u00138uQ\t\u0001q\t\u0005\u0002I\u00176\t\u0011J\u0003\u0002K\u001d\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00051K%\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017A\u0004)beRLG/[8o\u000fJ|W\u000f\u001d\t\u0003a!\u0019\"\u0001\u0003\f\u0015\u00039\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nT#A*+\u0005y!6&A+\u0011\u0005YSV\"A,\u000b\u0005aK\u0016!C;oG\",7m[3e\u0015\tQ\u0005$\u0003\u0002\\/\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class PartitionGroup {
   private final Option prefLoc;
   private final ArrayBuffer partitions;

   public static Option $lessinit$greater$default$1() {
      return PartitionGroup$.MODULE$.$lessinit$greater$default$1();
   }

   public Option prefLoc() {
      return this.prefLoc;
   }

   public ArrayBuffer partitions() {
      return this.partitions;
   }

   public int numPartitions() {
      return this.partitions().size();
   }

   public PartitionGroup(final Option prefLoc) {
      this.prefLoc = prefLoc;
      this.partitions = (ArrayBuffer).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
