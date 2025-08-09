package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LifecycleHandlerFluent extends BaseFluent {
   private ExecActionBuilder exec;
   private HTTPGetActionBuilder httpGet;
   private SleepActionBuilder sleep;
   private TCPSocketActionBuilder tcpSocket;
   private Map additionalProperties;

   public LifecycleHandlerFluent() {
   }

   public LifecycleHandlerFluent(LifecycleHandler instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LifecycleHandler instance) {
      instance = instance != null ? instance : new LifecycleHandler();
      if (instance != null) {
         this.withExec(instance.getExec());
         this.withHttpGet(instance.getHttpGet());
         this.withSleep(instance.getSleep());
         this.withTcpSocket(instance.getTcpSocket());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ExecAction buildExec() {
      return this.exec != null ? this.exec.build() : null;
   }

   public LifecycleHandlerFluent withExec(ExecAction exec) {
      this._visitables.remove("exec");
      if (exec != null) {
         this.exec = new ExecActionBuilder(exec);
         this._visitables.get("exec").add(this.exec);
      } else {
         this.exec = null;
         this._visitables.get("exec").remove(this.exec);
      }

      return this;
   }

   public boolean hasExec() {
      return this.exec != null;
   }

   public ExecNested withNewExec() {
      return new ExecNested((ExecAction)null);
   }

   public ExecNested withNewExecLike(ExecAction item) {
      return new ExecNested(item);
   }

   public ExecNested editExec() {
      return this.withNewExecLike((ExecAction)Optional.ofNullable(this.buildExec()).orElse((Object)null));
   }

   public ExecNested editOrNewExec() {
      return this.withNewExecLike((ExecAction)Optional.ofNullable(this.buildExec()).orElse((new ExecActionBuilder()).build()));
   }

   public ExecNested editOrNewExecLike(ExecAction item) {
      return this.withNewExecLike((ExecAction)Optional.ofNullable(this.buildExec()).orElse(item));
   }

   public HTTPGetAction buildHttpGet() {
      return this.httpGet != null ? this.httpGet.build() : null;
   }

   public LifecycleHandlerFluent withHttpGet(HTTPGetAction httpGet) {
      this._visitables.remove("httpGet");
      if (httpGet != null) {
         this.httpGet = new HTTPGetActionBuilder(httpGet);
         this._visitables.get("httpGet").add(this.httpGet);
      } else {
         this.httpGet = null;
         this._visitables.get("httpGet").remove(this.httpGet);
      }

      return this;
   }

   public boolean hasHttpGet() {
      return this.httpGet != null;
   }

   public HttpGetNested withNewHttpGet() {
      return new HttpGetNested((HTTPGetAction)null);
   }

   public HttpGetNested withNewHttpGetLike(HTTPGetAction item) {
      return new HttpGetNested(item);
   }

   public HttpGetNested editHttpGet() {
      return this.withNewHttpGetLike((HTTPGetAction)Optional.ofNullable(this.buildHttpGet()).orElse((Object)null));
   }

   public HttpGetNested editOrNewHttpGet() {
      return this.withNewHttpGetLike((HTTPGetAction)Optional.ofNullable(this.buildHttpGet()).orElse((new HTTPGetActionBuilder()).build()));
   }

   public HttpGetNested editOrNewHttpGetLike(HTTPGetAction item) {
      return this.withNewHttpGetLike((HTTPGetAction)Optional.ofNullable(this.buildHttpGet()).orElse(item));
   }

   public SleepAction buildSleep() {
      return this.sleep != null ? this.sleep.build() : null;
   }

   public LifecycleHandlerFluent withSleep(SleepAction sleep) {
      this._visitables.remove("sleep");
      if (sleep != null) {
         this.sleep = new SleepActionBuilder(sleep);
         this._visitables.get("sleep").add(this.sleep);
      } else {
         this.sleep = null;
         this._visitables.get("sleep").remove(this.sleep);
      }

      return this;
   }

   public boolean hasSleep() {
      return this.sleep != null;
   }

   public LifecycleHandlerFluent withNewSleep(Long seconds) {
      return this.withSleep(new SleepAction(seconds));
   }

   public SleepNested withNewSleep() {
      return new SleepNested((SleepAction)null);
   }

   public SleepNested withNewSleepLike(SleepAction item) {
      return new SleepNested(item);
   }

   public SleepNested editSleep() {
      return this.withNewSleepLike((SleepAction)Optional.ofNullable(this.buildSleep()).orElse((Object)null));
   }

   public SleepNested editOrNewSleep() {
      return this.withNewSleepLike((SleepAction)Optional.ofNullable(this.buildSleep()).orElse((new SleepActionBuilder()).build()));
   }

   public SleepNested editOrNewSleepLike(SleepAction item) {
      return this.withNewSleepLike((SleepAction)Optional.ofNullable(this.buildSleep()).orElse(item));
   }

   public TCPSocketAction buildTcpSocket() {
      return this.tcpSocket != null ? this.tcpSocket.build() : null;
   }

   public LifecycleHandlerFluent withTcpSocket(TCPSocketAction tcpSocket) {
      this._visitables.remove("tcpSocket");
      if (tcpSocket != null) {
         this.tcpSocket = new TCPSocketActionBuilder(tcpSocket);
         this._visitables.get("tcpSocket").add(this.tcpSocket);
      } else {
         this.tcpSocket = null;
         this._visitables.get("tcpSocket").remove(this.tcpSocket);
      }

      return this;
   }

   public boolean hasTcpSocket() {
      return this.tcpSocket != null;
   }

   public TcpSocketNested withNewTcpSocket() {
      return new TcpSocketNested((TCPSocketAction)null);
   }

   public TcpSocketNested withNewTcpSocketLike(TCPSocketAction item) {
      return new TcpSocketNested(item);
   }

   public TcpSocketNested editTcpSocket() {
      return this.withNewTcpSocketLike((TCPSocketAction)Optional.ofNullable(this.buildTcpSocket()).orElse((Object)null));
   }

   public TcpSocketNested editOrNewTcpSocket() {
      return this.withNewTcpSocketLike((TCPSocketAction)Optional.ofNullable(this.buildTcpSocket()).orElse((new TCPSocketActionBuilder()).build()));
   }

   public TcpSocketNested editOrNewTcpSocketLike(TCPSocketAction item) {
      return this.withNewTcpSocketLike((TCPSocketAction)Optional.ofNullable(this.buildTcpSocket()).orElse(item));
   }

   public LifecycleHandlerFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LifecycleHandlerFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LifecycleHandlerFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LifecycleHandlerFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public LifecycleHandlerFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            LifecycleHandlerFluent that = (LifecycleHandlerFluent)o;
            if (!Objects.equals(this.exec, that.exec)) {
               return false;
            } else if (!Objects.equals(this.httpGet, that.httpGet)) {
               return false;
            } else if (!Objects.equals(this.sleep, that.sleep)) {
               return false;
            } else if (!Objects.equals(this.tcpSocket, that.tcpSocket)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.exec, this.httpGet, this.sleep, this.tcpSocket, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.exec != null) {
         sb.append("exec:");
         sb.append(this.exec + ",");
      }

      if (this.httpGet != null) {
         sb.append("httpGet:");
         sb.append(this.httpGet + ",");
      }

      if (this.sleep != null) {
         sb.append("sleep:");
         sb.append(this.sleep + ",");
      }

      if (this.tcpSocket != null) {
         sb.append("tcpSocket:");
         sb.append(this.tcpSocket + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ExecNested extends ExecActionFluent implements Nested {
      ExecActionBuilder builder;

      ExecNested(ExecAction item) {
         this.builder = new ExecActionBuilder(this, item);
      }

      public Object and() {
         return LifecycleHandlerFluent.this.withExec(this.builder.build());
      }

      public Object endExec() {
         return this.and();
      }
   }

   public class HttpGetNested extends HTTPGetActionFluent implements Nested {
      HTTPGetActionBuilder builder;

      HttpGetNested(HTTPGetAction item) {
         this.builder = new HTTPGetActionBuilder(this, item);
      }

      public Object and() {
         return LifecycleHandlerFluent.this.withHttpGet(this.builder.build());
      }

      public Object endHttpGet() {
         return this.and();
      }
   }

   public class SleepNested extends SleepActionFluent implements Nested {
      SleepActionBuilder builder;

      SleepNested(SleepAction item) {
         this.builder = new SleepActionBuilder(this, item);
      }

      public Object and() {
         return LifecycleHandlerFluent.this.withSleep(this.builder.build());
      }

      public Object endSleep() {
         return this.and();
      }
   }

   public class TcpSocketNested extends TCPSocketActionFluent implements Nested {
      TCPSocketActionBuilder builder;

      TcpSocketNested(TCPSocketAction item) {
         this.builder = new TCPSocketActionBuilder(this, item);
      }

      public Object and() {
         return LifecycleHandlerFluent.this.withTcpSocket(this.builder.build());
      }

      public Object endTcpSocket() {
         return this.and();
      }
   }
}
