package org.apache.logging.log4j.layout.template.json.resolver;

public interface TemplateResolverFactory {
   String CATEGORY = "JsonTemplateResolverFactory";

   Class getValueClass();

   Class getContextClass();

   String getName();

   TemplateResolver create(TemplateResolverContext context, TemplateResolverConfig config);
}
