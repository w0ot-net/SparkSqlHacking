package org.glassfish.jersey.server.spi.internal;

import java.util.function.Function;
import org.glassfish.jersey.model.Parameter;
import org.glassfish.jersey.server.ContainerRequest;

public final class ParamValueFactoryWithSource implements Function {
   private final Function parameterFunction;
   private final Parameter.Source parameterSource;

   public ParamValueFactoryWithSource(Function paramFunction, Parameter.Source parameterSource) {
      this.parameterFunction = paramFunction;
      this.parameterSource = parameterSource;
   }

   public Object apply(ContainerRequest request) {
      return this.parameterFunction.apply(request);
   }

   public Parameter.Source getSource() {
      return this.parameterSource;
   }
}
