package org.apache.datasketches.kll;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import org.apache.datasketches.common.Util;

final class KllItemsHelper {
   static Object[] createItemsArray(Class clazz, Object item, long weight) {
      int itemsArrLen = Long.bitCount(weight);
      T[] itemsArr = (T[])((Object[])((Object[])Array.newInstance(clazz, itemsArrLen)));
      Arrays.fill(itemsArr, item);
      return itemsArr;
   }

   private static void compressWhileUpdatingSketch(KllItemsSketch itmSk) {
      int level = KllHelper.findLevelToCompact(itmSk.getK(), itmSk.getM(), itmSk.getNumLevels(), itmSk.levelsArr);
      if (level == itmSk.getNumLevels() - 1) {
         KllHelper.addEmptyTopLevelToCompletelyFullSketch(itmSk);
      }

      int[] myLevelsArr = itmSk.levelsArr;
      int rawBeg = myLevelsArr[level];
      int rawEnd = myLevelsArr[level + 1];
      int popAbove = myLevelsArr[level + 2] - rawEnd;
      int rawPop = rawEnd - rawBeg;
      boolean oddPop = Util.isOdd((long)rawPop);
      int adjBeg = oddPop ? rawBeg + 1 : rawBeg;
      int adjPop = oddPop ? rawPop - 1 : rawPop;
      int halfAdjPop = adjPop / 2;
      Object[] myItemsArr = itmSk.getTotalItemsArray();
      if (level == 0) {
         Arrays.sort(myItemsArr, adjBeg, adjBeg + adjPop, itmSk.comparator);
      }

      if (popAbove == 0) {
         randomlyHalveUpItems(myItemsArr, adjBeg, adjPop, KllSketch.random);
      } else {
         randomlyHalveDownItems(myItemsArr, adjBeg, adjPop, KllSketch.random);
         mergeSortedItemsArrays(myItemsArr, adjBeg, halfAdjPop, myItemsArr, rawEnd, popAbove, myItemsArr, adjBeg + halfAdjPop, itmSk.comparator);
      }

      int newIndex = myLevelsArr[level + 1] - halfAdjPop;
      itmSk.setLevelsArrayAt(level + 1, newIndex);
      if (oddPop) {
         itmSk.setLevelsArrayAt(level, myLevelsArr[level + 1] - 1);
         myItemsArr[myLevelsArr[level]] = myItemsArr[rawBeg];
      } else {
         itmSk.setLevelsArrayAt(level, myLevelsArr[level + 1]);
      }

      assert myLevelsArr[level] == rawBeg + halfAdjPop;

      if (level > 0) {
         int amount = rawBeg - myLevelsArr[0];
         System.arraycopy(myItemsArr, myLevelsArr[0], myItemsArr, myLevelsArr[0] + halfAdjPop, amount);
      }

      for(int lvl = 0; lvl < level; ++lvl) {
         newIndex = myLevelsArr[lvl] + halfAdjPop;
         itmSk.setLevelsArrayAt(lvl, newIndex);
      }

      itmSk.setItemsArray(myItemsArr);
   }

   static void mergeItemImpl(KllItemsSketch mySketch, KllItemsSketch otherItmSk, Comparator comp) {
      if (!otherItmSk.isEmpty()) {
         boolean myEmpty = mySketch.isEmpty();
         Object myMin = myEmpty ? null : mySketch.getMinItem();
         Object myMax = myEmpty ? null : mySketch.getMaxItem();
         int myMinK = mySketch.getMinK();
         long finalN = Math.addExact(mySketch.getN(), otherItmSk.getN());
         int otherNumLevels = otherItmSk.getNumLevels();
         int[] otherLevelsArr = otherItmSk.levelsArr;
         Object[] otherItemsArr;
         if (otherItmSk.isCompactSingleItem()) {
            updateItem(mySketch, otherItmSk.getSingleItem());
            otherItemsArr = new Object[0];
         } else {
            otherItemsArr = otherItmSk.getTotalItemsArray();

            for(int i = otherLevelsArr[0]; i < otherLevelsArr[1]; ++i) {
               updateItem(mySketch, otherItemsArr[i]);
            }
         }

         int myCurNumLevels = mySketch.getNumLevels();
         int[] myCurLevelsArr = mySketch.levelsArr;
         Object[] myCurItemsArr = mySketch.getTotalItemsArray();
         int myNewNumLevels = myCurNumLevels;
         int[] myNewLevelsArr = myCurLevelsArr;
         Object[] myNewItemsArr = myCurItemsArr;
         if (otherNumLevels > 1 && !otherItmSk.isCompactSingleItem()) {
            int tmpSpaceNeeded = mySketch.getNumRetained() + KllHelper.getNumRetainedAboveLevelZero(otherNumLevels, otherLevelsArr);
            Object[] workbuf = new Object[tmpSpaceNeeded];
            int provisionalNumLevels = Math.max(myCurNumLevels, otherNumLevels);
            int ub = Math.max(KllHelper.ubOnNumLevels(finalN), provisionalNumLevels);
            int[] worklevels = new int[ub + 2];
            int[] outlevels = new int[ub + 2];
            populateItemWorkArrays(workbuf, worklevels, provisionalNumLevels, myCurNumLevels, myCurLevelsArr, myCurItemsArr, otherNumLevels, otherLevelsArr, otherItemsArr, comp);
            int[] result = generalItemsCompress(mySketch.getK(), mySketch.getM(), provisionalNumLevels, workbuf, worklevels, workbuf, outlevels, mySketch.isLevelZeroSorted(), KllSketch.random, comp);
            int targetItemCount = result[1];
            int curItemCount = result[2];
            myNewNumLevels = result[0];

            assert myNewNumLevels <= ub;

            myNewItemsArr = targetItemCount == myCurItemsArr.length ? myCurItemsArr : new Object[targetItemCount];
            int freeSpaceAtBottom = targetItemCount - curItemCount;
            System.arraycopy(workbuf, outlevels[0], myNewItemsArr, freeSpaceAtBottom, curItemCount);
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
         }

         mySketch.setN(finalN);
         if (otherItmSk.isEstimationMode()) {
            mySketch.setMinK(Math.min(myMinK, otherItmSk.getMinK()));
         }

         mySketch.setNumLevels(myNewNumLevels);
         mySketch.setLevelsArray(myNewLevelsArr);
         mySketch.setItemsArray(myNewItemsArr);
         Object otherMin = otherItmSk.getMinItem();
         Object otherMax = otherItmSk.getMaxItem();
         if (myEmpty) {
            mySketch.setMinItem(otherMin);
            mySketch.setMaxItem(otherMax);
         } else {
            mySketch.setMinItem(Util.minT(myMin, otherMin, comp));
            mySketch.setMaxItem(Util.maxT(myMax, otherMax, comp));
         }

         assert KllHelper.sumTheSampleWeights(mySketch.getNumLevels(), mySketch.levelsArr) == mySketch.getN();

      }
   }

   private static void mergeSortedItemsArrays(Object[] bufA, int startA, int lenA, Object[] bufB, int startB, int lenB, Object[] bufC, int startC, Comparator comp) {
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
         } else if (Util.lt(bufA[a], bufB[b], comp)) {
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

   private static void randomlyHalveDownItems(Object[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + offset;

      for(int i = start; i < start + half_length; ++i) {
         buf[i] = buf[j];
         j += 2;
      }

   }

   private static void randomlyHalveUpItems(Object[] buf, int start, int length, Random random) {
      assert Util.isEven((long)length);

      int half_length = length / 2;
      int offset = random.nextInt(2);
      int j = start + length - 1 - offset;

      for(int i = start + length - 1; i >= start + half_length; --i) {
         buf[i] = buf[j];
         j -= 2;
      }

   }

   static void updateItem(KllItemsSketch itmSk, Object item) {
      itmSk.updateMinMax(item);
      int freeSpace = itmSk.levelsArr[0];

      assert freeSpace >= 0;

      if (freeSpace == 0) {
         compressWhileUpdatingSketch(itmSk);
         freeSpace = itmSk.levelsArr[0];

         assert freeSpace > 0;
      }

      itmSk.incN(1);
      itmSk.setLevelZeroSorted(false);
      int nextPos = freeSpace - 1;
      itmSk.setLevelsArrayAt(0, nextPos);
      itmSk.setItemsArrayAt(nextPos, item);
   }

   static void updateItem(KllItemsSketch itmSk, Object item, long weight) {
      if (weight < (long)itmSk.levelsArr[0]) {
         for(int i = 0; i < (int)weight; ++i) {
            updateItem(itmSk, item);
         }
      } else {
         itmSk.updateMinMax(item);
         KllHeapItemsSketch<T> tmpSk = new KllHeapItemsSketch(itmSk.getK(), 8, item, weight, itmSk.comparator, itmSk.serDe);
         itmSk.merge(tmpSk);
      }

   }

   private static int[] generalItemsCompress(int k, int m, int numLevelsIn, Object[] inBuf, int[] inLevels, Object[] outBuf, int[] outLevels, boolean isLevelZeroSorted, Random random, Comparator comp) {
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
               Arrays.sort(inBuf, adjBeg, adjBeg + adjPop, comp);
            }

            if (popAbove == 0) {
               randomlyHalveUpItems(inBuf, adjBeg, adjPop, random);
            } else {
               randomlyHalveDownItems(inBuf, adjBeg, adjPop, random);
               mergeSortedItemsArrays(inBuf, adjBeg, halfAdjPop, inBuf, rawLim, popAbove, inBuf, adjBeg + halfAdjPop, comp);
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

   private static void populateItemWorkArrays(Object[] workbuf, int[] worklevels, int provisionalNumLevels, int myCurNumLevels, int[] myCurLevelsArr, Object[] myCurItemsArr, int otherNumLevels, int[] otherLevelsArr, Object[] otherItemsArr, Comparator comp) {
      worklevels[0] = 0;
      int selfPopZero = KllHelper.currentLevelSizeItems(0, myCurNumLevels, myCurLevelsArr);
      System.arraycopy(myCurItemsArr, myCurLevelsArr[0], workbuf, worklevels[0], selfPopZero);
      worklevels[1] = worklevels[0] + selfPopZero;

      for(int lvl = 1; lvl < provisionalNumLevels; ++lvl) {
         int selfPop = KllHelper.currentLevelSizeItems(lvl, myCurNumLevels, myCurLevelsArr);
         int otherPop = KllHelper.currentLevelSizeItems(lvl, otherNumLevels, otherLevelsArr);
         worklevels[lvl + 1] = worklevels[lvl] + selfPop + otherPop;

         assert selfPop >= 0 && otherPop >= 0;

         if (selfPop != 0 || otherPop != 0) {
            if (selfPop > 0 && otherPop == 0) {
               System.arraycopy(myCurItemsArr, myCurLevelsArr[lvl], workbuf, worklevels[lvl], selfPop);
            } else if (selfPop == 0 && otherPop > 0) {
               System.arraycopy(otherItemsArr, otherLevelsArr[lvl], workbuf, worklevels[lvl], otherPop);
            } else if (selfPop > 0 && otherPop > 0) {
               mergeSortedItemsArrays(myCurItemsArr, myCurLevelsArr[lvl], selfPop, otherItemsArr, otherLevelsArr[lvl], otherPop, workbuf, worklevels[lvl], comp);
            }
         }
      }

   }
}
