package io.jsonwebtoken.impl;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.lang.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class DefaultJwtHeaderBuilder extends DefaultJweHeaderBuilder implements Jwts.HeaderBuilder {
   public DefaultJwtHeaderBuilder() {
   }

   public DefaultJwtHeaderBuilder(DefaultJweHeaderMutator src) {
      super(src);
   }

   private static ParameterMap sanitizeCrit(ParameterMap m, boolean protectedHeader) {
      Set<String> crit = (Set)m.get(DefaultProtectedHeader.CRIT);
      if (crit == null) {
         return m;
      } else {
         m = new ParameterMap(DefaultJweHeader.PARAMS, m, true);
         m.remove(DefaultProtectedHeader.CRIT.getId());
         if (!protectedHeader) {
            return m;
         } else {
            Set<String> newCrit = new LinkedHashSet(crit);

            for(String val : crit) {
               if (DefaultJweHeader.PARAMS.containsKey(val) || !m.containsKey(val)) {
                  newCrit.remove(val);
               }
            }

            if (!Collections.isEmpty(newCrit)) {
               m.put((Parameter)DefaultProtectedHeader.CRIT, newCrit);
            }

            return m;
         }
      }
   }

   public Header build() {
      this.x509.apply();
      ParameterMap m = (ParameterMap)this.DELEGATE;
      if (DefaultJweHeader.isCandidate(m)) {
         return new DefaultJweHeader(sanitizeCrit(m, true));
      } else {
         return (Header)(DefaultProtectedHeader.isCandidate(m) ? new DefaultJwsHeader(sanitizeCrit(m, true)) : new DefaultHeader(sanitizeCrit(m, false)));
      }
   }
}
