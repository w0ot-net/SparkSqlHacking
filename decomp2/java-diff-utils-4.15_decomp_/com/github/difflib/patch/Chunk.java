package com.github.difflib.patch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Chunk implements Serializable {
   private final int position;
   private List lines;
   private final List changePosition;

   public Chunk(int position, List lines, List changePosition) {
      this.position = position;
      this.lines = new ArrayList(lines);
      this.changePosition = changePosition != null ? new ArrayList(changePosition) : null;
   }

   public Chunk(int position, List lines) {
      this(position, (List)lines, (List)null);
   }

   public Chunk(int position, Object[] lines, List changePosition) {
      this.position = position;
      this.lines = Arrays.asList(lines);
      this.changePosition = changePosition != null ? new ArrayList(changePosition) : null;
   }

   public Chunk(int position, Object[] lines) {
      this(position, (Object[])lines, (List)null);
   }

   public VerifyChunk verifyChunk(List target) throws PatchFailedException {
      return this.verifyChunk(target, 0, this.getPosition());
   }

   public VerifyChunk verifyChunk(List target, int fuzz, int position) throws PatchFailedException {
      int lastIndex = this.size() - fuzz;
      int last = position + this.size() - 1;
      if (position + fuzz <= target.size() && last - fuzz <= target.size()) {
         for(int i = fuzz; i < lastIndex; ++i) {
            if (!target.get(position + i).equals(this.lines.get(i))) {
               return VerifyChunk.CONTENT_DOES_NOT_MATCH_TARGET;
            }
         }

         return VerifyChunk.OK;
      } else {
         return VerifyChunk.POSITION_OUT_OF_TARGET;
      }
   }

   public int getPosition() {
      return this.position;
   }

   public void setLines(List lines) {
      this.lines = lines;
   }

   public List getLines() {
      return this.lines;
   }

   public List getChangePosition() {
      return this.changePosition;
   }

   public int size() {
      return this.lines.size();
   }

   public int last() {
      return this.getPosition() + this.size() - 1;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.lines, this.position, this.size()});
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Chunk<?> other = (Chunk)obj;
         if (this.lines == null) {
            if (other.lines != null) {
               return false;
            }
         } else if (!this.lines.equals(other.lines)) {
            return false;
         }

         return this.position == other.position;
      }
   }

   public String toString() {
      return "[position: " + this.position + ", size: " + this.size() + ", lines: " + this.lines + "]";
   }
}
