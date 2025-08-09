package scala.xml.dtd;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class REQUIRED$ extends DefaultDecl implements Product, Serializable {
   public static final REQUIRED$ MODULE$ = new REQUIRED$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String toString() {
      return "#REQUIRED";
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append("#REQUIRED");
   }

   public String productPrefix() {
      return "REQUIRED";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof REQUIRED$;
   }

   public int hashCode() {
      return 389487519;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(REQUIRED$.class);
   }

   private REQUIRED$() {
   }
}
