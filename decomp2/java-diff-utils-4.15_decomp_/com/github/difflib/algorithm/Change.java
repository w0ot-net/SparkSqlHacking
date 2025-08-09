package com.github.difflib.algorithm;

import com.github.difflib.patch.DeltaType;

public class Change {
   public final DeltaType deltaType;
   public final int startOriginal;
   public final int endOriginal;
   public final int startRevised;
   public final int endRevised;

   public Change(DeltaType deltaType, int startOriginal, int endOriginal, int startRevised, int endRevised) {
      this.deltaType = deltaType;
      this.startOriginal = startOriginal;
      this.endOriginal = endOriginal;
      this.startRevised = startRevised;
      this.endRevised = endRevised;
   }

   public Change withEndOriginal(int endOriginal) {
      return new Change(this.deltaType, this.startOriginal, endOriginal, this.startRevised, this.endRevised);
   }

   public Change withEndRevised(int endRevised) {
      return new Change(this.deltaType, this.startOriginal, this.endOriginal, this.startRevised, endRevised);
   }
}
