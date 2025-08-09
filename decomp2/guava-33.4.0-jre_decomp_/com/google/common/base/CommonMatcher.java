package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class CommonMatcher {
   public abstract boolean matches();

   public abstract boolean find();

   public abstract boolean find(int index);

   public abstract String replaceAll(String replacement);

   public abstract int end();

   public abstract int start();
}
