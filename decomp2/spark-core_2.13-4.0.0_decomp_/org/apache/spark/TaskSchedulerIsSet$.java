package org.apache.spark;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class TaskSchedulerIsSet$ implements Product, Serializable {
   public static final TaskSchedulerIsSet$ MODULE$ = new TaskSchedulerIsSet$();

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
      return "TaskSchedulerIsSet";
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
      return x$1 instanceof TaskSchedulerIsSet$;
   }

   public int hashCode() {
      return -234951710;
   }

   public String toString() {
      return "TaskSchedulerIsSet";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskSchedulerIsSet$.class);
   }

   private TaskSchedulerIsSet$() {
   }
}
