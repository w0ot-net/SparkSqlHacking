package org.apache.derby.impl.tools.ij;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import org.apache.derby.iapi.tools.i18n.LocalizedInput;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.shared.common.info.ProductVersionHolder;
import org.apache.derby.tools.JDBCDisplayUtil;

public class utilMain {
   private StatementFinder[] commandGrabber;
   UCode_CharStream charStream;
   ijTokenManager ijTokMgr;
   ij ijParser;
   ConnectionEnv[] connEnv;
   private int currCE;
   private final int numConnections;
   private boolean fileInput;
   private boolean initialFileInput;
   private boolean mtUse;
   private boolean firstRun;
   private LocalizedOutput out;
   private Hashtable ignoreErrors;
   private final boolean showErrorCode;
   private final String ijExceptionTrace;
   public static final int BUFFEREDFILESIZE = 2048;
   Stack oldGrabbers;
   LocalizedResource langUtil;

   utilMain(int var1, LocalizedOutput var2) throws ijFatalException {
      this(var1, var2, (Hashtable)null);
   }

   utilMain(int var1, LocalizedOutput var2, boolean var3) throws ijFatalException {
      this(var1, var2, (Hashtable)null);
      if (var3) {
         this.initFromEnvironment();
      }

   }

   public utilMain(int var1, LocalizedOutput var2, Hashtable var3) throws ijFatalException {
      this.firstRun = true;
      this.out = null;
      this.oldGrabbers = new Stack();
      this.langUtil = LocalizedResource.getInstance();
      this.charStream = new UCode_CharStream(new StringReader(" "), 1, 1);
      this.ijTokMgr = new ijTokenManager(this.charStream);
      this.ijParser = new ij(this.ijTokMgr, this);
      this.out = var2;
      if (var3 != null) {
         this.ignoreErrors = (Hashtable)var3.clone();
      }

      this.showErrorCode = Boolean.valueOf(util.getSystemProperty("ij.showErrorCode"));
      this.ijExceptionTrace = util.getSystemProperty("ij.exceptionTrace");
      this.numConnections = var1;
      this.commandGrabber = new StatementFinder[var1];
      this.connEnv = new ConnectionEnv[var1];

      for(int var4 = 0; var4 < var1; ++var4) {
         this.commandGrabber[var4] = new StatementFinder(this.langUtil.getNewInput(System.in), var2);
         this.connEnv[var4] = new ConnectionEnv(var4, var1 > 1, var1 == 1);
      }

      this.currCE = 0;
      this.fileInput = false;
      this.initialFileInput = false;
      this.firstRun = true;
   }

   public void initFromEnvironment() {
      this.ijParser.initFromEnvironment();

      for(int var1 = 0; var1 < this.numConnections; ++var1) {
         try {
            this.connEnv[var1].init(this.out);
         } catch (SQLException var3) {
            JDBCDisplayUtil.ShowException((PrintWriter)this.out, var3);
         } catch (ClassNotFoundException var4) {
            JDBCDisplayUtil.ShowException((PrintWriter)this.out, var4);
         } catch (InstantiationException var5) {
            JDBCDisplayUtil.ShowException((PrintWriter)this.out, var5);
         } catch (IllegalAccessException var6) {
            JDBCDisplayUtil.ShowException((PrintWriter)this.out, var6);
         } catch (NoSuchMethodException var7) {
            JDBCDisplayUtil.ShowException((PrintWriter)this.out, var7);
         } catch (InvocationTargetException var8) {
            JDBCDisplayUtil.ShowException((PrintWriter)this.out, var8);
         } catch (ijException var9) {
            JDBCDisplayUtil.ShowException((PrintWriter)this.out, var9);
         }
      }

   }

   public void go(LocalizedInput[] var1, LocalizedOutput var2) throws ijFatalException {
      this.out = var2;
      this.ijParser.setConnection(this.connEnv[this.currCE], this.numConnections > 1);
      this.fileInput = this.initialFileInput = !var1[this.currCE].isStandardInput();

      for(int var3 = 0; var3 < this.commandGrabber.length; ++var3) {
         this.commandGrabber[var3].ReInit(var1[var3]);
      }

      if (this.firstRun) {
         InputStream var8 = util.getResourceAsStream("/org/apache/derby/info/tools/info.properties");
         ProductVersionHolder var4 = ProductVersionHolder.getProductVersionHolderFromMyEnv(var8);
         String var5;
         if (var4 != null) {
            int var10000 = var4.getMajorVersion();
            var5 = var10000 + "." + var4.getMinorVersion();
         } else {
            var5 = "?";
         }

         var2.println(this.langUtil.getTextMessage("IJ_IjVers30C199", var5));

         for(int var6 = this.connEnv.length - 1; var6 >= 0; --var6) {
            Connection var7 = this.connEnv[var6].getConnection();
            if (var7 != null) {
               JDBCDisplayUtil.ShowWarnings((PrintWriter)var2, (Connection)var7);
            }
         }

         this.firstRun = false;
         this.supportIJProperties(this.connEnv[this.currCE]);
      }

      this.out = var2;
      this.runScriptGuts();
      this.cleanupGo(var1);
   }

   public int goScript(Connection var1, LocalizedInput var2) {
      this.connEnv[0].addSession(var1, (String)null);
      this.ijParser.setConnection(this.connEnv[0], this.numConnections > 1);
      this.supportIJProperties(this.connEnv[0]);
      this.fileInput = this.initialFileInput = !var2.isStandardInput();
      this.commandGrabber[0].ReInit(var2);
      return this.runScriptGuts();
   }

   private void supportIJProperties(ConnectionEnv var1) {
      boolean var2 = Boolean.valueOf(util.getSystemProperty("ij.showNoCountForSelect"));
      JDBCDisplayUtil.setShowSelectCount(!var2);
      boolean var3 = Boolean.valueOf(util.getSystemProperty("ij.showNoConnectionsAtStart"));
      if (!var3) {
         try {
            ijResult var4 = this.ijParser.showConnectionsMethod(true);
            this.displayResult(this.out, var4, var1.getConnection());
         } catch (SQLException var5) {
            this.handleSQLException(this.out, var5);
         }
      }

   }

   private int runScriptGuts() {
      int var1 = 0;
      boolean var2 = false;

      for(String var3 = null; !this.ijParser.exit && !var2; this.currCE = ++this.currCE % this.connEnv.length) {
         try {
            this.ijParser.setConnection(this.connEnv[this.currCE], this.numConnections > 1);
         } catch (Throwable var10) {
         }

         this.connEnv[this.currCE].doPrompt(true, this.out);

         try {
            Object var16 = null;
            this.out.flush();

            for(var3 = this.commandGrabber[this.currCE].nextStatement(); var3 == null && !this.oldGrabbers.empty(); var3 = this.commandGrabber[this.currCE].nextStatement()) {
               if (this.fileInput) {
                  this.commandGrabber[this.currCE].close();
               }

               this.commandGrabber[this.currCE] = (StatementFinder)this.oldGrabbers.pop();
               if (this.oldGrabbers.empty()) {
                  this.fileInput = this.initialFileInput;
               }
            }

            if (var3 == null && this.oldGrabbers.empty()) {
               var2 = true;
            } else {
               boolean var4 = this.ijParser.getElapsedTimeState();
               long var5 = 0L;
               if (this.fileInput) {
                  this.out.println(var3 + ";");
                  this.out.flush();
               }

               this.charStream.ReInit((Reader)(new StringReader(var3)), 1, 1);
               this.ijTokMgr.ReInit(this.charStream);
               this.ijParser.ReInit(this.ijTokMgr);
               if (var4) {
                  var5 = System.currentTimeMillis();
               }

               ijResult var9 = this.ijParser.ijStatement();
               this.displayResult(this.out, var9, this.connEnv[this.currCE].getConnection());
               if (var4) {
                  long var7 = System.currentTimeMillis();
                  this.out.println(this.langUtil.getTextMessage("IJ_ElapTime0Mil", this.langUtil.getNumberAsString(var7 - var5)));
               }
            }
         } catch (ParseException var11) {
            if (var3 != null) {
               var1 += this.doCatch(var3) ? 0 : 1;
            }
         } catch (TokenMgrError var12) {
            if (var3 != null) {
               var1 += this.doCatch(var3) ? 0 : 1;
            }
         } catch (SQLException var13) {
            ++var1;
            this.handleSQLException(this.out, var13);
         } catch (ijException var14) {
            ++var1;
            this.out.println(this.langUtil.getTextMessage("IJ_IjErro0", var14.getMessage()));
            this.doTrace(var14);
         } catch (Throwable var15) {
            ++var1;
            this.out.println(this.langUtil.getTextMessage("IJ_JavaErro0", var15.toString()));
            var15.printStackTrace();
            this.doTrace(var15);
         }
      }

      return var1;
   }

   private void cleanupGo(LocalizedInput[] var1) {
      try {
         for(int var2 = 0; var2 < this.connEnv.length; ++var2) {
            this.connEnv[var2].removeAllSessions();
         }
      } catch (SQLException var7) {
         this.handleSQLException(this.out, var7);
      }

      for(int var8 = 0; var8 < this.numConnections; ++var8) {
         try {
            var1[var8].close();
         } catch (Exception var6) {
            this.out.println(this.langUtil.getTextMessage("IJ_CannotCloseInFile", var6.toString()));
         }
      }

      if (this.ijParser.exit || this.initialFileInput && !this.mtUse) {
         Object var9 = null;

         try {
            var10 = DriverManager.getDriver("jdbc:derby:");
         } catch (Throwable var5) {
            var10 = null;
         }

         if (var10 != null) {
            try {
               DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException var4) {
            }
         }
      }

   }

   private void displayResult(LocalizedOutput var1, ijResult var2, Connection var3) throws SQLException {
      if (var2 != null) {
         if (var2.isConnection()) {
            if (var2.hasWarnings()) {
               JDBCDisplayUtil.ShowWarnings((PrintWriter)var1, (SQLWarning)var2.getSQLWarnings());
               var2.clearSQLWarnings();
            }
         } else if (var2.isStatement()) {
            Statement var4 = var2.getStatement();

            try {
               JDBCDisplayUtil.DisplayResults((PrintWriter)var1, (Statement)var4, this.connEnv[this.currCE].getConnection());
            } catch (SQLException var9) {
               var2.closeStatement();
               throw var9;
            }

            var2.closeStatement();
         } else if (var2.isNextRowOfResultSet()) {
            ResultSet var10 = var2.getNextRowOfResultSet();
            JDBCDisplayUtil.DisplayCurrentRow((PrintWriter)var1, var10, this.connEnv[this.currCE].getConnection());
         } else if (var2.isVector()) {
            util.DisplayVector(var1, var2.getVector());
            if (var2.hasWarnings()) {
               JDBCDisplayUtil.ShowWarnings((PrintWriter)var1, (SQLWarning)var2.getSQLWarnings());
               var2.clearSQLWarnings();
            }
         } else if (var2.isMulti()) {
            try {
               util.DisplayMulti(var1, (PreparedStatement)var2.getStatement(), var2.getResultSet(), this.connEnv[this.currCE].getConnection());
            } catch (SQLException var8) {
               var2.closeStatement();
               throw var8;
            }

            var2.closeStatement();
            if (var2.hasWarnings()) {
               JDBCDisplayUtil.ShowWarnings((PrintWriter)var1, (SQLWarning)var2.getSQLWarnings());
               var2.clearSQLWarnings();
            }
         } else if (var2.isResultSet()) {
            ResultSet var11 = var2.getResultSet();

            try {
               JDBCDisplayUtil.DisplayResults(var1, var11, this.connEnv[this.currCE].getConnection(), var2.getColumnDisplayList(), var2.getColumnWidthList());
            } catch (SQLException var7) {
               var2.closeStatement();
               throw var7;
            }

            var2.closeStatement();
         } else if (var2.isMultipleResultSetResult()) {
            List var12 = var2.getMultipleResultSets();

            try {
               JDBCDisplayUtil.DisplayMultipleResults(var1, var12, this.connEnv[this.currCE].getConnection(), var2.getColumnDisplayList(), var2.getColumnWidthList());
            } catch (SQLException var6) {
               var2.closeStatement();
               throw var6;
            }
         } else if (var2.isException()) {
            JDBCDisplayUtil.ShowException((PrintWriter)var1, var2.getException());
         }
      }

   }

   private boolean doCatch(String var1) {
      try {
         boolean var2 = this.ijParser.getElapsedTimeState();
         long var3 = 0L;
         if (var2) {
            var3 = System.currentTimeMillis();
         }

         ijResult var7 = this.ijParser.executeImmediate(var1);
         this.displayResult(this.out, var7, this.connEnv[this.currCE].getConnection());
         if (var2) {
            long var5 = System.currentTimeMillis();
            this.out.println(this.langUtil.getTextMessage("IJ_ElapTime0Mil_4", this.langUtil.getNumberAsString(var5 - var3)));
         }

         return true;
      } catch (SQLException var8) {
         this.handleSQLException(this.out, var8);
      } catch (ijException var9) {
         this.out.println(this.langUtil.getTextMessage("IJ_IjErro0_5", var9.getMessage()));
         this.doTrace(var9);
      } catch (ijTokenException var10) {
         this.out.println(this.langUtil.getTextMessage("IJ_IjErro0_6", var10.getMessage()));
         this.doTrace(var10);
      } catch (Throwable var11) {
         this.out.println(this.langUtil.getTextMessage("IJ_JavaErro0_7", var11.toString()));
         var11.printStackTrace();
         this.doTrace(var11);
      }

      return false;
   }

   private void handleSQLException(LocalizedOutput var1, SQLException var2) throws ijFatalException {
      Object var4 = null;
      SQLException var5 = null;
      String var3;
      if (this.showErrorCode) {
         var3 = this.langUtil.getTextMessage("IJ_Erro0", this.langUtil.getNumberAsString(var2.getErrorCode()));
      } else {
         var3 = "";
      }

      boolean var6;
      for(var6 = false; var2 != null; var2 = var2.getNextException()) {
         String var9 = var2.getSQLState();
         if ("42X01".equals(var9)) {
            var6 = true;
         }

         if (this.ignoreErrors != null) {
            if (var9 != null && this.ignoreErrors.get(var9) != null) {
               continue;
            }

            var5 = var2;
         }

         String var7 = JDBCDisplayUtil.mapNull(var2.getSQLState(), this.langUtil.getTextMessage("IJ_NoSqls"));
         String var8 = JDBCDisplayUtil.mapNull(var2.getMessage(), this.langUtil.getTextMessage("IJ_NoMess"));
         var1.println(this.langUtil.getTextMessage("IJ_Erro012", var7, var8, var3));
         this.doTrace(var2);
      }

      if (var5 != null) {
         throw new ijFatalException(var5);
      } else {
         if (var6) {
            var1.println(this.langUtil.getTextMessage("IJ_SuggestHelp"));
         }

      }
   }

   private void doTrace(Throwable var1) {
      if (this.ijExceptionTrace != null) {
         var1.printStackTrace(this.out);
      }

      this.out.flush();
   }

   void newInput(String var1) {
      Object var2 = null;

      try {
         var5 = new FileInputStream(var1);
      } catch (FileNotFoundException var4) {
         throw ijException.fileNotFound();
      }

      if (var5 != null) {
         this.oldGrabbers.push(this.commandGrabber[this.currCE]);
         this.commandGrabber[this.currCE] = new StatementFinder(this.langUtil.getNewInput(new BufferedInputStream(var5, 2048)), (LocalizedOutput)null);
         this.fileInput = true;
      }
   }

   void newResourceInput(String var1) {
      InputStream var2 = util.getResourceAsStream(var1);
      if (var2 == null) {
         throw ijException.resourceNotFound();
      } else {
         this.oldGrabbers.push(this.commandGrabber[this.currCE]);
         this.commandGrabber[this.currCE] = new StatementFinder(this.langUtil.getNewEncodedInput(new BufferedInputStream(var2, 2048), "UTF8"), (LocalizedOutput)null);
         this.fileInput = true;
      }
   }

   static void doPrompt(boolean var0, LocalizedOutput var1, String var2) {
      if (var0) {
         var1.print("ij" + (var2 == null ? "" : var2) + "> ");
      } else {
         var1.print("> ");
      }

      var1.flush();
   }

   void setMtUse(boolean var1) {
      this.mtUse = var1;
   }

   private void checkScrollableCursor(ResultSet var1, String var2) throws ijException, SQLException {
      if (var1.getType() == 1003) {
         throw ijException.forwardOnlyCursor(var2);
      }
   }

   ijResult absolute(ResultSet var1, int var2) throws SQLException {
      this.checkScrollableCursor(var1, "ABSOLUTE");
      return new ijRowResult(var1, var1.absolute(var2));
   }

   ijResult relative(ResultSet var1, int var2) throws SQLException {
      this.checkScrollableCursor(var1, "RELATIVE");
      return new ijRowResult(var1, var1.relative(var2));
   }

   ijResult beforeFirst(ResultSet var1) throws SQLException {
      this.checkScrollableCursor(var1, "BEFORE FIRST");
      var1.beforeFirst();
      return new ijRowResult(var1, false);
   }

   ijResult first(ResultSet var1) throws SQLException {
      this.checkScrollableCursor(var1, "FIRST");
      return new ijRowResult(var1, var1.first());
   }

   ijResult afterLast(ResultSet var1) throws SQLException {
      this.checkScrollableCursor(var1, "AFTER LAST");
      var1.afterLast();
      return new ijRowResult(var1, false);
   }

   ijResult last(ResultSet var1) throws SQLException {
      this.checkScrollableCursor(var1, "LAST");
      return new ijRowResult(var1, var1.last());
   }

   ijResult previous(ResultSet var1) throws SQLException {
      this.checkScrollableCursor(var1, "PREVIOUS");
      return new ijRowResult(var1, var1.previous());
   }

   int getCurrentRowNumber(ResultSet var1) throws SQLException {
      this.checkScrollableCursor(var1, "GETCURRENTROWNUMBER");
      return var1.getRow();
   }
}
