package org.apache.derby.impl.sql;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.Statement;
import org.apache.derby.iapi.sql.StorablePreparedStatement;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class GenericStorablePreparedStatement extends GenericPreparedStatement implements Formatable, StorablePreparedStatement {
   private ByteArray byteCode;
   private String className;

   public GenericStorablePreparedStatement() {
   }

   GenericStorablePreparedStatement(Statement var1) {
      super(var1);
   }

   ByteArray getByteCodeSaver() {
      if (this.byteCode == null) {
         this.byteCode = new ByteArray();
      }

      return this.byteCode;
   }

   public GeneratedClass getActivationClass() throws StandardException {
      if (this.activationClass == null) {
         this.loadGeneratedClass();
      }

      return this.activationClass;
   }

   void setActivationClass(GeneratedClass var1) {
      super.setActivationClass(var1);
      if (var1 != null) {
         this.className = var1.getName();
         if (this.byteCode != null && this.byteCode.getArray() == null) {
            this.byteCode = null;
         }
      }

   }

   public void loadGeneratedClass() throws StandardException {
      LanguageConnectionContext var1 = (LanguageConnectionContext)getContext("LanguageConnectionContext");
      ClassFactory var2 = var1.getLanguageConnectionFactory().getClassFactory();
      GeneratedClass var3 = var2.loadGeneratedClass(this.className, this.byteCode);
      this.setActivationClass(var3);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeObject(this.getCursorInfo());
      var1.writeBoolean(this.needsSavepoint());
      var1.writeBoolean(this.isAtomic);
      var1.writeObject(this.executionConstants);
      var1.writeObject(this.resultDesc);
      if (this.savedObjects == null) {
         var1.writeBoolean(false);
      } else {
         var1.writeBoolean(true);
         ArrayUtil.writeArrayLength(var1, this.savedObjects);
         ArrayUtil.writeArrayItems(var1, this.savedObjects);
      }

      var1.writeObject(this.className);
      var1.writeBoolean(this.byteCode != null);
      if (this.byteCode != null) {
         this.byteCode.writeExternal(var1);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.setCursorInfo((CursorInfo)var1.readObject());
      this.setNeedsSavepoint(var1.readBoolean());
      this.isAtomic = var1.readBoolean();
      this.executionConstants = (ConstantAction)var1.readObject();
      this.resultDesc = (ResultDescription)var1.readObject();
      if (var1.readBoolean()) {
         this.savedObjects = new Object[ArrayUtil.readArrayLength(var1)];
         ArrayUtil.readArrayItems(var1, this.savedObjects);
      }

      this.className = (String)var1.readObject();
      if (var1.readBoolean()) {
         this.byteCode = new ByteArray();
         this.byteCode.readExternal(var1);
      } else {
         this.byteCode = null;
      }

   }

   public int getTypeFormatId() {
      return 225;
   }

   public boolean isStorable() {
      return true;
   }

   public String toString() {
      return "";
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
