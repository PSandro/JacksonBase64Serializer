import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Before;
import org.junit.Test;
import wrapper.Base64Wrapper;
import wrapper.Base64WrapperSerializer;

import java.io.IOException;

import static org.junit.Assert.*;

public class FooSerializerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private static String FOO_JSON_BASE64 = "\"eyJmaWVsZDEiOiJmb28iLCJmaWVsZDIiOjQyfQ==\"";

    @Before
    public void setup() throws IOException {
        SimpleModule module = new SimpleModule();
        module.addSerializer(new Base64WrapperSerializer());
        this.mapper.registerModule(module);
        this.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    public void testFooSerialize() throws IOException {
        final Foo foo = new Foo();
        final Base64Wrapper<Foo> base64Wrapper = Base64Wrapper.of(foo);

        final String base64Json = this.mapper.writeValueAsString(base64Wrapper);

        assertNotNull(base64Json);
        assertEquals(FOO_JSON_BASE64, base64Json);
    }

}
