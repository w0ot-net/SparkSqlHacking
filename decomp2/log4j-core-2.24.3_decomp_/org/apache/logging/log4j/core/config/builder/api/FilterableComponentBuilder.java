package org.apache.logging.log4j.core.config.builder.api;

public interface FilterableComponentBuilder extends ComponentBuilder {
   ComponentBuilder add(FilterComponentBuilder assembler);
}
