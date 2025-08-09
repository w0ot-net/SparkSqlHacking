package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"accessModes", "awsElasticBlockStore", "azureDisk", "azureFile", "capacity", "cephfs", "cinder", "claimRef", "csi", "fc", "flexVolume", "flocker", "gcePersistentDisk", "glusterfs", "hostPath", "iscsi", "local", "mountOptions", "nfs", "nodeAffinity", "persistentVolumeReclaimPolicy", "photonPersistentDisk", "portworxVolume", "quobyte", "rbd", "scaleIO", "storageClassName", "storageos", "volumeAttributesClassName", "volumeMode", "vsphereVolume"})
public class PersistentVolumeSpec implements Editable, KubernetesResource {
   @JsonProperty("accessModes")
   @JsonInclude(Include.NON_EMPTY)
   private List accessModes = new ArrayList();
   @JsonProperty("awsElasticBlockStore")
   private AWSElasticBlockStoreVolumeSource awsElasticBlockStore;
   @JsonProperty("azureDisk")
   private AzureDiskVolumeSource azureDisk;
   @JsonProperty("azureFile")
   private AzureFilePersistentVolumeSource azureFile;
   @JsonProperty("capacity")
   @JsonInclude(Include.NON_EMPTY)
   private Map capacity = new LinkedHashMap();
   @JsonProperty("cephfs")
   private CephFSPersistentVolumeSource cephfs;
   @JsonProperty("cinder")
   private CinderPersistentVolumeSource cinder;
   @JsonProperty("claimRef")
   private ObjectReference claimRef;
   @JsonProperty("csi")
   private CSIPersistentVolumeSource csi;
   @JsonProperty("fc")
   private FCVolumeSource fc;
   @JsonProperty("flexVolume")
   private FlexPersistentVolumeSource flexVolume;
   @JsonProperty("flocker")
   private FlockerVolumeSource flocker;
   @JsonProperty("gcePersistentDisk")
   private GCEPersistentDiskVolumeSource gcePersistentDisk;
   @JsonProperty("glusterfs")
   private GlusterfsPersistentVolumeSource glusterfs;
   @JsonProperty("hostPath")
   private HostPathVolumeSource hostPath;
   @JsonProperty("iscsi")
   private ISCSIPersistentVolumeSource iscsi;
   @JsonProperty("local")
   private LocalVolumeSource local;
   @JsonProperty("mountOptions")
   @JsonInclude(Include.NON_EMPTY)
   private List mountOptions = new ArrayList();
   @JsonProperty("nfs")
   private NFSVolumeSource nfs;
   @JsonProperty("nodeAffinity")
   private VolumeNodeAffinity nodeAffinity;
   @JsonProperty("persistentVolumeReclaimPolicy")
   private String persistentVolumeReclaimPolicy;
   @JsonProperty("photonPersistentDisk")
   private PhotonPersistentDiskVolumeSource photonPersistentDisk;
   @JsonProperty("portworxVolume")
   private PortworxVolumeSource portworxVolume;
   @JsonProperty("quobyte")
   private QuobyteVolumeSource quobyte;
   @JsonProperty("rbd")
   private RBDPersistentVolumeSource rbd;
   @JsonProperty("scaleIO")
   private ScaleIOPersistentVolumeSource scaleIO;
   @JsonProperty("storageClassName")
   private String storageClassName;
   @JsonProperty("storageos")
   private StorageOSPersistentVolumeSource storageos;
   @JsonProperty("volumeAttributesClassName")
   private String volumeAttributesClassName;
   @JsonProperty("volumeMode")
   private String volumeMode;
   @JsonProperty("vsphereVolume")
   private VsphereVirtualDiskVolumeSource vsphereVolume;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PersistentVolumeSpec() {
   }

   public PersistentVolumeSpec(List accessModes, AWSElasticBlockStoreVolumeSource awsElasticBlockStore, AzureDiskVolumeSource azureDisk, AzureFilePersistentVolumeSource azureFile, Map capacity, CephFSPersistentVolumeSource cephfs, CinderPersistentVolumeSource cinder, ObjectReference claimRef, CSIPersistentVolumeSource csi, FCVolumeSource fc, FlexPersistentVolumeSource flexVolume, FlockerVolumeSource flocker, GCEPersistentDiskVolumeSource gcePersistentDisk, GlusterfsPersistentVolumeSource glusterfs, HostPathVolumeSource hostPath, ISCSIPersistentVolumeSource iscsi, LocalVolumeSource local, List mountOptions, NFSVolumeSource nfs, VolumeNodeAffinity nodeAffinity, String persistentVolumeReclaimPolicy, PhotonPersistentDiskVolumeSource photonPersistentDisk, PortworxVolumeSource portworxVolume, QuobyteVolumeSource quobyte, RBDPersistentVolumeSource rbd, ScaleIOPersistentVolumeSource scaleIO, String storageClassName, StorageOSPersistentVolumeSource storageos, String volumeAttributesClassName, String volumeMode, VsphereVirtualDiskVolumeSource vsphereVolume) {
      this.accessModes = accessModes;
      this.awsElasticBlockStore = awsElasticBlockStore;
      this.azureDisk = azureDisk;
      this.azureFile = azureFile;
      this.capacity = capacity;
      this.cephfs = cephfs;
      this.cinder = cinder;
      this.claimRef = claimRef;
      this.csi = csi;
      this.fc = fc;
      this.flexVolume = flexVolume;
      this.flocker = flocker;
      this.gcePersistentDisk = gcePersistentDisk;
      this.glusterfs = glusterfs;
      this.hostPath = hostPath;
      this.iscsi = iscsi;
      this.local = local;
      this.mountOptions = mountOptions;
      this.nfs = nfs;
      this.nodeAffinity = nodeAffinity;
      this.persistentVolumeReclaimPolicy = persistentVolumeReclaimPolicy;
      this.photonPersistentDisk = photonPersistentDisk;
      this.portworxVolume = portworxVolume;
      this.quobyte = quobyte;
      this.rbd = rbd;
      this.scaleIO = scaleIO;
      this.storageClassName = storageClassName;
      this.storageos = storageos;
      this.volumeAttributesClassName = volumeAttributesClassName;
      this.volumeMode = volumeMode;
      this.vsphereVolume = vsphereVolume;
   }

   @JsonProperty("accessModes")
   @JsonInclude(Include.NON_EMPTY)
   public List getAccessModes() {
      return this.accessModes;
   }

   @JsonProperty("accessModes")
   public void setAccessModes(List accessModes) {
      this.accessModes = accessModes;
   }

   @JsonProperty("awsElasticBlockStore")
   public AWSElasticBlockStoreVolumeSource getAwsElasticBlockStore() {
      return this.awsElasticBlockStore;
   }

   @JsonProperty("awsElasticBlockStore")
   public void setAwsElasticBlockStore(AWSElasticBlockStoreVolumeSource awsElasticBlockStore) {
      this.awsElasticBlockStore = awsElasticBlockStore;
   }

   @JsonProperty("azureDisk")
   public AzureDiskVolumeSource getAzureDisk() {
      return this.azureDisk;
   }

   @JsonProperty("azureDisk")
   public void setAzureDisk(AzureDiskVolumeSource azureDisk) {
      this.azureDisk = azureDisk;
   }

   @JsonProperty("azureFile")
   public AzureFilePersistentVolumeSource getAzureFile() {
      return this.azureFile;
   }

   @JsonProperty("azureFile")
   public void setAzureFile(AzureFilePersistentVolumeSource azureFile) {
      this.azureFile = azureFile;
   }

   @JsonProperty("capacity")
   @JsonInclude(Include.NON_EMPTY)
   public Map getCapacity() {
      return this.capacity;
   }

   @JsonProperty("capacity")
   public void setCapacity(Map capacity) {
      this.capacity = capacity;
   }

   @JsonProperty("cephfs")
   public CephFSPersistentVolumeSource getCephfs() {
      return this.cephfs;
   }

   @JsonProperty("cephfs")
   public void setCephfs(CephFSPersistentVolumeSource cephfs) {
      this.cephfs = cephfs;
   }

   @JsonProperty("cinder")
   public CinderPersistentVolumeSource getCinder() {
      return this.cinder;
   }

   @JsonProperty("cinder")
   public void setCinder(CinderPersistentVolumeSource cinder) {
      this.cinder = cinder;
   }

   @JsonProperty("claimRef")
   public ObjectReference getClaimRef() {
      return this.claimRef;
   }

   @JsonProperty("claimRef")
   public void setClaimRef(ObjectReference claimRef) {
      this.claimRef = claimRef;
   }

   @JsonProperty("csi")
   public CSIPersistentVolumeSource getCsi() {
      return this.csi;
   }

   @JsonProperty("csi")
   public void setCsi(CSIPersistentVolumeSource csi) {
      this.csi = csi;
   }

   @JsonProperty("fc")
   public FCVolumeSource getFc() {
      return this.fc;
   }

   @JsonProperty("fc")
   public void setFc(FCVolumeSource fc) {
      this.fc = fc;
   }

   @JsonProperty("flexVolume")
   public FlexPersistentVolumeSource getFlexVolume() {
      return this.flexVolume;
   }

   @JsonProperty("flexVolume")
   public void setFlexVolume(FlexPersistentVolumeSource flexVolume) {
      this.flexVolume = flexVolume;
   }

   @JsonProperty("flocker")
   public FlockerVolumeSource getFlocker() {
      return this.flocker;
   }

   @JsonProperty("flocker")
   public void setFlocker(FlockerVolumeSource flocker) {
      this.flocker = flocker;
   }

   @JsonProperty("gcePersistentDisk")
   public GCEPersistentDiskVolumeSource getGcePersistentDisk() {
      return this.gcePersistentDisk;
   }

   @JsonProperty("gcePersistentDisk")
   public void setGcePersistentDisk(GCEPersistentDiskVolumeSource gcePersistentDisk) {
      this.gcePersistentDisk = gcePersistentDisk;
   }

   @JsonProperty("glusterfs")
   public GlusterfsPersistentVolumeSource getGlusterfs() {
      return this.glusterfs;
   }

   @JsonProperty("glusterfs")
   public void setGlusterfs(GlusterfsPersistentVolumeSource glusterfs) {
      this.glusterfs = glusterfs;
   }

   @JsonProperty("hostPath")
   public HostPathVolumeSource getHostPath() {
      return this.hostPath;
   }

   @JsonProperty("hostPath")
   public void setHostPath(HostPathVolumeSource hostPath) {
      this.hostPath = hostPath;
   }

   @JsonProperty("iscsi")
   public ISCSIPersistentVolumeSource getIscsi() {
      return this.iscsi;
   }

   @JsonProperty("iscsi")
   public void setIscsi(ISCSIPersistentVolumeSource iscsi) {
      this.iscsi = iscsi;
   }

   @JsonProperty("local")
   public LocalVolumeSource getLocal() {
      return this.local;
   }

   @JsonProperty("local")
   public void setLocal(LocalVolumeSource local) {
      this.local = local;
   }

   @JsonProperty("mountOptions")
   @JsonInclude(Include.NON_EMPTY)
   public List getMountOptions() {
      return this.mountOptions;
   }

   @JsonProperty("mountOptions")
   public void setMountOptions(List mountOptions) {
      this.mountOptions = mountOptions;
   }

   @JsonProperty("nfs")
   public NFSVolumeSource getNfs() {
      return this.nfs;
   }

   @JsonProperty("nfs")
   public void setNfs(NFSVolumeSource nfs) {
      this.nfs = nfs;
   }

   @JsonProperty("nodeAffinity")
   public VolumeNodeAffinity getNodeAffinity() {
      return this.nodeAffinity;
   }

   @JsonProperty("nodeAffinity")
   public void setNodeAffinity(VolumeNodeAffinity nodeAffinity) {
      this.nodeAffinity = nodeAffinity;
   }

   @JsonProperty("persistentVolumeReclaimPolicy")
   public String getPersistentVolumeReclaimPolicy() {
      return this.persistentVolumeReclaimPolicy;
   }

   @JsonProperty("persistentVolumeReclaimPolicy")
   public void setPersistentVolumeReclaimPolicy(String persistentVolumeReclaimPolicy) {
      this.persistentVolumeReclaimPolicy = persistentVolumeReclaimPolicy;
   }

   @JsonProperty("photonPersistentDisk")
   public PhotonPersistentDiskVolumeSource getPhotonPersistentDisk() {
      return this.photonPersistentDisk;
   }

   @JsonProperty("photonPersistentDisk")
   public void setPhotonPersistentDisk(PhotonPersistentDiskVolumeSource photonPersistentDisk) {
      this.photonPersistentDisk = photonPersistentDisk;
   }

   @JsonProperty("portworxVolume")
   public PortworxVolumeSource getPortworxVolume() {
      return this.portworxVolume;
   }

   @JsonProperty("portworxVolume")
   public void setPortworxVolume(PortworxVolumeSource portworxVolume) {
      this.portworxVolume = portworxVolume;
   }

   @JsonProperty("quobyte")
   public QuobyteVolumeSource getQuobyte() {
      return this.quobyte;
   }

   @JsonProperty("quobyte")
   public void setQuobyte(QuobyteVolumeSource quobyte) {
      this.quobyte = quobyte;
   }

   @JsonProperty("rbd")
   public RBDPersistentVolumeSource getRbd() {
      return this.rbd;
   }

   @JsonProperty("rbd")
   public void setRbd(RBDPersistentVolumeSource rbd) {
      this.rbd = rbd;
   }

   @JsonProperty("scaleIO")
   public ScaleIOPersistentVolumeSource getScaleIO() {
      return this.scaleIO;
   }

   @JsonProperty("scaleIO")
   public void setScaleIO(ScaleIOPersistentVolumeSource scaleIO) {
      this.scaleIO = scaleIO;
   }

   @JsonProperty("storageClassName")
   public String getStorageClassName() {
      return this.storageClassName;
   }

   @JsonProperty("storageClassName")
   public void setStorageClassName(String storageClassName) {
      this.storageClassName = storageClassName;
   }

   @JsonProperty("storageos")
   public StorageOSPersistentVolumeSource getStorageos() {
      return this.storageos;
   }

   @JsonProperty("storageos")
   public void setStorageos(StorageOSPersistentVolumeSource storageos) {
      this.storageos = storageos;
   }

   @JsonProperty("volumeAttributesClassName")
   public String getVolumeAttributesClassName() {
      return this.volumeAttributesClassName;
   }

   @JsonProperty("volumeAttributesClassName")
   public void setVolumeAttributesClassName(String volumeAttributesClassName) {
      this.volumeAttributesClassName = volumeAttributesClassName;
   }

   @JsonProperty("volumeMode")
   public String getVolumeMode() {
      return this.volumeMode;
   }

   @JsonProperty("volumeMode")
   public void setVolumeMode(String volumeMode) {
      this.volumeMode = volumeMode;
   }

   @JsonProperty("vsphereVolume")
   public VsphereVirtualDiskVolumeSource getVsphereVolume() {
      return this.vsphereVolume;
   }

   @JsonProperty("vsphereVolume")
   public void setVsphereVolume(VsphereVirtualDiskVolumeSource vsphereVolume) {
      this.vsphereVolume = vsphereVolume;
   }

   @JsonIgnore
   public PersistentVolumeSpecBuilder edit() {
      return new PersistentVolumeSpecBuilder(this);
   }

   @JsonIgnore
   public PersistentVolumeSpecBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      List var10000 = this.getAccessModes();
      return "PersistentVolumeSpec(accessModes=" + var10000 + ", awsElasticBlockStore=" + this.getAwsElasticBlockStore() + ", azureDisk=" + this.getAzureDisk() + ", azureFile=" + this.getAzureFile() + ", capacity=" + this.getCapacity() + ", cephfs=" + this.getCephfs() + ", cinder=" + this.getCinder() + ", claimRef=" + this.getClaimRef() + ", csi=" + this.getCsi() + ", fc=" + this.getFc() + ", flexVolume=" + this.getFlexVolume() + ", flocker=" + this.getFlocker() + ", gcePersistentDisk=" + this.getGcePersistentDisk() + ", glusterfs=" + this.getGlusterfs() + ", hostPath=" + this.getHostPath() + ", iscsi=" + this.getIscsi() + ", local=" + this.getLocal() + ", mountOptions=" + this.getMountOptions() + ", nfs=" + this.getNfs() + ", nodeAffinity=" + this.getNodeAffinity() + ", persistentVolumeReclaimPolicy=" + this.getPersistentVolumeReclaimPolicy() + ", photonPersistentDisk=" + this.getPhotonPersistentDisk() + ", portworxVolume=" + this.getPortworxVolume() + ", quobyte=" + this.getQuobyte() + ", rbd=" + this.getRbd() + ", scaleIO=" + this.getScaleIO() + ", storageClassName=" + this.getStorageClassName() + ", storageos=" + this.getStorageos() + ", volumeAttributesClassName=" + this.getVolumeAttributesClassName() + ", volumeMode=" + this.getVolumeMode() + ", vsphereVolume=" + this.getVsphereVolume() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersistentVolumeSpec)) {
         return false;
      } else {
         PersistentVolumeSpec other = (PersistentVolumeSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$accessModes = this.getAccessModes();
            Object other$accessModes = other.getAccessModes();
            if (this$accessModes == null) {
               if (other$accessModes != null) {
                  return false;
               }
            } else if (!this$accessModes.equals(other$accessModes)) {
               return false;
            }

            Object this$awsElasticBlockStore = this.getAwsElasticBlockStore();
            Object other$awsElasticBlockStore = other.getAwsElasticBlockStore();
            if (this$awsElasticBlockStore == null) {
               if (other$awsElasticBlockStore != null) {
                  return false;
               }
            } else if (!this$awsElasticBlockStore.equals(other$awsElasticBlockStore)) {
               return false;
            }

            Object this$azureDisk = this.getAzureDisk();
            Object other$azureDisk = other.getAzureDisk();
            if (this$azureDisk == null) {
               if (other$azureDisk != null) {
                  return false;
               }
            } else if (!this$azureDisk.equals(other$azureDisk)) {
               return false;
            }

            Object this$azureFile = this.getAzureFile();
            Object other$azureFile = other.getAzureFile();
            if (this$azureFile == null) {
               if (other$azureFile != null) {
                  return false;
               }
            } else if (!this$azureFile.equals(other$azureFile)) {
               return false;
            }

            Object this$capacity = this.getCapacity();
            Object other$capacity = other.getCapacity();
            if (this$capacity == null) {
               if (other$capacity != null) {
                  return false;
               }
            } else if (!this$capacity.equals(other$capacity)) {
               return false;
            }

            Object this$cephfs = this.getCephfs();
            Object other$cephfs = other.getCephfs();
            if (this$cephfs == null) {
               if (other$cephfs != null) {
                  return false;
               }
            } else if (!this$cephfs.equals(other$cephfs)) {
               return false;
            }

            Object this$cinder = this.getCinder();
            Object other$cinder = other.getCinder();
            if (this$cinder == null) {
               if (other$cinder != null) {
                  return false;
               }
            } else if (!this$cinder.equals(other$cinder)) {
               return false;
            }

            Object this$claimRef = this.getClaimRef();
            Object other$claimRef = other.getClaimRef();
            if (this$claimRef == null) {
               if (other$claimRef != null) {
                  return false;
               }
            } else if (!this$claimRef.equals(other$claimRef)) {
               return false;
            }

            Object this$csi = this.getCsi();
            Object other$csi = other.getCsi();
            if (this$csi == null) {
               if (other$csi != null) {
                  return false;
               }
            } else if (!this$csi.equals(other$csi)) {
               return false;
            }

            Object this$fc = this.getFc();
            Object other$fc = other.getFc();
            if (this$fc == null) {
               if (other$fc != null) {
                  return false;
               }
            } else if (!this$fc.equals(other$fc)) {
               return false;
            }

            Object this$flexVolume = this.getFlexVolume();
            Object other$flexVolume = other.getFlexVolume();
            if (this$flexVolume == null) {
               if (other$flexVolume != null) {
                  return false;
               }
            } else if (!this$flexVolume.equals(other$flexVolume)) {
               return false;
            }

            Object this$flocker = this.getFlocker();
            Object other$flocker = other.getFlocker();
            if (this$flocker == null) {
               if (other$flocker != null) {
                  return false;
               }
            } else if (!this$flocker.equals(other$flocker)) {
               return false;
            }

            Object this$gcePersistentDisk = this.getGcePersistentDisk();
            Object other$gcePersistentDisk = other.getGcePersistentDisk();
            if (this$gcePersistentDisk == null) {
               if (other$gcePersistentDisk != null) {
                  return false;
               }
            } else if (!this$gcePersistentDisk.equals(other$gcePersistentDisk)) {
               return false;
            }

            Object this$glusterfs = this.getGlusterfs();
            Object other$glusterfs = other.getGlusterfs();
            if (this$glusterfs == null) {
               if (other$glusterfs != null) {
                  return false;
               }
            } else if (!this$glusterfs.equals(other$glusterfs)) {
               return false;
            }

            Object this$hostPath = this.getHostPath();
            Object other$hostPath = other.getHostPath();
            if (this$hostPath == null) {
               if (other$hostPath != null) {
                  return false;
               }
            } else if (!this$hostPath.equals(other$hostPath)) {
               return false;
            }

            Object this$iscsi = this.getIscsi();
            Object other$iscsi = other.getIscsi();
            if (this$iscsi == null) {
               if (other$iscsi != null) {
                  return false;
               }
            } else if (!this$iscsi.equals(other$iscsi)) {
               return false;
            }

            Object this$local = this.getLocal();
            Object other$local = other.getLocal();
            if (this$local == null) {
               if (other$local != null) {
                  return false;
               }
            } else if (!this$local.equals(other$local)) {
               return false;
            }

            Object this$mountOptions = this.getMountOptions();
            Object other$mountOptions = other.getMountOptions();
            if (this$mountOptions == null) {
               if (other$mountOptions != null) {
                  return false;
               }
            } else if (!this$mountOptions.equals(other$mountOptions)) {
               return false;
            }

            Object this$nfs = this.getNfs();
            Object other$nfs = other.getNfs();
            if (this$nfs == null) {
               if (other$nfs != null) {
                  return false;
               }
            } else if (!this$nfs.equals(other$nfs)) {
               return false;
            }

            Object this$nodeAffinity = this.getNodeAffinity();
            Object other$nodeAffinity = other.getNodeAffinity();
            if (this$nodeAffinity == null) {
               if (other$nodeAffinity != null) {
                  return false;
               }
            } else if (!this$nodeAffinity.equals(other$nodeAffinity)) {
               return false;
            }

            Object this$persistentVolumeReclaimPolicy = this.getPersistentVolumeReclaimPolicy();
            Object other$persistentVolumeReclaimPolicy = other.getPersistentVolumeReclaimPolicy();
            if (this$persistentVolumeReclaimPolicy == null) {
               if (other$persistentVolumeReclaimPolicy != null) {
                  return false;
               }
            } else if (!this$persistentVolumeReclaimPolicy.equals(other$persistentVolumeReclaimPolicy)) {
               return false;
            }

            Object this$photonPersistentDisk = this.getPhotonPersistentDisk();
            Object other$photonPersistentDisk = other.getPhotonPersistentDisk();
            if (this$photonPersistentDisk == null) {
               if (other$photonPersistentDisk != null) {
                  return false;
               }
            } else if (!this$photonPersistentDisk.equals(other$photonPersistentDisk)) {
               return false;
            }

            Object this$portworxVolume = this.getPortworxVolume();
            Object other$portworxVolume = other.getPortworxVolume();
            if (this$portworxVolume == null) {
               if (other$portworxVolume != null) {
                  return false;
               }
            } else if (!this$portworxVolume.equals(other$portworxVolume)) {
               return false;
            }

            Object this$quobyte = this.getQuobyte();
            Object other$quobyte = other.getQuobyte();
            if (this$quobyte == null) {
               if (other$quobyte != null) {
                  return false;
               }
            } else if (!this$quobyte.equals(other$quobyte)) {
               return false;
            }

            Object this$rbd = this.getRbd();
            Object other$rbd = other.getRbd();
            if (this$rbd == null) {
               if (other$rbd != null) {
                  return false;
               }
            } else if (!this$rbd.equals(other$rbd)) {
               return false;
            }

            Object this$scaleIO = this.getScaleIO();
            Object other$scaleIO = other.getScaleIO();
            if (this$scaleIO == null) {
               if (other$scaleIO != null) {
                  return false;
               }
            } else if (!this$scaleIO.equals(other$scaleIO)) {
               return false;
            }

            Object this$storageClassName = this.getStorageClassName();
            Object other$storageClassName = other.getStorageClassName();
            if (this$storageClassName == null) {
               if (other$storageClassName != null) {
                  return false;
               }
            } else if (!this$storageClassName.equals(other$storageClassName)) {
               return false;
            }

            Object this$storageos = this.getStorageos();
            Object other$storageos = other.getStorageos();
            if (this$storageos == null) {
               if (other$storageos != null) {
                  return false;
               }
            } else if (!this$storageos.equals(other$storageos)) {
               return false;
            }

            Object this$volumeAttributesClassName = this.getVolumeAttributesClassName();
            Object other$volumeAttributesClassName = other.getVolumeAttributesClassName();
            if (this$volumeAttributesClassName == null) {
               if (other$volumeAttributesClassName != null) {
                  return false;
               }
            } else if (!this$volumeAttributesClassName.equals(other$volumeAttributesClassName)) {
               return false;
            }

            Object this$volumeMode = this.getVolumeMode();
            Object other$volumeMode = other.getVolumeMode();
            if (this$volumeMode == null) {
               if (other$volumeMode != null) {
                  return false;
               }
            } else if (!this$volumeMode.equals(other$volumeMode)) {
               return false;
            }

            Object this$vsphereVolume = this.getVsphereVolume();
            Object other$vsphereVolume = other.getVsphereVolume();
            if (this$vsphereVolume == null) {
               if (other$vsphereVolume != null) {
                  return false;
               }
            } else if (!this$vsphereVolume.equals(other$vsphereVolume)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof PersistentVolumeSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $accessModes = this.getAccessModes();
      result = result * 59 + ($accessModes == null ? 43 : $accessModes.hashCode());
      Object $awsElasticBlockStore = this.getAwsElasticBlockStore();
      result = result * 59 + ($awsElasticBlockStore == null ? 43 : $awsElasticBlockStore.hashCode());
      Object $azureDisk = this.getAzureDisk();
      result = result * 59 + ($azureDisk == null ? 43 : $azureDisk.hashCode());
      Object $azureFile = this.getAzureFile();
      result = result * 59 + ($azureFile == null ? 43 : $azureFile.hashCode());
      Object $capacity = this.getCapacity();
      result = result * 59 + ($capacity == null ? 43 : $capacity.hashCode());
      Object $cephfs = this.getCephfs();
      result = result * 59 + ($cephfs == null ? 43 : $cephfs.hashCode());
      Object $cinder = this.getCinder();
      result = result * 59 + ($cinder == null ? 43 : $cinder.hashCode());
      Object $claimRef = this.getClaimRef();
      result = result * 59 + ($claimRef == null ? 43 : $claimRef.hashCode());
      Object $csi = this.getCsi();
      result = result * 59 + ($csi == null ? 43 : $csi.hashCode());
      Object $fc = this.getFc();
      result = result * 59 + ($fc == null ? 43 : $fc.hashCode());
      Object $flexVolume = this.getFlexVolume();
      result = result * 59 + ($flexVolume == null ? 43 : $flexVolume.hashCode());
      Object $flocker = this.getFlocker();
      result = result * 59 + ($flocker == null ? 43 : $flocker.hashCode());
      Object $gcePersistentDisk = this.getGcePersistentDisk();
      result = result * 59 + ($gcePersistentDisk == null ? 43 : $gcePersistentDisk.hashCode());
      Object $glusterfs = this.getGlusterfs();
      result = result * 59 + ($glusterfs == null ? 43 : $glusterfs.hashCode());
      Object $hostPath = this.getHostPath();
      result = result * 59 + ($hostPath == null ? 43 : $hostPath.hashCode());
      Object $iscsi = this.getIscsi();
      result = result * 59 + ($iscsi == null ? 43 : $iscsi.hashCode());
      Object $local = this.getLocal();
      result = result * 59 + ($local == null ? 43 : $local.hashCode());
      Object $mountOptions = this.getMountOptions();
      result = result * 59 + ($mountOptions == null ? 43 : $mountOptions.hashCode());
      Object $nfs = this.getNfs();
      result = result * 59 + ($nfs == null ? 43 : $nfs.hashCode());
      Object $nodeAffinity = this.getNodeAffinity();
      result = result * 59 + ($nodeAffinity == null ? 43 : $nodeAffinity.hashCode());
      Object $persistentVolumeReclaimPolicy = this.getPersistentVolumeReclaimPolicy();
      result = result * 59 + ($persistentVolumeReclaimPolicy == null ? 43 : $persistentVolumeReclaimPolicy.hashCode());
      Object $photonPersistentDisk = this.getPhotonPersistentDisk();
      result = result * 59 + ($photonPersistentDisk == null ? 43 : $photonPersistentDisk.hashCode());
      Object $portworxVolume = this.getPortworxVolume();
      result = result * 59 + ($portworxVolume == null ? 43 : $portworxVolume.hashCode());
      Object $quobyte = this.getQuobyte();
      result = result * 59 + ($quobyte == null ? 43 : $quobyte.hashCode());
      Object $rbd = this.getRbd();
      result = result * 59 + ($rbd == null ? 43 : $rbd.hashCode());
      Object $scaleIO = this.getScaleIO();
      result = result * 59 + ($scaleIO == null ? 43 : $scaleIO.hashCode());
      Object $storageClassName = this.getStorageClassName();
      result = result * 59 + ($storageClassName == null ? 43 : $storageClassName.hashCode());
      Object $storageos = this.getStorageos();
      result = result * 59 + ($storageos == null ? 43 : $storageos.hashCode());
      Object $volumeAttributesClassName = this.getVolumeAttributesClassName();
      result = result * 59 + ($volumeAttributesClassName == null ? 43 : $volumeAttributesClassName.hashCode());
      Object $volumeMode = this.getVolumeMode();
      result = result * 59 + ($volumeMode == null ? 43 : $volumeMode.hashCode());
      Object $vsphereVolume = this.getVsphereVolume();
      result = result * 59 + ($vsphereVolume == null ? 43 : $vsphereVolume.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
