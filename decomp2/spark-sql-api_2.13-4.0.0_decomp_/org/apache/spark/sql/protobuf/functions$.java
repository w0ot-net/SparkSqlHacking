package org.apache.spark.sql.protobuf;

import java.util.Map;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column$;
import org.apache.spark.sql.util.ProtobufUtils$;
import scala.jdk.CollectionConverters.;

public final class functions$ {
   public static final functions$ MODULE$ = new functions$();

   @Experimental
   public Column from_protobuf(final Column data, final String messageName, final String descFilePath, final Map options) {
      byte[] descriptorFileContent = ProtobufUtils$.MODULE$.readDescriptorFileContent(descFilePath);
      return this.from_protobuf(data, messageName, descriptorFileContent, options);
   }

   @Experimental
   public Column from_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet, final Map options) {
      return Column$.MODULE$.fnWithOptions("from_protobuf", .MODULE$.MapHasAsScala(options).asScala().iterator(), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageName), org.apache.spark.sql.functions$.MODULE$.lit(binaryFileDescriptorSet)}));
   }

   @Experimental
   public Column from_protobuf(final Column data, final String messageName, final String descFilePath) {
      byte[] fileContent = ProtobufUtils$.MODULE$.readDescriptorFileContent(descFilePath);
      return this.from_protobuf(data, messageName, fileContent);
   }

   @Experimental
   public Column from_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet) {
      return Column$.MODULE$.fn("from_protobuf", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageName), org.apache.spark.sql.functions$.MODULE$.lit(binaryFileDescriptorSet)}));
   }

   @Experimental
   public Column from_protobuf(final Column data, final String messageClassName) {
      return Column$.MODULE$.fn("from_protobuf", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageClassName)}));
   }

   @Experimental
   public Column from_protobuf(final Column data, final String messageClassName, final Map options) {
      return Column$.MODULE$.fnWithOptions("from_protobuf", .MODULE$.MapHasAsScala(options).asScala().iterator(), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageClassName)}));
   }

   @Experimental
   public Column to_protobuf(final Column data, final String messageName, final String descFilePath) {
      return this.to_protobuf(data, messageName, descFilePath, .MODULE$.MapHasAsJava(scala.Predef..MODULE$.Map().empty()).asJava());
   }

   @Experimental
   public Column to_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet) {
      return Column$.MODULE$.fn("to_protobuf", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageName), org.apache.spark.sql.functions$.MODULE$.lit(binaryFileDescriptorSet)}));
   }

   @Experimental
   public Column to_protobuf(final Column data, final String messageName, final String descFilePath, final Map options) {
      byte[] fileContent = ProtobufUtils$.MODULE$.readDescriptorFileContent(descFilePath);
      return this.to_protobuf(data, messageName, fileContent, options);
   }

   @Experimental
   public Column to_protobuf(final Column data, final String messageName, final byte[] binaryFileDescriptorSet, final Map options) {
      return Column$.MODULE$.fnWithOptions("to_protobuf", .MODULE$.MapHasAsScala(options).asScala().iterator(), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageName), org.apache.spark.sql.functions$.MODULE$.lit(binaryFileDescriptorSet)}));
   }

   @Experimental
   public Column to_protobuf(final Column data, final String messageClassName) {
      return Column$.MODULE$.fn("to_protobuf", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageClassName)}));
   }

   @Experimental
   public Column to_protobuf(final Column data, final String messageClassName, final Map options) {
      return Column$.MODULE$.fnWithOptions("to_protobuf", .MODULE$.MapHasAsScala(options).asScala().iterator(), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{data, org.apache.spark.sql.functions$.MODULE$.lit(messageClassName)}));
   }

   private functions$() {
   }
}
