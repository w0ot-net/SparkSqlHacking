package org.roaringbitmap;

class RunContainerCharRankIterator extends RunContainerCharIterator implements PeekableCharRankIterator {
   private int nextRank = 1;

   RunContainerCharRankIterator(RunContainer p) {
      super(p);
   }

   public char next() {
      ++this.nextRank;
      return super.next();
   }

   public int nextAsInt() {
      ++this.nextRank;
      return super.nextAsInt();
   }

   public void advanceIfNeeded(char minval) {
      while(true) {
         if (this.base + this.maxlength < minval) {
            this.nextRank += this.maxlength - this.le + 1;
            ++this.pos;
            this.le = 0;
            if (this.pos < this.parent.nbrruns) {
               this.maxlength = this.parent.getLength(this.pos);
               this.base = this.parent.getValue(this.pos);
               continue;
            }

            return;
         }

         if (this.base > minval) {
            return;
         }

         int nextLe = minval - this.base;
         this.nextRank += nextLe - this.le;
         this.le = nextLe;
         return;
      }
   }

   public int peekNextRank() {
      return this.nextRank;
   }

   public RunContainerCharRankIterator clone() {
      return (RunContainerCharRankIterator)super.clone();
   }
}
