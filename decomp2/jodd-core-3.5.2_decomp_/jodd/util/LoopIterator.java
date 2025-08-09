package jodd.util;

public class LoopIterator {
   protected final int start;
   protected final int end;
   protected final int step;
   protected final int modulus;
   protected boolean first;
   protected boolean last;
   protected int value;
   protected int count;
   protected final boolean looping;

   public LoopIterator(int start, int end) {
      this(start, end, 1, 2);
   }

   public LoopIterator(int start, int end, int step) {
      this(start, end, step, 2);
   }

   public LoopIterator(int start, int end, int step, int modulus) {
      this.start = start;
      this.end = end;
      this.step = step;
      this.modulus = modulus;
      this.looping = step > 0 ? start <= end : start >= end;
   }

   public int getCount() {
      return this.count;
   }

   public int getIndex() {
      return this.count - 1;
   }

   public int getValue() {
      return this.value;
   }

   public boolean isEven() {
      return this.count % 2 == 0;
   }

   public boolean isOdd() {
      return this.count % 2 == 1;
   }

   public int modulus(int operand) {
      return this.count % operand;
   }

   public int getModulus() {
      return this.count % this.modulus;
   }

   public int getModulusValue() {
      return this.modulus;
   }

   public int getIndexModulus() {
      return (this.count - 1) % this.modulus;
   }

   public boolean isFirst() {
      return this.first;
   }

   public boolean isLast() {
      return this.last;
   }

   public boolean next() {
      if (!this.looping) {
         return false;
      } else if (this.last) {
         return false;
      } else {
         if (this.count == 0) {
            this.value = this.start;
            this.first = true;
         } else {
            this.value += this.step;
            this.first = false;
         }

         ++this.count;
         this.last = this.isLastIteration(this.value + this.step);
         return true;
      }
   }

   public void reset() {
      this.count = 0;
      this.last = false;
   }

   protected boolean isLastIteration(int value) {
      return this.step > 0 ? value > this.end : value < this.end;
   }

   public String toString() {
      return this.looping ? this.value + ":" + this.count + ':' + (this.first ? 'F' : '_') + ':' + (this.last ? 'L' : '_') + ':' + this.getModulus() : "N.A.";
   }
}
