package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.TypeLiteral;

public interface NamedBindingBuilder extends BindingBuilder {
   NamedBindingBuilder to(Class var1);

   NamedBindingBuilder to(TypeLiteral var1);

   NamedBindingBuilder loadedBy(HK2Loader var1);

   NamedBindingBuilder withMetadata(String var1, String var2);

   NamedBindingBuilder withMetadata(String var1, List var2);

   NamedBindingBuilder qualifiedBy(Annotation var1);

   ScopedNamedBindingBuilder in(Class var1);

   void ranked(int var1);

   NamedBindingBuilder proxy(boolean var1);

   NamedBindingBuilder asType(Type var1);
}
