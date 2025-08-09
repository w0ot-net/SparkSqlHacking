package org.glassfish.jersey.server;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.BootstrapConfigurator;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.wadl.processor.OptionsMethodProcessor;

class ModelProcessorConfigurator implements BootstrapConfigurator {
   private static final Function CAST_TO_MODEL_PROCESSOR;
   private static final Predicate BINDING_MODEL_PROCESSOR_ONLY;
   private static final Predicate CONTRACT_PROVIDER_MODEL_PROCESSOR_ONLY;

   public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
      ServerBootstrapBag serverBag = (ServerBootstrapBag)bootstrapBag;
      ResourceConfig runtimeConfig = serverBag.getRuntimeConfig();
      ComponentBag componentBag = runtimeConfig.getComponentBag();
      OptionsMethodProcessor optionsMethodProcessor = new OptionsMethodProcessor();
      injectionManager.register(Bindings.service(optionsMethodProcessor).to(ModelProcessor.class));
      Stream var10000 = componentBag.getClasses(CONTRACT_PROVIDER_MODEL_PROCESSOR_ONLY).stream();
      injectionManager.getClass();
      List<ModelProcessor> modelProcessors = (List)Stream.concat(var10000.map(injectionManager::createAndInitialize), componentBag.getInstances(CONTRACT_PROVIDER_MODEL_PROCESSOR_ONLY).stream()).map(CAST_TO_MODEL_PROCESSOR).collect(Collectors.toList());
      modelProcessors.add(optionsMethodProcessor);
      List<ModelProcessor> modelProcessorsFromBinders = ComponentBag.getFromBinders(injectionManager, componentBag, CAST_TO_MODEL_PROCESSOR, BINDING_MODEL_PROCESSOR_ONLY);
      modelProcessors.addAll(modelProcessorsFromBinders);
      serverBag.setModelProcessors(modelProcessors);
   }

   static {
      ModelProcessor.class.getClass();
      CAST_TO_MODEL_PROCESSOR = ModelProcessor.class::cast;
      BINDING_MODEL_PROCESSOR_ONLY = (binding) -> binding.getContracts().contains(ModelProcessor.class);
      CONTRACT_PROVIDER_MODEL_PROCESSOR_ONLY = (provider) -> provider.getContracts().contains(ModelProcessor.class);
   }
}
