package org.json4s.jackson;

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
   bytes = "\u0006\u0005\u0019;Q!\u0002\u0004\t\n51Qa\u0004\u0004\t\nAAQAI\u0001\u0005\u0002\rBa\u0001J\u0001!\u0002\u0013)\u0003\"B\u0019\u0002\t\u0003\u0012\u0014A\u0007&WC2,X\rR3tKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(BA\u0004\t\u0003\u001dQ\u0017mY6t_:T!!\u0003\u0006\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005Y\u0011aA8sO\u000e\u0001\u0001C\u0001\b\u0002\u001b\u00051!A\u0007&WC2,X\rR3tKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u00148CA\u0001\u0012!\t\u0011rD\u0004\u0002\u0014;5\tAC\u0003\u0002\u0016-\u0005)A-Z:fe*\u0011q\u0003G\u0001\tI\u0006$\u0018MY5oI*\u0011q!\u0007\u0006\u00035m\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003q\t1aY8n\u0013\tqB#A\u0007EKN,'/[1mSj,'o]\u0005\u0003A\u0005\u0012AAQ1tK*\u0011a\u0004F\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\tqAS0W\u00032+V\tE\u0002'W5j\u0011a\n\u0006\u0003Q%\nA\u0001\\1oO*\t!&\u0001\u0003kCZ\f\u0017B\u0001\u0017(\u0005\u0015\u0019E.Y:t!\tqs&D\u0001\t\u0013\t\u0001\u0004B\u0001\u0004K-\u0006dW/Z\u0001\u0015M&tGMQ3b]\u0012+7/\u001a:jC2L'0\u001a:\u0015\tM2D(\u0011\t\u0003\u001dQJ!!\u000e\u0004\u0003%)3\u0016\r\\;f\t\u0016\u001cXM]5bY&TXM\u001d\u0005\u0006o\u0011\u0001\r\u0001O\u0001\tU\u00064\u0018\rV=qKB\u0011\u0011HO\u0007\u0002-%\u00111H\u0006\u0002\t\u0015\u00064\u0018\rV=qK\")Q\b\u0002a\u0001}\u000511m\u001c8gS\u001e\u0004\"!O \n\u0005\u00013\"!\u0006#fg\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:4\u0017n\u001a\u0005\u0006\u0005\u0012\u0001\raQ\u0001\tE\u0016\fg\u000eR3tGB\u0011\u0011\bR\u0005\u0003\u000bZ\u0011qBQ3b]\u0012+7o\u0019:jaRLwN\u001c"
)
public final class JValueDeserializerResolver {
   public static JValueDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return JValueDeserializerResolver$.MODULE$.findBeanDeserializer(javaType, config, beanDesc);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return JValueDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return JValueDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }
}
