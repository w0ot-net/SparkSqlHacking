package scala.xml.dtd;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class IMPLIED$ extends DefaultDecl implements Product, Serializable {
   public static final IMPLIED$ MODULE$ = new IMPLIED$();

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
      return "#IMPLIED";
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append("#IMPLIED");
   }

   public String productPrefix() {
      return "IMPLIED";
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
      return x$1 instanceof IMPLIED$;
   }

   public int hashCode() {
      return -1651045240;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IMPLIED$.class);
   }

   private IMPLIED$() {
   }
}
