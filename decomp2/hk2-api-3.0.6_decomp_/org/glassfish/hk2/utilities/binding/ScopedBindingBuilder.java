package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

public interface ScopedBindingBuilder extends BindingBuilder {
   ScopedBindingBuilder to(Class var1);

   ScopedBindingBuilder to(TypeLiteral var1);

   ScopedBindingBuilder loadedBy(HK2Loader var1);

   ScopedBindingBuilder withMetadata(String var1, String var2);

   ScopedBindingBuilder withMetadata(String var1, List var2);

   ScopedBindingBuilder qualifiedBy(Annotation var1);

   ScopedNamedBindingBuilder named(String var1);

   void ranked(int var1);

   ScopedBindingBuilder proxy(boolean var1);

   ScopedBindingBuilder proxyForSameScope(boolean var1);

   ScopedBindingBuilder analyzeWith(String var1);
}
