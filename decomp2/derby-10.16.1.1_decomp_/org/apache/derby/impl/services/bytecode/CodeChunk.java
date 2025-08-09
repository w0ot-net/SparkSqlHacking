package org.apache.derby.impl.services.bytecode;

import java.io.IOException;
import java.util.Arrays;
import org.apache.derby.iapi.services.classfile.CONSTANT_Index_info;
import org.apache.derby.iapi.services.classfile.CONSTANT_Utf8_info;
import org.apache.derby.iapi.services.classfile.ClassFormatOutput;
import org.apache.derby.iapi.services.classfile.ClassHolder;
import org.apache.derby.iapi.services.classfile.ClassMember;
import org.apache.derby.iapi.services.io.ArrayOutputStream;

final class CodeChunk {
   private static final int CODE_OFFSET = 8;
   static final short[] LOAD_VARIABLE = new short[]{21, 21, 21, 22, 23, 24, 21, 25};
   static final short[] LOAD_VARIABLE_FAST = new short[]{26, 26, 26, 30, 34, 38, 26, 42};
   static final short[] STORE_VARIABLE = new short[]{54, 54, 54, 55, 56, 57, 54, 58};
   static final short[] STORE_VARIABLE_FAST = new short[]{59, 59, 59, 63, 67, 71, 59, 75};
   static final short[] ARRAY_ACCESS = new short[]{51, 53, 46, 47, 48, 49, 52, 50};
   static final short[] ARRAY_STORE = new short[]{84, 86, 79, 80, 81, 82, 85, 83};
   static final short[] RETURN_OPCODE = new short[]{172, 172, 172, 173, 174, 175, 172, 176};
   static final short[][][] CAST_CONVERSION_INFO = new short[][][]{{{0, 0}, {0, 1}, {0, 2}, {0, 2}, {0, 2}, {0, 2}, {0, 6}, {-999, 7}}, {{0, 0}, {0, 1}, {0, 2}, {0, 2}, {0, 2}, {0, 2}, {0, 6}, {-999, 7}}, {{145, 0}, {147, 1}, {0, 2}, {133, 3}, {134, 4}, {135, 5}, {145, 6}, {-999, 7}}, {{136, 2}, {136, 2}, {136, 2}, {0, 3}, {137, 4}, {138, 5}, {136, 2}, {-999, 7}}, {{139, 2}, {139, 2}, {139, 2}, {140, 3}, {0, 4}, {141, 5}, {139, 2}, {-999, 7}}, {{142, 2}, {142, 2}, {142, 2}, {143, 3}, {144, 4}, {0, 5}, {142, 2}, {-999, 7}}, {{0, 0}, {0, 1}, {0, 2}, {0, 2}, {0, 2}, {0, 2}, {0, 6}, {-999, 7}}, {{-999, 0}, {-999, 1}, {-999, 2}, {-999, 3}, {-999, 4}, {-999, 5}, {-999, 6}, {0, 7}}};
   private static final byte[] push1_1i = new byte[]{1, 1};
   private static final byte[] push2_1i = new byte[]{2, 1};
   private static final byte[] NS = new byte[]{0, -1};
   private static final byte VARIABLE_STACK = -128;
   private static final byte[][] OPCODE_ACTION;
   private final int pcDelta;
   final BCClass cb;
   private final ClassFormatOutput cout;

   private void limitHit(IOException var1) {
      this.cb.addLimitExceeded(var1.toString());
   }

   void addInstr(short var1) {
      try {
         this.cout.putU1(var1);
      } catch (IOException var3) {
         this.limitHit(var3);
      }

   }

   void addInstrU2(short var1, int var2) {
      try {
         this.cout.putU1(var1);
         this.cout.putU2(var2);
      } catch (IOException var4) {
         this.limitHit(var4);
      }

   }

   void addInstrU4(short var1, int var2) {
      try {
         this.cout.putU1(var1);
         this.cout.putU4(var2);
      } catch (IOException var4) {
         this.limitHit(var4);
      }

   }

   void addInstrU1(short var1, int var2) {
      try {
         this.cout.putU1(var1);
         this.cout.putU1(var2);
      } catch (IOException var4) {
         this.limitHit(var4);
      }

   }

   void addInstrCPE(short var1, int var2) {
      if (var2 < 256) {
         this.addInstrU1(var1, var2);
      } else {
         this.addInstrU2((short)(var1 + 1), var2);
      }

   }

   void addInstrWide(short var1, int var2) {
      if (var2 < 256) {
         this.addInstrU1(var1, var2);
      } else {
         this.addInstr((short)196);
         this.addInstrU2(var1, var2);
      }

   }

   void addInstrU2U1U1(short var1, int var2, short var3, short var4) {
      try {
         this.cout.putU1(var1);
         this.cout.putU2(var2);
         this.cout.putU1(var3);
         this.cout.putU1(var4);
      } catch (IOException var6) {
         this.limitHit(var6);
      }

   }

   int getPC() {
      return this.cout.size() + this.pcDelta;
   }

   private static int instructionLength(short var0) {
      byte var1 = OPCODE_ACTION[var0][1];
      return var1;
   }

   CodeChunk(BCClass var1) {
      this.cb = var1;
      this.cout = new ClassFormatOutput();

      try {
         this.cout.putU2(0);
         this.cout.putU2(0);
         this.cout.putU4(0);
      } catch (IOException var3) {
         this.limitHit(var3);
      }

      this.pcDelta = -8;
   }

   private CodeChunk(CodeChunk var1, int var2, int var3) {
      this.cb = var1.cb;
      ArrayOutputStream var4 = new ArrayOutputStream(var1.cout.getData());

      try {
         var4.setPosition(8 + var2);
         var4.setLimit(var3);
      } catch (IOException var6) {
         this.limitHit(var6);
      }

      this.cout = new ClassFormatOutput(var4);
      this.pcDelta = var2;
   }

   private void fixLengths(BCMethod var1, int var2, int var3, int var4) {
      byte[] var5 = this.cout.getData();
      if (var1 != null && var2 > 65535) {
         this.cb.addLimitExceeded(var1, "max_stack", 65535, var2);
      }

      var5[0] = (byte)(var2 >> 8);
      var5[1] = (byte)var2;
      if (var1 != null && var3 > 65535) {
         this.cb.addLimitExceeded(var1, "max_locals", 65535, var3);
      }

      var5[2] = (byte)(var3 >> 8);
      var5[3] = (byte)var3;
      if (var1 != null && var4 > 65535) {
         this.cb.addLimitExceeded(var1, "code_length", 65535, var4);
      }

      var5[4] = (byte)(var4 >> 24);
      var5[5] = (byte)(var4 >> 16);
      var5[6] = (byte)(var4 >> 8);
      var5[7] = (byte)var4;
   }

   void complete(BCMethod var1, ClassHolder var2, ClassMember var3, int var4, int var5) {
      int var6 = this.getPC();
      ClassFormatOutput var7 = this.cout;

      try {
         var7.putU2(0);
         var7.putU2(0);
      } catch (IOException var9) {
         this.limitHit(var9);
      }

      this.fixLengths(var1, var4, var5, var6);
      var3.addAttribute("Code", var7);
   }

   short getOpcode(int var1) {
      return (short)(this.cout.getData()[8 + var1] & 255);
   }

   private int getU2(int var1) {
      byte[] var2 = this.cout.getData();
      int var3 = 8 + var1 + 1;
      return (var2[var3] & 255) << 8 | var2[var3 + 1] & 255;
   }

   private int getU4(int var1) {
      byte[] var2 = this.cout.getData();
      int var3 = 8 + var1 + 1;
      return (var2[var3] & 255) << 24 | (var2[var3 + 1] & 255) << 16 | (var2[var3 + 2] & 255) << 8 | var2[var3 + 3] & 255;
   }

   CodeChunk insertCodeSpace(int var1, int var2) {
      short var3 = this.getOpcode(var1);
      int var4 = instructionLength(var3);
      if (var2 > 0) {
         int var5 = this.getPC() - var1 - var4;

         for(int var6 = 0; var6 < var2; ++var6) {
            this.addInstr((short)0);
         }

         byte[] var9 = this.cout.getData();
         int var7 = 8 + var1 + var4;
         System.arraycopy(var9, var7, var9, var7 + var2, var5);
         Arrays.fill(var9, var7, var7 + var2, (byte)0);
      }

      var2 += var4;
      return new CodeChunk(this, var1, var2);
   }

   private int findMaxStack(ClassHolder var1, int var2, int var3) {
      int var4 = var2 + var3;
      int var5 = 0;
      int var6 = 0;

      while(var2 < var4) {
         short var7 = this.getOpcode(var2);
         int var8 = this.stackWordDelta(var1, var2, var7);
         var5 += var8;
         if (var5 > var6) {
            var6 = var5;
         }

         int[] var9 = this.findConditionalPCs(var2, var7);
         if (var9 != null && var9[3] != -1) {
            int var10 = this.findMaxStack(var1, var9[1], var9[2]);
            if (var5 + var10 > var6) {
               var6 = var5 + var10;
            }

            var2 = var9[3];
         } else {
            var2 += instructionLength(var7);
         }
      }

      return var6;
   }

   private int stackWordDelta(ClassHolder var1, int var2, short var3) {
      int var4 = OPCODE_ACTION[var3][0];
      if (var4 == -128) {
         var4 = this.getVariableStackDelta(var1, var2, var3);
      }

      return var4;
   }

   private String getTypeDescriptor(ClassHolder var1, int var2) {
      int var3 = this.getU2(var2);
      CONSTANT_Index_info var4 = (CONSTANT_Index_info)var1.getEntry(var3);
      int var5 = var4.getI2();
      var4 = (CONSTANT_Index_info)var1.getEntry(var5);
      int var6 = var4.getI2();
      CONSTANT_Utf8_info var7 = (CONSTANT_Utf8_info)var1.getEntry(var6);
      String var8 = var7.toString();
      return var8;
   }

   private static int getDescriptorWordCount(String var0) {
      byte var1;
      if ("D".equals(var0)) {
         var1 = 2;
      } else if ("J".equals(var0)) {
         var1 = 2;
      } else if (var0.charAt(0) == '(') {
         switch (var0.charAt(var0.length() - 1)) {
            case 'D':
            case 'J':
               var1 = 2;
               break;
            case 'V':
               var1 = 0;
               break;
            default:
               var1 = 1;
         }
      } else {
         var1 = 1;
      }

      return var1;
   }

   private int getVariableStackDelta(ClassHolder var1, int var2, int var3) {
      String var4 = this.getTypeDescriptor(var1, var2);
      int var5 = getDescriptorWordCount(var4);
      int var6 = 0;
      switch (var3) {
         case 178:
            var6 = var5;
            break;
         case 179:
            var6 = -var5;
            break;
         case 180:
            var6 = var5 - 1;
            break;
         case 181:
            var6 = -var5 - 1;
            break;
         case 182:
         case 183:
            var6 = -1;
         case 184:
            var6 += var5 - parameterWordCount(var4);
            break;
         case 185:
            var6 = var5 - this.getOpcode(var2 + 3);
            break;
         default:
            System.out.println("WHO IS THIS ");
      }

      return var6;
   }

   private static int parameterWordCount(String var0) {
      int var1 = 0;
      int var2 = 1;

      while(true) {
         label26:
         switch (var0.charAt(var2)) {
            case ')':
               return var1;
            case 'D':
            case 'J':
               var1 += 2;
               break;
            case '[':
               while(true) {
                  ++var2;
                  if (var0.charAt(var2) != '[') {
                     if (var0.charAt(var2) != 'L') {
                        ++var1;
                        break label26;
                     }
                     break;
                  }
               }
            case 'L':
               do {
                  ++var2;
               } while(var0.charAt(var2) != ';');

               ++var1;
               break;
            default:
               ++var1;
         }

         ++var2;
      }
   }

   private int[] findConditionalPCs(int var1, short var2) {
      switch (var2) {
         case 153:
         case 154:
         case 198:
         case 199:
            int var5 = this.getU2(var1);
            int var3;
            int var4;
            if (var5 == 8 && this.getOpcode(var1 + 3) == 200) {
               var3 = var1 + 3 + 5;
               var4 = var1 + 3 + this.getU4(var1 + 3);
            } else {
               var3 = var1 + 3;
               var4 = var1 + var5;
            }

            int var6 = -1;
            int var7 = var3;

            while(var7 < var4) {
               short var8 = this.getOpcode(var7);
               int[] var9 = this.findConditionalPCs(var7, var8);
               if (var9 != null) {
                  var7 = var9[5];
               } else if (var8 == 167) {
                  if (var7 == var4 - 3) {
                     var6 = var7 + this.getU2(var7);
                     break;
                  }
               } else if (var8 == 200) {
                  if (var7 == var4 - 5) {
                     var6 = var7 + this.getU4(var7);
                     break;
                  }
               } else {
                  var7 += instructionLength(var8);
               }
            }

            int var11;
            if (var6 == -1) {
               var6 = var4;
               var4 = -1;
               var11 = var6 - var3;
               var7 = -1;
            } else {
               var11 = var4 - var3;
               var7 = var6 - var4;
            }

            int[] var12 = new int[]{var1, var3, var11, var4, var7, var6};
            return var12;
         default:
            return null;
      }
   }

   final int splitZeroStack(BCMethod var1, ClassHolder var2, int var3, int var4) {
      int var5 = splitMinLength(var1);
      int var6 = 0;
      int var7 = -1;
      int var8 = -1;
      int var9 = this.getPC();
      int var10 = var3;

      while(var10 < var9) {
         short var11 = this.getOpcode(var10);
         int var12 = this.stackWordDelta(var2, var10, var11);
         var6 += var12;
         int[] var13 = this.findConditionalPCs(var10, var11);
         if (var13 != null) {
            if (var13[3] != -1) {
               var10 = var13[3];
               continue;
            }

            if (var8 == -1) {
               var8 = var13[5];
            }
         }

         var10 += instructionLength(var11);
         if (var8 != -1) {
            if (var10 > var8) {
               var8 = -1;
            }
         } else if (var6 == 0) {
            int var14 = var10 - var3;
            if (var14 >= var4) {
               if (var14 > 65534) {
                  var14 = -1;
               } else if (isReturn(var11)) {
                  var14 = -1;
               }

               if (var14 == -1) {
                  if (var7 == -1) {
                     return -1;
                  }

                  if (var7 <= var5) {
                     return -1;
                  }

                  var14 = var7;
               }

               BCMethod var15 = this.startSubMethod(var1, "void", var3, var14);
               return this.splitCodeIntoSubMethod(var1, var2, var15, var3, var14);
            }

            var7 = var14;
         }
      }

      return -1;
   }

   private BCMethod startSubMethod(BCMethod var1, String var2, int var3, int var4) {
      boolean var5 = this.usesParameters(var1, var3, var4);
      return var1.getNewSubMethod(var2, var5);
   }

   private boolean usesParameters(BCMethod var1, int var2, int var3) {
      if (var1.parameters == null) {
         return false;
      } else {
         boolean var4 = (var1.myEntry.getModifier() & 8) != 0;
         int var5 = var2 + var3;

         while(var2 < var5) {
            short var6 = this.getOpcode(var2);
            switch (var6) {
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
                  return true;
               case 26:
               case 30:
               case 34:
               case 38:
                  return true;
               case 27:
               case 31:
               case 35:
               case 39:
               case 43:
                  return true;
               case 28:
               case 32:
               case 36:
               case 40:
               case 44:
                  return true;
               case 29:
               case 33:
               case 37:
               case 41:
               case 45:
                  return true;
               case 42:
                  if (var4) {
                     return true;
                  }
               default:
                  var2 += instructionLength(var6);
            }
         }

         return false;
      }
   }

   private int splitCodeIntoSubMethod(BCMethod var1, ClassHolder var2, BCMethod var3, int var4, int var5) {
      CodeChunk var6 = var3.myCode;
      byte[] var7 = this.cout.getData();

      try {
         var6.cout.write(var7, 8 + var4, var5);
      } catch (IOException var9) {
         this.limitHit(var9);
      }

      if (var3.myReturnType.equals("void")) {
         var6.addInstr((short)177);
      } else {
         var6.addInstr((short)176);
      }

      if (this.cb.limitMsg != null) {
         return -1;
      } else {
         var3.maxStack = var6.findMaxStack(var2, 0, var6.getPC());
         var3.complete();
         return this.removePushedCode(var1, var2, var3, var4, var5);
      }
   }

   private int removePushedCode(BCMethod var1, ClassHolder var2, BCMethod var3, int var4, int var5) {
      int var6 = this.getPC();
      CodeChunk var7 = new CodeChunk(var1.cb);
      var1.myCode = var7;
      var1.maxStack = 0;
      byte[] var8 = this.cout.getData();
      if (var4 != 0) {
         try {
            var7.cout.write(var8, 8, var4);
         } catch (IOException var14) {
            this.limitHit(var14);
         }
      }

      var1.callSubMethod(var3);
      int var9 = var7.getPC();
      int var10 = var4 + var5;
      int var11 = var6 - var5 - var4;

      try {
         var7.cout.write(var8, 8 + var10, var11);
      } catch (IOException var13) {
         this.limitHit(var13);
      }

      if (this.cb.limitMsg != null) {
         return -1;
      } else {
         var1.maxStack = var7.findMaxStack(var2, 0, var7.getPC());
         return var9;
      }
   }

   final int splitExpressionOut(BCMethod var1, ClassHolder var2, int var3, int var4) {
      int var5 = -1;
      int var6 = -1;
      String var7 = null;
      int var8 = splitMinLength(var1);
      int[] var9 = new int[var4 + 1];
      int var10 = 0;
      byte var11 = -1;
      int var12 = this.getPC();
      int var13 = 0;

      while(var13 < var12) {
         short var14 = this.getOpcode(var13);
         int var15 = this.stackWordDelta(var2, var13, var14);
         var10 += var15;
         int[] var16 = this.findConditionalPCs(var13, var14);
         if (var16 != null) {
            return -1;
         }

         var13 += instructionLength(var14);
         if (var11 != -1) {
            if (var13 > var11) {
               var11 = -1;
            }
         } else {
            int var17 = var13 - instructionLength(var14);
            switch (var14) {
               case 0:
               case 87:
               case 88:
               case 119:
               case 139:
               case 143:
               case 190:
               case 192:
                  break;
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
               case 11:
               case 12:
               case 13:
               case 16:
               case 17:
               case 18:
               case 19:
               case 23:
               case 25:
               case 42:
               case 43:
               case 44:
               case 45:
                  var9[var10] = var17;
                  break;
               case 9:
               case 10:
               case 14:
               case 15:
               case 20:
               case 22:
               case 30:
               case 31:
               case 32:
               case 33:
                  var9[var10 - 1] = var9[var10] = var17;
                  break;
               case 21:
               case 24:
               case 26:
               case 27:
               case 28:
               case 29:
               case 34:
               case 35:
               case 36:
               case 37:
               case 38:
               case 39:
               case 40:
               case 41:
               case 46:
               case 47:
               case 48:
               case 49:
               case 50:
               case 51:
               case 52:
               case 53:
               case 54:
               case 55:
               case 56:
               case 57:
               case 58:
               case 59:
               case 60:
               case 61:
               case 62:
               case 63:
               case 64:
               case 65:
               case 66:
               case 67:
               case 68:
               case 69:
               case 70:
               case 71:
               case 72:
               case 73:
               case 74:
               case 75:
               case 76:
               case 77:
               case 78:
               case 79:
               case 80:
               case 81:
               case 82:
               case 83:
               case 84:
               case 85:
               case 86:
               case 89:
               case 90:
               case 91:
               case 92:
               case 93:
               case 94:
               case 96:
               case 97:
               case 98:
               case 99:
               case 100:
               case 101:
               case 102:
               case 103:
               case 104:
               case 105:
               case 106:
               case 107:
               case 108:
               case 109:
               case 110:
               case 111:
               case 112:
               case 113:
               case 114:
               case 115:
               case 116:
               case 117:
               case 118:
               case 120:
               case 121:
               case 122:
               case 123:
               case 124:
               case 125:
               case 126:
               case 127:
               case 128:
               case 129:
               case 130:
               case 131:
               case 132:
               case 134:
               case 135:
               case 136:
               case 137:
               case 138:
               case 140:
               case 141:
               case 142:
               case 144:
               case 145:
               case 146:
               case 147:
               case 148:
               case 149:
               case 150:
               case 151:
               case 152:
               case 153:
               case 154:
               case 155:
               case 156:
               case 157:
               case 158:
               case 159:
               case 160:
               case 161:
               case 162:
               case 163:
               case 164:
               case 165:
               case 166:
               case 167:
               case 168:
               case 169:
               case 170:
               case 171:
               case 172:
               case 173:
               case 174:
               case 175:
               case 176:
               case 177:
               case 178:
               case 179:
               case 181:
               case 183:
               case 184:
               case 186:
               case 187:
               case 188:
               case 189:
               case 191:
               default:
                  Arrays.fill(var9, 0, var10 + 1, -1);
                  break;
               case 95:
                  var9[var10] = var9[var10 - 1];
                  break;
               case 133:
                  var9[var10] = var9[var10 - 1];
                  break;
               case 180:
                  String var26 = this.getTypeDescriptor(var2, var17);
                  int var27 = getDescriptorWordCount(var26);
                  if (var27 == 2) {
                     var9[var10] = var9[var10 - 1];
                  }
                  break;
               case 182:
               case 185:
                  String var18 = this.getTypeDescriptor(var2, var17);
                  int var19 = getDescriptorWordCount(var18);
                  int var20;
                  if (var19 == 0) {
                     var20 = -1;
                  } else if (var19 == 1) {
                     var20 = var9[var10];
                  } else {
                     var20 = -1;
                     var9[var10] = var9[var10 - 1];
                  }

                  if (var20 != -1) {
                     int var21 = var13 - var20;
                     if (var21 > var8 && var21 <= 65534) {
                        int var22 = var18.lastIndexOf(41);
                        if (var18.charAt(var22 + 1) == 'L') {
                           String var23 = var18.substring(var22 + 2, var18.length() - 1);
                           var23 = var23.replace('/', '.');
                           if (var21 >= var3) {
                              BCMethod var24 = this.startSubMethod(var1, var23, var20, var21);
                              return this.splitCodeIntoSubMethod(var1, var2, var24, var20, var21);
                           }

                           if (var21 > var6) {
                              var5 = var20;
                              var6 = var21;
                              var7 = var23;
                           }
                        }
                     }
                  }
            }
         }
      }

      if (var6 != -1) {
         BCMethod var25 = this.startSubMethod(var1, var7, var5, var6);
         return this.splitCodeIntoSubMethod(var1, var2, var25, var5, var6);
      } else {
         return -1;
      }
   }

   private static boolean isReturn(short var0) {
      switch (var0) {
         case 172:
         case 173:
         case 174:
         case 175:
         case 176:
         case 177:
            return true;
         default:
            return false;
      }
   }

   private static int splitMinLength(BCMethod var0) {
      int var1 = 4;
      if (var0.parameters != null) {
         int var2 = var0.parameters.length;
         var1 += var2;
         if (var2 > 3) {
            var1 += var2 - 3;
         }
      }

      return var1;
   }

   static {
      OPCODE_ACTION = new byte[][]{{0, 1}, push1_1i, push1_1i, push1_1i, push1_1i, push1_1i, push1_1i, push1_1i, push1_1i, push2_1i, push2_1i, push1_1i, push1_1i, push1_1i, push2_1i, push2_1i, {1, 2}, {1, 3}, {1, 2}, {1, 3}, {2, 3}, {1, 2}, {2, 2}, {1, 2}, {2, 2}, {1, 2}, push1_1i, push1_1i, push1_1i, push1_1i, push2_1i, push2_1i, push2_1i, push2_1i, push1_1i, push1_1i, push1_1i, push1_1i, push2_1i, push2_1i, push2_1i, push2_1i, push1_1i, push1_1i, push1_1i, push1_1i, {-1, 1}, {0, 1}, {-1, 1}, {0, 1}, {-1, 1}, {-1, 1}, {-1, 1}, {-1, 1}, {-1, 2}, {-2, 2}, {-1, 2}, {-2, 2}, {-1, 2}, {-1, 1}, {-1, 1}, {-1, 1}, {-1, 1}, {-2, 1}, {-2, 1}, {-2, 1}, {-2, 1}, {-1, 1}, {-1, 1}, {-1, 1}, {-1, 1}, {-2, 1}, {-2, 1}, {-2, 1}, {-2, 1}, {-1, 1}, {-1, 1}, {-1, 1}, {-1, 1}, {-3, 1}, {-4, 1}, {-3, 1}, {-4, 1}, {-3, 1}, {-3, 1}, {-3, 1}, {-3, 1}, {-1, 1}, {-2, 1}, push1_1i, push1_1i, push1_1i, push2_1i, push2_1i, push2_1i, {0, 1}, NS, NS, {-1, 1}, {-2, 1}, NS, NS, {-1, 1}, {-2, 1}, NS, NS, {-1, 1}, {-2, 1}, NS, NS, {-1, 1}, {-2, 1}, {-1, 1}, {-2, 1}, {-1, 1}, {-2, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}, {-1, 1}, NS, NS, NS, NS, NS, {-1, 1}, NS, {-1, 1}, NS, NS, NS, NS, push1_1i, {0, 1}, push1_1i, {-1, 1}, {-1, 1}, {0, 1}, {0, 1}, push2_1i, push1_1i, {-1, 1}, {0, 1}, {-1, 1}, {0, 1}, {0, 1}, {0, 1}, NS, {-1, 1}, {-1, 1}, {-3, 1}, {-3, 1}, {-1, 3}, {-1, 3}, {-1, 3}, {-1, 3}, {-1, 3}, {-1, 3}, NS, NS, NS, NS, NS, NS, NS, NS, {0, 3}, NS, NS, NS, NS, {-1, 1}, {-2, 1}, {-1, 1}, {-2, 1}, {-1, 1}, {0, 1}, {-128, 3}, {-128, 3}, {-128, 3}, {-128, 3}, {-128, 3}, {-128, 3}, {-128, 3}, {-128, 5}, NS, {1, 3}, {0, 2}, {0, 3}, {0, 1}, NS, {0, 3}, {0, 3}, NS, NS, NS, NS, {-1, 3}, {-1, 3}, {0, 5}, NS, NS};
   }
}
