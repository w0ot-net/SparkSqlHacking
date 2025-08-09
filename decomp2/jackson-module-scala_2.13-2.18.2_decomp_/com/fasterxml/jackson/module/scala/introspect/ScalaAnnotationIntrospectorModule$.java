package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.fasterxml.jackson.module.scala.JacksonModule;
import com.fasterxml.jackson.module.scala.LookupCacheFactory;
import scala.Function1;
import scala.Option;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;

public final class ScalaAnnotationIntrospectorModule$ extends Module implements ScalaAnnotationIntrospectorModule {
   public static final ScalaAnnotationIntrospectorModule$ MODULE$ = new ScalaAnnotationIntrospectorModule$();
   private static LookupCacheFactory com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory;
   private static boolean com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes;
   private static int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize;
   private static int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize;
   private static LookupCache _descriptorCache;
   private static LookupCache _scalaTypeCache;
   private static Map overrideMap;
   private static Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers;

   static {
      JacksonModule.$init$(MODULE$);
      ScalaAnnotationIntrospectorModule.$init$(MODULE$);
   }

   public void setLookupCacheFactory(final LookupCacheFactory lookupCacheFactory) {
      ScalaAnnotationIntrospectorModule.setLookupCacheFactory$(this, lookupCacheFactory);
   }

   public void setDescriptorCacheSize(final int size) {
      ScalaAnnotationIntrospectorModule.setDescriptorCacheSize$(this, size);
   }

   public void setScalaTypeCacheSize(final int size) {
      ScalaAnnotationIntrospectorModule.setScalaTypeCacheSize$(this, size);
   }

   public void registerReferencedValueType(final Class clazz, final String fieldName, final Class referencedType) {
      ScalaAnnotationIntrospectorModule.registerReferencedValueType$(this, clazz, fieldName, referencedType);
   }

   public Option getRegisteredReferencedValueType(final Class clazz, final String fieldName) {
      return ScalaAnnotationIntrospectorModule.getRegisteredReferencedValueType$(this, clazz, fieldName);
   }

   public void clearRegisteredReferencedTypes(final Class clazz) {
      ScalaAnnotationIntrospectorModule.clearRegisteredReferencedTypes$(this, clazz);
   }

   public void clearRegisteredReferencedTypes() {
      ScalaAnnotationIntrospectorModule.clearRegisteredReferencedTypes$(this);
   }

   /** @deprecated */
   public LookupCache setDescriptorCache(final LookupCache cache) {
      return ScalaAnnotationIntrospectorModule.setDescriptorCache$(this, cache);
   }

   /** @deprecated */
   public LookupCache setScalaTypeCache(final LookupCache cache) {
      return ScalaAnnotationIntrospectorModule.setScalaTypeCache$(this, cache);
   }

   public void supportScala3Classes(final boolean support) {
      ScalaAnnotationIntrospectorModule.supportScala3Classes$(this, support);
   }

   public boolean shouldSupportScala3Classes() {
      return ScalaAnnotationIntrospectorModule.shouldSupportScala3Classes$(this);
   }

   public String getModuleName() {
      return JacksonModule.getModuleName$(this);
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

   public LookupCacheFactory com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory() {
      return com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory_$eq(final LookupCacheFactory x$1) {
      com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory = x$1;
   }

   public boolean com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes() {
      return com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes_$eq(final boolean x$1) {
      com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes = x$1;
   }

   public int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize() {
      return com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize_$eq(final int x$1) {
      com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize = x$1;
   }

   public int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize() {
      return com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize_$eq(final int x$1) {
      com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize = x$1;
   }

   public LookupCache _descriptorCache() {
      return _descriptorCache;
   }

   public void _descriptorCache_$eq(final LookupCache x$1) {
      _descriptorCache = x$1;
   }

   public LookupCache _scalaTypeCache() {
      return _scalaTypeCache;
   }

   public void _scalaTypeCache_$eq(final LookupCache x$1) {
      _scalaTypeCache = x$1;
   }

   public Map overrideMap() {
      return overrideMap;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$_setter_$overrideMap_$eq(final Map x$1) {
      overrideMap = x$1;
   }

   public Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers() {
      return com$fasterxml$jackson$module$scala$JacksonModule$$initializers;
   }

   public final void com$fasterxml$jackson$module$scala$JacksonModule$_setter_$com$fasterxml$jackson$module$scala$JacksonModule$$initializers_$eq(final Builder x$1) {
      com$fasterxml$jackson$module$scala$JacksonModule$$initializers = x$1;
   }

   private ScalaAnnotationIntrospectorModule$() {
   }
}
