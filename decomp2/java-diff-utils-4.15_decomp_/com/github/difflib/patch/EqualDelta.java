package com.github.difflib.patch;

import java.util.List;

public class EqualDelta extends AbstractDelta {
   public EqualDelta(Chunk source, Chunk target) {
      super(DeltaType.EQUAL, source, target);
   }

   protected void applyTo(List target) throws PatchFailedException {
   }

   protected void restore(List target) {
   }

   protected void applyFuzzyToAt(List target, int fuzz, int delta) {
   }

   public String toString() {
      return "[EqualDelta, position: " + this.getSource().getPosition() + ", lines: " + this.getSource().getLines() + "]";
   }

   public AbstractDelta withChunks(Chunk original, Chunk revised) {
      return new EqualDelta(original, revised);
   }
}
