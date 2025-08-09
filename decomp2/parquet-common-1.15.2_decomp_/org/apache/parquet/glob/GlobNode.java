package org.apache.parquet.glob;

import java.util.List;

interface GlobNode {
   Object accept(Visitor var1);

   public static class Atom implements GlobNode {
      private final String s;

      public Atom(String s) {
         this.s = s;
      }

      public String get() {
         return this.s;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() && this.s.equals(((Atom)o).s);
         }
      }

      public int hashCode() {
         return this.s.hashCode();
      }

      public String toString() {
         return "Atom(" + this.s + ")";
      }

      public Object accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static class OneOf implements GlobNode {
      private final List children;

      public OneOf(List children) {
         this.children = children;
      }

      public List getChildren() {
         return this.children;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() && this.children.equals(((OneOf)o).children);
         }
      }

      public int hashCode() {
         return this.children.hashCode();
      }

      public String toString() {
         return "OneOf" + this.children;
      }

      public Object accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static class GlobNodeSequence implements GlobNode {
      private final List children;

      public GlobNodeSequence(List children) {
         this.children = children;
      }

      public List getChildren() {
         return this.children;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() && this.children.equals(((OneOf)o).children);
         }
      }

      public int hashCode() {
         return this.children.hashCode();
      }

      public String toString() {
         return "GlobNodeSequence" + this.children;
      }

      public Object accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public interface Visitor {
      Object visit(Atom var1);

      Object visit(OneOf var1);

      Object visit(GlobNodeSequence var1);
   }
}
