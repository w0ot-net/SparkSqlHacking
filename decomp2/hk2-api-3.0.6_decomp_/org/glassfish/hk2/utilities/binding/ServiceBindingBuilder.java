package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

public interface ServiceBindingBuilder extends BindingBuilder {
   ServiceBindingBuilder to(Class var1);

   ServiceBindingBuilder to(TypeLiteral var1);

   ServiceBindingBuilder to(Type var1);

   ServiceBindingBuilder loadedBy(HK2Loader var1);

   ServiceBindingBuilder withMetadata(String var1, String var2);

   ServiceBindingBuilder withMetadata(String var1, List var2);

   ServiceBindingBuilder qualifiedBy(Annotation var1);

   ScopedBindingBuilder in(Annotation var1);

   ScopedBindingBuilder in(Class var1);

   NamedBindingBuilder named(String var1);

   void ranked(int var1);

   ServiceBindingBuilder proxy(boolean var1);

   ServiceBindingBuilder proxyForSameScope(boolean var1);

   ServiceBindingBuilder analyzeWith(String var1);

   ServiceBindingBuilder asType(Type var1);
}
