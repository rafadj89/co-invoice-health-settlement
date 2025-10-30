package com.reven.rips.application.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RipsFileReceivedEvent extends ApplicationEvent {
    private final byte[] fileContent;
    private final String originalFilename;
    private final String nit;

    public RipsFileReceivedEvent(Object source, byte[] fileContent, String originalFilename, String nit) {
        super(source);
        this.fileContent = fileContent;
        this.originalFilename = originalFilename;
        this.nit = nit;
    }
}