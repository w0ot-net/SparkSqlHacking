package org.glassfish.hk2.utilities;

import java.util.HashSet;
import java.util.Set;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.IndexedFilter;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.PopulatorPostProcessor;
import org.glassfish.hk2.api.ServiceLocator;

@PerLookup
public class DuplicatePostProcessor implements PopulatorPostProcessor {
   private final DuplicatePostProcessorMode mode;
   private final HashSet strictDupSet;
   private final HashSet implOnlyDupSet;

   public DuplicatePostProcessor() {
      this(DuplicatePostProcessorMode.STRICT);
   }

   public DuplicatePostProcessor(DuplicatePostProcessorMode mode) {
      this.strictDupSet = new HashSet();
      this.implOnlyDupSet = new HashSet();
      this.mode = mode;
   }

   public DuplicatePostProcessorMode getMode() {
      return this.mode;
   }

   public DescriptorImpl process(ServiceLocator serviceLocator, DescriptorImpl descriptorImpl) {
      switch (this.mode) {
         case STRICT:
            return this.strict(serviceLocator, descriptorImpl);
         case IMPLEMENTATION_ONLY:
            return this.implementationOnly(serviceLocator, descriptorImpl);
         default:
            throw new AssertionError("UnkownMode: " + this.mode);
      }
   }

   private DescriptorImpl implementationOnly(ServiceLocator serviceLocator, final DescriptorImpl descriptorImpl) {
      final String impl = descriptorImpl.getImplementation();
      if (impl == null) {
         return descriptorImpl;
      } else {
         ImplOnlyKey key = new ImplOnlyKey(descriptorImpl);
         if (this.implOnlyDupSet.contains(key)) {
            return null;
         } else {
            this.implOnlyDupSet.add(key);
            return serviceLocator.getBestDescriptor(new Filter() {
               public boolean matches(Descriptor d) {
                  return d.getImplementation().equals(impl) && d.getDescriptorType().equals(descriptorImpl.getDescriptorType());
               }
            }) != null ? null : descriptorImpl;
         }
      }
   }

   private DescriptorImpl strict(ServiceLocator serviceLocator, final DescriptorImpl descriptorImpl) {
      if (this.strictDupSet.contains(descriptorImpl)) {
         return null;
      } else {
         this.strictDupSet.add(descriptorImpl);
         Set<String> contracts = descriptorImpl.getAdvertisedContracts();
         final String contract = null;

         for(String candidate : contracts) {
            if (candidate.equals(descriptorImpl.getImplementation())) {
               contract = candidate;
               break;
            }

            contract = candidate;
         }

         final String fName = descriptorImpl.getName();
         return serviceLocator.getBestDescriptor(new IndexedFilter() {
            public boolean matches(Descriptor d) {
               return descriptorImpl.equals(d);
            }

            public String getAdvertisedContract() {
               return contract;
            }

            public String getName() {
               return fName;
            }
         }) != null ? null : descriptorImpl;
      }
   }

   public String toString() {
      DuplicatePostProcessorMode var10000 = this.mode;
      return "DuplicateCodeProcessor(" + var10000 + "," + System.identityHashCode(this) + ")";
   }

   private static final class ImplOnlyKey {
      private final String impl;
      private final DescriptorType type;
      private final int hash;

      private ImplOnlyKey(Descriptor desc) {
         this.impl = desc.getImplementation();
         this.type = desc.getDescriptorType();
         this.hash = this.impl.hashCode() ^ this.type.hashCode();
      }

      public int hashCode() {
         return this.hash;
      }

      public boolean equals(Object o) {
         if (o == null) {
            return false;
         } else if (!(o instanceof ImplOnlyKey)) {
            return false;
         } else {
            ImplOnlyKey other = (ImplOnlyKey)o;
            return other.impl.equals(this.impl) && other.type.equals(this.type);
         }
      }

      public String toString() {
         String var10000 = this.impl;
         return "ImplOnlyKey(" + var10000 + "," + this.type + "," + System.identityHashCode(this) + ")";
      }
   }
}
