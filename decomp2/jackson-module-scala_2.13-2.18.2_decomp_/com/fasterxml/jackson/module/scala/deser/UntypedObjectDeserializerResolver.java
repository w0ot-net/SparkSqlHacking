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
   bytes = "\u0006\u0005!;Q!\u0002\u0004\t\nM1Q!\u0006\u0004\t\nYAQAI\u0001\u0005\u0002\rB\u0001\u0002J\u0001\t\u0006\u0004%\t!\n\u0005\u0006g\u0005!\t\u0005N\u0001\"+:$\u0018\u0010]3e\u001f\nTWm\u0019;EKN,'/[1mSj,'OU3t_24XM\u001d\u0006\u0003\u000f!\tQ\u0001Z3tKJT!!\u0003\u0006\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005-a\u0011AB7pIVdWM\u0003\u0002\u000e\u001d\u00059!.Y2lg>t'BA\b\u0011\u0003%1\u0017m\u001d;feblGNC\u0001\u0012\u0003\r\u0019w.\\\u0002\u0001!\t!\u0012!D\u0001\u0007\u0005\u0005*f\u000e^=qK\u0012|%M[3di\u0012+7/\u001a:jC2L'0\u001a:SKN|GN^3s'\t\tq\u0003\u0005\u0002\u0019?9\u0011\u0011$H\u0007\u00025)\u0011qa\u0007\u0006\u000391\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003=i\tQ\u0002R3tKJL\u0017\r\\5{KJ\u001c\u0018B\u0001\u0011\"\u0005\u0011\u0011\u0015m]3\u000b\u0005yQ\u0012A\u0002\u001fj]&$h\bF\u0001\u0014\u0003\u0019y%IS#D)V\ta\u0005E\u0002(Y9j\u0011\u0001\u000b\u0006\u0003S)\nA\u0001\\1oO*\t1&\u0001\u0003kCZ\f\u0017BA\u0017)\u0005\u0015\u0019E.Y:t!\ty\u0013'D\u00011\u0015\u0005I\u0011B\u0001\u001a1\u0005\u0019\te.\u001f*fM\u0006!b-\u001b8e\u0005\u0016\fg\u000eR3tKJL\u0017\r\\5{KJ$B!\u000e\u001d?\u0007B\u0011ACN\u0005\u0003o\u0019\u0011a$\u00168usB,GmU2bY\u0006|%M[3di\u0012+7/\u001a:jC2L'0\u001a:\t\u000be\"\u0001\u0019\u0001\u001e\u0002\u0011)\fg/\u0019+za\u0016\u0004\"a\u000f\u001f\u000e\u0003mI!!P\u000e\u0003\u0011)\u000bg/\u0019+za\u0016DQa\u0010\u0003A\u0002\u0001\u000baaY8oM&<\u0007CA\u001eB\u0013\t\u00115DA\u000bEKN,'/[1mSj\fG/[8o\u0007>tg-[4\t\u000b\u0011#\u0001\u0019A#\u0002\u0011\t,\u0017M\u001c#fg\u000e\u0004\"a\u000f$\n\u0005\u001d[\"a\u0004\"fC:$Um]2sSB$\u0018n\u001c8"
)
public final class UntypedObjectDeserializerResolver {
   public static UntypedScalaObjectDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return UntypedObjectDeserializerResolver$.MODULE$.findBeanDeserializer(javaType, config, beanDesc);
   }

   public static Class OBJECT() {
      return UntypedObjectDeserializerResolver$.MODULE$.OBJECT();
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return UntypedObjectDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return UntypedObjectDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
