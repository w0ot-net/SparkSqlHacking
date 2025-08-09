package com.codahale.metrics;

public interface SettableGauge extends Gauge {
   void setValue(Object value);
}
