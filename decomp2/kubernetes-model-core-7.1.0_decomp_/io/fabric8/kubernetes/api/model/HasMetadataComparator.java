package io.fabric8.kubernetes.api.model;

import java.util.Comparator;

public class HasMetadataComparator implements Comparator {
   private Integer getKindValue(String kind) {
      try {
         switch (kind) {
            case "SecurityContextConstraints":
               return 0;
            case "Namespace":
            case "Project":
            case "ProjectRequest":
               return 1;
            case "LimitRange":
               return 2;
            case "ResourceQuota":
               return 3;
            case "RoleBindingRestriction":
               return 4;
            case "Secret":
               return 12;
            case "ServiceAccount":
               return 13;
            case "OAuthClient":
               return 14;
            case "Service":
               return 15;
            case "PolicyBinding":
               return 16;
            case "ClusterPolicyBinding":
               return 17;
            case "Role":
               return 18;
            case "RoleBinding":
               return 19;
            case "PersistentVolume":
               return 20;
            case "PersistentVolumeClaim":
               return 21;
            case "ImageStream":
               return 30;
            case "ImageStreamTag":
               return 31;
            default:
               return 100;
         }
      } catch (IllegalArgumentException var4) {
         return 100;
      }
   }

   public int compare(HasMetadata a, HasMetadata b) {
      if (a != null && b != null) {
         if (a != b && !a.equals(b)) {
            int kindOrderCompare = this.getKindValue(a.getKind()).compareTo(this.getKindValue(b.getKind()));
            if (kindOrderCompare != 0) {
               return kindOrderCompare;
            } else {
               String classNameA = a.getClass().getSimpleName();
               String classNameB = b.getClass().getSimpleName();
               int classCompare = classNameA.compareTo(classNameB);
               return classCompare != 0 ? classCompare : a.getMetadata().getName().compareTo(b.getMetadata().getName());
            }
         } else {
            return 0;
         }
      } else {
         throw new NullPointerException("Cannot compare null HasMetadata objects");
      }
   }
}
