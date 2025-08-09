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
   bytes = "\u0006\u0005e;QAB\u0004\t\nQ1QAF\u0004\t\n]AQaI\u0001\u0005\u0002\u0011Bq!J\u0001C\u0002\u0013%a\u0005\u0003\u00048\u0003\u0001\u0006Ia\n\u0005\u0006q\u0005!\t%O\u0001\u001e\u000b:,X.\u001a:bi&|gnU3sS\u0006d\u0017N_3s%\u0016\u001cx\u000e\u001c<fe*\u0011\u0001\"C\u0001\u0004g\u0016\u0014(B\u0001\u0006\f\u0003\u0015\u00198-\u00197b\u0015\taQ\"\u0001\u0004n_\u0012,H.\u001a\u0006\u0003\u001d=\tqA[1dWN|gN\u0003\u0002\u0011#\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002%\u0005\u00191m\\7\u0004\u0001A\u0011Q#A\u0007\u0002\u000f\tiRI\\;nKJ\fG/[8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014(+Z:pYZ,'o\u0005\u0002\u00021A\u0011\u0011\u0004\t\b\u00035yi\u0011a\u0007\u0006\u0003\u0011qQ!!H\u0007\u0002\u0011\u0011\fG/\u00192j]\u0012L!aH\u000e\u0002\u0017M+'/[1mSj,'o]\u0005\u0003C\t\u0012AAQ1tK*\u0011qdG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\t\u0011\"\u00128v[\u000ec\u0017m]:\u0016\u0003\u001d\u00022\u0001K\u00170\u001b\u0005I#B\u0001\u0016,\u0003\u0011a\u0017M\\4\u000b\u00031\nAA[1wC&\u0011a&\u000b\u0002\u0006\u00072\f7o\u001d\t\u0003aU\u0002\"!M\u001a\u000e\u0003IR\u0011AC\u0005\u0003iI\u00121\"\u00128v[\u0016\u0014\u0018\r^5p]&\u0011ag\r\u0002\u0006-\u0006dW/Z\u0001\u000b\u000b:,Xn\u00117bgN\u0004\u0013A\u00044j]\u0012\u001cVM]5bY&TXM\u001d\u000b\u0005u){E\u000b\r\u0002<\u0003B\u0019A(P \u000e\u0003qI!A\u0010\u000f\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>feB\u0011\u0001)\u0011\u0007\u0001\t%\u0011U!!A\u0001\u0002\u000b\u00051IA\u0002`IM\n\"\u0001R$\u0011\u0005E*\u0015B\u0001$3\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\r%\n\u0005%\u0013$aA!os\")1*\u0002a\u0001\u0019\u000611m\u001c8gS\u001e\u0004\"\u0001P'\n\u00059c\"aE*fe&\fG.\u001b>bi&|gnQ8oM&<\u0007\"\u0002)\u0006\u0001\u0004\t\u0016\u0001\u00036bm\u0006$\u0016\u0010]3\u0011\u0005q\u0012\u0016BA*\u001d\u0005!Q\u0015M^1UsB,\u0007\"B+\u0006\u0001\u00041\u0016a\u00042fC:$Um]2sSB$\u0018n\u001c8\u0011\u0005q:\u0016B\u0001-\u001d\u0005=\u0011U-\u00198EKN\u001c'/\u001b9uS>t\u0007"
)
public final class EnumerationSerializerResolver {
   public static JsonSerializer findSerializer(final SerializationConfig config, final JavaType javaType, final BeanDescription beanDescription) {
      return EnumerationSerializerResolver$.MODULE$.findSerializer(config, javaType, beanDescription);
   }

   public static JsonSerializer findMapLikeSerializer(final SerializationConfig x$1, final MapLikeType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return EnumerationSerializerResolver$.MODULE$.findMapLikeSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findMapSerializer(final SerializationConfig x$1, final MapType x$2, final BeanDescription x$3, final JsonSerializer x$4, final TypeSerializer x$5, final JsonSerializer x$6) {
      return EnumerationSerializerResolver$.MODULE$.findMapSerializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonSerializer findCollectionLikeSerializer(final SerializationConfig x$1, final CollectionLikeType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return EnumerationSerializerResolver$.MODULE$.findCollectionLikeSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findCollectionSerializer(final SerializationConfig x$1, final CollectionType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return EnumerationSerializerResolver$.MODULE$.findCollectionSerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findArraySerializer(final SerializationConfig x$1, final ArrayType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return EnumerationSerializerResolver$.MODULE$.findArraySerializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonSerializer findReferenceSerializer(final SerializationConfig x$1, final ReferenceType x$2, final BeanDescription x$3, final TypeSerializer x$4, final JsonSerializer x$5) {
      return EnumerationSerializerResolver$.MODULE$.findReferenceSerializer(x$1, x$2, x$3, x$4, x$5);
   }
}
