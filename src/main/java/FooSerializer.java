import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;

class FooSerializer extends StdSerializer<Foo> {

    private final JsonSerializer<Object> defaultSerializer;

    public FooSerializer(JsonSerializer<Object> defaultSerializer) {
        super(Foo.class);
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(Foo value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator tempGen = provider.getGenerator().getCodec().getFactory().createGenerator(stringWriter);
        defaultSerializer.serialize(value, tempGen, provider);

        tempGen.flush();

        String json = stringWriter.toString();
        String base64 = new String(Base64.getEncoder().encode(json.getBytes()));
        gen.writeString(base64);
    }
}