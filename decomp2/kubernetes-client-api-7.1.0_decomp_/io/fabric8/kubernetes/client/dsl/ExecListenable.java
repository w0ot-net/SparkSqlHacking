package io.fabric8.kubernetes.client.dsl;

public interface ExecListenable extends Execable {
   Execable usingListener(ExecListener var1);
}
