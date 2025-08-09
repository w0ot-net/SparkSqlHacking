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

public class PodSpecFluent extends BaseFluent {
   private Long activeDeadlineSeconds;
   private AffinityBuilder affinity;
   private Boolean automountServiceAccountToken;
   private ArrayList containers = new ArrayList();
   private PodDNSConfigBuilder dnsConfig;
   private String dnsPolicy;
   private Boolean enableServiceLinks;
   private ArrayList ephemeralContainers = new ArrayList();
   private ArrayList hostAliases = new ArrayList();
   private Boolean hostIPC;
   private Boolean hostNetwork;
   private Boolean hostPID;
   private Boolean hostUsers;
   private String hostname;
   private ArrayList imagePullSecrets = new ArrayList();
   private ArrayList initContainers = new ArrayList();
   private String nodeName;
   private Map nodeSelector;
   private PodOSBuilder os;
   private Map overhead;
   private String preemptionPolicy;
   private Integer priority;
   private String priorityClassName;
   private ArrayList readinessGates = new ArrayList();
   private ArrayList resourceClaims = new ArrayList();
   private ResourceRequirementsBuilder resources;
   private String restartPolicy;
   private String runtimeClassName;
   private String schedulerName;
   private ArrayList schedulingGates = new ArrayList();
   private PodSecurityContextBuilder securityContext;
   private String serviceAccount;
   private String serviceAccountName;
   private Boolean setHostnameAsFQDN;
   private Boolean shareProcessNamespace;
   private String subdomain;
   private Long terminationGracePeriodSeconds;
   private ArrayList tolerations = new ArrayList();
   private ArrayList topologySpreadConstraints = new ArrayList();
   private ArrayList volumes = new ArrayList();
   private Map additionalProperties;

   public PodSpecFluent() {
   }

   public PodSpecFluent(PodSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodSpec instance) {
      instance = instance != null ? instance : new PodSpec();
      if (instance != null) {
         this.withActiveDeadlineSeconds(instance.getActiveDeadlineSeconds());
         this.withAffinity(instance.getAffinity());
         this.withAutomountServiceAccountToken(instance.getAutomountServiceAccountToken());
         this.withContainers(instance.getContainers());
         this.withDnsConfig(instance.getDnsConfig());
         this.withDnsPolicy(instance.getDnsPolicy());
         this.withEnableServiceLinks(instance.getEnableServiceLinks());
         this.withEphemeralContainers(instance.getEphemeralContainers());
         this.withHostAliases(instance.getHostAliases());
         this.withHostIPC(instance.getHostIPC());
         this.withHostNetwork(instance.getHostNetwork());
         this.withHostPID(instance.getHostPID());
         this.withHostUsers(instance.getHostUsers());
         this.withHostname(instance.getHostname());
         this.withImagePullSecrets(instance.getImagePullSecrets());
         this.withInitContainers(instance.getInitContainers());
         this.withNodeName(instance.getNodeName());
         this.withNodeSelector(instance.getNodeSelector());
         this.withOs(instance.getOs());
         this.withOverhead(instance.getOverhead());
         this.withPreemptionPolicy(instance.getPreemptionPolicy());
         this.withPriority(instance.getPriority());
         this.withPriorityClassName(instance.getPriorityClassName());
         this.withReadinessGates(instance.getReadinessGates());
         this.withResourceClaims(instance.getResourceClaims());
         this.withResources(instance.getResources());
         this.withRestartPolicy(instance.getRestartPolicy());
         this.withRuntimeClassName(instance.getRuntimeClassName());
         this.withSchedulerName(instance.getSchedulerName());
         this.withSchedulingGates(instance.getSchedulingGates());
         this.withSecurityContext(instance.getSecurityContext());
         this.withServiceAccount(instance.getServiceAccount());
         this.withServiceAccountName(instance.getServiceAccountName());
         this.withSetHostnameAsFQDN(instance.getSetHostnameAsFQDN());
         this.withShareProcessNamespace(instance.getShareProcessNamespace());
         this.withSubdomain(instance.getSubdomain());
         this.withTerminationGracePeriodSeconds(instance.getTerminationGracePeriodSeconds());
         this.withTolerations(instance.getTolerations());
         this.withTopologySpreadConstraints(instance.getTopologySpreadConstraints());
         this.withVolumes(instance.getVolumes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Long getActiveDeadlineSeconds() {
      return this.activeDeadlineSeconds;
   }

   public PodSpecFluent withActiveDeadlineSeconds(Long activeDeadlineSeconds) {
      this.activeDeadlineSeconds = activeDeadlineSeconds;
      return this;
   }

   public boolean hasActiveDeadlineSeconds() {
      return this.activeDeadlineSeconds != null;
   }

   public Affinity buildAffinity() {
      return this.affinity != null ? this.affinity.build() : null;
   }

   public PodSpecFluent withAffinity(Affinity affinity) {
      this._visitables.remove("affinity");
      if (affinity != null) {
         this.affinity = new AffinityBuilder(affinity);
         this._visitables.get("affinity").add(this.affinity);
      } else {
         this.affinity = null;
         this._visitables.get("affinity").remove(this.affinity);
      }

      return this;
   }

   public boolean hasAffinity() {
      return this.affinity != null;
   }

   public AffinityNested withNewAffinity() {
      return new AffinityNested((Affinity)null);
   }

   public AffinityNested withNewAffinityLike(Affinity item) {
      return new AffinityNested(item);
   }

   public AffinityNested editAffinity() {
      return this.withNewAffinityLike((Affinity)Optional.ofNullable(this.buildAffinity()).orElse((Object)null));
   }

   public AffinityNested editOrNewAffinity() {
      return this.withNewAffinityLike((Affinity)Optional.ofNullable(this.buildAffinity()).orElse((new AffinityBuilder()).build()));
   }

   public AffinityNested editOrNewAffinityLike(Affinity item) {
      return this.withNewAffinityLike((Affinity)Optional.ofNullable(this.buildAffinity()).orElse(item));
   }

   public Boolean getAutomountServiceAccountToken() {
      return this.automountServiceAccountToken;
   }

   public PodSpecFluent withAutomountServiceAccountToken(Boolean automountServiceAccountToken) {
      this.automountServiceAccountToken = automountServiceAccountToken;
      return this;
   }

   public boolean hasAutomountServiceAccountToken() {
      return this.automountServiceAccountToken != null;
   }

   public PodSpecFluent addToContainers(int index, Container item) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      ContainerBuilder builder = new ContainerBuilder(item);
      if (index >= 0 && index < this.containers.size()) {
         this._visitables.get("containers").add(index, builder);
         this.containers.add(index, builder);
      } else {
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToContainers(int index, Container item) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      ContainerBuilder builder = new ContainerBuilder(item);
      if (index >= 0 && index < this.containers.size()) {
         this._visitables.get("containers").set(index, builder);
         this.containers.set(index, builder);
      } else {
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToContainers(Container... items) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      for(Container item : items) {
         ContainerBuilder builder = new ContainerBuilder(item);
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToContainers(Collection items) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      for(Container item : items) {
         ContainerBuilder builder = new ContainerBuilder(item);
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromContainers(Container... items) {
      if (this.containers == null) {
         return this;
      } else {
         for(Container item : items) {
            ContainerBuilder builder = new ContainerBuilder(item);
            this._visitables.get("containers").remove(builder);
            this.containers.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromContainers(Collection items) {
      if (this.containers == null) {
         return this;
      } else {
         for(Container item : items) {
            ContainerBuilder builder = new ContainerBuilder(item);
            this._visitables.get("containers").remove(builder);
            this.containers.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromContainers(Predicate predicate) {
      if (this.containers == null) {
         return this;
      } else {
         Iterator<ContainerBuilder> each = this.containers.iterator();
         List visitables = this._visitables.get("containers");

         while(each.hasNext()) {
            ContainerBuilder builder = (ContainerBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildContainers() {
      return this.containers != null ? build(this.containers) : null;
   }

   public Container buildContainer(int index) {
      return ((ContainerBuilder)this.containers.get(index)).build();
   }

   public Container buildFirstContainer() {
      return ((ContainerBuilder)this.containers.get(0)).build();
   }

   public Container buildLastContainer() {
      return ((ContainerBuilder)this.containers.get(this.containers.size() - 1)).build();
   }

   public Container buildMatchingContainer(Predicate predicate) {
      for(ContainerBuilder item : this.containers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingContainer(Predicate predicate) {
      for(ContainerBuilder item : this.containers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withContainers(List containers) {
      if (this.containers != null) {
         this._visitables.get("containers").clear();
      }

      if (containers != null) {
         this.containers = new ArrayList();

         for(Container item : containers) {
            this.addToContainers(item);
         }
      } else {
         this.containers = null;
      }

      return this;
   }

   public PodSpecFluent withContainers(Container... containers) {
      if (this.containers != null) {
         this.containers.clear();
         this._visitables.remove("containers");
      }

      if (containers != null) {
         for(Container item : containers) {
            this.addToContainers(item);
         }
      }

      return this;
   }

   public boolean hasContainers() {
      return this.containers != null && !this.containers.isEmpty();
   }

   public ContainersNested addNewContainer() {
      return new ContainersNested(-1, (Container)null);
   }

   public ContainersNested addNewContainerLike(Container item) {
      return new ContainersNested(-1, item);
   }

   public ContainersNested setNewContainerLike(int index, Container item) {
      return new ContainersNested(index, item);
   }

   public ContainersNested editContainer(int index) {
      if (this.containers.size() <= index) {
         throw new RuntimeException("Can't edit containers. Index exceeds size.");
      } else {
         return this.setNewContainerLike(index, this.buildContainer(index));
      }
   }

   public ContainersNested editFirstContainer() {
      if (this.containers.size() == 0) {
         throw new RuntimeException("Can't edit first containers. The list is empty.");
      } else {
         return this.setNewContainerLike(0, this.buildContainer(0));
      }
   }

   public ContainersNested editLastContainer() {
      int index = this.containers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last containers. The list is empty.");
      } else {
         return this.setNewContainerLike(index, this.buildContainer(index));
      }
   }

   public ContainersNested editMatchingContainer(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.containers.size(); ++i) {
         if (predicate.test((ContainerBuilder)this.containers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching containers. No match found.");
      } else {
         return this.setNewContainerLike(index, this.buildContainer(index));
      }
   }

   public PodDNSConfig buildDnsConfig() {
      return this.dnsConfig != null ? this.dnsConfig.build() : null;
   }

   public PodSpecFluent withDnsConfig(PodDNSConfig dnsConfig) {
      this._visitables.remove("dnsConfig");
      if (dnsConfig != null) {
         this.dnsConfig = new PodDNSConfigBuilder(dnsConfig);
         this._visitables.get("dnsConfig").add(this.dnsConfig);
      } else {
         this.dnsConfig = null;
         this._visitables.get("dnsConfig").remove(this.dnsConfig);
      }

      return this;
   }

   public boolean hasDnsConfig() {
      return this.dnsConfig != null;
   }

   public DnsConfigNested withNewDnsConfig() {
      return new DnsConfigNested((PodDNSConfig)null);
   }

   public DnsConfigNested withNewDnsConfigLike(PodDNSConfig item) {
      return new DnsConfigNested(item);
   }

   public DnsConfigNested editDnsConfig() {
      return this.withNewDnsConfigLike((PodDNSConfig)Optional.ofNullable(this.buildDnsConfig()).orElse((Object)null));
   }

   public DnsConfigNested editOrNewDnsConfig() {
      return this.withNewDnsConfigLike((PodDNSConfig)Optional.ofNullable(this.buildDnsConfig()).orElse((new PodDNSConfigBuilder()).build()));
   }

   public DnsConfigNested editOrNewDnsConfigLike(PodDNSConfig item) {
      return this.withNewDnsConfigLike((PodDNSConfig)Optional.ofNullable(this.buildDnsConfig()).orElse(item));
   }

   public String getDnsPolicy() {
      return this.dnsPolicy;
   }

   public PodSpecFluent withDnsPolicy(String dnsPolicy) {
      this.dnsPolicy = dnsPolicy;
      return this;
   }

   public boolean hasDnsPolicy() {
      return this.dnsPolicy != null;
   }

   public Boolean getEnableServiceLinks() {
      return this.enableServiceLinks;
   }

   public PodSpecFluent withEnableServiceLinks(Boolean enableServiceLinks) {
      this.enableServiceLinks = enableServiceLinks;
      return this;
   }

   public boolean hasEnableServiceLinks() {
      return this.enableServiceLinks != null;
   }

   public PodSpecFluent addToEphemeralContainers(int index, EphemeralContainer item) {
      if (this.ephemeralContainers == null) {
         this.ephemeralContainers = new ArrayList();
      }

      EphemeralContainerBuilder builder = new EphemeralContainerBuilder(item);
      if (index >= 0 && index < this.ephemeralContainers.size()) {
         this._visitables.get("ephemeralContainers").add(index, builder);
         this.ephemeralContainers.add(index, builder);
      } else {
         this._visitables.get("ephemeralContainers").add(builder);
         this.ephemeralContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToEphemeralContainers(int index, EphemeralContainer item) {
      if (this.ephemeralContainers == null) {
         this.ephemeralContainers = new ArrayList();
      }

      EphemeralContainerBuilder builder = new EphemeralContainerBuilder(item);
      if (index >= 0 && index < this.ephemeralContainers.size()) {
         this._visitables.get("ephemeralContainers").set(index, builder);
         this.ephemeralContainers.set(index, builder);
      } else {
         this._visitables.get("ephemeralContainers").add(builder);
         this.ephemeralContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToEphemeralContainers(EphemeralContainer... items) {
      if (this.ephemeralContainers == null) {
         this.ephemeralContainers = new ArrayList();
      }

      for(EphemeralContainer item : items) {
         EphemeralContainerBuilder builder = new EphemeralContainerBuilder(item);
         this._visitables.get("ephemeralContainers").add(builder);
         this.ephemeralContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToEphemeralContainers(Collection items) {
      if (this.ephemeralContainers == null) {
         this.ephemeralContainers = new ArrayList();
      }

      for(EphemeralContainer item : items) {
         EphemeralContainerBuilder builder = new EphemeralContainerBuilder(item);
         this._visitables.get("ephemeralContainers").add(builder);
         this.ephemeralContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromEphemeralContainers(EphemeralContainer... items) {
      if (this.ephemeralContainers == null) {
         return this;
      } else {
         for(EphemeralContainer item : items) {
            EphemeralContainerBuilder builder = new EphemeralContainerBuilder(item);
            this._visitables.get("ephemeralContainers").remove(builder);
            this.ephemeralContainers.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromEphemeralContainers(Collection items) {
      if (this.ephemeralContainers == null) {
         return this;
      } else {
         for(EphemeralContainer item : items) {
            EphemeralContainerBuilder builder = new EphemeralContainerBuilder(item);
            this._visitables.get("ephemeralContainers").remove(builder);
            this.ephemeralContainers.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromEphemeralContainers(Predicate predicate) {
      if (this.ephemeralContainers == null) {
         return this;
      } else {
         Iterator<EphemeralContainerBuilder> each = this.ephemeralContainers.iterator();
         List visitables = this._visitables.get("ephemeralContainers");

         while(each.hasNext()) {
            EphemeralContainerBuilder builder = (EphemeralContainerBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildEphemeralContainers() {
      return this.ephemeralContainers != null ? build(this.ephemeralContainers) : null;
   }

   public EphemeralContainer buildEphemeralContainer(int index) {
      return ((EphemeralContainerBuilder)this.ephemeralContainers.get(index)).build();
   }

   public EphemeralContainer buildFirstEphemeralContainer() {
      return ((EphemeralContainerBuilder)this.ephemeralContainers.get(0)).build();
   }

   public EphemeralContainer buildLastEphemeralContainer() {
      return ((EphemeralContainerBuilder)this.ephemeralContainers.get(this.ephemeralContainers.size() - 1)).build();
   }

   public EphemeralContainer buildMatchingEphemeralContainer(Predicate predicate) {
      for(EphemeralContainerBuilder item : this.ephemeralContainers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingEphemeralContainer(Predicate predicate) {
      for(EphemeralContainerBuilder item : this.ephemeralContainers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withEphemeralContainers(List ephemeralContainers) {
      if (this.ephemeralContainers != null) {
         this._visitables.get("ephemeralContainers").clear();
      }

      if (ephemeralContainers != null) {
         this.ephemeralContainers = new ArrayList();

         for(EphemeralContainer item : ephemeralContainers) {
            this.addToEphemeralContainers(item);
         }
      } else {
         this.ephemeralContainers = null;
      }

      return this;
   }

   public PodSpecFluent withEphemeralContainers(EphemeralContainer... ephemeralContainers) {
      if (this.ephemeralContainers != null) {
         this.ephemeralContainers.clear();
         this._visitables.remove("ephemeralContainers");
      }

      if (ephemeralContainers != null) {
         for(EphemeralContainer item : ephemeralContainers) {
            this.addToEphemeralContainers(item);
         }
      }

      return this;
   }

   public boolean hasEphemeralContainers() {
      return this.ephemeralContainers != null && !this.ephemeralContainers.isEmpty();
   }

   public EphemeralContainersNested addNewEphemeralContainer() {
      return new EphemeralContainersNested(-1, (EphemeralContainer)null);
   }

   public EphemeralContainersNested addNewEphemeralContainerLike(EphemeralContainer item) {
      return new EphemeralContainersNested(-1, item);
   }

   public EphemeralContainersNested setNewEphemeralContainerLike(int index, EphemeralContainer item) {
      return new EphemeralContainersNested(index, item);
   }

   public EphemeralContainersNested editEphemeralContainer(int index) {
      if (this.ephemeralContainers.size() <= index) {
         throw new RuntimeException("Can't edit ephemeralContainers. Index exceeds size.");
      } else {
         return this.setNewEphemeralContainerLike(index, this.buildEphemeralContainer(index));
      }
   }

   public EphemeralContainersNested editFirstEphemeralContainer() {
      if (this.ephemeralContainers.size() == 0) {
         throw new RuntimeException("Can't edit first ephemeralContainers. The list is empty.");
      } else {
         return this.setNewEphemeralContainerLike(0, this.buildEphemeralContainer(0));
      }
   }

   public EphemeralContainersNested editLastEphemeralContainer() {
      int index = this.ephemeralContainers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ephemeralContainers. The list is empty.");
      } else {
         return this.setNewEphemeralContainerLike(index, this.buildEphemeralContainer(index));
      }
   }

   public EphemeralContainersNested editMatchingEphemeralContainer(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ephemeralContainers.size(); ++i) {
         if (predicate.test((EphemeralContainerBuilder)this.ephemeralContainers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ephemeralContainers. No match found.");
      } else {
         return this.setNewEphemeralContainerLike(index, this.buildEphemeralContainer(index));
      }
   }

   public PodSpecFluent addToHostAliases(int index, HostAlias item) {
      if (this.hostAliases == null) {
         this.hostAliases = new ArrayList();
      }

      HostAliasBuilder builder = new HostAliasBuilder(item);
      if (index >= 0 && index < this.hostAliases.size()) {
         this._visitables.get("hostAliases").add(index, builder);
         this.hostAliases.add(index, builder);
      } else {
         this._visitables.get("hostAliases").add(builder);
         this.hostAliases.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToHostAliases(int index, HostAlias item) {
      if (this.hostAliases == null) {
         this.hostAliases = new ArrayList();
      }

      HostAliasBuilder builder = new HostAliasBuilder(item);
      if (index >= 0 && index < this.hostAliases.size()) {
         this._visitables.get("hostAliases").set(index, builder);
         this.hostAliases.set(index, builder);
      } else {
         this._visitables.get("hostAliases").add(builder);
         this.hostAliases.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToHostAliases(HostAlias... items) {
      if (this.hostAliases == null) {
         this.hostAliases = new ArrayList();
      }

      for(HostAlias item : items) {
         HostAliasBuilder builder = new HostAliasBuilder(item);
         this._visitables.get("hostAliases").add(builder);
         this.hostAliases.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToHostAliases(Collection items) {
      if (this.hostAliases == null) {
         this.hostAliases = new ArrayList();
      }

      for(HostAlias item : items) {
         HostAliasBuilder builder = new HostAliasBuilder(item);
         this._visitables.get("hostAliases").add(builder);
         this.hostAliases.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromHostAliases(HostAlias... items) {
      if (this.hostAliases == null) {
         return this;
      } else {
         for(HostAlias item : items) {
            HostAliasBuilder builder = new HostAliasBuilder(item);
            this._visitables.get("hostAliases").remove(builder);
            this.hostAliases.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromHostAliases(Collection items) {
      if (this.hostAliases == null) {
         return this;
      } else {
         for(HostAlias item : items) {
            HostAliasBuilder builder = new HostAliasBuilder(item);
            this._visitables.get("hostAliases").remove(builder);
            this.hostAliases.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromHostAliases(Predicate predicate) {
      if (this.hostAliases == null) {
         return this;
      } else {
         Iterator<HostAliasBuilder> each = this.hostAliases.iterator();
         List visitables = this._visitables.get("hostAliases");

         while(each.hasNext()) {
            HostAliasBuilder builder = (HostAliasBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildHostAliases() {
      return this.hostAliases != null ? build(this.hostAliases) : null;
   }

   public HostAlias buildHostAlias(int index) {
      return ((HostAliasBuilder)this.hostAliases.get(index)).build();
   }

   public HostAlias buildFirstHostAlias() {
      return ((HostAliasBuilder)this.hostAliases.get(0)).build();
   }

   public HostAlias buildLastHostAlias() {
      return ((HostAliasBuilder)this.hostAliases.get(this.hostAliases.size() - 1)).build();
   }

   public HostAlias buildMatchingHostAlias(Predicate predicate) {
      for(HostAliasBuilder item : this.hostAliases) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingHostAlias(Predicate predicate) {
      for(HostAliasBuilder item : this.hostAliases) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withHostAliases(List hostAliases) {
      if (this.hostAliases != null) {
         this._visitables.get("hostAliases").clear();
      }

      if (hostAliases != null) {
         this.hostAliases = new ArrayList();

         for(HostAlias item : hostAliases) {
            this.addToHostAliases(item);
         }
      } else {
         this.hostAliases = null;
      }

      return this;
   }

   public PodSpecFluent withHostAliases(HostAlias... hostAliases) {
      if (this.hostAliases != null) {
         this.hostAliases.clear();
         this._visitables.remove("hostAliases");
      }

      if (hostAliases != null) {
         for(HostAlias item : hostAliases) {
            this.addToHostAliases(item);
         }
      }

      return this;
   }

   public boolean hasHostAliases() {
      return this.hostAliases != null && !this.hostAliases.isEmpty();
   }

   public HostAliasesNested addNewHostAlias() {
      return new HostAliasesNested(-1, (HostAlias)null);
   }

   public HostAliasesNested addNewHostAliasLike(HostAlias item) {
      return new HostAliasesNested(-1, item);
   }

   public HostAliasesNested setNewHostAliasLike(int index, HostAlias item) {
      return new HostAliasesNested(index, item);
   }

   public HostAliasesNested editHostAlias(int index) {
      if (this.hostAliases.size() <= index) {
         throw new RuntimeException("Can't edit hostAliases. Index exceeds size.");
      } else {
         return this.setNewHostAliasLike(index, this.buildHostAlias(index));
      }
   }

   public HostAliasesNested editFirstHostAlias() {
      if (this.hostAliases.size() == 0) {
         throw new RuntimeException("Can't edit first hostAliases. The list is empty.");
      } else {
         return this.setNewHostAliasLike(0, this.buildHostAlias(0));
      }
   }

   public HostAliasesNested editLastHostAlias() {
      int index = this.hostAliases.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last hostAliases. The list is empty.");
      } else {
         return this.setNewHostAliasLike(index, this.buildHostAlias(index));
      }
   }

   public HostAliasesNested editMatchingHostAlias(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.hostAliases.size(); ++i) {
         if (predicate.test((HostAliasBuilder)this.hostAliases.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching hostAliases. No match found.");
      } else {
         return this.setNewHostAliasLike(index, this.buildHostAlias(index));
      }
   }

   public Boolean getHostIPC() {
      return this.hostIPC;
   }

   public PodSpecFluent withHostIPC(Boolean hostIPC) {
      this.hostIPC = hostIPC;
      return this;
   }

   public boolean hasHostIPC() {
      return this.hostIPC != null;
   }

   public Boolean getHostNetwork() {
      return this.hostNetwork;
   }

   public PodSpecFluent withHostNetwork(Boolean hostNetwork) {
      this.hostNetwork = hostNetwork;
      return this;
   }

   public boolean hasHostNetwork() {
      return this.hostNetwork != null;
   }

   public Boolean getHostPID() {
      return this.hostPID;
   }

   public PodSpecFluent withHostPID(Boolean hostPID) {
      this.hostPID = hostPID;
      return this;
   }

   public boolean hasHostPID() {
      return this.hostPID != null;
   }

   public Boolean getHostUsers() {
      return this.hostUsers;
   }

   public PodSpecFluent withHostUsers(Boolean hostUsers) {
      this.hostUsers = hostUsers;
      return this;
   }

   public boolean hasHostUsers() {
      return this.hostUsers != null;
   }

   public String getHostname() {
      return this.hostname;
   }

   public PodSpecFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public PodSpecFluent addToImagePullSecrets(int index, LocalObjectReference item) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
      if (index >= 0 && index < this.imagePullSecrets.size()) {
         this._visitables.get("imagePullSecrets").add(index, builder);
         this.imagePullSecrets.add(index, builder);
      } else {
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToImagePullSecrets(int index, LocalObjectReference item) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
      if (index >= 0 && index < this.imagePullSecrets.size()) {
         this._visitables.get("imagePullSecrets").set(index, builder);
         this.imagePullSecrets.set(index, builder);
      } else {
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToImagePullSecrets(LocalObjectReference... items) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      for(LocalObjectReference item : items) {
         LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToImagePullSecrets(Collection items) {
      if (this.imagePullSecrets == null) {
         this.imagePullSecrets = new ArrayList();
      }

      for(LocalObjectReference item : items) {
         LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
         this._visitables.get("imagePullSecrets").add(builder);
         this.imagePullSecrets.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromImagePullSecrets(LocalObjectReference... items) {
      if (this.imagePullSecrets == null) {
         return this;
      } else {
         for(LocalObjectReference item : items) {
            LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
            this._visitables.get("imagePullSecrets").remove(builder);
            this.imagePullSecrets.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromImagePullSecrets(Collection items) {
      if (this.imagePullSecrets == null) {
         return this;
      } else {
         for(LocalObjectReference item : items) {
            LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
            this._visitables.get("imagePullSecrets").remove(builder);
            this.imagePullSecrets.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromImagePullSecrets(Predicate predicate) {
      if (this.imagePullSecrets == null) {
         return this;
      } else {
         Iterator<LocalObjectReferenceBuilder> each = this.imagePullSecrets.iterator();
         List visitables = this._visitables.get("imagePullSecrets");

         while(each.hasNext()) {
            LocalObjectReferenceBuilder builder = (LocalObjectReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildImagePullSecrets() {
      return this.imagePullSecrets != null ? build(this.imagePullSecrets) : null;
   }

   public LocalObjectReference buildImagePullSecret(int index) {
      return ((LocalObjectReferenceBuilder)this.imagePullSecrets.get(index)).build();
   }

   public LocalObjectReference buildFirstImagePullSecret() {
      return ((LocalObjectReferenceBuilder)this.imagePullSecrets.get(0)).build();
   }

   public LocalObjectReference buildLastImagePullSecret() {
      return ((LocalObjectReferenceBuilder)this.imagePullSecrets.get(this.imagePullSecrets.size() - 1)).build();
   }

   public LocalObjectReference buildMatchingImagePullSecret(Predicate predicate) {
      for(LocalObjectReferenceBuilder item : this.imagePullSecrets) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingImagePullSecret(Predicate predicate) {
      for(LocalObjectReferenceBuilder item : this.imagePullSecrets) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withImagePullSecrets(List imagePullSecrets) {
      if (this.imagePullSecrets != null) {
         this._visitables.get("imagePullSecrets").clear();
      }

      if (imagePullSecrets != null) {
         this.imagePullSecrets = new ArrayList();

         for(LocalObjectReference item : imagePullSecrets) {
            this.addToImagePullSecrets(item);
         }
      } else {
         this.imagePullSecrets = null;
      }

      return this;
   }

   public PodSpecFluent withImagePullSecrets(LocalObjectReference... imagePullSecrets) {
      if (this.imagePullSecrets != null) {
         this.imagePullSecrets.clear();
         this._visitables.remove("imagePullSecrets");
      }

      if (imagePullSecrets != null) {
         for(LocalObjectReference item : imagePullSecrets) {
            this.addToImagePullSecrets(item);
         }
      }

      return this;
   }

   public boolean hasImagePullSecrets() {
      return this.imagePullSecrets != null && !this.imagePullSecrets.isEmpty();
   }

   public PodSpecFluent addNewImagePullSecret(String name) {
      return this.addToImagePullSecrets(new LocalObjectReference(name));
   }

   public ImagePullSecretsNested addNewImagePullSecret() {
      return new ImagePullSecretsNested(-1, (LocalObjectReference)null);
   }

   public ImagePullSecretsNested addNewImagePullSecretLike(LocalObjectReference item) {
      return new ImagePullSecretsNested(-1, item);
   }

   public ImagePullSecretsNested setNewImagePullSecretLike(int index, LocalObjectReference item) {
      return new ImagePullSecretsNested(index, item);
   }

   public ImagePullSecretsNested editImagePullSecret(int index) {
      if (this.imagePullSecrets.size() <= index) {
         throw new RuntimeException("Can't edit imagePullSecrets. Index exceeds size.");
      } else {
         return this.setNewImagePullSecretLike(index, this.buildImagePullSecret(index));
      }
   }

   public ImagePullSecretsNested editFirstImagePullSecret() {
      if (this.imagePullSecrets.size() == 0) {
         throw new RuntimeException("Can't edit first imagePullSecrets. The list is empty.");
      } else {
         return this.setNewImagePullSecretLike(0, this.buildImagePullSecret(0));
      }
   }

   public ImagePullSecretsNested editLastImagePullSecret() {
      int index = this.imagePullSecrets.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last imagePullSecrets. The list is empty.");
      } else {
         return this.setNewImagePullSecretLike(index, this.buildImagePullSecret(index));
      }
   }

   public ImagePullSecretsNested editMatchingImagePullSecret(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.imagePullSecrets.size(); ++i) {
         if (predicate.test((LocalObjectReferenceBuilder)this.imagePullSecrets.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching imagePullSecrets. No match found.");
      } else {
         return this.setNewImagePullSecretLike(index, this.buildImagePullSecret(index));
      }
   }

   public PodSpecFluent addToInitContainers(int index, Container item) {
      if (this.initContainers == null) {
         this.initContainers = new ArrayList();
      }

      ContainerBuilder builder = new ContainerBuilder(item);
      if (index >= 0 && index < this.initContainers.size()) {
         this._visitables.get("initContainers").add(index, builder);
         this.initContainers.add(index, builder);
      } else {
         this._visitables.get("initContainers").add(builder);
         this.initContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToInitContainers(int index, Container item) {
      if (this.initContainers == null) {
         this.initContainers = new ArrayList();
      }

      ContainerBuilder builder = new ContainerBuilder(item);
      if (index >= 0 && index < this.initContainers.size()) {
         this._visitables.get("initContainers").set(index, builder);
         this.initContainers.set(index, builder);
      } else {
         this._visitables.get("initContainers").add(builder);
         this.initContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToInitContainers(Container... items) {
      if (this.initContainers == null) {
         this.initContainers = new ArrayList();
      }

      for(Container item : items) {
         ContainerBuilder builder = new ContainerBuilder(item);
         this._visitables.get("initContainers").add(builder);
         this.initContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToInitContainers(Collection items) {
      if (this.initContainers == null) {
         this.initContainers = new ArrayList();
      }

      for(Container item : items) {
         ContainerBuilder builder = new ContainerBuilder(item);
         this._visitables.get("initContainers").add(builder);
         this.initContainers.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromInitContainers(Container... items) {
      if (this.initContainers == null) {
         return this;
      } else {
         for(Container item : items) {
            ContainerBuilder builder = new ContainerBuilder(item);
            this._visitables.get("initContainers").remove(builder);
            this.initContainers.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromInitContainers(Collection items) {
      if (this.initContainers == null) {
         return this;
      } else {
         for(Container item : items) {
            ContainerBuilder builder = new ContainerBuilder(item);
            this._visitables.get("initContainers").remove(builder);
            this.initContainers.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromInitContainers(Predicate predicate) {
      if (this.initContainers == null) {
         return this;
      } else {
         Iterator<ContainerBuilder> each = this.initContainers.iterator();
         List visitables = this._visitables.get("initContainers");

         while(each.hasNext()) {
            ContainerBuilder builder = (ContainerBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildInitContainers() {
      return this.initContainers != null ? build(this.initContainers) : null;
   }

   public Container buildInitContainer(int index) {
      return ((ContainerBuilder)this.initContainers.get(index)).build();
   }

   public Container buildFirstInitContainer() {
      return ((ContainerBuilder)this.initContainers.get(0)).build();
   }

   public Container buildLastInitContainer() {
      return ((ContainerBuilder)this.initContainers.get(this.initContainers.size() - 1)).build();
   }

   public Container buildMatchingInitContainer(Predicate predicate) {
      for(ContainerBuilder item : this.initContainers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingInitContainer(Predicate predicate) {
      for(ContainerBuilder item : this.initContainers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withInitContainers(List initContainers) {
      if (this.initContainers != null) {
         this._visitables.get("initContainers").clear();
      }

      if (initContainers != null) {
         this.initContainers = new ArrayList();

         for(Container item : initContainers) {
            this.addToInitContainers(item);
         }
      } else {
         this.initContainers = null;
      }

      return this;
   }

   public PodSpecFluent withInitContainers(Container... initContainers) {
      if (this.initContainers != null) {
         this.initContainers.clear();
         this._visitables.remove("initContainers");
      }

      if (initContainers != null) {
         for(Container item : initContainers) {
            this.addToInitContainers(item);
         }
      }

      return this;
   }

   public boolean hasInitContainers() {
      return this.initContainers != null && !this.initContainers.isEmpty();
   }

   public InitContainersNested addNewInitContainer() {
      return new InitContainersNested(-1, (Container)null);
   }

   public InitContainersNested addNewInitContainerLike(Container item) {
      return new InitContainersNested(-1, item);
   }

   public InitContainersNested setNewInitContainerLike(int index, Container item) {
      return new InitContainersNested(index, item);
   }

   public InitContainersNested editInitContainer(int index) {
      if (this.initContainers.size() <= index) {
         throw new RuntimeException("Can't edit initContainers. Index exceeds size.");
      } else {
         return this.setNewInitContainerLike(index, this.buildInitContainer(index));
      }
   }

   public InitContainersNested editFirstInitContainer() {
      if (this.initContainers.size() == 0) {
         throw new RuntimeException("Can't edit first initContainers. The list is empty.");
      } else {
         return this.setNewInitContainerLike(0, this.buildInitContainer(0));
      }
   }

   public InitContainersNested editLastInitContainer() {
      int index = this.initContainers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last initContainers. The list is empty.");
      } else {
         return this.setNewInitContainerLike(index, this.buildInitContainer(index));
      }
   }

   public InitContainersNested editMatchingInitContainer(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.initContainers.size(); ++i) {
         if (predicate.test((ContainerBuilder)this.initContainers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching initContainers. No match found.");
      } else {
         return this.setNewInitContainerLike(index, this.buildInitContainer(index));
      }
   }

   public String getNodeName() {
      return this.nodeName;
   }

   public PodSpecFluent withNodeName(String nodeName) {
      this.nodeName = nodeName;
      return this;
   }

   public boolean hasNodeName() {
      return this.nodeName != null;
   }

   public PodSpecFluent addToNodeSelector(String key, String value) {
      if (this.nodeSelector == null && key != null && value != null) {
         this.nodeSelector = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.nodeSelector.put(key, value);
      }

      return this;
   }

   public PodSpecFluent addToNodeSelector(Map map) {
      if (this.nodeSelector == null && map != null) {
         this.nodeSelector = new LinkedHashMap();
      }

      if (map != null) {
         this.nodeSelector.putAll(map);
      }

      return this;
   }

   public PodSpecFluent removeFromNodeSelector(String key) {
      if (this.nodeSelector == null) {
         return this;
      } else {
         if (key != null && this.nodeSelector != null) {
            this.nodeSelector.remove(key);
         }

         return this;
      }
   }

   public PodSpecFluent removeFromNodeSelector(Map map) {
      if (this.nodeSelector == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.nodeSelector != null) {
                  this.nodeSelector.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getNodeSelector() {
      return this.nodeSelector;
   }

   public PodSpecFluent withNodeSelector(Map nodeSelector) {
      if (nodeSelector == null) {
         this.nodeSelector = null;
      } else {
         this.nodeSelector = new LinkedHashMap(nodeSelector);
      }

      return this;
   }

   public boolean hasNodeSelector() {
      return this.nodeSelector != null;
   }

   public PodOS buildOs() {
      return this.os != null ? this.os.build() : null;
   }

   public PodSpecFluent withOs(PodOS os) {
      this._visitables.remove("os");
      if (os != null) {
         this.os = new PodOSBuilder(os);
         this._visitables.get("os").add(this.os);
      } else {
         this.os = null;
         this._visitables.get("os").remove(this.os);
      }

      return this;
   }

   public boolean hasOs() {
      return this.os != null;
   }

   public PodSpecFluent withNewOs(String name) {
      return this.withOs(new PodOS(name));
   }

   public OsNested withNewOs() {
      return new OsNested((PodOS)null);
   }

   public OsNested withNewOsLike(PodOS item) {
      return new OsNested(item);
   }

   public OsNested editOs() {
      return this.withNewOsLike((PodOS)Optional.ofNullable(this.buildOs()).orElse((Object)null));
   }

   public OsNested editOrNewOs() {
      return this.withNewOsLike((PodOS)Optional.ofNullable(this.buildOs()).orElse((new PodOSBuilder()).build()));
   }

   public OsNested editOrNewOsLike(PodOS item) {
      return this.withNewOsLike((PodOS)Optional.ofNullable(this.buildOs()).orElse(item));
   }

   public PodSpecFluent addToOverhead(String key, Quantity value) {
      if (this.overhead == null && key != null && value != null) {
         this.overhead = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.overhead.put(key, value);
      }

      return this;
   }

   public PodSpecFluent addToOverhead(Map map) {
      if (this.overhead == null && map != null) {
         this.overhead = new LinkedHashMap();
      }

      if (map != null) {
         this.overhead.putAll(map);
      }

      return this;
   }

   public PodSpecFluent removeFromOverhead(String key) {
      if (this.overhead == null) {
         return this;
      } else {
         if (key != null && this.overhead != null) {
            this.overhead.remove(key);
         }

         return this;
      }
   }

   public PodSpecFluent removeFromOverhead(Map map) {
      if (this.overhead == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.overhead != null) {
                  this.overhead.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getOverhead() {
      return this.overhead;
   }

   public PodSpecFluent withOverhead(Map overhead) {
      if (overhead == null) {
         this.overhead = null;
      } else {
         this.overhead = new LinkedHashMap(overhead);
      }

      return this;
   }

   public boolean hasOverhead() {
      return this.overhead != null;
   }

   public String getPreemptionPolicy() {
      return this.preemptionPolicy;
   }

   public PodSpecFluent withPreemptionPolicy(String preemptionPolicy) {
      this.preemptionPolicy = preemptionPolicy;
      return this;
   }

   public boolean hasPreemptionPolicy() {
      return this.preemptionPolicy != null;
   }

   public Integer getPriority() {
      return this.priority;
   }

   public PodSpecFluent withPriority(Integer priority) {
      this.priority = priority;
      return this;
   }

   public boolean hasPriority() {
      return this.priority != null;
   }

   public String getPriorityClassName() {
      return this.priorityClassName;
   }

   public PodSpecFluent withPriorityClassName(String priorityClassName) {
      this.priorityClassName = priorityClassName;
      return this;
   }

   public boolean hasPriorityClassName() {
      return this.priorityClassName != null;
   }

   public PodSpecFluent addToReadinessGates(int index, PodReadinessGate item) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      PodReadinessGateBuilder builder = new PodReadinessGateBuilder(item);
      if (index >= 0 && index < this.readinessGates.size()) {
         this._visitables.get("readinessGates").add(index, builder);
         this.readinessGates.add(index, builder);
      } else {
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToReadinessGates(int index, PodReadinessGate item) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      PodReadinessGateBuilder builder = new PodReadinessGateBuilder(item);
      if (index >= 0 && index < this.readinessGates.size()) {
         this._visitables.get("readinessGates").set(index, builder);
         this.readinessGates.set(index, builder);
      } else {
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToReadinessGates(PodReadinessGate... items) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      for(PodReadinessGate item : items) {
         PodReadinessGateBuilder builder = new PodReadinessGateBuilder(item);
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToReadinessGates(Collection items) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      for(PodReadinessGate item : items) {
         PodReadinessGateBuilder builder = new PodReadinessGateBuilder(item);
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromReadinessGates(PodReadinessGate... items) {
      if (this.readinessGates == null) {
         return this;
      } else {
         for(PodReadinessGate item : items) {
            PodReadinessGateBuilder builder = new PodReadinessGateBuilder(item);
            this._visitables.get("readinessGates").remove(builder);
            this.readinessGates.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromReadinessGates(Collection items) {
      if (this.readinessGates == null) {
         return this;
      } else {
         for(PodReadinessGate item : items) {
            PodReadinessGateBuilder builder = new PodReadinessGateBuilder(item);
            this._visitables.get("readinessGates").remove(builder);
            this.readinessGates.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromReadinessGates(Predicate predicate) {
      if (this.readinessGates == null) {
         return this;
      } else {
         Iterator<PodReadinessGateBuilder> each = this.readinessGates.iterator();
         List visitables = this._visitables.get("readinessGates");

         while(each.hasNext()) {
            PodReadinessGateBuilder builder = (PodReadinessGateBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildReadinessGates() {
      return this.readinessGates != null ? build(this.readinessGates) : null;
   }

   public PodReadinessGate buildReadinessGate(int index) {
      return ((PodReadinessGateBuilder)this.readinessGates.get(index)).build();
   }

   public PodReadinessGate buildFirstReadinessGate() {
      return ((PodReadinessGateBuilder)this.readinessGates.get(0)).build();
   }

   public PodReadinessGate buildLastReadinessGate() {
      return ((PodReadinessGateBuilder)this.readinessGates.get(this.readinessGates.size() - 1)).build();
   }

   public PodReadinessGate buildMatchingReadinessGate(Predicate predicate) {
      for(PodReadinessGateBuilder item : this.readinessGates) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingReadinessGate(Predicate predicate) {
      for(PodReadinessGateBuilder item : this.readinessGates) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withReadinessGates(List readinessGates) {
      if (this.readinessGates != null) {
         this._visitables.get("readinessGates").clear();
      }

      if (readinessGates != null) {
         this.readinessGates = new ArrayList();

         for(PodReadinessGate item : readinessGates) {
            this.addToReadinessGates(item);
         }
      } else {
         this.readinessGates = null;
      }

      return this;
   }

   public PodSpecFluent withReadinessGates(PodReadinessGate... readinessGates) {
      if (this.readinessGates != null) {
         this.readinessGates.clear();
         this._visitables.remove("readinessGates");
      }

      if (readinessGates != null) {
         for(PodReadinessGate item : readinessGates) {
            this.addToReadinessGates(item);
         }
      }

      return this;
   }

   public boolean hasReadinessGates() {
      return this.readinessGates != null && !this.readinessGates.isEmpty();
   }

   public PodSpecFluent addNewReadinessGate(String conditionType) {
      return this.addToReadinessGates(new PodReadinessGate(conditionType));
   }

   public ReadinessGatesNested addNewReadinessGate() {
      return new ReadinessGatesNested(-1, (PodReadinessGate)null);
   }

   public ReadinessGatesNested addNewReadinessGateLike(PodReadinessGate item) {
      return new ReadinessGatesNested(-1, item);
   }

   public ReadinessGatesNested setNewReadinessGateLike(int index, PodReadinessGate item) {
      return new ReadinessGatesNested(index, item);
   }

   public ReadinessGatesNested editReadinessGate(int index) {
      if (this.readinessGates.size() <= index) {
         throw new RuntimeException("Can't edit readinessGates. Index exceeds size.");
      } else {
         return this.setNewReadinessGateLike(index, this.buildReadinessGate(index));
      }
   }

   public ReadinessGatesNested editFirstReadinessGate() {
      if (this.readinessGates.size() == 0) {
         throw new RuntimeException("Can't edit first readinessGates. The list is empty.");
      } else {
         return this.setNewReadinessGateLike(0, this.buildReadinessGate(0));
      }
   }

   public ReadinessGatesNested editLastReadinessGate() {
      int index = this.readinessGates.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last readinessGates. The list is empty.");
      } else {
         return this.setNewReadinessGateLike(index, this.buildReadinessGate(index));
      }
   }

   public ReadinessGatesNested editMatchingReadinessGate(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.readinessGates.size(); ++i) {
         if (predicate.test((PodReadinessGateBuilder)this.readinessGates.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching readinessGates. No match found.");
      } else {
         return this.setNewReadinessGateLike(index, this.buildReadinessGate(index));
      }
   }

   public PodSpecFluent addToResourceClaims(int index, PodResourceClaim item) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      PodResourceClaimBuilder builder = new PodResourceClaimBuilder(item);
      if (index >= 0 && index < this.resourceClaims.size()) {
         this._visitables.get("resourceClaims").add(index, builder);
         this.resourceClaims.add(index, builder);
      } else {
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToResourceClaims(int index, PodResourceClaim item) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      PodResourceClaimBuilder builder = new PodResourceClaimBuilder(item);
      if (index >= 0 && index < this.resourceClaims.size()) {
         this._visitables.get("resourceClaims").set(index, builder);
         this.resourceClaims.set(index, builder);
      } else {
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToResourceClaims(PodResourceClaim... items) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      for(PodResourceClaim item : items) {
         PodResourceClaimBuilder builder = new PodResourceClaimBuilder(item);
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToResourceClaims(Collection items) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      for(PodResourceClaim item : items) {
         PodResourceClaimBuilder builder = new PodResourceClaimBuilder(item);
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromResourceClaims(PodResourceClaim... items) {
      if (this.resourceClaims == null) {
         return this;
      } else {
         for(PodResourceClaim item : items) {
            PodResourceClaimBuilder builder = new PodResourceClaimBuilder(item);
            this._visitables.get("resourceClaims").remove(builder);
            this.resourceClaims.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromResourceClaims(Collection items) {
      if (this.resourceClaims == null) {
         return this;
      } else {
         for(PodResourceClaim item : items) {
            PodResourceClaimBuilder builder = new PodResourceClaimBuilder(item);
            this._visitables.get("resourceClaims").remove(builder);
            this.resourceClaims.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromResourceClaims(Predicate predicate) {
      if (this.resourceClaims == null) {
         return this;
      } else {
         Iterator<PodResourceClaimBuilder> each = this.resourceClaims.iterator();
         List visitables = this._visitables.get("resourceClaims");

         while(each.hasNext()) {
            PodResourceClaimBuilder builder = (PodResourceClaimBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResourceClaims() {
      return this.resourceClaims != null ? build(this.resourceClaims) : null;
   }

   public PodResourceClaim buildResourceClaim(int index) {
      return ((PodResourceClaimBuilder)this.resourceClaims.get(index)).build();
   }

   public PodResourceClaim buildFirstResourceClaim() {
      return ((PodResourceClaimBuilder)this.resourceClaims.get(0)).build();
   }

   public PodResourceClaim buildLastResourceClaim() {
      return ((PodResourceClaimBuilder)this.resourceClaims.get(this.resourceClaims.size() - 1)).build();
   }

   public PodResourceClaim buildMatchingResourceClaim(Predicate predicate) {
      for(PodResourceClaimBuilder item : this.resourceClaims) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResourceClaim(Predicate predicate) {
      for(PodResourceClaimBuilder item : this.resourceClaims) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withResourceClaims(List resourceClaims) {
      if (this.resourceClaims != null) {
         this._visitables.get("resourceClaims").clear();
      }

      if (resourceClaims != null) {
         this.resourceClaims = new ArrayList();

         for(PodResourceClaim item : resourceClaims) {
            this.addToResourceClaims(item);
         }
      } else {
         this.resourceClaims = null;
      }

      return this;
   }

   public PodSpecFluent withResourceClaims(PodResourceClaim... resourceClaims) {
      if (this.resourceClaims != null) {
         this.resourceClaims.clear();
         this._visitables.remove("resourceClaims");
      }

      if (resourceClaims != null) {
         for(PodResourceClaim item : resourceClaims) {
            this.addToResourceClaims(item);
         }
      }

      return this;
   }

   public boolean hasResourceClaims() {
      return this.resourceClaims != null && !this.resourceClaims.isEmpty();
   }

   public PodSpecFluent addNewResourceClaim(String name, String resourceClaimName, String resourceClaimTemplateName) {
      return this.addToResourceClaims(new PodResourceClaim(name, resourceClaimName, resourceClaimTemplateName));
   }

   public ResourceClaimsNested addNewResourceClaim() {
      return new ResourceClaimsNested(-1, (PodResourceClaim)null);
   }

   public ResourceClaimsNested addNewResourceClaimLike(PodResourceClaim item) {
      return new ResourceClaimsNested(-1, item);
   }

   public ResourceClaimsNested setNewResourceClaimLike(int index, PodResourceClaim item) {
      return new ResourceClaimsNested(index, item);
   }

   public ResourceClaimsNested editResourceClaim(int index) {
      if (this.resourceClaims.size() <= index) {
         throw new RuntimeException("Can't edit resourceClaims. Index exceeds size.");
      } else {
         return this.setNewResourceClaimLike(index, this.buildResourceClaim(index));
      }
   }

   public ResourceClaimsNested editFirstResourceClaim() {
      if (this.resourceClaims.size() == 0) {
         throw new RuntimeException("Can't edit first resourceClaims. The list is empty.");
      } else {
         return this.setNewResourceClaimLike(0, this.buildResourceClaim(0));
      }
   }

   public ResourceClaimsNested editLastResourceClaim() {
      int index = this.resourceClaims.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last resourceClaims. The list is empty.");
      } else {
         return this.setNewResourceClaimLike(index, this.buildResourceClaim(index));
      }
   }

   public ResourceClaimsNested editMatchingResourceClaim(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.resourceClaims.size(); ++i) {
         if (predicate.test((PodResourceClaimBuilder)this.resourceClaims.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching resourceClaims. No match found.");
      } else {
         return this.setNewResourceClaimLike(index, this.buildResourceClaim(index));
      }
   }

   public ResourceRequirements buildResources() {
      return this.resources != null ? this.resources.build() : null;
   }

   public PodSpecFluent withResources(ResourceRequirements resources) {
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

   public PodSpecFluent withRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
      return this;
   }

   public boolean hasRestartPolicy() {
      return this.restartPolicy != null;
   }

   public String getRuntimeClassName() {
      return this.runtimeClassName;
   }

   public PodSpecFluent withRuntimeClassName(String runtimeClassName) {
      this.runtimeClassName = runtimeClassName;
      return this;
   }

   public boolean hasRuntimeClassName() {
      return this.runtimeClassName != null;
   }

   public String getSchedulerName() {
      return this.schedulerName;
   }

   public PodSpecFluent withSchedulerName(String schedulerName) {
      this.schedulerName = schedulerName;
      return this;
   }

   public boolean hasSchedulerName() {
      return this.schedulerName != null;
   }

   public PodSpecFluent addToSchedulingGates(int index, PodSchedulingGate item) {
      if (this.schedulingGates == null) {
         this.schedulingGates = new ArrayList();
      }

      PodSchedulingGateBuilder builder = new PodSchedulingGateBuilder(item);
      if (index >= 0 && index < this.schedulingGates.size()) {
         this._visitables.get("schedulingGates").add(index, builder);
         this.schedulingGates.add(index, builder);
      } else {
         this._visitables.get("schedulingGates").add(builder);
         this.schedulingGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToSchedulingGates(int index, PodSchedulingGate item) {
      if (this.schedulingGates == null) {
         this.schedulingGates = new ArrayList();
      }

      PodSchedulingGateBuilder builder = new PodSchedulingGateBuilder(item);
      if (index >= 0 && index < this.schedulingGates.size()) {
         this._visitables.get("schedulingGates").set(index, builder);
         this.schedulingGates.set(index, builder);
      } else {
         this._visitables.get("schedulingGates").add(builder);
         this.schedulingGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToSchedulingGates(PodSchedulingGate... items) {
      if (this.schedulingGates == null) {
         this.schedulingGates = new ArrayList();
      }

      for(PodSchedulingGate item : items) {
         PodSchedulingGateBuilder builder = new PodSchedulingGateBuilder(item);
         this._visitables.get("schedulingGates").add(builder);
         this.schedulingGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToSchedulingGates(Collection items) {
      if (this.schedulingGates == null) {
         this.schedulingGates = new ArrayList();
      }

      for(PodSchedulingGate item : items) {
         PodSchedulingGateBuilder builder = new PodSchedulingGateBuilder(item);
         this._visitables.get("schedulingGates").add(builder);
         this.schedulingGates.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromSchedulingGates(PodSchedulingGate... items) {
      if (this.schedulingGates == null) {
         return this;
      } else {
         for(PodSchedulingGate item : items) {
            PodSchedulingGateBuilder builder = new PodSchedulingGateBuilder(item);
            this._visitables.get("schedulingGates").remove(builder);
            this.schedulingGates.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromSchedulingGates(Collection items) {
      if (this.schedulingGates == null) {
         return this;
      } else {
         for(PodSchedulingGate item : items) {
            PodSchedulingGateBuilder builder = new PodSchedulingGateBuilder(item);
            this._visitables.get("schedulingGates").remove(builder);
            this.schedulingGates.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromSchedulingGates(Predicate predicate) {
      if (this.schedulingGates == null) {
         return this;
      } else {
         Iterator<PodSchedulingGateBuilder> each = this.schedulingGates.iterator();
         List visitables = this._visitables.get("schedulingGates");

         while(each.hasNext()) {
            PodSchedulingGateBuilder builder = (PodSchedulingGateBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSchedulingGates() {
      return this.schedulingGates != null ? build(this.schedulingGates) : null;
   }

   public PodSchedulingGate buildSchedulingGate(int index) {
      return ((PodSchedulingGateBuilder)this.schedulingGates.get(index)).build();
   }

   public PodSchedulingGate buildFirstSchedulingGate() {
      return ((PodSchedulingGateBuilder)this.schedulingGates.get(0)).build();
   }

   public PodSchedulingGate buildLastSchedulingGate() {
      return ((PodSchedulingGateBuilder)this.schedulingGates.get(this.schedulingGates.size() - 1)).build();
   }

   public PodSchedulingGate buildMatchingSchedulingGate(Predicate predicate) {
      for(PodSchedulingGateBuilder item : this.schedulingGates) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSchedulingGate(Predicate predicate) {
      for(PodSchedulingGateBuilder item : this.schedulingGates) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withSchedulingGates(List schedulingGates) {
      if (this.schedulingGates != null) {
         this._visitables.get("schedulingGates").clear();
      }

      if (schedulingGates != null) {
         this.schedulingGates = new ArrayList();

         for(PodSchedulingGate item : schedulingGates) {
            this.addToSchedulingGates(item);
         }
      } else {
         this.schedulingGates = null;
      }

      return this;
   }

   public PodSpecFluent withSchedulingGates(PodSchedulingGate... schedulingGates) {
      if (this.schedulingGates != null) {
         this.schedulingGates.clear();
         this._visitables.remove("schedulingGates");
      }

      if (schedulingGates != null) {
         for(PodSchedulingGate item : schedulingGates) {
            this.addToSchedulingGates(item);
         }
      }

      return this;
   }

   public boolean hasSchedulingGates() {
      return this.schedulingGates != null && !this.schedulingGates.isEmpty();
   }

   public PodSpecFluent addNewSchedulingGate(String name) {
      return this.addToSchedulingGates(new PodSchedulingGate(name));
   }

   public SchedulingGatesNested addNewSchedulingGate() {
      return new SchedulingGatesNested(-1, (PodSchedulingGate)null);
   }

   public SchedulingGatesNested addNewSchedulingGateLike(PodSchedulingGate item) {
      return new SchedulingGatesNested(-1, item);
   }

   public SchedulingGatesNested setNewSchedulingGateLike(int index, PodSchedulingGate item) {
      return new SchedulingGatesNested(index, item);
   }

   public SchedulingGatesNested editSchedulingGate(int index) {
      if (this.schedulingGates.size() <= index) {
         throw new RuntimeException("Can't edit schedulingGates. Index exceeds size.");
      } else {
         return this.setNewSchedulingGateLike(index, this.buildSchedulingGate(index));
      }
   }

   public SchedulingGatesNested editFirstSchedulingGate() {
      if (this.schedulingGates.size() == 0) {
         throw new RuntimeException("Can't edit first schedulingGates. The list is empty.");
      } else {
         return this.setNewSchedulingGateLike(0, this.buildSchedulingGate(0));
      }
   }

   public SchedulingGatesNested editLastSchedulingGate() {
      int index = this.schedulingGates.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last schedulingGates. The list is empty.");
      } else {
         return this.setNewSchedulingGateLike(index, this.buildSchedulingGate(index));
      }
   }

   public SchedulingGatesNested editMatchingSchedulingGate(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.schedulingGates.size(); ++i) {
         if (predicate.test((PodSchedulingGateBuilder)this.schedulingGates.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching schedulingGates. No match found.");
      } else {
         return this.setNewSchedulingGateLike(index, this.buildSchedulingGate(index));
      }
   }

   public PodSecurityContext buildSecurityContext() {
      return this.securityContext != null ? this.securityContext.build() : null;
   }

   public PodSpecFluent withSecurityContext(PodSecurityContext securityContext) {
      this._visitables.remove("securityContext");
      if (securityContext != null) {
         this.securityContext = new PodSecurityContextBuilder(securityContext);
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
      return new SecurityContextNested((PodSecurityContext)null);
   }

   public SecurityContextNested withNewSecurityContextLike(PodSecurityContext item) {
      return new SecurityContextNested(item);
   }

   public SecurityContextNested editSecurityContext() {
      return this.withNewSecurityContextLike((PodSecurityContext)Optional.ofNullable(this.buildSecurityContext()).orElse((Object)null));
   }

   public SecurityContextNested editOrNewSecurityContext() {
      return this.withNewSecurityContextLike((PodSecurityContext)Optional.ofNullable(this.buildSecurityContext()).orElse((new PodSecurityContextBuilder()).build()));
   }

   public SecurityContextNested editOrNewSecurityContextLike(PodSecurityContext item) {
      return this.withNewSecurityContextLike((PodSecurityContext)Optional.ofNullable(this.buildSecurityContext()).orElse(item));
   }

   public String getServiceAccount() {
      return this.serviceAccount;
   }

   public PodSpecFluent withServiceAccount(String serviceAccount) {
      this.serviceAccount = serviceAccount;
      return this;
   }

   public boolean hasServiceAccount() {
      return this.serviceAccount != null;
   }

   public String getServiceAccountName() {
      return this.serviceAccountName;
   }

   public PodSpecFluent withServiceAccountName(String serviceAccountName) {
      this.serviceAccountName = serviceAccountName;
      return this;
   }

   public boolean hasServiceAccountName() {
      return this.serviceAccountName != null;
   }

   public Boolean getSetHostnameAsFQDN() {
      return this.setHostnameAsFQDN;
   }

   public PodSpecFluent withSetHostnameAsFQDN(Boolean setHostnameAsFQDN) {
      this.setHostnameAsFQDN = setHostnameAsFQDN;
      return this;
   }

   public boolean hasSetHostnameAsFQDN() {
      return this.setHostnameAsFQDN != null;
   }

   public Boolean getShareProcessNamespace() {
      return this.shareProcessNamespace;
   }

   public PodSpecFluent withShareProcessNamespace(Boolean shareProcessNamespace) {
      this.shareProcessNamespace = shareProcessNamespace;
      return this;
   }

   public boolean hasShareProcessNamespace() {
      return this.shareProcessNamespace != null;
   }

   public String getSubdomain() {
      return this.subdomain;
   }

   public PodSpecFluent withSubdomain(String subdomain) {
      this.subdomain = subdomain;
      return this;
   }

   public boolean hasSubdomain() {
      return this.subdomain != null;
   }

   public Long getTerminationGracePeriodSeconds() {
      return this.terminationGracePeriodSeconds;
   }

   public PodSpecFluent withTerminationGracePeriodSeconds(Long terminationGracePeriodSeconds) {
      this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
      return this;
   }

   public boolean hasTerminationGracePeriodSeconds() {
      return this.terminationGracePeriodSeconds != null;
   }

   public PodSpecFluent addToTolerations(int index, Toleration item) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      TolerationBuilder builder = new TolerationBuilder(item);
      if (index >= 0 && index < this.tolerations.size()) {
         this._visitables.get("tolerations").add(index, builder);
         this.tolerations.add(index, builder);
      } else {
         this._visitables.get("tolerations").add(builder);
         this.tolerations.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToTolerations(int index, Toleration item) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      TolerationBuilder builder = new TolerationBuilder(item);
      if (index >= 0 && index < this.tolerations.size()) {
         this._visitables.get("tolerations").set(index, builder);
         this.tolerations.set(index, builder);
      } else {
         this._visitables.get("tolerations").add(builder);
         this.tolerations.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToTolerations(Toleration... items) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      for(Toleration item : items) {
         TolerationBuilder builder = new TolerationBuilder(item);
         this._visitables.get("tolerations").add(builder);
         this.tolerations.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToTolerations(Collection items) {
      if (this.tolerations == null) {
         this.tolerations = new ArrayList();
      }

      for(Toleration item : items) {
         TolerationBuilder builder = new TolerationBuilder(item);
         this._visitables.get("tolerations").add(builder);
         this.tolerations.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromTolerations(Toleration... items) {
      if (this.tolerations == null) {
         return this;
      } else {
         for(Toleration item : items) {
            TolerationBuilder builder = new TolerationBuilder(item);
            this._visitables.get("tolerations").remove(builder);
            this.tolerations.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromTolerations(Collection items) {
      if (this.tolerations == null) {
         return this;
      } else {
         for(Toleration item : items) {
            TolerationBuilder builder = new TolerationBuilder(item);
            this._visitables.get("tolerations").remove(builder);
            this.tolerations.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromTolerations(Predicate predicate) {
      if (this.tolerations == null) {
         return this;
      } else {
         Iterator<TolerationBuilder> each = this.tolerations.iterator();
         List visitables = this._visitables.get("tolerations");

         while(each.hasNext()) {
            TolerationBuilder builder = (TolerationBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTolerations() {
      return this.tolerations != null ? build(this.tolerations) : null;
   }

   public Toleration buildToleration(int index) {
      return ((TolerationBuilder)this.tolerations.get(index)).build();
   }

   public Toleration buildFirstToleration() {
      return ((TolerationBuilder)this.tolerations.get(0)).build();
   }

   public Toleration buildLastToleration() {
      return ((TolerationBuilder)this.tolerations.get(this.tolerations.size() - 1)).build();
   }

   public Toleration buildMatchingToleration(Predicate predicate) {
      for(TolerationBuilder item : this.tolerations) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingToleration(Predicate predicate) {
      for(TolerationBuilder item : this.tolerations) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withTolerations(List tolerations) {
      if (this.tolerations != null) {
         this._visitables.get("tolerations").clear();
      }

      if (tolerations != null) {
         this.tolerations = new ArrayList();

         for(Toleration item : tolerations) {
            this.addToTolerations(item);
         }
      } else {
         this.tolerations = null;
      }

      return this;
   }

   public PodSpecFluent withTolerations(Toleration... tolerations) {
      if (this.tolerations != null) {
         this.tolerations.clear();
         this._visitables.remove("tolerations");
      }

      if (tolerations != null) {
         for(Toleration item : tolerations) {
            this.addToTolerations(item);
         }
      }

      return this;
   }

   public boolean hasTolerations() {
      return this.tolerations != null && !this.tolerations.isEmpty();
   }

   public PodSpecFluent addNewToleration(String effect, String key, String operator, Long tolerationSeconds, String value) {
      return this.addToTolerations(new Toleration(effect, key, operator, tolerationSeconds, value));
   }

   public TolerationsNested addNewToleration() {
      return new TolerationsNested(-1, (Toleration)null);
   }

   public TolerationsNested addNewTolerationLike(Toleration item) {
      return new TolerationsNested(-1, item);
   }

   public TolerationsNested setNewTolerationLike(int index, Toleration item) {
      return new TolerationsNested(index, item);
   }

   public TolerationsNested editToleration(int index) {
      if (this.tolerations.size() <= index) {
         throw new RuntimeException("Can't edit tolerations. Index exceeds size.");
      } else {
         return this.setNewTolerationLike(index, this.buildToleration(index));
      }
   }

   public TolerationsNested editFirstToleration() {
      if (this.tolerations.size() == 0) {
         throw new RuntimeException("Can't edit first tolerations. The list is empty.");
      } else {
         return this.setNewTolerationLike(0, this.buildToleration(0));
      }
   }

   public TolerationsNested editLastToleration() {
      int index = this.tolerations.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last tolerations. The list is empty.");
      } else {
         return this.setNewTolerationLike(index, this.buildToleration(index));
      }
   }

   public TolerationsNested editMatchingToleration(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.tolerations.size(); ++i) {
         if (predicate.test((TolerationBuilder)this.tolerations.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching tolerations. No match found.");
      } else {
         return this.setNewTolerationLike(index, this.buildToleration(index));
      }
   }

   public PodSpecFluent addToTopologySpreadConstraints(int index, TopologySpreadConstraint item) {
      if (this.topologySpreadConstraints == null) {
         this.topologySpreadConstraints = new ArrayList();
      }

      TopologySpreadConstraintBuilder builder = new TopologySpreadConstraintBuilder(item);
      if (index >= 0 && index < this.topologySpreadConstraints.size()) {
         this._visitables.get("topologySpreadConstraints").add(index, builder);
         this.topologySpreadConstraints.add(index, builder);
      } else {
         this._visitables.get("topologySpreadConstraints").add(builder);
         this.topologySpreadConstraints.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToTopologySpreadConstraints(int index, TopologySpreadConstraint item) {
      if (this.topologySpreadConstraints == null) {
         this.topologySpreadConstraints = new ArrayList();
      }

      TopologySpreadConstraintBuilder builder = new TopologySpreadConstraintBuilder(item);
      if (index >= 0 && index < this.topologySpreadConstraints.size()) {
         this._visitables.get("topologySpreadConstraints").set(index, builder);
         this.topologySpreadConstraints.set(index, builder);
      } else {
         this._visitables.get("topologySpreadConstraints").add(builder);
         this.topologySpreadConstraints.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToTopologySpreadConstraints(TopologySpreadConstraint... items) {
      if (this.topologySpreadConstraints == null) {
         this.topologySpreadConstraints = new ArrayList();
      }

      for(TopologySpreadConstraint item : items) {
         TopologySpreadConstraintBuilder builder = new TopologySpreadConstraintBuilder(item);
         this._visitables.get("topologySpreadConstraints").add(builder);
         this.topologySpreadConstraints.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToTopologySpreadConstraints(Collection items) {
      if (this.topologySpreadConstraints == null) {
         this.topologySpreadConstraints = new ArrayList();
      }

      for(TopologySpreadConstraint item : items) {
         TopologySpreadConstraintBuilder builder = new TopologySpreadConstraintBuilder(item);
         this._visitables.get("topologySpreadConstraints").add(builder);
         this.topologySpreadConstraints.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromTopologySpreadConstraints(TopologySpreadConstraint... items) {
      if (this.topologySpreadConstraints == null) {
         return this;
      } else {
         for(TopologySpreadConstraint item : items) {
            TopologySpreadConstraintBuilder builder = new TopologySpreadConstraintBuilder(item);
            this._visitables.get("topologySpreadConstraints").remove(builder);
            this.topologySpreadConstraints.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromTopologySpreadConstraints(Collection items) {
      if (this.topologySpreadConstraints == null) {
         return this;
      } else {
         for(TopologySpreadConstraint item : items) {
            TopologySpreadConstraintBuilder builder = new TopologySpreadConstraintBuilder(item);
            this._visitables.get("topologySpreadConstraints").remove(builder);
            this.topologySpreadConstraints.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromTopologySpreadConstraints(Predicate predicate) {
      if (this.topologySpreadConstraints == null) {
         return this;
      } else {
         Iterator<TopologySpreadConstraintBuilder> each = this.topologySpreadConstraints.iterator();
         List visitables = this._visitables.get("topologySpreadConstraints");

         while(each.hasNext()) {
            TopologySpreadConstraintBuilder builder = (TopologySpreadConstraintBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTopologySpreadConstraints() {
      return this.topologySpreadConstraints != null ? build(this.topologySpreadConstraints) : null;
   }

   public TopologySpreadConstraint buildTopologySpreadConstraint(int index) {
      return ((TopologySpreadConstraintBuilder)this.topologySpreadConstraints.get(index)).build();
   }

   public TopologySpreadConstraint buildFirstTopologySpreadConstraint() {
      return ((TopologySpreadConstraintBuilder)this.topologySpreadConstraints.get(0)).build();
   }

   public TopologySpreadConstraint buildLastTopologySpreadConstraint() {
      return ((TopologySpreadConstraintBuilder)this.topologySpreadConstraints.get(this.topologySpreadConstraints.size() - 1)).build();
   }

   public TopologySpreadConstraint buildMatchingTopologySpreadConstraint(Predicate predicate) {
      for(TopologySpreadConstraintBuilder item : this.topologySpreadConstraints) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTopologySpreadConstraint(Predicate predicate) {
      for(TopologySpreadConstraintBuilder item : this.topologySpreadConstraints) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withTopologySpreadConstraints(List topologySpreadConstraints) {
      if (this.topologySpreadConstraints != null) {
         this._visitables.get("topologySpreadConstraints").clear();
      }

      if (topologySpreadConstraints != null) {
         this.topologySpreadConstraints = new ArrayList();

         for(TopologySpreadConstraint item : topologySpreadConstraints) {
            this.addToTopologySpreadConstraints(item);
         }
      } else {
         this.topologySpreadConstraints = null;
      }

      return this;
   }

   public PodSpecFluent withTopologySpreadConstraints(TopologySpreadConstraint... topologySpreadConstraints) {
      if (this.topologySpreadConstraints != null) {
         this.topologySpreadConstraints.clear();
         this._visitables.remove("topologySpreadConstraints");
      }

      if (topologySpreadConstraints != null) {
         for(TopologySpreadConstraint item : topologySpreadConstraints) {
            this.addToTopologySpreadConstraints(item);
         }
      }

      return this;
   }

   public boolean hasTopologySpreadConstraints() {
      return this.topologySpreadConstraints != null && !this.topologySpreadConstraints.isEmpty();
   }

   public TopologySpreadConstraintsNested addNewTopologySpreadConstraint() {
      return new TopologySpreadConstraintsNested(-1, (TopologySpreadConstraint)null);
   }

   public TopologySpreadConstraintsNested addNewTopologySpreadConstraintLike(TopologySpreadConstraint item) {
      return new TopologySpreadConstraintsNested(-1, item);
   }

   public TopologySpreadConstraintsNested setNewTopologySpreadConstraintLike(int index, TopologySpreadConstraint item) {
      return new TopologySpreadConstraintsNested(index, item);
   }

   public TopologySpreadConstraintsNested editTopologySpreadConstraint(int index) {
      if (this.topologySpreadConstraints.size() <= index) {
         throw new RuntimeException("Can't edit topologySpreadConstraints. Index exceeds size.");
      } else {
         return this.setNewTopologySpreadConstraintLike(index, this.buildTopologySpreadConstraint(index));
      }
   }

   public TopologySpreadConstraintsNested editFirstTopologySpreadConstraint() {
      if (this.topologySpreadConstraints.size() == 0) {
         throw new RuntimeException("Can't edit first topologySpreadConstraints. The list is empty.");
      } else {
         return this.setNewTopologySpreadConstraintLike(0, this.buildTopologySpreadConstraint(0));
      }
   }

   public TopologySpreadConstraintsNested editLastTopologySpreadConstraint() {
      int index = this.topologySpreadConstraints.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last topologySpreadConstraints. The list is empty.");
      } else {
         return this.setNewTopologySpreadConstraintLike(index, this.buildTopologySpreadConstraint(index));
      }
   }

   public TopologySpreadConstraintsNested editMatchingTopologySpreadConstraint(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.topologySpreadConstraints.size(); ++i) {
         if (predicate.test((TopologySpreadConstraintBuilder)this.topologySpreadConstraints.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching topologySpreadConstraints. No match found.");
      } else {
         return this.setNewTopologySpreadConstraintLike(index, this.buildTopologySpreadConstraint(index));
      }
   }

   public PodSpecFluent addToVolumes(int index, Volume item) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      VolumeBuilder builder = new VolumeBuilder(item);
      if (index >= 0 && index < this.volumes.size()) {
         this._visitables.get("volumes").add(index, builder);
         this.volumes.add(index, builder);
      } else {
         this._visitables.get("volumes").add(builder);
         this.volumes.add(builder);
      }

      return this;
   }

   public PodSpecFluent setToVolumes(int index, Volume item) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      VolumeBuilder builder = new VolumeBuilder(item);
      if (index >= 0 && index < this.volumes.size()) {
         this._visitables.get("volumes").set(index, builder);
         this.volumes.set(index, builder);
      } else {
         this._visitables.get("volumes").add(builder);
         this.volumes.add(builder);
      }

      return this;
   }

   public PodSpecFluent addToVolumes(Volume... items) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      for(Volume item : items) {
         VolumeBuilder builder = new VolumeBuilder(item);
         this._visitables.get("volumes").add(builder);
         this.volumes.add(builder);
      }

      return this;
   }

   public PodSpecFluent addAllToVolumes(Collection items) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      for(Volume item : items) {
         VolumeBuilder builder = new VolumeBuilder(item);
         this._visitables.get("volumes").add(builder);
         this.volumes.add(builder);
      }

      return this;
   }

   public PodSpecFluent removeFromVolumes(Volume... items) {
      if (this.volumes == null) {
         return this;
      } else {
         for(Volume item : items) {
            VolumeBuilder builder = new VolumeBuilder(item);
            this._visitables.get("volumes").remove(builder);
            this.volumes.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeAllFromVolumes(Collection items) {
      if (this.volumes == null) {
         return this;
      } else {
         for(Volume item : items) {
            VolumeBuilder builder = new VolumeBuilder(item);
            this._visitables.get("volumes").remove(builder);
            this.volumes.remove(builder);
         }

         return this;
      }
   }

   public PodSpecFluent removeMatchingFromVolumes(Predicate predicate) {
      if (this.volumes == null) {
         return this;
      } else {
         Iterator<VolumeBuilder> each = this.volumes.iterator();
         List visitables = this._visitables.get("volumes");

         while(each.hasNext()) {
            VolumeBuilder builder = (VolumeBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVolumes() {
      return this.volumes != null ? build(this.volumes) : null;
   }

   public Volume buildVolume(int index) {
      return ((VolumeBuilder)this.volumes.get(index)).build();
   }

   public Volume buildFirstVolume() {
      return ((VolumeBuilder)this.volumes.get(0)).build();
   }

   public Volume buildLastVolume() {
      return ((VolumeBuilder)this.volumes.get(this.volumes.size() - 1)).build();
   }

   public Volume buildMatchingVolume(Predicate predicate) {
      for(VolumeBuilder item : this.volumes) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVolume(Predicate predicate) {
      for(VolumeBuilder item : this.volumes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSpecFluent withVolumes(List volumes) {
      if (this.volumes != null) {
         this._visitables.get("volumes").clear();
      }

      if (volumes != null) {
         this.volumes = new ArrayList();

         for(Volume item : volumes) {
            this.addToVolumes(item);
         }
      } else {
         this.volumes = null;
      }

      return this;
   }

   public PodSpecFluent withVolumes(Volume... volumes) {
      if (this.volumes != null) {
         this.volumes.clear();
         this._visitables.remove("volumes");
      }

      if (volumes != null) {
         for(Volume item : volumes) {
            this.addToVolumes(item);
         }
      }

      return this;
   }

   public boolean hasVolumes() {
      return this.volumes != null && !this.volumes.isEmpty();
   }

   public VolumesNested addNewVolume() {
      return new VolumesNested(-1, (Volume)null);
   }

   public VolumesNested addNewVolumeLike(Volume item) {
      return new VolumesNested(-1, item);
   }

   public VolumesNested setNewVolumeLike(int index, Volume item) {
      return new VolumesNested(index, item);
   }

   public VolumesNested editVolume(int index) {
      if (this.volumes.size() <= index) {
         throw new RuntimeException("Can't edit volumes. Index exceeds size.");
      } else {
         return this.setNewVolumeLike(index, this.buildVolume(index));
      }
   }

   public VolumesNested editFirstVolume() {
      if (this.volumes.size() == 0) {
         throw new RuntimeException("Can't edit first volumes. The list is empty.");
      } else {
         return this.setNewVolumeLike(0, this.buildVolume(0));
      }
   }

   public VolumesNested editLastVolume() {
      int index = this.volumes.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last volumes. The list is empty.");
      } else {
         return this.setNewVolumeLike(index, this.buildVolume(index));
      }
   }

   public VolumesNested editMatchingVolume(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.volumes.size(); ++i) {
         if (predicate.test((VolumeBuilder)this.volumes.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching volumes. No match found.");
      } else {
         return this.setNewVolumeLike(index, this.buildVolume(index));
      }
   }

   public PodSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodSpecFluent removeFromAdditionalProperties(Map map) {
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

   public PodSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            PodSpecFluent that = (PodSpecFluent)o;
            if (!Objects.equals(this.activeDeadlineSeconds, that.activeDeadlineSeconds)) {
               return false;
            } else if (!Objects.equals(this.affinity, that.affinity)) {
               return false;
            } else if (!Objects.equals(this.automountServiceAccountToken, that.automountServiceAccountToken)) {
               return false;
            } else if (!Objects.equals(this.containers, that.containers)) {
               return false;
            } else if (!Objects.equals(this.dnsConfig, that.dnsConfig)) {
               return false;
            } else if (!Objects.equals(this.dnsPolicy, that.dnsPolicy)) {
               return false;
            } else if (!Objects.equals(this.enableServiceLinks, that.enableServiceLinks)) {
               return false;
            } else if (!Objects.equals(this.ephemeralContainers, that.ephemeralContainers)) {
               return false;
            } else if (!Objects.equals(this.hostAliases, that.hostAliases)) {
               return false;
            } else if (!Objects.equals(this.hostIPC, that.hostIPC)) {
               return false;
            } else if (!Objects.equals(this.hostNetwork, that.hostNetwork)) {
               return false;
            } else if (!Objects.equals(this.hostPID, that.hostPID)) {
               return false;
            } else if (!Objects.equals(this.hostUsers, that.hostUsers)) {
               return false;
            } else if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.imagePullSecrets, that.imagePullSecrets)) {
               return false;
            } else if (!Objects.equals(this.initContainers, that.initContainers)) {
               return false;
            } else if (!Objects.equals(this.nodeName, that.nodeName)) {
               return false;
            } else if (!Objects.equals(this.nodeSelector, that.nodeSelector)) {
               return false;
            } else if (!Objects.equals(this.os, that.os)) {
               return false;
            } else if (!Objects.equals(this.overhead, that.overhead)) {
               return false;
            } else if (!Objects.equals(this.preemptionPolicy, that.preemptionPolicy)) {
               return false;
            } else if (!Objects.equals(this.priority, that.priority)) {
               return false;
            } else if (!Objects.equals(this.priorityClassName, that.priorityClassName)) {
               return false;
            } else if (!Objects.equals(this.readinessGates, that.readinessGates)) {
               return false;
            } else if (!Objects.equals(this.resourceClaims, that.resourceClaims)) {
               return false;
            } else if (!Objects.equals(this.resources, that.resources)) {
               return false;
            } else if (!Objects.equals(this.restartPolicy, that.restartPolicy)) {
               return false;
            } else if (!Objects.equals(this.runtimeClassName, that.runtimeClassName)) {
               return false;
            } else if (!Objects.equals(this.schedulerName, that.schedulerName)) {
               return false;
            } else if (!Objects.equals(this.schedulingGates, that.schedulingGates)) {
               return false;
            } else if (!Objects.equals(this.securityContext, that.securityContext)) {
               return false;
            } else if (!Objects.equals(this.serviceAccount, that.serviceAccount)) {
               return false;
            } else if (!Objects.equals(this.serviceAccountName, that.serviceAccountName)) {
               return false;
            } else if (!Objects.equals(this.setHostnameAsFQDN, that.setHostnameAsFQDN)) {
               return false;
            } else if (!Objects.equals(this.shareProcessNamespace, that.shareProcessNamespace)) {
               return false;
            } else if (!Objects.equals(this.subdomain, that.subdomain)) {
               return false;
            } else if (!Objects.equals(this.terminationGracePeriodSeconds, that.terminationGracePeriodSeconds)) {
               return false;
            } else if (!Objects.equals(this.tolerations, that.tolerations)) {
               return false;
            } else if (!Objects.equals(this.topologySpreadConstraints, that.topologySpreadConstraints)) {
               return false;
            } else if (!Objects.equals(this.volumes, that.volumes)) {
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
      return Objects.hash(new Object[]{this.activeDeadlineSeconds, this.affinity, this.automountServiceAccountToken, this.containers, this.dnsConfig, this.dnsPolicy, this.enableServiceLinks, this.ephemeralContainers, this.hostAliases, this.hostIPC, this.hostNetwork, this.hostPID, this.hostUsers, this.hostname, this.imagePullSecrets, this.initContainers, this.nodeName, this.nodeSelector, this.os, this.overhead, this.preemptionPolicy, this.priority, this.priorityClassName, this.readinessGates, this.resourceClaims, this.resources, this.restartPolicy, this.runtimeClassName, this.schedulerName, this.schedulingGates, this.securityContext, this.serviceAccount, this.serviceAccountName, this.setHostnameAsFQDN, this.shareProcessNamespace, this.subdomain, this.terminationGracePeriodSeconds, this.tolerations, this.topologySpreadConstraints, this.volumes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.activeDeadlineSeconds != null) {
         sb.append("activeDeadlineSeconds:");
         sb.append(this.activeDeadlineSeconds + ",");
      }

      if (this.affinity != null) {
         sb.append("affinity:");
         sb.append(this.affinity + ",");
      }

      if (this.automountServiceAccountToken != null) {
         sb.append("automountServiceAccountToken:");
         sb.append(this.automountServiceAccountToken + ",");
      }

      if (this.containers != null && !this.containers.isEmpty()) {
         sb.append("containers:");
         sb.append(this.containers + ",");
      }

      if (this.dnsConfig != null) {
         sb.append("dnsConfig:");
         sb.append(this.dnsConfig + ",");
      }

      if (this.dnsPolicy != null) {
         sb.append("dnsPolicy:");
         sb.append(this.dnsPolicy + ",");
      }

      if (this.enableServiceLinks != null) {
         sb.append("enableServiceLinks:");
         sb.append(this.enableServiceLinks + ",");
      }

      if (this.ephemeralContainers != null && !this.ephemeralContainers.isEmpty()) {
         sb.append("ephemeralContainers:");
         sb.append(this.ephemeralContainers + ",");
      }

      if (this.hostAliases != null && !this.hostAliases.isEmpty()) {
         sb.append("hostAliases:");
         sb.append(this.hostAliases + ",");
      }

      if (this.hostIPC != null) {
         sb.append("hostIPC:");
         sb.append(this.hostIPC + ",");
      }

      if (this.hostNetwork != null) {
         sb.append("hostNetwork:");
         sb.append(this.hostNetwork + ",");
      }

      if (this.hostPID != null) {
         sb.append("hostPID:");
         sb.append(this.hostPID + ",");
      }

      if (this.hostUsers != null) {
         sb.append("hostUsers:");
         sb.append(this.hostUsers + ",");
      }

      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.imagePullSecrets != null && !this.imagePullSecrets.isEmpty()) {
         sb.append("imagePullSecrets:");
         sb.append(this.imagePullSecrets + ",");
      }

      if (this.initContainers != null && !this.initContainers.isEmpty()) {
         sb.append("initContainers:");
         sb.append(this.initContainers + ",");
      }

      if (this.nodeName != null) {
         sb.append("nodeName:");
         sb.append(this.nodeName + ",");
      }

      if (this.nodeSelector != null && !this.nodeSelector.isEmpty()) {
         sb.append("nodeSelector:");
         sb.append(this.nodeSelector + ",");
      }

      if (this.os != null) {
         sb.append("os:");
         sb.append(this.os + ",");
      }

      if (this.overhead != null && !this.overhead.isEmpty()) {
         sb.append("overhead:");
         sb.append(this.overhead + ",");
      }

      if (this.preemptionPolicy != null) {
         sb.append("preemptionPolicy:");
         sb.append(this.preemptionPolicy + ",");
      }

      if (this.priority != null) {
         sb.append("priority:");
         sb.append(this.priority + ",");
      }

      if (this.priorityClassName != null) {
         sb.append("priorityClassName:");
         sb.append(this.priorityClassName + ",");
      }

      if (this.readinessGates != null && !this.readinessGates.isEmpty()) {
         sb.append("readinessGates:");
         sb.append(this.readinessGates + ",");
      }

      if (this.resourceClaims != null && !this.resourceClaims.isEmpty()) {
         sb.append("resourceClaims:");
         sb.append(this.resourceClaims + ",");
      }

      if (this.resources != null) {
         sb.append("resources:");
         sb.append(this.resources + ",");
      }

      if (this.restartPolicy != null) {
         sb.append("restartPolicy:");
         sb.append(this.restartPolicy + ",");
      }

      if (this.runtimeClassName != null) {
         sb.append("runtimeClassName:");
         sb.append(this.runtimeClassName + ",");
      }

      if (this.schedulerName != null) {
         sb.append("schedulerName:");
         sb.append(this.schedulerName + ",");
      }

      if (this.schedulingGates != null && !this.schedulingGates.isEmpty()) {
         sb.append("schedulingGates:");
         sb.append(this.schedulingGates + ",");
      }

      if (this.securityContext != null) {
         sb.append("securityContext:");
         sb.append(this.securityContext + ",");
      }

      if (this.serviceAccount != null) {
         sb.append("serviceAccount:");
         sb.append(this.serviceAccount + ",");
      }

      if (this.serviceAccountName != null) {
         sb.append("serviceAccountName:");
         sb.append(this.serviceAccountName + ",");
      }

      if (this.setHostnameAsFQDN != null) {
         sb.append("setHostnameAsFQDN:");
         sb.append(this.setHostnameAsFQDN + ",");
      }

      if (this.shareProcessNamespace != null) {
         sb.append("shareProcessNamespace:");
         sb.append(this.shareProcessNamespace + ",");
      }

      if (this.subdomain != null) {
         sb.append("subdomain:");
         sb.append(this.subdomain + ",");
      }

      if (this.terminationGracePeriodSeconds != null) {
         sb.append("terminationGracePeriodSeconds:");
         sb.append(this.terminationGracePeriodSeconds + ",");
      }

      if (this.tolerations != null && !this.tolerations.isEmpty()) {
         sb.append("tolerations:");
         sb.append(this.tolerations + ",");
      }

      if (this.topologySpreadConstraints != null && !this.topologySpreadConstraints.isEmpty()) {
         sb.append("topologySpreadConstraints:");
         sb.append(this.topologySpreadConstraints + ",");
      }

      if (this.volumes != null && !this.volumes.isEmpty()) {
         sb.append("volumes:");
         sb.append(this.volumes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public PodSpecFluent withAutomountServiceAccountToken() {
      return this.withAutomountServiceAccountToken(true);
   }

   public PodSpecFluent withEnableServiceLinks() {
      return this.withEnableServiceLinks(true);
   }

   public PodSpecFluent withHostIPC() {
      return this.withHostIPC(true);
   }

   public PodSpecFluent withHostNetwork() {
      return this.withHostNetwork(true);
   }

   public PodSpecFluent withHostPID() {
      return this.withHostPID(true);
   }

   public PodSpecFluent withHostUsers() {
      return this.withHostUsers(true);
   }

   public PodSpecFluent withSetHostnameAsFQDN() {
      return this.withSetHostnameAsFQDN(true);
   }

   public PodSpecFluent withShareProcessNamespace() {
      return this.withShareProcessNamespace(true);
   }

   public class AffinityNested extends AffinityFluent implements Nested {
      AffinityBuilder builder;

      AffinityNested(Affinity item) {
         this.builder = new AffinityBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.withAffinity(this.builder.build());
      }

      public Object endAffinity() {
         return this.and();
      }
   }

   public class ContainersNested extends ContainerFluent implements Nested {
      ContainerBuilder builder;
      int index;

      ContainersNested(int index, Container item) {
         this.index = index;
         this.builder = new ContainerBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToContainers(this.index, this.builder.build());
      }

      public Object endContainer() {
         return this.and();
      }
   }

   public class DnsConfigNested extends PodDNSConfigFluent implements Nested {
      PodDNSConfigBuilder builder;

      DnsConfigNested(PodDNSConfig item) {
         this.builder = new PodDNSConfigBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.withDnsConfig(this.builder.build());
      }

      public Object endDnsConfig() {
         return this.and();
      }
   }

   public class EphemeralContainersNested extends EphemeralContainerFluent implements Nested {
      EphemeralContainerBuilder builder;
      int index;

      EphemeralContainersNested(int index, EphemeralContainer item) {
         this.index = index;
         this.builder = new EphemeralContainerBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToEphemeralContainers(this.index, this.builder.build());
      }

      public Object endEphemeralContainer() {
         return this.and();
      }
   }

   public class HostAliasesNested extends HostAliasFluent implements Nested {
      HostAliasBuilder builder;
      int index;

      HostAliasesNested(int index, HostAlias item) {
         this.index = index;
         this.builder = new HostAliasBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToHostAliases(this.index, this.builder.build());
      }

      public Object endHostAlias() {
         return this.and();
      }
   }

   public class ImagePullSecretsNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;
      int index;

      ImagePullSecretsNested(int index, LocalObjectReference item) {
         this.index = index;
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToImagePullSecrets(this.index, this.builder.build());
      }

      public Object endImagePullSecret() {
         return this.and();
      }
   }

   public class InitContainersNested extends ContainerFluent implements Nested {
      ContainerBuilder builder;
      int index;

      InitContainersNested(int index, Container item) {
         this.index = index;
         this.builder = new ContainerBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToInitContainers(this.index, this.builder.build());
      }

      public Object endInitContainer() {
         return this.and();
      }
   }

   public class OsNested extends PodOSFluent implements Nested {
      PodOSBuilder builder;

      OsNested(PodOS item) {
         this.builder = new PodOSBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.withOs(this.builder.build());
      }

      public Object endOs() {
         return this.and();
      }
   }

   public class ReadinessGatesNested extends PodReadinessGateFluent implements Nested {
      PodReadinessGateBuilder builder;
      int index;

      ReadinessGatesNested(int index, PodReadinessGate item) {
         this.index = index;
         this.builder = new PodReadinessGateBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToReadinessGates(this.index, this.builder.build());
      }

      public Object endReadinessGate() {
         return this.and();
      }
   }

   public class ResourceClaimsNested extends PodResourceClaimFluent implements Nested {
      PodResourceClaimBuilder builder;
      int index;

      ResourceClaimsNested(int index, PodResourceClaim item) {
         this.index = index;
         this.builder = new PodResourceClaimBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToResourceClaims(this.index, this.builder.build());
      }

      public Object endResourceClaim() {
         return this.and();
      }
   }

   public class ResourcesNested extends ResourceRequirementsFluent implements Nested {
      ResourceRequirementsBuilder builder;

      ResourcesNested(ResourceRequirements item) {
         this.builder = new ResourceRequirementsBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.withResources(this.builder.build());
      }

      public Object endResources() {
         return this.and();
      }
   }

   public class SchedulingGatesNested extends PodSchedulingGateFluent implements Nested {
      PodSchedulingGateBuilder builder;
      int index;

      SchedulingGatesNested(int index, PodSchedulingGate item) {
         this.index = index;
         this.builder = new PodSchedulingGateBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToSchedulingGates(this.index, this.builder.build());
      }

      public Object endSchedulingGate() {
         return this.and();
      }
   }

   public class SecurityContextNested extends PodSecurityContextFluent implements Nested {
      PodSecurityContextBuilder builder;

      SecurityContextNested(PodSecurityContext item) {
         this.builder = new PodSecurityContextBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.withSecurityContext(this.builder.build());
      }

      public Object endSecurityContext() {
         return this.and();
      }
   }

   public class TolerationsNested extends TolerationFluent implements Nested {
      TolerationBuilder builder;
      int index;

      TolerationsNested(int index, Toleration item) {
         this.index = index;
         this.builder = new TolerationBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToTolerations(this.index, this.builder.build());
      }

      public Object endToleration() {
         return this.and();
      }
   }

   public class TopologySpreadConstraintsNested extends TopologySpreadConstraintFluent implements Nested {
      TopologySpreadConstraintBuilder builder;
      int index;

      TopologySpreadConstraintsNested(int index, TopologySpreadConstraint item) {
         this.index = index;
         this.builder = new TopologySpreadConstraintBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToTopologySpreadConstraints(this.index, this.builder.build());
      }

      public Object endTopologySpreadConstraint() {
         return this.and();
      }
   }

   public class VolumesNested extends VolumeFluent implements Nested {
      VolumeBuilder builder;
      int index;

      VolumesNested(int index, Volume item) {
         this.index = index;
         this.builder = new VolumeBuilder(this, item);
      }

      public Object and() {
         return PodSpecFluent.this.setToVolumes(this.index, this.builder.build());
      }

      public Object endVolume() {
         return this.and();
      }
   }
}
