package com.neoception.workshop.edge.scans;

import com.neoception.workshop.edge.eventHub.bus.EventBus;
import com.neoception.workshop.edge.utils.DateTimeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScansGenerator {

    private final EventBus eventBus;
	private final ScansService scansService;

	@Autowired
    public ScansGenerator(EventBus eventBus) {
        this.eventBus = eventBus;
        this.scansService = new ScansService(new DateTimeImpl(), eventBus);
    }

    // Executes every 5 seconds
    @Scheduled(fixedRate=5000)
    public void generateScan() {
        scansService.generateRandomScan();
    }
}