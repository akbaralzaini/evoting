package project.akbaralzaini.evoting.adminactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import project.akbaralzaini.evoting.R;
import project.akbaralzaini.evoting.siswaActivity.EditProfilActivity;

public class ProfilActivity extends Activity {

    Button btnEditProfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        btnEditProfil = findViewById(R.id.button_edit_profil);

        btnEditProfil.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), EditProfilActivity.class);
                startActivity(nextScreen);

            }
        });

    }
}
