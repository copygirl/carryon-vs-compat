package com.github.copygirl.carryonvscompat.fabric;

import com.github.copygirl.carryonvscompat.CarryOnVSCompat;

import net.fabricmc.api.ModInitializer;

public class CarryOnVSCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CarryOnVSCompat.init();
    }
}
