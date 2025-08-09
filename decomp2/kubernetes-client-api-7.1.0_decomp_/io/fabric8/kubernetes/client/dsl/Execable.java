package io.fabric8.kubernetes.client.dsl;

public interface Execable {
   ExecWatch exec(String... var1);

   ExecWatch attach();

   Execable withReadyWaitTimeout(Integer var1);
}
