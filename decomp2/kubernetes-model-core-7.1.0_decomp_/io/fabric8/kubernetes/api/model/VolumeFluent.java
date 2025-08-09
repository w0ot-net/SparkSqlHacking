package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class VolumeFluent extends BaseFluent {
   private AWSElasticBlockStoreVolumeSourceBuilder awsElasticBlockStore;
   private AzureDiskVolumeSourceBuilder azureDisk;
   private AzureFileVolumeSourceBuilder azureFile;
   private CephFSVolumeSourceBuilder cephfs;
   private CinderVolumeSourceBuilder cinder;
   private ConfigMapVolumeSourceBuilder configMap;
   private CSIVolumeSourceBuilder csi;
   private DownwardAPIVolumeSourceBuilder downwardAPI;
   private EmptyDirVolumeSourceBuilder emptyDir;
   private EphemeralVolumeSourceBuilder ephemeral;
   private FCVolumeSourceBuilder fc;
   private FlexVolumeSourceBuilder flexVolume;
   private FlockerVolumeSourceBuilder flocker;
   private GCEPersistentDiskVolumeSourceBuilder gcePersistentDisk;
   private GitRepoVolumeSourceBuilder gitRepo;
   private GlusterfsVolumeSourceBuilder glusterfs;
   private HostPathVolumeSourceBuilder hostPath;
   private ImageVolumeSourceBuilder image;
   private ISCSIVolumeSourceBuilder iscsi;
   private String name;
   private NFSVolumeSourceBuilder nfs;
   private PersistentVolumeClaimVolumeSourceBuilder persistentVolumeClaim;
   private PhotonPersistentDiskVolumeSourceBuilder photonPersistentDisk;
   private PortworxVolumeSourceBuilder portworxVolume;
   private ProjectedVolumeSourceBuilder projected;
   private QuobyteVolumeSourceBuilder quobyte;
   private RBDVolumeSourceBuilder rbd;
   private ScaleIOVolumeSourceBuilder scaleIO;
   private SecretVolumeSourceBuilder secret;
   private StorageOSVolumeSourceBuilder storageos;
   private VsphereVirtualDiskVolumeSourceBuilder vsphereVolume;
   private Map additionalProperties;

   public VolumeFluent() {
   }

   public VolumeFluent(Volume instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Volume instance) {
      instance = instance != null ? instance : new Volume();
      if (instance != null) {
         this.withAwsElasticBlockStore(instance.getAwsElasticBlockStore());
         this.withAzureDisk(instance.getAzureDisk());
         this.withAzureFile(instance.getAzureFile());
         this.withCephfs(instance.getCephfs());
         this.withCinder(instance.getCinder());
         this.withConfigMap(instance.getConfigMap());
         this.withCsi(instance.getCsi());
         this.withDownwardAPI(instance.getDownwardAPI());
         this.withEmptyDir(instance.getEmptyDir());
         this.withEphemeral(instance.getEphemeral());
         this.withFc(instance.getFc());
         this.withFlexVolume(instance.getFlexVolume());
         this.withFlocker(instance.getFlocker());
         this.withGcePersistentDisk(instance.getGcePersistentDisk());
         this.withGitRepo(instance.getGitRepo());
         this.withGlusterfs(instance.getGlusterfs());
         this.withHostPath(instance.getHostPath());
         this.withImage(instance.getImage());
         this.withIscsi(instance.getIscsi());
         this.withName(instance.getName());
         this.withNfs(instance.getNfs());
         this.withPersistentVolumeClaim(instance.getPersistentVolumeClaim());
         this.withPhotonPersistentDisk(instance.getPhotonPersistentDisk());
         this.withPortworxVolume(instance.getPortworxVolume());
         this.withProjected(instance.getProjected());
         this.withQuobyte(instance.getQuobyte());
         this.withRbd(instance.getRbd());
         this.withScaleIO(instance.getScaleIO());
         this.withSecret(instance.getSecret());
         this.withStorageos(instance.getStorageos());
         this.withVsphereVolume(instance.getVsphereVolume());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AWSElasticBlockStoreVolumeSource buildAwsElasticBlockStore() {
      return this.awsElasticBlockStore != null ? this.awsElasticBlockStore.build() : null;
   }

   public VolumeFluent withAwsElasticBlockStore(AWSElasticBlockStoreVolumeSource awsElasticBlockStore) {
      this._visitables.remove("awsElasticBlockStore");
      if (awsElasticBlockStore != null) {
         this.awsElasticBlockStore = new AWSElasticBlockStoreVolumeSourceBuilder(awsElasticBlockStore);
         this._visitables.get("awsElasticBlockStore").add(this.awsElasticBlockStore);
      } else {
         this.awsElasticBlockStore = null;
         this._visitables.get("awsElasticBlockStore").remove(this.awsElasticBlockStore);
      }

      return this;
   }

   public boolean hasAwsElasticBlockStore() {
      return this.awsElasticBlockStore != null;
   }

   public VolumeFluent withNewAwsElasticBlockStore(String fsType, Integer partition, Boolean readOnly, String volumeID) {
      return this.withAwsElasticBlockStore(new AWSElasticBlockStoreVolumeSource(fsType, partition, readOnly, volumeID));
   }

   public AwsElasticBlockStoreNested withNewAwsElasticBlockStore() {
      return new AwsElasticBlockStoreNested((AWSElasticBlockStoreVolumeSource)null);
   }

   public AwsElasticBlockStoreNested withNewAwsElasticBlockStoreLike(AWSElasticBlockStoreVolumeSource item) {
      return new AwsElasticBlockStoreNested(item);
   }

   public AwsElasticBlockStoreNested editAwsElasticBlockStore() {
      return this.withNewAwsElasticBlockStoreLike((AWSElasticBlockStoreVolumeSource)Optional.ofNullable(this.buildAwsElasticBlockStore()).orElse((Object)null));
   }

   public AwsElasticBlockStoreNested editOrNewAwsElasticBlockStore() {
      return this.withNewAwsElasticBlockStoreLike((AWSElasticBlockStoreVolumeSource)Optional.ofNullable(this.buildAwsElasticBlockStore()).orElse((new AWSElasticBlockStoreVolumeSourceBuilder()).build()));
   }

   public AwsElasticBlockStoreNested editOrNewAwsElasticBlockStoreLike(AWSElasticBlockStoreVolumeSource item) {
      return this.withNewAwsElasticBlockStoreLike((AWSElasticBlockStoreVolumeSource)Optional.ofNullable(this.buildAwsElasticBlockStore()).orElse(item));
   }

   public AzureDiskVolumeSource buildAzureDisk() {
      return this.azureDisk != null ? this.azureDisk.build() : null;
   }

   public VolumeFluent withAzureDisk(AzureDiskVolumeSource azureDisk) {
      this._visitables.remove("azureDisk");
      if (azureDisk != null) {
         this.azureDisk = new AzureDiskVolumeSourceBuilder(azureDisk);
         this._visitables.get("azureDisk").add(this.azureDisk);
      } else {
         this.azureDisk = null;
         this._visitables.get("azureDisk").remove(this.azureDisk);
      }

      return this;
   }

   public boolean hasAzureDisk() {
      return this.azureDisk != null;
   }

   public AzureDiskNested withNewAzureDisk() {
      return new AzureDiskNested((AzureDiskVolumeSource)null);
   }

   public AzureDiskNested withNewAzureDiskLike(AzureDiskVolumeSource item) {
      return new AzureDiskNested(item);
   }

   public AzureDiskNested editAzureDisk() {
      return this.withNewAzureDiskLike((AzureDiskVolumeSource)Optional.ofNullable(this.buildAzureDisk()).orElse((Object)null));
   }

   public AzureDiskNested editOrNewAzureDisk() {
      return this.withNewAzureDiskLike((AzureDiskVolumeSource)Optional.ofNullable(this.buildAzureDisk()).orElse((new AzureDiskVolumeSourceBuilder()).build()));
   }

   public AzureDiskNested editOrNewAzureDiskLike(AzureDiskVolumeSource item) {
      return this.withNewAzureDiskLike((AzureDiskVolumeSource)Optional.ofNullable(this.buildAzureDisk()).orElse(item));
   }

   public AzureFileVolumeSource buildAzureFile() {
      return this.azureFile != null ? this.azureFile.build() : null;
   }

   public VolumeFluent withAzureFile(AzureFileVolumeSource azureFile) {
      this._visitables.remove("azureFile");
      if (azureFile != null) {
         this.azureFile = new AzureFileVolumeSourceBuilder(azureFile);
         this._visitables.get("azureFile").add(this.azureFile);
      } else {
         this.azureFile = null;
         this._visitables.get("azureFile").remove(this.azureFile);
      }

      return this;
   }

   public boolean hasAzureFile() {
      return this.azureFile != null;
   }

   public VolumeFluent withNewAzureFile(Boolean readOnly, String secretName, String shareName) {
      return this.withAzureFile(new AzureFileVolumeSource(readOnly, secretName, shareName));
   }

   public AzureFileNested withNewAzureFile() {
      return new AzureFileNested((AzureFileVolumeSource)null);
   }

   public AzureFileNested withNewAzureFileLike(AzureFileVolumeSource item) {
      return new AzureFileNested(item);
   }

   public AzureFileNested editAzureFile() {
      return this.withNewAzureFileLike((AzureFileVolumeSource)Optional.ofNullable(this.buildAzureFile()).orElse((Object)null));
   }

   public AzureFileNested editOrNewAzureFile() {
      return this.withNewAzureFileLike((AzureFileVolumeSource)Optional.ofNullable(this.buildAzureFile()).orElse((new AzureFileVolumeSourceBuilder()).build()));
   }

   public AzureFileNested editOrNewAzureFileLike(AzureFileVolumeSource item) {
      return this.withNewAzureFileLike((AzureFileVolumeSource)Optional.ofNullable(this.buildAzureFile()).orElse(item));
   }

   public CephFSVolumeSource buildCephfs() {
      return this.cephfs != null ? this.cephfs.build() : null;
   }

   public VolumeFluent withCephfs(CephFSVolumeSource cephfs) {
      this._visitables.remove("cephfs");
      if (cephfs != null) {
         this.cephfs = new CephFSVolumeSourceBuilder(cephfs);
         this._visitables.get("cephfs").add(this.cephfs);
      } else {
         this.cephfs = null;
         this._visitables.get("cephfs").remove(this.cephfs);
      }

      return this;
   }

   public boolean hasCephfs() {
      return this.cephfs != null;
   }

   public CephfsNested withNewCephfs() {
      return new CephfsNested((CephFSVolumeSource)null);
   }

   public CephfsNested withNewCephfsLike(CephFSVolumeSource item) {
      return new CephfsNested(item);
   }

   public CephfsNested editCephfs() {
      return this.withNewCephfsLike((CephFSVolumeSource)Optional.ofNullable(this.buildCephfs()).orElse((Object)null));
   }

   public CephfsNested editOrNewCephfs() {
      return this.withNewCephfsLike((CephFSVolumeSource)Optional.ofNullable(this.buildCephfs()).orElse((new CephFSVolumeSourceBuilder()).build()));
   }

   public CephfsNested editOrNewCephfsLike(CephFSVolumeSource item) {
      return this.withNewCephfsLike((CephFSVolumeSource)Optional.ofNullable(this.buildCephfs()).orElse(item));
   }

   public CinderVolumeSource buildCinder() {
      return this.cinder != null ? this.cinder.build() : null;
   }

   public VolumeFluent withCinder(CinderVolumeSource cinder) {
      this._visitables.remove("cinder");
      if (cinder != null) {
         this.cinder = new CinderVolumeSourceBuilder(cinder);
         this._visitables.get("cinder").add(this.cinder);
      } else {
         this.cinder = null;
         this._visitables.get("cinder").remove(this.cinder);
      }

      return this;
   }

   public boolean hasCinder() {
      return this.cinder != null;
   }

   public CinderNested withNewCinder() {
      return new CinderNested((CinderVolumeSource)null);
   }

   public CinderNested withNewCinderLike(CinderVolumeSource item) {
      return new CinderNested(item);
   }

   public CinderNested editCinder() {
      return this.withNewCinderLike((CinderVolumeSource)Optional.ofNullable(this.buildCinder()).orElse((Object)null));
   }

   public CinderNested editOrNewCinder() {
      return this.withNewCinderLike((CinderVolumeSource)Optional.ofNullable(this.buildCinder()).orElse((new CinderVolumeSourceBuilder()).build()));
   }

   public CinderNested editOrNewCinderLike(CinderVolumeSource item) {
      return this.withNewCinderLike((CinderVolumeSource)Optional.ofNullable(this.buildCinder()).orElse(item));
   }

   public ConfigMapVolumeSource buildConfigMap() {
      return this.configMap != null ? this.configMap.build() : null;
   }

   public VolumeFluent withConfigMap(ConfigMapVolumeSource configMap) {
      this._visitables.remove("configMap");
      if (configMap != null) {
         this.configMap = new ConfigMapVolumeSourceBuilder(configMap);
         this._visitables.get("configMap").add(this.configMap);
      } else {
         this.configMap = null;
         this._visitables.get("configMap").remove(this.configMap);
      }

      return this;
   }

   public boolean hasConfigMap() {
      return this.configMap != null;
   }

   public ConfigMapNested withNewConfigMap() {
      return new ConfigMapNested((ConfigMapVolumeSource)null);
   }

   public ConfigMapNested withNewConfigMapLike(ConfigMapVolumeSource item) {
      return new ConfigMapNested(item);
   }

   public ConfigMapNested editConfigMap() {
      return this.withNewConfigMapLike((ConfigMapVolumeSource)Optional.ofNullable(this.buildConfigMap()).orElse((Object)null));
   }

   public ConfigMapNested editOrNewConfigMap() {
      return this.withNewConfigMapLike((ConfigMapVolumeSource)Optional.ofNullable(this.buildConfigMap()).orElse((new ConfigMapVolumeSourceBuilder()).build()));
   }

   public ConfigMapNested editOrNewConfigMapLike(ConfigMapVolumeSource item) {
      return this.withNewConfigMapLike((ConfigMapVolumeSource)Optional.ofNullable(this.buildConfigMap()).orElse(item));
   }

   public CSIVolumeSource buildCsi() {
      return this.csi != null ? this.csi.build() : null;
   }

   public VolumeFluent withCsi(CSIVolumeSource csi) {
      this._visitables.remove("csi");
      if (csi != null) {
         this.csi = new CSIVolumeSourceBuilder(csi);
         this._visitables.get("csi").add(this.csi);
      } else {
         this.csi = null;
         this._visitables.get("csi").remove(this.csi);
      }

      return this;
   }

   public boolean hasCsi() {
      return this.csi != null;
   }

   public CsiNested withNewCsi() {
      return new CsiNested((CSIVolumeSource)null);
   }

   public CsiNested withNewCsiLike(CSIVolumeSource item) {
      return new CsiNested(item);
   }

   public CsiNested editCsi() {
      return this.withNewCsiLike((CSIVolumeSource)Optional.ofNullable(this.buildCsi()).orElse((Object)null));
   }

   public CsiNested editOrNewCsi() {
      return this.withNewCsiLike((CSIVolumeSource)Optional.ofNullable(this.buildCsi()).orElse((new CSIVolumeSourceBuilder()).build()));
   }

   public CsiNested editOrNewCsiLike(CSIVolumeSource item) {
      return this.withNewCsiLike((CSIVolumeSource)Optional.ofNullable(this.buildCsi()).orElse(item));
   }

   public DownwardAPIVolumeSource buildDownwardAPI() {
      return this.downwardAPI != null ? this.downwardAPI.build() : null;
   }

   public VolumeFluent withDownwardAPI(DownwardAPIVolumeSource downwardAPI) {
      this._visitables.remove("downwardAPI");
      if (downwardAPI != null) {
         this.downwardAPI = new DownwardAPIVolumeSourceBuilder(downwardAPI);
         this._visitables.get("downwardAPI").add(this.downwardAPI);
      } else {
         this.downwardAPI = null;
         this._visitables.get("downwardAPI").remove(this.downwardAPI);
      }

      return this;
   }

   public boolean hasDownwardAPI() {
      return this.downwardAPI != null;
   }

   public DownwardAPINested withNewDownwardAPI() {
      return new DownwardAPINested((DownwardAPIVolumeSource)null);
   }

   public DownwardAPINested withNewDownwardAPILike(DownwardAPIVolumeSource item) {
      return new DownwardAPINested(item);
   }

   public DownwardAPINested editDownwardAPI() {
      return this.withNewDownwardAPILike((DownwardAPIVolumeSource)Optional.ofNullable(this.buildDownwardAPI()).orElse((Object)null));
   }

   public DownwardAPINested editOrNewDownwardAPI() {
      return this.withNewDownwardAPILike((DownwardAPIVolumeSource)Optional.ofNullable(this.buildDownwardAPI()).orElse((new DownwardAPIVolumeSourceBuilder()).build()));
   }

   public DownwardAPINested editOrNewDownwardAPILike(DownwardAPIVolumeSource item) {
      return this.withNewDownwardAPILike((DownwardAPIVolumeSource)Optional.ofNullable(this.buildDownwardAPI()).orElse(item));
   }

   public EmptyDirVolumeSource buildEmptyDir() {
      return this.emptyDir != null ? this.emptyDir.build() : null;
   }

   public VolumeFluent withEmptyDir(EmptyDirVolumeSource emptyDir) {
      this._visitables.remove("emptyDir");
      if (emptyDir != null) {
         this.emptyDir = new EmptyDirVolumeSourceBuilder(emptyDir);
         this._visitables.get("emptyDir").add(this.emptyDir);
      } else {
         this.emptyDir = null;
         this._visitables.get("emptyDir").remove(this.emptyDir);
      }

      return this;
   }

   public boolean hasEmptyDir() {
      return this.emptyDir != null;
   }

   public EmptyDirNested withNewEmptyDir() {
      return new EmptyDirNested((EmptyDirVolumeSource)null);
   }

   public EmptyDirNested withNewEmptyDirLike(EmptyDirVolumeSource item) {
      return new EmptyDirNested(item);
   }

   public EmptyDirNested editEmptyDir() {
      return this.withNewEmptyDirLike((EmptyDirVolumeSource)Optional.ofNullable(this.buildEmptyDir()).orElse((Object)null));
   }

   public EmptyDirNested editOrNewEmptyDir() {
      return this.withNewEmptyDirLike((EmptyDirVolumeSource)Optional.ofNullable(this.buildEmptyDir()).orElse((new EmptyDirVolumeSourceBuilder()).build()));
   }

   public EmptyDirNested editOrNewEmptyDirLike(EmptyDirVolumeSource item) {
      return this.withNewEmptyDirLike((EmptyDirVolumeSource)Optional.ofNullable(this.buildEmptyDir()).orElse(item));
   }

   public EphemeralVolumeSource buildEphemeral() {
      return this.ephemeral != null ? this.ephemeral.build() : null;
   }

   public VolumeFluent withEphemeral(EphemeralVolumeSource ephemeral) {
      this._visitables.remove("ephemeral");
      if (ephemeral != null) {
         this.ephemeral = new EphemeralVolumeSourceBuilder(ephemeral);
         this._visitables.get("ephemeral").add(this.ephemeral);
      } else {
         this.ephemeral = null;
         this._visitables.get("ephemeral").remove(this.ephemeral);
      }

      return this;
   }

   public boolean hasEphemeral() {
      return this.ephemeral != null;
   }

   public EphemeralNested withNewEphemeral() {
      return new EphemeralNested((EphemeralVolumeSource)null);
   }

   public EphemeralNested withNewEphemeralLike(EphemeralVolumeSource item) {
      return new EphemeralNested(item);
   }

   public EphemeralNested editEphemeral() {
      return this.withNewEphemeralLike((EphemeralVolumeSource)Optional.ofNullable(this.buildEphemeral()).orElse((Object)null));
   }

   public EphemeralNested editOrNewEphemeral() {
      return this.withNewEphemeralLike((EphemeralVolumeSource)Optional.ofNullable(this.buildEphemeral()).orElse((new EphemeralVolumeSourceBuilder()).build()));
   }

   public EphemeralNested editOrNewEphemeralLike(EphemeralVolumeSource item) {
      return this.withNewEphemeralLike((EphemeralVolumeSource)Optional.ofNullable(this.buildEphemeral()).orElse(item));
   }

   public FCVolumeSource buildFc() {
      return this.fc != null ? this.fc.build() : null;
   }

   public VolumeFluent withFc(FCVolumeSource fc) {
      this._visitables.remove("fc");
      if (fc != null) {
         this.fc = new FCVolumeSourceBuilder(fc);
         this._visitables.get("fc").add(this.fc);
      } else {
         this.fc = null;
         this._visitables.get("fc").remove(this.fc);
      }

      return this;
   }

   public boolean hasFc() {
      return this.fc != null;
   }

   public FcNested withNewFc() {
      return new FcNested((FCVolumeSource)null);
   }

   public FcNested withNewFcLike(FCVolumeSource item) {
      return new FcNested(item);
   }

   public FcNested editFc() {
      return this.withNewFcLike((FCVolumeSource)Optional.ofNullable(this.buildFc()).orElse((Object)null));
   }

   public FcNested editOrNewFc() {
      return this.withNewFcLike((FCVolumeSource)Optional.ofNullable(this.buildFc()).orElse((new FCVolumeSourceBuilder()).build()));
   }

   public FcNested editOrNewFcLike(FCVolumeSource item) {
      return this.withNewFcLike((FCVolumeSource)Optional.ofNullable(this.buildFc()).orElse(item));
   }

   public FlexVolumeSource buildFlexVolume() {
      return this.flexVolume != null ? this.flexVolume.build() : null;
   }

   public VolumeFluent withFlexVolume(FlexVolumeSource flexVolume) {
      this._visitables.remove("flexVolume");
      if (flexVolume != null) {
         this.flexVolume = new FlexVolumeSourceBuilder(flexVolume);
         this._visitables.get("flexVolume").add(this.flexVolume);
      } else {
         this.flexVolume = null;
         this._visitables.get("flexVolume").remove(this.flexVolume);
      }

      return this;
   }

   public boolean hasFlexVolume() {
      return this.flexVolume != null;
   }

   public FlexVolumeNested withNewFlexVolume() {
      return new FlexVolumeNested((FlexVolumeSource)null);
   }

   public FlexVolumeNested withNewFlexVolumeLike(FlexVolumeSource item) {
      return new FlexVolumeNested(item);
   }

   public FlexVolumeNested editFlexVolume() {
      return this.withNewFlexVolumeLike((FlexVolumeSource)Optional.ofNullable(this.buildFlexVolume()).orElse((Object)null));
   }

   public FlexVolumeNested editOrNewFlexVolume() {
      return this.withNewFlexVolumeLike((FlexVolumeSource)Optional.ofNullable(this.buildFlexVolume()).orElse((new FlexVolumeSourceBuilder()).build()));
   }

   public FlexVolumeNested editOrNewFlexVolumeLike(FlexVolumeSource item) {
      return this.withNewFlexVolumeLike((FlexVolumeSource)Optional.ofNullable(this.buildFlexVolume()).orElse(item));
   }

   public FlockerVolumeSource buildFlocker() {
      return this.flocker != null ? this.flocker.build() : null;
   }

   public VolumeFluent withFlocker(FlockerVolumeSource flocker) {
      this._visitables.remove("flocker");
      if (flocker != null) {
         this.flocker = new FlockerVolumeSourceBuilder(flocker);
         this._visitables.get("flocker").add(this.flocker);
      } else {
         this.flocker = null;
         this._visitables.get("flocker").remove(this.flocker);
      }

      return this;
   }

   public boolean hasFlocker() {
      return this.flocker != null;
   }

   public VolumeFluent withNewFlocker(String datasetName, String datasetUUID) {
      return this.withFlocker(new FlockerVolumeSource(datasetName, datasetUUID));
   }

   public FlockerNested withNewFlocker() {
      return new FlockerNested((FlockerVolumeSource)null);
   }

   public FlockerNested withNewFlockerLike(FlockerVolumeSource item) {
      return new FlockerNested(item);
   }

   public FlockerNested editFlocker() {
      return this.withNewFlockerLike((FlockerVolumeSource)Optional.ofNullable(this.buildFlocker()).orElse((Object)null));
   }

   public FlockerNested editOrNewFlocker() {
      return this.withNewFlockerLike((FlockerVolumeSource)Optional.ofNullable(this.buildFlocker()).orElse((new FlockerVolumeSourceBuilder()).build()));
   }

   public FlockerNested editOrNewFlockerLike(FlockerVolumeSource item) {
      return this.withNewFlockerLike((FlockerVolumeSource)Optional.ofNullable(this.buildFlocker()).orElse(item));
   }

   public GCEPersistentDiskVolumeSource buildGcePersistentDisk() {
      return this.gcePersistentDisk != null ? this.gcePersistentDisk.build() : null;
   }

   public VolumeFluent withGcePersistentDisk(GCEPersistentDiskVolumeSource gcePersistentDisk) {
      this._visitables.remove("gcePersistentDisk");
      if (gcePersistentDisk != null) {
         this.gcePersistentDisk = new GCEPersistentDiskVolumeSourceBuilder(gcePersistentDisk);
         this._visitables.get("gcePersistentDisk").add(this.gcePersistentDisk);
      } else {
         this.gcePersistentDisk = null;
         this._visitables.get("gcePersistentDisk").remove(this.gcePersistentDisk);
      }

      return this;
   }

   public boolean hasGcePersistentDisk() {
      return this.gcePersistentDisk != null;
   }

   public VolumeFluent withNewGcePersistentDisk(String fsType, Integer partition, String pdName, Boolean readOnly) {
      return this.withGcePersistentDisk(new GCEPersistentDiskVolumeSource(fsType, partition, pdName, readOnly));
   }

   public GcePersistentDiskNested withNewGcePersistentDisk() {
      return new GcePersistentDiskNested((GCEPersistentDiskVolumeSource)null);
   }

   public GcePersistentDiskNested withNewGcePersistentDiskLike(GCEPersistentDiskVolumeSource item) {
      return new GcePersistentDiskNested(item);
   }

   public GcePersistentDiskNested editGcePersistentDisk() {
      return this.withNewGcePersistentDiskLike((GCEPersistentDiskVolumeSource)Optional.ofNullable(this.buildGcePersistentDisk()).orElse((Object)null));
   }

   public GcePersistentDiskNested editOrNewGcePersistentDisk() {
      return this.withNewGcePersistentDiskLike((GCEPersistentDiskVolumeSource)Optional.ofNullable(this.buildGcePersistentDisk()).orElse((new GCEPersistentDiskVolumeSourceBuilder()).build()));
   }

   public GcePersistentDiskNested editOrNewGcePersistentDiskLike(GCEPersistentDiskVolumeSource item) {
      return this.withNewGcePersistentDiskLike((GCEPersistentDiskVolumeSource)Optional.ofNullable(this.buildGcePersistentDisk()).orElse(item));
   }

   public GitRepoVolumeSource buildGitRepo() {
      return this.gitRepo != null ? this.gitRepo.build() : null;
   }

   public VolumeFluent withGitRepo(GitRepoVolumeSource gitRepo) {
      this._visitables.remove("gitRepo");
      if (gitRepo != null) {
         this.gitRepo = new GitRepoVolumeSourceBuilder(gitRepo);
         this._visitables.get("gitRepo").add(this.gitRepo);
      } else {
         this.gitRepo = null;
         this._visitables.get("gitRepo").remove(this.gitRepo);
      }

      return this;
   }

   public boolean hasGitRepo() {
      return this.gitRepo != null;
   }

   public VolumeFluent withNewGitRepo(String directory, String repository, String revision) {
      return this.withGitRepo(new GitRepoVolumeSource(directory, repository, revision));
   }

   public GitRepoNested withNewGitRepo() {
      return new GitRepoNested((GitRepoVolumeSource)null);
   }

   public GitRepoNested withNewGitRepoLike(GitRepoVolumeSource item) {
      return new GitRepoNested(item);
   }

   public GitRepoNested editGitRepo() {
      return this.withNewGitRepoLike((GitRepoVolumeSource)Optional.ofNullable(this.buildGitRepo()).orElse((Object)null));
   }

   public GitRepoNested editOrNewGitRepo() {
      return this.withNewGitRepoLike((GitRepoVolumeSource)Optional.ofNullable(this.buildGitRepo()).orElse((new GitRepoVolumeSourceBuilder()).build()));
   }

   public GitRepoNested editOrNewGitRepoLike(GitRepoVolumeSource item) {
      return this.withNewGitRepoLike((GitRepoVolumeSource)Optional.ofNullable(this.buildGitRepo()).orElse(item));
   }

   public GlusterfsVolumeSource buildGlusterfs() {
      return this.glusterfs != null ? this.glusterfs.build() : null;
   }

   public VolumeFluent withGlusterfs(GlusterfsVolumeSource glusterfs) {
      this._visitables.remove("glusterfs");
      if (glusterfs != null) {
         this.glusterfs = new GlusterfsVolumeSourceBuilder(glusterfs);
         this._visitables.get("glusterfs").add(this.glusterfs);
      } else {
         this.glusterfs = null;
         this._visitables.get("glusterfs").remove(this.glusterfs);
      }

      return this;
   }

   public boolean hasGlusterfs() {
      return this.glusterfs != null;
   }

   public VolumeFluent withNewGlusterfs(String endpoints, String path, Boolean readOnly) {
      return this.withGlusterfs(new GlusterfsVolumeSource(endpoints, path, readOnly));
   }

   public GlusterfsNested withNewGlusterfs() {
      return new GlusterfsNested((GlusterfsVolumeSource)null);
   }

   public GlusterfsNested withNewGlusterfsLike(GlusterfsVolumeSource item) {
      return new GlusterfsNested(item);
   }

   public GlusterfsNested editGlusterfs() {
      return this.withNewGlusterfsLike((GlusterfsVolumeSource)Optional.ofNullable(this.buildGlusterfs()).orElse((Object)null));
   }

   public GlusterfsNested editOrNewGlusterfs() {
      return this.withNewGlusterfsLike((GlusterfsVolumeSource)Optional.ofNullable(this.buildGlusterfs()).orElse((new GlusterfsVolumeSourceBuilder()).build()));
   }

   public GlusterfsNested editOrNewGlusterfsLike(GlusterfsVolumeSource item) {
      return this.withNewGlusterfsLike((GlusterfsVolumeSource)Optional.ofNullable(this.buildGlusterfs()).orElse(item));
   }

   public HostPathVolumeSource buildHostPath() {
      return this.hostPath != null ? this.hostPath.build() : null;
   }

   public VolumeFluent withHostPath(HostPathVolumeSource hostPath) {
      this._visitables.remove("hostPath");
      if (hostPath != null) {
         this.hostPath = new HostPathVolumeSourceBuilder(hostPath);
         this._visitables.get("hostPath").add(this.hostPath);
      } else {
         this.hostPath = null;
         this._visitables.get("hostPath").remove(this.hostPath);
      }

      return this;
   }

   public boolean hasHostPath() {
      return this.hostPath != null;
   }

   public VolumeFluent withNewHostPath(String path, String type) {
      return this.withHostPath(new HostPathVolumeSource(path, type));
   }

   public HostPathNested withNewHostPath() {
      return new HostPathNested((HostPathVolumeSource)null);
   }

   public HostPathNested withNewHostPathLike(HostPathVolumeSource item) {
      return new HostPathNested(item);
   }

   public HostPathNested editHostPath() {
      return this.withNewHostPathLike((HostPathVolumeSource)Optional.ofNullable(this.buildHostPath()).orElse((Object)null));
   }

   public HostPathNested editOrNewHostPath() {
      return this.withNewHostPathLike((HostPathVolumeSource)Optional.ofNullable(this.buildHostPath()).orElse((new HostPathVolumeSourceBuilder()).build()));
   }

   public HostPathNested editOrNewHostPathLike(HostPathVolumeSource item) {
      return this.withNewHostPathLike((HostPathVolumeSource)Optional.ofNullable(this.buildHostPath()).orElse(item));
   }

   public ImageVolumeSource buildImage() {
      return this.image != null ? this.image.build() : null;
   }

   public VolumeFluent withImage(ImageVolumeSource image) {
      this._visitables.remove("image");
      if (image != null) {
         this.image = new ImageVolumeSourceBuilder(image);
         this._visitables.get("image").add(this.image);
      } else {
         this.image = null;
         this._visitables.get("image").remove(this.image);
      }

      return this;
   }

   public boolean hasImage() {
      return this.image != null;
   }

   public VolumeFluent withNewImage(String pullPolicy, String reference) {
      return this.withImage(new ImageVolumeSource(pullPolicy, reference));
   }

   public ImageNested withNewImage() {
      return new ImageNested((ImageVolumeSource)null);
   }

   public ImageNested withNewImageLike(ImageVolumeSource item) {
      return new ImageNested(item);
   }

   public ImageNested editImage() {
      return this.withNewImageLike((ImageVolumeSource)Optional.ofNullable(this.buildImage()).orElse((Object)null));
   }

   public ImageNested editOrNewImage() {
      return this.withNewImageLike((ImageVolumeSource)Optional.ofNullable(this.buildImage()).orElse((new ImageVolumeSourceBuilder()).build()));
   }

   public ImageNested editOrNewImageLike(ImageVolumeSource item) {
      return this.withNewImageLike((ImageVolumeSource)Optional.ofNullable(this.buildImage()).orElse(item));
   }

   public ISCSIVolumeSource buildIscsi() {
      return this.iscsi != null ? this.iscsi.build() : null;
   }

   public VolumeFluent withIscsi(ISCSIVolumeSource iscsi) {
      this._visitables.remove("iscsi");
      if (iscsi != null) {
         this.iscsi = new ISCSIVolumeSourceBuilder(iscsi);
         this._visitables.get("iscsi").add(this.iscsi);
      } else {
         this.iscsi = null;
         this._visitables.get("iscsi").remove(this.iscsi);
      }

      return this;
   }

   public boolean hasIscsi() {
      return this.iscsi != null;
   }

   public IscsiNested withNewIscsi() {
      return new IscsiNested((ISCSIVolumeSource)null);
   }

   public IscsiNested withNewIscsiLike(ISCSIVolumeSource item) {
      return new IscsiNested(item);
   }

   public IscsiNested editIscsi() {
      return this.withNewIscsiLike((ISCSIVolumeSource)Optional.ofNullable(this.buildIscsi()).orElse((Object)null));
   }

   public IscsiNested editOrNewIscsi() {
      return this.withNewIscsiLike((ISCSIVolumeSource)Optional.ofNullable(this.buildIscsi()).orElse((new ISCSIVolumeSourceBuilder()).build()));
   }

   public IscsiNested editOrNewIscsiLike(ISCSIVolumeSource item) {
      return this.withNewIscsiLike((ISCSIVolumeSource)Optional.ofNullable(this.buildIscsi()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public VolumeFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public NFSVolumeSource buildNfs() {
      return this.nfs != null ? this.nfs.build() : null;
   }

   public VolumeFluent withNfs(NFSVolumeSource nfs) {
      this._visitables.remove("nfs");
      if (nfs != null) {
         this.nfs = new NFSVolumeSourceBuilder(nfs);
         this._visitables.get("nfs").add(this.nfs);
      } else {
         this.nfs = null;
         this._visitables.get("nfs").remove(this.nfs);
      }

      return this;
   }

   public boolean hasNfs() {
      return this.nfs != null;
   }

   public VolumeFluent withNewNfs(String path, Boolean readOnly, String server) {
      return this.withNfs(new NFSVolumeSource(path, readOnly, server));
   }

   public NfsNested withNewNfs() {
      return new NfsNested((NFSVolumeSource)null);
   }

   public NfsNested withNewNfsLike(NFSVolumeSource item) {
      return new NfsNested(item);
   }

   public NfsNested editNfs() {
      return this.withNewNfsLike((NFSVolumeSource)Optional.ofNullable(this.buildNfs()).orElse((Object)null));
   }

   public NfsNested editOrNewNfs() {
      return this.withNewNfsLike((NFSVolumeSource)Optional.ofNullable(this.buildNfs()).orElse((new NFSVolumeSourceBuilder()).build()));
   }

   public NfsNested editOrNewNfsLike(NFSVolumeSource item) {
      return this.withNewNfsLike((NFSVolumeSource)Optional.ofNullable(this.buildNfs()).orElse(item));
   }

   public PersistentVolumeClaimVolumeSource buildPersistentVolumeClaim() {
      return this.persistentVolumeClaim != null ? this.persistentVolumeClaim.build() : null;
   }

   public VolumeFluent withPersistentVolumeClaim(PersistentVolumeClaimVolumeSource persistentVolumeClaim) {
      this._visitables.remove("persistentVolumeClaim");
      if (persistentVolumeClaim != null) {
         this.persistentVolumeClaim = new PersistentVolumeClaimVolumeSourceBuilder(persistentVolumeClaim);
         this._visitables.get("persistentVolumeClaim").add(this.persistentVolumeClaim);
      } else {
         this.persistentVolumeClaim = null;
         this._visitables.get("persistentVolumeClaim").remove(this.persistentVolumeClaim);
      }

      return this;
   }

   public boolean hasPersistentVolumeClaim() {
      return this.persistentVolumeClaim != null;
   }

   public VolumeFluent withNewPersistentVolumeClaim(String claimName, Boolean readOnly) {
      return this.withPersistentVolumeClaim(new PersistentVolumeClaimVolumeSource(claimName, readOnly));
   }

   public PersistentVolumeClaimNested withNewPersistentVolumeClaim() {
      return new PersistentVolumeClaimNested((PersistentVolumeClaimVolumeSource)null);
   }

   public PersistentVolumeClaimNested withNewPersistentVolumeClaimLike(PersistentVolumeClaimVolumeSource item) {
      return new PersistentVolumeClaimNested(item);
   }

   public PersistentVolumeClaimNested editPersistentVolumeClaim() {
      return this.withNewPersistentVolumeClaimLike((PersistentVolumeClaimVolumeSource)Optional.ofNullable(this.buildPersistentVolumeClaim()).orElse((Object)null));
   }

   public PersistentVolumeClaimNested editOrNewPersistentVolumeClaim() {
      return this.withNewPersistentVolumeClaimLike((PersistentVolumeClaimVolumeSource)Optional.ofNullable(this.buildPersistentVolumeClaim()).orElse((new PersistentVolumeClaimVolumeSourceBuilder()).build()));
   }

   public PersistentVolumeClaimNested editOrNewPersistentVolumeClaimLike(PersistentVolumeClaimVolumeSource item) {
      return this.withNewPersistentVolumeClaimLike((PersistentVolumeClaimVolumeSource)Optional.ofNullable(this.buildPersistentVolumeClaim()).orElse(item));
   }

   public PhotonPersistentDiskVolumeSource buildPhotonPersistentDisk() {
      return this.photonPersistentDisk != null ? this.photonPersistentDisk.build() : null;
   }

   public VolumeFluent withPhotonPersistentDisk(PhotonPersistentDiskVolumeSource photonPersistentDisk) {
      this._visitables.remove("photonPersistentDisk");
      if (photonPersistentDisk != null) {
         this.photonPersistentDisk = new PhotonPersistentDiskVolumeSourceBuilder(photonPersistentDisk);
         this._visitables.get("photonPersistentDisk").add(this.photonPersistentDisk);
      } else {
         this.photonPersistentDisk = null;
         this._visitables.get("photonPersistentDisk").remove(this.photonPersistentDisk);
      }

      return this;
   }

   public boolean hasPhotonPersistentDisk() {
      return this.photonPersistentDisk != null;
   }

   public VolumeFluent withNewPhotonPersistentDisk(String fsType, String pdID) {
      return this.withPhotonPersistentDisk(new PhotonPersistentDiskVolumeSource(fsType, pdID));
   }

   public PhotonPersistentDiskNested withNewPhotonPersistentDisk() {
      return new PhotonPersistentDiskNested((PhotonPersistentDiskVolumeSource)null);
   }

   public PhotonPersistentDiskNested withNewPhotonPersistentDiskLike(PhotonPersistentDiskVolumeSource item) {
      return new PhotonPersistentDiskNested(item);
   }

   public PhotonPersistentDiskNested editPhotonPersistentDisk() {
      return this.withNewPhotonPersistentDiskLike((PhotonPersistentDiskVolumeSource)Optional.ofNullable(this.buildPhotonPersistentDisk()).orElse((Object)null));
   }

   public PhotonPersistentDiskNested editOrNewPhotonPersistentDisk() {
      return this.withNewPhotonPersistentDiskLike((PhotonPersistentDiskVolumeSource)Optional.ofNullable(this.buildPhotonPersistentDisk()).orElse((new PhotonPersistentDiskVolumeSourceBuilder()).build()));
   }

   public PhotonPersistentDiskNested editOrNewPhotonPersistentDiskLike(PhotonPersistentDiskVolumeSource item) {
      return this.withNewPhotonPersistentDiskLike((PhotonPersistentDiskVolumeSource)Optional.ofNullable(this.buildPhotonPersistentDisk()).orElse(item));
   }

   public PortworxVolumeSource buildPortworxVolume() {
      return this.portworxVolume != null ? this.portworxVolume.build() : null;
   }

   public VolumeFluent withPortworxVolume(PortworxVolumeSource portworxVolume) {
      this._visitables.remove("portworxVolume");
      if (portworxVolume != null) {
         this.portworxVolume = new PortworxVolumeSourceBuilder(portworxVolume);
         this._visitables.get("portworxVolume").add(this.portworxVolume);
      } else {
         this.portworxVolume = null;
         this._visitables.get("portworxVolume").remove(this.portworxVolume);
      }

      return this;
   }

   public boolean hasPortworxVolume() {
      return this.portworxVolume != null;
   }

   public VolumeFluent withNewPortworxVolume(String fsType, Boolean readOnly, String volumeID) {
      return this.withPortworxVolume(new PortworxVolumeSource(fsType, readOnly, volumeID));
   }

   public PortworxVolumeNested withNewPortworxVolume() {
      return new PortworxVolumeNested((PortworxVolumeSource)null);
   }

   public PortworxVolumeNested withNewPortworxVolumeLike(PortworxVolumeSource item) {
      return new PortworxVolumeNested(item);
   }

   public PortworxVolumeNested editPortworxVolume() {
      return this.withNewPortworxVolumeLike((PortworxVolumeSource)Optional.ofNullable(this.buildPortworxVolume()).orElse((Object)null));
   }

   public PortworxVolumeNested editOrNewPortworxVolume() {
      return this.withNewPortworxVolumeLike((PortworxVolumeSource)Optional.ofNullable(this.buildPortworxVolume()).orElse((new PortworxVolumeSourceBuilder()).build()));
   }

   public PortworxVolumeNested editOrNewPortworxVolumeLike(PortworxVolumeSource item) {
      return this.withNewPortworxVolumeLike((PortworxVolumeSource)Optional.ofNullable(this.buildPortworxVolume()).orElse(item));
   }

   public ProjectedVolumeSource buildProjected() {
      return this.projected != null ? this.projected.build() : null;
   }

   public VolumeFluent withProjected(ProjectedVolumeSource projected) {
      this._visitables.remove("projected");
      if (projected != null) {
         this.projected = new ProjectedVolumeSourceBuilder(projected);
         this._visitables.get("projected").add(this.projected);
      } else {
         this.projected = null;
         this._visitables.get("projected").remove(this.projected);
      }

      return this;
   }

   public boolean hasProjected() {
      return this.projected != null;
   }

   public ProjectedNested withNewProjected() {
      return new ProjectedNested((ProjectedVolumeSource)null);
   }

   public ProjectedNested withNewProjectedLike(ProjectedVolumeSource item) {
      return new ProjectedNested(item);
   }

   public ProjectedNested editProjected() {
      return this.withNewProjectedLike((ProjectedVolumeSource)Optional.ofNullable(this.buildProjected()).orElse((Object)null));
   }

   public ProjectedNested editOrNewProjected() {
      return this.withNewProjectedLike((ProjectedVolumeSource)Optional.ofNullable(this.buildProjected()).orElse((new ProjectedVolumeSourceBuilder()).build()));
   }

   public ProjectedNested editOrNewProjectedLike(ProjectedVolumeSource item) {
      return this.withNewProjectedLike((ProjectedVolumeSource)Optional.ofNullable(this.buildProjected()).orElse(item));
   }

   public QuobyteVolumeSource buildQuobyte() {
      return this.quobyte != null ? this.quobyte.build() : null;
   }

   public VolumeFluent withQuobyte(QuobyteVolumeSource quobyte) {
      this._visitables.remove("quobyte");
      if (quobyte != null) {
         this.quobyte = new QuobyteVolumeSourceBuilder(quobyte);
         this._visitables.get("quobyte").add(this.quobyte);
      } else {
         this.quobyte = null;
         this._visitables.get("quobyte").remove(this.quobyte);
      }

      return this;
   }

   public boolean hasQuobyte() {
      return this.quobyte != null;
   }

   public QuobyteNested withNewQuobyte() {
      return new QuobyteNested((QuobyteVolumeSource)null);
   }

   public QuobyteNested withNewQuobyteLike(QuobyteVolumeSource item) {
      return new QuobyteNested(item);
   }

   public QuobyteNested editQuobyte() {
      return this.withNewQuobyteLike((QuobyteVolumeSource)Optional.ofNullable(this.buildQuobyte()).orElse((Object)null));
   }

   public QuobyteNested editOrNewQuobyte() {
      return this.withNewQuobyteLike((QuobyteVolumeSource)Optional.ofNullable(this.buildQuobyte()).orElse((new QuobyteVolumeSourceBuilder()).build()));
   }

   public QuobyteNested editOrNewQuobyteLike(QuobyteVolumeSource item) {
      return this.withNewQuobyteLike((QuobyteVolumeSource)Optional.ofNullable(this.buildQuobyte()).orElse(item));
   }

   public RBDVolumeSource buildRbd() {
      return this.rbd != null ? this.rbd.build() : null;
   }

   public VolumeFluent withRbd(RBDVolumeSource rbd) {
      this._visitables.remove("rbd");
      if (rbd != null) {
         this.rbd = new RBDVolumeSourceBuilder(rbd);
         this._visitables.get("rbd").add(this.rbd);
      } else {
         this.rbd = null;
         this._visitables.get("rbd").remove(this.rbd);
      }

      return this;
   }

   public boolean hasRbd() {
      return this.rbd != null;
   }

   public RbdNested withNewRbd() {
      return new RbdNested((RBDVolumeSource)null);
   }

   public RbdNested withNewRbdLike(RBDVolumeSource item) {
      return new RbdNested(item);
   }

   public RbdNested editRbd() {
      return this.withNewRbdLike((RBDVolumeSource)Optional.ofNullable(this.buildRbd()).orElse((Object)null));
   }

   public RbdNested editOrNewRbd() {
      return this.withNewRbdLike((RBDVolumeSource)Optional.ofNullable(this.buildRbd()).orElse((new RBDVolumeSourceBuilder()).build()));
   }

   public RbdNested editOrNewRbdLike(RBDVolumeSource item) {
      return this.withNewRbdLike((RBDVolumeSource)Optional.ofNullable(this.buildRbd()).orElse(item));
   }

   public ScaleIOVolumeSource buildScaleIO() {
      return this.scaleIO != null ? this.scaleIO.build() : null;
   }

   public VolumeFluent withScaleIO(ScaleIOVolumeSource scaleIO) {
      this._visitables.remove("scaleIO");
      if (scaleIO != null) {
         this.scaleIO = new ScaleIOVolumeSourceBuilder(scaleIO);
         this._visitables.get("scaleIO").add(this.scaleIO);
      } else {
         this.scaleIO = null;
         this._visitables.get("scaleIO").remove(this.scaleIO);
      }

      return this;
   }

   public boolean hasScaleIO() {
      return this.scaleIO != null;
   }

   public ScaleIONested withNewScaleIO() {
      return new ScaleIONested((ScaleIOVolumeSource)null);
   }

   public ScaleIONested withNewScaleIOLike(ScaleIOVolumeSource item) {
      return new ScaleIONested(item);
   }

   public ScaleIONested editScaleIO() {
      return this.withNewScaleIOLike((ScaleIOVolumeSource)Optional.ofNullable(this.buildScaleIO()).orElse((Object)null));
   }

   public ScaleIONested editOrNewScaleIO() {
      return this.withNewScaleIOLike((ScaleIOVolumeSource)Optional.ofNullable(this.buildScaleIO()).orElse((new ScaleIOVolumeSourceBuilder()).build()));
   }

   public ScaleIONested editOrNewScaleIOLike(ScaleIOVolumeSource item) {
      return this.withNewScaleIOLike((ScaleIOVolumeSource)Optional.ofNullable(this.buildScaleIO()).orElse(item));
   }

   public SecretVolumeSource buildSecret() {
      return this.secret != null ? this.secret.build() : null;
   }

   public VolumeFluent withSecret(SecretVolumeSource secret) {
      this._visitables.remove("secret");
      if (secret != null) {
         this.secret = new SecretVolumeSourceBuilder(secret);
         this._visitables.get("secret").add(this.secret);
      } else {
         this.secret = null;
         this._visitables.get("secret").remove(this.secret);
      }

      return this;
   }

   public boolean hasSecret() {
      return this.secret != null;
   }

   public SecretNested withNewSecret() {
      return new SecretNested((SecretVolumeSource)null);
   }

   public SecretNested withNewSecretLike(SecretVolumeSource item) {
      return new SecretNested(item);
   }

   public SecretNested editSecret() {
      return this.withNewSecretLike((SecretVolumeSource)Optional.ofNullable(this.buildSecret()).orElse((Object)null));
   }

   public SecretNested editOrNewSecret() {
      return this.withNewSecretLike((SecretVolumeSource)Optional.ofNullable(this.buildSecret()).orElse((new SecretVolumeSourceBuilder()).build()));
   }

   public SecretNested editOrNewSecretLike(SecretVolumeSource item) {
      return this.withNewSecretLike((SecretVolumeSource)Optional.ofNullable(this.buildSecret()).orElse(item));
   }

   public StorageOSVolumeSource buildStorageos() {
      return this.storageos != null ? this.storageos.build() : null;
   }

   public VolumeFluent withStorageos(StorageOSVolumeSource storageos) {
      this._visitables.remove("storageos");
      if (storageos != null) {
         this.storageos = new StorageOSVolumeSourceBuilder(storageos);
         this._visitables.get("storageos").add(this.storageos);
      } else {
         this.storageos = null;
         this._visitables.get("storageos").remove(this.storageos);
      }

      return this;
   }

   public boolean hasStorageos() {
      return this.storageos != null;
   }

   public StorageosNested withNewStorageos() {
      return new StorageosNested((StorageOSVolumeSource)null);
   }

   public StorageosNested withNewStorageosLike(StorageOSVolumeSource item) {
      return new StorageosNested(item);
   }

   public StorageosNested editStorageos() {
      return this.withNewStorageosLike((StorageOSVolumeSource)Optional.ofNullable(this.buildStorageos()).orElse((Object)null));
   }

   public StorageosNested editOrNewStorageos() {
      return this.withNewStorageosLike((StorageOSVolumeSource)Optional.ofNullable(this.buildStorageos()).orElse((new StorageOSVolumeSourceBuilder()).build()));
   }

   public StorageosNested editOrNewStorageosLike(StorageOSVolumeSource item) {
      return this.withNewStorageosLike((StorageOSVolumeSource)Optional.ofNullable(this.buildStorageos()).orElse(item));
   }

   public VsphereVirtualDiskVolumeSource buildVsphereVolume() {
      return this.vsphereVolume != null ? this.vsphereVolume.build() : null;
   }

   public VolumeFluent withVsphereVolume(VsphereVirtualDiskVolumeSource vsphereVolume) {
      this._visitables.remove("vsphereVolume");
      if (vsphereVolume != null) {
         this.vsphereVolume = new VsphereVirtualDiskVolumeSourceBuilder(vsphereVolume);
         this._visitables.get("vsphereVolume").add(this.vsphereVolume);
      } else {
         this.vsphereVolume = null;
         this._visitables.get("vsphereVolume").remove(this.vsphereVolume);
      }

      return this;
   }

   public boolean hasVsphereVolume() {
      return this.vsphereVolume != null;
   }

   public VolumeFluent withNewVsphereVolume(String fsType, String storagePolicyID, String storagePolicyName, String volumePath) {
      return this.withVsphereVolume(new VsphereVirtualDiskVolumeSource(fsType, storagePolicyID, storagePolicyName, volumePath));
   }

   public VsphereVolumeNested withNewVsphereVolume() {
      return new VsphereVolumeNested((VsphereVirtualDiskVolumeSource)null);
   }

   public VsphereVolumeNested withNewVsphereVolumeLike(VsphereVirtualDiskVolumeSource item) {
      return new VsphereVolumeNested(item);
   }

   public VsphereVolumeNested editVsphereVolume() {
      return this.withNewVsphereVolumeLike((VsphereVirtualDiskVolumeSource)Optional.ofNullable(this.buildVsphereVolume()).orElse((Object)null));
   }

   public VsphereVolumeNested editOrNewVsphereVolume() {
      return this.withNewVsphereVolumeLike((VsphereVirtualDiskVolumeSource)Optional.ofNullable(this.buildVsphereVolume()).orElse((new VsphereVirtualDiskVolumeSourceBuilder()).build()));
   }

   public VsphereVolumeNested editOrNewVsphereVolumeLike(VsphereVirtualDiskVolumeSource item) {
      return this.withNewVsphereVolumeLike((VsphereVirtualDiskVolumeSource)Optional.ofNullable(this.buildVsphereVolume()).orElse(item));
   }

   public VolumeFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeFluent that = (VolumeFluent)o;
            if (!Objects.equals(this.awsElasticBlockStore, that.awsElasticBlockStore)) {
               return false;
            } else if (!Objects.equals(this.azureDisk, that.azureDisk)) {
               return false;
            } else if (!Objects.equals(this.azureFile, that.azureFile)) {
               return false;
            } else if (!Objects.equals(this.cephfs, that.cephfs)) {
               return false;
            } else if (!Objects.equals(this.cinder, that.cinder)) {
               return false;
            } else if (!Objects.equals(this.configMap, that.configMap)) {
               return false;
            } else if (!Objects.equals(this.csi, that.csi)) {
               return false;
            } else if (!Objects.equals(this.downwardAPI, that.downwardAPI)) {
               return false;
            } else if (!Objects.equals(this.emptyDir, that.emptyDir)) {
               return false;
            } else if (!Objects.equals(this.ephemeral, that.ephemeral)) {
               return false;
            } else if (!Objects.equals(this.fc, that.fc)) {
               return false;
            } else if (!Objects.equals(this.flexVolume, that.flexVolume)) {
               return false;
            } else if (!Objects.equals(this.flocker, that.flocker)) {
               return false;
            } else if (!Objects.equals(this.gcePersistentDisk, that.gcePersistentDisk)) {
               return false;
            } else if (!Objects.equals(this.gitRepo, that.gitRepo)) {
               return false;
            } else if (!Objects.equals(this.glusterfs, that.glusterfs)) {
               return false;
            } else if (!Objects.equals(this.hostPath, that.hostPath)) {
               return false;
            } else if (!Objects.equals(this.image, that.image)) {
               return false;
            } else if (!Objects.equals(this.iscsi, that.iscsi)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.nfs, that.nfs)) {
               return false;
            } else if (!Objects.equals(this.persistentVolumeClaim, that.persistentVolumeClaim)) {
               return false;
            } else if (!Objects.equals(this.photonPersistentDisk, that.photonPersistentDisk)) {
               return false;
            } else if (!Objects.equals(this.portworxVolume, that.portworxVolume)) {
               return false;
            } else if (!Objects.equals(this.projected, that.projected)) {
               return false;
            } else if (!Objects.equals(this.quobyte, that.quobyte)) {
               return false;
            } else if (!Objects.equals(this.rbd, that.rbd)) {
               return false;
            } else if (!Objects.equals(this.scaleIO, that.scaleIO)) {
               return false;
            } else if (!Objects.equals(this.secret, that.secret)) {
               return false;
            } else if (!Objects.equals(this.storageos, that.storageos)) {
               return false;
            } else if (!Objects.equals(this.vsphereVolume, that.vsphereVolume)) {
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
      return Objects.hash(new Object[]{this.awsElasticBlockStore, this.azureDisk, this.azureFile, this.cephfs, this.cinder, this.configMap, this.csi, this.downwardAPI, this.emptyDir, this.ephemeral, this.fc, this.flexVolume, this.flocker, this.gcePersistentDisk, this.gitRepo, this.glusterfs, this.hostPath, this.image, this.iscsi, this.name, this.nfs, this.persistentVolumeClaim, this.photonPersistentDisk, this.portworxVolume, this.projected, this.quobyte, this.rbd, this.scaleIO, this.secret, this.storageos, this.vsphereVolume, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.awsElasticBlockStore != null) {
         sb.append("awsElasticBlockStore:");
         sb.append(this.awsElasticBlockStore + ",");
      }

      if (this.azureDisk != null) {
         sb.append("azureDisk:");
         sb.append(this.azureDisk + ",");
      }

      if (this.azureFile != null) {
         sb.append("azureFile:");
         sb.append(this.azureFile + ",");
      }

      if (this.cephfs != null) {
         sb.append("cephfs:");
         sb.append(this.cephfs + ",");
      }

      if (this.cinder != null) {
         sb.append("cinder:");
         sb.append(this.cinder + ",");
      }

      if (this.configMap != null) {
         sb.append("configMap:");
         sb.append(this.configMap + ",");
      }

      if (this.csi != null) {
         sb.append("csi:");
         sb.append(this.csi + ",");
      }

      if (this.downwardAPI != null) {
         sb.append("downwardAPI:");
         sb.append(this.downwardAPI + ",");
      }

      if (this.emptyDir != null) {
         sb.append("emptyDir:");
         sb.append(this.emptyDir + ",");
      }

      if (this.ephemeral != null) {
         sb.append("ephemeral:");
         sb.append(this.ephemeral + ",");
      }

      if (this.fc != null) {
         sb.append("fc:");
         sb.append(this.fc + ",");
      }

      if (this.flexVolume != null) {
         sb.append("flexVolume:");
         sb.append(this.flexVolume + ",");
      }

      if (this.flocker != null) {
         sb.append("flocker:");
         sb.append(this.flocker + ",");
      }

      if (this.gcePersistentDisk != null) {
         sb.append("gcePersistentDisk:");
         sb.append(this.gcePersistentDisk + ",");
      }

      if (this.gitRepo != null) {
         sb.append("gitRepo:");
         sb.append(this.gitRepo + ",");
      }

      if (this.glusterfs != null) {
         sb.append("glusterfs:");
         sb.append(this.glusterfs + ",");
      }

      if (this.hostPath != null) {
         sb.append("hostPath:");
         sb.append(this.hostPath + ",");
      }

      if (this.image != null) {
         sb.append("image:");
         sb.append(this.image + ",");
      }

      if (this.iscsi != null) {
         sb.append("iscsi:");
         sb.append(this.iscsi + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.nfs != null) {
         sb.append("nfs:");
         sb.append(this.nfs + ",");
      }

      if (this.persistentVolumeClaim != null) {
         sb.append("persistentVolumeClaim:");
         sb.append(this.persistentVolumeClaim + ",");
      }

      if (this.photonPersistentDisk != null) {
         sb.append("photonPersistentDisk:");
         sb.append(this.photonPersistentDisk + ",");
      }

      if (this.portworxVolume != null) {
         sb.append("portworxVolume:");
         sb.append(this.portworxVolume + ",");
      }

      if (this.projected != null) {
         sb.append("projected:");
         sb.append(this.projected + ",");
      }

      if (this.quobyte != null) {
         sb.append("quobyte:");
         sb.append(this.quobyte + ",");
      }

      if (this.rbd != null) {
         sb.append("rbd:");
         sb.append(this.rbd + ",");
      }

      if (this.scaleIO != null) {
         sb.append("scaleIO:");
         sb.append(this.scaleIO + ",");
      }

      if (this.secret != null) {
         sb.append("secret:");
         sb.append(this.secret + ",");
      }

      if (this.storageos != null) {
         sb.append("storageos:");
         sb.append(this.storageos + ",");
      }

      if (this.vsphereVolume != null) {
         sb.append("vsphereVolume:");
         sb.append(this.vsphereVolume + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AwsElasticBlockStoreNested extends AWSElasticBlockStoreVolumeSourceFluent implements Nested {
      AWSElasticBlockStoreVolumeSourceBuilder builder;

      AwsElasticBlockStoreNested(AWSElasticBlockStoreVolumeSource item) {
         this.builder = new AWSElasticBlockStoreVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withAwsElasticBlockStore(this.builder.build());
      }

      public Object endAwsElasticBlockStore() {
         return this.and();
      }
   }

   public class AzureDiskNested extends AzureDiskVolumeSourceFluent implements Nested {
      AzureDiskVolumeSourceBuilder builder;

      AzureDiskNested(AzureDiskVolumeSource item) {
         this.builder = new AzureDiskVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withAzureDisk(this.builder.build());
      }

      public Object endAzureDisk() {
         return this.and();
      }
   }

   public class AzureFileNested extends AzureFileVolumeSourceFluent implements Nested {
      AzureFileVolumeSourceBuilder builder;

      AzureFileNested(AzureFileVolumeSource item) {
         this.builder = new AzureFileVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withAzureFile(this.builder.build());
      }

      public Object endAzureFile() {
         return this.and();
      }
   }

   public class CephfsNested extends CephFSVolumeSourceFluent implements Nested {
      CephFSVolumeSourceBuilder builder;

      CephfsNested(CephFSVolumeSource item) {
         this.builder = new CephFSVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withCephfs(this.builder.build());
      }

      public Object endCephfs() {
         return this.and();
      }
   }

   public class CinderNested extends CinderVolumeSourceFluent implements Nested {
      CinderVolumeSourceBuilder builder;

      CinderNested(CinderVolumeSource item) {
         this.builder = new CinderVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withCinder(this.builder.build());
      }

      public Object endCinder() {
         return this.and();
      }
   }

   public class ConfigMapNested extends ConfigMapVolumeSourceFluent implements Nested {
      ConfigMapVolumeSourceBuilder builder;

      ConfigMapNested(ConfigMapVolumeSource item) {
         this.builder = new ConfigMapVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withConfigMap(this.builder.build());
      }

      public Object endConfigMap() {
         return this.and();
      }
   }

   public class CsiNested extends CSIVolumeSourceFluent implements Nested {
      CSIVolumeSourceBuilder builder;

      CsiNested(CSIVolumeSource item) {
         this.builder = new CSIVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withCsi(this.builder.build());
      }

      public Object endCsi() {
         return this.and();
      }
   }

   public class DownwardAPINested extends DownwardAPIVolumeSourceFluent implements Nested {
      DownwardAPIVolumeSourceBuilder builder;

      DownwardAPINested(DownwardAPIVolumeSource item) {
         this.builder = new DownwardAPIVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withDownwardAPI(this.builder.build());
      }

      public Object endDownwardAPI() {
         return this.and();
      }
   }

   public class EmptyDirNested extends EmptyDirVolumeSourceFluent implements Nested {
      EmptyDirVolumeSourceBuilder builder;

      EmptyDirNested(EmptyDirVolumeSource item) {
         this.builder = new EmptyDirVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withEmptyDir(this.builder.build());
      }

      public Object endEmptyDir() {
         return this.and();
      }
   }

   public class EphemeralNested extends EphemeralVolumeSourceFluent implements Nested {
      EphemeralVolumeSourceBuilder builder;

      EphemeralNested(EphemeralVolumeSource item) {
         this.builder = new EphemeralVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withEphemeral(this.builder.build());
      }

      public Object endEphemeral() {
         return this.and();
      }
   }

   public class FcNested extends FCVolumeSourceFluent implements Nested {
      FCVolumeSourceBuilder builder;

      FcNested(FCVolumeSource item) {
         this.builder = new FCVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withFc(this.builder.build());
      }

      public Object endFc() {
         return this.and();
      }
   }

   public class FlexVolumeNested extends FlexVolumeSourceFluent implements Nested {
      FlexVolumeSourceBuilder builder;

      FlexVolumeNested(FlexVolumeSource item) {
         this.builder = new FlexVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withFlexVolume(this.builder.build());
      }

      public Object endFlexVolume() {
         return this.and();
      }
   }

   public class FlockerNested extends FlockerVolumeSourceFluent implements Nested {
      FlockerVolumeSourceBuilder builder;

      FlockerNested(FlockerVolumeSource item) {
         this.builder = new FlockerVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withFlocker(this.builder.build());
      }

      public Object endFlocker() {
         return this.and();
      }
   }

   public class GcePersistentDiskNested extends GCEPersistentDiskVolumeSourceFluent implements Nested {
      GCEPersistentDiskVolumeSourceBuilder builder;

      GcePersistentDiskNested(GCEPersistentDiskVolumeSource item) {
         this.builder = new GCEPersistentDiskVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withGcePersistentDisk(this.builder.build());
      }

      public Object endGcePersistentDisk() {
         return this.and();
      }
   }

   public class GitRepoNested extends GitRepoVolumeSourceFluent implements Nested {
      GitRepoVolumeSourceBuilder builder;

      GitRepoNested(GitRepoVolumeSource item) {
         this.builder = new GitRepoVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withGitRepo(this.builder.build());
      }

      public Object endGitRepo() {
         return this.and();
      }
   }

   public class GlusterfsNested extends GlusterfsVolumeSourceFluent implements Nested {
      GlusterfsVolumeSourceBuilder builder;

      GlusterfsNested(GlusterfsVolumeSource item) {
         this.builder = new GlusterfsVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withGlusterfs(this.builder.build());
      }

      public Object endGlusterfs() {
         return this.and();
      }
   }

   public class HostPathNested extends HostPathVolumeSourceFluent implements Nested {
      HostPathVolumeSourceBuilder builder;

      HostPathNested(HostPathVolumeSource item) {
         this.builder = new HostPathVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withHostPath(this.builder.build());
      }

      public Object endHostPath() {
         return this.and();
      }
   }

   public class ImageNested extends ImageVolumeSourceFluent implements Nested {
      ImageVolumeSourceBuilder builder;

      ImageNested(ImageVolumeSource item) {
         this.builder = new ImageVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withImage(this.builder.build());
      }

      public Object endImage() {
         return this.and();
      }
   }

   public class IscsiNested extends ISCSIVolumeSourceFluent implements Nested {
      ISCSIVolumeSourceBuilder builder;

      IscsiNested(ISCSIVolumeSource item) {
         this.builder = new ISCSIVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withIscsi(this.builder.build());
      }

      public Object endIscsi() {
         return this.and();
      }
   }

   public class NfsNested extends NFSVolumeSourceFluent implements Nested {
      NFSVolumeSourceBuilder builder;

      NfsNested(NFSVolumeSource item) {
         this.builder = new NFSVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withNfs(this.builder.build());
      }

      public Object endNfs() {
         return this.and();
      }
   }

   public class PersistentVolumeClaimNested extends PersistentVolumeClaimVolumeSourceFluent implements Nested {
      PersistentVolumeClaimVolumeSourceBuilder builder;

      PersistentVolumeClaimNested(PersistentVolumeClaimVolumeSource item) {
         this.builder = new PersistentVolumeClaimVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withPersistentVolumeClaim(this.builder.build());
      }

      public Object endPersistentVolumeClaim() {
         return this.and();
      }
   }

   public class PhotonPersistentDiskNested extends PhotonPersistentDiskVolumeSourceFluent implements Nested {
      PhotonPersistentDiskVolumeSourceBuilder builder;

      PhotonPersistentDiskNested(PhotonPersistentDiskVolumeSource item) {
         this.builder = new PhotonPersistentDiskVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withPhotonPersistentDisk(this.builder.build());
      }

      public Object endPhotonPersistentDisk() {
         return this.and();
      }
   }

   public class PortworxVolumeNested extends PortworxVolumeSourceFluent implements Nested {
      PortworxVolumeSourceBuilder builder;

      PortworxVolumeNested(PortworxVolumeSource item) {
         this.builder = new PortworxVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withPortworxVolume(this.builder.build());
      }

      public Object endPortworxVolume() {
         return this.and();
      }
   }

   public class ProjectedNested extends ProjectedVolumeSourceFluent implements Nested {
      ProjectedVolumeSourceBuilder builder;

      ProjectedNested(ProjectedVolumeSource item) {
         this.builder = new ProjectedVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withProjected(this.builder.build());
      }

      public Object endProjected() {
         return this.and();
      }
   }

   public class QuobyteNested extends QuobyteVolumeSourceFluent implements Nested {
      QuobyteVolumeSourceBuilder builder;

      QuobyteNested(QuobyteVolumeSource item) {
         this.builder = new QuobyteVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withQuobyte(this.builder.build());
      }

      public Object endQuobyte() {
         return this.and();
      }
   }

   public class RbdNested extends RBDVolumeSourceFluent implements Nested {
      RBDVolumeSourceBuilder builder;

      RbdNested(RBDVolumeSource item) {
         this.builder = new RBDVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withRbd(this.builder.build());
      }

      public Object endRbd() {
         return this.and();
      }
   }

   public class ScaleIONested extends ScaleIOVolumeSourceFluent implements Nested {
      ScaleIOVolumeSourceBuilder builder;

      ScaleIONested(ScaleIOVolumeSource item) {
         this.builder = new ScaleIOVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withScaleIO(this.builder.build());
      }

      public Object endScaleIO() {
         return this.and();
      }
   }

   public class SecretNested extends SecretVolumeSourceFluent implements Nested {
      SecretVolumeSourceBuilder builder;

      SecretNested(SecretVolumeSource item) {
         this.builder = new SecretVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withSecret(this.builder.build());
      }

      public Object endSecret() {
         return this.and();
      }
   }

   public class StorageosNested extends StorageOSVolumeSourceFluent implements Nested {
      StorageOSVolumeSourceBuilder builder;

      StorageosNested(StorageOSVolumeSource item) {
         this.builder = new StorageOSVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withStorageos(this.builder.build());
      }

      public Object endStorageos() {
         return this.and();
      }
   }

   public class VsphereVolumeNested extends VsphereVirtualDiskVolumeSourceFluent implements Nested {
      VsphereVirtualDiskVolumeSourceBuilder builder;

      VsphereVolumeNested(VsphereVirtualDiskVolumeSource item) {
         this.builder = new VsphereVirtualDiskVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeFluent.this.withVsphereVolume(this.builder.build());
      }

      public Object endVsphereVolume() {
         return this.and();
      }
   }
}
