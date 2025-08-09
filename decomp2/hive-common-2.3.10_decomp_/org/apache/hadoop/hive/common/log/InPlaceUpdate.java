package org.apache.hadoop.hive.common.log;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.io.PrintStream;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.Nullable;
import jline.TerminalFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.Ansi.Erase;
import org.fusesource.jansi.internal.CLibrary;

public class InPlaceUpdate {
   public static final int MIN_TERMINAL_WIDTH = 94;
   private static final String HEADER_FORMAT = "%16s%10s %13s  %5s  %9s  %7s  %7s  %6s  %6s  ";
   private static final String VERTEX_FORMAT = "%-16s%10s %13s  %5s  %9s  %7s  %7s  %6s  %6s  ";
   private static final String FOOTER_FORMAT = "%-15s  %-30s %-4s  %-25s";
   private static final int PROGRESS_BAR_CHARS = 30;
   private static final String SEPARATOR = (new String(new char[94])).replace("\u0000", "-");
   private final DecimalFormat secondsFormatter;
   private int lines;
   private PrintStream out;

   public InPlaceUpdate(PrintStream out) {
      this.secondsFormatter = new DecimalFormat("#0.00");
      this.lines = 0;
      this.out = out;
   }

   public InPlaceUpdate() {
      this(System.out);
   }

   public static void reprintLine(PrintStream out, String line) {
      out.print(Ansi.ansi().eraseLine(Erase.ALL).a(line).a('\n').toString());
      out.flush();
   }

   public static void rePositionCursor(PrintStream ps) {
      ps.print(Ansi.ansi().cursorUp(0).toString());
      ps.flush();
   }

   private void reprintLine(String line) {
      reprintLine(this.out, line);
      ++this.lines;
   }

   private void reprintLineWithColorAsBold(String line, Ansi.Color color) {
      this.out.print(Ansi.ansi().eraseLine(Erase.ALL).fg(color).bold().a(line).a('\n').boldOff().reset().toString());
      this.out.flush();
      ++this.lines;
   }

   private void reprintMultiLine(String line) {
      int numLines = line.split("\r\n|\r|\n").length;
      this.out.print(Ansi.ansi().eraseLine(Erase.ALL).a(line).a('\n').toString());
      this.out.flush();
      this.lines += numLines;
   }

   private void repositionCursor() {
      if (this.lines > 0) {
         this.out.print(Ansi.ansi().cursorUp(this.lines).toString());
         this.out.flush();
         this.lines = 0;
      }

   }

   private String getInPlaceProgressBar(double percent) {
      StringWriter bar = new StringWriter();
      bar.append("[");
      int remainingChars = 26;
      int completed = (int)((double)remainingChars * percent);
      int pending = remainingChars - completed;

      for(int i = 0; i < completed; ++i) {
         bar.append("=");
      }

      bar.append(">>");

      for(int i = 0; i < pending; ++i) {
         bar.append("-");
      }

      bar.append("]");
      return bar.toString();
   }

   public void render(ProgressMonitor monitor) {
      if (monitor != null) {
         this.repositionCursor();
         this.reprintLine(SEPARATOR);
         this.reprintLineWithColorAsBold(String.format("%16s%10s %13s  %5s  %9s  %7s  %7s  %6s  %6s  ", monitor.headers().toArray()), Color.CYAN);
         this.reprintLine(SEPARATOR);
         List<String> printReady = Lists.transform(monitor.rows(), new Function() {
            @Nullable
            public String apply(@Nullable List row) {
               return String.format("%-16s%10s %13s  %5s  %9s  %7s  %7s  %6s  %6s  ", row.toArray());
            }
         });
         this.reprintMultiLine(StringUtils.join(printReady, "\n"));
         String progressStr = "" + (int)(monitor.progressedPercentage() * (double)100.0F) + "%";
         float et = (float)(System.currentTimeMillis() - monitor.startTime()) / 1000.0F;
         String elapsedTime = "ELAPSED TIME: " + this.secondsFormatter.format((double)et) + " s";
         String footer = String.format("%-15s  %-30s %-4s  %-25s", monitor.footerSummary(), this.getInPlaceProgressBar(monitor.progressedPercentage()), progressStr, elapsedTime);
         this.reprintLine(SEPARATOR);
         this.reprintLineWithColorAsBold(footer, Color.RED);
         this.reprintLine(SEPARATOR);
      }
   }

   public static boolean canRenderInPlace(HiveConf conf) {
      boolean inPlaceUpdates = HiveConf.getBoolVar(conf, HiveConf.ConfVars.TEZ_EXEC_INPLACE_PROGRESS);
      return inPlaceUpdates && isUnixTerminal() && TerminalFactory.get().getWidth() >= 94;
   }

   private static boolean isUnixTerminal() {
      String os = System.getProperty("os.name");
      if (os.startsWith("Windows")) {
         return false;
      } else {
         try {
            if (CLibrary.isatty(CLibrary.STDOUT_FILENO) == 0) {
               return false;
            } else {
               return CLibrary.isatty(CLibrary.STDERR_FILENO) != 0;
            }
         } catch (UnsatisfiedLinkError | NoClassDefFoundError var2) {
            return false;
         }
      }
   }
}
