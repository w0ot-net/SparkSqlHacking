package com.github.difflib.patch;

import java.util.List;
import java.util.Objects;

public final class ChangeDelta extends AbstractDelta {
   public ChangeDelta(Chunk source, Chunk target) {
      super(DeltaType.CHANGE, source, target);
      Objects.requireNonNull(source, "source must not be null");
      Objects.requireNonNull(target, "target must not be null");
   }

   protected void applyTo(List target) throws PatchFailedException {
      int position = this.getSource().getPosition();
      int size = this.getSource().size();

      for(int i = 0; i < size; ++i) {
         target.remove(position);
      }

      int i = 0;

      for(Object line : this.getTarget().getLines()) {
         target.add(position + i, line);
         ++i;
      }

   }

   protected void restore(List target) {
      int position = this.getTarget().getPosition();
      int size = this.getTarget().size();

      for(int i = 0; i < size; ++i) {
         target.remove(position);
      }

      int i = 0;

      for(Object line : this.getSource().getLines()) {
         target.add(position + i, line);
         ++i;
      }

   }

   protected void applyFuzzyToAt(List target, int fuzz, int position) throws PatchFailedException {
      int size = this.getSource().size();

      for(int i = fuzz; i < size - fuzz; ++i) {
         target.remove(position + fuzz);
      }

      int i = fuzz;

      for(Object line : this.getTarget().getLines().subList(fuzz, this.getTarget().size() - fuzz)) {
         target.add(position + i, line);
         ++i;
      }

   }

   public String toString() {
      return "[ChangeDelta, position: " + this.getSource().getPosition() + ", lines: " + this.getSource().getLines() + " to " + this.getTarget().getLines() + "]";
   }

   public AbstractDelta withChunks(Chunk original, Chunk revised) {
      return new ChangeDelta(original, revised);
   }
}
