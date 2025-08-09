package scala;

import java.util.NoSuchElementException;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;
import scala.runtime.Statics;

public final class None$ extends Option {
   public static final None$ MODULE$ = new None$();
   private static final long serialVersionUID = 5066590221178148012L;

   public Nothing$ get() {
      throw new NoSuchElementException("None.get");
   }

   public String productPrefix() {
      return "None";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return new AbstractIterator(this) {
         private int c;
         private final int cmax;
         private final Product x$2;

         public boolean hasNext() {
            return this.c < this.cmax;
         }

         public Object next() {
            Object result = this.x$2.productElement(this.c);
            ++this.c;
            return result;
         }

         public {
            this.x$2 = x$2;
            this.c = 0;
            this.cmax = x$2.productArity();
         }
      };
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof None$;
   }

   public int hashCode() {
      return 2433880;
   }

   public String toString() {
      return "None";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(None$.class);
   }

   private None$() {
   }
}
