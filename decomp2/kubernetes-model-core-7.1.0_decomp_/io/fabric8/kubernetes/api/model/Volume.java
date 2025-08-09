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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"awsElasticBlockStore", "azureDisk", "azureFile", "cephfs", "cinder", "configMap", "csi", "downwardAPI", "emptyDir", "ephemeral", "fc", "flexVolume", "flocker", "gcePersistentDisk", "gitRepo", "glusterfs", "hostPath", "image", "iscsi", "name", "nfs", "persistentVolumeClaim", "photonPersistentDisk", "portworxVolume", "projected", "quobyte", "rbd", "scaleIO", "secret", "storageos", "vsphereVolume"})
public class Volume implements Editable, KubernetesResource {
   @JsonProperty("awsElasticBlockStore")
   private AWSElasticBlockStoreVolumeSource awsElasticBlockStore;
   @JsonProperty("azureDisk")
   private AzureDiskVolumeSource azureDisk;
   @JsonProperty("azureFile")
   private AzureFileVolumeSource azureFile;
   @JsonProperty("cephfs")
   private CephFSVolumeSource cephfs;
   @JsonProperty("cinder")
   private CinderVolumeSource cinder;
   @JsonProperty("configMap")
   private ConfigMapVolumeSource configMap;
   @JsonProperty("csi")
   private CSIVolumeSource csi;
   @JsonProperty("downwardAPI")
   private DownwardAPIVolumeSource downwardAPI;
   @JsonProperty("emptyDir")
   private EmptyDirVolumeSource emptyDir;
   @JsonProperty("ephemeral")
   private EphemeralVolumeSource ephemeral;
   @JsonProperty("fc")
   private FCVolumeSource fc;
   @JsonProperty("flexVolume")
   private FlexVolumeSource flexVolume;
   @JsonProperty("flocker")
   private FlockerVolumeSource flocker;
   @JsonProperty("gcePersistentDisk")
   private GCEPersistentDiskVolumeSource gcePersistentDisk;
   @JsonProperty("gitRepo")
   private GitRepoVolumeSource gitRepo;
   @JsonProperty("glusterfs")
   private GlusterfsVolumeSource glusterfs;
   @JsonProperty("hostPath")
   private HostPathVolumeSource hostPath;
   @JsonProperty("image")
   private ImageVolumeSource image;
   @JsonProperty("iscsi")
   private ISCSIVolumeSource iscsi;
   @JsonProperty("name")
   private String name;
   @JsonProperty("nfs")
   private NFSVolumeSource nfs;
   @JsonProperty("persistentVolumeClaim")
   private PersistentVolumeClaimVolumeSource persistentVolumeClaim;
   @JsonProperty("photonPersistentDisk")
   private PhotonPersistentDiskVolumeSource photonPersistentDisk;
   @JsonProperty("portworxVolume")
   private PortworxVolumeSource portworxVolume;
   @JsonProperty("projected")
   private ProjectedVolumeSource projected;
   @JsonProperty("quobyte")
   private QuobyteVolumeSource quobyte;
   @JsonProperty("rbd")
   private RBDVolumeSource rbd;
   @JsonProperty("scaleIO")
   private ScaleIOVolumeSource scaleIO;
   @JsonProperty("secret")
   private SecretVolumeSource secret;
   @JsonProperty("storageos")
   private StorageOSVolumeSource storageos;
   @JsonProperty("vsphereVolume")
   private VsphereVirtualDiskVolumeSource vsphereVolume;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Volume() {
   }

   public Volume(AWSElasticBlockStoreVolumeSource awsElasticBlockStore, AzureDiskVolumeSource azureDisk, AzureFileVolumeSource azureFile, CephFSVolumeSource cephfs, CinderVolumeSource cinder, ConfigMapVolumeSource configMap, CSIVolumeSource csi, DownwardAPIVolumeSource downwardAPI, EmptyDirVolumeSource emptyDir, EphemeralVolumeSource ephemeral, FCVolumeSource fc, FlexVolumeSource flexVolume, FlockerVolumeSource flocker, GCEPersistentDiskVolumeSource gcePersistentDisk, GitRepoVolumeSource gitRepo, GlusterfsVolumeSource glusterfs, HostPathVolumeSource hostPath, ImageVolumeSource image, ISCSIVolumeSource iscsi, String name, NFSVolumeSource nfs, PersistentVolumeClaimVolumeSource persistentVolumeClaim, PhotonPersistentDiskVolumeSource photonPersistentDisk, PortworxVolumeSource portworxVolume, ProjectedVolumeSource projected, QuobyteVolumeSource quobyte, RBDVolumeSource rbd, ScaleIOVolumeSource scaleIO, SecretVolumeSource secret, StorageOSVolumeSource storageos, VsphereVirtualDiskVolumeSource vsphereVolume) {
      this.awsElasticBlockStore = awsElasticBlockStore;
      this.azureDisk = azureDisk;
      this.azureFile = azureFile;
      this.cephfs = cephfs;
      this.cinder = cinder;
      this.configMap = configMap;
      this.csi = csi;
      this.downwardAPI = downwardAPI;
      this.emptyDir = emptyDir;
      this.ephemeral = ephemeral;
      this.fc = fc;
      this.flexVolume = flexVolume;
      this.flocker = flocker;
      this.gcePersistentDisk = gcePersistentDisk;
      this.gitRepo = gitRepo;
      this.glusterfs = glusterfs;
      this.hostPath = hostPath;
      this.image = image;
      this.iscsi = iscsi;
      this.name = name;
      this.nfs = nfs;
      this.persistentVolumeClaim = persistentVolumeClaim;
      this.photonPersistentDisk = photonPersistentDisk;
      this.portworxVolume = portworxVolume;
      this.projected = projected;
      this.quobyte = quobyte;
      this.rbd = rbd;
      this.scaleIO = scaleIO;
      this.secret = secret;
      this.storageos = storageos;
      this.vsphereVolume = vsphereVolume;
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
   public AzureFileVolumeSource getAzureFile() {
      return this.azureFile;
   }

   @JsonProperty("azureFile")
   public void setAzureFile(AzureFileVolumeSource azureFile) {
      this.azureFile = azureFile;
   }

   @JsonProperty("cephfs")
   public CephFSVolumeSource getCephfs() {
      return this.cephfs;
   }

   @JsonProperty("cephfs")
   public void setCephfs(CephFSVolumeSource cephfs) {
      this.cephfs = cephfs;
   }

   @JsonProperty("cinder")
   public CinderVolumeSource getCinder() {
      return this.cinder;
   }

   @JsonProperty("cinder")
   public void setCinder(CinderVolumeSource cinder) {
      this.cinder = cinder;
   }

   @JsonProperty("configMap")
   public ConfigMapVolumeSource getConfigMap() {
      return this.configMap;
   }

   @JsonProperty("configMap")
   public void setConfigMap(ConfigMapVolumeSource configMap) {
      this.configMap = configMap;
   }

   @JsonProperty("csi")
   public CSIVolumeSource getCsi() {
      return this.csi;
   }

   @JsonProperty("csi")
   public void setCsi(CSIVolumeSource csi) {
      this.csi = csi;
   }

   @JsonProperty("downwardAPI")
   public DownwardAPIVolumeSource getDownwardAPI() {
      return this.downwardAPI;
   }

   @JsonProperty("downwardAPI")
   public void setDownwardAPI(DownwardAPIVolumeSource downwardAPI) {
      this.downwardAPI = downwardAPI;
   }

   @JsonProperty("emptyDir")
   public EmptyDirVolumeSource getEmptyDir() {
      return this.emptyDir;
   }

   @JsonProperty("emptyDir")
   public void setEmptyDir(EmptyDirVolumeSource emptyDir) {
      this.emptyDir = emptyDir;
   }

   @JsonProperty("ephemeral")
   public EphemeralVolumeSource getEphemeral() {
      return this.ephemeral;
   }

   @JsonProperty("ephemeral")
   public void setEphemeral(EphemeralVolumeSource ephemeral) {
      this.ephemeral = ephemeral;
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
   public FlexVolumeSource getFlexVolume() {
      return this.flexVolume;
   }

   @JsonProperty("flexVolume")
   public void setFlexVolume(FlexVolumeSource flexVolume) {
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

   @JsonProperty("gitRepo")
   public GitRepoVolumeSource getGitRepo() {
      return this.gitRepo;
   }

   @JsonProperty("gitRepo")
   public void setGitRepo(GitRepoVolumeSource gitRepo) {
      this.gitRepo = gitRepo;
   }

   @JsonProperty("glusterfs")
   public GlusterfsVolumeSource getGlusterfs() {
      return this.glusterfs;
   }

   @JsonProperty("glusterfs")
   public void setGlusterfs(GlusterfsVolumeSource glusterfs) {
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

   @JsonProperty("image")
   public ImageVolumeSource getImage() {
      return this.image;
   }

   @JsonProperty("image")
   public void setImage(ImageVolumeSource image) {
      this.image = image;
   }

   @JsonProperty("iscsi")
   public ISCSIVolumeSource getIscsi() {
      return this.iscsi;
   }

   @JsonProperty("iscsi")
   public void setIscsi(ISCSIVolumeSource iscsi) {
      this.iscsi = iscsi;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("nfs")
   public NFSVolumeSource getNfs() {
      return this.nfs;
   }

   @JsonProperty("nfs")
   public void setNfs(NFSVolumeSource nfs) {
      this.nfs = nfs;
   }

   @JsonProperty("persistentVolumeClaim")
   public PersistentVolumeClaimVolumeSource getPersistentVolumeClaim() {
      return this.persistentVolumeClaim;
   }

   @JsonProperty("persistentVolumeClaim")
   public void setPersistentVolumeClaim(PersistentVolumeClaimVolumeSource persistentVolumeClaim) {
      this.persistentVolumeClaim = persistentVolumeClaim;
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

   @JsonProperty("projected")
   public ProjectedVolumeSource getProjected() {
      return this.projected;
   }

   @JsonProperty("projected")
   public void setProjected(ProjectedVolumeSource projected) {
      this.projected = projected;
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
   public RBDVolumeSource getRbd() {
      return this.rbd;
   }

   @JsonProperty("rbd")
   public void setRbd(RBDVolumeSource rbd) {
      this.rbd = rbd;
   }

   @JsonProperty("scaleIO")
   public ScaleIOVolumeSource getScaleIO() {
      return this.scaleIO;
   }

   @JsonProperty("scaleIO")
   public void setScaleIO(ScaleIOVolumeSource scaleIO) {
      this.scaleIO = scaleIO;
   }

   @JsonProperty("secret")
   public SecretVolumeSource getSecret() {
      return this.secret;
   }

   @JsonProperty("secret")
   public void setSecret(SecretVolumeSource secret) {
      this.secret = secret;
   }

   @JsonProperty("storageos")
   public StorageOSVolumeSource getStorageos() {
      return this.storageos;
   }

   @JsonProperty("storageos")
   public void setStorageos(StorageOSVolumeSource storageos) {
      this.storageos = storageos;
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
   public VolumeBuilder edit() {
      return new VolumeBuilder(this);
   }

   @JsonIgnore
   public VolumeBuilder toBuilder() {
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
      AWSElasticBlockStoreVolumeSource var10000 = this.getAwsElasticBlockStore();
      return "Volume(awsElasticBlockStore=" + var10000 + ", azureDisk=" + this.getAzureDisk() + ", azureFile=" + this.getAzureFile() + ", cephfs=" + this.getCephfs() + ", cinder=" + this.getCinder() + ", configMap=" + this.getConfigMap() + ", csi=" + this.getCsi() + ", downwardAPI=" + this.getDownwardAPI() + ", emptyDir=" + this.getEmptyDir() + ", ephemeral=" + this.getEphemeral() + ", fc=" + this.getFc() + ", flexVolume=" + this.getFlexVolume() + ", flocker=" + this.getFlocker() + ", gcePersistentDisk=" + this.getGcePersistentDisk() + ", gitRepo=" + this.getGitRepo() + ", glusterfs=" + this.getGlusterfs() + ", hostPath=" + this.getHostPath() + ", image=" + this.getImage() + ", iscsi=" + this.getIscsi() + ", name=" + this.getName() + ", nfs=" + this.getNfs() + ", persistentVolumeClaim=" + this.getPersistentVolumeClaim() + ", photonPersistentDisk=" + this.getPhotonPersistentDisk() + ", portworxVolume=" + this.getPortworxVolume() + ", projected=" + this.getProjected() + ", quobyte=" + this.getQuobyte() + ", rbd=" + this.getRbd() + ", scaleIO=" + this.getScaleIO() + ", secret=" + this.getSecret() + ", storageos=" + this.getStorageos() + ", vsphereVolume=" + this.getVsphereVolume() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Volume)) {
         return false;
      } else {
         Volume other = (Volume)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
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

            Object this$configMap = this.getConfigMap();
            Object other$configMap = other.getConfigMap();
            if (this$configMap == null) {
               if (other$configMap != null) {
                  return false;
               }
            } else if (!this$configMap.equals(other$configMap)) {
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

            Object this$downwardAPI = this.getDownwardAPI();
            Object other$downwardAPI = other.getDownwardAPI();
            if (this$downwardAPI == null) {
               if (other$downwardAPI != null) {
                  return false;
               }
            } else if (!this$downwardAPI.equals(other$downwardAPI)) {
               return false;
            }

            Object this$emptyDir = this.getEmptyDir();
            Object other$emptyDir = other.getEmptyDir();
            if (this$emptyDir == null) {
               if (other$emptyDir != null) {
                  return false;
               }
            } else if (!this$emptyDir.equals(other$emptyDir)) {
               return false;
            }

            Object this$ephemeral = this.getEphemeral();
            Object other$ephemeral = other.getEphemeral();
            if (this$ephemeral == null) {
               if (other$ephemeral != null) {
                  return false;
               }
            } else if (!this$ephemeral.equals(other$ephemeral)) {
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

            Object this$gitRepo = this.getGitRepo();
            Object other$gitRepo = other.getGitRepo();
            if (this$gitRepo == null) {
               if (other$gitRepo != null) {
                  return false;
               }
            } else if (!this$gitRepo.equals(other$gitRepo)) {
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

            Object this$image = this.getImage();
            Object other$image = other.getImage();
            if (this$image == null) {
               if (other$image != null) {
                  return false;
               }
            } else if (!this$image.equals(other$image)) {
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

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
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

            Object this$persistentVolumeClaim = this.getPersistentVolumeClaim();
            Object other$persistentVolumeClaim = other.getPersistentVolumeClaim();
            if (this$persistentVolumeClaim == null) {
               if (other$persistentVolumeClaim != null) {
                  return false;
               }
            } else if (!this$persistentVolumeClaim.equals(other$persistentVolumeClaim)) {
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

            Object this$projected = this.getProjected();
            Object other$projected = other.getProjected();
            if (this$projected == null) {
               if (other$projected != null) {
                  return false;
               }
            } else if (!this$projected.equals(other$projected)) {
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

            Object this$secret = this.getSecret();
            Object other$secret = other.getSecret();
            if (this$secret == null) {
               if (other$secret != null) {
                  return false;
               }
            } else if (!this$secret.equals(other$secret)) {
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
      return other instanceof Volume;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $awsElasticBlockStore = this.getAwsElasticBlockStore();
      result = result * 59 + ($awsElasticBlockStore == null ? 43 : $awsElasticBlockStore.hashCode());
      Object $azureDisk = this.getAzureDisk();
      result = result * 59 + ($azureDisk == null ? 43 : $azureDisk.hashCode());
      Object $azureFile = this.getAzureFile();
      result = result * 59 + ($azureFile == null ? 43 : $azureFile.hashCode());
      Object $cephfs = this.getCephfs();
      result = result * 59 + ($cephfs == null ? 43 : $cephfs.hashCode());
      Object $cinder = this.getCinder();
      result = result * 59 + ($cinder == null ? 43 : $cinder.hashCode());
      Object $configMap = this.getConfigMap();
      result = result * 59 + ($configMap == null ? 43 : $configMap.hashCode());
      Object $csi = this.getCsi();
      result = result * 59 + ($csi == null ? 43 : $csi.hashCode());
      Object $downwardAPI = this.getDownwardAPI();
      result = result * 59 + ($downwardAPI == null ? 43 : $downwardAPI.hashCode());
      Object $emptyDir = this.getEmptyDir();
      result = result * 59 + ($emptyDir == null ? 43 : $emptyDir.hashCode());
      Object $ephemeral = this.getEphemeral();
      result = result * 59 + ($ephemeral == null ? 43 : $ephemeral.hashCode());
      Object $fc = this.getFc();
      result = result * 59 + ($fc == null ? 43 : $fc.hashCode());
      Object $flexVolume = this.getFlexVolume();
      result = result * 59 + ($flexVolume == null ? 43 : $flexVolume.hashCode());
      Object $flocker = this.getFlocker();
      result = result * 59 + ($flocker == null ? 43 : $flocker.hashCode());
      Object $gcePersistentDisk = this.getGcePersistentDisk();
      result = result * 59 + ($gcePersistentDisk == null ? 43 : $gcePersistentDisk.hashCode());
      Object $gitRepo = this.getGitRepo();
      result = result * 59 + ($gitRepo == null ? 43 : $gitRepo.hashCode());
      Object $glusterfs = this.getGlusterfs();
      result = result * 59 + ($glusterfs == null ? 43 : $glusterfs.hashCode());
      Object $hostPath = this.getHostPath();
      result = result * 59 + ($hostPath == null ? 43 : $hostPath.hashCode());
      Object $image = this.getImage();
      result = result * 59 + ($image == null ? 43 : $image.hashCode());
      Object $iscsi = this.getIscsi();
      result = result * 59 + ($iscsi == null ? 43 : $iscsi.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $nfs = this.getNfs();
      result = result * 59 + ($nfs == null ? 43 : $nfs.hashCode());
      Object $persistentVolumeClaim = this.getPersistentVolumeClaim();
      result = result * 59 + ($persistentVolumeClaim == null ? 43 : $persistentVolumeClaim.hashCode());
      Object $photonPersistentDisk = this.getPhotonPersistentDisk();
      result = result * 59 + ($photonPersistentDisk == null ? 43 : $photonPersistentDisk.hashCode());
      Object $portworxVolume = this.getPortworxVolume();
      result = result * 59 + ($portworxVolume == null ? 43 : $portworxVolume.hashCode());
      Object $projected = this.getProjected();
      result = result * 59 + ($projected == null ? 43 : $projected.hashCode());
      Object $quobyte = this.getQuobyte();
      result = result * 59 + ($quobyte == null ? 43 : $quobyte.hashCode());
      Object $rbd = this.getRbd();
      result = result * 59 + ($rbd == null ? 43 : $rbd.hashCode());
      Object $scaleIO = this.getScaleIO();
      result = result * 59 + ($scaleIO == null ? 43 : $scaleIO.hashCode());
      Object $secret = this.getSecret();
      result = result * 59 + ($secret == null ? 43 : $secret.hashCode());
      Object $storageos = this.getStorageos();
      result = result * 59 + ($storageos == null ? 43 : $storageos.hashCode());
      Object $vsphereVolume = this.getVsphereVolume();
      result = result * 59 + ($vsphereVolume == null ? 43 : $vsphereVolume.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
