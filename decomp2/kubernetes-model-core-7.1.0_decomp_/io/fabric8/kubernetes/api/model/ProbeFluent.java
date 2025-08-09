package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ProbeFluent extends BaseFluent {
   private ExecActionBuilder exec;
   private Integer failureThreshold;
   private GRPCActionBuilder grpc;
   private HTTPGetActionBuilder httpGet;
   private Integer initialDelaySeconds;
   private Integer periodSeconds;
   private Integer successThreshold;
   private TCPSocketActionBuilder tcpSocket;
   private Long terminationGracePeriodSeconds;
   private Integer timeoutSeconds;
   private Map additionalProperties;

   public ProbeFluent() {
   }

   public ProbeFluent(Probe instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Probe instance) {
      instance = instance != null ? instance : new Probe();
      if (instance != null) {
         this.withExec(instance.getExec());
         this.withFailureThreshold(instance.getFailureThreshold());
         this.withGrpc(instance.getGrpc());
         this.withHttpGet(instance.getHttpGet());
         this.withInitialDelaySeconds(instance.getInitialDelaySeconds());
         this.withPeriodSeconds(instance.getPeriodSeconds());
         this.withSuccessThreshold(instance.getSuccessThreshold());
         this.withTcpSocket(instance.getTcpSocket());
         this.withTerminationGracePeriodSeconds(instance.getTerminationGracePeriodSeconds());
         this.withTimeoutSeconds(instance.getTimeoutSeconds());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ExecAction buildExec() {
      return this.exec != null ? this.exec.build() : null;
   }

   public ProbeFluent withExec(ExecAction exec) {
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

   public Integer getFailureThreshold() {
      return this.failureThreshold;
   }

   public ProbeFluent withFailureThreshold(Integer failureThreshold) {
      this.failureThreshold = failureThreshold;
      return this;
   }

   public boolean hasFailureThreshold() {
      return this.failureThreshold != null;
   }

   public GRPCAction buildGrpc() {
      return this.grpc != null ? this.grpc.build() : null;
   }

   public ProbeFluent withGrpc(GRPCAction grpc) {
      this._visitables.remove("grpc");
      if (grpc != null) {
         this.grpc = new GRPCActionBuilder(grpc);
         this._visitables.get("grpc").add(this.grpc);
      } else {
         this.grpc = null;
         this._visitables.get("grpc").remove(this.grpc);
      }

      return this;
   }

   public boolean hasGrpc() {
      return this.grpc != null;
   }

   public ProbeFluent withNewGrpc(Integer port, String service) {
      return this.withGrpc(new GRPCAction(port, service));
   }

   public GrpcNested withNewGrpc() {
      return new GrpcNested((GRPCAction)null);
   }

   public GrpcNested withNewGrpcLike(GRPCAction item) {
      return new GrpcNested(item);
   }

   public GrpcNested editGrpc() {
      return this.withNewGrpcLike((GRPCAction)Optional.ofNullable(this.buildGrpc()).orElse((Object)null));
   }

   public GrpcNested editOrNewGrpc() {
      return this.withNewGrpcLike((GRPCAction)Optional.ofNullable(this.buildGrpc()).orElse((new GRPCActionBuilder()).build()));
   }

   public GrpcNested editOrNewGrpcLike(GRPCAction item) {
      return this.withNewGrpcLike((GRPCAction)Optional.ofNullable(this.buildGrpc()).orElse(item));
   }

   public HTTPGetAction buildHttpGet() {
      return this.httpGet != null ? this.httpGet.build() : null;
   }

   public ProbeFluent withHttpGet(HTTPGetAction httpGet) {
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

   public Integer getInitialDelaySeconds() {
      return this.initialDelaySeconds;
   }

   public ProbeFluent withInitialDelaySeconds(Integer initialDelaySeconds) {
      this.initialDelaySeconds = initialDelaySeconds;
      return this;
   }

   public boolean hasInitialDelaySeconds() {
      return this.initialDelaySeconds != null;
   }

   public Integer getPeriodSeconds() {
      return this.periodSeconds;
   }

   public ProbeFluent withPeriodSeconds(Integer periodSeconds) {
      this.periodSeconds = periodSeconds;
      return this;
   }

   public boolean hasPeriodSeconds() {
      return this.periodSeconds != null;
   }

   public Integer getSuccessThreshold() {
      return this.successThreshold;
   }

   public ProbeFluent withSuccessThreshold(Integer successThreshold) {
      this.successThreshold = successThreshold;
      return this;
   }

   public boolean hasSuccessThreshold() {
      return this.successThreshold != null;
   }

   public TCPSocketAction buildTcpSocket() {
      return this.tcpSocket != null ? this.tcpSocket.build() : null;
   }

   public ProbeFluent withTcpSocket(TCPSocketAction tcpSocket) {
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

   public Long getTerminationGracePeriodSeconds() {
      return this.terminationGracePeriodSeconds;
   }

   public ProbeFluent withTerminationGracePeriodSeconds(Long terminationGracePeriodSeconds) {
      this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
      return this;
   }

   public boolean hasTerminationGracePeriodSeconds() {
      return this.terminationGracePeriodSeconds != null;
   }

   public Integer getTimeoutSeconds() {
      return this.timeoutSeconds;
   }

   public ProbeFluent withTimeoutSeconds(Integer timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
      return this;
   }

   public boolean hasTimeoutSeconds() {
      return this.timeoutSeconds != null;
   }

   public ProbeFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ProbeFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ProbeFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ProbeFluent removeFromAdditionalProperties(Map map) {
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

   public ProbeFluent withAdditionalProperties(Map additionalProperties) {
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
            ProbeFluent that = (ProbeFluent)o;
            if (!Objects.equals(this.exec, that.exec)) {
               return false;
            } else if (!Objects.equals(this.failureThreshold, that.failureThreshold)) {
               return false;
            } else if (!Objects.equals(this.grpc, that.grpc)) {
               return false;
            } else if (!Objects.equals(this.httpGet, that.httpGet)) {
               return false;
            } else if (!Objects.equals(this.initialDelaySeconds, that.initialDelaySeconds)) {
               return false;
            } else if (!Objects.equals(this.periodSeconds, that.periodSeconds)) {
               return false;
            } else if (!Objects.equals(this.successThreshold, that.successThreshold)) {
               return false;
            } else if (!Objects.equals(this.tcpSocket, that.tcpSocket)) {
               return false;
            } else if (!Objects.equals(this.terminationGracePeriodSeconds, that.terminationGracePeriodSeconds)) {
               return false;
            } else if (!Objects.equals(this.timeoutSeconds, that.timeoutSeconds)) {
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
      return Objects.hash(new Object[]{this.exec, this.failureThreshold, this.grpc, this.httpGet, this.initialDelaySeconds, this.periodSeconds, this.successThreshold, this.tcpSocket, this.terminationGracePeriodSeconds, this.timeoutSeconds, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.exec != null) {
         sb.append("exec:");
         sb.append(this.exec + ",");
      }

      if (this.failureThreshold != null) {
         sb.append("failureThreshold:");
         sb.append(this.failureThreshold + ",");
      }

      if (this.grpc != null) {
         sb.append("grpc:");
         sb.append(this.grpc + ",");
      }

      if (this.httpGet != null) {
         sb.append("httpGet:");
         sb.append(this.httpGet + ",");
      }

      if (this.initialDelaySeconds != null) {
         sb.append("initialDelaySeconds:");
         sb.append(this.initialDelaySeconds + ",");
      }

      if (this.periodSeconds != null) {
         sb.append("periodSeconds:");
         sb.append(this.periodSeconds + ",");
      }

      if (this.successThreshold != null) {
         sb.append("successThreshold:");
         sb.append(this.successThreshold + ",");
      }

      if (this.tcpSocket != null) {
         sb.append("tcpSocket:");
         sb.append(this.tcpSocket + ",");
      }

      if (this.terminationGracePeriodSeconds != null) {
         sb.append("terminationGracePeriodSeconds:");
         sb.append(this.terminationGracePeriodSeconds + ",");
      }

      if (this.timeoutSeconds != null) {
         sb.append("timeoutSeconds:");
         sb.append(this.timeoutSeconds + ",");
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
         return ProbeFluent.this.withExec(this.builder.build());
      }

      public Object endExec() {
         return this.and();
      }
   }

   public class GrpcNested extends GRPCActionFluent implements Nested {
      GRPCActionBuilder builder;

      GrpcNested(GRPCAction item) {
         this.builder = new GRPCActionBuilder(this, item);
      }

      public Object and() {
         return ProbeFluent.this.withGrpc(this.builder.build());
      }

      public Object endGrpc() {
         return this.and();
      }
   }

   public class HttpGetNested extends HTTPGetActionFluent implements Nested {
      HTTPGetActionBuilder builder;

      HttpGetNested(HTTPGetAction item) {
         this.builder = new HTTPGetActionBuilder(this, item);
      }

      public Object and() {
         return ProbeFluent.this.withHttpGet(this.builder.build());
      }

      public Object endHttpGet() {
         return this.and();
      }
   }

   public class TcpSocketNested extends TCPSocketActionFluent implements Nested {
      TCPSocketActionBuilder builder;

      TcpSocketNested(TCPSocketAction item) {
         this.builder = new TCPSocketActionBuilder(this, item);
      }

      public Object and() {
         return ProbeFluent.this.withTcpSocket(this.builder.build());
      }

      public Object endTcpSocket() {
         return this.and();
      }
   }
}
