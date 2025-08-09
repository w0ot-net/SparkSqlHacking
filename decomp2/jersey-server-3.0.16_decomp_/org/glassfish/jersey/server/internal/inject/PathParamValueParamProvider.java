package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.PathSegment;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;
import org.glassfish.jersey.internal.inject.ExtractorException;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.server.model.Parameter;

@Singleton
final class PathParamValueParamProvider extends AbstractValueParamProvider {
   public PathParamValueParamProvider(Provider mpep) {
      super(mpep, Source.PATH);
   }

   public Function createValueProvider(Parameter parameter) {
      String parameterName = parameter.getSourceName();
      if (parameterName != null && parameterName.length() != 0) {
         Class<?> rawParameterType = parameter.getRawType();
         if (rawParameterType == PathSegment.class) {
            return new PathParamPathSegmentValueSupplier(parameterName, !parameter.isEncoded());
         } else {
            if (rawParameterType == List.class && parameter.getType() instanceof ParameterizedType) {
               ParameterizedType pt = (ParameterizedType)parameter.getType();
               Type[] targs = pt.getActualTypeArguments();
               if (targs.length == 1 && targs[0] == PathSegment.class) {
                  return new PathParamListPathSegmentValueSupplier(parameterName, !parameter.isEncoded());
               }
            }

            MultivaluedParameterExtractor<?> e = this.get(parameter);
            return e == null ? null : new PathParamValueProvider(e, !parameter.isEncoded());
         }
      } else {
         return null;
      }
   }

   private static final class PathParamValueProvider implements Function {
      private final MultivaluedParameterExtractor extractor;
      private final boolean decode;

      PathParamValueProvider(MultivaluedParameterExtractor extractor, boolean decode) {
         this.extractor = extractor;
         this.decode = decode;
      }

      public Object apply(ContainerRequest request) {
         try {
            return this.extractor.extract(request.getUriInfo().getPathParameters(this.decode));
         } catch (ExtractorException e) {
            throw new ParamException.PathParamException(e.getCause(), this.extractor.getName(), this.extractor.getDefaultValueString());
         }
      }
   }

   private static final class PathParamPathSegmentValueSupplier implements Function {
      private final String name;
      private final boolean decode;

      PathParamPathSegmentValueSupplier(String name, boolean decode) {
         this.name = name;
         this.decode = decode;
      }

      public PathSegment apply(ContainerRequest request) {
         List<PathSegment> ps = request.getUriInfo().getPathSegments(this.name, this.decode);
         return ps.isEmpty() ? null : (PathSegment)ps.get(ps.size() - 1);
      }
   }

   private static final class PathParamListPathSegmentValueSupplier implements Function {
      private final String name;
      private final boolean decode;

      PathParamListPathSegmentValueSupplier(String name, boolean decode) {
         this.name = name;
         this.decode = decode;
      }

      public List apply(ContainerRequest request) {
         return request.getUriInfo().getPathSegments(this.name, this.decode);
      }
   }
}
