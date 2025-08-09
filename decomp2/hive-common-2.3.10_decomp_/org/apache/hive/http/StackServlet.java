package org.apache.hive.http;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.State;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StackServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (HttpServer.isInstrumentationAccessAllowed(this.getServletContext(), request, response)) {
         response.setContentType("text/plain; charset=UTF-8");
         PrintStream out = new PrintStream(response.getOutputStream(), false, "UTF-8");
         Throwable var4 = null;

         try {
            this.printThreadInfo(out, "");
         } catch (Throwable var13) {
            var4 = var13;
            throw var13;
         } finally {
            if (out != null) {
               if (var4 != null) {
                  try {
                     out.close();
                  } catch (Throwable var12) {
                     var4.addSuppressed(var12);
                  }
               } else {
                  out.close();
               }
            }

         }

      }
   }

   private synchronized void printThreadInfo(PrintStream stream, String title) {
      int STACK_DEPTH = 20;
      boolean contention = threadBean.isThreadContentionMonitoringEnabled();
      long[] threadIds = threadBean.getAllThreadIds();
      stream.println("Process Thread Dump: " + title);
      stream.println(threadIds.length + " active threads");

      for(long tid : threadIds) {
         ThreadInfo info = threadBean.getThreadInfo(tid, 20);
         if (info == null) {
            stream.println("  Inactive");
         } else {
            stream.println("Thread " + this.getTaskName(info.getThreadId(), info.getThreadName()) + ":");
            Thread.State state = info.getThreadState();
            stream.println("  State: " + state);
            stream.println("  Blocked count: " + info.getBlockedCount());
            stream.println("  Wtaited count: " + info.getWaitedCount());
            if (contention) {
               stream.println("  Blocked time: " + info.getBlockedTime());
               stream.println("  Waited time: " + info.getWaitedTime());
            }

            if (state == State.WAITING) {
               stream.println("  Waiting on " + info.getLockName());
            } else if (state == State.BLOCKED) {
               stream.println("  Blocked on " + info.getLockName());
               stream.println("  Blocked by " + this.getTaskName(info.getLockOwnerId(), info.getLockOwnerName()));
            }

            stream.println("  Stack:");

            for(StackTraceElement frame : info.getStackTrace()) {
               stream.println("    " + frame.toString());
            }
         }
      }

      stream.flush();
   }

   private String getTaskName(long id, String name) {
      return name == null ? Long.toString(id) : id + " (" + name + ")";
   }
}
