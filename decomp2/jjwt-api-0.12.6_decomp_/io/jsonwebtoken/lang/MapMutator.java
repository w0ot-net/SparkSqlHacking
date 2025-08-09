package io.jsonwebtoken.lang;

import java.util.Map;

public interface MapMutator {
   MapMutator delete(Object var1);

   MapMutator empty();

   MapMutator add(Object var1, Object var2);

   MapMutator add(Map var1);
}
