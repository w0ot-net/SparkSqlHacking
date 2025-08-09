package scala.xml;

import java.io.Serializable;
import scala.Some;
import scala.Tuple4;
import scala.runtime.ModuleSerializationProxy;

public final class PrefixedAttribute$ implements Serializable {
   public static final PrefixedAttribute$ MODULE$ = new PrefixedAttribute$();

   public Some unapply(final PrefixedAttribute x) {
      return new Some(new Tuple4(x.pre(), x.key(), x.value(), x.next()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PrefixedAttribute$.class);
   }

   private PrefixedAttribute$() {
   }
}
