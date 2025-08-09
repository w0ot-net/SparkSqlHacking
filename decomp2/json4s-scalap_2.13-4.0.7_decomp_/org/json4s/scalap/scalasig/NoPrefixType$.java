package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class NoPrefixType$ extends Type implements Product, Serializable {
   public static final NoPrefixType$ MODULE$ = new NoPrefixType$();

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
      return "NoPrefixType";
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
      return x$1 instanceof NoPrefixType$;
   }

   public int hashCode() {
      return 238607789;
   }

   public String toString() {
      return "NoPrefixType";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NoPrefixType$.class);
   }

   private NoPrefixType$() {
   }
}
