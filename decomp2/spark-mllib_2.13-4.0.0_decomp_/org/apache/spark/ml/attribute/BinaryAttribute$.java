package org.apache.spark.ml.attribute;

import java.io.Serializable;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BinaryAttribute$ implements AttributeFactory, Serializable {
   public static final BinaryAttribute$ MODULE$ = new BinaryAttribute$();
   private static final BinaryAttribute defaultAttr;

   static {
      AttributeFactory.$init$(MODULE$);
      defaultAttr = new BinaryAttribute(MODULE$.$lessinit$greater$default$1(), MODULE$.$lessinit$greater$default$2(), MODULE$.$lessinit$greater$default$3());
   }

   public Attribute decodeStructField(final StructField field, final boolean preserveName) {
      return AttributeFactory.decodeStructField$(this, field, preserveName);
   }

   public Attribute fromStructField(final StructField field) {
      return AttributeFactory.fromStructField$(this, field);
   }

   public Option $lessinit$greater$default$1() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public final BinaryAttribute defaultAttr() {
      return defaultAttr;
   }

   public BinaryAttribute fromMetadata(final Metadata metadata) {
      Option name = (Option)(metadata.contains(AttributeKeys$.MODULE$.NAME()) ? new Some(metadata.getString(AttributeKeys$.MODULE$.NAME())) : .MODULE$);
      Option index = (Option)(metadata.contains(AttributeKeys$.MODULE$.INDEX()) ? new Some(BoxesRunTime.boxToInteger((int)metadata.getLong(AttributeKeys$.MODULE$.INDEX()))) : .MODULE$);
      Option values = (Option)(metadata.contains(AttributeKeys$.MODULE$.VALUES()) ? new Some(metadata.getStringArray(AttributeKeys$.MODULE$.VALUES())) : .MODULE$);
      return new BinaryAttribute(name, index, values);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BinaryAttribute$.class);
   }

   private BinaryAttribute$() {
   }
}
