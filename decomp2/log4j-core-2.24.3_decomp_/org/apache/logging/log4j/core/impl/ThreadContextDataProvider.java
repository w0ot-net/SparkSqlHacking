package org.apache.logging.log4j.core.impl;

import aQute.bnd.annotation.spi.ServiceProvider;
import java.util.Map;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.util.ContextDataProvider;
import org.apache.logging.log4j.util.StringMap;

@ServiceProvider(
   value = ContextDataProvider.class,
   resolution = "optional"
)
public class ThreadContextDataProvider implements ContextDataProvider {
   public Map supplyContextData() {
      return ThreadContext.getImmutableContext();
   }

   public StringMap supplyStringMap() {
      return ThreadContext.getThreadContextMap().getReadOnlyContextData();
   }
}
