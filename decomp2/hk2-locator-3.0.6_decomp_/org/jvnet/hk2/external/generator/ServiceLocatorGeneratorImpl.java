package org.jvnet.hk2.external.generator;

import jakarta.inject.Singleton;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.jvnet.hk2.internal.DefaultClassAnalyzer;
import org.jvnet.hk2.internal.DynamicConfigurationImpl;
import org.jvnet.hk2.internal.DynamicConfigurationServiceImpl;
import org.jvnet.hk2.internal.InstantiationServiceImpl;
import org.jvnet.hk2.internal.ServiceLocatorImpl;
import org.jvnet.hk2.internal.ServiceLocatorRuntimeImpl;
import org.jvnet.hk2.internal.Utilities;

public class ServiceLocatorGeneratorImpl implements ServiceLocatorGenerator {
   private ServiceLocatorImpl initialize(String name, ServiceLocator parent) {
      if (parent != null && !(parent instanceof ServiceLocatorImpl)) {
         String var10002 = ServiceLocatorImpl.class.getName();
         throw new AssertionError("parent must be a " + var10002 + " instead it is a " + parent.getClass().getName());
      } else {
         ServiceLocatorImpl sli = new ServiceLocatorImpl(name, (ServiceLocatorImpl)parent);
         DynamicConfigurationImpl dci = new DynamicConfigurationImpl(sli);
         dci.bind((Descriptor)Utilities.getLocatorDescriptor(sli));
         dci.addActiveDescriptor(Utilities.getThreeThirtyDescriptor(sli));
         dci.bind((Descriptor)BuilderHelper.link(DynamicConfigurationServiceImpl.class, false).to(DynamicConfigurationService.class).in(Singleton.class.getName()).localOnly().build());
         dci.bind((Descriptor)BuilderHelper.createConstantDescriptor(new DefaultClassAnalyzer(sli)));
         dci.bind((Descriptor)BuilderHelper.createDescriptorFromClass(ServiceLocatorRuntimeImpl.class));
         dci.bind((Descriptor)BuilderHelper.createConstantDescriptor(new InstantiationServiceImpl()));
         dci.commit();
         return sli;
      }
   }

   public ServiceLocator create(String name, ServiceLocator parent) {
      ServiceLocatorImpl retVal = this.initialize(name, parent);
      return retVal;
   }

   public String toString() {
      return "ServiceLocatorGeneratorImpl(hk2-locator, " + System.identityHashCode(this) + ")";
   }
}
