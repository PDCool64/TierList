package de.pdcool.tierlist;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class TierList implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("Hello from the server side!");
    }
}
/*

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class DuelStatsMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Registriere einen Event-Listener für das ClientTickEvents.START_WORLD_TICK
        ClientTickEvents.START_WORLD_TICK.register(client -> {
            // Überprüfe den Chat-Verlauf auf neue Nachrichten
            while (MinecraftClient.getInstance().inGameHud.getChatHud().getMessageCount() > 0) {
                // Hole die nächste Chat-Nachricht
                Text chatMessage = MinecraftClient.getInstance().inGameHud.getChatHud().getMessage(0).getMessage();
                // Hier kannst du die Nachricht verarbeiten
                String message = chatMessage.getString();
                // Überprüfe, ob die Nachricht auf ein Duell hinweist
                if (isDuelStartMessage(message)) {
                    // Extrahiere den Namen des Gegners aus der Nachricht
                    String opponentName = extractOpponentName(message);
                    // Rufe die Statistiken des Gegners von einer externen API ab
                    PlayerStats opponentStats = fetchOpponentStats(opponentName);
                    // Zeige die Statistiken des Gegners im Spiel an
                    displayOpponentStats(opponentStats);
                }
                // Entferne die verarbeitete Nachricht aus dem Chat-Verlauf
                MinecraftClient.getInstance().inGameHud.getChatHud().removeMessage(0);
            }
        });
    }

    private boolean isDuelStartMessage(String message) {
        // Überprüfe, ob die Nachricht auf ein Duell hinweist
        // Beispiel: Nachricht enthält "Du wurdest zu einem Duell von Spieler XY herausgefordert"
        // Implementiere deine Logik entsprechend
        return message.contains("Du wurdest zu einem Duell herausgefordert");
    }

    private String extractOpponentName(String message) {
        // Extrahiere den Namen des Gegners aus der Nachricht
        // Beispiel: Suche nach dem Muster "von Spieler [Name]" und extrahiere den Namen
        // Implementiere deine Logik entsprechend
        return "GegnerXYZ";
    }

    private PlayerStats fetchOpponentStats(String opponentName) {
        // Rufe die Statistiken des Gegners von einer externen API ab
        // Beispiel: Verwende eine HTTP-Anfrage, um die Statistiken des Spielers von einer API zu erhalten
        // Implementiere deine Logik entsprechend
        return new PlayerStats();
        }
private void displayOpponentStats(PlayerStats opponentStats) {
    // Zeige die Statistiken des Gegners im Spiel an
    // Beispiel: Sende eine Chat-Nachricht mit den Statistiken des Gegners
    // Implementiere deine Logik entsprechend
    MinecraftClient.getInstance().player.sendMessage(
            Text.of("Statistiken des Gegners: " + opponentStats),
            false
    );
}
}
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class DuelStatsMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Registriere einen Event-Listener für das ClientTickEvents.START_WORLD_TICK
        ClientTickEvents.START_WORLD_TICK.register(client -> {
            // Überprüfe den Chat-Verlauf auf neue Nachrichten
            int messageCount = MinecraftClient.getInstance().inGameHud.getChatHud().getMessageCount();
            for (int i = 0; i < messageCount; i++) {
                // Hole die nächste Chat-Nachricht
                Text chatMessage = MinecraftClient.getInstance().inGameHud.getChatHud().getMessage(i).getMessage();
                // Hier kannst du die Nachricht verarbeiten
                String message = chatMessage.getString();
                // Überprüfe, ob die Nachricht auf ein Duell hinweist
                if (isDuelStartMessage(message)) {
                    // Extrahiere den Namen des Gegners aus der Nachricht
                    String opponentName = extractOpponentName(message);
                    // Rufe die Statistiken des Gegners von einer externen API ab
                    PlayerStats opponentStats = fetchOpponentStats(opponentName);
                    // Zeige die Statistiken des Gegners im Spiel an
                    displayOpponentStats(opponentStats);
                }
            }
        });
    }

    private boolean isDuelStartMessage(String message) {
        // Überprüfe, ob die Nachricht auf ein Duell hinweist
        // Beispiel: Nachricht enthält "Du wurdest zu einem Duell von Spieler XY herausgefordert"
        // Implementiere deine Logik entsprechend
        return message.contains("Du wurdest zu einem Duell herausgefordert");
    }

    private String extractOpponentName(String message) {
        // Extrahiere den Namen des Gegners aus der Nachricht
        // Beispiel: Suche nach dem Muster "von Spieler [Name]" und extrahiere den Namen
        // Implementiere deine Logik entsprechend
        return "GegnerXYZ";
    }

    private PlayerStats fetchOpponentStats(String opponentName) {
        // Rufe die Statistiken des Gegners von einer externen API ab
        // Beispiel: Verwende eine HTTP-Anfrage, um die Statistiken des Spielers von einer API zu erhalten
        // Implementiere deine Logik entsprechend
        return new PlayerStats();
        }

private void displayOpponentStats(PlayerStats opponentStats) {
    // Zeige die Statistiken des Gegners im Spiel an
    // Beispiel: Sende eine Chat-Nachricht mit den Statistiken des Gegners
    // Implementiere deine Logik entsprechend
    MinecraftClient.getInstance().player.sendMessage(
            Text.of("Statistiken des Gegners: " + opponentStats),
            false
    );
}
}
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class DuelStatsMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Registriere einen Event-Listener für das ClientTickEvents.START_WORLD_TICK
        ClientTickEvents.START_WORLD_TICK.register(client -> {
            checkNewMessages();
        });
    }

    private void checkNewMessages() {
        // Hole die Anzahl der neuen Chat-Nachrichten seit dem letzten Durchlauf
        int messageCount = MinecraftClient.getInstance().inGameHud.getChatHud().getMessageCount();
        // Überprüfe nur die neuen Nachrichten seit dem letzten Durchlauf
        for (int i = messageCount - 1; i >= 0; i--) {
            // Hole die nächste Chat-Nachricht
            Text chatMessage = MinecraftClient.getInstance().inGameHud.getChatHud().getMessage(i).getMessage();
            // Hier kannst du die Nachricht verarbeiten
            String message = chatMessage.getString();
            // Überprüfe, ob die Nachricht auf ein Duell hinweist
            if (isDuelStartMessage(message)) {
                // Extrahiere den Namen des Gegners aus der Nachricht
                String opponentName = extractOpponentName(message);
                // Rufe die Statistiken des Gegners von einer externen API ab
                PlayerStats opponentStats = fetchOpponentStats(opponentName);
                // Zeige die Statistiken des Gegners im Spiel an
                displayOpponentStats(opponentStats);
                // Verlasse die Schleife, da wir nur auf die neueste Nachricht reagieren wollen
                break;
            }
        }
    }

    private boolean isDuelStartMessage(String message) {
        // Überprüfe, ob die Nachricht auf ein Duell hinweist
        // Beispiel: Nachricht enthält "Du wurdest zu einem Duell von Spieler XY herausgefordert"
        // Implementiere deine Logik entsprechend
        return message.contains("Du wurdest zu einem Duell herausgefordert");
    }

    private String extractOpponentName(String message) {
        // Extrahiere den Namen des Gegners aus der Nachricht
        // Beispiel: Suche nach dem Muster "von Spieler [Name]" und extrahiere den Namen
        // Implementiere deine Logik entsprechend
        return "GegnerXYZ";
    }

    private PlayerStats fetchOpponentStats(String opponentName) {
        // Rufe die Statistiken des Gegners von einer externen API ab
        // Beispiel: Verwende eine HTTP-Anfrage, um die Statistiken des Spielers von einer API zu erhalten
        // Implementiere deine Logik entsprechend
        return new PlayerStats();
        }

private void displayOpponentStats(PlayerStats opponentStats) {
    // Zeige die Statistiken des Gegners im Spiel an
    // Beispiel: Sende eine Chat-Nachricht mit den Statistiken des Gegners
    // Implementiere deine Logik entsprechend
    MinecraftClient.getInstance().player.sendMessage(
            Text.of("Statistiken des Gegners: " + opponentStats),
            false
    );
}
 */