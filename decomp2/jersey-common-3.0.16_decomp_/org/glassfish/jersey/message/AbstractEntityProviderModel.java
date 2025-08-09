package org.glassfish.jersey.message;

import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.glassfish.jersey.internal.util.ReflectionHelper;

public abstract class AbstractEntityProviderModel {
   private final Object provider;
   private final List declaredTypes;
   private final boolean custom;
   private final Class providedType;

   AbstractEntityProviderModel(Object provider, List declaredTypes, boolean custom, Class providerType) {
      this.provider = provider;
      this.declaredTypes = declaredTypes;
      this.custom = custom;
      this.providedType = getProviderClassParam(provider, providerType);
   }

   public Object provider() {
      return this.provider;
   }

   public List declaredTypes() {
      return this.declaredTypes;
   }

   public boolean isCustom() {
      return this.custom;
   }

   public Class providedType() {
      return this.providedType;
   }

   private static Class getProviderClassParam(Object provider, Class providerType) {
      ReflectionHelper.DeclaringClassInterfacePair pair = ReflectionHelper.getClass(provider.getClass(), providerType);
      Class[] classArgs = ReflectionHelper.getParameterizedClassArguments(pair);
      return classArgs != null ? classArgs[0] : Object.class;
   }
}
