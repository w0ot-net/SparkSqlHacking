package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Stable
public final class IntegerType$ extends IntegerType implements Product, Serializable {
   public static final IntegerType$ MODULE$ = new IntegerType$();

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
      return "IntegerType";
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
      return x$1 instanceof IntegerType$;
   }

   public int hashCode() {
      return -1828168328;
   }

   public String toString() {
      return "IntegerType";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IntegerType$.class);
   }

   private IntegerType$() {
   }
}
