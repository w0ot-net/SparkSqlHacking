package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Variant;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.Status.Family;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.glassfish.jersey.internal.LocalizationMessages;

public class OutboundJaxrsResponse extends Response {
   private final OutboundMessageContext context;
   private final Response.StatusType status;
   private boolean closed = false;
   private boolean buffered = false;

   public static OutboundJaxrsResponse from(Response response, Configuration configuration) {
      if (response instanceof OutboundJaxrsResponse) {
         ((OutboundJaxrsResponse)response).context.setConfiguration(configuration);
         return (OutboundJaxrsResponse)response;
      } else {
         Response.StatusType status = response.getStatusInfo();
         OutboundMessageContext context = new OutboundMessageContext(configuration);
         context.getHeaders().putAll(response.getMetadata());
         context.setEntity(response.getEntity());
         return new OutboundJaxrsResponse(status, context);
      }
   }

   /** @deprecated */
   @Deprecated
   public static OutboundJaxrsResponse from(Response response) {
      return from(response, (Configuration)null);
   }

   public OutboundJaxrsResponse(Response.StatusType status, OutboundMessageContext context) {
      this.status = status;
      this.context = context;
   }

   public OutboundMessageContext getContext() {
      return this.context;
   }

   public int getStatus() {
      return this.status.getStatusCode();
   }

   public Response.StatusType getStatusInfo() {
      return this.status;
   }

   public Object getEntity() {
      if (this.closed) {
         throw new IllegalStateException(LocalizationMessages.RESPONSE_CLOSED());
      } else {
         return this.context.getEntity();
      }
   }

   public Object readEntity(Class type) throws ProcessingException {
      throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
   }

   public Object readEntity(GenericType entityType) throws ProcessingException {
      throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
   }

   public Object readEntity(Class type, Annotation[] annotations) throws ProcessingException {
      throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
   }

   public Object readEntity(GenericType entityType, Annotation[] annotations) throws ProcessingException {
      throw new IllegalStateException(LocalizationMessages.NOT_SUPPORTED_ON_OUTBOUND_MESSAGE());
   }

   public boolean hasEntity() {
      if (this.closed) {
         throw new IllegalStateException(LocalizationMessages.RESPONSE_CLOSED());
      } else {
         return this.context.hasEntity();
      }
   }

   public boolean bufferEntity() throws ProcessingException {
      if (this.closed) {
         throw new IllegalStateException(LocalizationMessages.RESPONSE_CLOSED());
      } else if (this.context.hasEntity() && InputStream.class.isAssignableFrom(this.context.getEntityClass())) {
         if (this.buffered) {
            return true;
         } else {
            InputStream in = (InputStream)InputStream.class.cast(this.context.getEntity());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            try {
               int len;
               try {
                  while((len = in.read(buffer)) != -1) {
                     out.write(buffer, 0, len);
                  }
               } catch (IOException ex) {
                  throw new ProcessingException(ex);
               }
            } finally {
               try {
                  in.close();
               } catch (IOException ex) {
                  throw new ProcessingException(ex);
               }
            }

            this.context.setEntity(new ByteArrayInputStream(out.toByteArray()));
            this.buffered = true;
            return true;
         }
      } else {
         return false;
      }
   }

   public void close() throws ProcessingException {
      this.closed = true;
      this.context.close();
      if (this.buffered) {
         this.context.setEntity((Object)null);
      } else if (this.context.hasEntity() && InputStream.class.isAssignableFrom(this.context.getEntityClass())) {
         try {
            ((InputStream)InputStream.class.cast(this.context.getEntity())).close();
         } catch (IOException ex) {
            throw new ProcessingException(ex);
         }
      }

   }

   public MultivaluedMap getStringHeaders() {
      return this.context.getStringHeaders();
   }

   public String getHeaderString(String name) {
      return this.context.getHeaderString(name);
   }

   public MediaType getMediaType() {
      return this.context.getMediaType();
   }

   public Locale getLanguage() {
      return this.context.getLanguage();
   }

   public int getLength() {
      return this.context.getLength();
   }

   public Map getCookies() {
      return this.context.getResponseCookies();
   }

   public EntityTag getEntityTag() {
      return this.context.getEntityTag();
   }

   public Date getDate() {
      return this.context.getDate();
   }

   public Date getLastModified() {
      return this.context.getLastModified();
   }

   public Set getAllowedMethods() {
      return this.context.getAllowedMethods();
   }

   public URI getLocation() {
      return this.context.getLocation();
   }

   public Set getLinks() {
      return this.context.getLinks();
   }

   public boolean hasLink(String relation) {
      return this.context.hasLink(relation);
   }

   public Link getLink(String relation) {
      return this.context.getLink(relation);
   }

   public Link.Builder getLinkBuilder(String relation) {
      return this.context.getLinkBuilder(relation);
   }

   public MultivaluedMap getMetadata() {
      return this.context.getHeaders();
   }

   public String toString() {
      return "OutboundJaxrsResponse{status=" + this.status.getStatusCode() + ", reason=" + this.status.getReasonPhrase() + ", hasEntity=" + this.context.hasEntity() + ", closed=" + this.closed + ", buffered=" + this.buffered + "}";
   }

   public static class Builder extends Response.ResponseBuilder {
      private Response.StatusType status;
      private final OutboundMessageContext context;
      private static final InheritableThreadLocal baseUriThreadLocal = new InheritableThreadLocal();

      public static void setBaseUri(URI baseUri) {
         baseUriThreadLocal.set(baseUri);
      }

      private static URI getBaseUri() {
         return (URI)baseUriThreadLocal.get();
      }

      public static void clearBaseUri() {
         baseUriThreadLocal.remove();
      }

      public Builder(OutboundMessageContext context) {
         this.context = context;
      }

      public Response build() {
         Response.StatusType st = this.status;
         if (st == null) {
            st = this.context.hasEntity() ? Status.OK : Status.NO_CONTENT;
         }

         return new OutboundJaxrsResponse(st, new OutboundMessageContext(this.context));
      }

      public Response.ResponseBuilder clone() {
         return (new Builder(new OutboundMessageContext(this.context))).status(this.status);
      }

      public Response.ResponseBuilder status(Response.StatusType status) {
         if (status == null) {
            throw new IllegalArgumentException("Response status must not be 'null'");
         } else {
            this.status = status;
            return this;
         }
      }

      public Response.ResponseBuilder status(final int status, final String reasonPhrase) {
         if (status >= 100 && status <= 599) {
            final Response.Status.Family family = Family.familyOf(status);
            this.status = new Response.StatusType() {
               public int getStatusCode() {
                  return status;
               }

               public Response.Status.Family getFamily() {
                  return family;
               }

               public String getReasonPhrase() {
                  return reasonPhrase;
               }
            };
            return this;
         } else {
            throw new IllegalArgumentException("Response status must not be less than '100' or greater than '599'");
         }
      }

      public Response.ResponseBuilder status(int code) {
         this.status = Statuses.from(code);
         return this;
      }

      public Response.ResponseBuilder entity(Object entity) {
         this.context.setEntity(entity);
         return this;
      }

      public Response.ResponseBuilder entity(Object entity, Annotation[] annotations) {
         this.context.setEntity(entity, annotations);
         return this;
      }

      public Response.ResponseBuilder type(MediaType type) {
         this.context.setMediaType(type);
         return this;
      }

      public Response.ResponseBuilder type(String type) {
         return this.type(type == null ? null : MediaType.valueOf(type));
      }

      public Response.ResponseBuilder variant(Variant variant) {
         if (variant == null) {
            this.type((MediaType)null);
            this.language((String)null);
            this.encoding((String)null);
            return this;
         } else {
            this.type(variant.getMediaType());
            this.language(variant.getLanguage());
            this.encoding(variant.getEncoding());
            return this;
         }
      }

      public Response.ResponseBuilder variants(List variants) {
         if (variants == null) {
            this.header("Vary", (Object)null);
            return this;
         } else if (variants.isEmpty()) {
            return this;
         } else {
            MediaType accept = ((Variant)variants.get(0)).getMediaType();
            boolean vAccept = false;
            Locale acceptLanguage = ((Variant)variants.get(0)).getLanguage();
            boolean vAcceptLanguage = false;
            String acceptEncoding = ((Variant)variants.get(0)).getEncoding();
            boolean vAcceptEncoding = false;

            for(Variant v : variants) {
               vAccept |= !vAccept && this.vary(v.getMediaType(), accept);
               vAcceptLanguage |= !vAcceptLanguage && this.vary(v.getLanguage(), acceptLanguage);
               vAcceptEncoding |= !vAcceptEncoding && this.vary(v.getEncoding(), acceptEncoding);
            }

            StringBuilder vary = new StringBuilder();
            this.append(vary, vAccept, "Accept");
            this.append(vary, vAcceptLanguage, "Accept-Language");
            this.append(vary, vAcceptEncoding, "Accept-Encoding");
            if (vary.length() > 0) {
               this.header("Vary", vary.toString());
            }

            return this;
         }
      }

      private boolean vary(MediaType v, MediaType vary) {
         return v != null && !v.equals(vary);
      }

      private boolean vary(Locale v, Locale vary) {
         return v != null && !v.equals(vary);
      }

      private boolean vary(String v, String vary) {
         return v != null && !v.equalsIgnoreCase(vary);
      }

      private void append(StringBuilder sb, boolean v, String s) {
         if (v) {
            if (sb.length() > 0) {
               sb.append(',');
            }

            sb.append(s);
         }

      }

      public Response.ResponseBuilder language(String language) {
         this.headerSingle("Content-Language", language);
         return this;
      }

      public Response.ResponseBuilder language(Locale language) {
         this.headerSingle("Content-Language", language);
         return this;
      }

      public Response.ResponseBuilder location(URI location) {
         URI locationUri = location;
         if (location != null && !location.isAbsolute()) {
            URI baseUri = getBaseUri();
            if (baseUri != null) {
               locationUri = baseUri.resolve(location);
            }
         }

         this.headerSingle("Location", locationUri);
         return this;
      }

      public Response.ResponseBuilder contentLocation(URI location) {
         this.headerSingle("Content-Location", location);
         return this;
      }

      public Response.ResponseBuilder encoding(String encoding) {
         this.headerSingle("Content-Encoding", encoding);
         return this;
      }

      public Response.ResponseBuilder tag(EntityTag tag) {
         this.headerSingle("ETag", tag);
         return this;
      }

      public Response.ResponseBuilder tag(String tag) {
         return this.tag(tag == null ? null : new EntityTag(tag));
      }

      public Response.ResponseBuilder lastModified(Date lastModified) {
         this.headerSingle("Last-Modified", lastModified);
         return this;
      }

      public Response.ResponseBuilder cacheControl(CacheControl cacheControl) {
         this.headerSingle("Cache-Control", cacheControl);
         return this;
      }

      public Response.ResponseBuilder expires(Date expires) {
         this.headerSingle("Expires", expires);
         return this;
      }

      public Response.ResponseBuilder cookie(NewCookie... cookies) {
         if (cookies != null) {
            for(NewCookie cookie : cookies) {
               this.header("Set-Cookie", cookie);
            }
         } else {
            this.header("Set-Cookie", (Object)null);
         }

         return this;
      }

      public Response.ResponseBuilder header(String name, Object value) {
         return this.header(name, value, false);
      }

      private Response.ResponseBuilder headerSingle(String name, Object value) {
         return this.header(name, value, true);
      }

      private Response.ResponseBuilder header(String name, Object value, boolean single) {
         if (value != null) {
            if (single) {
               this.context.getHeaders().putSingle(name, value);
            } else {
               this.context.getHeaders().add(name, value);
            }
         } else {
            this.context.getHeaders().remove(name);
         }

         return this;
      }

      public Response.ResponseBuilder variants(Variant... variants) {
         return this.variants(Arrays.asList(variants));
      }

      public Response.ResponseBuilder links(Link... links) {
         if (links != null) {
            for(Link link : links) {
               this.header("Link", link);
            }
         } else {
            this.header("Link", (Object)null);
         }

         return this;
      }

      public Response.ResponseBuilder link(URI uri, String rel) {
         this.header("Link", Link.fromUri(uri).rel(rel).build(new Object[0]));
         return this;
      }

      public Response.ResponseBuilder link(String uri, String rel) {
         this.header("Link", Link.fromUri(uri).rel(rel).build(new Object[0]));
         return this;
      }

      public Response.ResponseBuilder allow(String... methods) {
         return methods != null && (methods.length != 1 || methods[0] != null) ? this.allow((Set)(new HashSet(Arrays.asList(methods)))) : this.allow((Set)null);
      }

      public Response.ResponseBuilder allow(Set methods) {
         if (methods == null) {
            return this.header("Allow", (Object)null, true);
         } else {
            StringBuilder allow = new StringBuilder();

            for(String m : methods) {
               this.append(allow, true, m);
            }

            return this.header("Allow", allow, true);
         }
      }

      public Response.ResponseBuilder replaceAll(MultivaluedMap headers) {
         this.context.replaceHeaders(headers);
         return this;
      }
   }
}
