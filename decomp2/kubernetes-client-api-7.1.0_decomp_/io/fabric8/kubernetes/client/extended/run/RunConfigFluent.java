package io.fabric8.kubernetes.client.extended.run;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class RunConfigFluent extends BaseFluent {
   private String name;
   private String image;
   private String imagePullPolicy;
   private String command;
   private List args;
   private String restartPolicy;
   private String serviceAccount;
   private Map labels;
   private Map env;
   private Map limits;
   private Map requests;
   private int port;

   public RunConfigFluent() {
   }

   public RunConfigFluent(RunConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RunConfig instance) {
      if (instance != null) {
         this.withName(instance.getName());
         this.withImage(instance.getImage());
         this.withImagePullPolicy(instance.getImagePullPolicy());
         this.withCommand(instance.getCommand());
         this.withArgs(instance.getArgs());
         this.withRestartPolicy(instance.getRestartPolicy());
         this.withServiceAccount(instance.getServiceAccount());
         this.withLabels(instance.getLabels());
         this.withEnv(instance.getEnv());
         this.withLimits(instance.getLimits());
         this.withRequests(instance.getRequests());
         this.withPort(instance.getPort());
      }

   }

   public String getName() {
      return this.name;
   }

   public RunConfigFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getImage() {
      return this.image;
   }

   public RunConfigFluent withImage(String image) {
      this.image = image;
      return this;
   }

   public boolean hasImage() {
      return this.image != null;
   }

   public String getImagePullPolicy() {
      return this.imagePullPolicy;
   }

   public RunConfigFluent withImagePullPolicy(String imagePullPolicy) {
      this.imagePullPolicy = imagePullPolicy;
      return this;
   }

   public boolean hasImagePullPolicy() {
      return this.imagePullPolicy != null;
   }

   public String getCommand() {
      return this.command;
   }

   public RunConfigFluent withCommand(String command) {
      this.command = command;
      return this;
   }

   public boolean hasCommand() {
      return this.command != null;
   }

   public RunConfigFluent addToArgs(int index, String item) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      this.args.add(index, item);
      return this;
   }

   public RunConfigFluent setToArgs(int index, String item) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      this.args.set(index, item);
      return this;
   }

   public RunConfigFluent addToArgs(String... items) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      for(String item : items) {
         this.args.add(item);
      }

      return this;
   }

   public RunConfigFluent addAllToArgs(Collection items) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      for(String item : items) {
         this.args.add(item);
      }

      return this;
   }

   public RunConfigFluent removeFromArgs(String... items) {
      if (this.args == null) {
         return this;
      } else {
         for(String item : items) {
            this.args.remove(item);
         }

         return this;
      }
   }

   public RunConfigFluent removeAllFromArgs(Collection items) {
      if (this.args == null) {
         return this;
      } else {
         for(String item : items) {
            this.args.remove(item);
         }

         return this;
      }
   }

   public List getArgs() {
      return this.args;
   }

   public String getArg(int index) {
      return (String)this.args.get(index);
   }

   public String getFirstArg() {
      return (String)this.args.get(0);
   }

   public String getLastArg() {
      return (String)this.args.get(this.args.size() - 1);
   }

   public String getMatchingArg(Predicate predicate) {
      for(String item : this.args) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingArg(Predicate predicate) {
      for(String item : this.args) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RunConfigFluent withArgs(List args) {
      if (args != null) {
         this.args = new ArrayList();

         for(String item : args) {
            this.addToArgs(item);
         }
      } else {
         this.args = null;
      }

      return this;
   }

   public RunConfigFluent withArgs(String... args) {
      if (this.args != null) {
         this.args.clear();
         this._visitables.remove("args");
      }

      if (args != null) {
         for(String item : args) {
            this.addToArgs(item);
         }
      }

      return this;
   }

   public boolean hasArgs() {
      return this.args != null && !this.args.isEmpty();
   }

   public String getRestartPolicy() {
      return this.restartPolicy;
   }

   public RunConfigFluent withRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
      return this;
   }

   public boolean hasRestartPolicy() {
      return this.restartPolicy != null;
   }

   public String getServiceAccount() {
      return this.serviceAccount;
   }

   public RunConfigFluent withServiceAccount(String serviceAccount) {
      this.serviceAccount = serviceAccount;
      return this;
   }

   public boolean hasServiceAccount() {
      return this.serviceAccount != null;
   }

   public RunConfigFluent addToLabels(String key, String value) {
      if (this.labels == null && key != null && value != null) {
         this.labels = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.labels.put(key, value);
      }

      return this;
   }

   public RunConfigFluent addToLabels(Map map) {
      if (this.labels == null && map != null) {
         this.labels = new LinkedHashMap();
      }

      if (map != null) {
         this.labels.putAll(map);
      }

      return this;
   }

   public RunConfigFluent removeFromLabels(String key) {
      if (this.labels == null) {
         return this;
      } else {
         if (key != null && this.labels != null) {
            this.labels.remove(key);
         }

         return this;
      }
   }

   public RunConfigFluent removeFromLabels(Map map) {
      if (this.labels == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.labels != null) {
                  this.labels.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getLabels() {
      return this.labels;
   }

   public RunConfigFluent withLabels(Map labels) {
      if (labels == null) {
         this.labels = null;
      } else {
         this.labels = new LinkedHashMap(labels);
      }

      return this;
   }

   public boolean hasLabels() {
      return this.labels != null;
   }

   public RunConfigFluent addToEnv(String key, String value) {
      if (this.env == null && key != null && value != null) {
         this.env = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.env.put(key, value);
      }

      return this;
   }

   public RunConfigFluent addToEnv(Map map) {
      if (this.env == null && map != null) {
         this.env = new LinkedHashMap();
      }

      if (map != null) {
         this.env.putAll(map);
      }

      return this;
   }

   public RunConfigFluent removeFromEnv(String key) {
      if (this.env == null) {
         return this;
      } else {
         if (key != null && this.env != null) {
            this.env.remove(key);
         }

         return this;
      }
   }

   public RunConfigFluent removeFromEnv(Map map) {
      if (this.env == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.env != null) {
                  this.env.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getEnv() {
      return this.env;
   }

   public RunConfigFluent withEnv(Map env) {
      if (env == null) {
         this.env = null;
      } else {
         this.env = new LinkedHashMap(env);
      }

      return this;
   }

   public boolean hasEnv() {
      return this.env != null;
   }

   public RunConfigFluent addToLimits(String key, Quantity value) {
      if (this.limits == null && key != null && value != null) {
         this.limits = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.limits.put(key, value);
      }

      return this;
   }

   public RunConfigFluent addToLimits(Map map) {
      if (this.limits == null && map != null) {
         this.limits = new LinkedHashMap();
      }

      if (map != null) {
         this.limits.putAll(map);
      }

      return this;
   }

   public RunConfigFluent removeFromLimits(String key) {
      if (this.limits == null) {
         return this;
      } else {
         if (key != null && this.limits != null) {
            this.limits.remove(key);
         }

         return this;
      }
   }

   public RunConfigFluent removeFromLimits(Map map) {
      if (this.limits == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.limits != null) {
                  this.limits.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getLimits() {
      return this.limits;
   }

   public RunConfigFluent withLimits(Map limits) {
      if (limits == null) {
         this.limits = null;
      } else {
         this.limits = new LinkedHashMap(limits);
      }

      return this;
   }

   public boolean hasLimits() {
      return this.limits != null;
   }

   public RunConfigFluent addToRequests(String key, Quantity value) {
      if (this.requests == null && key != null && value != null) {
         this.requests = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.requests.put(key, value);
      }

      return this;
   }

   public RunConfigFluent addToRequests(Map map) {
      if (this.requests == null && map != null) {
         this.requests = new LinkedHashMap();
      }

      if (map != null) {
         this.requests.putAll(map);
      }

      return this;
   }

   public RunConfigFluent removeFromRequests(String key) {
      if (this.requests == null) {
         return this;
      } else {
         if (key != null && this.requests != null) {
            this.requests.remove(key);
         }

         return this;
      }
   }

   public RunConfigFluent removeFromRequests(Map map) {
      if (this.requests == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.requests != null) {
                  this.requests.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getRequests() {
      return this.requests;
   }

   public RunConfigFluent withRequests(Map requests) {
      if (requests == null) {
         this.requests = null;
      } else {
         this.requests = new LinkedHashMap(requests);
      }

      return this;
   }

   public boolean hasRequests() {
      return this.requests != null;
   }

   public int getPort() {
      return this.port;
   }

   public RunConfigFluent withPort(int port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return true;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            RunConfigFluent that = (RunConfigFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.image, that.image)) {
               return false;
            } else if (!Objects.equals(this.imagePullPolicy, that.imagePullPolicy)) {
               return false;
            } else if (!Objects.equals(this.command, that.command)) {
               return false;
            } else if (!Objects.equals(this.args, that.args)) {
               return false;
            } else if (!Objects.equals(this.restartPolicy, that.restartPolicy)) {
               return false;
            } else if (!Objects.equals(this.serviceAccount, that.serviceAccount)) {
               return false;
            } else if (!Objects.equals(this.labels, that.labels)) {
               return false;
            } else if (!Objects.equals(this.env, that.env)) {
               return false;
            } else if (!Objects.equals(this.limits, that.limits)) {
               return false;
            } else if (!Objects.equals(this.requests, that.requests)) {
               return false;
            } else {
               return this.port == that.port;
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.name, this.image, this.imagePullPolicy, this.command, this.args, this.restartPolicy, this.serviceAccount, this.labels, this.env, this.limits, this.requests, this.port, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.image != null) {
         sb.append("image:");
         sb.append(this.image + ",");
      }

      if (this.imagePullPolicy != null) {
         sb.append("imagePullPolicy:");
         sb.append(this.imagePullPolicy + ",");
      }

      if (this.command != null) {
         sb.append("command:");
         sb.append(this.command + ",");
      }

      if (this.args != null && !this.args.isEmpty()) {
         sb.append("args:");
         sb.append(this.args + ",");
      }

      if (this.restartPolicy != null) {
         sb.append("restartPolicy:");
         sb.append(this.restartPolicy + ",");
      }

      if (this.serviceAccount != null) {
         sb.append("serviceAccount:");
         sb.append(this.serviceAccount + ",");
      }

      if (this.labels != null && !this.labels.isEmpty()) {
         sb.append("labels:");
         sb.append(this.labels + ",");
      }

      if (this.env != null && !this.env.isEmpty()) {
         sb.append("env:");
         sb.append(this.env + ",");
      }

      if (this.limits != null && !this.limits.isEmpty()) {
         sb.append("limits:");
         sb.append(this.limits + ",");
      }

      if (this.requests != null && !this.requests.isEmpty()) {
         sb.append("requests:");
         sb.append(this.requests + ",");
      }

      sb.append("port:");
      sb.append(this.port);
      sb.append("}");
      return sb.toString();
   }
}
