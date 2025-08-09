package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.api.model.ContainerState;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.readiness.Readiness;
import java.util.Collections;
import java.util.List;

public class PodStatusUtil {
   private static final String POD_RUNNING = "Running";
   private static final String POD_INITIALIZING = "PodInitializing";
   private static final String CONTAINER_COMPLETED = "Completed";

   private PodStatusUtil() {
   }

   public static boolean isRunning(Pod pod) {
      if (isInStatus("Running", pod)) {
         return true;
      } else if (!hasDeletionTimestamp(pod) && !isInitializing(pod)) {
         if (!hasRunningContainer(pod)) {
            return false;
         } else {
            return !hasCompletedContainer(pod) || Readiness.isPodReady(pod);
         }
      } else {
         return false;
      }
   }

   private static boolean isInStatus(String expected, Pod pod) {
      if (pod != null && pod.getStatus() != null && expected != null) {
         return expected.equals(pod.getStatus().getPhase()) || expected.equals(pod.getStatus().getReason());
      } else {
         return false;
      }
   }

   private static boolean hasDeletionTimestamp(Pod pod) {
      if (pod == null) {
         return false;
      } else {
         return pod.getMetadata() != null && pod.getMetadata().getDeletionTimestamp() != null;
      }
   }

   public static boolean isInitializing(Pod pod) {
      return pod == null ? false : pod.getStatus().getInitContainerStatuses().stream().anyMatch(PodStatusUtil::isInitializing);
   }

   private static boolean isInitializing(ContainerStatus status) {
      if (status == null) {
         return true;
      } else {
         ContainerState state = status.getState();
         if (state == null) {
            return true;
         } else if (isTerminated(state)) {
            return hasNonNullExitCode(state);
         } else if (isWaiting(state)) {
            return !isWaitingInitializing(state);
         } else {
            return true;
         }
      }
   }

   private static boolean isTerminated(ContainerState state) {
      return state == null || state.getTerminated() != null;
   }

   private static boolean hasNonNullExitCode(ContainerState state) {
      return state.getTerminated() != null && state.getTerminated().getExitCode() != 0;
   }

   private static boolean isWaiting(ContainerState state) {
      return state == null || state.getWaiting() != null;
   }

   private static boolean isWaitingInitializing(ContainerState state) {
      return state != null && state.getWaiting() != null && "PodInitializing".equals(state.getWaiting().getReason());
   }

   private static boolean hasRunningContainer(Pod pod) {
      return getContainerStatus(pod).stream().anyMatch(PodStatusUtil::isRunning);
   }

   private static boolean isRunning(ContainerStatus status) {
      return status.getReady() != null && status.getState() != null && status.getState().getRunning() != null;
   }

   private static boolean hasCompletedContainer(Pod pod) {
      return getContainerStatus(pod).stream().anyMatch(PodStatusUtil::isCompleted);
   }

   private static boolean isCompleted(ContainerStatus status) {
      return status.getState() != null && status.getState().getTerminated() != null && "Completed".equals(status.getState().getTerminated().getReason());
   }

   public static List getContainerStatus(Pod pod) {
      return pod != null && pod.getStatus() != null ? pod.getStatus().getContainerStatuses() : Collections.emptyList();
   }
}
