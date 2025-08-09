package org.apache.spark.ui.jobs;

import org.apache.spark.status.api.v1.JobData;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;aAB\u0004\t\u0002%\tbAB\n\b\u0011\u0003IA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011\u0005q\u0004C\u00033\u0003\u0011\u00051\u0007C\u0003A\u0003\u0011\u0005\u0011)A\u0006K_\n$\u0015\r^1Vi&d'B\u0001\u0005\n\u0003\u0011QwNY:\u000b\u0005)Y\u0011AA;j\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<\u0007C\u0001\n\u0002\u001b\u00059!a\u0003&pE\u0012\u000bG/Y+uS2\u001c\"!A\u000b\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#A\t\u0002\u0017\u001d,G\u000fR;sCRLwN\u001c\u000b\u0003A\u0019\u00022AF\u0011$\u0013\t\u0011sC\u0001\u0004PaRLwN\u001c\t\u0003-\u0011J!!J\f\u0003\t1{gn\u001a\u0005\u0006O\r\u0001\r\u0001K\u0001\bU>\u0014G)\u0019;b!\tI\u0003'D\u0001+\u0015\tYC&\u0001\u0002wc)\u0011QFL\u0001\u0004CBL'BA\u0018\f\u0003\u0019\u0019H/\u0019;vg&\u0011\u0011G\u000b\u0002\b\u0015>\u0014G)\u0019;b\u0003Q9W\r\u001e$pe6\fG\u000f^3e\tV\u0014\u0018\r^5p]R\u0011Ag\u0010\t\u0003kqr!A\u000e\u001e\u0011\u0005]:R\"\u0001\u001d\u000b\u0005eb\u0012A\u0002\u001fs_>$h(\u0003\u0002</\u00051\u0001K]3eK\u001aL!!\u0010 \u0003\rM#(/\u001b8h\u0015\tYt\u0003C\u0003(\t\u0001\u0007\u0001&\u0001\u000ehKR4uN]7biR,GmU;c[&\u001c8/[8o)&lW\r\u0006\u00025\u0005\")q%\u0002a\u0001Q\u0001"
)
public final class JobDataUtil {
   public static String getFormattedSubmissionTime(final JobData jobData) {
      return JobDataUtil$.MODULE$.getFormattedSubmissionTime(jobData);
   }

   public static String getFormattedDuration(final JobData jobData) {
      return JobDataUtil$.MODULE$.getFormattedDuration(jobData);
   }

   public static Option getDuration(final JobData jobData) {
      return JobDataUtil$.MODULE$.getDuration(jobData);
   }
}
