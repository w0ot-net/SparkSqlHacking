package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import java.util.function.Function;
import org.glassfish.jersey.internal.inject.ExtractorException;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.server.model.Parameter;

@Singleton
final class HeaderParamValueParamProvider extends AbstractValueParamProvider {
   public HeaderParamValueParamProvider(Provider mpep) {
      super(mpep, Source.HEADER);
   }

   public Function createValueProvider(Parameter parameter) {
      String parameterName = parameter.getSourceName();
      if (parameterName != null && parameterName.length() != 0) {
         MultivaluedParameterExtractor e = this.get(parameter);
         return e == null ? null : new HeaderParamValueProvider(e);
      } else {
         return null;
      }
   }

   private static final class HeaderParamValueProvider implements Function {
      private final MultivaluedParameterExtractor extractor;

      HeaderParamValueProvider(MultivaluedParameterExtractor extractor) {
         this.extractor = extractor;
      }

      public Object apply(ContainerRequest containerRequest) {
         try {
            return this.extractor.extract(containerRequest.getHeaders());
         } catch (ExtractorException e) {
            throw new ParamException.HeaderParamException(e.getCause(), this.extractor.getName(), this.extractor.getDefaultValueString());
         }
      }
   }
}
