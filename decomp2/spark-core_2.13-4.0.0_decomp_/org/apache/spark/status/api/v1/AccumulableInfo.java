package org.apache.spark.status.api.v1;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r3AAC\u0006\u00011!Aq\u0004\u0001BC\u0002\u0013\u0005\u0001\u0005\u0003\u0005%\u0001\t\u0005\t\u0015!\u0003\"\u0011!)\u0003A!b\u0001\n\u00031\u0003\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u0011M\u0002!Q1A\u0005\u0002QB\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\ts\u0001\u0011)\u0019!C\u0001M!A!\b\u0001B\u0001B\u0003%q\u0005\u0003\u0004<\u0001\u0011\u0005\u0011\u0003\u0010\u0002\u0010\u0003\u000e\u001cW/\\;mC\ndW-\u00138g_*\u0011A\"D\u0001\u0003mFR!AD\b\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0011#\u000511\u000f^1ukNT!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VMZ\u0001\u0003S\u0012,\u0012!\t\t\u00035\tJ!aI\u000e\u0003\t1{gnZ\u0001\u0004S\u0012\u0004\u0013\u0001\u00028b[\u0016,\u0012a\n\t\u0003Q=r!!K\u0017\u0011\u0005)ZR\"A\u0016\u000b\u00051:\u0012A\u0002\u001fs_>$h(\u0003\u0002/7\u00051\u0001K]3eK\u001aL!\u0001M\u0019\u0003\rM#(/\u001b8h\u0015\tq3$A\u0003oC6,\u0007%\u0001\u0004va\u0012\fG/Z\u000b\u0002kA\u0019!DN\u0014\n\u0005]Z\"AB(qi&|g.A\u0004va\u0012\fG/\u001a\u0011\u0002\u000bY\fG.^3\u0002\rY\fG.^3!\u0003\u0019a\u0014N\\5u}Q)Qh\u0010!B\u0005B\u0011a\bA\u0007\u0002\u0017!)q$\u0003a\u0001C!)Q%\u0003a\u0001O!)1'\u0003a\u0001k!)\u0011(\u0003a\u0001O\u0001"
)
public class AccumulableInfo {
   private final long id;
   private final String name;
   private final Option update;
   private final String value;

   public long id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public Option update() {
      return this.update;
   }

   public String value() {
      return this.value;
   }

   public AccumulableInfo(final long id, final String name, final Option update, final String value) {
      this.id = id;
      this.name = name;
      this.update = update;
      this.value = value;
   }
}
