package org.apache.ivy.util;

import java.util.List;

public interface MessageLogger {
   void log(String var1, int var2);

   void rawlog(String var1, int var2);

   void debug(String var1);

   void verbose(String var1);

   void deprecated(String var1);

   void info(String var1);

   void rawinfo(String var1);

   void warn(String var1);

   void error(String var1);

   List getProblems();

   List getWarns();

   List getErrors();

   void clearProblems();

   void sumupProblems();

   void progress();

   void endProgress();

   void endProgress(String var1);

   boolean isShowProgress();

   void setShowProgress(boolean var1);
}
