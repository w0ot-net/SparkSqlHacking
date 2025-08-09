package io.fabric8.kubernetes.client.dsl;

public interface NamespaceableResource extends Resource {
   Resource inNamespace(String var1);
}
