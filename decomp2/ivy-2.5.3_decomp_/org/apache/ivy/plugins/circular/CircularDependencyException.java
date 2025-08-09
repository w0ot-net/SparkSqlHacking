package org.apache.ivy.plugins.circular;

import java.util.Collection;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public class CircularDependencyException extends RuntimeException {
   private static final long serialVersionUID = 670272039106237360L;
   private ModuleRevisionId[] mrids;

   public CircularDependencyException(ModuleRevisionId[] mrids) {
      super(CircularDependencyHelper.formatMessage(mrids));
      this.mrids = mrids;
   }

   public CircularDependencyException(Collection mrids) {
      this((ModuleRevisionId[])mrids.toArray(new ModuleRevisionId[mrids.size()]));
   }

   public ModuleRevisionId[] getPath() {
      return this.mrids;
   }
}
