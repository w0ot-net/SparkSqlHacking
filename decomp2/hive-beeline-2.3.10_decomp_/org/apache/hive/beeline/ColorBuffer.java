package org.apache.hive.beeline;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

final class ColorBuffer implements Comparable {
   private static final ColorAttr BOLD = new ColorAttr("\u001b[1m");
   private static final ColorAttr NORMAL = new ColorAttr("\u001b[m");
   private static final ColorAttr REVERS = new ColorAttr("\u001b[7m");
   private static final ColorAttr LINED = new ColorAttr("\u001b[4m");
   private static final ColorAttr GREY = new ColorAttr("\u001b[1;30m");
   private static final ColorAttr RED = new ColorAttr("\u001b[1;31m");
   private static final ColorAttr GREEN = new ColorAttr("\u001b[1;32m");
   private static final ColorAttr BLUE = new ColorAttr("\u001b[1;34m");
   private static final ColorAttr CYAN = new ColorAttr("\u001b[1;36m");
   private static final ColorAttr YELLOW = new ColorAttr("\u001b[1;33m");
   private static final ColorAttr MAGENTA = new ColorAttr("\u001b[1;35m");
   private static final ColorAttr INVISIBLE = new ColorAttr("\u001b[8m");
   private final List parts = new LinkedList();
   private int visibleLength = 0;
   private final boolean useColor;

   public ColorBuffer(boolean useColor) {
      this.useColor = useColor;
      this.append("");
   }

   public ColorBuffer(String str, boolean useColor) {
      this.useColor = useColor;
      this.append(str);
   }

   ColorBuffer pad(ColorBuffer str, int len) {
      while(str.getVisibleLength() < len) {
         str.append(" ");
      }

      return this.append(str);
   }

   ColorBuffer center(String str, int len) {
      StringBuilder buf = new StringBuilder(str);

      while(buf.length() < len) {
         buf.append(" ");
         if (buf.length() < len) {
            buf.insert(0, " ");
         }
      }

      return this.append(buf.toString());
   }

   ColorBuffer pad(String str, int len) {
      if (str == null) {
         str = "";
      }

      return this.pad(new ColorBuffer(str, false), len);
   }

   public String getColor() {
      return this.getBuffer(this.useColor);
   }

   public String getMono() {
      return this.getBuffer(false);
   }

   String getBuffer(boolean color) {
      StringBuilder buf = new StringBuilder();

      for(Object part : this.parts) {
         if (color || !(part instanceof ColorAttr)) {
            buf.append(part.toString());
         }
      }

      return buf.toString();
   }

   public ColorBuffer truncate(int len) {
      if (len <= 0) {
         return this;
      } else {
         ColorBuffer cbuff = new ColorBuffer(this.useColor);
         ColorAttr lastAttr = null;
         Iterator<Object> i = this.parts.iterator();

         while(cbuff.getVisibleLength() < len && i.hasNext()) {
            Object next = i.next();
            if (next instanceof ColorAttr) {
               lastAttr = (ColorAttr)next;
               cbuff.append((ColorAttr)next);
            } else {
               String val = next.toString();
               if (cbuff.getVisibleLength() + val.length() > len) {
                  int partLen = len - cbuff.getVisibleLength();
                  val = val.substring(0, partLen);
               }

               cbuff.append(val);
            }
         }

         if (lastAttr != null && lastAttr != NORMAL) {
            cbuff.append(NORMAL);
         }

         return cbuff;
      }
   }

   public String toString() {
      return this.getColor();
   }

   public ColorBuffer append(String str) {
      this.parts.add(str);
      this.visibleLength += str.length();
      return this;
   }

   public ColorBuffer append(ColorBuffer buf) {
      this.parts.addAll(buf.parts);
      this.visibleLength += buf.getVisibleLength();
      return this;
   }

   private ColorBuffer append(ColorAttr attr) {
      this.parts.add(attr);
      return this;
   }

   public int getVisibleLength() {
      return this.visibleLength;
   }

   private ColorBuffer append(ColorAttr attr, String val) {
      this.parts.add(attr);
      this.parts.add(val);
      this.parts.add(NORMAL);
      this.visibleLength += val.length();
      return this;
   }

   public ColorBuffer bold(String str) {
      return this.append(BOLD, str);
   }

   public ColorBuffer lined(String str) {
      return this.append(LINED, str);
   }

   public ColorBuffer grey(String str) {
      return this.append(GREY, str);
   }

   public ColorBuffer red(String str) {
      return this.append(RED, str);
   }

   public ColorBuffer blue(String str) {
      return this.append(BLUE, str);
   }

   public ColorBuffer green(String str) {
      return this.append(GREEN, str);
   }

   public ColorBuffer cyan(String str) {
      return this.append(CYAN, str);
   }

   public ColorBuffer yellow(String str) {
      return this.append(YELLOW, str);
   }

   public ColorBuffer magenta(String str) {
      return this.append(MAGENTA, str);
   }

   public int compareTo(Object other) {
      return this.getMono().compareTo(((ColorBuffer)other).getMono());
   }

   private static class ColorAttr {
      private final String attr;

      public ColorAttr(String attr) {
         this.attr = attr;
      }

      public String toString() {
         return this.attr;
      }
   }
}
