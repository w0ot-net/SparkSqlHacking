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
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m;Q\u0001C\u0005\t\nY1Q\u0001G\u0005\t\neAQ\u0001M\u0001\u0005\u0002EBqAM\u0001C\u0002\u0013%1\u0007\u0003\u0004:\u0003\u0001\u0006I\u0001\u000e\u0005\u0006u\u0005!\te\u000f\u0005\u0006\u0015\u0006!\te\u0013\u0005\b#\u0006\t\t\u0011\"\u0003S\u0003I\u0011\u0015nZ%oi\u0012+7/\u001a:jC2L'0\u001a:\u000b\u0005)Y\u0011!\u00023fg\u0016\u0014(B\u0001\u0007\u000e\u0003\u0015\u00198-\u00197b\u0015\tqq\"\u0001\u0004n_\u0012,H.\u001a\u0006\u0003!E\tqA[1dWN|gN\u0003\u0002\u0013'\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002)\u0005\u00191m\\7\u0004\u0001A\u0011q#A\u0007\u0002\u0013\t\u0011\")[4J]R$Um]3sS\u0006d\u0017N_3s'\t\t!\u0004E\u0002\u001cC\rj\u0011\u0001\b\u0006\u0003;y\t1a\u001d;e\u0015\tQqD\u0003\u0002!\u001f\u0005AA-\u0019;bE&tG-\u0003\u0002#9\t)2\u000b\u001e3TG\u0006d\u0017M\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001\u0013.\u001d\t)#F\u0004\u0002'S5\tqE\u0003\u0002)+\u00051AH]8pizJ\u0011\u0001D\u0005\u0003W1\nq\u0001]1dW\u0006<WMC\u0001\r\u0013\tqsF\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003W1\na\u0001P5oSRtD#\u0001\f\u0002\ti+%kT\u000b\u0002iA\u0011Q\u0007O\u0007\u0002m)\u0011q\u0007L\u0001\u0005[\u0006$\b.\u0003\u0002/m\u0005)!,\u0012*PA\u0005YA-Z:fe&\fG.\u001b>f)\r\u0019C\b\u0012\u0005\u0006{\u0015\u0001\rAP\u0001\u0002aB\u0011qHQ\u0007\u0002\u0001*\u0011\u0011iD\u0001\u0005G>\u0014X-\u0003\u0002D\u0001\nQ!j]8o!\u0006\u00148/\u001a:\t\u000b\u0015+\u0001\u0019\u0001$\u0002\t\r$\b\u0010\u001e\t\u0003\u000f\"k\u0011aH\u0005\u0003\u0013~\u0011a\u0003R3tKJL\u0017\r\\5{CRLwN\\\"p]R,\u0007\u0010^\u0001\u000eO\u0016$X)\u001c9usZ\u000bG.^3\u0015\u00051\u0003\u0006CA'O\u001b\u0005a\u0013BA(-\u0005\u0019\te.\u001f*fM\")QI\u0002a\u0001\r\u0006aqO]5uKJ+\u0007\u000f\\1dKR\t1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006!A.\u00198h\u0015\u0005A\u0016\u0001\u00026bm\u0006L!AW+\u0003\r=\u0013'.Z2u\u0001"
)
public final class BigIntDeserializer {
   public static Object getEmptyValue(final DeserializationContext ctxt) {
      return BigIntDeserializer$.MODULE$.getEmptyValue(ctxt);
   }

   public static BigInt deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return BigIntDeserializer$.MODULE$.deserialize(p, ctxt);
   }

   public static Object deserialize(final JsonParser x$1, final DeserializationContext x$2, final Object x$3) throws IOException {
      return BigIntDeserializer$.MODULE$.deserialize(x$1, x$2, x$3);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3) throws IOException {
      return BigIntDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3);
   }

   public static AccessPattern getEmptyAccessPattern() {
      return BigIntDeserializer$.MODULE$.getEmptyAccessPattern();
   }

   public static AccessPattern getNullAccessPattern() {
      return BigIntDeserializer$.MODULE$.getNullAccessPattern();
   }

   public static Boolean supportsUpdate(final DeserializationConfig x$1) {
      return BigIntDeserializer$.MODULE$.supportsUpdate(x$1);
   }

   public static LogicalType logicalType() {
      return BigIntDeserializer$.MODULE$.logicalType();
   }

   public static ValueInstantiator getValueInstantiator() {
      return BigIntDeserializer$.MODULE$.getValueInstantiator();
   }

   public static JavaType getValueType(final DeserializationContext x$1) {
      return BigIntDeserializer$.MODULE$.getValueType(x$1);
   }

   public static JavaType getValueType() {
      return BigIntDeserializer$.MODULE$.getValueType();
   }

   /** @deprecated */
   @Deprecated
   public static Class getValueClass() {
      return BigIntDeserializer$.MODULE$.getValueClass();
   }

   public static Class handledType() {
      return BigIntDeserializer$.MODULE$.handledType();
   }

   /** @deprecated */
   @Deprecated
   public static Object getEmptyValue() {
      return BigIntDeserializer$.MODULE$.getEmptyValue();
   }

   /** @deprecated */
   @Deprecated
   public static Object getNullValue() {
      return BigIntDeserializer$.MODULE$.getNullValue();
   }

   public static SettableBeanProperty findBackReference(final String x$1) {
      return BigIntDeserializer$.MODULE$.findBackReference(x$1);
   }

   public static ObjectIdReader getObjectIdReader() {
      return BigIntDeserializer$.MODULE$.getObjectIdReader();
   }

   public static Object getAbsentValue(final DeserializationContext x$1) throws JsonMappingException {
      return BigIntDeserializer$.MODULE$.getAbsentValue(x$1);
   }

   public static Object getNullValue(final DeserializationContext x$1) throws JsonMappingException {
      return BigIntDeserializer$.MODULE$.getNullValue(x$1);
   }

   public static Collection getKnownPropertyNames() {
      return BigIntDeserializer$.MODULE$.getKnownPropertyNames();
   }

   public static JsonDeserializer getDelegatee() {
      return BigIntDeserializer$.MODULE$.getDelegatee();
   }

   public static boolean isCachable() {
      return BigIntDeserializer$.MODULE$.isCachable();
   }

   public static JsonDeserializer replaceDelegatee(final JsonDeserializer x$1) {
      return BigIntDeserializer$.MODULE$.replaceDelegatee(x$1);
   }

   public static JsonDeserializer unwrappingDeserializer(final NameTransformer x$1) {
      return BigIntDeserializer$.MODULE$.unwrappingDeserializer(x$1);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3, final Object x$4) throws IOException, JacksonException {
      return BigIntDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3, x$4);
   }
}
