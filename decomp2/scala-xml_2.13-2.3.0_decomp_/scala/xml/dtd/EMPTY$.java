package scala.xml.dtd;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class EMPTY$ extends ContentModel implements Product, Serializable {
   public static final EMPTY$ MODULE$ = new EMPTY$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append("EMPTY");
   }

   public String productPrefix() {
      return "EMPTY";
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
      return x$1 instanceof EMPTY$;
   }

   public int hashCode() {
      return 66096429;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EMPTY$.class);
   }

   private EMPTY$() {
   }
}
