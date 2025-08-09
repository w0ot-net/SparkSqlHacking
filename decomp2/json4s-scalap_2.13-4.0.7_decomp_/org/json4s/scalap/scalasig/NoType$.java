package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class NoType$ extends Type implements Product, Serializable {
   public static final NoType$ MODULE$ = new NoType$();

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
      return "NoType";
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
      return x$1 instanceof NoType$;
   }

   public int hashCode() {
      return -1956760389;
   }

   public String toString() {
      return "NoType";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NoType$.class);
   }

   private NoType$() {
   }
}
