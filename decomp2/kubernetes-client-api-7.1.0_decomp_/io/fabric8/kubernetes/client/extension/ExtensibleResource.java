package io.fabric8.kubernetes.client.extension;

import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.FieldValidateable;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.TimeoutableScalable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface ExtensibleResource extends Resource, TimeoutableScalable {
   ExtensibleResource lockResourceVersion(String var1);

   ExtensibleResource dryRun(boolean var1);

   ExtensibleResource withResourceVersion(String var1);

   ExtensibleResource fromServer();

   ExtensibleResource withGracePeriod(long var1);

   ExtensibleResource withPropagationPolicy(DeletionPropagation var1);

   ExtensibleResource withIndexers(Map var1);

   Client inWriteContext(Class var1);

   ExtensibleResource withLimit(Long var1);

   ExtensibleResource lockResourceVersion();

   Object getItem();

   ExtensibleResource fieldValidation(FieldValidateable.Validation var1);

   ExtensibleResource fieldManager(String var1);

   ExtensibleResource forceConflicts();

   ExtensibleResource withTimeout(long var1, TimeUnit var3);

   ExtensibleResource unlock();

   ExtensibleResource subresource(String var1);

   default ExtensibleResource withTimeoutInMillis(long timeoutInMillis) {
      return this.withTimeout(timeoutInMillis, TimeUnit.MILLISECONDS);
   }

   default Object scale(int count) {
      throw new KubernetesClientException("not implemented");
   }
}
