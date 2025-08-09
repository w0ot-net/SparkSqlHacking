package javassist.util;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.MethodEntryEvent;
import com.sun.jdi.request.EventRequestManager;
import com.sun.jdi.request.MethodEntryRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotSwapper {
   private VirtualMachine jvm;
   private MethodEntryRequest request;
   private Map newClassFiles;
   private Trigger trigger;
   private static final String HOST_NAME = "localhost";
   private static final String TRIGGER_NAME = Trigger.class.getName();

   public HotSwapper(int port) throws IOException, IllegalConnectorArgumentsException {
      this(Integer.toString(port));
   }

   public HotSwapper(String port) throws IOException, IllegalConnectorArgumentsException {
      this.jvm = null;
      this.request = null;
      this.newClassFiles = null;
      this.trigger = new Trigger();
      AttachingConnector connector = (AttachingConnector)this.findConnector("com.sun.jdi.SocketAttach");
      Map<String, Connector.Argument> arguments = connector.defaultArguments();
      ((Connector.Argument)arguments.get("hostname")).setValue("localhost");
      ((Connector.Argument)arguments.get("port")).setValue(port);
      this.jvm = connector.attach(arguments);
      EventRequestManager manager = this.jvm.eventRequestManager();
      this.request = methodEntryRequests(manager, TRIGGER_NAME);
   }

   private Connector findConnector(String connector) throws IOException {
      for(Connector con : Bootstrap.virtualMachineManager().allConnectors()) {
         if (con.name().equals(connector)) {
            return con;
         }
      }

      throw new IOException("Not found: " + connector);
   }

   private static MethodEntryRequest methodEntryRequests(EventRequestManager manager, String classpattern) {
      MethodEntryRequest mereq = manager.createMethodEntryRequest();
      mereq.addClassFilter(classpattern);
      mereq.setSuspendPolicy(1);
      return mereq;
   }

   private void deleteEventRequest(EventRequestManager manager, MethodEntryRequest request) {
      manager.deleteEventRequest(request);
   }

   public void reload(String className, byte[] classFile) {
      ReferenceType classtype = this.toRefType(className);
      Map<ReferenceType, byte[]> map = new HashMap();
      map.put(classtype, classFile);
      this.reload2(map, className);
   }

   public void reload(Map classFiles) {
      Map<ReferenceType, byte[]> map = new HashMap();
      String className = null;

      for(Map.Entry e : classFiles.entrySet()) {
         className = (String)e.getKey();
         map.put(this.toRefType(className), (byte[])e.getValue());
      }

      if (className != null) {
         this.reload2(map, className + " etc.");
      }

   }

   private ReferenceType toRefType(String className) {
      List<ReferenceType> list = this.jvm.classesByName(className);
      if (list != null && !list.isEmpty()) {
         return (ReferenceType)list.get(0);
      } else {
         throw new RuntimeException("no such class: " + className);
      }
   }

   private void reload2(Map map, String msg) {
      synchronized(this.trigger) {
         this.startDaemon();
         this.newClassFiles = map;
         this.request.enable();
         this.trigger.doSwap();
         this.request.disable();
         Map<ReferenceType, byte[]> ncf = this.newClassFiles;
         if (ncf != null) {
            this.newClassFiles = null;
            throw new RuntimeException("failed to reload: " + msg);
         }
      }
   }

   private void startDaemon() {
      (new Thread() {
         private void errorMsg(Throwable e) {
            System.err.print("Exception in thread \"HotSwap\" ");
            e.printStackTrace(System.err);
         }

         public void run() {
            EventSet events = null;

            try {
               events = HotSwapper.this.waitEvent();
               EventIterator iter = events.eventIterator();

               while(iter.hasNext()) {
                  Event event = iter.nextEvent();
                  if (event instanceof MethodEntryEvent) {
                     HotSwapper.this.hotswap();
                     break;
                  }
               }
            } catch (Throwable e) {
               this.errorMsg(e);
            }

            try {
               if (events != null) {
                  events.resume();
               }
            } catch (Throwable e) {
               this.errorMsg(e);
            }

         }
      }).start();
   }

   EventSet waitEvent() throws InterruptedException {
      EventQueue queue = this.jvm.eventQueue();
      return queue.remove();
   }

   void hotswap() {
      Map<ReferenceType, byte[]> map = this.newClassFiles;
      this.jvm.redefineClasses(map);
      this.newClassFiles = null;
   }
}
