package com.github.difflib.patch;

import java.util.List;

public final class InsertDelta extends AbstractDelta {
   public InsertDelta(Chunk original, Chunk revised) {
      super(DeltaType.INSERT, original, revised);
   }

   protected void applyTo(List target) throws PatchFailedException {
      int position = this.getSource().getPosition();
      List<T> lines = this.getTarget().getLines();

      for(int i = 0; i < lines.size(); ++i) {
         target.add(position + i, lines.get(i));
      }

   }

   protected void restore(List target) {
      int position = this.getTarget().getPosition();
      int size = this.getTarget().size();

      for(int i = 0; i < size; ++i) {
         target.remove(position);
      }

   }

   public String toString() {
      return "[InsertDelta, position: " + this.getSource().getPosition() + ", lines: " + this.getTarget().getLines() + "]";
   }

   public AbstractDelta withChunks(Chunk original, Chunk revised) {
      return new InsertDelta(original, revised);
   }
}
