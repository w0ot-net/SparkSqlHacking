package io.fabric8.kubernetes.client.extended.run;

import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.List;
import java.util.Map;

public class EditableRunConfig extends RunConfig implements Editable {
   public EditableRunConfig(String name, String image, String imagePullPolicy, String command, List args, String restartPolicy, String serviceAccount, Map labels, Map env, Map limits, Map requests, int port) {
      super(name, image, imagePullPolicy, command, args, restartPolicy, serviceAccount, labels, env, limits, requests, port);
   }

   public RunConfigBuilder edit() {
      return new RunConfigBuilder(this);
   }
}
