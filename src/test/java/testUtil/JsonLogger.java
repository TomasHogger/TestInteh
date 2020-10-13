package testUtil;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonLogger {

    private static final String FILE_PATH = "test_results.json";

    public static void log(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        Log testResult;

        if (file.length() == 0) {
            testResult = new Log(new ArrayList());
        } else {
            try {
                testResult = objectMapper.readValue(file, Log.class);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        testResult.list.add(object);

        try {
            objectMapper.writeValue(file, testResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class LocalDateSerializer extends StdSerializer<LocalDateTime> {

        public LocalDateSerializer() {
            super(LocalDateTime.class);
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            generator.writeString(value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        }
    }

    public static class LocalDateDeserializer extends StdDeserializer<LocalDateTime> {

        protected LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override
        public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            return LocalDateTime.parse(parser.readValueAs(String.class));
        }
    }

    public static class LocalDateSerializerForResult extends StdSerializer<LocalDate> {

        public LocalDateSerializerForResult() {
            super(LocalDate.class);
        }

        @Override
        public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    public static class LocalDateDeserializerForResult extends StdDeserializer<LocalDate> {

        protected LocalDateDeserializerForResult() {
            super(LocalDate.class);
        }

        @Override
        public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            return LocalDate.parse(parser.readValueAs(String.class));
        }
    }

    @Data
    public static class TestResult {
        @JsonProperty("TestName")
        private String testName;
        @JsonProperty("StartDateTime")
        @JsonDeserialize(using = JsonLogger.LocalDateDeserializer.class)
        @JsonSerialize(using = JsonLogger.LocalDateSerializer.class)
        private LocalDateTime startDateTime;
        @JsonProperty("EndDateTime")
        @JsonDeserialize(using = JsonLogger.LocalDateDeserializer.class)
        @JsonSerialize(using = JsonLogger.LocalDateSerializer.class)
        private LocalDateTime endDateTime;
    }

    @Data
    private static class Log {
        @JsonProperty("TestResults")
        private List list;
        public Log(List list) {
            this.list = list;
        }
        public Log() {}
    }
}
