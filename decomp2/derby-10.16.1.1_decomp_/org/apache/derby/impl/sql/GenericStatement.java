package org.apache.derby.impl.sql;

import java.sql.Timestamp;
import org.apache.derby.iapi.services.daemon.IndexStatisticsDaemon;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.Statement;
import org.apache.derby.iapi.sql.compile.ASTVisitor;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.sql.compile.StatementNode;
import org.apache.derby.impl.sql.conn.GenericLanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public class GenericStatement implements Statement {
   private final SchemaDescriptor compilationSchema;
   private final String statementText;
   private final boolean isForReadOnly;
   private int prepareIsolationLevel;
   private GenericPreparedStatement preparedStmt;

   public GenericStatement(SchemaDescriptor var1, String var2, boolean var3) {
      this.compilationSchema = var1;
      this.statementText = var2;
      this.isForReadOnly = var3;
   }

   public PreparedStatement prepare(LanguageConnectionContext var1) throws StandardException {
      return this.prepare(var1, false);
   }

   public PreparedStatement prepare(LanguageConnectionContext var1, boolean var2) throws StandardException {
      int var3 = var1.getStatementDepth();
      String var4 = null;

      while(true) {
         boolean var5 = false;

         try {
            PreparedStatement var6 = this.prepMinion(var1, true, (Object[])null, (SchemaDescriptor)null, var2);
            return var6;
         } catch (StandardException var16) {
            if ("XSAI2.S".equals(var16.getMessageId())) {
               String var7 = String.valueOf(var16.getArguments()[0]);
               if (!var7.equals(var4)) {
                  var5 = true;
               }

               var4 = var7;
            }

            throw var16;
         } finally {
            synchronized(this.preparedStmt) {
               if (var5 || this.preparedStmt.invalidatedWhileCompiling) {
                  this.preparedStmt.isValid = false;
                  this.preparedStmt.invalidatedWhileCompiling = false;
                  var5 = true;
               }
            }

            if (!var5) {
               ;
            } else {
               while(var1.getStatementDepth() > var3) {
                  var1.popStatementContext(var1.getStatementContext(), (Throwable)null);
               }
               continue;
            }
         }
      }
   }

   private PreparedStatement prepMinion(LanguageConnectionContext var1, boolean var2, Object[] var3, SchemaDescriptor var4, boolean var5) throws StandardException {
      long var6 = 0L;
      long var8 = 0L;
      long var10 = 0L;
      long var12 = 0L;
      long var14 = 0L;
      Timestamp var16 = null;
      Timestamp var17 = null;
      StatementContext var18 = null;
      if (this.preparedStmt != null && this.preparedStmt.upToDate()) {
         return this.preparedStmt;
      } else {
         if (var1.optimizerTracingIsOn()) {
            var1.getOptimizerTracer().traceStartStatement(this.getSource());
         }

         var6 = getCurrentTimeMillis(var1);
         if (var6 != 0L) {
            var16 = new Timestamp(var6);
         }

         this.prepareIsolationLevel = var1.getPrepareIsolationLevel();
         boolean var19 = false;
         if (this.preparedStmt == null) {
            if (var2) {
               this.preparedStmt = (GenericPreparedStatement)((GenericLanguageConnectionContext)var1).lookupStatement(this);
            }

            if (this.preparedStmt == null) {
               this.preparedStmt = new GenericPreparedStatement(this);
            } else {
               var19 = true;
            }
         }

         synchronized(this.preparedStmt) {
            while(true) {
               if (var19 && this.preparedStmt.referencesSessionSchema()) {
                  var19 = false;
                  this.preparedStmt = new GenericPreparedStatement(this);
                  break;
               }

               if (this.preparedStmt.upToDate()) {
                  return this.preparedStmt;
               }

               if (!this.preparedStmt.isCompiling()) {
                  break;
               }

               try {
                  this.preparedStmt.wait();
               } catch (InterruptedException var56) {
                  InterruptStatus.setInterrupted();
               }
            }

            this.preparedStmt.beginCompiling();
         }

         try {
            HeaderPrintWriter var20 = var1.getLogStatementText() ? Monitor.getStream() : null;
            if (!this.preparedStmt.isStorable() || var1.getStatementDepth() == 0) {
               var18 = var1.pushStatementContext(true, this.isForReadOnly, this.getSource(), (ParameterValueSet)null, false, 0L);
            }

            CompilerContext var21 = var1.pushCompilerContext(this.compilationSchema);
            if (this.prepareIsolationLevel != 0) {
               var21.setScanIsolationLevel(this.prepareIsolationLevel);
            }

            if (var5 || var4 != null && var4.isSystemSchema() && var4.equals(this.compilationSchema)) {
               var21.setReliability(0);
            }

            try {
               if (var20 != null) {
                  String var22 = var1.getTransactionExecute().getActiveStateTxIdString();
                  var20.printlnWithHeader("(XID = " + var22 + "), (SESSIONID = " + var1.getInstanceNumber() + "), (DATABASE = " + var1.getDbname() + "), (DRDAID = " + var1.getDrdaID() + "), Begin compiling prepared statement: " + this.getSource() + " :End prepared statement");
               }

               Parser var69 = var21.getParser();
               var21.setCurrentDependent(this.preparedStmt);
               StatementNode var23 = (StatementNode)var69.parseStatement(this.statementText, var3);
               var8 = getCurrentTimeMillis(var1);
               this.walkAST(var1, var23, 0);
               DataDictionary var24 = var1.getDataDictionary();
               int var25 = var24 == null ? 0 : var24.startReading(var1);

               try {
                  var1.beginNestedTransaction(true);
                  var23.bindStatement();
                  var10 = getCurrentTimeMillis(var1);
                  this.walkAST(var1, var23, 1);
                  if (this.preparedStmt.referencesSessionSchema(var23) && var19) {
                     ((GenericLanguageConnectionContext)var1).removeStatement(this);
                  }

                  var21.skipTypePrivileges(true);
                  var23.optimizeStatement();
                  var12 = getCurrentTimeMillis(var1);
                  this.walkAST(var1, var23, 2);
                  if (var20 != null) {
                     String var26 = var1.getTransactionExecute().getActiveStateTxIdString();
                     var20.printlnWithHeader("(XID = " + var26 + "), (SESSIONID = " + var1.getInstanceNumber() + "), (DATABASE = " + var1.getDbname() + "), (DRDAID = " + var1.getDrdaID() + "), End compiling prepared statement: " + this.getSource() + " :End prepared statement");
                  }
               } catch (StandardException var58) {
                  var1.commitNestedTransaction();
                  if (var20 != null) {
                     String var27 = var1.getTransactionExecute().getActiveStateTxIdString();
                     var20.printlnWithHeader("(XID = " + var27 + "), (SESSIONID = " + var1.getInstanceNumber() + "), (DATABASE = " + var1.getDbname() + "), (DRDAID = " + var1.getDrdaID() + "), Error compiling prepared statement: " + this.getSource() + " :End prepared statement");
                  }

                  throw var58;
               } finally {
                  if (var24 != null) {
                     var24.doneReading(var25, var1);
                  }

               }

               try {
                  GeneratedClass var70 = var23.generate(this.preparedStmt.getByteCodeSaver());
                  var14 = getCurrentTimeMillis(var1);
                  if (var14 != 0L) {
                     var17 = new Timestamp(var14);
                  }

                  this.preparedStmt.setConstantAction(var23.makeConstantAction());
                  this.preparedStmt.setSavedObjects(var21.getSavedObjects());
                  this.preparedStmt.setRequiredPermissionsList(var21.getRequiredPermissionsList());
                  this.preparedStmt.incrementVersionCounter();
                  this.preparedStmt.setActivationClass(var70);
                  this.preparedStmt.setNeedsSavepoint(var23.needsSavepoint());
                  this.preparedStmt.setCursorInfo((CursorInfo)var21.getCursorInfo());
                  this.preparedStmt.setIsAtomic(var23.isAtomic());
                  this.preparedStmt.setExecuteStatementNameAndSchema(var23.executeStatementName(), var23.executeSchemaName());
                  this.preparedStmt.setSPSName(var23.getSPSName());
                  this.preparedStmt.completeCompile(var23);
                  this.preparedStmt.setCompileTimeWarnings(var21.getWarnings());
                  TableDescriptor[] var71 = var23.updateIndexStatisticsFor();
                  if (var71.length > 0) {
                     IndexStatisticsDaemon var28 = var1.getDataDictionary().getIndexStatsRefresher(true);
                     if (var28 != null) {
                        for(int var29 = 0; var29 < var71.length; ++var29) {
                           var28.schedule(var71[var29]);
                        }
                     }
                  }
               } catch (StandardException var57) {
                  var1.commitNestedTransaction();
                  throw var57;
               }

               if (var1.getRunTimeStatisticsMode()) {
                  this.preparedStmt.setCompileTimeMillis(var8 - var6, var10 - var8, var12 - var10, var14 - var12, var14 - var6, var16, var17);
               }
            } finally {
               var1.popCompilerContext(var21);
            }
         } catch (StandardException var61) {
            if (var19) {
               ((GenericLanguageConnectionContext)var1).removeStatement(this);
            }

            throw var61;
         } finally {
            this.preparedStmt.endCompiling();
         }

         var1.commitNestedTransaction();
         if (var18 != null) {
            var1.popStatementContext(var18, (Throwable)null);
         }

         return this.preparedStmt;
      }
   }

   private void walkAST(LanguageConnectionContext var1, Visitable var2, int var3) throws StandardException {
      ASTVisitor var4 = var1.getASTVisitor();
      if (var4 != null) {
         var4.begin(this.statementText, var3);
         var2.accept(var4);
         var4.end(var3);
      }

   }

   public PreparedStatement prepareStorable(LanguageConnectionContext var1, PreparedStatement var2, Object[] var3, SchemaDescriptor var4, boolean var5) throws StandardException {
      if (var2 == null) {
         var2 = new GenericStorablePreparedStatement(this);
      } else {
         ((GenericPreparedStatement)var2).statement = this;
      }

      this.preparedStmt = (GenericPreparedStatement)var2;
      return this.prepMinion(var1, false, var3, var4, var5);
   }

   public String getSource() {
      return this.statementText;
   }

   public String getCompilationSchema() {
      return this.compilationSchema.getDescriptorName();
   }

   private static long getCurrentTimeMillis(LanguageConnectionContext var0) {
      return var0.getStatisticsTiming() ? System.currentTimeMillis() : 0L;
   }

   public PreparedStatement getPreparedStatement() {
      return this.preparedStmt;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof GenericStatement var2)) {
         return false;
      } else {
         return this.statementText.equals(var2.statementText) && this.isForReadOnly == var2.isForReadOnly && this.compilationSchema.equals(var2.compilationSchema) && this.prepareIsolationLevel == var2.prepareIsolationLevel;
      }
   }

   public int hashCode() {
      return this.statementText.hashCode();
   }
}
