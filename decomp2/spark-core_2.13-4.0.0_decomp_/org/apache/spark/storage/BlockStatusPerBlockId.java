package org.apache.spark.storage;

import java.util.HashMap;
import scala.Option;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q\u0001C\u0005\u0001\u0017EAQ\u0001\u0007\u0001\u0005\u0002iA\u0011\"\b\u0001A\u0002\u0003\u0007I\u0011\u0002\u0010\t\u00135\u0002\u0001\u0019!a\u0001\n\u0013q\u0003\"\u0003\u001b\u0001\u0001\u0004\u0005\t\u0015)\u0003 \u0011\u0015)\u0004\u0001\"\u00017\u0011\u0015a\u0004\u0001\"\u0001>\u0011\u0015\t\u0005\u0001\"\u0001C\u0005U\u0011En\\2l'R\fG/^:QKJ\u0014En\\2l\u0013\u0012T!AC\u0006\u0002\u000fM$xN]1hK*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0002\u0001%A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u00027A\u0011A\u0004A\u0007\u0002\u0013\u00051!\r\\8dWN,\u0012a\b\t\u0005A\u0015:#&D\u0001\"\u0015\t\u00113%\u0001\u0003vi&d'\"\u0001\u0013\u0002\t)\fg/Y\u0005\u0003M\u0005\u0012q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0002\u001dQ%\u0011\u0011&\u0003\u0002\b\u00052|7m[%e!\ta2&\u0003\u0002-\u0013\tY!\t\\8dWN#\u0018\r^;t\u0003)\u0011Gn\\2lg~#S-\u001d\u000b\u0003_I\u0002\"a\u0005\u0019\n\u0005E\"\"\u0001B+oSRDqaM\u0002\u0002\u0002\u0003\u0007q$A\u0002yIE\nqA\u00197pG.\u001c\b%A\u0002hKR$\"a\u000e\u001e\u0011\u0007MA$&\u0003\u0002:)\t1q\n\u001d;j_:DQaO\u0003A\u0002\u001d\nqA\u00197pG.LE-A\u0002qkR$2a\f @\u0011\u0015Yd\u00011\u0001(\u0011\u0015\u0001e\u00011\u0001+\u0003-\u0011Gn\\2l'R\fG/^:\u0002\rI,Wn\u001c<f)\ty3\tC\u0003<\u000f\u0001\u0007q\u0005"
)
public class BlockStatusPerBlockId {
   private HashMap blocks;

   private HashMap blocks() {
      return this.blocks;
   }

   private void blocks_$eq(final HashMap x$1) {
      this.blocks = x$1;
   }

   public Option get(final BlockId blockId) {
      return (Option)(this.blocks() == null ? .MODULE$ : scala.Option..MODULE$.apply(this.blocks().get(blockId)));
   }

   public void put(final BlockId blockId, final BlockStatus blockStatus) {
      if (this.blocks() == null) {
         this.blocks_$eq(new HashMap());
      }

      this.blocks().put(blockId, blockStatus);
   }

   public void remove(final BlockId blockId) {
      if (this.blocks() != null) {
         this.blocks().remove(blockId);
         if (this.blocks().isEmpty()) {
            this.blocks_$eq((HashMap)null);
         }
      }
   }
}
