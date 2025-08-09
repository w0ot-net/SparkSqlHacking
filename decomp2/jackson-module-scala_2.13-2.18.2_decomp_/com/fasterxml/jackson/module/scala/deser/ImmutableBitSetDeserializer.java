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
import scala.collection.immutable.BitSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;Q!\u0002\u0004\t\u0002M1Q!\u0006\u0004\t\u0002YAQ!K\u0001\u0005\u0002)BQaK\u0001\u0005B1BqaO\u0001\u0002\u0002\u0013%A(A\u000eJ[6,H/\u00192mK\nKGoU3u\t\u0016\u001cXM]5bY&TXM\u001d\u0006\u0003\u000f!\tQ\u0001Z3tKJT!!\u0003\u0006\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005-a\u0011AB7pIVdWM\u0003\u0002\u000e\u001d\u00059!.Y2lg>t'BA\b\u0011\u0003%1\u0017m\u001d;feblGNC\u0001\u0012\u0003\r\u0019w.\\\u0002\u0001!\t!\u0012!D\u0001\u0007\u0005mIU.\\;uC\ndWMQ5u'\u0016$H)Z:fe&\fG.\u001b>feN\u0011\u0011a\u0006\t\u00041y\u0001S\"A\r\u000b\u0005iY\u0012aA:uI*\u0011q\u0001\b\u0006\u0003;1\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003?e\u0011qb\u0015;e\t\u0016\u001cXM]5bY&TXM\u001d\t\u0003C\u001dj\u0011A\t\u0006\u0003G\u0011\n\u0011\"[7nkR\f'\r\\3\u000b\u0005\u00152\u0013AC2pY2,7\r^5p]*\t\u0011\"\u0003\u0002)E\t1!)\u001b;TKR\fa\u0001P5oSRtD#A\n\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0004A5*\u0004\"\u0002\u0018\u0004\u0001\u0004y\u0013!\u00019\u0011\u0005A\u001aT\"A\u0019\u000b\u0005Ib\u0011\u0001B2pe\u0016L!\u0001N\u0019\u0003\u0015)\u001bxN\u001c)beN,'\u000fC\u00037\u0007\u0001\u0007q'\u0001\u0003dib$\bC\u0001\u001d:\u001b\u0005a\u0012B\u0001\u001e\u001d\u0005Y!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8uKb$\u0018\u0001D<sSR,'+\u001a9mC\u000e,G#A\u001f\u0011\u0005y\u001aU\"A \u000b\u0005\u0001\u000b\u0015\u0001\u00027b]\u001eT\u0011AQ\u0001\u0005U\u00064\u0018-\u0003\u0002E\u007f\t1qJ\u00196fGR\u0004"
)
public final class ImmutableBitSetDeserializer {
   public static BitSet deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return ImmutableBitSetDeserializer$.MODULE$.deserialize(p, ctxt);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3) throws IOException {
      return ImmutableBitSetDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3);
   }

   public static ValueInstantiator getValueInstantiator() {
      return ImmutableBitSetDeserializer$.MODULE$.getValueInstantiator();
   }

   public static JavaType getValueType(final DeserializationContext x$1) {
      return ImmutableBitSetDeserializer$.MODULE$.getValueType(x$1);
   }

   public static JavaType getValueType() {
      return ImmutableBitSetDeserializer$.MODULE$.getValueType();
   }

   /** @deprecated */
   @Deprecated
   public static Class getValueClass() {
      return ImmutableBitSetDeserializer$.MODULE$.getValueClass();
   }

   public static Class handledType() {
      return ImmutableBitSetDeserializer$.MODULE$.handledType();
   }

   /** @deprecated */
   @Deprecated
   public static Object getEmptyValue() {
      return ImmutableBitSetDeserializer$.MODULE$.getEmptyValue();
   }

   /** @deprecated */
   @Deprecated
   public static Object getNullValue() {
      return ImmutableBitSetDeserializer$.MODULE$.getNullValue();
   }

   public static Boolean supportsUpdate(final DeserializationConfig x$1) {
      return ImmutableBitSetDeserializer$.MODULE$.supportsUpdate(x$1);
   }

   public static SettableBeanProperty findBackReference(final String x$1) {
      return ImmutableBitSetDeserializer$.MODULE$.findBackReference(x$1);
   }

   public static ObjectIdReader getObjectIdReader() {
      return ImmutableBitSetDeserializer$.MODULE$.getObjectIdReader();
   }

   public static AccessPattern getEmptyAccessPattern() {
      return ImmutableBitSetDeserializer$.MODULE$.getEmptyAccessPattern();
   }

   public static Object getEmptyValue(final DeserializationContext x$1) throws JsonMappingException {
      return ImmutableBitSetDeserializer$.MODULE$.getEmptyValue(x$1);
   }

   public static Object getAbsentValue(final DeserializationContext x$1) throws JsonMappingException {
      return ImmutableBitSetDeserializer$.MODULE$.getAbsentValue(x$1);
   }

   public static AccessPattern getNullAccessPattern() {
      return ImmutableBitSetDeserializer$.MODULE$.getNullAccessPattern();
   }

   public static Object getNullValue(final DeserializationContext x$1) throws JsonMappingException {
      return ImmutableBitSetDeserializer$.MODULE$.getNullValue(x$1);
   }

   public static Collection getKnownPropertyNames() {
      return ImmutableBitSetDeserializer$.MODULE$.getKnownPropertyNames();
   }

   public static JsonDeserializer getDelegatee() {
      return ImmutableBitSetDeserializer$.MODULE$.getDelegatee();
   }

   public static boolean isCachable() {
      return ImmutableBitSetDeserializer$.MODULE$.isCachable();
   }

   public static LogicalType logicalType() {
      return ImmutableBitSetDeserializer$.MODULE$.logicalType();
   }

   public static JsonDeserializer replaceDelegatee(final JsonDeserializer x$1) {
      return ImmutableBitSetDeserializer$.MODULE$.replaceDelegatee(x$1);
   }

   public static JsonDeserializer unwrappingDeserializer(final NameTransformer x$1) {
      return ImmutableBitSetDeserializer$.MODULE$.unwrappingDeserializer(x$1);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3, final Object x$4) throws IOException, JacksonException {
      return ImmutableBitSetDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3, x$4);
   }

   public static Object deserialize(final JsonParser x$1, final DeserializationContext x$2, final Object x$3) throws IOException, JacksonException {
      return ImmutableBitSetDeserializer$.MODULE$.deserialize(x$1, x$2, x$3);
   }
}
