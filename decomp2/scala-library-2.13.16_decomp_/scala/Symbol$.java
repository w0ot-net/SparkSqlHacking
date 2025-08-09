package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Symbol$ extends UniquenessCache implements Serializable {
   public static final Symbol$ MODULE$ = new Symbol$();

   public Symbol apply(final String name) {
      return (Symbol)super.apply(name);
   }

   public Symbol valueFromKey(final String name) {
      return new Symbol(name);
   }

   public Option keyFromValue(final Symbol sym) {
      return new Some(sym.name());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Symbol$.class);
   }

   private Symbol$() {
   }
}
