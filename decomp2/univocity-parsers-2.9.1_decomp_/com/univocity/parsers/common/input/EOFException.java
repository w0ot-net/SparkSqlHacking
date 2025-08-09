package com.univocity.parsers.common.input;

public final class EOFException extends RuntimeException {
   private static final long serialVersionUID = -4064380464076294133L;

   public Throwable fillInStackTrace() {
      return this;
   }
}
