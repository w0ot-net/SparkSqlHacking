package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.OperationInfo;

public class DefaultOperationInfo implements OperationInfo {
   private final String kind;
   private final String operationType;
   private final String name;
   private final String namespace;
   private final String group;
   private final String plural;
   private final String version;

   public DefaultOperationInfo(String kind, String operationType, String name, String namespace, String group, String plural, String version) {
      this.kind = kind;
      this.name = name;
      this.namespace = namespace;
      this.operationType = operationType;
      this.group = group;
      this.plural = plural;
      this.version = version;
   }

   public String getKind() {
      return this.kind;
   }

   public String getName() {
      return this.name;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public String getOperationType() {
      return this.operationType;
   }

   public OperationInfo forOperationType(String type) {
      return new DefaultOperationInfo(this.kind, type, this.name, this.namespace, this.group, this.plural, this.version);
   }

   public String getGroup() {
      return this.group;
   }

   public String getPlural() {
      return this.plural;
   }

   public String getVersion() {
      return this.version;
   }
}
