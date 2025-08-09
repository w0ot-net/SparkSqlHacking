package scala.collection.immutable;

import scala.MatchError;
import scala.collection.Iterator$;

public final class TreeSeqMap$Ordering$Iterator {
   private int index = 0;
   private final Object[] buffer = new Object[33];

   private TreeSeqMap.Ordering pop() {
      --this.index;
      return (TreeSeqMap.Ordering)this.buffer[this.index];
   }

   private void push(final TreeSeqMap.Ordering x) {
      this.buffer[this.index] = x;
      ++this.index;
   }

   public boolean hasNext() {
      return this.index > 0;
   }

   public Object next() {
      while(this.hasNext()) {
         boolean var1 = false;
         TreeSeqMap$Ordering$Bin var2 = null;
         TreeSeqMap.Ordering var3 = this.pop();
         if (var3 instanceof TreeSeqMap$Ordering$Bin) {
            var1 = true;
            var2 = (TreeSeqMap$Ordering$Bin)var3;
            TreeSeqMap.Ordering var4 = var2.left();
            TreeSeqMap.Ordering right = var2.right();
            if (var4 instanceof TreeSeqMap$Ordering$Tip) {
               Object v = ((TreeSeqMap$Ordering$Tip)var4).value();
               this.push(right);
               return v;
            }
         }

         if (!var1) {
            if (var3 instanceof TreeSeqMap$Ordering$Tip) {
               return ((TreeSeqMap$Ordering$Tip)var3).value();
            }

            if (TreeSeqMap$Ordering$Zero$.MODULE$.equals(var3)) {
               throw new IllegalStateException("empty subtree not allowed");
            }

            throw new MatchError(var3);
         }

         TreeSeqMap.Ordering left = var2.left();
         TreeSeqMap.Ordering right = var2.right();
         this.push(right);
         this.push(left);
      }

      Iterator$ var10000 = Iterator$.MODULE$;
      return Iterator$.scala$collection$Iterator$$_empty.next();
   }

   public TreeSeqMap$Ordering$Iterator(final TreeSeqMap.Ordering it) {
      TreeSeqMap$Ordering$Zero$ var2 = TreeSeqMap$Ordering$Zero$.MODULE$;
      if (it != null) {
         if (it.equals(var2)) {
            return;
         }
      }

      this.push(it);
   }
}
