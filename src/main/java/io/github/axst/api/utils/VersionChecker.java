package io.github.axst.api.utils;

import io.github.axst.Limee;
import io.github.axst.api.logger.Logger;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.Scanner;

public class VersionChecker {

    @SneakyThrows
    public void check() {
        URL url = new URL("https://raw.githubusercontent.com/AxstSoftware/Limee/main/version.txt");
        Scanner scanner = new Scanner(url.openStream());
        String release = scanner.nextLine();
        if (release.startsWith("version ")) {
            release = release.split(" ")[1];
            if (!release.equals(IReferences.VERSION)) {
                Limee.getInstance().getLogger().sendLog("We found an update for Limee [" + release + "]", Logger.LogLevel.INFO);
            } else {
                Limee.getInstance().getLogger().sendLog("No new updates for Limee", Logger.LogLevel.INFO);
            }
        }
    }

}
