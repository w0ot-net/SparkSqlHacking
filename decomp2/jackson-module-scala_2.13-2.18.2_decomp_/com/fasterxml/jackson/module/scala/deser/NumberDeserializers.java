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
   bytes = "\u0006\u0005!<Q\u0001C\u0005\t\nY1Q\u0001G\u0005\t\neAQ!J\u0001\u0005\u0002\u0019BqaJ\u0001C\u0002\u0013%\u0001\u0006\u0003\u0004?\u0003\u0001\u0006I!\u000b\u0005\b\u007f\u0005\u0011\r\u0011\"\u0003A\u0011\u0019)\u0015\u0001)A\u0005\u0003\")a)\u0001C!\u000f\u0006\u0019b*^7cKJ$Um]3sS\u0006d\u0017N_3sg*\u0011!bC\u0001\u0006I\u0016\u001cXM\u001d\u0006\u0003\u00195\tQa]2bY\u0006T!AD\b\u0002\r5|G-\u001e7f\u0015\t\u0001\u0012#A\u0004kC\u000e\\7o\u001c8\u000b\u0005I\u0019\u0012!\u00034bgR,'\u000f_7m\u0015\u0005!\u0012aA2p[\u000e\u0001\u0001CA\f\u0002\u001b\u0005I!a\u0005(v[\n,'\u000fR3tKJL\u0017\r\\5{KJ\u001c8CA\u0001\u001b!\tY\"E\u0004\u0002\u001dA5\tQD\u0003\u0002\u000b=)\u0011qdD\u0001\tI\u0006$\u0018MY5oI&\u0011\u0011%H\u0001\u000e\t\u0016\u001cXM]5bY&TXM]:\n\u0005\r\"#\u0001\u0002\"bg\u0016T!!I\u000f\u0002\rqJg.\u001b;?)\u00051\u0012a\u0004\"jO\u0012+7-[7bY\u000ec\u0017m]:\u0016\u0003%\u00022AK\u00182\u001b\u0005Y#B\u0001\u0017.\u0003\u0011a\u0017M\\4\u000b\u00039\nAA[1wC&\u0011\u0001g\u000b\u0002\u0006\u00072\f7o\u001d\t\u0003emr!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y*\u0012A\u0002\u001fs_>$h(C\u0001\r\u0013\tI$(A\u0004qC\u000e\\\u0017mZ3\u000b\u00031I!\u0001P\u001f\u0003\u0015\tKw\rR3dS6\fGN\u0003\u0002:u\u0005\u0001\")[4EK\u000eLW.\u00197DY\u0006\u001c8\u000fI\u0001\f\u0005&<\u0017J\u001c;DY\u0006\u001c8/F\u0001B!\rQsF\u0011\t\u0003e\rK!\u0001R\u001f\u0003\r\tKw-\u00138u\u00031\u0011\u0015nZ%oi\u000ec\u0017m]:!\u0003Q1\u0017N\u001c3CK\u0006tG)Z:fe&\fG.\u001b>feR!\u0001*\u00170da\tIu\nE\u0002K\u00176k\u0011AH\u0005\u0003\u0019z\u0011\u0001CS:p]\u0012+7/\u001a:jC2L'0\u001a:\u0011\u00059{E\u0002\u0001\u0003\n!\u001e\t\t\u0011!A\u0003\u0002E\u00131a\u0018\u00132#\t\u0011f\u000b\u0005\u0002T)6\t!(\u0003\u0002Vu\t9aj\u001c;iS:<\u0007CA*X\u0013\tA&HA\u0002B]fDQAW\u0004A\u0002m\u000b1\u0001\u001e9f!\tQE,\u0003\u0002^=\tA!*\u0019<b)f\u0004X\rC\u0003`\u000f\u0001\u0007\u0001-\u0001\u0004d_:4\u0017n\u001a\t\u0003\u0015\u0006L!A\u0019\u0010\u0003+\u0011+7/\u001a:jC2L'0\u0019;j_:\u001cuN\u001c4jO\")Am\u0002a\u0001K\u0006A!-Z1o\t\u0016\u001c8\r\u0005\u0002KM&\u0011qM\b\u0002\u0010\u0005\u0016\fg\u000eR3tGJL\u0007\u000f^5p]\u0002"
)
public final class NumberDeserializers {
   public static JsonDeserializer findBeanDeserializer(final JavaType tpe, final DeserializationConfig config, final BeanDescription beanDesc) {
      return NumberDeserializers$.MODULE$.findBeanDeserializer(tpe, config, beanDesc);
   }

   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findMapLikeDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return NumberDeserializers$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return NumberDeserializers$.MODULE$.hasDeserializerFor(x$1, x$2);
   }
}
