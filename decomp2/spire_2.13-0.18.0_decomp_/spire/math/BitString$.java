package spire.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class BitString$ implements Serializable {
   public static final BitString$ MODULE$ = new BitString$();

   public BitString apply(final BitString ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BitString$.class);
   }

   private BitString$() {
   }
}
