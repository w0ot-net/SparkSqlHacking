package org.glassfish.jaxb.runtime.api;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

public abstract class ClassResolver {
   protected ClassResolver() {
   }

   @Nullable
   public abstract Class resolveElementName(@NotNull String var1, @NotNull String var2) throws Exception;
}
