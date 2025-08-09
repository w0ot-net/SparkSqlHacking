package scala.collection.immutable;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.runtime.ModuleSerializationProxy;

public class TreeSeqMap$Ordering$Bin$ implements Serializable {
   public static final TreeSeqMap$Ordering$Bin$ MODULE$ = new TreeSeqMap$Ordering$Bin$();

   public final String toString() {
      return "Bin";
   }

   public TreeSeqMap$Ordering$Bin apply(final int prefix, final int mask, final TreeSeqMap.Ordering left, final TreeSeqMap.Ordering right) {
      return new TreeSeqMap$Ordering$Bin(prefix, mask, left, right);
   }

   public Option unapply(final TreeSeqMap$Ordering$Bin x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple4(x$0.prefix(), x$0.mask(), x$0.left(), x$0.right())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeSeqMap$Ordering$Bin$.class);
   }
}
