package org.glassfish.hk2.api;

import java.io.Closeable;
import java.util.List;

public interface ServiceHandle extends Closeable {
   Object getService();

   ActiveDescriptor getActiveDescriptor();

   boolean isActive();

   /** @deprecated */
   @Deprecated
   default void destroy() {
      this.close();
   }

   default void close() {
      this.destroy();
   }

   void setServiceData(Object var1);

   Object getServiceData();

   List getSubHandles();
}
