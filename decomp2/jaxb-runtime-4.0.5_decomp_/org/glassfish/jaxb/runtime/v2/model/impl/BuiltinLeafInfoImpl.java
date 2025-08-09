package org.glassfish.jaxb.runtime.v2.model.impl;

import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.core.BuiltinLeafInfo;
import org.glassfish.jaxb.core.v2.model.core.Element;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;

public class BuiltinLeafInfoImpl extends LeafInfoImpl implements BuiltinLeafInfo {
   private final QName[] typeNames;

   protected BuiltinLeafInfoImpl(Object type, QName... typeNames) {
      super(type, typeNames.length > 0 ? typeNames[0] : null);
      this.typeNames = typeNames;
   }

   public final QName[] getTypeNames() {
      return this.typeNames;
   }

   /** @deprecated */
   @Deprecated
   public final boolean isElement() {
      return false;
   }

   /** @deprecated */
   @Deprecated
   public final QName getElementName() {
      return null;
   }

   /** @deprecated */
   @Deprecated
   public final Element asElement() {
      return null;
   }

   public static Map createLeaves(Navigator nav) {
      Map<TypeT, BuiltinLeafInfoImpl<TypeT, ClassDeclT>> leaves = new HashMap();

      for(RuntimeBuiltinLeafInfoImpl leaf : RuntimeBuiltinLeafInfoImpl.builtinBeanInfos) {
         TypeT t = (TypeT)nav.ref(leaf.getClazz());
         leaves.put(t, new BuiltinLeafInfoImpl(t, leaf.getTypeNames()));
      }

      return leaves;
   }
}
