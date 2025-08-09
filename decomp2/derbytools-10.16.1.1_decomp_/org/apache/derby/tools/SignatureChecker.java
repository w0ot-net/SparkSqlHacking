package org.apache.derby.tools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;

public class SignatureChecker {
   private static final String WILDCARD = "%";
   private static final String[] SYSTEM_SCHEMAS = new String[]{"SQLJ", "SYSCS_UTIL", "SYSIBM"};
   private final ParsedArgs _parsedArgs;
   private final ArrayList _procedures = new ArrayList();
   private final ArrayList _functions = new ArrayList();
   private final boolean _debugging = false;
   private static LocalizedResource _messageFormatter;

   private SignatureChecker(ParsedArgs var1) {
      this._parsedArgs = var1;
   }

   public static void main(String[] var0) {
      ParsedArgs var1 = new ParsedArgs(var0);
      if (!var1.isValid()) {
         printUsage();
         System.exit(1);
      } else {
         SignatureChecker var2 = new SignatureChecker(var1);
         var2.execute();
      }

   }

   private void execute() {
      try {
         Connection var1 = this.getJ2SEConnection();
         if (var1 == null) {
            println(formatMessage("SC_NO_CONN"));
         } else {
            this.matchSignatures(var1);
            var1.close();
         }
      } catch (SQLException var2) {
         printThrowable(var2);
      }

   }

   private void matchSignatures(Connection var1) throws SQLException {
      this.matchProcedures(var1);
      this.matchFunctions(var1);
   }

   private void matchProcedures(Connection var1) throws SQLException {
      DatabaseMetaData var2 = var1.getMetaData();
      this.findProcedures(var2);
      this.countProcedureArgs(var2);
      int var3 = this._procedures.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         SQLRoutine var5 = this.getProcedure(var4);
         StringBuilder var6 = new StringBuilder();
         int var7 = var5.getArgCount();
         var6.append("call ");
         var6.append(var5.getQualifiedName());
         var6.append("( ");

         for(int var8 = 0; var8 < var7; ++var8) {
            if (var8 > 0) {
               var6.append(", ");
            }

            var6.append(" ? ");
         }

         var6.append(" )");
         this.checkSignature(var1, var6.toString(), this.makeReadableSignature(var5));
      }

   }

   private void matchFunctions(Connection var1) throws SQLException {
      DatabaseMetaData var2 = var1.getMetaData();
      this.findFunctions(var2);
      this.countFunctionArgs(var2);
      int var3 = this._functions.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         SQLRoutine var5 = this.getFunction(var4);
         StringBuilder var6 = new StringBuilder();
         int var7 = var5.getArgCount();
         if (var5.isTableFunction()) {
            var6.append("select * from table( ");
         } else {
            var6.append("values(  ");
         }

         var6.append(var5.getQualifiedName());
         var6.append("( ");

         for(int var8 = 0; var8 < var7; ++var8) {
            if (var8 > 0) {
               var6.append(", ");
            }

            var6.append(" ? ");
         }

         var6.append(" ) )");
         if (var5.isTableFunction()) {
            var6.append(" s");
         }

         this.checkSignature(var1, var6.toString(), this.makeReadableSignature(var5));
      }

   }

   private String makeReadableSignature(SQLRoutine var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = var1.getArgCount();
      var2.append(var1.getQualifiedName());
      var2.append("( ");

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var4 > 0) {
            var2.append(", ");
         }

         var2.append(" ");
         var2.append(var1.getArgType(var4));
         var2.append(" ");
      }

      var2.append(" )");
      return var2.toString();
   }

   private void findProcedures(DatabaseMetaData var1) throws SQLException {
      ResultSet var2 = var1.getProcedures((String)null, (String)null, "%");

      while(var2.next()) {
         String var3 = var2.getString(2);
         String var4 = var2.getString(3);
         if (!this.isSystemSchema(var3)) {
            this.putProcedure(var3, var4);
         }
      }

      var2.close();
   }

   private void countProcedureArgs(DatabaseMetaData var1) throws SQLException {
      int var2 = this._procedures.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         SQLRoutine var4 = this.getProcedure(var3);
         ResultSet var5 = var1.getProcedureColumns((String)null, var4.getSchema(), var4.getName(), "%");

         while(var5.next()) {
            var4.addArg(var5.getString(7));
         }

         var5.close();
      }

   }

   private void findFunctions(DatabaseMetaData var1) throws SQLException {
      try {
         ResultSet var2 = var1.getFunctions((String)null, (String)null, "%");

         while(var2.next()) {
            String var3 = var2.getString(2);
            String var4 = var2.getString(3);
            short var5 = var2.getShort(5);
            if (!this.isSystemSchema(var3)) {
               boolean var6 = var5 == 2;
               this.putFunction(var3, var4, var6);
            }
         }

         var2.close();
      } catch (SQLException var7) {
         throw new SQLException(var7.getMessage());
      }
   }

   private void countFunctionArgs(DatabaseMetaData var1) throws SQLException {
      int var2 = this._functions.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         SQLRoutine var4 = this.getFunction(var3);
         ResultSet var5 = var1.getFunctionColumns((String)null, var4.getSchema(), var4.getName(), "%");

         while(var5.next()) {
            short var6 = var5.getShort(5);
            if (var6 != 4 && var6 != 5) {
               var4.addArg(var5.getString(7));
            }
         }

         var5.close();
      }

   }

   private void checkSignature(Connection var1, String var2, String var3) {
      try {
         PreparedStatement var4 = this.prepareStatement(var1, var2);
         var4.close();
         println(formatMessage("SC_FOUND_MATCH", var3));
      } catch (SQLException var5) {
         println(formatMessage("SC_UNRESOLVABLE", var3, var5.getMessage()));
      }

   }

   private Connection getJ2SEConnection() throws SQLException {
      try {
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         Class.forName("java.sql.DriverManager");
      } catch (ClassNotFoundException var3) {
      }

      try {
         return DriverManager.getConnection(this._parsedArgs.getJ2seConnectionUrl());
      } catch (SQLException var2) {
         printThrowable(var2);
         return null;
      }
   }

   private PreparedStatement prepareStatement(Connection var1, String var2) throws SQLException {
      return var1.prepareStatement(var2);
   }

   private static void printUsage() {
      println(formatMessage("SC_USAGE"));
   }

   private static void printThrowable(Throwable var0) {
      var0.printStackTrace();
   }

   private static void println(String var0) {
      System.out.println(var0);
   }

   private boolean isSystemSchema(String var1) {
      int var2 = SYSTEM_SCHEMAS.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (SYSTEM_SCHEMAS[var3].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   private void putProcedure(String var1, String var2) {
      this._procedures.add(new SQLRoutine(var1, var2, false));
   }

   private SQLRoutine getProcedure(int var1) {
      return (SQLRoutine)this._procedures.get(var1);
   }

   private void putFunction(String var1, String var2, boolean var3) {
      this._functions.add(new SQLRoutine(var1, var2, var3));
   }

   private SQLRoutine getFunction(int var1) {
      return (SQLRoutine)this._functions.get(var1);
   }

   private static String formatMessage(String var0, Object... var1) {
      return getMessageFormatter().getTextMessage(var0, var1);
   }

   private static LocalizedResource getMessageFormatter() {
      if (_messageFormatter == null) {
         _messageFormatter = LocalizedResource.getInstance();
      }

      return _messageFormatter;
   }

   static class ParsedArgs {
      private boolean _isValid = false;
      private String _j2seConnectionUrl;

      public ParsedArgs(String[] var1) {
         this.parseArgs(var1);
      }

      public boolean isValid() {
         return this._isValid;
      }

      public String getJ2seConnectionUrl() {
         return this._j2seConnectionUrl;
      }

      private void parseArgs(String[] var1) {
         if (var1 != null) {
            if (var1.length == 1) {
               this._j2seConnectionUrl = var1[0];
               this._isValid = true;
            }
         }
      }
   }

   class SQLRoutine {
      private final String _schema;
      private final String _name;
      private final boolean _isTableFunction;
      private final ArrayList _argList = new ArrayList();

      public SQLRoutine(String var2, String var3, boolean var4) {
         this._schema = var2;
         this._name = var3;
         this._isTableFunction = var4;
      }

      public void addArg(String var1) {
         this._argList.add(var1);
      }

      public String getSchema() {
         return this._schema;
      }

      public String getName() {
         return this._name;
      }

      public int getArgCount() {
         return this._argList.size();
      }

      public String getArgType(int var1) {
         return (String)this._argList.get(var1);
      }

      public boolean isTableFunction() {
         return this._isTableFunction;
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("SQLRoutine( ");
         var1.append(this._schema);
         var1.append(", ");
         var1.append(this._name);
         var1.append(", ");
         var1.append("isTableFunction = ");
         var1.append(this._isTableFunction);
         var1.append(", ");
         var1.append(" argCount = ");
         var1.append(this.getArgCount());
         var1.append(" )");
         return var1.toString();
      }

      private String doubleQuote(String var1) {
         return "\"" + var1 + "\"";
      }

      public String getQualifiedName() {
         String var10000 = this.doubleQuote(this._schema);
         return var10000 + "." + this.doubleQuote(this._name);
      }
   }
}
