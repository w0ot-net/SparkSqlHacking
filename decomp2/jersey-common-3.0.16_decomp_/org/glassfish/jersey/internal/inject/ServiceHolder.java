package org.glassfish.jersey.internal.inject;

import java.util.Set;

public interface ServiceHolder {
   Object getInstance();

   Class getImplementationClass();

   Set getContractTypes();

   int getRank();
}
