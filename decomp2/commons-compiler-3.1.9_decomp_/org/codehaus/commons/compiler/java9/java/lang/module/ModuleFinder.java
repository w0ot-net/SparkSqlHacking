package org.codehaus.commons.compiler.java9.java.lang.module;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.commons.compiler.util.reflect.Classes;
import org.codehaus.commons.compiler.util.reflect.Methods;

public final class ModuleFinder {
   private static final Class CLASS = Classes.load("java.lang.module.ModuleFinder");
   private static final Method METHOD_ofSystem;
   private static final Method METHOD_findAll;
   private final Object delegate;

   private ModuleFinder(Object delegate) {
      this.delegate = delegate;
   }

   public static ModuleFinder ofSystem() {
      return new ModuleFinder(Methods.invoke(METHOD_ofSystem, (Object)null));
   }

   public Set findAll() {
      return (Set)wrapModuleReferences((Set)Methods.invoke(METHOD_findAll, this.delegate), new HashSet());
   }

   private static Collection wrapModuleReferences(Collection moduleReferences, Collection result) {
      for(Object mref : moduleReferences) {
         result.add(new ModuleReference(mref));
      }

      return result;
   }

   static {
      METHOD_ofSystem = Classes.getDeclaredMethod(CLASS, "ofSystem");
      METHOD_findAll = Classes.getDeclaredMethod(CLASS, "findAll");
   }
}
