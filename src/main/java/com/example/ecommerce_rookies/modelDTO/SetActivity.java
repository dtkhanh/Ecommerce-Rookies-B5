package com.example.ecommerce_rookies.modelDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetActivity {
    private String set_activity;
    public SetActivity(){}

    public SetActivity(String set_activity) {
        this.set_activity = set_activity;
    }
}
