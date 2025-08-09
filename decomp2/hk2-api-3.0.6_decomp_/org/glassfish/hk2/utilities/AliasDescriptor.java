package org.glassfish.hk2.utilities;

import jakarta.inject.Named;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.HK2Loader;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.general.GeneralUtilities;

public class AliasDescriptor extends AbstractActiveDescriptor {
   public static final String ALIAS_METADATA_MARKER = "__AliasOf";
   public static final String ALIAS_FREE_DESCRIPTOR = "FreeDescriptor";
   private static final long serialVersionUID = 2609895430798803508L;
   private ServiceLocator locator;
   private ActiveDescriptor descriptor;
   private String contract;
   private Set qualifiers;
   private Set qualifierNames;
   private boolean initialized = false;
   private static final Set EMPTY_CONTRACT_SET = new HashSet();
   private static final Set EMPTY_ANNOTATION_SET = new HashSet();

   public AliasDescriptor() {
   }

   public AliasDescriptor(ServiceLocator locator, ActiveDescriptor descriptor, String contract, String name) {
      super(EMPTY_CONTRACT_SET, (Class)null, name, EMPTY_ANNOTATION_SET, descriptor.getDescriptorType(), descriptor.getDescriptorVisibility(), descriptor.getRanking(), descriptor.isProxiable(), descriptor.isProxyForSameScope(), descriptor.getClassAnalysisName(), descriptor.getMetadata());
      this.locator = locator;
      this.descriptor = descriptor;
      this.contract = contract;
      this.addAdvertisedContract(contract);
      super.setScope(descriptor.getScope());
      super.addMetadata("__AliasOf", getAliasMetadataValue(descriptor));
   }

   private static String getAliasMetadataValue(ActiveDescriptor descriptor) {
      Long locatorId = descriptor.getLocatorId();
      Long serviceId = descriptor.getServiceId();
      return locatorId != null && serviceId != null ? locatorId + "." + serviceId : "FreeDescriptor";
   }

   public Class getImplementationClass() {
      this.ensureInitialized();
      return this.descriptor.getImplementationClass();
   }

   public Type getImplementationType() {
      this.ensureInitialized();
      return this.descriptor.getImplementationType();
   }

   public Object create(ServiceHandle root) {
      this.ensureInitialized();
      return this.locator.getServiceHandle(this.descriptor).getService();
   }

   public boolean isReified() {
      return true;
   }

   public String getImplementation() {
      return this.descriptor.getImplementation();
   }

   public Set getContractTypes() {
      this.ensureInitialized();
      return super.getContractTypes();
   }

   public Class getScopeAnnotation() {
      this.ensureInitialized();
      return this.descriptor.getScopeAnnotation();
   }

   public synchronized Set getQualifierAnnotations() {
      this.ensureInitialized();
      if (this.qualifiers == null) {
         this.qualifiers = new HashSet(this.descriptor.getQualifierAnnotations());
         if (this.getName() != null) {
            this.qualifiers.add(new NamedImpl(this.getName()));
         }
      }

      return this.qualifiers;
   }

   public synchronized Set getQualifiers() {
      if (this.qualifierNames != null) {
         return this.qualifierNames;
      } else {
         this.qualifierNames = new HashSet(this.descriptor.getQualifiers());
         if (this.getName() != null) {
            this.qualifierNames.add(Named.class.getName());
         }

         return this.qualifierNames;
      }
   }

   public List getInjectees() {
      this.ensureInitialized();
      return this.descriptor.getInjectees();
   }

   public void dispose(Object instance) {
      this.ensureInitialized();
      this.descriptor.dispose(instance);
   }

   public ActiveDescriptor getDescriptor() {
      return this.descriptor;
   }

   private synchronized void ensureInitialized() {
      if (!this.initialized) {
         if (!this.descriptor.isReified()) {
            this.descriptor = this.locator.reifyDescriptor(this.descriptor);
         }

         if (this.contract == null) {
            this.initialized = true;
            return;
         }

         HK2Loader loader = this.descriptor.getLoader();
         Type contractType = null;

         try {
            if (loader != null) {
               contractType = loader.loadClass(this.contract);
            } else {
               Class<?> ic = this.descriptor.getImplementationClass();
               ClassLoader cl = null;
               if (ic != null) {
                  cl = ic.getClassLoader();
               }

               if (cl == null) {
                  cl = ClassLoader.getSystemClassLoader();
               }

               contractType = cl.loadClass(this.contract);
            }
         } catch (ClassNotFoundException var5) {
         }

         super.addContractType(contractType);
         this.initialized = true;
      }

   }

   public int hashCode() {
      int retVal;
      synchronized(this) {
         retVal = this.descriptor.hashCode();
      }

      if (this.getName() != null) {
         retVal ^= this.getName().hashCode();
      }

      if (this.contract != null) {
         retVal ^= this.contract.hashCode();
      }

      return retVal;
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (!(o instanceof AliasDescriptor)) {
         return false;
      } else {
         AliasDescriptor<?> other = (AliasDescriptor)o;
         if (!other.descriptor.equals(this.descriptor)) {
            return false;
         } else {
            return !GeneralUtilities.safeEquals(other.getName(), this.getName()) ? false : GeneralUtilities.safeEquals(other.contract, this.contract);
         }
      }
   }
}
