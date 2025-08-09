package org.glassfish.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;

public class ConstantActiveDescriptor extends AbstractActiveDescriptor {
   private static final long serialVersionUID = -9196390718074767455L;
   private final Object theOne;

   public ConstantActiveDescriptor() {
      this.theOne = null;
   }

   public ConstantActiveDescriptor(Object theOne, Set advertisedContracts, Class scope, String name, Set qualifiers, DescriptorVisibility descriptorVisibility, Boolean proxy, Boolean proxyForSameScope, String classAnalysisName, Map metadata, int rank) {
      super(advertisedContracts, scope, name, qualifiers, DescriptorType.CLASS, descriptorVisibility, rank, proxy, proxyForSameScope, classAnalysisName, metadata);
      if (theOne == null) {
         throw new IllegalArgumentException();
      } else {
         this.theOne = theOne;
      }
   }

   public String getImplementation() {
      return this.theOne.getClass().getName();
   }

   public Object getCache() {
      return this.theOne;
   }

   public boolean isCacheSet() {
      return true;
   }

   public Class getImplementationClass() {
      return this.theOne.getClass();
   }

   public Type getImplementationType() {
      return this.theOne.getClass();
   }

   public Object create(ServiceHandle root) {
      return this.theOne;
   }
}
