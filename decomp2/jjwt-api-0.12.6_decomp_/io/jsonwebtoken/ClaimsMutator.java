package io.jsonwebtoken;

import io.jsonwebtoken.lang.NestedCollection;
import java.util.Date;

public interface ClaimsMutator {
   /** @deprecated */
   @Deprecated
   ClaimsMutator setIssuer(String var1);

   ClaimsMutator issuer(String var1);

   /** @deprecated */
   @Deprecated
   ClaimsMutator setSubject(String var1);

   ClaimsMutator subject(String var1);

   /** @deprecated */
   @Deprecated
   ClaimsMutator setAudience(String var1);

   AudienceCollection audience();

   /** @deprecated */
   @Deprecated
   ClaimsMutator setExpiration(Date var1);

   ClaimsMutator expiration(Date var1);

   /** @deprecated */
   @Deprecated
   ClaimsMutator setNotBefore(Date var1);

   ClaimsMutator notBefore(Date var1);

   /** @deprecated */
   @Deprecated
   ClaimsMutator setIssuedAt(Date var1);

   ClaimsMutator issuedAt(Date var1);

   /** @deprecated */
   @Deprecated
   ClaimsMutator setId(String var1);

   ClaimsMutator id(String var1);

   public interface AudienceCollection extends NestedCollection {
      /** @deprecated */
      @Deprecated
      Object single(String var1);
   }
}
