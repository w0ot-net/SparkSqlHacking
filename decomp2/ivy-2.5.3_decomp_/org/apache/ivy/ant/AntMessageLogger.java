package org.apache.ivy.ant;

import org.apache.ivy.Ivy;
import org.apache.ivy.util.AbstractMessageLogger;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.MessageLogger;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.Task;

public class AntMessageLogger extends AbstractMessageLogger {
   private static final int PROGRESS_LOG_PERIOD = 1500;
   private ProjectComponent task;
   private long lastProgressFlush = 0L;
   private StringBuilder buf = new StringBuilder();

   public static void register(ProjectComponent task, final Ivy ivy) {
      MessageLogger current = ivy.getLoggerEngine().peekLogger();
      if (current instanceof AntMessageLogger && task instanceof Task && ((AntMessageLogger)current).task instanceof Task) {
         Task currentTask = (Task)((AntMessageLogger)current).task;
         if (currentTask.getTaskName() != null && currentTask.getTaskName().equals(((Task)task).getTaskName())) {
            return;
         }
      }

      AntMessageLogger logger = new AntMessageLogger(task);
      ivy.getLoggerEngine().pushLogger(logger);
      task.getProject().addBuildListener(new BuildListener() {
         private int stackDepth = 0;

         public void buildFinished(BuildEvent event) {
         }

         public void buildStarted(BuildEvent event) {
         }

         public void targetStarted(BuildEvent event) {
         }

         public void targetFinished(BuildEvent event) {
         }

         public void taskStarted(BuildEvent event) {
            ++this.stackDepth;
         }

         public void taskFinished(BuildEvent event) {
            --this.stackDepth;
            if (this.stackDepth == -1) {
               ivy.getLoggerEngine().popLogger();
               event.getProject().removeBuildListener(this);
            }

         }

         public void messageLogged(BuildEvent event) {
         }
      });
   }

   protected AntMessageLogger(ProjectComponent task) {
      Checks.checkNotNull(task, "task");
      this.task = task;
   }

   public void log(String msg, int level) {
      this.task.log(msg, level);
   }

   public void rawlog(String msg, int level) {
      this.task.getProject().log(msg, level);
   }

   public void doProgress() {
      this.buf.append(".");
      if (this.lastProgressFlush == 0L) {
         this.lastProgressFlush = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() - this.lastProgressFlush > 1500L) {
         this.task.log(this.buf.toString());
         this.buf.setLength(0);
         this.lastProgressFlush = System.currentTimeMillis();
      }

   }

   public void doEndProgress(String msg) {
      this.task.log(this.buf + msg);
      this.buf.setLength(0);
      this.lastProgressFlush = 0L;
   }

   public String toString() {
      return "AntMessageLogger:" + this.task;
   }
}
