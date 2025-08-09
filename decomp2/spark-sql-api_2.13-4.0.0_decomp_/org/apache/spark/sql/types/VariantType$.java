package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Unstable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Unstable
public final class VariantType$ extends VariantType implements Product, Serializable {
   public static final VariantType$ MODULE$ = new VariantType$();

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
      return "VariantType";
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
      return x$1 instanceof VariantType$;
   }

   public int hashCode() {
      return 66646335;
   }

   public String toString() {
      return "VariantType";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VariantType$.class);
   }

   private VariantType$() {
   }
}
