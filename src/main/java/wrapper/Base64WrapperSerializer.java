package wrapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;

public class Base64WrapperSerializer extends StdSerializer<Base64Wrapper> {


    public Base64WrapperSerializer() {
        super(Base64Wrapper.class);
    }

    @Override
    public void serialize(Base64Wrapper value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator tempGen = provider.getGenerator().getCodec().getFactory().createGenerator(stringWriter);
        provider.defaultSerializeValue(value.getWrapped(), tempGen);
        tempGen.flush();

        String json = stringWriter.toString();
        String base64 = new String(Base64.getEncoder().encode(json.getBytes()));
        gen.writeString(base64);
    }
}