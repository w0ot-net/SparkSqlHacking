package org.apache.spark.sql.catalyst.plans.logical;

import java.io.Serializable;
import org.apache.spark.sql.streaming.GroupStateTimeout;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class EventTimeTimeout$ extends GroupStateTimeout implements Product, Serializable {
   public static final EventTimeTimeout$ MODULE$ = new EventTimeTimeout$();

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
      return "EventTimeTimeout";
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
      return x$1 instanceof EventTimeTimeout$;
   }

   public int hashCode() {
      return -1949567238;
   }

   public String toString() {
      return "EventTimeTimeout";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EventTimeTimeout$.class);
   }

   private EventTimeTimeout$() {
   }
}
