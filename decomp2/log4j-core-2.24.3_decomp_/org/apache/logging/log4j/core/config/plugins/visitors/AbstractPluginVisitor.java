package org.apache.logging.log4j.core.config.plugins.visitors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;

public abstract class AbstractPluginVisitor implements PluginVisitor {
   protected static final Logger LOGGER = StatusLogger.getLogger();
   protected final Class clazz;
   protected Annotation annotation;
   protected String[] aliases;
   protected Class conversionType;
   protected StrSubstitutor substitutor;
   protected Member member;

   protected AbstractPluginVisitor(final Class clazz) {
      this.clazz = clazz;
   }

   public PluginVisitor setAnnotation(final Annotation anAnnotation) {
      Annotation a = (Annotation)Objects.requireNonNull(anAnnotation, "No annotation was provided");
      if (this.clazz.isInstance(a)) {
         this.annotation = a;
      }

      return this;
   }

   public PluginVisitor setAliases(final String... someAliases) {
      this.aliases = someAliases;
      return this;
   }

   public PluginVisitor setConversionType(final Class aConversionType) {
      this.conversionType = (Class)Objects.requireNonNull(aConversionType, "No conversion type class was provided");
      return this;
   }

   public PluginVisitor setStrSubstitutor(final StrSubstitutor aSubstitutor) {
      this.substitutor = (StrSubstitutor)Objects.requireNonNull(aSubstitutor, "No StrSubstitutor was provided");
      return this;
   }

   public PluginVisitor setMember(final Member aMember) {
      this.member = aMember;
      return this;
   }

   protected static String removeAttributeValue(final Map attributes, final String name, final String... aliases) {
      for(Map.Entry entry : attributes.entrySet()) {
         String key = (String)entry.getKey();
         String value = (String)entry.getValue();
         if (key.equalsIgnoreCase(name)) {
            attributes.remove(key);
            return value;
         }

         if (aliases != null) {
            for(String alias : aliases) {
               if (key.equalsIgnoreCase(alias)) {
                  attributes.remove(key);
                  return value;
               }
            }
         }
      }

      return null;
   }

   protected Object convert(final String value, final Object defaultValue) {
      return defaultValue instanceof String ? TypeConverters.convert(value, this.conversionType, Strings.trimToNull((String)defaultValue)) : TypeConverters.convert(value, this.conversionType, defaultValue);
   }
}
