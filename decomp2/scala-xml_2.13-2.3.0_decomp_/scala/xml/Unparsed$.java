package scala.xml;

import java.io.Serializable;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class Unparsed$ implements Serializable {
   public static final Unparsed$ MODULE$ = new Unparsed$();

   public Unparsed apply(final String data) {
      return new Unparsed(data);
   }

   public Some unapply(final Unparsed x) {
      return new Some(x.data());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Unparsed$.class);
   }

   private Unparsed$() {
   }
}
