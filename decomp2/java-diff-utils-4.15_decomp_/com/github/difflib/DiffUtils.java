package com.github.difflib;

import com.github.difflib.algorithm.DiffAlgorithmFactory;
import com.github.difflib.algorithm.DiffAlgorithmI;
import com.github.difflib.algorithm.DiffAlgorithmListener;
import com.github.difflib.algorithm.myers.MyersDiff;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public final class DiffUtils {
   static DiffAlgorithmFactory DEFAULT_DIFF = MyersDiff.factory();

   public static void withDefaultDiffAlgorithmFactory(DiffAlgorithmFactory factory) {
      DEFAULT_DIFF = factory;
   }

   public static Patch diff(List original, List revised, DiffAlgorithmListener progress) {
      return diff(original, revised, DEFAULT_DIFF.create(), progress);
   }

   public static Patch diff(List original, List revised) {
      return diff(original, revised, DEFAULT_DIFF.create(), (DiffAlgorithmListener)null);
   }

   public static Patch diff(List original, List revised, boolean includeEqualParts) {
      return diff(original, revised, DEFAULT_DIFF.create(), (DiffAlgorithmListener)null, includeEqualParts);
   }

   public static Patch diff(String sourceText, String targetText, DiffAlgorithmListener progress) {
      return diff(Arrays.asList(sourceText.split("\n")), Arrays.asList(targetText.split("\n")), progress);
   }

   public static Patch diff(List source, List target, BiPredicate equalizer) {
      return equalizer != null ? diff(source, target, DEFAULT_DIFF.create(equalizer)) : diff((List)source, (List)target, (DiffAlgorithmI)(new MyersDiff()));
   }

   public static Patch diff(List original, List revised, DiffAlgorithmI algorithm, DiffAlgorithmListener progress) {
      return diff(original, revised, algorithm, progress, false);
   }

   public static Patch diff(List original, List revised, DiffAlgorithmI algorithm, DiffAlgorithmListener progress, boolean includeEqualParts) {
      Objects.requireNonNull(original, "original must not be null");
      Objects.requireNonNull(revised, "revised must not be null");
      Objects.requireNonNull(algorithm, "algorithm must not be null");
      return Patch.generate(original, revised, algorithm.computeDiff(original, revised, progress), includeEqualParts);
   }

   public static Patch diff(List original, List revised, DiffAlgorithmI algorithm) {
      return diff(original, revised, algorithm, (DiffAlgorithmListener)null);
   }

   public static Patch diffInline(String original, String revised) {
      List<String> origList = new ArrayList();
      List<String> revList = new ArrayList();
      char[] var4 = original.toCharArray();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Character character = var4[var6];
         origList.add(character.toString());
      }

      var4 = revised.toCharArray();
      var5 = var4.length;

      for(int var12 = 0; var12 < var5; ++var12) {
         Character character = var4[var12];
         revList.add(character.toString());
      }

      Patch<String> patch = diff(origList, revList);

      for(AbstractDelta delta : patch.getDeltas()) {
         delta.getSource().setLines(compressLines(delta.getSource().getLines(), ""));
         delta.getTarget().setLines(compressLines(delta.getTarget().getLines(), ""));
      }

      return patch;
   }

   public static List patch(List original, Patch patch) throws PatchFailedException {
      return patch.applyTo(original);
   }

   public static List unpatch(List revised, Patch patch) {
      return patch.restore(revised);
   }

   private static List compressLines(List lines, String delimiter) {
      return lines.isEmpty() ? Collections.emptyList() : Collections.singletonList(String.join(delimiter, lines));
   }

   private DiffUtils() {
   }
}
