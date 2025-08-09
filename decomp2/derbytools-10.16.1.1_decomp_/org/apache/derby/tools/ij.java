package org.apache.derby.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.derby.iapi.tools.i18n.LocalizedInput;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.impl.tools.ij.Main;
import org.apache.derby.impl.tools.ij.util;
import org.apache.derby.impl.tools.ij.utilMain;

public class ij {
   public static void main(String[] var0) throws IOException {
      Main.main(var0);
   }

   public static int runScript(Connection var0, InputStream var1, String var2, OutputStream var3, String var4) throws UnsupportedEncodingException {
      return runScript(var0, var1, var2, var3, var4, false);
   }

   public static int runScript(Connection var0, InputStream var1, String var2, OutputStream var3, String var4, boolean var5) throws UnsupportedEncodingException {
      LocalizedOutput var6 = var4 == null ? LocalizedResource.getInstance().getNewOutput(var3) : LocalizedResource.getInstance().getNewEncodedOutput(var3, var4);
      Main var7 = new Main(false);
      LocalizedInput var8 = LocalizedResource.getInstance().getNewEncodedInput(var1, var2);
      utilMain var9 = var7.getutilMain(1, var6, var5);
      return var9.goScript(var0, var8);
   }

   private ij() {
   }

   public static String getArg(String var0, String[] var1) {
      return util.getArg(var0, var1);
   }

   public static void getPropertyArg(String[] var0) throws IOException {
      util.getPropertyArg(var0);
   }

   public static Connection startJBMS() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException {
      return util.startJBMS();
   }
}
