package dev.g30v4.spring.data.cfg;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.ofInstant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import org.bson.types.Decimal128;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.mongodb.lang.NonNull;

@Configuration
public class MongoConf extends AbstractMongoClientConfiguration {
    
    private final MongoProperties mongoProperties;

    MongoConf(final MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Override
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new DateToZonedDateTimeConverter(),
                new ZonedDateTimeToDateConverter(),
                new BigDecimalDecimal128Converter(),
                new Decimal128BigDecimalConverter()
        ));
    }

    @ReadingConverter
    class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ofInstant(source.toInstant(), systemDefault());
        }
    }

    @WritingConverter
    class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

        @Override
        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    @WritingConverter
    private static class BigDecimalDecimal128Converter implements Converter<BigDecimal, Decimal128> {

        @Override
        public Decimal128 convert(@NonNull BigDecimal source) {
            if (source.scale() > 9) {
                source = source.setScale(10, RoundingMode.CEILING);
            }
            return new Decimal128(source);
        }
    }

    @ReadingConverter
    private static class Decimal128BigDecimalConverter implements Converter<Decimal128, BigDecimal> {

        @Override
        public BigDecimal convert(@NonNull Decimal128 source) {
            return source.bigDecimalValue();
        }

    }

    @Override
    protected String getDatabaseName() {
        return this.mongoProperties.getMongoClientDatabase();
    }
}
