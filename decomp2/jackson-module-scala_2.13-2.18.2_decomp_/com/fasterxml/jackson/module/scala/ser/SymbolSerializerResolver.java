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
   bytes = "\u0006\u0005);QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u00045\u0003\u0001\u0006Ia\n\u0005\u0006k\u0005!\tEN\u0001\u0019'fl'm\u001c7TKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(B\u0001\u0005\n\u0003\r\u0019XM\u001d\u0006\u0003\u0015-\tQa]2bY\u0006T!\u0001D\u0007\u0002\r5|G-\u001e7f\u0015\tqq\"A\u0004kC\u000e\\7o\u001c8\u000b\u0005A\t\u0012!\u00034bgR,'\u000f_7m\u0015\u0005\u0011\u0012aA2p[\u000e\u0001\u0001CA\u000b\u0002\u001b\u00059!\u0001G*z[\n|GnU3sS\u0006d\u0017N_3s%\u0016\u001cx\u000e\u001c<feN\u0011\u0011\u0001\u0007\t\u00033\u0001r!A\u0007\u0010\u000e\u0003mQ!\u0001\u0003\u000f\u000b\u0005ui\u0011\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005}Y\u0012aC*fe&\fG.\u001b>feNL!!\t\u0012\u0003\t\t\u000b7/\u001a\u0006\u0003?m\ta\u0001P5oSRtD#\u0001\u000b\u0002\rMKVJQ(M+\u00059\u0003c\u0001\u0015._5\t\u0011F\u0003\u0002+W\u0005!A.\u00198h\u0015\u0005a\u0013\u0001\u00026bm\u0006L!AL\u0015\u0003\u000b\rc\u0017m]:\u0011\u0005A\u0012T\"A\u0019\u000b\u0003)I!aM\u0019\u0003\rMKXNY8m\u0003\u001d\u0019\u0016,\u0014\"P\u0019\u0002\naBZ5oIN+'/[1mSj,'\u000f\u0006\u00038w\u0001+\u0005c\u0001\u001d:_5\tA$\u0003\u0002;9\tq!j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\"\u0002\u001f\u0006\u0001\u0004i\u0014AB2p]\u001aLw\r\u0005\u00029}%\u0011q\b\b\u0002\u0014'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:4\u0017n\u001a\u0005\u0006\u0003\u0016\u0001\rAQ\u0001\tU\u00064\u0018\rV=qKB\u0011\u0001hQ\u0005\u0003\tr\u0011\u0001BS1wCRK\b/\u001a\u0005\u0006\r\u0016\u0001\raR\u0001\tE\u0016\fg\u000eR3tGB\u0011\u0001\bS\u0005\u0003\u0013r\u0011qBQ3b]\u0012+7o\u0019:jaRLwN\u001c"
)
public final class SymbolSerializerResolver {
   public static JsonSerializer findSerializer(final SerializationConfig config, final JavaType javaType, final BeanDescription beanDesc) {
      return SymbolSerializerResolver$.MODULE$.findSerializer(config, javaType, beanDesc);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return SymbolSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return SymbolSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig x$1, final CollectionLikeType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return SymbolSerializerResolver$.MODULE$.findCollectionLikeSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return SymbolSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return SymbolSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findReferenceSerializer(final SerializationConfig x$1, final ReferenceType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return SymbolSerializerResolver$.MODULE$.findReferenceSerializer(x$1, x$2, x$3, x$4, x$5);
   }
}
