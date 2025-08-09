package org.apache.curator.framework.api;

public interface CreateBuilder extends CreateBuilderMain, Idempotentable {
   CreateBuilderMain withTtl(long var1);

   CreateBuilder2 orSetData();

   CreateBuilder2 orSetData(int var1);
}
