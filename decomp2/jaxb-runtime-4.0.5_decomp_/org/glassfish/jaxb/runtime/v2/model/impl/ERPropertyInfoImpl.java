package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;

abstract class ERPropertyInfoImpl extends PropertyInfoImpl {
   private final QName xmlName;
   private final boolean wrapperNillable;
   private final boolean wrapperRequired;

   public ERPropertyInfoImpl(ClassInfoImpl classInfo, PropertySeed propertySeed) {
      super(classInfo, propertySeed);
      XmlElementWrapper e = (XmlElementWrapper)this.seed.readAnnotation(XmlElementWrapper.class);
      boolean nil = false;
      boolean required = false;
      if (!this.isCollection()) {
         this.xmlName = null;
         if (e != null) {
            ModelBuilder var10000 = classInfo.builder;
            Messages var10003 = Messages.XML_ELEMENT_WRAPPER_ON_NON_COLLECTION;
            Object[] var10004 = new Object[1];
            String var10007 = this.nav().getClassName(this.parent.getClazz());
            var10004[0] = var10007 + "." + this.seed.getName();
            var10000.reportError(new IllegalAnnotationException(var10003.format(var10004), e));
         }
      } else if (e != null) {
         this.xmlName = this.calcXmlName(e);
         nil = e.nillable();
         required = e.required();
      } else {
         this.xmlName = null;
      }

      this.wrapperNillable = nil;
      this.wrapperRequired = required;
   }

   public final QName getXmlName() {
      return this.xmlName;
   }

   public final boolean isCollectionNillable() {
      return this.wrapperNillable;
   }

   public final boolean isCollectionRequired() {
      return this.wrapperRequired;
   }
}
