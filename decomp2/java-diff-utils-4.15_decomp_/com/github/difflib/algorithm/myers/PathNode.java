package com.github.difflib.algorithm.myers;

public final class PathNode {
   public final int i;
   public final int j;
   public final PathNode prev;
   public final boolean snake;
   public final boolean bootstrap;

   public PathNode(int i, int j, boolean snake, boolean bootstrap, PathNode prev) {
      this.i = i;
      this.j = j;
      this.bootstrap = bootstrap;
      if (snake) {
         this.prev = prev;
      } else {
         this.prev = prev == null ? null : prev.previousSnake();
      }

      this.snake = snake;
   }

   public boolean isSnake() {
      return this.snake;
   }

   public boolean isBootstrap() {
      return this.bootstrap;
   }

   public final PathNode previousSnake() {
      if (this.isBootstrap()) {
         return null;
      } else {
         return !this.isSnake() && this.prev != null ? this.prev.previousSnake() : this;
      }
   }

   public String toString() {
      StringBuilder buf = new StringBuilder("[");

      for(PathNode node = this; node != null; node = node.prev) {
         buf.append("(");
         buf.append(node.i);
         buf.append(",");
         buf.append(node.j);
         buf.append(")");
      }

      buf.append("]");
      return buf.toString();
   }
}
