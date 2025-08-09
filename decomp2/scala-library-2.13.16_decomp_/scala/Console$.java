package scala;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import scala.collection.StringOps$;
import scala.collection.immutable.Seq;
import scala.io.AnsiColor;
import scala.util.DynamicVariable;

public final class Console$ implements AnsiColor {
   public static final Console$ MODULE$ = new Console$();
   private static final DynamicVariable outVar;
   private static final DynamicVariable errVar;
   private static final DynamicVariable inVar;

   static {
      Console$ var10000 = MODULE$;
      outVar = new DynamicVariable(System.out);
      errVar = new DynamicVariable(System.err);
      inVar = new DynamicVariable(new BufferedReader(new InputStreamReader(System.in)));
   }

   public void setOutDirect(final PrintStream out) {
      outVar.value_$eq(out);
   }

   public void setErrDirect(final PrintStream err) {
      errVar.value_$eq(err);
   }

   public void setInDirect(final BufferedReader in) {
      inVar.value_$eq(in);
   }

   public PrintStream out() {
      return (PrintStream)outVar.value();
   }

   public PrintStream err() {
      return (PrintStream)errVar.value();
   }

   public BufferedReader in() {
      return (BufferedReader)inVar.value();
   }

   public Object withOut(final PrintStream out, final Function0 thunk) {
      return outVar.withValue(out, thunk);
   }

   public Object withOut(final OutputStream out, final Function0 thunk) {
      PrintStream withOut_out = new PrintStream(out);
      return outVar.withValue(withOut_out, thunk);
   }

   public Object withErr(final PrintStream err, final Function0 thunk) {
      return errVar.withValue(err, thunk);
   }

   public Object withErr(final OutputStream err, final Function0 thunk) {
      PrintStream withErr_err = new PrintStream(err);
      return errVar.withValue(withErr_err, thunk);
   }

   public Object withIn(final Reader reader, final Function0 thunk) {
      return inVar.withValue(new BufferedReader(reader), thunk);
   }

   public Object withIn(final InputStream in, final Function0 thunk) {
      Reader withIn_reader = new InputStreamReader(in);
      return inVar.withValue(new BufferedReader(withIn_reader), thunk);
   }

   public void print(final Object obj) {
      this.out().print(obj == null ? "null" : obj.toString());
   }

   public void flush() {
      this.out().flush();
   }

   public void println() {
      this.out().println();
   }

   public void println(final Object x) {
      this.out().println(x);
   }

   public void printf(final String text, final Seq args) {
      this.out().print(StringOps$.MODULE$.format$extension(text, args));
   }

   private Console$() {
   }
}
