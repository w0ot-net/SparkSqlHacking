package org.apache.logging.log4j.core.net.ssl;

import java.util.Objects;

class EnvironmentPasswordProvider implements PasswordProvider {
   private final String passwordEnvironmentVariable;

   public EnvironmentPasswordProvider(final String passwordEnvironmentVariable) {
      this.passwordEnvironmentVariable = (String)Objects.requireNonNull(passwordEnvironmentVariable, "passwordEnvironmentVariable");
   }

   public char[] getPassword() {
      String password = System.getenv(this.passwordEnvironmentVariable);
      return password == null ? null : password.toCharArray();
   }
}
