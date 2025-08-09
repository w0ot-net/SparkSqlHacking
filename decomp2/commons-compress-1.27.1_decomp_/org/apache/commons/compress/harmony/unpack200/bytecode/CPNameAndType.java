package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.compress.harmony.unpack200.SegmentUtils;

public class CPNameAndType extends ConstantPoolEntry {
   CPUTF8 descriptor;
   transient int descriptorIndex;
   CPUTF8 name;
   transient int nameIndex;
   private boolean hashCodeComputed;
   private int cachedHashCode;

   public CPNameAndType(CPUTF8 name, CPUTF8 descriptor, int globalIndex) {
      super((byte)12, globalIndex);
      this.name = (CPUTF8)Objects.requireNonNull(name, "name");
      this.descriptor = (CPUTF8)Objects.requireNonNull(descriptor, "descriptor");
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         CPNameAndType other = (CPNameAndType)obj;
         return Objects.equals(this.descriptor, other.descriptor) && Objects.equals(this.name, other.name);
      }
   }

   private void generateHashCode() {
      this.hashCodeComputed = true;
      int PRIME = 31;
      int result = 1;
      result = 31 * result + this.descriptor.hashCode();
      result = 31 * result + this.name.hashCode();
      this.cachedHashCode = result;
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      return new ClassFileEntry[]{this.name, this.descriptor};
   }

   public int hashCode() {
      if (!this.hashCodeComputed) {
         this.generateHashCode();
      }

      return this.cachedHashCode;
   }

   public int invokeInterfaceCount() {
      return 1 + SegmentUtils.countInvokeInterfaceArgs(this.descriptor.underlyingString());
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);
      this.descriptorIndex = pool.indexOf(this.descriptor);
      this.nameIndex = pool.indexOf(this.name);
   }

   public String toString() {
      return "NameAndType: " + this.name + "(" + this.descriptor + ")";
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      dos.writeShort(this.nameIndex);
      dos.writeShort(this.descriptorIndex);
   }
}
