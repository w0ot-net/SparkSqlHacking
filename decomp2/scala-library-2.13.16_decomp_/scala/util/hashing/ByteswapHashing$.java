package scala.util.hashing;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ByteswapHashing$ implements Serializable {
   public static final ByteswapHashing$ MODULE$ = new ByteswapHashing$();

   public Hashing chain(final Hashing h) {
      return new ByteswapHashing.Chained(h);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ByteswapHashing$.class);
   }

   private ByteswapHashing$() {
   }
}
