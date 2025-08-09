package org.apache.commons.compress.harmony.pack200;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;

public class NewAttribute extends Attribute {
   private boolean contextClass;
   private boolean contextMethod;
   private boolean contextField;
   private boolean contextCode;
   private final String layout;
   private byte[] contents;
   private int codeOff;
   private Label[] labels;
   private ClassReader classReader;
   private char[] buf;

   public NewAttribute(ClassReader classReader, String type, String layout, byte[] contents, char[] buf, int codeOff, Label[] labels) {
      super(type);
      this.classReader = classReader;
      this.contents = contents;
      this.layout = layout;
      this.codeOff = codeOff;
      this.labels = labels;
      this.buf = buf;
   }

   public NewAttribute(String type, String layout, int context) {
      super(type);
      this.layout = layout;
      this.addContext(context);
   }

   public void addContext(int context) {
      switch (context) {
         case 0:
            this.contextClass = true;
            break;
         case 1:
            this.contextField = true;
            break;
         case 2:
            this.contextMethod = true;
            break;
         case 3:
            this.contextCode = true;
      }

   }

   public byte[] getBytes() {
      return this.contents;
   }

   public Label getLabel(int index) {
      return this.labels[index];
   }

   public String getLayout() {
      return this.layout;
   }

   public boolean isCodeAttribute() {
      return this.codeOff != -1;
   }

   public boolean isContextClass() {
      return this.contextClass;
   }

   public boolean isContextCode() {
      return this.contextCode;
   }

   public boolean isContextField() {
      return this.contextField;
   }

   public boolean isContextMethod() {
      return this.contextMethod;
   }

   public boolean isUnknown() {
      return false;
   }

   public boolean isUnknown(int context) {
      switch (context) {
         case 0:
            return !this.contextClass;
         case 1:
            return !this.contextField;
         case 2:
            return !this.contextMethod;
         case 3:
            return !this.contextCode;
         default:
            return false;
      }
   }

   protected Attribute read(ClassReader cr, int off, int len, char[] buf, int codeOff, Label[] labels) {
      byte[] attributeContents = new byte[len];
      System.arraycopy(cr.b, off, attributeContents, 0, len);
      return new NewAttribute(cr, this.type, this.layout, attributeContents, buf, codeOff, labels);
   }

   public String readClass(int index) {
      return this.classReader.readClass(index, this.buf);
   }

   public Object readConst(int index) {
      return this.classReader.readConst(index, this.buf);
   }

   public String readUTF8(int index) {
      return this.classReader.readUTF8(index, this.buf);
   }

   public static class ErrorAttribute extends NewAttribute {
      public ErrorAttribute(String type, int context) {
         super(type, "", context);
      }

      protected Attribute read(ClassReader cr, int off, int len, char[] buf, int codeOff, Label[] labels) {
         throw new Error("Attribute " + this.type + " was found");
      }
   }

   public static class PassAttribute extends NewAttribute {
      public PassAttribute(String type, int context) {
         super(type, "", context);
      }

      protected Attribute read(ClassReader cr, int off, int len, char[] buf, int codeOff, Label[] labels) {
         throw new Segment.PassException();
      }
   }

   public static class StripAttribute extends NewAttribute {
      public StripAttribute(String type, int context) {
         super(type, "", context);
      }

      protected Attribute read(ClassReader cr, int off, int len, char[] buf, int codeOff, Label[] labels) {
         return null;
      }
   }
}
