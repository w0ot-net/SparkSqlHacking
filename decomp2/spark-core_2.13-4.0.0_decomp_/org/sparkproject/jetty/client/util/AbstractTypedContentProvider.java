package org.sparkproject.jetty.client.util;

import org.sparkproject.jetty.client.api.ContentProvider;

/** @deprecated */
@Deprecated
public abstract class AbstractTypedContentProvider implements ContentProvider.Typed {
   private final String contentType;

   protected AbstractTypedContentProvider(String contentType) {
      this.contentType = contentType;
   }

   public String getContentType() {
      return this.contentType;
   }
}
