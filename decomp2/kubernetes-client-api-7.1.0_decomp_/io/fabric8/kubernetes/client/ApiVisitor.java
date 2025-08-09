package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.APIResource;
import io.fabric8.kubernetes.client.dsl.MixedOperation;

public interface ApiVisitor {
   default ApiVisitResult visitApiGroup(String group) {
      return ApiVisitor.ApiVisitResult.CONTINUE;
   }

   default ApiVisitResult visitApiGroupVersion(String group, String version) {
      return ApiVisitor.ApiVisitResult.CONTINUE;
   }

   ApiVisitResult visitResource(String var1, String var2, APIResource var3, MixedOperation var4);

   public static enum ApiVisitResult {
      TERMINATE,
      CONTINUE,
      SKIP;
   }
}
