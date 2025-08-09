package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Collection;
import scala.Symbol;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005;Q!\u0002\u0004\t\nM1Q!\u0006\u0004\t\nYAQ!J\u0001\u0005\u0002\u0019BQaJ\u0001\u0005B!BqaN\u0001\u0002\u0002\u0013%\u0001(\u0001\nTs6\u0014w\u000e\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0004\t\u0003\u0015!Wm]3s\u0015\tI!\"A\u0003tG\u0006d\u0017M\u0003\u0002\f\u0019\u00051Qn\u001c3vY\u0016T!!\u0004\b\u0002\u000f)\f7m[:p]*\u0011q\u0002E\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011!E\u0001\u0004G>l7\u0001\u0001\t\u0003)\u0005i\u0011A\u0002\u0002\u0013'fl'm\u001c7EKN,'/[1mSj,'o\u0005\u0002\u0002/A\u0019\u0001D\b\u0011\u000e\u0003eQ!AG\u000e\u0002\u0007M$HM\u0003\u0002\b9)\u0011Q\u0004D\u0001\tI\u0006$\u0018MY5oI&\u0011q$\u0007\u0002\u0010'R$G)Z:fe&\fG.\u001b>feB\u0011\u0011eI\u0007\u0002E)\t\u0011\"\u0003\u0002%E\t11+_7c_2\fa\u0001P5oSRtD#A\n\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0004A%\n\u0004\"\u0002\u0016\u0004\u0001\u0004Y\u0013!\u00019\u0011\u00051zS\"A\u0017\u000b\u00059b\u0011\u0001B2pe\u0016L!\u0001M\u0017\u0003\u0015)\u001bxN\u001c)beN,'\u000fC\u00033\u0007\u0001\u00071'\u0001\u0003dib$\bC\u0001\u001b6\u001b\u0005a\u0012B\u0001\u001c\u001d\u0005Y!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8uKb$\u0018\u0001D<sSR,'+\u001a9mC\u000e,G#A\u001d\u0011\u0005izT\"A\u001e\u000b\u0005qj\u0014\u0001\u00027b]\u001eT\u0011AP\u0001\u0005U\u00064\u0018-\u0003\u0002Aw\t1qJ\u00196fGR\u0004"
)
public final class SymbolDeserializer {
   public static Symbol deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return SymbolDeserializer$.MODULE$.deserialize(p, ctxt);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3) throws IOException {
      return SymbolDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3);
   }

   public static ValueInstantiator getValueInstantiator() {
      return SymbolDeserializer$.MODULE$.getValueInstantiator();
   }

   public static JavaType getValueType(final DeserializationContext x$1) {
      return SymbolDeserializer$.MODULE$.getValueType(x$1);
   }

   public static JavaType getValueType() {
      return SymbolDeserializer$.MODULE$.getValueType();
   }

   /** @deprecated */
   @Deprecated
   public static Class getValueClass() {
      return SymbolDeserializer$.MODULE$.getValueClass();
   }

   public static Class handledType() {
      return SymbolDeserializer$.MODULE$.handledType();
   }

   /** @deprecated */
   @Deprecated
   public static Object getEmptyValue() {
      return SymbolDeserializer$.MODULE$.getEmptyValue();
   }

   /** @deprecated */
   @Deprecated
   public static Object getNullValue() {
      return SymbolDeserializer$.MODULE$.getNullValue();
   }

   public static Boolean supportsUpdate(final DeserializationConfig x$1) {
      return SymbolDeserializer$.MODULE$.supportsUpdate(x$1);
   }

   public static SettableBeanProperty findBackReference(final String x$1) {
      return SymbolDeserializer$.MODULE$.findBackReference(x$1);
   }

   public static ObjectIdReader getObjectIdReader() {
      return SymbolDeserializer$.MODULE$.getObjectIdReader();
   }

   public static AccessPattern getEmptyAccessPattern() {
      return SymbolDeserializer$.MODULE$.getEmptyAccessPattern();
   }

   public static Object getEmptyValue(final DeserializationContext x$1) throws JsonMappingException {
      return SymbolDeserializer$.MODULE$.getEmptyValue(x$1);
   }

   public static Object getAbsentValue(final DeserializationContext x$1) throws JsonMappingException {
      return SymbolDeserializer$.MODULE$.getAbsentValue(x$1);
   }

   public static AccessPattern getNullAccessPattern() {
      return SymbolDeserializer$.MODULE$.getNullAccessPattern();
   }

   public static Object getNullValue(final DeserializationContext x$1) throws JsonMappingException {
      return SymbolDeserializer$.MODULE$.getNullValue(x$1);
   }

   public static Collection getKnownPropertyNames() {
      return SymbolDeserializer$.MODULE$.getKnownPropertyNames();
   }

   public static JsonDeserializer getDelegatee() {
      return SymbolDeserializer$.MODULE$.getDelegatee();
   }

   public static boolean isCachable() {
      return SymbolDeserializer$.MODULE$.isCachable();
   }

   public static LogicalType logicalType() {
      return SymbolDeserializer$.MODULE$.logicalType();
   }

   public static JsonDeserializer replaceDelegatee(final JsonDeserializer x$1) {
      return SymbolDeserializer$.MODULE$.replaceDelegatee(x$1);
   }

   public static JsonDeserializer unwrappingDeserializer(final NameTransformer x$1) {
      return SymbolDeserializer$.MODULE$.unwrappingDeserializer(x$1);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3, final Object x$4) throws IOException, JacksonException {
      return SymbolDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3, x$4);
   }

   public static Object deserialize(final JsonParser x$1, final DeserializationContext x$2, final Object x$3) throws IOException, JacksonException {
      return SymbolDeserializer$.MODULE$.deserialize(x$1, x$2, x$3);
   }
}
