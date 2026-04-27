package net.poley.shake;

import net.fabricmc.api.DedicatedServerModInitializer;

import java.io.File;
import java.io.IOException;

public class ShakeModServer implements DedicatedServerModInitializer{

    @Override
    public void onInitializeServer() {
        createRequestsFolder();
        NetworkHandler.registerServer();
    }

    private void createRequestsFolder() {
        File requestsFolder = new File("requests");
        if (!requestsFolder.exists()) {
            boolean created = requestsFolder.mkdirs();
            if (created) {
                System.out.println("Requests folder created.");
            } else {
                System.err.println("Failed to create requests folder.");
            }
        }
    }
}
