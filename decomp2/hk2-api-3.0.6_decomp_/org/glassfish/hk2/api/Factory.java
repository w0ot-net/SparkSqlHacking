package org.glassfish.hk2.api;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface Factory {
   Object provide();

   void dispose(Object var1);
}
