package org.apache.derby.impl.sql;

import java.util.Properties;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyFactory;
import org.apache.derby.iapi.sql.LanguageFactory;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.conn.LanguageConnectionFactory;
import org.apache.derby.shared.common.error.StandardException;

public class GenericLanguageFactory implements LanguageFactory, ModuleControl {
   private GenericParameterValueSet emptySet;

   public void boot(boolean var1, Properties var2) throws StandardException {
      LanguageConnectionFactory var3 = (LanguageConnectionFactory)findServiceModule(this, "org.apache.derby.iapi.sql.conn.LanguageConnectionFactory");
      PropertyFactory var4 = var3.getPropertyFactory();
      if (var4 != null) {
         var4.addPropertySetNotification(new LanguageDbPropertySetter());
      }

      this.emptySet = new GenericParameterValueSet((ClassInspector)null, 0, false);
   }

   public void stop() {
   }

   public ParameterValueSet newParameterValueSet(ClassInspector var1, int var2, boolean var3) {
      return var2 == 0 ? this.emptySet : new GenericParameterValueSet(var1, var2, var3);
   }

   public ResultDescription getResultDescription(ResultDescription var1, int[] var2) {
      return new GenericResultDescription(var1, var2);
   }

   public ResultDescription getResultDescription(ResultColumnDescriptor[] var1, String var2) {
      return new GenericResultDescription(var1, var2);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }
}
