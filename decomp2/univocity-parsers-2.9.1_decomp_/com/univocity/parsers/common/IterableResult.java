package com.univocity.parsers.common;

public interface IterableResult extends Iterable {
   Context getContext();

   ResultIterator iterator();
}
