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
import java.util.function.Consumer;

public class MyersDiffWithLinearSpace implements DiffAlgorithmI {
   private final BiPredicate equalizer;

   public MyersDiffWithLinearSpace() {
      this.equalizer = Object::equals;
   }

   public MyersDiffWithLinearSpace(BiPredicate equalizer) {
      Objects.requireNonNull(equalizer, "equalizer must not be null");
      this.equalizer = equalizer;
   }

   public List computeDiff(List source, List target, DiffAlgorithmListener progress) {
      Objects.requireNonNull(source, "source list must not be null");
      Objects.requireNonNull(target, "target list must not be null");
      if (progress != null) {
         progress.diffStart();
      }

      MyersDiffWithLinearSpace<T>.DiffData data = new DiffData(source, target);
      int maxIdx = source.size() + target.size();
      this.buildScript(data, 0, source.size(), 0, target.size(), (idx) -> {
         if (progress != null) {
            progress.diffStep(idx, maxIdx);
         }

      });
      if (progress != null) {
         progress.diffEnd();
      }

      return data.script;
   }

   private void buildScript(DiffData data, int start1, int end1, int start2, int end2, Consumer progress) {
      if (progress != null) {
         progress.accept((end1 - start1) / 2 + (end2 - start2) / 2);
      }

      MyersDiffWithLinearSpace<T>.Snake middle = this.getMiddleSnake(data, start1, end1, start2, end2);
      if (middle == null || middle.start == end1 && middle.diag == end1 - end2 || middle.end == start1 && middle.diag == start1 - start2) {
         int i = start1;
         int j = start2;

         while(i < end1 || j < end2) {
            if (i < end1 && j < end2 && this.equalizer.test(data.source.get(i), data.target.get(j))) {
               ++i;
               ++j;
            } else if (end1 - start1 > end2 - start2) {
               if (!data.script.isEmpty() && ((Change)data.script.get(data.script.size() - 1)).endOriginal == i && ((Change)data.script.get(data.script.size() - 1)).deltaType == DeltaType.DELETE) {
                  data.script.set(data.script.size() - 1, ((Change)data.script.get(data.script.size() - 1)).withEndOriginal(i + 1));
               } else {
                  data.script.add(new Change(DeltaType.DELETE, i, i + 1, j, j));
               }

               ++i;
            } else {
               if (!data.script.isEmpty() && ((Change)data.script.get(data.script.size() - 1)).endRevised == j && ((Change)data.script.get(data.script.size() - 1)).deltaType == DeltaType.INSERT) {
                  data.script.set(data.script.size() - 1, ((Change)data.script.get(data.script.size() - 1)).withEndRevised(j + 1));
               } else {
                  data.script.add(new Change(DeltaType.INSERT, i, i, j, j + 1));
               }

               ++j;
            }
         }
      } else {
         this.buildScript(data, start1, middle.start, start2, middle.start - middle.diag, progress);
         this.buildScript(data, middle.end, end1, middle.end - middle.diag, end2, progress);
      }

   }

   private Snake getMiddleSnake(DiffData data, int start1, int end1, int start2, int end2) {
      int m = end1 - start1;
      int n = end2 - start2;
      if (m != 0 && n != 0) {
         int delta = m - n;
         int sum = n + m;
         int offset = (sum % 2 == 0 ? sum : sum + 1) / 2;
         data.vDown[1 + offset] = start1;
         data.vUp[1 + offset] = end1 + 1;

         for(int d = 0; d <= offset; ++d) {
            for(int k = -d; k <= d; k += 2) {
               int i = k + offset;
               if (k != -d && (k == d || data.vDown[i - 1] >= data.vDown[i + 1])) {
                  data.vDown[i] = data.vDown[i - 1] + 1;
               } else {
                  data.vDown[i] = data.vDown[i + 1];
               }

               int x = data.vDown[i];

               for(int y = x - start1 + start2 - k; x < end1 && y < end2 && this.equalizer.test(data.source.get(x), data.target.get(y)); ++y) {
                  ++x;
                  data.vDown[i] = x;
               }

               if (delta % 2 != 0 && delta - d <= k && k <= delta + d && data.vUp[i - delta] <= data.vDown[i]) {
                  return this.buildSnake(data, data.vUp[i - delta], k + start1 - start2, end1, end2);
               }
            }

            for(int k = delta - d; k <= delta + d; k += 2) {
               int i = k + offset - delta;
               if (k != delta - d && (k == delta + d || data.vUp[i + 1] > data.vUp[i - 1])) {
                  data.vUp[i] = data.vUp[i - 1];
               } else {
                  data.vUp[i] = data.vUp[i + 1] - 1;
               }

               int x = data.vUp[i] - 1;

               for(int y = x - start1 + start2 - k; x >= start1 && y >= start2 && this.equalizer.test(data.source.get(x), data.target.get(y)); --y) {
                  data.vUp[i] = x--;
               }

               if (delta % 2 == 0 && -d <= k && k <= d && data.vUp[i] <= data.vDown[i + delta]) {
                  return this.buildSnake(data, data.vUp[i], k + start1 - start2, end1, end2);
               }
            }
         }

         throw new IllegalStateException("could not find a diff path");
      } else {
         return null;
      }
   }

   private Snake buildSnake(DiffData data, int start, int diag, int end1, int end2) {
      int end;
      for(end = start; end - diag < end2 && end < end1 && this.equalizer.test(data.source.get(end), data.target.get(end - diag)); ++end) {
      }

      return new Snake(start, end, diag);
   }

   public static DiffAlgorithmFactory factory() {
      return new DiffAlgorithmFactory() {
         public DiffAlgorithmI create() {
            return new MyersDiffWithLinearSpace();
         }

         public DiffAlgorithmI create(BiPredicate equalizer) {
            return new MyersDiffWithLinearSpace(equalizer);
         }
      };
   }

   private class DiffData {
      final int size;
      final int[] vDown;
      final int[] vUp;
      final List script;
      final List source;
      final List target;

      public DiffData(List source, List target) {
         this.source = source;
         this.target = target;
         this.size = source.size() + target.size() + 2;
         this.vDown = new int[this.size];
         this.vUp = new int[this.size];
         this.script = new ArrayList();
      }
   }

   private class Snake {
      final int start;
      final int end;
      final int diag;

      public Snake(int start, int end, int diag) {
         this.start = start;
         this.end = end;
         this.diag = diag;
      }
   }
}
