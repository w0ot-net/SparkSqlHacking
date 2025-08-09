package org.joda.time.convert;

class ConverterSet {
   private final Converter[] iConverters;
   private Entry[] iSelectEntries;

   ConverterSet(Converter[] var1) {
      this.iConverters = var1;
      this.iSelectEntries = new Entry[16];
   }

   Converter select(Class var1) throws IllegalStateException {
      Entry[] var2 = this.iSelectEntries;
      int var3 = var2.length;
      int var4 = var1 == null ? 0 : var1.hashCode() & var3 - 1;

      Entry var5;
      while((var5 = var2[var4]) != null) {
         if (var5.iType == var1) {
            return var5.iConverter;
         }

         ++var4;
         if (var4 >= var3) {
            var4 = 0;
         }
      }

      Converter var6 = selectSlow(this, var1);
      var5 = new Entry(var1, var6);
      var2 = (Entry[])(([Lorg.joda.time.convert.ConverterSet.Entry;)var2).clone();
      var2[var4] = var5;

      for(int var7 = 0; var7 < var3; ++var7) {
         if (var2[var7] == null) {
            this.iSelectEntries = var2;
            return var6;
         }
      }

      int var15 = var3 << 1;
      Entry[] var8 = new Entry[var15];

      for(int var9 = 0; var9 < var3; ++var9) {
         var5 = var2[var9];
         var1 = var5.iType;
         var4 = var1 == null ? 0 : var1.hashCode() & var15 - 1;

         while(var8[var4] != null) {
            ++var4;
            if (var4 >= var15) {
               var4 = 0;
            }
         }

         var8[var4] = var5;
      }

      this.iSelectEntries = var8;
      return var6;
   }

   int size() {
      return this.iConverters.length;
   }

   void copyInto(Converter[] var1) {
      System.arraycopy(this.iConverters, 0, var1, 0, this.iConverters.length);
   }

   ConverterSet add(Converter var1, Converter[] var2) {
      Converter[] var3 = this.iConverters;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Converter var6 = var3[var5];
         if (var1.equals(var6)) {
            if (var2 != null) {
               var2[0] = null;
            }

            return this;
         }

         if (var1.getSupportedType() == var6.getSupportedType()) {
            Converter[] var7 = new Converter[var4];

            for(int var8 = 0; var8 < var4; ++var8) {
               if (var8 != var5) {
                  var7[var8] = var3[var8];
               } else {
                  var7[var8] = var1;
               }
            }

            if (var2 != null) {
               var2[0] = var6;
            }

            return new ConverterSet(var7);
         }
      }

      Converter[] var9 = new Converter[var4 + 1];
      System.arraycopy(var3, 0, var9, 0, var4);
      var9[var4] = var1;
      if (var2 != null) {
         var2[0] = null;
      }

      return new ConverterSet(var9);
   }

   ConverterSet remove(Converter var1, Converter[] var2) {
      Converter[] var3 = this.iConverters;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         if (var1.equals(var3[var5])) {
            return this.remove(var5, var2);
         }
      }

      if (var2 != null) {
         var2[0] = null;
      }

      return this;
   }

   ConverterSet remove(int var1, Converter[] var2) {
      Converter[] var3 = this.iConverters;
      int var4 = var3.length;
      if (var1 >= var4) {
         throw new IndexOutOfBoundsException();
      } else {
         if (var2 != null) {
            var2[0] = var3[var1];
         }

         Converter[] var5 = new Converter[var4 - 1];
         int var6 = 0;

         for(int var7 = 0; var7 < var4; ++var7) {
            if (var7 != var1) {
               var5[var6++] = var3[var7];
            }
         }

         return new ConverterSet(var5);
      }
   }

   private static Converter selectSlow(ConverterSet var0, Class var1) {
      Converter[] var2 = var0.iConverters;
      int var3 = var2.length;
      int var5 = var3;

      while(true) {
         --var5;
         if (var5 < 0) {
            if (var1 != null && var3 != 0) {
               if (var3 == 1) {
                  return var2[0];
               } else {
                  var5 = var3;

                  while(true) {
                     --var5;
                     if (var5 < 0) {
                        if (var3 == 1) {
                           return var2[0];
                        }

                        StringBuilder var11 = new StringBuilder();
                        var11.append("Unable to find best converter for type \"");
                        var11.append(var1.getName());
                        var11.append("\" from remaining set: ");

                        for(int var13 = 0; var13 < var3; ++var13) {
                           Converter var9 = var2[var13];
                           Class var14 = var9.getSupportedType();
                           var11.append(var9.getClass().getName());
                           var11.append('[');
                           var11.append(var14 == null ? null : var14.getName());
                           var11.append("], ");
                        }

                        throw new IllegalStateException(var11.toString());
                     }

                     Converter var8 = var2[var5];
                     Class var12 = var8.getSupportedType();
                     int var7 = var3;

                     while(true) {
                        --var7;
                        if (var7 < 0) {
                           break;
                        }

                        if (var7 != var5 && var2[var7].getSupportedType().isAssignableFrom(var12)) {
                           var0 = var0.remove(var7, (Converter[])null);
                           var2 = var0.iConverters;
                           var3 = var2.length;
                           var5 = var3 - 1;
                        }
                     }
                  }
               }
            } else {
               return null;
            }
         }

         Converter var4 = var2[var5];
         Class var6 = var4.getSupportedType();
         if (var6 == var1) {
            return var4;
         }

         if (var6 == null || var1 != null && !var6.isAssignableFrom(var1)) {
            var0 = var0.remove(var5, (Converter[])null);
            var2 = var0.iConverters;
            var3 = var2.length;
         }
      }
   }

   static class Entry {
      final Class iType;
      final Converter iConverter;

      Entry(Class var1, Converter var2) {
         this.iType = var1;
         this.iConverter = var2;
      }
   }
}
