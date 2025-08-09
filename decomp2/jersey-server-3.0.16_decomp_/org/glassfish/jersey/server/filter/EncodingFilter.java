package org.glassfish.jersey.server.filter;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.message.internal.HttpHeaderReader;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.spi.ContentEncoder;

@Priority(3000)
public final class EncodingFilter implements ContainerResponseFilter {
   private static final String IDENTITY_ENCODING = "identity";
   @Inject
   private InjectionManager injectionManager;
   private volatile SortedSet supportedEncodings = null;

   @SafeVarargs
   public static void enableFor(ResourceConfig rc, Class... encoders) {
      rc.registerClasses(encoders).registerClasses(EncodingFilter.class);
   }

   public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
      if (response.hasEntity()) {
         List<String> varyHeader = (List)response.getStringHeaders().get("Vary");
         if (varyHeader == null || !varyHeader.contains("Accept-Encoding")) {
            response.getHeaders().add("Vary", "Accept-Encoding");
         }

         if (response.getHeaders().getFirst("Content-Encoding") == null) {
            List<String> acceptEncoding = (List)request.getHeaders().get("Accept-Encoding");
            if (acceptEncoding != null && !acceptEncoding.isEmpty()) {
               List<ContentEncoding> encodings = new ArrayList();

               for(String input : acceptEncoding) {
                  if (!input.isEmpty()) {
                     String[] tokens = input.split(",");

                     for(String token : tokens) {
                        try {
                           ContentEncoding encoding = EncodingFilter.ContentEncoding.fromString(token);
                           encodings.add(encoding);
                        } catch (ParseException e) {
                           Logger.getLogger(EncodingFilter.class.getName()).log(Level.WARNING, e.getLocalizedMessage(), e);
                        }
                     }
                  }
               }

               Collections.sort(encodings);
               encodings.add(new ContentEncoding("identity", -1));
               SortedSet<String> acceptedEncodings = new TreeSet(this.getSupportedEncodings());
               boolean anyRemaining = false;
               String contentEncoding = null;

               for(ContentEncoding encoding : encodings) {
                  if (encoding.q == 0) {
                     if ("*".equals(encoding.name)) {
                        break;
                     }

                     acceptedEncodings.remove(encoding.name);
                  } else if ("*".equals(encoding.name)) {
                     anyRemaining = true;
                  } else if (acceptedEncodings.contains(encoding.name)) {
                     contentEncoding = encoding.name;
                     break;
                  }
               }

               if (contentEncoding == null) {
                  if (!anyRemaining || acceptedEncodings.isEmpty()) {
                     throw new NotAcceptableException();
                  }

                  contentEncoding = (String)acceptedEncodings.first();
               }

               if (!"identity".equals(contentEncoding)) {
                  response.getHeaders().putSingle("Content-Encoding", contentEncoding);
               }

            }
         }
      }
   }

   SortedSet getSupportedEncodings() {
      if (this.supportedEncodings == null) {
         SortedSet<String> se = new TreeSet();

         for(ContentEncoder encoder : this.injectionManager.getAllInstances(ContentEncoder.class)) {
            se.addAll(encoder.getSupportedEncodings());
         }

         se.add("identity");
         this.supportedEncodings = se;
      }

      return this.supportedEncodings;
   }

   private static class ContentEncoding implements Comparable {
      public final String name;
      public final int q;

      public ContentEncoding(String encoding, int q) {
         this.name = encoding;
         this.q = q;
      }

      public static ContentEncoding fromString(String input) throws ParseException {
         HttpHeaderReader reader = HttpHeaderReader.newInstance(input);
         reader.hasNext();
         return new ContentEncoding(reader.nextToken().toString(), HttpHeaderReader.readQualityFactorParameter(reader));
      }

      public int hashCode() {
         return 41 * this.name.hashCode() + this.q;
      }

      public boolean equals(Object obj) {
         return obj == this || obj != null && obj instanceof ContentEncoding && this.name.equals(((ContentEncoding)obj).name) && this.q == ((ContentEncoding)obj).q;
      }

      public int compareTo(ContentEncoding o) {
         return Integer.compare(o.q, this.q);
      }
   }
}
