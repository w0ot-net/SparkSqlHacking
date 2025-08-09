package com.univocity.parsers.fixed;

public enum FieldAlignment {
   LEFT,
   CENTER,
   RIGHT;

   public int calculatePadding(int totalLength, int lengthToWrite) {
      if (this != LEFT && totalLength > lengthToWrite) {
         if (this == RIGHT) {
            return totalLength - lengthToWrite;
         } else {
            int padding = totalLength / 2 - lengthToWrite / 2;
            if (lengthToWrite + padding > totalLength) {
               --padding;
            }

            return padding;
         }
      } else {
         return 0;
      }
   }
}
