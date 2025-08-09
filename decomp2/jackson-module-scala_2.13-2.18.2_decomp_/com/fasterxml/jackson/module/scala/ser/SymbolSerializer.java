package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import scala.Symbol;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:Q\u0001B\u0003\t\nI1Q\u0001F\u0003\t\nUAQ!I\u0001\u0005\u0002\tBQaI\u0001\u0005\u0002\u0011\n\u0001cU=nE>d7+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011aA:fe*\u0011\u0001\"C\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u0015-\ta!\\8ek2,'B\u0001\u0007\u000e\u0003\u001dQ\u0017mY6t_:T!AD\b\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\t\u0002\u0007\r|Wn\u0001\u0001\u0011\u0005M\tQ\"A\u0003\u0003!MKXNY8m'\u0016\u0014\u0018.\u00197ju\u0016\u00148CA\u0001\u0017!\r9\"\u0004H\u0007\u00021)\u0011\u0011dC\u0001\tI\u0006$\u0018MY5oI&\u00111\u0004\u0007\u0002\u000f\u0015N|gnU3sS\u0006d\u0017N_3s!\tir$D\u0001\u001f\u0015\u0005A\u0011B\u0001\u0011\u001f\u0005\u0019\u0019\u00160\u001c2pY\u00061A(\u001b8jiz\"\u0012AE\u0001\ng\u0016\u0014\u0018.\u00197ju\u0016$B!\n\u0015+eA\u0011QDJ\u0005\u0003Oy\u0011A!\u00168ji\")\u0011f\u0001a\u00019\u0005)a/\u00197vK\")1f\u0001a\u0001Y\u0005!!nZ3o!\ti\u0003'D\u0001/\u0015\ty3\"\u0001\u0003d_J,\u0017BA\u0019/\u00055Q5o\u001c8HK:,'/\u0019;pe\")1g\u0001a\u0001i\u0005A\u0001O]8wS\u0012,'\u000f\u0005\u0002\u0018k%\u0011a\u0007\u0007\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bK]8wS\u0012,'\u000f"
)
public final class SymbolSerializer {
   public static void serialize(final Symbol value, final JsonGenerator jgen, final SerializerProvider provider) {
      SymbolSerializer$.MODULE$.serialize(value, jgen, provider);
   }

   public static void acceptJsonFormatVisitor(final JsonFormatVisitorWrapper x$1, final JavaType x$2) throws JsonMappingException {
      SymbolSerializer$.MODULE$.acceptJsonFormatVisitor(x$1, x$2);
   }

   public static Iterator properties() {
      return SymbolSerializer$.MODULE$.properties();
   }

   public static JsonSerializer getDelegatee() {
      return SymbolSerializer$.MODULE$.getDelegatee();
   }

   public static boolean isUnwrappingSerializer() {
      return SymbolSerializer$.MODULE$.isUnwrappingSerializer();
   }

   public static boolean usesObjectId() {
      return SymbolSerializer$.MODULE$.usesObjectId();
   }

   public static boolean isEmpty(final SerializerProvider x$1, final Object x$2) {
      return SymbolSerializer$.MODULE$.isEmpty(x$1, x$2);
   }

   /** @deprecated */
   @Deprecated
   public static boolean isEmpty(final Object x$1) {
      return SymbolSerializer$.MODULE$.isEmpty(x$1);
   }

   public static Class handledType() {
      return SymbolSerializer$.MODULE$.handledType();
   }

   public static void serializeWithType(final Object x$1, final JsonGenerator x$2, final SerializerProvider x$3, final TypeSerializer x$4) throws IOException {
      SymbolSerializer$.MODULE$.serializeWithType(x$1, x$2, x$3, x$4);
   }

   public static JsonSerializer withIgnoredProperties(final Set x$1) {
      return SymbolSerializer$.MODULE$.withIgnoredProperties(x$1);
   }

   public static JsonSerializer withFilterId(final Object x$1) {
      return SymbolSerializer$.MODULE$.withFilterId(x$1);
   }

   public static JsonSerializer replaceDelegatee(final JsonSerializer x$1) {
      return SymbolSerializer$.MODULE$.replaceDelegatee(x$1);
   }

   public static JsonSerializer unwrappingSerializer(final NameTransformer x$1) {
      return SymbolSerializer$.MODULE$.unwrappingSerializer(x$1);
   }
}
