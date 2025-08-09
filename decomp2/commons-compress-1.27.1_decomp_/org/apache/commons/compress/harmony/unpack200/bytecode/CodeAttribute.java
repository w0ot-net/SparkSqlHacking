package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.Segment;

public class CodeAttribute extends BCIRenumberedAttribute {
   private static CPUTF8 attributeName;
   public List attributes = new ArrayList();
   public List byteCodeOffsets = new ArrayList();
   public List byteCodes = new ArrayList();
   public int codeLength;
   public List exceptionTable;
   public int maxLocals;
   public int maxStack;

   public static void setAttributeName(CPUTF8 attributeName) {
      CodeAttribute.attributeName = attributeName;
   }

   public CodeAttribute(int maxStack, int maxLocals, byte[] codePacked, Segment segment, OperandManager operandManager, List exceptionTable) {
      super(attributeName);
      this.maxLocals = maxLocals;
      this.maxStack = maxStack;
      this.codeLength = 0;
      this.exceptionTable = exceptionTable;
      this.byteCodeOffsets.add(0);
      int byteCodeIndex = 0;

      for(int i = 0; i < codePacked.length; ++i) {
         ByteCode byteCode = ByteCode.getByteCode(codePacked[i] & 255);
         byteCode.setByteCodeIndex(byteCodeIndex);
         ++byteCodeIndex;
         byteCode.extractOperands(operandManager, segment, this.codeLength);
         this.byteCodes.add(byteCode);
         this.codeLength += byteCode.getLength();
         int lastBytecodePosition = (Integer)this.byteCodeOffsets.get(this.byteCodeOffsets.size() - 1);
         if (byteCode.hasMultipleByteCodes()) {
            this.byteCodeOffsets.add(lastBytecodePosition + 1);
            ++byteCodeIndex;
         }

         if (i < codePacked.length - 1) {
            this.byteCodeOffsets.add(lastBytecodePosition + byteCode.getLength());
         }

         if (byteCode.getOpcode() == 196) {
            ++i;
         }
      }

      for(ByteCode byteCode : this.byteCodes) {
         byteCode.applyByteCodeTargetFixup(this);
      }

   }

   public void addAttribute(Attribute attribute) {
      this.attributes.add(attribute);
      if (attribute instanceof LocalVariableTableAttribute) {
         ((LocalVariableTableAttribute)attribute).setCodeLength(this.codeLength);
      }

      if (attribute instanceof LocalVariableTypeTableAttribute) {
         ((LocalVariableTypeTableAttribute)attribute).setCodeLength(this.codeLength);
      }

   }

   protected int getLength() {
      int attributesSize = 0;

      for(Attribute attribute : this.attributes) {
         attributesSize += attribute.getLengthIncludingHeader();
      }

      return 8 + this.codeLength + 2 + this.exceptionTable.size() * 8 + 2 + attributesSize;
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      List<ClassFileEntry> nestedEntries = new ArrayList(this.attributes.size() + this.byteCodes.size() + 10);
      nestedEntries.add(this.getAttributeName());
      nestedEntries.addAll(this.byteCodes);
      nestedEntries.addAll(this.attributes);

      for(ExceptionTableEntry entry : this.exceptionTable) {
         CPClass catchType = entry.getCatchType();
         if (catchType != null) {
            nestedEntries.add(catchType);
         }
      }

      return (ClassFileEntry[])nestedEntries.toArray(NONE);
   }

   protected int[] getStartPCs() {
      return null;
   }

   public void renumber(List byteCodeOffsets) {
      this.exceptionTable.forEach((entry) -> entry.renumber(byteCodeOffsets));
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);
      this.attributes.forEach((attribute) -> attribute.resolve(pool));
      this.byteCodes.forEach((byteCode) -> byteCode.resolve(pool));
      this.exceptionTable.forEach((byteCode) -> byteCode.resolve(pool));
   }

   public String toString() {
      return "Code: " + this.getLength() + " bytes";
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      dos.writeShort(this.maxStack);
      dos.writeShort(this.maxLocals);
      dos.writeInt(this.codeLength);

      for(ByteCode byteCode : this.byteCodes) {
         byteCode.write(dos);
      }

      dos.writeShort(this.exceptionTable.size());

      for(ExceptionTableEntry entry : this.exceptionTable) {
         entry.write(dos);
      }

      dos.writeShort(this.attributes.size());

      for(Attribute attribute : this.attributes) {
         attribute.write(dos);
      }

   }
}
