package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

public interface ScopedNamedBindingBuilder extends BindingBuilder {
   ScopedNamedBindingBuilder to(Class var1);

   ScopedNamedBindingBuilder to(TypeLiteral var1);

   ScopedNamedBindingBuilder loadedBy(HK2Loader var1);

   ScopedNamedBindingBuilder withMetadata(String var1, String var2);

   ScopedNamedBindingBuilder withMetadata(String var1, List var2);

   ScopedNamedBindingBuilder qualifiedBy(Annotation var1);

   void ranked(int var1);

   ScopedNamedBindingBuilder proxy(boolean var1);

   ScopedNamedBindingBuilder analyzeWith(String var1);
}
