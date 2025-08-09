package breeze.linalg;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class CompleteSVD$ extends SVDMode implements Product, Serializable {
   public static final CompleteSVD$ MODULE$ = new CompleteSVD$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String productPrefix() {
      return "CompleteSVD";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof CompleteSVD$;
   }

   public int hashCode() {
      return 2070282824;
   }

   public String toString() {
      return "CompleteSVD";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CompleteSVD$.class);
   }

   private CompleteSVD$() {
      super("A");
   }
}
