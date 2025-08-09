package org.roaringbitmap.art;

import java.util.Iterator;
import org.roaringbitmap.Container;

public class ContainerIterator implements Iterator {
   private Containers containers;
   private Iterator containerArrIte;
   private Container[] currentSecondLevelArr;
   private int currentSecondLevelArrSize;
   private int currentSecondLevelArrIdx;
   private int currentFistLevelArrIdx;
   private boolean currentSecondLevelArrIteOver;
   private Container currentContainer;
   private boolean consumedCurrent;

   public ContainerIterator(Containers containers) {
      this.containers = containers;
      this.containerArrIte = containers.getContainerArrays().iterator();
      this.currentSecondLevelArrIteOver = true;
      this.consumedCurrent = true;
      this.currentFistLevelArrIdx = -1;
      this.currentSecondLevelArrIdx = 0;
      this.currentSecondLevelArrSize = 0;
   }

   public boolean hasNext() {
      boolean hasContainer = this.containers.getContainerSize() > 0L;
      if (!hasContainer) {
         return false;
      } else if (!this.consumedCurrent) {
         return true;
      } else {
         boolean foundOneContainer = false;

         while(this.currentSecondLevelArrIteOver && this.containerArrIte.hasNext()) {
            this.currentSecondLevelArr = (Container[])this.containerArrIte.next();
            ++this.currentFistLevelArrIdx;
            this.currentSecondLevelArrIdx = 0;

            for(this.currentSecondLevelArrSize = this.currentSecondLevelArr.length; this.currentSecondLevelArrIdx < this.currentSecondLevelArrSize; ++this.currentSecondLevelArrIdx) {
               Container container = this.currentSecondLevelArr[this.currentSecondLevelArrIdx];
               if (container != null) {
                  this.currentContainer = container;
                  this.consumedCurrent = false;
                  this.currentSecondLevelArrIteOver = false;
                  foundOneContainer = true;
                  ++this.currentSecondLevelArrIdx;
                  break;
               }
            }
         }

         if (!this.currentSecondLevelArrIteOver && !foundOneContainer) {
            while(this.currentSecondLevelArrIdx < this.currentSecondLevelArrSize) {
               Container container = this.currentSecondLevelArr[this.currentSecondLevelArrIdx];
               if (container != null) {
                  this.currentContainer = container;
                  this.consumedCurrent = false;
                  ++this.currentSecondLevelArrIdx;
                  foundOneContainer = true;
                  break;
               }

               ++this.currentSecondLevelArrIdx;
            }

            if (this.currentSecondLevelArrIdx == this.currentSecondLevelArrSize) {
               this.currentSecondLevelArrIteOver = true;
            }
         }

         return foundOneContainer;
      }
   }

   public Container next() {
      this.consumedCurrent = true;
      return this.currentContainer;
   }

   public long getCurrentContainerIdx() {
      int secondLevelArrIdx = this.currentSecondLevelArrIdx - 1;
      return Containers.toContainerIdx(this.currentFistLevelArrIdx, secondLevelArrIdx);
   }

   public void replace(Container container) {
      int secondLevelArrIdx = this.currentSecondLevelArrIdx - 1;
      this.containers.replace(this.currentFistLevelArrIdx, secondLevelArrIdx, container);
   }
}
