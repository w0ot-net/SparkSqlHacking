package io.fabric8.kubernetes.client.dsl;

public interface TimeTailPrettyLoggable extends TailPrettyLoggable {
   TailPrettyLoggable sinceTime(String var1);

   TailPrettyLoggable sinceSeconds(int var1);
}
