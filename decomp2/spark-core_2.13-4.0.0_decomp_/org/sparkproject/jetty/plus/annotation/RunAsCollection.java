package org.sparkproject.jetty.plus.annotation;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.servlet.ServletHolder;

/** @deprecated */
@Deprecated
public class RunAsCollection {
   private static final Logger LOG = LoggerFactory.getLogger(RunAsCollection.class);
   public static final String RUNAS_COLLECTION = "org.sparkproject.jetty.runAsCollection";
   private ConcurrentMap _runAsMap = new ConcurrentHashMap();

   public void add(RunAs runAs) {
      if (runAs != null && runAs.getTargetClassName() != null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Adding run-as for class=" + runAs.getTargetClassName());
         }

         RunAs prev = (RunAs)this._runAsMap.putIfAbsent(runAs.getTargetClassName(), runAs);
         if (prev != null) {
            LOG.warn("Run-As {} on class {} ignored, already run-as {}", new Object[]{runAs.getRoleName(), runAs.getTargetClassName(), prev.getRoleName()});
         }

      }
   }

   public RunAs getRunAs(Object o) {
      return o == null ? null : (RunAs)this._runAsMap.get(o.getClass().getName());
   }

   public void setRunAs(Object o) {
      if (o != null) {
         if (ServletHolder.class.isAssignableFrom(o.getClass())) {
            RunAs runAs = (RunAs)this._runAsMap.get(o.getClass().getName());
            if (runAs != null) {
               runAs.setRunAs((ServletHolder)o);
            }
         }
      }
   }
}
