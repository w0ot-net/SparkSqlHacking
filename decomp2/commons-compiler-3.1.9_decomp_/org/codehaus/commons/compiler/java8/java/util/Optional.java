package org.codehaus.commons.compiler.java8.java.util;

import java.lang.reflect.Method;
import org.codehaus.commons.compiler.util.reflect.Classes;
import org.codehaus.commons.compiler.util.reflect.Methods;

public class Optional {
   private static final Class CLASS = Classes.load("java.util.Optional");
   private static final Method METHOD_get;
   private final Object delegate;

   public Optional(Object delegate) {
      this.delegate = delegate;
   }

   public Object get() {
      return Methods.invoke(METHOD_get, this.delegate);
   }

   static {
      METHOD_get = Classes.getDeclaredMethod(CLASS, "get");
   }
}
