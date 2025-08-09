package io.jsonwebtoken;

import io.jsonwebtoken.lang.Assert;

public abstract class LocatorAdapter implements Locator {
   public final Object locate(Header header) {
      Assert.notNull(header, "Header cannot be null.");
      if (header instanceof ProtectedHeader) {
         ProtectedHeader protectedHeader = (ProtectedHeader)header;
         return this.locate(protectedHeader);
      } else {
         return this.doLocate(header);
      }
   }

   protected Object locate(ProtectedHeader header) {
      if (header instanceof JwsHeader) {
         return this.locate((JwsHeader)header);
      } else {
         Assert.isInstanceOf(JweHeader.class, header, "Unrecognized ProtectedHeader type.");
         return this.locate((JweHeader)header);
      }
   }

   protected Object locate(JweHeader header) {
      return null;
   }

   protected Object locate(JwsHeader header) {
      return null;
   }

   protected Object doLocate(Header header) {
      return null;
   }
}
