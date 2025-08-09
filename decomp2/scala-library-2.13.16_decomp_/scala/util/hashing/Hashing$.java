package scala.util.hashing;

import java.io.Serializable;
import scala.Function1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Hashing$ implements Serializable {
   public static final Hashing$ MODULE$ = new Hashing$();

   public Hashing.Default default() {
      return new Hashing.Default();
   }

   public Hashing fromFunction(final Function1 f) {
      return new Hashing(f) {
         private final Function1 f$1;

         public int hash(final Object x) {
            return BoxesRunTime.unboxToInt(this.f$1.apply(x));
         }

         public {
            this.f$1 = f$1;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Hashing$.class);
   }

   private Hashing$() {
   }
}
