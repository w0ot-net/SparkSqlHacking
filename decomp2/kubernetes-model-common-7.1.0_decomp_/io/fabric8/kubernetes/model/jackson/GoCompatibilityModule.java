package io.fabric8.kubernetes.model.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class GoCompatibilityModule extends SimpleModule {
   public GoCompatibilityModule() {
      this.addDeserializer(Integer.class, new GoIntegerDeserializer());
   }
}
