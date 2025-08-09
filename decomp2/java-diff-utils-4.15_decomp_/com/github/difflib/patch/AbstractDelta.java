package com.github.difflib.patch;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class AbstractDelta implements Serializable {
   private final Chunk source;
   private final Chunk target;
   private final DeltaType type;

   public AbstractDelta(DeltaType type, Chunk source, Chunk target) {
      Objects.requireNonNull(source);
      Objects.requireNonNull(target);
      Objects.requireNonNull(type);
      this.type = type;
      this.source = source;
      this.target = target;
   }

   public Chunk getSource() {
      return this.source;
   }

   public Chunk getTarget() {
      return this.target;
   }

   public DeltaType getType() {
      return this.type;
   }

   protected VerifyChunk verifyChunkToFitTarget(List target) throws PatchFailedException {
      return this.getSource().verifyChunk(target);
   }

   protected VerifyChunk verifyAndApplyTo(List target) throws PatchFailedException {
      VerifyChunk verify = this.verifyChunkToFitTarget(target);
      if (verify == VerifyChunk.OK) {
         this.applyTo(target);
      }

      return verify;
   }

   protected abstract void applyTo(List var1) throws PatchFailedException;

   protected abstract void restore(List var1);

   protected void applyFuzzyToAt(List target, int fuzz, int position) throws PatchFailedException {
      throw new UnsupportedOperationException(this.getClass().getSimpleName() + " does not supports applying patch fuzzy");
   }

   public abstract AbstractDelta withChunks(Chunk var1, Chunk var2);

   public int hashCode() {
      return Objects.hash(new Object[]{this.source, this.target, this.type});
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         AbstractDelta<?> other = (AbstractDelta)obj;
         if (!Objects.equals(this.source, other.source)) {
            return false;
         } else if (!Objects.equals(this.target, other.target)) {
            return false;
         } else {
            return this.type == other.type;
         }
      }
   }
}
