package org.apache.spark.sql.hive.client;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class package$hive$v4_0$ extends package.HiveVersion implements Product, Serializable {
   public static final package$hive$v4_0$ MODULE$ = new package$hive$v4_0$();

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
      return "v4_0";
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
      return x$1 instanceof package$hive$v4_0$;
   }

   public int hashCode() {
      return 3568303;
   }

   public String toString() {
      return "v4_0";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(package$hive$v4_0$.class);
   }

   public package$hive$v4_0$() {
      super("4.0.1", (Seq)scala.package..MODULE$.Seq().apply(.MODULE$.wrapRefArray((Object[])(new String[]{"org.apache.hadoop:hadoop-hdfs:3.3.6", "org.datanucleus:datanucleus-api-jdo:5.2.8", "org.datanucleus:datanucleus-rdbms:5.2.10", "org.datanucleus:javax.jdo:3.2.0-release", "org.springframework:spring-core:5.3.21", "org.springframework:spring-jdbc:5.3.21", "org.antlr:antlr4-runtime:4.9.3", "org.apache.derby:derby:10.14.2.0"}))), new scala.collection.immutable..colon.colon("org.apache.calcite:calcite-druid", new scala.collection.immutable..colon.colon("org.apache.curator:*", new scala.collection.immutable..colon.colon("org.pentaho:pentaho-aggdesigner-algorithm", new scala.collection.immutable..colon.colon("org.apache.hive:hive-vector-code-gen", scala.collection.immutable.Nil..MODULE$)))));
   }
}
