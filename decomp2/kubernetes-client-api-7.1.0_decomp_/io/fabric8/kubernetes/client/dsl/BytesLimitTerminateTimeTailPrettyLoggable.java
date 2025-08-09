package io.fabric8.kubernetes.client.dsl;

public interface BytesLimitTerminateTimeTailPrettyLoggable extends TimeTailPrettyLoggable {
   TimeTailPrettyLoggable limitBytes(int var1);

   TimeTailPrettyLoggable terminated();
}
