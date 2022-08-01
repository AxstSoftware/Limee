package io.github.axst.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Module {
    @Getter
    public String name;
    @Getter
    public String description;
    @Getter
    public int version;
    @Getter @Setter
    public Boolean enabled;

    public void toggleModule() {
        setEnabled(!getEnabled());
    }
}
