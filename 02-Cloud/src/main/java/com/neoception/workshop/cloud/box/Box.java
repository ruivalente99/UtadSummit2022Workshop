package com.neoception.workshop.cloud.box;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
public class Box {
    @Id
    private UUID id;
    private UUID boxId;
    private ZonedDateTime timestamp;
}
