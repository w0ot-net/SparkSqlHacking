package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import org.xml.sax.SAXParseException;
import scala.Console.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t3qAB\u0004\u0011\u0002\u0007\u0005a\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003!\u0001\u0011\u0005\u0013\u0005C\u0003-\u0001\u0011\u0005S\u0006C\u00030\u0001\u0011\u0005\u0003\u0007C\u00033\u0001\u0011E1GA\nD_:\u001cx\u000e\\3FeJ|'\u000fS1oI2,'O\u0003\u0002\t\u0013\u00059\u0001/\u0019:tS:<'B\u0001\u0006\f\u0003\rAX\u000e\u001c\u0006\u0002\u0019\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u0010!\t\u0001\u0002$D\u0001\u0012\u0015\t\u00112#A\u0002fqRT!\u0001F\u000b\u0002\u0007M\f\u0007P\u0003\u0002\u000b-)\tq#A\u0002pe\u001eL!!G\t\u0003\u001f\u0011+g-Y;mi\"\u000bg\u000e\u001a7feJ\na\u0001J5oSR$C#\u0001\u000f\u0011\u0005uqR\"A\u0006\n\u0005}Y!\u0001B+oSR\fqa^1s]&tw\r\u0006\u0002\u001dE!)1E\u0001a\u0001I\u0005\u0011Q\r\u001f\t\u0003K%r!AJ\u0014\u000e\u0003%I!\u0001K\u0005\u0002\u000fA\f7m[1hK&\u0011!f\u000b\u0002\u0012'\u0006C\u0006+\u0019:tK\u0016C8-\u001a9uS>t'B\u0001\u0015\n\u0003\u0015)'O]8s)\tab\u0006C\u0003$\u0007\u0001\u0007A%\u0001\u0006gCR\fG.\u0012:s_J$\"\u0001H\u0019\t\u000b\r\"\u0001\u0019\u0001\u0013\u0002\u0015A\u0014\u0018N\u001c;FeJ|'\u000fF\u0002\u001di\u0005CQ!N\u0003A\u0002Y\nq!\u001a:sif\u0004X\r\u0005\u00028}9\u0011\u0001\b\u0010\t\u0003s-i\u0011A\u000f\u0006\u0003w5\ta\u0001\u0010:p_Rt\u0014BA\u001f\f\u0003\u0019\u0001&/\u001a3fM&\u0011q\b\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005uZ\u0001\"B\u0012\u0006\u0001\u0004!\u0003"
)
public interface ConsoleErrorHandler {
   // $FF: synthetic method
   static void warning$(final ConsoleErrorHandler $this, final SAXParseException ex) {
      $this.warning(ex);
   }

   default void warning(final SAXParseException ex) {
   }

   // $FF: synthetic method
   static void error$(final ConsoleErrorHandler $this, final SAXParseException ex) {
      $this.error(ex);
   }

   default void error(final SAXParseException ex) {
      this.printError("Error", ex);
   }

   // $FF: synthetic method
   static void fatalError$(final ConsoleErrorHandler $this, final SAXParseException ex) {
      $this.fatalError(ex);
   }

   default void fatalError(final SAXParseException ex) {
      this.printError("Fatal Error", ex);
   }

   // $FF: synthetic method
   static void printError$(final ConsoleErrorHandler $this, final String errtype, final SAXParseException ex) {
      $this.printError(errtype, ex);
   }

   default void printError(final String errtype, final SAXParseException ex) {
      .MODULE$.withOut(.MODULE$.err(), (JFunction0.mcV.sp)() -> {
         .MODULE$.println((new StringBuilder(6)).append("[").append(errtype).append("]:").append(ex.getLineNumber()).append(":").append(ex.getColumnNumber()).append(": ").append(ex.getMessage()).toString());
         .MODULE$.flush();
      });
   }

   static void $init$(final ConsoleErrorHandler $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
