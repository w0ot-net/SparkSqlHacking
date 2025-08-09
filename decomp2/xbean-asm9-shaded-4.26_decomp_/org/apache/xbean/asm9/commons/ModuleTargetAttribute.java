package org.apache.xbean.asm9.commons;

import org.apache.xbean.asm9.Attribute;
import org.apache.xbean.asm9.ByteVector;
import org.apache.xbean.asm9.ClassReader;
import org.apache.xbean.asm9.ClassWriter;
import org.apache.xbean.asm9.Label;

public final class ModuleTargetAttribute extends Attribute {
   public String platform;

   public ModuleTargetAttribute(String platform) {
      super("ModuleTarget");
      this.platform = platform;
   }

   public ModuleTargetAttribute() {
      this((String)null);
   }

   protected Attribute read(ClassReader classReader, int offset, int length, char[] charBuffer, int codeOffset, Label[] labels) {
      return new ModuleTargetAttribute(classReader.readUTF8(offset, charBuffer));
   }

   protected ByteVector write(ClassWriter classWriter, byte[] code, int codeLength, int maxStack, int maxLocals) {
      ByteVector byteVector = new ByteVector();
      byteVector.putShort(this.platform == null ? 0 : classWriter.newUTF8(this.platform));
      return byteVector;
   }
}
