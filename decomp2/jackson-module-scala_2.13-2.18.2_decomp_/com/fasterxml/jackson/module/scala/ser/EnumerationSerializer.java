package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import scala.Enumeration;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m2Aa\u0001\u0003\u0005#!)A\u0005\u0001C\u0001K!)q\u0005\u0001C!Q\t)RI\\;nKJ\fG/[8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0003\u0007\u0003\r\u0019XM\u001d\u0006\u0003\u000f!\tQa]2bY\u0006T!!\u0003\u0006\u0002\r5|G-\u001e7f\u0015\tYA\"A\u0004kC\u000e\\7o\u001c8\u000b\u00055q\u0011!\u00034bgR,'\u000f_7m\u0015\u0005y\u0011aA2p[\u000e\u00011c\u0001\u0001\u0013AA\u00191C\u0006\r\u000e\u0003QQ!!\u0006\u0006\u0002\u0011\u0011\fG/\u00192j]\u0012L!a\u0006\u000b\u0003\u001d)\u001bxN\\*fe&\fG.\u001b>feB\u0011\u0011D\b\t\u00035qi\u0011a\u0007\u0006\u0002\u000f%\u0011Qd\u0007\u0002\f\u000b:,X.\u001a:bi&|g.\u0003\u0002 9\t)a+\u00197vKB\u0011\u0011EI\u0007\u0002\t%\u00111\u0005\u0002\u0002 \u0007>tG/\u001a=uk\u0006dWI\\;nKJ\fG/[8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u0001'!\t\t\u0003!A\u0005tKJL\u0017\r\\5{KR!\u0011\u0006\f\u00187!\tQ\"&\u0003\u0002,7\t!QK\\5u\u0011\u0015i#\u00011\u0001\u0019\u0003\u00151\u0018\r\\;f\u0011\u0015y#\u00011\u00011\u0003\u0011Qw-\u001a8\u0011\u0005E\"T\"\u0001\u001a\u000b\u0005MR\u0011\u0001B2pe\u0016L!!\u000e\u001a\u0003\u001b)\u001bxN\\$f]\u0016\u0014\u0018\r^8s\u0011\u00159$\u00011\u00019\u0003!\u0001(o\u001c<jI\u0016\u0014\bCA\n:\u0013\tQDC\u0001\nTKJL\u0017\r\\5{KJ\u0004&o\u001c<jI\u0016\u0014\b"
)
public class EnumerationSerializer extends JsonSerializer implements ContextualEnumerationSerializer {
   public JsonSerializer createContextual(final SerializerProvider serializerProvider, final BeanProperty beanProperty) {
      return ContextualEnumerationSerializer.createContextual$(this, serializerProvider, beanProperty);
   }

   public void serialize(final Enumeration.Value value, final JsonGenerator jgen, final SerializerProvider provider) {
      Field parentEnum = (Field).MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])value.getClass().getSuperclass().getDeclaredFields()), (f) -> BoxesRunTime.boxToBoolean($anonfun$serialize$1(f))).getOrElse(() -> {
         throw new RuntimeException("failed to find $outer field on Enumeration class");
      });
      String enumClass = scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(parentEnum.get(value).getClass().getName()), "$");
      jgen.writeStartObject();
      jgen.writeStringField("enumClass", enumClass);
      jgen.writeStringField("value", value.toString());
      jgen.writeEndObject();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$serialize$1(final Field f) {
      boolean var2;
      label23: {
         String var10000 = f.getName();
         String var1 = "$outer";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public EnumerationSerializer() {
      ContextualEnumerationSerializer.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
