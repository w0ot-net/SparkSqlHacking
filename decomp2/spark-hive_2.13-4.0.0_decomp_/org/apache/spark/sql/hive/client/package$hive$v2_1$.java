package org.apache.spark.sql.hive.client;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class package$hive$v2_1$ extends package.HiveVersion implements Product, Serializable {
   public static final package$hive$v2_1$ MODULE$ = new package$hive$v2_1$();

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
      return "v2_1";
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
      return x$1 instanceof package$hive$v2_1$;
   }

   public int hashCode() {
      return 3566382;
   }

   public String toString() {
      return "v2_1";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$hive$v2_1$.class);
   }

   public package$hive$v2_1$() {
      String x$1 = "2.1.1";
      Seq x$2 = new scala.collection.immutable..colon.colon("org.apache.calcite:calcite-core", new scala.collection.immutable..colon.colon("org.apache.calcite:calcite-avatica", new scala.collection.immutable..colon.colon("org.apache.curator:*", new scala.collection.immutable..colon.colon("org.pentaho:pentaho-aggdesigner-algorithm", scala.collection.immutable.Nil..MODULE$))));
      Seq x$3 = package.HiveVersion$.MODULE$.$lessinit$greater$default$2();
      super("2.1.1", x$3, x$2);
   }
}
