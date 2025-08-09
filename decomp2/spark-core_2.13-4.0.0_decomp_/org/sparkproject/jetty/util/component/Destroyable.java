package org.sparkproject.jetty.util.component;

import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;

@ManagedObject
public interface Destroyable {
   @ManagedOperation(
      value = "Destroys this component",
      impact = "ACTION"
   )
   void destroy();
}
