package org.apache.derby.iapi.sql.dictionary;

import java.sql.Timestamp;
import java.util.Arrays;
import org.apache.derby.iapi.types.DataTypeUtilities;

public final class UserDescriptor extends TupleDescriptor {
   private String _userName;
   private String _hashingScheme;
   private char[] _password;
   private Timestamp _lastModified;

   UserDescriptor(DataDictionary var1, String var2, String var3, char[] var4, Timestamp var5) {
      super(var1);
      this._userName = var2;
      this._hashingScheme = var3;
      if (var4 == null) {
         this._password = null;
      } else {
         this._password = new char[var4.length];
         System.arraycopy(var4, 0, this._password, 0, var4.length);
      }

      this._lastModified = DataTypeUtilities.clone(var5);
   }

   public String getUserName() {
      return this._userName;
   }

   public String getHashingScheme() {
      return this._hashingScheme;
   }

   public Timestamp getLastModified() {
      return DataTypeUtilities.clone(this._lastModified);
   }

   public char[] getAndZeroPassword() {
      int var1 = this._password.length;
      char[] var2 = new char[var1];
      System.arraycopy(this._password, 0, var2, 0, var1);
      Arrays.fill(this._password, '\u0000');
      return var2;
   }

   public String getDescriptorType() {
      return "User";
   }

   public String getDescriptorName() {
      return this._userName;
   }
}
