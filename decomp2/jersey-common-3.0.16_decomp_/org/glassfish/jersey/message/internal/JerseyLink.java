package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.uri.UriTemplate;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

public final class JerseyLink extends Link {
   private final URI uri;
   private final Map params;

   private JerseyLink(URI uri, Map params) {
      this.uri = uri;
      this.params = params;
   }

   public URI getUri() {
      return this.uri;
   }

   public UriBuilder getUriBuilder() {
      return (new JerseyUriBuilder()).uri(this.uri);
   }

   public String getRel() {
      return (String)this.params.get("rel");
   }

   public List getRels() {
      String rels = (String)this.params.get("rel");
      return rels == null ? Collections.emptyList() : Arrays.asList(rels.split(" +"));
   }

   public String getTitle() {
      return (String)this.params.get("title");
   }

   public String getType() {
      return (String)this.params.get("type");
   }

   public Map getParams() {
      return this.params;
   }

   public String toString() {
      return LinkProvider.stringfy(this);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Link)) {
         return false;
      } else {
         Link otherLink = (Link)other;
         return this.uri.equals(otherLink.getUri()) && this.params.equals(otherLink.getParams());
      }
   }

   public int hashCode() {
      int hash = 3;
      hash = 89 * hash + (this.uri != null ? this.uri.hashCode() : 0);
      hash = 89 * hash + (this.params != null ? this.params.hashCode() : 0);
      return hash;
   }

   public static class Builder implements Link.Builder {
      private UriBuilder uriBuilder = new JerseyUriBuilder();
      private URI baseUri = null;
      private Map params = new HashMap();

      public Builder link(Link link) {
         this.uriBuilder.uri(link.getUri());
         this.params.clear();
         this.params.putAll(link.getParams());
         return this;
      }

      public Builder link(String link) {
         LinkProvider.initBuilder(this, link);
         return this;
      }

      public Builder uri(URI uri) {
         this.uriBuilder = UriBuilder.fromUri(uri);
         return this;
      }

      public Builder uri(String uri) {
         this.uriBuilder = UriBuilder.fromUri(uri);
         return this;
      }

      public Builder uriBuilder(UriBuilder uriBuilder) {
         this.uriBuilder = UriBuilder.fromUri(uriBuilder.toTemplate());
         return this;
      }

      public Link.Builder baseUri(URI uri) {
         this.baseUri = uri;
         return this;
      }

      public Link.Builder baseUri(String uri) {
         this.baseUri = URI.create(uri);
         return this;
      }

      public Builder rel(String rel) {
         String rels = (String)this.params.get("rel");
         this.param("rel", rels == null ? rel : rels + " " + rel);
         return this;
      }

      public Builder title(String title) {
         this.param("title", title);
         return this;
      }

      public Builder type(String type) {
         this.param("type", type);
         return this;
      }

      public Builder param(String name, String value) {
         if (name != null && value != null) {
            this.params.put(name, value);
            return this;
         } else {
            throw new IllegalArgumentException("Link parameter name or value is null");
         }
      }

      public JerseyLink build(Object... values) {
         URI linkUri = this.resolveLinkUri(values);
         return new JerseyLink(linkUri, Collections.unmodifiableMap(new HashMap(this.params)));
      }

      public Link buildRelativized(URI uri, Object... values) {
         URI linkUri = UriTemplate.relativize(uri, this.resolveLinkUri(values));
         return new JerseyLink(linkUri, Collections.unmodifiableMap(new HashMap(this.params)));
      }

      private URI resolveLinkUri(Object[] values) {
         URI linkUri = this.uriBuilder.build(values);
         return this.baseUri != null && !linkUri.isAbsolute() ? UriTemplate.resolve(this.baseUri, linkUri) : UriTemplate.normalize(linkUri);
      }
   }
}
