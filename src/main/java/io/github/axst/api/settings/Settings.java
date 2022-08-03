package io.github.axst.api.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Settings {
    @Getter
    public String name;
    @Getter
    public double version;
}
