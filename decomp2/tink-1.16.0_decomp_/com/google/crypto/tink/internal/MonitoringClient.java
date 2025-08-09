package com.google.crypto.tink.internal;

import com.google.crypto.tink.annotations.Alpha;

@Alpha
public interface MonitoringClient {
   Logger createLogger(MonitoringKeysetInfo keysetInfo, String primitive, String api);

   public interface Logger {
      void log(int keyId, long numBytesAsInput);

      void logFailure();
   }
}
