package io.jsonwebtoken;

import io.jsonwebtoken.lang.MapMutator;

public interface HeaderMutator extends MapMutator {
   HeaderMutator type(String var1);

   HeaderMutator contentType(String var1);

   /** @deprecated */
   @Deprecated
   HeaderMutator setType(String var1);

   /** @deprecated */
   @Deprecated
   HeaderMutator setContentType(String var1);

   /** @deprecated */
   @Deprecated
   HeaderMutator setCompressionAlgorithm(String var1);
}
