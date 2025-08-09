package org.apache.spark.sql.catalyst.util;

import scala.collection.immutable.Seq;

public final class AttributeNameParser$ implements AttributeNameParser {
   public static final AttributeNameParser$ MODULE$ = new AttributeNameParser$();

   static {
      AttributeNameParser.$init$(MODULE$);
   }

   public Seq parseAttributeName(final String name) {
      return AttributeNameParser.parseAttributeName$(this, name);
   }

   private AttributeNameParser$() {
   }
}
