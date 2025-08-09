package org.apache.ivy.core.event.retrieve;

import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.retrieve.RetrieveOptions;

public class EndRetrieveEvent extends RetrieveEvent {
   public static final String NAME = "post-retrieve";
   private long duration;
   private int nbCopied;
   private int nbUpToDate;
   private long totalCopiedSize;

   public EndRetrieveEvent(ModuleRevisionId mrid, String[] confs, long elapsedTime, int targetsCopied, int targetsUpToDate, long totalCopiedSize, RetrieveOptions options) {
      super("post-retrieve", mrid, confs, options);
      this.duration = elapsedTime;
      this.nbCopied = targetsCopied;
      this.nbUpToDate = targetsUpToDate;
      this.totalCopiedSize = totalCopiedSize;
      this.addAttribute("duration", String.valueOf(elapsedTime));
      this.addAttribute("size", String.valueOf(totalCopiedSize));
      this.addAttribute("nbCopied", String.valueOf(targetsCopied));
      this.addAttribute("nbUptodate", String.valueOf(targetsUpToDate));
   }

   public long getDuration() {
      return this.duration;
   }

   public int getNbCopied() {
      return this.nbCopied;
   }

   public int getNbUpToDate() {
      return this.nbUpToDate;
   }

   public long getTotalCopiedSize() {
      return this.totalCopiedSize;
   }
}
