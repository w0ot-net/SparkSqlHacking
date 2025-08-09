package scala.collection.immutable;

import java.io.Serializable;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.runtime.ModuleSerializationProxy;

public class TreeSeqMap$Ordering$Tip$ implements Serializable {
   public static final TreeSeqMap$Ordering$Tip$ MODULE$ = new TreeSeqMap$Ordering$Tip$();

   public final String toString() {
      return "Tip";
   }

   public TreeSeqMap$Ordering$Tip apply(final int ord, final Object value) {
      return new TreeSeqMap$Ordering$Tip(ord, value);
   }

   public Option unapply(final TreeSeqMap$Ordering$Tip x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2(x$0.ord(), x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeSeqMap$Ordering$Tip$.class);
   }
}
