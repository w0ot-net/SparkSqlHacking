package org.apache.derby.iapi.jdbc;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Properties;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;

public final class DRDAServerStarter implements ModuleControl, Runnable {
   private Object server;
   private Method runServerMethod;
   private Method serverShutdownMethod;
   private Thread serverThread;
   private static final String serverClassName = "org.apache.derby.impl.drda.NetworkServerControlImpl";
   private Class serverClass;
   private InetAddress listenAddress = null;
   private int portNumber = -1;
   private String userArg = null;
   private String passwordArg = null;
   private PrintWriter consoleWriter = null;

   public void setStartInfo(InetAddress var1, int var2, String var3, String var4, PrintWriter var5) {
      this.userArg = var3;
      this.passwordArg = var4;
      this.setStartInfo(var1, var2, var5);
   }

   public void setStartInfo(InetAddress var1, int var2, PrintWriter var3) {
      this.listenAddress = var1;
      this.portNumber = var2;
      if (var3 != null) {
         this.consoleWriter = new PrintWriter(var3, true);
      } else {
         this.consoleWriter = var3;
      }

   }

   private void findStartStopMethods(Class var1) throws NoSuchMethodException {
      this.runServerMethod = var1.getMethod("blockingStart", PrintWriter.class);
      this.serverShutdownMethod = var1.getMethod("directShutdown", (Class[])null);
   }

   public void boot(boolean var1, Properties var2) {
      if (this.server == null) {
         try {
            this.serverClass = Class.forName("org.apache.derby.impl.drda.NetworkServerControlImpl");
         } catch (ClassNotFoundException var7) {
            Monitor.logTextMessage("J100", "org.apache.derby.impl.drda.NetworkServerControlImpl");
            return;
         } catch (Error var8) {
            Monitor.logTextMessage("J101", "org.apache.derby.impl.drda.NetworkServerControlImpl", var8.getMessage());
            return;
         }

         try {
            Constructor var3;
            try {
               if (this.listenAddress == null) {
                  var3 = this.serverClass.getConstructor(String.class, String.class);
               } else {
                  var3 = this.serverClass.getConstructor(InetAddress.class, Integer.TYPE, String.class, String.class);
               }
            } catch (Exception var5) {
               Monitor.logTextMessage("J102", var5.getMessage());
               var5.printStackTrace(Monitor.getStream().getPrintWriter());
               return;
            }

            this.findStartStopMethods(this.serverClass);
            if (this.listenAddress == null) {
               this.server = var3.newInstance(this.userArg, this.passwordArg);
            } else {
               this.server = var3.newInstance(this.listenAddress, this.portNumber, this.userArg, this.passwordArg);
            }

            this.serverThread = getMonitor().getDaemonThread(this, "NetworkServerStarter", false);
            this.serverThread.start();
         } catch (Exception var6) {
            Monitor.logTextMessage("J102", var6.getMessage());
            this.server = null;
            var6.printStackTrace(Monitor.getStream().getPrintWriter());
         }

      }
   }

   public void run() {
      try {
         this.runServerMethod.invoke(this.server, this.consoleWriter);
      } catch (InvocationTargetException var2) {
         Monitor.logTextMessage("J102", var2.getTargetException().getMessage());
         var2.printStackTrace(Monitor.getStream().getPrintWriter());
         this.server = null;
      } catch (Exception var3) {
         Monitor.logTextMessage("J102", var3.getMessage());
         this.server = null;
         var3.printStackTrace(Monitor.getStream().getPrintWriter());
      }

   }

   public void stop() {
      try {
         if (this.serverThread != null && this.serverThread.isAlive()) {
            this.serverShutdownMethod.invoke(this.server, (Object[])null);
            this.serverThread.interrupt();
            this.serverThread = null;
         }
      } catch (InvocationTargetException var2) {
         Monitor.logTextMessage("J103", var2.getTargetException().getMessage());
         var2.printStackTrace(Monitor.getStream().getPrintWriter());
      } catch (Exception var3) {
         Monitor.logTextMessage("J103", var3.getMessage());
         var3.printStackTrace(Monitor.getStream().getPrintWriter());
      }

      this.serverThread = null;
      this.server = null;
      this.serverClass = null;
      this.listenAddress = null;
      this.portNumber = -1;
      this.consoleWriter = null;
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
