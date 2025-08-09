package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

final class RuntimeEnumConstantImpl extends EnumConstantImpl {
   public RuntimeEnumConstantImpl(RuntimeEnumLeafInfoImpl owner, String name, String lexical, EnumConstantImpl next) {
      super(owner, name, lexical, next);
   }
}
