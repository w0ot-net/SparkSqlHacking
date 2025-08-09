package org.glassfish.hk2.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ClassAnalyzer {
   String DEFAULT_IMPLEMENTATION_NAME = "default";

   Constructor getConstructor(Class var1) throws MultiException, NoSuchMethodException;

   Set getInitializerMethods(Class var1) throws MultiException;

   Set getFields(Class var1) throws MultiException;

   Method getPostConstructMethod(Class var1) throws MultiException;

   Method getPreDestroyMethod(Class var1) throws MultiException;
}
