package org.jline.reader.impl;

import java.util.function.Consumer;

public class UndoTree {
   private final Consumer state;
   private final Node parent;
   private Node current;

   public UndoTree(Consumer s) {
      this.state = s;
      this.parent = new Node((Object)null);
      this.parent.left = this.parent;
      this.clear();
   }

   public void clear() {
      this.current = this.parent;
   }

   public void newState(Object state) {
      UndoTree<T>.Node node = new Node(state);
      this.current.right = node;
      node.left = this.current;
      this.current = node;
   }

   public boolean canUndo() {
      return this.current.left != this.parent;
   }

   public boolean canRedo() {
      return this.current.right != null;
   }

   public void undo() {
      if (!this.canUndo()) {
         throw new IllegalStateException("Cannot undo.");
      } else {
         this.current = this.current.left;
         this.state.accept(this.current.state);
      }
   }

   public void redo() {
      if (!this.canRedo()) {
         throw new IllegalStateException("Cannot redo.");
      } else {
         this.current = this.current.right;
         this.state.accept(this.current.state);
      }
   }

   private class Node {
      private final Object state;
      private Node left = null;
      private Node right = null;

      public Node(Object s) {
         this.state = s;
      }
   }
}
