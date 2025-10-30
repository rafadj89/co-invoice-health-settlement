package com.reven.rips.application.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CuvFileReceivedEvent extends ApplicationEvent {
    private final byte[] fileContent;
    private final String originalFilename;
    private final Long nit;

    public CuvFileReceivedEvent(Object source, byte[] fileContent, String originalFilename, Long nit) {
        super(source);
        this.fileContent = fileContent;
        this.originalFilename = originalFilename;
        this.nit = nit;
    }
}