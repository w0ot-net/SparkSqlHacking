package org.apache.spark.api.python;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import scala.Option;
import scala.collection.immutable.;

public final class PythonRunner$ {
   public static final PythonRunner$ MODULE$ = new PythonRunner$();
   private static final ConcurrentHashMap.KeySetView runningMonitorThreads = ConcurrentHashMap.newKeySet();
   private static final AtomicBoolean printPythonInfo = new AtomicBoolean(true);

   public ConcurrentHashMap.KeySetView runningMonitorThreads() {
      return runningMonitorThreads;
   }

   private AtomicBoolean printPythonInfo() {
      return printPythonInfo;
   }

   public PythonRunner apply(final PythonFunction func, final Option jobArtifactUUID) {
      if (this.printPythonInfo().compareAndSet(true, false)) {
         PythonUtils$.MODULE$.logPythonInfo(func.pythonExec());
      }

      return new PythonRunner(new .colon.colon(new ChainedPythonFunctions(new .colon.colon(func, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$), jobArtifactUUID);
   }

   private PythonRunner$() {
   }
}
