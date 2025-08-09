package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import py4j.Protocol;
import py4j.Py4JAuthenticationException;
import py4j.Py4JException;

public class AuthCommand extends AbstractCommand {
   public static final String COMMAND_NAME = "A";
   private final String authToken;
   private volatile boolean hasAuthenticated;

   public AuthCommand(String authToken) {
      this.commandName = "A";
      this.authToken = authToken;
      this.hasAuthenticated = false;
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      if (!"A".equals(commandName)) {
         writer.write(Protocol.getOutputErrorCommand("Authentication error: unexpected command."));
         writer.flush();
         throw new Py4JAuthenticationException(String.format("Expected %s, got %s instead.", "A", commandName));
      } else {
         String clientToken = reader.readLine();
         if (this.authToken.equals(clientToken)) {
            writer.write(Protocol.getOutputVoidCommand());
            writer.flush();
            this.hasAuthenticated = true;
         } else {
            writer.write(Protocol.getOutputErrorCommand("Authentication error: bad auth token received."));
            writer.flush();
            throw new Py4JAuthenticationException("Client authentication unsuccessful.");
         }
      }
   }

   public boolean isAuthenticated() {
      return this.hasAuthenticated;
   }
}
