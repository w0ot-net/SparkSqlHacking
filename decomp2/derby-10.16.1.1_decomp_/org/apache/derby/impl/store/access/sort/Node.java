package org.apache.derby.impl.store.access.sort;

import org.apache.derby.iapi.types.DataValueDescriptor;

final class Node {
   public int balance;
   public Node leftLink;
   public Node rightLink;
   public DataValueDescriptor[] key;
   public int id;
   public Node dupChain;
   public int aux;

   public Node(int var1) {
      this.id = var1;
      this.reset();
   }

   public void reset() {
      this.balance = 0;
      this.leftLink = null;
      this.rightLink = null;
      this.key = null;
      this.dupChain = null;
      this.aux = 0;
   }

   public Node link(int var1) {
      return var1 < 0 ? this.leftLink : this.rightLink;
   }

   public void setLink(int var1, Node var2) {
      if (var1 < 0) {
         this.leftLink = var2;
      } else {
         this.rightLink = var2;
      }

   }

   DataValueDescriptor[] getKey() {
      return this.key;
   }

   public String toString() {
      return null;
   }
}
