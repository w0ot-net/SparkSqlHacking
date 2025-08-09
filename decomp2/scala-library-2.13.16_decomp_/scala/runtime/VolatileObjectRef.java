package scala.runtime;

import java.io.Serializable;

public final class VolatileObjectRef implements Serializable {
   private static final long serialVersionUID = -9055728157600312291L;
   public volatile Object elem;

   public VolatileObjectRef(Object elem) {
      this.elem = elem;
   }

   public String toString() {
      return String.valueOf(this.elem);
   }

   public static VolatileObjectRef create(Object e) {
      return new VolatileObjectRef(e);
   }

   public static VolatileObjectRef zero() {
      return new VolatileObjectRef((Object)null);
   }
}
