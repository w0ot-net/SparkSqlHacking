package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.module.scala.deser.ImmutableBitSetDeserializer$;
import com.fasterxml.jackson.module.scala.deser.MutableBitSetDeserializer$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.None.;
import scala.collection.immutable.BitSet;
import scala.collection.mutable.Builder;
import scala.runtime.BoxedUnit;

public final class BitSetDeserializerModule$ extends Module implements JacksonModule {
   public static final BitSetDeserializerModule$ MODULE$ = new BitSetDeserializerModule$();
   private static Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers;

   static {
      JacksonModule.$init$(MODULE$);
      MODULE$.$plus$eq((Function1)((x$1) -> {
         $anonfun$new$1(x$1);
         return BoxedUnit.UNIT;
      }));
   }

   public Version version() {
      return JacksonModule.version$(this);
   }

   public void setupModule(final Module.SetupContext context) {
      JacksonModule.setupModule$(this, context);
   }

   public JacksonModule $plus$eq(final Function1 init) {
      return JacksonModule.$plus$eq$(this, (Function1)init);
   }

   public JacksonModule $plus$eq(final Serializers ser) {
      return JacksonModule.$plus$eq$(this, (Serializers)ser);
   }

   public JacksonModule $plus$eq(final Deserializers deser) {
      return JacksonModule.$plus$eq$(this, (Deserializers)deser);
   }

   public JacksonModule $plus$eq(final TypeModifier typeMod) {
      return JacksonModule.$plus$eq$(this, (TypeModifier)typeMod);
   }

   public JacksonModule $plus$eq(final BeanSerializerModifier beanSerMod) {
      return JacksonModule.$plus$eq$(this, (BeanSerializerModifier)beanSerMod);
   }

   public Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers() {
      return com$fasterxml$jackson$module$scala$JacksonModule$$initializers;
   }

   public final void com$fasterxml$jackson$module$scala$JacksonModule$_setter_$com$fasterxml$jackson$module$scala$JacksonModule$$initializers_$eq(final Builder x$1) {
      com$fasterxml$jackson$module$scala$JacksonModule$$initializers = x$1;
   }

   public String getModuleName() {
      return "BitSetDeserializerModule";
   }

   // $FF: synthetic method
   public static final void $anonfun$new$1(final Module.SetupContext x$1) {
      x$1.addDeserializers(new Deserializers.Base() {
         private final Class IMMUTABLE_BITSET_CLASS = BitSet.class;
         private final Class MUTABLE_BITSET_CLASS = scala.collection.mutable.BitSet.class;

         private Class IMMUTABLE_BITSET_CLASS() {
            return this.IMMUTABLE_BITSET_CLASS;
         }

         private Class MUTABLE_BITSET_CLASS() {
            return this.MUTABLE_BITSET_CLASS;
         }

         public JsonDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
            Class rawClass = javaType.getRawClass();
            if (this.IMMUTABLE_BITSET_CLASS().isAssignableFrom(rawClass)) {
               return ImmutableBitSetDeserializer$.MODULE$;
            } else {
               return (JsonDeserializer)(this.MUTABLE_BITSET_CLASS().isAssignableFrom(rawClass) ? MutableBitSetDeserializer$.MODULE$ : (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
            }
         }
      });
   }

   private BitSetDeserializerModule$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
