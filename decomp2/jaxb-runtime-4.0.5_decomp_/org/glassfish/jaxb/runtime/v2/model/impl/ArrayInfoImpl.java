package org.glassfish.jaxb.runtime.v2.model.impl;

import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.ArrayInfo;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.util.ArrayInfoUtil;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.core.v2.runtime.Location;

class ArrayInfoImpl extends TypeInfoImpl implements ArrayInfo, Location {
   private final NonElement itemType;
   private final QName typeName;
   private final Object arrayType;

   public ArrayInfoImpl(ModelBuilder builder, Locatable upstream, Object arrayType) {
      super(builder, upstream);
      this.arrayType = arrayType;
      TypeT componentType = (TypeT)this.nav().getComponentType(arrayType);
      this.itemType = builder.getTypeInfo(componentType, this);
      QName n = this.itemType.getTypeName();
      if (n == null) {
         builder.reportError(new IllegalAnnotationException(Messages.ANONYMOUS_ARRAY_ITEM.format(this.nav().getTypeName(componentType)), this));
         n = new QName("#dummy");
      }

      this.typeName = ArrayInfoUtil.calcArrayTypeName(n);
   }

   public NonElement getItemType() {
      return this.itemType;
   }

   public QName getTypeName() {
      return this.typeName;
   }

   public boolean isSimpleType() {
      return false;
   }

   public Object getType() {
      return this.arrayType;
   }

   /** @deprecated */
   @Deprecated
   public final boolean canBeReferencedByIDREF() {
      return false;
   }

   public Location getLocation() {
      return this;
   }

   public String toString() {
      return this.nav().getTypeName(this.arrayType);
   }
}
