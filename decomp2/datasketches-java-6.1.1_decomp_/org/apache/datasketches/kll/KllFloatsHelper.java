package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Random;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;

final class KllFloatsHelper {
   static float[] createItemsArray(float item, long weight) {
      int itemsArrLen = Long.bitCount(weight);
      float[] itemsArr = new float[itemsArrLen];
      Arrays.fill(itemsArr, item);
      return itemsArr;
   }

   static void compressWhileUpdatingSketch(KllFloatsSketch fltSk) {
      int level = KllHelper.findLevelToCompact(fltSk.getK(), fltSk.getM(), fltSk.getNumLevels(), fltSk.levelsArr);
      if (level == fltSk.getNumLevels() - 1) {
         KllHelper.addEmptyTopLevelToCompletelyFullSketch(fltSk);
      }

      int[] myLevelsArr = fltSk.levelsArr;
      int rawBeg = myLevelsArr[level];
      int rawEnd = myLevelsArr[level + 1];
      int popAbove = myLevelsArr[level + 2] - rawEnd;
      int rawPop = rawEnd - rawBeg;
      boolean oddPop = Util.isOdd((long)rawPop);
      int adjBeg = oddPop ? rawBeg + 1 : rawBeg;
      int adjPop = oddPop ? rawPop - 1 : rawPop;
      int halfAdjPop = adjPop / 2;
      float[] myFloatItemsArr = fltSk.getFloatItemsArray();
      if (level == 0) {
         Arrays.sort(myFloatItemsArr, adjBeg, adjBeg + adjPop);
      }

      if (popAbove == 0) {
         randomlyHalveUpFloats(myFloatItemsArr, adjBeg, adjPop, KllSketch.random);
      } else {
         randomlyHalveDownFloats(myFloatItemsArr, adjBeg, adjPop, KllSketch.random);
         mergeSortedFloatArrays(myFloatItemsArr, adjBeg, halfAdjPop, myFloatItemsArr, rawEnd, popAbove, myFloatItemsArr, adjBeg + halfAdjPop);
      }

      int newIndex = myLevelsArr[level + 1] - halfAdjPop;
      fltSk.setLevelsArrayAt(level + 1, newIndex);
      if (oddPop) {
         fltSk.setLevelsArrayAt(level, myLevelsArr[level + 1] - 1);
         myFloatItemsArr[myLevelsArr[level]] = myFloatItemsArr[rawBeg];
      } else {
         fltSk.setLevelsArrayAt(level, myLevelsArr[level + 1]);
      }

      assert myLevelsArr[level] == rawBeg + halfAdjPop;

      if (level > 0) {
         int amount = rawBeg - myLevelsArr[0];
         System.arraycopy(myFloatItemsArr, myLevelsArr[0], myFloatItemsArr, myLevelsArr[0] + halfAdjPop, amount);
      }

      for(int lvl = 0; lvl < level; ++lvl) {
         newIndex = myLevelsArr[lvl] + halfAdjPop;
         fltSk.setLevelsArrayAt(lvl, newIndex);
      }

      fltSk.setFloatItemsArray(myFloatItemsArr);
   }

   static void mergeFloatImpl(KllFloatsSketch mySketch, KllFloatsSketch otherFltSk) {
      if (!otherFltSk.isEmpty()) {
         boolean myEmpty = mySketch.isEmpty();
         float myMin = mySketch.getMinItemInternal();
         float myMax = mySketch.getMaxItemInternal();
         int myMinK = mySketch.getMinK();
         long finalN = Math.addExact(mySketch.getN(), otherFltSk.getN());
         int otherNumLevels = otherFltSk.getNumLevels();
         int[] otherLevelsArr = otherFltSk.levelsArr;
         float[] otherFloatItemsArr;
         if (otherFltSk.isCompactSingleItem()) {
            KllFloatsSketch.updateFloat(mySketch, otherFltSk.getFloatSingleItem());
            otherFloatItemsArr = new float[0];
         } else {
            otherFloatItemsArr = otherFltSk.getFloatItemsArray();

            for(int i = otherLevelsArr[0]; i < otherLevelsArr[1]; ++i) {
               KllFloatsSketch.updateFloat(mySketch, otherFloatItemsArr[i]);
            }
         }

         int myCurNumLevels = mySketch.getNumLevels();
         int[] myCurLevelsArr = mySketch.levelsArr;
         float[] myCurFloatItemsArr = mySketch.getFloatItemsArray();
         int myNewNumLevels = myCurNumLevels;
         int[] myNewLevelsArr = myCurLevelsArr;
         float[] myNewFloatItemsArr = myCurFloatItemsArr;
         if (otherNumLevels > 1 && !otherFltSk.isCompactSingleItem()) {
            int tmpSpaceNeeded = mySketch.getNumRetained() + KllHelper.getNumRetainedAboveLevelZero(otherNumLevels, otherLevelsArr);
            float[] workbuf = new float[tmpSpaceNeeded];
            int provisionalNumLevels = Math.max(myCurNumLevels, otherNumLevels);
            int ub = Math.max(KllHelper.ubOnNumLevels(finalN), provisionalNumLevels);
            int[] worklevels = new int[ub + 2];
            int[] outlevels = new int[ub + 2];
            populateFloatWorkArrays(workbuf, worklevels, provisionalNumLevels, myCurNumLevels, myCurLevelsArr, myCurFloatItemsArr, otherNumLevels, otherLevelsArr, otherFloatItemsArr);
            int[] result = generalFloatsCompress(mySketch.getK(), mySketch.getM(), provisionalNumLevels, workbuf, worklevels, workbuf, outlevels, mySketch.isLevelZeroSorted(), KllSketch.random);
            int targetItemCount = result[1];
            int curItemCount = result[2];
            myNewNumLevels = result[0];

            assert myNewNumLevels <= ub;

            myNewFloatItemsArr = targetItemCount == myCurFloatItemsArr.length ? myCurFloatItemsArr : new float[targetItemCount];
            int freeSpaceAtBottom = targetItemCount - curItemCount;
            System.arraycopy(workbuf, outlevels[0], myNewFloatItemsArr, freeSpaceAtBottom, curItemCount);
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
               WritableMemory wmem = KllHelper.memorySpaceMgmt(mySketch, myNewLevelsArr.length, myNewFloatItemsArr.length);
               mySketch.setWritableMemory(wmem);
            }
         }

         mySketch.setN(finalN);
         if (otherFltSk.isEstimationMode()) {
            mySketch.setMinK(Math.min(myMinK, otherFltSk.getMinK()));
         }

         mySketch.setNumLevels(myNewNumLevels);
         mySketch.setLevelsArray(myNewLevelsArr);
         mySketch.setFloatItemsArray(myNewFloatItemsArr);
         float otherMin = otherFltSk.getMinItem();
         float otherMax = otherFltSk.getMaxItem();
         if (myEmpty) {
            mySketch.setMinItem(otherMin);
            mySketch.setMaxItem(otherMax);
         } else {
            mySketch.setMinItem(Math.min(myMin, otherMin));
            mySketch.setMaxItem(Math.max(myMax, otherMax));
         }

         assert KllHelper.sumTheSampleWeights(mySketch.getNumLevels(), mySketch.levelsArr) == mySketch.getN();

      }
   }

   private static void mergeSortedFloatArrays(float[] bufA, int startA, int lenA, float[] bufB, int startB, int lenB, float[] bufC, int startC) {
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

   private static void randomlyHalveDownFloats(float[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + offset;

      for(int i = start; i < start + half_length; ++i) {
         buf[i] = buf[j];
         j += 2;
      }

   }

   private static void randomlyHalveUpFloats(float[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + length - 1 - offset;

      for(int i = start + length - 1; i >= start + half_length; --i) {
         buf[i] = buf[j];
         j -= 2;
      }

   }

   private static int[] generalFloatsCompress(int k, int m, int numLevelsIn, float[] inBuf, int[] inLevels, float[] outBuf, int[] outLevels, boolean isLevelZeroSorted, Random random) {
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
               randomlyHalveUpFloats(inBuf, adjBeg, adjPop, random);
            } else {
               randomlyHalveDownFloats(inBuf, adjBeg, adjPop, random);
               mergeSortedFloatArrays(inBuf, adjBeg, halfAdjPop, inBuf, rawLim, popAbove, inBuf, adjBeg + halfAdjPop);
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

   private static void populateFloatWorkArrays(float[] workBuf, int[] workLevels, int provisionalNumLevels, int myCurNumLevels, int[] myCurLevelsArr, float[] myCurFloatItemsArr, int otherNumLevels, int[] otherLevelsArr, float[] otherFloatItemsArr) {
      workLevels[0] = 0;
      int selfPopZero = KllHelper.currentLevelSizeItems(0, myCurNumLevels, myCurLevelsArr);
      System.arraycopy(myCurFloatItemsArr, myCurLevelsArr[0], workBuf, workLevels[0], selfPopZero);
      workLevels[1] = workLevels[0] + selfPopZero;

      for(int lvl = 1; lvl < provisionalNumLevels; ++lvl) {
         int selfPop = KllHelper.currentLevelSizeItems(lvl, myCurNumLevels, myCurLevelsArr);
         int otherPop = KllHelper.currentLevelSizeItems(lvl, otherNumLevels, otherLevelsArr);
         workLevels[lvl + 1] = workLevels[lvl] + selfPop + otherPop;

         assert selfPop >= 0 && otherPop >= 0;

         if (selfPop != 0 || otherPop != 0) {
            if (selfPop > 0 && otherPop == 0) {
               System.arraycopy(myCurFloatItemsArr, myCurLevelsArr[lvl], workBuf, workLevels[lvl], selfPop);
            } else if (selfPop == 0 && otherPop > 0) {
               System.arraycopy(otherFloatItemsArr, otherLevelsArr[lvl], workBuf, workLevels[lvl], otherPop);
            } else if (selfPop > 0 && otherPop > 0) {
               mergeSortedFloatArrays(myCurFloatItemsArr, myCurLevelsArr[lvl], selfPop, otherFloatItemsArr, otherLevelsArr[lvl], otherPop, workBuf, workLevels[lvl]);
            }
         }
      }

   }
}
