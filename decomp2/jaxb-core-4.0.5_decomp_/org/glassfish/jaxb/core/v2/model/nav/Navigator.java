package org.glassfish.jaxb.core.v2.model.nav;

import java.util.Collection;
import org.glassfish.jaxb.core.v2.runtime.Location;

public interface Navigator {
   Object getSuperClass(Object var1);

   Object getBaseClass(Object var1, Object var2);

   String getClassName(Object var1);

   String getTypeName(Object var1);

   String getClassShortName(Object var1);

   Collection getDeclaredFields(Object var1);

   Object getDeclaredField(Object var1, String var2);

   Collection getDeclaredMethods(Object var1);

   Object getDeclaringClassForField(Object var1);

   Object getDeclaringClassForMethod(Object var1);

   Object getFieldType(Object var1);

   String getFieldName(Object var1);

   String getMethodName(Object var1);

   Object getReturnType(Object var1);

   Object[] getMethodParameters(Object var1);

   boolean isStaticMethod(Object var1);

   boolean isSubClassOf(Object var1, Object var2);

   Object ref(Class var1);

   Object use(Object var1);

   Object asDecl(Object var1);

   Object asDecl(Class var1);

   boolean isArray(Object var1);

   boolean isArrayButNotByteArray(Object var1);

   Object getComponentType(Object var1);

   Object getTypeArgument(Object var1, int var2);

   boolean isParameterizedType(Object var1);

   boolean isPrimitive(Object var1);

   Object getPrimitive(Class var1);

   Location getClassLocation(Object var1);

   Location getFieldLocation(Object var1);

   Location getMethodLocation(Object var1);

   boolean hasDefaultConstructor(Object var1);

   boolean isStaticField(Object var1);

   boolean isPublicMethod(Object var1);

   boolean isFinalMethod(Object var1);

   boolean isPublicField(Object var1);

   boolean isEnum(Object var1);

   Object erasure(Object var1);

   boolean isAbstract(Object var1);

   boolean isFinal(Object var1);

   Object[] getEnumConstants(Object var1);

   Object getVoidType();

   String getPackageName(Object var1);

   Object loadObjectFactory(Object var1, String var2);

   boolean isBridgeMethod(Object var1);

   boolean isOverriding(Object var1, Object var2);

   boolean isInterface(Object var1);

   boolean isTransient(Object var1);

   boolean isInnerClass(Object var1);

   boolean isSameType(Object var1, Object var2);
}
