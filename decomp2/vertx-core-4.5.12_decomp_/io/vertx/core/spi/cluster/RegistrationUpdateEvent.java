package io.vertx.core.spi.cluster;

import java.util.Collections;
import java.util.List;

public class RegistrationUpdateEvent {
   private final String address;
   private final List registrations;

   public RegistrationUpdateEvent(String address, List registrations) {
      this.address = address;
      this.registrations = registrations == null ? Collections.emptyList() : registrations;
   }

   public String address() {
      return this.address;
   }

   public List registrations() {
      return this.registrations;
   }

   public String toString() {
      return "RegistrationUpdateEvent{address='" + this.address + '\'' + ", registrations=" + this.registrations + '}';
   }
}
