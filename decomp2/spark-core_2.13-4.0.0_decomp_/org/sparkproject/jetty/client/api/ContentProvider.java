package org.sparkproject.jetty.client.api;

import org.sparkproject.jetty.client.internal.RequestContentAdapter;

/** @deprecated */
@Deprecated
public interface ContentProvider extends Iterable {
   static Request.Content toRequestContent(ContentProvider provider) {
      return new RequestContentAdapter(provider);
   }

   long getLength();

   default boolean isReproducible() {
      return false;
   }

   /** @deprecated */
   @Deprecated
   public interface Typed extends ContentProvider {
      String getContentType();
   }
}
