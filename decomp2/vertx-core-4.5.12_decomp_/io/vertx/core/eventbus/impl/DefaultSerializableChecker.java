package io.vertx.core.eventbus.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DefaultSerializableChecker {
   INSTANCE;

   private final Set classNames = (Set)Stream.of(byte[].class, Number.class, BigDecimal.class, BigInteger.class).map(Class::getName).collect(Collectors.toSet());

   public boolean check(String className) {
      return this.classNames.contains(className);
   }
}
