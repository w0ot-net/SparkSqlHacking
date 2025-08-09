package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.glassfish.jaxb.core.v2.model.core.Adapter;
import org.glassfish.jaxb.core.v2.model.core.RegistryInfo;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeClassInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeRef;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

final class RuntimeElementInfoImpl extends ElementInfoImpl implements RuntimeElementInfo {
   private final Class adapterType;

   public RuntimeElementInfoImpl(RuntimeModelBuilder modelBuilder, RegistryInfo registry, Method method) throws IllegalAnnotationException {
      super(modelBuilder, (RegistryInfoImpl)registry, method);
      Adapter<Type, Class> a = this.getProperty().getAdapter();
      if (a != null) {
         this.adapterType = (Class)a.adapterType;
      } else {
         this.adapterType = null;
      }

   }

   protected ElementInfoImpl.PropertyImpl createPropertyImpl() {
      return new RuntimePropertyImpl();
   }

   public RuntimeElementPropertyInfo getProperty() {
      return (RuntimeElementPropertyInfo)super.getProperty();
   }

   public Class getType() {
      return (Class)Utils.REFLECTION_NAVIGATOR.erasure((Type)super.getType());
   }

   public RuntimeClassInfo getScope() {
      return (RuntimeClassInfo)super.getScope();
   }

   public RuntimeNonElement getContentType() {
      return (RuntimeNonElement)super.getContentType();
   }

   class RuntimePropertyImpl extends ElementInfoImpl.PropertyImpl implements RuntimeElementPropertyInfo, RuntimeTypeRef {
      public Accessor getAccessor() {
         return RuntimeElementInfoImpl.this.adapterType == null ? Accessor.JAXB_ELEMENT_VALUE : Accessor.JAXB_ELEMENT_VALUE.adapt((Class)this.getAdapter().defaultType, RuntimeElementInfoImpl.this.adapterType);
      }

      public Type getRawType() {
         return Collection.class;
      }

      public Type getIndividualType() {
         return (Type)RuntimeElementInfoImpl.this.getContentType().getType();
      }

      public boolean elementOnlyContent() {
         return false;
      }

      public List getTypes() {
         return Collections.singletonList(this);
      }

      public List ref() {
         return super.ref();
      }

      public RuntimeNonElement getTarget() {
         return (RuntimeNonElement)super.getTarget();
      }

      public RuntimePropertyInfo getSource() {
         return this;
      }

      public Transducer getTransducer() {
         return RuntimeModelBuilder.createTransducer(this);
      }
   }
}
