package jakarta.ws.rs.core;

import jakarta.ws.rs.ext.RuntimeDelegate;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;

public abstract class Link {
   public static final String TITLE = "title";
   public static final String REL = "rel";
   public static final String TYPE = "type";

   public abstract URI getUri();

   public abstract UriBuilder getUriBuilder();

   public abstract String getRel();

   public abstract List getRels();

   public abstract String getTitle();

   public abstract String getType();

   public abstract Map getParams();

   public abstract String toString();

   public static Link valueOf(String value) {
      Builder b = RuntimeDelegate.getInstance().createLinkBuilder();
      b.link(value);
      return b.build();
   }

   public static Builder fromUri(URI uri) {
      Builder b = RuntimeDelegate.getInstance().createLinkBuilder();
      b.uri(uri);
      return b;
   }

   public static Builder fromUri(String uri) {
      Builder b = RuntimeDelegate.getInstance().createLinkBuilder();
      b.uri(uri);
      return b;
   }

   public static Builder fromUriBuilder(UriBuilder uriBuilder) {
      Builder b = RuntimeDelegate.getInstance().createLinkBuilder();
      b.uriBuilder(uriBuilder);
      return b;
   }

   public static Builder fromLink(Link link) {
      Builder b = RuntimeDelegate.getInstance().createLinkBuilder();
      b.link(link);
      return b;
   }

   public static Builder fromPath(String path) {
      return fromUriBuilder(UriBuilder.fromPath(path));
   }

   public static Builder fromResource(Class resource) {
      return fromUriBuilder(UriBuilder.fromResource(resource));
   }

   public static Builder fromMethod(Class resource, String method) {
      return fromUriBuilder(UriBuilder.fromMethod(resource, method));
   }

   public static class JaxbLink {
      private URI uri;
      private Map params;

      public JaxbLink() {
      }

      public JaxbLink(URI uri) {
         this.uri = uri;
      }

      public JaxbLink(URI uri, Map params) {
         this.uri = uri;
         this.params = params;
      }

      @XmlAttribute(
         name = "href"
      )
      public URI getUri() {
         return this.uri;
      }

      @XmlAnyAttribute
      public Map getParams() {
         if (this.params == null) {
            this.params = new HashMap();
         }

         return this.params;
      }

      void setUri(URI uri) {
         this.uri = uri;
      }

      void setParams(Map params) {
         this.params = params;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof JaxbLink)) {
            return false;
         } else {
            JaxbLink jaxbLink = (JaxbLink)o;
            if (this.uri != null) {
               if (!this.uri.equals(jaxbLink.uri)) {
                  return false;
               }
            } else if (jaxbLink.uri != null) {
               return false;
            }

            if (this.params == jaxbLink.params) {
               return true;
            } else if (this.params == null) {
               return jaxbLink.params.isEmpty();
            } else if (jaxbLink.params == null) {
               return this.params.isEmpty();
            } else {
               return this.params.equals(jaxbLink.params);
            }
         }
      }

      public int hashCode() {
         int result = this.uri != null ? this.uri.hashCode() : 0;
         result = 31 * result + (this.params != null && !this.params.isEmpty() ? this.params.hashCode() : 0);
         return result;
      }
   }

   public static class JaxbAdapter extends XmlAdapter {
      public Link unmarshal(JaxbLink v) {
         Builder lb = Link.fromUri(v.getUri());

         for(Map.Entry e : v.getParams().entrySet()) {
            lb.param(((QName)e.getKey()).getLocalPart(), e.getValue().toString());
         }

         return lb.build();
      }

      public JaxbLink marshal(Link v) {
         JaxbLink jl = new JaxbLink(v.getUri());

         for(Map.Entry e : v.getParams().entrySet()) {
            String name = (String)e.getKey();
            jl.getParams().put(new QName("", name), e.getValue());
         }

         return jl;
      }
   }

   public interface Builder {
      Builder link(Link var1);

      Builder link(String var1);

      Builder uri(URI var1);

      Builder uri(String var1);

      Builder baseUri(URI var1);

      Builder baseUri(String var1);

      Builder uriBuilder(UriBuilder var1);

      Builder rel(String var1);

      Builder title(String var1);

      Builder type(String var1);

      Builder param(String var1, String var2);

      Link build(Object... var1);

      Link buildRelativized(URI var1, Object... var2);
   }
}
