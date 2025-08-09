package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ConstantValueAttribute extends Attribute {
   private static CPUTF8 attributeName;
   private int constantIndex;
   private final ClassFileEntry entry;

   public static void setAttributeName(CPUTF8 cpUTF8Value) {
      attributeName = cpUTF8Value;
   }

   public ConstantValueAttribute(ClassFileEntry entry) {
      super(attributeName);
      this.entry = (ClassFileEntry)Objects.requireNonNull(entry, "entry");
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equals(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ConstantValueAttribute other = (ConstantValueAttribute)obj;
         return Objects.equals(this.entry, other.entry);
      }
   }

   protected int getLength() {
      return 2;
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      return new ClassFileEntry[]{this.getAttributeName(), this.entry};
   }

   public int hashCode() {
      int PRIME = 31;
      int result = super.hashCode();
      result = 31 * result + (this.entry == null ? 0 : this.entry.hashCode());
      return result;
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);
      this.entry.resolve(pool);
      this.constantIndex = pool.indexOf(this.entry);
   }

   public String toString() {
      return "Constant:" + this.entry;
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      dos.writeShort(this.constantIndex);
   }
}
