package es.udc.fi.lbd.monuzz.id.hospital.converters;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

import java.sql.Date;
import java.time.LocalDate;


@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
	
    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
    	return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
