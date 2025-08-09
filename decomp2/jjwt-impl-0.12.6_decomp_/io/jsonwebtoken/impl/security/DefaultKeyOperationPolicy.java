package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.security.KeyOperation;
import io.jsonwebtoken.security.KeyOperationPolicy;
import java.util.Collection;

final class DefaultKeyOperationPolicy implements KeyOperationPolicy {
   private final Collection ops;
   private final boolean allowUnrelated;

   DefaultKeyOperationPolicy(Collection ops, boolean allowUnrelated) {
      Assert.notEmpty(ops, "KeyOperation collection cannot be null or empty.");
      this.ops = Collections.immutable(ops);
      this.allowUnrelated = allowUnrelated;
   }

   public Collection getOperations() {
      return this.ops;
   }

   public void validate(Collection ops) {
      if (!this.allowUnrelated && !Collections.isEmpty(ops)) {
         for(KeyOperation operation : ops) {
            for(KeyOperation inner : ops) {
               if (!operation.isRelated(inner)) {
                  String msg = "Unrelated key operations are not allowed. KeyOperation [" + inner + "] is unrelated to [" + operation + "].";
                  throw new IllegalArgumentException(msg);
               }
            }
         }

      }
   }

   public int hashCode() {
      int hash = Boolean.valueOf(this.allowUnrelated).hashCode();
      KeyOperation[] ops = (KeyOperation[])this.ops.toArray(new KeyOperation[0]);
      hash = 31 * hash + Objects.nullSafeHashCode((Object[])ops);
      return hash;
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof DefaultKeyOperationPolicy)) {
         return false;
      } else {
         DefaultKeyOperationPolicy other = (DefaultKeyOperationPolicy)obj;
         return this.allowUnrelated == other.allowUnrelated && Collections.size(this.ops) == Collections.size(other.ops) && this.ops.containsAll(other.ops);
      }
   }
}
