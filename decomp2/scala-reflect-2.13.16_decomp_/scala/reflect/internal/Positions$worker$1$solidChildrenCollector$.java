package scala.reflect.internal;

import java.util.ArrayList;
import java.util.Arrays;

public class Positions$worker$1$solidChildrenCollector$ extends Positions.ChildSolidDescendantsCollector {
   private int size;
   private Trees.Tree[] childSolidDescendants;
   private final ArrayList spares;
   // $FF: synthetic field
   private final worker$1$ $outer;

   public Trees.Tree[] borrowArray() {
      Trees.Tree[] borrowed = this.childSolidDescendants;
      this.childSolidDescendants = this.spares.isEmpty() ? new Trees.Tree[32] : (Trees.Tree[])this.spares.remove(this.spares.size() - 1);
      this.clear();
      return borrowed;
   }

   public void spareArray(final Trees.Tree[] array) {
      this.spares.add(array);
   }

   public Trees.Tree child(final int i) {
      return this.childSolidDescendants[i];
   }

   public int collectedSize() {
      return this.size;
   }

   public Trees.Tree[] sortedArray() {
      if (this.size > 1) {
         Arrays.sort(this.childSolidDescendants, 0, this.size, this.$outer.scala$reflect$internal$Positions$worker$$$outer().scala$reflect$internal$Positions$$posStartOrdering());
      }

      return this.childSolidDescendants;
   }

   public void clear() {
      this.size = 0;
   }

   public void traverseSolidChild(final Trees.Tree t) {
      if (this.size == this.childSolidDescendants.length) {
         this.spareArray(this.childSolidDescendants);
         this.childSolidDescendants = (Trees.Tree[])Arrays.copyOf(this.childSolidDescendants, this.size << 1);
      }

      this.childSolidDescendants[this.size] = t;
      ++this.size;
   }

   public Positions$worker$1$solidChildrenCollector$(final worker$1$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
         this.size = 0;
         this.childSolidDescendants = new Trees.Tree[32];
         this.spares = new ArrayList();
      }
   }
}
