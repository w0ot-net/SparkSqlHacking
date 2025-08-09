package io.fabric8.kubernetes.client.extended.run;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.ContainerPort;
import io.fabric8.kubernetes.api.model.ContainerPortBuilder;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.PodSpecBuilder;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunConfigUtil {
   private static final String DEFAULT_RESTART_POLICY = "Always";

   private RunConfigUtil() {
   }

   public static ObjectMeta getObjectMetadataFromRunConfig(RunConfig generatorRunConfig) {
      ObjectMetaBuilder objectMetaBuilder = new ObjectMetaBuilder();
      if (generatorRunConfig.getName() != null) {
         objectMetaBuilder.withName(generatorRunConfig.getName());
         objectMetaBuilder.addToLabels("run", generatorRunConfig.getName());
      }

      if (generatorRunConfig.getLabels() != null) {
         objectMetaBuilder.addToLabels(generatorRunConfig.getLabels());
      }

      return objectMetaBuilder.build();
   }

   public static PodSpec getPodSpecFromRunConfig(RunConfig generatorRunConfig) {
      PodSpecBuilder podSpecBuilder = new PodSpecBuilder();
      if (generatorRunConfig.getRestartPolicy() != null) {
         podSpecBuilder.withRestartPolicy(generatorRunConfig.getRestartPolicy());
      } else {
         podSpecBuilder.withRestartPolicy("Always");
      }

      if (generatorRunConfig.getServiceAccount() != null) {
         podSpecBuilder.withServiceAccountName(generatorRunConfig.getServiceAccount());
      }

      podSpecBuilder.addToContainers(new Container[]{containerFromConfig(generatorRunConfig)});
      return podSpecBuilder.build();
   }

   static Container containerFromConfig(RunConfig runConfig) {
      ContainerBuilder containerBuilder = new ContainerBuilder();
      containerBuilder.withName(runConfig.getName());
      containerBuilder.withImage(runConfig.getImage());
      containerBuilder.withImagePullPolicy(runConfig.getImagePullPolicy());
      containerBuilder.withArgs(argsFromConfig(runConfig));
      containerBuilder.withCommand(commandFromConfig(runConfig));
      if (runConfig.getEnv() != null) {
         containerBuilder.withEnv(KubernetesResourceUtil.convertMapToEnvVarList(runConfig.getEnv()));
      }

      if (runConfig.getPort() > 0) {
         containerBuilder.withPorts(new ContainerPort[]{((ContainerPortBuilder)(new ContainerPortBuilder()).withContainerPort(runConfig.getPort())).build()});
      }

      if (runConfig.getLimits() != null) {
         ((ContainerFluent.ResourcesNested)containerBuilder.editOrNewResources().addToLimits(runConfig.getLimits())).endResources();
      }

      if (runConfig.getRequests() != null) {
         ((ContainerFluent.ResourcesNested)containerBuilder.editOrNewResources().addToRequests(runConfig.getRequests())).endResources();
      }

      return containerBuilder.build();
   }

   private static String[] argsFromConfig(RunConfig runConfig) {
      return Utils.isNullOrEmpty(runConfig.getCommand()) && runConfig.getArgs() != null ? (String[])runConfig.getArgs().toArray(new String[0]) : new String[0];
   }

   private static String[] commandFromConfig(RunConfig runConfig) {
      if (Utils.isNotNullOrEmpty(runConfig.getCommand())) {
         List<String> command = new ArrayList(Collections.singletonList(runConfig.getCommand()));
         if (runConfig.getArgs() != null) {
            command.addAll(runConfig.getArgs());
         }

         return (String[])command.toArray(new String[0]);
      } else {
         return new String[0];
      }
   }
}
