package org.sw.alarm;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Mateusz on 15/01/2017.
 */

public final class BluetoothUtils {

    private BluetoothUtils() {
    }

    public static boolean setBluetooth(boolean enable) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean isEnabled = bluetoothAdapter.isEnabled();
        if (enable && !isEnabled) {
            return bluetoothAdapter.enable();
        }
        else if(!enable && isEnabled) {
            return bluetoothAdapter.disable();
        }
        // No need to change bluetooth state
        return true;
    }

}
