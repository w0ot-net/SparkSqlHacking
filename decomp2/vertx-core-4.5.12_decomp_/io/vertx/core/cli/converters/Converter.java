package io.vertx.core.cli.converters;

@FunctionalInterface
public interface Converter {
   Object fromString(String var1);
}
