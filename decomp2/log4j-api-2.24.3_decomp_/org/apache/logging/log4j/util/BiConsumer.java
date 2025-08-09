package org.apache.logging.log4j.util;

@FunctionalInterface
public interface BiConsumer extends java.util.function.BiConsumer {
   void accept(Object k, Object v);
}
