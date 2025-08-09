package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

public final class OptionSerializer$ implements Serializable {
   public static final OptionSerializer$ MODULE$ = new OptionSerializer$();

   public boolean useStatic(final SerializerProvider provider, final Option property, final Option referredType) {
      if (referredType.isEmpty()) {
         return false;
      } else if (((JavaType)referredType.get()).isJavaLangObject()) {
         return false;
      } else if (((JavaType)referredType.get()).isFinal()) {
         return true;
      } else if (((JavaType)referredType.get()).useStaticType()) {
         return true;
      } else {
         ObjectRef result = ObjectRef.create(.MODULE$);
         scala.util.control.Breaks..MODULE$.breakable((JFunction0.mcV.sp)() -> property.flatMap((p) -> scala.Option..MODULE$.apply(p.getMember())).foreach((ann) -> {
               $anonfun$useStatic$3(provider, result, ann);
               return BoxedUnit.UNIT;
            }));
         Option var6 = (Option)result.elem;
         if (var6 instanceof Some) {
            Some var7 = (Some)var6;
            boolean bool = BoxesRunTime.unboxToBoolean(var7.value());
            return bool;
         } else {
            return provider.isEnabled(MapperFeature.USE_STATIC_TYPING);
         }
      }
   }

   public JsonSerializer findSerializer(final SerializerProvider provider, final Class typ, final Option prop) {
      return provider.findTypedValueSerializer(typ, true, (BeanProperty)prop.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   public JsonSerializer findSerializer(final SerializerProvider provider, final JavaType typ, final Option prop) {
      return provider.findTypedValueSerializer(typ, true, (BeanProperty)prop.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   public boolean hasContentTypeAnnotation(final SerializerProvider provider, final BeanProperty property) {
      AnnotationIntrospector intr = provider.getAnnotationIntrospector();
      if (property != null && intr != null) {
         return intr.refineSerializationType(provider.getConfig(), property.getMember(), property.getType()) != null;
      } else {
         return false;
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OptionSerializer$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$useStatic$4(final AnnotatedMember ann$1, final ObjectRef result$1, final AnnotationIntrospector intr) {
      label28: {
         JsonSerialize.Typing typing = intr.findSerializationTyping(ann$1);
         JsonSerialize.Typing var4 = Typing.STATIC;
         if (typing == null) {
            if (var4 == null) {
               break label28;
            }
         } else if (typing.equals(var4)) {
            break label28;
         }

         label20: {
            JsonSerialize.Typing var5 = Typing.DYNAMIC;
            if (typing == null) {
               if (var5 == null) {
                  break label20;
               }
            } else if (typing.equals(var5)) {
               break label20;
            }

            return;
         }

         result$1.elem = new Some(BoxesRunTime.boxToBoolean(false));
         throw scala.util.control.Breaks..MODULE$.break();
      }

      result$1.elem = new Some(BoxesRunTime.boxToBoolean(true));
      throw scala.util.control.Breaks..MODULE$.break();
   }

   // $FF: synthetic method
   public static final void $anonfun$useStatic$3(final SerializerProvider provider$1, final ObjectRef result$1, final AnnotatedMember ann) {
      scala.Option..MODULE$.apply(provider$1.getAnnotationIntrospector()).foreach((intr) -> {
         $anonfun$useStatic$4(ann, result$1, intr);
         return BoxedUnit.UNIT;
      });
   }

   private OptionSerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
