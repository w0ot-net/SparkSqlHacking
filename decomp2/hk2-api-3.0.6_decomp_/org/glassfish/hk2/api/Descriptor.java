package org.glassfish.hk2.api;

import java.util.Map;
import java.util.Set;

public interface Descriptor {
   String getImplementation();

   Set getAdvertisedContracts();

   String getScope();

   String getName();

   Set getQualifiers();

   DescriptorType getDescriptorType();

   DescriptorVisibility getDescriptorVisibility();

   Map getMetadata();

   HK2Loader getLoader();

   int getRanking();

   int setRanking(int var1);

   Boolean isProxiable();

   Boolean isProxyForSameScope();

   String getClassAnalysisName();

   Long getServiceId();

   Long getLocatorId();
}
