package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.fasterxml.jackson.module.scala.deser.EitherDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.EnumerationDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.OptionDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.ScalaNumberDeserializersModule;
import com.fasterxml.jackson.module.scala.deser.ScalaObjectDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.SeqDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.SortedMapDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.SortedSetDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.SymbolDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.TupleDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.UnsortedMapDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.UnsortedSetDeserializerModule;
import com.fasterxml.jackson.module.scala.deser.UntypedObjectDeserializerModule;
import com.fasterxml.jackson.module.scala.introspect.ScalaAnnotationIntrospectorModule;
import com.fasterxml.jackson.module.scala.modifiers.ScalaTypeModifierModule;
import com.fasterxml.jackson.module.scala.ser.EitherSerializerModule;
import com.fasterxml.jackson.module.scala.ser.EnumerationSerializerModule;
import com.fasterxml.jackson.module.scala.ser.IterableSerializerModule;
import com.fasterxml.jackson.module.scala.ser.IteratorSerializerModule;
import com.fasterxml.jackson.module.scala.ser.MapSerializerModule;
import com.fasterxml.jackson.module.scala.ser.OptionSerializerModule;
import com.fasterxml.jackson.module.scala.ser.SymbolSerializerModule;
import com.fasterxml.jackson.module.scala.ser.TupleSerializerModule;
import scala.Function1;
import scala.Option;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t4AAB\u0004\u0001%!)Q\n\u0001C\u0001\u001d\")\u0001\u000b\u0001C!#\u001e)al\u0002E\u0001?\u001a)aa\u0002E\u0001A\")Q\n\u0002C\u0001C\n\u0011B)\u001a4bk2$8kY1mC6{G-\u001e7f\u0015\tA\u0011\"A\u0003tG\u0006d\u0017M\u0003\u0002\u000b\u0017\u00051Qn\u001c3vY\u0016T!\u0001D\u0007\u0002\u000f)\f7m[:p]*\u0011abD\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001E\u0001\u0004G>l7\u0001A\n\u0012\u0001MIR\u0004I\u0012'S1z#'N\u001e?\t\u001eS\u0005C\u0001\u000b\u0018\u001b\u0005)\"B\u0001\f\f\u0003!!\u0017\r^1cS:$\u0017B\u0001\r\u0016\u0005\u0019iu\u000eZ;mKB\u0011!dG\u0007\u0002\u000f%\u0011Ad\u0002\u0002\u000e\u0015\u0006\u001c7n]8o\u001b>$W\u000f\\3\u0011\u0005iq\u0012BA\u0010\b\u00059IE/\u001a:bi>\u0014Xj\u001c3vY\u0016\u0004\"AG\u0011\n\u0005\t:!!E#ok6,'/\u0019;j_:lu\u000eZ;mKB\u0011!\u0004J\u0005\u0003K\u001d\u0011Ab\u00149uS>tWj\u001c3vY\u0016\u0004\"AG\u0014\n\u0005!:!!C*fc6{G-\u001e7f!\tQ\"&\u0003\u0002,\u000f\tq\u0011\n^3sC\ndW-T8ek2,\u0007C\u0001\u000e.\u0013\tqsAA\u0006UkBdW-T8ek2,\u0007C\u0001\u000e1\u0013\t\ttAA\u0005NCBlu\u000eZ;mKB\u0011!dM\u0005\u0003i\u001d\u0011\u0011bU3u\u001b>$W\u000f\\3\u0011\u0005YJT\"A\u001c\u000b\u0005a:\u0011!\u00023fg\u0016\u0014\u0018B\u0001\u001e8\u0005y\u00196-\u00197b\u001dVl'-\u001a:EKN,'/[1mSj,'o]'pIVdW\r\u0005\u00027y%\u0011Qh\u000e\u0002\u001e'\u000e\fG.Y(cU\u0016\u001cG\u000fR3tKJL\u0017\r\\5{KJlu\u000eZ;mKB\u0011qHQ\u0007\u0002\u0001*\u0011\u0011iB\u0001\u000bS:$(o\\:qK\u000e$\u0018BA\"A\u0005\u0005\u001a6-\u00197b\u0003:tw\u000e^1uS>t\u0017J\u001c;s_N\u0004Xm\u0019;pe6{G-\u001e7f!\t1T)\u0003\u0002Go\tyRK\u001c;za\u0016$wJ\u00196fGR$Um]3sS\u0006d\u0017N_3s\u001b>$W\u000f\\3\u0011\u0005iA\u0015BA%\b\u00051)\u0015\u000e\u001e5fe6{G-\u001e7f!\tQ2*\u0003\u0002M\u000f\ta1+_7c_2lu\u000eZ;mK\u00061A(\u001b8jiz\"\u0012a\u0014\t\u00035\u0001\tQbZ3u\u001b>$W\u000f\\3OC6,G#\u0001*\u0011\u0005M[fB\u0001+Z!\t)\u0006,D\u0001W\u0015\t9\u0016#\u0001\u0004=e>|GO\u0010\u0006\u0002\u0011%\u0011!\fW\u0001\u0007!J,G-\u001a4\n\u0005qk&AB*ue&twM\u0003\u0002[1\u0006\u0011B)\u001a4bk2$8kY1mC6{G-\u001e7f!\tQBa\u0005\u0002\u0005\u001fR\tq\f"
)
public class DefaultScalaModule extends Module implements IteratorModule, EnumerationModule, OptionModule, SeqModule, IterableModule, TupleModule, MapModule, SetModule, ScalaNumberDeserializersModule, ScalaObjectDeserializerModule, ScalaAnnotationIntrospectorModule, UntypedObjectDeserializerModule, EitherModule, SymbolModule {
   private LookupCacheFactory com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory;
   private boolean com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes;
   private int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize;
   private int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize;
   private LookupCache _descriptorCache;
   private LookupCache _scalaTypeCache;
   private Map overrideMap;
   private Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers;

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
      return this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory_$eq(final LookupCacheFactory x$1) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory = x$1;
   }

   public boolean com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes() {
      return this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes_$eq(final boolean x$1) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes = x$1;
   }

   public int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize() {
      return this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize_$eq(final int x$1) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize = x$1;
   }

   public int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize() {
      return this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize_$eq(final int x$1) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize = x$1;
   }

   public LookupCache _descriptorCache() {
      return this._descriptorCache;
   }

   public void _descriptorCache_$eq(final LookupCache x$1) {
      this._descriptorCache = x$1;
   }

   public LookupCache _scalaTypeCache() {
      return this._scalaTypeCache;
   }

   public void _scalaTypeCache_$eq(final LookupCache x$1) {
      this._scalaTypeCache = x$1;
   }

   public Map overrideMap() {
      return this.overrideMap;
   }

   public void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$_setter_$overrideMap_$eq(final Map x$1) {
      this.overrideMap = x$1;
   }

   public Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers() {
      return this.com$fasterxml$jackson$module$scala$JacksonModule$$initializers;
   }

   public final void com$fasterxml$jackson$module$scala$JacksonModule$_setter_$com$fasterxml$jackson$module$scala$JacksonModule$$initializers_$eq(final Builder x$1) {
      this.com$fasterxml$jackson$module$scala$JacksonModule$$initializers = x$1;
   }

   public String getModuleName() {
      return "DefaultScalaModule";
   }

   public DefaultScalaModule() {
      JacksonModule.$init$(this);
      ScalaTypeModifierModule.$init$(this);
      IteratorSerializerModule.$init$(this);
      IteratorModule.$init$(this);
      EnumerationSerializerModule.$init$(this);
      EnumerationDeserializerModule.$init$(this);
      EnumerationModule.$init$(this);
      OptionSerializerModule.$init$(this);
      OptionDeserializerModule.$init$(this);
      OptionModule.$init$(this);
      IterableSerializerModule.$init$(this);
      SeqDeserializerModule.$init$(this);
      SeqModule.$init$(this);
      IterableModule.$init$(this);
      TupleSerializerModule.$init$(this);
      TupleDeserializerModule.$init$(this);
      TupleModule.$init$(this);
      MapSerializerModule.$init$(this);
      UnsortedMapDeserializerModule.$init$(this);
      SortedMapDeserializerModule.$init$(this);
      MapModule.$init$(this);
      UnsortedSetDeserializerModule.$init$(this);
      SortedSetDeserializerModule.$init$(this);
      SetModule.$init$(this);
      ScalaNumberDeserializersModule.$init$(this);
      ScalaObjectDeserializerModule.$init$(this);
      ScalaAnnotationIntrospectorModule.$init$(this);
      UntypedObjectDeserializerModule.$init$(this);
      EitherDeserializerModule.$init$(this);
      EitherSerializerModule.$init$(this);
      EitherModule.$init$(this);
      SymbolSerializerModule.$init$(this);
      SymbolDeserializerModule.$init$(this);
      SymbolModule.$init$(this);
      Statics.releaseFence();
   }
}
