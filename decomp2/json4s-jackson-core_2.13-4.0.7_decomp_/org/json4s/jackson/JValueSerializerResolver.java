package org.json4s.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019;Q!\u0002\u0004\t\n51Qa\u0004\u0004\t\nAAQAI\u0001\u0005\u0002\rBa\u0001J\u0001!\u0002\u0013)\u0003\"B\u0019\u0002\t\u0003\u0012\u0014\u0001\u0007&WC2,XmU3sS\u0006d\u0017N_3s%\u0016\u001cx\u000e\u001c<fe*\u0011q\u0001C\u0001\bU\u0006\u001c7n]8o\u0015\tI!\"\u0001\u0004kg>tGg\u001d\u0006\u0002\u0017\u0005\u0019qN]4\u0004\u0001A\u0011a\"A\u0007\u0002\r\tA\"JV1mk\u0016\u001cVM]5bY&TXM\u001d*fg>dg/\u001a:\u0014\u0005\u0005\t\u0002C\u0001\n \u001d\t\u0019R$D\u0001\u0015\u0015\t)b#A\u0002tKJT!a\u0006\r\u0002\u0011\u0011\fG/\u00192j]\u0012T!aB\r\u000b\u0005iY\u0012!\u00034bgR,'\u000f_7m\u0015\u0005a\u0012aA2p[&\u0011a\u0004F\u0001\f'\u0016\u0014\u0018.\u00197ju\u0016\u00148/\u0003\u0002!C\t!!)Y:f\u0015\tqB#\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\u00051!JV!M+\u0016\u00032AJ\u0016.\u001b\u00059#B\u0001\u0015*\u0003\u0011a\u0017M\\4\u000b\u0003)\nAA[1wC&\u0011Af\n\u0002\u0006\u00072\f7o\u001d\t\u0003]=j\u0011\u0001C\u0005\u0003a!\u0011aA\u0013,bYV,\u0017A\u00044j]\u0012\u001cVM]5bY&TXM\u001d\u000b\u0005gYb\u0014\t\u0005\u0002\u000fi%\u0011QG\u0002\u0002\u0011\u0015Z\u000bG.^3TKJL\u0017\r\\5{KJDQa\u000e\u0003A\u0002a\naaY8oM&<\u0007CA\u001d;\u001b\u00051\u0012BA\u001e\u0017\u0005M\u0019VM]5bY&T\u0018\r^5p]\u000e{gNZ5h\u0011\u0015iD\u00011\u0001?\u0003\u001d!\b.\u001a+za\u0016\u0004\"!O \n\u0005\u00013\"\u0001\u0003&bm\u0006$\u0016\u0010]3\t\u000b\t#\u0001\u0019A\"\u0002\u0011\t,\u0017M\u001c#fg\u000e\u0004\"!\u000f#\n\u0005\u00153\"a\u0004\"fC:$Um]2sSB$\u0018n\u001c8"
)
public final class JValueSerializerResolver {
   public static JValueSerializer findSerializer(final SerializationConfig config, final JavaType theType, final BeanDescription beanDesc) {
      return JValueSerializerResolver$.MODULE$.findSerializer(config, theType, beanDesc);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return JValueSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return JValueSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig x$1, final CollectionLikeType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return JValueSerializerResolver$.MODULE$.findCollectionLikeSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return JValueSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return JValueSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findReferenceSerializer(final SerializationConfig x$1, final ReferenceType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return JValueSerializerResolver$.MODULE$.findReferenceSerializer(x$1, x$2, x$3, x$4, x$5);
   }
}
