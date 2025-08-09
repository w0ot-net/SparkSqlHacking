package org.antlr.v4.runtime;

import org.antlr.v4.runtime.misc.Pair;

public interface TokenFactory {
   Token create(Pair var1, int var2, String var3, int var4, int var5, int var6, int var7, int var8);

   Token create(int var1, String var2);
}
