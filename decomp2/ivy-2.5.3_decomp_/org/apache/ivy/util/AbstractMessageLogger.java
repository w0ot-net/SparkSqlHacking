package org.apache.ivy.util;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMessageLogger implements MessageLogger {
   private List problems = new ArrayList();
   private List warns = new ArrayList();
   private List errors = new ArrayList();
   private boolean showProgress = true;

   public void debug(String msg) {
      this.log(msg, 4);
   }

   public void verbose(String msg) {
      this.log(msg, 3);
   }

   public void deprecated(String msg) {
      this.log("DEPRECATED: " + msg, 1);
   }

   public void info(String msg) {
      this.log(msg, 2);
   }

   public void rawinfo(String msg) {
      this.rawlog(msg, 2);
   }

   public void warn(String msg) {
      this.log("WARN: " + msg, 3);
      this.problems.add("WARN:  " + msg);
      this.getWarns().add(msg);
   }

   public void error(String msg) {
      this.log("ERROR: " + msg, 3);
      this.problems.add("\tERROR: " + msg);
      this.getErrors().add(msg);
   }

   public List getProblems() {
      return this.problems;
   }

   public void sumupProblems() {
      MessageLoggerHelper.sumupProblems(this);
      this.clearProblems();
   }

   public void clearProblems() {
      this.problems.clear();
      this.warns.clear();
      this.errors.clear();
   }

   public List getErrors() {
      return this.errors;
   }

   public List getWarns() {
      return this.warns;
   }

   public void progress() {
      if (this.showProgress) {
         this.doProgress();
      }

   }

   public void endProgress() {
      this.endProgress("");
   }

   public void endProgress(String msg) {
      if (this.showProgress) {
         this.doEndProgress(msg);
      }

   }

   public boolean isShowProgress() {
      return this.showProgress;
   }

   public void setShowProgress(boolean progress) {
      this.showProgress = progress;
   }

   protected abstract void doProgress();

   protected abstract void doEndProgress(String var1);
}
