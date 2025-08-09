package org.apache.spark.sql.types;

import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class IndeterminateStringType$ extends StringType implements Product {
   public static final IndeterminateStringType$ MODULE$ = new IndeterminateStringType$();

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
      return "IndeterminateStringType";
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
      return x$1 instanceof IndeterminateStringType$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IndeterminateStringType$.class);
   }

   private IndeterminateStringType$() {
      super(-1, StringType$.MODULE$.$lessinit$greater$default$2());
   }
}
