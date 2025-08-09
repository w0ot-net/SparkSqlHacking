package org.apache.logging.log4j.layout.template.json.resolver;

public interface TemplateResolverInterceptor {
   String CATEGORY = "JsonTemplateResolverInterceptor";

   Class getValueClass();

   Class getContextClass();

   default Object processTemplateBeforeResolverInjection(final TemplateResolverContext context, final Object node) {
      return node;
   }
}
