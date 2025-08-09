package com.github.difflib.patch;

import java.util.List;

public final class DeleteDelta extends AbstractDelta {
   public DeleteDelta(Chunk original, Chunk revised) {
      super(DeltaType.DELETE, original, revised);
   }

   protected void applyTo(List target) throws PatchFailedException {
      int position = this.getSource().getPosition();
      int size = this.getSource().size();

      for(int i = 0; i < size; ++i) {
         target.remove(position);
      }

   }

   protected void restore(List target) {
      int position = this.getTarget().getPosition();
      List<T> lines = this.getSource().getLines();

      for(int i = 0; i < lines.size(); ++i) {
         target.add(position + i, lines.get(i));
      }

   }

   public String toString() {
      return "[DeleteDelta, position: " + this.getSource().getPosition() + ", lines: " + this.getSource().getLines() + "]";
   }

   public AbstractDelta withChunks(Chunk original, Chunk revised) {
      return new DeleteDelta(original, revised);
   }
}
