package org.glassfish.jersey.server.spi.internal;

import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import java.util.function.Function;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.spi.Contract;

@Contract
@ConstrainedTo(RuntimeType.SERVER)
public interface ValueParamProvider {
   Function getValueProvider(Parameter var1);

   PriorityType getPriority();

   public static enum Priority implements PriorityType {
      LOW(100),
      NORMAL(200),
      HIGH(300);

      private final int weight;

      private Priority(int weight) {
         this.weight = weight;
      }

      public int getWeight() {
         return this.weight;
      }
   }

   public interface PriorityType {
      int getWeight();
   }
}
