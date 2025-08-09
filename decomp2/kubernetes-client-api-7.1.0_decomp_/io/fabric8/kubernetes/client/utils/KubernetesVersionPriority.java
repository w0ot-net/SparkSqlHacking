package io.fabric8.kubernetes.client.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KubernetesVersionPriority {
   private KubernetesVersionPriority() {
   }

   public static String highestPriority(List versions) {
      List<KubernetesVersionFactory.Version> byPriority = sortByPriority(versions);
      return byPriority.isEmpty() ? null : ((KubernetesVersionFactory.Version)byPriority.get(0)).getFull();
   }

   private static List sortByPriority(List versions) {
      return versions != null && !versions.isEmpty() ? (List)versions.stream().map(KubernetesVersionFactory::create).sorted(Collections.reverseOrder()).collect(Collectors.toList()) : Collections.emptyList();
   }

   public static List sortByPriority(List resources, Function versionProvider) {
      Utils.checkNotNull(versionProvider, "versionProvider function can't be null");
      return resources != null && !resources.isEmpty() ? (List)resources.stream().sorted(Comparator.comparing((o) -> KubernetesVersionFactory.create((String)versionProvider.apply(o)), Comparator.reverseOrder())).collect(Collectors.toList()) : Collections.emptyList();
   }
}
