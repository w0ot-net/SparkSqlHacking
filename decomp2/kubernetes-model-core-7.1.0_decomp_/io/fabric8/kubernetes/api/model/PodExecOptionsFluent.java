package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class PodExecOptionsFluent extends BaseFluent {
   private String apiVersion;
   private List command = new ArrayList();
   private String container;
   private String kind;
   private Boolean stderr;
   private Boolean stdin;
   private Boolean stdout;
   private Boolean tty;
   private Map additionalProperties;

   public PodExecOptionsFluent() {
   }

   public PodExecOptionsFluent(PodExecOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodExecOptions instance) {
      instance = instance != null ? instance : new PodExecOptions();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withCommand(instance.getCommand());
         this.withContainer(instance.getContainer());
         this.withKind(instance.getKind());
         this.withStderr(instance.getStderr());
         this.withStdin(instance.getStdin());
         this.withStdout(instance.getStdout());
         this.withTty(instance.getTty());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public PodExecOptionsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public PodExecOptionsFluent addToCommand(int index, String item) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      this.command.add(index, item);
      return this;
   }

   public PodExecOptionsFluent setToCommand(int index, String item) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      this.command.set(index, item);
      return this;
   }

   public PodExecOptionsFluent addToCommand(String... items) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      for(String item : items) {
         this.command.add(item);
      }

      return this;
   }

   public PodExecOptionsFluent addAllToCommand(Collection items) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      for(String item : items) {
         this.command.add(item);
      }

      return this;
   }

   public PodExecOptionsFluent removeFromCommand(String... items) {
      if (this.command == null) {
         return this;
      } else {
         for(String item : items) {
            this.command.remove(item);
         }

         return this;
      }
   }

   public PodExecOptionsFluent removeAllFromCommand(Collection items) {
      if (this.command == null) {
         return this;
      } else {
         for(String item : items) {
            this.command.remove(item);
         }

         return this;
      }
   }

   public List getCommand() {
      return this.command;
   }

   public String getCommand(int index) {
      return (String)this.command.get(index);
   }

   public String getFirstCommand() {
      return (String)this.command.get(0);
   }

   public String getLastCommand() {
      return (String)this.command.get(this.command.size() - 1);
   }

   public String getMatchingCommand(Predicate predicate) {
      for(String item : this.command) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCommand(Predicate predicate) {
      for(String item : this.command) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodExecOptionsFluent withCommand(List command) {
      if (command != null) {
         this.command = new ArrayList();

         for(String item : command) {
            this.addToCommand(item);
         }
      } else {
         this.command = null;
      }

      return this;
   }

   public PodExecOptionsFluent withCommand(String... command) {
      if (this.command != null) {
         this.command.clear();
         this._visitables.remove("command");
      }

      if (command != null) {
         for(String item : command) {
            this.addToCommand(item);
         }
      }

      return this;
   }

   public boolean hasCommand() {
      return this.command != null && !this.command.isEmpty();
   }

   public String getContainer() {
      return this.container;
   }

   public PodExecOptionsFluent withContainer(String container) {
      this.container = container;
      return this;
   }

   public boolean hasContainer() {
      return this.container != null;
   }

   public String getKind() {
      return this.kind;
   }

   public PodExecOptionsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public Boolean getStderr() {
      return this.stderr;
   }

   public PodExecOptionsFluent withStderr(Boolean stderr) {
      this.stderr = stderr;
      return this;
   }

   public boolean hasStderr() {
      return this.stderr != null;
   }

   public Boolean getStdin() {
      return this.stdin;
   }

   public PodExecOptionsFluent withStdin(Boolean stdin) {
      this.stdin = stdin;
      return this;
   }

   public boolean hasStdin() {
      return this.stdin != null;
   }

   public Boolean getStdout() {
      return this.stdout;
   }

   public PodExecOptionsFluent withStdout(Boolean stdout) {
      this.stdout = stdout;
      return this;
   }

   public boolean hasStdout() {
      return this.stdout != null;
   }

   public Boolean getTty() {
      return this.tty;
   }

   public PodExecOptionsFluent withTty(Boolean tty) {
      this.tty = tty;
      return this;
   }

   public boolean hasTty() {
      return this.tty != null;
   }

   public PodExecOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodExecOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodExecOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodExecOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public PodExecOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            PodExecOptionsFluent that = (PodExecOptionsFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.command, that.command)) {
               return false;
            } else if (!Objects.equals(this.container, that.container)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.stderr, that.stderr)) {
               return false;
            } else if (!Objects.equals(this.stdin, that.stdin)) {
               return false;
            } else if (!Objects.equals(this.stdout, that.stdout)) {
               return false;
            } else if (!Objects.equals(this.tty, that.tty)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.command, this.container, this.kind, this.stderr, this.stdin, this.stdout, this.tty, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.command != null && !this.command.isEmpty()) {
         sb.append("command:");
         sb.append(this.command + ",");
      }

      if (this.container != null) {
         sb.append("container:");
         sb.append(this.container + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.stderr != null) {
         sb.append("stderr:");
         sb.append(this.stderr + ",");
      }

      if (this.stdin != null) {
         sb.append("stdin:");
         sb.append(this.stdin + ",");
      }

      if (this.stdout != null) {
         sb.append("stdout:");
         sb.append(this.stdout + ",");
      }

      if (this.tty != null) {
         sb.append("tty:");
         sb.append(this.tty + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public PodExecOptionsFluent withStderr() {
      return this.withStderr(true);
   }

   public PodExecOptionsFluent withStdin() {
      return this.withStdin(true);
   }

   public PodExecOptionsFluent withStdout() {
      return this.withStdout(true);
   }

   public PodExecOptionsFluent withTty() {
      return this.withTty(true);
   }
}
