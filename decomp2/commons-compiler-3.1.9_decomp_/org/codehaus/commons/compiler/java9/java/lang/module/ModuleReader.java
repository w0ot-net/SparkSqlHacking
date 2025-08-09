package org.codehaus.commons.compiler.java9.java.lang.module;

import java.io.IOException;
import java.lang.reflect.Method;
import org.codehaus.commons.compiler.java8.java.util.stream.Stream;
import org.codehaus.commons.compiler.util.reflect.Classes;
import org.codehaus.commons.compiler.util.reflect.Methods;

public class ModuleReader {
   private static final Class CLASS = Classes.load("java.lang.module.ModuleReader");
   private static final Method METHOD_list;
   private final Object delegate;

   public ModuleReader(Object delegate) {
      this.delegate = delegate;
   }

   public Stream list() throws IOException {
      return new Stream(Methods.invoke(METHOD_list, this.delegate));
   }

   static {
      METHOD_list = Classes.getDeclaredMethod(CLASS, "list");
   }
}
