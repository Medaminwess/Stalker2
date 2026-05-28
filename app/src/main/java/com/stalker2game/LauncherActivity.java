package com.stalker2game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import java.io.*;

/**
 * Stalker 2 (English Translation) - Standalone Launcher
 * Extracts game.jar to device storage and launches it via J2ME Loader runtime.
 */
public class LauncherActivity extends Activity {

    private static final String GAME_JAR = "game.jar";
    private static final String GAME_DIR = "Stalker2EN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        extractAndLaunch();
    }

    private void extractAndLaunch() {
        new Thread(() -> {
            try {
                // Extract JAR from assets to app's private files directory
                File gameDir = new File(getFilesDir(), GAME_DIR);
                gameDir.mkdirs();
                File jarFile = new File(gameDir, GAME_JAR);

                // Copy only if not already extracted
                if (!jarFile.exists()) {
                    try (InputStream in = getAssets().open(GAME_JAR);
                         FileOutputStream out = new FileOutputStream(jarFile)) {
                        byte[] buf = new byte[8192];
                        int len;
                        while ((len = in.read(buf)) != -1) out.write(buf, 0, len);
                    }
                }

                // Launch via J2ME Loader's emulator activity
                Intent intent = new Intent(this,
                    ru.playsoftware.j2meloader.EmulatorActivity.class);
                intent.putExtra("midletPath", jarFile.getAbsolutePath());
                intent.putExtra("midletName", "Stalker 2 (EN)");
                startActivity(intent);
                finish();

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    android.widget.Toast.makeText(this,
                        "Error: " + e.getMessage(),
                        android.widget.Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }
}
