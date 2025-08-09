package org.apache.commons.math3.geometry.partitioning.utilities;

/** @deprecated */
@Deprecated
public class AVLTree {
   private Node top = null;

   public void insert(Comparable element) {
      if (element != null) {
         if (this.top == null) {
            this.top = new Node(element, (Node)null);
         } else {
            this.top.insert(element);
         }
      }

   }

   public boolean delete(Comparable element) {
      if (element != null) {
         for(AVLTree<T>.Node node = this.getNotSmaller(element); node != null; node = node.getNext()) {
            if (node.element == element) {
               node.delete();
               return true;
            }

            if (node.element.compareTo(element) > 0) {
               return false;
            }
         }
      }

      return false;
   }

   public boolean isEmpty() {
      return this.top == null;
   }

   public int size() {
      return this.top == null ? 0 : this.top.size();
   }

   public Node getSmallest() {
      return this.top == null ? null : this.top.getSmallest();
   }

   public Node getLargest() {
      return this.top == null ? null : this.top.getLargest();
   }

   public Node getNotSmaller(Comparable reference) {
      AVLTree<T>.Node candidate = null;
      AVLTree<T>.Node node = this.top;

      while(node != null) {
         if (node.element.compareTo(reference) < 0) {
            if (node.right == null) {
               return candidate;
            }

            node = node.right;
         } else {
            candidate = node;
            if (node.left == null) {
               return node;
            }

            node = node.left;
         }
      }

      return null;
   }

   public Node getNotLarger(Comparable reference) {
      AVLTree<T>.Node candidate = null;
      AVLTree<T>.Node node = this.top;

      while(node != null) {
         if (node.element.compareTo(reference) > 0) {
            if (node.left == null) {
               return candidate;
            }

            node = node.left;
         } else {
            candidate = node;
            if (node.right == null) {
               return node;
            }

            node = node.right;
         }
      }

      return null;
   }

   private static enum Skew {
      LEFT_HIGH,
      RIGHT_HIGH,
      BALANCED;
   }

   public class Node {
      private Comparable element;
      private Node left;
      private Node right;
      private Node parent;
      private Skew skew;

      Node(Comparable element, Node parent) {
         this.element = element;
         this.left = null;
         this.right = null;
         this.parent = parent;
         this.skew = AVLTree.Skew.BALANCED;
      }

      public Comparable getElement() {
         return this.element;
      }

      int size() {
         return 1 + (this.left == null ? 0 : this.left.size()) + (this.right == null ? 0 : this.right.size());
      }

      Node getSmallest() {
         AVLTree<T>.Node node;
         for(node = this; node.left != null; node = node.left) {
         }

         return node;
      }

      Node getLargest() {
         AVLTree<T>.Node node;
         for(node = this; node.right != null; node = node.right) {
         }

         return node;
      }

      public Node getPrevious() {
         if (this.left != null) {
            AVLTree<T>.Node node = this.left.getLargest();
            if (node != null) {
               return node;
            }
         }

         for(AVLTree<T>.Node node = this; node.parent != null; node = node.parent) {
            if (node != node.parent.left) {
               return node.parent;
            }
         }

         return null;
      }

      public Node getNext() {
         if (this.right != null) {
            AVLTree<T>.Node node = this.right.getSmallest();
            if (node != null) {
               return node;
            }
         }

         for(AVLTree<T>.Node node = this; node.parent != null; node = node.parent) {
            if (node != node.parent.right) {
               return node.parent;
            }
         }

         return null;
      }

      boolean insert(Comparable newElement) {
         if (newElement.compareTo(this.element) < 0) {
            if (this.left == null) {
               this.left = AVLTree.this.new Node(newElement, this);
               return this.rebalanceLeftGrown();
            } else {
               return this.left.insert(newElement) ? this.rebalanceLeftGrown() : false;
            }
         } else if (this.right == null) {
            this.right = AVLTree.this.new Node(newElement, this);
            return this.rebalanceRightGrown();
         } else {
            return this.right.insert(newElement) ? this.rebalanceRightGrown() : false;
         }
      }

      public void delete() {
         if (this.parent == null && this.left == null && this.right == null) {
            this.element = null;
            AVLTree.this.top = null;
         } else {
            AVLTree<T>.Node node;
            AVLTree<T>.Node child;
            boolean leftShrunk;
            if (this.left == null && this.right == null) {
               node = this;
               this.element = null;
               leftShrunk = this == this.parent.left;
               child = null;
            } else {
               node = this.left != null ? this.left.getLargest() : this.right.getSmallest();
               this.element = node.element;
               leftShrunk = node == node.parent.left;
               child = node.left != null ? node.left : node.right;
            }

            node = node.parent;
            if (leftShrunk) {
               node.left = child;
            } else {
               node.right = child;
            }

            if (child != null) {
               child.parent = node;
            }

            while(true) {
               if (leftShrunk) {
                  if (!node.rebalanceLeftShrunk()) {
                     break;
                  }
               } else if (!node.rebalanceRightShrunk()) {
                  break;
               }

               if (node.parent == null) {
                  return;
               }

               leftShrunk = node == node.parent.left;
               node = node.parent;
            }
         }

      }

      private boolean rebalanceLeftGrown() {
         switch (this.skew) {
            case LEFT_HIGH:
               if (this.left.skew == AVLTree.Skew.LEFT_HIGH) {
                  this.rotateCW();
                  this.skew = AVLTree.Skew.BALANCED;
                  this.right.skew = AVLTree.Skew.BALANCED;
               } else {
                  Skew s = this.left.right.skew;
                  this.left.rotateCCW();
                  this.rotateCW();
                  switch (s) {
                     case LEFT_HIGH:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.RIGHT_HIGH;
                        break;
                     case RIGHT_HIGH:
                        this.left.skew = AVLTree.Skew.LEFT_HIGH;
                        this.right.skew = AVLTree.Skew.BALANCED;
                        break;
                     default:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.BALANCED;
                  }

                  this.skew = AVLTree.Skew.BALANCED;
               }

               return false;
            case RIGHT_HIGH:
               this.skew = AVLTree.Skew.BALANCED;
               return false;
            default:
               this.skew = AVLTree.Skew.LEFT_HIGH;
               return true;
         }
      }

      private boolean rebalanceRightGrown() {
         switch (this.skew) {
            case LEFT_HIGH:
               this.skew = AVLTree.Skew.BALANCED;
               return false;
            case RIGHT_HIGH:
               if (this.right.skew == AVLTree.Skew.RIGHT_HIGH) {
                  this.rotateCCW();
                  this.skew = AVLTree.Skew.BALANCED;
                  this.left.skew = AVLTree.Skew.BALANCED;
               } else {
                  Skew s = this.right.left.skew;
                  this.right.rotateCW();
                  this.rotateCCW();
                  switch (s) {
                     case LEFT_HIGH:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.RIGHT_HIGH;
                        break;
                     case RIGHT_HIGH:
                        this.left.skew = AVLTree.Skew.LEFT_HIGH;
                        this.right.skew = AVLTree.Skew.BALANCED;
                        break;
                     default:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.BALANCED;
                  }

                  this.skew = AVLTree.Skew.BALANCED;
               }

               return false;
            default:
               this.skew = AVLTree.Skew.RIGHT_HIGH;
               return true;
         }
      }

      private boolean rebalanceLeftShrunk() {
         switch (this.skew) {
            case LEFT_HIGH:
               this.skew = AVLTree.Skew.BALANCED;
               return true;
            case RIGHT_HIGH:
               if (this.right.skew == AVLTree.Skew.RIGHT_HIGH) {
                  this.rotateCCW();
                  this.skew = AVLTree.Skew.BALANCED;
                  this.left.skew = AVLTree.Skew.BALANCED;
                  return true;
               } else {
                  if (this.right.skew == AVLTree.Skew.BALANCED) {
                     this.rotateCCW();
                     this.skew = AVLTree.Skew.LEFT_HIGH;
                     this.left.skew = AVLTree.Skew.RIGHT_HIGH;
                     return false;
                  }

                  Skew s = this.right.left.skew;
                  this.right.rotateCW();
                  this.rotateCCW();
                  switch (s) {
                     case LEFT_HIGH:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.RIGHT_HIGH;
                        break;
                     case RIGHT_HIGH:
                        this.left.skew = AVLTree.Skew.LEFT_HIGH;
                        this.right.skew = AVLTree.Skew.BALANCED;
                        break;
                     default:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.BALANCED;
                  }

                  this.skew = AVLTree.Skew.BALANCED;
                  return true;
               }
            default:
               this.skew = AVLTree.Skew.RIGHT_HIGH;
               return false;
         }
      }

      private boolean rebalanceRightShrunk() {
         switch (this.skew) {
            case LEFT_HIGH:
               if (this.left.skew == AVLTree.Skew.LEFT_HIGH) {
                  this.rotateCW();
                  this.skew = AVLTree.Skew.BALANCED;
                  this.right.skew = AVLTree.Skew.BALANCED;
                  return true;
               } else {
                  if (this.left.skew == AVLTree.Skew.BALANCED) {
                     this.rotateCW();
                     this.skew = AVLTree.Skew.RIGHT_HIGH;
                     this.right.skew = AVLTree.Skew.LEFT_HIGH;
                     return false;
                  }

                  Skew s = this.left.right.skew;
                  this.left.rotateCCW();
                  this.rotateCW();
                  switch (s) {
                     case LEFT_HIGH:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.RIGHT_HIGH;
                        break;
                     case RIGHT_HIGH:
                        this.left.skew = AVLTree.Skew.LEFT_HIGH;
                        this.right.skew = AVLTree.Skew.BALANCED;
                        break;
                     default:
                        this.left.skew = AVLTree.Skew.BALANCED;
                        this.right.skew = AVLTree.Skew.BALANCED;
                  }

                  this.skew = AVLTree.Skew.BALANCED;
                  return true;
               }
            case RIGHT_HIGH:
               this.skew = AVLTree.Skew.BALANCED;
               return true;
            default:
               this.skew = AVLTree.Skew.LEFT_HIGH;
               return false;
         }
      }

      private void rotateCW() {
         T tmpElt = (T)this.element;
         this.element = this.left.element;
         this.left.element = tmpElt;
         AVLTree<T>.Node tmpNode = this.left;
         this.left = tmpNode.left;
         tmpNode.left = tmpNode.right;
         tmpNode.right = this.right;
         this.right = tmpNode;
         if (this.left != null) {
            this.left.parent = this;
         }

         if (this.right.right != null) {
            this.right.right.parent = this.right;
         }

      }

      private void rotateCCW() {
         T tmpElt = (T)this.element;
         this.element = this.right.element;
         this.right.element = tmpElt;
         AVLTree<T>.Node tmpNode = this.right;
         this.right = tmpNode.right;
         tmpNode.right = tmpNode.left;
         tmpNode.left = this.left;
         this.left = tmpNode;
         if (this.right != null) {
            this.right.parent = this;
         }

         if (this.left.left != null) {
            this.left.left.parent = this.left;
         }

      }
   }
}
