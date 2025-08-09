package com.fasterxml.jackson.module.scala.ser;

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
   bytes = "\u0006\u0005);QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u00045\u0003\u0001\u0006Ia\n\u0005\u0006k\u0005!\tEN\u0001\u0018)V\u0004H.Z*fe&\fG.\u001b>feJ+7o\u001c7wKJT!\u0001C\u0005\u0002\u0007M,'O\u0003\u0002\u000b\u0017\u0005)1oY1mC*\u0011A\"D\u0001\u0007[>$W\u000f\\3\u000b\u00059y\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003!E\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003I\t1aY8n\u0007\u0001\u0001\"!F\u0001\u000e\u0003\u001d\u0011q\u0003V;qY\u0016\u001cVM]5bY&TXM\u001d*fg>dg/\u001a:\u0014\u0005\u0005A\u0002CA\r!\u001d\tQb$D\u0001\u001c\u0015\tAAD\u0003\u0002\u001e\u001b\u0005AA-\u0019;bE&tG-\u0003\u0002 7\u0005Y1+\u001a:jC2L'0\u001a:t\u0013\t\t#E\u0001\u0003CCN,'BA\u0010\u001c\u0003\u0019a\u0014N\\5u}Q\tA#A\u0004Q%>#Uk\u0011+\u0016\u0003\u001d\u00022\u0001K\u00170\u001b\u0005I#B\u0001\u0016,\u0003\u0011a\u0017M\\4\u000b\u00031\nAA[1wC&\u0011a&\u000b\u0002\u0006\u00072\f7o\u001d\t\u0003aIj\u0011!\r\u0006\u0002\u0015%\u00111'\r\u0002\b!J|G-^2u\u0003!\u0001&k\u0014#V\u0007R\u0003\u0013A\u00044j]\u0012\u001cVM]5bY&TXM\u001d\u000b\u0005oi\u0002U\t\u0005\u0002\u0016q%\u0011\u0011h\u0002\u0002\u0010)V\u0004H.Z*fe&\fG.\u001b>fe\")1(\u0002a\u0001y\u000511m\u001c8gS\u001e\u0004\"!\u0010 \u000e\u0003qI!a\u0010\u000f\u0003'M+'/[1mSj\fG/[8o\u0007>tg-[4\t\u000b\u0005+\u0001\u0019\u0001\"\u0002\u0011)\fg/\u0019+za\u0016\u0004\"!P\"\n\u0005\u0011c\"\u0001\u0003&bm\u0006$\u0016\u0010]3\t\u000b\u0019+\u0001\u0019A$\u0002\u0011\t,\u0017M\u001c#fg\u000e\u0004\"!\u0010%\n\u0005%c\"a\u0004\"fC:$Um]2sSB$\u0018n\u001c8"
)
public final class TupleSerializerResolver {
   public static TupleSerializer findSerializer(final SerializationConfig config, final JavaType javaType, final BeanDescription beanDesc) {
      return TupleSerializerResolver$.MODULE$.findSerializer(config, javaType, beanDesc);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return TupleSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return TupleSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig x$1, final CollectionLikeType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return TupleSerializerResolver$.MODULE$.findCollectionLikeSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return TupleSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return TupleSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findReferenceSerializer(final SerializationConfig x$1, final ReferenceType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return TupleSerializerResolver$.MODULE$.findReferenceSerializer(x$1, x$2, x$3, x$4, x$5);
   }
}
