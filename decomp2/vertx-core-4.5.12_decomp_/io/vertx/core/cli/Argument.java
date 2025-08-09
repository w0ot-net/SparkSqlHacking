package io.vertx.core.cli;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import java.util.Objects;

@DataObject
@JsonGen(
   publicConverter = false
)
public class Argument {
   public static final String DEFAULT_ARG_NAME = "value";
   protected int index;
   protected String argName;
   protected String description;
   protected boolean hidden;
   protected boolean required;
   protected String defaultValue;
   protected boolean multiValued;

   public Argument() {
      this.index = -1;
      this.argName = "value";
      this.required = true;
      this.multiValued = false;
   }

   public Argument(Argument other) {
      this();
      this.index = other.index;
      this.argName = other.argName;
      this.description = other.description;
      this.hidden = other.hidden;
      this.required = other.required;
      this.defaultValue = other.defaultValue;
      this.multiValued = other.multiValued;
   }

   public Argument(JsonObject json) {
      this();
      ArgumentConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      ArgumentConverter.toJson(this, json);
      return json;
   }

   public String getArgName() {
      return this.argName;
   }

   public Argument setArgName(String argName) {
      Objects.requireNonNull(argName);
      this.argName = argName;
      return this;
   }

   public String getDescription() {
      return this.description;
   }

   public Argument setDescription(String description) {
      Objects.requireNonNull(description);
      this.description = description;
      return this;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public Argument setHidden(boolean hidden) {
      this.hidden = hidden;
      return this;
   }

   public int getIndex() {
      return this.index;
   }

   public Argument setIndex(int index) {
      if (index < 0) {
         throw new IllegalArgumentException("Argument index cannot be negative");
      } else {
         this.index = index;
         return this;
      }
   }

   public boolean isRequired() {
      return this.required;
   }

   public Argument setRequired(boolean required) {
      this.required = required;
      return this;
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public Argument setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public boolean isMultiValued() {
      return this.multiValued;
   }

   public Argument setMultiValued(boolean multiValued) {
      this.multiValued = multiValued;
      return this;
   }

   public void ensureValidity() {
      if (this.index < 0) {
         throw new IllegalArgumentException("The index cannot be negative");
      }
   }
}
