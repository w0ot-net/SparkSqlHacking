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
   bytes = "\u0006\u0005-<QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u0004:\u0003\u0001\u0006Ia\n\u0005\u0006\u0003\u0006!\tEQ\u0001\u0019\u001fB$\u0018n\u001c8TKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(B\u0001\u0005\n\u0003\r\u0019XM\u001d\u0006\u0003\u0015-\tQa]2bY\u0006T!\u0001D\u0007\u0002\r5|G-\u001e7f\u0015\tqq\"A\u0004kC\u000e\\7o\u001c8\u000b\u0005A\t\u0012!\u00034bgR,'\u000f_7m\u0015\u0005\u0011\u0012aA2p[\u000e\u0001\u0001CA\u000b\u0002\u001b\u00059!\u0001G(qi&|gnU3sS\u0006d\u0017N_3s%\u0016\u001cx\u000e\u001c<feN\u0011\u0011\u0001\u0007\t\u00033\u0001r!A\u0007\u0010\u000e\u0003mQ!\u0001\u0003\u000f\u000b\u0005ui\u0011\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005}Y\u0012aC*fe&\fG.\u001b>feNL!!\t\u0012\u0003\t\t\u000b7/\u001a\u0006\u0003?m\ta\u0001P5oSRtD#\u0001\u000b\u0002\r=\u0003F+S(O+\u00059\u0003c\u0001\u0015._5\t\u0011F\u0003\u0002+W\u0005!A.\u00198h\u0015\u0005a\u0013\u0001\u00026bm\u0006L!AL\u0015\u0003\u000b\rc\u0017m]:1\u0005A:\u0004cA\u00194k5\t!GC\u0001\u000b\u0013\t!$G\u0001\u0004PaRLwN\u001c\t\u0003m]b\u0001\u0001B\u00059\t\u0005\u0005\t\u0011!B\u0001u\t!q\fJ\u00199\u0003\u001dy\u0005\u000bV%P\u001d\u0002\n\"a\u000f \u0011\u0005Eb\u0014BA\u001f3\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!M \n\u0005\u0001\u0013$aA!os\u00069b-\u001b8e%\u00164WM]3oG\u0016\u001cVM]5bY&TXM\u001d\u000b\u0007\u0007.\u0003\u0006,X31\u0005\u0011K\u0005cA#G\u00116\tA$\u0003\u0002H9\tq!j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001\u001cJ\t%QU!!A\u0001\u0002\u000b\u0005!H\u0001\u0003`IEJ\u0004\"\u0002'\u0006\u0001\u0004i\u0015AB2p]\u001aLw\r\u0005\u0002F\u001d&\u0011q\n\b\u0002\u0014'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:4\u0017n\u001a\u0005\u0006#\u0016\u0001\rAU\u0001\be\u00164G+\u001f9f!\t\u0019f+D\u0001U\u0015\t)F$\u0001\u0003usB,\u0017BA,U\u00055\u0011VMZ3sK:\u001cW\rV=qK\")\u0011,\u0002a\u00015\u0006A!-Z1o\t\u0016\u001c8\r\u0005\u0002F7&\u0011A\f\b\u0002\u0010\u0005\u0016\fg\u000eR3tGJL\u0007\u000f^5p]\")a,\u0002a\u0001?\u0006)2m\u001c8uK:$H+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u00011d\u001b\u0005\t'B\u00012\u001d\u0003!Q7o\u001c8usB,\u0017B\u00013b\u00059!\u0016\u0010]3TKJL\u0017\r\\5{KJDQAZ\u0003A\u0002\u001d\facY8oi\u0016tGOV1mk\u0016\u001cVM]5bY&TXM\u001d\t\u0004\u000b\u001aC\u0007CA\u0019j\u0013\tQ'G\u0001\u0004B]f\u0014VM\u001a"
)
public final class OptionSerializerResolver {
   public static JsonSerializer findReferenceSerializer(final SerializationConfig config, final ReferenceType refType, final BeanDescription beanDesc, final TypeSerializer contentTypeSerializer, final JsonSerializer contentValueSerializer) {
      return OptionSerializerResolver$.MODULE$.findReferenceSerializer(config, refType, beanDesc, contentTypeSerializer, contentValueSerializer);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return OptionSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return OptionSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig x$1, final CollectionLikeType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return OptionSerializerResolver$.MODULE$.findCollectionLikeSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return OptionSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return OptionSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findSerializer(final SerializationConfig x$1, final JavaType x$2, final BeanDescription x$3) {
      return OptionSerializerResolver$.MODULE$.findSerializer(x$1, x$2, x$3);
   }
}
