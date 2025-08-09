package org.apache.spark.storage;

import scala.Enumeration;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193Q\u0001C\u0005\u0001\u0017EA\u0001\u0002\u0007\u0001\u0003\u0006\u0004%\tA\u0007\u0005\tU\u0001\u0011\t\u0011)A\u00057!A1\u0006\u0001BC\u0002\u0013\u0005A\u0006\u0003\u00059\u0001\t\u0005\t\u0015!\u0003.\u0011!I\u0004A!b\u0001\n\u0003Q\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u000b}\u0002A\u0011\u0001!\u0003\u0017\tcwnY6SKN,H\u000e\u001e\u0006\u0003\u0015-\tqa\u001d;pe\u0006<WM\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\t\u0001!\u0003\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VMZ\u0001\u0005I\u0006$\u0018m\u0001\u0001\u0016\u0003m\u00012\u0001\b\u0013(\u001d\ti\"E\u0004\u0002\u001fC5\tqD\u0003\u0002!3\u00051AH]8pizJ\u0011!F\u0005\u0003GQ\tq\u0001]1dW\u0006<W-\u0003\u0002&M\tA\u0011\n^3sCR|'O\u0003\u0002$)A\u00111\u0003K\u0005\u0003SQ\u00111!\u00118z\u0003\u0015!\u0017\r^1!\u0003)\u0011X-\u00193NKRDw\u000eZ\u000b\u0002[A\u0011a\u0006\u000e\b\u0003_Ij\u0011\u0001\r\u0006\u0003c-\t\u0001\"\u001a=fGV$xN]\u0005\u0003gA\na\u0002R1uCJ+\u0017\rZ'fi\"|G-\u0003\u00026m\t)a+\u00197vK&\u0011q\u0007\u0006\u0002\f\u000b:,X.\u001a:bi&|g.A\u0006sK\u0006$W*\u001a;i_\u0012\u0004\u0013!\u00022zi\u0016\u001cX#A\u001e\u0011\u0005Ma\u0014BA\u001f\u0015\u0005\u0011auN\\4\u0002\r\tLH/Z:!\u0003\u0019a\u0014N\\5u}Q!\u0011i\u0011#F!\t\u0011\u0005!D\u0001\n\u0011\u0015Ar\u00011\u0001\u001c\u0011\u0015Ys\u00011\u0001.\u0011\u0015It\u00011\u0001<\u0001"
)
public class BlockResult {
   private final Iterator data;
   private final Enumeration.Value readMethod;
   private final long bytes;

   public Iterator data() {
      return this.data;
   }

   public Enumeration.Value readMethod() {
      return this.readMethod;
   }

   public long bytes() {
      return this.bytes;
   }

   public BlockResult(final Iterator data, final Enumeration.Value readMethod, final long bytes) {
      this.data = data;
      this.readMethod = readMethod;
      this.bytes = bytes;
   }
}
