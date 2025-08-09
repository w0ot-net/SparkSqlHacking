package io.jsonwebtoken.security;

import io.jsonwebtoken.lang.Builder;
import io.jsonwebtoken.lang.CollectionMutator;
import java.util.Collection;

public interface KeyOperationPolicyBuilder extends CollectionMutator, Builder {
   KeyOperationPolicyBuilder unrelated();

   KeyOperationPolicyBuilder add(KeyOperation var1);

   KeyOperationPolicyBuilder add(Collection var1);
}
