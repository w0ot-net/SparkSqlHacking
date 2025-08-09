package org.apache.ivy.core.event.retrieve;

import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.retrieve.RetrieveOptions;

public class RetrieveEvent extends IvyEvent {
   private ModuleRevisionId mrid;
   private RetrieveOptions options;

   protected RetrieveEvent(String name, ModuleRevisionId mrid, String[] confs, RetrieveOptions options) {
      super(name);
      this.mrid = mrid;
      this.addMridAttributes(mrid);
      this.addConfsAttribute(confs);
      this.addAttribute("symlink", String.valueOf(options.isMakeSymlinks()));
      this.addAttribute("sync", String.valueOf(options.isSync()));
      this.options = options;
   }

   public ModuleRevisionId getModuleRevisionId() {
      return this.mrid;
   }

   public RetrieveOptions getOptions() {
      return this.options;
   }
}
