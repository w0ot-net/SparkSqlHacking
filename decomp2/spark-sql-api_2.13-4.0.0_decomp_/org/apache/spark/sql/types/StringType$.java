package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Stable
public final class StringType$ extends StringType implements Product {
   public static final StringType$ MODULE$ = new StringType$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public StringConstraint $lessinit$greater$default$2() {
      return NoConstraint$.MODULE$;
   }

   public StringType apply(final int collationId) {
      return new StringType(collationId, this.$lessinit$greater$default$2());
   }

   public StringType apply(final String collation) {
      int collationId = CollationFactory.collationNameToId(collation);
      return new StringType(collationId, this.$lessinit$greater$default$2());
   }

   public String productPrefix() {
      return "StringType";
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
      return x$1 instanceof StringType$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringType$.class);
   }

   private StringType$() {
      super(CollationFactory.UTF8_BINARY_COLLATION_ID, NoConstraint$.MODULE$);
   }
}
