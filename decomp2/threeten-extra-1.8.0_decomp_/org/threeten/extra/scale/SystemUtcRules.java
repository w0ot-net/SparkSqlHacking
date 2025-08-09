package org.threeten.extra.scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

final class SystemUtcRules extends UtcRules implements Serializable {
   private static final String LEAP_SECONDS_TXT = "org/threeten/extra/scale/LeapSeconds.txt";
   private static final Pattern LEAP_FILE_FORMAT = Pattern.compile("([0-9-]{10})[ ]+([0-9]+)");
   private static final long serialVersionUID = 7594178360693417218L;
   static final SystemUtcRules INSTANCE = new SystemUtcRules();
   private AtomicReference dataRef = new AtomicReference(loadLeapSeconds());

   private SystemUtcRules() {
   }

   private Object readResolve() {
      return INSTANCE;
   }

   void register(long mjDay, int leapAdjustment) {
      if (leapAdjustment != -1 && leapAdjustment != 1) {
         throw new IllegalArgumentException("Leap adjustment must be -1 or 1");
      } else {
         Data data = (Data)this.dataRef.get();
         int pos = Arrays.binarySearch(data.dates, mjDay);
         int currentAdj = pos > 0 ? data.offsets[pos] - data.offsets[pos - 1] : 0;
         if (currentAdj != leapAdjustment) {
            if (mjDay <= data.dates[data.dates.length - 1]) {
               throw new IllegalArgumentException("Date must be after the last configured leap second date");
            } else {
               long[] dates = Arrays.copyOf(data.dates, data.dates.length + 1);
               int[] offsets = Arrays.copyOf(data.offsets, data.offsets.length + 1);
               long[] taiSeconds = Arrays.copyOf(data.taiSeconds, data.taiSeconds.length + 1);
               int offset = offsets[offsets.length - 2] + leapAdjustment;
               dates[dates.length - 1] = mjDay;
               offsets[offsets.length - 1] = offset;
               taiSeconds[taiSeconds.length - 1] = tai(mjDay, offset);
               Data newData = new Data(dates, offsets, taiSeconds);
               if (!this.dataRef.compareAndSet(data, newData)) {
                  throw new ConcurrentModificationException("Unable to update leap second rules as they have already been updated");
               }
            }
         }
      }
   }

   public String getName() {
      return "System";
   }

   public int getLeapSecondAdjustment(long mjDay) {
      Data data = (Data)this.dataRef.get();
      int pos = Arrays.binarySearch(data.dates, mjDay);
      return pos > 0 ? data.offsets[pos] - data.offsets[pos - 1] : 0;
   }

   public int getTaiOffset(long mjDay) {
      Data data = (Data)this.dataRef.get();
      int pos = Arrays.binarySearch(data.dates, mjDay);
      pos = pos < 0 ? ~pos : pos;
      return pos > 0 ? data.offsets[pos - 1] : 10;
   }

   public long[] getLeapSecondDates() {
      Data data = (Data)this.dataRef.get();
      return (long[])data.dates.clone();
   }

   public UtcInstant convertToUtc(TaiInstant taiInstant) {
      Data data = (Data)this.dataRef.get();
      long[] mjds = data.dates;
      long[] tais = data.taiSeconds;
      int pos = Arrays.binarySearch(tais, taiInstant.getTaiSeconds());
      pos = pos >= 0 ? pos : ~pos - 1;
      int taiOffset = pos >= 0 ? data.offsets[pos] : 10;
      long adjustedTaiSecs = taiInstant.getTaiSeconds() - (long)taiOffset;
      long mjd = Math.floorDiv(adjustedTaiSecs, 86400L) + 36204L;
      long nod = Math.floorMod(adjustedTaiSecs, 86400L) * 1000000000L + (long)taiInstant.getNano();
      long mjdNextRegionStart = pos + 1 < mjds.length ? mjds[pos + 1] + 1L : Long.MAX_VALUE;
      if (mjd == mjdNextRegionStart) {
         --mjd;
         nod = 86400000000000L + nod / 1000000000L * 1000000000L + nod % 1000000000L;
      }

      return UtcInstant.ofModifiedJulianDay(mjd, nod);
   }

   private static Data loadLeapSeconds() {
      Data bestData = null;
      URL url = null;

      try {
         Enumeration<URL> en = Thread.currentThread().getContextClassLoader().getResources("META-INF/org/threeten/extra/scale/LeapSeconds.txt");

         while(en.hasMoreElements()) {
            url = (URL)en.nextElement();
            Data candidate = loadLeapSeconds(url);
            if (bestData == null || candidate.getNewestDate() > bestData.getNewestDate()) {
               bestData = candidate;
            }
         }

         en = Thread.currentThread().getContextClassLoader().getResources("org/threeten/extra/scale/LeapSeconds.txt");

         while(en.hasMoreElements()) {
            url = (URL)en.nextElement();
            Data candidate = loadLeapSeconds(url);
            if (bestData == null || candidate.getNewestDate() > bestData.getNewestDate()) {
               bestData = candidate;
            }
         }

         url = SystemUtcRules.class.getResource("/org/threeten/extra/scale/LeapSeconds.txt");
         if (url != null) {
            Data candidate = loadLeapSeconds(url);
            if (bestData == null || candidate.getNewestDate() > bestData.getNewestDate()) {
               bestData = candidate;
            }
         }
      } catch (Exception ex) {
         throw new RuntimeException("Unable to load time-zone rule data: " + url, ex);
      }

      if (bestData == null) {
         bestData = new Data(new long[]{41317L}, new int[]{10}, new long[]{tai(41317L, 10)});
      }

      return bestData;
   }

   private static Data loadLeapSeconds(URL url) throws ClassNotFoundException, IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

      List<String> lines;
      try {
         lines = (List)reader.lines().collect(Collectors.toList());
      } catch (Throwable var9) {
         try {
            reader.close();
         } catch (Throwable var8) {
            var9.addSuppressed(var8);
         }

         throw var9;
      }

      reader.close();
      ArrayList dates = new ArrayList();
      ArrayList offsets = new ArrayList();

      for(String line : lines) {
         line = line.trim();
         if (!line.isEmpty() && !line.startsWith("#")) {
            Matcher matcher = LEAP_FILE_FORMAT.matcher(line);
            if (!matcher.matches()) {
               throw new StreamCorruptedException("Invalid leap second file");
            }

            dates.add(LocalDate.parse(matcher.group(1)).getLong(JulianFields.MODIFIED_JULIAN_DAY));
            offsets.add(Integer.valueOf(matcher.group(2)));
         }
      }

      long[] datesData = new long[dates.size()];
      int[] offsetsData = new int[dates.size()];
      long[] taiData = new long[dates.size()];

      for(int i = 0; i < datesData.length; ++i) {
         datesData[i] = (Long)dates.get(i);
         offsetsData[i] = (Integer)offsets.get(i);
         taiData[i] = tai(datesData[i], offsetsData[i]);
      }

      return new Data(datesData, offsetsData, taiData);
   }

   private static long tai(long changeMjd, int offset) {
      return (changeMjd + 1L - 36204L) * 86400L + (long)offset;
   }

   private static final class Data implements Serializable {
      private static final long serialVersionUID = -3655687912882817265L;
      private final long[] dates;
      private final int[] offsets;
      private final long[] taiSeconds;

      private Data(long[] dates, int[] offsets, long[] taiSeconds) {
         this.dates = dates;
         this.offsets = offsets;
         this.taiSeconds = taiSeconds;
      }

      public long getNewestDate() {
         return this.dates[this.dates.length - 1];
      }
   }
}
