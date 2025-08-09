package org.rocksdb;

import java.io.Serializable;
import java.util.Objects;

public class Status implements Serializable {
   private static final long serialVersionUID = -3794191127754280439L;
   private final Code code;
   private final SubCode subCode;
   private final String state;

   public Status(Code var1, SubCode var2, String var3) {
      this.code = var1;
      this.subCode = var2;
      this.state = var3;
   }

   private Status(byte var1, byte var2, String var3) {
      this.code = Status.Code.getCode(var1);
      this.subCode = Status.SubCode.getSubCode(var2);
      this.state = var3;
   }

   public Code getCode() {
      return this.code;
   }

   public SubCode getSubCode() {
      return this.subCode;
   }

   public String getState() {
      return this.state;
   }

   public String getCodeString() {
      StringBuilder var1 = (new StringBuilder()).append(this.code.name());
      if (this.subCode != null && this.subCode != Status.SubCode.None) {
         var1.append("(").append(this.subCode.name()).append(")");
      }

      return var1.toString();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Status var2 = (Status)var1;
         return this.code == var2.code && this.subCode == var2.subCode && Objects.equals(this.state, var2.state);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.code, this.subCode, this.state});
   }

   public static enum Code {
      Ok((byte)0),
      NotFound((byte)1),
      Corruption((byte)2),
      NotSupported((byte)3),
      InvalidArgument((byte)4),
      IOError((byte)5),
      MergeInProgress((byte)6),
      Incomplete((byte)7),
      ShutdownInProgress((byte)8),
      TimedOut((byte)9),
      Aborted((byte)10),
      Busy((byte)11),
      Expired((byte)12),
      TryAgain((byte)13),
      Undefined((byte)127);

      private final byte value;

      private Code(byte var3) {
         this.value = var3;
      }

      public static Code getCode(byte var0) {
         for(Code var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         throw new IllegalArgumentException("Illegal value provided for Code (" + var0 + ").");
      }

      public byte getValue() {
         return this.value;
      }
   }

   public static enum SubCode {
      None((byte)0),
      MutexTimeout((byte)1),
      LockTimeout((byte)2),
      LockLimit((byte)3),
      NoSpace((byte)4),
      Deadlock((byte)5),
      StaleFile((byte)6),
      MemoryLimit((byte)7),
      Undefined((byte)127);

      private final byte value;

      private SubCode(byte var3) {
         this.value = var3;
      }

      public static SubCode getSubCode(byte var0) {
         for(SubCode var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         throw new IllegalArgumentException("Illegal value provided for SubCode (" + var0 + ").");
      }

      public byte getValue() {
         return this.value;
      }
   }
}
