package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.HasMetadata;

public interface OperationInfo {
   String getKind();

   String getName();

   String getNamespace();

   String getOperationType();

   OperationInfo forOperationType(String var1);

   default String getGroup() {
      return null;
   }

   default String getPlural() {
      return null;
   }

   default String getVersion() {
      return null;
   }

   default String getFullResourceName() {
      String plural = this.getPlural();
      String group = this.getGroup();
      return plural != null && group != null ? HasMetadata.getFullResourceName(plural, group) : null;
   }
}
