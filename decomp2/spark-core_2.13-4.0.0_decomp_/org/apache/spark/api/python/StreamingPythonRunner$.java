package org.apache.spark.api.python;

public final class StreamingPythonRunner$ {
   public static final StreamingPythonRunner$ MODULE$ = new StreamingPythonRunner$();

   public StreamingPythonRunner apply(final PythonFunction func, final String connectUrl, final String sessionId, final String workerModule) {
      return new StreamingPythonRunner(func, connectUrl, sessionId, workerModule);
   }

   private StreamingPythonRunner$() {
   }
}
