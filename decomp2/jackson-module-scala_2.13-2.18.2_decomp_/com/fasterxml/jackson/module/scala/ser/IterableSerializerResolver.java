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
   bytes = "\u0006\u0005\u0005=q!\u0002\u0006\f\u0011\u0013Ab!\u0002\u000e\f\u0011\u0013Y\u0002\"B\u0014\u0002\t\u0003A\u0003bB\u0015\u0002\u0005\u0004%IA\u000b\u0005\u0007o\u0005\u0001\u000b\u0011B\u0016\t\u000fa\n!\u0019!C\u0005s!1q)\u0001Q\u0001\niBq\u0001U\u0001C\u0002\u0013%\u0011\u000b\u0003\u0004[\u0003\u0001\u0006IA\u0015\u0005\u0006=\u0006!\teX\u0001\u001b\u0013R,'/\u00192mKN+'/[1mSj,'OU3t_24XM\u001d\u0006\u0003\u00195\t1a]3s\u0015\tqq\"A\u0003tG\u0006d\u0017M\u0003\u0002\u0011#\u00051Qn\u001c3vY\u0016T!AE\n\u0002\u000f)\f7m[:p]*\u0011A#F\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011AF\u0001\u0004G>l7\u0001\u0001\t\u00033\u0005i\u0011a\u0003\u0002\u001b\u0013R,'/\u00192mKN+'/[1mSj,'OU3t_24XM]\n\u0003\u0003q\u0001\"!\b\u0013\u000f\u0005y\u0011S\"A\u0010\u000b\u00051\u0001#BA\u0011\u0012\u0003!!\u0017\r^1cS:$\u0017BA\u0012 \u0003-\u0019VM]5bY&TXM]:\n\u0005\u00152#\u0001\u0002\"bg\u0016T!aI\u0010\u0002\rqJg.\u001b;?)\u0005A\u0012A\u0006&T\u001f:\u001bVIU%B\u0019&S\u0016I\u0011'F?\u000ec\u0015iU*\u0016\u0003-\u00022\u0001L\u00194\u001b\u0005i#B\u0001\u00180\u0003\u0011a\u0017M\\4\u000b\u0003A\nAA[1wC&\u0011!'\f\u0002\u0006\u00072\f7o\u001d\t\u0003iUj\u0011\u0001I\u0005\u0003m\u0001\u0012\u0001CS:p]N+'/[1mSj\f'\r\\3\u0002/)\u001bvJT*F%&\u000bE*\u0013.B\u00052+ul\u0011'B'N\u0003\u0013AD%U\u000bJ\u000b%\tT#`\u00072\u000b5kU\u000b\u0002uA\u0019A&M\u001e1\u0005q*\u0005cA\u001fB\u00076\taH\u0003\u0002@\u0001\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00039I!A\u0011 \u0003\u0011%#XM]1cY\u0016\u0004\"\u0001R#\r\u0001\u0011IaIBA\u0001\u0002\u0003\u0015\t\u0001\u0013\u0002\u0004?\u00122\u0014aD%U\u000bJ\u000b%\tT#`\u00072\u000b5k\u0015\u0011\u0012\u0005%k\u0005C\u0001&L\u001b\u0005\u0001\u0015B\u0001'A\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0013(\n\u0005=\u0003%aA!os\u0006IQ*\u0011)`\u00072\u000b5kU\u000b\u0002%B\u0019A&M*1\u0007QCF\f\u0005\u0003>+^[\u0016B\u0001,?\u0005\ri\u0015\r\u001d\t\u0003\tb#\u0011\"\u0017\u0005\u0002\u0002\u0003\u0005)\u0011\u0001%\u0003\u0007}#s'\u0001\u0006N\u0003B{6\tT!T'\u0002\u0002\"\u0001\u0012/\u0005\u0013uC\u0011\u0011!A\u0001\u0006\u0003A%aA0%q\u0005ab-\u001b8e\u0007>dG.Z2uS>tG*[6f'\u0016\u0014\u0018.\u00197ju\u0016\u0014Hc\u00021hYRL\u00181\u0001\u0019\u0003C\u0016\u00042\u0001\u000e2e\u0013\t\u0019\u0007E\u0001\bKg>t7+\u001a:jC2L'0\u001a:\u0011\u0005\u0011+G!\u00034\n\u0003\u0003\u0005\tQ!\u0001I\u0005\ryF%\u000f\u0005\u0006Q&\u0001\r![\u0001\u0007G>tg-[4\u0011\u0005QR\u0017BA6!\u0005M\u0019VM]5bY&T\u0018\r^5p]\u000e{gNZ5h\u0011\u0015i\u0017\u00021\u0001o\u00039\u0019w\u000e\u001c7fGRLwN\u001c+za\u0016\u0004\"a\u001c:\u000e\u0003AT!!\u001d\u0011\u0002\tQL\b/Z\u0005\u0003gB\u0014!cQ8mY\u0016\u001cG/[8o\u0019&\\W\rV=qK\")Q/\u0003a\u0001m\u0006y!-Z1o\t\u0016\u001c8M]5qi&|g\u000e\u0005\u00025o&\u0011\u0001\u0010\t\u0002\u0010\u0005\u0016\fg\u000eR3tGJL\u0007\u000f^5p]\")!0\u0003a\u0001w\u0006)R\r\\3nK:$H+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001?\u0000\u001b\u0005i(B\u0001@!\u0003!Q7o\u001c8usB,\u0017bAA\u0001{\nqA+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bbBA\u0003\u0013\u0001\u0007\u0011qA\u0001\u0012K2,W.\u001a8u'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003\u0002\u001bc\u0003\u0013\u00012\u0001LA\u0006\u0013\r\ti!\f\u0002\u0007\u001f\nTWm\u0019;"
)
public final class IterableSerializerResolver {
   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig config, final CollectionLikeType collectionType, final BeanDescription beanDescription, final TypeSerializer elementTypeSerializer, final JsonSerializer elementSerializer) {
      return IterableSerializerResolver$.MODULE$.findCollectionLikeSerializer(config, collectionType, beanDescription, elementTypeSerializer, elementSerializer);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return IterableSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return IterableSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return IterableSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return IterableSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findReferenceSerializer(final SerializationConfig x$1, final ReferenceType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return IterableSerializerResolver$.MODULE$.findReferenceSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findSerializer(final SerializationConfig x$1, final JavaType x$2, final BeanDescription x$3) {
      return IterableSerializerResolver$.MODULE$.findSerializer(x$1, x$2, x$3);
   }
}
