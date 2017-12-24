package edu.bracketly.frontend.navigation;

import android.content.Context;
import android.content.Intent;

import edu.bracketly.frontend.app.main.MainActivity;

/**
 * Created by howor on 23.12.2017.
 */

public final class Navigator {
    private Navigator() {
    }

    public static void openMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
