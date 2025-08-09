package org.apache.spark.deploy.k8s;

import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import scala.Enumeration;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class SparkKubernetesClientFactory$ClientType$Val extends Enumeration.Val implements Product {
   private final ConfigEntry requestTimeoutEntry;
   private final ConfigEntry connectionTimeoutEntry;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ConfigEntry requestTimeoutEntry() {
      return this.requestTimeoutEntry;
   }

   public ConfigEntry connectionTimeoutEntry() {
      return this.connectionTimeoutEntry;
   }

   public int requestTimeout(final SparkConf conf) {
      return BoxesRunTime.unboxToInt(conf.get(this.requestTimeoutEntry()));
   }

   public int connectionTimeout(final SparkConf conf) {
      return BoxesRunTime.unboxToInt(conf.get(this.connectionTimeoutEntry()));
   }

   public SparkKubernetesClientFactory$ClientType$Val copy(final ConfigEntry requestTimeoutEntry, final ConfigEntry connectionTimeoutEntry) {
      return new SparkKubernetesClientFactory$ClientType$Val(requestTimeoutEntry, connectionTimeoutEntry);
   }

   public ConfigEntry copy$default$1() {
      return this.requestTimeoutEntry();
   }

   public ConfigEntry copy$default$2() {
      return this.connectionTimeoutEntry();
   }

   public String productPrefix() {
      return "Val";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.requestTimeoutEntry();
         }
         case 1 -> {
            return this.connectionTimeoutEntry();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof SparkKubernetesClientFactory$ClientType$Val;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "requestTimeoutEntry";
         }
         case 1 -> {
            return "connectionTimeoutEntry";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public SparkKubernetesClientFactory$ClientType$Val(final ConfigEntry requestTimeoutEntry, final ConfigEntry connectionTimeoutEntry) {
      super(SparkKubernetesClientFactory.ClientType$.MODULE$);
      this.requestTimeoutEntry = requestTimeoutEntry;
      this.connectionTimeoutEntry = connectionTimeoutEntry;
      Product.$init$(this);
   }
}
