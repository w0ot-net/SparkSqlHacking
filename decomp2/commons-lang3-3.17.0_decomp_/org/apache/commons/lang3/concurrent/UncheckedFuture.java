package org.apache.commons.lang3.concurrent;

import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface UncheckedFuture extends Future {
   static Stream map(Collection futures) {
      return futures.stream().map(UncheckedFuture::on);
   }

   static Collection on(Collection futures) {
      return (Collection)map(futures).collect(Collectors.toList());
   }

   static UncheckedFuture on(Future future) {
      return new UncheckedFutureImpl(future);
   }

   Object get();

   Object get(long var1, TimeUnit var3);
}
