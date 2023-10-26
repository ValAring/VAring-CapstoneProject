package de.neuefische.backend;
import java.time.ZonedDateTime;

public class DateTimeService {
    public ZonedDateTime getCurrentDateTime() {
        return ZonedDateTime.now();
    }
}
