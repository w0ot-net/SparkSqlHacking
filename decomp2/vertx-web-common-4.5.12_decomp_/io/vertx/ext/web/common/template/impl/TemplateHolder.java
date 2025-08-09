package io.vertx.ext.web.common.template.impl;

import io.vertx.core.shareddata.Shareable;

public class TemplateHolder implements Shareable {
   private final Object template;
   private final String baseDir;

   public TemplateHolder(Object template) {
      this(template, (String)null);
   }

   public TemplateHolder(Object template, String baseDir) {
      this.template = template;
      this.baseDir = baseDir;
   }

   public Object template() {
      return this.template;
   }

   public String baseDir() {
      return this.baseDir;
   }
}
