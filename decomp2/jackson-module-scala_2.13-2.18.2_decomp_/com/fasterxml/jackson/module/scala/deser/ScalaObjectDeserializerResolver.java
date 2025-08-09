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
   bytes = "\u0006\u0005\u0019;Q\u0001B\u0003\t\nI1Q\u0001F\u0003\t\nUAQ!I\u0001\u0005\u0002\tBQaI\u0001\u0005B\u0011\nqdU2bY\u0006|%M[3di\u0012+7/\u001a:jC2L'0\u001a:SKN|GN^3s\u0015\t1q!A\u0003eKN,'O\u0003\u0002\t\u0013\u0005)1oY1mC*\u0011!bC\u0001\u0007[>$W\u000f\\3\u000b\u00051i\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003\u001d=\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003A\t1aY8n\u0007\u0001\u0001\"aE\u0001\u000e\u0003\u0015\u0011qdU2bY\u0006|%M[3di\u0012+7/\u001a:jC2L'0\u001a:SKN|GN^3s'\t\ta\u0003\u0005\u0002\u0018=9\u0011\u0001\u0004H\u0007\u00023)\u0011aA\u0007\u0006\u00037-\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003;e\tQ\u0002R3tKJL\u0017\r\\5{KJ\u001c\u0018BA\u0010!\u0005\u0011\u0011\u0015m]3\u000b\u0005uI\u0012A\u0002\u001fj]&$h\bF\u0001\u0013\u0003Q1\u0017N\u001c3CK\u0006tG)Z:fe&\fG.\u001b>feR!Qe\u000e\u001fBa\t1C\u0006E\u0002(Q)j\u0011AG\u0005\u0003Si\u0011\u0001CS:p]\u0012+7/\u001a:jC2L'0\u001a:\u0011\u0005-bC\u0002\u0001\u0003\n[\r\t\t\u0011!A\u0003\u00029\u00121a\u0018\u00132#\tyC\u0007\u0005\u00021e5\t\u0011GC\u0001\t\u0013\t\u0019\u0014GA\u0004O_RD\u0017N\\4\u0011\u0005A*\u0014B\u0001\u001c2\u0005\r\te.\u001f\u0005\u0006q\r\u0001\r!O\u0001\tU\u00064\u0018\rV=qKB\u0011qEO\u0005\u0003wi\u0011\u0001BS1wCRK\b/\u001a\u0005\u0006{\r\u0001\rAP\u0001\u0007G>tg-[4\u0011\u0005\u001dz\u0014B\u0001!\u001b\u0005U!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8gS\u001eDQAQ\u0002A\u0002\r\u000b\u0001BY3b]\u0012+7o\u0019\t\u0003O\u0011K!!\u0012\u000e\u0003\u001f\t+\u0017M\u001c#fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004"
)
public final class ScalaObjectDeserializerResolver {
   public static JsonDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return ScalaObjectDeserializerResolver$.MODULE$.findBeanDeserializer(javaType, config, beanDesc);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return ScalaObjectDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return ScalaObjectDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
