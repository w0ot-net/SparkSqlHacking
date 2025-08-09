package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfoSet;

final class RuntimeTypeInfoSetImpl extends TypeInfoSetImpl implements RuntimeTypeInfoSet {
   public RuntimeTypeInfoSetImpl(AnnotationReader reader) {
      super(Utils.REFLECTION_NAVIGATOR, reader, RuntimeBuiltinLeafInfoImpl.LEAVES);
   }

   protected RuntimeNonElement createAnyType() {
      return RuntimeAnyTypeImpl.theInstance;
   }

   public RuntimeNonElement getTypeInfo(Type type) {
      return (RuntimeNonElement)super.getTypeInfo((Object)type);
   }

   public RuntimeNonElement getAnyTypeInfo() {
      return (RuntimeNonElement)super.getAnyTypeInfo();
   }

   public RuntimeNonElement getClassInfo(Class clazz) {
      return (RuntimeNonElement)super.getClassInfo(clazz);
   }

   public Map beans() {
      return super.beans();
   }

   public Map builtins() {
      return super.builtins();
   }

   public Map enums() {
      return super.enums();
   }

   public Map arrays() {
      return super.arrays();
   }

   public RuntimeElementInfoImpl getElementInfo(Class scope, QName name) {
      return (RuntimeElementInfoImpl)super.getElementInfo(scope, name);
   }

   public Map getElementMappings(Class scope) {
      return super.getElementMappings(scope);
   }

   public Iterable getAllElements() {
      return super.getAllElements();
   }
}
