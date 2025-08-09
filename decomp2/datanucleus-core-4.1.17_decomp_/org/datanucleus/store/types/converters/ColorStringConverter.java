package org.datanucleus.store.types.converters;

import java.awt.Color;

public class ColorStringConverter implements TypeConverter {
   private static final long serialVersionUID = -7940282427064674388L;

   public Color toMemberType(String str) {
      if (str == null) {
         return null;
      } else {
         int componentLength = (str.length() - 1) / 4;
         String rStr = str.substring(1, 1 + componentLength);
         String gStr = str.substring(1 + componentLength, 1 + 2 * componentLength);
         String bStr = str.substring(1 + 2 * componentLength, 1 + 3 * componentLength);
         String aStr = str.substring(1 + 3 * componentLength);
         int r = Integer.parseInt(rStr, 16);
         int g = Integer.parseInt(gStr, 16);
         int b = Integer.parseInt(bStr, 16);
         int a = Integer.parseInt(aStr, 16);
         return new Color(r, g, b, a);
      }
   }

   public String toDatastoreType(Color c) {
      if (c == null) {
         return null;
      } else {
         String r = c.getRed() < 16 ? "0" + Integer.toHexString(c.getRed()) : Integer.toHexString(c.getRed());
         String g = c.getGreen() < 16 ? "0" + Integer.toHexString(c.getGreen()) : Integer.toHexString(c.getGreen());
         String b = c.getBlue() < 16 ? "0" + Integer.toHexString(c.getBlue()) : Integer.toHexString(c.getBlue());
         String a = c.getAlpha() < 16 ? "0" + Integer.toHexString(c.getAlpha()) : Integer.toHexString(c.getAlpha());
         return "#" + r + g + b + a;
      }
   }
}
