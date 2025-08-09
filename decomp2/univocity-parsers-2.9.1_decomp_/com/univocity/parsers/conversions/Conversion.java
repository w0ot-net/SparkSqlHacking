package com.univocity.parsers.conversions;

public interface Conversion {
   Object execute(Object var1);

   Object revert(Object var1);
}
