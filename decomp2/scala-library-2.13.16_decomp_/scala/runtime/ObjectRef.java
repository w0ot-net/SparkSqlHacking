package scala.runtime;

import java.io.Serializable;

public final class ObjectRef implements Serializable {
   private static final long serialVersionUID = -9055728157600312291L;
   public Object elem;

   public ObjectRef(Object elem) {
      this.elem = elem;
   }

   public String toString() {
      return String.valueOf(this.elem);
   }

   public static ObjectRef create(Object e) {
      return new ObjectRef(e);
   }

   public static ObjectRef zero() {
      return new ObjectRef((Object)null);
   }
}
