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
import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m;Q\u0001C\u0005\t\nY1Q\u0001G\u0005\t\neAQ\u0001M\u0001\u0005\u0002EBqAM\u0001C\u0002\u0013%1\u0007\u0003\u0004:\u0003\u0001\u0006I\u0001\u000e\u0005\u0006u\u0005!\te\u000f\u0005\u0006\u0015\u0006!\te\u0013\u0005\b#\u0006\t\t\u0011\"\u0003S\u0003Y\u0011\u0015n\u001a#fG&l\u0017\r\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0006\f\u0003\u0015!Wm]3s\u0015\taQ\"A\u0003tG\u0006d\u0017M\u0003\u0002\u000f\u001f\u00051Qn\u001c3vY\u0016T!\u0001E\t\u0002\u000f)\f7m[:p]*\u0011!cE\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001F\u0001\u0004G>l7\u0001\u0001\t\u0003/\u0005i\u0011!\u0003\u0002\u0017\u0005&<G)Z2j[\u0006dG)Z:fe&\fG.\u001b>feN\u0011\u0011A\u0007\t\u00047\u0005\u001aS\"\u0001\u000f\u000b\u0005uq\u0012aA:uI*\u0011!b\b\u0006\u0003A=\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003Eq\u0011Qc\u0015;e'\u000e\fG.\u0019:EKN,'/[1mSj,'\u000f\u0005\u0002%[9\u0011QE\u000b\b\u0003M%j\u0011a\n\u0006\u0003QU\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0007\n\u0005-b\u0013a\u00029bG.\fw-\u001a\u0006\u0002\u0019%\u0011af\f\u0002\u000b\u0005&<G)Z2j[\u0006d'BA\u0016-\u0003\u0019a\u0014N\\5u}Q\ta#\u0001\u0003[\u000bJ{U#\u0001\u001b\u0011\u0005UBT\"\u0001\u001c\u000b\u0005]b\u0013\u0001B7bi\"L!A\f\u001c\u0002\u000bi+%k\u0014\u0011\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0004Gq\"\u0005\"B\u001f\u0006\u0001\u0004q\u0014!\u00019\u0011\u0005}\u0012U\"\u0001!\u000b\u0005\u0005{\u0011\u0001B2pe\u0016L!a\u0011!\u0003\u0015)\u001bxN\u001c)beN,'\u000fC\u0003F\u000b\u0001\u0007a)\u0001\u0003dib$\bCA$I\u001b\u0005y\u0012BA% \u0005Y!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8uKb$\u0018!D4fi\u0016k\u0007\u000f^=WC2,X\r\u0006\u0002M!B\u0011QJT\u0007\u0002Y%\u0011q\n\f\u0002\u0007\u0003:L(+\u001a4\t\u000b\u00153\u0001\u0019\u0001$\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003M\u0003\"\u0001V-\u000e\u0003US!AV,\u0002\t1\fgn\u001a\u0006\u00021\u0006!!.\u0019<b\u0013\tQVK\u0001\u0004PE*,7\r\u001e"
)
public final class BigDecimalDeserializer {
   public static Object getEmptyValue(final DeserializationContext ctxt) {
      return BigDecimalDeserializer$.MODULE$.getEmptyValue(ctxt);
   }

   public static BigDecimal deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return BigDecimalDeserializer$.MODULE$.deserialize(p, ctxt);
   }

   public static Object deserialize(final JsonParser x$1, final DeserializationContext x$2, final Object x$3) throws IOException {
      return BigDecimalDeserializer$.MODULE$.deserialize(x$1, x$2, x$3);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3) throws IOException {
      return BigDecimalDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3);
   }

   public static AccessPattern getEmptyAccessPattern() {
      return BigDecimalDeserializer$.MODULE$.getEmptyAccessPattern();
   }

   public static AccessPattern getNullAccessPattern() {
      return BigDecimalDeserializer$.MODULE$.getNullAccessPattern();
   }

   public static Boolean supportsUpdate(final DeserializationConfig x$1) {
      return BigDecimalDeserializer$.MODULE$.supportsUpdate(x$1);
   }

   public static LogicalType logicalType() {
      return BigDecimalDeserializer$.MODULE$.logicalType();
   }

   public static ValueInstantiator getValueInstantiator() {
      return BigDecimalDeserializer$.MODULE$.getValueInstantiator();
   }

   public static JavaType getValueType(final DeserializationContext x$1) {
      return BigDecimalDeserializer$.MODULE$.getValueType(x$1);
   }

   public static JavaType getValueType() {
      return BigDecimalDeserializer$.MODULE$.getValueType();
   }

   /** @deprecated */
   @Deprecated
   public static Class getValueClass() {
      return BigDecimalDeserializer$.MODULE$.getValueClass();
   }

   public static Class handledType() {
      return BigDecimalDeserializer$.MODULE$.handledType();
   }

   /** @deprecated */
   @Deprecated
   public static Object getEmptyValue() {
      return BigDecimalDeserializer$.MODULE$.getEmptyValue();
   }

   /** @deprecated */
   @Deprecated
   public static Object getNullValue() {
      return BigDecimalDeserializer$.MODULE$.getNullValue();
   }

   public static SettableBeanProperty findBackReference(final String x$1) {
      return BigDecimalDeserializer$.MODULE$.findBackReference(x$1);
   }

   public static ObjectIdReader getObjectIdReader() {
      return BigDecimalDeserializer$.MODULE$.getObjectIdReader();
   }

   public static Object getAbsentValue(final DeserializationContext x$1) throws JsonMappingException {
      return BigDecimalDeserializer$.MODULE$.getAbsentValue(x$1);
   }

   public static Object getNullValue(final DeserializationContext x$1) throws JsonMappingException {
      return BigDecimalDeserializer$.MODULE$.getNullValue(x$1);
   }

   public static Collection getKnownPropertyNames() {
      return BigDecimalDeserializer$.MODULE$.getKnownPropertyNames();
   }

   public static JsonDeserializer getDelegatee() {
      return BigDecimalDeserializer$.MODULE$.getDelegatee();
   }

   public static boolean isCachable() {
      return BigDecimalDeserializer$.MODULE$.isCachable();
   }

   public static JsonDeserializer replaceDelegatee(final JsonDeserializer x$1) {
      return BigDecimalDeserializer$.MODULE$.replaceDelegatee(x$1);
   }

   public static JsonDeserializer unwrappingDeserializer(final NameTransformer x$1) {
      return BigDecimalDeserializer$.MODULE$.unwrappingDeserializer(x$1);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3, final Object x$4) throws IOException, JacksonException {
      return BigDecimalDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3, x$4);
   }
}
