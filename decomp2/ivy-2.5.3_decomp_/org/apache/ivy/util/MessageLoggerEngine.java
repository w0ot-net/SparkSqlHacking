package org.apache.ivy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MessageLoggerEngine implements MessageLogger {
   private final ThreadLocal loggerStacks = new ThreadLocal();
   private MessageLogger defaultLogger = null;
   private List problems = new ArrayList();
   private List warns = new ArrayList();
   private List errors = new ArrayList();

   private Stack getLoggerStack() {
      Stack<MessageLogger> stack = (Stack)this.loggerStacks.get();
      if (stack == null) {
         stack = new Stack();
         this.loggerStacks.set(stack);
      }

      return stack;
   }

   public void setDefaultLogger(MessageLogger defaultLogger) {
      this.defaultLogger = defaultLogger;
   }

   public void pushLogger(MessageLogger logger) {
      Checks.checkNotNull(logger, "logger");
      this.getLoggerStack().push(logger);
   }

   public void popLogger() {
      if (!this.getLoggerStack().isEmpty()) {
         this.getLoggerStack().pop();
      }

   }

   public MessageLogger peekLogger() {
      return this.getLoggerStack().isEmpty() ? this.getDefaultLogger() : (MessageLogger)this.getLoggerStack().peek();
   }

   private MessageLogger getDefaultLogger() {
      return this.defaultLogger == null ? Message.getDefaultLogger() : this.defaultLogger;
   }

   public void warn(String msg) {
      this.peekLogger().warn(msg);
      this.problems.add("WARN:  " + msg);
      this.warns.add(msg);
   }

   public void error(String msg) {
      this.peekLogger().error(msg);
      this.problems.add("\tERROR: " + msg);
      this.errors.add(msg);
   }

   public List getErrors() {
      return this.errors;
   }

   public List getProblems() {
      return this.problems;
   }

   public List getWarns() {
      return this.warns;
   }

   public void sumupProblems() {
      MessageLoggerHelper.sumupProblems(this);
      this.clearProblems();
   }

   public void clearProblems() {
      this.getDefaultLogger().clearProblems();

      for(MessageLogger l : this.getLoggerStack()) {
         l.clearProblems();
      }

      this.problems.clear();
      this.errors.clear();
      this.warns.clear();
   }

   public void setShowProgress(boolean progress) {
      this.getDefaultLogger().setShowProgress(progress);

      for(MessageLogger l : this.getLoggerStack()) {
         l.setShowProgress(progress);
      }

   }

   public boolean isShowProgress() {
      return this.getDefaultLogger().isShowProgress();
   }

   public void debug(String msg) {
      this.peekLogger().debug(msg);
   }

   public void deprecated(String msg) {
      this.peekLogger().deprecated(msg);
   }

   public void endProgress() {
      this.peekLogger().endProgress();
   }

   public void endProgress(String msg) {
      this.peekLogger().endProgress(msg);
   }

   public void info(String msg) {
      this.peekLogger().info(msg);
   }

   public void rawinfo(String msg) {
      this.peekLogger().rawinfo(msg);
   }

   public void log(String msg, int level) {
      this.peekLogger().log(msg, level);
   }

   public void progress() {
      this.peekLogger().progress();
   }

   public void rawlog(String msg, int level) {
      this.peekLogger().rawlog(msg, level);
   }

   public void verbose(String msg) {
      this.peekLogger().verbose(msg);
   }
}
