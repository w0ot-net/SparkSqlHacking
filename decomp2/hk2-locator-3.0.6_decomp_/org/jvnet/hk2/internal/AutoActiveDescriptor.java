package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;

public class AutoActiveDescriptor extends AbstractActiveDescriptor {
   private static final long serialVersionUID = -7921574114250721537L;
   private Class implClass;
   private Creator creator;
   private SystemDescriptor hk2Parent;
   private Type implType;

   public AutoActiveDescriptor() {
   }

   public AutoActiveDescriptor(Class clazz, Creator creator, Set advertisedContracts, Class scope, String name, Set qualifiers, DescriptorVisibility descriptorVisibility, int ranking, Boolean proxy, Boolean proxyForSameScope, String classAnalysisName, Map metadata, DescriptorType descriptorType, Type clazzType) {
      super(advertisedContracts, scope, name, qualifiers, DescriptorType.CLASS, descriptorVisibility, ranking, proxy, proxyForSameScope, classAnalysisName, metadata);
      this.implClass = clazz;
      this.creator = creator;
      this.setImplementation(this.implClass.getName());
      this.setDescriptorType(descriptorType);
      if (clazzType == null) {
         this.implType = clazz;
      } else {
         this.implType = clazzType;
      }

   }

   void resetSelfDescriptor(ActiveDescriptor toMe) {
      if (this.creator instanceof ClazzCreator) {
         ClazzCreator<?> cc = (ClazzCreator)this.creator;
         cc.resetSelfDescriptor(toMe);
      }
   }

   void setHK2Parent(SystemDescriptor hk2Parent) {
      this.hk2Parent = hk2Parent;
   }

   public Class getImplementationClass() {
      return this.implClass;
   }

   public Type getImplementationType() {
      return this.implType;
   }

   public void setImplementationType(Type t) {
      this.implType = t;
   }

   public Object create(ServiceHandle root) {
      return this.creator.create(root, this.hk2Parent);
   }

   public void dispose(Object instance) {
      this.creator.dispose(instance);
   }

   public List getInjectees() {
      return this.creator.getInjectees();
   }
}
