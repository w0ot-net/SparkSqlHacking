package com.github.difflib.patch;

import com.github.difflib.algorithm.Change;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public final class Patch implements Serializable {
   private final List deltas;
   public final ConflictOutput CONFLICT_PRODUCES_EXCEPTION;
   public static final ConflictOutput CONFLICT_PRODUCES_MERGE_CONFLICT = (verifyChunk, delta, result) -> {
      if (result.size() <= delta.getSource().getPosition()) {
         throw new UnsupportedOperationException("Not supported yet.");
      } else {
         List<String> orgData = new ArrayList();

         for(int i = 0; i < delta.getSource().size(); ++i) {
            orgData.add(result.get(delta.getSource().getPosition()));
            result.remove(delta.getSource().getPosition());
         }

         orgData.add(0, "<<<<<< HEAD");
         orgData.add("======");
         orgData.addAll(delta.getSource().getLines());
         orgData.add(">>>>>>> PATCH");
         result.addAll(delta.getSource().getPosition(), orgData);
      }
   };
   private ConflictOutput conflictOutput;

   public Patch() {
      this(10);
   }

   public Patch(int estimatedPatchSize) {
      this.CONFLICT_PRODUCES_EXCEPTION = (verifyChunk, delta, result) -> {
         throw new PatchFailedException("could not apply patch due to " + verifyChunk.toString());
      };
      this.conflictOutput = this.CONFLICT_PRODUCES_EXCEPTION;
      this.deltas = new ArrayList(estimatedPatchSize);
   }

   public List applyTo(List target) throws PatchFailedException {
      List<T> result = new ArrayList(target);
      this.applyToExisting(result);
      return result;
   }

   public void applyToExisting(List target) throws PatchFailedException {
      ListIterator<AbstractDelta<T>> it = this.getDeltas().listIterator(this.deltas.size());

      while(it.hasPrevious()) {
         AbstractDelta<T> delta = (AbstractDelta)it.previous();
         VerifyChunk valid = delta.verifyAndApplyTo(target);
         if (valid != VerifyChunk.OK) {
            this.conflictOutput.processConflict(valid, delta, target);
         }
      }

   }

   public List applyFuzzy(List target, int maxFuzz) throws PatchFailedException {
      PatchApplyingContext<T> ctx = new PatchApplyingContext(new ArrayList(target), maxFuzz);
      int lastPatchDelta = 0;

      for(AbstractDelta delta : this.getDeltas()) {
         ctx.defaultPosition = delta.getSource().getPosition() + lastPatchDelta;
         int patchPosition = this.findPositionFuzzy(ctx, delta);
         if (0 <= patchPosition) {
            delta.applyFuzzyToAt(ctx.result, ctx.currentFuzz, patchPosition);
            lastPatchDelta = patchPosition - delta.getSource().getPosition();
            ctx.lastPatchEnd = delta.getSource().last() + lastPatchDelta;
         } else {
            this.conflictOutput.processConflict(VerifyChunk.CONTENT_DOES_NOT_MATCH_TARGET, delta, ctx.result);
         }
      }

      return ctx.result;
   }

   private int findPositionFuzzy(PatchApplyingContext ctx, AbstractDelta delta) throws PatchFailedException {
      for(int fuzz = 0; fuzz <= ctx.maxFuzz; ++fuzz) {
         ctx.currentFuzz = fuzz;
         int foundPosition = this.findPositionWithFuzz(ctx, delta, fuzz);
         if (foundPosition >= 0) {
            return foundPosition;
         }
      }

      return -1;
   }

   private int findPositionWithFuzz(PatchApplyingContext ctx, AbstractDelta delta, int fuzz) throws PatchFailedException {
      if (delta.getSource().verifyChunk(ctx.result, fuzz, ctx.defaultPosition) == VerifyChunk.OK) {
         return ctx.defaultPosition;
      } else {
         ctx.beforeOutRange = false;
         ctx.afterOutRange = false;

         for(int moreDelta = 0; moreDelta >= 0; ++moreDelta) {
            int pos = this.findPositionWithFuzzAndMoreDelta(ctx, delta, fuzz, moreDelta);
            if (pos >= 0) {
               return pos;
            }

            if (ctx.beforeOutRange && ctx.afterOutRange) {
               break;
            }
         }

         return -1;
      }
   }

   private int findPositionWithFuzzAndMoreDelta(PatchApplyingContext ctx, AbstractDelta delta, int fuzz, int moreDelta) throws PatchFailedException {
      if (!ctx.beforeOutRange) {
         int beginAt = ctx.defaultPosition - moreDelta + fuzz;
         if (beginAt <= ctx.lastPatchEnd) {
            ctx.beforeOutRange = true;
         }
      }

      if (!ctx.afterOutRange) {
         int beginAt = ctx.defaultPosition + moreDelta + delta.getSource().size() - fuzz;
         if (ctx.result.size() < beginAt) {
            ctx.afterOutRange = true;
         }
      }

      if (!ctx.beforeOutRange) {
         VerifyChunk before = delta.getSource().verifyChunk(ctx.result, fuzz, ctx.defaultPosition - moreDelta);
         if (before == VerifyChunk.OK) {
            return ctx.defaultPosition - moreDelta;
         }
      }

      if (!ctx.afterOutRange) {
         VerifyChunk after = delta.getSource().verifyChunk(ctx.result, fuzz, ctx.defaultPosition + moreDelta);
         if (after == VerifyChunk.OK) {
            return ctx.defaultPosition + moreDelta;
         }
      }

      return -1;
   }

   public Patch withConflictOutput(ConflictOutput conflictOutput) {
      this.conflictOutput = conflictOutput;
      return this;
   }

   public List restore(List target) {
      List<T> result = new ArrayList(target);
      this.restoreToExisting(result);
      return result;
   }

   public void restoreToExisting(List target) {
      ListIterator<AbstractDelta<T>> it = this.getDeltas().listIterator(this.deltas.size());

      while(it.hasPrevious()) {
         AbstractDelta<T> delta = (AbstractDelta)it.previous();
         delta.restore(target);
      }

   }

   public void addDelta(AbstractDelta delta) {
      this.deltas.add(delta);
   }

   public List getDeltas() {
      this.deltas.sort(Comparator.comparing((d) -> d.getSource().getPosition()));
      return this.deltas;
   }

   public String toString() {
      return "Patch{deltas=" + this.deltas + '}';
   }

   public static Patch generate(List original, List revised, List changes) {
      return generate(original, revised, changes, false);
   }

   private static Chunk buildChunk(int start, int end, List data) {
      return new Chunk(start, new ArrayList(data.subList(start, end)));
   }

   public static Patch generate(List original, List revised, List _changes, boolean includeEquals) {
      Patch<T> patch = new Patch(_changes.size());
      int startOriginal = 0;
      int startRevised = 0;
      List<Change> changes = _changes;
      if (includeEquals) {
         changes = new ArrayList(_changes);
         Collections.sort(changes, Comparator.comparing((d) -> d.startOriginal));
      }

      for(Change change : changes) {
         if (includeEquals && startOriginal < change.startOriginal) {
            patch.addDelta(new EqualDelta(buildChunk(startOriginal, change.startOriginal, original), buildChunk(startRevised, change.startRevised, revised)));
         }

         Chunk<T> orgChunk = buildChunk(change.startOriginal, change.endOriginal, original);
         Chunk<T> revChunk = buildChunk(change.startRevised, change.endRevised, revised);
         switch (change.deltaType) {
            case DELETE:
               patch.addDelta(new DeleteDelta(orgChunk, revChunk));
               break;
            case INSERT:
               patch.addDelta(new InsertDelta(orgChunk, revChunk));
               break;
            case CHANGE:
               patch.addDelta(new ChangeDelta(orgChunk, revChunk));
         }

         startOriginal = change.endOriginal;
         startRevised = change.endRevised;
      }

      if (includeEquals && startOriginal < original.size()) {
         patch.addDelta(new EqualDelta(buildChunk(startOriginal, original.size(), original), buildChunk(startRevised, revised.size(), revised)));
      }

      return patch;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda lambda) {
      switch (lambda.getImplMethodName()) {
         case "lambda$static$5696245$1":
            if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("com/github/difflib/patch/ConflictOutput") && lambda.getFunctionalInterfaceMethodName().equals("processConflict") && lambda.getFunctionalInterfaceMethodSignature().equals("(Lcom/github/difflib/patch/VerifyChunk;Lcom/github/difflib/patch/AbstractDelta;Ljava/util/List;)V") && lambda.getImplClass().equals("com/github/difflib/patch/Patch") && lambda.getImplMethodSignature().equals("(Lcom/github/difflib/patch/VerifyChunk;Lcom/github/difflib/patch/AbstractDelta;Ljava/util/List;)V")) {
               return (ConflictOutput)(verifyChunk, delta, result) -> {
                  if (result.size() <= delta.getSource().getPosition()) {
                     throw new UnsupportedOperationException("Not supported yet.");
                  } else {
                     List<String> orgData = new ArrayList();

                     for(int i = 0; i < delta.getSource().size(); ++i) {
                        orgData.add(result.get(delta.getSource().getPosition()));
                        result.remove(delta.getSource().getPosition());
                     }

                     orgData.add(0, "<<<<<< HEAD");
                     orgData.add("======");
                     orgData.addAll(delta.getSource().getLines());
                     orgData.add(">>>>>>> PATCH");
                     result.addAll(delta.getSource().getPosition(), orgData);
                  }
               };
            }
            break;
         case "lambda$new$e23a5734$1":
            if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("com/github/difflib/patch/ConflictOutput") && lambda.getFunctionalInterfaceMethodName().equals("processConflict") && lambda.getFunctionalInterfaceMethodSignature().equals("(Lcom/github/difflib/patch/VerifyChunk;Lcom/github/difflib/patch/AbstractDelta;Ljava/util/List;)V") && lambda.getImplClass().equals("com/github/difflib/patch/Patch") && lambda.getImplMethodSignature().equals("(Lcom/github/difflib/patch/VerifyChunk;Lcom/github/difflib/patch/AbstractDelta;Ljava/util/List;)V")) {
               return (ConflictOutput)(verifyChunk, delta, result) -> {
                  throw new PatchFailedException("could not apply patch due to " + verifyChunk.toString());
               };
            }
      }

      throw new IllegalArgumentException("Invalid lambda deserialization");
   }

   private static class PatchApplyingContext {
      public final List result;
      public final int maxFuzz;
      public int lastPatchEnd;
      public int currentFuzz;
      public int defaultPosition;
      public boolean beforeOutRange;
      public boolean afterOutRange;

      private PatchApplyingContext(List result, int maxFuzz) {
         this.lastPatchEnd = -1;
         this.currentFuzz = 0;
         this.beforeOutRange = false;
         this.afterOutRange = false;
         this.result = result;
         this.maxFuzz = maxFuzz;
      }
   }
}
