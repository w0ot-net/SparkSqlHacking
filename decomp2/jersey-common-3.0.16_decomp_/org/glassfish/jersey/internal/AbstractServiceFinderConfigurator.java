package org.glassfish.jersey.internal;

import jakarta.ws.rs.RuntimeType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public abstract class AbstractServiceFinderConfigurator implements BootstrapConfigurator {
   private final Class contract;
   private final RuntimeType runtimeType;

   protected AbstractServiceFinderConfigurator(Class contract, RuntimeType runtimeType) {
      this.contract = contract;
      this.runtimeType = runtimeType;
   }

   protected List loadImplementations(Map applicationProperties) {
      return PropertiesHelper.isMetaInfServicesEnabled(applicationProperties, this.runtimeType) ? (List)Stream.of(ServiceFinder.find(this.contract, true).toClassArray()).collect(Collectors.toList()) : Collections.emptyList();
   }

   protected RuntimeType getRuntimeType() {
      return this.runtimeType;
   }

   protected Class getContract() {
      return this.contract;
   }
}
