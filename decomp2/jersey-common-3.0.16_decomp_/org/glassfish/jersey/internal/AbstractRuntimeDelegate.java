package org.glassfish.jersey.internal;

import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.Variant;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.net.URI;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.glassfish.jersey.message.internal.JerseyLink;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.glassfish.jersey.message.internal.VariantListBuilder;
import org.glassfish.jersey.spi.HeaderDelegateProvider;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

public abstract class AbstractRuntimeDelegate extends RuntimeDelegate {
   private final Set hps;
   private final Map map;

   protected AbstractRuntimeDelegate(Set hps) {
      this.hps = hps;
      this.map = new WeakHashMap();
      this.map.put(EntityTag.class, this._createHeaderDelegate(EntityTag.class));
      this.map.put(MediaType.class, this._createHeaderDelegate(MediaType.class));
      this.map.put(CacheControl.class, this._createHeaderDelegate(CacheControl.class));
      this.map.put(NewCookie.class, this._createHeaderDelegate(NewCookie.class));
      this.map.put(Cookie.class, this._createHeaderDelegate(Cookie.class));
      this.map.put(URI.class, this._createHeaderDelegate(URI.class));
      this.map.put(Date.class, this._createHeaderDelegate(Date.class));
      this.map.put(String.class, this._createHeaderDelegate(String.class));
   }

   public Variant.VariantListBuilder createVariantListBuilder() {
      return new VariantListBuilder();
   }

   public Response.ResponseBuilder createResponseBuilder() {
      return new OutboundJaxrsResponse.Builder(new OutboundMessageContext((Configuration)null));
   }

   public UriBuilder createUriBuilder() {
      return new JerseyUriBuilder();
   }

   public Link.Builder createLinkBuilder() {
      return new JerseyLink.Builder();
   }

   public RuntimeDelegate.HeaderDelegate createHeaderDelegate(Class type) {
      if (type == null) {
         throw new IllegalArgumentException("type parameter cannot be null");
      } else {
         RuntimeDelegate.HeaderDelegate<T> delegate = (RuntimeDelegate.HeaderDelegate)this.map.get(type);
         return delegate != null ? delegate : this._createHeaderDelegate(type);
      }
   }

   private RuntimeDelegate.HeaderDelegate _createHeaderDelegate(Class type) {
      for(HeaderDelegateProvider hp : this.hps) {
         if (hp.supports(type)) {
            return hp;
         }
      }

      return null;
   }
}
