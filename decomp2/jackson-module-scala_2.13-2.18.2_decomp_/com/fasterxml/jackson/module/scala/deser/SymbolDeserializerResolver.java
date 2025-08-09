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
   bytes = "\u0006\u0005);QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u00045\u0003\u0001\u0006Ia\n\u0005\u0006k\u0005!\tEN\u0001\u001b'fl'm\u001c7EKN,'/[1mSj,'OU3t_24XM\u001d\u0006\u0003\u0011%\tQ\u0001Z3tKJT!AC\u0006\u0002\u000bM\u001c\u0017\r\\1\u000b\u00051i\u0011AB7pIVdWM\u0003\u0002\u000f\u001f\u00059!.Y2lg>t'B\u0001\t\u0012\u0003%1\u0017m\u001d;feblGNC\u0001\u0013\u0003\r\u0019w.\\\u0002\u0001!\t)\u0012!D\u0001\b\u0005i\u0019\u00160\u001c2pY\u0012+7/\u001a:jC2L'0\u001a:SKN|GN^3s'\t\t\u0001\u0004\u0005\u0002\u001aA9\u0011!DH\u0007\u00027)\u0011\u0001\u0002\b\u0006\u0003;5\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003?m\tQ\u0002R3tKJL\u0017\r\\5{KJ\u001c\u0018BA\u0011#\u0005\u0011\u0011\u0015m]3\u000b\u0005}Y\u0012A\u0002\u001fj]&$h\bF\u0001\u0015\u0003\u0019\u0019\u0016,\u0014\"P\u0019V\tq\u0005E\u0002)[=j\u0011!\u000b\u0006\u0003U-\nA\u0001\\1oO*\tA&\u0001\u0003kCZ\f\u0017B\u0001\u0018*\u0005\u0015\u0019E.Y:t!\t\u0001$'D\u00012\u0015\u0005Q\u0011BA\u001a2\u0005\u0019\u0019\u00160\u001c2pY\u000691+W'C\u001f2\u0003\u0013\u0001\u00064j]\u0012\u0014U-\u00198EKN,'/[1mSj,'\u000f\u0006\u00038w\u0001+\u0005c\u0001\u001d:_5\tA$\u0003\u0002;9\t\u0001\"j]8o\t\u0016\u001cXM]5bY&TXM\u001d\u0005\u0006y\u0015\u0001\r!P\u0001\tU\u00064\u0018\rV=qKB\u0011\u0001HP\u0005\u0003\u007fq\u0011\u0001BS1wCRK\b/\u001a\u0005\u0006\u0003\u0016\u0001\rAQ\u0001\u0007G>tg-[4\u0011\u0005a\u001a\u0015B\u0001#\u001d\u0005U!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8gS\u001eDQAR\u0003A\u0002\u001d\u000b\u0001BY3b]\u0012+7o\u0019\t\u0003q!K!!\u0013\u000f\u0003\u001f\t+\u0017M\u001c#fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004"
)
public final class SymbolDeserializerResolver {
   public static JsonDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return SymbolDeserializerResolver$.MODULE$.findBeanDeserializer(javaType, config, beanDesc);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return SymbolDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return SymbolDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
