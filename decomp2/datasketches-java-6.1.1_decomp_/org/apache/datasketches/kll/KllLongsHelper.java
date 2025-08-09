package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Random;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;

final class KllLongsHelper {
   static long[] createItemsArray(long item, long weight) {
      int itemsArrLen = Long.bitCount(weight);
      long[] itemsArr = new long[itemsArrLen];
      Arrays.fill(itemsArr, item);
      return itemsArr;
   }

   static void compressWhileUpdatingSketch(KllLongsSketch lngSk) {
      int level = KllHelper.findLevelToCompact(lngSk.getK(), lngSk.getM(), lngSk.getNumLevels(), lngSk.levelsArr);
      if (level == lngSk.getNumLevels() - 1) {
         KllHelper.addEmptyTopLevelToCompletelyFullSketch(lngSk);
      }

      int[] myLevelsArr = lngSk.levelsArr;
      int rawBeg = myLevelsArr[level];
      int rawEnd = myLevelsArr[level + 1];
      int popAbove = myLevelsArr[level + 2] - rawEnd;
      int rawPop = rawEnd - rawBeg;
      boolean oddPop = Util.isOdd((long)rawPop);
      int adjBeg = oddPop ? rawBeg + 1 : rawBeg;
      int adjPop = oddPop ? rawPop - 1 : rawPop;
      int halfAdjPop = adjPop / 2;
      long[] myLongItemsArray = lngSk.getLongItemsArray();
      if (level == 0) {
         Arrays.sort(myLongItemsArray, adjBeg, adjBeg + adjPop);
      }

      if (popAbove == 0) {
         randomlyHalveUpLongs(myLongItemsArray, adjBeg, adjPop, KllSketch.random);
      } else {
         randomlyHalveDownLongs(myLongItemsArray, adjBeg, adjPop, KllSketch.random);
         mergeSortedLongArrays(myLongItemsArray, adjBeg, halfAdjPop, myLongItemsArray, rawEnd, popAbove, myLongItemsArray, adjBeg + halfAdjPop);
      }

      int newIndex = myLevelsArr[level + 1] - halfAdjPop;
      lngSk.setLevelsArrayAt(level + 1, newIndex);
      if (oddPop) {
         lngSk.setLevelsArrayAt(level, myLevelsArr[level + 1] - 1);
         myLongItemsArray[myLevelsArr[level]] = myLongItemsArray[rawBeg];
      } else {
         lngSk.setLevelsArrayAt(level, myLevelsArr[level + 1]);
      }

      assert myLevelsArr[level] == rawBeg + halfAdjPop;

      if (level > 0) {
         int amount = rawBeg - myLevelsArr[0];
         System.arraycopy(myLongItemsArray, myLevelsArr[0], myLongItemsArray, myLevelsArr[0] + halfAdjPop, amount);
      }

      for(int lvl = 0; lvl < level; ++lvl) {
         newIndex = myLevelsArr[lvl] + halfAdjPop;
         lngSk.setLevelsArrayAt(lvl, newIndex);
      }

      lngSk.setLongItemsArray(myLongItemsArray);
   }

   static void mergeLongsImpl(KllLongsSketch mySketch, KllLongsSketch otherLngSk) {
      if (!otherLngSk.isEmpty()) {
         boolean myEmpty = mySketch.isEmpty();
         long myMin = mySketch.getMinItemInternal();
         long myMax = mySketch.getMaxItemInternal();
         int myMinK = mySketch.getMinK();
         long finalN = Math.addExact(mySketch.getN(), otherLngSk.getN());
         int otherNumLevels = otherLngSk.getNumLevels();
         int[] otherLevelsArr = otherLngSk.levelsArr;
         long[] otherLongItemsArray;
         if (otherLngSk.isCompactSingleItem()) {
            KllLongsSketch.updateLong(mySketch, otherLngSk.getLongSingleItem());
            otherLongItemsArray = new long[0];
         } else {
            otherLongItemsArray = otherLngSk.getLongItemsArray();

            for(int i = otherLevelsArr[0]; i < otherLevelsArr[1]; ++i) {
               KllLongsSketch.updateLong(mySketch, otherLongItemsArray[i]);
            }
         }

         int myCurNumLevels = mySketch.getNumLevels();
         int[] myCurLevelsArr = mySketch.levelsArr;
         long[] myCurLongItemsArray = mySketch.getLongItemsArray();
         int myNewNumLevels = myCurNumLevels;
         int[] myNewLevelsArr = myCurLevelsArr;
         long[] myNewLongItemsArray = myCurLongItemsArray;
         if (otherNumLevels > 1 && !otherLngSk.isCompactSingleItem()) {
            int tmpSpaceNeeded = mySketch.getNumRetained() + KllHelper.getNumRetainedAboveLevelZero(otherNumLevels, otherLevelsArr);
            long[] workbuf = new long[tmpSpaceNeeded];
            int provisionalNumLevels = Math.max(myCurNumLevels, otherNumLevels);
            int ub = Math.max(KllHelper.ubOnNumLevels(finalN), provisionalNumLevels);
            int[] worklevels = new int[ub + 2];
            int[] outlevels = new int[ub + 2];
            populateLongWorkArrays(workbuf, worklevels, provisionalNumLevels, myCurNumLevels, myCurLevelsArr, myCurLongItemsArray, otherNumLevels, otherLevelsArr, otherLongItemsArray);
            int[] result = generalLongsCompress(mySketch.getK(), mySketch.getM(), provisionalNumLevels, workbuf, worklevels, workbuf, outlevels, mySketch.isLevelZeroSorted(), KllSketch.random);
            int targetItemCount = result[1];
            int curItemCount = result[2];
            myNewNumLevels = result[0];

            assert myNewNumLevels <= ub;

            myNewLongItemsArray = targetItemCount == myCurLongItemsArray.length ? myCurLongItemsArray : new long[targetItemCount];
            int freeSpaceAtBottom = targetItemCount - curItemCount;
            System.arraycopy(workbuf, outlevels[0], myNewLongItemsArray, freeSpaceAtBottom, curItemCount);
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
               WritableMemory wmem = KllHelper.memorySpaceMgmt(mySketch, myNewLevelsArr.length, myNewLongItemsArray.length);
               mySketch.setWritableMemory(wmem);
            }
         }

         mySketch.setN(finalN);
         if (otherLngSk.isEstimationMode()) {
            mySketch.setMinK(Math.min(myMinK, otherLngSk.getMinK()));
         }

         mySketch.setNumLevels(myNewNumLevels);
         mySketch.setLevelsArray(myNewLevelsArr);
         mySketch.setLongItemsArray(myNewLongItemsArray);
         long otherMin = otherLngSk.getMinItemInternal();
         long otherMax = otherLngSk.getMaxItemInternal();
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

   private static void mergeSortedLongArrays(long[] bufA, int startA, int lenA, long[] bufB, int startB, int lenB, long[] bufC, int startC) {
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

   private static void randomlyHalveDownLongs(long[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + offset;

      for(int i = start; i < start + half_length; ++i) {
         buf[i] = buf[j];
         j += 2;
      }

   }

   private static void randomlyHalveUpLongs(long[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + length - 1 - offset;

      for(int i = start + length - 1; i >= start + half_length; --i) {
         buf[i] = buf[j];
         j -= 2;
      }

   }

   private static int[] generalLongsCompress(int k, int m, int numLevelsIn, long[] inBuf, int[] inLevels, long[] outBuf, int[] outLevels, boolean isLevelZeroSorted, Random random) {
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
               randomlyHalveUpLongs(inBuf, adjBeg, adjPop, random);
            } else {
               randomlyHalveDownLongs(inBuf, adjBeg, adjPop, random);
               mergeSortedLongArrays(inBuf, adjBeg, halfAdjPop, inBuf, rawLim, popAbove, inBuf, adjBeg + halfAdjPop);
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

   private static void populateLongWorkArrays(long[] workBuf, int[] workLevels, int provisionalNumLevels, int myCurNumLevels, int[] myCurLevelsArr, long[] myCurLongItemsArr, int otherNumLevels, int[] otherLevelsArr, long[] otherLongItemsArr) {
      workLevels[0] = 0;
      int selfPopZero = KllHelper.currentLevelSizeItems(0, myCurNumLevels, myCurLevelsArr);
      System.arraycopy(myCurLongItemsArr, myCurLevelsArr[0], workBuf, workLevels[0], selfPopZero);
      workLevels[1] = workLevels[0] + selfPopZero;

      for(int lvl = 1; lvl < provisionalNumLevels; ++lvl) {
         int selfPop = KllHelper.currentLevelSizeItems(lvl, myCurNumLevels, myCurLevelsArr);
         int otherPop = KllHelper.currentLevelSizeItems(lvl, otherNumLevels, otherLevelsArr);
         workLevels[lvl + 1] = workLevels[lvl] + selfPop + otherPop;

         assert selfPop >= 0 && otherPop >= 0;

         if (selfPop != 0 || otherPop != 0) {
            if (selfPop > 0 && otherPop == 0) {
               System.arraycopy(myCurLongItemsArr, myCurLevelsArr[lvl], workBuf, workLevels[lvl], selfPop);
            } else if (selfPop == 0 && otherPop > 0) {
               System.arraycopy(otherLongItemsArr, otherLevelsArr[lvl], workBuf, workLevels[lvl], otherPop);
            } else if (selfPop > 0 && otherPop > 0) {
               mergeSortedLongArrays(myCurLongItemsArr, myCurLevelsArr[lvl], selfPop, otherLongItemsArr, otherLevelsArr[lvl], otherPop, workBuf, workLevels[lvl]);
            }
         }
      }

   }
}
