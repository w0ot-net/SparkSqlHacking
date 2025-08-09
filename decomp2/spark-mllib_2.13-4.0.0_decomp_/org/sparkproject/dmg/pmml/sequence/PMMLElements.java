package org.sparkproject.dmg.pmml.sequence;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field ANTECEDENTSEQUENCE_EXTENSIONS = ReflectionUtil.getField(AntecedentSequence.class, "extensions");
   Field ANTECEDENTSEQUENCE_SEQUENCEREFERENCE = ReflectionUtil.getField(AntecedentSequence.class, "sequenceReference");
   Field ANTECEDENTSEQUENCE_TIME = ReflectionUtil.getField(AntecedentSequence.class, "time");
   Field CONSEQUENTSEQUENCE_EXTENSIONS = ReflectionUtil.getField(ConsequentSequence.class, "extensions");
   Field CONSEQUENTSEQUENCE_SEQUENCEREFERENCE = ReflectionUtil.getField(ConsequentSequence.class, "sequenceReference");
   Field CONSEQUENTSEQUENCE_TIME = ReflectionUtil.getField(ConsequentSequence.class, "time");
   Field CONSTRAINTS_EXTENSIONS = ReflectionUtil.getField(Constraints.class, "extensions");
   Field DELIMITER_EXTENSIONS = ReflectionUtil.getField(Delimiter.class, "extensions");
   Field SEQUENCE_EXTENSIONS = ReflectionUtil.getField(Sequence.class, "extensions");
   Field SEQUENCE_SETREFERENCE = ReflectionUtil.getField(Sequence.class, "setReference");
   Field SEQUENCE_CONTENT = ReflectionUtil.getField(Sequence.class, "content");
   Field SEQUENCE_TIME = ReflectionUtil.getField(Sequence.class, "time");
   Field SEQUENCEMODEL_EXTENSIONS = ReflectionUtil.getField(SequenceModel.class, "extensions");
   Field SEQUENCEMODEL_MININGSCHEMA = ReflectionUtil.getField(SequenceModel.class, "miningSchema");
   Field SEQUENCEMODEL_MODELSTATS = ReflectionUtil.getField(SequenceModel.class, "modelStats");
   Field SEQUENCEMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(SequenceModel.class, "localTransformations");
   Field SEQUENCEMODEL_CONSTRAINTS = ReflectionUtil.getField(SequenceModel.class, "constraints");
   Field SEQUENCEMODEL_ITEMS = ReflectionUtil.getField(SequenceModel.class, "items");
   Field SEQUENCEMODEL_ITEMSETS = ReflectionUtil.getField(SequenceModel.class, "itemsets");
   Field SEQUENCEMODEL_SETPREDICATES = ReflectionUtil.getField(SequenceModel.class, "setPredicates");
   Field SEQUENCEMODEL_SEQUENCES = ReflectionUtil.getField(SequenceModel.class, "sequences");
   Field SEQUENCEMODEL_SEQUENCERULES = ReflectionUtil.getField(SequenceModel.class, "sequenceRules");
   Field SEQUENCEREFERENCE_EXTENSIONS = ReflectionUtil.getField(SequenceReference.class, "extensions");
   Field SETPREDICATE_EXTENSIONS = ReflectionUtil.getField(SetPredicate.class, "extensions");
   Field SETPREDICATE_ARRAY = ReflectionUtil.getField(SetPredicate.class, "array");
   Field SETREFERENCE_EXTENSIONS = ReflectionUtil.getField(SetReference.class, "extensions");
   Field TIME_EXTENSIONS = ReflectionUtil.getField(Time.class, "extensions");
}
