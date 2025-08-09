package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;

import java.io.IOException;
import java.io.InputStream;

public class ClassReader {
   public static final int SKIP_CODE = 1;
   public static final int SKIP_DEBUG = 2;
   public static final int SKIP_FRAMES = 4;
   public static final int EXPAND_FRAMES = 8;
   public final byte[] b;
   private final int[] a;
   private final String[] c;
   private final int d;
   public final int header;

   public ClassReader(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public ClassReader(byte[] var1, int var2, int var3) {
      this.b = var1;
      if (this.readShort(var2 + 6) > 52) {
         throw new IllegalArgumentException();
      } else {
         this.a = new int[this.readUnsignedShort(var2 + 8)];
         int var4 = this.a.length;
         this.c = new String[var4];
         int var5 = 0;
         int var6 = var2 + 10;

         for(int var7 = 1; var7 < var4; ++var7) {
            this.a[var7] = var6 + 1;
            int var8;
            switch (var1[var6]) {
               case 1:
                  var8 = 3 + this.readUnsignedShort(var6 + 1);
                  if (var8 > var5) {
                     var5 = var8;
                  }
                  break;
               case 2:
               case 7:
               case 8:
               case 13:
               case 14:
               case 16:
               case 17:
               default:
                  var8 = 3;
                  break;
               case 3:
               case 4:
               case 9:
               case 10:
               case 11:
               case 12:
               case 18:
                  var8 = 5;
                  break;
               case 5:
               case 6:
                  var8 = 9;
                  ++var7;
                  break;
               case 15:
                  var8 = 4;
            }

            var6 += var8;
         }

         this.d = var5;
         this.header = var6;
      }
   }

   public int getAccess() {
      return this.readUnsignedShort(this.header);
   }

   public String getClassName() {
      return this.readClass(this.header + 2, new char[this.d]);
   }

   public String getSuperName() {
      return this.readClass(this.header + 4, new char[this.d]);
   }

   public String[] getInterfaces() {
      int var1 = this.header + 6;
      int var2 = this.readUnsignedShort(var1);
      String[] var3 = new String[var2];
      if (var2 > 0) {
         char[] var4 = new char[this.d];

         for(int var5 = 0; var5 < var2; ++var5) {
            var1 += 2;
            var3[var5] = this.readClass(var1, var4);
         }
      }

      return var3;
   }

   void a(ClassWriter var1) {
      char[] var2 = new char[this.d];
      int var3 = this.a.length;
      Item[] var4 = new Item[var3];

      for(int var5 = 1; var5 < var3; ++var5) {
         int var6 = this.a[var5];
         byte var7 = this.b[var6 - 1];
         Item var8 = new Item(var5);
         switch (var7) {
            case 1:
               String var15 = this.c[var5];
               if (var15 == null) {
                  var6 = this.a[var5];
                  var15 = this.c[var5] = this.a(var6 + 2, this.readUnsignedShort(var6), var2);
               }

               var8.a(var7, var15, (String)null, (String)null);
               break;
            case 2:
            case 7:
            case 8:
            case 13:
            case 14:
            case 16:
            case 17:
            default:
               var8.a(var7, this.readUTF8(var6, var2), (String)null, (String)null);
               break;
            case 3:
               var8.a(this.readInt(var6));
               break;
            case 4:
               var8.a(Float.intBitsToFloat(this.readInt(var6)));
               break;
            case 5:
               var8.a(this.readLong(var6));
               ++var5;
               break;
            case 6:
               var8.a(Double.longBitsToDouble(this.readLong(var6)));
               ++var5;
               break;
            case 9:
            case 10:
            case 11:
               int var14 = this.a[this.readUnsignedShort(var6 + 2)];
               var8.a(var7, this.readClass(var6, var2), this.readUTF8(var14, var2), this.readUTF8(var14 + 2, var2));
               break;
            case 12:
               var8.a(var7, this.readUTF8(var6, var2), this.readUTF8(var6 + 2, var2), (String)null);
               break;
            case 15:
               int var10 = this.a[this.readUnsignedShort(var6 + 1)];
               int var13 = this.a[this.readUnsignedShort(var10 + 2)];
               var8.a(20 + this.readByte(var6), this.readClass(var10, var2), this.readUTF8(var13, var2), this.readUTF8(var13 + 2, var2));
               break;
            case 18:
               if (var1.A == null) {
                  this.a(var1, var4, var2);
               }

               int var9 = this.a[this.readUnsignedShort(var6 + 2)];
               var8.a(this.readUTF8(var9, var2), this.readUTF8(var9 + 2, var2), this.readUnsignedShort(var6));
         }

         int var16 = var8.j % var4.length;
         var8.k = var4[var16];
         var4[var16] = var8;
      }

      int var11 = this.a[1] - 1;
      var1.d.putByteArray(this.b, var11, this.header - var11);
      var1.e = var4;
      var1.f = (int)((double)0.75F * (double)var3);
      var1.c = var3;
   }

   private void a(ClassWriter var1, Item[] var2, char[] var3) {
      int var4 = this.a();
      boolean var5 = false;

      for(int var6 = this.readUnsignedShort(var4); var6 > 0; --var6) {
         String var7 = this.readUTF8(var4 + 2, var3);
         if ("BootstrapMethods".equals(var7)) {
            var5 = true;
            break;
         }

         var4 += 6 + this.readInt(var4 + 4);
      }

      if (var5) {
         int var13 = this.readUnsignedShort(var4 + 8);
         int var14 = 0;

         for(int var8 = var4 + 10; var14 < var13; ++var14) {
            int var9 = var8 - var4 - 10;
            int var10 = this.readConst(this.readUnsignedShort(var8), var3).hashCode();

            for(int var11 = this.readUnsignedShort(var8 + 2); var11 > 0; --var11) {
               var10 ^= this.readConst(this.readUnsignedShort(var8 + 4), var3).hashCode();
               var8 += 2;
            }

            var8 += 4;
            Item var17 = new Item(var14);
            var17.a(var9, var10 & Integer.MAX_VALUE);
            int var12 = var17.j % var2.length;
            var17.k = var2[var12];
            var2[var12] = var17;
         }

         var14 = this.readInt(var4 + 4);
         ByteVector var16 = new ByteVector(var14 + 62);
         var16.putByteArray(this.b, var4 + 10, var14 - 2);
         var1.z = var13;
         var1.A = var16;
      }
   }

   public ClassReader(InputStream var1) throws IOException {
      this(a(var1, false));
   }

   public ClassReader(String var1) throws IOException {
      this(a(ClassLoader.getSystemResourceAsStream(var1.replace('.', '/') + ".class"), true));
   }

   private static byte[] a(InputStream var0, boolean var1) throws IOException {
      if (var0 == null) {
         throw new IOException("Class not found");
      } else {
         try {
            byte[] var2 = new byte[var0.available()];
            int var3 = 0;

            while(true) {
               int var4 = var0.read(var2, var3, var2.length - var3);
               if (var4 == -1) {
                  if (var3 < var2.length) {
                     byte[] var10 = new byte[var3];
                     System.arraycopy(var2, 0, var10, 0, var3);
                     var2 = var10;
                  }

                  byte[] var11 = var2;
                  return var11;
               }

               var3 += var4;
               if (var3 == var2.length) {
                  int var5 = var0.read();
                  if (var5 < 0) {
                     byte[] var12 = var2;
                     return var12;
                  }

                  byte[] var6 = new byte[var2.length + 1000];
                  System.arraycopy(var2, 0, var6, 0, var3);
                  var6[var3++] = (byte)var5;
                  var2 = var6;
               }
            }
         } finally {
            if (var1) {
               var0.close();
            }

         }
      }
   }

   public void accept(ClassVisitor var1, int var2) {
      this.accept(var1, new Attribute[0], var2);
   }

   public void accept(ClassVisitor var1, Attribute[] var2, int var3) {
      int var4 = this.header;
      char[] var5 = new char[this.d];
      Context var6 = new Context();
      var6.a = var2;
      var6.b = var3;
      var6.c = var5;
      int var7 = this.readUnsignedShort(var4);
      String var8 = this.readClass(var4 + 2, var5);
      String var9 = this.readClass(var4 + 4, var5);
      String[] var10 = new String[this.readUnsignedShort(var4 + 6)];
      var4 += 8;

      for(int var11 = 0; var11 < var10.length; ++var11) {
         var10[var11] = this.readClass(var4, var5);
         var4 += 2;
      }

      String var32 = null;
      String var12 = null;
      String var13 = null;
      String var14 = null;
      String var15 = null;
      String var16 = null;
      int var17 = 0;
      int var18 = 0;
      int var19 = 0;
      int var20 = 0;
      int var21 = 0;
      Attribute var22 = null;
      var4 = this.a();

      for(int var23 = this.readUnsignedShort(var4); var23 > 0; --var23) {
         String var24 = this.readUTF8(var4 + 2, var5);
         if ("SourceFile".equals(var24)) {
            var12 = this.readUTF8(var4 + 8, var5);
         } else if ("InnerClasses".equals(var24)) {
            var21 = var4 + 8;
         } else if ("EnclosingMethod".equals(var24)) {
            var14 = this.readClass(var4 + 8, var5);
            int var50 = this.readUnsignedShort(var4 + 10);
            if (var50 != 0) {
               var15 = this.readUTF8(this.a[var50], var5);
               var16 = this.readUTF8(this.a[var50] + 2, var5);
            }
         } else if ("Signature".equals(var24)) {
            var32 = this.readUTF8(var4 + 8, var5);
         } else if ("RuntimeVisibleAnnotations".equals(var24)) {
            var17 = var4 + 8;
         } else if ("RuntimeVisibleTypeAnnotations".equals(var24)) {
            var19 = var4 + 8;
         } else if ("Deprecated".equals(var24)) {
            var7 |= 131072;
         } else if ("Synthetic".equals(var24)) {
            var7 |= 266240;
         } else if ("SourceDebugExtension".equals(var24)) {
            int var49 = this.readInt(var4 + 4);
            var13 = this.a(var4 + 8, var49, new char[var49]);
         } else if ("RuntimeInvisibleAnnotations".equals(var24)) {
            var18 = var4 + 8;
         } else if ("RuntimeInvisibleTypeAnnotations".equals(var24)) {
            var20 = var4 + 8;
         } else if (!"BootstrapMethods".equals(var24)) {
            Attribute var48 = this.a(var2, var24, var4 + 8, this.readInt(var4 + 4), var5, -1, (Label[])null);
            if (var48 != null) {
               var48.a = var22;
               var22 = var48;
            }
         } else {
            int[] var25 = new int[this.readUnsignedShort(var4 + 8)];
            int var26 = 0;

            for(int var27 = var4 + 10; var26 < var25.length; ++var26) {
               var25[var26] = var27;
               var27 += 2 + this.readUnsignedShort(var27 + 2) << 1;
            }

            var6.d = var25;
         }

         var4 += 6 + this.readInt(var4 + 4);
      }

      var1.visit(this.readInt(this.a[1] - 7), var7, var8, var32, var9, var10);
      if ((var3 & 2) == 0 && (var12 != null || var13 != null)) {
         var1.visitSource(var12, var13);
      }

      if (var14 != null) {
         var1.visitOuterClass(var14, var15, var16);
      }

      if (var17 != 0) {
         int var33 = this.readUnsignedShort(var17);

         for(int var41 = var17 + 2; var33 > 0; --var33) {
            var41 = this.a(var41 + 2, var5, true, var1.visitAnnotation(this.readUTF8(var41, var5), true));
         }
      }

      if (var18 != 0) {
         int var34 = this.readUnsignedShort(var18);

         for(int var42 = var18 + 2; var34 > 0; --var34) {
            var42 = this.a(var42 + 2, var5, true, var1.visitAnnotation(this.readUTF8(var42, var5), false));
         }
      }

      if (var19 != 0) {
         int var35 = this.readUnsignedShort(var19);

         for(int var43 = var19 + 2; var35 > 0; --var35) {
            var43 = this.a(var6, var43);
            var43 = this.a(var43 + 2, var5, true, var1.visitTypeAnnotation(var6.i, var6.j, this.readUTF8(var43, var5), true));
         }
      }

      if (var20 != 0) {
         int var36 = this.readUnsignedShort(var20);

         for(int var45 = var20 + 2; var36 > 0; --var36) {
            var45 = this.a(var6, var45);
            var45 = this.a(var45 + 2, var5, true, var1.visitTypeAnnotation(var6.i, var6.j, this.readUTF8(var45, var5), false));
         }
      }

      while(var22 != null) {
         Attribute var37 = var22.a;
         var22.a = null;
         var1.visitAttribute(var22);
         var22 = var37;
      }

      if (var21 != 0) {
         int var38 = var21 + 2;

         for(int var47 = this.readUnsignedShort(var21); var47 > 0; --var47) {
            var1.visitInnerClass(this.readClass(var38, var5), this.readClass(var38 + 2, var5), this.readUTF8(var38 + 4, var5), this.readUnsignedShort(var38 + 6));
            var38 += 8;
         }
      }

      var4 = this.header + 10 + 2 * var10.length;

      for(int var39 = this.readUnsignedShort(var4 - 2); var39 > 0; --var39) {
         var4 = this.a(var1, var6, var4);
      }

      var4 += 2;

      for(int var40 = this.readUnsignedShort(var4 - 2); var40 > 0; --var40) {
         var4 = this.b(var1, var6, var4);
      }

      var1.visitEnd();
   }

   private int a(ClassVisitor var1, Context var2, int var3) {
      char[] var4 = var2.c;
      int var5 = this.readUnsignedShort(var3);
      String var6 = this.readUTF8(var3 + 2, var4);
      String var7 = this.readUTF8(var3 + 4, var4);
      var3 += 6;
      String var8 = null;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      int var12 = 0;
      Object var13 = null;
      Attribute var14 = null;

      for(int var15 = this.readUnsignedShort(var3); var15 > 0; --var15) {
         String var16 = this.readUTF8(var3 + 2, var4);
         if ("ConstantValue".equals(var16)) {
            int var17 = this.readUnsignedShort(var3 + 8);
            var13 = var17 == 0 ? null : this.readConst(var17, var4);
         } else if ("Signature".equals(var16)) {
            var8 = this.readUTF8(var3 + 8, var4);
         } else if ("Deprecated".equals(var16)) {
            var5 |= 131072;
         } else if ("Synthetic".equals(var16)) {
            var5 |= 266240;
         } else if ("RuntimeVisibleAnnotations".equals(var16)) {
            var9 = var3 + 8;
         } else if ("RuntimeVisibleTypeAnnotations".equals(var16)) {
            var11 = var3 + 8;
         } else if ("RuntimeInvisibleAnnotations".equals(var16)) {
            var10 = var3 + 8;
         } else if ("RuntimeInvisibleTypeAnnotations".equals(var16)) {
            var12 = var3 + 8;
         } else {
            Attribute var26 = this.a(var2.a, var16, var3 + 8, this.readInt(var3 + 4), var4, -1, (Label[])null);
            if (var26 != null) {
               var26.a = var14;
               var14 = var26;
            }
         }

         var3 += 6 + this.readInt(var3 + 4);
      }

      var3 += 2;
      FieldVisitor var20 = var1.visitField(var5, var6, var7, var8, var13);
      if (var20 == null) {
         return var3;
      } else {
         if (var9 != 0) {
            int var21 = this.readUnsignedShort(var9);

            for(int var27 = var9 + 2; var21 > 0; --var21) {
               var27 = this.a(var27 + 2, var4, true, var20.visitAnnotation(this.readUTF8(var27, var4), true));
            }
         }

         if (var10 != 0) {
            int var22 = this.readUnsignedShort(var10);

            for(int var28 = var10 + 2; var22 > 0; --var22) {
               var28 = this.a(var28 + 2, var4, true, var20.visitAnnotation(this.readUTF8(var28, var4), false));
            }
         }

         if (var11 != 0) {
            int var23 = this.readUnsignedShort(var11);

            for(int var29 = var11 + 2; var23 > 0; --var23) {
               var29 = this.a(var2, var29);
               var29 = this.a(var29 + 2, var4, true, var20.visitTypeAnnotation(var2.i, var2.j, this.readUTF8(var29, var4), true));
            }
         }

         if (var12 != 0) {
            int var24 = this.readUnsignedShort(var12);

            for(int var31 = var12 + 2; var24 > 0; --var24) {
               var31 = this.a(var2, var31);
               var31 = this.a(var31 + 2, var4, true, var20.visitTypeAnnotation(var2.i, var2.j, this.readUTF8(var31, var4), false));
            }
         }

         while(var14 != null) {
            Attribute var25 = var14.a;
            var14.a = null;
            var20.visitAttribute(var14);
            var14 = var25;
         }

         var20.visitEnd();
         return var3;
      }
   }

   private int b(ClassVisitor var1, Context var2, int var3) {
      char[] var4 = var2.c;
      var2.e = this.readUnsignedShort(var3);
      var2.f = this.readUTF8(var3 + 2, var4);
      var2.g = this.readUTF8(var3 + 4, var4);
      var3 += 6;
      int var5 = 0;
      int var6 = 0;
      String[] var7 = null;
      String var8 = null;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      int var12 = 0;
      int var13 = 0;
      int var14 = 0;
      int var15 = 0;
      int var16 = 0;
      int var17 = var3;
      Attribute var18 = null;

      for(int var19 = this.readUnsignedShort(var3); var19 > 0; --var19) {
         String var20 = this.readUTF8(var3 + 2, var4);
         if ("Code".equals(var20)) {
            if ((var2.b & 1) == 0) {
               var5 = var3 + 8;
            }
         } else if ("Exceptions".equals(var20)) {
            var7 = new String[this.readUnsignedShort(var3 + 8)];
            var6 = var3 + 10;

            for(int var34 = 0; var34 < var7.length; ++var34) {
               var7[var34] = this.readClass(var6, var4);
               var6 += 2;
            }
         } else if ("Signature".equals(var20)) {
            var8 = this.readUTF8(var3 + 8, var4);
         } else if ("Deprecated".equals(var20)) {
            var2.e |= 131072;
         } else if ("RuntimeVisibleAnnotations".equals(var20)) {
            var10 = var3 + 8;
         } else if ("RuntimeVisibleTypeAnnotations".equals(var20)) {
            var12 = var3 + 8;
         } else if ("AnnotationDefault".equals(var20)) {
            var14 = var3 + 8;
         } else if ("Synthetic".equals(var20)) {
            var2.e |= 266240;
         } else if ("RuntimeInvisibleAnnotations".equals(var20)) {
            var11 = var3 + 8;
         } else if ("RuntimeInvisibleTypeAnnotations".equals(var20)) {
            var13 = var3 + 8;
         } else if ("RuntimeVisibleParameterAnnotations".equals(var20)) {
            var15 = var3 + 8;
         } else if ("RuntimeInvisibleParameterAnnotations".equals(var20)) {
            var16 = var3 + 8;
         } else if ("MethodParameters".equals(var20)) {
            var9 = var3 + 8;
         } else {
            Attribute var21 = this.a(var2.a, var20, var3 + 8, this.readInt(var3 + 4), var4, -1, (Label[])null);
            if (var21 != null) {
               var21.a = var18;
               var18 = var21;
            }
         }

         var3 += 6 + this.readInt(var3 + 4);
      }

      var3 += 2;
      MethodVisitor var25 = var1.visitMethod(var2.e, var2.f, var2.g, var8, var7);
      if (var25 == null) {
         return var3;
      } else {
         if (var25 instanceof MethodWriter) {
            MethodWriter var26 = (MethodWriter)var25;
            if (var26.b.M == this && var8 == var26.g) {
               boolean var35 = false;
               if (var7 == null) {
                  var35 = var26.j == 0;
               } else if (var7.length == var26.j) {
                  var35 = true;

                  for(int var22 = var7.length - 1; var22 >= 0; --var22) {
                     var6 -= 2;
                     if (var26.k[var22] != this.readUnsignedShort(var6)) {
                        var35 = false;
                        break;
                     }
                  }
               }

               if (var35) {
                  var26.h = var17;
                  var26.i = var3 - var17;
                  return var3;
               }
            }
         }

         if (var9 != 0) {
            int var27 = this.b[var9] & 255;

            for(int var36 = var9 + 1; var27 > 0; var36 += 4) {
               var25.visitParameter(this.readUTF8(var36, var4), this.readUnsignedShort(var36 + 2));
               --var27;
            }
         }

         if (var14 != 0) {
            AnnotationVisitor var28 = var25.visitAnnotationDefault();
            this.a(var14, var4, (String)null, var28);
            if (var28 != null) {
               var28.visitEnd();
            }
         }

         if (var10 != 0) {
            int var29 = this.readUnsignedShort(var10);

            for(int var37 = var10 + 2; var29 > 0; --var29) {
               var37 = this.a(var37 + 2, var4, true, var25.visitAnnotation(this.readUTF8(var37, var4), true));
            }
         }

         if (var11 != 0) {
            int var30 = this.readUnsignedShort(var11);

            for(int var38 = var11 + 2; var30 > 0; --var30) {
               var38 = this.a(var38 + 2, var4, true, var25.visitAnnotation(this.readUTF8(var38, var4), false));
            }
         }

         if (var12 != 0) {
            int var31 = this.readUnsignedShort(var12);

            for(int var39 = var12 + 2; var31 > 0; --var31) {
               var39 = this.a(var2, var39);
               var39 = this.a(var39 + 2, var4, true, var25.visitTypeAnnotation(var2.i, var2.j, this.readUTF8(var39, var4), true));
            }
         }

         if (var13 != 0) {
            int var32 = this.readUnsignedShort(var13);

            for(int var41 = var13 + 2; var32 > 0; --var32) {
               var41 = this.a(var2, var41);
               var41 = this.a(var41 + 2, var4, true, var25.visitTypeAnnotation(var2.i, var2.j, this.readUTF8(var41, var4), false));
            }
         }

         if (var15 != 0) {
            this.b(var25, var2, var15, true);
         }

         if (var16 != 0) {
            this.b(var25, var2, var16, false);
         }

         while(var18 != null) {
            Attribute var33 = var18.a;
            var18.a = null;
            var25.visitAttribute(var18);
            var18 = var33;
         }

         if (var5 != 0) {
            var25.visitCode();
            this.a(var25, var2, var5);
         }

         var25.visitEnd();
         return var3;
      }
   }

   private void a(MethodVisitor var1, Context var2, int var3) {
      byte[] var4 = this.b;
      char[] var5 = var2.c;
      int var6 = this.readUnsignedShort(var3);
      int var7 = this.readUnsignedShort(var3 + 2);
      int var8 = this.readInt(var3 + 4);
      var3 += 8;
      int var9 = var3;
      int var10 = var3 + var8;
      Label[] var11 = var2.h = new Label[var8 + 2];
      this.readLabel(var8 + 1, var11);

      while(var3 < var10) {
         int var12 = var3 - var9;
         int var13 = var4[var3] & 255;
         switch (ClassWriter.a[var13]) {
            case 0:
            case 4:
               ++var3;
               break;
            case 1:
            case 3:
            case 11:
               var3 += 2;
               break;
            case 2:
            case 5:
            case 6:
            case 12:
            case 13:
               var3 += 3;
               break;
            case 7:
            case 8:
               var3 += 5;
               break;
            case 9:
               this.readLabel(var12 + this.readShort(var3 + 1), var11);
               var3 += 3;
               break;
            case 10:
               this.readLabel(var12 + this.readInt(var3 + 1), var11);
               var3 += 5;
               break;
            case 14:
               var3 = var3 + 4 - (var12 & 3);
               this.readLabel(var12 + this.readInt(var3), var11);

               for(int var52 = this.readInt(var3 + 8) - this.readInt(var3 + 4) + 1; var52 > 0; --var52) {
                  this.readLabel(var12 + this.readInt(var3 + 12), var11);
                  var3 += 4;
               }

               var3 += 12;
               break;
            case 15:
               var3 = var3 + 4 - (var12 & 3);
               this.readLabel(var12 + this.readInt(var3), var11);

               for(int var14 = this.readInt(var3 + 4); var14 > 0; --var14) {
                  this.readLabel(var12 + this.readInt(var3 + 12), var11);
                  var3 += 8;
               }

               var3 += 8;
               break;
            case 16:
            default:
               var3 += 4;
               break;
            case 17:
               var13 = var4[var3 + 1] & 255;
               if (var13 == 132) {
                  var3 += 6;
               } else {
                  var3 += 4;
               }
         }
      }

      for(int var47 = this.readUnsignedShort(var3); var47 > 0; --var47) {
         Label var50 = this.readLabel(this.readUnsignedShort(var3 + 2), var11);
         Label var53 = this.readLabel(this.readUnsignedShort(var3 + 4), var11);
         Label var15 = this.readLabel(this.readUnsignedShort(var3 + 6), var11);
         String var16 = this.readUTF8(this.a[this.readUnsignedShort(var3 + 8)], var5);
         var1.visitTryCatchBlock(var50, var53, var15, var16);
         var3 += 8;
      }

      var3 += 2;
      int[] var48 = null;
      int[] var51 = null;
      int var54 = 0;
      int var55 = 0;
      int var56 = -1;
      int var17 = -1;
      int var18 = 0;
      int var19 = 0;
      boolean var20 = true;
      boolean var21 = (var2.b & 8) != 0;
      int var22 = 0;
      int var23 = 0;
      int var24 = 0;
      Context var25 = null;
      Attribute var26 = null;

      for(int var27 = this.readUnsignedShort(var3); var27 > 0; --var27) {
         String var28 = this.readUTF8(var3 + 2, var5);
         if ("LocalVariableTable".equals(var28)) {
            if ((var2.b & 2) == 0) {
               var18 = var3 + 8;
               int var72 = this.readUnsignedShort(var3 + 8);

               for(int var80 = var3; var72 > 0; --var72) {
                  int var90 = this.readUnsignedShort(var80 + 10);
                  if (var11[var90] == null) {
                     Label var111 = this.readLabel(var90, var11);
                     var111.a |= 1;
                  }

                  var90 += this.readUnsignedShort(var80 + 12);
                  if (var11[var90] == null) {
                     Label var112 = this.readLabel(var90, var11);
                     var112.a |= 1;
                  }

                  var80 += 10;
               }
            }
         } else if ("LocalVariableTypeTable".equals(var28)) {
            var19 = var3 + 8;
         } else if ("LineNumberTable".equals(var28)) {
            if ((var2.b & 2) == 0) {
               int var71 = this.readUnsignedShort(var3 + 8);

               for(int var79 = var3; var71 > 0; --var71) {
                  int var31 = this.readUnsignedShort(var79 + 10);
                  if (var11[var31] == null) {
                     Label var10000 = this.readLabel(var31, var11);
                     var10000.a |= 1;
                  }

                  Label var32;
                  for(var32 = var11[var31]; var32.b > 0; var32 = var32.k) {
                     if (var32.k == null) {
                        var32.k = new Label();
                     }
                  }

                  var32.b = this.readUnsignedShort(var79 + 12);
                  var79 += 4;
               }
            }
         } else if ("RuntimeVisibleTypeAnnotations".equals(var28)) {
            var48 = this.a(var1, var2, var3 + 8, true);
            var56 = var48.length != 0 && this.readByte(var48[0]) >= 67 ? this.readUnsignedShort(var48[0] + 1) : -1;
         } else if (!"RuntimeInvisibleTypeAnnotations".equals(var28)) {
            if ("StackMapTable".equals(var28)) {
               if ((var2.b & 4) == 0) {
                  var22 = var3 + 10;
                  var23 = this.readInt(var3 + 4);
                  var24 = this.readUnsignedShort(var3 + 8);
               }
            } else if ("StackMap".equals(var28)) {
               if ((var2.b & 4) == 0) {
                  var20 = false;
                  var22 = var3 + 10;
                  var23 = this.readInt(var3 + 4);
                  var24 = this.readUnsignedShort(var3 + 8);
               }
            } else {
               for(int var29 = 0; var29 < var2.a.length; ++var29) {
                  if (var2.a[var29].type.equals(var28)) {
                     Attribute var30 = var2.a[var29].read(this, var3 + 8, this.readInt(var3 + 4), var5, var9 - 8, var11);
                     if (var30 != null) {
                        var30.a = var26;
                        var26 = var30;
                     }
                  }
               }
            }
         } else {
            var51 = this.a(var1, var2, var3 + 8, false);
            var17 = var51.length != 0 && this.readByte(var51[0]) >= 67 ? this.readUnsignedShort(var51[0] + 1) : -1;
         }

         var3 += 6 + this.readInt(var3 + 4);
      }

      var3 += 2;
      if (var22 != 0) {
         var25 = var2;
         var2.o = -1;
         var2.p = 0;
         var2.q = 0;
         var2.r = 0;
         var2.t = 0;
         var2.s = new Object[var7];
         var2.u = new Object[var6];
         if (var21) {
            this.a(var2);
         }

         for(int var57 = var22; var57 < var22 + var23 - 2; ++var57) {
            if (var4[var57] == 8) {
               int var63 = this.readUnsignedShort(var57 + 1);
               if (var63 >= 0 && var63 < var8 && (var4[var9 + var63] & 255) == 187) {
                  this.readLabel(var63, var11);
               }
            }
         }
      }

      var3 = var9;

      while(var3 < var10) {
         int var58 = var3 - var9;
         Label var64 = var11[var58];
         if (var64 != null) {
            Label var73 = var64.k;
            var64.k = null;
            var1.visitLabel(var64);
            if ((var2.b & 2) == 0 && var64.b > 0) {
               var1.visitLineNumber(var64.b, var64);

               while(var73 != null) {
                  var1.visitLineNumber(var73.b, var64);
                  var73 = var73.k;
               }
            }
         }

         while(var25 != null && (var25.o == var58 || var25.o == -1)) {
            if (var25.o != -1) {
               if (var20 && !var21) {
                  var1.visitFrame(var25.p, var25.r, var25.s, var25.t, var25.u);
               } else {
                  var1.visitFrame(-1, var25.q, var25.s, var25.t, var25.u);
               }
            }

            if (var24 > 0) {
               var22 = this.a(var22, var20, var21, var25);
               --var24;
            } else {
               var25 = null;
            }
         }

         int var74 = var4[var3] & 255;
         switch (ClassWriter.a[var74]) {
            case 0:
               var1.visitInsn(var74);
               ++var3;
               break;
            case 1:
               var1.visitIntInsn(var74, var4[var3 + 1]);
               var3 += 2;
               break;
            case 2:
               var1.visitIntInsn(var74, this.readShort(var3 + 1));
               var3 += 3;
               break;
            case 3:
               var1.visitVarInsn(var74, var4[var3 + 1] & 255);
               var3 += 2;
               break;
            case 4:
               if (var74 > 54) {
                  var74 -= 59;
                  var1.visitVarInsn(54 + (var74 >> 2), var74 & 3);
               } else {
                  var74 -= 26;
                  var1.visitVarInsn(21 + (var74 >> 2), var74 & 3);
               }

               ++var3;
               break;
            case 5:
               var1.visitTypeInsn(var74, this.readClass(var3 + 1, var5));
               var3 += 3;
               break;
            case 6:
            case 7:
               int var85 = this.a[this.readUnsignedShort(var3 + 1)];
               boolean var96 = var4[var85 - 1] == 11;
               String var101 = this.readClass(var85, var5);
               var85 = this.a[this.readUnsignedShort(var85 + 2)];
               String var105 = this.readUTF8(var85, var5);
               String var109 = this.readUTF8(var85 + 2, var5);
               if (var74 < 182) {
                  var1.visitFieldInsn(var74, var101, var105, var109);
               } else {
                  var1.visitMethodInsn(var74, var101, var105, var109, var96);
               }

               if (var74 == 185) {
                  var3 += 5;
               } else {
                  var3 += 3;
               }
               break;
            case 8:
               int var83 = this.a[this.readUnsignedShort(var3 + 1)];
               int var94 = var2.d[this.readUnsignedShort(var83)];
               Handle var100 = (Handle)this.readConst(this.readUnsignedShort(var94), var5);
               int var104 = this.readUnsignedShort(var94 + 2);
               Object[] var108 = new Object[var104];
               var94 += 4;

               for(int var35 = 0; var35 < var104; ++var35) {
                  var108[var35] = this.readConst(this.readUnsignedShort(var94), var5);
                  var94 += 2;
               }

               var83 = this.a[this.readUnsignedShort(var83 + 2)];
               String var110 = this.readUTF8(var83, var5);
               String var36 = this.readUTF8(var83 + 2, var5);
               var1.visitInvokeDynamicInsn(var110, var36, var100, var108);
               var3 += 5;
               break;
            case 9:
               var1.visitJumpInsn(var74, var11[var58 + this.readShort(var3 + 1)]);
               var3 += 3;
               break;
            case 10:
               var1.visitJumpInsn(var74 - 33, var11[var58 + this.readInt(var3 + 1)]);
               var3 += 5;
               break;
            case 11:
               var1.visitLdcInsn(this.readConst(var4[var3 + 1] & 255, var5));
               var3 += 2;
               break;
            case 12:
               var1.visitLdcInsn(this.readConst(this.readUnsignedShort(var3 + 1), var5));
               var3 += 3;
               break;
            case 13:
               var1.visitIincInsn(var4[var3 + 1] & 255, var4[var3 + 2]);
               var3 += 3;
               break;
            case 14:
               var3 = var3 + 4 - (var58 & 3);
               int var82 = var58 + this.readInt(var3);
               int var93 = this.readInt(var3 + 4);
               int var99 = this.readInt(var3 + 8);
               Label[] var103 = new Label[var99 - var93 + 1];
               var3 += 12;

               for(int var107 = 0; var107 < var103.length; ++var107) {
                  var103[var107] = var11[var58 + this.readInt(var3)];
                  var3 += 4;
               }

               var1.visitTableSwitchInsn(var93, var99, var11[var82], var103);
               break;
            case 15:
               var3 = var3 + 4 - (var58 & 3);
               int var81 = var58 + this.readInt(var3);
               int var92 = this.readInt(var3 + 4);
               int[] var98 = new int[var92];
               Label[] var33 = new Label[var92];
               var3 += 8;

               for(int var34 = 0; var34 < var92; ++var34) {
                  var98[var34] = this.readInt(var3);
                  var33[var34] = var11[var58 + this.readInt(var3 + 4)];
                  var3 += 8;
               }

               var1.visitLookupSwitchInsn(var11[var81], var98, var33);
               break;
            case 16:
            default:
               var1.visitMultiANewArrayInsn(this.readClass(var3 + 1, var5), var4[var3 + 3] & 255);
               var3 += 4;
               break;
            case 17:
               var74 = var4[var3 + 1] & 255;
               if (var74 == 132) {
                  var1.visitIincInsn(this.readUnsignedShort(var3 + 2), this.readShort(var3 + 4));
                  var3 += 6;
               } else {
                  var1.visitVarInsn(var74, this.readUnsignedShort(var3 + 2));
                  var3 += 4;
               }
         }

         while(var48 != null && var54 < var48.length && var56 <= var58) {
            if (var56 == var58) {
               int var87 = this.a(var2, var48[var54]);
               this.a(var87 + 2, var5, true, var1.visitInsnAnnotation(var2.i, var2.j, this.readUTF8(var87, var5), true));
            }

            ++var54;
            var56 = var54 < var48.length && this.readByte(var48[var54]) >= 67 ? this.readUnsignedShort(var48[var54] + 1) : -1;
         }

         while(var51 != null && var55 < var51.length && var17 <= var58) {
            if (var17 == var58) {
               int var88 = this.a(var2, var51[var55]);
               this.a(var88 + 2, var5, true, var1.visitInsnAnnotation(var2.i, var2.j, this.readUTF8(var88, var5), false));
            }

            ++var55;
            var17 = var55 < var51.length && this.readByte(var51[var55]) >= 67 ? this.readUnsignedShort(var51[var55] + 1) : -1;
         }
      }

      if (var11[var8] != null) {
         var1.visitLabel(var11[var8]);
      }

      if ((var2.b & 2) == 0 && var18 != 0) {
         int[] var59 = null;
         if (var19 != 0) {
            var3 = var19 + 2;
            var59 = new int[this.readUnsignedShort(var19) * 3];

            for(int var65 = var59.length; var65 > 0; var3 += 10) {
               --var65;
               var59[var65] = var3 + 6;
               --var65;
               var59[var65] = this.readUnsignedShort(var3 + 8);
               --var65;
               var59[var65] = this.readUnsignedShort(var3);
            }
         }

         var3 = var18 + 2;

         for(int var68 = this.readUnsignedShort(var18); var68 > 0; --var68) {
            int var78 = this.readUnsignedShort(var3);
            int var89 = this.readUnsignedShort(var3 + 2);
            int var97 = this.readUnsignedShort(var3 + 8);
            String var102 = null;
            if (var59 != null) {
               for(int var106 = 0; var106 < var59.length; var106 += 3) {
                  if (var59[var106] == var78 && var59[var106 + 1] == var97) {
                     var102 = this.readUTF8(var59[var106 + 2], var5);
                     break;
                  }
               }
            }

            var1.visitLocalVariable(this.readUTF8(var3 + 4, var5), this.readUTF8(var3 + 6, var5), var102, var11[var78], var11[var78 + var89], var97);
            var3 += 10;
         }
      }

      if (var48 != null) {
         for(int var60 = 0; var60 < var48.length; ++var60) {
            if (this.readByte(var48[var60]) >> 1 == 32) {
               int var69 = this.a(var2, var48[var60]);
               this.a(var69 + 2, var5, true, var1.visitLocalVariableAnnotation(var2.i, var2.j, var2.l, var2.m, var2.n, this.readUTF8(var69, var5), true));
            }
         }
      }

      if (var51 != null) {
         for(int var61 = 0; var61 < var51.length; ++var61) {
            if (this.readByte(var51[var61]) >> 1 == 32) {
               int var70 = this.a(var2, var51[var61]);
               this.a(var70 + 2, var5, true, var1.visitLocalVariableAnnotation(var2.i, var2.j, var2.l, var2.m, var2.n, this.readUTF8(var70, var5), false));
            }
         }
      }

      while(var26 != null) {
         Attribute var62 = var26.a;
         var26.a = null;
         var1.visitAttribute(var26);
         var26 = var62;
      }

      var1.visitMaxs(var6, var7);
   }

   private int[] a(MethodVisitor var1, Context var2, int var3, boolean var4) {
      char[] var5 = var2.c;
      int[] var6 = new int[this.readUnsignedShort(var3)];
      var3 += 2;

      for(int var7 = 0; var7 < var6.length; ++var7) {
         var6[var7] = var3;
         int var8 = this.readInt(var3);
         switch (var8 >>> 24) {
            case 0:
            case 1:
            case 22:
               var3 += 2;
               break;
            case 19:
            case 20:
            case 21:
               ++var3;
               break;
            case 64:
            case 65:
               for(int var9 = this.readUnsignedShort(var3 + 1); var9 > 0; --var9) {
                  int var10 = this.readUnsignedShort(var3 + 3);
                  int var11 = this.readUnsignedShort(var3 + 5);
                  this.readLabel(var10, var2.h);
                  this.readLabel(var10 + var11, var2.h);
                  var3 += 6;
               }

               var3 += 3;
               break;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
               var3 += 4;
               break;
            default:
               var3 += 3;
         }

         int var15 = this.readByte(var3);
         if (var8 >>> 24 == 66) {
            TypePath var16 = var15 == 0 ? null : new TypePath(this.b, var3);
            var3 += 1 + 2 * var15;
            var3 = this.a(var3 + 2, var5, true, var1.visitTryCatchAnnotation(var8, var16, this.readUTF8(var3, var5), var4));
         } else {
            var3 = this.a(var3 + 3 + 2 * var15, var5, true, (AnnotationVisitor)null);
         }
      }

      return var6;
   }

   private int a(Context var1, int var2) {
      int var3 = this.readInt(var2);
      switch (var3 >>> 24) {
         case 0:
         case 1:
         case 22:
            var3 &= -65536;
            var2 += 2;
            break;
         case 19:
         case 20:
         case 21:
            var3 &= -16777216;
            ++var2;
            break;
         case 64:
         case 65:
            var3 &= -16777216;
            int var4 = this.readUnsignedShort(var2 + 1);
            var1.l = new Label[var4];
            var1.m = new Label[var4];
            var1.n = new int[var4];
            var2 += 3;

            for(int var5 = 0; var5 < var4; ++var5) {
               int var6 = this.readUnsignedShort(var2);
               int var7 = this.readUnsignedShort(var2 + 2);
               var1.l[var5] = this.readLabel(var6, var1.h);
               var1.m[var5] = this.readLabel(var6 + var7, var1.h);
               var1.n[var5] = this.readUnsignedShort(var2 + 4);
               var2 += 6;
            }
            break;
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
            var3 &= -16776961;
            var2 += 4;
            break;
         default:
            var3 &= var3 >>> 24 < 67 ? -256 : -16777216;
            var2 += 3;
      }

      int var10 = this.readByte(var2);
      var1.i = var3;
      var1.j = var10 == 0 ? null : new TypePath(this.b, var2);
      return var2 + 1 + 2 * var10;
   }

   private void b(MethodVisitor var1, Context var2, int var3, boolean var4) {
      int var5 = this.b[var3++] & 255;
      int var6 = Type.getArgumentTypes(var2.g).length - var5;

      int var7;
      for(var7 = 0; var7 < var6; ++var7) {
         AnnotationVisitor var8 = var1.visitParameterAnnotation(var7, "Ljava/lang/Synthetic;", false);
         if (var8 != null) {
            var8.visitEnd();
         }
      }

      for(char[] var9 = var2.c; var7 < var5 + var6; ++var7) {
         int var10 = this.readUnsignedShort(var3);

         for(var3 += 2; var10 > 0; --var10) {
            AnnotationVisitor var12 = var1.visitParameterAnnotation(var7, this.readUTF8(var3, var9), var4);
            var3 = this.a(var3 + 2, var9, true, var12);
         }
      }

   }

   private int a(int var1, char[] var2, boolean var3, AnnotationVisitor var4) {
      int var5 = this.readUnsignedShort(var1);
      var1 += 2;
      if (var3) {
         while(var5 > 0) {
            var1 = this.a(var1 + 2, var2, this.readUTF8(var1, var2), var4);
            --var5;
         }
      } else {
         while(var5 > 0) {
            var1 = this.a(var1, var2, (String)null, var4);
            --var5;
         }
      }

      if (var4 != null) {
         var4.visitEnd();
      }

      return var1;
   }

   private int a(int param1, char[] param2, String param3, AnnotationVisitor param4) {
      // $FF: Couldn't be decompiled
   }

   private void a(Context var1) {
      String var2 = var1.g;
      Object[] var3 = var1.s;
      int var4 = 0;
      if ((var1.e & 8) == 0) {
         if ("<init>".equals(var1.f)) {
            var3[var4++] = Opcodes.UNINITIALIZED_THIS;
         } else {
            var3[var4++] = this.readClass(this.header + 2, var1.c);
         }
      }

      int var5 = 1;

      while(true) {
         int var6 = var5;
         switch (var2.charAt(var5++)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
               var3[var4++] = Opcodes.INTEGER;
               break;
            case 'D':
               var3[var4++] = Opcodes.DOUBLE;
               break;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            default:
               var1.q = var4;
               return;
            case 'F':
               var3[var4++] = Opcodes.FLOAT;
               break;
            case 'J':
               var3[var4++] = Opcodes.LONG;
               break;
            case 'L':
               while(var2.charAt(var5) != ';') {
                  ++var5;
               }

               var3[var4++] = var2.substring(var6 + 1, var5++);
               break;
            case '[':
               while(var2.charAt(var5) == '[') {
                  ++var5;
               }

               if (var2.charAt(var5) == 'L') {
                  ++var5;

                  while(var2.charAt(var5) != ';') {
                     ++var5;
                  }
               }

               int var9 = var4++;
               ++var5;
               var3[var9] = var2.substring(var6, var5);
         }
      }
   }

   private int a(int var1, boolean var2, boolean var3, Context var4) {
      char[] var5 = var4.c;
      Label[] var6 = var4.h;
      int var7;
      if (var2) {
         var7 = this.b[var1++] & 255;
      } else {
         var7 = 255;
         var4.o = -1;
      }

      var4.r = 0;
      int var8;
      if (var7 < 64) {
         var8 = var7;
         var4.p = 3;
         var4.t = 0;
      } else if (var7 < 128) {
         var8 = var7 - 64;
         var1 = this.a(var4.u, 0, var1, var5, var6);
         var4.p = 4;
         var4.t = 1;
      } else {
         var8 = this.readUnsignedShort(var1);
         var1 += 2;
         if (var7 == 247) {
            var1 = this.a(var4.u, 0, var1, var5, var6);
            var4.p = 4;
            var4.t = 1;
         } else if (var7 >= 248 && var7 < 251) {
            var4.p = 2;
            var4.r = 251 - var7;
            var4.q -= var4.r;
            var4.t = 0;
         } else if (var7 == 251) {
            var4.p = 3;
            var4.t = 0;
         } else if (var7 < 255) {
            int var9 = var3 ? var4.q : 0;

            for(int var10 = var7 - 251; var10 > 0; --var10) {
               var1 = this.a(var4.s, var9++, var1, var5, var6);
            }

            var4.p = 1;
            var4.r = var7 - 251;
            var4.q += var4.r;
            var4.t = 0;
         } else {
            var4.p = 0;
            int var12 = this.readUnsignedShort(var1);
            var1 += 2;
            var4.r = var12;
            var4.q = var12;

            for(int var14 = 0; var12 > 0; --var12) {
               var1 = this.a(var4.s, var14++, var1, var5, var6);
            }

            var12 = this.readUnsignedShort(var1);
            var1 += 2;
            var4.t = var12;

            for(int var15 = 0; var12 > 0; --var12) {
               var1 = this.a(var4.u, var15++, var1, var5, var6);
            }
         }
      }

      var4.o += var8 + 1;
      this.readLabel(var4.o, var6);
      return var1;
   }

   private int a(Object[] var1, int var2, int var3, char[] var4, Label[] var5) {
      int var6 = this.b[var3++] & 255;
      switch (var6) {
         case 0:
            var1[var2] = Opcodes.TOP;
            break;
         case 1:
            var1[var2] = Opcodes.INTEGER;
            break;
         case 2:
            var1[var2] = Opcodes.FLOAT;
            break;
         case 3:
            var1[var2] = Opcodes.DOUBLE;
            break;
         case 4:
            var1[var2] = Opcodes.LONG;
            break;
         case 5:
            var1[var2] = Opcodes.NULL;
            break;
         case 6:
            var1[var2] = Opcodes.UNINITIALIZED_THIS;
            break;
         case 7:
            var1[var2] = this.readClass(var3, var4);
            var3 += 2;
            break;
         default:
            var1[var2] = this.readLabel(this.readUnsignedShort(var3), var5);
            var3 += 2;
      }

      return var3;
   }

   protected Label readLabel(int var1, Label[] var2) {
      if (var2[var1] == null) {
         var2[var1] = new Label();
      }

      return var2[var1];
   }

   private int a() {
      int var1 = this.header + 8 + this.readUnsignedShort(this.header + 6) * 2;

      for(int var2 = this.readUnsignedShort(var1); var2 > 0; --var2) {
         for(int var3 = this.readUnsignedShort(var1 + 8); var3 > 0; --var3) {
            var1 += 6 + this.readInt(var1 + 12);
         }

         var1 += 8;
      }

      var1 += 2;

      for(int var5 = this.readUnsignedShort(var1); var5 > 0; --var5) {
         for(int var6 = this.readUnsignedShort(var1 + 8); var6 > 0; --var6) {
            var1 += 6 + this.readInt(var1 + 12);
         }

         var1 += 8;
      }

      return var1 + 2;
   }

   private Attribute a(Attribute[] var1, String var2, int var3, int var4, char[] var5, int var6, Label[] var7) {
      for(int var8 = 0; var8 < var1.length; ++var8) {
         if (var1[var8].type.equals(var2)) {
            return var1[var8].read(this, var3, var4, var5, var6, var7);
         }
      }

      return (new Attribute(var2)).read(this, var3, var4, (char[])null, -1, (Label[])null);
   }

   public int getItemCount() {
      return this.a.length;
   }

   public int getItem(int var1) {
      return this.a[var1];
   }

   public int getMaxStringLength() {
      return this.d;
   }

   public int readByte(int var1) {
      return this.b[var1] & 255;
   }

   public int readUnsignedShort(int var1) {
      byte[] var2 = this.b;
      return (var2[var1] & 255) << 8 | var2[var1 + 1] & 255;
   }

   public short readShort(int var1) {
      byte[] var2 = this.b;
      return (short)((var2[var1] & 255) << 8 | var2[var1 + 1] & 255);
   }

   public int readInt(int var1) {
      byte[] var2 = this.b;
      return (var2[var1] & 255) << 24 | (var2[var1 + 1] & 255) << 16 | (var2[var1 + 2] & 255) << 8 | var2[var1 + 3] & 255;
   }

   public long readLong(int var1) {
      long var2 = (long)this.readInt(var1);
      long var4 = (long)this.readInt(var1 + 4) & 4294967295L;
      return var2 << 32 | var4;
   }

   public String readUTF8(int var1, char[] var2) {
      int var3 = this.readUnsignedShort(var1);
      if (var1 != 0 && var3 != 0) {
         String var4 = this.c[var3];
         if (var4 != null) {
            return var4;
         } else {
            var1 = this.a[var3];
            return this.c[var3] = this.a(var1 + 2, this.readUnsignedShort(var1), var2);
         }
      } else {
         return null;
      }
   }

   private String a(int var1, int var2, char[] var3) {
      int var4 = var1 + var2;
      byte[] var5 = this.b;
      int var6 = 0;
      byte var7 = 0;
      char var8 = 0;

      while(var1 < var4) {
         int var9 = var5[var1++];
         switch (var7) {
            case 0:
               var9 &= 255;
               if (var9 < 128) {
                  var3[var6++] = (char)var9;
               } else {
                  if (var9 < 224 && var9 > 191) {
                     var8 = (char)(var9 & 31);
                     var7 = 1;
                     continue;
                  }

                  var8 = (char)(var9 & 15);
                  var7 = 2;
               }
               break;
            case 1:
               var3[var6++] = (char)(var8 << 6 | var9 & 63);
               var7 = 0;
               break;
            case 2:
               var8 = (char)(var8 << 6 | var9 & 63);
               var7 = 1;
         }
      }

      return new String(var3, 0, var6);
   }

   public String readClass(int var1, char[] var2) {
      return this.readUTF8(this.a[this.readUnsignedShort(var1)], var2);
   }

   public Object readConst(int var1, char[] var2) {
      int var3 = this.a[var1];
      switch (this.b[var3 - 1]) {
         case 3:
            return new Integer(this.readInt(var3));
         case 4:
            return new Float(Float.intBitsToFloat(this.readInt(var3)));
         case 5:
            return new Long(this.readLong(var3));
         case 6:
            return new Double(Double.longBitsToDouble(this.readLong(var3)));
         case 7:
            return Type.getObjectType(this.readUTF8(var3, var2));
         case 8:
            return this.readUTF8(var3, var2);
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         default:
            int var4 = this.readByte(var3);
            int[] var5 = this.a;
            int var6 = var5[this.readUnsignedShort(var3 + 1)];
            String var7 = this.readClass(var6, var2);
            var6 = var5[this.readUnsignedShort(var6 + 2)];
            String var8 = this.readUTF8(var6, var2);
            String var9 = this.readUTF8(var6 + 2, var2);
            return new Handle(var4, var7, var8, var9);
         case 16:
            return Type.getMethodType(this.readUTF8(var3, var2));
      }
   }
}
