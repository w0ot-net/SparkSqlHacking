package io.vertx.core.dns;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;

@VertxGen
public interface DnsClient {
   @Fluent
   DnsClient lookup(String var1, Handler var2);

   Future lookup(String var1);

   @Fluent
   DnsClient lookup4(String var1, Handler var2);

   Future lookup4(String var1);

   @Fluent
   DnsClient lookup6(String var1, Handler var2);

   Future lookup6(String var1);

   @Fluent
   DnsClient resolveA(String var1, Handler var2);

   Future resolveA(String var1);

   @Fluent
   DnsClient resolveAAAA(String var1, Handler var2);

   Future resolveAAAA(String var1);

   @Fluent
   DnsClient resolveCNAME(String var1, Handler var2);

   Future resolveCNAME(String var1);

   @Fluent
   DnsClient resolveMX(String var1, Handler var2);

   Future resolveMX(String var1);

   @Fluent
   DnsClient resolveTXT(String var1, Handler var2);

   Future resolveTXT(String var1);

   @Fluent
   DnsClient resolvePTR(String var1, Handler var2);

   Future resolvePTR(String var1);

   @Fluent
   DnsClient resolveNS(String var1, Handler var2);

   Future resolveNS(String var1);

   @Fluent
   DnsClient resolveSRV(String var1, Handler var2);

   Future resolveSRV(String var1);

   @Fluent
   DnsClient reverseLookup(String var1, Handler var2);

   Future reverseLookup(String var1);

   void close(Handler var1);

   Future close();
}
