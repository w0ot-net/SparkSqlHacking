package org.apache.curator.framework.imps;

import java.util.Optional;
import java.util.UUID;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.utils.ZKPaths;

public final class ProtectedUtils {
   @VisibleForTesting
   static final String PROTECTED_PREFIX = "_c_";
   @VisibleForTesting
   static final char PROTECTED_SEPARATOR = '-';
   @VisibleForTesting
   static final int PROTECTED_PREFIX_WITH_UUID_LENGTH = "_c_".length() + 36 + 1;

   private ProtectedUtils() {
      throw new RuntimeException("Protected Utils is a helper class");
   }

   public static String getProtectedPrefix(String protectedId) {
      return "_c_" + protectedId + '-';
   }

   private static String extractProtectedIdInternal(String znodeName) {
      return znodeName.substring("_c_".length(), PROTECTED_PREFIX_WITH_UUID_LENGTH - 1);
   }

   public static boolean isProtectedZNode(String znodeName) {
      if (znodeName.length() > PROTECTED_PREFIX_WITH_UUID_LENGTH && znodeName.startsWith("_c_") && znodeName.charAt(PROTECTED_PREFIX_WITH_UUID_LENGTH - 1) == '-') {
         try {
            UUID.fromString(extractProtectedIdInternal(znodeName));
            return true;
         } catch (IllegalArgumentException var2) {
         }
      }

      return false;
   }

   public static String normalize(String znodeName) {
      return isProtectedZNode(znodeName) ? znodeName.substring(PROTECTED_PREFIX_WITH_UUID_LENGTH) : znodeName;
   }

   public static String normalizePath(String path) {
      ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(path);
      String name = pathAndNode.getNode();
      return isProtectedZNode(name) ? ZKPaths.makePath(pathAndNode.getPath(), normalize(name)) : path;
   }

   public static Optional extractProtectedId(String znodeName) {
      return Optional.ofNullable(isProtectedZNode(znodeName) ? extractProtectedIdInternal(znodeName) : null);
   }

   public static String toProtectedZNode(String znodeName, String protectedId) {
      return protectedId == null ? znodeName : getProtectedPrefix(protectedId) + znodeName;
   }

   public static String toProtectedZNodePath(String path, String protectedId) {
      if (protectedId == null) {
         return path;
      } else {
         ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(path);
         return ZKPaths.makePath(pathAndNode.getPath(), toProtectedZNode(pathAndNode.getNode(), protectedId));
      }
   }
}
