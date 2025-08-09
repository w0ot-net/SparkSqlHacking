package com.univocity.parsers.common;

import java.util.TreeMap;

final class DummyFormat extends Format {
   static final DummyFormat instance = new DummyFormat();

   private DummyFormat() {
   }

   protected final TreeMap getConfiguration() {
      return new TreeMap();
   }
}
