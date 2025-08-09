package org.apache.logging.log4j.layout.template.json.resolver;

final class StackTraceElementResolverFactory implements TemplateResolverFactory {
   private static final StackTraceElementResolverFactory INSTANCE = new StackTraceElementResolverFactory();

   private StackTraceElementResolverFactory() {
   }

   static StackTraceElementResolverFactory getInstance() {
      return INSTANCE;
   }

   public Class getValueClass() {
      return StackTraceElement.class;
   }

   public Class getContextClass() {
      return StackTraceElementResolverContext.class;
   }

   public String getName() {
      return StackTraceElementResolver.getName();
   }

   public StackTraceElementResolver create(final StackTraceElementResolverContext context, final TemplateResolverConfig config) {
      return new StackTraceElementResolver(config);
   }
}
