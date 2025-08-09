package org.glassfish.jaxb.runtime.v2.model.impl;

import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeRef;

class TypeRefImpl implements TypeRef {
   private final QName elementName;
   private final Object type;
   protected final ElementPropertyInfoImpl owner;
   private NonElement ref;
   private final boolean isNillable;
   private String defaultValue;

   public TypeRefImpl(ElementPropertyInfoImpl owner, QName elementName, Object type, boolean isNillable, String defaultValue) {
      this.owner = owner;
      this.elementName = elementName;
      this.type = type;
      this.isNillable = isNillable;
      this.defaultValue = defaultValue;

      assert owner != null;

      assert elementName != null;

      assert type != null;

   }

   public NonElement getTarget() {
      if (this.ref == null) {
         this.calcRef();
      }

      return this.ref;
   }

   public QName getTagName() {
      return this.elementName;
   }

   public boolean isNillable() {
      return this.isNillable;
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   protected void link() {
      this.calcRef();
   }

   private void calcRef() {
      this.ref = this.owner.parent.builder.getTypeInfo(this.type, this.owner);

      assert this.ref != null;

   }

   public PropertyInfo getSource() {
      return this.owner;
   }
}
