package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

public interface InjectionManager {
   void completeRegistration();

   void shutdown();

   boolean isShutdown();

   void register(Binding var1);

   void register(Iterable var1);

   void register(Binder var1);

   void register(Object var1) throws IllegalArgumentException;

   boolean isRegistrable(Class var1);

   Object create(Class var1);

   Object createAndInitialize(Class var1);

   List getAllServiceHolders(Class var1, Annotation... var2);

   Object getInstance(Class var1, Annotation... var2);

   Object getInstance(Class var1, String var2);

   Object getInstance(Class var1);

   Object getInstance(Type var1);

   Object getInstance(ForeignDescriptor var1);

   ForeignDescriptor createForeignDescriptor(Binding var1);

   List getAllInstances(Type var1);

   void inject(Object var1);

   void inject(Object var1, String var2);

   void preDestroy(Object var1);
}
