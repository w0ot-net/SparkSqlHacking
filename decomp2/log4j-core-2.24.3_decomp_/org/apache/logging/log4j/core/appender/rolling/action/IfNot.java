package org.apache.logging.log4j.core.appender.rolling.action;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

@Plugin(
   name = "IfNot",
   category = "Core",
   printObject = true
)
public final class IfNot implements PathCondition {
   private final PathCondition negate;

   private IfNot(final PathCondition negate) {
      this.negate = (PathCondition)Objects.requireNonNull(negate, "filter");
   }

   public PathCondition getWrappedFilter() {
      return this.negate;
   }

   public boolean accept(final Path baseDir, final Path relativePath, final BasicFileAttributes attrs) {
      return !this.negate.accept(baseDir, relativePath, attrs);
   }

   public void beforeFileTreeWalk() {
      this.negate.beforeFileTreeWalk();
   }

   @PluginFactory
   public static IfNot createNotCondition(@PluginElement("PathConditions") @Required(message = "No condition provided for IfNot") final PathCondition condition) {
      return new IfNot(condition);
   }

   public String toString() {
      return "IfNot(" + this.negate + ")";
   }
}
