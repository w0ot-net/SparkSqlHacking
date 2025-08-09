package org.sparkproject.spark_core.protobuf;

public final class LegacyUnredactedTextFormat {
   private LegacyUnredactedTextFormat() {
   }

   static String legacyUnredactedMultilineString(MessageOrBuilder message) {
      return TextFormat.printer().printToString(message, TextFormat.Printer.FieldReporterLevel.LEGACY_MULTILINE);
   }

   static String legacyUnredactedMultilineString(UnknownFieldSet fields) {
      return TextFormat.printer().printToString(fields);
   }

   static String legacyUnredactedSingleLineString(MessageOrBuilder message) {
      return TextFormat.printer().emittingSingleLine(true).printToString(message, TextFormat.Printer.FieldReporterLevel.LEGACY_SINGLE_LINE);
   }

   static String legacyUnredactedSingleLineString(UnknownFieldSet fields) {
      return TextFormat.printer().emittingSingleLine(true).printToString(fields);
   }
}
