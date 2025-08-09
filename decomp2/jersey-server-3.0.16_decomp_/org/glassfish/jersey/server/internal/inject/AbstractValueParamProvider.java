package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Provider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import org.glassfish.jersey.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;

public abstract class AbstractValueParamProvider implements ValueParamProvider {
   private final Provider mpep;
   private final Set compatibleSources;

   protected AbstractValueParamProvider(Provider mpep, Parameter.Source... compatibleSources) {
      this.mpep = mpep;
      this.compatibleSources = new HashSet(Arrays.asList(compatibleSources));
   }

   protected final MultivaluedParameterExtractor get(org.glassfish.jersey.server.model.Parameter parameter) {
      return ((MultivaluedParameterExtractorProvider)this.mpep.get()).get(parameter);
   }

   protected abstract Function createValueProvider(org.glassfish.jersey.server.model.Parameter var1);

   public final Function getValueProvider(org.glassfish.jersey.server.model.Parameter parameter) {
      return !this.compatibleSources.contains(parameter.getSource()) ? null : this.createValueProvider(parameter);
   }

   public ValueParamProvider.PriorityType getPriority() {
      return ValueParamProvider.Priority.NORMAL;
   }
}
