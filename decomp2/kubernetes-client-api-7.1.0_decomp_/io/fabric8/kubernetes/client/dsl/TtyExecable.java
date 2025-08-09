package io.fabric8.kubernetes.client.dsl;

public interface TtyExecable extends ExecListenable {
   ExecListenable withTTY();
}
