package org.apache.parquet.hadoop.util.wrapped.io;

import org.apache.parquet.util.DynMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class BindingUtils {
   private static final Logger LOG = LoggerFactory.getLogger(BindingUtils.class);

   private BindingUtils() {
   }

   static DynMethods.UnboundMethod loadInvocation(Class source, Class returnType, String name, Class... parameterTypes) {
      if (source != null) {
         DynMethods.UnboundMethod m = (new DynMethods.Builder(name)).impl(source, name, parameterTypes).orNoop().build();
         if (m.isNoop()) {
            LOG.debug("Failed to load method {} from {}", name, source);
         } else {
            LOG.debug("Found method {} from {}", name, source);
         }

         return m;
      } else {
         return noop(name);
      }
   }

   static DynMethods.UnboundMethod noop(String name) {
      return (new DynMethods.Builder(name)).orNoop().build();
   }

   static boolean implemented(DynMethods.UnboundMethod... methods) {
      for(DynMethods.UnboundMethod method : methods) {
         if (method.isNoop()) {
            return false;
         }
      }

      return true;
   }
}
