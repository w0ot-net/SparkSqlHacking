package org.glassfish.hk2.utilities;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class EnableLookupExceptionsModule extends AbstractBinder {
   protected void configure() {
      this.addActiveDescriptor(RethrowErrorService.class);
   }
}
