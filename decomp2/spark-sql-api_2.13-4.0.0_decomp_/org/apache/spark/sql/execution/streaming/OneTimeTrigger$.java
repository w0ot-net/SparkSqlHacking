package org.apache.spark.sql.execution.streaming;

import java.io.Serializable;
import org.apache.spark.sql.streaming.Trigger;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class OneTimeTrigger$ extends Trigger implements Product, Serializable {
   public static final OneTimeTrigger$ MODULE$ = new OneTimeTrigger$();

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
      return "OneTimeTrigger";
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
      return x$1 instanceof OneTimeTrigger$;
   }

   public int hashCode() {
      return -2029767675;
   }

   public String toString() {
      return "OneTimeTrigger";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneTimeTrigger$.class);
   }

   private OneTimeTrigger$() {
   }
}
