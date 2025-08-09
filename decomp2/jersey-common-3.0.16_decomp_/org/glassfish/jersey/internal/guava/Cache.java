package org.glassfish.jersey.internal.guava;

public interface Cache {
   Object getIfPresent(Object var1);

   void put(Object var1, Object var2);
}
