package org.jvnet.hk2.internal;

import java.util.List;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceHandle;

public interface Creator {
   List getInjectees();

   Object create(ServiceHandle var1, SystemDescriptor var2) throws MultiException;

   void dispose(Object var1) throws MultiException;
}
