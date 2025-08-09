package io.fabric8.kubernetes.client.dsl;

public interface PrettyLoggable extends Loggable {
   Loggable withPrettyOutput();
}
