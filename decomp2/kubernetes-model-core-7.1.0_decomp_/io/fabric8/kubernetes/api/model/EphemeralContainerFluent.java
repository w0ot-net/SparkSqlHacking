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
import java.util.Optional;
import java.util.function.Predicate;

public class EphemeralContainerFluent extends BaseFluent {
   private List args = new ArrayList();
   private List command = new ArrayList();
   private ArrayList env = new ArrayList();
   private ArrayList envFrom = new ArrayList();
   private String image;
   private String imagePullPolicy;
   private LifecycleBuilder lifecycle;
   private ProbeBuilder livenessProbe;
   private String name;
   private ArrayList ports = new ArrayList();
   private ProbeBuilder readinessProbe;
   private ArrayList resizePolicy = new ArrayList();
   private ResourceRequirementsBuilder resources;
   private String restartPolicy;
   private SecurityContextBuilder securityContext;
   private ProbeBuilder startupProbe;
   private Boolean stdin;
   private Boolean stdinOnce;
   private String targetContainerName;
   private String terminationMessagePath;
   private String terminationMessagePolicy;
   private Boolean tty;
   private ArrayList volumeDevices = new ArrayList();
   private ArrayList volumeMounts = new ArrayList();
   private String workingDir;
   private Map additionalProperties;

   public EphemeralContainerFluent() {
   }

   public EphemeralContainerFluent(EphemeralContainer instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EphemeralContainer instance) {
      instance = instance != null ? instance : new EphemeralContainer();
      if (instance != null) {
         this.withArgs(instance.getArgs());
         this.withCommand(instance.getCommand());
         this.withEnv(instance.getEnv());
         this.withEnvFrom(instance.getEnvFrom());
         this.withImage(instance.getImage());
         this.withImagePullPolicy(instance.getImagePullPolicy());
         this.withLifecycle(instance.getLifecycle());
         this.withLivenessProbe(instance.getLivenessProbe());
         this.withName(instance.getName());
         this.withPorts(instance.getPorts());
         this.withReadinessProbe(instance.getReadinessProbe());
         this.withResizePolicy(instance.getResizePolicy());
         this.withResources(instance.getResources());
         this.withRestartPolicy(instance.getRestartPolicy());
         this.withSecurityContext(instance.getSecurityContext());
         this.withStartupProbe(instance.getStartupProbe());
         this.withStdin(instance.getStdin());
         this.withStdinOnce(instance.getStdinOnce());
         this.withTargetContainerName(instance.getTargetContainerName());
         this.withTerminationMessagePath(instance.getTerminationMessagePath());
         this.withTerminationMessagePolicy(instance.getTerminationMessagePolicy());
         this.withTty(instance.getTty());
         this.withVolumeDevices(instance.getVolumeDevices());
         this.withVolumeMounts(instance.getVolumeMounts());
         this.withWorkingDir(instance.getWorkingDir());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public EphemeralContainerFluent addToArgs(int index, String item) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      this.args.add(index, item);
      return this;
   }

   public EphemeralContainerFluent setToArgs(int index, String item) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      this.args.set(index, item);
      return this;
   }

   public EphemeralContainerFluent addToArgs(String... items) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      for(String item : items) {
         this.args.add(item);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToArgs(Collection items) {
      if (this.args == null) {
         this.args = new ArrayList();
      }

      for(String item : items) {
         this.args.add(item);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromArgs(String... items) {
      if (this.args == null) {
         return this;
      } else {
         for(String item : items) {
            this.args.remove(item);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromArgs(Collection items) {
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

   public EphemeralContainerFluent withArgs(List args) {
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

   public EphemeralContainerFluent withArgs(String... args) {
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

   public EphemeralContainerFluent addToCommand(int index, String item) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      this.command.add(index, item);
      return this;
   }

   public EphemeralContainerFluent setToCommand(int index, String item) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      this.command.set(index, item);
      return this;
   }

   public EphemeralContainerFluent addToCommand(String... items) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      for(String item : items) {
         this.command.add(item);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToCommand(Collection items) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      for(String item : items) {
         this.command.add(item);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromCommand(String... items) {
      if (this.command == null) {
         return this;
      } else {
         for(String item : items) {
            this.command.remove(item);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromCommand(Collection items) {
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

   public EphemeralContainerFluent withCommand(List command) {
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

   public EphemeralContainerFluent withCommand(String... command) {
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

   public EphemeralContainerFluent addToEnv(int index, EnvVar item) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      EnvVarBuilder builder = new EnvVarBuilder(item);
      if (index >= 0 && index < this.env.size()) {
         this._visitables.get("env").add(index, builder);
         this.env.add(index, builder);
      } else {
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent setToEnv(int index, EnvVar item) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      EnvVarBuilder builder = new EnvVarBuilder(item);
      if (index >= 0 && index < this.env.size()) {
         this._visitables.get("env").set(index, builder);
         this.env.set(index, builder);
      } else {
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addToEnv(EnvVar... items) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      for(EnvVar item : items) {
         EnvVarBuilder builder = new EnvVarBuilder(item);
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToEnv(Collection items) {
      if (this.env == null) {
         this.env = new ArrayList();
      }

      for(EnvVar item : items) {
         EnvVarBuilder builder = new EnvVarBuilder(item);
         this._visitables.get("env").add(builder);
         this.env.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromEnv(EnvVar... items) {
      if (this.env == null) {
         return this;
      } else {
         for(EnvVar item : items) {
            EnvVarBuilder builder = new EnvVarBuilder(item);
            this._visitables.get("env").remove(builder);
            this.env.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromEnv(Collection items) {
      if (this.env == null) {
         return this;
      } else {
         for(EnvVar item : items) {
            EnvVarBuilder builder = new EnvVarBuilder(item);
            this._visitables.get("env").remove(builder);
            this.env.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeMatchingFromEnv(Predicate predicate) {
      if (this.env == null) {
         return this;
      } else {
         Iterator<EnvVarBuilder> each = this.env.iterator();
         List visitables = this._visitables.get("env");

         while(each.hasNext()) {
            EnvVarBuilder builder = (EnvVarBuilder)each.next();
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

   public EnvVar buildEnv(int index) {
      return ((EnvVarBuilder)this.env.get(index)).build();
   }

   public EnvVar buildFirstEnv() {
      return ((EnvVarBuilder)this.env.get(0)).build();
   }

   public EnvVar buildLastEnv() {
      return ((EnvVarBuilder)this.env.get(this.env.size() - 1)).build();
   }

   public EnvVar buildMatchingEnv(Predicate predicate) {
      for(EnvVarBuilder item : this.env) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingEnv(Predicate predicate) {
      for(EnvVarBuilder item : this.env) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EphemeralContainerFluent withEnv(List env) {
      if (this.env != null) {
         this._visitables.get("env").clear();
      }

      if (env != null) {
         this.env = new ArrayList();

         for(EnvVar item : env) {
            this.addToEnv(item);
         }
      } else {
         this.env = null;
      }

      return this;
   }

   public EphemeralContainerFluent withEnv(EnvVar... env) {
      if (this.env != null) {
         this.env.clear();
         this._visitables.remove("env");
      }

      if (env != null) {
         for(EnvVar item : env) {
            this.addToEnv(item);
         }
      }

      return this;
   }

   public boolean hasEnv() {
      return this.env != null && !this.env.isEmpty();
   }

   public EnvNested addNewEnv() {
      return new EnvNested(-1, (EnvVar)null);
   }

   public EnvNested addNewEnvLike(EnvVar item) {
      return new EnvNested(-1, item);
   }

   public EnvNested setNewEnvLike(int index, EnvVar item) {
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
         if (predicate.test((EnvVarBuilder)this.env.get(i))) {
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

   public EphemeralContainerFluent addToEnvFrom(int index, EnvFromSource item) {
      if (this.envFrom == null) {
         this.envFrom = new ArrayList();
      }

      EnvFromSourceBuilder builder = new EnvFromSourceBuilder(item);
      if (index >= 0 && index < this.envFrom.size()) {
         this._visitables.get("envFrom").add(index, builder);
         this.envFrom.add(index, builder);
      } else {
         this._visitables.get("envFrom").add(builder);
         this.envFrom.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent setToEnvFrom(int index, EnvFromSource item) {
      if (this.envFrom == null) {
         this.envFrom = new ArrayList();
      }

      EnvFromSourceBuilder builder = new EnvFromSourceBuilder(item);
      if (index >= 0 && index < this.envFrom.size()) {
         this._visitables.get("envFrom").set(index, builder);
         this.envFrom.set(index, builder);
      } else {
         this._visitables.get("envFrom").add(builder);
         this.envFrom.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addToEnvFrom(EnvFromSource... items) {
      if (this.envFrom == null) {
         this.envFrom = new ArrayList();
      }

      for(EnvFromSource item : items) {
         EnvFromSourceBuilder builder = new EnvFromSourceBuilder(item);
         this._visitables.get("envFrom").add(builder);
         this.envFrom.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToEnvFrom(Collection items) {
      if (this.envFrom == null) {
         this.envFrom = new ArrayList();
      }

      for(EnvFromSource item : items) {
         EnvFromSourceBuilder builder = new EnvFromSourceBuilder(item);
         this._visitables.get("envFrom").add(builder);
         this.envFrom.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromEnvFrom(EnvFromSource... items) {
      if (this.envFrom == null) {
         return this;
      } else {
         for(EnvFromSource item : items) {
            EnvFromSourceBuilder builder = new EnvFromSourceBuilder(item);
            this._visitables.get("envFrom").remove(builder);
            this.envFrom.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromEnvFrom(Collection items) {
      if (this.envFrom == null) {
         return this;
      } else {
         for(EnvFromSource item : items) {
            EnvFromSourceBuilder builder = new EnvFromSourceBuilder(item);
            this._visitables.get("envFrom").remove(builder);
            this.envFrom.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeMatchingFromEnvFrom(Predicate predicate) {
      if (this.envFrom == null) {
         return this;
      } else {
         Iterator<EnvFromSourceBuilder> each = this.envFrom.iterator();
         List visitables = this._visitables.get("envFrom");

         while(each.hasNext()) {
            EnvFromSourceBuilder builder = (EnvFromSourceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildEnvFrom() {
      return this.envFrom != null ? build(this.envFrom) : null;
   }

   public EnvFromSource buildEnvFrom(int index) {
      return ((EnvFromSourceBuilder)this.envFrom.get(index)).build();
   }

   public EnvFromSource buildFirstEnvFrom() {
      return ((EnvFromSourceBuilder)this.envFrom.get(0)).build();
   }

   public EnvFromSource buildLastEnvFrom() {
      return ((EnvFromSourceBuilder)this.envFrom.get(this.envFrom.size() - 1)).build();
   }

   public EnvFromSource buildMatchingEnvFrom(Predicate predicate) {
      for(EnvFromSourceBuilder item : this.envFrom) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingEnvFrom(Predicate predicate) {
      for(EnvFromSourceBuilder item : this.envFrom) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EphemeralContainerFluent withEnvFrom(List envFrom) {
      if (this.envFrom != null) {
         this._visitables.get("envFrom").clear();
      }

      if (envFrom != null) {
         this.envFrom = new ArrayList();

         for(EnvFromSource item : envFrom) {
            this.addToEnvFrom(item);
         }
      } else {
         this.envFrom = null;
      }

      return this;
   }

   public EphemeralContainerFluent withEnvFrom(EnvFromSource... envFrom) {
      if (this.envFrom != null) {
         this.envFrom.clear();
         this._visitables.remove("envFrom");
      }

      if (envFrom != null) {
         for(EnvFromSource item : envFrom) {
            this.addToEnvFrom(item);
         }
      }

      return this;
   }

   public boolean hasEnvFrom() {
      return this.envFrom != null && !this.envFrom.isEmpty();
   }

   public EnvFromNested addNewEnvFrom() {
      return new EnvFromNested(-1, (EnvFromSource)null);
   }

   public EnvFromNested addNewEnvFromLike(EnvFromSource item) {
      return new EnvFromNested(-1, item);
   }

   public EnvFromNested setNewEnvFromLike(int index, EnvFromSource item) {
      return new EnvFromNested(index, item);
   }

   public EnvFromNested editEnvFrom(int index) {
      if (this.envFrom.size() <= index) {
         throw new RuntimeException("Can't edit envFrom. Index exceeds size.");
      } else {
         return this.setNewEnvFromLike(index, this.buildEnvFrom(index));
      }
   }

   public EnvFromNested editFirstEnvFrom() {
      if (this.envFrom.size() == 0) {
         throw new RuntimeException("Can't edit first envFrom. The list is empty.");
      } else {
         return this.setNewEnvFromLike(0, this.buildEnvFrom(0));
      }
   }

   public EnvFromNested editLastEnvFrom() {
      int index = this.envFrom.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last envFrom. The list is empty.");
      } else {
         return this.setNewEnvFromLike(index, this.buildEnvFrom(index));
      }
   }

   public EnvFromNested editMatchingEnvFrom(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.envFrom.size(); ++i) {
         if (predicate.test((EnvFromSourceBuilder)this.envFrom.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching envFrom. No match found.");
      } else {
         return this.setNewEnvFromLike(index, this.buildEnvFrom(index));
      }
   }

   public String getImage() {
      return this.image;
   }

   public EphemeralContainerFluent withImage(String image) {
      this.image = image;
      return this;
   }

   public boolean hasImage() {
      return this.image != null;
   }

   public String getImagePullPolicy() {
      return this.imagePullPolicy;
   }

   public EphemeralContainerFluent withImagePullPolicy(String imagePullPolicy) {
      this.imagePullPolicy = imagePullPolicy;
      return this;
   }

   public boolean hasImagePullPolicy() {
      return this.imagePullPolicy != null;
   }

   public Lifecycle buildLifecycle() {
      return this.lifecycle != null ? this.lifecycle.build() : null;
   }

   public EphemeralContainerFluent withLifecycle(Lifecycle lifecycle) {
      this._visitables.remove("lifecycle");
      if (lifecycle != null) {
         this.lifecycle = new LifecycleBuilder(lifecycle);
         this._visitables.get("lifecycle").add(this.lifecycle);
      } else {
         this.lifecycle = null;
         this._visitables.get("lifecycle").remove(this.lifecycle);
      }

      return this;
   }

   public boolean hasLifecycle() {
      return this.lifecycle != null;
   }

   public LifecycleNested withNewLifecycle() {
      return new LifecycleNested((Lifecycle)null);
   }

   public LifecycleNested withNewLifecycleLike(Lifecycle item) {
      return new LifecycleNested(item);
   }

   public LifecycleNested editLifecycle() {
      return this.withNewLifecycleLike((Lifecycle)Optional.ofNullable(this.buildLifecycle()).orElse((Object)null));
   }

   public LifecycleNested editOrNewLifecycle() {
      return this.withNewLifecycleLike((Lifecycle)Optional.ofNullable(this.buildLifecycle()).orElse((new LifecycleBuilder()).build()));
   }

   public LifecycleNested editOrNewLifecycleLike(Lifecycle item) {
      return this.withNewLifecycleLike((Lifecycle)Optional.ofNullable(this.buildLifecycle()).orElse(item));
   }

   public Probe buildLivenessProbe() {
      return this.livenessProbe != null ? this.livenessProbe.build() : null;
   }

   public EphemeralContainerFluent withLivenessProbe(Probe livenessProbe) {
      this._visitables.remove("livenessProbe");
      if (livenessProbe != null) {
         this.livenessProbe = new ProbeBuilder(livenessProbe);
         this._visitables.get("livenessProbe").add(this.livenessProbe);
      } else {
         this.livenessProbe = null;
         this._visitables.get("livenessProbe").remove(this.livenessProbe);
      }

      return this;
   }

   public boolean hasLivenessProbe() {
      return this.livenessProbe != null;
   }

   public LivenessProbeNested withNewLivenessProbe() {
      return new LivenessProbeNested((Probe)null);
   }

   public LivenessProbeNested withNewLivenessProbeLike(Probe item) {
      return new LivenessProbeNested(item);
   }

   public LivenessProbeNested editLivenessProbe() {
      return this.withNewLivenessProbeLike((Probe)Optional.ofNullable(this.buildLivenessProbe()).orElse((Object)null));
   }

   public LivenessProbeNested editOrNewLivenessProbe() {
      return this.withNewLivenessProbeLike((Probe)Optional.ofNullable(this.buildLivenessProbe()).orElse((new ProbeBuilder()).build()));
   }

   public LivenessProbeNested editOrNewLivenessProbeLike(Probe item) {
      return this.withNewLivenessProbeLike((Probe)Optional.ofNullable(this.buildLivenessProbe()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public EphemeralContainerFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public EphemeralContainerFluent addToPorts(int index, ContainerPort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      ContainerPortBuilder builder = new ContainerPortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").add(index, builder);
         this.ports.add(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent setToPorts(int index, ContainerPort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      ContainerPortBuilder builder = new ContainerPortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").set(index, builder);
         this.ports.set(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addToPorts(ContainerPort... items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(ContainerPort item : items) {
         ContainerPortBuilder builder = new ContainerPortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToPorts(Collection items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(ContainerPort item : items) {
         ContainerPortBuilder builder = new ContainerPortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromPorts(ContainerPort... items) {
      if (this.ports == null) {
         return this;
      } else {
         for(ContainerPort item : items) {
            ContainerPortBuilder builder = new ContainerPortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromPorts(Collection items) {
      if (this.ports == null) {
         return this;
      } else {
         for(ContainerPort item : items) {
            ContainerPortBuilder builder = new ContainerPortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeMatchingFromPorts(Predicate predicate) {
      if (this.ports == null) {
         return this;
      } else {
         Iterator<ContainerPortBuilder> each = this.ports.iterator();
         List visitables = this._visitables.get("ports");

         while(each.hasNext()) {
            ContainerPortBuilder builder = (ContainerPortBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildPorts() {
      return this.ports != null ? build(this.ports) : null;
   }

   public ContainerPort buildPort(int index) {
      return ((ContainerPortBuilder)this.ports.get(index)).build();
   }

   public ContainerPort buildFirstPort() {
      return ((ContainerPortBuilder)this.ports.get(0)).build();
   }

   public ContainerPort buildLastPort() {
      return ((ContainerPortBuilder)this.ports.get(this.ports.size() - 1)).build();
   }

   public ContainerPort buildMatchingPort(Predicate predicate) {
      for(ContainerPortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPort(Predicate predicate) {
      for(ContainerPortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EphemeralContainerFluent withPorts(List ports) {
      if (this.ports != null) {
         this._visitables.get("ports").clear();
      }

      if (ports != null) {
         this.ports = new ArrayList();

         for(ContainerPort item : ports) {
            this.addToPorts(item);
         }
      } else {
         this.ports = null;
      }

      return this;
   }

   public EphemeralContainerFluent withPorts(ContainerPort... ports) {
      if (this.ports != null) {
         this.ports.clear();
         this._visitables.remove("ports");
      }

      if (ports != null) {
         for(ContainerPort item : ports) {
            this.addToPorts(item);
         }
      }

      return this;
   }

   public boolean hasPorts() {
      return this.ports != null && !this.ports.isEmpty();
   }

   public EphemeralContainerFluent addNewPort(Integer containerPort, String hostIP, Integer hostPort, String name, String protocol) {
      return this.addToPorts(new ContainerPort(containerPort, hostIP, hostPort, name, protocol));
   }

   public PortsNested addNewPort() {
      return new PortsNested(-1, (ContainerPort)null);
   }

   public PortsNested addNewPortLike(ContainerPort item) {
      return new PortsNested(-1, item);
   }

   public PortsNested setNewPortLike(int index, ContainerPort item) {
      return new PortsNested(index, item);
   }

   public PortsNested editPort(int index) {
      if (this.ports.size() <= index) {
         throw new RuntimeException("Can't edit ports. Index exceeds size.");
      } else {
         return this.setNewPortLike(index, this.buildPort(index));
      }
   }

   public PortsNested editFirstPort() {
      if (this.ports.size() == 0) {
         throw new RuntimeException("Can't edit first ports. The list is empty.");
      } else {
         return this.setNewPortLike(0, this.buildPort(0));
      }
   }

   public PortsNested editLastPort() {
      int index = this.ports.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ports. The list is empty.");
      } else {
         return this.setNewPortLike(index, this.buildPort(index));
      }
   }

   public PortsNested editMatchingPort(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ports.size(); ++i) {
         if (predicate.test((ContainerPortBuilder)this.ports.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ports. No match found.");
      } else {
         return this.setNewPortLike(index, this.buildPort(index));
      }
   }

   public Probe buildReadinessProbe() {
      return this.readinessProbe != null ? this.readinessProbe.build() : null;
   }

   public EphemeralContainerFluent withReadinessProbe(Probe readinessProbe) {
      this._visitables.remove("readinessProbe");
      if (readinessProbe != null) {
         this.readinessProbe = new ProbeBuilder(readinessProbe);
         this._visitables.get("readinessProbe").add(this.readinessProbe);
      } else {
         this.readinessProbe = null;
         this._visitables.get("readinessProbe").remove(this.readinessProbe);
      }

      return this;
   }

   public boolean hasReadinessProbe() {
      return this.readinessProbe != null;
   }

   public ReadinessProbeNested withNewReadinessProbe() {
      return new ReadinessProbeNested((Probe)null);
   }

   public ReadinessProbeNested withNewReadinessProbeLike(Probe item) {
      return new ReadinessProbeNested(item);
   }

   public ReadinessProbeNested editReadinessProbe() {
      return this.withNewReadinessProbeLike((Probe)Optional.ofNullable(this.buildReadinessProbe()).orElse((Object)null));
   }

   public ReadinessProbeNested editOrNewReadinessProbe() {
      return this.withNewReadinessProbeLike((Probe)Optional.ofNullable(this.buildReadinessProbe()).orElse((new ProbeBuilder()).build()));
   }

   public ReadinessProbeNested editOrNewReadinessProbeLike(Probe item) {
      return this.withNewReadinessProbeLike((Probe)Optional.ofNullable(this.buildReadinessProbe()).orElse(item));
   }

   public EphemeralContainerFluent addToResizePolicy(int index, ContainerResizePolicy item) {
      if (this.resizePolicy == null) {
         this.resizePolicy = new ArrayList();
      }

      ContainerResizePolicyBuilder builder = new ContainerResizePolicyBuilder(item);
      if (index >= 0 && index < this.resizePolicy.size()) {
         this._visitables.get("resizePolicy").add(index, builder);
         this.resizePolicy.add(index, builder);
      } else {
         this._visitables.get("resizePolicy").add(builder);
         this.resizePolicy.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent setToResizePolicy(int index, ContainerResizePolicy item) {
      if (this.resizePolicy == null) {
         this.resizePolicy = new ArrayList();
      }

      ContainerResizePolicyBuilder builder = new ContainerResizePolicyBuilder(item);
      if (index >= 0 && index < this.resizePolicy.size()) {
         this._visitables.get("resizePolicy").set(index, builder);
         this.resizePolicy.set(index, builder);
      } else {
         this._visitables.get("resizePolicy").add(builder);
         this.resizePolicy.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addToResizePolicy(ContainerResizePolicy... items) {
      if (this.resizePolicy == null) {
         this.resizePolicy = new ArrayList();
      }

      for(ContainerResizePolicy item : items) {
         ContainerResizePolicyBuilder builder = new ContainerResizePolicyBuilder(item);
         this._visitables.get("resizePolicy").add(builder);
         this.resizePolicy.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToResizePolicy(Collection items) {
      if (this.resizePolicy == null) {
         this.resizePolicy = new ArrayList();
      }

      for(ContainerResizePolicy item : items) {
         ContainerResizePolicyBuilder builder = new ContainerResizePolicyBuilder(item);
         this._visitables.get("resizePolicy").add(builder);
         this.resizePolicy.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromResizePolicy(ContainerResizePolicy... items) {
      if (this.resizePolicy == null) {
         return this;
      } else {
         for(ContainerResizePolicy item : items) {
            ContainerResizePolicyBuilder builder = new ContainerResizePolicyBuilder(item);
            this._visitables.get("resizePolicy").remove(builder);
            this.resizePolicy.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromResizePolicy(Collection items) {
      if (this.resizePolicy == null) {
         return this;
      } else {
         for(ContainerResizePolicy item : items) {
            ContainerResizePolicyBuilder builder = new ContainerResizePolicyBuilder(item);
            this._visitables.get("resizePolicy").remove(builder);
            this.resizePolicy.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeMatchingFromResizePolicy(Predicate predicate) {
      if (this.resizePolicy == null) {
         return this;
      } else {
         Iterator<ContainerResizePolicyBuilder> each = this.resizePolicy.iterator();
         List visitables = this._visitables.get("resizePolicy");

         while(each.hasNext()) {
            ContainerResizePolicyBuilder builder = (ContainerResizePolicyBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResizePolicy() {
      return this.resizePolicy != null ? build(this.resizePolicy) : null;
   }

   public ContainerResizePolicy buildResizePolicy(int index) {
      return ((ContainerResizePolicyBuilder)this.resizePolicy.get(index)).build();
   }

   public ContainerResizePolicy buildFirstResizePolicy() {
      return ((ContainerResizePolicyBuilder)this.resizePolicy.get(0)).build();
   }

   public ContainerResizePolicy buildLastResizePolicy() {
      return ((ContainerResizePolicyBuilder)this.resizePolicy.get(this.resizePolicy.size() - 1)).build();
   }

   public ContainerResizePolicy buildMatchingResizePolicy(Predicate predicate) {
      for(ContainerResizePolicyBuilder item : this.resizePolicy) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResizePolicy(Predicate predicate) {
      for(ContainerResizePolicyBuilder item : this.resizePolicy) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EphemeralContainerFluent withResizePolicy(List resizePolicy) {
      if (this.resizePolicy != null) {
         this._visitables.get("resizePolicy").clear();
      }

      if (resizePolicy != null) {
         this.resizePolicy = new ArrayList();

         for(ContainerResizePolicy item : resizePolicy) {
            this.addToResizePolicy(item);
         }
      } else {
         this.resizePolicy = null;
      }

      return this;
   }

   public EphemeralContainerFluent withResizePolicy(ContainerResizePolicy... resizePolicy) {
      if (this.resizePolicy != null) {
         this.resizePolicy.clear();
         this._visitables.remove("resizePolicy");
      }

      if (resizePolicy != null) {
         for(ContainerResizePolicy item : resizePolicy) {
            this.addToResizePolicy(item);
         }
      }

      return this;
   }

   public boolean hasResizePolicy() {
      return this.resizePolicy != null && !this.resizePolicy.isEmpty();
   }

   public EphemeralContainerFluent addNewResizePolicy(String resourceName, String restartPolicy) {
      return this.addToResizePolicy(new ContainerResizePolicy(resourceName, restartPolicy));
   }

   public ResizePolicyNested addNewResizePolicy() {
      return new ResizePolicyNested(-1, (ContainerResizePolicy)null);
   }

   public ResizePolicyNested addNewResizePolicyLike(ContainerResizePolicy item) {
      return new ResizePolicyNested(-1, item);
   }

   public ResizePolicyNested setNewResizePolicyLike(int index, ContainerResizePolicy item) {
      return new ResizePolicyNested(index, item);
   }

   public ResizePolicyNested editResizePolicy(int index) {
      if (this.resizePolicy.size() <= index) {
         throw new RuntimeException("Can't edit resizePolicy. Index exceeds size.");
      } else {
         return this.setNewResizePolicyLike(index, this.buildResizePolicy(index));
      }
   }

   public ResizePolicyNested editFirstResizePolicy() {
      if (this.resizePolicy.size() == 0) {
         throw new RuntimeException("Can't edit first resizePolicy. The list is empty.");
      } else {
         return this.setNewResizePolicyLike(0, this.buildResizePolicy(0));
      }
   }

   public ResizePolicyNested editLastResizePolicy() {
      int index = this.resizePolicy.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last resizePolicy. The list is empty.");
      } else {
         return this.setNewResizePolicyLike(index, this.buildResizePolicy(index));
      }
   }

   public ResizePolicyNested editMatchingResizePolicy(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.resizePolicy.size(); ++i) {
         if (predicate.test((ContainerResizePolicyBuilder)this.resizePolicy.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching resizePolicy. No match found.");
      } else {
         return this.setNewResizePolicyLike(index, this.buildResizePolicy(index));
      }
   }

   public ResourceRequirements buildResources() {
      return this.resources != null ? this.resources.build() : null;
   }

   public EphemeralContainerFluent withResources(ResourceRequirements resources) {
      this._visitables.remove("resources");
      if (resources != null) {
         this.resources = new ResourceRequirementsBuilder(resources);
         this._visitables.get("resources").add(this.resources);
      } else {
         this.resources = null;
         this._visitables.get("resources").remove(this.resources);
      }

      return this;
   }

   public boolean hasResources() {
      return this.resources != null;
   }

   public ResourcesNested withNewResources() {
      return new ResourcesNested((ResourceRequirements)null);
   }

   public ResourcesNested withNewResourcesLike(ResourceRequirements item) {
      return new ResourcesNested(item);
   }

   public ResourcesNested editResources() {
      return this.withNewResourcesLike((ResourceRequirements)Optional.ofNullable(this.buildResources()).orElse((Object)null));
   }

   public ResourcesNested editOrNewResources() {
      return this.withNewResourcesLike((ResourceRequirements)Optional.ofNullable(this.buildResources()).orElse((new ResourceRequirementsBuilder()).build()));
   }

   public ResourcesNested editOrNewResourcesLike(ResourceRequirements item) {
      return this.withNewResourcesLike((ResourceRequirements)Optional.ofNullable(this.buildResources()).orElse(item));
   }

   public String getRestartPolicy() {
      return this.restartPolicy;
   }

   public EphemeralContainerFluent withRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
      return this;
   }

   public boolean hasRestartPolicy() {
      return this.restartPolicy != null;
   }

   public SecurityContext buildSecurityContext() {
      return this.securityContext != null ? this.securityContext.build() : null;
   }

   public EphemeralContainerFluent withSecurityContext(SecurityContext securityContext) {
      this._visitables.remove("securityContext");
      if (securityContext != null) {
         this.securityContext = new SecurityContextBuilder(securityContext);
         this._visitables.get("securityContext").add(this.securityContext);
      } else {
         this.securityContext = null;
         this._visitables.get("securityContext").remove(this.securityContext);
      }

      return this;
   }

   public boolean hasSecurityContext() {
      return this.securityContext != null;
   }

   public SecurityContextNested withNewSecurityContext() {
      return new SecurityContextNested((SecurityContext)null);
   }

   public SecurityContextNested withNewSecurityContextLike(SecurityContext item) {
      return new SecurityContextNested(item);
   }

   public SecurityContextNested editSecurityContext() {
      return this.withNewSecurityContextLike((SecurityContext)Optional.ofNullable(this.buildSecurityContext()).orElse((Object)null));
   }

   public SecurityContextNested editOrNewSecurityContext() {
      return this.withNewSecurityContextLike((SecurityContext)Optional.ofNullable(this.buildSecurityContext()).orElse((new SecurityContextBuilder()).build()));
   }

   public SecurityContextNested editOrNewSecurityContextLike(SecurityContext item) {
      return this.withNewSecurityContextLike((SecurityContext)Optional.ofNullable(this.buildSecurityContext()).orElse(item));
   }

   public Probe buildStartupProbe() {
      return this.startupProbe != null ? this.startupProbe.build() : null;
   }

   public EphemeralContainerFluent withStartupProbe(Probe startupProbe) {
      this._visitables.remove("startupProbe");
      if (startupProbe != null) {
         this.startupProbe = new ProbeBuilder(startupProbe);
         this._visitables.get("startupProbe").add(this.startupProbe);
      } else {
         this.startupProbe = null;
         this._visitables.get("startupProbe").remove(this.startupProbe);
      }

      return this;
   }

   public boolean hasStartupProbe() {
      return this.startupProbe != null;
   }

   public StartupProbeNested withNewStartupProbe() {
      return new StartupProbeNested((Probe)null);
   }

   public StartupProbeNested withNewStartupProbeLike(Probe item) {
      return new StartupProbeNested(item);
   }

   public StartupProbeNested editStartupProbe() {
      return this.withNewStartupProbeLike((Probe)Optional.ofNullable(this.buildStartupProbe()).orElse((Object)null));
   }

   public StartupProbeNested editOrNewStartupProbe() {
      return this.withNewStartupProbeLike((Probe)Optional.ofNullable(this.buildStartupProbe()).orElse((new ProbeBuilder()).build()));
   }

   public StartupProbeNested editOrNewStartupProbeLike(Probe item) {
      return this.withNewStartupProbeLike((Probe)Optional.ofNullable(this.buildStartupProbe()).orElse(item));
   }

   public Boolean getStdin() {
      return this.stdin;
   }

   public EphemeralContainerFluent withStdin(Boolean stdin) {
      this.stdin = stdin;
      return this;
   }

   public boolean hasStdin() {
      return this.stdin != null;
   }

   public Boolean getStdinOnce() {
      return this.stdinOnce;
   }

   public EphemeralContainerFluent withStdinOnce(Boolean stdinOnce) {
      this.stdinOnce = stdinOnce;
      return this;
   }

   public boolean hasStdinOnce() {
      return this.stdinOnce != null;
   }

   public String getTargetContainerName() {
      return this.targetContainerName;
   }

   public EphemeralContainerFluent withTargetContainerName(String targetContainerName) {
      this.targetContainerName = targetContainerName;
      return this;
   }

   public boolean hasTargetContainerName() {
      return this.targetContainerName != null;
   }

   public String getTerminationMessagePath() {
      return this.terminationMessagePath;
   }

   public EphemeralContainerFluent withTerminationMessagePath(String terminationMessagePath) {
      this.terminationMessagePath = terminationMessagePath;
      return this;
   }

   public boolean hasTerminationMessagePath() {
      return this.terminationMessagePath != null;
   }

   public String getTerminationMessagePolicy() {
      return this.terminationMessagePolicy;
   }

   public EphemeralContainerFluent withTerminationMessagePolicy(String terminationMessagePolicy) {
      this.terminationMessagePolicy = terminationMessagePolicy;
      return this;
   }

   public boolean hasTerminationMessagePolicy() {
      return this.terminationMessagePolicy != null;
   }

   public Boolean getTty() {
      return this.tty;
   }

   public EphemeralContainerFluent withTty(Boolean tty) {
      this.tty = tty;
      return this;
   }

   public boolean hasTty() {
      return this.tty != null;
   }

   public EphemeralContainerFluent addToVolumeDevices(int index, VolumeDevice item) {
      if (this.volumeDevices == null) {
         this.volumeDevices = new ArrayList();
      }

      VolumeDeviceBuilder builder = new VolumeDeviceBuilder(item);
      if (index >= 0 && index < this.volumeDevices.size()) {
         this._visitables.get("volumeDevices").add(index, builder);
         this.volumeDevices.add(index, builder);
      } else {
         this._visitables.get("volumeDevices").add(builder);
         this.volumeDevices.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent setToVolumeDevices(int index, VolumeDevice item) {
      if (this.volumeDevices == null) {
         this.volumeDevices = new ArrayList();
      }

      VolumeDeviceBuilder builder = new VolumeDeviceBuilder(item);
      if (index >= 0 && index < this.volumeDevices.size()) {
         this._visitables.get("volumeDevices").set(index, builder);
         this.volumeDevices.set(index, builder);
      } else {
         this._visitables.get("volumeDevices").add(builder);
         this.volumeDevices.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addToVolumeDevices(VolumeDevice... items) {
      if (this.volumeDevices == null) {
         this.volumeDevices = new ArrayList();
      }

      for(VolumeDevice item : items) {
         VolumeDeviceBuilder builder = new VolumeDeviceBuilder(item);
         this._visitables.get("volumeDevices").add(builder);
         this.volumeDevices.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToVolumeDevices(Collection items) {
      if (this.volumeDevices == null) {
         this.volumeDevices = new ArrayList();
      }

      for(VolumeDevice item : items) {
         VolumeDeviceBuilder builder = new VolumeDeviceBuilder(item);
         this._visitables.get("volumeDevices").add(builder);
         this.volumeDevices.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromVolumeDevices(VolumeDevice... items) {
      if (this.volumeDevices == null) {
         return this;
      } else {
         for(VolumeDevice item : items) {
            VolumeDeviceBuilder builder = new VolumeDeviceBuilder(item);
            this._visitables.get("volumeDevices").remove(builder);
            this.volumeDevices.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromVolumeDevices(Collection items) {
      if (this.volumeDevices == null) {
         return this;
      } else {
         for(VolumeDevice item : items) {
            VolumeDeviceBuilder builder = new VolumeDeviceBuilder(item);
            this._visitables.get("volumeDevices").remove(builder);
            this.volumeDevices.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeMatchingFromVolumeDevices(Predicate predicate) {
      if (this.volumeDevices == null) {
         return this;
      } else {
         Iterator<VolumeDeviceBuilder> each = this.volumeDevices.iterator();
         List visitables = this._visitables.get("volumeDevices");

         while(each.hasNext()) {
            VolumeDeviceBuilder builder = (VolumeDeviceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVolumeDevices() {
      return this.volumeDevices != null ? build(this.volumeDevices) : null;
   }

   public VolumeDevice buildVolumeDevice(int index) {
      return ((VolumeDeviceBuilder)this.volumeDevices.get(index)).build();
   }

   public VolumeDevice buildFirstVolumeDevice() {
      return ((VolumeDeviceBuilder)this.volumeDevices.get(0)).build();
   }

   public VolumeDevice buildLastVolumeDevice() {
      return ((VolumeDeviceBuilder)this.volumeDevices.get(this.volumeDevices.size() - 1)).build();
   }

   public VolumeDevice buildMatchingVolumeDevice(Predicate predicate) {
      for(VolumeDeviceBuilder item : this.volumeDevices) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVolumeDevice(Predicate predicate) {
      for(VolumeDeviceBuilder item : this.volumeDevices) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EphemeralContainerFluent withVolumeDevices(List volumeDevices) {
      if (this.volumeDevices != null) {
         this._visitables.get("volumeDevices").clear();
      }

      if (volumeDevices != null) {
         this.volumeDevices = new ArrayList();

         for(VolumeDevice item : volumeDevices) {
            this.addToVolumeDevices(item);
         }
      } else {
         this.volumeDevices = null;
      }

      return this;
   }

   public EphemeralContainerFluent withVolumeDevices(VolumeDevice... volumeDevices) {
      if (this.volumeDevices != null) {
         this.volumeDevices.clear();
         this._visitables.remove("volumeDevices");
      }

      if (volumeDevices != null) {
         for(VolumeDevice item : volumeDevices) {
            this.addToVolumeDevices(item);
         }
      }

      return this;
   }

   public boolean hasVolumeDevices() {
      return this.volumeDevices != null && !this.volumeDevices.isEmpty();
   }

   public EphemeralContainerFluent addNewVolumeDevice(String devicePath, String name) {
      return this.addToVolumeDevices(new VolumeDevice(devicePath, name));
   }

   public VolumeDevicesNested addNewVolumeDevice() {
      return new VolumeDevicesNested(-1, (VolumeDevice)null);
   }

   public VolumeDevicesNested addNewVolumeDeviceLike(VolumeDevice item) {
      return new VolumeDevicesNested(-1, item);
   }

   public VolumeDevicesNested setNewVolumeDeviceLike(int index, VolumeDevice item) {
      return new VolumeDevicesNested(index, item);
   }

   public VolumeDevicesNested editVolumeDevice(int index) {
      if (this.volumeDevices.size() <= index) {
         throw new RuntimeException("Can't edit volumeDevices. Index exceeds size.");
      } else {
         return this.setNewVolumeDeviceLike(index, this.buildVolumeDevice(index));
      }
   }

   public VolumeDevicesNested editFirstVolumeDevice() {
      if (this.volumeDevices.size() == 0) {
         throw new RuntimeException("Can't edit first volumeDevices. The list is empty.");
      } else {
         return this.setNewVolumeDeviceLike(0, this.buildVolumeDevice(0));
      }
   }

   public VolumeDevicesNested editLastVolumeDevice() {
      int index = this.volumeDevices.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last volumeDevices. The list is empty.");
      } else {
         return this.setNewVolumeDeviceLike(index, this.buildVolumeDevice(index));
      }
   }

   public VolumeDevicesNested editMatchingVolumeDevice(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.volumeDevices.size(); ++i) {
         if (predicate.test((VolumeDeviceBuilder)this.volumeDevices.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching volumeDevices. No match found.");
      } else {
         return this.setNewVolumeDeviceLike(index, this.buildVolumeDevice(index));
      }
   }

   public EphemeralContainerFluent addToVolumeMounts(int index, VolumeMount item) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      VolumeMountBuilder builder = new VolumeMountBuilder(item);
      if (index >= 0 && index < this.volumeMounts.size()) {
         this._visitables.get("volumeMounts").add(index, builder);
         this.volumeMounts.add(index, builder);
      } else {
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent setToVolumeMounts(int index, VolumeMount item) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      VolumeMountBuilder builder = new VolumeMountBuilder(item);
      if (index >= 0 && index < this.volumeMounts.size()) {
         this._visitables.get("volumeMounts").set(index, builder);
         this.volumeMounts.set(index, builder);
      } else {
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addToVolumeMounts(VolumeMount... items) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      for(VolumeMount item : items) {
         VolumeMountBuilder builder = new VolumeMountBuilder(item);
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent addAllToVolumeMounts(Collection items) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      for(VolumeMount item : items) {
         VolumeMountBuilder builder = new VolumeMountBuilder(item);
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromVolumeMounts(VolumeMount... items) {
      if (this.volumeMounts == null) {
         return this;
      } else {
         for(VolumeMount item : items) {
            VolumeMountBuilder builder = new VolumeMountBuilder(item);
            this._visitables.get("volumeMounts").remove(builder);
            this.volumeMounts.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeAllFromVolumeMounts(Collection items) {
      if (this.volumeMounts == null) {
         return this;
      } else {
         for(VolumeMount item : items) {
            VolumeMountBuilder builder = new VolumeMountBuilder(item);
            this._visitables.get("volumeMounts").remove(builder);
            this.volumeMounts.remove(builder);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeMatchingFromVolumeMounts(Predicate predicate) {
      if (this.volumeMounts == null) {
         return this;
      } else {
         Iterator<VolumeMountBuilder> each = this.volumeMounts.iterator();
         List visitables = this._visitables.get("volumeMounts");

         while(each.hasNext()) {
            VolumeMountBuilder builder = (VolumeMountBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVolumeMounts() {
      return this.volumeMounts != null ? build(this.volumeMounts) : null;
   }

   public VolumeMount buildVolumeMount(int index) {
      return ((VolumeMountBuilder)this.volumeMounts.get(index)).build();
   }

   public VolumeMount buildFirstVolumeMount() {
      return ((VolumeMountBuilder)this.volumeMounts.get(0)).build();
   }

   public VolumeMount buildLastVolumeMount() {
      return ((VolumeMountBuilder)this.volumeMounts.get(this.volumeMounts.size() - 1)).build();
   }

   public VolumeMount buildMatchingVolumeMount(Predicate predicate) {
      for(VolumeMountBuilder item : this.volumeMounts) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVolumeMount(Predicate predicate) {
      for(VolumeMountBuilder item : this.volumeMounts) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EphemeralContainerFluent withVolumeMounts(List volumeMounts) {
      if (this.volumeMounts != null) {
         this._visitables.get("volumeMounts").clear();
      }

      if (volumeMounts != null) {
         this.volumeMounts = new ArrayList();

         for(VolumeMount item : volumeMounts) {
            this.addToVolumeMounts(item);
         }
      } else {
         this.volumeMounts = null;
      }

      return this;
   }

   public EphemeralContainerFluent withVolumeMounts(VolumeMount... volumeMounts) {
      if (this.volumeMounts != null) {
         this.volumeMounts.clear();
         this._visitables.remove("volumeMounts");
      }

      if (volumeMounts != null) {
         for(VolumeMount item : volumeMounts) {
            this.addToVolumeMounts(item);
         }
      }

      return this;
   }

   public boolean hasVolumeMounts() {
      return this.volumeMounts != null && !this.volumeMounts.isEmpty();
   }

   public VolumeMountsNested addNewVolumeMount() {
      return new VolumeMountsNested(-1, (VolumeMount)null);
   }

   public VolumeMountsNested addNewVolumeMountLike(VolumeMount item) {
      return new VolumeMountsNested(-1, item);
   }

   public VolumeMountsNested setNewVolumeMountLike(int index, VolumeMount item) {
      return new VolumeMountsNested(index, item);
   }

   public VolumeMountsNested editVolumeMount(int index) {
      if (this.volumeMounts.size() <= index) {
         throw new RuntimeException("Can't edit volumeMounts. Index exceeds size.");
      } else {
         return this.setNewVolumeMountLike(index, this.buildVolumeMount(index));
      }
   }

   public VolumeMountsNested editFirstVolumeMount() {
      if (this.volumeMounts.size() == 0) {
         throw new RuntimeException("Can't edit first volumeMounts. The list is empty.");
      } else {
         return this.setNewVolumeMountLike(0, this.buildVolumeMount(0));
      }
   }

   public VolumeMountsNested editLastVolumeMount() {
      int index = this.volumeMounts.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last volumeMounts. The list is empty.");
      } else {
         return this.setNewVolumeMountLike(index, this.buildVolumeMount(index));
      }
   }

   public VolumeMountsNested editMatchingVolumeMount(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.volumeMounts.size(); ++i) {
         if (predicate.test((VolumeMountBuilder)this.volumeMounts.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching volumeMounts. No match found.");
      } else {
         return this.setNewVolumeMountLike(index, this.buildVolumeMount(index));
      }
   }

   public String getWorkingDir() {
      return this.workingDir;
   }

   public EphemeralContainerFluent withWorkingDir(String workingDir) {
      this.workingDir = workingDir;
      return this;
   }

   public boolean hasWorkingDir() {
      return this.workingDir != null;
   }

   public EphemeralContainerFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EphemeralContainerFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EphemeralContainerFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EphemeralContainerFluent removeFromAdditionalProperties(Map map) {
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

   public EphemeralContainerFluent withAdditionalProperties(Map additionalProperties) {
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
            EphemeralContainerFluent that = (EphemeralContainerFluent)o;
            if (!Objects.equals(this.args, that.args)) {
               return false;
            } else if (!Objects.equals(this.command, that.command)) {
               return false;
            } else if (!Objects.equals(this.env, that.env)) {
               return false;
            } else if (!Objects.equals(this.envFrom, that.envFrom)) {
               return false;
            } else if (!Objects.equals(this.image, that.image)) {
               return false;
            } else if (!Objects.equals(this.imagePullPolicy, that.imagePullPolicy)) {
               return false;
            } else if (!Objects.equals(this.lifecycle, that.lifecycle)) {
               return false;
            } else if (!Objects.equals(this.livenessProbe, that.livenessProbe)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.ports, that.ports)) {
               return false;
            } else if (!Objects.equals(this.readinessProbe, that.readinessProbe)) {
               return false;
            } else if (!Objects.equals(this.resizePolicy, that.resizePolicy)) {
               return false;
            } else if (!Objects.equals(this.resources, that.resources)) {
               return false;
            } else if (!Objects.equals(this.restartPolicy, that.restartPolicy)) {
               return false;
            } else if (!Objects.equals(this.securityContext, that.securityContext)) {
               return false;
            } else if (!Objects.equals(this.startupProbe, that.startupProbe)) {
               return false;
            } else if (!Objects.equals(this.stdin, that.stdin)) {
               return false;
            } else if (!Objects.equals(this.stdinOnce, that.stdinOnce)) {
               return false;
            } else if (!Objects.equals(this.targetContainerName, that.targetContainerName)) {
               return false;
            } else if (!Objects.equals(this.terminationMessagePath, that.terminationMessagePath)) {
               return false;
            } else if (!Objects.equals(this.terminationMessagePolicy, that.terminationMessagePolicy)) {
               return false;
            } else if (!Objects.equals(this.tty, that.tty)) {
               return false;
            } else if (!Objects.equals(this.volumeDevices, that.volumeDevices)) {
               return false;
            } else if (!Objects.equals(this.volumeMounts, that.volumeMounts)) {
               return false;
            } else if (!Objects.equals(this.workingDir, that.workingDir)) {
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
      return Objects.hash(new Object[]{this.args, this.command, this.env, this.envFrom, this.image, this.imagePullPolicy, this.lifecycle, this.livenessProbe, this.name, this.ports, this.readinessProbe, this.resizePolicy, this.resources, this.restartPolicy, this.securityContext, this.startupProbe, this.stdin, this.stdinOnce, this.targetContainerName, this.terminationMessagePath, this.terminationMessagePolicy, this.tty, this.volumeDevices, this.volumeMounts, this.workingDir, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.args != null && !this.args.isEmpty()) {
         sb.append("args:");
         sb.append(this.args + ",");
      }

      if (this.command != null && !this.command.isEmpty()) {
         sb.append("command:");
         sb.append(this.command + ",");
      }

      if (this.env != null && !this.env.isEmpty()) {
         sb.append("env:");
         sb.append(this.env + ",");
      }

      if (this.envFrom != null && !this.envFrom.isEmpty()) {
         sb.append("envFrom:");
         sb.append(this.envFrom + ",");
      }

      if (this.image != null) {
         sb.append("image:");
         sb.append(this.image + ",");
      }

      if (this.imagePullPolicy != null) {
         sb.append("imagePullPolicy:");
         sb.append(this.imagePullPolicy + ",");
      }

      if (this.lifecycle != null) {
         sb.append("lifecycle:");
         sb.append(this.lifecycle + ",");
      }

      if (this.livenessProbe != null) {
         sb.append("livenessProbe:");
         sb.append(this.livenessProbe + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.ports != null && !this.ports.isEmpty()) {
         sb.append("ports:");
         sb.append(this.ports + ",");
      }

      if (this.readinessProbe != null) {
         sb.append("readinessProbe:");
         sb.append(this.readinessProbe + ",");
      }

      if (this.resizePolicy != null && !this.resizePolicy.isEmpty()) {
         sb.append("resizePolicy:");
         sb.append(this.resizePolicy + ",");
      }

      if (this.resources != null) {
         sb.append("resources:");
         sb.append(this.resources + ",");
      }

      if (this.restartPolicy != null) {
         sb.append("restartPolicy:");
         sb.append(this.restartPolicy + ",");
      }

      if (this.securityContext != null) {
         sb.append("securityContext:");
         sb.append(this.securityContext + ",");
      }

      if (this.startupProbe != null) {
         sb.append("startupProbe:");
         sb.append(this.startupProbe + ",");
      }

      if (this.stdin != null) {
         sb.append("stdin:");
         sb.append(this.stdin + ",");
      }

      if (this.stdinOnce != null) {
         sb.append("stdinOnce:");
         sb.append(this.stdinOnce + ",");
      }

      if (this.targetContainerName != null) {
         sb.append("targetContainerName:");
         sb.append(this.targetContainerName + ",");
      }

      if (this.terminationMessagePath != null) {
         sb.append("terminationMessagePath:");
         sb.append(this.terminationMessagePath + ",");
      }

      if (this.terminationMessagePolicy != null) {
         sb.append("terminationMessagePolicy:");
         sb.append(this.terminationMessagePolicy + ",");
      }

      if (this.tty != null) {
         sb.append("tty:");
         sb.append(this.tty + ",");
      }

      if (this.volumeDevices != null && !this.volumeDevices.isEmpty()) {
         sb.append("volumeDevices:");
         sb.append(this.volumeDevices + ",");
      }

      if (this.volumeMounts != null && !this.volumeMounts.isEmpty()) {
         sb.append("volumeMounts:");
         sb.append(this.volumeMounts + ",");
      }

      if (this.workingDir != null) {
         sb.append("workingDir:");
         sb.append(this.workingDir + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public EphemeralContainerFluent withStdin() {
      return this.withStdin(true);
   }

   public EphemeralContainerFluent withStdinOnce() {
      return this.withStdinOnce(true);
   }

   public EphemeralContainerFluent withTty() {
      return this.withTty(true);
   }

   public class EnvNested extends EnvVarFluent implements Nested {
      EnvVarBuilder builder;
      int index;

      EnvNested(int index, EnvVar item) {
         this.index = index;
         this.builder = new EnvVarBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.setToEnv(this.index, this.builder.build());
      }

      public Object endEnv() {
         return this.and();
      }
   }

   public class EnvFromNested extends EnvFromSourceFluent implements Nested {
      EnvFromSourceBuilder builder;
      int index;

      EnvFromNested(int index, EnvFromSource item) {
         this.index = index;
         this.builder = new EnvFromSourceBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.setToEnvFrom(this.index, this.builder.build());
      }

      public Object endEnvFrom() {
         return this.and();
      }
   }

   public class LifecycleNested extends LifecycleFluent implements Nested {
      LifecycleBuilder builder;

      LifecycleNested(Lifecycle item) {
         this.builder = new LifecycleBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.withLifecycle(this.builder.build());
      }

      public Object endLifecycle() {
         return this.and();
      }
   }

   public class LivenessProbeNested extends ProbeFluent implements Nested {
      ProbeBuilder builder;

      LivenessProbeNested(Probe item) {
         this.builder = new ProbeBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.withLivenessProbe(this.builder.build());
      }

      public Object endLivenessProbe() {
         return this.and();
      }
   }

   public class PortsNested extends ContainerPortFluent implements Nested {
      ContainerPortBuilder builder;
      int index;

      PortsNested(int index, ContainerPort item) {
         this.index = index;
         this.builder = new ContainerPortBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.setToPorts(this.index, this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }

   public class ReadinessProbeNested extends ProbeFluent implements Nested {
      ProbeBuilder builder;

      ReadinessProbeNested(Probe item) {
         this.builder = new ProbeBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.withReadinessProbe(this.builder.build());
      }

      public Object endReadinessProbe() {
         return this.and();
      }
   }

   public class ResizePolicyNested extends ContainerResizePolicyFluent implements Nested {
      ContainerResizePolicyBuilder builder;
      int index;

      ResizePolicyNested(int index, ContainerResizePolicy item) {
         this.index = index;
         this.builder = new ContainerResizePolicyBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.setToResizePolicy(this.index, this.builder.build());
      }

      public Object endResizePolicy() {
         return this.and();
      }
   }

   public class ResourcesNested extends ResourceRequirementsFluent implements Nested {
      ResourceRequirementsBuilder builder;

      ResourcesNested(ResourceRequirements item) {
         this.builder = new ResourceRequirementsBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.withResources(this.builder.build());
      }

      public Object endResources() {
         return this.and();
      }
   }

   public class SecurityContextNested extends SecurityContextFluent implements Nested {
      SecurityContextBuilder builder;

      SecurityContextNested(SecurityContext item) {
         this.builder = new SecurityContextBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.withSecurityContext(this.builder.build());
      }

      public Object endSecurityContext() {
         return this.and();
      }
   }

   public class StartupProbeNested extends ProbeFluent implements Nested {
      ProbeBuilder builder;

      StartupProbeNested(Probe item) {
         this.builder = new ProbeBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.withStartupProbe(this.builder.build());
      }

      public Object endStartupProbe() {
         return this.and();
      }
   }

   public class VolumeDevicesNested extends VolumeDeviceFluent implements Nested {
      VolumeDeviceBuilder builder;
      int index;

      VolumeDevicesNested(int index, VolumeDevice item) {
         this.index = index;
         this.builder = new VolumeDeviceBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.setToVolumeDevices(this.index, this.builder.build());
      }

      public Object endVolumeDevice() {
         return this.and();
      }
   }

   public class VolumeMountsNested extends VolumeMountFluent implements Nested {
      VolumeMountBuilder builder;
      int index;

      VolumeMountsNested(int index, VolumeMount item) {
         this.index = index;
         this.builder = new VolumeMountBuilder(this, item);
      }

      public Object and() {
         return EphemeralContainerFluent.this.setToVolumeMounts(this.index, this.builder.build());
      }

      public Object endVolumeMount() {
         return this.and();
      }
   }
}
