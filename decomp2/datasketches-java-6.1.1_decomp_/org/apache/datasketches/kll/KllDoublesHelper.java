package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Random;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;

final class KllDoublesHelper {
   static double[] createItemsArray(double item, long weight) {
      int itemsArrLen = Long.bitCount(weight);
      double[] itemsArr = new double[itemsArrLen];
      Arrays.fill(itemsArr, item);
      return itemsArr;
   }

   static void compressWhileUpdatingSketch(KllDoublesSketch dblSk) {
      int level = KllHelper.findLevelToCompact(dblSk.getK(), dblSk.getM(), dblSk.getNumLevels(), dblSk.levelsArr);
      if (level == dblSk.getNumLevels() - 1) {
         KllHelper.addEmptyTopLevelToCompletelyFullSketch(dblSk);
      }

      int[] myLevelsArr = dblSk.levelsArr;
      int rawBeg = myLevelsArr[level];
      int rawEnd = myLevelsArr[level + 1];
      int popAbove = myLevelsArr[level + 2] - rawEnd;
      int rawPop = rawEnd - rawBeg;
      boolean oddPop = Util.isOdd((long)rawPop);
      int adjBeg = oddPop ? rawBeg + 1 : rawBeg;
      int adjPop = oddPop ? rawPop - 1 : rawPop;
      int halfAdjPop = adjPop / 2;
      double[] myDoubleItemsArr = dblSk.getDoubleItemsArray();
      if (level == 0) {
         Arrays.sort(myDoubleItemsArr, adjBeg, adjBeg + adjPop);
      }

      if (popAbove == 0) {
         randomlyHalveUpDoubles(myDoubleItemsArr, adjBeg, adjPop, KllSketch.random);
      } else {
         randomlyHalveDownDoubles(myDoubleItemsArr, adjBeg, adjPop, KllSketch.random);
         mergeSortedDoubleArrays(myDoubleItemsArr, adjBeg, halfAdjPop, myDoubleItemsArr, rawEnd, popAbove, myDoubleItemsArr, adjBeg + halfAdjPop);
      }

      int newIndex = myLevelsArr[level + 1] - halfAdjPop;
      dblSk.setLevelsArrayAt(level + 1, newIndex);
      if (oddPop) {
         dblSk.setLevelsArrayAt(level, myLevelsArr[level + 1] - 1);
         myDoubleItemsArr[myLevelsArr[level]] = myDoubleItemsArr[rawBeg];
      } else {
         dblSk.setLevelsArrayAt(level, myLevelsArr[level + 1]);
      }

      assert myLevelsArr[level] == rawBeg + halfAdjPop;

      if (level > 0) {
         int amount = rawBeg - myLevelsArr[0];
         System.arraycopy(myDoubleItemsArr, myLevelsArr[0], myDoubleItemsArr, myLevelsArr[0] + halfAdjPop, amount);
      }

      for(int lvl = 0; lvl < level; ++lvl) {
         newIndex = myLevelsArr[lvl] + halfAdjPop;
         dblSk.setLevelsArrayAt(lvl, newIndex);
      }

      dblSk.setDoubleItemsArray(myDoubleItemsArr);
   }

   static void mergeDoubleImpl(KllDoublesSketch mySketch, KllDoublesSketch otherDblSk) {
      if (!otherDblSk.isEmpty()) {
         boolean myEmpty = mySketch.isEmpty();
         double myMin = mySketch.getMinItemInternal();
         double myMax = mySketch.getMaxItemInternal();
         int myMinK = mySketch.getMinK();
         long finalN = Math.addExact(mySketch.getN(), otherDblSk.getN());
         int otherNumLevels = otherDblSk.getNumLevels();
         int[] otherLevelsArr = otherDblSk.levelsArr;
         double[] otherDoubleItemsArr;
         if (otherDblSk.isCompactSingleItem()) {
            KllDoublesSketch.updateDouble(mySketch, otherDblSk.getDoubleSingleItem());
            otherDoubleItemsArr = new double[0];
         } else {
            otherDoubleItemsArr = otherDblSk.getDoubleItemsArray();

            for(int i = otherLevelsArr[0]; i < otherLevelsArr[1]; ++i) {
               KllDoublesSketch.updateDouble(mySketch, otherDoubleItemsArr[i]);
            }
         }

         int myCurNumLevels = mySketch.getNumLevels();
         int[] myCurLevelsArr = mySketch.levelsArr;
         double[] myCurDoubleItemsArr = mySketch.getDoubleItemsArray();
         int myNewNumLevels = myCurNumLevels;
         int[] myNewLevelsArr = myCurLevelsArr;
         double[] myNewDoubleItemsArr = myCurDoubleItemsArr;
         if (otherNumLevels > 1 && !otherDblSk.isCompactSingleItem()) {
            int tmpSpaceNeeded = mySketch.getNumRetained() + KllHelper.getNumRetainedAboveLevelZero(otherNumLevels, otherLevelsArr);
            double[] workbuf = new double[tmpSpaceNeeded];
            int provisionalNumLevels = Math.max(myCurNumLevels, otherNumLevels);
            int ub = Math.max(KllHelper.ubOnNumLevels(finalN), provisionalNumLevels);
            int[] worklevels = new int[ub + 2];
            int[] outlevels = new int[ub + 2];
            populateDoubleWorkArrays(workbuf, worklevels, provisionalNumLevels, myCurNumLevels, myCurLevelsArr, myCurDoubleItemsArr, otherNumLevels, otherLevelsArr, otherDoubleItemsArr);
            int[] result = generalDoublesCompress(mySketch.getK(), mySketch.getM(), provisionalNumLevels, workbuf, worklevels, workbuf, outlevels, mySketch.isLevelZeroSorted(), KllSketch.random);
            int targetItemCount = result[1];
            int curItemCount = result[2];
            myNewNumLevels = result[0];

            assert myNewNumLevels <= ub;

            myNewDoubleItemsArr = targetItemCount == myCurDoubleItemsArr.length ? myCurDoubleItemsArr : new double[targetItemCount];
            int freeSpaceAtBottom = targetItemCount - curItemCount;
            System.arraycopy(workbuf, outlevels[0], myNewDoubleItemsArr, freeSpaceAtBottom, curItemCount);
            int theShift = freeSpaceAtBottom - outlevels[0];
            int finalLevelsArrLen;
            if (myCurLevelsArr.length < myNewNumLevels + 1) {
               finalLevelsArrLen = myNewNumLevels + 1;
            } else {
               finalLevelsArrLen = myCurLevelsArr.length;
            }

            myNewLevelsArr = new int[finalLevelsArrLen];

            for(int lvl = 0; lvl < myNewNumLevels + 1; ++lvl) {
               myNewLevelsArr[lvl] = outlevels[lvl] + theShift;
            }

            if (mySketch.getWritableMemory() != null) {
               WritableMemory wmem = KllHelper.memorySpaceMgmt(mySketch, myNewLevelsArr.length, myNewDoubleItemsArr.length);
               mySketch.setWritableMemory(wmem);
            }
         }

         mySketch.setN(finalN);
         if (otherDblSk.isEstimationMode()) {
            mySketch.setMinK(Math.min(myMinK, otherDblSk.getMinK()));
         }

         mySketch.setNumLevels(myNewNumLevels);
         mySketch.setLevelsArray(myNewLevelsArr);
         mySketch.setDoubleItemsArray(myNewDoubleItemsArr);
         double otherMin = otherDblSk.getMinItemInternal();
         double otherMax = otherDblSk.getMaxItemInternal();
         if (!Double.isNaN(myMin) && !myEmpty) {
            mySketch.setMinItem(Math.min(myMin, otherMin));
            mySketch.setMaxItem(Math.max(myMax, otherMax));
         } else {
            mySketch.setMinItem(otherMin);
            mySketch.setMaxItem(otherMax);
         }

         assert KllHelper.sumTheSampleWeights(mySketch.getNumLevels(), mySketch.levelsArr) == mySketch.getN();

      }
   }

   private static void mergeSortedDoubleArrays(double[] bufA, int startA, int lenA, double[] bufB, int startB, int lenB, double[] bufC, int startC) {
      int lenC = lenA + lenB;
      int limA = startA + lenA;
      int limB = startB + lenB;
      int limC = startC + lenC;
      int a = startA;
      int b = startB;

      for(int c = startC; c < limC; ++c) {
         if (a == limA) {
            bufC[c] = bufB[b];
            ++b;
         } else if (b == limB) {
            bufC[c] = bufA[a];
            ++a;
         } else if (bufA[a] < bufB[b]) {
            bufC[c] = bufA[a];
            ++a;
         } else {
            bufC[c] = bufB[b];
            ++b;
         }
      }

      assert a == limA;

      assert b == limB;

   }

   private static void randomlyHalveDownDoubles(double[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + offset;

      for(int i = start; i < start + half_length; ++i) {
         buf[i] = buf[j];
         j += 2;
      }

   }

   private static void randomlyHalveUpDoubles(double[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + length - 1 - offset;

      for(int i = start + length - 1; i >= start + half_length; --i) {
         buf[i] = buf[j];
         j -= 2;
      }

   }

   private static int[] generalDoublesCompress(int k, int m, int numLevelsIn, double[] inBuf, int[] inLevels, double[] outBuf, int[] outLevels, boolean isLevelZeroSorted, Random random) {
      assert numLevelsIn > 0;

      int numLevels = numLevelsIn;
      int currentItemCount = inLevels[numLevelsIn] - inLevels[0];
      int targetItemCount = KllHelper.computeTotalItemCapacity(k, m, numLevelsIn);
      boolean doneYet = false;
      outLevels[0] = 0;
      int curLevel = -1;

      while(!doneYet) {
         ++curLevel;
         if (curLevel == numLevels - 1) {
            inLevels[curLevel + 2] = inLevels[curLevel + 1];
         }

         int rawBeg = inLevels[curLevel];
         int rawLim = inLevels[curLevel + 1];
         int rawPop = rawLim - rawBeg;
         if (currentItemCount >= targetItemCount && rawPop >= KllHelper.levelCapacity(k, numLevels, curLevel, m)) {
            int popAbove = inLevels[curLevel + 2] - rawLim;
            boolean oddPop = Util.isOdd((long)rawPop);
            int adjBeg = oddPop ? 1 + rawBeg : rawBeg;
            int adjPop = oddPop ? rawPop - 1 : rawPop;
            int halfAdjPop = adjPop / 2;
            if (oddPop) {
               outBuf[outLevels[curLevel]] = inBuf[rawBeg];
               outLevels[curLevel + 1] = outLevels[curLevel] + 1;
            } else {
               outLevels[curLevel + 1] = outLevels[curLevel];
            }

            if (curLevel == 0 && !isLevelZeroSorted) {
               Arrays.sort(inBuf, adjBeg, adjBeg + adjPop);
            }

            if (popAbove == 0) {
               randomlyHalveUpDoubles(inBuf, adjBeg, adjPop, random);
            } else {
               randomlyHalveDownDoubles(inBuf, adjBeg, adjPop, random);
               mergeSortedDoubleArrays(inBuf, adjBeg, halfAdjPop, inBuf, rawLim, popAbove, inBuf, adjBeg + halfAdjPop);
            }

            currentItemCount -= halfAdjPop;
            inLevels[curLevel + 1] -= halfAdjPop;
            if (curLevel == numLevels - 1) {
               ++numLevels;
               targetItemCount += KllHelper.levelCapacity(k, numLevels, 0, m);
            }
         } else {
            assert rawBeg >= outLevels[curLevel];

            System.arraycopy(inBuf, rawBeg, outBuf, outLevels[curLevel], rawPop);
            outLevels[curLevel + 1] = outLevels[curLevel] + rawPop;
         }

         if (curLevel == numLevels - 1) {
            doneYet = true;
         }
      }

      assert outLevels[numLevels] - outLevels[0] == currentItemCount;

      return new int[]{numLevels, targetItemCount, currentItemCount};
   }

   private static void populateDoubleWorkArrays(double[] workBuf, int[] workLevels, int provisionalNumLevels, int myCurNumLevels, int[] myCurLevelsArr, double[] myCurDoubleItemsArr, int otherNumLevels, int[] otherLevelsArr, double[] otherDoubleItemsArr) {
      workLevels[0] = 0;
      int selfPopZero = KllHelper.currentLevelSizeItems(0, myCurNumLevels, myCurLevelsArr);
      System.arraycopy(myCurDoubleItemsArr, myCurLevelsArr[0], workBuf, workLevels[0], selfPopZero);
      workLevels[1] = workLevels[0] + selfPopZero;

      for(int lvl = 1; lvl < provisionalNumLevels; ++lvl) {
         int selfPop = KllHelper.currentLevelSizeItems(lvl, myCurNumLevels, myCurLevelsArr);
         int otherPop = KllHelper.currentLevelSizeItems(lvl, otherNumLevels, otherLevelsArr);
         workLevels[lvl + 1] = workLevels[lvl] + selfPop + otherPop;

         assert selfPop >= 0 && otherPop >= 0;

         if (selfPop != 0 || otherPop != 0) {
            if (selfPop > 0 && otherPop == 0) {
               System.arraycopy(myCurDoubleItemsArr, myCurLevelsArr[lvl], workBuf, workLevels[lvl], selfPop);
            } else if (selfPop == 0 && otherPop > 0) {
               System.arraycopy(otherDoubleItemsArr, otherLevelsArr[lvl], workBuf, workLevels[lvl], otherPop);
            } else if (selfPop > 0 && otherPop > 0) {
               mergeSortedDoubleArrays(myCurDoubleItemsArr, myCurLevelsArr[lvl], selfPop, otherDoubleItemsArr, otherLevelsArr[lvl], otherPop, workBuf, workLevels[lvl]);
            }
         }
      }

   }
}
