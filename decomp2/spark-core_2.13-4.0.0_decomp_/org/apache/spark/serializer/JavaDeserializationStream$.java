package org.apache.spark.serializer;

import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.BoxedUnit;

public final class JavaDeserializationStream$ {
   public static final JavaDeserializationStream$ MODULE$ = new JavaDeserializationStream$();
   private static final Map primitiveMappings;

   static {
      primitiveMappings = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("boolean"), Boolean.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("byte"), Byte.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("char"), Character.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("short"), Short.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("int"), Integer.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("long"), Long.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("float"), Float.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("double"), Double.TYPE), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("void"), BoxedUnit.TYPE)})));
   }

   public Map primitiveMappings() {
      return primitiveMappings;
   }

   private JavaDeserializationStream$() {
   }
}
