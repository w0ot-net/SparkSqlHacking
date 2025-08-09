package com.github.difflib.text;

import java.io.Serializable;
import java.util.Objects;

public final class DiffRow implements Serializable {
   private Tag tag;
   private final String oldLine;
   private final String newLine;

   public DiffRow(Tag tag, String oldLine, String newLine) {
      this.tag = tag;
      this.oldLine = oldLine;
      this.newLine = newLine;
   }

   public Tag getTag() {
      return this.tag;
   }

   public void setTag(Tag tag) {
      this.tag = tag;
   }

   public String getOldLine() {
      return this.oldLine;
   }

   public String getNewLine() {
      return this.newLine;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.newLine, this.oldLine, this.tag});
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         DiffRow other = (DiffRow)obj;
         if (this.newLine == null) {
            if (other.newLine != null) {
               return false;
            }
         } else if (!this.newLine.equals(other.newLine)) {
            return false;
         }

         if (this.oldLine == null) {
            if (other.oldLine != null) {
               return false;
            }
         } else if (!this.oldLine.equals(other.oldLine)) {
            return false;
         }

         if (this.tag == null) {
            if (other.tag != null) {
               return false;
            }
         } else if (!this.tag.equals(other.tag)) {
            return false;
         }

         return true;
      }
   }

   public String toString() {
      return "[" + this.tag + "," + this.oldLine + "," + this.newLine + "]";
   }

   public static enum Tag {
      INSERT,
      DELETE,
      CHANGE,
      EQUAL;
   }
}
