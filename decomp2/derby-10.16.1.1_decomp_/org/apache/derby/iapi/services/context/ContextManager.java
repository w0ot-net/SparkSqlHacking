package org.apache.derby.iapi.services.context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.shared.common.error.ErrorStringBuilder;
import org.apache.derby.shared.common.error.ExceptionUtil;
import org.apache.derby.shared.common.error.PassThroughException;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.LocaleFinder;
import org.apache.derby.shared.common.info.JVMInfo;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public class ContextManager {
   private final HashMap ctxTable = new HashMap();
   private final ArrayList holder = new ArrayList();
   private Locale messageLocale;
   final ContextService owningCsf;
   private int logSeverityLevel;
   private int extDiagSeverityLevel;
   private HeaderPrintWriter errorStream;
   private ErrorStringBuilder errorStringBuilder;
   private String threadDump;
   private boolean shutdown;
   private LocaleFinder finder;
   Thread activeThread;
   int activeCount;

   public void pushContext(Context var1) {
      this.checkInterrupt();
      String var2 = var1.getIdName();
      CtxStack var3 = (CtxStack)this.ctxTable.get(var2);
      if (var3 == null) {
         var3 = new CtxStack();
         this.ctxTable.put(var2, var3);
      }

      var3.push(var1);
      this.holder.add(var1);
   }

   public Context getContext(String var1) {
      this.checkInterrupt();
      CtxStack var2 = (CtxStack)this.ctxTable.get(var1);
      return var2 == null ? null : var2.top();
   }

   public void popContext() {
      this.checkInterrupt();
      if (!this.holder.isEmpty()) {
         Context var1 = (Context)this.holder.remove(this.holder.size() - 1);
         String var2 = var1.getIdName();
         CtxStack var3 = (CtxStack)this.ctxTable.get(var2);
         var3.pop();
      }
   }

   void popContext(Context var1) {
      this.checkInterrupt();
      this.holder.remove(this.holder.lastIndexOf(var1));
      String var2 = var1.getIdName();
      CtxStack var3 = (CtxStack)this.ctxTable.get(var2);
      var3.remove(var1);
   }

   final boolean isEmpty() {
      return this.holder.isEmpty();
   }

   public final List getContextStack(String var1) {
      CtxStack var2 = (CtxStack)this.ctxTable.get(var1);
      return var2 == null ? Collections.emptyList() : var2.getUnmodifiableList();
   }

   public boolean cleanupOnError(Throwable var1, boolean var2) {
      if (this.shutdown) {
         return true;
      } else {
         if (this.errorStringBuilder == null) {
            this.errorStringBuilder = new ErrorStringBuilder(this.errorStream.getHeader());
         }

         ThreadDeath var3 = null;
         if (var1 instanceof ThreadDeath) {
            var3 = (ThreadDeath)var1;
         }

         if (var1 instanceof PassThroughException) {
            var1 = ((Throwable)var1).getCause();
         }

         boolean var4 = this.reportError((Throwable)var1);
         if (var4) {
            StringBuffer var5 = null;
            if (!this.shutdown) {
               ContextImpl var6 = (ContextImpl)this.getContext("LanguageConnectionContext");
               if (var6 != null) {
                  var5 = var6.appendErrorInfo();
               }
            }

            String var13 = "Cleanup action starting";
            if (var5 != null) {
               var5.append(var13);
               var13 = var5.toString();
            }

            this.errorStringBuilder.appendln(var13);
            if (!this.shutdown) {
               ContextImpl var7 = (ContextImpl)this.getContext("StatementContext");
               if (var7 != null) {
                  var5 = var7.appendErrorInfo();
                  if (var5 != null) {
                     this.errorStringBuilder.appendln(var5.toString());
                  }
               }
            }
         }

         label121:
         while(true) {
            int var12 = this.getErrorSeverity((Throwable)var1);
            if (var4) {
               this.errorStringBuilder.stackTrace((Throwable)var1);
               this.flushErrorString();
            }

            boolean var14 = false;
            int var15 = this.holder.size() - 1;

            while(true) {
               if (var15 < 0) {
                  break label121;
               }

               try {
                  if (var14) {
                     break label121;
                  }

                  Context var8 = (Context)this.holder.get(var15);
                  var14 = var8.isLastHandler(var12);
                  var8.cleanupOnError((Throwable)var1);
                  if (var4 && var2 && var12 >= this.extDiagSeverityLevel) {
                     this.threadDump = ExceptionUtil.dumpThreads();
                  } else {
                     this.threadDump = null;
                  }
               } catch (StandardException var9) {
                  if (var1 instanceof StandardException && var9.getSeverity() > ((StandardException)var1).getSeverity()) {
                     var1 = var9;
                     var4 = this.reportError(var9);
                     if (var4) {
                        this.errorStream.println("New exception raised during cleanup " + ((Throwable)var9).getMessage());
                        this.errorStream.flush();
                     }
                     break;
                  }

                  if (this.reportError(var9)) {
                     this.errorStringBuilder.appendln("Less severe exception raised during cleanup (ignored) " + var9.getMessage());
                     this.errorStringBuilder.stackTrace(var9);
                     this.flushErrorString();
                  }
               } catch (Throwable var10) {
                  var4 = this.reportError(var10);
                  if (var1 instanceof StandardException) {
                     var1 = var10;
                     if (var4) {
                        this.errorStream.println("New exception raised during cleanup " + var10.getMessage());
                        this.errorStream.flush();
                     }
                     break;
                  }

                  if (var4) {
                     this.errorStringBuilder.appendln("Equally severe exception raised during cleanup (ignored) " + var10.getMessage());
                     this.errorStringBuilder.stackTrace(var10);
                     this.flushErrorString();
                  }

                  if (var10 instanceof ThreadDeath) {
                     if (var3 != null) {
                        throw var3;
                     }

                     var3 = (ThreadDeath)var10;
                  }
               }

               --var15;
            }
         }

         if (this.threadDump != null) {
            this.errorStream.println(this.threadDump);
            JVMInfo.javaDump();
         }

         if (var4) {
            this.errorStream.println("Cleanup action completed");
            this.errorStream.flush();
         }

         if (var3 != null) {
            throw var3;
         } else {
            return false;
         }
      }
   }

   synchronized boolean setInterrupted(Context var1) {
      boolean var2 = var1 == null || this.holder.contains(var1);
      if (var2) {
         this.shutdown = true;
      }

      return var2;
   }

   private void checkInterrupt() {
      if (this.shutdown) {
         throw new ShutdownException();
      }
   }

   public void setLocaleFinder(LocaleFinder var1) {
      this.finder = var1;
   }

   public void setMessageLocale(String var1) throws StandardException {
      this.messageLocale = Monitor.getLocaleFromString(var1);
   }

   public Locale getMessageLocale() {
      if (this.messageLocale != null) {
         return this.messageLocale;
      } else {
         if (this.finder != null) {
            try {
               return this.finder.getCurrentLocale();
            } catch (StandardException var2) {
            }
         }

         return Locale.getDefault();
      }
   }

   private void flushErrorString() {
      this.errorStream.print(this.errorStringBuilder.get().toString());
      this.errorStream.flush();
      this.errorStringBuilder.reset();
   }

   private boolean reportError(Throwable var1) {
      if (var1 instanceof StandardException) {
         StandardException var2 = (StandardException)var1;
         switch (var2.report()) {
            case 0:
               int var3 = var2.getSeverity();
               return var3 >= this.logSeverityLevel || var3 == 0;
            case 1:
               return false;
            case 2:
            default:
               return true;
         }
      } else {
         return !(var1 instanceof ShutdownException);
      }
   }

   public int getErrorSeverity(Throwable var1) {
      if (var1 instanceof StandardException) {
         return ((StandardException)var1).getErrorCode();
      } else {
         return var1 instanceof SQLException ? ((SQLException)var1).getErrorCode() : 0;
      }
   }

   ContextManager(ContextService var1, HeaderPrintWriter var2) {
      this.errorStream = var2;
      this.owningCsf = var1;
      this.logSeverityLevel = PropertyUtil.getSystemInt("derby.stream.error.logSeverityLevel", 40000);
      this.extDiagSeverityLevel = PropertyUtil.getSystemInt("derby.stream.error.extendedDiagSeverityLevel", 40000);
   }

   private static final class CtxStack {
      private final ArrayList stack_ = new ArrayList();
      private final List view_;
      private Context top_;

      private CtxStack() {
         this.view_ = Collections.unmodifiableList(this.stack_);
         this.top_ = null;
      }

      void push(Context var1) {
         this.stack_.add(var1);
         this.top_ = var1;
      }

      void pop() {
         this.stack_.remove(this.stack_.size() - 1);
         this.top_ = this.stack_.isEmpty() ? null : (Context)this.stack_.get(this.stack_.size() - 1);
      }

      void remove(Context var1) {
         if (var1 == this.top_) {
            this.pop();
         } else {
            this.stack_.remove(this.stack_.lastIndexOf(var1));
         }
      }

      Context top() {
         return this.top_;
      }

      boolean isEmpty() {
         return this.stack_.isEmpty();
      }

      List getUnmodifiableList() {
         return this.view_;
      }
   }
}
