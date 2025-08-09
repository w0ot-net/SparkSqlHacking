package scala.xml;

import java.io.Serializable;
import scala.Some;
import scala.Tuple3;
import scala.runtime.ModuleSerializationProxy;

public final class UnprefixedAttribute$ implements Serializable {
   public static final UnprefixedAttribute$ MODULE$ = new UnprefixedAttribute$();

   public Some unapply(final UnprefixedAttribute x) {
      return new Some(new Tuple3(x.key(), x.value(), x.next()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnprefixedAttribute$.class);
   }

   private UnprefixedAttribute$() {
   }
}
