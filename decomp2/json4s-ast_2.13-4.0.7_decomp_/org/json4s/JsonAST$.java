package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.Seq;

public final class JsonAST$ {
   public static final JsonAST$ MODULE$ = new JsonAST$();
   private static final JNothing$ JNothing;
   private static final JNull$ JNull;
   private static final JString$ JString;
   private static final JDouble$ JDouble;
   private static final JDecimal$ JDecimal;
   private static final JLong$ JLong;
   private static final JInt$ JInt;
   private static final JBool$ JBool;
   private static final JField$ JField;
   private static final JObject$ JObject;
   private static final JArray$ JArray;
   private static final JSet$ JSet;

   static {
      JNothing = JNothing$.MODULE$;
      JNull = JNull$.MODULE$;
      JString = JString$.MODULE$;
      JDouble = JDouble$.MODULE$;
      JDecimal = JDecimal$.MODULE$;
      JLong = JLong$.MODULE$;
      JInt = JInt$.MODULE$;
      JBool = JBool$.MODULE$;
      JField = JField$.MODULE$;
      JObject = JObject$.MODULE$;
      JArray = JArray$.MODULE$;
      JSet = JSet$.MODULE$;
   }

   public JValue concat(final Seq xs) {
      return (JValue)xs.foldLeft(this.JNothing(), (x$1, x$2) -> x$1.$plus$plus(x$2));
   }

   public JNothing$ JNothing() {
      return JNothing;
   }

   public JNull$ JNull() {
      return JNull;
   }

   public JString$ JString() {
      return JString;
   }

   public JDouble$ JDouble() {
      return JDouble;
   }

   public JDecimal$ JDecimal() {
      return JDecimal;
   }

   public JLong$ JLong() {
      return JLong;
   }

   public JInt$ JInt() {
      return JInt;
   }

   public JBool$ JBool() {
      return JBool;
   }

   public JField$ JField() {
      return JField;
   }

   public JObject$ JObject() {
      return JObject;
   }

   public JArray$ JArray() {
      return JArray;
   }

   public JSet$ JSet() {
      return JSet;
   }

   private JsonAST$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
