package io.fabric8.kubernetes.model.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.util.List;
import java.util.stream.Collectors;

public class UnmatchedFieldTypeModule extends SimpleModule {
   private boolean logWarnings;
   private boolean restrictToTemplates;
   private static final ThreadLocal IN_TEMPLATE = ThreadLocal.withInitial(() -> false);

   public UnmatchedFieldTypeModule() {
      this(true, true);
   }

   public UnmatchedFieldTypeModule(boolean logWarnings, boolean restrictToTemplates) {
      this.logWarnings = logWarnings;
      this.restrictToTemplates = restrictToTemplates;
      this.setDeserializerModifier(new BeanDeserializerModifier() {
         public BeanDeserializerBuilder updateBuilder(DeserializationConfig config, BeanDescription beanDesc, BeanDeserializerBuilder builder) {
            builder.getProperties().forEachRemaining((p) -> builder.addOrReplaceProperty(new SettableBeanPropertyDelegating(p, builder.getAnySetter(), UnmatchedFieldTypeModule.this::useAnySetter) {
               }, true));
            return builder;
         }
      });
      this.setSerializerModifier(new BeanSerializerModifier() {
         public BeanSerializerBuilder updateBuilder(SerializationConfig config, BeanDescription beanDesc, BeanSerializerBuilder builder) {
            builder.setProperties((List)builder.getProperties().stream().map((p) -> new BeanPropertyWriterDelegate(p, builder.getBeanDescription().findAnyGetter(), UnmatchedFieldTypeModule.this::isLogWarnings)).collect(Collectors.toList()));
            return builder;
         }
      });
   }

   boolean isLogWarnings() {
      return this.logWarnings;
   }

   public void setLogWarnings(boolean logWarnings) {
      this.logWarnings = logWarnings;
   }

   boolean isRestrictToTemplates() {
      return this.restrictToTemplates;
   }

   boolean useAnySetter() {
      return !this.restrictToTemplates || isInTemplate();
   }

   public void setRestrictToTemplates(boolean restrictToTemplates) {
      this.restrictToTemplates = restrictToTemplates;
   }

   public static boolean isInTemplate() {
      return Boolean.TRUE.equals(IN_TEMPLATE.get());
   }

   public static void setInTemplate() {
      IN_TEMPLATE.set(true);
   }

   public static void removeInTemplate() {
      IN_TEMPLATE.remove();
   }
}
