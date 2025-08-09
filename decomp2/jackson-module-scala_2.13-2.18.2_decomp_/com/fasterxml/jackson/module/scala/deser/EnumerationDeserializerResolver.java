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
   bytes = "\u0006\u00055;QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u00048\u0003\u0001\u0006Ia\n\u0005\u0006q\u0005!\t%O\u0001 \u000b:,X.\u001a:bi&|g\u000eR3tKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(B\u0001\u0005\n\u0003\u0015!Wm]3s\u0015\tQ1\"A\u0003tG\u0006d\u0017M\u0003\u0002\r\u001b\u00051Qn\u001c3vY\u0016T!AD\b\u0002\u000f)\f7m[:p]*\u0011\u0001#E\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011AE\u0001\u0004G>l7\u0001\u0001\t\u0003+\u0005i\u0011a\u0002\u0002 \u000b:,X.\u001a:bi&|g\u000eR3tKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u00148CA\u0001\u0019!\tI\u0002E\u0004\u0002\u001b=5\t1D\u0003\u0002\t9)\u0011Q$D\u0001\tI\u0006$\u0018MY5oI&\u0011qdG\u0001\u000e\t\u0016\u001cXM]5bY&TXM]:\n\u0005\u0005\u0012#\u0001\u0002\"bg\u0016T!aH\u000e\u0002\rqJg.\u001b;?)\u0005!\u0012aC#O+6+%+\u0011+J\u001f:+\u0012a\n\t\u0004Q5zS\"A\u0015\u000b\u0005)Z\u0013\u0001\u00027b]\u001eT\u0011\u0001L\u0001\u0005U\u00064\u0018-\u0003\u0002/S\t)1\t\\1tgB\u0011\u0001'\u000e\t\u0003cMj\u0011A\r\u0006\u0002\u0015%\u0011AG\r\u0002\f\u000b:,X.\u001a:bi&|g.\u0003\u00027g\t)a+\u00197vK\u0006aQIT+N\u000bJ\u000bE+S(OA\u0005!b-\u001b8e\u0005\u0016\fg\u000eR3tKJL\u0017\r\\5{KJ$BAO\u001fD\u0011B\u0011QcO\u0005\u0003y\u001d\u0011q#\u00128v[\u0016\u0014\u0018\r^5p]\u0012+7/\u001a:jC2L'0\u001a:\t\u000by*\u0001\u0019A \u0002\u0011)\fg/\u0019+za\u0016\u0004\"\u0001Q!\u000e\u0003qI!A\u0011\u000f\u0003\u0011)\u000bg/\u0019+za\u0016DQ\u0001R\u0003A\u0002\u0015\u000baaY8oM&<\u0007C\u0001!G\u0013\t9EDA\u000bEKN,'/[1mSj\fG/[8o\u0007>tg-[4\t\u000b%+\u0001\u0019\u0001&\u0002\u0011\t,\u0017M\u001c#fg\u000e\u0004\"\u0001Q&\n\u00051c\"a\u0004\"fC:$Um]2sSB$\u0018n\u001c8"
)
public final class EnumerationDeserializerResolver {
   public static EnumerationDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return EnumerationDeserializerResolver$.MODULE$.findBeanDeserializer(javaType, config, beanDesc);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return EnumerationDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return EnumerationDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
