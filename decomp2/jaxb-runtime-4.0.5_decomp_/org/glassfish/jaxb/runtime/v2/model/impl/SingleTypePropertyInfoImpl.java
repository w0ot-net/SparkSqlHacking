package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.annotation.XmlList;
import java.util.Collections;
import java.util.List;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElementRef;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

abstract class SingleTypePropertyInfoImpl extends PropertyInfoImpl {
   private NonElement type;
   private final Accessor acc;
   private Transducer xducer;

   public SingleTypePropertyInfoImpl(ClassInfoImpl classInfo, PropertySeed seed) {
      super(classInfo, seed);
      if (this instanceof RuntimePropertyInfo) {
         Accessor rawAcc = ((RuntimeClassInfoImpl.RuntimePropertySeed)seed).getAccessor();
         if (this.getAdapter() != null && !this.isCollection()) {
            rawAcc = rawAcc.adapt(((RuntimePropertyInfo)this).getAdapter());
         }

         this.acc = rawAcc;
      } else {
         this.acc = null;
      }

   }

   public List ref() {
      return Collections.singletonList(this.getTarget());
   }

   public NonElement getTarget() {
      if (this.type == null) {
         assert this.parent.builder != null : "this method must be called during the build stage";

         this.type = this.parent.builder.getTypeInfo(this.getIndividualType(), this);
      }

      return this.type;
   }

   public PropertyInfo getSource() {
      return this;
   }

   public void link() {
      super.link();
      if (!NonElement.ANYTYPE_NAME.equals(this.type.getTypeName()) && !this.type.isSimpleType() && this.id() != ID.IDREF) {
         this.parent.builder.reportError(new IllegalAnnotationException(Messages.SIMPLE_TYPE_IS_REQUIRED.format(), this.seed));
      }

      if (!this.isCollection() && this.seed.hasAnnotation(XmlList.class)) {
         this.parent.builder.reportError(new IllegalAnnotationException(Messages.XMLLIST_ON_SINGLE_PROPERTY.format(), this));
      }

   }

   public Accessor getAccessor() {
      return this.acc;
   }

   public Transducer getTransducer() {
      if (this.xducer == null) {
         this.xducer = RuntimeModelBuilder.createTransducer((RuntimeNonElementRef)this);
         if (this.xducer == null) {
            this.xducer = RuntimeBuiltinLeafInfoImpl.STRING;
         }
      }

      return this.xducer;
   }
}
