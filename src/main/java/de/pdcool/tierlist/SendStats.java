package de.pdcool.tierlist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class SendStats {
    static Gson gson = new Gson();
    public static void sendStatsOf(String playerName) {
        CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL("https://mctiers.com/api/search_profile/" + playerName);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();

                int status = connection.getResponseCode();
                System.out.println(status);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();
                System.out.println(content.toString());
                return gson.fromJson(content.toString(), JsonObject.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).thenAccept(jsonObject -> {
            String opponent = jsonObject.get("name").getAsString();
            String region = jsonObject.get("region").getAsString();
            int overall = jsonObject.get("overall").getAsInt();
            int points = jsonObject.get("points").getAsInt();

            send("§cStats of " + opponent + "(" + region + "):");
            send("§aOverall: " + overall);
            send("§aPoints: " + points);
            JsonObject rankings = jsonObject.get("rankings").getAsJsonObject();
            for (String key : rankings.keySet()) {
                JsonObject ranking = rankings.get(key).getAsJsonObject();
                int tier = ranking.get("tier").getAsInt();
                int pos = ranking.get("pos").getAsInt();
                int peak_tier = ranking.get("peak_tier").getAsInt();
                int peak_pos = ranking.get("peak_pos").getAsInt();
                long attained = ranking.get("attained").getAsLong();
                boolean retired = ranking.get("retired").getAsBoolean();
                send("§4" + key + ": §a" + convertRankingToString(tier, pos, peak_tier, peak_pos, attained, retired));
            }
        }).exceptionally(e -> {
            send("§cPlayer not found!");
            return null;
        });
    }
    private static void send(String message) {
        MinecraftClient.getInstance().player.sendMessage(Text.of(message));
    }

    private static String convertRankingToString(int tier, int pos, int peak_tier, int peak_pos, long attained, boolean retired) {
        String result = pos == 0 ? "H" + tier : "L" + tier;
        if (retired) {
            result += " (Retired)";
        } else {
            if (peak_tier!= tier || peak_pos != pos) {
                result += " (Peak: " + (peak_pos == 0 ? "H" + peak_tier : "L" + peak_tier) + ")    ";
            }
            result += " §o§6" + new Date(attained * 1000).toLocaleString().substring(0,10) + "§o";
        }
        return result;
    }
}
