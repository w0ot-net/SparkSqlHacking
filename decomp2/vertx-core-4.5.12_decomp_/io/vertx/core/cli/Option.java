package io.vertx.core.cli;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@DataObject
@JsonGen(
   publicConverter = false
)
public class Option {
   public static final String DEFAULT_ARG_NAME = "value";
   public static final String NO_NAME = "\u0000";
   protected String longName;
   protected String shortName;
   protected String argName;
   protected String description;
   protected boolean required;
   protected boolean hidden;
   protected boolean singleValued;
   protected boolean multiValued;
   protected String defaultValue;
   protected boolean flag;
   protected boolean help;
   protected Set choices;

   public Option() {
      this.longName = "\u0000";
      this.shortName = "\u0000";
      this.argName = "value";
      this.singleValued = true;
      this.choices = new TreeSet();
   }

   public Option(Option other) {
      this();
      this.longName = other.longName;
      this.shortName = other.shortName;
      this.argName = other.argName;
      this.description = other.description;
      this.required = other.required;
      this.hidden = other.hidden;
      this.singleValued = other.singleValued;
      this.multiValued = other.multiValued;
      this.defaultValue = other.defaultValue;
      this.flag = other.flag;
      this.help = other.help;
      this.choices = other.choices;
   }

   public Option(JsonObject json) {
      this();
      OptionConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      OptionConverter.toJson(this, json);
      return json;
   }

   public void ensureValidity() {
      if ((this.shortName == null || this.shortName.equals("\u0000")) && (this.longName == null || this.longName.equals("\u0000"))) {
         throw new IllegalArgumentException("An option needs at least a long name or a short name");
      }
   }

   public boolean acceptValue() {
      return this.singleValued || this.multiValued;
   }

   public String getName() {
      return this.longName != null && !this.longName.equals("\u0000") ? this.longName : this.shortName;
   }

   public boolean isMultiValued() {
      return this.multiValued;
   }

   public Option setMultiValued(boolean multiValued) {
      this.multiValued = multiValued;
      if (this.multiValued) {
         this.singleValued = true;
      }

      return this;
   }

   public boolean isSingleValued() {
      return this.singleValued;
   }

   public Option setSingleValued(boolean singleValued) {
      this.singleValued = singleValued;
      return this;
   }

   public String getArgName() {
      return this.argName;
   }

   public Option setArgName(String argName) {
      Objects.requireNonNull(argName);
      this.argName = argName;
      return this;
   }

   public String getDescription() {
      return this.description;
   }

   public Option setDescription(String description) {
      this.description = description;
      return this;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public Option setHidden(boolean hidden) {
      this.hidden = hidden;
      return this;
   }

   public String getLongName() {
      return this.longName;
   }

   public Option setLongName(String longName) {
      this.longName = longName;
      return this;
   }

   public boolean isRequired() {
      return this.required;
   }

   public Option setRequired(boolean required) {
      this.required = required;
      return this;
   }

   public String getShortName() {
      return this.shortName;
   }

   public Option setShortName(String shortName) {
      this.shortName = shortName;
      return this;
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public Option setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
      if (this.defaultValue != null) {
         this.setRequired(false);
      }

      return this;
   }

   public boolean isFlag() {
      return this.flag;
   }

   public Option setFlag(boolean flag) {
      this.flag = flag;
      if (flag) {
         this.setSingleValued(false);
      }

      return this;
   }

   public boolean isHelp() {
      return this.help;
   }

   public Option setHelp(boolean help) {
      this.help = help;
      return this;
   }

   public Set getChoices() {
      return this.choices;
   }

   public Option setChoices(Set choices) {
      this.choices = choices;
      return this;
   }

   public Option addChoice(String choice) {
      this.choices.add(choice);
      return this;
   }
}
