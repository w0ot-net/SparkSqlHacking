package org.apache.commons.lang3.concurrent;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class EventCountCircuitBreaker extends AbstractCircuitBreaker {
   private static final Map STRATEGY_MAP = createStrategyMap();
   private final AtomicReference checkIntervalData;
   private final int openingThreshold;
   private final long openingInterval;
   private final int closingThreshold;
   private final long closingInterval;

   private static Map createStrategyMap() {
      Map<AbstractCircuitBreaker.State, StateStrategy> map = new EnumMap(AbstractCircuitBreaker.State.class);
      map.put(AbstractCircuitBreaker.State.CLOSED, new StateStrategyClosed());
      map.put(AbstractCircuitBreaker.State.OPEN, new StateStrategyOpen());
      return map;
   }

   private static StateStrategy stateStrategy(AbstractCircuitBreaker.State state) {
      return (StateStrategy)STRATEGY_MAP.get(state);
   }

   public EventCountCircuitBreaker(int threshold, long checkInterval, TimeUnit checkUnit) {
      this(threshold, checkInterval, checkUnit, threshold);
   }

   public EventCountCircuitBreaker(int openingThreshold, long checkInterval, TimeUnit checkUnit, int closingThreshold) {
      this(openingThreshold, checkInterval, checkUnit, closingThreshold, checkInterval, checkUnit);
   }

   public EventCountCircuitBreaker(int openingThreshold, long openingInterval, TimeUnit openingUnit, int closingThreshold, long closingInterval, TimeUnit closingUnit) {
      this.checkIntervalData = new AtomicReference(new CheckIntervalData(0, 0L));
      this.openingThreshold = openingThreshold;
      this.openingInterval = openingUnit.toNanos(openingInterval);
      this.closingThreshold = closingThreshold;
      this.closingInterval = closingUnit.toNanos(closingInterval);
   }

   private void changeStateAndStartNewCheckInterval(AbstractCircuitBreaker.State newState) {
      this.changeState(newState);
      this.checkIntervalData.set(new CheckIntervalData(0, this.nanoTime()));
   }

   public boolean checkState() {
      return this.performStateCheck(0);
   }

   public void close() {
      super.close();
      this.checkIntervalData.set(new CheckIntervalData(0, this.nanoTime()));
   }

   public long getClosingInterval() {
      return this.closingInterval;
   }

   public int getClosingThreshold() {
      return this.closingThreshold;
   }

   public long getOpeningInterval() {
      return this.openingInterval;
   }

   public int getOpeningThreshold() {
      return this.openingThreshold;
   }

   public boolean incrementAndCheckState() {
      return this.incrementAndCheckState(1);
   }

   public boolean incrementAndCheckState(Integer increment) {
      return this.performStateCheck(increment);
   }

   long nanoTime() {
      return System.nanoTime();
   }

   private CheckIntervalData nextCheckIntervalData(int increment, CheckIntervalData currentData, AbstractCircuitBreaker.State currentState, long time) {
      CheckIntervalData nextData;
      if (stateStrategy(currentState).isCheckIntervalFinished(this, currentData, time)) {
         nextData = new CheckIntervalData(increment, time);
      } else {
         nextData = currentData.increment(increment);
      }

      return nextData;
   }

   public void open() {
      super.open();
      this.checkIntervalData.set(new CheckIntervalData(0, this.nanoTime()));
   }

   private boolean performStateCheck(int increment) {
      CheckIntervalData currentData;
      CheckIntervalData nextData;
      AbstractCircuitBreaker.State currentState;
      do {
         long time = this.nanoTime();
         currentState = (AbstractCircuitBreaker.State)this.state.get();
         currentData = (CheckIntervalData)this.checkIntervalData.get();
         nextData = this.nextCheckIntervalData(increment, currentData, currentState, time);
      } while(!this.updateCheckIntervalData(currentData, nextData));

      if (stateStrategy(currentState).isStateTransition(this, currentData, nextData)) {
         currentState = currentState.oppositeState();
         this.changeStateAndStartNewCheckInterval(currentState);
      }

      return !isOpen(currentState);
   }

   private boolean updateCheckIntervalData(CheckIntervalData currentData, CheckIntervalData nextData) {
      return currentData == nextData || this.checkIntervalData.compareAndSet(currentData, nextData);
   }

   private static final class CheckIntervalData {
      private final int eventCount;
      private final long checkIntervalStart;

      CheckIntervalData(int count, long intervalStart) {
         this.eventCount = count;
         this.checkIntervalStart = intervalStart;
      }

      public long getCheckIntervalStart() {
         return this.checkIntervalStart;
      }

      public int getEventCount() {
         return this.eventCount;
      }

      public CheckIntervalData increment(int delta) {
         return delta == 0 ? this : new CheckIntervalData(this.getEventCount() + delta, this.getCheckIntervalStart());
      }
   }

   private abstract static class StateStrategy {
      private StateStrategy() {
      }

      protected abstract long fetchCheckInterval(EventCountCircuitBreaker var1);

      public boolean isCheckIntervalFinished(EventCountCircuitBreaker breaker, CheckIntervalData currentData, long now) {
         return now - currentData.getCheckIntervalStart() > this.fetchCheckInterval(breaker);
      }

      public abstract boolean isStateTransition(EventCountCircuitBreaker var1, CheckIntervalData var2, CheckIntervalData var3);
   }

   private static final class StateStrategyClosed extends StateStrategy {
      private StateStrategyClosed() {
      }

      protected long fetchCheckInterval(EventCountCircuitBreaker breaker) {
         return breaker.getOpeningInterval();
      }

      public boolean isStateTransition(EventCountCircuitBreaker breaker, CheckIntervalData currentData, CheckIntervalData nextData) {
         return nextData.getEventCount() > breaker.getOpeningThreshold();
      }
   }

   private static final class StateStrategyOpen extends StateStrategy {
      private StateStrategyOpen() {
      }

      protected long fetchCheckInterval(EventCountCircuitBreaker breaker) {
         return breaker.getClosingInterval();
      }

      public boolean isStateTransition(EventCountCircuitBreaker breaker, CheckIntervalData currentData, CheckIntervalData nextData) {
         return nextData.getCheckIntervalStart() != currentData.getCheckIntervalStart() && currentData.getEventCount() < breaker.getClosingThreshold();
      }
   }
}
