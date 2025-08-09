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
import scala.collection.mutable.BitSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;Q!\u0002\u0004\t\u0002M1Q!\u0006\u0004\t\u0002YAQ!K\u0001\u0005\u0002)BQaK\u0001\u0005B1BqaO\u0001\u0002\u0002\u0013%A(A\rNkR\f'\r\\3CSR\u001cV\r\u001e#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0004\t\u0003\u0015!Wm]3s\u0015\tI!\"A\u0003tG\u0006d\u0017M\u0003\u0002\f\u0019\u00051Qn\u001c3vY\u0016T!!\u0004\b\u0002\u000f)\f7m[:p]*\u0011q\u0002E\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011!E\u0001\u0004G>l7\u0001\u0001\t\u0003)\u0005i\u0011A\u0002\u0002\u001a\u001bV$\u0018M\u00197f\u0005&$8+\u001a;EKN,'/[1mSj,'o\u0005\u0002\u0002/A\u0019\u0001D\b\u0011\u000e\u0003eQ!AG\u000e\u0002\u0007M$HM\u0003\u0002\b9)\u0011Q\u0004D\u0001\tI\u0006$\u0018MY5oI&\u0011q$\u0007\u0002\u0010'R$G)Z:fe&\fG.\u001b>feB\u0011\u0011eJ\u0007\u0002E)\u00111\u0005J\u0001\b[V$\u0018M\u00197f\u0015\t)c%\u0001\u0006d_2dWm\u0019;j_:T\u0011!C\u0005\u0003Q\t\u0012aAQ5u'\u0016$\u0018A\u0002\u001fj]&$h\bF\u0001\u0014\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u0007\u0001jS\u0007C\u0003/\u0007\u0001\u0007q&A\u0001q!\t\u00014'D\u00012\u0015\t\u0011D\"\u0001\u0003d_J,\u0017B\u0001\u001b2\u0005)Q5o\u001c8QCJ\u001cXM\u001d\u0005\u0006m\r\u0001\raN\u0001\u0005GRDH\u000f\u0005\u00029s5\tA$\u0003\u0002;9\t1B)Z:fe&\fG.\u001b>bi&|gnQ8oi\u0016DH/\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001>!\tq4)D\u0001@\u0015\t\u0001\u0015)\u0001\u0003mC:<'\"\u0001\"\u0002\t)\fg/Y\u0005\u0003\t~\u0012aa\u00142kK\u000e$\b"
)
public final class MutableBitSetDeserializer {
   public static BitSet deserialize(final JsonParser p, final DeserializationContext ctxt) {
      return MutableBitSetDeserializer$.MODULE$.deserialize(p, ctxt);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3) throws IOException {
      return MutableBitSetDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3);
   }

   public static ValueInstantiator getValueInstantiator() {
      return MutableBitSetDeserializer$.MODULE$.getValueInstantiator();
   }

   public static JavaType getValueType(final DeserializationContext x$1) {
      return MutableBitSetDeserializer$.MODULE$.getValueType(x$1);
   }

   public static JavaType getValueType() {
      return MutableBitSetDeserializer$.MODULE$.getValueType();
   }

   /** @deprecated */
   @Deprecated
   public static Class getValueClass() {
      return MutableBitSetDeserializer$.MODULE$.getValueClass();
   }

   public static Class handledType() {
      return MutableBitSetDeserializer$.MODULE$.handledType();
   }

   /** @deprecated */
   @Deprecated
   public static Object getEmptyValue() {
      return MutableBitSetDeserializer$.MODULE$.getEmptyValue();
   }

   /** @deprecated */
   @Deprecated
   public static Object getNullValue() {
      return MutableBitSetDeserializer$.MODULE$.getNullValue();
   }

   public static Boolean supportsUpdate(final DeserializationConfig x$1) {
      return MutableBitSetDeserializer$.MODULE$.supportsUpdate(x$1);
   }

   public static SettableBeanProperty findBackReference(final String x$1) {
      return MutableBitSetDeserializer$.MODULE$.findBackReference(x$1);
   }

   public static ObjectIdReader getObjectIdReader() {
      return MutableBitSetDeserializer$.MODULE$.getObjectIdReader();
   }

   public static AccessPattern getEmptyAccessPattern() {
      return MutableBitSetDeserializer$.MODULE$.getEmptyAccessPattern();
   }

   public static Object getEmptyValue(final DeserializationContext x$1) throws JsonMappingException {
      return MutableBitSetDeserializer$.MODULE$.getEmptyValue(x$1);
   }

   public static Object getAbsentValue(final DeserializationContext x$1) throws JsonMappingException {
      return MutableBitSetDeserializer$.MODULE$.getAbsentValue(x$1);
   }

   public static AccessPattern getNullAccessPattern() {
      return MutableBitSetDeserializer$.MODULE$.getNullAccessPattern();
   }

   public static Object getNullValue(final DeserializationContext x$1) throws JsonMappingException {
      return MutableBitSetDeserializer$.MODULE$.getNullValue(x$1);
   }

   public static Collection getKnownPropertyNames() {
      return MutableBitSetDeserializer$.MODULE$.getKnownPropertyNames();
   }

   public static JsonDeserializer getDelegatee() {
      return MutableBitSetDeserializer$.MODULE$.getDelegatee();
   }

   public static boolean isCachable() {
      return MutableBitSetDeserializer$.MODULE$.isCachable();
   }

   public static LogicalType logicalType() {
      return MutableBitSetDeserializer$.MODULE$.logicalType();
   }

   public static JsonDeserializer replaceDelegatee(final JsonDeserializer x$1) {
      return MutableBitSetDeserializer$.MODULE$.replaceDelegatee(x$1);
   }

   public static JsonDeserializer unwrappingDeserializer(final NameTransformer x$1) {
      return MutableBitSetDeserializer$.MODULE$.unwrappingDeserializer(x$1);
   }

   public static Object deserializeWithType(final JsonParser x$1, final DeserializationContext x$2, final TypeDeserializer x$3, final Object x$4) throws IOException, JacksonException {
      return MutableBitSetDeserializer$.MODULE$.deserializeWithType(x$1, x$2, x$3, x$4);
   }

   public static Object deserialize(final JsonParser x$1, final DeserializationContext x$2, final Object x$3) throws IOException, JacksonException {
      return MutableBitSetDeserializer$.MODULE$.deserialize(x$1, x$2, x$3);
   }
}
