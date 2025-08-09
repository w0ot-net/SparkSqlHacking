package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.module.scala.JacksonModule;
import scala.Function1;
import scala.collection.mutable.Builder;

public final class ScalaObjectDeserializerModule$ extends Module implements ScalaObjectDeserializerModule {
   public static final ScalaObjectDeserializerModule$ MODULE$ = new ScalaObjectDeserializerModule$();
   private static Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers;

   static {
      JacksonModule.$init$(MODULE$);
      ScalaObjectDeserializerModule.$init$(MODULE$);
   }

   public String getModuleName() {
      return ScalaObjectDeserializerModule.getModuleName$(this);
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

   private ScalaObjectDeserializerModule$() {
   }
}
