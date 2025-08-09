package org.apache.logging.log4j.layout.template.json.resolver;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.layout.template.json.util.Recycler;

public class CounterResolver implements EventResolver {
   private final Consumer delegate;

   public CounterResolver(final EventResolverContext context, final TemplateResolverConfig config) {
      this.delegate = createDelegate(context, config);
   }

   private static Consumer createDelegate(final EventResolverContext context, final TemplateResolverConfig config) {
      BigInteger start = readStart(config);
      boolean overflowing = config.getBoolean("overflowing", true);
      boolean stringified = config.getBoolean("stringified", false);
      if (stringified) {
         Recycler<StringBuilder> stringBuilderRecycler = createStringBuilderRecycler(context);
         return overflowing ? createStringifiedLongResolver(start, stringBuilderRecycler) : createStringifiedBigIntegerResolver(start, stringBuilderRecycler);
      } else {
         return overflowing ? createLongResolver(start) : createBigIntegerResolver(start);
      }
   }

   private static BigInteger readStart(final TemplateResolverConfig config) {
      Object start = config.getObject("start", Object.class);
      if (start == null) {
         return BigInteger.ZERO;
      } else if (!(start instanceof Short) && !(start instanceof Integer) && !(start instanceof Long)) {
         if (start instanceof BigInteger) {
            return (BigInteger)start;
         } else {
            Class<?> clazz = start.getClass();
            String message = String.format("could not read start of type %s: %s", clazz, config);
            throw new IllegalArgumentException(message);
         }
      } else {
         return BigInteger.valueOf(((Number)start).longValue());
      }
   }

   private static Consumer createLongResolver(final BigInteger start) {
      long effectiveStart = start.longValue();
      AtomicLong counter = new AtomicLong(effectiveStart);
      return (jsonWriter) -> {
         long number = counter.getAndIncrement();
         jsonWriter.writeNumber(number);
      };
   }

   private static Consumer createBigIntegerResolver(final BigInteger start) {
      AtomicBigInteger counter = new AtomicBigInteger(start);
      return (jsonWriter) -> {
         BigInteger number = counter.getAndIncrement();
         jsonWriter.writeNumber(number);
      };
   }

   private static Recycler createStringBuilderRecycler(final EventResolverContext context) {
      return context.getRecyclerFactory().create(StringBuilder::new, (stringBuilder) -> {
         int maxLength = context.getJsonWriter().getMaxStringLength();
         trimStringBuilder(stringBuilder, maxLength);
      });
   }

   private static void trimStringBuilder(final StringBuilder stringBuilder, final int maxLength) {
      if (stringBuilder.length() > maxLength) {
         stringBuilder.setLength(maxLength);
         stringBuilder.trimToSize();
      }

      stringBuilder.setLength(0);
   }

   private static Consumer createStringifiedLongResolver(final BigInteger start, final Recycler stringBuilderRecycler) {
      long effectiveStart = start.longValue();
      AtomicLong counter = new AtomicLong(effectiveStart);
      return (jsonWriter) -> {
         long number = counter.getAndIncrement();
         StringBuilder stringBuilder = (StringBuilder)stringBuilderRecycler.acquire();

         try {
            stringBuilder.append(number);
            jsonWriter.writeString((CharSequence)stringBuilder);
         } finally {
            stringBuilderRecycler.release(stringBuilder);
         }

      };
   }

   private static Consumer createStringifiedBigIntegerResolver(final BigInteger start, final Recycler stringBuilderRecycler) {
      AtomicBigInteger counter = new AtomicBigInteger(start);
      return (jsonWriter) -> {
         BigInteger number = counter.getAndIncrement();
         StringBuilder stringBuilder = (StringBuilder)stringBuilderRecycler.acquire();

         try {
            stringBuilder.append(number);
            jsonWriter.writeString((CharSequence)stringBuilder);
         } finally {
            stringBuilderRecycler.release(stringBuilder);
         }

      };
   }

   static String getName() {
      return "counter";
   }

   public void resolve(final LogEvent ignored, final JsonWriter jsonWriter) {
      this.delegate.accept(jsonWriter);
   }

   private static final class AtomicBigInteger {
      private final AtomicReference lastNumber;

      private AtomicBigInteger(final BigInteger start) {
         this.lastNumber = new AtomicReference(start);
      }

      private BigInteger getAndIncrement() {
         BigInteger prevNumber;
         BigInteger nextNumber;
         do {
            prevNumber = (BigInteger)this.lastNumber.get();
            nextNumber = prevNumber.add(BigInteger.ONE);
         } while(!this.compareAndSetWithBackOff(prevNumber, nextNumber));

         return prevNumber;
      }

      private boolean compareAndSetWithBackOff(final BigInteger prevNumber, final BigInteger nextNumber) {
         if (this.lastNumber.compareAndSet(prevNumber, nextNumber)) {
            return true;
         } else {
            LockSupport.parkNanos(1L);
            return false;
         }
      }
   }
}
