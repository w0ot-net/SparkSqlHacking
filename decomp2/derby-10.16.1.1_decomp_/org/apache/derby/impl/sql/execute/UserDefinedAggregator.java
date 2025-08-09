package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.InvocationTargetException;
import org.apache.derby.agg.Aggregator;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public final class UserDefinedAggregator implements ExecAggregator {
   private static final int FIRST_VERSION = 0;
   private Aggregator _aggregator;
   private DataTypeDescriptor _resultType;
   private boolean _eliminatedNulls;

   public void setup(ClassFactory var1, String var2, DataTypeDescriptor var3) {
      try {
         this.setup(var1.loadApplicationClass(var2), var3);
      } catch (ClassNotFoundException var5) {
         this.logAggregatorInstantiationError(var2, var5);
      }

   }

   private void setup(Class var1, DataTypeDescriptor var2) {
      String var3 = var1.getName();

      try {
         this._aggregator = (Aggregator)var1.getConstructor().newInstance();
         this._aggregator.init();
      } catch (InstantiationException var5) {
         this.logAggregatorInstantiationError(var3, var5);
      } catch (IllegalAccessException var6) {
         this.logAggregatorInstantiationError(var3, var6);
      } catch (NoSuchMethodException var7) {
         this.logAggregatorInstantiationError(var3, var7);
      } catch (InvocationTargetException var8) {
         this.logAggregatorInstantiationError(var3, var8);
      }

      this._resultType = var2;
   }

   public boolean didEliminateNulls() {
      return this._eliminatedNulls;
   }

   public void accumulate(DataValueDescriptor var1, Object var2) throws StandardException {
      if (var1 != null && !var1.isNull()) {
         Object var3 = var1.getObject();
         this._aggregator.accumulate(var3);
      } else {
         this._eliminatedNulls = true;
      }
   }

   public void merge(ExecAggregator var1) throws StandardException {
      UserDefinedAggregator var2 = (UserDefinedAggregator)var1;
      this._aggregator.merge(var2._aggregator);
   }

   public DataValueDescriptor getResult() throws StandardException {
      Object var1 = this._aggregator.terminate();
      if (var1 == null) {
         return null;
      } else {
         DataValueDescriptor var2 = this._resultType.getNull();
         var2.setObjectForCast(var1, true, var1.getClass().getName());
         return var2;
      }
   }

   public ExecAggregator newAggregator() {
      UserDefinedAggregator var1 = new UserDefinedAggregator();
      var1.setup(this._aggregator.getClass(), this._resultType);
      return var1;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(0);
      var1.writeObject(this._aggregator);
      var1.writeObject(this._resultType);
      var1.writeBoolean(this._eliminatedNulls);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      var1.readInt();
      this._aggregator = (Aggregator)var1.readObject();
      this._resultType = (DataTypeDescriptor)var1.readObject();
      this._eliminatedNulls = var1.readBoolean();
   }

   public int getTypeFormatId() {
      return 323;
   }

   private void logAggregatorInstantiationError(String var1, Throwable var2) {
      String var3 = MessageService.getTextMessage("C008", new Object[]{var1, var2.getMessage()});
      Monitor.getStream().println(var3);
      Exception var4 = new Exception(var3, var2);
      var4.printStackTrace(Monitor.getStream().getPrintWriter());
   }
}
