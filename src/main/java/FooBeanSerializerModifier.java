import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class FooBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public JsonSerializer<?> modifySerializer(
            SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {

        if (beanDesc.getBeanClass().equals(Foo.class)) {
            return new FooSerializer((JsonSerializer<Object>) serializer);
        }
        return serializer;
    }
}