package org.apache.spark.deploy.k8s;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.MapOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@Unstable
@DeveloperApi
public final class KubernetesVolumeUtils$ {
   public static final KubernetesVolumeUtils$ MODULE$ = new KubernetesVolumeUtils$();

   public Seq parseVolumesWithPrefix(final SparkConf sparkConf, final String prefix) {
      Map properties = .MODULE$.wrapRefArray((Object[])sparkConf.getAllWithPrefix(prefix)).toMap(scala..less.colon.less..MODULE$.refl());
      return ((IterableOnceOps)this.getVolumeTypesAndNames(properties).map((x0$1) -> {
         if (x0$1 != null) {
            String volumeType = (String)x0$1._1();
            String volumeName = (String)x0$1._2();
            String pathKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_MOUNT_PATH_KEY();
            String readOnlyKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_MOUNT_READONLY_KEY();
            String subPathKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_MOUNT_SUBPATH_KEY();
            String subPathExprKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_MOUNT_SUBPATHEXPR_KEY();
            String labelKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_LABEL_KEY();
            String annotationKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_ANNOTATION_KEY();
            MODULE$.verifyMutuallyExclusiveOptionKeys(properties, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{subPathKey, subPathExprKey})));
            Map volumeLabelsMap = (Map)((MapOps)properties.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$parseVolumesWithPrefix$2(labelKey, x$1)))).map((x0$2) -> {
               if (x0$2 != null) {
                  String k = (String)x0$2._1();
                  String v = (String)x0$2._2();
                  return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(k.replaceAll(labelKey, "")), v);
               } else {
                  throw new MatchError(x0$2);
               }
            });
            Map volumeAnnotationsMap = (Map)((MapOps)properties.filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$parseVolumesWithPrefix$4(annotationKey, x$2)))).map((x0$3) -> {
               if (x0$3 != null) {
                  String k = (String)x0$3._1();
                  String v = (String)x0$3._2();
                  return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(k.replaceAll(annotationKey, "")), v);
               } else {
                  throw new MatchError(x0$3);
               }
            });
            return new KubernetesVolumeSpec(volumeName, (String)properties.apply(pathKey), (String)properties.getOrElse(subPathKey, () -> ""), (String)properties.getOrElse(subPathExprKey, () -> ""), properties.get(readOnlyKey).exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$parseVolumesWithPrefix$8(x$3))), MODULE$.parseVolumeSpecificConf(properties, volumeType, volumeName, scala.Option..MODULE$.apply(volumeLabelsMap), scala.Option..MODULE$.apply(volumeAnnotationsMap)));
         } else {
            throw new MatchError(x0$1);
         }
      })).toSeq();
   }

   private Set getVolumeTypesAndNames(final Map properties) {
      return ((IterableOnceOps)properties.keys().flatMap((k) -> {
         List var2 = .MODULE$.wrapRefArray((Object[])scala.collection.StringOps..MODULE$.split$extension(.MODULE$.augmentString(k), '.')).toList();
         if (var2 instanceof scala.collection.immutable..colon.colon var3) {
            String tpe = (String)var3.head();
            List var5 = var3.next$access$1();
            if (var5 instanceof scala.collection.immutable..colon.colon var6) {
               String name = (String)var6.head();
               return new Some(new Tuple2(tpe, name));
            }
         }

         return scala.None..MODULE$;
      })).toSet();
   }

   private KubernetesVolumeSpecificConf parseVolumeSpecificConf(final Map options, final String volumeType, final String volumeName, final Option labels, final Option annotations) {
      label56: {
         String var10000 = Config$.MODULE$.KUBERNETES_VOLUMES_HOSTPATH_TYPE();
         if (var10000 == null) {
            if (volumeType == null) {
               break label56;
            }
         } else if (var10000.equals(volumeType)) {
            break label56;
         }

         label57: {
            var10000 = Config$.MODULE$.KUBERNETES_VOLUMES_PVC_TYPE();
            if (var10000 == null) {
               if (volumeType == null) {
                  break label57;
               }
            } else if (var10000.equals(volumeType)) {
               break label57;
            }

            label58: {
               var10000 = Config$.MODULE$.KUBERNETES_VOLUMES_EMPTYDIR_TYPE();
               if (var10000 == null) {
                  if (volumeType == null) {
                     break label58;
                  }
               } else if (var10000.equals(volumeType)) {
                  break label58;
               }

               label34: {
                  var10000 = Config$.MODULE$.KUBERNETES_VOLUMES_NFS_TYPE();
                  if (var10000 == null) {
                     if (volumeType == null) {
                        break label34;
                     }
                  } else if (var10000.equals(volumeType)) {
                     break label34;
                  }

                  throw new IllegalArgumentException("Kubernetes Volume type `" + volumeType + "` is not supported");
               }

               String pathKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_PATH_KEY();
               String serverKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_SERVER_KEY();
               this.verifyOptionKey(options, pathKey, Config$.MODULE$.KUBERNETES_VOLUMES_NFS_TYPE());
               this.verifyOptionKey(options, serverKey, Config$.MODULE$.KUBERNETES_VOLUMES_NFS_TYPE());
               return new KubernetesNFSVolumeConf((String)options.apply(pathKey), (String)options.apply(serverKey));
            }

            String mediumKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_MEDIUM_KEY();
            String sizeLimitKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_SIZE_LIMIT_KEY();
            this.verifySize(options.get(sizeLimitKey));
            return new KubernetesEmptyDirVolumeConf(options.get(mediumKey), options.get(sizeLimitKey));
         }

         String claimNameKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_CLAIM_NAME_KEY();
         String storageClassKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_CLAIM_STORAGE_CLASS_KEY();
         String sizeLimitKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_SIZE_LIMIT_KEY();
         this.verifyOptionKey(options, claimNameKey, Config$.MODULE$.KUBERNETES_VOLUMES_PVC_TYPE());
         this.verifySize(options.get(sizeLimitKey));
         return new KubernetesPVCVolumeConf((String)options.apply(claimNameKey), options.get(storageClassKey), options.get(sizeLimitKey), labels, annotations);
      }

      String pathKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_PATH_KEY();
      String typeKey = volumeType + "." + volumeName + "." + Config$.MODULE$.KUBERNETES_VOLUMES_OPTIONS_TYPE_KEY();
      this.verifyOptionKey(options, pathKey, Config$.MODULE$.KUBERNETES_VOLUMES_HOSTPATH_TYPE());
      return new KubernetesHostPathVolumeConf((String)options.apply(pathKey), (String)options.getOrElse(typeKey, () -> ""));
   }

   private void verifyOptionKey(final Map options, final String key, final String msg) {
      if (!options.isDefinedAt(key)) {
         throw new NoSuchElementException(key + " is required for " + msg);
      }
   }

   private void verifyMutuallyExclusiveOptionKeys(final Map options, final Seq keys) {
      Seq givenKeys = (Seq)keys.filter((key) -> BoxesRunTime.boxToBoolean($anonfun$verifyMutuallyExclusiveOptionKeys$1(options, key)));
      if (givenKeys.length() > 1) {
         throw new IllegalArgumentException("These config options are mutually exclusive: " + givenKeys.mkString(", "));
      }
   }

   private void verifySize(final Option size) {
      size.foreach((v) -> {
         $anonfun$verifySize$1(v);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseVolumesWithPrefix$2(final String labelKey$1, final Tuple2 x$1) {
      return ((String)x$1._1()).startsWith(labelKey$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseVolumesWithPrefix$4(final String annotationKey$1, final Tuple2 x$2) {
      return ((String)x$2._1()).startsWith(annotationKey$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseVolumesWithPrefix$8(final String x$3) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(.MODULE$.augmentString(x$3));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$verifyMutuallyExclusiveOptionKeys$1(final Map options$1, final String key) {
      return options$1.contains(key);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$verifySize$2(final char x$4) {
      return scala.runtime.RichChar..MODULE$.isDigit$extension(.MODULE$.charWrapper(x$4));
   }

   // $FF: synthetic method
   public static final void $anonfun$verifySize$1(final String v) {
      if (scala.collection.StringOps..MODULE$.forall$extension(.MODULE$.augmentString(v), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$verifySize$2(BoxesRunTime.unboxToChar(x$4)))) && Long.parseLong(v) < 1024L) {
         throw new IllegalArgumentException("Volume size `" + v + "` is smaller than 1KiB. Missing units?");
      }
   }

   private KubernetesVolumeUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
