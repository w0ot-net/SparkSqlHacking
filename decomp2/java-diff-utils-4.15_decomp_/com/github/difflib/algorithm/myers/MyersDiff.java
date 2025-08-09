package com.github.difflib.algorithm.myers;

import com.github.difflib.algorithm.Change;
import com.github.difflib.algorithm.DiffAlgorithmFactory;
import com.github.difflib.algorithm.DiffAlgorithmI;
import com.github.difflib.algorithm.DiffAlgorithmListener;
import com.github.difflib.patch.DeltaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public final class MyersDiff implements DiffAlgorithmI {
   private final BiPredicate equalizer;

   public MyersDiff() {
      this.equalizer = Object::equals;
   }

   public MyersDiff(BiPredicate equalizer) {
      Objects.requireNonNull(equalizer, "equalizer must not be null");
      this.equalizer = equalizer;
   }

   public List computeDiff(List source, List target, DiffAlgorithmListener progress) {
      Objects.requireNonNull(source, "source list must not be null");
      Objects.requireNonNull(target, "target list must not be null");
      if (progress != null) {
         progress.diffStart();
      }

      PathNode path = this.buildPath(source, target, progress);
      List<Change> result = this.buildRevision(path, source, target);
      if (progress != null) {
         progress.diffEnd();
      }

      return result;
   }

   private PathNode buildPath(List orig, List rev, DiffAlgorithmListener progress) {
      Objects.requireNonNull(orig, "original sequence is null");
      Objects.requireNonNull(rev, "revised sequence is null");
      int N = orig.size();
      int M = rev.size();
      int MAX = N + M + 1;
      int size = 1 + 2 * MAX;
      int middle = size / 2;
      PathNode[] diagonal = new PathNode[size];
      diagonal[middle + 1] = new PathNode(0, -1, true, true, (PathNode)null);

      for(int d = 0; d < MAX; ++d) {
         if (progress != null) {
            progress.diffStep(d, MAX);
         }

         for(int k = -d; k <= d; k += 2) {
            int kmiddle = middle + k;
            int kplus = kmiddle + 1;
            int kminus = kmiddle - 1;
            PathNode prev;
            int i;
            if (k != -d && (k == d || diagonal[kminus].i >= diagonal[kplus].i)) {
               i = diagonal[kminus].i + 1;
               prev = diagonal[kminus];
            } else {
               i = diagonal[kplus].i;
               prev = diagonal[kplus];
            }

            diagonal[kminus] = null;
            int j = i - k;

            PathNode node;
            for(node = new PathNode(i, j, false, false, prev); i < N && j < M && this.equalizer.test(orig.get(i), rev.get(j)); ++j) {
               ++i;
            }

            if (i != node.i) {
               node = new PathNode(i, j, true, false, node);
            }

            diagonal[kmiddle] = node;
            if (i >= N && j >= M) {
               return diagonal[kmiddle];
            }
         }

         diagonal[middle + d - 1] = null;
      }

      throw new IllegalStateException("could not find a diff path");
   }

   private List buildRevision(PathNode actualPath, List orig, List rev) {
      Objects.requireNonNull(actualPath, "path is null");
      Objects.requireNonNull(orig, "original sequence is null");
      Objects.requireNonNull(rev, "revised sequence is null");
      PathNode path = actualPath;
      List<Change> changes = new ArrayList();
      if (actualPath.isSnake()) {
         path = actualPath.prev;
      }

      while(path != null && path.prev != null && path.prev.j >= 0) {
         if (path.isSnake()) {
            throw new IllegalStateException("bad diffpath: found snake when looking for diff");
         }

         int i = path.i;
         int j = path.j;
         path = path.prev;
         int ianchor = path.i;
         int janchor = path.j;
         if (ianchor == i && janchor != j) {
            changes.add(new Change(DeltaType.INSERT, ianchor, i, janchor, j));
         } else if (ianchor != i && janchor == j) {
            changes.add(new Change(DeltaType.DELETE, ianchor, i, janchor, j));
         } else {
            changes.add(new Change(DeltaType.CHANGE, ianchor, i, janchor, j));
         }

         if (path.isSnake()) {
            path = path.prev;
         }
      }

      return changes;
   }

   public static DiffAlgorithmFactory factory() {
      return new DiffAlgorithmFactory() {
         public DiffAlgorithmI create() {
            return new MyersDiff();
         }

         public DiffAlgorithmI create(BiPredicate equalizer) {
            return new MyersDiff(equalizer);
         }
      };
   }
}
