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
   bytes = "\u0006\u0005q<Q\u0001C\u0005\t\nY1Q\u0001G\u0005\t\neAQ!J\u0001\u0005\u0002\u0019BqaJ\u0001C\u0002\u0013%\u0001\u0006\u0003\u0004>\u0003\u0001\u0006I!\u000b\u0005\b\u0013\u0006\u0011\r\u0011\"\u0003K\u0011\u0019\u0001\u0016\u0001)A\u0005\u0017\")\u0011+\u0001C!%\u0006)R*\u00199TKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(B\u0001\u0006\f\u0003\r\u0019XM\u001d\u0006\u0003\u00195\tQa]2bY\u0006T!AD\b\u0002\r5|G-\u001e7f\u0015\t\u0001\u0012#A\u0004kC\u000e\\7o\u001c8\u000b\u0005I\u0019\u0012!\u00034bgR,'\u000f_7m\u0015\u0005!\u0012aA2p[\u000e\u0001\u0001CA\f\u0002\u001b\u0005I!!F'baN+'/[1mSj,'OU3t_24XM]\n\u0003\u0003i\u0001\"a\u0007\u0012\u000f\u0005q\u0001S\"A\u000f\u000b\u0005)q\"BA\u0010\u0010\u0003!!\u0017\r^1cS:$\u0017BA\u0011\u001e\u0003-\u0019VM]5bY&TXM]:\n\u0005\r\"#\u0001\u0002\"bg\u0016T!!I\u000f\u0002\rqJg.\u001b;?)\u00051\u0012A\u0003\"B'\u0016{6\tT!T'V\t\u0011\u0006E\u0002+_Ej\u0011a\u000b\u0006\u0003Y5\nA\u0001\\1oO*\ta&\u0001\u0003kCZ\f\u0017B\u0001\u0019,\u0005\u0015\u0019E.Y:ta\r\u00114h\u0012\t\u0005g]Jd)D\u00015\u0015\t)d'\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001D\u0005\u0003qQ\u00121!T1q!\tQ4\b\u0004\u0001\u0005\u0013q\"\u0011\u0011!A\u0001\u0006\u0003q$\u0001B0%cE\n1BQ!T\u000b~\u001bE*Q*TAE\u0011qh\u0011\t\u0003\u0001\u0006k\u0011AN\u0005\u0003\u0005Z\u0012qAT8uQ&tw\r\u0005\u0002A\t&\u0011QI\u000e\u0002\u0004\u0003:L\bC\u0001\u001eH\t%AE!!A\u0001\u0002\u000b\u0005aH\u0001\u0003`IE\u0012\u0014A\u0006&T\u001f:\u001bVIU%B\u0019&S\u0016I\u0011'F?\u000ec\u0015iU*\u0016\u0003-\u00032AK\u0018M!\tie*D\u0001\u001f\u0013\tyeD\u0001\tKg>t7+\u001a:jC2L'0\u00192mK\u00069\"jU(O'\u0016\u0013\u0016*\u0011'J5\u0006\u0013E*R0D\u0019\u0006\u001b6\u000bI\u0001\u0016M&tG-T1q\u0019&\\WmU3sS\u0006d\u0017N_3s)\u001d\u0019&lX4mej\u0004$\u0001\u0016-\u0011\u00075+v+\u0003\u0002W=\tq!j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001\u001eY\t%Iv!!A\u0001\u0002\u000b\u0005aH\u0001\u0003`IE\u001a\u0004\"B.\b\u0001\u0004a\u0016AB2p]\u001aLw\r\u0005\u0002N;&\u0011aL\b\u0002\u0014'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:4\u0017n\u001a\u0005\u0006A\u001e\u0001\r!Y\u0001\f[\u0006\u0004H*[6f)f\u0004X\r\u0005\u0002cK6\t1M\u0003\u0002e=\u0005!A/\u001f9f\u0013\t17MA\u0006NCBd\u0015n[3UsB,\u0007\"\u00025\b\u0001\u0004I\u0017\u0001\u00032fC:$Um]2\u0011\u00055S\u0017BA6\u001f\u0005=\u0011U-\u00198EKN\u001c'/\u001b9uS>t\u0007\"B7\b\u0001\u0004q\u0017!D6fsN+'/[1mSj,'\u000fE\u0002N+>\u0004\"\u0001\u00119\n\u0005E4$AB!osJ+g\rC\u0003t\u000f\u0001\u0007A/A\u000bfY\u0016lWM\u001c;UsB,7+\u001a:jC2L'0\u001a:\u0011\u0005UDX\"\u0001<\u000b\u0005]t\u0012\u0001\u00036t_:$\u0018\u0010]3\n\u0005e4(A\u0004+za\u0016\u001cVM]5bY&TXM\u001d\u0005\u0006w\u001e\u0001\rA\\\u0001\u0017K2,W.\u001a8u-\u0006dW/Z*fe&\fG.\u001b>fe\u0002"
)
public final class MapSerializerResolver {
   public static JsonSerializer findMapLikeSerializer(final SerializationConfig config, final MapLikeType mapLikeType, final BeanDescription beanDesc, final JsonSerializer keySerializer, final TypeSerializer elementTypeSerializer, final JsonSerializer elementValueSerializer) {
      return MapSerializerResolver$.MODULE$.findMapLikeSerializer(config, mapLikeType, beanDesc, keySerializer, elementTypeSerializer, elementValueSerializer);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return MapSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig x$1, final CollectionLikeType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return MapSerializerResolver$.MODULE$.findCollectionLikeSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return MapSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return MapSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findReferenceSerializer(final SerializationConfig x$1, final ReferenceType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return MapSerializerResolver$.MODULE$.findReferenceSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findSerializer(final SerializationConfig x$1, final JavaType x$2, final BeanDescription x$3) {
      return MapSerializerResolver$.MODULE$.findSerializer(x$1, x$2, x$3);
   }
}
