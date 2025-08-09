package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.core.MapPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;

class MapPropertyInfoImpl extends PropertyInfoImpl implements MapPropertyInfo {
   private final QName xmlName;
   private boolean nil;
   private final Object keyType;
   private final Object valueType;
   private NonElement keyTypeInfo;
   private NonElement valueTypeInfo;

   public MapPropertyInfoImpl(ClassInfoImpl ci, PropertySeed seed) {
      super(ci, seed);
      XmlElementWrapper xe = (XmlElementWrapper)seed.readAnnotation(XmlElementWrapper.class);
      this.xmlName = this.calcXmlName(xe);
      this.nil = xe != null && xe.nillable();
      T raw = (T)this.getRawType();
      T bt = (T)this.nav().getBaseClass(raw, this.nav().asDecl(Map.class));

      assert bt != null;

      if (this.nav().isParameterizedType(bt)) {
         this.keyType = this.nav().getTypeArgument(bt, 0);
         this.valueType = this.nav().getTypeArgument(bt, 1);
      } else {
         this.keyType = this.valueType = this.nav().ref(Object.class);
      }

   }

   public Collection ref() {
      return Arrays.asList(this.getKeyType(), this.getValueType());
   }

   public final PropertyKind kind() {
      return PropertyKind.MAP;
   }

   public QName getXmlName() {
      return this.xmlName;
   }

   public boolean isCollectionNillable() {
      return this.nil;
   }

   public NonElement getKeyType() {
      if (this.keyTypeInfo == null) {
         this.keyTypeInfo = this.getTarget(this.keyType);
      }

      return this.keyTypeInfo;
   }

   public NonElement getValueType() {
      if (this.valueTypeInfo == null) {
         this.valueTypeInfo = this.getTarget(this.valueType);
      }

      return this.valueTypeInfo;
   }

   public NonElement getTarget(Object type) {
      assert this.parent.builder != null : "this method must be called during the build stage";

      return this.parent.builder.getTypeInfo(type, this);
   }
}
