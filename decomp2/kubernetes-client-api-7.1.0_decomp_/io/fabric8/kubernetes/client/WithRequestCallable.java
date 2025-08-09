package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.FunctionCallable;
import java.util.function.Function;

public class WithRequestCallable implements FunctionCallable {
   private final Client client;
   private final RequestConfig requestConfig;

   public WithRequestCallable(Client client, RequestConfig requestConfig) {
      this.client = client;
      this.requestConfig = requestConfig;
   }

   public Object call(Function function) {
      C newClient = (C)this.client.newClient(this.requestConfig).adapt(this.client.getClass());
      return function.apply(newClient);
   }
}
