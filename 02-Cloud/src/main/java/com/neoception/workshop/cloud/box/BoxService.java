package com.neoception.workshop.cloud.box;

import com.neoception.workshop.cloud.utils.DateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository boxRepository;
    private final DateTime dateTime;

    public List<Box> getAll(Pageable pageable) {
        return boxRepository.findAll(pageable);
    }

    @Transactional
    public Box create(UUID boxId, ZonedDateTime timestamp) {

        log.debug("BOX | DB CREATION | BEGIN | [boxId={}, timestamp={}]", boxId, timestamp);

        var box = new Box();
        box.setId(UUID.randomUUID());
        box.setBoxId(boxId);
        box.setTimestamp(timestamp);
        boxRepository.save(box);

        log.debug("BOX | DB CREATION | END | [box={}]", box);

        return box;
    }
}
