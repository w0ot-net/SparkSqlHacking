package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class PersistentVolumeSpecFluent extends BaseFluent {
   private List accessModes = new ArrayList();
   private AWSElasticBlockStoreVolumeSourceBuilder awsElasticBlockStore;
   private AzureDiskVolumeSourceBuilder azureDisk;
   private AzureFilePersistentVolumeSourceBuilder azureFile;
   private Map capacity;
   private CephFSPersistentVolumeSourceBuilder cephfs;
   private CinderPersistentVolumeSourceBuilder cinder;
   private ObjectReferenceBuilder claimRef;
   private CSIPersistentVolumeSourceBuilder csi;
   private FCVolumeSourceBuilder fc;
   private FlexPersistentVolumeSourceBuilder flexVolume;
   private FlockerVolumeSourceBuilder flocker;
   private GCEPersistentDiskVolumeSourceBuilder gcePersistentDisk;
   private GlusterfsPersistentVolumeSourceBuilder glusterfs;
   private HostPathVolumeSourceBuilder hostPath;
   private ISCSIPersistentVolumeSourceBuilder iscsi;
   private LocalVolumeSourceBuilder local;
   private List mountOptions = new ArrayList();
   private NFSVolumeSourceBuilder nfs;
   private VolumeNodeAffinityBuilder nodeAffinity;
   private String persistentVolumeReclaimPolicy;
   private PhotonPersistentDiskVolumeSourceBuilder photonPersistentDisk;
   private PortworxVolumeSourceBuilder portworxVolume;
   private QuobyteVolumeSourceBuilder quobyte;
   private RBDPersistentVolumeSourceBuilder rbd;
   private ScaleIOPersistentVolumeSourceBuilder scaleIO;
   private String storageClassName;
   private StorageOSPersistentVolumeSourceBuilder storageos;
   private String volumeAttributesClassName;
   private String volumeMode;
   private VsphereVirtualDiskVolumeSourceBuilder vsphereVolume;
   private Map additionalProperties;

   public PersistentVolumeSpecFluent() {
   }

   public PersistentVolumeSpecFluent(PersistentVolumeSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PersistentVolumeSpec instance) {
      instance = instance != null ? instance : new PersistentVolumeSpec();
      if (instance != null) {
         this.withAccessModes(instance.getAccessModes());
         this.withAwsElasticBlockStore(instance.getAwsElasticBlockStore());
         this.withAzureDisk(instance.getAzureDisk());
         this.withAzureFile(instance.getAzureFile());
         this.withCapacity(instance.getCapacity());
         this.withCephfs(instance.getCephfs());
         this.withCinder(instance.getCinder());
         this.withClaimRef(instance.getClaimRef());
         this.withCsi(instance.getCsi());
         this.withFc(instance.getFc());
         this.withFlexVolume(instance.getFlexVolume());
         this.withFlocker(instance.getFlocker());
         this.withGcePersistentDisk(instance.getGcePersistentDisk());
         this.withGlusterfs(instance.getGlusterfs());
         this.withHostPath(instance.getHostPath());
         this.withIscsi(instance.getIscsi());
         this.withLocal(instance.getLocal());
         this.withMountOptions(instance.getMountOptions());
         this.withNfs(instance.getNfs());
         this.withNodeAffinity(instance.getNodeAffinity());
         this.withPersistentVolumeReclaimPolicy(instance.getPersistentVolumeReclaimPolicy());
         this.withPhotonPersistentDisk(instance.getPhotonPersistentDisk());
         this.withPortworxVolume(instance.getPortworxVolume());
         this.withQuobyte(instance.getQuobyte());
         this.withRbd(instance.getRbd());
         this.withScaleIO(instance.getScaleIO());
         this.withStorageClassName(instance.getStorageClassName());
         this.withStorageos(instance.getStorageos());
         this.withVolumeAttributesClassName(instance.getVolumeAttributesClassName());
         this.withVolumeMode(instance.getVolumeMode());
         this.withVsphereVolume(instance.getVsphereVolume());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PersistentVolumeSpecFluent addToAccessModes(int index, String item) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      this.accessModes.add(index, item);
      return this;
   }

   public PersistentVolumeSpecFluent setToAccessModes(int index, String item) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      this.accessModes.set(index, item);
      return this;
   }

   public PersistentVolumeSpecFluent addToAccessModes(String... items) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      for(String item : items) {
         this.accessModes.add(item);
      }

      return this;
   }

   public PersistentVolumeSpecFluent addAllToAccessModes(Collection items) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      for(String item : items) {
         this.accessModes.add(item);
      }

      return this;
   }

   public PersistentVolumeSpecFluent removeFromAccessModes(String... items) {
      if (this.accessModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.accessModes.remove(item);
         }

         return this;
      }
   }

   public PersistentVolumeSpecFluent removeAllFromAccessModes(Collection items) {
      if (this.accessModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.accessModes.remove(item);
         }

         return this;
      }
   }

   public List getAccessModes() {
      return this.accessModes;
   }

   public String getAccessMode(int index) {
      return (String)this.accessModes.get(index);
   }

   public String getFirstAccessMode() {
      return (String)this.accessModes.get(0);
   }

   public String getLastAccessMode() {
      return (String)this.accessModes.get(this.accessModes.size() - 1);
   }

   public String getMatchingAccessMode(Predicate predicate) {
      for(String item : this.accessModes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAccessMode(Predicate predicate) {
      for(String item : this.accessModes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PersistentVolumeSpecFluent withAccessModes(List accessModes) {
      if (accessModes != null) {
         this.accessModes = new ArrayList();

         for(String item : accessModes) {
            this.addToAccessModes(item);
         }
      } else {
         this.accessModes = null;
      }

      return this;
   }

   public PersistentVolumeSpecFluent withAccessModes(String... accessModes) {
      if (this.accessModes != null) {
         this.accessModes.clear();
         this._visitables.remove("accessModes");
      }

      if (accessModes != null) {
         for(String item : accessModes) {
            this.addToAccessModes(item);
         }
      }

      return this;
   }

   public boolean hasAccessModes() {
      return this.accessModes != null && !this.accessModes.isEmpty();
   }

   public AWSElasticBlockStoreVolumeSource buildAwsElasticBlockStore() {
      return this.awsElasticBlockStore != null ? this.awsElasticBlockStore.build() : null;
   }

   public PersistentVolumeSpecFluent withAwsElasticBlockStore(AWSElasticBlockStoreVolumeSource awsElasticBlockStore) {
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

   public PersistentVolumeSpecFluent withNewAwsElasticBlockStore(String fsType, Integer partition, Boolean readOnly, String volumeID) {
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

   public PersistentVolumeSpecFluent withAzureDisk(AzureDiskVolumeSource azureDisk) {
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

   public AzureFilePersistentVolumeSource buildAzureFile() {
      return this.azureFile != null ? this.azureFile.build() : null;
   }

   public PersistentVolumeSpecFluent withAzureFile(AzureFilePersistentVolumeSource azureFile) {
      this._visitables.remove("azureFile");
      if (azureFile != null) {
         this.azureFile = new AzureFilePersistentVolumeSourceBuilder(azureFile);
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

   public PersistentVolumeSpecFluent withNewAzureFile(Boolean readOnly, String secretName, String secretNamespace, String shareName) {
      return this.withAzureFile(new AzureFilePersistentVolumeSource(readOnly, secretName, secretNamespace, shareName));
   }

   public AzureFileNested withNewAzureFile() {
      return new AzureFileNested((AzureFilePersistentVolumeSource)null);
   }

   public AzureFileNested withNewAzureFileLike(AzureFilePersistentVolumeSource item) {
      return new AzureFileNested(item);
   }

   public AzureFileNested editAzureFile() {
      return this.withNewAzureFileLike((AzureFilePersistentVolumeSource)Optional.ofNullable(this.buildAzureFile()).orElse((Object)null));
   }

   public AzureFileNested editOrNewAzureFile() {
      return this.withNewAzureFileLike((AzureFilePersistentVolumeSource)Optional.ofNullable(this.buildAzureFile()).orElse((new AzureFilePersistentVolumeSourceBuilder()).build()));
   }

   public AzureFileNested editOrNewAzureFileLike(AzureFilePersistentVolumeSource item) {
      return this.withNewAzureFileLike((AzureFilePersistentVolumeSource)Optional.ofNullable(this.buildAzureFile()).orElse(item));
   }

   public PersistentVolumeSpecFluent addToCapacity(String key, Quantity value) {
      if (this.capacity == null && key != null && value != null) {
         this.capacity = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.capacity.put(key, value);
      }

      return this;
   }

   public PersistentVolumeSpecFluent addToCapacity(Map map) {
      if (this.capacity == null && map != null) {
         this.capacity = new LinkedHashMap();
      }

      if (map != null) {
         this.capacity.putAll(map);
      }

      return this;
   }

   public PersistentVolumeSpecFluent removeFromCapacity(String key) {
      if (this.capacity == null) {
         return this;
      } else {
         if (key != null && this.capacity != null) {
            this.capacity.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeSpecFluent removeFromCapacity(Map map) {
      if (this.capacity == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.capacity != null) {
                  this.capacity.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getCapacity() {
      return this.capacity;
   }

   public PersistentVolumeSpecFluent withCapacity(Map capacity) {
      if (capacity == null) {
         this.capacity = null;
      } else {
         this.capacity = new LinkedHashMap(capacity);
      }

      return this;
   }

   public boolean hasCapacity() {
      return this.capacity != null;
   }

   public CephFSPersistentVolumeSource buildCephfs() {
      return this.cephfs != null ? this.cephfs.build() : null;
   }

   public PersistentVolumeSpecFluent withCephfs(CephFSPersistentVolumeSource cephfs) {
      this._visitables.remove("cephfs");
      if (cephfs != null) {
         this.cephfs = new CephFSPersistentVolumeSourceBuilder(cephfs);
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
      return new CephfsNested((CephFSPersistentVolumeSource)null);
   }

   public CephfsNested withNewCephfsLike(CephFSPersistentVolumeSource item) {
      return new CephfsNested(item);
   }

   public CephfsNested editCephfs() {
      return this.withNewCephfsLike((CephFSPersistentVolumeSource)Optional.ofNullable(this.buildCephfs()).orElse((Object)null));
   }

   public CephfsNested editOrNewCephfs() {
      return this.withNewCephfsLike((CephFSPersistentVolumeSource)Optional.ofNullable(this.buildCephfs()).orElse((new CephFSPersistentVolumeSourceBuilder()).build()));
   }

   public CephfsNested editOrNewCephfsLike(CephFSPersistentVolumeSource item) {
      return this.withNewCephfsLike((CephFSPersistentVolumeSource)Optional.ofNullable(this.buildCephfs()).orElse(item));
   }

   public CinderPersistentVolumeSource buildCinder() {
      return this.cinder != null ? this.cinder.build() : null;
   }

   public PersistentVolumeSpecFluent withCinder(CinderPersistentVolumeSource cinder) {
      this._visitables.remove("cinder");
      if (cinder != null) {
         this.cinder = new CinderPersistentVolumeSourceBuilder(cinder);
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
      return new CinderNested((CinderPersistentVolumeSource)null);
   }

   public CinderNested withNewCinderLike(CinderPersistentVolumeSource item) {
      return new CinderNested(item);
   }

   public CinderNested editCinder() {
      return this.withNewCinderLike((CinderPersistentVolumeSource)Optional.ofNullable(this.buildCinder()).orElse((Object)null));
   }

   public CinderNested editOrNewCinder() {
      return this.withNewCinderLike((CinderPersistentVolumeSource)Optional.ofNullable(this.buildCinder()).orElse((new CinderPersistentVolumeSourceBuilder()).build()));
   }

   public CinderNested editOrNewCinderLike(CinderPersistentVolumeSource item) {
      return this.withNewCinderLike((CinderPersistentVolumeSource)Optional.ofNullable(this.buildCinder()).orElse(item));
   }

   public ObjectReference buildClaimRef() {
      return this.claimRef != null ? this.claimRef.build() : null;
   }

   public PersistentVolumeSpecFluent withClaimRef(ObjectReference claimRef) {
      this._visitables.remove("claimRef");
      if (claimRef != null) {
         this.claimRef = new ObjectReferenceBuilder(claimRef);
         this._visitables.get("claimRef").add(this.claimRef);
      } else {
         this.claimRef = null;
         this._visitables.get("claimRef").remove(this.claimRef);
      }

      return this;
   }

   public boolean hasClaimRef() {
      return this.claimRef != null;
   }

   public ClaimRefNested withNewClaimRef() {
      return new ClaimRefNested((ObjectReference)null);
   }

   public ClaimRefNested withNewClaimRefLike(ObjectReference item) {
      return new ClaimRefNested(item);
   }

   public ClaimRefNested editClaimRef() {
      return this.withNewClaimRefLike((ObjectReference)Optional.ofNullable(this.buildClaimRef()).orElse((Object)null));
   }

   public ClaimRefNested editOrNewClaimRef() {
      return this.withNewClaimRefLike((ObjectReference)Optional.ofNullable(this.buildClaimRef()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public ClaimRefNested editOrNewClaimRefLike(ObjectReference item) {
      return this.withNewClaimRefLike((ObjectReference)Optional.ofNullable(this.buildClaimRef()).orElse(item));
   }

   public CSIPersistentVolumeSource buildCsi() {
      return this.csi != null ? this.csi.build() : null;
   }

   public PersistentVolumeSpecFluent withCsi(CSIPersistentVolumeSource csi) {
      this._visitables.remove("csi");
      if (csi != null) {
         this.csi = new CSIPersistentVolumeSourceBuilder(csi);
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
      return new CsiNested((CSIPersistentVolumeSource)null);
   }

   public CsiNested withNewCsiLike(CSIPersistentVolumeSource item) {
      return new CsiNested(item);
   }

   public CsiNested editCsi() {
      return this.withNewCsiLike((CSIPersistentVolumeSource)Optional.ofNullable(this.buildCsi()).orElse((Object)null));
   }

   public CsiNested editOrNewCsi() {
      return this.withNewCsiLike((CSIPersistentVolumeSource)Optional.ofNullable(this.buildCsi()).orElse((new CSIPersistentVolumeSourceBuilder()).build()));
   }

   public CsiNested editOrNewCsiLike(CSIPersistentVolumeSource item) {
      return this.withNewCsiLike((CSIPersistentVolumeSource)Optional.ofNullable(this.buildCsi()).orElse(item));
   }

   public FCVolumeSource buildFc() {
      return this.fc != null ? this.fc.build() : null;
   }

   public PersistentVolumeSpecFluent withFc(FCVolumeSource fc) {
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

   public FlexPersistentVolumeSource buildFlexVolume() {
      return this.flexVolume != null ? this.flexVolume.build() : null;
   }

   public PersistentVolumeSpecFluent withFlexVolume(FlexPersistentVolumeSource flexVolume) {
      this._visitables.remove("flexVolume");
      if (flexVolume != null) {
         this.flexVolume = new FlexPersistentVolumeSourceBuilder(flexVolume);
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
      return new FlexVolumeNested((FlexPersistentVolumeSource)null);
   }

   public FlexVolumeNested withNewFlexVolumeLike(FlexPersistentVolumeSource item) {
      return new FlexVolumeNested(item);
   }

   public FlexVolumeNested editFlexVolume() {
      return this.withNewFlexVolumeLike((FlexPersistentVolumeSource)Optional.ofNullable(this.buildFlexVolume()).orElse((Object)null));
   }

   public FlexVolumeNested editOrNewFlexVolume() {
      return this.withNewFlexVolumeLike((FlexPersistentVolumeSource)Optional.ofNullable(this.buildFlexVolume()).orElse((new FlexPersistentVolumeSourceBuilder()).build()));
   }

   public FlexVolumeNested editOrNewFlexVolumeLike(FlexPersistentVolumeSource item) {
      return this.withNewFlexVolumeLike((FlexPersistentVolumeSource)Optional.ofNullable(this.buildFlexVolume()).orElse(item));
   }

   public FlockerVolumeSource buildFlocker() {
      return this.flocker != null ? this.flocker.build() : null;
   }

   public PersistentVolumeSpecFluent withFlocker(FlockerVolumeSource flocker) {
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

   public PersistentVolumeSpecFluent withNewFlocker(String datasetName, String datasetUUID) {
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

   public PersistentVolumeSpecFluent withGcePersistentDisk(GCEPersistentDiskVolumeSource gcePersistentDisk) {
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

   public PersistentVolumeSpecFluent withNewGcePersistentDisk(String fsType, Integer partition, String pdName, Boolean readOnly) {
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

   public GlusterfsPersistentVolumeSource buildGlusterfs() {
      return this.glusterfs != null ? this.glusterfs.build() : null;
   }

   public PersistentVolumeSpecFluent withGlusterfs(GlusterfsPersistentVolumeSource glusterfs) {
      this._visitables.remove("glusterfs");
      if (glusterfs != null) {
         this.glusterfs = new GlusterfsPersistentVolumeSourceBuilder(glusterfs);
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

   public PersistentVolumeSpecFluent withNewGlusterfs(String endpoints, String endpointsNamespace, String path, Boolean readOnly) {
      return this.withGlusterfs(new GlusterfsPersistentVolumeSource(endpoints, endpointsNamespace, path, readOnly));
   }

   public GlusterfsNested withNewGlusterfs() {
      return new GlusterfsNested((GlusterfsPersistentVolumeSource)null);
   }

   public GlusterfsNested withNewGlusterfsLike(GlusterfsPersistentVolumeSource item) {
      return new GlusterfsNested(item);
   }

   public GlusterfsNested editGlusterfs() {
      return this.withNewGlusterfsLike((GlusterfsPersistentVolumeSource)Optional.ofNullable(this.buildGlusterfs()).orElse((Object)null));
   }

   public GlusterfsNested editOrNewGlusterfs() {
      return this.withNewGlusterfsLike((GlusterfsPersistentVolumeSource)Optional.ofNullable(this.buildGlusterfs()).orElse((new GlusterfsPersistentVolumeSourceBuilder()).build()));
   }

   public GlusterfsNested editOrNewGlusterfsLike(GlusterfsPersistentVolumeSource item) {
      return this.withNewGlusterfsLike((GlusterfsPersistentVolumeSource)Optional.ofNullable(this.buildGlusterfs()).orElse(item));
   }

   public HostPathVolumeSource buildHostPath() {
      return this.hostPath != null ? this.hostPath.build() : null;
   }

   public PersistentVolumeSpecFluent withHostPath(HostPathVolumeSource hostPath) {
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

   public PersistentVolumeSpecFluent withNewHostPath(String path, String type) {
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

   public ISCSIPersistentVolumeSource buildIscsi() {
      return this.iscsi != null ? this.iscsi.build() : null;
   }

   public PersistentVolumeSpecFluent withIscsi(ISCSIPersistentVolumeSource iscsi) {
      this._visitables.remove("iscsi");
      if (iscsi != null) {
         this.iscsi = new ISCSIPersistentVolumeSourceBuilder(iscsi);
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
      return new IscsiNested((ISCSIPersistentVolumeSource)null);
   }

   public IscsiNested withNewIscsiLike(ISCSIPersistentVolumeSource item) {
      return new IscsiNested(item);
   }

   public IscsiNested editIscsi() {
      return this.withNewIscsiLike((ISCSIPersistentVolumeSource)Optional.ofNullable(this.buildIscsi()).orElse((Object)null));
   }

   public IscsiNested editOrNewIscsi() {
      return this.withNewIscsiLike((ISCSIPersistentVolumeSource)Optional.ofNullable(this.buildIscsi()).orElse((new ISCSIPersistentVolumeSourceBuilder()).build()));
   }

   public IscsiNested editOrNewIscsiLike(ISCSIPersistentVolumeSource item) {
      return this.withNewIscsiLike((ISCSIPersistentVolumeSource)Optional.ofNullable(this.buildIscsi()).orElse(item));
   }

   public LocalVolumeSource buildLocal() {
      return this.local != null ? this.local.build() : null;
   }

   public PersistentVolumeSpecFluent withLocal(LocalVolumeSource local) {
      this._visitables.remove("local");
      if (local != null) {
         this.local = new LocalVolumeSourceBuilder(local);
         this._visitables.get("local").add(this.local);
      } else {
         this.local = null;
         this._visitables.get("local").remove(this.local);
      }

      return this;
   }

   public boolean hasLocal() {
      return this.local != null;
   }

   public PersistentVolumeSpecFluent withNewLocal(String fsType, String path) {
      return this.withLocal(new LocalVolumeSource(fsType, path));
   }

   public LocalNested withNewLocal() {
      return new LocalNested((LocalVolumeSource)null);
   }

   public LocalNested withNewLocalLike(LocalVolumeSource item) {
      return new LocalNested(item);
   }

   public LocalNested editLocal() {
      return this.withNewLocalLike((LocalVolumeSource)Optional.ofNullable(this.buildLocal()).orElse((Object)null));
   }

   public LocalNested editOrNewLocal() {
      return this.withNewLocalLike((LocalVolumeSource)Optional.ofNullable(this.buildLocal()).orElse((new LocalVolumeSourceBuilder()).build()));
   }

   public LocalNested editOrNewLocalLike(LocalVolumeSource item) {
      return this.withNewLocalLike((LocalVolumeSource)Optional.ofNullable(this.buildLocal()).orElse(item));
   }

   public PersistentVolumeSpecFluent addToMountOptions(int index, String item) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      this.mountOptions.add(index, item);
      return this;
   }

   public PersistentVolumeSpecFluent setToMountOptions(int index, String item) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      this.mountOptions.set(index, item);
      return this;
   }

   public PersistentVolumeSpecFluent addToMountOptions(String... items) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      for(String item : items) {
         this.mountOptions.add(item);
      }

      return this;
   }

   public PersistentVolumeSpecFluent addAllToMountOptions(Collection items) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      for(String item : items) {
         this.mountOptions.add(item);
      }

      return this;
   }

   public PersistentVolumeSpecFluent removeFromMountOptions(String... items) {
      if (this.mountOptions == null) {
         return this;
      } else {
         for(String item : items) {
            this.mountOptions.remove(item);
         }

         return this;
      }
   }

   public PersistentVolumeSpecFluent removeAllFromMountOptions(Collection items) {
      if (this.mountOptions == null) {
         return this;
      } else {
         for(String item : items) {
            this.mountOptions.remove(item);
         }

         return this;
      }
   }

   public List getMountOptions() {
      return this.mountOptions;
   }

   public String getMountOption(int index) {
      return (String)this.mountOptions.get(index);
   }

   public String getFirstMountOption() {
      return (String)this.mountOptions.get(0);
   }

   public String getLastMountOption() {
      return (String)this.mountOptions.get(this.mountOptions.size() - 1);
   }

   public String getMatchingMountOption(Predicate predicate) {
      for(String item : this.mountOptions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingMountOption(Predicate predicate) {
      for(String item : this.mountOptions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PersistentVolumeSpecFluent withMountOptions(List mountOptions) {
      if (mountOptions != null) {
         this.mountOptions = new ArrayList();

         for(String item : mountOptions) {
            this.addToMountOptions(item);
         }
      } else {
         this.mountOptions = null;
      }

      return this;
   }

   public PersistentVolumeSpecFluent withMountOptions(String... mountOptions) {
      if (this.mountOptions != null) {
         this.mountOptions.clear();
         this._visitables.remove("mountOptions");
      }

      if (mountOptions != null) {
         for(String item : mountOptions) {
            this.addToMountOptions(item);
         }
      }

      return this;
   }

   public boolean hasMountOptions() {
      return this.mountOptions != null && !this.mountOptions.isEmpty();
   }

   public NFSVolumeSource buildNfs() {
      return this.nfs != null ? this.nfs.build() : null;
   }

   public PersistentVolumeSpecFluent withNfs(NFSVolumeSource nfs) {
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

   public PersistentVolumeSpecFluent withNewNfs(String path, Boolean readOnly, String server) {
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

   public VolumeNodeAffinity buildNodeAffinity() {
      return this.nodeAffinity != null ? this.nodeAffinity.build() : null;
   }

   public PersistentVolumeSpecFluent withNodeAffinity(VolumeNodeAffinity nodeAffinity) {
      this._visitables.remove("nodeAffinity");
      if (nodeAffinity != null) {
         this.nodeAffinity = new VolumeNodeAffinityBuilder(nodeAffinity);
         this._visitables.get("nodeAffinity").add(this.nodeAffinity);
      } else {
         this.nodeAffinity = null;
         this._visitables.get("nodeAffinity").remove(this.nodeAffinity);
      }

      return this;
   }

   public boolean hasNodeAffinity() {
      return this.nodeAffinity != null;
   }

   public NodeAffinityNested withNewNodeAffinity() {
      return new NodeAffinityNested((VolumeNodeAffinity)null);
   }

   public NodeAffinityNested withNewNodeAffinityLike(VolumeNodeAffinity item) {
      return new NodeAffinityNested(item);
   }

   public NodeAffinityNested editNodeAffinity() {
      return this.withNewNodeAffinityLike((VolumeNodeAffinity)Optional.ofNullable(this.buildNodeAffinity()).orElse((Object)null));
   }

   public NodeAffinityNested editOrNewNodeAffinity() {
      return this.withNewNodeAffinityLike((VolumeNodeAffinity)Optional.ofNullable(this.buildNodeAffinity()).orElse((new VolumeNodeAffinityBuilder()).build()));
   }

   public NodeAffinityNested editOrNewNodeAffinityLike(VolumeNodeAffinity item) {
      return this.withNewNodeAffinityLike((VolumeNodeAffinity)Optional.ofNullable(this.buildNodeAffinity()).orElse(item));
   }

   public String getPersistentVolumeReclaimPolicy() {
      return this.persistentVolumeReclaimPolicy;
   }

   public PersistentVolumeSpecFluent withPersistentVolumeReclaimPolicy(String persistentVolumeReclaimPolicy) {
      this.persistentVolumeReclaimPolicy = persistentVolumeReclaimPolicy;
      return this;
   }

   public boolean hasPersistentVolumeReclaimPolicy() {
      return this.persistentVolumeReclaimPolicy != null;
   }

   public PhotonPersistentDiskVolumeSource buildPhotonPersistentDisk() {
      return this.photonPersistentDisk != null ? this.photonPersistentDisk.build() : null;
   }

   public PersistentVolumeSpecFluent withPhotonPersistentDisk(PhotonPersistentDiskVolumeSource photonPersistentDisk) {
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

   public PersistentVolumeSpecFluent withNewPhotonPersistentDisk(String fsType, String pdID) {
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

   public PersistentVolumeSpecFluent withPortworxVolume(PortworxVolumeSource portworxVolume) {
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

   public PersistentVolumeSpecFluent withNewPortworxVolume(String fsType, Boolean readOnly, String volumeID) {
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

   public QuobyteVolumeSource buildQuobyte() {
      return this.quobyte != null ? this.quobyte.build() : null;
   }

   public PersistentVolumeSpecFluent withQuobyte(QuobyteVolumeSource quobyte) {
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

   public RBDPersistentVolumeSource buildRbd() {
      return this.rbd != null ? this.rbd.build() : null;
   }

   public PersistentVolumeSpecFluent withRbd(RBDPersistentVolumeSource rbd) {
      this._visitables.remove("rbd");
      if (rbd != null) {
         this.rbd = new RBDPersistentVolumeSourceBuilder(rbd);
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
      return new RbdNested((RBDPersistentVolumeSource)null);
   }

   public RbdNested withNewRbdLike(RBDPersistentVolumeSource item) {
      return new RbdNested(item);
   }

   public RbdNested editRbd() {
      return this.withNewRbdLike((RBDPersistentVolumeSource)Optional.ofNullable(this.buildRbd()).orElse((Object)null));
   }

   public RbdNested editOrNewRbd() {
      return this.withNewRbdLike((RBDPersistentVolumeSource)Optional.ofNullable(this.buildRbd()).orElse((new RBDPersistentVolumeSourceBuilder()).build()));
   }

   public RbdNested editOrNewRbdLike(RBDPersistentVolumeSource item) {
      return this.withNewRbdLike((RBDPersistentVolumeSource)Optional.ofNullable(this.buildRbd()).orElse(item));
   }

   public ScaleIOPersistentVolumeSource buildScaleIO() {
      return this.scaleIO != null ? this.scaleIO.build() : null;
   }

   public PersistentVolumeSpecFluent withScaleIO(ScaleIOPersistentVolumeSource scaleIO) {
      this._visitables.remove("scaleIO");
      if (scaleIO != null) {
         this.scaleIO = new ScaleIOPersistentVolumeSourceBuilder(scaleIO);
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
      return new ScaleIONested((ScaleIOPersistentVolumeSource)null);
   }

   public ScaleIONested withNewScaleIOLike(ScaleIOPersistentVolumeSource item) {
      return new ScaleIONested(item);
   }

   public ScaleIONested editScaleIO() {
      return this.withNewScaleIOLike((ScaleIOPersistentVolumeSource)Optional.ofNullable(this.buildScaleIO()).orElse((Object)null));
   }

   public ScaleIONested editOrNewScaleIO() {
      return this.withNewScaleIOLike((ScaleIOPersistentVolumeSource)Optional.ofNullable(this.buildScaleIO()).orElse((new ScaleIOPersistentVolumeSourceBuilder()).build()));
   }

   public ScaleIONested editOrNewScaleIOLike(ScaleIOPersistentVolumeSource item) {
      return this.withNewScaleIOLike((ScaleIOPersistentVolumeSource)Optional.ofNullable(this.buildScaleIO()).orElse(item));
   }

   public String getStorageClassName() {
      return this.storageClassName;
   }

   public PersistentVolumeSpecFluent withStorageClassName(String storageClassName) {
      this.storageClassName = storageClassName;
      return this;
   }

   public boolean hasStorageClassName() {
      return this.storageClassName != null;
   }

   public StorageOSPersistentVolumeSource buildStorageos() {
      return this.storageos != null ? this.storageos.build() : null;
   }

   public PersistentVolumeSpecFluent withStorageos(StorageOSPersistentVolumeSource storageos) {
      this._visitables.remove("storageos");
      if (storageos != null) {
         this.storageos = new StorageOSPersistentVolumeSourceBuilder(storageos);
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
      return new StorageosNested((StorageOSPersistentVolumeSource)null);
   }

   public StorageosNested withNewStorageosLike(StorageOSPersistentVolumeSource item) {
      return new StorageosNested(item);
   }

   public StorageosNested editStorageos() {
      return this.withNewStorageosLike((StorageOSPersistentVolumeSource)Optional.ofNullable(this.buildStorageos()).orElse((Object)null));
   }

   public StorageosNested editOrNewStorageos() {
      return this.withNewStorageosLike((StorageOSPersistentVolumeSource)Optional.ofNullable(this.buildStorageos()).orElse((new StorageOSPersistentVolumeSourceBuilder()).build()));
   }

   public StorageosNested editOrNewStorageosLike(StorageOSPersistentVolumeSource item) {
      return this.withNewStorageosLike((StorageOSPersistentVolumeSource)Optional.ofNullable(this.buildStorageos()).orElse(item));
   }

   public String getVolumeAttributesClassName() {
      return this.volumeAttributesClassName;
   }

   public PersistentVolumeSpecFluent withVolumeAttributesClassName(String volumeAttributesClassName) {
      this.volumeAttributesClassName = volumeAttributesClassName;
      return this;
   }

   public boolean hasVolumeAttributesClassName() {
      return this.volumeAttributesClassName != null;
   }

   public String getVolumeMode() {
      return this.volumeMode;
   }

   public PersistentVolumeSpecFluent withVolumeMode(String volumeMode) {
      this.volumeMode = volumeMode;
      return this;
   }

   public boolean hasVolumeMode() {
      return this.volumeMode != null;
   }

   public VsphereVirtualDiskVolumeSource buildVsphereVolume() {
      return this.vsphereVolume != null ? this.vsphereVolume.build() : null;
   }

   public PersistentVolumeSpecFluent withVsphereVolume(VsphereVirtualDiskVolumeSource vsphereVolume) {
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

   public PersistentVolumeSpecFluent withNewVsphereVolume(String fsType, String storagePolicyID, String storagePolicyName, String volumePath) {
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

   public PersistentVolumeSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PersistentVolumeSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PersistentVolumeSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeSpecFluent removeFromAdditionalProperties(Map map) {
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

   public PersistentVolumeSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            PersistentVolumeSpecFluent that = (PersistentVolumeSpecFluent)o;
            if (!Objects.equals(this.accessModes, that.accessModes)) {
               return false;
            } else if (!Objects.equals(this.awsElasticBlockStore, that.awsElasticBlockStore)) {
               return false;
            } else if (!Objects.equals(this.azureDisk, that.azureDisk)) {
               return false;
            } else if (!Objects.equals(this.azureFile, that.azureFile)) {
               return false;
            } else if (!Objects.equals(this.capacity, that.capacity)) {
               return false;
            } else if (!Objects.equals(this.cephfs, that.cephfs)) {
               return false;
            } else if (!Objects.equals(this.cinder, that.cinder)) {
               return false;
            } else if (!Objects.equals(this.claimRef, that.claimRef)) {
               return false;
            } else if (!Objects.equals(this.csi, that.csi)) {
               return false;
            } else if (!Objects.equals(this.fc, that.fc)) {
               return false;
            } else if (!Objects.equals(this.flexVolume, that.flexVolume)) {
               return false;
            } else if (!Objects.equals(this.flocker, that.flocker)) {
               return false;
            } else if (!Objects.equals(this.gcePersistentDisk, that.gcePersistentDisk)) {
               return false;
            } else if (!Objects.equals(this.glusterfs, that.glusterfs)) {
               return false;
            } else if (!Objects.equals(this.hostPath, that.hostPath)) {
               return false;
            } else if (!Objects.equals(this.iscsi, that.iscsi)) {
               return false;
            } else if (!Objects.equals(this.local, that.local)) {
               return false;
            } else if (!Objects.equals(this.mountOptions, that.mountOptions)) {
               return false;
            } else if (!Objects.equals(this.nfs, that.nfs)) {
               return false;
            } else if (!Objects.equals(this.nodeAffinity, that.nodeAffinity)) {
               return false;
            } else if (!Objects.equals(this.persistentVolumeReclaimPolicy, that.persistentVolumeReclaimPolicy)) {
               return false;
            } else if (!Objects.equals(this.photonPersistentDisk, that.photonPersistentDisk)) {
               return false;
            } else if (!Objects.equals(this.portworxVolume, that.portworxVolume)) {
               return false;
            } else if (!Objects.equals(this.quobyte, that.quobyte)) {
               return false;
            } else if (!Objects.equals(this.rbd, that.rbd)) {
               return false;
            } else if (!Objects.equals(this.scaleIO, that.scaleIO)) {
               return false;
            } else if (!Objects.equals(this.storageClassName, that.storageClassName)) {
               return false;
            } else if (!Objects.equals(this.storageos, that.storageos)) {
               return false;
            } else if (!Objects.equals(this.volumeAttributesClassName, that.volumeAttributesClassName)) {
               return false;
            } else if (!Objects.equals(this.volumeMode, that.volumeMode)) {
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
      return Objects.hash(new Object[]{this.accessModes, this.awsElasticBlockStore, this.azureDisk, this.azureFile, this.capacity, this.cephfs, this.cinder, this.claimRef, this.csi, this.fc, this.flexVolume, this.flocker, this.gcePersistentDisk, this.glusterfs, this.hostPath, this.iscsi, this.local, this.mountOptions, this.nfs, this.nodeAffinity, this.persistentVolumeReclaimPolicy, this.photonPersistentDisk, this.portworxVolume, this.quobyte, this.rbd, this.scaleIO, this.storageClassName, this.storageos, this.volumeAttributesClassName, this.volumeMode, this.vsphereVolume, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.accessModes != null && !this.accessModes.isEmpty()) {
         sb.append("accessModes:");
         sb.append(this.accessModes + ",");
      }

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

      if (this.capacity != null && !this.capacity.isEmpty()) {
         sb.append("capacity:");
         sb.append(this.capacity + ",");
      }

      if (this.cephfs != null) {
         sb.append("cephfs:");
         sb.append(this.cephfs + ",");
      }

      if (this.cinder != null) {
         sb.append("cinder:");
         sb.append(this.cinder + ",");
      }

      if (this.claimRef != null) {
         sb.append("claimRef:");
         sb.append(this.claimRef + ",");
      }

      if (this.csi != null) {
         sb.append("csi:");
         sb.append(this.csi + ",");
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

      if (this.glusterfs != null) {
         sb.append("glusterfs:");
         sb.append(this.glusterfs + ",");
      }

      if (this.hostPath != null) {
         sb.append("hostPath:");
         sb.append(this.hostPath + ",");
      }

      if (this.iscsi != null) {
         sb.append("iscsi:");
         sb.append(this.iscsi + ",");
      }

      if (this.local != null) {
         sb.append("local:");
         sb.append(this.local + ",");
      }

      if (this.mountOptions != null && !this.mountOptions.isEmpty()) {
         sb.append("mountOptions:");
         sb.append(this.mountOptions + ",");
      }

      if (this.nfs != null) {
         sb.append("nfs:");
         sb.append(this.nfs + ",");
      }

      if (this.nodeAffinity != null) {
         sb.append("nodeAffinity:");
         sb.append(this.nodeAffinity + ",");
      }

      if (this.persistentVolumeReclaimPolicy != null) {
         sb.append("persistentVolumeReclaimPolicy:");
         sb.append(this.persistentVolumeReclaimPolicy + ",");
      }

      if (this.photonPersistentDisk != null) {
         sb.append("photonPersistentDisk:");
         sb.append(this.photonPersistentDisk + ",");
      }

      if (this.portworxVolume != null) {
         sb.append("portworxVolume:");
         sb.append(this.portworxVolume + ",");
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

      if (this.storageClassName != null) {
         sb.append("storageClassName:");
         sb.append(this.storageClassName + ",");
      }

      if (this.storageos != null) {
         sb.append("storageos:");
         sb.append(this.storageos + ",");
      }

      if (this.volumeAttributesClassName != null) {
         sb.append("volumeAttributesClassName:");
         sb.append(this.volumeAttributesClassName + ",");
      }

      if (this.volumeMode != null) {
         sb.append("volumeMode:");
         sb.append(this.volumeMode + ",");
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
         return PersistentVolumeSpecFluent.this.withAwsElasticBlockStore(this.builder.build());
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
         return PersistentVolumeSpecFluent.this.withAzureDisk(this.builder.build());
      }

      public Object endAzureDisk() {
         return this.and();
      }
   }

   public class AzureFileNested extends AzureFilePersistentVolumeSourceFluent implements Nested {
      AzureFilePersistentVolumeSourceBuilder builder;

      AzureFileNested(AzureFilePersistentVolumeSource item) {
         this.builder = new AzureFilePersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withAzureFile(this.builder.build());
      }

      public Object endAzureFile() {
         return this.and();
      }
   }

   public class CephfsNested extends CephFSPersistentVolumeSourceFluent implements Nested {
      CephFSPersistentVolumeSourceBuilder builder;

      CephfsNested(CephFSPersistentVolumeSource item) {
         this.builder = new CephFSPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withCephfs(this.builder.build());
      }

      public Object endCephfs() {
         return this.and();
      }
   }

   public class CinderNested extends CinderPersistentVolumeSourceFluent implements Nested {
      CinderPersistentVolumeSourceBuilder builder;

      CinderNested(CinderPersistentVolumeSource item) {
         this.builder = new CinderPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withCinder(this.builder.build());
      }

      public Object endCinder() {
         return this.and();
      }
   }

   public class ClaimRefNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      ClaimRefNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withClaimRef(this.builder.build());
      }

      public Object endClaimRef() {
         return this.and();
      }
   }

   public class CsiNested extends CSIPersistentVolumeSourceFluent implements Nested {
      CSIPersistentVolumeSourceBuilder builder;

      CsiNested(CSIPersistentVolumeSource item) {
         this.builder = new CSIPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withCsi(this.builder.build());
      }

      public Object endCsi() {
         return this.and();
      }
   }

   public class FcNested extends FCVolumeSourceFluent implements Nested {
      FCVolumeSourceBuilder builder;

      FcNested(FCVolumeSource item) {
         this.builder = new FCVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withFc(this.builder.build());
      }

      public Object endFc() {
         return this.and();
      }
   }

   public class FlexVolumeNested extends FlexPersistentVolumeSourceFluent implements Nested {
      FlexPersistentVolumeSourceBuilder builder;

      FlexVolumeNested(FlexPersistentVolumeSource item) {
         this.builder = new FlexPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withFlexVolume(this.builder.build());
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
         return PersistentVolumeSpecFluent.this.withFlocker(this.builder.build());
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
         return PersistentVolumeSpecFluent.this.withGcePersistentDisk(this.builder.build());
      }

      public Object endGcePersistentDisk() {
         return this.and();
      }
   }

   public class GlusterfsNested extends GlusterfsPersistentVolumeSourceFluent implements Nested {
      GlusterfsPersistentVolumeSourceBuilder builder;

      GlusterfsNested(GlusterfsPersistentVolumeSource item) {
         this.builder = new GlusterfsPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withGlusterfs(this.builder.build());
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
         return PersistentVolumeSpecFluent.this.withHostPath(this.builder.build());
      }

      public Object endHostPath() {
         return this.and();
      }
   }

   public class IscsiNested extends ISCSIPersistentVolumeSourceFluent implements Nested {
      ISCSIPersistentVolumeSourceBuilder builder;

      IscsiNested(ISCSIPersistentVolumeSource item) {
         this.builder = new ISCSIPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withIscsi(this.builder.build());
      }

      public Object endIscsi() {
         return this.and();
      }
   }

   public class LocalNested extends LocalVolumeSourceFluent implements Nested {
      LocalVolumeSourceBuilder builder;

      LocalNested(LocalVolumeSource item) {
         this.builder = new LocalVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withLocal(this.builder.build());
      }

      public Object endLocal() {
         return this.and();
      }
   }

   public class NfsNested extends NFSVolumeSourceFluent implements Nested {
      NFSVolumeSourceBuilder builder;

      NfsNested(NFSVolumeSource item) {
         this.builder = new NFSVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withNfs(this.builder.build());
      }

      public Object endNfs() {
         return this.and();
      }
   }

   public class NodeAffinityNested extends VolumeNodeAffinityFluent implements Nested {
      VolumeNodeAffinityBuilder builder;

      NodeAffinityNested(VolumeNodeAffinity item) {
         this.builder = new VolumeNodeAffinityBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withNodeAffinity(this.builder.build());
      }

      public Object endNodeAffinity() {
         return this.and();
      }
   }

   public class PhotonPersistentDiskNested extends PhotonPersistentDiskVolumeSourceFluent implements Nested {
      PhotonPersistentDiskVolumeSourceBuilder builder;

      PhotonPersistentDiskNested(PhotonPersistentDiskVolumeSource item) {
         this.builder = new PhotonPersistentDiskVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withPhotonPersistentDisk(this.builder.build());
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
         return PersistentVolumeSpecFluent.this.withPortworxVolume(this.builder.build());
      }

      public Object endPortworxVolume() {
         return this.and();
      }
   }

   public class QuobyteNested extends QuobyteVolumeSourceFluent implements Nested {
      QuobyteVolumeSourceBuilder builder;

      QuobyteNested(QuobyteVolumeSource item) {
         this.builder = new QuobyteVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withQuobyte(this.builder.build());
      }

      public Object endQuobyte() {
         return this.and();
      }
   }

   public class RbdNested extends RBDPersistentVolumeSourceFluent implements Nested {
      RBDPersistentVolumeSourceBuilder builder;

      RbdNested(RBDPersistentVolumeSource item) {
         this.builder = new RBDPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withRbd(this.builder.build());
      }

      public Object endRbd() {
         return this.and();
      }
   }

   public class ScaleIONested extends ScaleIOPersistentVolumeSourceFluent implements Nested {
      ScaleIOPersistentVolumeSourceBuilder builder;

      ScaleIONested(ScaleIOPersistentVolumeSource item) {
         this.builder = new ScaleIOPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withScaleIO(this.builder.build());
      }

      public Object endScaleIO() {
         return this.and();
      }
   }

   public class StorageosNested extends StorageOSPersistentVolumeSourceFluent implements Nested {
      StorageOSPersistentVolumeSourceBuilder builder;

      StorageosNested(StorageOSPersistentVolumeSource item) {
         this.builder = new StorageOSPersistentVolumeSourceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeSpecFluent.this.withStorageos(this.builder.build());
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
         return PersistentVolumeSpecFluent.this.withVsphereVolume(this.builder.build());
      }

      public Object endVsphereVolume() {
         return this.and();
      }
   }
}
