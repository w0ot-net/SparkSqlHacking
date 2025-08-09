package org.apache.logging.log4j.core.config.builder.api;

public interface LoggableComponentBuilder extends FilterableComponentBuilder {
   ComponentBuilder add(AppenderRefComponentBuilder assembler);
}
