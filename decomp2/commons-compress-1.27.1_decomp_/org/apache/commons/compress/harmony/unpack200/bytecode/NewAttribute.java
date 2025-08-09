package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewAttribute extends BCIRenumberedAttribute {
   private final List lengths = new ArrayList();
   private final List body = new ArrayList();
   private ClassConstantPool pool;
   private final int layoutIndex;

   public NewAttribute(CPUTF8 attributeName, int layoutIndex) {
      super(attributeName);
      this.layoutIndex = layoutIndex;
   }

   public void addBCIndex(int length, int value) {
      this.lengths.add(length);
      this.body.add(new BCIndex(value));
   }

   public void addBCLength(int length, int value) {
      this.lengths.add(length);
      this.body.add(new BCLength(value));
   }

   public void addBCOffset(int length, int value) {
      this.lengths.add(length);
      this.body.add(new BCOffset(value));
   }

   public void addInteger(int length, long value) {
      this.lengths.add(length);
      this.body.add(value);
   }

   public void addToBody(int length, Object value) {
      this.lengths.add(length);
      this.body.add(value);
   }

   public int getLayoutIndex() {
      return this.layoutIndex;
   }

   protected int getLength() {
      int length = 0;

      for(Integer len : this.lengths) {
         length += len;
      }

      return length;
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      int total = 1;

      for(Object element : this.body) {
         if (element instanceof ClassFileEntry) {
            ++total;
         }
      }

      ClassFileEntry[] nested = new ClassFileEntry[total];
      nested[0] = this.getAttributeName();
      int i = 1;

      for(Object element : this.body) {
         if (element instanceof ClassFileEntry) {
            nested[i] = (ClassFileEntry)element;
            ++i;
         }
      }

      return nested;
   }

   protected int[] getStartPCs() {
      return null;
   }

   public void renumber(List byteCodeOffsets) {
      if (!this.renumbered) {
         Object previous = null;

         for(Object obj : this.body) {
            if (obj instanceof BCIndex) {
               BCIndex bcIndex = (BCIndex)obj;
               bcIndex.setActualValue((Integer)byteCodeOffsets.get(bcIndex.index));
            } else if (obj instanceof BCOffset) {
               BCOffset bcOffset = (BCOffset)obj;
               if (previous instanceof BCIndex) {
                  int index = ((BCIndex)previous).index + bcOffset.offset;
                  bcOffset.setIndex(index);
                  bcOffset.setActualValue((Integer)byteCodeOffsets.get(index));
               } else if (previous instanceof BCOffset) {
                  int index = ((BCOffset)previous).index + bcOffset.offset;
                  bcOffset.setIndex(index);
                  bcOffset.setActualValue((Integer)byteCodeOffsets.get(index));
               } else {
                  bcOffset.setActualValue((Integer)byteCodeOffsets.get(bcOffset.offset));
               }
            } else if (obj instanceof BCLength) {
               BCLength bcLength = (BCLength)obj;
               BCIndex prevIndex = (BCIndex)previous;
               int index = prevIndex.index + bcLength.length;
               int actualLength = (Integer)byteCodeOffsets.get(index) - prevIndex.actualValue;
               bcLength.setActualValue(actualLength);
            }

            previous = obj;
         }

         this.renumbered = true;
      }

   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);

      for(Object element : this.body) {
         if (element instanceof ClassFileEntry) {
            ((ClassFileEntry)element).resolve(pool);
         }
      }

      this.pool = pool;
   }

   public String toString() {
      return this.attributeName.underlyingString();
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      for(int i = 0; i < this.lengths.size(); ++i) {
         int length = (Integer)this.lengths.get(i);
         Object obj = this.body.get(i);
         long value = 0L;
         if (obj instanceof Long) {
            value = (Long)obj;
         } else if (obj instanceof ClassFileEntry) {
            value = (long)this.pool.indexOf((ClassFileEntry)obj);
         } else if (obj instanceof AbstractBcValue) {
            value = (long)((AbstractBcValue)obj).actualValue;
         }

         switch (length) {
            case 1:
               dos.writeByte((int)value);
               break;
            case 2:
               dos.writeShort((int)value);
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               break;
            case 4:
               dos.writeInt((int)value);
               break;
            case 8:
               dos.writeLong(value);
         }
      }

   }

   private abstract static class AbstractBcValue {
      int actualValue;

      private AbstractBcValue() {
      }

      public void setActualValue(int value) {
         this.actualValue = value;
      }
   }

   private static final class BCIndex extends AbstractBcValue {
      private final int index;

      BCIndex(int index) {
         this.index = index;
      }
   }

   private static final class BCLength extends AbstractBcValue {
      private final int length;

      BCLength(int length) {
         this.length = length;
      }
   }

   private static final class BCOffset extends AbstractBcValue {
      private final int offset;
      private int index;

      BCOffset(int offset) {
         this.offset = offset;
      }

      public void setIndex(int index) {
         this.index = index;
      }
   }
}
