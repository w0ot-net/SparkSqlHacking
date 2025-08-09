package org.glassfish.hk2.api;

import jakarta.inject.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface IterableProvider extends Provider, Iterable {
   ServiceHandle getHandle();

   int getSize();

   IterableProvider named(String var1);

   IterableProvider ofType(Type var1);

   IterableProvider qualifiedWith(Annotation... var1);

   Iterable handleIterator();
}
