package com.homeautomation.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RoomTest {
    @SuppressWarnings("unchecked")
    @Test
    public void invalidRoomCannotBeCreatedAndGivesProperMessage() throws Exception {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Set<ConstraintViolation<Room>> violations = validator.validate(new Room("room", 1200));
        ConstraintViolation<Room>[] violationsArray = violations.toArray(new ConstraintViolation[violations.size()]);

        assertEquals(violations.size(), 1);
        assertEquals("Room size must be less than 1,000", violationsArray[0].getMessage());
    }
}
