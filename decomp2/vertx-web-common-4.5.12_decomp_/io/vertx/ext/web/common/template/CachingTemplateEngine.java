package io.vertx.ext.web.common.template;

import io.vertx.core.Vertx;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.common.WebEnvironment;
import io.vertx.ext.web.common.template.impl.TemplateHolder;
import java.util.Objects;

public abstract class CachingTemplateEngine implements TemplateEngine {
   private final LocalMap cache;
   protected String extension;

   protected CachingTemplateEngine(Vertx vertx, String ext) {
      if (!WebEnvironment.development()) {
         this.cache = vertx.sharedData().getLocalMap("__vertx.web.template.cache");
      } else {
         this.cache = null;
      }

      Objects.requireNonNull(ext);
      this.extension = ext.charAt(0) == '.' ? ext : "." + ext;
   }

   public TemplateHolder getTemplate(String filename) {
      return this.cache != null ? (TemplateHolder)this.cache.get(filename) : null;
   }

   public TemplateHolder putTemplate(String filename, TemplateHolder templateHolder) {
      return this.cache != null ? (TemplateHolder)this.cache.put(filename, templateHolder) : null;
   }

   protected String adjustLocation(String location) {
      if (this.extension != null && !location.endsWith(this.extension)) {
         location = location + this.extension;
      }

      return location;
   }

   public void clearCache() {
      this.cache.clear();
   }
}
