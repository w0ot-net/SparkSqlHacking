package py4j;

import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import py4j.reflection.MethodInvoker;
import py4j.reflection.PythonProxyHandler;
import py4j.reflection.ReflectionEngine;

public class Gateway {
   private final Map bindings;
   private final AtomicInteger objCounter;
   private final AtomicInteger argCounter;
   private static final String OBJECT_NAME_PREFIX = "o";
   private final Object entryPoint;
   private final ReflectionEngine rEngine;
   private Py4JPythonClient cbClient;
   private final JVMView defaultJVMView;
   private final Logger logger;
   private boolean isStarted;

   public Gateway(Object entryPoint) {
      this(entryPoint, (Py4JPythonClient)null);
   }

   public Gateway(Object entryPoint, Py4JPythonClient cbClient) {
      this.bindings = new ConcurrentHashMap();
      this.objCounter = new AtomicInteger();
      this.argCounter = new AtomicInteger();
      this.rEngine = new ReflectionEngine();
      this.logger = Logger.getLogger(Gateway.class.getName());
      this.isStarted = false;
      this.entryPoint = entryPoint;
      this.cbClient = cbClient;
      this.defaultJVMView = new JVMView("default", "j");
   }

   public void resetCallbackClient(InetAddress pythonAddress, int pythonPort) {
      if (this.cbClient == null) {
         throw new Py4JException("Callback Client is already null and cannot be reset");
      } else {
         this.cbClient.shutdown();
         this.cbClient = this.cbClient.copyWith(pythonAddress, pythonPort);
      }
   }

   public void closeConnection() {
      this.logger.info("Cleaning Connection");
   }

   public void deleteObject(String objectId) {
      this.bindings.remove(objectId);
   }

   protected AtomicInteger getArgCounter() {
      return this.argCounter;
   }

   public Map getBindings() {
      return this.bindings;
   }

   public Py4JPythonClient getCallbackClient() {
      return this.cbClient;
   }

   public JVMView getDefaultJVMView() {
      return this.defaultJVMView;
   }

   public Object getEntryPoint() {
      return this.entryPoint;
   }

   protected String getNextObjectId() {
      return "o" + this.objCounter.getAndIncrement();
   }

   protected AtomicInteger getObjCounter() {
      return this.objCounter;
   }

   public Object getObject(String objectId) {
      return this.bindings.get(objectId);
   }

   protected Object getObjectFromId(String targetObjectId) {
      return targetObjectId.startsWith("z:") ? null : this.getObject(targetObjectId);
   }

   public ReflectionEngine getReflectionEngine() {
      return this.rEngine;
   }

   public ReturnObject getReturnObject(Object object) {
      ReturnObject returnObject;
      if (object != null) {
         if (this.isPrimitiveObject(object)) {
            returnObject = ReturnObject.getPrimitiveReturnObject(object);
         } else if (object == ReflectionEngine.RETURN_VOID) {
            returnObject = ReturnObject.getVoidReturnObject();
         } else if (this.isDecimalObject(object)) {
            returnObject = ReturnObject.getDecimalReturnObject(object);
         } else if (this.isList(object)) {
            String objectId = this.putNewObject(object);
            returnObject = ReturnObject.getListReturnObject(objectId, ((List)object).size());
         } else if (this.isMap(object)) {
            String objectId = this.putNewObject(object);
            returnObject = ReturnObject.getMapReturnObject(objectId, ((Map)object).size());
         } else if (this.isArray(object)) {
            String objectId = this.putNewObject(object);
            returnObject = ReturnObject.getArrayReturnObject(objectId, Array.getLength(object));
         } else if (this.isSet(object)) {
            String objectId = this.putNewObject(object);
            returnObject = ReturnObject.getSetReturnObject(objectId, ((Set)object).size());
         } else if (this.isIterator(object)) {
            String objectId = this.putNewObject(object);
            returnObject = ReturnObject.getIteratorReturnObject(objectId);
         } else {
            String objectId = this.putNewObject(object);
            returnObject = ReturnObject.getReferenceReturnObject(objectId);
         }
      } else {
         returnObject = ReturnObject.getNullReturnObject();
      }

      return returnObject;
   }

   public ReturnObject invoke(String fqn, List args) {
      if (args == null) {
         args = new ArrayList();
      }

      ReturnObject returnObject = null;

      try {
         this.logger.finer("Calling constructor: " + fqn);
         Object[] parameters = args.toArray();
         MethodInvoker method = this.rEngine.getConstructor(fqn, parameters);
         Object object = this.rEngine.invoke((Object)null, method, parameters);
         returnObject = this.getReturnObject(object);
      } catch (Py4JJavaException je) {
         String id = this.putNewObject(je.getCause());
         returnObject = ReturnObject.getErrorReferenceReturnObject(id);
      } catch (Py4JException pe) {
         throw pe;
      } catch (Exception e) {
         throw new Py4JException(e);
      }

      return returnObject;
   }

   public ReturnObject invoke(String methodName, String targetObjectId, List args) {
      if (args == null) {
         args = new ArrayList();
      }

      ReturnObject returnObject = null;

      try {
         Object targetObject = this.getObjectFromId(targetObjectId);
         this.logger.finer("Calling: " + methodName);
         Object[] parameters = args.toArray();
         MethodInvoker method = null;
         if (targetObject != null) {
            method = this.rEngine.getMethod(targetObject, methodName, parameters);
         } else {
            if (!targetObjectId.startsWith("z:")) {
               throw new Py4JException("Target Object ID does not exist for this gateway :" + targetObjectId);
            }

            method = this.rEngine.getMethod(targetObjectId.substring("z:".length()), methodName, parameters);
         }

         Object object = this.rEngine.invoke(targetObject, method, parameters);
         returnObject = this.getReturnObject(object);
      } catch (Py4JJavaException je) {
         String id = this.putNewObject(je.getCause());
         returnObject = ReturnObject.getErrorReferenceReturnObject(id);
      } catch (Py4JException pe) {
         throw pe;
      } catch (Exception e) {
         throw new Py4JException(e);
      }

      return returnObject;
   }

   protected boolean isArray(Object object) {
      return object.getClass().isArray();
   }

   protected boolean isDecimalObject(Object object) {
      return object instanceof BigDecimal;
   }

   private boolean isIterator(Object object) {
      return object instanceof Iterator;
   }

   protected boolean isList(Object object) {
      return object instanceof List;
   }

   protected boolean isMap(Object object) {
      return object instanceof Map;
   }

   protected boolean isPrimitiveObject(Object object) {
      return object instanceof Boolean || object instanceof String || object instanceof Number && !(object instanceof BigDecimal) && !(object instanceof BigInteger) || object instanceof Character || object instanceof byte[];
   }

   protected boolean isSet(Object object) {
      return object instanceof Set;
   }

   public boolean isStarted() {
      return this.isStarted;
   }

   public String putNewObject(Object object) {
      String id = this.getNextObjectId();
      this.bindings.put(id, object);
      return id;
   }

   public Object putObject(String id, Object object) {
      return this.bindings.put(id, object);
   }

   public void setStarted(boolean isStarted) {
      this.isStarted = isStarted;
   }

   public Object createProxy(ClassLoader classLoader, Class[] interfacesToImplement, String objectId) {
      return Proxy.newProxyInstance(classLoader, interfacesToImplement, this.createPythonProxyHandler(objectId));
   }

   protected PythonProxyHandler createPythonProxyHandler(String id) {
      return new PythonProxyHandler(id, this);
   }

   public void shutdown() {
      this.shutdown(true);
   }

   public void shutdown(boolean shutdownCallbackClient) {
      this.isStarted = false;
      this.bindings.clear();
      if (this.cbClient != null && shutdownCallbackClient) {
         this.cbClient.shutdown();
      }

   }

   public void startup() {
      this.isStarted = true;
      if (this.entryPoint != null) {
         this.bindings.put("t", this.entryPoint);
      }

      this.bindings.put("j", this.defaultJVMView);
   }
}
