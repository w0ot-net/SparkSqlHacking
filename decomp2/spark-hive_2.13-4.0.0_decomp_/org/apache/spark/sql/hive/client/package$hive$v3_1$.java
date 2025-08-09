package org.apache.spark.sql.hive.client;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class package$hive$v3_1$ extends package.HiveVersion implements Product, Serializable {
   public static final package$hive$v3_1$ MODULE$ = new package$hive$v3_1$();

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
      return "v3_1";
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
      return x$1 instanceof package$hive$v3_1$;
   }

   public int hashCode() {
      return 3567343;
   }

   public String toString() {
      return "v3_1";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$hive$v3_1$.class);
   }

   public package$hive$v3_1$() {
      super("3.1.3", new scala.collection.immutable..colon.colon("org.apache.logging.log4j:log4j-api:2.10.0", new scala.collection.immutable..colon.colon("org.apache.derby:derby:10.14.1.0", scala.collection.immutable.Nil..MODULE$)), new scala.collection.immutable..colon.colon("org.apache.calcite:calcite-druid", new scala.collection.immutable..colon.colon("org.apache.curator:*", new scala.collection.immutable..colon.colon("org.pentaho:pentaho-aggdesigner-algorithm", new scala.collection.immutable..colon.colon("org.apache.hive:hive-vector-code-gen", scala.collection.immutable.Nil..MODULE$)))));
   }
}
