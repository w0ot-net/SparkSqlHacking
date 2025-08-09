package org.apache.derby.impl.store.access.sort;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class SortBuffer {
   public static final int INSERT_OK = 0;
   public static final int INSERT_DUPLICATE = 1;
   public static final int INSERT_FULL = 2;
   private MergeSort sort;
   private NodeAllocator allocator = null;
   private Node head = null;
   private int height = 0;
   private DataValueDescriptor[] deletedKey;
   private boolean subtreeShrunk;
   private int nextAux;
   private int lastAux;

   void setNextAux(int var1) {
      this.nextAux = var1;
   }

   int getLastAux() {
      return this.lastAux;
   }

   SortBuffer(MergeSort var1) {
      this.sort = var1;
   }

   boolean init() {
      this.allocator = new NodeAllocator();
      boolean var1 = false;
      if (this.sort.sortBufferMin > 0) {
         var1 = this.allocator.init(this.sort.sortBufferMin, this.sort.sortBufferMax);
      } else {
         var1 = this.allocator.init(this.sort.sortBufferMax);
      }

      if (!var1) {
         this.allocator = null;
         return false;
      } else {
         this.reset();
         return true;
      }
   }

   void reset() {
      this.allocator.reset();
      this.head = this.allocator.newNode();
      this.height = 0;
   }

   void close() {
      if (this.allocator != null) {
         this.allocator.close();
      }

      this.allocator = null;
      this.height = 0;
      this.head = null;
   }

   void grow(int var1) {
      if (var1 > 0) {
         this.allocator.grow(var1);
      }

   }

   int capacity() {
      return this.allocator == null ? 0 : this.allocator.capacity() - 1;
   }

   int insert(DataValueDescriptor[] var1) throws StandardException {
      if (this.head.rightLink == null) {
         if (this.sort.sortObserver != null && (var1 = this.sort.sortObserver.insertNonDuplicateKey(var1)) == null) {
            return 1;
         } else {
            Node var14 = this.allocator.newNode();
            var14.key = var1;
            var14.aux = this.nextAux;
            this.head.rightLink = var14;
            this.height = 1;
            return 0;
         }
      } else {
         Node var7 = this.head;
         Node var6;
         Node var3 = var6 = this.head.rightLink;

         Node var12;
         while(true) {
            int var2 = this.sort.compare(var1, var3.key);
            if (var2 == 0) {
               if (this.sort.sortObserver != null && (var1 = this.sort.sortObserver.insertDuplicateKey(var1, var3.key)) == null) {
                  return 1;
               }

               var12 = this.allocator.newNode();
               if (var12 == null) {
                  return 2;
               }

               var12.aux = this.nextAux;
               var12.key = var1;
               var12.dupChain = var3.dupChain;
               var3.dupChain = var12;
               return 0;
            }

            if (var2 < 0) {
               var12 = var3.leftLink;
               if (var12 == null) {
                  var12 = this.allocator.newNode();
                  if (var12 == null) {
                     return 2;
                  }

                  var12.aux = this.nextAux;
                  var3.leftLink = var12;
                  break;
               }
            } else {
               var12 = var3.rightLink;
               if (var12 == null) {
                  var12 = this.allocator.newNode();
                  if (var12 == null) {
                     return 2;
                  }

                  var12.aux = this.nextAux;
                  var3.rightLink = var12;
                  break;
               }
            }

            if (var12.balance != 0) {
               var7 = var3;
               var6 = var12;
            }

            var3 = var12;
         }

         if (this.sort.sortObserver != null && (var1 = this.sort.sortObserver.insertNonDuplicateKey(var1)) == null) {
            return 1;
         } else {
            var12.key = var1;
            int var9 = this.sort.compare(var1, var6.key);
            Node var5;
            if (var9 < 0) {
               var5 = var3 = var6.leftLink;
            } else {
               var5 = var3 = var6.rightLink;
            }

            while(var3 != var12) {
               if (this.sort.compare(var1, var3.key) < 0) {
                  var3.balance = -1;
                  var3 = var3.leftLink;
               } else {
                  var3.balance = 1;
                  var3 = var3.rightLink;
               }
            }

            int var8 = var9 > 0 ? 1 : (var9 == 0 ? 0 : -1);
            if (var6.balance == 0) {
               var6.balance = var8;
               ++this.height;
               return 0;
            } else if (var6.balance == -var8) {
               var6.balance = 0;
               return 0;
            } else {
               if (var5.balance == var8) {
                  var3 = var5;
                  var6.setLink(var8, var5.link(-var8));
                  var5.setLink(-var8, var6);
                  var6.balance = 0;
                  var5.balance = 0;
               } else {
                  var3 = var5.link(-var8);
                  var5.setLink(-var8, var3.link(var8));
                  var3.setLink(var8, var5);
                  var6.setLink(var8, var3.link(-var8));
                  var3.setLink(-var8, var6);
                  if (var3.balance == var8) {
                     var6.balance = -var8;
                     var5.balance = 0;
                  } else if (var3.balance == 0) {
                     var6.balance = 0;
                     var5.balance = 0;
                  } else {
                     var6.balance = 0;
                     var5.balance = var8;
                  }

                  var3.balance = 0;
               }

               if (var6 == var7.rightLink) {
                  var7.rightLink = var3;
               } else {
                  var7.leftLink = var3;
               }

               return 0;
            }
         }
      }
   }

   DataValueDescriptor[] removeFirst() {
      if (this.head.rightLink == null) {
         return null;
      } else {
         this.head.rightLink = this.deleteLeftmost(this.head.rightLink);
         if (this.subtreeShrunk) {
            --this.height;
         }

         return this.deletedKey;
      }
   }

   private Node deleteLeftmost(Node var1) {
      if (var1.leftLink == null) {
         if (var1.dupChain != null) {
            Node var3 = var1.dupChain;
            this.deletedKey = var3.key;
            this.lastAux = var3.aux;
            var1.dupChain = var3.dupChain;
            this.allocator.freeNode(var3);
            Object var4 = null;
            this.subtreeShrunk = false;
            return var1;
         } else {
            this.deletedKey = var1.key;
            this.lastAux = var1.aux;
            this.subtreeShrunk = true;
            Node var2 = var1.rightLink;
            this.allocator.freeNode(var1);
            return var2;
         }
      } else {
         var1.leftLink = this.deleteLeftmost(var1.leftLink);
         if (!this.subtreeShrunk) {
            return var1;
         } else if (var1.balance == 1) {
            return this.rotateRight(var1);
         } else {
            if (var1.balance == -1) {
               var1.balance = 0;
               this.subtreeShrunk = true;
            } else {
               var1.balance = 1;
               this.subtreeShrunk = false;
            }

            return var1;
         }
      }
   }

   private Node rotateRight(Node var1) {
      Node var3 = var1.rightLink;
      if (var3.balance >= 0) {
         var1.rightLink = var3.leftLink;
         var3.leftLink = var1;
         if (var3.balance == 0) {
            var1.balance = 1;
            var3.balance = -1;
            this.subtreeShrunk = false;
         } else {
            var1.balance = 0;
            var3.balance = 0;
            this.subtreeShrunk = true;
         }

         return var3;
      } else {
         Node var4 = var3.leftLink;
         var1.rightLink = var4.leftLink;
         var4.leftLink = var1;
         var3.leftLink = var4.rightLink;
         var4.rightLink = var3;
         if (var4.balance == 1) {
            var1.balance = -1;
            var3.balance = 0;
         } else if (var4.balance == -1) {
            var1.balance = 0;
            var3.balance = 1;
         } else {
            var1.balance = 0;
            var3.balance = 0;
         }

         var4.balance = 0;
         this.subtreeShrunk = true;
         return var4;
      }
   }

   public void check() {
   }

   private String checkNode(Node var1) {
      return null;
   }

   private int depth(Node var1) {
      int var2 = 0;
      int var3 = 0;
      if (var1 == null) {
         return 0;
      } else {
         if (var1.leftLink != null) {
            var2 = this.depth(var1.leftLink);
         }

         if (var1.rightLink != null) {
            var3 = this.depth(var1.rightLink);
         }

         return var3 > var2 ? var3 + 1 : var2 + 1;
      }
   }

   public void print() {
      Node var1 = this.head.rightLink;
      int var10001 = this.height;
      System.out.println("tree height: " + var10001 + " root: " + (var1 == null ? -1 : var1.id));
      if (var1 != null) {
         this.printRecursive(var1, 0);
      }

   }

   private void printRecursive(Node var1, int var2) {
      if (var1.rightLink != null) {
         this.printRecursive(var1.rightLink, var2 + 1);
      }

      for(int var3 = 0; var3 < var2; ++var3) {
         System.out.print("       ");
      }

      System.out.println(var1);
      if (var1.leftLink != null) {
         this.printRecursive(var1.leftLink, var2 + 1);
      }

   }

   private void debug(String var1) {
   }
}
