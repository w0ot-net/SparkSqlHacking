package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ExecConfigFluent extends BaseFluent {
   private String apiVersion;
   private List args = new ArrayList();
   private String command;
   private ArrayList env = new ArrayList();
   private String installHint;
   private String interactiveMode;
   private Boolean provideClusterInfo;
   private Map additionalProperties;

   public ExecConfigFluent() {
   }

   public ExecConfigFluent(ExecConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExecConfig instance) {
      instance = instance != null ? instance : new ExecConfig();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withArgs(instance.getArgs());
         this.withCommand(instance.getCommand());
         this.withEnv(instance.getEnv());
         this.withInstallHint(instance.getInstallHint());
         this.withInteractiveMode(instance.getInteractiveMode());
         this.withProvideClusterInfo(instance.getProvideClusterInfo());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ExecConfigFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public ExecConfigFluent addToArgs(int index, String item) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      this.args.add(index, item);
      return this;
   }

   public ExecConfigFluent setToArgs(int index, String item) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      this.args.set(index, item);
      return this;
   }

   public ExecConfigFluent addToArgs(String... items) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      for(String item : items) {
         this.args.add(item);
      }

      return this;
   }

   public ExecConfigFluent addAllToArgs(Collection items) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      for(String item : items) {
         this.args.add(item);
      }

      return this;
   }

   public ExecConfigFluent removeFromArgs(String... items) {
      if (this.args == null) {
         return this;
      } else {
         for(String item : items) {
            this.args.remove(item);
         }

         return this;
      }
   }

   public ExecConfigFluent removeAllFromArgs(Collection items) {
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

   public ExecConfigFluent withArgs(List args) {
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

   public ExecConfigFluent withArgs(String... args) {
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

   public String getCommand() {
      return this.command;
   }

   public ExecConfigFluent withCommand(String command) {
      this.command = command;
      return this;
   }

   public boolean hasCommand() {
      return this.command != null;
   }

   public ExecConfigFluent addToEnv(int index, ExecEnvVar item) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      ExecEnvVarBuilder builder = new ExecEnvVarBuilder(item);
      if (index >= 0 && index < this.env.size()) {
         this._visitables.get("env").add(index, builder);
         this.env.add(index, builder);
      } else {
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public ExecConfigFluent setToEnv(int index, ExecEnvVar item) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      ExecEnvVarBuilder builder = new ExecEnvVarBuilder(item);
      if (index >= 0 && index < this.env.size()) {
         this._visitables.get("env").set(index, builder);
         this.env.set(index, builder);
      } else {
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public ExecConfigFluent addToEnv(ExecEnvVar... items) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      for(ExecEnvVar item : items) {
         ExecEnvVarBuilder builder = new ExecEnvVarBuilder(item);
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public ExecConfigFluent addAllToEnv(Collection items) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      for(ExecEnvVar item : items) {
         ExecEnvVarBuilder builder = new ExecEnvVarBuilder(item);
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public ExecConfigFluent removeFromEnv(ExecEnvVar... items) {
      if (this.env == null) {
         return this;
      } else {
         for(ExecEnvVar item : items) {
            ExecEnvVarBuilder builder = new ExecEnvVarBuilder(item);
            this._visitables.get("env").remove(builder);
            this.env.remove(builder);
         }

         return this;
      }
   }

   public ExecConfigFluent removeAllFromEnv(Collection items) {
      if (this.env == null) {
         return this;
      } else {
         for(ExecEnvVar item : items) {
            ExecEnvVarBuilder builder = new ExecEnvVarBuilder(item);
            this._visitables.get("env").remove(builder);
            this.env.remove(builder);
         }

         return this;
      }
   }

   public ExecConfigFluent removeMatchingFromEnv(Predicate predicate) {
      if (this.env == null) {
         return this;
      } else {
         Iterator<ExecEnvVarBuilder> each = this.env.iterator();
         List visitables = this._visitables.get("env");

         while(each.hasNext()) {
            ExecEnvVarBuilder builder = (ExecEnvVarBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildEnv() {
      return this.env != null ? build(this.env) : null;
   }

   public ExecEnvVar buildEnv(int index) {
      return ((ExecEnvVarBuilder)this.env.get(index)).build();
   }

   public ExecEnvVar buildFirstEnv() {
      return ((ExecEnvVarBuilder)this.env.get(0)).build();
   }

   public ExecEnvVar buildLastEnv() {
      return ((ExecEnvVarBuilder)this.env.get(this.env.size() - 1)).build();
   }

   public ExecEnvVar buildMatchingEnv(Predicate predicate) {
      for(ExecEnvVarBuilder item : this.env) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingEnv(Predicate predicate) {
      for(ExecEnvVarBuilder item : this.env) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ExecConfigFluent withEnv(List env) {
      if (this.env != null) {
         this._visitables.get("env").clear();
      }

      if (env != null) {
         this.env = new ArrayList();

         for(ExecEnvVar item : env) {
            this.addToEnv(item);
         }
      } else {
         this.env = null;
      }

      return this;
   }

   public ExecConfigFluent withEnv(ExecEnvVar... env) {
      if (this.env != null) {
         this.env.clear();
         this._visitables.remove("env");
      }

      if (env != null) {
         for(ExecEnvVar item : env) {
            this.addToEnv(item);
         }
      }

      return this;
   }

   public boolean hasEnv() {
      return this.env != null && !this.env.isEmpty();
   }

   public ExecConfigFluent addNewEnv(String name, String value) {
      return this.addToEnv(new ExecEnvVar(name, value));
   }

   public EnvNested addNewEnv() {
      return new EnvNested(-1, (ExecEnvVar)null);
   }

   public EnvNested addNewEnvLike(ExecEnvVar item) {
      return new EnvNested(-1, item);
   }

   public EnvNested setNewEnvLike(int index, ExecEnvVar item) {
      return new EnvNested(index, item);
   }

   public EnvNested editEnv(int index) {
      if (this.env.size() <= index) {
         throw new RuntimeException("Can't edit env. Index exceeds size.");
      } else {
         return this.setNewEnvLike(index, this.buildEnv(index));
      }
   }

   public EnvNested editFirstEnv() {
      if (this.env.size() == 0) {
         throw new RuntimeException("Can't edit first env. The list is empty.");
      } else {
         return this.setNewEnvLike(0, this.buildEnv(0));
      }
   }

   public EnvNested editLastEnv() {
      int index = this.env.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last env. The list is empty.");
      } else {
         return this.setNewEnvLike(index, this.buildEnv(index));
      }
   }

   public EnvNested editMatchingEnv(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.env.size(); ++i) {
         if (predicate.test((ExecEnvVarBuilder)this.env.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching env. No match found.");
      } else {
         return this.setNewEnvLike(index, this.buildEnv(index));
      }
   }

   public String getInstallHint() {
      return this.installHint;
   }

   public ExecConfigFluent withInstallHint(String installHint) {
      this.installHint = installHint;
      return this;
   }

   public boolean hasInstallHint() {
      return this.installHint != null;
   }

   public String getInteractiveMode() {
      return this.interactiveMode;
   }

   public ExecConfigFluent withInteractiveMode(String interactiveMode) {
      this.interactiveMode = interactiveMode;
      return this;
   }

   public boolean hasInteractiveMode() {
      return this.interactiveMode != null;
   }

   public Boolean getProvideClusterInfo() {
      return this.provideClusterInfo;
   }

   public ExecConfigFluent withProvideClusterInfo(Boolean provideClusterInfo) {
      this.provideClusterInfo = provideClusterInfo;
      return this;
   }

   public boolean hasProvideClusterInfo() {
      return this.provideClusterInfo != null;
   }

   public ExecConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExecConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExecConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExecConfigFluent removeFromAdditionalProperties(Map map) {
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

   public ExecConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            ExecConfigFluent that = (ExecConfigFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.args, that.args)) {
               return false;
            } else if (!Objects.equals(this.command, that.command)) {
               return false;
            } else if (!Objects.equals(this.env, that.env)) {
               return false;
            } else if (!Objects.equals(this.installHint, that.installHint)) {
               return false;
            } else if (!Objects.equals(this.interactiveMode, that.interactiveMode)) {
               return false;
            } else if (!Objects.equals(this.provideClusterInfo, that.provideClusterInfo)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.args, this.command, this.env, this.installHint, this.interactiveMode, this.provideClusterInfo, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.args != null && !this.args.isEmpty()) {
         sb.append("args:");
         sb.append(this.args + ",");
      }

      if (this.command != null) {
         sb.append("command:");
         sb.append(this.command + ",");
      }

      if (this.env != null && !this.env.isEmpty()) {
         sb.append("env:");
         sb.append(this.env + ",");
      }

      if (this.installHint != null) {
         sb.append("installHint:");
         sb.append(this.installHint + ",");
      }

      if (this.interactiveMode != null) {
         sb.append("interactiveMode:");
         sb.append(this.interactiveMode + ",");
      }

      if (this.provideClusterInfo != null) {
         sb.append("provideClusterInfo:");
         sb.append(this.provideClusterInfo + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ExecConfigFluent withProvideClusterInfo() {
      return this.withProvideClusterInfo(true);
   }

   public class EnvNested extends ExecEnvVarFluent implements Nested {
      ExecEnvVarBuilder builder;
      int index;

      EnvNested(int index, ExecEnvVar item) {
         this.index = index;
         this.builder = new ExecEnvVarBuilder(this, item);
      }

      public Object and() {
         return ExecConfigFluent.this.setToEnv(this.index, this.builder.build());
      }

      public Object endEnv() {
         return this.and();
      }
   }
}
