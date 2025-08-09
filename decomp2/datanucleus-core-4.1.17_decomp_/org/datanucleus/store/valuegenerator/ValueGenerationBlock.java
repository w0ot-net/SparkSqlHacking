package org.datanucleus.store.valuegenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.datanucleus.util.StringUtils;

public class ValueGenerationBlock implements Serializable {
   private static final long serialVersionUID = -7180698168837344934L;
   private int nextIndex = 0;
   private final List valueList;

   public ValueGenerationBlock(Object[] values) {
      this.valueList = Arrays.asList(values);
   }

   public ValueGenerationBlock(List oid) {
      this.valueList = new ArrayList(oid);
   }

   public Object current() {
      if (this.nextIndex != 0 && this.nextIndex - 1 < this.valueList.size()) {
         return this.valueList.get(this.nextIndex - 1);
      } else {
         throw new NoSuchElementException();
      }
   }

   public Object next() {
      if (this.nextIndex >= this.valueList.size()) {
         throw new NoSuchElementException();
      } else {
         return this.valueList.get(this.nextIndex++);
      }
   }

   public boolean hasNext() {
      return this.nextIndex < this.valueList.size();
   }

   public void addBlock(ValueGenerationBlock block) {
      if (block != null) {
         while(block.hasNext()) {
            this.valueList.add(block.next());
         }

      }
   }

   public String toString() {
      return "ValueGenerationBlock : " + StringUtils.collectionToString(this.valueList);
   }
}
