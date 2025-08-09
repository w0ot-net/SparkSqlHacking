package scala.collection.immutable;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.None$;
import scala.Product;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.runtime.Nothing$;
import scala.runtime.Statics;

public final class Nil$ extends List implements Product {
   public static final Nil$ MODULE$ = new Nil$();
   private static final transient Tuple2 EmptyUnzip;

   static {
      Nil$ var10000 = MODULE$;
      EmptyUnzip = new Tuple2(MODULE$, MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Nothing$ head() {
      throw new NoSuchElementException("head of empty list");
   }

   public None$ headOption() {
      return None$.MODULE$;
   }

   public Nothing$ tail() {
      throw new UnsupportedOperationException("tail of empty list");
   }

   public Nothing$ last() {
      throw new NoSuchElementException("last of empty list");
   }

   public Nothing$ init() {
      throw new UnsupportedOperationException("init of empty list");
   }

   public int knownSize() {
      return 0;
   }

   public Iterator iterator() {
      Iterator$ var10000 = Iterator$.MODULE$;
      return Iterator$.scala$collection$Iterator$$_empty;
   }

   public Tuple2 unzip(final Function1 asPair) {
      return EmptyUnzip;
   }

   public String productPrefix() {
      return "Nil";
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

   private Nil$() {
   }
}
