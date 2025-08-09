package org.codehaus.commons.compiler.java8.java.util.function;

import java.lang.reflect.Method;
import org.codehaus.commons.compiler.util.reflect.Classes;

public interface Consumer {
   Class CLASS = Classes.load("java.util.function.Consumer");
   Method METHOD_accept__T = Classes.getDeclaredMethod(CLASS, "accept", Object.class);

   void accept(Object var1);
}
