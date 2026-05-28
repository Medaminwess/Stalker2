package com.stalker2game;
import android.app.*;
import android.content.Intent;
import android.os.*;
import android.widget.Toast;
import java.io.*;

public class LauncherActivity extends Activity {
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        new Thread(() -> {
            try {
                File dir = new File(getFilesDir(), "games");
                dir.mkdirs();
                File jar = new File(dir, "stalker2.jar");
                if (!jar.exists()) {
                    InputStream in = getAssets().open("game.jar");
                    FileOutputStream out = new FileOutputStream(jar);
                    byte[] buf = new byte[8192]; int n;
                    while ((n = in.read(buf)) != -1) out.write(buf, 0, n);
                    in.close(); out.close();
                }
                Intent i = getPackageManager()
                    .getLaunchIntentForPackage("ru.playsoftware.j2meloader");
                runOnUiThread(() -> {
                    if (i != null) startActivity(i);
                    else Toast.makeText(this,"Install J2ME Loader first",1).show();
                });
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(this, e.getMessage(), 1).show());
            }
        }).start();
    }
}
