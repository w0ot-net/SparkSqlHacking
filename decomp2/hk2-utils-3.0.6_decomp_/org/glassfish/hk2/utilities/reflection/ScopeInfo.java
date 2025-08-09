package org.glassfish.hk2.utilities.reflection;

import java.lang.annotation.Annotation;

public class ScopeInfo {
   private final Annotation scope;
   private final Class annoType;

   public ScopeInfo(Annotation scope, Class annoType) {
      this.scope = scope;
      this.annoType = annoType;
   }

   public Annotation getScope() {
      return this.scope;
   }

   public Class getAnnoType() {
      return this.annoType;
   }
}
