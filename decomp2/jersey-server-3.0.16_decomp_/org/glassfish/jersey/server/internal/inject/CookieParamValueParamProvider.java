package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.Map;
import java.util.function.Function;
import org.glassfish.jersey.internal.inject.ExtractorException;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.server.model.Parameter;

@Singleton
final class CookieParamValueParamProvider extends AbstractValueParamProvider {
   public CookieParamValueParamProvider(Provider mpep) {
      super(mpep, Source.COOKIE);
   }

   public Function createValueProvider(Parameter parameter) {
      String parameterName = parameter.getSourceName();
      if (parameterName != null && parameterName.length() != 0) {
         if (parameter.getRawType() == Cookie.class) {
            return new CookieTypeParamValueProvider(parameterName);
         } else {
            MultivaluedParameterExtractor e = this.get(parameter);
            return e == null ? null : new CookieParamValueProvider(e);
         }
      } else {
         return null;
      }
   }

   private static final class CookieParamValueProvider implements Function {
      private final MultivaluedParameterExtractor extractor;

      CookieParamValueProvider(MultivaluedParameterExtractor extractor) {
         this.extractor = extractor;
      }

      public Object apply(ContainerRequest containerRequest) {
         MultivaluedMap<String, String> cookies = new MultivaluedStringMap();

         for(Map.Entry e : containerRequest.getCookies().entrySet()) {
            cookies.putSingle(e.getKey(), ((Cookie)e.getValue()).getValue());
         }

         try {
            return this.extractor.extract(cookies);
         } catch (ExtractorException ex) {
            throw new ParamException.CookieParamException(ex.getCause(), this.extractor.getName(), this.extractor.getDefaultValueString());
         }
      }
   }

   private static final class CookieTypeParamValueProvider implements Function {
      private final String name;

      CookieTypeParamValueProvider(String name) {
         this.name = name;
      }

      public Cookie apply(ContainerRequest containerRequest) {
         return (Cookie)containerRequest.getCookies().get(this.name);
      }
   }
}
