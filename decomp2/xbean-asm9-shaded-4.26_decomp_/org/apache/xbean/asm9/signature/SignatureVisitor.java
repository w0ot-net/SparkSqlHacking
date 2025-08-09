package org.apache.xbean.asm9.signature;

public abstract class SignatureVisitor {
   public static final char EXTENDS = '+';
   public static final char SUPER = '-';
   public static final char INSTANCEOF = '=';
   protected final int api;

   protected SignatureVisitor(int api) {
      if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
         throw new IllegalArgumentException(stringConcat$0(api));
      } else {
         this.api = api;
      }
   }

   // $FF: synthetic method
   private static String stringConcat$0(int var0) {
      return "Unsupported api " + var0;
   }

   public void visitFormalTypeParameter(String name) {
   }

   public SignatureVisitor visitClassBound() {
      return this;
   }

   public SignatureVisitor visitInterfaceBound() {
      return this;
   }

   public SignatureVisitor visitSuperclass() {
      return this;
   }

   public SignatureVisitor visitInterface() {
      return this;
   }

   public SignatureVisitor visitParameterType() {
      return this;
   }

   public SignatureVisitor visitReturnType() {
      return this;
   }

   public SignatureVisitor visitExceptionType() {
      return this;
   }

   public void visitBaseType(char descriptor) {
   }

   public void visitTypeVariable(String name) {
   }

   public SignatureVisitor visitArrayType() {
      return this;
   }

   public void visitClassType(String name) {
   }

   public void visitInnerClassType(String name) {
   }

   public void visitTypeArgument() {
   }

   public SignatureVisitor visitTypeArgument(char wildcard) {
      return this;
   }

   public void visitEnd() {
   }
}
