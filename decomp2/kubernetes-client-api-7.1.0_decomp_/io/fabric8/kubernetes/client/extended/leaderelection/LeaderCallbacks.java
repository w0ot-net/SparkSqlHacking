package io.fabric8.kubernetes.client.extended.leaderelection;

import java.util.Objects;
import java.util.function.Consumer;

public class LeaderCallbacks {
   private final Runnable onStartLeading;
   private final Runnable onStopLeading;
   private final Consumer onNewLeader;

   public LeaderCallbacks(Runnable onStartLeading, Runnable onStopLeading, Consumer onNewLeader) {
      this.onStartLeading = (Runnable)Objects.requireNonNull(onStartLeading, "onStartLeading callback is required");
      this.onStopLeading = (Runnable)Objects.requireNonNull(onStopLeading, "onStopLeading callback is required");
      this.onNewLeader = (Consumer)Objects.requireNonNull(onNewLeader, "onNewLeader callback is required");
   }

   public void onStartLeading() {
      this.onStartLeading.run();
   }

   public void onStopLeading() {
      this.onStopLeading.run();
   }

   public void onNewLeader(String id) {
      this.onNewLeader.accept(id);
   }
}
