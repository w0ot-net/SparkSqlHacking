package org.apache.derby.impl.services.bytecode;

import java.io.IOException;
import java.util.Vector;
import org.apache.derby.iapi.services.classfile.ClassFormatOutput;
import org.apache.derby.iapi.services.classfile.ClassHolder;
import org.apache.derby.iapi.services.classfile.ClassMember;
import org.apache.derby.iapi.services.compiler.ClassBuilder;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;

class BCMethod implements MethodBuilder {
   static final int CODE_SPLIT_LENGTH = 65535;
   final BCClass cb;
   protected final ClassHolder modClass;
   final String myReturnType;
   private final String myName;
   BCLocalField[] parameters;
   private final String[] parameterTypes;
   Vector thrownExceptions;
   CodeChunk myCode;
   protected ClassMember myEntry;
   private int currentVarNum;
   private int statementNum;
   private boolean handlingOverflow;
   private int subMethodCount;
   private Type[] stackTypes = new Type[8];
   private int stackTypeOffset;
   int maxStack;
   private int stackDepth;
   private Conditional condition;
   private static final byte[] newArrayElementTypeMap = new byte[]{8, 9, 10, 11, 6, 7, 5};
   static final byte T_BOOLEAN = 4;

   BCMethod(ClassBuilder var1, String var2, String var3, int var4, String[] var5, BCJava var6) {
      this.cb = (BCClass)var1;
      this.modClass = this.cb.modify();
      this.myReturnType = var2;
      this.myName = var3;
      if ((var4 & 8) == 0) {
         this.currentVarNum = 1;
      }

      String[] var7;
      if (var5 != null && var5.length != 0) {
         int var8 = var5.length;
         var7 = new String[var8];
         this.parameters = new BCLocalField[var8];

         for(int var9 = 0; var9 < var8; ++var9) {
            Type var10 = var6.type(var5[var9]);
            this.parameters[var9] = new BCLocalField(var10, this.currentVarNum);
            this.currentVarNum += var10.width();
            var7[var9] = var10.vmName();
         }
      } else {
         var7 = BCMethodDescriptor.EMPTY;
      }

      String var11 = BCMethodDescriptor.get(var7, var6.type(var2).vmName(), var6);
      this.myEntry = this.modClass.addMember(var3, var11, var4);
      this.myCode = new CodeChunk(this.cb);
      this.parameterTypes = var5;
   }

   public String getName() {
      return this.myName;
   }

   public void getParameter(int var1) {
      int var2 = this.parameters[var1].cpi;
      short var3 = this.parameters[var1].type.vmType();
      if (var2 < 4) {
         this.myCode.addInstr((short)(CodeChunk.LOAD_VARIABLE_FAST[var3] + var2));
      } else {
         this.myCode.addInstrWide(CodeChunk.LOAD_VARIABLE[var3], var2);
      }

      this.growStack(this.parameters[var1].type);
   }

   public void addThrownException(String var1) {
      if (this.thrownExceptions == null) {
         this.thrownExceptions = new Vector();
      }

      this.thrownExceptions.add(var1);
   }

   public void complete() {
      if (this.myCode.getPC() > 65535) {
         this.splitMethod();
      }

      this.writeExceptions();
      this.myCode.complete(this, this.modClass, this.myEntry, this.maxStack, this.currentVarNum);
   }

   private void splitMethod() {
      int var1 = 0;
      boolean var2 = true;

      for(int var3 = this.myCode.getPC(); this.cb.limitMsg == null && var3 > 65535; var3 = this.myCode.getPC()) {
         int var4 = var3 - var1;
         int var5;
         if (var3 < 131070) {
            var5 = var3 - '\uffff';
         } else {
            var5 = 65534;
         }

         if (var5 > var4) {
            var5 = var4;
         }

         if (var2) {
            var1 = this.myCode.splitZeroStack(this, this.modClass, var1, var5);
         } else {
            var1 = this.myCode.splitExpressionOut(this, this.modClass, var5, this.maxStack);
         }

         if (var1 < 0) {
            if (!var2) {
               break;
            }

            var2 = false;
            var1 = 0;
         }
      }

   }

   ClassHolder constantPool() {
      return this.modClass;
   }

   protected void writeExceptions() {
      if (this.thrownExceptions != null) {
         int var1 = this.thrownExceptions.size();
         if (var1 != 0) {
            try {
               ClassFormatOutput var2 = new ClassFormatOutput(var1 * 2 + 2);
               var2.putU2(var1);

               for(int var3 = 0; var3 < var1; ++var3) {
                  String var4 = ((String)this.thrownExceptions.get(var3)).toString();
                  int var5 = this.modClass.addClassReference(var4);
                  var2.putU2(var5);
               }

               this.myEntry.addAttribute("Exceptions", var2);
            } catch (IOException var6) {
            }
         }

      }
   }

   private void growStack(int var1, Type var2) {
      this.stackDepth += var1;
      if (this.stackDepth > this.maxStack) {
         this.maxStack = this.stackDepth;
      }

      if (this.stackTypeOffset >= this.stackTypes.length) {
         Type[] var3 = new Type[this.stackTypes.length + 8];
         System.arraycopy(this.stackTypes, 0, var3, 0, this.stackTypes.length);
         this.stackTypes = var3;
      }

      this.stackTypes[this.stackTypeOffset++] = var2;
   }

   private void growStack(Type var1) {
      this.growStack(var1.width(), var1);
   }

   private Type popStack() {
      --this.stackTypeOffset;
      Type var1 = this.stackTypes[this.stackTypeOffset];
      this.stackDepth -= var1.width();
      return var1;
   }

   private Type[] copyStack() {
      Type[] var1 = new Type[this.stackTypeOffset];
      System.arraycopy(this.stackTypes, 0, var1, 0, this.stackTypeOffset);
      return var1;
   }

   public void pushThis() {
      this.myCode.addInstr((short)42);
      this.growStack(1, this.cb.classType);
   }

   public void push(byte var1) {
      this.push(var1, Type.BYTE);
   }

   public void push(boolean var1) {
      this.push(var1 ? 1 : 0, Type.BOOLEAN);
   }

   public void push(short var1) {
      this.push(var1, Type.SHORT);
   }

   public void push(int var1) {
      this.push(var1, Type.INT);
   }

   public void dup() {
      Type var1 = this.popStack();
      this.myCode.addInstr((short)(var1.width() == 2 ? 92 : 89));
      this.growStack(var1);
      this.growStack(var1);
   }

   public void swap() {
      Type var1 = this.popStack();
      Type var2 = this.popStack();
      this.growStack(var1);
      this.growStack(var2);
      if (var1.width() == 1) {
         if (var2.width() == 1) {
            this.myCode.addInstr((short)95);
            return;
         }

         this.myCode.addInstr((short)91);
         this.myCode.addInstr((short)87);
      } else if (var2.width() == 1) {
         this.myCode.addInstr((short)93);
         this.myCode.addInstr((short)88);
      } else {
         this.myCode.addInstr((short)94);
         this.myCode.addInstr((short)88);
      }

      this.growStack(var1);
      this.popStack();
   }

   private void push(int var1, Type var2) {
      CodeChunk var3 = this.myCode;
      if (var1 >= -1 && var1 <= 5) {
         var3.addInstr((short)(3 + var1));
      } else if (var1 >= -128 && var1 <= 127) {
         var3.addInstrU1((short)16, var1);
      } else if (var1 >= -32768 && var1 <= 32767) {
         var3.addInstrU2((short)17, var1);
      } else {
         int var4 = this.modClass.addConstant(var1);
         this.addInstrCPE((short)18, var4);
      }

      this.growStack(var2.width(), var2);
   }

   public void push(long var1) {
      CodeChunk var3 = this.myCode;
      if (var1 != 0L && var1 != 1L) {
         if (var1 >= -2147483648L && var1 <= 2147483647L) {
            this.push((int)var1, Type.LONG);
            var3.addInstr((short)133);
            return;
         }

         int var5 = this.modClass.addConstant(var1);
         var3.addInstrU2((short)20, var5);
      } else {
         int var4 = var1 == 0L ? 9 : 10;
         var3.addInstr((short)var4);
      }

      this.growStack(2, Type.LONG);
   }

   public void push(float var1) {
      CodeChunk var2 = this.myCode;
      if ((double)var1 == (double)0.0F) {
         var2.addInstr((short)11);
      } else if ((double)var1 == (double)1.0F) {
         var2.addInstr((short)12);
      } else if ((double)var1 == (double)2.0F) {
         var2.addInstr((short)13);
      } else {
         int var3 = this.modClass.addConstant(var1);
         this.addInstrCPE((short)18, var3);
      }

      this.growStack(1, Type.FLOAT);
   }

   public void push(double var1) {
      CodeChunk var3 = this.myCode;
      if (var1 == (double)0.0F) {
         var3.addInstr((short)14);
      } else {
         int var4 = this.modClass.addConstant(var1);
         var3.addInstrU2((short)20, var4);
      }

      this.growStack(2, Type.DOUBLE);
   }

   public void push(String var1) {
      int var2 = this.modClass.addConstant(var1);
      this.addInstrCPE((short)18, var2);
      this.growStack(1, Type.STRING);
   }

   public void methodReturn() {
      short var1;
      if (this.stackDepth != 0) {
         Type var2 = this.popStack();
         var1 = CodeChunk.RETURN_OPCODE[var2.vmType()];
      } else {
         var1 = 177;
      }

      this.myCode.addInstr(var1);
   }

   public Object describeMethod(short var1, String var2, String var3, String var4) {
      Type var5 = this.cb.factory.type(var4);
      String var6 = BCMethodDescriptor.get(BCMethodDescriptor.EMPTY, var5.vmName(), this.cb.factory);
      if (var2 == null && var1 != 184) {
         Type var7 = this.stackTypes[this.stackTypeOffset - 1];
         if (var2 == null) {
            var2 = var7.javaName();
         }
      }

      int var8 = this.modClass.addMethodReference(var2, var3, var6, var1 == 185);
      return new BCMethodCaller(var1, var5, var8);
   }

   public int callMethod(Object var1) {
      this.popStack();
      BCMethodCaller var2 = (BCMethodCaller)var1;
      int var3 = var2.cpi;
      short var4 = var2.opcode;
      if (var4 == 185) {
         this.myCode.addInstrU2U1U1(var4, var3, (short)1, (short)0);
      } else {
         this.myCode.addInstrU2(var4, var3);
      }

      Type var5 = var2.type;
      int var6 = var5.width();
      if (var6 != 0) {
         this.growStack(var6, var5);
      } else {
         this.overflowMethodCheck();
      }

      return var3;
   }

   public int callMethod(short var1, String var2, String var3, String var4, int var5) {
      Type var6 = this.cb.factory.type(var4);
      int var7 = this.stackDepth;
      Object var8 = null;
      String[] var9;
      if (var5 == 0) {
         var9 = BCMethodDescriptor.EMPTY;
      } else {
         var9 = new String[var5];

         for(int var10 = var5 - 1; var10 >= 0; --var10) {
            Type var11 = this.popStack();
            var9[var10] = var11.vmName();
         }
      }

      String var15 = BCMethodDescriptor.get(var9, var6.vmName(), this.cb.factory);
      Type var16 = null;
      if (var1 != 184) {
         var16 = this.popStack();
      }

      Type var12 = this.vmNameDeclaringClass(var2);
      if (var12 != null) {
         var16 = var12;
      }

      int var13 = this.modClass.addMethodReference(var16.vmNameSimple, var3, var15, var1 == 185);
      if (var1 == 185) {
         short var14 = (short)(var7 - this.stackDepth);
         this.myCode.addInstrU2U1U1(var1, var13, var14, (short)0);
      } else {
         this.myCode.addInstrU2(var1, var13);
      }

      int var17 = var6.width();
      if (var17 != 0) {
         this.growStack(var17, var6);
      } else {
         this.overflowMethodCheck();
      }

      return var13;
   }

   private Type vmNameDeclaringClass(String var1) {
      return var1 == null ? null : this.cb.factory.type(var1);
   }

   public void callSuper() {
      this.pushThis();
      this.callMethod((short)183, this.cb.getSuperClassName(), "<init>", "void", 0);
   }

   public void pushNewStart(String var1) {
      int var2 = this.modClass.addClassReference(var1);
      this.myCode.addInstrU2((short)187, var2);
      this.myCode.addInstr((short)89);
      Type var3 = this.cb.factory.type(var1);
      this.growStack(1, var3);
      this.growStack(1, var3);
   }

   public void pushNewComplete(int var1) {
      this.callMethod((short)183, (String)null, "<init>", "void", var1);
   }

   public void upCast(String var1) {
      Type var2 = this.cb.factory.type(var1);
      this.stackTypes[this.stackTypeOffset - 1] = var2;
   }

   public void cast(String var1) {
      Type var2 = this.stackTypes[this.stackTypeOffset - 1];
      short var3 = var2.vmType();
      if (var3 != 7 || !var1.equals(var2.javaName())) {
         Type var4 = this.cb.factory.type(var1);
         this.popStack();
         short var5 = var4.vmType();
         if (var3 == 7) {
            int var6 = this.modClass.addClassReference(var4.vmNameSimple);
            this.myCode.addInstrU2((short)192, var6);
         } else {
            short var8 = 0;

            while(var3 != var5 && var8 != -999) {
               short[] var7 = CodeChunk.CAST_CONVERSION_INFO[var3][var5];
               var3 = var7[1];
               var8 = var7[0];
               if (var8 != 0) {
                  this.myCode.addInstr(var8);
               }
            }
         }

         this.growStack(var4);
      }
   }

   public void isInstanceOf(String var1) {
      int var2 = this.modClass.addClassReference(var1);
      this.myCode.addInstrU2((short)193, var2);
      this.popStack();
      this.growStack(1, Type.BOOLEAN);
   }

   public void pushNull(String var1) {
      this.myCode.addInstr((short)1);
      this.growStack(1, this.cb.factory.type(var1));
   }

   public void getField(LocalField var1) {
      BCLocalField var2 = (BCLocalField)var1;
      Type var3 = var2.type;
      this.pushThis();
      this.myCode.addInstrU2((short)180, var2.cpi);
      this.popStack();
      this.growStack(var3);
   }

   public void getField(String var1, String var2, String var3) {
      Type var4 = this.popStack();
      Type var5 = this.vmNameDeclaringClass(var1);
      if (var5 != null) {
         var4 = var5;
      }

      this.getField((short)180, var4.vmNameSimple, var2, var3);
   }

   public void getStaticField(String var1, String var2, String var3) {
      this.getField((short)178, var1, var2, var3);
   }

   private void getField(short var1, String var2, String var3, String var4) {
      Type var5 = this.cb.factory.type(var4);
      int var6 = this.modClass.addFieldReference(this.vmNameDeclaringClass(var2).vmNameSimple, var3, var5.vmName());
      this.myCode.addInstrU2(var1, var6);
      this.growStack(var5);
   }

   public void setField(LocalField var1) {
      BCLocalField var2 = (BCLocalField)var1;
      this.putField(var2.type, var2.cpi, false);
      this.overflowMethodCheck();
   }

   public void putField(LocalField var1) {
      BCLocalField var2 = (BCLocalField)var1;
      this.putField(var2.type, var2.cpi, true);
   }

   public void putField(String var1, String var2) {
      Type var3 = this.cb.factory.type(var2);
      int var4 = this.modClass.addFieldReference(this.cb.classType.vmNameSimple, var1, var3.vmName());
      this.putField(var3, var4, true);
   }

   private void putField(Type var1, int var2, boolean var3) {
      if (var3) {
         this.myCode.addInstr((short)(var1.width() == 2 ? 92 : 89));
         this.growStack(var1);
      }

      this.pushThis();
      this.swap();
      this.myCode.addInstrU2((short)181, var2);
      this.popStack();
      this.popStack();
   }

   public void putField(String var1, String var2, String var3) {
      Type var4 = this.popStack();
      Type var5 = this.popStack();
      this.myCode.addInstr((short)(var4.width() == 2 ? 93 : 90));
      this.growStack(var4);
      this.growStack(var5);
      this.growStack(var4);
      Type var6 = this.vmNameDeclaringClass(var1);
      if (var6 != null) {
         var5 = var6;
      }

      Type var7 = this.cb.factory.type(var3);
      int var8 = this.modClass.addFieldReference(var5.vmNameSimple, var2, var7.vmName());
      this.myCode.addInstrU2((short)181, var8);
      this.popStack();
      this.popStack();
   }

   public void conditionalIfNull() {
      this.conditionalIf((short)199);
   }

   public void conditionalIf() {
      this.conditionalIf((short)153);
   }

   private void conditionalIf(short var1) {
      this.popStack();
      this.condition = new Conditional(this.condition, this.myCode, var1, this.copyStack());
   }

   public void startElseCode() {
      Type[] var1 = this.condition.startElse(this, this.myCode, this.copyStack());

      for(int var2 = this.stackDepth = 0; var2 < var1.length; ++var2) {
         this.stackDepth += (this.stackTypes[var2] = var1[var2]).width();
      }

      this.stackTypeOffset = var1.length;
   }

   public void completeConditional() {
      this.condition = this.condition.end(this, this.myCode, this.stackTypes, this.stackTypeOffset);
   }

   public void pop() {
      Type var1 = this.popStack();
      this.myCode.addInstr((short)(var1.width() == 2 ? 88 : 87));
      this.overflowMethodCheck();
   }

   public void endStatement() {
      if (this.stackDepth != 0) {
         this.pop();
      }

   }

   public void getArrayElement(int var1) {
      this.push(var1);
      this.popStack();
      Type var2 = this.popStack();
      String var3 = var2.javaName();
      String var4 = var3.substring(0, var3.length() - 2);
      Type var5 = this.cb.factory.type(var4);
      short var6 = var5.vmType();
      if (var6 == 2 && var5.vmName().equals("Z")) {
         var6 = 0;
      }

      this.myCode.addInstr(CodeChunk.ARRAY_ACCESS[var6]);
      this.growStack(var5);
   }

   public void setArrayElement(int var1) {
      this.push(var1);
      this.swap();
      Type var2 = this.popStack();
      this.popStack();
      this.popStack();
      short var3 = var2.vmType();
      if (var3 == 2 && var2.vmName().equals("Z")) {
         var3 = 0;
      }

      this.myCode.addInstr(CodeChunk.ARRAY_STORE[var3]);
   }

   public void pushNewArray(String var1, int var2) {
      this.push(var2);
      this.popStack();
      Type var3 = this.cb.factory.type(var1);
      if (var3.vmType() == 7) {
         int var4 = this.modClass.addClassReference(var3.javaName());
         this.myCode.addInstrU2((short)189, var4);
      } else {
         byte var5;
         if (var3.vmType() == 2 && 'Z' == var3.vmName().charAt(0)) {
            var5 = 4;
         } else {
            var5 = newArrayElementTypeMap[var3.vmType()];
         }

         this.myCode.addInstrU1((short)188, var5);
      }

      this.growStack(1, this.cb.factory.type(var1.concat("[]")));
   }

   private void addInstrCPE(short var1, int var2) {
      if (var2 >= 65535) {
         this.cb.addLimitExceeded(this, "constant_pool_count", 65535, var2);
      }

      this.myCode.addInstrCPE(var1, var2);
   }

   public boolean statementNumHitLimit(int var1) {
      if (this.statementNum > 2048) {
         return true;
      } else {
         this.statementNum += var1;
         return false;
      }
   }

   private void overflowMethodCheck() {
      if (this.stackDepth == 0) {
         if (!this.handlingOverflow) {
            if (this.condition == null) {
               int var1 = this.myCode.getPC();
               if (var1 >= 55000) {
                  if (this.parameters == null || this.parameters.length == 0) {
                     BCMethod var2 = this.getNewSubMethod(this.myReturnType, false);
                     this.handlingOverflow = true;
                     this.callSubMethod(var2);
                     this.methodReturn();
                     this.complete();
                     this.handlingOverflow = false;
                     this.myEntry = var2.myEntry;
                     this.myCode = var2.myCode;
                     this.currentVarNum = var2.currentVarNum;
                     this.statementNum = var2.statementNum;
                     this.stackTypes = var2.stackTypes;
                     this.stackTypeOffset = var2.stackTypeOffset;
                     this.maxStack = var2.maxStack;
                     this.stackDepth = var2.stackDepth;
                  }
               }
            }
         }
      }
   }

   final BCMethod getNewSubMethod(String var1, boolean var2) {
      int var3 = this.myEntry.getModifier();
      var3 &= -6;
      var3 |= 2;
      String var10000 = this.myName;
      String var4 = var10000 + "_s" + Integer.toString(this.subMethodCount++);
      BCMethod var5 = (BCMethod)this.cb.newMethodBuilder(var3, var1, var4, var2 ? this.parameterTypes : null);
      var5.thrownExceptions = this.thrownExceptions;
      return var5;
   }

   final void callSubMethod(BCMethod var1) {
      short var2;
      if ((this.myEntry.getModifier() & 8) == 0) {
         var2 = 182;
         this.pushThis();
      } else {
         var2 = 184;
      }

      int var3 = var1.parameters == null ? 0 : var1.parameters.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         this.getParameter(var4);
      }

      this.callMethod(var2, this.modClass.getName(), var1.getName(), var1.myReturnType, var3);
   }
}
