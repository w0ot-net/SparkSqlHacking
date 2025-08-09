package org.codehaus.commons.compiler.java9.java.lang.module;

import java.io.IOException;
import java.lang.reflect.Method;
import org.codehaus.commons.compiler.java8.java.util.Optional;
import org.codehaus.commons.compiler.util.reflect.Classes;
import org.codehaus.commons.compiler.util.reflect.Methods;

public class ModuleReference {
   private static final Class CLASS = Classes.load("java.lang.module.ModuleReference");
   private static final Method METHOD_location;
   private static final Method METHOD_open;
   private final Object delegate;

   public ModuleReference(Object delegate) {
      this.delegate = delegate;
   }

   public Optional location() {
      return new Optional(Methods.invoke(METHOD_location, this.delegate));
   }

   public ModuleReader open() throws IOException {
      return new ModuleReader(Methods.invoke(METHOD_open, this.delegate));
   }

   static {
      METHOD_location = Classes.getDeclaredMethod(CLASS, "location");
      METHOD_open = Classes.getDeclaredMethod(CLASS, "open");
   }
}
