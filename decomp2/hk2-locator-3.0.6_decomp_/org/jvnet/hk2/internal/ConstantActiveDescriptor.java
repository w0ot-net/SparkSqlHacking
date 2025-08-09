package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;

public class ConstantActiveDescriptor extends AbstractActiveDescriptor {
   private static final long serialVersionUID = 3663054975929743877L;
   private Object theOne;
   private Long locatorId;

   public ConstantActiveDescriptor() {
   }

   public ConstantActiveDescriptor(Object theOne, ServiceLocatorImpl locator) {
      super(new HashSet(), PerLookup.class, (String)null, new HashSet(), DescriptorType.CLASS, DescriptorVisibility.NORMAL, 0, (Boolean)null, (Boolean)null, locator.getPerLocatorUtilities().getAutoAnalyzerName(theOne.getClass()), (Map)null);
      this.theOne = theOne;
      this.locatorId = locator.getLocatorId();
   }

   public ConstantActiveDescriptor(Object theOne, Set advertisedContracts, Class scope, String name, Set qualifiers, DescriptorVisibility visibility, int ranking, Boolean proxy, Boolean proxyForSameScope, String analyzerName, long locatorId, Map metadata) {
      super(advertisedContracts, scope, name, qualifiers, DescriptorType.CLASS, visibility, ranking, proxy, proxyForSameScope, analyzerName, metadata);
      if (theOne == null) {
         throw new IllegalArgumentException();
      } else {
         this.theOne = theOne;
         this.locatorId = locatorId;
      }
   }

   public String getImplementation() {
      return this.theOne.getClass().getName();
   }

   public Long getLocatorId() {
      return this.locatorId;
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

   public void setImplementationType(Type t) {
      throw new AssertionError("Can not set type of a constant descriptor");
   }

   public Object create(ServiceHandle root) {
      return this.theOne;
   }

   public void dispose(Object instance) {
   }
}
