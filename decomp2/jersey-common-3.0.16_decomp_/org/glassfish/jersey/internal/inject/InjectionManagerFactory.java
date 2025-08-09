package org.glassfish.jersey.internal.inject;

public interface InjectionManagerFactory {
   default InjectionManager create() {
      return this.create((Object)null);
   }

   InjectionManager create(Object var1);
}
