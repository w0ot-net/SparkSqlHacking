package org.apache.spark.ml.attribute;

import org.apache.spark.sql.types.Metadata;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class UnresolvedAttribute$ extends Attribute {
   public static final UnresolvedAttribute$ MODULE$ = new UnresolvedAttribute$();

   public AttributeType attrType() {
      return AttributeType$.MODULE$.Unresolved();
   }

   public Attribute withIndex(final int index) {
      return this;
   }

   public boolean isNumeric() {
      return false;
   }

   public Attribute withoutIndex() {
      return this;
   }

   public boolean isNominal() {
      return false;
   }

   public Option name() {
      return .MODULE$;
   }

   public Metadata toMetadataImpl(final boolean withType) {
      return org.apache.spark.sql.types.Metadata..MODULE$.empty();
   }

   public Attribute withoutName() {
      return this;
   }

   public Option index() {
      return .MODULE$;
   }

   public Attribute withName(final String name) {
      return this;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnresolvedAttribute$.class);
   }

   private UnresolvedAttribute$() {
   }
}
