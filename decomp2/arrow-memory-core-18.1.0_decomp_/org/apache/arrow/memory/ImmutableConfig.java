package org.apache.arrow.memory;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.arrow.memory.rounding.RoundingPolicy;
import org.immutables.value.Generated;

@Generated(
   from = "BaseAllocator.Config",
   generator = "Immutables"
)
final class ImmutableConfig extends BaseAllocator.Config {
   private final AllocationManager.Factory allocationManagerFactory;
   private final AllocationListener listener;
   private final long initReservation;
   private final long maxAllocation;
   private final RoundingPolicy roundingPolicy;
   private static final byte STAGE_INITIALIZING = -1;
   private static final byte STAGE_UNINITIALIZED = 0;
   private static final byte STAGE_INITIALIZED = 1;
   private transient volatile InitShim initShim = new InitShim();

   private ImmutableConfig(Builder builder) {
      if (builder.allocationManagerFactory != null) {
         this.initShim.allocationManagerFactory(builder.allocationManagerFactory);
      }

      if (builder.listener != null) {
         this.initShim.listener(builder.listener);
      }

      if (builder.initReservationIsSet()) {
         this.initShim.initReservation(builder.initReservation);
      }

      if (builder.maxAllocationIsSet()) {
         this.initShim.maxAllocation(builder.maxAllocation);
      }

      if (builder.roundingPolicy != null) {
         this.initShim.roundingPolicy(builder.roundingPolicy);
      }

      this.allocationManagerFactory = this.initShim.getAllocationManagerFactory();
      this.listener = this.initShim.getListener();
      this.initReservation = this.initShim.getInitReservation();
      this.maxAllocation = this.initShim.getMaxAllocation();
      this.roundingPolicy = this.initShim.getRoundingPolicy();
      this.initShim = null;
   }

   private ImmutableConfig(AllocationManager.Factory allocationManagerFactory, AllocationListener listener, long initReservation, long maxAllocation, RoundingPolicy roundingPolicy) {
      this.allocationManagerFactory = allocationManagerFactory;
      this.listener = listener;
      this.initReservation = initReservation;
      this.maxAllocation = maxAllocation;
      this.roundingPolicy = roundingPolicy;
      this.initShim = null;
   }

   AllocationManager.Factory getAllocationManagerFactory() {
      InitShim shim = this.initShim;
      return shim != null ? shim.getAllocationManagerFactory() : this.allocationManagerFactory;
   }

   AllocationListener getListener() {
      InitShim shim = this.initShim;
      return shim != null ? shim.getListener() : this.listener;
   }

   long getInitReservation() {
      InitShim shim = this.initShim;
      return shim != null ? shim.getInitReservation() : this.initReservation;
   }

   long getMaxAllocation() {
      InitShim shim = this.initShim;
      return shim != null ? shim.getMaxAllocation() : this.maxAllocation;
   }

   RoundingPolicy getRoundingPolicy() {
      InitShim shim = this.initShim;
      return shim != null ? shim.getRoundingPolicy() : this.roundingPolicy;
   }

   public final ImmutableConfig withAllocationManagerFactory(AllocationManager.Factory value) {
      if (this.allocationManagerFactory == value) {
         return this;
      } else {
         AllocationManager.Factory newValue = (AllocationManager.Factory)Objects.requireNonNull(value, "allocationManagerFactory");
         return new ImmutableConfig(newValue, this.listener, this.initReservation, this.maxAllocation, this.roundingPolicy);
      }
   }

   public final ImmutableConfig withListener(AllocationListener value) {
      if (this.listener == value) {
         return this;
      } else {
         AllocationListener newValue = (AllocationListener)Objects.requireNonNull(value, "listener");
         return new ImmutableConfig(this.allocationManagerFactory, newValue, this.initReservation, this.maxAllocation, this.roundingPolicy);
      }
   }

   public final ImmutableConfig withInitReservation(long value) {
      return this.initReservation == value ? this : new ImmutableConfig(this.allocationManagerFactory, this.listener, value, this.maxAllocation, this.roundingPolicy);
   }

   public final ImmutableConfig withMaxAllocation(long value) {
      return this.maxAllocation == value ? this : new ImmutableConfig(this.allocationManagerFactory, this.listener, this.initReservation, value, this.roundingPolicy);
   }

   public final ImmutableConfig withRoundingPolicy(RoundingPolicy value) {
      if (this.roundingPolicy == value) {
         return this;
      } else {
         RoundingPolicy newValue = (RoundingPolicy)Objects.requireNonNull(value, "roundingPolicy");
         return new ImmutableConfig(this.allocationManagerFactory, this.listener, this.initReservation, this.maxAllocation, newValue);
      }
   }

   public boolean equals(Object another) {
      if (this == another) {
         return true;
      } else {
         return another instanceof ImmutableConfig && this.equalTo(0, (ImmutableConfig)another);
      }
   }

   private boolean equalTo(int synthetic, ImmutableConfig another) {
      return this.allocationManagerFactory.equals(another.allocationManagerFactory) && this.listener.equals(another.listener) && this.initReservation == another.initReservation && this.maxAllocation == another.maxAllocation && this.roundingPolicy.equals(another.roundingPolicy);
   }

   public int hashCode() {
      int h = 5381;
      h += (h << 5) + this.allocationManagerFactory.hashCode();
      h += (h << 5) + this.listener.hashCode();
      h += (h << 5) + Long.hashCode(this.initReservation);
      h += (h << 5) + Long.hashCode(this.maxAllocation);
      h += (h << 5) + this.roundingPolicy.hashCode();
      return h;
   }

   public String toString() {
      String var10000 = String.valueOf(this.allocationManagerFactory);
      return "Config{allocationManagerFactory=" + var10000 + ", listener=" + String.valueOf(this.listener) + ", initReservation=" + this.initReservation + ", maxAllocation=" + this.maxAllocation + ", roundingPolicy=" + String.valueOf(this.roundingPolicy) + "}";
   }

   public static ImmutableConfig copyOf(BaseAllocator.Config instance) {
      return instance instanceof ImmutableConfig ? (ImmutableConfig)instance : builder().from(instance).build();
   }

   public static Builder builder() {
      return new Builder();
   }

   @Generated(
      from = "BaseAllocator.Config",
      generator = "Immutables"
   )
   private final class InitShim {
      private byte allocationManagerFactoryBuildStage = 0;
      private AllocationManager.Factory allocationManagerFactory;
      private byte listenerBuildStage = 0;
      private AllocationListener listener;
      private byte initReservationBuildStage = 0;
      private long initReservation;
      private byte maxAllocationBuildStage = 0;
      private long maxAllocation;
      private byte roundingPolicyBuildStage = 0;
      private RoundingPolicy roundingPolicy;

      AllocationManager.Factory getAllocationManagerFactory() {
         if (this.allocationManagerFactoryBuildStage == -1) {
            throw new IllegalStateException(this.formatInitCycleMessage());
         } else {
            if (this.allocationManagerFactoryBuildStage == 0) {
               this.allocationManagerFactoryBuildStage = -1;
               this.allocationManagerFactory = (AllocationManager.Factory)Objects.requireNonNull(ImmutableConfig.super.getAllocationManagerFactory(), "allocationManagerFactory");
               this.allocationManagerFactoryBuildStage = 1;
            }

            return this.allocationManagerFactory;
         }
      }

      void allocationManagerFactory(AllocationManager.Factory allocationManagerFactory) {
         this.allocationManagerFactory = allocationManagerFactory;
         this.allocationManagerFactoryBuildStage = 1;
      }

      AllocationListener getListener() {
         if (this.listenerBuildStage == -1) {
            throw new IllegalStateException(this.formatInitCycleMessage());
         } else {
            if (this.listenerBuildStage == 0) {
               this.listenerBuildStage = -1;
               this.listener = (AllocationListener)Objects.requireNonNull(ImmutableConfig.super.getListener(), "listener");
               this.listenerBuildStage = 1;
            }

            return this.listener;
         }
      }

      void listener(AllocationListener listener) {
         this.listener = listener;
         this.listenerBuildStage = 1;
      }

      long getInitReservation() {
         if (this.initReservationBuildStage == -1) {
            throw new IllegalStateException(this.formatInitCycleMessage());
         } else {
            if (this.initReservationBuildStage == 0) {
               this.initReservationBuildStage = -1;
               this.initReservation = ImmutableConfig.super.getInitReservation();
               this.initReservationBuildStage = 1;
            }

            return this.initReservation;
         }
      }

      void initReservation(long initReservation) {
         this.initReservation = initReservation;
         this.initReservationBuildStage = 1;
      }

      long getMaxAllocation() {
         if (this.maxAllocationBuildStage == -1) {
            throw new IllegalStateException(this.formatInitCycleMessage());
         } else {
            if (this.maxAllocationBuildStage == 0) {
               this.maxAllocationBuildStage = -1;
               this.maxAllocation = ImmutableConfig.super.getMaxAllocation();
               this.maxAllocationBuildStage = 1;
            }

            return this.maxAllocation;
         }
      }

      void maxAllocation(long maxAllocation) {
         this.maxAllocation = maxAllocation;
         this.maxAllocationBuildStage = 1;
      }

      RoundingPolicy getRoundingPolicy() {
         if (this.roundingPolicyBuildStage == -1) {
            throw new IllegalStateException(this.formatInitCycleMessage());
         } else {
            if (this.roundingPolicyBuildStage == 0) {
               this.roundingPolicyBuildStage = -1;
               this.roundingPolicy = (RoundingPolicy)Objects.requireNonNull(ImmutableConfig.super.getRoundingPolicy(), "roundingPolicy");
               this.roundingPolicyBuildStage = 1;
            }

            return this.roundingPolicy;
         }
      }

      void roundingPolicy(RoundingPolicy roundingPolicy) {
         this.roundingPolicy = roundingPolicy;
         this.roundingPolicyBuildStage = 1;
      }

      private String formatInitCycleMessage() {
         List<String> attributes = new ArrayList();
         if (this.allocationManagerFactoryBuildStage == -1) {
            attributes.add("allocationManagerFactory");
         }

         if (this.listenerBuildStage == -1) {
            attributes.add("listener");
         }

         if (this.initReservationBuildStage == -1) {
            attributes.add("initReservation");
         }

         if (this.maxAllocationBuildStage == -1) {
            attributes.add("maxAllocation");
         }

         if (this.roundingPolicyBuildStage == -1) {
            attributes.add("roundingPolicy");
         }

         return "Cannot build Config, attribute initializers form cycle " + String.valueOf(attributes);
      }
   }

   @Generated(
      from = "BaseAllocator.Config",
      generator = "Immutables"
   )
   public static final class Builder {
      private static final long OPT_BIT_INIT_RESERVATION = 1L;
      private static final long OPT_BIT_MAX_ALLOCATION = 2L;
      private long optBits;
      private AllocationManager.Factory allocationManagerFactory;
      private AllocationListener listener;
      private long initReservation;
      private long maxAllocation;
      private RoundingPolicy roundingPolicy;

      private Builder() {
      }

      @CanIgnoreReturnValue
      public final Builder from(BaseAllocator.Config instance) {
         Objects.requireNonNull(instance, "instance");
         this.allocationManagerFactory(instance.getAllocationManagerFactory());
         this.listener(instance.getListener());
         this.initReservation(instance.getInitReservation());
         this.maxAllocation(instance.getMaxAllocation());
         this.roundingPolicy(instance.getRoundingPolicy());
         return this;
      }

      @CanIgnoreReturnValue
      public final Builder allocationManagerFactory(AllocationManager.Factory allocationManagerFactory) {
         this.allocationManagerFactory = (AllocationManager.Factory)Objects.requireNonNull(allocationManagerFactory, "allocationManagerFactory");
         return this;
      }

      @CanIgnoreReturnValue
      public final Builder listener(AllocationListener listener) {
         this.listener = (AllocationListener)Objects.requireNonNull(listener, "listener");
         return this;
      }

      @CanIgnoreReturnValue
      public final Builder initReservation(long initReservation) {
         this.initReservation = initReservation;
         this.optBits |= 1L;
         return this;
      }

      @CanIgnoreReturnValue
      public final Builder maxAllocation(long maxAllocation) {
         this.maxAllocation = maxAllocation;
         this.optBits |= 2L;
         return this;
      }

      @CanIgnoreReturnValue
      public final Builder roundingPolicy(RoundingPolicy roundingPolicy) {
         this.roundingPolicy = (RoundingPolicy)Objects.requireNonNull(roundingPolicy, "roundingPolicy");
         return this;
      }

      public ImmutableConfig build() {
         return new ImmutableConfig(this);
      }

      private boolean initReservationIsSet() {
         return (this.optBits & 1L) != 0L;
      }

      private boolean maxAllocationIsSet() {
         return (this.optBits & 2L) != 0L;
      }
   }
}
