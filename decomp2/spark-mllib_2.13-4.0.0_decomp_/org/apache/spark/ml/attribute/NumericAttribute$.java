package org.apache.spark.ml.attribute;

import java.io.Serializable;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class NumericAttribute$ implements AttributeFactory, Serializable {
   public static final NumericAttribute$ MODULE$ = new NumericAttribute$();
   private static final NumericAttribute defaultAttr;

   static {
      AttributeFactory.$init$(MODULE$);
      defaultAttr = new NumericAttribute(MODULE$.$lessinit$greater$default$1(), MODULE$.$lessinit$greater$default$2(), MODULE$.$lessinit$greater$default$3(), MODULE$.$lessinit$greater$default$4(), MODULE$.$lessinit$greater$default$5(), MODULE$.$lessinit$greater$default$6());
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

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$6() {
      return .MODULE$;
   }

   public NumericAttribute defaultAttr() {
      return defaultAttr;
   }

   public NumericAttribute fromMetadata(final Metadata metadata) {
      Option name = (Option)(metadata.contains(AttributeKeys$.MODULE$.NAME()) ? new Some(metadata.getString(AttributeKeys$.MODULE$.NAME())) : .MODULE$);
      Option index = (Option)(metadata.contains(AttributeKeys$.MODULE$.INDEX()) ? new Some(BoxesRunTime.boxToInteger((int)metadata.getLong(AttributeKeys$.MODULE$.INDEX()))) : .MODULE$);
      Option min = (Option)(metadata.contains(AttributeKeys$.MODULE$.MIN()) ? new Some(BoxesRunTime.boxToDouble(metadata.getDouble(AttributeKeys$.MODULE$.MIN()))) : .MODULE$);
      Option max = (Option)(metadata.contains(AttributeKeys$.MODULE$.MAX()) ? new Some(BoxesRunTime.boxToDouble(metadata.getDouble(AttributeKeys$.MODULE$.MAX()))) : .MODULE$);
      Option std = (Option)(metadata.contains(AttributeKeys$.MODULE$.STD()) ? new Some(BoxesRunTime.boxToDouble(metadata.getDouble(AttributeKeys$.MODULE$.STD()))) : .MODULE$);
      Option sparsity = (Option)(metadata.contains(AttributeKeys$.MODULE$.SPARSITY()) ? new Some(BoxesRunTime.boxToDouble(metadata.getDouble(AttributeKeys$.MODULE$.SPARSITY()))) : .MODULE$);
      return new NumericAttribute(name, index, min, max, std, sparsity);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NumericAttribute$.class);
   }

   private NumericAttribute$() {
   }
}
