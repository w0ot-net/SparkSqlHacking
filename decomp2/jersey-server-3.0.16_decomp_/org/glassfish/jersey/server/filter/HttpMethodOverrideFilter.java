package org.glassfish.jersey.server.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.LocalizationMessages;

@PreMatching
@Priority(3050)
public final class HttpMethodOverrideFilter implements ContainerRequestFilter {
   final int config;

   public static void enableFor(ResourceConfig rc, Source... sources) {
      rc.registerClasses(HttpMethodOverrideFilter.class);
      rc.property("jersey.config.server.httpMethodOverride", sources);
   }

   public HttpMethodOverrideFilter(@Context Configuration rc) {
      this(parseConfig(rc.getProperty("jersey.config.server.httpMethodOverride")));
   }

   public HttpMethodOverrideFilter(Source... sources) {
      int c = 0;

      for(Source cf : sources) {
         if (cf != null) {
            c |= cf.getFlag();
         }
      }

      if (c == 0) {
         c = 3;
      }

      this.config = c;
   }

   private static Source[] parseConfig(Object config) {
      if (config == null) {
         return new Source[0];
      } else if (config instanceof Source[]) {
         return (Source[])config;
      } else if (config instanceof Source) {
         return new Source[]{(Source)config};
      } else {
         String[] stringValues;
         if (config instanceof String) {
            stringValues = Tokenizer.tokenize((String)config, " ,;\n");
         } else {
            if (!(config instanceof String[])) {
               return new Source[0];
            }

            stringValues = Tokenizer.tokenize((String[])config, " ,;\n");
         }

         Source[] result = new Source[stringValues.length];

         for(int i = 0; i < stringValues.length; ++i) {
            try {
               result[i] = HttpMethodOverrideFilter.Source.valueOf(stringValues[i]);
            } catch (IllegalArgumentException var5) {
               Logger.getLogger(HttpMethodOverrideFilter.class.getName()).log(Level.WARNING, LocalizationMessages.INVALID_CONFIG_PROPERTY_VALUE("jersey.config.server.httpMethodOverride", stringValues[i]));
            }
         }

         return result;
      }
   }

   private String getParamValue(Source source, MultivaluedMap paramsMap, String paramName) {
      String value = source.isPresentIn(this.config) ? (String)paramsMap.getFirst(paramName) : null;
      if (value == null) {
         return null;
      } else {
         value = value.trim();
         return value.length() == 0 ? null : value.toUpperCase(Locale.ROOT);
      }
   }

   public void filter(ContainerRequestContext request) {
      if (request.getMethod().equalsIgnoreCase("POST")) {
         String header = this.getParamValue(HttpMethodOverrideFilter.Source.HEADER, request.getHeaders(), "X-HTTP-Method-Override");
         String query = this.getParamValue(HttpMethodOverrideFilter.Source.QUERY, request.getUriInfo().getQueryParameters(), "_method");
         String override;
         if (header == null) {
            override = query;
         } else {
            override = header;
            if (query != null && !query.equals(header)) {
               throw new BadRequestException();
            }
         }

         if (override != null) {
            request.setMethod(override);
            if (override.equals("GET") && request.getMediaType() != null && MediaType.APPLICATION_FORM_URLENCODED_TYPE.getType().equals(request.getMediaType().getType())) {
               UriBuilder ub = request.getUriInfo().getRequestUriBuilder();
               Form f = (Form)((ContainerRequest)request).readEntity(Form.class);

               for(Map.Entry param : f.asMap().entrySet()) {
                  ub.queryParam((String)param.getKey(), ((List)param.getValue()).toArray());
               }

               request.setRequestUri(request.getUriInfo().getBaseUri(), ub.build(new Object[0]));
            }
         }

      }
   }

   public static enum Source {
      HEADER(1),
      QUERY(2);

      private final int flag;

      private Source(int flag) {
         this.flag = flag;
      }

      public int getFlag() {
         return this.flag;
      }

      public boolean isPresentIn(int config) {
         return (config & this.flag) == this.flag;
      }
   }
}
