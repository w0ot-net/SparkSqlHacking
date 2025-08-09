package org.glassfish.jaxb.runtime;

import jakarta.xml.bind.Marshaller;

public interface CycleRecoverable {
   Object onCycleDetected(Context var1);

   public interface Context {
      Marshaller getMarshaller();
   }
}
