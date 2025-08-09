package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y;QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u00045\u0003\u0001\u0006Ia\n\u0005\u0006k\u0005!\tEN\u0001\u001a)V\u0004H.\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014(+Z:pYZ,'O\u0003\u0002\t\u0013\u0005)A-Z:fe*\u0011!bC\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u00195\ta!\\8ek2,'B\u0001\b\u0010\u0003\u001dQ\u0017mY6t_:T!\u0001E\t\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\n\u0002\u0007\r|Wn\u0001\u0001\u0011\u0005U\tQ\"A\u0004\u00033Q+\b\u000f\\3EKN,'/[1mSj,'OU3t_24XM]\n\u0003\u0003a\u0001\"!\u0007\u0011\u000f\u0005iqR\"A\u000e\u000b\u0005!a\"BA\u000f\u000e\u0003!!\u0017\r^1cS:$\u0017BA\u0010\u001c\u00035!Um]3sS\u0006d\u0017N_3sg&\u0011\u0011E\t\u0002\u0005\u0005\u0006\u001cXM\u0003\u0002 7\u00051A(\u001b8jiz\"\u0012\u0001F\u0001\b!J{E)V\"U+\u00059\u0003c\u0001\u0015._5\t\u0011F\u0003\u0002+W\u0005!A.\u00198h\u0015\u0005a\u0013\u0001\u00026bm\u0006L!AL\u0015\u0003\u000b\rc\u0017m]:\u0011\u0005A\u0012T\"A\u0019\u000b\u0003)I!aM\u0019\u0003\u000fA\u0013x\u000eZ;di\u0006A\u0001KU(E+\u000e#\u0006%\u0001\u000bgS:$')Z1o\t\u0016\u001cXM]5bY&TXM\u001d\u000b\u0005o\u001dc\u0015\u000b\r\u00029}A\u0019\u0011H\u000f\u001f\u000e\u0003qI!a\u000f\u000f\u0003!)\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bCA\u001f?\u0019\u0001!\u0011bP\u0003\u0002\u0002\u0003\u0005)\u0011\u0001!\u0003\u0007}#\u0013'\u0005\u0002B\tB\u0011\u0001GQ\u0005\u0003\u0007F\u0012qAT8uQ&tw\r\u0005\u00021\u000b&\u0011a)\r\u0002\u0004\u0003:L\b\"\u0002%\u0006\u0001\u0004I\u0015\u0001\u00036bm\u0006$\u0016\u0010]3\u0011\u0005eR\u0015BA&\u001d\u0005!Q\u0015M^1UsB,\u0007\"B'\u0006\u0001\u0004q\u0015AB2p]\u001aLw\r\u0005\u0002:\u001f&\u0011\u0001\u000b\b\u0002\u0016\t\u0016\u001cXM]5bY&T\u0018\r^5p]\u000e{gNZ5h\u0011\u0015\u0011V\u00011\u0001T\u0003!\u0011W-\u00198EKN\u001c\u0007CA\u001dU\u0013\t)FDA\bCK\u0006tG)Z:de&\u0004H/[8o\u0001"
)
public final class TupleDeserializerResolver {
   public static JsonDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return TupleDeserializerResolver$.MODULE$.findBeanDeserializer(javaType, config, beanDesc);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return TupleDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return TupleDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
