package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import java.util.Collection;

public class IdRegistry extends StringRegistry {
   public static final Function FN = new Function() {
      public String apply(Identifiable identifiable) {
         Assert.notNull(identifiable, "Identifiable argument cannot be null.");
         return (String)Assert.notNull(Strings.clean(identifiable.getId()), "Identifier cannot be null or empty.");
      }
   };

   public static Function fn() {
      return FN;
   }

   public IdRegistry(String name, Collection instances) {
      this(name, instances, true);
   }

   public IdRegistry(String name, Collection instances, boolean caseSensitive) {
      super(name, "id", Assert.notEmpty(instances, "Collection of Identifiable instances may not be null or empty."), fn(), caseSensitive);
   }
}
