package org.apache.logging.log4j.layout.template.json.resolver;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

public interface TemplateResolverContext {
   Class getContextClass();

   Map getResolverFactoryByName();

   List getResolverInterceptors();

   TemplateResolverStringSubstitutor getSubstitutor();

   JsonWriter getJsonWriter();

   default Object processTemplateBeforeResolverInjection(final Object node) {
      return node;
   }
}
