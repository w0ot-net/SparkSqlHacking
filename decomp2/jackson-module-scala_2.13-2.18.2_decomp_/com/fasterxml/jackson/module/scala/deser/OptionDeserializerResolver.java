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
   bytes = "\u0006\u0005-<QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u00048\u0003\u0001\u0006Ia\n\u0005\u0006q\u0005!\t%O\u0001\u001b\u001fB$\u0018n\u001c8EKN,'/[1mSj,'OU3t_24XM\u001d\u0006\u0003\u0011%\tQ\u0001Z3tKJT!AC\u0006\u0002\u000bM\u001c\u0017\r\\1\u000b\u00051i\u0011AB7pIVdWM\u0003\u0002\u000f\u001f\u00059!.Y2lg>t'B\u0001\t\u0012\u0003%1\u0017m\u001d;feblGNC\u0001\u0013\u0003\r\u0019w.\\\u0002\u0001!\t)\u0012!D\u0001\b\u0005iy\u0005\u000f^5p]\u0012+7/\u001a:jC2L'0\u001a:SKN|GN^3s'\t\t\u0001\u0004\u0005\u0002\u001aA9\u0011!DH\u0007\u00027)\u0011\u0001\u0002\b\u0006\u0003;5\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003?m\tQ\u0002R3tKJL\u0017\r\\5{KJ\u001c\u0018BA\u0011#\u0005\u0011\u0011\u0015m]3\u000b\u0005}Y\u0012A\u0002\u001fj]&$h\bF\u0001\u0015\u0003\u0019y\u0005\u000bV%P\u001dV\tq\u0005E\u0002)[=j\u0011!\u000b\u0006\u0003U-\nA\u0001\\1oO*\tA&\u0001\u0003kCZ\f\u0017B\u0001\u0018*\u0005\u0015\u0019E.Y:t!\r\u0001$\u0007N\u0007\u0002c)\t!\"\u0003\u00024c\t1q\n\u001d;j_:\u0004\"\u0001M\u001b\n\u0005Y\n$AB!osJ+g-A\u0004P!RKuJ\u0014\u0011\u00023\u0019Lg\u000e\u001a*fM\u0016\u0014XM\\2f\t\u0016\u001cXM]5bY&TXM\u001d\u000b\u0007u)\u0013v\u000b\u001831\u0005m\n\u0005c\u0001\u001f>\u007f5\tA$\u0003\u0002?9\t\u0001\"j]8o\t\u0016\u001cXM]5bY&TXM\u001d\t\u0003\u0001\u0006c\u0001\u0001B\u0005C\u000b\u0005\u0005\t\u0011!B\u0001\u0007\n\u0019q\f\n\u001b\u0012\u0005\u0011;\u0005C\u0001\u0019F\u0013\t1\u0015GA\u0004O_RD\u0017N\\4\u0011\u0005AB\u0015BA%2\u0005\r\te.\u001f\u0005\u0006\u0017\u0016\u0001\r\u0001T\u0001\be\u00164G+\u001f9f!\ti\u0005+D\u0001O\u0015\tyE$\u0001\u0003usB,\u0017BA)O\u00055\u0011VMZ3sK:\u001cW\rV=qK\")1+\u0002a\u0001)\u000611m\u001c8gS\u001e\u0004\"\u0001P+\n\u0005Yc\"!\u0006#fg\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:4\u0017n\u001a\u0005\u00061\u0016\u0001\r!W\u0001\tE\u0016\fg\u000eR3tGB\u0011AHW\u0005\u00037r\u0011qBQ3b]\u0012+7o\u0019:jaRLwN\u001c\u0005\u0006;\u0016\u0001\rAX\u0001\u0018G>tG/\u001a8u)f\u0004X\rR3tKJL\u0017\r\\5{KJ\u0004\"a\u00182\u000e\u0003\u0001T!!\u0019\u000f\u0002\u0011)\u001cxN\u001c;za\u0016L!a\u00191\u0003!QK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\"B3\u0006\u0001\u00041\u0017aE2p]R,g\u000e\u001e#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bGA4j!\raT\b\u001b\t\u0003\u0001&$\u0011B\u001b3\u0002\u0002\u0003\u0005)\u0011A\"\u0003\u0007}#3\u0007"
)
public final class OptionDeserializerResolver {
   public static JsonDeserializer findReferenceDeserializer(final ReferenceType refType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer contentTypeDeserializer, final JsonDeserializer contentDeserializer) {
      return OptionDeserializerResolver$.MODULE$.findReferenceDeserializer(refType, config, beanDesc, contentTypeDeserializer, contentDeserializer);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findBeanDeserializer(final JavaType x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findBeanDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return OptionDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return OptionDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
