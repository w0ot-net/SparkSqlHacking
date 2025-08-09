package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.Event;
import io.fabric8.kubernetes.api.model.EventList;
import io.fabric8.kubernetes.api.model.PodTemplate;
import io.fabric8.kubernetes.api.model.PodTemplateList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1APIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1APIGroupClient extends ClientAdapter implements V1APIGroupDSL {
   public MixedOperation podTemplates() {
      return this.resources(PodTemplate.class, PodTemplateList.class);
   }

   public MixedOperation events() {
      return this.resources(Event.class, EventList.class);
   }

   public V1APIGroupClient newInstance() {
      return new V1APIGroupClient();
   }
}
